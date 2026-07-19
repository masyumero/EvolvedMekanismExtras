package io.github.masyumero.emextras.common.tier.transmitter;

import io.github.masyumero.emextras.common.config.LoadConfig;
import mekanism.common.tier.TubeTier;

public class EMExtraTTier {

    public static long getTubePullAmount(TubeTier tier) {
        return switch (tier) {
            case BASIC -> LoadConfig.EMEXTRA_CONFIG.absoluteOverclockedPressurizedTubePullAmount.get().longValue();
            case ADVANCED -> LoadConfig.EMEXTRA_CONFIG.supremeQuantumPressurizedTubePullAmount.get().longValue();
            case ELITE -> LoadConfig.EMEXTRA_CONFIG.cosmicDensePressurizedTubePullAmount.get().longValue();
            case ULTIMATE -> LoadConfig.EMEXTRA_CONFIG.infiniteMultiversalPressurizedTubePullAmount.get().longValue();
        };
    }

    public static long getTubeCapacity(TubeTier tier) {
        return switch (tier) {
            case BASIC -> LoadConfig.EMEXTRA_CONFIG.absoluteOverclockedPressurizedTubeCapacity.get().longValue();
            case ADVANCED -> LoadConfig.EMEXTRA_CONFIG.supremeQuantumPressurizedTubeCapacity.get().longValue();
            case ELITE -> LoadConfig.EMEXTRA_CONFIG.cosmicDensePressurizedTubeCapacity.get().longValue();
            case ULTIMATE -> LoadConfig.EMEXTRA_CONFIG.infiniteMultiversalPressurizedTubeCapacity.get().longValue();
        };
    }
}
