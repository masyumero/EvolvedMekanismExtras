package io.github.masyumero.emextras.common.config;

import mekanism.common.config.BaseMekanismConfig;
import mekanism.common.config.value.CachedLongValue;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

public class EMExtraTankCapacityConfig extends BaseMekanismConfig {

    private final ForgeConfigSpec configSpec;
    public final CachedLongValue EMExtraAbsoluteOverclockedFactories;
    public final CachedLongValue EMExtraSupremeQuantumOsmiumFactories;
    public final CachedLongValue EMExtraCosmicDenseOsmiumFactories;
    public final CachedLongValue EMExtraInfiniteMultiversalFactories;
    public final CachedLongValue EMExtraAbsoluteOverclockedInfusingFactory;
    public final CachedLongValue EMExtraSupremeQuantumInfusingFactory;
    public final CachedLongValue EMExtraCosmicDenseInfusingFactory;
    public final CachedLongValue EMExtraInfiniteMultiversalInfusingFactory;
    public EMExtraTankCapacityConfig() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.comment("Evolved Mekanism Extras Tank Capacity Config");
        builder.push("tank");
        EMExtraAbsoluteOverclockedFactories = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB).").defineInRange("AbsoluteOverclockedFactories",30240,1,Long.MAX_VALUE));
        EMExtraSupremeQuantumOsmiumFactories = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB).").defineInRange("SupremeQuantumFactories",41160,1,Long.MAX_VALUE));
        EMExtraCosmicDenseOsmiumFactories = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB).").defineInRange("CosmicDenseFactories",53760,1,Long.MAX_VALUE));
        EMExtraInfiniteMultiversalFactories = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB).").defineInRange("InfiniteMultiversalFactories",68040,1,Long.MAX_VALUE));
        EMExtraAbsoluteOverclockedInfusingFactory = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB).").defineInRange("AbsoluteOverclockedInfusingFactory",144000,1,Long.MAX_VALUE));
        EMExtraSupremeQuantumInfusingFactory = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB).").defineInRange("SupremeQuantumInfusingFactory",196000,1,Long.MAX_VALUE));
        EMExtraCosmicDenseInfusingFactory = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB).").defineInRange("CosmicDenseInfusingFactory",256000,1,Long.MAX_VALUE));
        EMExtraInfiniteMultiversalInfusingFactory = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB).").defineInRange("InfiniteMultiversalInfusingFactory",324000,1,Long.MAX_VALUE));
        configSpec = builder.build();
    }

    @Override
    public String getFileName() {
        return "EvolvedMekanismExtras-Capacity";
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
