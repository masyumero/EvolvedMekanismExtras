package io.github.masyumero.emextras.common.tile.factory;

import io.github.masyumero.emextras.common.inventory.slot.EMExtraFactoryInputInventorySlot;
import io.github.masyumero.emextras.common.inventory.slot.EMExtraFactoryOutputInventorySlot;
import mekanism.api.IContentsListener;
import mekanism.api.providers.IBlockProvider;
import mekanism.api.recipes.MekanismRecipe;
import mekanism.api.recipes.cache.CachedRecipe;
import mekanism.api.recipes.inputs.IInputHandler;
import mekanism.api.recipes.inputs.InputHelper;
import mekanism.api.recipes.outputs.IOutputHandler;
import mekanism.api.recipes.outputs.OutputHelper;
import mekanism.common.capabilities.holder.slot.InventorySlotHelper;
import mekanism.common.inventory.warning.WarningTracker;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

public abstract class TileEntityItemToItemEMExtraFactory<RECIPE extends MekanismRecipe> extends TileEntityEMExtraFactory<RECIPE> {
    protected IInputHandler<@NotNull ItemStack>[] inputHandlers;
    protected IOutputHandler<@NotNull ItemStack>[] outputHandlers;

    protected TileEntityItemToItemEMExtraFactory(IBlockProvider blockProvider, BlockPos pos, BlockState state, List<CachedRecipe.OperationTracker.RecipeError> errorTypes, Set<CachedRecipe.OperationTracker.RecipeError> globalErrorTypes) {
        super(blockProvider, pos, state, errorTypes, globalErrorTypes);
    }

    protected void addSlots(InventorySlotHelper builder, IContentsListener listener, IContentsListener updateSortingListener) {
        this.inputHandlers = new IInputHandler[this.tier.processes];
        this.outputHandlers = new IOutputHandler[this.tier.processes];
        this.processInfoSlots = new ProcessInfo[this.tier.processes];
        int baseX = 27;
        int baseXMult = 19;

        for(int i = 0; i < this.tier.processes; ++i) {
            int xPos = baseX + i * baseXMult;
            EMExtraFactoryOutputInventorySlot outputSlot = EMExtraFactoryOutputInventorySlot.at(this, updateSortingListener, xPos, 57);
            EMExtraFactoryInputInventorySlot inputSlot = EMExtraFactoryInputInventorySlot.create(this, i, outputSlot, this.recipeCacheLookupMonitors[i], xPos, 13);
            int index = i;
            builder.addSlot(inputSlot).tracksWarnings(slot -> slot.warning(WarningTracker.WarningType.NO_MATCHING_RECIPE, this.getWarningCheck(CachedRecipe.OperationTracker.RecipeError.NOT_ENOUGH_INPUT, index)));
            builder.addSlot(outputSlot).tracksWarnings(slot -> slot.warning(WarningTracker.WarningType.NO_SPACE_IN_OUTPUT, this.getWarningCheck(CachedRecipe.OperationTracker.RecipeError.NOT_ENOUGH_OUTPUT_SPACE, index)));
            this.inputHandlers[i] = InputHelper.getInputHandler(inputSlot, CachedRecipe.OperationTracker.RecipeError.NOT_ENOUGH_INPUT);
            this.outputHandlers[i] = OutputHelper.getOutputHandler(outputSlot, CachedRecipe.OperationTracker.RecipeError.NOT_ENOUGH_OUTPUT_SPACE);
            this.processInfoSlots[i] = new ProcessInfo(i, inputSlot, outputSlot, null);
        }

    }
}
