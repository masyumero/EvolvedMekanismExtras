package io.github.masyumero.emextras.common.util;

import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import mekanism.common.content.blocktype.FactoryType;

public class EMExtraEnumUtils {

    private EMExtraEnumUtils() {
    }

    public static final EMExtraFactoryTier[] EMEXTRA_FACTORY_TIERS = EMExtraFactoryTier.values();
    public static final FactoryType[] FACTORY_TYPES = new FactoryType[] {
            FactoryType.SMELTING,
            FactoryType.ENRICHING,
            FactoryType.CRUSHING,
            FactoryType.COMPRESSING,
            FactoryType.COMBINING,
            FactoryType.PURIFYING,
            FactoryType.INJECTING,
            FactoryType.INFUSING,
            FactoryType.SAWING,
            EMFactoryType.ALLOYING
    };
}
