package io.github.masyumero.emextras.common.block.attribute;

import io.github.masyumero.emextras.api.tier.IEMExtraTier;
import mekanism.common.MekanismLang;
import mekanism.common.content.blocktype.BlockType;

import java.util.HashMap;
import java.util.Map;

public record EMExtraAttributeTier<TIER extends IEMExtraTier>(TIER tier) implements EMExtraAttribute {
    private static final Map<IEMExtraTier, BlockType> typeCache = new HashMap<>();

    public static <T extends IEMExtraTier> BlockType getPassthroughType(T tier) {
        return typeCache.computeIfAbsent(tier, t -> BlockType.BlockTypeBuilder.createBlock(MekanismLang.EMPTY).with(new EMExtraAttribute[]{new EMExtraAttributeTier<>(t)}).build());
    }
}
