package io.github.masyumero.emextras.datagen.common.recipe.impl;

import com.jerry.mekaf.common.block.attribute.AttributeAdvancedFactoryType;
import com.jerry.mekaf.common.content.blocktype.AdvancedFactoryType;
import com.jerry.mekaf.common.registries.AdvancedFactoryBlocks;
import com.jerry.mekextras.common.integration.mekaf.registries.ExtraAdvancedFactoryBlocks;
import com.jerry.mekextras.common.integration.mekmm.registries.ExtraMoreMachineBlocks;
import com.jerry.mekextras.common.registries.ExtraBlocks;
import com.jerry.mekextras.common.tags.ExtraTags;
import com.jerry.mekextras.common.tier.ExtraFactoryTier;
import com.jerry.mekmm.common.block.attribute.MoreMachineAttributeFactoryType;
import com.jerry.mekmm.common.content.blocktype.MoreMachineFactoryType;
import com.jerry.mekmm.common.registries.MoreMachineBlocks;
import com.jerry.mekmm.common.util.MoreMachineEnumUtils;
import fr.iglee42.evolvedmekanism.registries.EMBlocks;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import fr.iglee42.evolvedmekanism.registries.EMTags;
import fr.iglee42.evolvedmekanism.tiers.EMFactoryTier;
import io.github.masyumero.emextras.common.integration.mekmm.EMExtraMoreMachineFactoryTypes;
import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.common.block.attribute.EMExtraAttributeFactoryType;
import io.github.masyumero.emextras.common.integration.mekaf.registries.EMExtraAdvancedFactoryBlocks;
import io.github.masyumero.emextras.common.integration.mekmm.registries.EMExtraMoreMachineBlocks;
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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.Tags;

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
    public void addRecipes(RecipeOutput consumer, HolderLookup.Provider registries) {
        String basePath = "factory/";
        // Factories
        for (FactoryType type : EnumUtils.FACTORY_TYPES) {
            addFactoryRecipes(consumer, basePath, type, EMExtraFactoryTier.ABSOLUTE_OVERCLOCKED, ExtraFactoryTier.ABSOLUTE, EMFactoryTier.OVERCLOCKED,
                    ExtraTags.Items.ALLOYS_RADIANCE, EMTags.Items.ALLOYS_HYPERCHARGED, EMExtraTags.Items.CIRCUITS_ABSOLUTE_OVERCLOCKED);
            addFactoryRecipes(consumer, basePath, type, EMExtraFactoryTier.SUPREME_QUANTUM, ExtraFactoryTier.SUPREME, EMFactoryTier.QUANTUM,
                    ExtraTags.Items.ALLOYS_THERMONUCLEAR, EMTags.Items.ALLOYS_SUBATOMIC, EMExtraTags.Items.CIRCUITS_SUPREME_QUANTUM);
            addFactoryRecipes(consumer, basePath, type, EMExtraFactoryTier.COSMIC_DENSE, ExtraFactoryTier.COSMIC, EMFactoryTier.DENSE,
                    ExtraTags.Items.ALLOYS_SHINING, EMTags.Items.ALLOYS_SINGULAR, EMExtraTags.Items.CIRCUITS_COSMIC_DENSE);
            addFactoryRecipes(consumer, basePath, type, EMExtraFactoryTier.INFINITE_MULTIVERSAL, ExtraFactoryTier.INFINITE, EMFactoryTier.MULTIVERSAL,
                    ExtraTags.Items.ALLOYS_SPECTRUM, EMTags.Items.ALLOYS_EXOVERSAL, EMExtraTags.Items.CIRCUITS_INFINITE_MULTIVERSAL);
        }

        // Alloying
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

        // MoreMachine Factories
        for (MoreMachineFactoryType type : EMExtraMoreMachineFactoryTypes.SUPPORTED_FACTORY_TYPES) {
            addMoreMachineFactoryRecipes(consumer, basePath, type, EMExtraFactoryTier.ABSOLUTE_OVERCLOCKED, ExtraFactoryTier.ABSOLUTE, EMFactoryTier.OVERCLOCKED,
                    ExtraTags.Items.ALLOYS_RADIANCE, EMTags.Items.ALLOYS_HYPERCHARGED, EMExtraTags.Items.CIRCUITS_ABSOLUTE_OVERCLOCKED);
            addMoreMachineFactoryRecipes(consumer, basePath, type, EMExtraFactoryTier.SUPREME_QUANTUM, ExtraFactoryTier.SUPREME, EMFactoryTier.QUANTUM,
                    ExtraTags.Items.ALLOYS_THERMONUCLEAR, EMTags.Items.ALLOYS_SUBATOMIC, EMExtraTags.Items.CIRCUITS_SUPREME_QUANTUM);
            addMoreMachineFactoryRecipes(consumer, basePath, type, EMExtraFactoryTier.COSMIC_DENSE, ExtraFactoryTier.COSMIC, EMFactoryTier.DENSE,
                    ExtraTags.Items.ALLOYS_SHINING, EMTags.Items.ALLOYS_SINGULAR, EMExtraTags.Items.CIRCUITS_COSMIC_DENSE);
            addMoreMachineFactoryRecipes(consumer, basePath, type, EMExtraFactoryTier.INFINITE_MULTIVERSAL, ExtraFactoryTier.INFINITE, EMFactoryTier.MULTIVERSAL,
                    ExtraTags.Items.ALLOYS_SPECTRUM, EMTags.Items.ALLOYS_EXOVERSAL, EMExtraTags.Items.CIRCUITS_INFINITE_MULTIVERSAL);
        }

        // Advanced Factories
        for (AdvancedFactoryType type : MoreMachineEnumUtils.ADVANCED_FACTORY_TYPES) {
            addAdvancedFactoryRecipes(consumer, basePath, type, EMExtraFactoryTier.ABSOLUTE_OVERCLOCKED, ExtraFactoryTier.ABSOLUTE, EMFactoryTier.OVERCLOCKED,
                    ExtraTags.Items.ALLOYS_RADIANCE, EMTags.Items.ALLOYS_HYPERCHARGED, EMExtraTags.Items.CIRCUITS_ABSOLUTE_OVERCLOCKED);
            addAdvancedFactoryRecipes(consumer, basePath, type, EMExtraFactoryTier.SUPREME_QUANTUM, ExtraFactoryTier.SUPREME, EMFactoryTier.QUANTUM,
                    ExtraTags.Items.ALLOYS_THERMONUCLEAR, EMTags.Items.ALLOYS_SUBATOMIC, EMExtraTags.Items.CIRCUITS_SUPREME_QUANTUM);
            addAdvancedFactoryRecipes(consumer, basePath, type, EMExtraFactoryTier.COSMIC_DENSE, ExtraFactoryTier.COSMIC, EMFactoryTier.DENSE,
                    ExtraTags.Items.ALLOYS_SHINING, EMTags.Items.ALLOYS_SINGULAR, EMExtraTags.Items.CIRCUITS_COSMIC_DENSE);
            addAdvancedFactoryRecipes(consumer, basePath, type, EMExtraFactoryTier.INFINITE_MULTIVERSAL, ExtraFactoryTier.INFINITE, EMFactoryTier.MULTIVERSAL,
                    ExtraTags.Items.ALLOYS_SPECTRUM, EMTags.Items.ALLOYS_EXOVERSAL, EMExtraTags.Items.CIRCUITS_INFINITE_MULTIVERSAL);
        }
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

    private void addFactoryRecipes(RecipeOutput consumer, EMExtraFactoryTier tier,
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

    private void addFactoryRecipes(RecipeOutput consumer, String basePath, FactoryType type,
                                   EMExtraFactoryTier tier, ExtraFactoryTier toExtraUpgradeTier, FactoryTier toUpgradeTier,
                                   TagKey<Item> alloyTag, TagKey<Item> extraAlloyTag, TagKey<Item> circuitTag) {
        addFactoryRecipes(consumer, basePath, tier, factoryTier -> EMExtraBlockUtils.getEMExtraFactory(factoryTier, type), EMBlocks.getFactory(toUpgradeTier, type), ExtraBlocks.getExtraFactory(toExtraUpgradeTier, type), alloyTag, extraAlloyTag, circuitTag);
    }

    private void addFactoryRecipes(RecipeOutput consumer, String basePath, EMExtraFactoryTier tier,
                                   Function<EMExtraFactoryTier, BlockRegistryObject<?, ?>> factory, BlockRegistryObject<?, ?> toExtraUpgrade, BlockRegistryObject<?, ?> toUpgrade,
                                   TagKey<Item> alloyTag, TagKey<Item> extraAlloyTag, TagKey<Item> circuitTag) {
        addFactoryRecipes(consumer, tier, factory, toExtraUpgrade, toUpgrade, alloyTag, extraAlloyTag, circuitTag, EMExtras.rl(basePath + tier.getEMExtraTier().getLowerName() + "/" + Attribute.getOrThrow(factory.apply(tier), EMExtraAttributeFactoryType.class).getFactoryType().getRegistryNameComponent()));
    }

    private void addEMExtraAlloyingFactoryRecipes(RecipeOutput consumer, String basePath,
                                                  EMExtraFactoryTier tier, ExtraFactoryTier toExtraUpgradeTier, FactoryTier toUpgradeTier,
                                                  TagKey<Item> alloyTag, TagKey<Item> extraAlloyTag, TagKey<Item> circuitTag) {
        addFactoryRecipes(consumer, basePath, tier, factoryTier -> EMExtraBlockUtils.getEMExtraFactory(factoryTier, EMFactoryType.ALLOYING), EMBlocks.getFactory(toUpgradeTier, EMFactoryType.ALLOYING), EMExtraBlocks.getExtraFactory(toExtraUpgradeTier, EMFactoryType.ALLOYING), alloyTag, extraAlloyTag, circuitTag);
    }

    private void addMoreMachineFactoryRecipes(RecipeOutput consumer, String basePath, MoreMachineFactoryType type,
                                   EMExtraFactoryTier tier, ExtraFactoryTier toExtraUpgradeTier, FactoryTier toUpgradeTier,
                                   TagKey<Item> alloyTag, TagKey<Item> extraAlloyTag, TagKey<Item> circuitTag) {
        addMoreMachineFactoryRecipes(consumer, basePath, tier, factoryTier -> EMExtraMoreMachineBlocks.getEMExtraMoreMachineFactory(factoryTier, type), MoreMachineBlocks.getMoreMachineFactory(toUpgradeTier, type), ExtraMoreMachineBlocks.getExtraMoreMachineFactory(toExtraUpgradeTier, type), alloyTag, extraAlloyTag, circuitTag);
    }

    private void addMoreMachineFactoryRecipes(RecipeOutput consumer, String basePath, EMExtraFactoryTier tier,
                                   Function<EMExtraFactoryTier, BlockRegistryObject<?, ?>> factory, BlockRegistryObject<?, ?> toExtraUpgrade, BlockRegistryObject<?, ?> toUpgrade,
                                   TagKey<Item> alloyTag, TagKey<Item> extraAlloyTag, TagKey<Item> circuitTag) {
        addFactoryRecipes(consumer, tier, factory, toExtraUpgrade, toUpgrade, alloyTag, extraAlloyTag, circuitTag, EMExtras.rl(basePath + tier.getEMExtraTier().getLowerName() + "/" + Attribute.getOrThrow(factory.apply(tier), MoreMachineAttributeFactoryType.class).getMoreMachineFactoryType().getRegistryNameComponent()));
    }

    private void addAdvancedFactoryRecipes(RecipeOutput consumer, String basePath, AdvancedFactoryType type,
                                   EMExtraFactoryTier tier, ExtraFactoryTier toExtraUpgradeTier, FactoryTier toUpgradeTier,
                                   TagKey<Item> alloyTag, TagKey<Item> extraAlloyTag, TagKey<Item> circuitTag) {
        addAdvancedFactoryRecipes(consumer, basePath, tier, factoryTier -> EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactory(factoryTier, type), AdvancedFactoryBlocks.getAdvancedFactory(toUpgradeTier, type), ExtraAdvancedFactoryBlocks.getExtraAdvancedFactory(toExtraUpgradeTier, type), alloyTag, extraAlloyTag, circuitTag);
    }

    private void addAdvancedFactoryRecipes(RecipeOutput consumer, String basePath, EMExtraFactoryTier tier,
                                   Function<EMExtraFactoryTier, BlockRegistryObject<?, ?>> factory, BlockRegistryObject<?, ?> toExtraUpgrade, BlockRegistryObject<?, ?> toUpgrade,
                                   TagKey<Item> alloyTag, TagKey<Item> extraAlloyTag, TagKey<Item> circuitTag) {
        addFactoryRecipes(consumer, tier, factory, toExtraUpgrade, toUpgrade, alloyTag, extraAlloyTag, circuitTag, EMExtras.rl(basePath + tier.getEMExtraTier().getLowerName() + "/" + Attribute.getOrThrow(factory.apply(tier), AttributeAdvancedFactoryType.class).getAdvancedFactoryType().getRegistryNameComponent()));
    }
}
