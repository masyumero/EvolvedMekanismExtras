package io.github.masyumero.emextras;

import com.mojang.logging.LogUtils;
import io.github.masyumero.emextras.common.EMExtraTags;
import io.github.masyumero.emextras.common.config.LoadConfig;
import io.github.masyumero.emextras.common.integration.Addons;
import io.github.masyumero.emextras.common.integration.mekaf.regisrty.EMExtraAdvancedFactoryBlocks;
import io.github.masyumero.emextras.common.integration.mekaf.regisrty.EMExtraAdvancedFactoryContainerTypes;
import io.github.masyumero.emextras.common.integration.mekaf.regisrty.EMExtraAdvancedFactoryTileEntityTypes;
import io.github.masyumero.emextras.common.integration.mekmm.registry.EMExtraMoreMachineBlocks;
import io.github.masyumero.emextras.common.integration.mekmm.registry.EMExtraMoreMachineContainerTypes;
import io.github.masyumero.emextras.common.integration.mekmm.registry.EMExtraMoreMachineTileEntityTypes;
import io.github.masyumero.emextras.common.registry.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(EMExtras.MODID)
public class EMExtras {

    public static final String MODID = "emextras";
    public static final String MOD_NAME = "EvolvedMekanismExtras";

    public static final Logger LOGGER = LogUtils.getLogger();

    @SuppressWarnings("removal")
    public EMExtras() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        LoadConfig.registerConfig(ModLoadingContext.get());
        modEventBus.addListener(this::commonSetup);
        EMExtraItems.register(modEventBus);
        EMExtraBlocks.register(modEventBus);
        EMExtraTileEntityTypes.register(modEventBus);
        EMExtraContainerTypes.register(modEventBus);
        EMExtraTab.register(modEventBus);
        conditionalRegistry(modEventBus);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(EMExtraTags::init);
    }

    private static void conditionalRegistry(IEventBus modEventBus) {
        if (Addons.MEKMM.isLoaded()) {
            EMExtraMoreMachineBlocks.register(modEventBus);
            EMExtraMoreMachineContainerTypes.register(modEventBus);
            EMExtraMoreMachineTileEntityTypes.register(modEventBus);
            EMExtraAdvancedFactoryBlocks.register(modEventBus);
            EMExtraAdvancedFactoryContainerTypes.register(modEventBus);
            EMExtraAdvancedFactoryTileEntityTypes.register(modEventBus);
        }
    }

    @SuppressWarnings("removal")
    public static ResourceLocation rl(String path){
        return new ResourceLocation(EMExtras.MODID, path);
    }
}
