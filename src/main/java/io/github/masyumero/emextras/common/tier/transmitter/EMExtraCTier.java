package io.github.masyumero.emextras.common.tier.transmitter;

import io.github.masyumero.emextras.common.config.LoadConfig;
import mekanism.api.math.FloatingLong;
import mekanism.common.tier.CableTier;

public class EMExtraCTier {

    public static FloatingLong getCapacityAsFloatingLong(CableTier tier) {
        if (tier == null) return FloatingLong.create(8000L);
        return switch (tier) {
            case BASIC -> LoadConfig.EMEXTRA_CONFIG.absoluteOverclockedUniversalCableCapacity.get();
            case ADVANCED -> LoadConfig.EMEXTRA_CONFIG.supremeQuantumUniversalCableCapacity.get();
            case ELITE -> LoadConfig.EMEXTRA_CONFIG.cosmicDenseUniversalCableCapacity.get();
            case ULTIMATE -> LoadConfig.EMEXTRA_CONFIG.infiniteMultiversalUniversalCableCapacity.get();
        };
    }
}