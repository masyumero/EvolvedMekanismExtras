package io.github.masyumero.emextras.mixin;

import com.jerry.mekanism_extras.common.item.ItemAlloyRadiance;
import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import fr.iglee42.evolvedmekanism.tiers.EMBaseTier;
import io.github.masyumero.emextras.common.content.network.transmitter.IEMExtraUpgradeableTransmitter;
import io.github.masyumero.emextras.common.registry.EMExtraBlocks;
import mekanism.api.providers.IBlockProvider;
import mekanism.api.tier.BaseTier;
import mekanism.common.Mekanism;
import mekanism.common.content.network.transmitter.IUpgradeableTransmitter;
import mekanism.common.content.network.transmitter.Transmitter;
import mekanism.common.item.ItemAlloy;
import mekanism.common.tile.transmitter.*;
import mekanism.common.upgrade.transmitter.TransmitterUpgradeData;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ItemAlloyRadiance.class, remap = false)
public abstract class MixinItemAlloyRadiance {

    @Unique
    private static boolean emextras$isEMExtra;

    @Unique
    private static boolean emextras$isEvolved;

    @Unique
    private static BaseTier emextras$beforeTier;

    // Mek to EMExtra
    @Definition(id = "world", local = @Local(type = Level.class, name = "world"))
    @Definition(id = "isClientSide", field = "Lnet/minecraft/world/level/Level;isClientSide:Z")
    @Expression("world.isClientSide")
    @Inject(method = "useOn", at = @At(value = "MIXINEXTRAS:EXPRESSION"), remap = true)
    private void useOnInject(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir, @Local(name = "player") Player player) {
        emextras$isEMExtra = player.getOffhandItem().getItem() instanceof ItemAlloy alloy && alloy.getTier().ordinal() > 2;
        emextras$beforeTier = null;
    }

    @Inject(method = "onExtraAlloyInteraction", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;shrink(I)V"), remap = true)
    private void shrinkOffHandItemInject(Player player, ItemStack stack, Level level, BlockPos worldPosition, TileEntityTransmitter tileEntityTransmitter, CallbackInfo ci) {
        if (emextras$isEMExtra) {
            player.getOffhandItem().shrink(1);
        }
    }

    // Evolved to EMExtra
    @Definition(id = "upgradeableTransmitter", local = @Local(type = IUpgradeableTransmitter.class, name = "upgradeableTransmitter"))
    @Definition(id = "getTier", method = "Lmekanism/common/content/network/transmitter/IUpgradeableTransmitter;getTier()Lmekanism/api/tier/ITier;")
    @Definition(id = "getBaseTier", method = "Lmekanism/api/tier/ITier;getBaseTier()Lmekanism/api/tier/BaseTier;")
    @Expression("upgradeableTransmitter.getTier().getBaseTier() == ?")
    @ModifyExpressionValue(method = "onExtraAlloyInteraction", at = @At("MIXINEXTRAS:EXPRESSION"))
    private boolean checkIsUpgradeableModifyExpressionValue(boolean original, @Local(name = "upgradeableTransmitter") IUpgradeableTransmitter<?> upgradeableTransmitter) {
        BaseTier transmitterBaseTier = upgradeableTransmitter.getTier().getBaseTier();
        emextras$isEvolved = transmitterBaseTier  == EMBaseTier.OVERCLOCKED;
        if (emextras$isEvolved) {
            emextras$isEMExtra = false;
        }
        if (emextras$beforeTier != null) {
            if (emextras$beforeTier == transmitterBaseTier) {
                emextras$beforeTier = transmitterBaseTier;
                return original || emextras$isEvolved;
            } else {
                return false;
            }
        }
        emextras$beforeTier = transmitterBaseTier;
        return original || emextras$isEvolved;
    }

    // Both
    @Definition(id = "upgradedTransmitter", local = @Local(type = Transmitter.class, name = "upgradedTransmitter"))
    @Definition(id = "IUpgradeableTransmitter", type = IUpgradeableTransmitter.class)
    @Expression("upgradedTransmitter instanceof IUpgradeableTransmitter")
    @ModifyExpressionValue(method = "onExtraAlloyInteraction", at = @At("MIXINEXTRAS:EXPRESSION"))
    private boolean onExtraAlloyInteractionModifyExpressionValue(boolean original) {
        return original && !emextras$isEMExtra && !emextras$isEvolved;
    }

    @Definition(id = "upgradedTransmitter", local = @Local(type = Transmitter.class, name = "upgradedTransmitter"))
    @Definition(id = "IUpgradeableTransmitter", type = IUpgradeableTransmitter.class)
    @Expression("upgradedTransmitter instanceof IUpgradeableTransmitter")
    @Inject(method = "onExtraAlloyInteraction", at = @At(value = "MIXINEXTRAS:EXPRESSION"))
    private void onExtraAlloyInteractionInject(Player player, ItemStack stack, Level level, BlockPos worldPosition, TileEntityTransmitter tileEntityTransmitter, CallbackInfo ci, @Local(name = "upgradedTransmitter") Transmitter<?, ?, ?> upgradedTransmitter, @Local(name = "upgradeData") TransmitterUpgradeData upgradeData) {
        if (emextras$isEMExtra || emextras$isEvolved) {
            if (upgradedTransmitter instanceof IUpgradeableTransmitter) {
                emextras$transferUpgradeData((IEMExtraUpgradeableTransmitter<?>) upgradedTransmitter, upgradeData);
            } else {
                Mekanism.logger.warn("Unhandled upgrade data.", new IllegalStateException());
            }
        }
    }

    @Definition(id = "logger", field = "Lmekanism/common/Mekanism;logger:Lorg/slf4j/Logger;")
    @Definition(id = "warn", method = "Lorg/slf4j/Logger;warn(Ljava/lang/String;Ljava/lang/Throwable;)V")
    @Definition(id = "IllegalStateException", type = IllegalStateException.class)
    @Expression("logger.warn('Unhandled upgrade data.', new IllegalStateException())")
    @Redirect(method = "onExtraAlloyInteraction", at = @At("MIXINEXTRAS:EXPRESSION"))
    private void loggerRedirect(Logger instance, String s, Throwable throwable) {
        if (!emextras$isEMExtra && !emextras$isEvolved) {
            instance.warn(s, throwable);
        }
    }

    @Inject(method = "getiBlockProvider", at = @At("HEAD"), cancellable = true)
    private static void getiBlockProviderInject(TileEntityTransmitter transmitterTile, CallbackInfoReturnable<IBlockProvider> cir) {
        if (emextras$isEMExtra || emextras$isEvolved) {
            if (transmitterTile instanceof TileEntityPressurizedTube) {
                cir.setReturnValue(EMExtraBlocks.ABSOLUTE_OVERCLOCKED_PRESSURIZED_TUBE);
            } else if (transmitterTile instanceof TileEntityUniversalCable) {
                cir.setReturnValue(EMExtraBlocks.ABSOLUTE_OVERCLOCKED_UNIVERSAL_CABLE);
            } else if (transmitterTile instanceof TileEntityMechanicalPipe) {
                cir.setReturnValue(EMExtraBlocks.ABSOLUTE_OVERCLOCKED_MECHANICAL_PIPE);
            } else if (transmitterTile instanceof TileEntityThermodynamicConductor) {
                cir.setReturnValue(EMExtraBlocks.ABSOLUTE_OVERCLOCKED_THERMODYNAMIC_CONDUCTOR);
            } else {
                cir.setReturnValue(transmitterTile instanceof TileEntityLogisticalTransporter ? EMExtraBlocks.ABSOLUTE_OVERCLOCKED_LOGISTICAL_TRANSPORTER : null);
            }
        }
    }

    @Unique
    @SuppressWarnings("unchecked")
    private <DATA extends TransmitterUpgradeData> void emextras$transferUpgradeData(IEMExtraUpgradeableTransmitter<DATA> upgradeableTransmitter, TransmitterUpgradeData data) {
        if (upgradeableTransmitter.dataTypeMatches(data)) {
            upgradeableTransmitter.parseUpgradeData((DATA) data);
        } else {
            Mekanism.logger.warn("Unhandled upgrade data.", new IllegalStateException());
        }
    }
}
