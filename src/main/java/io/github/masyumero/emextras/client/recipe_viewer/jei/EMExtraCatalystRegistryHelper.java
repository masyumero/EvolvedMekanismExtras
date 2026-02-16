package io.github.masyumero.emextras.client.recipe_viewer.jei;

import io.github.masyumero.emextras.common.block.attribute.EMExtraAttributeFactoryType;
import io.github.masyumero.emextras.common.registry.EMExtraBlocks;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import io.github.masyumero.emextras.common.util.EMExtraEnumUtils;
import mekanism.client.recipe_viewer.jei.MekanismJEI;
import mekanism.client.recipe_viewer.type.IRecipeViewerRecipeType;
import mekanism.common.block.attribute.Attribute;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

import java.util.List;

public class EMExtraCatalystRegistryHelper {

    public static void register(IRecipeCatalystRegistration registry, IRecipeViewerRecipeType<?>... categories) {
        for (IRecipeViewerRecipeType<?> category : categories) {
            register(registry, MekanismJEI.genericRecipeType(category), category.workstations());
        }
    }

    public static void register(IRecipeCatalystRegistration registry, RecipeType<?> recipeType, List<ItemLike> workstations) {
        for (ItemLike workstation : workstations) {
            Item item = workstation.asItem();
            registry.addRecipeCatalyst(item, recipeType);
            if (item instanceof BlockItem blockItem) {
                EMExtraAttributeFactoryType factoryType = Attribute.get(blockItem.getBlock(), EMExtraAttributeFactoryType.class);
                if (factoryType != null) {
                    for (EMExtraFactoryTier tier : EMExtraEnumUtils.EMEXTRA_FACTORY_TIERS) {
                        registry.addRecipeCatalyst(EMExtraBlocks.getEMExtraFactory(tier, factoryType.getFactoryType()), recipeType);
                    }
                }
            }
        }
    }
}
