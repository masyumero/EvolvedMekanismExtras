package io.github.masyumero.emextras.common.util;

import com.jerry.mekanism_extras.api.tier.AdvancedTier;
import fr.iglee42.evolvedmekanism.tiers.EMBaseTier;
import io.github.masyumero.emextras.api.tier.EMExtraTier;
import mekanism.api.tier.BaseTier;

public class EMExtraTierUtils {

    public static EMExtraTier advancedToEMExtraTier(AdvancedTier tier) {
        return switch (tier) {
            case ABSOLUTE -> EMExtraTier.ABSOLUTE_OVERCLOCKED;
            case SUPREME -> EMExtraTier.SUPREME_QUANTUM;
            case COSMIC -> EMExtraTier.COSMIC_DENSE;
            case INFINITE -> EMExtraTier.INFINITE_MULTIVERSAL;
        };
    }

    public static EMExtraTier evolvedToEMExtraTier(BaseTier tier) {
        if (tier == EMBaseTier.OVERCLOCKED) return EMExtraTier.ABSOLUTE_OVERCLOCKED;
        if (tier == EMBaseTier.QUANTUM) return EMExtraTier.SUPREME_QUANTUM;
        if (tier == EMBaseTier.DENSE) return EMExtraTier.COSMIC_DENSE;
        if (tier == EMBaseTier.MULTIVERSAL) return EMExtraTier.INFINITE_MULTIVERSAL;
        return null;
    }
}
