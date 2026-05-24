package io.github.masyumero.emextras.datagen.common.recipe.impl;

import com.jerry.mekanism_extras.common.registry.ExtraBlock;
import com.jerry.mekanism_extras.common.tier.AdvancedFactoryTier;
import fr.iglee42.evolvedmekanism.registries.EMBlocks;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import fr.iglee42.evolvedmekanism.registries.EMTags;
import fr.iglee42.evolvedmekanism.tiers.EMFactoryTier;
import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.common.block.attribute.EMExtraAttributeFactoryType;
import io.github.masyumero.emextras.common.content.blocktype.EMExtraFactoryType;
import io.github.masyumero.emextras.common.registry.EMExtrasBlock;
import io.github.masyumero.emextras.common.EMExtraTags;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import io.github.masyumero.emextras.common.util.EMExtraBlockUtils;
import io.github.masyumero.emextras.common.util.EMExtraTagUtils;
import io.github.masyumero.emextras.datagen.common.recipe.ISubRecipeProvider;
import io.github.masyumero.emextras.datagen.common.recipe.builder.MekDataShapedRecipeBuilder;
import io.github.masyumero.emextras.datagen.common.recipe.pattern.Pattern;
import io.github.masyumero.emextras.datagen.common.recipe.pattern.RecipePattern;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.block.attribute.AttributeFactoryType;
import mekanism.common.content.blocktype.FactoryType;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.registries.MekanismBlocks;
import mekanism.common.tags.MekanismTags;
import mekanism.common.tier.FactoryTier;
import mekanism.common.util.EnumUtils;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;
import java.util.function.Function;

public class FactoryRecipeProvider implements ISubRecipeProvider {

    private static final RecipePattern EMEXTRA_FACTORY_PATTERN = RecipePattern.createPattern(
            RecipePattern.TripleLine.of(Pattern.ALLOY, Pattern.CIRCUIT, Pattern.EXTRA_ALLOY),
            RecipePattern.TripleLine.of(Pattern.PREVIOUS, Pattern.STEEL_CASING, Pattern.EXTRA_PREVIOUS),
            RecipePattern.TripleLine.of(Pattern.EXTRA_ALLOY, Pattern.CIRCUIT, Pattern.ALLOY)
    );

    private static final RecipePattern FACTORY_PATTERN = RecipePattern.createPattern(
            RecipePattern.TripleLine.of(Pattern.ALLOY, Pattern.CIRCUIT, Pattern.ALLOY),
            RecipePattern.TripleLine.of(Pattern.CONSTANT, Pattern.PREVIOUS, Pattern.CONSTANT),
            RecipePattern.TripleLine.of(Pattern.ALLOY, Pattern.CIRCUIT, Pattern.ALLOY)
    );

    @Override
    public void addRecipes(Consumer<FinishedRecipe> consumer) {
        String basePath = "factory/";
        // Factories
        for (FactoryType type : EnumUtils.FACTORY_TYPES) {
            if (type == EMFactoryType.ALLOYING) {
                continue;
            }
            addFactoryRecipes(consumer, basePath, type, EMExtraFactoryTier.ABSOLUTE_OVERCLOCKED, AdvancedFactoryTier.ABSOLUTE, EMFactoryTier.OVERCLOCKED,
                    EMExtraTagUtils.ALLOYS_RADIANCE, EMTags.Items.ALLOYS_HYPERCHARGED, EMExtraTags.Items.ABSOLUTE_OVERCLOCKED_CONTROL_CIRCUIT);
            addFactoryRecipes(consumer, basePath, type, EMExtraFactoryTier.SUPREME_QUANTUM, AdvancedFactoryTier.SUPREME, EMFactoryTier.QUANTUM,
                    EMExtraTagUtils.ALLOYS_THERMONUCLEAR, EMTags.Items.ALLOYS_SUBATOMIC, EMExtraTags.Items.SUPREME_QUANTUM_CONTROL_CIRCUIT);
            addFactoryRecipes(consumer, basePath, type, EMExtraFactoryTier.COSMIC_DENSE, AdvancedFactoryTier.COSMIC, EMFactoryTier.DENSE,
                    EMExtraTagUtils.ALLOYS_SHINING, EMTags.Items.ALLOYS_SINGULAR, EMExtraTags.Items.COSMIC_DENSE_CONTROL_CIRCUIT);
            addFactoryRecipes(consumer, basePath, type, EMExtraFactoryTier.INFINITE_MULTIVERSAL, AdvancedFactoryTier.INFINITE, EMFactoryTier.MULTIVERSAL,
                    EMExtraTagUtils.ALLOYS_SPECTRUM, EMTags.Items.ALLOYS_EXOVERSAL, EMExtraTags.Items.INFINITE_MULTIVERSAL_CONTROL_CIRCUIT);
        }

        // Alloying
        addEMExtraAlloyingFactoryRecipes(consumer, basePath, EMExtraFactoryTier.ABSOLUTE_OVERCLOCKED, AdvancedFactoryTier.ABSOLUTE, EMFactoryTier.OVERCLOCKED,
                EMExtraTagUtils.ALLOYS_RADIANCE, EMTags.Items.ALLOYS_HYPERCHARGED, EMExtraTags.Items.ABSOLUTE_OVERCLOCKED_CONTROL_CIRCUIT);
        addEMExtraAlloyingFactoryRecipes(consumer, basePath, EMExtraFactoryTier.SUPREME_QUANTUM, AdvancedFactoryTier.SUPREME, EMFactoryTier.QUANTUM,
                EMExtraTagUtils.ALLOYS_THERMONUCLEAR, EMTags.Items.ALLOYS_SUBATOMIC, EMExtraTags.Items.SUPREME_QUANTUM_CONTROL_CIRCUIT);
        addEMExtraAlloyingFactoryRecipes(consumer, basePath, EMExtraFactoryTier.COSMIC_DENSE, AdvancedFactoryTier.COSMIC, EMFactoryTier.DENSE,
                EMExtraTagUtils.ALLOYS_SHINING, EMTags.Items.ALLOYS_SINGULAR, EMExtraTags.Items.COSMIC_DENSE_CONTROL_CIRCUIT);
        addEMExtraAlloyingFactoryRecipes(consumer, basePath, EMExtraFactoryTier.INFINITE_MULTIVERSAL, AdvancedFactoryTier.INFINITE, EMFactoryTier.MULTIVERSAL,
                EMExtraTagUtils.ALLOYS_SPECTRUM, EMTags.Items.ALLOYS_EXOVERSAL, EMExtraTags.Items.INFINITE_MULTIVERSAL_CONTROL_CIRCUIT);

        addExtraFactoryRecipes(consumer, basePath, MekanismBlocks.getFactory(FactoryTier.ULTIMATE, EMFactoryType.ALLOYING), EMFactoryType.ALLOYING, AdvancedFactoryTier.ABSOLUTE, EMExtraTagUtils.ALLOYS_RADIANCE, EMExtraTagUtils.CIRCUITS_ABSOLUTE, Tags.Items.GEMS_EMERALD);
        addExtraFactoryRecipes(consumer, basePath, EMExtrasBlock.getAdvancedFactory(AdvancedFactoryTier.ABSOLUTE, EMFactoryType.ALLOYING), EMFactoryType.ALLOYING, AdvancedFactoryTier.SUPREME, EMExtraTagUtils.ALLOYS_THERMONUCLEAR, EMExtraTagUtils.CIRCUITS_SUPREME, Tags.Items.INGOTS_NETHERITE);
        addExtraFactoryRecipes(consumer, basePath, EMExtrasBlock.getAdvancedFactory(AdvancedFactoryTier.SUPREME, EMFactoryType.ALLOYING), EMFactoryType.ALLOYING, AdvancedFactoryTier.COSMIC, EMExtraTagUtils.ALLOYS_SHINING, EMExtraTagUtils.CIRCUITS_COSMIC, MekanismTags.Items.INGOTS_REFINED_OBSIDIAN);
        var factory = EMExtrasBlock.getAdvancedFactory(AdvancedFactoryTier.INFINITE, EMFactoryType.ALLOYING);
        MekDataShapedRecipeBuilder.shapedRecipe(factory)
                .pattern(RecipePattern.createPattern(
                        RecipePattern.TripleLine.of(Pattern.ALLOY, Pattern.CIRCUIT, Pattern.ALLOY),
                        RecipePattern.TripleLine.of(Pattern.CONSTANT, Pattern.PREVIOUS, Pattern.EXTRA_CONSTANT),
                        RecipePattern.TripleLine.of(Pattern.ALLOY, Pattern.CIRCUIT, Pattern.ALLOY))
                )
                .key(Pattern.ALLOY, EMExtraTagUtils.ALLOYS_SPECTRUM)
                .key(Pattern.CIRCUIT, EMExtraTagUtils.CIRCUITS_INFINITE)
                .key(Pattern.CONSTANT, MekanismTags.Items.PELLETS_PLUTONIUM)
                .key(Pattern.EXTRA_CONSTANT, MekanismTags.Items.PELLETS_POLONIUM)
                .key(Pattern.PREVIOUS, EMExtrasBlock.getAdvancedFactory(AdvancedFactoryTier.COSMIC, EMFactoryType.ALLOYING))
                .build(consumer, EMExtras.rl(basePath + "infinite/" + Attribute.get(factory, AttributeFactoryType.class).getFactoryType().getRegistryNameComponent()));
    }

    private void addExtraFactoryRecipes(Consumer<FinishedRecipe> consumer, String basePath, BlockRegistryObject<?, ?> toUpgrade , FactoryType type,
                                        AdvancedFactoryTier tier, TagKey<Item> alloyTag, TagKey<Item> circuitTag, TagKey<Item> ingotTag) {
        var factory = EMExtrasBlock.getAdvancedFactory(tier, type);
        MekDataShapedRecipeBuilder.shapedRecipe(factory)
                .pattern(FACTORY_PATTERN)
                .key(Pattern.ALLOY, alloyTag)
                .key(Pattern.CIRCUIT, circuitTag)
                .key(Pattern.CONSTANT, ingotTag)
                .key(Pattern.PREVIOUS, toUpgrade)
                .build(consumer, EMExtras.rl(basePath + tier.getAdvanceTier().getLowerName() + "/" + Attribute.get(factory, AttributeFactoryType.class).getFactoryType().getRegistryNameComponent()));
    }

    private void addFactoryRecipes(Consumer<FinishedRecipe> consumer, EMExtraFactoryTier tier,
                                   Function<EMExtraFactoryTier, BlockRegistryObject<?, ?>> factory, BlockRegistryObject<?, ?> toExtraUpgrade, BlockRegistryObject<?, ?> toUpgrade,
                                   TagKey<Item> alloyTag, TagKey<Item> extraAlloyTag, TagKey<Item> circuitTag, ResourceLocation path) {
        MekDataShapedRecipeBuilder.shapedRecipe(factory.apply(tier))
                .pattern(EMEXTRA_FACTORY_PATTERN)
                .key(Pattern.ALLOY, alloyTag)
                .key(Pattern.EXTRA_ALLOY, extraAlloyTag)
                .key(Pattern.CIRCUIT, circuitTag)
                .key(Pattern.PREVIOUS, toUpgrade)
                .key(Pattern.EXTRA_PREVIOUS, toExtraUpgrade)
                .key(Pattern.STEEL_CASING, MekanismBlocks.STEEL_CASING)
                .build(consumer, path);
    }

    private void addFactoryRecipes(Consumer<FinishedRecipe> consumer, String basePath, FactoryType type,
                                   EMExtraFactoryTier tier, AdvancedFactoryTier toExtraUpgradeTier, FactoryTier toUpgradeTier,
                                   TagKey<Item> alloyTag, TagKey<Item> extraAlloyTag, TagKey<Item> circuitTag) {
        addFactoryRecipes(consumer, basePath, tier, factoryTier -> EMExtraBlockUtils.getEMExtraFactory(factoryTier, type), MekanismBlocks.getFactory(toUpgradeTier, type), ExtraBlock.getAdvancedFactory(toExtraUpgradeTier, type), alloyTag, extraAlloyTag, circuitTag);
    }

    private void addFactoryRecipes(Consumer<FinishedRecipe> consumer, String basePath, EMExtraFactoryTier tier,
                                   Function<EMExtraFactoryTier, BlockRegistryObject<?, ?>> factory, BlockRegistryObject<?, ?> toExtraUpgrade, BlockRegistryObject<?, ?> toUpgrade,
                                   TagKey<Item> alloyTag, TagKey<Item> extraAlloyTag, TagKey<Item> circuitTag) {
        addFactoryRecipes(consumer, tier, factory, toExtraUpgrade, toUpgrade, alloyTag, extraAlloyTag, circuitTag, EMExtras.rl(basePath + tier.getEMExtraTier().getLowerName() + "/" + Attribute.get(factory.apply(tier), EMExtraAttributeFactoryType.class).getFactoryType().getRegistryNameComponent()));
    }

    private void addEMExtraAlloyingFactoryRecipes(Consumer<FinishedRecipe> consumer, String basePath,
                                                  EMExtraFactoryTier tier, AdvancedFactoryTier toExtraUpgradeTier, FactoryTier toUpgradeTier,
                                                  TagKey<Item> alloyTag, TagKey<Item> extraAlloyTag, TagKey<Item> circuitTag) {
        addFactoryRecipes(consumer, basePath, tier, factoryTier -> EMExtrasBlock.getEMExtraFactory(factoryTier, EMExtraFactoryType.ALLOYING), MekanismBlocks.getFactory(toUpgradeTier, EMFactoryType.ALLOYING), EMExtrasBlock.getAdvancedFactory(toExtraUpgradeTier, EMFactoryType.ALLOYING), alloyTag, extraAlloyTag, circuitTag);
    }
}