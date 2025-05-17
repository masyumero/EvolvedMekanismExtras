package io.github.masyumero.emextras.client.events;

import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.client.gui.machine.GuiEMExtraFactory;
import io.github.masyumero.emextras.common.registry.EMExtrasContainerTypes;
import mekanism.client.ClientRegistrationUtil;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(modid = EMExtras.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegistration {
    private ClientRegistration() {
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void registerContainers(RegisterEvent event) {
        event.register(Registries.MENU, helper -> {
            ClientRegistrationUtil.registerScreen(EMExtrasContainerTypes.FACTORY, GuiEMExtraFactory::new);
        });
    }

}
