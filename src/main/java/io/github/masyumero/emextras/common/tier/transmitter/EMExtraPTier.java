package io.github.masyumero.emextras.common.tier.transmitter;

import io.github.masyumero.emextras.common.config.LoadConfig;
import mekanism.common.tier.PipeTier;

public class EMExtraPTier {

    public static int getPipePullAmount(PipeTier tier) {
        return switch (tier) {
            case BASIC -> LoadConfig.EMEXTRA_CONFIG.absoluteOverclockedMechanicalPipePullAmount.get().intValue();
            case ADVANCED -> LoadConfig.EMEXTRA_CONFIG.supremeQuantumMechanicalPipePullAmount.get().intValue();
            case ELITE -> LoadConfig.EMEXTRA_CONFIG.cosmicDenseMechanicalPipePullAmount.get().intValue();
            case ULTIMATE -> LoadConfig.EMEXTRA_CONFIG.infiniteMultiversalMechanicalPipePullAmount.get().intValue();
        };
    }

    public static long getPipeCapacity(PipeTier tier) {
        return switch (tier) {
            case BASIC -> LoadConfig.EMEXTRA_CONFIG.absoluteOverclockedMechanicalPipeCapacity.get().longValue();
            case ADVANCED -> LoadConfig.EMEXTRA_CONFIG.supremeQuantumMechanicalPipeCapacity.get().longValue();
            case ELITE -> LoadConfig.EMEXTRA_CONFIG.cosmicDenseMechanicalPipeCapacity.get().longValue();
            case ULTIMATE -> LoadConfig.EMEXTRA_CONFIG.infiniteMultiversalMechanicalPipeCapacity.get().longValue();
        };
    }
}
