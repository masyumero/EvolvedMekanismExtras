package io.github.masyumero.emextras.common.config;

import io.github.masyumero.emextras.EMExtras;
import mekanism.common.config.IMekanismConfig;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class EMExtraConfigHelper {
    private EMExtraConfigHelper() {
    }

    public static final Path CONFIG_DIR = FMLPaths.getOrCreateGameRelativePath(FMLPaths.CONFIGDIR.get().resolve(EMExtras.MOD_NAME));

    public static void registerConfig(ModContainer modContainer, IMekanismConfig config) {
        EMExtraModConfig modConfig = new EMExtraModConfig(modContainer, config);
        if (config.addToContainer()) {
            modContainer.addConfig(modConfig);
        }
    }
}
