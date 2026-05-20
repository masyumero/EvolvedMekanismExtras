package io.github.masyumero.emextras.common.tier.transmitter;

import io.github.masyumero.emextras.common.config.LoadConfig;
import mekanism.common.tier.TubeTier;

public class EMExtraTTier {

    public static long getTubePullAmount(TubeTier tier) {
        return switch (tier) {
            case BASIC -> LoadConfig.emExtraConfig.absoluteOverclockedPressurizedTubePullAmount.get().longValue();
            case ADVANCED -> LoadConfig.emExtraConfig.supremeQuantumPressurizedTubePullAmount.get().longValue();
            case ELITE -> LoadConfig.emExtraConfig.cosmicDensePressurizedTubePullAmount.get().longValue();
            case ULTIMATE -> LoadConfig.emExtraConfig.infiniteMultiversalPressurizedTubePullAmount.get().longValue();
        };
    }

    public static long getTubeCapacity(TubeTier tier) {
        return switch (tier) {
            case BASIC -> LoadConfig.emExtraConfig.absoluteOverclockedPressurizedTubeCapacity.get().longValue();
            case ADVANCED -> LoadConfig.emExtraConfig.supremeQuantumPressurizedTubeCapacity.get().longValue();
            case ELITE -> LoadConfig.emExtraConfig.cosmicDensePressurizedTubeCapacity.get().longValue();
            case ULTIMATE -> LoadConfig.emExtraConfig.infiniteMultiversalPressurizedTubeCapacity.get().longValue();
        };
    }
}
