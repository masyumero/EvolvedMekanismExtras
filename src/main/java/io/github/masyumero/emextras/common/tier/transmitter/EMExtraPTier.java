package io.github.masyumero.emextras.common.tier.transmitter;

import io.github.masyumero.emextras.common.config.LoadConfig;
import mekanism.common.tier.PipeTier;

public class EMExtraPTier {

    public static int getPipePullAmount(PipeTier tier) {
        return switch (tier) {
            case BASIC -> LoadConfig.emExtraConfig.absoluteOverclockedMechanicalPipePullAmount.get().intValue();
            case ADVANCED -> LoadConfig.emExtraConfig.supremeQuantumMechanicalPipePullAmount.get().intValue();
            case ELITE -> LoadConfig.emExtraConfig.cosmicDenseMechanicalPipePullAmount.get().intValue();
            case ULTIMATE -> LoadConfig.emExtraConfig.infiniteMultiversalMechanicalPipePullAmount.get().intValue();
        };
    }

    public static long getPipeCapacity(PipeTier tier) {
        return switch (tier) {
            case BASIC -> LoadConfig.emExtraConfig.absoluteOverclockedMechanicalPipeCapacity.get().longValue();
            case ADVANCED -> LoadConfig.emExtraConfig.supremeQuantumMechanicalPipeCapacity.get().longValue();
            case ELITE -> LoadConfig.emExtraConfig.cosmicDenseMechanicalPipeCapacity.get().longValue();
            case ULTIMATE -> LoadConfig.emExtraConfig.infiniteMultiversalMechanicalPipeCapacity.get().longValue();
        };
    }
}
