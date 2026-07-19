package io.github.masyumero.emextras.common.integration.mekaf.tile.factory;

import io.github.masyumero.emextras.common.config.LoadConfig;
import io.github.masyumero.emextras.common.integration.mekaf.tile.factory.base.TileEntityEMExtraMergedToItemFactory;

import mekanism.api.chemical.ChemicalTankBuilder;
import mekanism.api.chemical.merged.MergedChemicalTank;
import mekanism.api.providers.IBlockProvider;
import mekanism.api.recipes.ChemicalCrystallizerRecipe;
import mekanism.api.recipes.cache.CachedRecipe;
import mekanism.api.recipes.cache.CachedRecipe.OperationTracker.RecipeError;
import mekanism.api.recipes.cache.ChemicalCrystallizerCachedRecipe;
import mekanism.api.recipes.inputs.BoxedChemicalInputHandler;
import mekanism.common.lib.transmitter.TransmissionType;
import mekanism.common.recipe.IMekanismRecipeTypeProvider;
import mekanism.common.recipe.MekanismRecipeType;
import mekanism.common.recipe.lookup.cache.ChemicalCrystallizerInputRecipeCache;
import mekanism.common.tile.component.TileComponentEjector;
import mekanism.common.util.MekanismUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

import com.jerry.mekaf.common.upgrade.MergedToItemUpgradeData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;

public class TileEntityEMExtraCrystallizingFactory extends TileEntityEMExtraMergedToItemFactory<ChemicalCrystallizerRecipe> {

    private static final List<RecipeError> TRACKED_ERROR_TYPES = List.of(
            RecipeError.NOT_ENOUGH_ENERGY,
            RecipeError.NOT_ENOUGH_INPUT,
            RecipeError.NOT_ENOUGH_OUTPUT_SPACE,
            RecipeError.INPUT_DOESNT_PRODUCE_OUTPUT);
    private static final Set<RecipeError> GLOBAL_ERROR_TYPES = Set.of(
            RecipeError.NOT_ENOUGH_ENERGY);

    public TileEntityEMExtraCrystallizingFactory(IBlockProvider blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, TRACKED_ERROR_TYPES, GLOBAL_ERROR_TYPES);

        ejectorComponent = new TileComponentEjector(this);
        ejectorComponent.setOutputData(configComponent, TransmissionType.ITEM);
    }

    @Override
    protected void presetVariables() {
        super.presetVariables();
        inputTank = new MergedChemicalTank[tier.processes];
        mergedInputHandlers = new BoxedChemicalInputHandler[tier.processes];

        for (int i = 0; i < tier.processes; i++) {
            inputTank[i] = MergedChemicalTank.create(
                    ChemicalTankBuilder.GAS.input(getTankCapacity(), gas -> getRecipeType().getInputCache().containsInput(level, gas), recipeCacheLookupMonitors[i]),
                    ChemicalTankBuilder.INFUSION.input(getTankCapacity(), infuseType -> getRecipeType().getInputCache().containsInput(level, infuseType), recipeCacheLookupMonitors[i]),
                    ChemicalTankBuilder.PIGMENT.input(getTankCapacity(), pigment -> getRecipeType().getInputCache().containsInput(level, pigment), recipeCacheLookupMonitors[i]),
                    ChemicalTankBuilder.SLURRY.input(getTankCapacity(), slurry -> getRecipeType().getInputCache().containsInput(level, slurry), recipeCacheLookupMonitors[i]));
            mergedInputHandlers[i] = new BoxedChemicalInputHandler(inputTank[i], RecipeError.NOT_ENOUGH_INPUT);
        }
    }

    private long getTankCapacity() {
        if (LoadConfig.EMEXTRA_MORE_CAPACITY_CONFIG.moreCapacityMode.get()) {
            return switch (tier) {
                case INFINITE_MULTIVERSAL -> LoadConfig.EMEXTRA_MORE_CAPACITY_CONFIG.infiniteMultiversalCrystallizing.get();
                case COSMIC_DENSE -> LoadConfig.EMEXTRA_MORE_CAPACITY_CONFIG.cosmicDenseCrystallizing.get();
                case SUPREME_QUANTUM -> LoadConfig.EMEXTRA_MORE_CAPACITY_CONFIG.supremeQuantumCrystallizing.get();
                case ABSOLUTE_OVERCLOCKED -> LoadConfig.EMEXTRA_MORE_CAPACITY_CONFIG.absoluteOverclockedCrystallizing.get();
            };
        } else {
            return MAX_CHEMICAL * tier.processes;
        }
    }

    @NotNull
    public IMekanismRecipeTypeProvider<ChemicalCrystallizerRecipe, ChemicalCrystallizerInputRecipeCache> getRecipeType() {
        return MekanismRecipeType.CRYSTALLIZING;
    }

    @Nullable
    public ChemicalCrystallizerRecipe getRecipe(int cacheIndex) {
        return getRecipeType().getInputCache().findFirstRecipe(level, mergedInputHandlers[cacheIndex].getInput());
    }

    @NotNull
    public CachedRecipe<ChemicalCrystallizerRecipe> createNewCachedRecipe(@NotNull ChemicalCrystallizerRecipe recipe, int cacheIndex) {
        return new ChemicalCrystallizerCachedRecipe(recipe, recheckAllRecipeErrors[cacheIndex], mergedInputHandlers[cacheIndex], itemOutputHandlers[cacheIndex])
                .setErrorsChanged(errors -> errorTracker.onErrorsChanged(errors, cacheIndex))
                .setCanHolderFunction(() -> MekanismUtils.canFunction(this))
                .setActive(active -> setActiveState(active, cacheIndex))
                .setEnergyRequirements(energyContainer::getEnergyPerTick, energyContainer)
                .setRequiredTicks(this::getTicksRequired)
                .setOnFinish(this::markForSave)
                .setOperatingTicksChanged(operatingTicks -> progress[cacheIndex] = operatingTicks)
                .setBaselineMaxOperations(this::getBaselineMaxOperations);
    }

    @Nullable
    public MergedToItemUpgradeData getUpgradeData() {
        return new MergedToItemUpgradeData(redstone, getControlType(), getEnergyContainer(), progress, energySlot, inputChemicalTanks, outputItemSlots, isSorting(), getComponents());
    }
}
