package io.github.masyumero.emextras.common.tier;

import io.github.masyumero.emextras.api.tier.EMExtraTier;
import io.github.masyumero.emextras.api.tier.IEMExtraTier;
import mekanism.api.math.FloatingLong;
import mekanism.common.config.value.CachedFloatingLongValue;
import org.jetbrains.annotations.Nullable;

public enum EMExtraIPTier implements IEMExtraTier {
    ABSOLUTE_OVERCLOCKED(EMExtraTier.ABSOLUTE_OVERCLOCKED, FloatingLong.createConst(4_194_304_000L)),
    SUPREME_QUANTUM(EMExtraTier.SUPREME_QUANTUM, FloatingLong.createConst(33_554_432_000L)),
    COSMIC_DENSE(EMExtraTier.COSMIC_DENSE, FloatingLong.createConst(268_435_456_000L)),
    INFINITE_MULTIVERSAL(EMExtraTier.INFINITE_MULTIVERSAL, FloatingLong.createConst(2_147_483_648_000L));

    private final FloatingLong baseOutput;
    private final EMExtraTier emExtraTier;
    @Nullable
    private CachedFloatingLongValue outputReference;

    EMExtraIPTier(EMExtraTier tier, FloatingLong out) {
        baseOutput = out;
        emExtraTier = tier;
    }

    @Override
    public EMExtraTier getEMExtraTier() {
        return emExtraTier;
    }

    public FloatingLong getOutput() {
        return outputReference == null ? getBaseOutput() : outputReference.getOrDefault();
    }

    public FloatingLong getBaseOutput() {
        return baseOutput;
    }

    /**
     * ONLY CALL THIS FROM TierConfig. It is used to give the EMExtraIPTier a reference to the actual config value object
     */
    public void setConfigReference(CachedFloatingLongValue outputReference) {
        this.outputReference = outputReference;
    }
}
