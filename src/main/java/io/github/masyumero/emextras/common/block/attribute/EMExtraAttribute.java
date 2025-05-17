package io.github.masyumero.emextras.common.block.attribute;

import com.jerry.mekanism_extras.api.tier.AdvancedTier;
import com.jerry.mekanism_extras.api.tier.IAdvancedTier;
import mekanism.common.block.attribute.Attribute;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

public interface EMExtraAttribute extends Attribute {
    @Nullable
    @SuppressWarnings("unchecked")
    static <TIER extends IAdvancedTier> TIER getTier(Block block, Class<TIER> tierClass) {
        EMExtraAttributeTier<TIER> attr = Attribute.get(block, EMExtraAttributeTier.class);
        return attr == null ? null : attr.tier();
    }

    @Nullable
    static AdvancedTier getAdvanceTier(Block block) {
        EMExtraAttributeTier<?> attr = Attribute.get(block, EMExtraAttributeTier.class);
        return attr == null ? null : attr.tier().getAdvanceTier();
    }
}
