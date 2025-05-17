package io.github.masyumero.emextras.client.jei;

import com.jerry.mekanism_extras.client.jei.ExtraCatalystRegistryHelper;
import com.jerry.mekanism_extras.common.tier.AdvancedFactoryTier;
import fr.iglee42.evolvedmekanism.jei.EMJEI;
import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.common.content.blocktype.EMExtraFactoryType;
import io.github.masyumero.emextras.common.registry.EMExtrasBlock;
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
        ExtraCatalystRegistryHelper.register(registry, EMJEI.ALLOYING, EMExtrasBlock.getEMExtraFactory(AdvancedFactoryTier.ABSOLUTE, EMExtraFactoryType.ALLOYING), EMExtrasBlock.getEMExtraFactory(AdvancedFactoryTier.SUPREME, EMExtraFactoryType.ALLOYING),
                EMExtrasBlock.getEMExtraFactory(AdvancedFactoryTier.COSMIC, EMExtraFactoryType.ALLOYING), EMExtrasBlock.getEMExtraFactory(AdvancedFactoryTier.INFINITE, EMExtraFactoryType.ALLOYING));

    }
}
