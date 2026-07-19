package io.github.masyumero.emextras.client.events;

import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.client.gui.machine.GuiEMExtraAdvancedFactory;
import io.github.masyumero.emextras.client.gui.machine.GuiEMExtraFactory;
import io.github.masyumero.emextras.client.gui.machine.GuiEMExtraMoreMachineFactory;
import io.github.masyumero.emextras.client.render.transmitter.*;
import io.github.masyumero.emextras.common.integration.Addons;
import io.github.masyumero.emextras.common.integration.mekaf.regisrty.EMExtraAdvancedFactoryContainerTypes;
import io.github.masyumero.emextras.common.integration.mekmm.registry.EMExtraMoreMachineContainerTypes;
import io.github.masyumero.emextras.common.registry.EMExtraBlocks;
import io.github.masyumero.emextras.common.registry.EMExtraContainerTypes;
import io.github.masyumero.emextras.common.registry.EMExtraTileEntityTypes;

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
        ClientRegistrationUtil.bindTileEntityRenderer(event, RenderEMExtraUniversalCable::new, EMExtraTileEntityTypes.ABSOLUTE_OVERCLOCKED_UNIVERSAL_CABLE,
                EMExtraTileEntityTypes.SUPREME_QUANTUM_UNIVERSAL_CABLE, EMExtraTileEntityTypes.COSMIC_DENSE_UNIVERSAL_CABLE, EMExtraTileEntityTypes.INFINITE_MULTIVERSAL_UNIVERSAL_CABLE);
        // logistical transporter
        ClientRegistrationUtil.bindTileEntityRenderer(event, RenderEMExtraLogisticalTransporter::new, EMExtraTileEntityTypes.ABSOLUTE_OVERCLOCKED_LOGISTICAL_TRANSPORTER,
                EMExtraTileEntityTypes.SUPREME_QUANTUM_LOGISTICAL_TRANSPORTER, EMExtraTileEntityTypes.COSMIC_DENSE_LOGISTICAL_TRANSPORTER, EMExtraTileEntityTypes.INFINITE_MULTIVERSAL_LOGISTICAL_TRANSPORTER);
        // mechanical pipe
        ClientRegistrationUtil.bindTileEntityRenderer(event, RenderEMExtraMechanicalPipe::new, EMExtraTileEntityTypes.ABSOLUTE_OVERCLOCKED_MECHANICAL_PIPE,
                EMExtraTileEntityTypes.SUPREME_QUANTUM_MECHANICAL_PIPE, EMExtraTileEntityTypes.COSMIC_DENSE_MECHANICAL_PIPE, EMExtraTileEntityTypes.INFINITE_MULTIVERSAL_MECHANICAL_PIPE);
        // pressurized tube
        ClientRegistrationUtil.bindTileEntityRenderer(event, RenderEMExtraPressurizedTube::new, EMExtraTileEntityTypes.ABSOLUTE_OVERCLOCKED_PRESSURIZED_TUBE,
                EMExtraTileEntityTypes.SUPREME_QUANTUM_PRESSURIZED_TUBE, EMExtraTileEntityTypes.COSMIC_DENSE_PRESSURIZED_TUBE, EMExtraTileEntityTypes.INFINITE_MULTIVERSAL_PRESSURIZED_TUBE);
        // thermodynamic conductor
        ClientRegistrationUtil.bindTileEntityRenderer(event, RenderEMExtraThermodynamicConductor::new, EMExtraTileEntityTypes.ABSOLUTE_OVERCLOCKED_THERMODYNAMIC_CONDUCTOR,
                EMExtraTileEntityTypes.SUPREME_QUANTUM_THERMODYNAMIC_CONDUCTOR, EMExtraTileEntityTypes.COSMIC_DENSE_THERMODYNAMIC_CONDUCTOR, EMExtraTileEntityTypes.INFINITE_MULTIVERSAL_THERMODYNAMIC_CONDUCTOR);
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
            ClientRegistrationUtil.registerScreen(EMExtraContainerTypes.FACTORY, GuiEMExtraFactory::new);
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
                }, EMExtraBlocks.ABSOLUTE_OVERCLOCKED_LOGISTICAL_TRANSPORTER, EMExtraBlocks.SUPREME_QUANTUM_LOGISTICAL_TRANSPORTER, EMExtraBlocks.COSMIC_DENSE_LOGISTICAL_TRANSPORTER,
                EMExtraBlocks.INFINITE_MULTIVERSAL_LOGISTICAL_TRANSPORTER);
    }

    @SubscribeEvent
    public static void registerItemDecorations(RegisterItemDecorationsEvent event) {
        TransmitterTypeDecorator.registerDecorators(event, EMExtraBlocks.ABSOLUTE_OVERCLOCKED_PRESSURIZED_TUBE, EMExtraBlocks.SUPREME_QUANTUM_PRESSURIZED_TUBE,
                EMExtraBlocks.COSMIC_DENSE_PRESSURIZED_TUBE, EMExtraBlocks.INFINITE_MULTIVERSAL_PRESSURIZED_TUBE, EMExtraBlocks.ABSOLUTE_OVERCLOCKED_THERMODYNAMIC_CONDUCTOR,
                EMExtraBlocks.SUPREME_QUANTUM_THERMODYNAMIC_CONDUCTOR, EMExtraBlocks.COSMIC_DENSE_THERMODYNAMIC_CONDUCTOR, EMExtraBlocks.INFINITE_MULTIVERSAL_THERMODYNAMIC_CONDUCTOR,
                EMExtraBlocks.ABSOLUTE_OVERCLOCKED_UNIVERSAL_CABLE, EMExtraBlocks.SUPREME_QUANTUM_UNIVERSAL_CABLE, EMExtraBlocks.COSMIC_DENSE_UNIVERSAL_CABLE, EMExtraBlocks.INFINITE_MULTIVERSAL_UNIVERSAL_CABLE);
    }
}
