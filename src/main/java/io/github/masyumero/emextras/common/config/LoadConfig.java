package io.github.masyumero.emextras.common.config;

import io.github.masyumero.emextras.EMExtras;
import mekanism.common.config.IMekanismConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.IConfigSpec;
import net.neoforged.fml.event.config.ModConfigEvent;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LoadConfig {
    private LoadConfig() {
    }

    private static final Map<IConfigSpec, IMekanismConfig> KNOWN_CONFIGS = new HashMap<>();
    public static final EMExtraUsageConfig emExtraUsageConfig = new EMExtraUsageConfig();
    public static final EMExtraStorageConfig emExtraStorageConfig = new EMExtraStorageConfig();
    public static final EMExtraTankCapacityConfig emExtraTankCapacityConfig = new EMExtraTankCapacityConfig();

    public static void registerConfig(ModContainer modContainer) {
        EMExtraConfigHelper.registerConfig(KNOWN_CONFIGS, modContainer, emExtraUsageConfig);
        EMExtraConfigHelper.registerConfig(KNOWN_CONFIGS, modContainer, emExtraStorageConfig);
        EMExtraConfigHelper.registerConfig(KNOWN_CONFIGS, modContainer, emExtraTankCapacityConfig);
    }

    public static void onConfigLoad(ModConfigEvent configEvent) {
        EMExtraConfigHelper.onConfigLoad(configEvent, EMExtras.MODID, KNOWN_CONFIGS);
    }

    public static Collection<IMekanismConfig> getConfigs() {
        return Collections.unmodifiableCollection(KNOWN_CONFIGS.values());
    }
}
