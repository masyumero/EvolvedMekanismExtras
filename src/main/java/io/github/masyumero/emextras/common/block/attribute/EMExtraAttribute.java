package io.github.masyumero.emextras.common.block.attribute;

import io.github.masyumero.emextras.api.tier.EMExtraTier;
import io.github.masyumero.emextras.api.tier.IEMExtraTier;
import mekanism.common.block.attribute.Attribute;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

public interface EMExtraAttribute extends Attribute {
    @Nullable
    @SuppressWarnings("unchecked")
    static <TIER extends IEMExtraTier> TIER getTier(Block block, Class<TIER> tierClass) {
        EMExtraAttributeTier<TIER> attr = Attribute.get(block, EMExtraAttributeTier.class);
        return attr == null ? null : attr.tier();
    }

    @Nullable
    static EMExtraTier getEMExtraTier(Block block) {
        EMExtraAttributeTier<?> attr = Attribute.get(block, EMExtraAttributeTier.class);
        return attr == null ? null : attr.tier().getEMExtraTier();
    }
}
