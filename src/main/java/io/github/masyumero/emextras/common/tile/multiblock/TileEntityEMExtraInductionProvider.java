package io.github.masyumero.emextras.common.tile.multiblock;

import io.github.masyumero.emextras.common.block.attribute.EMExtraAttribute;
import io.github.masyumero.emextras.common.tier.EMExtraIPTier;
import mekanism.api.providers.IBlockProvider;
import mekanism.common.tile.prefab.TileEntityInternalMultiblock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class TileEntityEMExtraInductionProvider extends TileEntityInternalMultiblock {

    public EMExtraIPTier tier;

    public TileEntityEMExtraInductionProvider(IBlockProvider blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state);
    }

    @Override
    protected void presetVariables() {
        super.presetVariables();
        tier = EMExtraAttribute.getTier(getBlockType(), EMExtraIPTier.class);
    }
}
