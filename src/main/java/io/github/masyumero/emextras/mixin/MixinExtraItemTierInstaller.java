package io.github.masyumero.emextras.mixin;

import com.jerry.mekanism_extras.api.tier.AdvancedTier;
import com.jerry.mekanism_extras.common.item.ExtraItemTierInstaller;

import io.github.masyumero.emextras.api.mixin.impl.ImplMixinItemTierInstallers;
import io.github.masyumero.emextras.api.tier.EMExtraTier;
import io.github.masyumero.emextras.common.util.EMExtraTierUtils;

import mekanism.common.tile.factory.TileEntityFactory;
import mekanism.common.util.WorldUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import com.llamalad7.mixinextras.sugar.Local;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ExtraItemTierInstaller.class, remap = false)
public class MixinExtraItemTierInstaller {

    @Mutable
    @Unique
    @Nullable
    private @Final EMExtraTier emextras$toTier;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void initInject(AdvancedTier fromTier, AdvancedTier toTier, Item.Properties properties, CallbackInfo ci) {
        emextras$toTier = EMExtraTierUtils.advancedToEMExtraTier(toTier);
    }

    @Inject(method = "useOn", at = @At("TAIL"), remap = true, cancellable = true)
    private void useOnInject(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir, @Local(name = "pos") BlockPos pos, @Local(name = "world") Level world) {
        if (WorldUtils.getTileEntity(world, pos) instanceof TileEntityFactory) {
            cir.setReturnValue(ImplMixinItemTierInstallers.emextras$useOn(context, emextras$toTier));
        }
    }
}
