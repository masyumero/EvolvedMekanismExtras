package io.github.masyumero.emextras.mixin.client;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import io.github.masyumero.emextras.api.tier.EMExtraTier;
import io.github.masyumero.emextras.common.block.attribute.EMExtraAttribute;
import io.github.masyumero.emextras.common.block.transmitter.*;
import io.github.masyumero.emextras.common.util.EMExtraColorUtils;
import io.github.masyumero.emextras.common.util.EMExtraTransporterUtils;
import irislgtm.mto.MTOModClient;
import mekanism.api.tier.BaseTier;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = MTOModClient.class, remap = false)
public class MixinMTOModClient {

    @Definition(id = "tier", local = @Local(type = BaseTier.class, name = "tier"))
    @Expression("tier != null")
    @ModifyExpressionValue(method = "onBlockHighlight", at = @At(value = "MIXINEXTRAS:EXPRESSION"))
    private boolean onBlockHighlightModifyExpressionValue(boolean original, @Local(name = "state") BlockState state, @Share("emExtraTier") LocalRef<EMExtraTier> emExtraTierRef) {
        EMExtraTier emExtraTier = EMExtraAttribute.getEMExtraTier(state.getBlock());
        emExtraTierRef.set(emExtraTier);
        return original || emExtraTier != null;
    }

    @Definition(id = "getRgbCode", method = "Lmekanism/api/tier/BaseTier;getRgbCode()[I")
    @Definition(id = "tier", local = @Local(type = BaseTier.class, name = "tier"))
    @Expression("tier.getRgbCode()")
    @WrapOperation(method = "onBlockHighlight", at = @At(value = "MIXINEXTRAS:EXPRESSION"))
    private int[] onBlockHighlightWrapOperation(BaseTier instance, Operation<int[]> original, @Local(name = "state") BlockState state, @Local(name = "tier") BaseTier tier, @Share("emExtraTier") LocalRef<EMExtraTier> emExtraTierRef) {
        EMExtraTier emExtraTier = emExtraTierRef.get();
        if (emExtraTier != null) {
            return EMExtraColorUtils.getRGBColor(emExtraTier.getRgbSupplier().getAsInt());
        }
        if (tier != null) {
            if (state.getBlock().getClass().getPackageName().equals("io.github.masyumero.emextras.common.block.transmitter")) {
                return EMExtraColorUtils.getRGBColor(EMExtraTransporterUtils.baseToEMExtraTier(tier).getRgbSupplier().getAsInt());
            }
        }
        return original.call(instance);
    }
}
