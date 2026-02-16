package io.github.masyumero.emextras.common.block.attribute;

import io.github.masyumero.emextras.api.tier.EMExtraTier;
import io.github.masyumero.emextras.api.tier.IEMExtraTier;
import mekanism.common.block.attribute.Attribute;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

public interface EMExtraAttribute extends Attribute {

    @Nullable
    static <TIER extends IEMExtraTier> TIER getEMExtraTier(Holder<Block> block, Class<TIER> tierClass) {
        return getEMExtraTier(block.value(), tierClass);
    }

    @Nullable
    static <TIER extends IEMExtraTier> TIER getEMExtraTier(Block block, Class<TIER> tierClass) {
        EMExtraAttributeTier<?> attr = Attribute.get(block, EMExtraAttributeTier.class);
        return attr == null ? null : tierClass.cast(attr.tier());
    }

    @Nullable
    static EMExtraTier getEMExtraTier(Holder<Block> block) {
        EMExtraAttributeTier<?> attr = Attribute.get(block, EMExtraAttributeTier.class);
        return attr == null ? null : attr.tier().getEMExtraTier();
    }
}
