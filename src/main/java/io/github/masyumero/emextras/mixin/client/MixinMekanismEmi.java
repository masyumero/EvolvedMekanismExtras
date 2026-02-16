package io.github.masyumero.emextras.mixin.client;

import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import io.github.masyumero.emextras.common.block.attribute.EMExtraAttributeFactoryType;
import io.github.masyumero.emextras.common.registry.EMExtraBlocks;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import io.github.masyumero.emextras.common.util.EMExtraEnumUtils;
import mekanism.client.recipe_viewer.emi.MekanismEmi;
import mekanism.common.block.attribute.Attribute;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(value = MekanismEmi.class, remap = false)
public abstract class MixinMekanismEmi {

    @Inject(method = "addWorkstations", at = @At(value = "TAIL"))
    private static void mixinAddWorkstations(EmiRegistry registry, EmiRecipeCategory category, List<ItemLike> workstations, CallbackInfo ci) {
        for (ItemLike workstation : workstations) {
            Item item = workstation.asItem();
            if (item instanceof BlockItem blockItem) {
                EMExtraAttributeFactoryType factoryType = Attribute.get(blockItem.getBlock(), EMExtraAttributeFactoryType.class);
                if (factoryType != null) {
                    for (EMExtraFactoryTier tier : EMExtraEnumUtils.EMEXTRA_FACTORY_TIERS) {
                        registry.addWorkstation(category, EmiStack.of(EMExtraBlocks.getEMExtraFactory(tier, factoryType.getFactoryType())));
                    }
                }
            }
        }
    }
}
