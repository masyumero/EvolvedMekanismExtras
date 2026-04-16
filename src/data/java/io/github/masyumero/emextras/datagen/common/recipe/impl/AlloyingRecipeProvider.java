package io.github.masyumero.emextras.datagen.common.recipe.impl;

import com.jerry.mekanism_extras.common.registry.ExtraItem;
import fr.iglee42.evolvedmekanism.registries.EMItems;
import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.api.datagen.recipe.builder.AlloyerRecipeBuilder;
import io.github.masyumero.emextras.common.registry.EMExtrasItem;
import io.github.masyumero.emextras.datagen.common.recipe.ISubRecipeProvider;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

public class AlloyingRecipeProvider implements ISubRecipeProvider {

    @Override
    public void addRecipes(Consumer<FinishedRecipe> consumer) {
        String basePath = "alloying/";
        AlloyerRecipeBuilder.alloying(
                IngredientCreatorAccess.item().from(EMExtrasItem.ABSOLUTE_OVERCLOCKED_CONTROL_CIRCUIT, 2),
                IngredientCreatorAccess.item().from(ExtraItem.ABSOLUTE_TIER_INSTALLER),
                IngredientCreatorAccess.item().from(EMItems.OVERCLOCKED_TIER_INSTALLER),
                EMExtrasItem.ABSOLUTE_OVERCLOCKED_TIER_INSTALLER.getItemStack()).build(consumer, EMExtras.rl(basePath + "tier_installer/absolute_overclocked"));
        AlloyerRecipeBuilder.alloying(
                IngredientCreatorAccess.item().from(EMExtrasItem.SUPREME_QUANTUM_CONTROL_CIRCUIT, 2),
                IngredientCreatorAccess.item().from(ExtraItem.SUPREME_TIER_INSTALLER),
                IngredientCreatorAccess.item().from(EMItems.QUANTUM_TIER_INSTALLER),
                EMExtrasItem.SUPREME_QUANTUM_TIER_INSTALLER.getItemStack()).build(consumer, EMExtras.rl(basePath + "tier_installer/supreme_quantum"));
        AlloyerRecipeBuilder.alloying(
                IngredientCreatorAccess.item().from(EMExtrasItem.COSMIC_DENSE_CONTROL_CIRCUIT, 2),
                IngredientCreatorAccess.item().from(ExtraItem.COSMIC_TIER_INSTALLER),
                IngredientCreatorAccess.item().from(EMItems.DENSE_TIER_INSTALLER),
                EMExtrasItem.COSMIC_DENSE_TIER_INSTALLER.getItemStack()).build(consumer, EMExtras.rl(basePath + "tier_installer/cosmic_dense"));
        AlloyerRecipeBuilder.alloying(
                IngredientCreatorAccess.item().from(EMExtrasItem.INFINITE_MULTIVERSAL_CONTROL_CIRCUIT, 2),
                IngredientCreatorAccess.item().from(ExtraItem.INFINITE_TIER_INSTALLER),
                IngredientCreatorAccess.item().from(EMItems.MULTIVERSAL_TIER_INSTALLER),
                EMExtrasItem.INFINITE_MULTIVERSAL_TIER_INSTALLER.getItemStack()).build(consumer, EMExtras.rl(basePath + "tier_installer/infinite_multiversal"));
    }
}
