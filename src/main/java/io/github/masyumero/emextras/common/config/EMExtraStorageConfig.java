package io.github.masyumero.emextras.common.config;

import mekanism.common.config.BaseMekanismConfig;
import mekanism.common.config.value.CachedLongValue;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public class EMExtraStorageConfig extends BaseMekanismConfig {

    private final ModConfigSpec configSpec;

    public final CachedLongValue alloyingFactory;

    public EMExtraStorageConfig() {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        builder.comment("Evolved Mekanism Extras Storage Config");
        builder.push("storage");
        alloyingFactory = CachedLongValue.definedMin(this, builder, EMExtraConfigTranslations.ENERGY_STORAGE_ALLOYING_FACTORY, "alloyingFactory", 40_000L, 1);
        configSpec = builder.build();
    }

    @Override
    public String getFileName() {
        return "EvolvedMekanismExtras-Storage";
    }

    @Override
    public String getTranslation() {
        return "Storage Config";
    }

    @Override
    public ModConfigSpec getConfigSpec() {
        return configSpec;
    }

    @Override
    public ModConfig.Type getConfigType() {
        return ModConfig.Type.SERVER;
    }
}
