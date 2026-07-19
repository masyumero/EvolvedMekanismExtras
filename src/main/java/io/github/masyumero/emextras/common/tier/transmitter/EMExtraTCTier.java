package io.github.masyumero.emextras.common.tier.transmitter;

import io.github.masyumero.emextras.common.config.LoadConfig;
import mekanism.common.tier.ConductorTier;

public class EMExtraTCTier {
    
    public static long getConduction(ConductorTier tier) {
        return switch (tier) {
            case BASIC -> LoadConfig.EMEXTRA_CONFIG.absoluteOverclockedThermodynamicConductorConduction.get().longValue();
            case ADVANCED -> LoadConfig.EMEXTRA_CONFIG.supremeQuantumThermodynamicConductorConduction.get().longValue();
            case ELITE -> LoadConfig.EMEXTRA_CONFIG.cosmicDenseThermodynamicConductorConduction.get().longValue();
            case ULTIMATE -> LoadConfig.EMEXTRA_CONFIG.infiniteMultiversalThermodynamicConductorConduction.get().longValue();
        };
    }

    public static long getHeatCapacity(ConductorTier tier) {
        return switch (tier) {
            case BASIC -> LoadConfig.EMEXTRA_CONFIG.absoluteOverclockedThermodynamicConductornCapacity.get().longValue();
            case ADVANCED -> LoadConfig.EMEXTRA_CONFIG.supremeQuantumThermodynamicConductornCapacity.get().longValue();
            case ELITE -> LoadConfig.EMEXTRA_CONFIG.cosmicDenseThermodynamicConductornCapacity.get().longValue();
            case ULTIMATE -> LoadConfig.EMEXTRA_CONFIG.infiniteMultiversalThermodynamicConductornCapacity.get().longValue();
        };
    }

    public static long getConductionInsulation(ConductorTier tier) {
        return switch (tier) {
            case BASIC -> LoadConfig.EMEXTRA_CONFIG.absoluteOverclockedThermodynamicConductornInsulation.get().longValue();
            case ADVANCED -> LoadConfig.EMEXTRA_CONFIG.supremeQuantumThermodynamicConductornInsulation.get().longValue();
            case ELITE -> LoadConfig.EMEXTRA_CONFIG.cosmicDenseThermodynamicConductornInsulation.get().longValue();
            case ULTIMATE -> LoadConfig.EMEXTRA_CONFIG.infiniteMultiversalThermodynamicConductornInsulation.get().longValue();
        };
    }
}
