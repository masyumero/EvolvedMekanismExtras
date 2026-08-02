package io.github.masyumero.emextras.client.jei;

import com.jerry.mekanism_extras.common.tier.ExtraFactoryTier;
import com.jerry.mekanism_extras.common.util.ExtraEnumUtils;

import io.github.masyumero.emextras.common.integration.Addons;
import io.github.masyumero.emextras.common.registry.EMExtraBlocks;
import io.github.masyumero.emextras.EMExtras;

import fr.iglee42.evolvedmekanism.jei.EMJEI;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import mekanism.client.jei.MekanismJEIRecipeType;
import mekanism.common.registries.MekanismBlocks;
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
        return EMExtras.rl("jei_plugin");
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registry) {
        EMExtraCatalystRegistryHelper.register(registry, MekanismBlocks.ENRICHMENT_CHAMBER);
        EMExtraCatalystRegistryHelper.register(registry, MekanismBlocks.CRUSHER);
        EMExtraCatalystRegistryHelper.register(registry, MekanismBlocks.COMBINER);
        EMExtraCatalystRegistryHelper.register(registry, MekanismBlocks.PURIFICATION_CHAMBER, MekanismJEIRecipeType.GAS_CONVERSION);
        EMExtraCatalystRegistryHelper.register(registry, MekanismBlocks.OSMIUM_COMPRESSOR, MekanismJEIRecipeType.GAS_CONVERSION);
        EMExtraCatalystRegistryHelper.register(registry, MekanismBlocks.CHEMICAL_INJECTION_CHAMBER, MekanismJEIRecipeType.GAS_CONVERSION);
        EMExtraCatalystRegistryHelper.register(registry, MekanismBlocks.PRECISION_SAWMILL);
        EMExtraCatalystRegistryHelper.register(registry, MekanismBlocks.METALLURGIC_INFUSER, MekanismJEIRecipeType.INFUSION_CONVERSION);
        EMExtraCatalystRegistryHelper.registerRecipeItem(registry, MekanismBlocks.ENERGIZED_SMELTER, MekanismJEIRecipeType.SMELTING, RecipeTypes.SMELTING);

        for (ExtraFactoryTier tier : ExtraEnumUtils.EXTRA_FACTORY_TIERS) {
            EMExtraCatalystRegistryHelper.register(registry, EMJEI.ALLOYING, EMExtraBlocks.getExtraFactory(tier, EMFactoryType.ALLOYING));
        }

        if (Addons.MEKMM.isLoaded()) {
            EMExtraAFCatalystRegistryHelper.register(registry);
            EMExtraMMCatalystRegistryHelper.register(registry);
        }
    }
}

