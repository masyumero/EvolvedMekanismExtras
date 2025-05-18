package io.github.masyumero.emextras.common.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import io.github.masyumero.emextras.EMExtras;
import mekanism.common.config.IMekanismConfig;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.config.ConfigFileTypeHandler;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;
import java.util.function.Function;

public class EMExtraModConfig extends ModConfig {

    private static final EMExtraConfigFileTypeHandler EMEXTRA_TOML = new EMExtraConfigFileTypeHandler();

    private final IMekanismConfig mekanismEMExtraConfig;

    public EMExtraModConfig(ModContainer container, IMekanismConfig config) {
        super(config.getConfigType(), config.getConfigSpec(), container, EMExtras.MOD_NAME + "/" + config.getFileName() + ".toml");
        this.mekanismEMExtraConfig = config;
    }

    @Override
    public ConfigFileTypeHandler getHandler() {
        return EMEXTRA_TOML;
    }

    public void clearCache(ModConfigEvent event) {
        mekanismEMExtraConfig.clearCache(event instanceof ModConfigEvent.Unloading);
    }

    private static class EMExtraConfigFileTypeHandler extends ConfigFileTypeHandler {

        private static Path getPath(Path configBasePath) {
            //Intercept server config path reading for Mekanism configs and reroute it to the normal config directory
            if (configBasePath.endsWith("serverconfig")) {
                return FMLPaths.CONFIGDIR.get();
            }
            return configBasePath;
        }

        @Override
        public Function<ModConfig, CommentedFileConfig> reader(Path configBasePath) {
            return super.reader(getPath(configBasePath));
        }

        @Override
        public void unload(Path configBasePath, ModConfig config) {
            super.unload(getPath(configBasePath), config);
        }
    }
}
