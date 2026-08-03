package io.github.masyumero.emextras.mixin;

import com.jerry.mekanism_extras.api.tier.ExtraAlloyTier;
import com.jerry.mekanism_extras.common.content.network.transmitter.IExtraUpgradeableTransmitter;
import com.jerry.mekanism_extras.common.tile.transmitter.ExtraTileEntityTransmitter;
import io.github.masyumero.emextras.api.IEMExtraAlloyInteraction;
import io.github.masyumero.emextras.api.mixin.impl.ImplMixinTileEntityTransmitters;
import io.github.masyumero.emextras.common.content.network.transmitter.IEMExtraUpgradeableTransmitter;
import io.github.masyumero.emextras.common.tile.transmitter.TileEntityEMExtraTransmitter;
import io.github.masyumero.emextras.common.util.EMExtraTierUtils;
import mekanism.api.providers.IBlockProvider;
import mekanism.api.tier.AlloyTier;
import mekanism.common.Mekanism;
import mekanism.common.advancements.MekanismCriteriaTriggers;
import mekanism.common.content.network.transmitter.BufferedTransmitter;
import mekanism.common.content.network.transmitter.Transmitter;
import mekanism.common.lib.transmitter.DynamicBufferedNetwork;
import mekanism.common.lib.transmitter.DynamicNetwork;
import mekanism.common.tile.transmitter.TileEntityTransmitter;
import mekanism.common.upgrade.transmitter.TransmitterUpgradeData;
import mekanism.common.util.WorldUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.ArrayList;
import java.util.List;

@Mixin(value = ExtraTileEntityTransmitter.class, remap = false)
public abstract class MixinExtraTileEntityTransmitter extends TileEntityTransmitter implements IEMExtraAlloyInteraction {

    public MixinExtraTileEntityTransmitter(IBlockProvider blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state);
    }

    @Override
    public void onEMExtraAlloyInteraction(Player player, ItemStack stack, @Nullable ExtraAlloyTier extraTier, @Nullable AlloyTier evolvedTier) {
        if (evolvedTier == null) {
            return;
        }

        if (getLevel() != null && getTransmitter().hasTransmitterNetwork()) {
            DynamicNetwork<?, ?, ?> transmitterNetwork = getTransmitter().getTransmitterNetwork();
            List<Transmitter<?, ?, ?>> list = new ArrayList<>(transmitterNetwork.getTransmitters());
            list.sort((o1, o2) -> {
                if (o1 != null && o2 != null) {
                    return Double.compare(o1.getTilePos().distSqr(worldPosition), o2.getTilePos().distSqr(worldPosition));
                }
                return 0;
            });
            boolean sharesSet = false;
            int upgraded = 0;
            for (Transmitter<?, ?, ?> transmitter : list) {
                if (transmitter instanceof IExtraUpgradeableTransmitter<?> upgradeableTransmitter && ImplMixinTileEntityTransmitters.emextras$canUpgrade(upgradeableTransmitter, evolvedTier)) {
                    TileEntityTransmitter transmitterTile = transmitter.getTransmitterTile();
                    BlockState state = transmitterTile.getBlockState();
                    BlockState upgradeState = ImplMixinTileEntityTransmitters.emextras$upgradeResult(state, EMExtraTierUtils.evolvedToEMExtraTier(evolvedTier.getBaseTier()), getTransmitterType());
                    if (state == upgradeState) {
                        // Skip if it would not actually upgrade anything
                        continue;
                    }
                    if (!sharesSet) {
                        if (transmitterNetwork instanceof DynamicBufferedNetwork dynamicNetwork) {
                            // Ensure we save the shares to the tiles so that they can properly take them, and they
                            // don't get voided
                            dynamicNetwork.validateSaveShares((BufferedTransmitter<?, ?, ?, ?>) transmitter);
                        }
                        sharesSet = true;
                    }
                    transmitter.startUpgrading();
                    TransmitterUpgradeData upgradeData = upgradeableTransmitter.getUpgradeData();
                    BlockPos transmitterPos = transmitter.getTilePos();
                    Level transmitterWorld = transmitter.getTileWorld();
                    if (upgradeData == null) {
                        Mekanism.logger.warn("Got no upgrade data for transmitter at position: {} in {} but it said it would be able to provide some.",
                                transmitterPos, transmitterWorld);
                    } else {
                        transmitterWorld.setBlockAndUpdate(transmitterPos, upgradeState);
                        TileEntityEMExtraTransmitter upgradedTile = WorldUtils.getTileEntity(TileEntityEMExtraTransmitter.class, transmitterWorld, transmitterPos);
                        if (upgradedTile == null) {
                            Mekanism.logger.warn("Error upgrading transmitter at position: {} in {}.", transmitterPos, transmitterWorld);
                        } else {
                            Transmitter<?, ?, ?> upgradedTransmitter = upgradedTile.getTransmitter();
                            if (upgradedTransmitter instanceof IEMExtraUpgradeableTransmitter) {
                                emextras$transferUpgradeData((IEMExtraUpgradeableTransmitter<?>) upgradedTransmitter, upgradeData);
                            } else {
                                Mekanism.logger.warn("Unhandled upgrade data.", new IllegalStateException());
                            }
                            upgraded++;
                            if (upgraded == 8) {
                                break;
                            }
                        }
                    }
                }
            }
            if (upgraded > 0) {
                // Invalidate the network so that it properly has new references to everything
                transmitterNetwork.invalidate(null);
                if (!player.isCreative()) {
                    stack.shrink(1);
                }
                if (player instanceof ServerPlayer serverPlayer) {
                    MekanismCriteriaTriggers.ALLOY_UPGRADE.trigger(serverPlayer);
                }
            }
        }
    }

    @Unique
    private <DATA extends TransmitterUpgradeData> void emextras$transferUpgradeData(IEMExtraUpgradeableTransmitter<DATA> upgradeableTransmitter, TransmitterUpgradeData data) {
        if (upgradeableTransmitter.dataTypeMatches(data)) {
            upgradeableTransmitter.parseUpgradeData((DATA) data);
        } else {
            Mekanism.logger.warn("Unhandled upgrade data.", new IllegalStateException());
        }
    }
}
