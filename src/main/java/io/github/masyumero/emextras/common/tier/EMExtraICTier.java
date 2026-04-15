package io.github.masyumero.emextras.common.tier;

import io.github.masyumero.emextras.api.tier.EMExtraTier;
import io.github.masyumero.emextras.api.tier.IEMExtraTier;
import mekanism.api.math.FloatingLong;
import mekanism.common.config.value.CachedFloatingLongValue;
import org.jetbrains.annotations.Nullable;

public enum EMExtraICTier implements IEMExtraTier {
    ABSOLUTE_OVERCLOCKED(EMExtraTier.ABSOLUTE_OVERCLOCKED, FloatingLong.createConst(131_072_000_000_000L)),
    SUPREME_QUANTUM(EMExtraTier.SUPREME_QUANTUM, FloatingLong.createConst(1_048_576_000_000_000L)),
    COSMIC_DENSE(EMExtraTier.COSMIC_DENSE, FloatingLong.createConst(8_388_608_000_000_000L)),
    INFINITE_MULTIVERSAL(EMExtraTier.INFINITE_MULTIVERSAL, FloatingLong.createConst(Long.MAX_VALUE));

    private final FloatingLong baseMaxEnergy;
    private final EMExtraTier emExtraTier;
    @Nullable
    private CachedFloatingLongValue storageReference;

    EMExtraICTier(EMExtraTier tier, FloatingLong out) {
        baseMaxEnergy = out;
        emExtraTier = tier;
    }

    @Override
    public EMExtraTier getEMExtraTier() {
        return emExtraTier;
    }

    public FloatingLong getMaxEnergy() {
        return storageReference == null ? getBaseMaxEnergy() : storageReference.getOrDefault();
    }

    public FloatingLong getBaseMaxEnergy() {
        return baseMaxEnergy;
    }

    /**
     * ONLY CALL THIS FROM TierConfig. It is used to give the EMExtraICTier a reference to the actual config value object
     */
    public void setConfigReference(CachedFloatingLongValue storageReference) {
        this.storageReference = storageReference;
    }
}
