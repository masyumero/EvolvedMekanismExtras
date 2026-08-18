package io.github.masyumero.emextras.datagen.common.recipe.impl;

import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.common.registry.EMExtraItems;
import io.github.masyumero.emextras.datagen.common.recipe.ISubRecipeProvider;
import mekanism.api.datagen.recipe.builder.CombinerRecipeBuilder;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import mekanism.common.registries.MekanismItems;
import mekanism.common.tags.MekanismTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;

public class CombinerRecipeProvider implements ISubRecipeProvider {

    @Override
    public void addRecipes(RecipeOutput consumer, HolderLookup.Provider registries) {
        String basePath = "combiner/";
        CombinerRecipeBuilder.combining(
                IngredientCreatorAccess.item().from(MekanismTags.Items.CIRCUITS_BASIC),
                IngredientCreatorAccess.item().from(MekanismItems.HDPE_SHEET, 4),
                EMExtraItems.BASE_CONTROL_CIRCUIT.asStack()).build(consumer, EMExtras.rl(basePath + "base_circuit"));
    }
}
