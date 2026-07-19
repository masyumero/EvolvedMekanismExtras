package io.github.masyumero.emextras.mixin;

import com.jerry.mekanism_extras.common.item.ItemAlloyRadiance;
import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import io.github.masyumero.emextras.common.content.network.transmitter.IEMExtraUpgradeableTransmitter;
import io.github.masyumero.emextras.common.registry.EMExtraBlocks;
import mekanism.api.providers.IBlockProvider;
import mekanism.common.Mekanism;
import mekanism.common.content.network.transmitter.IUpgradeableTransmitter;
import mekanism.common.content.network.transmitter.Transmitter;
import mekanism.common.item.ItemAlloy;
import mekanism.common.tile.transmitter.*;
import mekanism.common.upgrade.transmitter.TransmitterUpgradeData;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
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
public abstract class MixinItemAlloyRadiance extends Item {

    @Unique
    private static boolean emextras$isEMExtra;

    public MixinItemAlloyRadiance(Properties properties) {
        super(properties);
    }

    @Definition(id = "world", local = @Local(type = Level.class, name = "world"))
    @Definition(id = "isClientSide", field = "Lnet/minecraft/world/level/Level;isClientSide:Z")
    @Expression("world.isClientSide")
    @Inject(method = "useOn", at = @At(value = "MIXINEXTRAS:EXPRESSION"), remap = true)
    private void useOnInject(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir, @Local(name = "player") Player player) {
        emextras$isEMExtra = player.getOffhandItem().getItem() instanceof ItemAlloy alloy && alloy.getTier().ordinal() > 2;
    }

    @Definition(id = "upgradedTransmitter", local = @Local(type = Transmitter.class, name = "upgradedTransmitter"))
    @Definition(id = "IUpgradeableTransmitter", type = IUpgradeableTransmitter.class)
    @Expression("upgradedTransmitter instanceof IUpgradeableTransmitter")
    @ModifyExpressionValue(method = "onExtraAlloyInteraction", at = @At("MIXINEXTRAS:EXPRESSION"))
    private boolean onExtraAlloyInteractionModifyExpressionValue(boolean original) {
        return original && !emextras$isEMExtra;
    }

    @Definition(id = "upgradedTransmitter", local = @Local(type = Transmitter.class, name = "upgradedTransmitter"))
    @Definition(id = "IUpgradeableTransmitter", type = IUpgradeableTransmitter.class)
    @Expression("upgradedTransmitter instanceof IUpgradeableTransmitter")
    @Inject(method = "onExtraAlloyInteraction", at = @At(value = "MIXINEXTRAS:EXPRESSION"))
    private void onExtraAlloyInteractionInject(Player player, ItemStack stack, Level level, BlockPos worldPosition, TileEntityTransmitter tileEntityTransmitter, CallbackInfo ci, @Local(name = "upgradedTransmitter") Transmitter<?, ?, ?> upgradedTransmitter, @Local(name = "upgradeData") TransmitterUpgradeData upgradeData) {
        if (emextras$isEMExtra) {
            if (upgradedTransmitter instanceof IUpgradeableTransmitter) {
                emextras$transferUpgradeData((IEMExtraUpgradeableTransmitter<?>) upgradedTransmitter, upgradeData);
            } else {
                Mekanism.logger.warn("Unhandled upgrade data.", new IllegalStateException());
            }
        }
    }

    @Inject(method = "onExtraAlloyInteraction", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;shrink(I)V"))
    private void shirinkOffHandItemInject(Player player, ItemStack stack, Level level, BlockPos worldPosition, TileEntityTransmitter tileEntityTransmitter, CallbackInfo ci) {
        if (emextras$isEMExtra) {
            player.getOffhandItem().shrink(1);
        }
    }

    @Definition(id = "logger", field = "Lmekanism/common/Mekanism;logger:Lorg/slf4j/Logger;")
    @Definition(id = "warn", method = "Lorg/slf4j/Logger;warn(Ljava/lang/String;Ljava/lang/Throwable;)V")
    @Definition(id = "IllegalStateException", type = IllegalStateException.class)
    @Expression("logger.warn('Unhandled upgrade data.', new IllegalStateException())")
    @Redirect(method = "onExtraAlloyInteraction", at = @At("MIXINEXTRAS:EXPRESSION"))
    private void loggerRedirect(Logger instance, String s, Throwable throwable) {
        if (!emextras$isEMExtra) {
            instance.warn(s, throwable);
        }
    }

    @Inject(method = "getiBlockProvider", at = @At("HEAD"), cancellable = true)
    private static void getiBlockProviderInject(TileEntityTransmitter transmitterTile, CallbackInfoReturnable<IBlockProvider> cir) {
        if (emextras$isEMExtra) {
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
