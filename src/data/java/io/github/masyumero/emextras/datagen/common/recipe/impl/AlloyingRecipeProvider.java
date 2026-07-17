package io.github.masyumero.emextras.datagen.common.recipe.impl;

import com.jerry.mekanism_extras.common.registries.ExtraItems;
import fr.iglee42.evolvedmekanism.registries.EMItems;
import fr.iglee42.evolvedmekanism.registries.EMTags;
import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.api.datagen.recipe.builder.AlloyerRecipeBuilder;
import io.github.masyumero.emextras.api.tier.EMExtraTier;
import io.github.masyumero.emextras.common.EMExtraTags;
import io.github.masyumero.emextras.common.registry.EMExtrasBlock;
import io.github.masyumero.emextras.common.registry.EMExtrasItem;
import io.github.masyumero.emextras.common.util.EMExtraTagUtils;
import io.github.masyumero.emextras.common.util.EMExtraTransporterUtils;
import io.github.masyumero.emextras.datagen.common.recipe.ISubRecipeProvider;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import mekanism.api.tier.ITier;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.registries.MekanismBlocks;
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
                EMExtrasItem.ABSOLUTE_OVERCLOCKED_TIER_INSTALLER.getItemStack()).build(consumer, EMExtras.rl(basePath + "tier_installer/absolute_overclocked"));
        AlloyerRecipeBuilder.alloying(
                IngredientCreatorAccess.item().from(EMExtraTags.Items.SUPREME_QUANTUM_CONTROL_CIRCUIT, 2),
                IngredientCreatorAccess.item().from(ExtraItems.SUPREME_TIER_INSTALLER),
                IngredientCreatorAccess.item().from(EMItems.QUANTUM_TIER_INSTALLER),
                EMExtrasItem.SUPREME_QUANTUM_TIER_INSTALLER.getItemStack()).build(consumer, EMExtras.rl(basePath + "tier_installer/supreme_quantum"));
        AlloyerRecipeBuilder.alloying(
                IngredientCreatorAccess.item().from(EMExtraTags.Items.COSMIC_DENSE_CONTROL_CIRCUIT, 2),
                IngredientCreatorAccess.item().from(ExtraItems.COSMIC_TIER_INSTALLER),
                IngredientCreatorAccess.item().from(EMItems.DENSE_TIER_INSTALLER),
                EMExtrasItem.COSMIC_DENSE_TIER_INSTALLER.getItemStack()).build(consumer, EMExtras.rl(basePath + "tier_installer/cosmic_dense"));
        AlloyerRecipeBuilder.alloying(
                IngredientCreatorAccess.item().from(EMExtraTags.Items.INFINITE_MULTIVERSAL_CONTROL_CIRCUIT, 2),
                IngredientCreatorAccess.item().from(ExtraItems.INFINITE_TIER_INSTALLER),
                IngredientCreatorAccess.item().from(EMItems.MULTIVERSAL_TIER_INSTALLER),
                EMExtrasItem.INFINITE_MULTIVERSAL_TIER_INSTALLER.getItemStack()).build(consumer, EMExtras.rl(basePath + "tier_installer/infinite_multiversal"));
        // transmitters
        transmitters(consumer, "logistical_transporter", EMExtrasBlock.ABSOLUTE_OVERCLOCKED_LOGISTICAL_TRANSPORTER, MekanismBlocks.ULTIMATE_LOGISTICAL_TRANSPORTER, EMExtraTagUtils.ALLOYS_RADIANCE, EMTags.Items.ALLOYS_OVERCLOCKED);
        transmitters(consumer, "mechanical_pipe", EMExtrasBlock.ABSOLUTE_OVERCLOCKED_MECHANICAL_PIPE, MekanismBlocks.ULTIMATE_MECHANICAL_PIPE, EMExtraTagUtils.ALLOYS_RADIANCE, EMTags.Items.ALLOYS_OVERCLOCKED);
        transmitters(consumer, "pressurized_tube", EMExtrasBlock.ABSOLUTE_OVERCLOCKED_PRESSURIZED_TUBE, MekanismBlocks.ULTIMATE_PRESSURIZED_TUBE, EMExtraTagUtils.ALLOYS_RADIANCE, EMTags.Items.ALLOYS_OVERCLOCKED);
        transmitters(consumer, "thermodynamic_conductor", EMExtrasBlock.ABSOLUTE_OVERCLOCKED_THERMODYNAMIC_CONDUCTOR, MekanismBlocks.ULTIMATE_THERMODYNAMIC_CONDUCTOR, EMExtraTagUtils.ALLOYS_RADIANCE, EMTags.Items.ALLOYS_OVERCLOCKED);
        transmitters(consumer, "universal_cable", EMExtrasBlock.ABSOLUTE_OVERCLOCKED_UNIVERSAL_CABLE, MekanismBlocks.ULTIMATE_UNIVERSAL_CABLE, EMExtraTagUtils.ALLOYS_RADIANCE, EMTags.Items.ALLOYS_OVERCLOCKED);

        transmitters(consumer, "logistical_transporter", EMExtrasBlock.SUPREME_QUANTUM_LOGISTICAL_TRANSPORTER, EMExtrasBlock.ABSOLUTE_OVERCLOCKED_LOGISTICAL_TRANSPORTER, EMExtraTagUtils.ALLOYS_THERMONUCLEAR, EMTags.Items.ALLOYS_QUANTUM);
        transmitters(consumer, "mechanical_pipe", EMExtrasBlock.SUPREME_QUANTUM_MECHANICAL_PIPE, EMExtrasBlock.ABSOLUTE_OVERCLOCKED_MECHANICAL_PIPE, EMExtraTagUtils.ALLOYS_THERMONUCLEAR, EMTags.Items.ALLOYS_QUANTUM);
        transmitters(consumer, "pressurized_tube", EMExtrasBlock.SUPREME_QUANTUM_PRESSURIZED_TUBE, EMExtrasBlock.ABSOLUTE_OVERCLOCKED_PRESSURIZED_TUBE, EMExtraTagUtils.ALLOYS_THERMONUCLEAR, EMTags.Items.ALLOYS_QUANTUM);
        transmitters(consumer, "thermodynamic_conductor", EMExtrasBlock.SUPREME_QUANTUM_THERMODYNAMIC_CONDUCTOR, EMExtrasBlock.ABSOLUTE_OVERCLOCKED_THERMODYNAMIC_CONDUCTOR, EMExtraTagUtils.ALLOYS_THERMONUCLEAR, EMTags.Items.ALLOYS_QUANTUM);
        transmitters(consumer, "universal_cable", EMExtrasBlock.SUPREME_QUANTUM_UNIVERSAL_CABLE, EMExtrasBlock.ABSOLUTE_OVERCLOCKED_UNIVERSAL_CABLE, EMExtraTagUtils.ALLOYS_THERMONUCLEAR, EMTags.Items.ALLOYS_QUANTUM);

        transmitters(consumer, "logistical_transporter", EMExtrasBlock.COSMIC_DENSE_LOGISTICAL_TRANSPORTER, EMExtrasBlock.SUPREME_QUANTUM_LOGISTICAL_TRANSPORTER, EMExtraTagUtils.ALLOYS_SHINING, EMTags.Items.ALLOYS_DENSE);
        transmitters(consumer, "mechanical_pipe", EMExtrasBlock.COSMIC_DENSE_MECHANICAL_PIPE, EMExtrasBlock.SUPREME_QUANTUM_MECHANICAL_PIPE, EMExtraTagUtils.ALLOYS_SHINING, EMTags.Items.ALLOYS_DENSE);
        transmitters(consumer, "pressurized_tube", EMExtrasBlock.COSMIC_DENSE_PRESSURIZED_TUBE, EMExtrasBlock.SUPREME_QUANTUM_PRESSURIZED_TUBE, EMExtraTagUtils.ALLOYS_SHINING, EMTags.Items.ALLOYS_DENSE);
        transmitters(consumer, "thermodynamic_conductor", EMExtrasBlock.COSMIC_DENSE_THERMODYNAMIC_CONDUCTOR, EMExtrasBlock.SUPREME_QUANTUM_THERMODYNAMIC_CONDUCTOR, EMExtraTagUtils.ALLOYS_SHINING, EMTags.Items.ALLOYS_DENSE);
        transmitters(consumer, "universal_cable", EMExtrasBlock.COSMIC_DENSE_UNIVERSAL_CABLE, EMExtrasBlock.SUPREME_QUANTUM_UNIVERSAL_CABLE, EMExtraTagUtils.ALLOYS_SHINING, EMTags.Items.ALLOYS_DENSE);

        transmitters(consumer, "logistical_transporter", EMExtrasBlock.INFINITE_MULTIVERSAL_LOGISTICAL_TRANSPORTER, EMExtrasBlock.COSMIC_DENSE_LOGISTICAL_TRANSPORTER, EMExtraTagUtils.ALLOYS_SPECTRUM, EMTags.Items.ALLOYS_MULTIVERSAL);
        transmitters(consumer, "mechanical_pipe", EMExtrasBlock.INFINITE_MULTIVERSAL_MECHANICAL_PIPE, EMExtrasBlock.COSMIC_DENSE_MECHANICAL_PIPE, EMExtraTagUtils.ALLOYS_SPECTRUM, EMTags.Items.ALLOYS_MULTIVERSAL);
        transmitters(consumer, "pressurized_tube", EMExtrasBlock.INFINITE_MULTIVERSAL_PRESSURIZED_TUBE, EMExtrasBlock.COSMIC_DENSE_PRESSURIZED_TUBE, EMExtraTagUtils.ALLOYS_SPECTRUM, EMTags.Items.ALLOYS_MULTIVERSAL);
        transmitters(consumer, "thermodynamic_conductor", EMExtrasBlock.INFINITE_MULTIVERSAL_THERMODYNAMIC_CONDUCTOR, EMExtrasBlock.COSMIC_DENSE_THERMODYNAMIC_CONDUCTOR, EMExtraTagUtils.ALLOYS_SPECTRUM, EMTags.Items.ALLOYS_MULTIVERSAL);
        transmitters(consumer, "universal_cable", EMExtrasBlock.INFINITE_MULTIVERSAL_UNIVERSAL_CABLE, EMExtrasBlock.COSMIC_DENSE_UNIVERSAL_CABLE, EMExtraTagUtils.ALLOYS_SPECTRUM, EMTags.Items.ALLOYS_MULTIVERSAL);
    }

    private void transmitters(Consumer<FinishedRecipe> consumer, String type, BlockRegistryObject<?, ?> toTransmitter, ItemLike fromTransmitter, TagKey<Item> extraAlloy, TagKey<Item> evolvedAlloy) {
        EMExtraTier tier = EMExtraTransporterUtils.baseToEMExtraTier(Attribute.getTier(toTransmitter, ITier.class).getBaseTier());
        AlloyerRecipeBuilder.alloying(
                IngredientCreatorAccess.item().from(fromTransmitter, 8),
                IngredientCreatorAccess.item().from(extraAlloy, 1),
                IngredientCreatorAccess.item().from(evolvedAlloy, 1),
                toTransmitter.getItemStack(8)).build(consumer, EMExtras.rl("transmitter/" + type + "/" + tier.getLowerName()));
    }
}
