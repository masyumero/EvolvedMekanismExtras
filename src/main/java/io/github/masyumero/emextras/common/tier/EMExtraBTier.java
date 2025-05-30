package io.github.masyumero.emextras.common.tier;

import io.github.masyumero.emextras.api.tier.EMExtraTier;
import io.github.masyumero.emextras.api.tier.IEMExtraTier;
import mekanism.common.config.value.CachedIntValue;
import mekanism.common.config.value.CachedLongValue;

public enum EMExtraBTier implements IEMExtraTier {
    ABSOLUTE_OVERCLOCKED(EMExtraTier.ABSOLUTE_OVERCLOCKED, 1_048_576*2),
    SUPREME_QUANTUM(EMExtraTier.SUPREME_QUANTUM, 8_388_608*2),
    COSMIC_DENSE(EMExtraTier.COSMIC_DENSE, 134_217_728*2),
    INFINITE_MULTIVERSAL(EMExtraTier.INFINITE_MULTIVERSAL, Integer.MAX_VALUE);

    private final int emExtraStorage;
    private final EMExtraTier emExtraTier;
    private CachedIntValue storageReference;

    EMExtraBTier(EMExtraTier tier, int s) {
        emExtraTier = tier;
        emExtraStorage = s;
    }

    @Override
    public EMExtraTier getEMExtraTier() {
        return emExtraTier;
    }

    public int getStorage() {
        return storageReference == null ? getEMExtraStorage() : storageReference.getOrDefault();
    }

    public int getEMExtraStorage() {
        return emExtraStorage;
    }

    /**
     * ONLY CALL THIS FROM TierConfig. It is used to give the BinTier a reference to the actual config value object
     */
    public void setConfigReference(CachedIntValue storageReference) {
        this.storageReference = storageReference;
    }
}
