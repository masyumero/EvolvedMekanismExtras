package io.github.masyumero.emextras.datagen.common.recipe.impl;

import com.jerry.mekaf.common.block.attribute.AttributeAdvancedFactoryType;
import com.jerry.mekaf.common.content.blocktype.AdvancedFactoryType;
import com.jerry.mekaf.common.registries.AdvancedFactoryBlocks;
import com.jerry.mekanism_extras.common.integration.mekaf.registries.ExtraAdvancedFactoryBlocks;
import com.jerry.mekanism_extras.common.integration.mekmm.registries.ExtraMoreMachineBlocks;
import com.jerry.mekanism_extras.common.registries.ExtraBlocks;
import com.jerry.mekanism_extras.common.tier.ExtraFactoryTier;
import com.jerry.mekmm.common.block.attribute.AttributeMoreMachineFactoryType;
import com.jerry.mekmm.common.content.blocktype.MoreMachineFactoryType;
import com.jerry.mekmm.common.registries.MoreMachineBlocks;
import com.jerry.mekmm.common.util.MoreMachineEnumUtils;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import fr.iglee42.evolvedmekanism.registries.EMTags;
import fr.iglee42.evolvedmekanism.tiers.EMFactoryTier;
import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.common.integration.mekaf.regisrty.EMExtraAdvancedFactoryBlocks;
import io.github.masyumero.emextras.common.integration.mekmm.registry.EMExtraMoreMachineBlocks;
import io.github.masyumero.emextras.common.registry.EMExtraBlocks;
import io.github.masyumero.emextras.common.EMExtraTags;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
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
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;

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
            addFactoryRecipes(consumer, basePath, type, EMExtraFactoryTier.ABSOLUTE_OVERCLOCKED, ExtraFactoryTier.ABSOLUTE, EMFactoryTier.OVERCLOCKED,
                    EMExtraTagUtils.ALLOYS_RADIANCE, EMTags.Items.ALLOYS_HYPERCHARGED, EMExtraTags.Items.ABSOLUTE_OVERCLOCKED_CONTROL_CIRCUIT);
            addFactoryRecipes(consumer, basePath, type, EMExtraFactoryTier.SUPREME_QUANTUM, ExtraFactoryTier.SUPREME, EMFactoryTier.QUANTUM,
                    EMExtraTagUtils.ALLOYS_THERMONUCLEAR, EMTags.Items.ALLOYS_SUBATOMIC, EMExtraTags.Items.SUPREME_QUANTUM_CONTROL_CIRCUIT);
            addFactoryRecipes(consumer, basePath, type, EMExtraFactoryTier.COSMIC_DENSE, ExtraFactoryTier.COSMIC, EMFactoryTier.DENSE,
                    EMExtraTagUtils.ALLOYS_SHINING, EMTags.Items.ALLOYS_SINGULAR, EMExtraTags.Items.COSMIC_DENSE_CONTROL_CIRCUIT);
            addFactoryRecipes(consumer, basePath, type, EMExtraFactoryTier.INFINITE_MULTIVERSAL, ExtraFactoryTier.INFINITE, EMFactoryTier.MULTIVERSAL,
                    EMExtraTagUtils.ALLOYS_SPECTRUM, EMTags.Items.ALLOYS_EXOVERSAL, EMExtraTags.Items.INFINITE_MULTIVERSAL_CONTROL_CIRCUIT);
        }

        // Alloying
        addEMExtraAlloyingFactoryRecipes(consumer, basePath, EMExtraFactoryTier.ABSOLUTE_OVERCLOCKED, ExtraFactoryTier.ABSOLUTE, EMFactoryTier.OVERCLOCKED,
                EMExtraTagUtils.ALLOYS_RADIANCE, EMTags.Items.ALLOYS_HYPERCHARGED, EMExtraTags.Items.ABSOLUTE_OVERCLOCKED_CONTROL_CIRCUIT);
        addEMExtraAlloyingFactoryRecipes(consumer, basePath, EMExtraFactoryTier.SUPREME_QUANTUM, ExtraFactoryTier.SUPREME, EMFactoryTier.QUANTUM,
                EMExtraTagUtils.ALLOYS_THERMONUCLEAR, EMTags.Items.ALLOYS_SUBATOMIC, EMExtraTags.Items.SUPREME_QUANTUM_CONTROL_CIRCUIT);
        addEMExtraAlloyingFactoryRecipes(consumer, basePath, EMExtraFactoryTier.COSMIC_DENSE, ExtraFactoryTier.COSMIC, EMFactoryTier.DENSE,
                EMExtraTagUtils.ALLOYS_SHINING, EMTags.Items.ALLOYS_SINGULAR, EMExtraTags.Items.COSMIC_DENSE_CONTROL_CIRCUIT);
        addEMExtraAlloyingFactoryRecipes(consumer, basePath, EMExtraFactoryTier.INFINITE_MULTIVERSAL, ExtraFactoryTier.INFINITE, EMFactoryTier.MULTIVERSAL,
                EMExtraTagUtils.ALLOYS_SPECTRUM, EMTags.Items.ALLOYS_EXOVERSAL, EMExtraTags.Items.INFINITE_MULTIVERSAL_CONTROL_CIRCUIT);

        addExtraFactoryRecipes(consumer, basePath, MekanismBlocks.getFactory(FactoryTier.ULTIMATE, EMFactoryType.ALLOYING), EMFactoryType.ALLOYING, ExtraFactoryTier.ABSOLUTE, EMExtraTagUtils.ALLOYS_RADIANCE, EMExtraTagUtils.CIRCUITS_ABSOLUTE, Tags.Items.GEMS_EMERALD);
        addExtraFactoryRecipes(consumer, basePath, EMExtraBlocks.getExtraFactory(ExtraFactoryTier.ABSOLUTE, EMFactoryType.ALLOYING), EMFactoryType.ALLOYING, ExtraFactoryTier.SUPREME, EMExtraTagUtils.ALLOYS_THERMONUCLEAR, EMExtraTagUtils.CIRCUITS_SUPREME, Tags.Items.INGOTS_NETHERITE);
        addExtraFactoryRecipes(consumer, basePath, EMExtraBlocks.getExtraFactory(ExtraFactoryTier.SUPREME, EMFactoryType.ALLOYING), EMFactoryType.ALLOYING, ExtraFactoryTier.COSMIC, EMExtraTagUtils.ALLOYS_SHINING, EMExtraTagUtils.CIRCUITS_COSMIC, MekanismTags.Items.INGOTS_REFINED_OBSIDIAN);
        var factory = EMExtraBlocks.getExtraFactory(ExtraFactoryTier.INFINITE, EMFactoryType.ALLOYING);
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
                .key(Pattern.PREVIOUS, EMExtraBlocks.getExtraFactory(ExtraFactoryTier.COSMIC, EMFactoryType.ALLOYING))
                .build(consumer, EMExtras.rl(basePath + "infinite/" + Attribute.get(factory, AttributeFactoryType.class).getFactoryType().getRegistryNameComponent()));

        // MoreMachine Factories
        for (MoreMachineFactoryType type : MoreMachineEnumUtils.MM_FACTORY_TYPES) {
            addMoreMachineFactoryRecipes(consumer, basePath, type, EMExtraFactoryTier.ABSOLUTE_OVERCLOCKED, ExtraFactoryTier.ABSOLUTE, EMFactoryTier.OVERCLOCKED,
                    EMExtraTagUtils.ALLOYS_RADIANCE, EMTags.Items.ALLOYS_HYPERCHARGED, EMExtraTags.Items.ABSOLUTE_OVERCLOCKED_CONTROL_CIRCUIT);
            addMoreMachineFactoryRecipes(consumer, basePath, type, EMExtraFactoryTier.SUPREME_QUANTUM, ExtraFactoryTier.SUPREME, EMFactoryTier.QUANTUM,
                    EMExtraTagUtils.ALLOYS_THERMONUCLEAR, EMTags.Items.ALLOYS_SUBATOMIC, EMExtraTags.Items.SUPREME_QUANTUM_CONTROL_CIRCUIT);
            addMoreMachineFactoryRecipes(consumer, basePath, type, EMExtraFactoryTier.COSMIC_DENSE, ExtraFactoryTier.COSMIC, EMFactoryTier.DENSE,
                    EMExtraTagUtils.ALLOYS_SHINING, EMTags.Items.ALLOYS_SINGULAR, EMExtraTags.Items.COSMIC_DENSE_CONTROL_CIRCUIT);
            addMoreMachineFactoryRecipes(consumer, basePath, type, EMExtraFactoryTier.INFINITE_MULTIVERSAL, ExtraFactoryTier.INFINITE, EMFactoryTier.MULTIVERSAL,
                    EMExtraTagUtils.ALLOYS_SPECTRUM, EMTags.Items.ALLOYS_EXOVERSAL, EMExtraTags.Items.INFINITE_MULTIVERSAL_CONTROL_CIRCUIT);
        }

        // Advanced Factories
        for (AdvancedFactoryType type : MoreMachineEnumUtils.ADVANCED_FACTORY_TYPES) {
            addAdvancedFactoryRecipes(consumer, basePath, type, EMExtraFactoryTier.ABSOLUTE_OVERCLOCKED, ExtraFactoryTier.ABSOLUTE, EMFactoryTier.OVERCLOCKED,
                    EMExtraTagUtils.ALLOYS_RADIANCE, EMTags.Items.ALLOYS_HYPERCHARGED, EMExtraTags.Items.ABSOLUTE_OVERCLOCKED_CONTROL_CIRCUIT);
            addAdvancedFactoryRecipes(consumer, basePath, type, EMExtraFactoryTier.SUPREME_QUANTUM, ExtraFactoryTier.SUPREME, EMFactoryTier.QUANTUM,
                    EMExtraTagUtils.ALLOYS_THERMONUCLEAR, EMTags.Items.ALLOYS_SUBATOMIC, EMExtraTags.Items.SUPREME_QUANTUM_CONTROL_CIRCUIT);
            addAdvancedFactoryRecipes(consumer, basePath, type, EMExtraFactoryTier.COSMIC_DENSE, ExtraFactoryTier.COSMIC, EMFactoryTier.DENSE,
                    EMExtraTagUtils.ALLOYS_SHINING, EMTags.Items.ALLOYS_SINGULAR, EMExtraTags.Items.COSMIC_DENSE_CONTROL_CIRCUIT);
            addAdvancedFactoryRecipes(consumer, basePath, type, EMExtraFactoryTier.INFINITE_MULTIVERSAL, ExtraFactoryTier.INFINITE, EMFactoryTier.MULTIVERSAL,
                    EMExtraTagUtils.ALLOYS_SPECTRUM, EMTags.Items.ALLOYS_EXOVERSAL, EMExtraTags.Items.INFINITE_MULTIVERSAL_CONTROL_CIRCUIT);
        }
    }

    private void addExtraFactoryRecipes(Consumer<FinishedRecipe> consumer, String basePath, BlockRegistryObject<?, ?> toUpgrade , FactoryType type,
                                        ExtraFactoryTier tier, TagKey<Item> alloyTag, TagKey<Item> circuitTag, TagKey<Item> ingotTag) {
        var factory = EMExtraBlocks.getExtraFactory(tier, type);
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
                                   TagKey<Item> alloyTag, TagKey<Item> extraAlloyTag, TagKey<Item> circuitTag, ResourceLocation path, ICondition... conditions) {
        var recipe = MekDataShapedRecipeBuilder.shapedRecipe(factory.apply(tier))
                .pattern(EMEXTRA_FACTORY_PATTERN)
                .key(Pattern.ALLOY, alloyTag)
                .key(Pattern.EXTRA_ALLOY, extraAlloyTag)
                .key(Pattern.CIRCUIT, circuitTag)
                .key(Pattern.PREVIOUS, toUpgrade)
                .key(Pattern.EXTRA_PREVIOUS, toExtraUpgrade)
                .key(Pattern.STEEL_CASING, MekanismBlocks.STEEL_CASING);
        for (ICondition condition : conditions) {
            recipe.addCondition(condition);
        }
        recipe.build(consumer, path);
    }

    private void addFactoryRecipes(Consumer<FinishedRecipe> consumer, String basePath, FactoryType type,
                                   EMExtraFactoryTier tier, ExtraFactoryTier toExtraUpgradeTier, FactoryTier toUpgradeTier,
                                   TagKey<Item> alloyTag, TagKey<Item> extraAlloyTag, TagKey<Item> circuitTag) {
        addFactoryRecipes(consumer, basePath, tier, factoryTier -> EMExtraBlocks.getEMExtraFactory(factoryTier, type), MekanismBlocks.getFactory(toUpgradeTier, type), ExtraBlocks.getAdvancedFactory(toExtraUpgradeTier, type), alloyTag, extraAlloyTag, circuitTag);
    }

    private void addFactoryRecipes(Consumer<FinishedRecipe> consumer, String basePath, EMExtraFactoryTier tier,
                                   Function<EMExtraFactoryTier, BlockRegistryObject<?, ?>> factory, BlockRegistryObject<?, ?> toExtraUpgrade, BlockRegistryObject<?, ?> toUpgrade,
                                   TagKey<Item> alloyTag, TagKey<Item> extraAlloyTag, TagKey<Item> circuitTag) {
        addFactoryRecipes(consumer, tier, factory, toExtraUpgrade, toUpgrade, alloyTag, extraAlloyTag, circuitTag, EMExtras.rl(basePath + tier.getEMExtraTier().getLowerName() + "/" + Attribute.get(factory.apply(tier), AttributeFactoryType.class).getFactoryType().getRegistryNameComponent()));
    }

    private void addEMExtraAlloyingFactoryRecipes(Consumer<FinishedRecipe> consumer, String basePath,
                                                  EMExtraFactoryTier tier, ExtraFactoryTier toExtraUpgradeTier, FactoryTier toUpgradeTier,
                                                  TagKey<Item> alloyTag, TagKey<Item> extraAlloyTag, TagKey<Item> circuitTag) {
        addFactoryRecipes(consumer, basePath, tier, factoryTier -> EMExtraBlocks.getEMExtraFactory(factoryTier, EMFactoryType.ALLOYING), MekanismBlocks.getFactory(toUpgradeTier, EMFactoryType.ALLOYING), EMExtraBlocks.getExtraFactory(toExtraUpgradeTier, EMFactoryType.ALLOYING), alloyTag, extraAlloyTag, circuitTag);
    }

    private void addMoreMachineFactoryRecipes(Consumer<FinishedRecipe> consumer, String basePath, MoreMachineFactoryType type,
                                              EMExtraFactoryTier tier, ExtraFactoryTier toExtraUpgradeTier, FactoryTier toUpgradeTier,
                                              TagKey<Item> alloyTag, TagKey<Item> extraAlloyTag, TagKey<Item> circuitTag) {
        addMoreMachineFactoryRecipes(consumer, basePath, tier, factoryTier -> EMExtraMoreMachineBlocks.getEMExtraMoreMachineFactory(factoryTier, type), MoreMachineBlocks.getMoreMachineFactory(toUpgradeTier, type), ExtraMoreMachineBlocks.getExtraMoreMachineFactory(toExtraUpgradeTier, type), alloyTag, extraAlloyTag, circuitTag);
    }

    private void addMoreMachineFactoryRecipes(Consumer<FinishedRecipe> consumer, String basePath, EMExtraFactoryTier tier,
                                              Function<EMExtraFactoryTier, BlockRegistryObject<?, ?>> factory, BlockRegistryObject<?, ?> toExtraUpgrade, BlockRegistryObject<?, ?> toUpgrade,
                                              TagKey<Item> alloyTag, TagKey<Item> extraAlloyTag, TagKey<Item> circuitTag) {
        addFactoryRecipes(consumer, tier, factory, toExtraUpgrade, toUpgrade, alloyTag, extraAlloyTag, circuitTag, EMExtras.rl(basePath + tier.getEMExtraTier().getLowerName() + "/" + Attribute.get(factory.apply(tier), AttributeMoreMachineFactoryType.class).getMoreMachineFactoryType().getRegistryNameComponent()), new ModLoadedCondition("mekmm"));
    }

    private void addAdvancedFactoryRecipes(Consumer<FinishedRecipe> consumer, String basePath, AdvancedFactoryType type,
                                           EMExtraFactoryTier tier, ExtraFactoryTier toExtraUpgradeTier, FactoryTier toUpgradeTier,
                                           TagKey<Item> alloyTag, TagKey<Item> extraAlloyTag, TagKey<Item> circuitTag) {
        addAdvancedFactoryRecipes(consumer, basePath, tier, factoryTier -> EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactory(factoryTier, type), AdvancedFactoryBlocks.getAdvancedFactory(toUpgradeTier, type), ExtraAdvancedFactoryBlocks.getExtraAdvancedFactory(toExtraUpgradeTier, type), alloyTag, extraAlloyTag, circuitTag);
    }

    private void addAdvancedFactoryRecipes(Consumer<FinishedRecipe> consumer, String basePath, EMExtraFactoryTier tier,
                                           Function<EMExtraFactoryTier, BlockRegistryObject<?, ?>> factory, BlockRegistryObject<?, ?> toExtraUpgrade, BlockRegistryObject<?, ?> toUpgrade,
                                           TagKey<Item> alloyTag, TagKey<Item> extraAlloyTag, TagKey<Item> circuitTag) {
        addFactoryRecipes(consumer, tier, factory, toExtraUpgrade, toUpgrade, alloyTag, extraAlloyTag, circuitTag, EMExtras.rl(basePath + tier.getEMExtraTier().getLowerName() + "/" + Attribute.get(factory.apply(tier), AttributeAdvancedFactoryType.class).getAdvancedFactoryType().getRegistryNameComponent()), new ModLoadedCondition("mekmm"));
    }
}