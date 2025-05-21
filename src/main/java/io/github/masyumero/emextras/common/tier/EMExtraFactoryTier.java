package io.github.masyumero.emextras.common.tier;

import io.github.masyumero.emextras.api.tier.EMExtraTier;
import io.github.masyumero.emextras.api.tier.IEMExtraTier;

public enum EMExtraFactoryTier implements IEMExtraTier {
    ABSOLUTE_OVERCLOCKED(EMExtraTier.ABSOLUTE_OVERCLOCKED, 12),
    SUPREME_QUANTUM(EMExtraTier.SUPREME_QUANTUM, 14),
    COSMIC_DENSE(EMExtraTier.COSMIC_DENSE, 16),
    INFINITE_MULTIVERSAL(EMExtraTier.INFINITE_MULTIVERSAL, 18);

    public final int processes;
    private final EMExtraTier emExtraTier;

    EMExtraFactoryTier(EMExtraTier tier, int process) {
        processes = process;
        emExtraTier = tier;
    }

    @Override
    public EMExtraTier getEMExtraTier() {
        return emExtraTier;
    }
}