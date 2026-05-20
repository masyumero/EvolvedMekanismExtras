package io.github.masyumero.emextras.common.tier.transmitter;

import io.github.masyumero.emextras.common.config.LoadConfig;
import mekanism.common.tier.ConductorTier;

public class EMExtraTCTier {
    
    public static long getConduction(ConductorTier tier) {
        return switch (tier) {
            case BASIC -> LoadConfig.emExtraConfig.absoluteOverclockedThermodynamicConductorConduction.get().longValue();
            case ADVANCED -> LoadConfig.emExtraConfig.supremeQuantumThermodynamicConductorConduction.get().longValue();
            case ELITE -> LoadConfig.emExtraConfig.cosmicDenseThermodynamicConductorConduction.get().longValue();
            case ULTIMATE -> LoadConfig.emExtraConfig.infiniteMultiversalThermodynamicConductorConduction.get().longValue();
        };
    }

    public static long getHeatCapacity(ConductorTier tier) {
        return switch (tier) {
            case BASIC -> LoadConfig.emExtraConfig.absoluteOverclockedThermodynamicConductornCapacity.get().longValue();
            case ADVANCED -> LoadConfig.emExtraConfig.supremeQuantumThermodynamicConductornCapacity.get().longValue();
            case ELITE -> LoadConfig.emExtraConfig.cosmicDenseThermodynamicConductornCapacity.get().longValue();
            case ULTIMATE -> LoadConfig.emExtraConfig.infiniteMultiversalThermodynamicConductornCapacity.get().longValue();
        };
    }

    public static long getConductionInsulation(ConductorTier tier) {
        return switch (tier) {
            case BASIC -> LoadConfig.emExtraConfig.absoluteOverclockedThermodynamicConductornInsulation.get().longValue();
            case ADVANCED -> LoadConfig.emExtraConfig.supremeQuantumThermodynamicConductornInsulation.get().longValue();
            case ELITE -> LoadConfig.emExtraConfig.cosmicDenseThermodynamicConductornInsulation.get().longValue();
            case ULTIMATE -> LoadConfig.emExtraConfig.infiniteMultiversalThermodynamicConductornInsulation.get().longValue();
        };
    }
}
