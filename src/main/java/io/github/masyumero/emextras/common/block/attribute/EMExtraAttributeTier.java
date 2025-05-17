package io.github.masyumero.emextras.common.block.attribute;

import com.jerry.mekanism_extras.api.tier.IAdvancedTier;
import mekanism.common.MekanismLang;
import mekanism.common.content.blocktype.BlockType;

import java.util.HashMap;
import java.util.Map;

public record EMExtraAttributeTier<TIER extends IAdvancedTier>(TIER tier) implements EMExtraAttribute {
    private static final Map<IAdvancedTier, BlockType> typeCache = new HashMap<>();

    public static <T extends IAdvancedTier> BlockType getPassthroughType(T tier) {
        return typeCache.computeIfAbsent(tier, t -> BlockType.BlockTypeBuilder.createBlock(MekanismLang.EMPTY).with(new EMExtraAttribute[]{new EMExtraAttributeTier<>(t)}).build());
    }
}
