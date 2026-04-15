package io.github.masyumero.emextras.mixin.matrix;

import com.jerry.mekanism_extras.common.content.matrix.ExtraMatrixEnergyContainer;
import com.jerry.mekanism_extras.common.content.matrix.ExtraMatrixMultiblockData;
import io.github.masyumero.emextras.api.mixin.IMixinMatrixEnergyContainer;
import io.github.masyumero.emextras.api.mixin.IMixinMatrixMultiblockData;
import io.github.masyumero.emextras.common.tile.multiblock.TileEntityEMExtraInductionCell;
import io.github.masyumero.emextras.common.tile.multiblock.TileEntityEMExtraInductionProvider;
import mekanism.common.content.matrix.MatrixEnergyContainer;
import mekanism.common.content.matrix.MatrixMultiblockData;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

public class MixinTargetMatrixMultiblockData{

    @Mixin(value = MatrixMultiblockData.class, remap = false)
    public static abstract class MixinMatrixMultiblockData implements IMixinMatrixMultiblockData {

        @Shadow
        @Final
        private @NotNull MatrixEnergyContainer energyContainer;

        @Override
        public void emextras$addCell(TileEntityEMExtraInductionCell cell) {
            ((IMixinMatrixEnergyContainer)energyContainer).emextras$addEMExtraCell(cell.getBlockPos(), cell);
        }

        @Override
        public void emextras$addProvider(TileEntityEMExtraInductionProvider provider) {
            ((IMixinMatrixEnergyContainer)energyContainer).emextras$addEMExtraProvider(provider.getBlockPos(), provider);
        }
    }

    @Mixin(value = ExtraMatrixMultiblockData.class, remap = false)
    public static abstract class MixinExtraMatrixMultiblockData implements IMixinMatrixMultiblockData {

        @Shadow
        @Final
        private @NotNull ExtraMatrixEnergyContainer energyContainer;

        @Override
        public void emextras$addCell(TileEntityEMExtraInductionCell cell) {
            ((IMixinMatrixEnergyContainer)energyContainer).emextras$addEMExtraCell(cell.getBlockPos(), cell);
        }

        @Override
        public void emextras$addProvider(TileEntityEMExtraInductionProvider provider) {
            ((IMixinMatrixEnergyContainer)energyContainer).emextras$addEMExtraProvider(provider.getBlockPos(), provider);
        }
    }
}
