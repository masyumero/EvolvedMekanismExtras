package io.github.masyumero.emextras.datagen.common.recipe.impl;

import com.jerry.mekextras.common.registries.ExtraBlocks;
import com.jerry.mekextras.common.tags.ExtraTags;
import com.jerry.mekextras.common.tier.ExtraFactoryTier;
import fr.iglee42.evolvedmekanism.registries.EMBlocks;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import fr.iglee42.evolvedmekanism.registries.EMTags;
import fr.iglee42.evolvedmekanism.tiers.EMFactoryTier;
import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.common.block.attribute.EMExtraAttributeFactoryType;
import io.github.masyumero.emextras.common.registry.EMExtraBlocks;
import io.github.masyumero.emextras.common.tags.EMExtraTags;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import io.github.masyumero.emextras.common.util.EMExtraBlockUtils;
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
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.Tags;

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
    public void addRecipes(RecipeOutput consumer, HolderLookup.Provider registries) {
        String basePath = "factory/";
        for (FactoryType type : EnumUtils.FACTORY_TYPES) {
            addEMExtraFactoryRecipes(consumer, basePath, type, EMExtraFactoryTier.ABSOLUTE_OVERCLOCKED, ExtraFactoryTier.ABSOLUTE, EMFactoryTier.OVERCLOCKED,
                    ExtraTags.Items.ALLOYS_RADIANCE, EMTags.Items.ALLOYS_HYPERCHARGED, EMExtraTags.Items.CIRCUITS_ABSOLUTE_OVERCLOCKED);
            addEMExtraFactoryRecipes(consumer, basePath, type, EMExtraFactoryTier.SUPREME_QUANTUM, ExtraFactoryTier.SUPREME, EMFactoryTier.QUANTUM,
                    ExtraTags.Items.ALLOYS_THERMONUCLEAR, EMTags.Items.ALLOYS_SUBATOMIC, EMExtraTags.Items.CIRCUITS_SUPREME_QUANTUM);
            addEMExtraFactoryRecipes(consumer, basePath, type, EMExtraFactoryTier.COSMIC_DENSE, ExtraFactoryTier.COSMIC, EMFactoryTier.DENSE,
                    ExtraTags.Items.ALLOYS_SHINING, EMTags.Items.ALLOYS_SINGULAR, EMExtraTags.Items.CIRCUITS_COSMIC_DENSE);
            addEMExtraFactoryRecipes(consumer, basePath, type, EMExtraFactoryTier.INFINITE_MULTIVERSAL, ExtraFactoryTier.INFINITE, EMFactoryTier.MULTIVERSAL,
                    ExtraTags.Items.ALLOYS_SPECTRUM, EMTags.Items.ALLOYS_EXOVERSAL, EMExtraTags.Items.CIRCUITS_INFINITE_MULTIVERSAL);
        }

        addEMExtraAlloyingFactoryRecipes(consumer, basePath, EMExtraFactoryTier.ABSOLUTE_OVERCLOCKED, ExtraFactoryTier.ABSOLUTE, EMFactoryTier.OVERCLOCKED,
                ExtraTags.Items.ALLOYS_RADIANCE, EMTags.Items.ALLOYS_HYPERCHARGED, EMExtraTags.Items.CIRCUITS_ABSOLUTE_OVERCLOCKED);
        addEMExtraAlloyingFactoryRecipes(consumer, basePath, EMExtraFactoryTier.SUPREME_QUANTUM, ExtraFactoryTier.SUPREME, EMFactoryTier.QUANTUM,
                ExtraTags.Items.ALLOYS_THERMONUCLEAR, EMTags.Items.ALLOYS_SUBATOMIC, EMExtraTags.Items.CIRCUITS_SUPREME_QUANTUM);
        addEMExtraAlloyingFactoryRecipes(consumer, basePath, EMExtraFactoryTier.COSMIC_DENSE, ExtraFactoryTier.COSMIC, EMFactoryTier.DENSE,
                ExtraTags.Items.ALLOYS_SHINING, EMTags.Items.ALLOYS_SINGULAR, EMExtraTags.Items.CIRCUITS_COSMIC_DENSE);
        addEMExtraAlloyingFactoryRecipes(consumer, basePath, EMExtraFactoryTier.INFINITE_MULTIVERSAL, ExtraFactoryTier.INFINITE, EMFactoryTier.MULTIVERSAL,
                ExtraTags.Items.ALLOYS_SPECTRUM, EMTags.Items.ALLOYS_EXOVERSAL, EMExtraTags.Items.CIRCUITS_INFINITE_MULTIVERSAL);

        addExtraFactoryRecipes(consumer, basePath, EMBlocks.getFactory(FactoryTier.ULTIMATE, EMFactoryType.ALLOYING), EMFactoryType.ALLOYING, ExtraFactoryTier.ABSOLUTE, ExtraTags.Items.ALLOYS_RADIANCE, ExtraTags.Items.CIRCUITS_ABSOLUTE, Tags.Items.GEMS_EMERALD);
        addExtraFactoryRecipes(consumer, basePath, EMExtraBlocks.getExtraFactory(ExtraFactoryTier.ABSOLUTE, EMFactoryType.ALLOYING), EMFactoryType.ALLOYING, ExtraFactoryTier.SUPREME, ExtraTags.Items.ALLOYS_THERMONUCLEAR, ExtraTags.Items.CIRCUITS_SUPREME, Tags.Items.INGOTS_NETHERITE);
        addExtraFactoryRecipes(consumer, basePath, EMExtraBlocks.getExtraFactory(ExtraFactoryTier.SUPREME, EMFactoryType.ALLOYING), EMFactoryType.ALLOYING, ExtraFactoryTier.COSMIC, ExtraTags.Items.ALLOYS_SHINING, ExtraTags.Items.CIRCUITS_COSMIC, MekanismTags.Items.INGOTS_REFINED_OBSIDIAN);
        var factory = EMExtraBlocks.getExtraFactory(ExtraFactoryTier.INFINITE, EMFactoryType.ALLOYING);
        MekDataShapedRecipeBuilder.shapedRecipe(factory)
                .pattern(RecipePattern.createPattern(
                                RecipePattern.TripleLine.of(Pattern.ALLOY, Pattern.CIRCUIT, Pattern.ALLOY),
                                RecipePattern.TripleLine.of(Pattern.CONSTANT, Pattern.PREVIOUS, Pattern.EXTRA_CONSTANT),
                                RecipePattern.TripleLine.of(Pattern.ALLOY, Pattern.CIRCUIT, Pattern.ALLOY))
                )
                .key(Pattern.ALLOY, ExtraTags.Items.ALLOYS_SPECTRUM)
                .key(Pattern.CIRCUIT, ExtraTags.Items.CIRCUITS_INFINITE)
                .key(Pattern.CONSTANT, MekanismTags.Items.PELLETS_PLUTONIUM)
                .key(Pattern.EXTRA_CONSTANT, MekanismTags.Items.PELLETS_POLONIUM)
                .key(Pattern.PREVIOUS, EMExtraBlocks.getExtraFactory(ExtraFactoryTier.COSMIC, EMFactoryType.ALLOYING))
                .build(consumer, EMExtras.rl(basePath + "infinite/" + Attribute.getOrThrow(factory, AttributeFactoryType.class).getFactoryType().getRegistryNameComponent()));
    }

    private void addExtraFactoryRecipes(RecipeOutput consumer, String basePath, BlockRegistryObject<?, ?> toUpgrade , FactoryType type,
                                        ExtraFactoryTier tier, TagKey<Item> alloyTag, TagKey<Item> circuitTag, TagKey<Item> ingotTag) {
        var factory = EMExtraBlocks.getExtraFactory(tier, type);
        MekDataShapedRecipeBuilder.shapedRecipe(factory)
                .pattern(FACTORY_PATTERN)
                .key(Pattern.ALLOY, alloyTag)
                .key(Pattern.CIRCUIT, circuitTag)
                .key(Pattern.CONSTANT, ingotTag)
                .key(Pattern.PREVIOUS, toUpgrade)
                .build(consumer, EMExtras.rl(basePath + tier.getAdvanceTier().getLowerName() + "/" + Attribute.getOrThrow(factory, AttributeFactoryType.class).getFactoryType().getRegistryNameComponent()));
    }

    private void addEMExtraFactoryRecipes(RecipeOutput consumer, String basePath, FactoryType type,
                                          EMExtraFactoryTier tier, BlockRegistryObject<?, ?> toExtraUpgrade, BlockRegistryObject<?, ?> toUpgrade,
                                          TagKey<Item> alloyTag, TagKey<Item> extraAlloyTag, TagKey<Item> circuitTag) {
        var factory = EMExtraBlockUtils.getEMExtraFactory(tier, type);
        MekDataShapedRecipeBuilder.shapedRecipe(factory)
                .pattern(EMEXTRA_FACTORY_PATTERN)
                .key(Pattern.ALLOY, alloyTag)
                .key(Pattern.EXTRA_ALLOY, extraAlloyTag)
                .key(Pattern.CIRCUIT, circuitTag)
                .key(Pattern.PREVIOUS, toUpgrade)
                .key(Pattern.EXTRA_PREVIOUS, toExtraUpgrade)
                .key(Pattern.STEEL_CASING, MekanismBlocks.STEEL_CASING)
                .build(consumer, EMExtras.rl(basePath + tier.getEMExtraTier().getLowerName() + "/" + Attribute.getOrThrow(factory, EMExtraAttributeFactoryType.class).getFactoryType().getRegistryNameComponent()));
    }

    private void addEMExtraFactoryRecipes(RecipeOutput consumer, String basePath, FactoryType type,
                                          EMExtraFactoryTier tier, ExtraFactoryTier toExtraUpgradeTier, FactoryTier toUpgradeTier,
                                          TagKey<Item> alloyTag, TagKey<Item> extraAlloyTag, TagKey<Item> circuitTag) {
        addEMExtraFactoryRecipes(consumer, basePath, type, tier, EMBlocks.getFactory(toUpgradeTier, type), ExtraBlocks.getExtraFactory(toExtraUpgradeTier, type), alloyTag, extraAlloyTag, circuitTag);
    }

    private void addEMExtraAlloyingFactoryRecipes(RecipeOutput consumer, String basePath,
                                          EMExtraFactoryTier tier, ExtraFactoryTier toExtraUpgradeTier, FactoryTier toUpgradeTier,
                                          TagKey<Item> alloyTag, TagKey<Item> extraAlloyTag, TagKey<Item> circuitTag) {
        addEMExtraFactoryRecipes(consumer, basePath, EMFactoryType.ALLOYING, tier, EMBlocks.getFactory(toUpgradeTier, EMFactoryType.ALLOYING), EMExtraBlocks.getExtraFactory(toExtraUpgradeTier, EMFactoryType.ALLOYING), alloyTag, extraAlloyTag, circuitTag);
    }
}
