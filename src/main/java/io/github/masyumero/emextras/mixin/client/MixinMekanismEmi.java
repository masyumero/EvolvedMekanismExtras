package io.github.masyumero.emextras.mixin.client;

import com.jerry.mekaf.common.block.attribute.AttributeAdvancedFactoryType;
import com.jerry.mekextras.common.tier.ExtraFactoryTier;
import com.jerry.mekextras.common.util.ExtraEnumUtils;

import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.common.integration.mekaf.registries.EMExtraAdvancedFactoryBlocks;
import io.github.masyumero.emextras.common.registry.EMExtraBlocks;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import io.github.masyumero.emextras.common.util.EMExtraBlockUtils;
import io.github.masyumero.emextras.common.util.EMExtraEnumUtils;

import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import mekanism.client.recipe_viewer.emi.MekanismEmi;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.block.attribute.AttributeFactoryType;
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
                AttributeFactoryType factoryType = Attribute.get(blockItem.getBlock(), AttributeFactoryType.class);
                if (factoryType != null) {
                    if (factoryType.getFactoryType() == EMFactoryType.ALLOYING) {
                        for (ExtraFactoryTier tier : ExtraEnumUtils.EXTRA_FACTORY_TIERS) {
                            registry.addWorkstation(category, EmiStack.of(EMExtraBlocks.getExtraFactory(tier, factoryType.getFactoryType())));
                        }
                    }
                    for (EMExtraFactoryTier tier : EMExtraEnumUtils.EMEXTRA_FACTORY_TIERS) {
                        registry.addWorkstation(category, EmiStack.of(EMExtraBlockUtils.getEMExtraFactory(tier, factoryType.getFactoryType())));
                    }
                }
                if (EMExtras.hooks.mekmm.isLoaded()) {
                    AttributeAdvancedFactoryType advancedFactoryType = Attribute.get(blockItem.getBlock(), AttributeAdvancedFactoryType.class);
                    if (advancedFactoryType != null) {
                        for (EMExtraFactoryTier tier : EMExtraEnumUtils.EMEXTRA_FACTORY_TIERS) {
                            registry.addWorkstation(category, EmiStack.of(EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactory(tier, advancedFactoryType.getAdvancedFactoryType())));
                        }
                    }
                }
            }
        }
    }
}
