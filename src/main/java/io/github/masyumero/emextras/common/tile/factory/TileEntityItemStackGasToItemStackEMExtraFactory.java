package io.github.masyumero.emextras.common.tile.factory;

import io.github.masyumero.emextras.common.block.attribute.EMExtraAttributeFactoryType;
import io.github.masyumero.emextras.common.config.LoadConfig;
import io.github.masyumero.emextras.common.content.blocktype.EMExtraFactoryType;
import mekanism.api.IContentsListener;
import mekanism.api.NBTConstants;
import mekanism.api.RelativeSide;
import mekanism.api.Upgrade;
import mekanism.api.chemical.ChemicalTankBuilder;
import mekanism.api.chemical.gas.Gas;
import mekanism.api.chemical.gas.GasStack;
import mekanism.api.chemical.gas.IGasTank;
import mekanism.api.inventory.IInventorySlot;
import mekanism.api.math.MathUtils;
import mekanism.api.providers.IBlockProvider;
import mekanism.api.recipes.ItemStackGasToItemStackRecipe;
import mekanism.api.recipes.cache.CachedRecipe;
import mekanism.api.recipes.cache.CachedRecipe.OperationTracker.RecipeError;
import mekanism.api.recipes.cache.ItemStackConstantChemicalToItemStackCachedRecipe;
import mekanism.api.recipes.inputs.ILongInputHandler;
import mekanism.api.recipes.inputs.InputHelper;
import mekanism.common.Mekanism;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.capabilities.holder.chemical.ChemicalTankHelper;
import mekanism.common.capabilities.holder.chemical.IChemicalTankHolder;
import mekanism.common.capabilities.holder.slot.InventorySlotHelper;
import mekanism.common.integration.computer.ComputerException;
import mekanism.common.integration.computer.SpecialComputerMethodWrapper;
import mekanism.common.integration.computer.annotation.ComputerMethod;
import mekanism.common.integration.computer.annotation.WrappingComputerMethod;
import mekanism.common.inventory.slot.chemical.GasInventorySlot;
import mekanism.common.lib.transmitter.TransmissionType;
import mekanism.common.recipe.IMekanismRecipeTypeProvider;
import mekanism.common.recipe.MekanismRecipeType;
import mekanism.common.recipe.lookup.IDoubleRecipeLookupHandler;
import mekanism.common.recipe.lookup.IRecipeLookupHandler;
import mekanism.common.recipe.lookup.cache.InputRecipeCache;
import mekanism.common.tile.interfaces.IHasDumpButton;
import mekanism.common.upgrade.AdvancedMachineUpgradeData;
import mekanism.common.upgrade.IUpgradeData;
import mekanism.common.util.InventoryUtils;
import mekanism.common.util.MekanismUtils;
import mekanism.common.util.StatUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.LongArrayTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

//Compressing, injecting, purifying
public class TileEntityItemStackGasToItemStackEMExtraFactory extends TileEntityItemToItemEMExtraFactory<ItemStackGasToItemStackRecipe> implements IHasDumpButton,
        IDoubleRecipeLookupHandler.ItemChemicalRecipeLookupHandler<Gas, GasStack, ItemStackGasToItemStackRecipe>, IRecipeLookupHandler.ConstantUsageRecipeLookupHandler {

    private static final List<RecipeError> TRACKED_ERROR_TYPES = List.of(
            RecipeError.NOT_ENOUGH_ENERGY,
            RecipeError.NOT_ENOUGH_INPUT,
            RecipeError.NOT_ENOUGH_SECONDARY_INPUT,
            RecipeError.NOT_ENOUGH_OUTPUT_SPACE,
            RecipeError.INPUT_DOESNT_PRODUCE_OUTPUT
    );
    private static final Set<RecipeError> GLOBAL_ERROR_TYPES = Set.of(
            RecipeError.NOT_ENOUGH_ENERGY,
            RecipeError.NOT_ENOUGH_SECONDARY_INPUT
    );

    private final ILongInputHandler<@NotNull GasStack> gasInputHandler;
    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.class, methodNames = "getChemicalItem", docPlaceholder = "chemical item (extra) slot")
    GasInventorySlot extraSlot;
    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.class, methodNames = {"getChemical", "getChemicalCapacity", "getChemicalNeeded",
            "getChemicalFilledPercentage"}, docPlaceholder = "gas tank")
    IGasTank gasTank;
    private final ItemStackConstantChemicalToItemStackCachedRecipe.ChemicalUsageMultiplier gasUsageMultiplier;
    private final long[] usedSoFar;
    private double gasPerTickMeanMultiplier = 1;
    private long baseTotalUsage;

    public TileEntityItemStackGasToItemStackEMExtraFactory(IBlockProvider blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, TRACKED_ERROR_TYPES, GLOBAL_ERROR_TYPES);
        gasInputHandler = InputHelper.getConstantInputHandler(gasTank);
        configComponent.addSupported(TransmissionType.GAS);
        if (allowExtractingChemical()) {
            configComponent.setupIOConfig(TransmissionType.GAS, gasTank, RelativeSide.RIGHT).setCanEject(false);
        } else {
            configComponent.setupInputConfig(TransmissionType.GAS, gasTank);
        }
        baseTotalUsage = BASE_TICKS_REQUIRED;
        usedSoFar = new long[tier.processes];
        if (useStatisticalMechanics()) {
            //Note: Statistical mechanics works best by just using the mean gas usage we want to target
            // rather than adjusting the mean each time to try and reach a given target
            gasUsageMultiplier = (usedSoFar, operatingTicks) -> StatUtils.inversePoisson(gasPerTickMeanMultiplier);
        } else {
            gasUsageMultiplier = (usedSoFar, operatingTicks) -> {
                long baseRemaining = baseTotalUsage - usedSoFar;
                int remainingTicks = type == EMExtraFactoryType.COMPRESSING ? 1 : getTicksRequired() - operatingTicks;
                if (baseRemaining < remainingTicks) {
                    //If we already used more than we would need to use (due to removing speed upgrades or adding gas upgrades)
                    // then just don't use any gas this tick
                    return 0;
                } else if (baseRemaining == remainingTicks) {
                    return 1;
                }
                return Math.max(MathUtils.clampToLong(baseRemaining / (double) remainingTicks), 0);
            };
        }
    }

    @NotNull
    @Override
    public IChemicalTankHolder<Gas, GasStack, IGasTank> getInitialGasTanks(IContentsListener listener) {
        ChemicalTankHelper<Gas, GasStack, IGasTank> builder = ChemicalTankHelper.forSideGasWithConfig(this::getDirection, this::getConfig);
        if (allowExtractingChemical()) {
            gasTank = ChemicalTankBuilder.GAS.create(getGasTankCapacity(), this::containsRecipeB, markAllMonitorsChanged(listener));
        } else {
            gasTank = ChemicalTankBuilder.GAS.create(getGasTankCapacity(), this::containsRecipeB, markAllMonitorsChanged(listener));
        }
        builder.addTank(gasTank);
        return builder.build();
    }

    private long getGasTankCapacity() {
        return switch (tier) {
            case INFINITE_MULTIVERSAL -> LoadConfig.emExtraTankCapacityConfig.EMExtraInfiniteMultiversalFactories.get();
            case COSMIC_DENSE -> LoadConfig.emExtraTankCapacityConfig.EMExtraCosmicDenseOsmiumFactories.get();
            case SUPREME_QUANTUM -> LoadConfig.emExtraTankCapacityConfig.EMExtraSupremeQuantumOsmiumFactories.get();
            case ABSOLUTE_OVERCLOCKED -> LoadConfig.emExtraTankCapacityConfig.EMExtraAbsoluteOverclockedFactories.get();
            case ABSOLUTE, INFINITE, COSMIC, SUPREME -> 0L;
        };
    }

    @Override
    protected void addSlots(InventorySlotHelper builder, IContentsListener listener, IContentsListener updateSortingListener) {
        super.addSlots(builder, listener, updateSortingListener);
        //Note: We care about the gas tank not the slot when it comes to recipes and updating sorting
        builder.addSlot(extraSlot = GasInventorySlot.fillOrConvert(gasTank, this::getLevel, listener, 7, 57));
    }

    public IGasTank getGasTank() {
        return gasTank;
    }

    @Nullable
    @Override
    protected GasInventorySlot getExtraSlot() {
        return extraSlot;
    }

    @Override
    public boolean isValidInputItem(@NotNull ItemStack stack) {
        return containsRecipeA(stack);
    }

    @Override
    protected int getNeededInput(ItemStackGasToItemStackRecipe recipe, ItemStack inputStack) {
        return MathUtils.clampToInt(recipe.getItemInput().getNeededAmount(inputStack));
    }

    @Override
    protected boolean isCachedRecipeValid(@Nullable CachedRecipe<ItemStackGasToItemStackRecipe> cached, @NotNull ItemStack stack) {
        if (cached != null) {
            ItemStackGasToItemStackRecipe cachedRecipe = cached.getRecipe();
            return cachedRecipe.getItemInput().testType(stack) && (gasTank.isEmpty() || cachedRecipe.getChemicalInput().testType(gasTank.getType()));
        }
        return false;
    }

    @Override
    protected ItemStackGasToItemStackRecipe findRecipe(int process, @NotNull ItemStack fallbackInput, @NotNull IInventorySlot outputSlot,
                                                       @Nullable IInventorySlot secondaryOutputSlot) {
        GasStack stored = gasTank.getStack();
        ItemStack output = outputSlot.getStack();
        return getRecipeType().getInputCache().findTypeBasedRecipe(level, fallbackInput, stored,
                recipe -> InventoryUtils.areItemsStackable(recipe.getOutput(fallbackInput, stored), output));
    }

    @Override
    protected void handleSecondaryFuel() {
        extraSlot.fillTankOrConvert();
    }

    @NotNull
    @Override
    public IMekanismRecipeTypeProvider<ItemStackGasToItemStackRecipe, InputRecipeCache.ItemChemical<Gas, GasStack, ItemStackGasToItemStackRecipe>> getRecipeType() {
        return switch (type) {
            case INJECTING -> MekanismRecipeType.INJECTING;
            case PURIFYING -> MekanismRecipeType.PURIFYING;
            default -> MekanismRecipeType.COMPRESSING;
        };
    }

    private boolean allowExtractingChemical() {
        //Note: We can't use type directly as when this is being used for creating the chemical tank the type field hasn't been set yet
        return Attribute.get(blockProvider, EMExtraAttributeFactoryType.class).getFactoryType() == EMExtraFactoryType.COMPRESSING;
    }

    private boolean useStatisticalMechanics() {
        return type == EMExtraFactoryType.INJECTING || type == EMExtraFactoryType.PURIFYING;
    }

    @Nullable
    @Override
    public ItemStackGasToItemStackRecipe getRecipe(int cacheIndex) {
        return findFirstRecipe(inputHandlers[cacheIndex], gasInputHandler);
    }

    @NotNull
    @Override
    public CachedRecipe<ItemStackGasToItemStackRecipe> createNewCachedRecipe(@NotNull ItemStackGasToItemStackRecipe recipe, int cacheIndex) {
        return new ItemStackConstantChemicalToItemStackCachedRecipe<>(recipe, recheckAllRecipeErrors[cacheIndex], inputHandlers[cacheIndex], gasInputHandler,
                gasUsageMultiplier, used -> usedSoFar[cacheIndex] = used, outputHandlers[cacheIndex])
                .setErrorsChanged(errors -> errorTracker.onErrorsChanged(errors, cacheIndex))
                .setCanHolderFunction(() -> MekanismUtils.canFunction(this))
                .setActive(active -> setActiveState(active, cacheIndex))
                .setEnergyRequirements(energyContainer::getEnergyPerTick, energyContainer)
                .setRequiredTicks(this::getTicksRequired)
                .setOnFinish(this::markForSave)
                .setBaselineMaxOperations(() -> baselineMaxOperations)
                .setOperatingTicksChanged(operatingTicks -> progress[cacheIndex] = operatingTicks);
    }

    @Override
    public boolean hasSecondaryResourceBar() {
        return true;
    }

    @Override
    public void load(@NotNull CompoundTag nbt) {
        super.load(nbt);
        if (nbt.contains(NBTConstants.USED_SO_FAR, Tag.TAG_LONG_ARRAY)) {
            long[] savedUsed = nbt.getLongArray(NBTConstants.USED_SO_FAR);
            if (tier.processes != savedUsed.length) {
                Arrays.fill(usedSoFar, 0);
            }
            for (int i = 0; i < tier.processes && i < savedUsed.length; i++) {
                usedSoFar[i] = savedUsed[i];
            }
        } else {
            Arrays.fill(usedSoFar, 0);
        }
    }

    @Override
    public void saveAdditional(@NotNull CompoundTag nbtTags) {
        super.saveAdditional(nbtTags);
        nbtTags.put(NBTConstants.USED_SO_FAR, new LongArrayTag(Arrays.copyOf(usedSoFar, usedSoFar.length)));
    }

    @Override
    public long getSavedUsedSoFar(int cacheIndex) {
        return usedSoFar[cacheIndex];
    }

    @Override
    public void recalculateUpgrades(Upgrade upgrade) {
        super.recalculateUpgrades(upgrade);
        if (upgrade == Upgrade.SPEED || upgrade == Upgrade.GAS && supportsUpgrade(Upgrade.GAS)) {
            if (useStatisticalMechanics()) {
                gasPerTickMeanMultiplier = MekanismUtils.getGasPerTickMeanMultiplier(this);
            } else {
                baseTotalUsage = MekanismUtils.getBaseUsage(this, BASE_TICKS_REQUIRED);
            }
        }
    }

    @Override
    public void parseUpgradeData(@NotNull IUpgradeData upgradeData) {
        if (upgradeData instanceof AdvancedMachineUpgradeData data) {
            //Generic factory upgrade data handling
            super.parseUpgradeData(upgradeData);
            //Copy the contents using NBT so that if it is not actually valid due to a reload we don't crash
            gasTank.deserializeNBT(data.stored.serializeNBT());
            extraSlot.deserializeNBT(data.gasSlot.serializeNBT());
            System.arraycopy(data.usedSoFar, 0, usedSoFar, 0, data.usedSoFar.length);
        } else {
            Mekanism.logger.warn("Unhandled upgrade data.", new Throwable());
        }
    }

    @NotNull
    @Override
    public AdvancedMachineUpgradeData getUpgradeData() {
        return new AdvancedMachineUpgradeData(redstone, getControlType(), getEnergyContainer(), progress, usedSoFar, gasTank, extraSlot, energySlot, inputSlots, outputSlots,
                isSorting(), getComponents());
    }

    @Override
    public void dump() {
        gasTank.setEmpty();
    }

    //Methods relating to IComputerTile
    @ComputerMethod(requiresPublicSecurity = true, methodDescription = "Empty the contents of the gas tank into the environment")
    void dumpChemical() throws ComputerException {
        validateSecurityIsPublic();
        dump();
    }
    //End methods IComputerTile

    @Override
    public Component getName() {
        return null;
    }
}