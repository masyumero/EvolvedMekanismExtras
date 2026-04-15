package io.github.masyumero.emextras.mixin.matrix;

import com.jerry.mekanism_extras.common.content.matrix.ExtraMatrixEnergyContainer;
import io.github.masyumero.emextras.api.mixin.IMixinMatrixEnergyContainer;
import io.github.masyumero.emextras.api.mixin.impl.ImplMixinMatrixEnergyContainer;
import io.github.masyumero.emextras.common.tier.EMExtraIPTier;
import io.github.masyumero.emextras.common.tile.multiblock.TileEntityEMExtraInductionCell;
import io.github.masyumero.emextras.common.tile.multiblock.TileEntityEMExtraInductionProvider;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.energy.IEnergyContainer;
import mekanism.api.math.FloatingLong;
import mekanism.common.capabilities.energy.MachineEnergyContainer;
import mekanism.common.content.matrix.MatrixEnergyContainer;
import net.minecraft.core.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;
import java.util.Set;

public abstract class MixinTargetMatrixEnergyContainer {

    @NothingNullByDefault
    @Mixin(value = MatrixEnergyContainer.class, remap = false)
    public static abstract class MixinMatrixEnergyContainer implements IMixinMatrixEnergyContainer {

        @Shadow
        private FloatingLong storageCap;
        @Shadow
        private FloatingLong cachedTotal;
        @Shadow
        private FloatingLong transferCap;
        @Shadow
        @Final
        private Set<BlockPos> invalidPositions;
        @Unique
        private final Map<BlockPos, EMExtraIPTier> emextras$providers = new Object2ObjectOpenHashMap<>();
        @Unique
        private final Map<BlockPos, IEnergyContainer> emextras$cells = new Object2ObjectOpenHashMap<>();

        @Override
        public void emextras$addEMExtraCell(BlockPos pos, TileEntityEMExtraInductionCell cell) {
            MachineEnergyContainer<TileEntityEMExtraInductionCell> energyContainer = cell.getEnergyContainer();
            emextras$cells.put(pos, energyContainer);
            storageCap = storageCap.plusEqual(energyContainer.getMaxEnergy());
            cachedTotal = cachedTotal.plusEqual(energyContainer.getEnergy());
        }

        @Override
        public void emextras$addEMExtraProvider(BlockPos pos, TileEntityEMExtraInductionProvider provider) {
            emextras$providers.put(pos, provider.tier);
            transferCap = transferCap.plusEqual(provider.tier.getOutput());
        }

        @Inject(method = "removeInternal", at = @At("TAIL"))
        private void removeInternalInject(BlockPos pos, CallbackInfo ci) {
            emextras$removeInternal(pos);
        }

        @Override
        public void emextras$removeInternal(BlockPos pos) {
            if (invalidPositions.add(pos)) {
                if (emextras$providers.containsKey(pos)) {
                    //It is a provider
                    transferCap = transferCap.minusEqual(emextras$providers.get(pos).getOutput());
                } else if (emextras$cells.containsKey(pos)) {
                    //It is a cell
                    IEnergyContainer cellContainer = emextras$cells.get(pos);
                    storageCap = storageCap.plusEqual(cellContainer.getMaxEnergy());
                    cachedTotal = cachedTotal.minusEqual(cellContainer.getEnergy());
                }
            }
        }

        @Inject(method = "invalidate", at = @At("TAIL"))
        private void invalidateInject(CallbackInfo ci) {
            ImplMixinMatrixEnergyContainer.emextras$invalidate(emextras$providers, emextras$cells);
        }

        @Inject(method = "tick", at = @At("HEAD"))
        private void tickInject(CallbackInfo ci) {
            ImplMixinMatrixEnergyContainer.emextras$tick(invalidPositions, emextras$providers, emextras$cells);
        }

        @Inject(method = "addEnergy", at = @At(value = "INVOKE", target = "Lmekanism/api/math/FloatingLong;plusEqual(Lmekanism/api/math/FloatingLong;)Lmekanism/api/math/FloatingLong;"))
        private void addEnergyInject(FloatingLong energy, CallbackInfo ci) {
            ImplMixinMatrixEnergyContainer.emextras$addEnergy(energy, emextras$cells);
        }

        @Inject(method = "removeEnergy", at = @At(value = "INVOKE", target = "Lmekanism/api/math/FloatingLong;minusEqual(Lmekanism/api/math/FloatingLong;)Lmekanism/api/math/FloatingLong;", ordinal = 0))
        private void removeEnergyInject(FloatingLong energy, CallbackInfo ci) {
            ImplMixinMatrixEnergyContainer.emextras$removeEnergy(energy, emextras$cells);
        }

        @Redirect(method = "getCells", at = @At(value = "INVOKE", target = "Ljava/util/Map;size()I"))
        private int getCellsRedirect(Map<?, ?> instance) {
            return instance.size() + emextras$cells.size();
        }

        @Redirect(method = "getProviders", at = @At(value = "INVOKE", target = "Ljava/util/Map;size()I"))
        private int getProviderRedirect(Map<?, ?> instance) {
            return instance.size() + emextras$providers.size();
        }
    }

    @NothingNullByDefault
    @Mixin(value = ExtraMatrixEnergyContainer.class, remap = false)
    public static abstract class MixinExtraMatrixEnergyContainer implements IMixinMatrixEnergyContainer {

        @Shadow
        private FloatingLong storageCap;
        @Shadow
        private FloatingLong cachedTotal;
        @Shadow
        private FloatingLong transferCap;
        @Shadow
        @Final
        private Set<BlockPos> invalidPositions;
        @Unique
        private final Map<BlockPos, EMExtraIPTier> emextras$providers = new Object2ObjectOpenHashMap<>();
        @Unique
        private final Map<BlockPos, IEnergyContainer> emextras$cells = new Object2ObjectOpenHashMap<>();

        @Override
        public void emextras$addEMExtraCell(BlockPos pos, TileEntityEMExtraInductionCell cell) {
            MachineEnergyContainer<TileEntityEMExtraInductionCell> energyContainer = cell.getEnergyContainer();
            emextras$cells.put(pos, energyContainer);
            storageCap = storageCap.plusEqual(energyContainer.getMaxEnergy());
            cachedTotal = cachedTotal.plusEqual(energyContainer.getEnergy());
        }

        @Override
        public void emextras$addEMExtraProvider(BlockPos pos, TileEntityEMExtraInductionProvider provider) {
            emextras$providers.put(pos, provider.tier);
            transferCap = transferCap.plusEqual(provider.tier.getOutput());
        }

        @Inject(method = "removeInternal", at = @At("TAIL"))
        private void removeInternalInject(BlockPos pos, CallbackInfo ci) {
            emextras$removeInternal(pos);
        }

        @Override
        public void emextras$removeInternal(BlockPos pos) {
            if (invalidPositions.add(pos)) {
                if (emextras$providers.containsKey(pos)) {
                    //It is a provider
                    transferCap = transferCap.minusEqual(emextras$providers.get(pos).getOutput());
                } else if (emextras$cells.containsKey(pos)) {
                    //It is a cell
                    IEnergyContainer cellContainer = emextras$cells.get(pos);
                    storageCap = storageCap.plusEqual(cellContainer.getMaxEnergy());
                    cachedTotal = cachedTotal.minusEqual(cellContainer.getEnergy());
                }
            }
        }

        @Inject(method = "invalidate", at = @At("TAIL"))
        private void invalidateInject(CallbackInfo ci) {
            ImplMixinMatrixEnergyContainer.emextras$invalidate(emextras$providers, emextras$cells);
        }

        @Inject(method = "tick", at = @At("HEAD"))
        private void tickInject(CallbackInfo ci) {
            ImplMixinMatrixEnergyContainer.emextras$tick(invalidPositions, emextras$providers, emextras$cells);
        }

        @Inject(method = "addEnergy", at = @At(value = "INVOKE", target = "Lmekanism/api/math/FloatingLong;plusEqual(Lmekanism/api/math/FloatingLong;)Lmekanism/api/math/FloatingLong;"))
        private void addEnergyInject(FloatingLong energy, CallbackInfo ci) {
            ImplMixinMatrixEnergyContainer.emextras$addEnergy(energy, emextras$cells);
        }

        @Inject(method = "removeEnergy", at = @At(value = "INVOKE", target = "Lmekanism/api/math/FloatingLong;minusEqual(Lmekanism/api/math/FloatingLong;)Lmekanism/api/math/FloatingLong;", ordinal = 0))
        private void removeEnergyInject(FloatingLong energy, CallbackInfo ci) {
            ImplMixinMatrixEnergyContainer.emextras$removeEnergy(energy, emextras$cells);
        }

        @Redirect(method = "getCells", at = @At(value = "INVOKE", target = "Ljava/util/Map;size()I"))
        private int getCellsRedirect(Map<?, ?> instance) {
            return instance.size() + emextras$cells.size();
        }

        @Redirect(method = "getProviders", at = @At(value = "INVOKE", target = "Ljava/util/Map;size()I"))
        private int getProviderRedirect(Map<?, ?> instance) {
            return instance.size() + emextras$providers.size();
        }
    }
}
