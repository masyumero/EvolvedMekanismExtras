package io.github.masyumero.emextras.common.config;

import mekanism.common.config.BaseMekanismConfig;
import mekanism.common.config.value.CachedLongValue;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public class EMExtraUsageConfig extends BaseMekanismConfig {

    private final ModConfigSpec configSpec;

    public final CachedLongValue alloyingFactory;

    public EMExtraUsageConfig() {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        builder.comment("Evolved Mekanism Extras Usage Config");
        builder.push("usage");
        alloyingFactory = CachedLongValue.definedMin(this, builder, EMExtraConfigTranslations.ENERGY_USAGE_ALLOYING_FACTORY, "alloyingFactory", 50L, 1);
        configSpec = builder.build();
    }

    @Override
    public String getFileName() {
        return "EvolvedMekanismExtras-Usage";
    }

    @Override
    public String getTranslation() {
        return "Usage Config";
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
