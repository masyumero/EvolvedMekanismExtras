package io.github.masyumero.emextras.mixin.matrix;

import com.jerry.mekanism_extras.common.content.matrix.ExtraMatrixMultiblockData;
import com.jerry.mekanism_extras.common.content.matrix.ExtraMatrixValidator;
import io.github.masyumero.emextras.api.mixin.IMixinMatrixMultiblockData;
import io.github.masyumero.emextras.api.mixin.IMixinMatrixValidator;
import io.github.masyumero.emextras.api.mixin.impl.ImplMixinMatrixValidator;
import io.github.masyumero.emextras.common.tile.multiblock.TileEntityEMExtraInductionCell;
import io.github.masyumero.emextras.common.tile.multiblock.TileEntityEMExtraInductionProvider;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import mekanism.common.content.matrix.MatrixMultiblockData;
import mekanism.common.content.matrix.MatrixValidator;
import mekanism.common.lib.multiblock.CuboidStructureValidator;
import mekanism.common.lib.multiblock.FormationProtocol;
import mekanism.common.lib.multiblock.MultiblockData;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

public class MixinTargetMatrixValidator {

    @Mixin(value = {
            MatrixValidator.class,
            ExtraMatrixValidator.class
    }, remap = false)
    public static abstract class MixinMatrixValidator extends CuboidStructureValidator<MultiblockData> implements IMixinMatrixValidator {

        @Unique
        private final List<TileEntityEMExtraInductionCell> emextras$cells = new ArrayList<>();
        @Unique
        private final List<TileEntityEMExtraInductionProvider> emextras$providers = new ArrayList<>();

        @Inject(method = "validateInner", at = @At(value = "TAIL"), cancellable = true)
        private void validetaInnerInject(BlockState state, Long2ObjectMap<ChunkAccess> chunkMap, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
            cir.setReturnValue(ImplMixinMatrixValidator.emextras$validateInner(state, chunkMap, pos, world, emextras$cells, emextras$providers));
        }

        @Override
        public FormationProtocol.FormationResult emextras$postcheck(MultiblockData structure, Long2ObjectMap<ChunkAccess> chunkMap) {
            emextras$cells.forEach(((IMixinMatrixMultiblockData)structure)::emextras$addCell);
            emextras$providers.forEach(((IMixinMatrixMultiblockData)structure)::emextras$addProvider);
            return FormationProtocol.FormationResult.SUCCESS;
        }
    }

    @Mixin(value = MatrixValidator.class, remap = false)
    public static abstract class MixinMatrixValidatorPostcheck {

        @Inject(method = "postcheck(Lmekanism/common/content/matrix/MatrixMultiblockData;Lit/unimi/dsi/fastutil/longs/Long2ObjectMap;)Lmekanism/common/lib/multiblock/FormationProtocol$FormationResult;", at = @At("TAIL"), cancellable = true)
        private void postcheck(MatrixMultiblockData structure, Long2ObjectMap<ChunkAccess> chunkMap, CallbackInfoReturnable<FormationProtocol.FormationResult> cir) {
            cir.setReturnValue(((IMixinMatrixValidator)this).emextras$postcheck(structure, chunkMap));
        }
    }

    @Mixin(value = ExtraMatrixValidator.class, remap = false)
    public static abstract class MixinExtraMatrixValidatorPostcheck {

        @Inject(method = "postcheck(Lcom/jerry/mekanism_extras/common/content/matrix/ExtraMatrixMultiblockData;Lit/unimi/dsi/fastutil/longs/Long2ObjectMap;)Lmekanism/common/lib/multiblock/FormationProtocol$FormationResult;", at = @At("TAIL"), cancellable = true)
        private void postcheck(ExtraMatrixMultiblockData structure, Long2ObjectMap<ChunkAccess> chunkMap, CallbackInfoReturnable<FormationProtocol.FormationResult> cir) {
            cir.setReturnValue(((IMixinMatrixValidator)this).emextras$postcheck(structure, chunkMap));
        }
    }
}
