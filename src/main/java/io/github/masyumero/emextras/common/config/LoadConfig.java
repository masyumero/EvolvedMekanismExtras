package io.github.masyumero.emextras.common.config;

import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModLoadingContext;

public class LoadConfig {
    private LoadConfig() {
    }

    public static final EMExtraUsageConfig emExtraUsageConfig = new EMExtraUsageConfig();
    public static final EMExtraStorageConfig emExtraStorageConfig = new EMExtraStorageConfig();

    public static void registerConfig(ModLoadingContext modLoadingContext) {
        ModContainer modContainer = modLoadingContext.getActiveContainer();
        EMExtraConfigHelper.registerConfig(modContainer, emExtraUsageConfig);
        EMExtraConfigHelper.registerConfig(modContainer, emExtraStorageConfig);
    }
}
