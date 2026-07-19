package io.github.masyumero.emextras.common.config;

import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModLoadingContext;

public class LoadConfig {
    private LoadConfig() {
    }

    public static final EMExtraConfig EMEXTRA_CONFIG = new EMExtraConfig();
    public static final EMExtraUsageConfig EMEXTRA_USAGE_CONFIG = new EMExtraUsageConfig();
    public static final EMExtraStorageConfig EMEXTRA_STORAGE_CONFIG = new EMExtraStorageConfig();
    public static final EMExtraMoreCapacityConfig EMEXTRA_MORE_CAPACITY_CONFIG = new EMExtraMoreCapacityConfig();

    @SuppressWarnings("removal")
    public static void registerConfig(ModLoadingContext modLoadingContext) {
        ModContainer modContainer = modLoadingContext.getActiveContainer();
        EMExtraConfigHelper.registerConfig(modContainer, EMEXTRA_CONFIG);
        EMExtraConfigHelper.registerConfig(modContainer, EMEXTRA_USAGE_CONFIG);
        EMExtraConfigHelper.registerConfig(modContainer, EMEXTRA_STORAGE_CONFIG);
        EMExtraConfigHelper.registerConfig(modContainer, EMEXTRA_MORE_CAPACITY_CONFIG);
    }
}
