package io.github.masyumero.emextras.common.config;

import mekanism.api.math.FloatingLong;
import mekanism.common.config.BaseMekanismConfig;
import mekanism.common.config.value.CachedFloatingLongValue;
import mekanism.common.config.value.CachedLongValue;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

public class EMExtraUsageConfig extends BaseMekanismConfig {

    private final ForgeConfigSpec configSpec;

    public final CachedFloatingLongValue alloyer;

    public EMExtraUsageConfig() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.comment("Evolved Mekanism Extras Usage Config");
        builder.push("usage");
        alloyer = CachedFloatingLongValue.define(this, builder, "Energy per operation tick (Joules).", "alloyer", FloatingLong.createConst(50));
        configSpec = builder.build();
    }

    @Override
    public String getFileName() {
        return "EvolvedMekanismExtras-Usage";
    }

    @Override
    public ForgeConfigSpec getConfigSpec() {
        return configSpec;
    }

    @Override
    public ModConfig.Type getConfigType() {
        return ModConfig.Type.SERVER;
    }
}
