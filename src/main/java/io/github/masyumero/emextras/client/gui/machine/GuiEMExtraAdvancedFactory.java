package io.github.masyumero.emextras.client.gui.machine;

import io.github.masyumero.emextras.client.gui.element.tab.EMExtraAdvancedFactoryGuiSortingTab;
import io.github.masyumero.emextras.common.integration.mekaf.tile.factory.TileEntityEMExtraPaintingFactory;
import io.github.masyumero.emextras.common.integration.mekaf.tile.factory.TileEntityEMExtraPressurizedReactingFactory;
import io.github.masyumero.emextras.common.integration.mekaf.tile.factory.TileEntityEMExtraWashingFactory;
import io.github.masyumero.emextras.common.integration.mekaf.tile.factory.base.*;
import mekanism.api.recipes.cache.CachedRecipe.OperationTracker.RecipeError;
import mekanism.client.SpecialColors;
import mekanism.client.gui.GuiConfigurableTile;
import mekanism.client.gui.element.GuiDumpButton;
import mekanism.client.gui.element.GuiSideHolder;
import mekanism.client.gui.element.bar.GuiChemicalBar;
import mekanism.client.gui.element.bar.GuiFluidBar;
import mekanism.client.gui.element.bar.GuiVerticalPowerBar;
import mekanism.client.gui.element.gauge.GaugeType;
import mekanism.client.gui.element.gauge.GuiFluidGauge;
import mekanism.client.gui.element.gauge.GuiGasGauge;
import mekanism.client.gui.element.gauge.GuiMergedChemicalTankGauge;
import mekanism.client.gui.element.gauge.GuiPigmentGauge;
import mekanism.client.gui.element.gauge.GuiSlurryGauge;
import mekanism.client.gui.element.progress.GuiProgress;
import mekanism.client.gui.element.progress.ProgressType;
import mekanism.client.gui.element.tab.GuiEnergyTab;
import mekanism.client.jei.MekanismJEIRecipeType;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import mekanism.common.inventory.warning.WarningTracker.WarningType;
import mekanism.common.tile.interfaces.IHasDumpButton;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

import org.jetbrains.annotations.NotNull;

public class GuiEMExtraAdvancedFactory extends GuiConfigurableTile<TileEntityEMExtraAdvancedFactoryBase<?>, MekanismTileContainer<TileEntityEMExtraAdvancedFactoryBase<?>>> {

    public GuiEMExtraAdvancedFactory(MekanismTileContainer<TileEntityEMExtraAdvancedFactoryBase<?>> container, Inventory inv, Component title) {
        super(container, inv, title);
        imageHeight += tile instanceof TileEntityEMExtraPressurizedReactingFactory ? 8 : 13;
        if (tile instanceof TileEntityEMExtraGasToGasFactory || tile instanceof TileEntityEMExtraSlurryToSlurryFactory) {
            imageHeight += 13;
        }

        if (tile.hasExtrasResourceBar()) {
            imageHeight += 11;
            if (tile instanceof TileEntityEMExtraGasToGasFactory || tile instanceof TileEntityEMExtraSlurryToSlurryFactory) {
                inventoryLabelY = 111;
            } else if (tile instanceof TileEntityEMExtraItemToItemAdvancedFactory) {
                imageHeight -= 13;
                inventoryLabelY = 85;
            } else {
                inventoryLabelY = tile instanceof TileEntityEMExtraPressurizedReactingFactory ? 93 : 98;
            }
        } else if (tile instanceof TileEntityEMExtraGasToGasFactory || tile instanceof TileEntityEMExtraSlurryToSlurryFactory) {
            inventoryLabelY = 103;
        } else {
            inventoryLabelY = 88;
        }

        imageWidth += tile.tier.imageWidth;
        inventoryLabelX = tile.tier.inventoryLabelX;
        titleLabelY = 4;
        dynamicSlots = true;
    }

    @Override
    protected void addGuiElements() {
        if (tile instanceof TileEntityEMExtraWashingFactory) {
            addRenderableWidget(GuiSideHolder.create(this, imageWidth, 66, 57, false, true, SpecialColors.TAB_CHEMICAL_WASHER));
        }
        super.addGuiElements();
        if (!(tile instanceof TileEntityEMExtraMergedToItemFactory)) {
            addRenderableWidget(new EMExtraAdvancedFactoryGuiSortingTab(this, tile));
        }
        addRenderableWidget(new GuiVerticalPowerBar(this, tile.getEnergyContainer(), imageWidth - 12, 16, getEnergyHeight()))
                .warning(WarningType.NOT_ENOUGH_ENERGY, tile.getWarningCheck(RecipeError.NOT_ENOUGH_ENERGY, 0));
        // 左下角能量面板
        addRenderableWidget(new GuiEnergyTab(this, tile.getEnergyContainer(), tile::getLastUsage));

        if (tile.hasExtrasResourceBar()) {
            addRenderableWidget(new GuiDumpButton<>(this, (TileEntityEMExtraAdvancedFactoryBase<?> & IHasDumpButton) tile, getButtonX(), 76 + 13 * tile.TankCount()));
            if (tile instanceof TileEntityEMExtraWashingFactory factory) {
                addRenderableWidget(new GuiFluidBar(this, GuiFluidBar.getProvider(factory.getFluidTankBar(), factory.getFluidTanks(null)), 7, 102, getBarWidth(), 4, true))
                        .warning(WarningType.NO_MATCHING_RECIPE, factory.getWarningCheck(RecipeError.NOT_ENOUGH_SECONDARY_INPUT, 0));
            } else if (tile instanceof TileEntityEMExtraPressurizedReactingFactory factory) {
                // 出输出化学储罐
                addRenderableWidget(new GuiGasGauge(() -> factory.outputGasTank, () -> factory.getGasTanks(null), GaugeType.SMALL, this, 6, 44))
                        .warning(WarningType.NO_SPACE_IN_OUTPUT, factory.getWarningCheck(TileEntityEMExtraPressurizedReactingFactory.NOT_ENOUGH_SPACE_GAS_OUTPUT_ERROR, 0));
                // 化学储罐条
                addRenderableWidget(new GuiChemicalBar<>(this, GuiChemicalBar.getProvider(factory.getGasTankBar(), factory.getGasTanks(null)), 7, 76, getBarWidth(), 4, true))
                        .warning(WarningType.NO_MATCHING_RECIPE, factory.getWarningCheck(RecipeError.NOT_ENOUGH_SECONDARY_INPUT, 0));
                // 流体储罐条
                addRenderableWidget(new GuiFluidBar(this, GuiFluidBar.getProvider(factory.getFluidTankBar(), factory.getFluidTanks(null)), 7, 84, getBarWidth(), 4, true))
                        .warning(WarningType.NO_MATCHING_RECIPE, factory.getWarningCheck(RecipeError.NOT_ENOUGH_SECONDARY_INPUT, 0));
            } else if (tile instanceof TileEntityEMExtraPaintingFactory factory) {
                // 化学储罐条
                addRenderableWidget(new GuiChemicalBar<>(this, GuiChemicalBar.getProvider(factory.pigmentTank, factory.getPigmentTanks(null)), 7, 76, getBarWidth(), 4, true))
                        .warning(WarningType.NO_MATCHING_RECIPE, factory.getWarningCheck(RecipeError.NOT_ENOUGH_SECONDARY_INPUT, 0));
            } else {
                // TODO:计划删除getGasTankBar()
                addRenderableWidget(new GuiChemicalBar<>(this, GuiChemicalBar.getProvider(tile.getGasTankBar(), tile.getGasTanks(null)),
                        7, tile instanceof TileEntityEMExtraGasToGasFactory ? 102 : 89, getBarWidth(), 4, true))
                        .warning(WarningType.NO_MATCHING_RECIPE, tile.getWarningCheck(RecipeError.NOT_ENOUGH_SECONDARY_INPUT, 0));
            }
        }

        // 物品到气体的工厂只需要一排储罐，物品槽位在TileEntity中被添加
        if (tile instanceof TileEntityEMExtraItemToGasFactory<?> factory) {
            for (int i = 0; i < tile.tier.processes; i++) {
                int index = i;
                addRenderableWidget(new GuiGasGauge(() -> factory.outputGasTanks.get(index), () -> factory.getGasTanks(null), GaugeType.SMALL, this, factory.getXPos(index) - 1, 57))
                        .warning(WarningType.NO_SPACE_IN_OUTPUT, factory.getWarningCheck(RecipeError.NOT_ENOUGH_OUTPUT_SPACE, index));
            }
        }

        // 物品到染料的工厂只需要一排储罐，物品槽位在TileEntity中被添加
        if (tile instanceof TileEntityEMExtraItemToPigmentFactory<?> factory) {
            for (int i = 0; i < tile.tier.processes; i++) {
                int index = i;
                addRenderableWidget(new GuiPigmentGauge(() -> factory.outputPigmentTanks.get(index), () -> factory.getPigmentTanks(null), GaugeType.SMALL, this, factory.getXPos(index) - 1, 57))
                        .warning(WarningType.NO_SPACE_IN_OUTPUT, factory.getWarningCheck(RecipeError.NOT_ENOUGH_OUTPUT_SPACE, index));
            }
        }

        // 物品到混合化学品的工厂只需要一排储罐，物品槽位在TileEntity中被添加
        if (tile instanceof TileEntityEMExtraItemToMergedFactory<?> factory) {
            for (int i = 0; i < tile.tier.processes; i++) {
                int index = i;
                addRenderableWidget(new GuiMergedChemicalTankGauge<>(() -> factory.outputChemicalTanks.get(index), () -> factory, GaugeType.SMALL, this, factory.getXPos(index) - 1, 57))
                        .warning(WarningType.NO_SPACE_IN_OUTPUT, factory.getWarningCheck(RecipeError.NOT_ENOUGH_OUTPUT_SPACE, index));
            }
        }

        // 气体生产气体的工厂需要两排储罐
        if (tile instanceof TileEntityEMExtraGasToGasFactory<?> factory) {
            for (int i = 0; i < tile.tier.processes; i++) {
                int index = i;
                addRenderableWidget(new GuiGasGauge(() -> factory.inputGasTanks.get(index), () -> tile.getGasTanks(null), GaugeType.SMALL, this, factory.getXPos(index) - 1, 13))
                        .warning(WarningType.NO_MATCHING_RECIPE, factory.getWarningCheck(RecipeError.NOT_ENOUGH_LEFT_INPUT, index));
                addRenderableWidget(new GuiGasGauge(() -> factory.outputGasTanks.get(index), () -> tile.getGasTanks(null), GaugeType.SMALL, this, factory.getXPos(index) - 1, 70))
                        .warning(WarningType.NO_SPACE_IN_OUTPUT, factory.getWarningCheck(RecipeError.NOT_ENOUGH_OUTPUT_SPACE, index));
            }
        }

        // 浆液生产浆液的工厂需要两排储罐
        if (tile instanceof TileEntityEMExtraSlurryToSlurryFactory<?> factory) {
            for (int i = 0; i < tile.tier.processes; i++) {
                int index = i;
                addRenderableWidget(new GuiSlurryGauge(() -> factory.inputSlurryTanks.get(index), () -> tile.getSlurryTanks(null), GaugeType.SMALL, this, factory.getXPos(index) - 1, 13))
                        .warning(WarningType.NO_MATCHING_RECIPE, factory.getWarningCheck(RecipeError.NOT_ENOUGH_LEFT_INPUT, index));
                addRenderableWidget(new GuiSlurryGauge(() -> factory.outputSlurryTanks.get(index), () -> tile.getSlurryTanks(null), GaugeType.SMALL, this, factory.getXPos(index) - 1, 70))
                        .warning(WarningType.NO_SPACE_IN_OUTPUT, factory.getWarningCheck(RecipeError.NOT_ENOUGH_OUTPUT_SPACE, index));
            }
        }

        // 混合化学品到物品的工厂只需要一排储罐，但储罐在上面
        if (tile instanceof TileEntityEMExtraMergedToItemFactory<?> factory) {
            for (int i = 0; i < tile.tier.processes; i++) {
                int index = i;
                addRenderableWidget(new GuiMergedChemicalTankGauge<>(() -> factory.inputChemicalTanks.get(index), () -> factory, GaugeType.SMALL, this, factory.getXPos(index) - 1, 13))
                        .warning(WarningType.NO_MATCHING_RECIPE, factory.getWarningCheck(RecipeError.NOT_ENOUGH_INPUT, index));
            }
        }

        // 物品到流体体的工厂只需要一排储罐，物品槽位在TileEntity中被添加
        if (tile instanceof TileEntityEMExtraItemToFluidFactory<?> factory) {
            for (int i = 0; i < tile.tier.processes; i++) {
                int index = i;
                addRenderableWidget(new GuiFluidGauge(() -> factory.outputFluidTanks.get(index), () -> factory.getFluidTanks(null), GaugeType.SMALL, this, factory.getXPos(index) - 1, 57))
                        .warning(WarningType.NO_SPACE_IN_OUTPUT, factory.getWarningCheck(RecipeError.NOT_ENOUGH_OUTPUT_SPACE, index));
            }
        }

        // 进度条
        for (int i = 0; i < tile.tier.processes; i++) {
            int cacheIndex = i;
            addProgress(new GuiProgress(() -> tile.getScaledProgress(1, cacheIndex), ProgressType.DOWN, this, 4 + tile.getXPos(i), getProgressYPos()))
                    .warning(WarningType.INPUT_DOESNT_PRODUCE_OUTPUT, tile.getWarningCheck(RecipeError.INPUT_DOESNT_PRODUCE_OUTPUT, cacheIndex));
        }
    }

    private int getEnergyHeight() {
        if (tile instanceof TileEntityEMExtraGasToGasFactory<?> || tile instanceof TileEntityEMExtraSlurryToSlurryFactory<?>) {
            return 78;
        } else if (tile instanceof TileEntityEMExtraMergedToItemFactory<?> || tile instanceof TileEntityEMExtraItemToMergedFactory<?> || tile instanceof TileEntityEMExtraItemToGasFactory<?> || tile instanceof TileEntityEMExtraItemToFluidFactory<?>) {
            return 65;
        } else {
            return 52;
        }
    }

    private int getProgressYPos() {
        if (tile instanceof TileEntityEMExtraGasToGasFactory<?> || tile instanceof TileEntityEMExtraSlurryToSlurryFactory<?> || tile instanceof TileEntityEMExtraMergedToItemFactory<?>) {
            return 46;
        } else {
            return 33;
        }
    }

    private GuiProgress addProgress(GuiProgress progressBar) {
        MekanismJEIRecipeType<?> jeiType = switch (tile.getAdvancedFactoryType()) {
            case OXIDIZING -> MekanismJEIRecipeType.OXIDIZING;
            case DISSOLVING -> MekanismJEIRecipeType.DISSOLUTION;
            case WASHING -> MekanismJEIRecipeType.WASHING;
            case CRYSTALLIZING -> MekanismJEIRecipeType.CRYSTALLIZING;
            case PRESSURISED_REACTING -> MekanismJEIRecipeType.REACTION;
            case CENTRIFUGING -> MekanismJEIRecipeType.CENTRIFUGING;
            case LIQUIFYING -> MekanismJEIRecipeType.NUTRITIONAL_LIQUIFICATION;
            case PIGMENT_EXTRACTING -> MekanismJEIRecipeType.PIGMENT_EXTRACTING;
            case PAINTING -> MekanismJEIRecipeType.PAINTING;
        };
        return addRenderableWidget(progressBar.jeiCategories(jeiType));
    }

    private int getBarWidth() {
        return 210 + 38 * tile.tier.ordinal();
    }

    private int getButtonX() {
        return 220 + 38 * tile.tier.ordinal();
    }

    @Override
    protected void drawForegroundText(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY) {
        renderTitleText(guiGraphics);
        drawString(guiGraphics, playerInventoryTitle, inventoryLabelX, inventoryLabelY, titleTextColor());
        super.drawForegroundText(guiGraphics, mouseX, mouseY);
    }
}
