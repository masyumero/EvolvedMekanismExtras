package io.github.masyumero.emextras.common.tier.transmitter;

import io.github.masyumero.emextras.common.config.LoadConfig;
import mekanism.common.tier.TransporterTier;

public class EMExtraTPTier {

    public static int getSpeed(TransporterTier tier) {
        return switch (tier) {
            case BASIC -> LoadConfig.emExtraConfig.absoluteOverclockedLogisticalTransporterSpeed.get().intValue();
            case ADVANCED -> LoadConfig.emExtraConfig.supremeQuantumLogisticalTransporterSpeed.get().intValue();
            case ELITE -> LoadConfig.emExtraConfig.cosmicDenseLogisticalTransporterSpeed.get().intValue();
            case ULTIMATE -> LoadConfig.emExtraConfig.infiniteMultiversalLogisticalTransporterSpeed.get().intValue();
        };
    }

    public static int getPullAmount(TransporterTier tier) {
        return switch (tier) {
            case BASIC -> LoadConfig.emExtraConfig.absoluteOverclockedLogisticalTransporterPullAmount.get().intValue();
            case ADVANCED -> LoadConfig.emExtraConfig.supremeQuantumLogisticalTransporterPullAmount.get().intValue();
            case ELITE -> LoadConfig.emExtraConfig.cosmicDenseLogisticalTransporterPullAmount.get().intValue();
            case ULTIMATE -> LoadConfig.emExtraConfig.infiniteMultiversalLogisticalTransporterPullAmount.get().intValue();
        };
    }
}
