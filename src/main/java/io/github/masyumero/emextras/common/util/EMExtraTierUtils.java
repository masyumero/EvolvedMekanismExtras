package io.github.masyumero.emextras.common.util;

import com.jerry.mekanism_extras.api.tier.AdvancedTier;
import com.jerry.mekanism_extras.common.block.attribute.ExtraAttribute;
import fr.iglee42.evolvedmekanism.tiers.EMBaseTier;
import io.github.masyumero.emextras.api.tier.EMExtraTier;
import mekanism.api.tier.BaseTier;
import mekanism.common.block.attribute.Attribute;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

public class EMExtraTierUtils {

    @Nullable
    public static Object getTier(Block block) {
        if (ExtraAttribute.getAdvanceTier(block) != null) {
            return ExtraAttribute.getAdvanceTier(block);
        } else {
            return Attribute.getBaseTier(block);
        }
    }

    @Nullable
    public static EMExtraTier toEMExtraTier(Object tierObject) {
        if (tierObject instanceof AdvancedTier tier) {
            return advancedToEMExtraTier(tier);
        } else if (tierObject instanceof BaseTier tier) {
            return evolvedToEMExtraTier(tier);
        } else {
            return null;
        }
    }

    public static EMExtraTier advancedToEMExtraTier(AdvancedTier tier) {
        return switch (tier) {
            case ABSOLUTE -> EMExtraTier.ABSOLUTE_OVERCLOCKED;
            case SUPREME -> EMExtraTier.SUPREME_QUANTUM;
            case COSMIC -> EMExtraTier.COSMIC_DENSE;
            case INFINITE -> EMExtraTier.INFINITE_MULTIVERSAL;
        };
    }

    @Nullable
    public static EMExtraTier evolvedToEMExtraTier(BaseTier tier) {
        if (tier == EMBaseTier.OVERCLOCKED) return EMExtraTier.ABSOLUTE_OVERCLOCKED;
        if (tier == EMBaseTier.QUANTUM) return EMExtraTier.SUPREME_QUANTUM;
        if (tier == EMBaseTier.DENSE) return EMExtraTier.COSMIC_DENSE;
        if (tier == EMBaseTier.MULTIVERSAL) return EMExtraTier.INFINITE_MULTIVERSAL;
        return null;
    }
}
