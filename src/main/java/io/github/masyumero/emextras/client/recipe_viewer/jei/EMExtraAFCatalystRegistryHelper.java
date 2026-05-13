package io.github.masyumero.emextras.client.recipe_viewer.jei;

import io.github.masyumero.emextras.common.integration.mekaf.registries.EMExtraAdvancedFactoryBlocks;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import io.github.masyumero.emextras.common.util.EMExtraEnumUtils;

import mekanism.client.recipe_viewer.jei.MekanismJEI;
import mekanism.client.recipe_viewer.type.IRecipeViewerRecipeType;
import mekanism.common.block.attribute.Attribute;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

import com.jerry.mekaf.common.block.attribute.AttributeAdvancedFactoryType;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;

import java.util.List;

public class EMExtraAFCatalystRegistryHelper {

    public static void register(IRecipeCatalystRegistration registry, IRecipeViewerRecipeType<?>... categories) {
        for (IRecipeViewerRecipeType<?> category : categories) {
            register(registry, MekanismJEI.genericRecipeType(category), category.workstations());
        }
    }

    public static void register(IRecipeCatalystRegistration registry, RecipeType<?> recipeType, List<ItemLike> workstations) {
        for (ItemLike workstation : workstations) {
            Item item = workstation.asItem();
            if (item instanceof BlockItem blockItem) {
                AttributeAdvancedFactoryType factoryType = Attribute.get(blockItem.getBlock(), AttributeAdvancedFactoryType.class);
                if (factoryType != null) {
                    for (EMExtraFactoryTier tier : EMExtraEnumUtils.EMEXTRA_FACTORY_TIERS) {
                        registry.addRecipeCatalyst(EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactory(tier, factoryType.getAdvancedFactoryType()), recipeType);
                    }
                }
            }
        }
    }
}
