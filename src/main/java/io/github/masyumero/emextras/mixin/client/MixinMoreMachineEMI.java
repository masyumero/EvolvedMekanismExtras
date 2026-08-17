package io.github.masyumero.emextras.mixin.client;

import com.jerry.mekmm.client.recipe_viewer.emi.MoreMachineEMI;
import com.jerry.mekmm.common.block.attribute.MoreMachineAttributeFactoryType;
import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.sugar.Local;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import io.github.masyumero.emextras.common.integration.mekmm.EMExtraMoreMachineFactoryTypes;
import io.github.masyumero.emextras.common.integration.mekmm.registries.EMExtraMoreMachineBlocks;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import io.github.masyumero.emextras.common.util.EMExtraEnumUtils;
import net.minecraft.world.level.ItemLike;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Debug(export = true)
@Mixin(value = MoreMachineEMI.class, remap = false)
public class MixinMoreMachineEMI {

    @Definition(id = "factoryType", local = @Local(type = MoreMachineAttributeFactoryType.class))
    @Expression("factoryType != null")
    @Inject(method = "addWorkstations", at = @At(value = "MIXINEXTRAS:EXPRESSION", shift = At.Shift.AFTER))
    private static void addWorkstationsInject(EmiRegistry registry, EmiRecipeCategory category, List<ItemLike> workstations, CallbackInfo ci, @Local(name = "factoryType") MoreMachineAttributeFactoryType factoryType) {
        if (EMExtraMoreMachineFactoryTypes.isSupported(factoryType.getMoreMachineFactoryType())) {
            for (EMExtraFactoryTier tier : EMExtraEnumUtils.EMEXTRA_FACTORY_TIERS) {
                registry.addWorkstation(category, EmiStack.of(EMExtraMoreMachineBlocks.getEMExtraMoreMachineFactory(tier, factoryType.getMoreMachineFactoryType())));
            }
        }
    }
}
