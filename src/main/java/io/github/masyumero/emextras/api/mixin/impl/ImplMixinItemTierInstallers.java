package io.github.masyumero.emextras.api.mixin.impl;

import io.github.masyumero.emextras.api.tier.EMExtraTier;
import io.github.masyumero.emextras.common.block.attribute.EMExtraAttributeUpgradeable;
import io.github.masyumero.emextras.common.item.EMExtraTieredItem;
import io.github.masyumero.emextras.common.util.EMExtraTierUtils;

import mekanism.common.Mekanism;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.tile.base.TileEntityMekanism;
import mekanism.common.tile.interfaces.ITierUpgradable;
import mekanism.common.tile.interfaces.ITileDirectional;
import mekanism.common.upgrade.IUpgradeData;
import mekanism.common.util.WorldUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ImplMixinItemTierInstallers {

    public static InteractionResult emextras$useOn(UseOnContext context, EMExtraTier toTier) {
        Player player = context.getPlayer();
        Level world = context.getLevel();
        if (world.isClientSide || player == null) {
            return InteractionResult.PASS;
        }
        ItemStack offhandItemStack = player.getOffhandItem();
        if (!(offhandItemStack.getItem() instanceof EMExtraTieredItem item) || toTier != item.getTier()) {
            return InteractionResult.FAIL;
        }
        BlockPos pos = context.getClickedPos();
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        EMExtraAttributeUpgradeable upgradeableBlock = Attribute.get(block, EMExtraAttributeUpgradeable.class);
        if (upgradeableBlock != null) {
            EMExtraTier baseTier = EMExtraTierUtils.toEMExtraTier(EMExtraTierUtils.getTier(block));
            if (baseTier == null) {
                return InteractionResult.FAIL;
            }
            if (baseTier == toTier) {
                BlockState upgradeState = upgradeableBlock.upgradeResult(state, toTier);
                if (state == upgradeState) {
                    return InteractionResult.PASS;
                }
                BlockEntity tile = WorldUtils.getTileEntity(world, pos);
                if (tile instanceof ITierUpgradable tierUpgradable) {
                    if (tile instanceof TileEntityMekanism tileMek && !tileMek.playersUsing.isEmpty()) {
                        return InteractionResult.FAIL;
                    }
                    IUpgradeData upgradeData = tierUpgradable.getUpgradeData();
                    if (upgradeData == null) {
                        if (tierUpgradable.canBeUpgraded()) {
                            Mekanism.logger.warn("Got no upgrade data for block {} at position: {} in {} but it said it would be able to provide some.", block, pos, world);
                            return InteractionResult.FAIL;
                        }
                    } else {
                        world.setBlockAndUpdate(pos, upgradeState);
                        // TODO: Make it so it doesn't have to be a TileEntityMekanism?
                        TileEntityMekanism upgradedTile = WorldUtils.getTileEntity(TileEntityMekanism.class, world, pos);
                        if (upgradedTile == null) {
                            Mekanism.logger.warn("Error upgrading block at position: {} in {}.", pos, world);
                            return InteractionResult.FAIL;
                        } else {
                            if (tile instanceof ITileDirectional directional && directional.isDirectional()) {
                                upgradedTile.setFacing(directional.getDirection());
                            }
                            upgradedTile.parseUpgradeData(upgradeData);
                            upgradedTile.sendUpdatePacket();
                            upgradedTile.setChanged();
                            if (!player.isCreative()) {
                                context.getItemInHand().shrink(1);
                                offhandItemStack.shrink(2);
                            }
                            return InteractionResult.sidedSuccess(world.isClientSide);
                        }
                    }
                }
            }
        }
        return InteractionResult.PASS;
    }
}
