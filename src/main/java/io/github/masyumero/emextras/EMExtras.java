package io.github.masyumero.emextras;

import com.mojang.logging.LogUtils;
import io.github.masyumero.emextras.common.config.LoadConfig;
import io.github.masyumero.emextras.common.registry.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(EMExtras.MODID)
public class EMExtras {

    public static final String MODID = "emextras";
    public static final String MOD_NAME = "EvolvedMekanismExtras";

    private static final Logger LOGGER = LogUtils.getLogger();

    @SuppressWarnings("removal")
    public EMExtras() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        LoadConfig.registerConfig(ModLoadingContext.get());
        EMExtrasItem.register(modEventBus);
        EMExtrasBlock.register(modEventBus);
        EMExtrasTileEntityTypes.register(modEventBus);
        EMExtrasContainerTypes.register(modEventBus);
        EMExtraTab.register(modEventBus);
    }

    @SuppressWarnings("removal")
    public static ResourceLocation rl(String path){
        return new ResourceLocation(EMExtras.MODID, path);
    }
}
