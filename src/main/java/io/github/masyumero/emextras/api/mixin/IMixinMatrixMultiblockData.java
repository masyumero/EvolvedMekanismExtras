package io.github.masyumero.emextras.api.mixin;

import io.github.masyumero.emextras.common.tile.multiblock.TileEntityEMExtraInductionCell;
import io.github.masyumero.emextras.common.tile.multiblock.TileEntityEMExtraInductionProvider;

public interface IMixinMatrixMultiblockData {

    void emextras$addCell(TileEntityEMExtraInductionCell cell);

    void emextras$addProvider(TileEntityEMExtraInductionProvider provider);
}
