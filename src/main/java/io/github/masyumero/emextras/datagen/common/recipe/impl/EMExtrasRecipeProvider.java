package io.github.masyumero.emextras.datagen.common.recipe.impl;

import io.github.masyumero.emextras.datagen.common.recipe.BaseRecipeProvider;
import io.github.masyumero.emextras.datagen.common.recipe.ISubRecipeProvider;
import mekanism.api.annotations.NothingNullByDefault;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@NothingNullByDefault
public class EMExtrasRecipeProvider extends BaseRecipeProvider {

    public static final char GLASS_CHAR = 'G';

    public EMExtrasRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper existingFileHelper) {
        super(output, provider, existingFileHelper);
    }

    @Override
    protected void addRecipes(RecipeOutput consumer, HolderLookup.Provider registries) {
    }

    @Override
    protected List<ISubRecipeProvider> getSubRecipeProviders() {
        return List.of(
                new AlloyingRecipeProvider()
        );
    }
}
