package io.github.masyumero.emextras.datagen.common.recipe.impl;

import io.github.masyumero.emextras.datagen.common.recipe.BaseRecipeProvider;
import io.github.masyumero.emextras.datagen.common.recipe.ISubRecipeProvider;
import mekanism.api.annotations.NothingNullByDefault;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.*;
import java.util.function.Consumer;

@NothingNullByDefault
public class EMExtrasRecipeProvider extends BaseRecipeProvider {

    public static final char GLASS_CHAR = 'G';

    public EMExtrasRecipeProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, existingFileHelper);
    }

    @Override
    protected void addRecipes(Consumer<FinishedRecipe> consumer) {
    }

    @Override
    protected List<ISubRecipeProvider> getSubRecipeProviders() {
        return List.of(
                new AlloyingRecipeProvider()
        );
    }
}
