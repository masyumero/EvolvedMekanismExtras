package io.github.masyumero.emextras.common.util;

import io.github.masyumero.emextras.common.content.blocktype.EMExtraFactoryType;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;

public class EMExtraEnumUtils {

    private EMExtraEnumUtils() {
    }

    public static final EMExtraFactoryTier[] EMEXTRA_FACTORY_TIERS = EMExtraFactoryTier.values();
    public static final EMExtraFactoryType[] EMEXTRA_FACTORY_TYPES = EMExtraFactoryType.values();
}
