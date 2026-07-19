package io.github.masyumero.emextras.client.jei;

import com.jerry.mekanism_extras.client.jei.ExtraCatalystRegistryHelper;
import com.jerry.mekanism_extras.common.tier.ExtraFactoryTier;
import com.jerry.mekanism_extras.common.util.ExtraEnumUtils;

import io.github.masyumero.emextras.common.integration.Addons;
import io.github.masyumero.emextras.common.registry.EMExtraBlocks;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.common.content.blocktype.EMExtraFactoryType;
import io.github.masyumero.emextras.common.util.EMExtraEnumUtils;

import fr.iglee42.evolvedmekanism.jei.EMJEI;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import mekanism.client.jei.MekanismJEIRecipeType;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
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
        for (EMExtraFactoryTier tier : EMExtraEnumUtils.EMEXTRA_FACTORY_TIERS) {
            for (EMExtraFactoryType type : EMExtraEnumUtils.EMEXTRA_FACTORY_TYPES) {
                if (type == EMExtraFactoryType.ALLOYING) {
                    ExtraCatalystRegistryHelper.register(registry, type.getRecipeType(type), EMExtraBlocks.getEMExtraFactory(tier, type));
                } else if (type == EMExtraFactoryType.INFUSING) {
                    ExtraCatalystRegistryHelper.register(registry, type.getRecipeType(type), EMExtraBlocks.getEMExtraFactory(tier, type));
                    ExtraCatalystRegistryHelper.register(registry, MekanismJEIRecipeType.INFUSION_CONVERSION, EMExtraBlocks.getEMExtraFactory(tier, type));
                } else if (type == EMExtraFactoryType.PURIFYING) {
                    ExtraCatalystRegistryHelper.register(registry, type.getRecipeType(type), EMExtraBlocks.getEMExtraFactory(tier, type));
                    ExtraCatalystRegistryHelper.register(registry, MekanismJEIRecipeType.GAS_CONVERSION, EMExtraBlocks.getEMExtraFactory(tier, type));
                } else if (type == EMExtraFactoryType.COMPRESSING) {
                    ExtraCatalystRegistryHelper.register(registry, type.getRecipeType(type), EMExtraBlocks.getEMExtraFactory(tier, type));
                    ExtraCatalystRegistryHelper.register(registry, MekanismJEIRecipeType.GAS_CONVERSION, EMExtraBlocks.getEMExtraFactory(tier, type));
                } else if (type == EMExtraFactoryType.INJECTING) {
                    ExtraCatalystRegistryHelper.register(registry, type.getRecipeType(type), EMExtraBlocks.getEMExtraFactory(tier, type));
                    ExtraCatalystRegistryHelper.register(registry, MekanismJEIRecipeType.GAS_CONVERSION, EMExtraBlocks.getEMExtraFactory(tier, type));
                } else if (type != EMExtraFactoryType.ADVANCED_ALLOYING){
                    ExtraCatalystRegistryHelper.register(registry, type.getRecipeType(type), EMExtraBlocks.getEMExtraFactory(tier, type));
                }
            }
        }
        for (ExtraFactoryTier tier : ExtraEnumUtils.EXTRA_FACTORY_TIERS) {
            ExtraCatalystRegistryHelper.register(registry, EMJEI.ALLOYING, EMExtraBlocks.getExtraFactory(tier, EMFactoryType.ALLOYING));
        }

        if (Addons.MEKMM.isLoaded()) {
            EMExtraAFCatalystRegistryHelper.register(registry);
            EMExtraMMCatalystRegistryHelper.register(registry);
        }
    }
}

