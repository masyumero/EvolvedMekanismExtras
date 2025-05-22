package io.github.masyumero.emextras.common.tile.factory;

import io.github.masyumero.emextras.common.inventory.slot.EMExtraStackableInputInventorySlot;
import mekanism.api.IContentsListener;
import mekanism.api.inventory.IInventorySlot;
import mekanism.api.math.MathUtils;
import mekanism.api.providers.IBlockProvider;
import mekanism.api.recipes.CombinerRecipe;
import mekanism.api.recipes.cache.CachedRecipe;
import mekanism.api.recipes.cache.CachedRecipe.OperationTracker.RecipeError;
import mekanism.api.recipes.cache.TwoInputCachedRecipe;
import mekanism.api.recipes.inputs.IInputHandler;
import mekanism.api.recipes.inputs.InputHelper;
import mekanism.common.Mekanism;
import mekanism.common.capabilities.holder.slot.InventorySlotHelper;
import mekanism.common.integration.computer.SpecialComputerMethodWrapper;
import mekanism.common.integration.computer.annotation.WrappingComputerMethod;
import mekanism.common.inventory.container.slot.ContainerSlotType;
import mekanism.common.recipe.IMekanismRecipeTypeProvider;
import mekanism.common.recipe.MekanismRecipeType;
import mekanism.common.recipe.lookup.IDoubleRecipeLookupHandler;
import mekanism.common.recipe.lookup.cache.InputRecipeCache;
import mekanism.common.upgrade.CombinerUpgradeData;
import mekanism.common.upgrade.IUpgradeData;
import mekanism.common.util.InventoryUtils;
import mekanism.common.util.MekanismUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;

public class TileEntityCombiningEMExtraFactory  extends TileEntityItemToItemEMExtraFactory<CombinerRecipe> implements IDoubleRecipeLookupHandler.DoubleItemRecipeLookupHandler<CombinerRecipe> {

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

    private final IInputHandler<@NotNull ItemStack> extraInputHandler;

    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.class, methodNames = "getSecondaryInput", docPlaceholder = "secondary input slot")
    EMExtraStackableInputInventorySlot extraSlot;

    public TileEntityCombiningEMExtraFactory(IBlockProvider blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, TRACKED_ERROR_TYPES, GLOBAL_ERROR_TYPES);
        extraInputHandler = InputHelper.getInputHandler(extraSlot, RecipeError.NOT_ENOUGH_SECONDARY_INPUT);
    }

    @Override
    protected void addSlots(InventorySlotHelper builder, IContentsListener listener, IContentsListener updateSortingListener) {
        super.addSlots(builder, listener, updateSortingListener);
        builder.addSlot(extraSlot = EMExtraStackableInputInventorySlot.at(this, this::containsRecipeB, markAllMonitorsChanged(listener), 7, 57));
        extraSlot.setSlotType(ContainerSlotType.EXTRA);
    }

    @Nullable
    @Override
    protected EMExtraStackableInputInventorySlot getExtraSlot() {
        return extraSlot;
    }

    @Override
    public boolean isValidInputItem(@NotNull ItemStack stack) {
        return containsRecipeA(stack);
    }

    @Override
    protected int getNeededInput(CombinerRecipe recipe, ItemStack inputStack) {
        return MathUtils.clampToInt(recipe.getMainInput().getNeededAmount(inputStack));
    }

    @Override
    protected boolean isCachedRecipeValid(@Nullable CachedRecipe<CombinerRecipe> cached, @NotNull ItemStack stack) {
        if (cached != null) {
            CombinerRecipe cachedRecipe = cached.getRecipe();
            return cachedRecipe.getMainInput().testType(stack) && (extraSlot.isEmpty() || cachedRecipe.getExtraInput().testType(extraSlot.getStack()));
        }
        return false;
    }

    @Override
    protected CombinerRecipe findRecipe(int process, @NotNull ItemStack fallbackInput, @NotNull IInventorySlot outputSlot, @Nullable IInventorySlot secondaryOutputSlot) {
        ItemStack extra = extraSlot.getStack();
        ItemStack output = outputSlot.getStack();
        return getRecipeType().getInputCache().findTypeBasedRecipe(level, fallbackInput, extra,
                recipe -> InventoryUtils.areItemsStackable(recipe.getOutput(fallbackInput, extra), output));
    }

    @NotNull
    @Override
    public IMekanismRecipeTypeProvider<CombinerRecipe, InputRecipeCache.DoubleItem<CombinerRecipe>> getRecipeType() {
        return MekanismRecipeType.COMBINING;
    }

    @Nullable
    @Override
    public CombinerRecipe getRecipe(int cacheIndex) {
        return findFirstRecipe(inputHandlers[cacheIndex], extraInputHandler);
    }

    @NotNull
    @Override
    public CachedRecipe<CombinerRecipe> createNewCachedRecipe(@NotNull CombinerRecipe recipe, int cacheIndex) {
        return TwoInputCachedRecipe.combiner(recipe, recheckAllRecipeErrors[cacheIndex], inputHandlers[cacheIndex], extraInputHandler, outputHandlers[cacheIndex])
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
    public void parseUpgradeData(@NotNull IUpgradeData upgradeData) {
        if (upgradeData instanceof CombinerUpgradeData data) {
            //Generic factory upgrade data handling
            super.parseUpgradeData(upgradeData);
            //Copy the stack using NBT so that if it is not actually valid due to a reload we don't crash
            extraSlot.deserializeNBT(data.extraSlot.serializeNBT());
        } else {
            Mekanism.logger.warn("Unhandled upgrade data.", new Throwable());
        }
    }

    @NotNull
    @Override
    public CombinerUpgradeData getUpgradeData() {
        return new CombinerUpgradeData(redstone, getControlType(), getEnergyContainer(), progress, energySlot, extraSlot, inputSlots, outputSlots, isSorting(), getComponents());
    }

    @Override
    public Component getName() {
        return null;
    }
}