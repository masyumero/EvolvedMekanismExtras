package io.github.masyumero.emextras;

import com.mojang.logging.LogUtils;
import io.github.masyumero.emextras.common.EMExtraTags;
import io.github.masyumero.emextras.common.config.LoadConfig;
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
        EMExtrasItem.register(modEventBus);
        EMExtrasBlock.register(modEventBus);
        EMExtrasTileEntityTypes.register(modEventBus);
        EMExtrasContainerTypes.register(modEventBus);
        EMExtraTab.register(modEventBus);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(EMExtraTags::init);
    }

    @SuppressWarnings("removal")
    public static ResourceLocation rl(String path){
        return new ResourceLocation(EMExtras.MODID, path);
    }
}
