package io.github.masyumero.emextras.datagen.common.recipe.impl;

import com.jerry.mekanism_extras.common.registries.ExtraItems;
import fr.iglee42.evolvedmekanism.registries.EMItems;
import fr.iglee42.evolvedmekanism.registries.EMTags;
import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.api.datagen.recipe.builder.AlloyerRecipeBuilder;
import io.github.masyumero.emextras.api.tier.EMExtraTier;
import io.github.masyumero.emextras.common.EMExtraTags;
import io.github.masyumero.emextras.common.registry.EMExtraBlocks;
import io.github.masyumero.emextras.common.registry.EMExtraItems;
import io.github.masyumero.emextras.common.util.EMExtraTagUtils;
import io.github.masyumero.emextras.common.util.EMExtraTransporterUtils;
import io.github.masyumero.emextras.datagen.common.recipe.ISubRecipeProvider;
import mekanism.api.recipes.ingredients.ItemStackIngredient;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import mekanism.api.tier.ITier;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.registration.impl.ItemRegistryObject;
import mekanism.common.registries.MekanismBlocks;
import mekanism.common.registries.MekanismItems;
import mekanism.common.resource.PrimaryResource;
import mekanism.common.resource.ResourceType;
import mekanism.common.tags.MekanismTags;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

public class AlloyingRecipeProvider implements ISubRecipeProvider {

    @Override
    public void addRecipes(Consumer<FinishedRecipe> consumer) {
        String basePath = "alloying/";
        AlloyerRecipeBuilder.alloying(
                IngredientCreatorAccess.item().from(EMExtraTags.Items.ABSOLUTE_OVERCLOCKED_CONTROL_CIRCUIT, 2),
                IngredientCreatorAccess.item().from(ExtraItems.ABSOLUTE_TIER_INSTALLER),
                IngredientCreatorAccess.item().from(EMItems.OVERCLOCKED_TIER_INSTALLER),
                EMExtraItems.ABSOLUTE_OVERCLOCKED_TIER_INSTALLER.getItemStack()).build(consumer, EMExtras.rl(basePath + "tier_installer/absolute_overclocked"));
        AlloyerRecipeBuilder.alloying(
                IngredientCreatorAccess.item().from(EMExtraTags.Items.SUPREME_QUANTUM_CONTROL_CIRCUIT, 2),
                IngredientCreatorAccess.item().from(ExtraItems.SUPREME_TIER_INSTALLER),
                IngredientCreatorAccess.item().from(EMItems.QUANTUM_TIER_INSTALLER),
                EMExtraItems.SUPREME_QUANTUM_TIER_INSTALLER.getItemStack()).build(consumer, EMExtras.rl(basePath + "tier_installer/supreme_quantum"));
        AlloyerRecipeBuilder.alloying(
                IngredientCreatorAccess.item().from(EMExtraTags.Items.COSMIC_DENSE_CONTROL_CIRCUIT, 2),
                IngredientCreatorAccess.item().from(ExtraItems.COSMIC_TIER_INSTALLER),
                IngredientCreatorAccess.item().from(EMItems.DENSE_TIER_INSTALLER),
                EMExtraItems.COSMIC_DENSE_TIER_INSTALLER.getItemStack()).build(consumer, EMExtras.rl(basePath + "tier_installer/cosmic_dense"));
        AlloyerRecipeBuilder.alloying(
                IngredientCreatorAccess.item().from(EMExtraTags.Items.INFINITE_MULTIVERSAL_CONTROL_CIRCUIT, 2),
                IngredientCreatorAccess.item().from(ExtraItems.INFINITE_TIER_INSTALLER),
                IngredientCreatorAccess.item().from(EMItems.MULTIVERSAL_TIER_INSTALLER),
                EMExtraItems.INFINITE_MULTIVERSAL_TIER_INSTALLER.getItemStack()).build(consumer, EMExtras.rl(basePath + "tier_installer/infinite_multiversal"));
        // transmitters
        transmitters(consumer, "logistical_transporter", EMExtraBlocks.ABSOLUTE_OVERCLOCKED_LOGISTICAL_TRANSPORTER, MekanismBlocks.ULTIMATE_LOGISTICAL_TRANSPORTER, EMExtraTagUtils.ALLOYS_RADIANCE, EMTags.Items.ALLOYS_OVERCLOCKED);
        transmitters(consumer, "mechanical_pipe", EMExtraBlocks.ABSOLUTE_OVERCLOCKED_MECHANICAL_PIPE, MekanismBlocks.ULTIMATE_MECHANICAL_PIPE, EMExtraTagUtils.ALLOYS_RADIANCE, EMTags.Items.ALLOYS_OVERCLOCKED);
        transmitters(consumer, "pressurized_tube", EMExtraBlocks.ABSOLUTE_OVERCLOCKED_PRESSURIZED_TUBE, MekanismBlocks.ULTIMATE_PRESSURIZED_TUBE, EMExtraTagUtils.ALLOYS_RADIANCE, EMTags.Items.ALLOYS_OVERCLOCKED);
        transmitters(consumer, "thermodynamic_conductor", EMExtraBlocks.ABSOLUTE_OVERCLOCKED_THERMODYNAMIC_CONDUCTOR, MekanismBlocks.ULTIMATE_THERMODYNAMIC_CONDUCTOR, EMExtraTagUtils.ALLOYS_RADIANCE, EMTags.Items.ALLOYS_OVERCLOCKED);
        transmitters(consumer, "universal_cable", EMExtraBlocks.ABSOLUTE_OVERCLOCKED_UNIVERSAL_CABLE, MekanismBlocks.ULTIMATE_UNIVERSAL_CABLE, EMExtraTagUtils.ALLOYS_RADIANCE, EMTags.Items.ALLOYS_OVERCLOCKED);

        transmitters(consumer, "logistical_transporter", EMExtraBlocks.SUPREME_QUANTUM_LOGISTICAL_TRANSPORTER, EMExtraBlocks.ABSOLUTE_OVERCLOCKED_LOGISTICAL_TRANSPORTER, EMExtraTagUtils.ALLOYS_THERMONUCLEAR, EMTags.Items.ALLOYS_QUANTUM);
        transmitters(consumer, "mechanical_pipe", EMExtraBlocks.SUPREME_QUANTUM_MECHANICAL_PIPE, EMExtraBlocks.ABSOLUTE_OVERCLOCKED_MECHANICAL_PIPE, EMExtraTagUtils.ALLOYS_THERMONUCLEAR, EMTags.Items.ALLOYS_QUANTUM);
        transmitters(consumer, "pressurized_tube", EMExtraBlocks.SUPREME_QUANTUM_PRESSURIZED_TUBE, EMExtraBlocks.ABSOLUTE_OVERCLOCKED_PRESSURIZED_TUBE, EMExtraTagUtils.ALLOYS_THERMONUCLEAR, EMTags.Items.ALLOYS_QUANTUM);
        transmitters(consumer, "thermodynamic_conductor", EMExtraBlocks.SUPREME_QUANTUM_THERMODYNAMIC_CONDUCTOR, EMExtraBlocks.ABSOLUTE_OVERCLOCKED_THERMODYNAMIC_CONDUCTOR, EMExtraTagUtils.ALLOYS_THERMONUCLEAR, EMTags.Items.ALLOYS_QUANTUM);
        transmitters(consumer, "universal_cable", EMExtraBlocks.SUPREME_QUANTUM_UNIVERSAL_CABLE, EMExtraBlocks.ABSOLUTE_OVERCLOCKED_UNIVERSAL_CABLE, EMExtraTagUtils.ALLOYS_THERMONUCLEAR, EMTags.Items.ALLOYS_QUANTUM);

        transmitters(consumer, "logistical_transporter", EMExtraBlocks.COSMIC_DENSE_LOGISTICAL_TRANSPORTER, EMExtraBlocks.SUPREME_QUANTUM_LOGISTICAL_TRANSPORTER, EMExtraTagUtils.ALLOYS_SHINING, EMTags.Items.ALLOYS_DENSE);
        transmitters(consumer, "mechanical_pipe", EMExtraBlocks.COSMIC_DENSE_MECHANICAL_PIPE, EMExtraBlocks.SUPREME_QUANTUM_MECHANICAL_PIPE, EMExtraTagUtils.ALLOYS_SHINING, EMTags.Items.ALLOYS_DENSE);
        transmitters(consumer, "pressurized_tube", EMExtraBlocks.COSMIC_DENSE_PRESSURIZED_TUBE, EMExtraBlocks.SUPREME_QUANTUM_PRESSURIZED_TUBE, EMExtraTagUtils.ALLOYS_SHINING, EMTags.Items.ALLOYS_DENSE);
        transmitters(consumer, "thermodynamic_conductor", EMExtraBlocks.COSMIC_DENSE_THERMODYNAMIC_CONDUCTOR, EMExtraBlocks.SUPREME_QUANTUM_THERMODYNAMIC_CONDUCTOR, EMExtraTagUtils.ALLOYS_SHINING, EMTags.Items.ALLOYS_DENSE);
        transmitters(consumer, "universal_cable", EMExtraBlocks.COSMIC_DENSE_UNIVERSAL_CABLE, EMExtraBlocks.SUPREME_QUANTUM_UNIVERSAL_CABLE, EMExtraTagUtils.ALLOYS_SHINING, EMTags.Items.ALLOYS_DENSE);

        transmitters(consumer, "logistical_transporter", EMExtraBlocks.INFINITE_MULTIVERSAL_LOGISTICAL_TRANSPORTER, EMExtraBlocks.COSMIC_DENSE_LOGISTICAL_TRANSPORTER, EMExtraTagUtils.ALLOYS_SPECTRUM, EMTags.Items.ALLOYS_MULTIVERSAL);
        transmitters(consumer, "mechanical_pipe", EMExtraBlocks.INFINITE_MULTIVERSAL_MECHANICAL_PIPE, EMExtraBlocks.COSMIC_DENSE_MECHANICAL_PIPE, EMExtraTagUtils.ALLOYS_SPECTRUM, EMTags.Items.ALLOYS_MULTIVERSAL);
        transmitters(consumer, "pressurized_tube", EMExtraBlocks.INFINITE_MULTIVERSAL_PRESSURIZED_TUBE, EMExtraBlocks.COSMIC_DENSE_PRESSURIZED_TUBE, EMExtraTagUtils.ALLOYS_SPECTRUM, EMTags.Items.ALLOYS_MULTIVERSAL);
        transmitters(consumer, "thermodynamic_conductor", EMExtraBlocks.INFINITE_MULTIVERSAL_THERMODYNAMIC_CONDUCTOR, EMExtraBlocks.COSMIC_DENSE_THERMODYNAMIC_CONDUCTOR, EMExtraTagUtils.ALLOYS_SPECTRUM, EMTags.Items.ALLOYS_MULTIVERSAL);
        transmitters(consumer, "universal_cable", EMExtraBlocks.INFINITE_MULTIVERSAL_UNIVERSAL_CABLE, EMExtraBlocks.COSMIC_DENSE_UNIVERSAL_CABLE, EMExtraTagUtils.ALLOYS_SPECTRUM, EMTags.Items.ALLOYS_MULTIVERSAL);

        // circuits
        alloyingCircuit(consumer, basePath + "circuits/", EMExtraItems.getCircuit(EMExtraTier.ABSOLUTE_OVERCLOCKED), EMExtraTagUtils.CIRCUITS_ABSOLUTE, EMTags.Items.CIRCUITS_OVERCLOCKED, IngredientCreatorAccess.item().from(EMExtraItems.BASE_CONTROL_CIRCUIT));
        alloyingCircuit(consumer, basePath + "circuits/", EMExtraItems.getCircuit(EMExtraTier.SUPREME_QUANTUM), EMExtraTagUtils.CIRCUITS_SUPREME, EMTags.Items.CIRCUITS_QUANTUM, IngredientCreatorAccess.item().from(EMExtraItems.BASE_CONTROL_CIRCUIT));
        alloyingCircuit(consumer, basePath + "circuits/", EMExtraItems.getCircuit(EMExtraTier.COSMIC_DENSE), EMExtraTagUtils.CIRCUITS_COSMIC, EMTags.Items.CIRCUITS_DENSE, IngredientCreatorAccess.item().from(EMExtraItems.BASE_CONTROL_CIRCUIT));
        alloyingCircuit(consumer, basePath + "circuits/", EMExtraItems.getCircuit(EMExtraTier.INFINITE_MULTIVERSAL), EMExtraTagUtils.CIRCUITS_INFINITE, EMTags.Items.CIRCUITS_MULTIVERSAL, IngredientCreatorAccess.item().from(EMExtraItems.BASE_CONTROL_CIRCUIT));

        alloyingCircuit(consumer, basePath + "circuits/upgrade/", EMExtraItems.getCircuit(EMExtraTier.SUPREME_QUANTUM), EMExtraTagUtils.ALLOYS_THERMONUCLEAR, EMTags.Items.ALLOYS_QUANTUM, IngredientCreatorAccess.item().from(EMExtraTags.Items.ABSOLUTE_OVERCLOCKED_CONTROL_CIRCUIT));
        alloyingCircuit(consumer, basePath + "circuits/upgrade/", EMExtraItems.getCircuit(EMExtraTier.COSMIC_DENSE), EMExtraTagUtils.ALLOYS_SHINING, EMTags.Items.ALLOYS_DENSE, IngredientCreatorAccess.item().from(EMExtraTags.Items.SUPREME_QUANTUM_CONTROL_CIRCUIT));
        alloyingCircuit(consumer, basePath + "circuits/upgrade/", EMExtraItems.getCircuit(EMExtraTier.INFINITE_MULTIVERSAL), EMExtraTagUtils.ALLOYS_SPECTRUM, EMTags.Items.ALLOYS_MULTIVERSAL, IngredientCreatorAccess.item().from(EMExtraTags.Items.COSMIC_DENSE_CONTROL_CIRCUIT));

        alloyingCircuit(consumer, basePath + "circuits/", MekanismItems.BASIC_CONTROL_CIRCUIT, MekanismTags.Items.ALLOYS_BASIC, MekanismTags.Items.PROCESSED_RESOURCES.get(ResourceType.INGOT, PrimaryResource.OSMIUM), IngredientCreatorAccess.item().from(EMExtraItems.BASE_CONTROL_CIRCUIT));
        alloyingCircuit(consumer, basePath + "circuits/", MekanismItems.ADVANCED_CONTROL_CIRCUIT, MekanismTags.Items.ALLOYS_ADVANCED, MekanismTags.Items.CIRCUITS_BASIC, IngredientCreatorAccess.item().from(EMExtraItems.BASE_CONTROL_CIRCUIT));
        alloyingCircuit(consumer, basePath + "circuits/", MekanismItems.ELITE_CONTROL_CIRCUIT, MekanismTags.Items.ALLOYS_ELITE, MekanismTags.Items.CIRCUITS_ADVANCED, IngredientCreatorAccess.item().from(EMExtraItems.BASE_CONTROL_CIRCUIT));
        alloyingCircuit(consumer, basePath + "circuits/", MekanismItems.ULTIMATE_CONTROL_CIRCUIT, MekanismTags.Items.ALLOYS_ULTIMATE, MekanismTags.Items.CIRCUITS_ELITE, IngredientCreatorAccess.item().from(EMExtraItems.BASE_CONTROL_CIRCUIT));

        alloyingCircuit(consumer, basePath + "circuits/", ExtraItems.ABSOLUTE_CONTROL_CIRCUIT, EMExtraTagUtils.ALLOYS_RADIANCE, MekanismTags.Items.CIRCUITS_ULTIMATE, IngredientCreatorAccess.item().from(EMExtraItems.BASE_CONTROL_CIRCUIT));
        alloyingCircuit(consumer, basePath + "circuits/", ExtraItems.SUPREME_CONTROL_CIRCUIT, EMExtraTagUtils.ALLOYS_THERMONUCLEAR, EMExtraTagUtils.CIRCUITS_ABSOLUTE, IngredientCreatorAccess.item().from(EMExtraItems.BASE_CONTROL_CIRCUIT));
        alloyingCircuit(consumer, basePath + "circuits/", ExtraItems.COSMIC_CONTROL_CIRCUIT, EMExtraTagUtils.ALLOYS_SHINING, EMExtraTagUtils.CIRCUITS_SUPREME, IngredientCreatorAccess.item().from(EMExtraItems.BASE_CONTROL_CIRCUIT));
        alloyingCircuit(consumer, basePath + "circuits/", ExtraItems.INFINITE_CONTROL_CIRCUIT, EMExtraTagUtils.ALLOYS_SPECTRUM, EMExtraTagUtils.CIRCUITS_COSMIC, IngredientCreatorAccess.item().from(EMExtraItems.BASE_CONTROL_CIRCUIT));

        alloyingCircuit(consumer, basePath + "circuits/", EMItems.OVERCLOCKED_CONTROL_CIRCUIT, EMTags.Items.ALLOYS_OVERCLOCKED, MekanismTags.Items.CIRCUITS_ULTIMATE, IngredientCreatorAccess.item().from(EMExtraItems.BASE_CONTROL_CIRCUIT));
        alloyingCircuit(consumer, basePath + "circuits/", EMItems.QUANTUM_CONTROL_CIRCUIT, EMTags.Items.ALLOYS_QUANTUM, EMTags.Items.CIRCUITS_OVERCLOCKED, IngredientCreatorAccess.item().from(EMExtraItems.BASE_CONTROL_CIRCUIT));
        alloyingCircuit(consumer, basePath + "circuits/", EMItems.DENSE_CONTROL_CIRCUIT, EMTags.Items.ALLOYS_DENSE, EMTags.Items.CIRCUITS_QUANTUM, IngredientCreatorAccess.item().from(EMExtraItems.BASE_CONTROL_CIRCUIT));
        alloyingCircuit(consumer, basePath + "circuits/", EMItems.MULTIVERSAL_CONTROL_CIRCUIT, EMTags.Items.ALLOYS_MULTIVERSAL, EMTags.Items.CIRCUITS_DENSE, IngredientCreatorAccess.item().from(EMExtraItems.BASE_CONTROL_CIRCUIT));
        alloyingCircuit(consumer, basePath + "circuits/", EMItems.CREATIVE_CONTROL_CIRCUIT, EMTags.Items.ALLOYS_CREATIVE, EMTags.Items.CIRCUITS_MULTIVERSAL, IngredientCreatorAccess.item().from(EMExtraItems.BASE_CONTROL_CIRCUIT));
    }

    private void transmitters(Consumer<FinishedRecipe> consumer, String type, BlockRegistryObject<?, ?> toTransmitter, ItemLike fromTransmitter, TagKey<Item> extraAlloy, TagKey<Item> evolvedAlloy) {
        EMExtraTier tier = EMExtraTransporterUtils.baseToEMExtraTier(Attribute.getTier(toTransmitter, ITier.class).getBaseTier());
        AlloyerRecipeBuilder.alloying(
                IngredientCreatorAccess.item().from(fromTransmitter, 8),
                IngredientCreatorAccess.item().from(extraAlloy, 1),
                IngredientCreatorAccess.item().from(evolvedAlloy, 1),
                toTransmitter.getItemStack(8)).build(consumer, EMExtras.rl("transmitter/" + type + "/" + tier.getLowerName()));
    }

    private void alloyingCircuit(Consumer<FinishedRecipe> consumer, String path, ItemRegistryObject<?> output, TagKey<Item> extraCircuit, TagKey<Item> tertiaryExtraCircuit, ItemStackIngredient mainCircuit) {
        AlloyerRecipeBuilder.alloying(
                mainCircuit,
                IngredientCreatorAccess.item().from(extraCircuit),
                IngredientCreatorAccess.item().from(tertiaryExtraCircuit),
                output.getItemStack()).build(consumer, EMExtras.rl(path + output.getName()));
    }
}
