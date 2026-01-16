package io.github.masyumero.emextras.api.datagen.recipe.builder;

import com.google.gson.JsonObject;
import fr.iglee42.evolvedmekanism.EvolvedMekanism;
import fr.iglee42.evolvedmekanism.utils.EMJsonConstants;
import mekanism.api.JsonConstants;
import mekanism.api.SerializerHelper;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.datagen.recipe.MekanismRecipeBuilder;
import mekanism.api.recipes.ingredients.ItemStackIngredient;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;

@NothingNullByDefault
public class AlloyerRecipeBuilder extends MekanismRecipeBuilder<AlloyerRecipeBuilder> {

    private final ItemStackIngredient mainInput;
    private final ItemStackIngredient extraInput;
    private final ItemStackIngredient tertiaryExtraInput;
    private final ItemStack output;

    protected AlloyerRecipeBuilder(ItemStackIngredient mainInput, ItemStackIngredient extraInput, ItemStackIngredient tertiaryExtraInput, ItemStack output) {
        super(EvolvedMekanism.rl("alloying"));
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
    protected MekanismRecipeBuilder<AlloyerRecipeBuilder>.RecipeResult getResult(ResourceLocation id) {
        return new AlloyerRecipeResult(id);
    }

    public void build(Consumer<FinishedRecipe> consumer) {
        build(consumer, output.getItem());
    }

    public class AlloyerRecipeResult extends RecipeResult {

        protected AlloyerRecipeResult(ResourceLocation id) {
            super(id);
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            json.add(JsonConstants.MAIN_INPUT, mainInput.serialize());
            json.add(JsonConstants.EXTRA_INPUT, extraInput.serialize());
            json.add(EMJsonConstants.SECOND_EXTRA_INPUT, tertiaryExtraInput.serialize());
            json.add(JsonConstants.OUTPUT, SerializerHelper.serializeItemStack(output));
        }
    }
}
