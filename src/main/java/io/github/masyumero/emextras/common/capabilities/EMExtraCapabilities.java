package io.github.masyumero.emextras.common.capabilities;

import io.github.masyumero.emextras.api.IEMExtraAlloyInteraction;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class EMExtraCapabilities {

    private EMExtraCapabilities() {}

    public static final Capability<IEMExtraAlloyInteraction> EMEXTRA_ALLOY_INTERACTION = CapabilityManager.get(new CapabilityToken<>() {});
}
