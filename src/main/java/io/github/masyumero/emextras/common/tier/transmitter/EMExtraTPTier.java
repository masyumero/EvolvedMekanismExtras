package io.github.masyumero.emextras.common.tier.transmitter;

import io.github.masyumero.emextras.common.config.LoadConfig;
import mekanism.common.tier.TransporterTier;

public class EMExtraTPTier {

    public static int getSpeed(TransporterTier tier) {
        return switch (tier) {
            case BASIC -> LoadConfig.EMEXTRA_CONFIG.absoluteOverclockedLogisticalTransporterSpeed.get().intValue();
            case ADVANCED -> LoadConfig.EMEXTRA_CONFIG.supremeQuantumLogisticalTransporterSpeed.get().intValue();
            case ELITE -> LoadConfig.EMEXTRA_CONFIG.cosmicDenseLogisticalTransporterSpeed.get().intValue();
            case ULTIMATE -> LoadConfig.EMEXTRA_CONFIG.infiniteMultiversalLogisticalTransporterSpeed.get().intValue();
        };
    }

    public static int getPullAmount(TransporterTier tier) {
        return switch (tier) {
            case BASIC -> LoadConfig.EMEXTRA_CONFIG.absoluteOverclockedLogisticalTransporterPullAmount.get().intValue();
            case ADVANCED -> LoadConfig.EMEXTRA_CONFIG.supremeQuantumLogisticalTransporterPullAmount.get().intValue();
            case ELITE -> LoadConfig.EMEXTRA_CONFIG.cosmicDenseLogisticalTransporterPullAmount.get().intValue();
            case ULTIMATE -> LoadConfig.EMEXTRA_CONFIG.infiniteMultiversalLogisticalTransporterPullAmount.get().intValue();
        };
    }
}
