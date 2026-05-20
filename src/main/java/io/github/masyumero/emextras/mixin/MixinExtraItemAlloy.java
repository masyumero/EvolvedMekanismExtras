package io.github.masyumero.emextras.mixin;

import com.jerry.mekanism_extras.api.tier.ExtraAlloyTier;
import com.jerry.mekanism_extras.common.item.ExtraItemAlloy;
import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;import com.llamalad7.mixinextras.sugar.Local;
import io.github.masyumero.emextras.api.IEMExtraAlloyInteraction;
import io.github.masyumero.emextras.common.capabilities.EMExtraCapabilities;
import mekanism.common.item.ItemAlloy;
import mekanism.common.util.CapabilityUtils;
import mekanism.common.util.MekanismUtils;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.util.LazyOptional;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ExtraItemAlloy.class, remap = false)
public abstract class MixinExtraItemAlloy extends Item {

    @Shadow
    @Final
    private ExtraAlloyTier tier;

    public MixinExtraItemAlloy(Properties properties) {
        super(properties);
    }

    @Definition(id = "capability", local = @Local(type = LazyOptional.class, name = "capability"))
    @Definition(id = "isPresent", method = "Lnet/minecraftforge/common/util/LazyOptional;isPresent()Z")
    @Expression("capability.isPresent()")
    @Inject(method = "useOn", at = @At(value = "MIXINEXTRAS:EXPRESSION"), cancellable = true)
    private void useOnInject(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir, @Local(name = "tile") BlockEntity tile, @Local(name = "player") Player player, @Local(name = "world") Level world) {
        if (player.getOffhandItem().getItem() instanceof ItemAlloy alloy && alloy.getTier().ordinal() > 2) {
            LazyOptional<IEMExtraAlloyInteraction> capability = CapabilityUtils.getCapability(tile, EMExtraCapabilities.EMEXTRA_ALLOY_INTERACTION, context.getClickedFace());
            if (capability.isPresent()) {
                if (!world.isClientSide()) {
                    capability.orElseThrow(MekanismUtils.MISSING_CAP_ERROR).onEMExtraAlloyInteraction(player, context.getItemInHand(), tier, alloy.getTier());
                }
                cir.setReturnValue(InteractionResult.sidedSuccess(world.isClientSide));
            }
        }
    }
}
