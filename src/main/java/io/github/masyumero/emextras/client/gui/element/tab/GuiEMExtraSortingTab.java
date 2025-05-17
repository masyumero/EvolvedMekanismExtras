package io.github.masyumero.emextras.client.gui.element.tab;

import io.github.masyumero.emextras.common.network.to_server.EMExtraPacketGuiInteract;
import io.github.masyumero.emextras.common.tile.factory.TileEntityEMExtraFactory;
import mekanism.client.SpecialColors;
import mekanism.client.gui.IGuiWrapper;
import mekanism.client.gui.element.GuiInsetElement;
import mekanism.client.render.MekanismRenderer;
import mekanism.common.Mekanism;
import mekanism.common.MekanismLang;
import mekanism.common.util.MekanismUtils;
import mekanism.common.util.text.BooleanStateDisplay;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import org.jetbrains.annotations.NotNull;

public class GuiEMExtraSortingTab extends GuiInsetElement<TileEntityEMExtraFactory<?>> {

    public GuiEMExtraSortingTab(IGuiWrapper gui, TileEntityEMExtraFactory<?> tile) {
        super(MekanismUtils.getResource(MekanismUtils.ResourceType.GUI, "sorting.png"), gui, tile, -26, 62, 35, 18, true);
    }

    @Override
    public void drawBackground(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        super.drawBackground(guiGraphics, mouseX, mouseY, partialTicks);
        drawTextScaledBound(guiGraphics, BooleanStateDisplay.OnOff.of(dataSource.isSorting()).getTextComponent(), relativeX + 3, relativeY + 24, titleTextColor(), 21);
    }

    @Override
    public void renderToolTip(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY) {
        super.renderToolTip(guiGraphics, mouseX, mouseY);
        displayTooltips(guiGraphics, mouseX, mouseY, MekanismLang.AUTO_SORT.translate());
    }

    @Override
    protected void colorTab(GuiGraphics guiGraphics) {
        MekanismRenderer.color(guiGraphics, SpecialColors.TAB_FACTORY_SORT);
    }

    @Override
    public void onClick(double mouseX, double mouseY, int button) {
        Mekanism.packetHandler().sendToServer(new EMExtraPacketGuiInteract(EMExtraPacketGuiInteract.EMExtraGuiInteraction.AUTO_SORT_BUTTON, dataSource));
    }

    @Override
    protected void renderWidget(@NotNull GuiGraphics guiGraphics, int i, int i1, float v) {

    }

    @Override
    protected void updateWidgetNarration(@NotNull NarrationElementOutput narrationElementOutput) {

    }
}
