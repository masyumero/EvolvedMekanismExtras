package io.github.masyumero.emextras.mixin;

import com.jerry.mekanism_extras.api.tier.AdvancedTier;
import com.jerry.mekanism_extras.common.block.attribute.ExtraAttribute;
import com.jerry.mekanism_extras.common.block.attribute.ExtraAttributeUpgradeable;
import com.jerry.mekanism_extras.common.item.ExtraItemTierInstaller;
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
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = ExtraItemTierInstaller.class, remap = false)
public class MixinExtraItemTierInstaller {

    @Final
    @Shadow
    private AdvancedTier fromTier;
    @Final
    @Shadow
    private AdvancedTier toTier;

    /**
     * @author masyumero
     * @reason Without this, it somehow won't work.
     */
    @Overwrite(remap = true)
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        Level world = context.getLevel();
        if (world.isClientSide || player == null) {
            return InteractionResult.PASS;
        }
        BlockPos pos = context.getClickedPos();
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        ExtraAttributeUpgradeable upgradeableBlock = Attribute.get(block, ExtraAttributeUpgradeable.class);
        if (upgradeableBlock != null) {
            AdvancedTier baseTier = ExtraAttribute.getAdvanceTier(block);
            if (baseTier == fromTier && baseTier != toTier) {
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
