package io.github.masyumero.emextras.api.mixin.impl;

import io.github.masyumero.emextras.common.registry.EMExtrasBlockType;
import io.github.masyumero.emextras.common.tile.multiblock.TileEntityEMExtraInductionCell;
import io.github.masyumero.emextras.common.tile.multiblock.TileEntityEMExtraInductionProvider;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import mekanism.common.content.blocktype.BlockType;
import mekanism.common.util.WorldUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;

import java.util.List;

public class ImplMixinMatrixValidator {

    public static boolean emextras$validateInner(BlockState state, Long2ObjectMap<ChunkAccess> chunkMap, BlockPos pos, Level world, List<TileEntityEMExtraInductionCell> cells, List<TileEntityEMExtraInductionProvider> providers) {
        if (BlockType.is(state.getBlock(), EMExtrasBlockType.ABSOLUTE_OVERCLOCKED_INDUCTION_CELL, EMExtrasBlockType.SUPREME_QUANTUM_INDUCTION_CELL,
                EMExtrasBlockType.COSMIC_DENSE_INDUCTION_CELL, EMExtrasBlockType.INFINITE_MULTIVERSAL_INDUCTION_CELL, EMExtrasBlockType.ABSOLUTE_OVERCLOCKED_INDUCTION_PROVIDER,
                EMExtrasBlockType.SUPREME_QUANTUM_INDUCTION_PROVIDER, EMExtrasBlockType.COSMIC_DENSE_INDUCTION_PROVIDER, EMExtrasBlockType.INFINITE_MULTIVERSAL_INDUCTION_PROVIDER)) {//Compare blocks against the type before bothering to look up the tile
            BlockEntity tile = WorldUtils.getTileEntity(world, chunkMap, pos);
            if (tile instanceof TileEntityEMExtraInductionCell cell) {
                cells.add(cell);
                return true;
            } else if (tile instanceof TileEntityEMExtraInductionProvider provider) {
                providers.add(provider);
                return true;
            }
            //Else something went wrong
        }
        return false;
    }
}
