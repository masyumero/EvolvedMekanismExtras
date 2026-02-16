package io.github.masyumero.emextras.datagen.common.recipe.impl;

import com.jerry.mekextras.common.registries.ExtraItems;
import fr.iglee42.evolvedmekanism.registries.EMItems;
import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.api.datagen.recipe.builder.AlloyerRecipeBuilder;
import io.github.masyumero.emextras.common.registry.EMExtraItems;
import io.github.masyumero.emextras.datagen.common.recipe.ISubRecipeProvider;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;

public class AlloyingRecipeProvider implements ISubRecipeProvider {

    @Override
    public void addRecipes(RecipeOutput consumer, HolderLookup.Provider registries) {
        String basePath = "alloying/";
        AlloyerRecipeBuilder.alloying(
                IngredientCreatorAccess.item().from(EMExtraItems.ABSOLUTE_OVERCLOCKED_CONTROL_CIRCUIT, 2),
                IngredientCreatorAccess.item().from(ExtraItems.ABSOLUTE_TIER_INSTALLER),
                IngredientCreatorAccess.item().from(EMItems.OVERCLOCKED_TIER_INSTALLER),
                EMExtraItems.ABSOLUTE_OVERCLOCKED_TIER_INSTALLER.asStack()).build(consumer, EMExtras.rl(basePath + "tier_installer/absolute_overclocked"));
        AlloyerRecipeBuilder.alloying(
                IngredientCreatorAccess.item().from(EMExtraItems.SUPREME_QUANTUM_CONTROL_CIRCUIT, 2),
                IngredientCreatorAccess.item().from(ExtraItems.SUPREME_TIER_INSTALLER),
                IngredientCreatorAccess.item().from(EMItems.QUANTUM_TIER_INSTALLER),
                EMExtraItems.SUPREME_QUANTUM_TIER_INSTALLER.asStack()).build(consumer, EMExtras.rl(basePath + "tier_installer/supreme_quantum"));
        AlloyerRecipeBuilder.alloying(
                IngredientCreatorAccess.item().from(EMExtraItems.COSMIC_DENSE_CONTROL_CIRCUIT, 2),
                IngredientCreatorAccess.item().from(ExtraItems.COSMIC_TIER_INSTALLER),
                IngredientCreatorAccess.item().from(EMItems.DENSE_TIER_INSTALLER),
                EMExtraItems.COSMIC_DENSE_TIER_INSTALLER.asStack()).build(consumer, EMExtras.rl(basePath + "tier_installer/cosmic_dense"));
        AlloyerRecipeBuilder.alloying(
                IngredientCreatorAccess.item().from(EMExtraItems.INFINITE_MULTIVERSAL_CONTROL_CIRCUIT, 2),
                IngredientCreatorAccess.item().from(ExtraItems.INFINITE_TIER_INSTALLER),
                IngredientCreatorAccess.item().from(EMItems.MULTIVERSAL_TIER_INSTALLER),
                EMExtraItems.INFINITE_MULTIVERSAL_TIER_INSTALLER.asStack()).build(consumer, EMExtras.rl(basePath + "tier_installer/infinite_multiversal"));
    }
}
