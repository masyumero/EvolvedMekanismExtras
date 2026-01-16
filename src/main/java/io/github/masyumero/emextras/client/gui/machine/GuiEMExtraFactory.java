package io.github.masyumero.emextras.client.gui.machine;

import fr.iglee42.evolvedmekanism.jei.EMJEI;
import io.github.masyumero.emextras.client.gui.element.tab.GuiEMExtraSortingTab;
import io.github.masyumero.emextras.common.tile.factory.TileEntityEMExtraFactory;
import io.github.masyumero.emextras.common.tile.factory.TileEntityItemStackGasToItemStackEMExtraFactory;
import io.github.masyumero.emextras.common.tile.factory.TileEntityMetallurgicInfuserEMExtraFactory;
import io.github.masyumero.emextras.common.tile.factory.TileEntitySawingEMExtraFactory;
import mekanism.api.recipes.cache.CachedRecipe;
import mekanism.client.gui.GuiConfigurableTile;
import mekanism.client.gui.element.GuiDumpButton;
import mekanism.client.gui.element.bar.GuiChemicalBar;
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

public class GuiEMExtraFactory extends GuiConfigurableTile<TileEntityEMExtraFactory<?>, MekanismTileContainer<TileEntityEMExtraFactory<?>>> {

    public GuiEMExtraFactory(MekanismTileContainer<TileEntityEMExtraFactory<?>> container, Inventory inv, Component title) {
        super(container, inv, title);
        if (tile.hasSecondaryResourceBar()) {
            imageHeight += 11;
            inventoryLabelY = 85;
        } else if (tile instanceof TileEntitySawingEMExtraFactory) {
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
        addRenderableWidget(new GuiEMExtraSortingTab(this, tile));
        addRenderableWidget(new GuiVerticalPowerBar(this, tile.getEnergyContainer(), imageWidth - 12, 16,  52))
                .warning(WarningTracker.WarningType.NOT_ENOUGH_ENERGY, tile.getWarningCheck(CachedRecipe.OperationTracker.RecipeError.NOT_ENOUGH_ENERGY, 0));
        addRenderableWidget(new GuiEnergyTab(this, tile.getEnergyContainer(), tile::getLastUsage));
        int index = tile.tier.ordinal();
        if (tile.hasSecondaryResourceBar()) {
            ISupportsWarning<?> secondaryBar = null;
            if (tile instanceof TileEntityMetallurgicInfuserEMExtraFactory factory) {
                secondaryBar = addRenderableWidget(new GuiChemicalBar<>(this, GuiChemicalBar.getProvider(factory.getInfusionTank(), tile.getInfusionTanks(null)),
                        7, 76, 210 + 38 * index, 4, true));
                addRenderableWidget(new GuiDumpButton<>(this, factory, 220 + 38 * index, 76));
            } else if (tile instanceof TileEntityItemStackGasToItemStackEMExtraFactory factory) {
                secondaryBar = addRenderableWidget(new GuiChemicalBar<>(this, GuiChemicalBar.getProvider(factory.getGasTank(), tile.getGasTanks(null)),
                        7, 76, 210 + 38 * index, 4, true));
                addRenderableWidget(new GuiDumpButton<>(this, factory, 220 + 38 * index, 76));
            }
            if (secondaryBar != null) {
                secondaryBar.warning(WarningTracker.WarningType.NO_MATCHING_RECIPE, tile.getWarningCheck(CachedRecipe.OperationTracker.RecipeError.NOT_ENOUGH_SECONDARY_INPUT, 0));
            }
        }

        int baseX = 27;
        int baseXMult = 19;
        for (int i = 0; i < tile.tier.processes; i++) {
            int cacheIndex = i;
            addProgress(new GuiProgress(() -> tile.getScaledProgress(1, cacheIndex), ProgressType.DOWN, this, 4 + baseX + (i * baseXMult), 33))
                    .warning(WarningTracker.WarningType.INPUT_DOESNT_PRODUCE_OUTPUT, tile.getWarningCheck(CachedRecipe.OperationTracker.RecipeError.INPUT_DOESNT_PRODUCE_OUTPUT, cacheIndex));
        }
    }

    private GuiProgress addProgress(GuiProgress progressBar) {
        MekanismJEIRecipeType<?> jeiType = switch (tile.getFactoryType()) {
            case ALLOYING, ADVANCED_ALLOYING -> EMJEI.ALLOYING;
            case SMELTING -> MekanismJEIRecipeType.SMELTING;
            case ENRICHING -> MekanismJEIRecipeType.ENRICHING;
            case CRUSHING -> MekanismJEIRecipeType.CRUSHING;
            case SAWING -> MekanismJEIRecipeType.SAWING;
            case INFUSING -> MekanismJEIRecipeType.METALLURGIC_INFUSING;
            case COMBINING -> MekanismJEIRecipeType.COMBINING;
            case INJECTING -> MekanismJEIRecipeType.INJECTING;
            case PURIFYING -> MekanismJEIRecipeType.PURIFYING;
            case COMPRESSING -> MekanismJEIRecipeType.COMPRESSING;
        };
        return addRenderableWidget(progressBar.jeiCategories(jeiType));
    }

    @Override
    protected void drawForegroundText(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY) {
        renderTitleText(guiGraphics);
        drawString(guiGraphics, playerInventoryTitle, inventoryLabelX, inventoryLabelY, titleTextColor());
        super.drawForegroundText(guiGraphics, mouseX, mouseY);
    }
}