package io.github.masyumero.emextras.datagen.common.recipe.impl;

import com.jerry.mekanism_extras.common.registries.ExtraBlocks;
import fr.iglee42.evolvedmekanism.registries.EMBlocks;
import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.common.EMExtraTags;
import io.github.masyumero.emextras.common.block.attribute.EMExtraAttribute;
import io.github.masyumero.emextras.common.registry.EMExtrasBlock;
import io.github.masyumero.emextras.common.tier.EMExtraICTier;
import io.github.masyumero.emextras.common.tier.EMExtraIPTier;
import io.github.masyumero.emextras.datagen.common.recipe.BaseRecipeProvider;
import io.github.masyumero.emextras.datagen.common.recipe.ISubRecipeProvider;
import io.github.masyumero.emextras.datagen.common.recipe.builder.MekDataShapedRecipeBuilder;
import io.github.masyumero.emextras.datagen.common.recipe.pattern.Pattern;
import io.github.masyumero.emextras.datagen.common.recipe.pattern.RecipePattern;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.common.registries.MekanismBlocks;
import mekanism.common.registries.MekanismItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

@NothingNullByDefault
public class EMExtrasRecipeProvider extends BaseRecipeProvider {

    public static final char GLASS_CHAR = 'G';
    public static final char OTHER_PREVIOUS_CHAR = 'O';

    private final RecipePattern inductionCellPattern = RecipePattern.createPattern(
            RecipePattern.TripleLine.of(Pattern.ENERGY, OTHER_PREVIOUS_CHAR, Pattern.ENERGY),
            RecipePattern.TripleLine.of(Pattern.PREVIOUS, Pattern.CONSTANT, Pattern.PREVIOUS),
            RecipePattern.TripleLine.of(Pattern.ENERGY, OTHER_PREVIOUS_CHAR, Pattern.ENERGY)
    );

    private final RecipePattern inductionProviderPattern = RecipePattern.createPattern(
            RecipePattern.TripleLine.of(Pattern.CIRCUIT, OTHER_PREVIOUS_CHAR, Pattern.CIRCUIT),
            RecipePattern.TripleLine.of(Pattern.PREVIOUS, Pattern.CONSTANT, Pattern.PREVIOUS),
            RecipePattern.TripleLine.of(Pattern.CIRCUIT, OTHER_PREVIOUS_CHAR, Pattern.CIRCUIT)
    );

    public EMExtrasRecipeProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, existingFileHelper);
    }

    @Override
    protected void addRecipes(Consumer<FinishedRecipe> consumer) {
        addInduction(consumer);
    }

    @Override
    protected List<ISubRecipeProvider> getSubRecipeProviders() {
        return List.of(
                new AlloyingRecipeProvider(),
                new CombinerRecipeProvider(),
                new FactoryRecipeProvider()
        );
    }

    private void addInduction(Consumer<FinishedRecipe> consumer) {
        String basePath = "induction/";
        Function<Block, ResourceLocation> cellPath = block -> EMExtras.rl(basePath + "cell/" + EMExtraAttribute.getTier(block, EMExtraICTier.class).getEMExtraTier().getLowerName());
        Function<Block, ResourceLocation> providerPath = block -> EMExtras.rl(basePath + "provider/" + EMExtraAttribute.getTier(block, EMExtraIPTier.class).getEMExtraTier().getLowerName());

        // Cells
        MekDataShapedRecipeBuilder.shapedRecipe(EMExtrasBlock.ABSOLUTE_OVERCLOCKED_INDUCTION_CELL).pattern(inductionCellPattern)
                .key(Pattern.ENERGY, MekanismItems.ENERGY_TABLET)
                .key(OTHER_PREVIOUS_CHAR, ExtraBlocks.ABSOLUTE_INDUCTION_CELL)
                .key(Pattern.PREVIOUS, EMBlocks.OVERCLOCKED_INDUCTION_CELL)
                .key(Pattern.CONSTANT, MekanismBlocks.ULTIMATE_INDUCTION_CELL)
                .build(consumer, cellPath.apply(EMExtrasBlock.ABSOLUTE_OVERCLOCKED_INDUCTION_CELL.getBlock()));

        MekDataShapedRecipeBuilder.shapedRecipe(EMExtrasBlock.SUPREME_QUANTUM_INDUCTION_CELL).pattern(inductionCellPattern)
                .key(Pattern.ENERGY, MekanismItems.ENERGY_TABLET)
                .key(OTHER_PREVIOUS_CHAR, ExtraBlocks.SUPREME_INDUCTION_CELL)
                .key(Pattern.PREVIOUS, EMBlocks.QUANTUM_INDUCTION_CELL)
                .key(Pattern.CONSTANT, EMExtrasBlock.ABSOLUTE_OVERCLOCKED_INDUCTION_CELL)
                .build(consumer, cellPath.apply(EMExtrasBlock.SUPREME_QUANTUM_INDUCTION_CELL.getBlock()));

        MekDataShapedRecipeBuilder.shapedRecipe(EMExtrasBlock.COSMIC_DENSE_INDUCTION_CELL).pattern(inductionCellPattern)
                .key(Pattern.ENERGY, MekanismItems.ENERGY_TABLET)
                .key(OTHER_PREVIOUS_CHAR, ExtraBlocks.COSMIC_INDUCTION_CELL)
                .key(Pattern.PREVIOUS, EMBlocks.DENSE_INDUCTION_CELL)
                .key(Pattern.CONSTANT, EMExtrasBlock.SUPREME_QUANTUM_INDUCTION_CELL)
                .build(consumer, cellPath.apply(EMExtrasBlock.COSMIC_DENSE_INDUCTION_CELL.getBlock()));

        MekDataShapedRecipeBuilder.shapedRecipe(EMExtrasBlock.INFINITE_MULTIVERSAL_INDUCTION_CELL).pattern(inductionCellPattern)
                .key(Pattern.ENERGY, MekanismItems.ENERGY_TABLET)
                .key(OTHER_PREVIOUS_CHAR, ExtraBlocks.INFINITE_INDUCTION_CELL)
                .key(Pattern.PREVIOUS, EMBlocks.MULTIVERSAL_INDUCTION_CELL)
                .key(Pattern.CONSTANT, EMExtrasBlock.COSMIC_DENSE_INDUCTION_CELL)
                .build(consumer, cellPath.apply(EMExtrasBlock.INFINITE_MULTIVERSAL_INDUCTION_CELL.getBlock()));

        // Providers
        MekDataShapedRecipeBuilder.shapedRecipe(EMExtrasBlock.ABSOLUTE_OVERCLOCKED_INDUCTION_PROVIDER).pattern(inductionProviderPattern)
                .key(Pattern.CIRCUIT, EMExtraTags.Items.ABSOLUTE_OVERCLOCKED_CONTROL_CIRCUIT)
                .key(OTHER_PREVIOUS_CHAR, ExtraBlocks.ABSOLUTE_INDUCTION_PROVIDER)
                .key(Pattern.PREVIOUS, EMBlocks.OVERCLOCKED_INDUCTION_PROVIDER)
                .key(Pattern.CONSTANT, MekanismBlocks.ULTIMATE_INDUCTION_PROVIDER)
                .build(consumer, providerPath.apply(EMExtrasBlock.ABSOLUTE_OVERCLOCKED_INDUCTION_PROVIDER.getBlock()));

        MekDataShapedRecipeBuilder.shapedRecipe(EMExtrasBlock.SUPREME_QUANTUM_INDUCTION_PROVIDER).pattern(inductionProviderPattern)
                .key(Pattern.CIRCUIT, EMExtraTags.Items.SUPREME_QUANTUM_CONTROL_CIRCUIT)
                .key(OTHER_PREVIOUS_CHAR, ExtraBlocks.SUPREME_INDUCTION_PROVIDER)
                .key(Pattern.PREVIOUS, EMBlocks.QUANTUM_INDUCTION_PROVIDER)
                .key(Pattern.CONSTANT, EMExtrasBlock.ABSOLUTE_OVERCLOCKED_INDUCTION_PROVIDER)
                .build(consumer, providerPath.apply(EMExtrasBlock.SUPREME_QUANTUM_INDUCTION_PROVIDER.getBlock()));

        MekDataShapedRecipeBuilder.shapedRecipe(EMExtrasBlock.COSMIC_DENSE_INDUCTION_PROVIDER).pattern(inductionProviderPattern)
                .key(Pattern.CIRCUIT, EMExtraTags.Items.COSMIC_DENSE_CONTROL_CIRCUIT)
                .key(OTHER_PREVIOUS_CHAR, ExtraBlocks.COSMIC_INDUCTION_PROVIDER)
                .key(Pattern.PREVIOUS, EMBlocks.DENSE_INDUCTION_PROVIDER)
                .key(Pattern.CONSTANT, EMExtrasBlock.SUPREME_QUANTUM_INDUCTION_PROVIDER)
                .build(consumer, providerPath.apply(EMExtrasBlock.COSMIC_DENSE_INDUCTION_PROVIDER.getBlock()));

        MekDataShapedRecipeBuilder.shapedRecipe(EMExtrasBlock.INFINITE_MULTIVERSAL_INDUCTION_PROVIDER).pattern(inductionProviderPattern)
                .key(Pattern.CIRCUIT, EMExtraTags.Items.INFINITE_MULTIVERSAL_CONTROL_CIRCUIT)
                .key(OTHER_PREVIOUS_CHAR, ExtraBlocks.INFINITE_INDUCTION_PROVIDER)
                .key(Pattern.PREVIOUS, EMBlocks.MULTIVERSAL_INDUCTION_PROVIDER)
                .key(Pattern.CONSTANT, EMExtrasBlock.COSMIC_DENSE_INDUCTION_PROVIDER)
                .build(consumer, providerPath.apply(EMExtrasBlock.INFINITE_MULTIVERSAL_INDUCTION_PROVIDER.getBlock()));
    }
}
