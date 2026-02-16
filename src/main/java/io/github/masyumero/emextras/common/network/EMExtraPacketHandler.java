package io.github.masyumero.emextras.common.network;

import io.github.masyumero.emextras.common.network.to_server.EMExtraPacketGuiInteract;
import mekanism.common.lib.Version;
import mekanism.common.network.BasePacketHandler;
import mekanism.common.network.to_client.configuration.SyncAllSecurityData;

import net.minecraft.network.protocol.configuration.ServerConfigurationPacketListener;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.network.event.RegisterConfigurationTasksEvent;

public class EMExtraPacketHandler extends BasePacketHandler {

    public EMExtraPacketHandler(IEventBus modEventBus, Version version) {
        super(modEventBus, version);
        modEventBus.addListener(RegisterConfigurationTasksEvent.class, event -> {
            ServerConfigurationPacketListener listener = event.getListener();
            event.register(new SyncAllSecurityData(listener));
        });
    }

    @Override
    protected void registerClientToServer(PacketRegistrar registrar) {
        registrar.play(EMExtraPacketGuiInteract.TYPE, EMExtraPacketGuiInteract.STREAM_CODEC);
    }

    @Override
    protected void registerServerToClient(PacketRegistrar registrar) {}
}