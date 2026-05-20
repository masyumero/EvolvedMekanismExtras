package io.github.masyumero.emextras.common.util;

import com.jerry.mekanism_extras.api.tier.AdvancedTier;
import io.github.masyumero.emextras.api.tier.EMExtraTier;

public class EMExtraTierUtils {

    public static EMExtraTier advanceToEMExtraTier(AdvancedTier tier) {
        return switch (tier) {
            case ABSOLUTE -> EMExtraTier.ABSOLUTE_OVERCLOCKED;
            case SUPREME -> EMExtraTier.SUPREME_QUANTUM;
            case COSMIC -> EMExtraTier.COSMIC_DENSE;
            case INFINITE -> EMExtraTier.INFINITE_MULTIVERSAL;
        };
    }
}
