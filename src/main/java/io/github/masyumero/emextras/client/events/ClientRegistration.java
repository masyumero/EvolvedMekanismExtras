package io.github.masyumero.emextras.client.events;

import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.client.gui.machine.GuiEMExtraAdvancedFactory;
import io.github.masyumero.emextras.client.gui.machine.GuiEMExtraFactory;
import io.github.masyumero.emextras.client.gui.machine.GuiEMExtraMoreMachineFactory;
import io.github.masyumero.emextras.client.render.transmitter.*;
import io.github.masyumero.emextras.common.integration.Addons;
import io.github.masyumero.emextras.common.integration.mekaf.regisrty.EMExtraAdvancedFactoryContainerTypes;
import io.github.masyumero.emextras.common.integration.mekmm.registry.EMExtraMoreMachineContainerTypes;
import io.github.masyumero.emextras.common.registry.EMExtrasBlock;
import io.github.masyumero.emextras.common.registry.EMExtrasContainerTypes;
import io.github.masyumero.emextras.common.registry.EMExtrasTileEntityTypes;

import io.github.masyumero.emextras.common.tile.transmitter.TileEntityEMExtraLogisticalTransporter;
import mekanism.api.text.EnumColor;
import mekanism.client.ClientRegistrationUtil;
import mekanism.client.render.MekanismRenderer;
import mekanism.client.render.item.TransmitterTypeDecorator;
import mekanism.common.util.WorldUtils;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterItemDecorationsEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(modid = EMExtras.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegistration {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        // universal cable
        ClientRegistrationUtil.bindTileEntityRenderer(event, RenderEMExtraUniversalCable::new, EMExtrasTileEntityTypes.ABSOLUTE_OVERCLOCKED_UNIVERSAL_CABLE,
                EMExtrasTileEntityTypes.SUPREME_QUANTUM_UNIVERSAL_CABLE, EMExtrasTileEntityTypes.COSMIC_DENSE_UNIVERSAL_CABLE, EMExtrasTileEntityTypes.INFINITE_MULTIVERSAL_UNIVERSAL_CABLE);
        // logistical transporter
        ClientRegistrationUtil.bindTileEntityRenderer(event, RenderEMExtraLogisticalTransporter::new, EMExtrasTileEntityTypes.ABSOLUTE_OVERCLOCKED_LOGISTICAL_TRANSPORTER,
                EMExtrasTileEntityTypes.SUPREME_QUANTUM_LOGISTICAL_TRANSPORTER, EMExtrasTileEntityTypes.COSMIC_DENSE_LOGISTICAL_TRANSPORTER, EMExtrasTileEntityTypes.INFINITE_MULTIVERSAL_LOGISTICAL_TRANSPORTER);
        // mechanical pipe
        ClientRegistrationUtil.bindTileEntityRenderer(event, RenderEMExtraMechanicalPipe::new, EMExtrasTileEntityTypes.ABSOLUTE_OVERCLOCKED_MECHANICAL_PIPE,
                EMExtrasTileEntityTypes.SUPREME_QUANTUM_MECHANICAL_PIPE, EMExtrasTileEntityTypes.COSMIC_DENSE_MECHANICAL_PIPE, EMExtrasTileEntityTypes.INFINITE_MULTIVERSAL_MECHANICAL_PIPE);
        // pressurized tube
        ClientRegistrationUtil.bindTileEntityRenderer(event, RenderEMExtraPressurizedTube::new, EMExtrasTileEntityTypes.ABSOLUTE_OVERCLOCKED_PRESSURIZED_TUBE,
                EMExtrasTileEntityTypes.SUPREME_QUANTUM_PRESSURIZED_TUBE, EMExtrasTileEntityTypes.COSMIC_DENSE_PRESSURIZED_TUBE, EMExtrasTileEntityTypes.INFINITE_MULTIVERSAL_PRESSURIZED_TUBE);
        // thermodynamic conductor
        ClientRegistrationUtil.bindTileEntityRenderer(event, RenderEMExtraThermodynamicConductor::new, EMExtrasTileEntityTypes.ABSOLUTE_OVERCLOCKED_THERMODYNAMIC_CONDUCTOR,
                EMExtrasTileEntityTypes.SUPREME_QUANTUM_THERMODYNAMIC_CONDUCTOR, EMExtrasTileEntityTypes.COSMIC_DENSE_THERMODYNAMIC_CONDUCTOR, EMExtrasTileEntityTypes.INFINITE_MULTIVERSAL_THERMODYNAMIC_CONDUCTOR);
    }

    @SubscribeEvent
    public static void onStitch(TextureStitchEvent.Post event) {
        TextureAtlas map = event.getAtlas();
        RenderEMExtraLogisticalTransporter.onStitch(map);
        RenderEMExtraMechanicalPipe.onStitch();
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerContainers(RegisterEvent event) {
        event.register(Registries.MENU, helper -> {
            ClientRegistrationUtil.registerScreen(EMExtrasContainerTypes.FACTORY, GuiEMExtraFactory::new);
            if (Addons.MEKMM.isLoaded()) {
                ClientRegistrationUtil.registerScreen(EMExtraAdvancedFactoryContainerTypes.ADVANCED_FACTORY, GuiEMExtraAdvancedFactory::new);
                ClientRegistrationUtil.registerScreen(EMExtraMoreMachineContainerTypes.MORE_MACHINE_FACTORY, GuiEMExtraMoreMachineFactory::new);
            }
        });
    }

    @SubscribeEvent
    public static void registerBlockColorHandlers(RegisterColorHandlersEvent.Block event) {
        ClientRegistrationUtil.registerBlockColorHandler(event, (state, world, pos, tintIndex) -> {
                    if (tintIndex == 1 && pos != null) {
                        TileEntityEMExtraLogisticalTransporter transporter = WorldUtils.getTileEntity(TileEntityEMExtraLogisticalTransporter.class, world, pos);
                        if (transporter != null) {
                            EnumColor renderColor = transporter.getTransmitter().getColor();
                            if (renderColor != null) {
                                return MekanismRenderer.getColorARGB(renderColor, 1);
                            }
                        }
                    }
                    return -1;
                }, EMExtrasBlock.ABSOLUTE_OVERCLOCKED_LOGISTICAL_TRANSPORTER, EMExtrasBlock.SUPREME_QUANTUM_LOGISTICAL_TRANSPORTER, EMExtrasBlock.COSMIC_DENSE_LOGISTICAL_TRANSPORTER,
                EMExtrasBlock.INFINITE_MULTIVERSAL_LOGISTICAL_TRANSPORTER);
    }

    @SubscribeEvent
    public static void registerItemDecorations(RegisterItemDecorationsEvent event) {
        TransmitterTypeDecorator.registerDecorators(event, EMExtrasBlock.ABSOLUTE_OVERCLOCKED_PRESSURIZED_TUBE, EMExtrasBlock.SUPREME_QUANTUM_PRESSURIZED_TUBE,
                EMExtrasBlock.COSMIC_DENSE_PRESSURIZED_TUBE, EMExtrasBlock.INFINITE_MULTIVERSAL_PRESSURIZED_TUBE, EMExtrasBlock.ABSOLUTE_OVERCLOCKED_THERMODYNAMIC_CONDUCTOR,
                EMExtrasBlock.SUPREME_QUANTUM_THERMODYNAMIC_CONDUCTOR, EMExtrasBlock.COSMIC_DENSE_THERMODYNAMIC_CONDUCTOR, EMExtrasBlock.INFINITE_MULTIVERSAL_THERMODYNAMIC_CONDUCTOR,
                EMExtrasBlock.ABSOLUTE_OVERCLOCKED_UNIVERSAL_CABLE, EMExtrasBlock.SUPREME_QUANTUM_UNIVERSAL_CABLE, EMExtrasBlock.COSMIC_DENSE_UNIVERSAL_CABLE, EMExtrasBlock.INFINITE_MULTIVERSAL_UNIVERSAL_CABLE);
    }
}
