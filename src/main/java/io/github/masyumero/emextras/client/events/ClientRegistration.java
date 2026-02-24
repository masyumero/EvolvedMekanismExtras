package io.github.masyumero.emextras.client.events;

import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.client.gui.machine.GuiEMExtraAdvancedFactory;
import io.github.masyumero.emextras.client.gui.machine.GuiEMExtraFactory;
import io.github.masyumero.emextras.client.gui.machine.GuiEMExtraMoreMachineFactory;
import io.github.masyumero.emextras.common.integration.mekaf.registries.EMExtraAdvancedFactoryContainerTypes;
import io.github.masyumero.emextras.common.integration.mekmm.registries.EMExtraMoreMachineContainerTypes;
import io.github.masyumero.emextras.common.registry.EMExtraContainerTypes;
import mekanism.client.ClientRegistrationUtil;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(modid = EMExtras.MODID, value = Dist.CLIENT)
public class ClientRegistration {
    private ClientRegistration() {
    }

    @SubscribeEvent
    public static void registerContainers(RegisterMenuScreensEvent event) {
        ClientRegistrationUtil.registerScreen(event, EMExtraContainerTypes.FACTORY, GuiEMExtraFactory::new);

        if (EMExtras.hooks.mekmm.isLoaded()) {
            ClientRegistrationUtil.registerScreen(event, EMExtraAdvancedFactoryContainerTypes.ADVANCED_FACTORY, GuiEMExtraAdvancedFactory::new);
            ClientRegistrationUtil.registerScreen(event, EMExtraMoreMachineContainerTypes.MORE_MACHINE_FACTORY, GuiEMExtraMoreMachineFactory::new);
        }
    }
}
