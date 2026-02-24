package io.github.masyumero.emextras.client.recipe_viewer.jei;

import com.jerry.mekaf.common.content.blocktype.AdvancedFactoryType;

import io.github.masyumero.emextras.common.integration.mekaf.registries.EMExtraAdvancedFactoryBlocks;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import io.github.masyumero.emextras.common.util.EMExtraEnumUtils;

import mekanism.client.recipe_viewer.jei.MekanismJEI;
import mekanism.client.recipe_viewer.type.IRecipeViewerRecipeType;
import mekanism.common.registries.MekanismBlocks;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

import java.util.List;

public class EMExtraAFCatalystRegistryHelper {

    public static void register(IRecipeCatalystRegistration registry, boolean needOrdinary, IRecipeViewerRecipeType<?>... categories) {
        for (IRecipeViewerRecipeType<?> category : categories) {
            register(registry, MekanismJEI.genericRecipeType(category), category.workstations(), needOrdinary);
        }
    }

    public static void register(IRecipeCatalystRegistration registry, RecipeType<?> recipeType, List<ItemLike> workstations, boolean needOrdinary) {
        for (ItemLike workstation : workstations) {
            Item item = workstation.asItem();
            if (needOrdinary) {
                registry.addRecipeCatalyst(item, recipeType);
            }
            for (EMExtraFactoryTier tier : EMExtraEnumUtils.EMEXTRA_FACTORY_TIERS) {
                if (workstation == MekanismBlocks.CHEMICAL_OXIDIZER) {
                    registry.addRecipeCatalyst(EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactory(tier, AdvancedFactoryType.OXIDIZING), recipeType);
                } else if (workstation == MekanismBlocks.CHEMICAL_INFUSER) {
                    registry.addRecipeCatalyst(EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactory(tier, AdvancedFactoryType.CHEMICAL_INFUSING), recipeType);
                } else if (workstation == MekanismBlocks.CHEMICAL_DISSOLUTION_CHAMBER) {
                    registry.addRecipeCatalyst(EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactory(tier, AdvancedFactoryType.DISSOLVING), recipeType);
                } else if (workstation == MekanismBlocks.CHEMICAL_WASHER) {
                    registry.addRecipeCatalyst(EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactory(tier, AdvancedFactoryType.WASHING), recipeType);
                } else if (workstation == MekanismBlocks.CHEMICAL_CRYSTALLIZER) {
                    registry.addRecipeCatalyst(EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactory(tier, AdvancedFactoryType.CRYSTALLIZING), recipeType);
                } else if (workstation == MekanismBlocks.PRESSURIZED_REACTION_CHAMBER) {
                    registry.addRecipeCatalyst(EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactory(tier, AdvancedFactoryType.PRESSURISED_REACTING), recipeType);
                } else if (workstation == MekanismBlocks.ISOTOPIC_CENTRIFUGE) {
                    registry.addRecipeCatalyst(EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactory(tier, AdvancedFactoryType.CENTRIFUGING), recipeType);
                } else if (workstation == MekanismBlocks.NUTRITIONAL_LIQUIFIER) {
                    registry.addRecipeCatalyst(EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactory(tier, AdvancedFactoryType.LIQUIFYING), recipeType);
                }
            }
        }
    }
}
