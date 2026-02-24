package io.github.masyumero.emextras.client.gui.machine;

import io.github.masyumero.emextras.client.gui.element.tab.EMExtraGuiSortingTab;
import io.github.masyumero.emextras.common.tile.factory.TileEntityEMExtraFactory;
import io.github.masyumero.emextras.common.tile.factory.TileEntityEMExtraItemStackChemicalToItemStackFactory;
import io.github.masyumero.emextras.common.tile.factory.TileEntityEMExtraSawingFactory;
import mekanism.api.recipes.cache.CachedRecipe;
import mekanism.client.gui.GuiConfigurableTile;
import mekanism.client.gui.element.GuiDumpButton;
import mekanism.client.gui.element.bar.GuiChemicalBar;
import mekanism.client.gui.element.bar.GuiVerticalPowerBar;
import mekanism.client.gui.element.progress.GuiProgress;
import mekanism.client.gui.element.progress.ProgressType;
import mekanism.client.gui.element.tab.GuiEnergyTab;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import mekanism.common.inventory.warning.WarningTracker;
import mekanism.common.tile.interfaces.IHasDumpButton;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GuiEMExtraFactory extends GuiConfigurableTile<TileEntityEMExtraFactory<?>, MekanismTileContainer<TileEntityEMExtraFactory<?>>> {

    @Nullable
    private GuiDumpButton<?> dumpButton;

    public GuiEMExtraFactory(MekanismTileContainer<TileEntityEMExtraFactory<?>> container, Inventory inv, Component title) {
        super(container, inv, title);
        if (tile.hasSecondaryResourceBar()) {
            imageHeight += 11;
            inventoryLabelY = 85;
        } else if (tile instanceof TileEntityEMExtraSawingFactory) {
            imageHeight += 21;
            inventoryLabelY = 95;
        } else {
            inventoryLabelY = 75;
        }
        imageWidth += tile.tier.imageWidth;
        inventoryLabelX = tile.tier.inventoryLabelX;

        titleLabelY = 4;
        dynamicSlots = true;
    }

    @Override
    protected void addGuiElements() {
        super.addGuiElements();
        addRenderableWidget(new EMExtraGuiSortingTab(this, tile));
        addRenderableWidget(new GuiVerticalPowerBar(this, tile.getEnergyContainer(), imageWidth - 12, 16, tile instanceof TileEntityEMExtraSawingFactory ? 73 : 52))
                .warning(WarningTracker.WarningType.NOT_ENOUGH_ENERGY, tile.getWarningCheck(CachedRecipe.OperationTracker.RecipeError.NOT_ENOUGH_ENERGY, 0));
        addRenderableWidget(new GuiEnergyTab(this, tile.getEnergyContainer(), tile::getLastUsage));
        if (tile.hasSecondaryResourceBar()) {
            if (tile instanceof TileEntityEMExtraItemStackChemicalToItemStackFactory factory) {
                int index = tile.tier.ordinal();
                addRenderableWidget(new GuiChemicalBar(this, GuiChemicalBar.getProvider(factory.getChemicalTank(), tile.getChemicalTanks(null)), 7, 76,
                        210 + 38 * index, 4, true))
                        .warning(WarningTracker.WarningType.NO_MATCHING_RECIPE, tile.getWarningCheck(CachedRecipe.OperationTracker.RecipeError.NOT_ENOUGH_SECONDARY_INPUT, 0));
                dumpButton = addRenderableWidget(new GuiDumpButton<>(this, (TileEntityEMExtraFactory<?> & IHasDumpButton) tile,
                        220 + 38 * index, 76));
            }
        }

        int baseX = 27;
        int baseXMult = 19;
        for (int i = 0; i < tile.tier.processes; i++) {
            int cacheIndex = i;
            addRenderableWidget(new GuiProgress(() -> tile.getScaledProgress(1, cacheIndex), ProgressType.DOWN, this, 4 + baseX + (i * baseXMult), 33))
                    .recipeViewerCategory(tile)
                    .warning(WarningTracker.WarningType.INPUT_DOESNT_PRODUCE_OUTPUT, tile.getWarningCheck(CachedRecipe.OperationTracker.RecipeError.INPUT_DOESNT_PRODUCE_OUTPUT, cacheIndex));
        }
    }

    @Override
    protected void drawForegroundText(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY) {
        renderTitleText(guiGraphics);
        renderInventoryText(guiGraphics, dumpButton == null ? getXSize() : dumpButton.getRelativeX());
        super.drawForegroundText(guiGraphics, mouseX, mouseY);
    }
}