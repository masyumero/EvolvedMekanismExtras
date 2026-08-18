package io.github.masyumero.emextras.client.recipe_viewer.jei;

import com.jerry.mekmm.client.recipe_viewer.MoreMachineRecipeViewerRecipeType;
import fr.iglee42.evolvedmekanism.jei.JEIRecipeTypes;
import io.github.masyumero.emextras.EMExtras;
import mekanism.client.recipe_viewer.jei.MekanismJEI;
import mekanism.client.recipe_viewer.type.RecipeViewerRecipeType;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

@JeiPlugin
public class EMExtraJEI implements IModPlugin {

    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(EMExtras.MODID, "jei_plugin");
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registry) {
        if (!MekanismJEI.shouldLoad()) {
            return;
        }

        EMExtraCatalystRegistryHelper.register(registry,  RecipeViewerRecipeType.ENRICHING, RecipeViewerRecipeType.CRUSHING, RecipeViewerRecipeType.COMBINING,
                RecipeViewerRecipeType.PURIFYING, RecipeViewerRecipeType.COMPRESSING, RecipeViewerRecipeType.INJECTING, RecipeViewerRecipeType.SAWING,
                RecipeViewerRecipeType.METALLURGIC_INFUSING, RecipeViewerRecipeType.SMELTING, RecipeViewerRecipeType.CHEMICAL_CONVERSION, JEIRecipeTypes.ALLOYING);

        EMExtraCatalystRegistryHelper.register(registry, RecipeTypes.SMELTING, RecipeViewerRecipeType.VANILLA_SMELTING.workstations());

        if (EMExtras.hooks.mekmm.isLoaded()) {
            EMExtraMMCatalystRegistryHelper.register(registry, MoreMachineRecipeViewerRecipeType.RECYCLER, MoreMachineRecipeViewerRecipeType.PLANTING_STATION, MoreMachineRecipeViewerRecipeType.REPLICATOR, MoreMachineRecipeViewerRecipeType.PRESSING,
                    MoreMachineRecipeViewerRecipeType.FLUID_REPLICATOR, MoreMachineRecipeViewerRecipeType.CHEMICAL_REPLICATOR, MoreMachineRecipeViewerRecipeType.STAMPING, MoreMachineRecipeViewerRecipeType.LATHE, MoreMachineRecipeViewerRecipeType.ROLLING_MILL);
            EMExtraAFCatalystRegistryHelper.register(registry, RecipeViewerRecipeType.OXIDIZING, RecipeViewerRecipeType.DISSOLUTION, RecipeViewerRecipeType.WASHING, RecipeViewerRecipeType.CRYSTALLIZING,
                    RecipeViewerRecipeType.REACTION, RecipeViewerRecipeType.CENTRIFUGING, RecipeViewerRecipeType.NUTRITIONAL_LIQUIFICATION, RecipeViewerRecipeType.PIGMENT_EXTRACTING, RecipeViewerRecipeType.PAINTING);
        }
    }
}

