package io.github.masyumero.emextras.common.tier.transmitter;

import io.github.masyumero.emextras.common.config.LoadConfig;
import mekanism.api.math.FloatingLong;
import mekanism.common.tier.CableTier;

public class EMExtraCTier {

    public static FloatingLong getCapacityAsFloatingLong(CableTier tier) {
        if (tier == null) return FloatingLong.create(8000L);
        return switch (tier) {
            case BASIC -> LoadConfig.emExtraConfig.absoluteOverclockedUniversalCableCapacity.get();
            case ADVANCED -> LoadConfig.emExtraConfig.supremeQuantumUniversalCableCapacity.get();
            case ELITE -> LoadConfig.emExtraConfig.cosmicDenseUniversalCableCapacity.get();
            case ULTIMATE -> LoadConfig.emExtraConfig.infiniteMultiversalUniversalCableCapacity.get();
        };
    }
}