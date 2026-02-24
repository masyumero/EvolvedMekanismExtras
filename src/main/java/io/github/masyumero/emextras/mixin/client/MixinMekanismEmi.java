package io.github.masyumero.emextras.mixin.client;

import com.jerry.mekaf.common.content.blocktype.AdvancedFactoryType;
import com.jerry.mekextras.common.tier.ExtraFactoryTier;
import com.jerry.mekextras.common.util.ExtraEnumUtils;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.common.integration.mekaf.registries.EMExtraAdvancedFactoryBlocks;
import io.github.masyumero.emextras.common.registry.EMExtraBlocks;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import io.github.masyumero.emextras.common.util.EMExtraBlockUtils;
import io.github.masyumero.emextras.common.util.EMExtraEnumUtils;
import mekanism.client.recipe_viewer.emi.MekanismEmi;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.block.attribute.AttributeFactoryType;
import mekanism.common.registries.MekanismBlocks;
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
                if (EMExtras.hooks.mekmm.isLoaded()){
                    for (EMExtraFactoryTier tier : EMExtraEnumUtils.EMEXTRA_FACTORY_TIERS) {
                        if (workstation == MekanismBlocks.CHEMICAL_OXIDIZER) {
                            registry.addWorkstation(category, EmiStack.of(EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactory(tier, AdvancedFactoryType.OXIDIZING)));
                        } else if (workstation == MekanismBlocks.CHEMICAL_INFUSER) {
                            registry.addWorkstation(category, EmiStack.of(EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactory(tier, AdvancedFactoryType.CHEMICAL_INFUSING)));
                        } else if (workstation == MekanismBlocks.CHEMICAL_DISSOLUTION_CHAMBER) {
                            registry.addWorkstation(category, EmiStack.of(EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactory(tier, AdvancedFactoryType.DISSOLVING)));
                        } else if (workstation == MekanismBlocks.CHEMICAL_WASHER) {
                            registry.addWorkstation(category, EmiStack.of(EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactory(tier, AdvancedFactoryType.WASHING)));
                        } else if (workstation == MekanismBlocks.CHEMICAL_CRYSTALLIZER) {
                            registry.addWorkstation(category, EmiStack.of(EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactory(tier, AdvancedFactoryType.CRYSTALLIZING)));
                        } else if (workstation == MekanismBlocks.PRESSURIZED_REACTION_CHAMBER) {
                            registry.addWorkstation(category, EmiStack.of(EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactory(tier, AdvancedFactoryType.PRESSURISED_REACTING)));
                        } else if (workstation == MekanismBlocks.ISOTOPIC_CENTRIFUGE) {
                            registry.addWorkstation(category, EmiStack.of(EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactory(tier, AdvancedFactoryType.CENTRIFUGING)));
                        } else if (workstation == MekanismBlocks.NUTRITIONAL_LIQUIFIER) {
                            registry.addWorkstation(category, EmiStack.of(EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactory(tier, AdvancedFactoryType.LIQUIFYING)));
                        }
                    }
                }
            }
        }
    }
}
