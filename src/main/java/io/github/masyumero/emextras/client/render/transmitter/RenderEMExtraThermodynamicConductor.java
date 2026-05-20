package io.github.masyumero.emextras.client.render.transmitter;

import io.github.masyumero.emextras.common.content.network.transmitter.EMExtraThermodynamicConductor;
import io.github.masyumero.emextras.common.tile.transmitter.TileEntityEMExtraThermodynamicConductor;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.client.render.MekanismRenderer;
import mekanism.client.render.transmitter.RenderTransmitterBase;
import mekanism.common.base.ProfilerConstants;
import mekanism.common.util.HeatUtils;

import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.util.profiling.ProfilerFiller;

import com.mojang.blaze3d.vertex.PoseStack;

@NothingNullByDefault
public class RenderEMExtraThermodynamicConductor extends RenderTransmitterBase<TileEntityEMExtraThermodynamicConductor> {

    public RenderEMExtraThermodynamicConductor(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void render(TileEntityEMExtraThermodynamicConductor tile, float partialTick, PoseStack matrix, MultiBufferSource renderer, int light, int overlayLight,
                          ProfilerFiller profiler) {
        matrix.pushPose();
        matrix.translate(0.5, 0.5, 0.5);
        EMExtraThermodynamicConductor conductor = tile.getTransmitter();
        int argb = HeatUtils.getColorFromTemp(conductor.getTotalTemperature(), conductor.getBaseColor()).argb();
        renderModel(tile, matrix, renderer.getBuffer(Sheets.translucentCullBlockSheet()), argb, MekanismRenderer.getAlpha(argb), LightTexture.FULL_BRIGHT,
                overlayLight, MekanismRenderer.heatIcon);
        matrix.popPose();
    }

    @Override
    protected String getProfilerSection() {
        return ProfilerConstants.THERMODYNAMIC_CONDUCTOR;
    }
}