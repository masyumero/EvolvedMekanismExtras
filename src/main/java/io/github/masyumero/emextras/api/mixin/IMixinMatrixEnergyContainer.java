package io.github.masyumero.emextras.api.mixin;

import io.github.masyumero.emextras.common.tile.multiblock.TileEntityEMExtraInductionCell;
import io.github.masyumero.emextras.common.tile.multiblock.TileEntityEMExtraInductionProvider;
import net.minecraft.core.BlockPos;

public interface IMixinMatrixEnergyContainer {

    void emextras$addEMExtraCell(BlockPos pos, TileEntityEMExtraInductionCell cell);

    void emextras$addEMExtraProvider(BlockPos pos, TileEntityEMExtraInductionProvider provider);

    void emextras$removeInternal(BlockPos pos);
}
