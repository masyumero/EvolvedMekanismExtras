package io.github.masyumero.emextras.common.tier;

import io.github.masyumero.emextras.api.tier.EMExtraTier;
import io.github.masyumero.emextras.api.tier.IEMExtraTier;

public enum EMExtraFactoryTier implements IEMExtraTier {
    ABSOLUTE(EMExtraTier.ABSOLUTE, 11,false),
    SUPREME(EMExtraTier.SUPREME, 13, false),
    COSMIC(EMExtraTier.COSMIC, 15,false),
    INFINITE(EMExtraTier.INFINITE, 17,false),
    ABSOLUTE_OVERCLOCKED(EMExtraTier.ABSOLUTE_OVERCLOCKED, 12, 91, 53),
    SUPREME_QUANTUM(EMExtraTier.SUPREME_QUANTUM, 14, 129, 72),
    COSMIC_DENSE(EMExtraTier.COSMIC_DENSE, 16, 167, 91),
    INFINITE_MULTIVERSAL(EMExtraTier.INFINITE_MULTIVERSAL, 18, 205, 110);

    public final int processes;
    public int imageWidth;
    public int inventoryLabelX;
    private boolean isEvolved = true;
    private final EMExtraTier emExtraTier;

    EMExtraFactoryTier(EMExtraTier tier, int process, int guiImageWidth, int guiInventoryLabelX) {
        processes = process;
        emExtraTier = tier;
        imageWidth = guiImageWidth;
        inventoryLabelX = guiInventoryLabelX;
    }

    EMExtraFactoryTier(EMExtraTier tier, int process, boolean isEvolved) {
        processes = process;
        emExtraTier = tier;
        this.isEvolved = isEvolved;
    }

    public boolean isEvolved() {
        return isEvolved;
    }

    @Override
    public EMExtraTier getEMExtraTier() {
        return emExtraTier;
    }
}