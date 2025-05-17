package io.github.masyumero.emextras.client.gui.machine;

import fr.iglee42.evolvedmekanism.jei.EMJEI;
import io.github.masyumero.emextras.client.gui.element.tab.GuiEMExtraSortingTab;
import io.github.masyumero.emextras.common.tile.factory.TileEntityAdvancedAlloyingFactory;
import io.github.masyumero.emextras.common.tile.factory.TileEntityEMExtraFactory;
import mekanism.api.recipes.cache.CachedRecipe;
import mekanism.client.gui.GuiConfigurableTile;
import mekanism.client.gui.element.bar.GuiVerticalPowerBar;
import mekanism.client.gui.element.progress.GuiProgress;
import mekanism.client.gui.element.progress.ProgressType;
import mekanism.client.gui.element.tab.GuiEnergyTab;
import mekanism.client.jei.MekanismJEIRecipeType;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import mekanism.common.inventory.warning.ISupportsWarning;
import mekanism.common.inventory.warning.WarningTracker;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.function.BooleanSupplier;

public class GuiEMExtraFactory extends GuiConfigurableTile<TileEntityAdvancedAlloyingFactory, MekanismTileContainer<TileEntityAdvancedAlloyingFactory>> {
    public BooleanSupplier getWarningCheck(CachedRecipe.OperationTracker.RecipeError error, int processIndex) {

