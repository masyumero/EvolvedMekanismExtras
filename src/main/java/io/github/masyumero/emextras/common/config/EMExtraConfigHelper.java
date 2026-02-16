package io.github.masyumero.emextras.common.config;

import io.github.masyumero.emextras.EMExtras;
import mekanism.common.config.IMekanismConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.IConfigSpec;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.fml.loading.FMLPaths;

import java.nio.file.Path;
import java.util.Map;

public class EMExtraConfigHelper {
    private EMExtraConfigHelper() {
    }

    public static final Path CONFIG_DIR = FMLPaths.getOrCreateGameRelativePath(FMLPaths.CONFIGDIR.get().resolve(EMExtras.MOD_NAME));

    public static void registerConfig(Map<IConfigSpec, IMekanismConfig> knownConfigs, ModContainer modContainer, IMekanismConfig config) {
        modContainer.registerConfig(config.getConfigType(), config.getConfigSpec(),   "EvolvedMekanismExtras/" + config.getFileName() + ".toml");
        knownConfigs.put(config.getConfigSpec(), config);
    }

    public static void onConfigLoad(ModConfigEvent event, String modid, Map<IConfigSpec, IMekanismConfig> knownConfigs) {
        // Note: We listen to both the initial load and the reload, to make sure that we fix any accidentally
        // cached values from calls before the initial loading
        ModConfig config = event.getConfig();
        // Make sure it is for the same modid as us
        if (config.getModId().equals(modid)) {
            IMekanismConfig mekanismConfig = knownConfigs.get(config.getSpec());
            if (mekanismConfig != null) {
                mekanismConfig.clearCache(event instanceof ModConfigEvent.Unloading);
            }
        }
    }
}
