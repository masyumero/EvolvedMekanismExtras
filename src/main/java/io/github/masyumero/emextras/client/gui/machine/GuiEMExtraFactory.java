package io.github.masyumero.emextras.client.gui.machine;

import fr.iglee42.evolvedmekanism.jei.EMJEI;
import io.github.masyumero.emextras.client.gui.element.tab.GuiEMExtraSortingTab;
import io.github.masyumero.emextras.common.tile.factory.TileEntityEMExtraFactory;
import mekanism.api.recipes.cache.CachedRecipe;
import mekanism.client.gui.GuiConfigurableTile;
import mekanism.client.gui.GuiUtils;
import mekanism.client.gui.element.bar.GuiVerticalPowerBar;
import mekanism.client.gui.element.progress.GuiProgress;
import mekanism.client.gui.element.progress.ProgressType;
import mekanism.client.gui.element.tab.GuiEnergyTab;
import mekanism.client.jei.MekanismJEIRecipeType;
import mekanism.client.render.MekanismRenderer;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import mekanism.common.inventory.warning.WarningTracker;
import mekanism.common.util.MekanismUtils;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class GuiEMExtraFactory extends GuiConfigurableTile<TileEntityEMExtraFactory<?>, MekanismTileContainer<TileEntityEMExtraFactory<?>>> {

    public static final ResourceLocation BASE_BACKGROUND = MekanismUtils.getResource(MekanismUtils.ResourceType.GUI, "base.png");

    public GuiEMExtraFactory(MekanismTileContainer<TileEntityEMExtraFactory<?>> container, Inventory inv, Component title) {
        super(container, inv, title);
        inventoryLabelY = 75;
        int index = tile.tier.ordinal();
        imageWidth += (36 * (index + 2)) + (2 * index);
        inventoryLabelX = (22 * (index + 2)) - (3 * index);
        titleLabelY = 4;
        dynamicSlots = true;
    }

    @Override
    protected void renderBg(@NotNull GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        MekanismRenderer.resetColor(guiGraphics);
        GuiUtils.renderBackgroundTexture(guiGraphics, BASE_BACKGROUND, 4, 4, leftPos, topPos, imageWidth, imageHeight, 256, 256);
    }


    @Override
    protected void addGuiElements() {
        super.addGuiElements();
        addRenderableWidget(new GuiEMExtraSortingTab(this, tile));
        addRenderableWidget(new GuiVerticalPowerBar(this, tile.getEnergyContainer(), imageWidth - 12, 16,  52))
                .warning(WarningTracker.WarningType.NOT_ENOUGH_ENERGY, tile.getWarningCheck(CachedRecipe.OperationTracker.RecipeError.NOT_ENOUGH_ENERGY, 0));
        addRenderableWidget(new GuiEnergyTab(this, tile.getEnergyContainer(), tile::getLastUsage));
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
            case ALLOYING -> EMJEI.ALLOYING;
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