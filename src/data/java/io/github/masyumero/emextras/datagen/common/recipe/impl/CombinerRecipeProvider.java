package io.github.masyumero.emextras.datagen.common.recipe.impl;

import com.jerry.mekanism_extras.common.registries.ExtraBlocks;
import fr.iglee42.evolvedmekanism.registries.EMBlocks;
import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.api.tier.EMExtraTier;
import io.github.masyumero.emextras.common.registry.EMExtrasBlock;
import io.github.masyumero.emextras.common.util.EMExtraTransporterUtils;
import io.github.masyumero.emextras.datagen.common.recipe.ISubRecipeProvider;
import mekanism.api.datagen.recipe.builder.CombinerRecipeBuilder;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import mekanism.api.tier.ITier;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.registration.impl.BlockRegistryObject;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

public class CombinerRecipeProvider implements ISubRecipeProvider {

    @Override
    public void addRecipes(Consumer<FinishedRecipe> consumer) {
        String basePath = "combining/";
        combinedTransmitter(consumer, basePath, "logistical_transporter", EMExtrasBlock.ABSOLUTE_OVERCLOCKED_LOGISTICAL_TRANSPORTER, ExtraBlocks.ABSOLUTE_LOGISTICAL_TRANSPORTER, EMBlocks.OVERCLOCKED_LOGISTICAL_TRANSPORTER);
        combinedTransmitter(consumer, basePath, "mechanical_pipe", EMExtrasBlock.ABSOLUTE_OVERCLOCKED_MECHANICAL_PIPE, ExtraBlocks.ABSOLUTE_MECHANICAL_PIPE, EMBlocks.OVERCLOCKED_MECHANICAL_PIPE);
        combinedTransmitter(consumer, basePath, "pressurized_tube", EMExtrasBlock.ABSOLUTE_OVERCLOCKED_PRESSURIZED_TUBE, ExtraBlocks.ABSOLUTE_PRESSURIZED_TUBE, EMBlocks.OVERCLOCKED_PRESSURIZED_TUBE);
        combinedTransmitter(consumer, basePath, "thermodynamic_conductor", EMExtrasBlock.ABSOLUTE_OVERCLOCKED_THERMODYNAMIC_CONDUCTOR, ExtraBlocks.ABSOLUTE_THERMODYNAMIC_CONDUCTOR, EMBlocks.OVERCLOCKED_THERMODYNAMIC_CONDUCTOR);
        combinedTransmitter(consumer, basePath, "universal_cable", EMExtrasBlock.ABSOLUTE_OVERCLOCKED_UNIVERSAL_CABLE, ExtraBlocks.ABSOLUTE_UNIVERSAL_CABLE, EMBlocks.OVERCLOCKED_UNIVERSAL_CABLE);

        combinedTransmitter(consumer, basePath, "logistical_transporter", EMExtrasBlock.SUPREME_QUANTUM_LOGISTICAL_TRANSPORTER, ExtraBlocks.SUPREME_LOGISTICAL_TRANSPORTER, EMBlocks.QUANTUM_LOGISTICAL_TRANSPORTER);
        combinedTransmitter(consumer, basePath, "mechanical_pipe", EMExtrasBlock.SUPREME_QUANTUM_MECHANICAL_PIPE, ExtraBlocks.SUPREME_MECHANICAL_PIPE, EMBlocks.QUANTUM_MECHANICAL_PIPE);
        combinedTransmitter(consumer, basePath, "pressurized_tube", EMExtrasBlock.SUPREME_QUANTUM_PRESSURIZED_TUBE, ExtraBlocks.SUPREME_PRESSURIZED_TUBE, EMBlocks.QUANTUM_PRESSURIZED_TUBE);
        combinedTransmitter(consumer, basePath, "thermodynamic_conductor", EMExtrasBlock.SUPREME_QUANTUM_THERMODYNAMIC_CONDUCTOR, ExtraBlocks.SUPREME_THERMODYNAMIC_CONDUCTOR, EMBlocks.QUANTUM_THERMODYNAMIC_CONDUCTOR);
        combinedTransmitter(consumer, basePath, "universal_cable", EMExtrasBlock.SUPREME_QUANTUM_UNIVERSAL_CABLE, ExtraBlocks.SUPREME_UNIVERSAL_CABLE, EMBlocks.QUANTUM_UNIVERSAL_CABLE);

        combinedTransmitter(consumer, basePath, "logistical_transporter", EMExtrasBlock.COSMIC_DENSE_LOGISTICAL_TRANSPORTER, ExtraBlocks.COSMIC_LOGISTICAL_TRANSPORTER, EMBlocks.DENSE_LOGISTICAL_TRANSPORTER);
        combinedTransmitter(consumer, basePath, "mechanical_pipe", EMExtrasBlock.COSMIC_DENSE_MECHANICAL_PIPE, ExtraBlocks.COSMIC_MECHANICAL_PIPE, EMBlocks.DENSE_MECHANICAL_PIPE);
        combinedTransmitter(consumer, basePath, "pressurized_tube", EMExtrasBlock.COSMIC_DENSE_PRESSURIZED_TUBE, ExtraBlocks.COSMIC_PRESSURIZED_TUBE, EMBlocks.DENSE_PRESSURIZED_TUBE);
        combinedTransmitter(consumer, basePath, "thermodynamic_conductor", EMExtrasBlock.COSMIC_DENSE_THERMODYNAMIC_CONDUCTOR, ExtraBlocks.COSMIC_THERMODYNAMIC_CONDUCTOR, EMBlocks.DENSE_THERMODYNAMIC_CONDUCTOR);
        combinedTransmitter(consumer, basePath, "universal_cable", EMExtrasBlock.COSMIC_DENSE_UNIVERSAL_CABLE, ExtraBlocks.COSMIC_UNIVERSAL_CABLE, EMBlocks.DENSE_UNIVERSAL_CABLE);

        combinedTransmitter(consumer, basePath, "logistical_transporter", EMExtrasBlock.INFINITE_MULTIVERSAL_LOGISTICAL_TRANSPORTER, ExtraBlocks.INFINITE_LOGISTICAL_TRANSPORTER, EMBlocks.MULTIVERSAL_LOGISTICAL_TRANSPORTER);
        combinedTransmitter(consumer, basePath, "mechanical_pipe", EMExtrasBlock.INFINITE_MULTIVERSAL_MECHANICAL_PIPE, ExtraBlocks.INFINITE_MECHANICAL_PIPE, EMBlocks.MULTIVERSAL_MECHANICAL_PIPE);
        combinedTransmitter(consumer, basePath, "pressurized_tube", EMExtrasBlock.INFINITE_MULTIVERSAL_PRESSURIZED_TUBE, ExtraBlocks.INFINITE_PRESSURIZED_TUBE, EMBlocks.MULTIVERSAL_PRESSURIZED_TUBE);
        combinedTransmitter(consumer, basePath, "thermodynamic_conductor", EMExtrasBlock.INFINITE_MULTIVERSAL_THERMODYNAMIC_CONDUCTOR, ExtraBlocks.INFINITE_THERMODYNAMIC_CONDUCTOR, EMBlocks.MULTIVERSAL_THERMODYNAMIC_CONDUCTOR);
        combinedTransmitter(consumer, basePath, "universal_cable", EMExtrasBlock.INFINITE_MULTIVERSAL_UNIVERSAL_CABLE, ExtraBlocks.INFINITE_UNIVERSAL_CABLE, EMBlocks.MULTIVERSAL_UNIVERSAL_CABLE);
    }

    private void combinedTransmitter(Consumer<FinishedRecipe> consumer, String basePath, String type, BlockRegistryObject<?, ?> result, ItemLike extraTransmitter, ItemLike evolvedTransmitter) {
        EMExtraTier tier = EMExtraTransporterUtils.baseToEMExtraTier(Attribute.getTier(result, ITier.class).getBaseTier());
        CombinerRecipeBuilder.combining(
                IngredientCreatorAccess.item().from(extraTransmitter, 4),
                IngredientCreatorAccess.item().from(evolvedTransmitter, 4),
                result.getItemStack(8)).build(consumer, EMExtras.rl(basePath + "transmitter/" + type + "/" + tier.getLowerName()));
    }
}
