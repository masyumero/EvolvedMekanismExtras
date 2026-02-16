package io.github.masyumero.emextras.api.datagen.recipe.builder;

import fr.iglee42.evolvedmekanism.impl.BasicAlloyerRecipe;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.datagen.recipe.MekanismRecipeBuilder;
import mekanism.api.recipes.ingredients.ItemStackIngredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;

@NothingNullByDefault
public class AlloyerRecipeBuilder extends MekanismRecipeBuilder<AlloyerRecipeBuilder> {

    private final ItemStackIngredient mainInput;
    private final ItemStackIngredient extraInput;
    private final ItemStackIngredient tertiaryExtraInput;
    private final ItemStack output;

    protected AlloyerRecipeBuilder(ItemStackIngredient mainInput, ItemStackIngredient extraInput, ItemStackIngredient tertiaryExtraInput, ItemStack output) {
        this.mainInput = mainInput;
        this.extraInput = extraInput;
        this.tertiaryExtraInput = tertiaryExtraInput;
        this.output = output;
    }

    public static AlloyerRecipeBuilder alloying(ItemStackIngredient mainInput, ItemStackIngredient extraInput, ItemStackIngredient tertiaryExtraInput, ItemStack output) {
        if (output.isEmpty()) {
            throw new IllegalArgumentException("This alloying recipe requires a non empty item output.");
        }
        return new AlloyerRecipeBuilder(mainInput, extraInput, tertiaryExtraInput, output);
    }

    @Override
    protected Recipe<?> asRecipe() {
        return new BasicAlloyerRecipe(mainInput, extraInput, tertiaryExtraInput, output);
    }
}
