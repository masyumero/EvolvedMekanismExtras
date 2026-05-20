package io.github.masyumero.emextras.common.config;

import io.github.masyumero.emextras.EMExtras;
import mekanism.api.heat.HeatAPI;
import mekanism.api.math.FloatingLong;
import mekanism.common.config.BaseMekanismConfig;
import mekanism.common.config.value.CachedFloatingLongValue;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

public class EMExtraConfig extends BaseMekanismConfig {
    private final ForgeConfigSpec configSpec;
    public final CachedFloatingLongValue absoluteOverclockedUniversalCableCapacity;
    public final CachedFloatingLongValue supremeQuantumUniversalCableCapacity;
    public final CachedFloatingLongValue cosmicDenseUniversalCableCapacity;
    public final CachedFloatingLongValue infiniteMultiversalUniversalCableCapacity;

    public final CachedFloatingLongValue absoluteOverclockedMechanicalPipePullAmount;
    public final CachedFloatingLongValue supremeQuantumMechanicalPipePullAmount;
    public final CachedFloatingLongValue cosmicDenseMechanicalPipePullAmount;
    public final CachedFloatingLongValue infiniteMultiversalMechanicalPipePullAmount;

    public final CachedFloatingLongValue absoluteOverclockedMechanicalPipeCapacity;
    public final CachedFloatingLongValue supremeQuantumMechanicalPipeCapacity;
    public final CachedFloatingLongValue cosmicDenseMechanicalPipeCapacity;
    public final CachedFloatingLongValue infiniteMultiversalMechanicalPipeCapacity;

    public final CachedFloatingLongValue absoluteOverclockedThermodynamicConductorConduction;
    public final CachedFloatingLongValue supremeQuantumThermodynamicConductorConduction;
    public final CachedFloatingLongValue cosmicDenseThermodynamicConductorConduction;
    public final CachedFloatingLongValue infiniteMultiversalThermodynamicConductorConduction;

    public final CachedFloatingLongValue absoluteOverclockedThermodynamicConductornCapacity;
    public final CachedFloatingLongValue supremeQuantumThermodynamicConductornCapacity;
    public final CachedFloatingLongValue cosmicDenseThermodynamicConductornCapacity;
    public final CachedFloatingLongValue infiniteMultiversalThermodynamicConductornCapacity;

    public final CachedFloatingLongValue absoluteOverclockedThermodynamicConductornInsulation;
    public final CachedFloatingLongValue supremeQuantumThermodynamicConductornInsulation;
    public final CachedFloatingLongValue cosmicDenseThermodynamicConductornInsulation;
    public final CachedFloatingLongValue infiniteMultiversalThermodynamicConductornInsulation;

    public final CachedFloatingLongValue absoluteOverclockedLogisticalTransporterSpeed;
    public final CachedFloatingLongValue supremeQuantumLogisticalTransporterSpeed;
    public final CachedFloatingLongValue cosmicDenseLogisticalTransporterSpeed;
    public final CachedFloatingLongValue infiniteMultiversalLogisticalTransporterSpeed;

    public final CachedFloatingLongValue absoluteOverclockedLogisticalTransporterPullAmount;
    public final CachedFloatingLongValue supremeQuantumLogisticalTransporterPullAmount;
    public final CachedFloatingLongValue cosmicDenseLogisticalTransporterPullAmount;
    public final CachedFloatingLongValue infiniteMultiversalLogisticalTransporterPullAmount;

    public final CachedFloatingLongValue absoluteOverclockedPressurizedTubePullAmount;
    public final CachedFloatingLongValue supremeQuantumPressurizedTubePullAmount;
    public final CachedFloatingLongValue cosmicDensePressurizedTubePullAmount;
    public final CachedFloatingLongValue infiniteMultiversalPressurizedTubePullAmount;

    public final CachedFloatingLongValue absoluteOverclockedPressurizedTubeCapacity;
    public final CachedFloatingLongValue supremeQuantumPressurizedTubeCapacity;
    public final CachedFloatingLongValue cosmicDensePressurizedTubeCapacity;
    public final CachedFloatingLongValue infiniteMultiversalPressurizedTubeCapacity;

    public EMExtraConfig() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.comment("Config").push(EMExtras.MODID);

        final String noteUC = "Internal buffer in Joules of each 'TIER' universal cable.(long)";
        builder.comment("Universal Cables").push("universal cables");
        absoluteOverclockedUniversalCableCapacity = CachedFloatingLongValue.define(this, builder, noteUC,"absoluteOverclockedUniversalCable", FloatingLong.createConst(262_144_000L));
        supremeQuantumUniversalCableCapacity = CachedFloatingLongValue.define(this, builder, noteUC,"supremeQuantumUniversalCable", FloatingLong.createConst(2_097_152_000L));
        cosmicDenseUniversalCableCapacity = CachedFloatingLongValue.define(this, builder, noteUC, "cosmicDenseUniversalCable", FloatingLong.createConst(16_777_216_000L));
        infiniteMultiversalUniversalCableCapacity = CachedFloatingLongValue.define(this, builder, noteUC, "infiniteMultiversalUniversalCable", FloatingLong.createConst(134_217_728_000L));
        builder.pop();

        final String noteMP = "Capacity of 'TIER' mechanical pipes in mB.(long)";
        final String noteMP2 = "Pump rate of 'TIER' mechanical pipes in mB/t.(int)";
        builder.comment("Mechanical Pipes").push("mechanical pipes");
        absoluteOverclockedMechanicalPipePullAmount = CachedFloatingLongValue.define(this, builder, noteMP2, "absoluteOverclockedMechanicalPipePullAmount", FloatingLong.createConst(1_024_000));
        supremeQuantumMechanicalPipePullAmount = CachedFloatingLongValue.define(this, builder, noteMP2,"supremeQuantumMechanicalPipePullAmount", FloatingLong.createConst(8_192_000));
        cosmicDenseMechanicalPipePullAmount = CachedFloatingLongValue.define(this, builder, noteMP2,"cosmicDenseMechanicalPipePullAmount", FloatingLong.createConst(65_536_000));
        infiniteMultiversalMechanicalPipePullAmount = CachedFloatingLongValue.define(this, builder, noteMP2,"infiniteMultiversalMechanicalPipePullAmount", FloatingLong.createConst(524_288_000));

        absoluteOverclockedMechanicalPipeCapacity = CachedFloatingLongValue.define(this, builder, noteMP, "absoluteOverclockedMechanicalPipeCapacity", FloatingLong.createConst(4_096_000));
        supremeQuantumMechanicalPipeCapacity = CachedFloatingLongValue.define(this, builder, noteMP, "supremeQuantumMechanicalPipeCapacity", FloatingLong.createConst(32_768_000));
        cosmicDenseMechanicalPipeCapacity = CachedFloatingLongValue.define(this, builder, noteMP, "cosmicDenseMechanicalPipeCapacity", FloatingLong.createConst(262_144_000));
        infiniteMultiversalMechanicalPipeCapacity = CachedFloatingLongValue.define(this, builder, noteMP, "infiniteMultiversalMechanicalPipeCapacity", FloatingLong.createConst(2_097_152_000));
        builder.pop();

        final String noteTC = "Conduction value of 'TIER' thermodynamic conductors.(long)";
        final String noteTC2 = "Heat capacity of 'TIER' thermodynamic conductors.(long)";
        final String noteTC3 = "Insulation value of 'TIER' thermodynamic conductor(long).";
        builder.comment("Thermodynamic Conductors").push("thermodynamic conductors");
        absoluteOverclockedThermodynamicConductorConduction = CachedFloatingLongValue.define(this, builder, noteTC, "absoluteOverclockedThermodynamicConductorConduction", FloatingLong.createConst(15L));
        supremeQuantumThermodynamicConductorConduction = CachedFloatingLongValue.define(this, builder, noteTC, "supremeQuantumThermodynamicConductorConduction", FloatingLong.createConst(20L));
        cosmicDenseThermodynamicConductorConduction = CachedFloatingLongValue.define(this, builder, noteTC, "cosmicDenseThermodynamicConductorConduction", FloatingLong.createConst(25L));
        infiniteMultiversalThermodynamicConductorConduction = CachedFloatingLongValue.define(this, builder, noteTC, "infiniteMultiversalThermodynamicConductorConduction", FloatingLong.createConst(30L));

        absoluteOverclockedThermodynamicConductornCapacity = CachedFloatingLongValue.define(this, builder, noteTC2, "absoluteOverclockedThermodynamicConductornCapacity", FloatingLong.createConst(HeatAPI.DEFAULT_HEAT_CAPACITY));
        supremeQuantumThermodynamicConductornCapacity = CachedFloatingLongValue.define(this, builder, noteTC2, "supremeQuantumThermodynamicConductornCapacity", FloatingLong.createConst(HeatAPI.DEFAULT_HEAT_CAPACITY));
        cosmicDenseThermodynamicConductornCapacity = CachedFloatingLongValue.define(this, builder, noteTC2, "cosmicDenseThermodynamicConductornCapacity", FloatingLong.createConst(HeatAPI.DEFAULT_HEAT_CAPACITY));
        infiniteMultiversalThermodynamicConductornCapacity = CachedFloatingLongValue.define(this, builder, noteTC2, "infiniteMultiversalThermodynamicConductornCapacity", FloatingLong.createConst(HeatAPI.DEFAULT_HEAT_CAPACITY));

        absoluteOverclockedThermodynamicConductornInsulation = CachedFloatingLongValue.define(this, builder, noteTC3, "absoluteOverclockedThermodynamicConductornInsulation", FloatingLong.createConst(600000L));
        supremeQuantumThermodynamicConductornInsulation = CachedFloatingLongValue.define(this, builder, noteTC3, "supremeQuantumThermodynamicConductornInsulation", FloatingLong.createConst(900000L));
        cosmicDenseThermodynamicConductornInsulation = CachedFloatingLongValue.define(this, builder, noteTC3, "cosmicDenseThermodynamicConductornInsulation", FloatingLong.createConst(2000000L));
        infiniteMultiversalThermodynamicConductornInsulation = CachedFloatingLongValue.define(this, builder, noteTC3, "infiniteMultiversalThermodynamicConductornInsulation", FloatingLong.createConst(8000000L));
        builder.pop();

        final String noteLT = "Five times the travel speed in m/s of 'TIER' logistical transporter.(int)";
        final String noteLT2 = "Item throughput rate of 'TIER' logistical transporters in items/half second.(int)";
        builder.comment("Logistical Transporters").push("logistical transporters");
        absoluteOverclockedLogisticalTransporterSpeed = CachedFloatingLongValue.define(this, builder, noteLT, "absoluteOverclockedLogisticalTransporterSpeed", FloatingLong.createConst(60));
        supremeQuantumLogisticalTransporterSpeed = CachedFloatingLongValue.define(this, builder, noteLT, "supremeQuantumLogisticalTransporterSpeed", FloatingLong.createConst(65));
        cosmicDenseLogisticalTransporterSpeed = CachedFloatingLongValue.define(this, builder, noteLT, "cosmicDenseLogisticalTransporterSpeed", FloatingLong.createConst(85));
        infiniteMultiversalLogisticalTransporterSpeed = CachedFloatingLongValue.define(this, builder, noteLT, "infiniteMultiversalLogisticalTransporterSpeed", FloatingLong.createConst(125));

        absoluteOverclockedLogisticalTransporterPullAmount = CachedFloatingLongValue.define(this, builder, noteLT2, "absoluteOverclockedLogisticalTransporterPullAmount", FloatingLong.createConst(192));
        supremeQuantumLogisticalTransporterPullAmount = CachedFloatingLongValue.define(this, builder, noteLT2, "supremeQuantumLogisticalTransporterPullAmount", FloatingLong.createConst(384));
        cosmicDenseLogisticalTransporterPullAmount = CachedFloatingLongValue.define(this, builder, noteLT2, "cosmicDenseLogisticalTransporterPullAmount", FloatingLong.createConst(768));
        infiniteMultiversalLogisticalTransporterPullAmount = CachedFloatingLongValue.define(this, builder, noteLT2, "infiniteMultiversalLogisticalTransporterPullAmount", FloatingLong.createConst(1536));
        builder.pop();

        final String notePT = "Capacity of 'TIER' pressurized tubes in mB.(long)";
        final String notePT2 = "Pump rate of 'TIER' pressurized tubes in mB/t.(long)";
        builder.comment("Pressurized Tubes").push("pressurized tubes");
        absoluteOverclockedPressurizedTubePullAmount = CachedFloatingLongValue.define(this, builder, notePT2, "absoluteOverclockedPressurizedTubePullAmount", FloatingLong.createConst(8_192_000));
        supremeQuantumPressurizedTubePullAmount = CachedFloatingLongValue.define(this, builder, notePT2, "supremeQuantumPressurizedTubePullAmount", FloatingLong.createConst(65_536_000));
        cosmicDensePressurizedTubePullAmount = CachedFloatingLongValue.define(this, builder, notePT2, "cosmicDensePressurizedTubePullAmount", FloatingLong.createConst(524_288_000));
        infiniteMultiversalPressurizedTubePullAmount = CachedFloatingLongValue.define(this, builder, notePT2, "infiniteMultiversalPressurizedTubePullAmount", FloatingLong.createConst(4_194_304_000L));

        absoluteOverclockedPressurizedTubeCapacity = CachedFloatingLongValue.define(this, builder, notePT, "absoluteOverclockedPressurizedTubeCapacity", FloatingLong.createConst(32_768_000));
        supremeQuantumPressurizedTubeCapacity = CachedFloatingLongValue.define(this, builder, notePT, "supremeQuantumPressurizedTubeCapacity", FloatingLong.createConst(262_144_000));
        cosmicDensePressurizedTubeCapacity = CachedFloatingLongValue.define(this, builder, notePT, "cosmicDensePressurizedTubeCapacity", FloatingLong.createConst(2_097_152_000));
        infiniteMultiversalPressurizedTubeCapacity = CachedFloatingLongValue.define(this, builder, notePT, "infiniteMultiversalPressurizedTubeCapacity", FloatingLong.createConst(16_777_216_000L));
        builder.pop();
        configSpec = builder.build();
    }

    @Override
    public String getFileName() {
        return "EvolvedMekanismExtras";
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
