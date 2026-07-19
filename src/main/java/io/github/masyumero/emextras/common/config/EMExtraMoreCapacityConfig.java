package io.github.masyumero.emextras.common.config;

import mekanism.common.config.BaseMekanismConfig;
import mekanism.common.config.value.CachedBooleanValue;
import mekanism.common.config.value.CachedIntValue;
import mekanism.common.config.value.CachedLongValue;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

public class EMExtraMoreCapacityConfig extends BaseMekanismConfig {

    private final ForgeConfigSpec configSpec;
    public final CachedBooleanValue moreCapacityMode;
    public final CachedLongValue absoluteOverclockedCompressing;
    public final CachedLongValue supremeQuantumCompressing;
    public final CachedLongValue cosmicDenseCompressing;
    public final CachedLongValue infiniteMultiversalCompressing;

    public final CachedLongValue absoluteOverclockedInjecting;
    public final CachedLongValue supremeQuantumInjecting;
    public final CachedLongValue cosmicDenseInjecting;
    public final CachedLongValue infiniteMultiversalInjecting;

    public final CachedLongValue absoluteOverclockedPurifying;
    public final CachedLongValue supremeQuantumPurifying;
    public final CachedLongValue cosmicDensePurifying;
    public final CachedLongValue infiniteMultiversalPurifying;

    public final CachedLongValue absoluteOverclockedInfusingFactory;
    public final CachedLongValue supremeQuantumInfusingFactory;
    public final CachedLongValue cosmicDenseInfusingFactory;
    public final CachedLongValue infiniteMultiversalInfusingFactory;

    public final CachedLongValue absoluteOverclockedCentrifugingInput;
    public final CachedLongValue supremeQuantumCentrifugingInput;
    public final CachedLongValue cosmicDenseCentrifugingInput;
    public final CachedLongValue infiniteMultiversalCentrifugingInput;
    public final CachedLongValue absoluteOverclockedCentrifugingOutput;
    public final CachedLongValue supremeQuantumCentrifugingOutput;
    public final CachedLongValue cosmicDenseCentrifugingOutput;
    public final CachedLongValue infiniteMultiversalCentrifugingOutput;
    
    public final CachedLongValue absoluteOverclockedWashingInput;
    public final CachedLongValue supremeQuantumWashingInput;
    public final CachedLongValue cosmicDenseWashingInput;
    public final CachedLongValue infiniteMultiversalWashingInput;
    public final CachedIntValue absoluteOverclockedWashingFluidInput;
    public final CachedIntValue supremeQuantumWashingFluidInput;
    public final CachedIntValue cosmicDenseWashingFluidInput;
    public final CachedIntValue infiniteMultiversalWashingFluidInput;
    public final CachedLongValue absoluteOverclockedWashingOutput;
    public final CachedLongValue supremeQuantumWashingOutput;
    public final CachedLongValue cosmicDenseWashingOutput;
    public final CachedLongValue infiniteMultiversalWashingOutput;
    
    public final CachedLongValue absoluteOverclockedDissolvingInput;
    public final CachedLongValue supremeQuantumDissolvingInput;
    public final CachedLongValue cosmicDenseDissolvingInput;
    public final CachedLongValue infiniteMultiversalDissolvingInput;
    public final CachedLongValue absoluteOverclockedDissolvingOutput;
    public final CachedLongValue supremeQuantumDissolvingOutput;
    public final CachedLongValue cosmicDenseDissolvingOutput;
    public final CachedLongValue infiniteMultiversalDissolvingOutput;
    
    public final CachedLongValue absoluteOverclockedOxidizing;
    public final CachedLongValue supremeQuantumOxidizing;
    public final CachedLongValue cosmicDenseOxidizing;
    public final CachedLongValue infiniteMultiversalOxidizing;

    public final CachedLongValue absoluteOverclockedCrystallizing;
    public final CachedLongValue supremeQuantumCrystallizing;
    public final CachedLongValue cosmicDenseCrystallizing;
    public final CachedLongValue infiniteMultiversalCrystallizing;
    
    public final CachedLongValue absoluteOverclockedPigmentExtracting;
    public final CachedLongValue supremeQuantumPigmentExtracting;
    public final CachedLongValue cosmicDensePigmentExtracting;
    public final CachedLongValue infiniteMultiversalPigmentExtracting;
    
    public final CachedIntValue absoluteOverclockedLiquifying;
    public final CachedIntValue supremeQuantumLiquifying;
    public final CachedIntValue cosmicDenseLiquifying;
    public final CachedIntValue infiniteMultiversalLiquifying;
    
    public final CachedLongValue absoluteOverclockedPainting;
    public final CachedLongValue supremeQuantumPainting;
    public final CachedLongValue cosmicDensePainting;
    public final CachedLongValue infiniteMultiversalPainting;
    
    public final CachedLongValue absoluteOverclockedPlanting;
    public final CachedLongValue supremeQuantumPlanting;
    public final CachedLongValue cosmicDensePlanting;
    public final CachedLongValue infiniteMultiversalPlanting;
    
    public final CachedLongValue absoluteOverclockedPRCInput;
    public final CachedLongValue supremeQuantumPRCInput;
    public final CachedLongValue cosmicDensePRCInput;
    public final CachedLongValue infiniteMultiversalPRCInput;
    public final CachedIntValue absoluteOverclockedPRCFluidInput;
    public final CachedIntValue supremeQuantumPRCFluidInput;
    public final CachedIntValue cosmicDensePRCFluidInput;
    public final CachedIntValue infiniteMultiversalPRCFluidInput;
    public final CachedLongValue absoluteOverclockedPRCOutput;
    public final CachedLongValue supremeQuantumPRCOutput;
    public final CachedLongValue cosmicDensePRCOutput;
    public final CachedLongValue infiniteMultiversalPRCOutput;
    
    public final CachedLongValue absoluteOverclockedReplicating;
    public final CachedLongValue supremeQuantumReplicating;
    public final CachedLongValue cosmicDenseReplicating;
    public final CachedLongValue infiniteMultiversalReplicating;
    
    public EMExtraMoreCapacityConfig() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.comment("Evolved Mekanism Extras More Capacity Config");
        builder.push("More Capacity Mode");
        moreCapacityMode = CachedBooleanValue.wrap(this, builder.comment("If this is true, these configurations will be applied.").define("MoreCapacityMode", false));
        builder.pop().push("CompressingFactoryEMExtras");
        absoluteOverclockedCompressing  = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 3024000, Vanilla: 30240").defineInRange("AbsoluteOverclockedChemicalTankCapacity",3024000,1,Long.MAX_VALUE));
        supremeQuantumCompressing       = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 4116000, Vanilla: 41160").defineInRange("SupremeQuantumChemicalTankCapacity",4116000,1,Long.MAX_VALUE));
        cosmicDenseCompressing          = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 5376000, Vanilla: 53760").defineInRange("CosmicDenseChemicalTankCapacity",5376000,1,Long.MAX_VALUE));
        infiniteMultiversalCompressing  = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 6804000, Vanilla: 68040").defineInRange("InfiniteMultiversalChemicalTankCapacity",6804000,1,Long.MAX_VALUE));
        builder.pop().push("InjectingFactoryEMExtras");
        absoluteOverclockedInjecting    = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 3024000, Vanilla: 30240").defineInRange("AbsoluteOverclockedChemicalTankCapacity",3024000,1,Long.MAX_VALUE));
        supremeQuantumInjecting         = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 4116000, Vanilla: 41160").defineInRange("SupremeQuantumChemicalTankCapacity",4116000,1,Long.MAX_VALUE));
        cosmicDenseInjecting            = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 5376000, Vanilla: 53760").defineInRange("CosmicDenseChemicalTankCapacity",5376000,1,Long.MAX_VALUE));
        infiniteMultiversalInjecting    = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 6804000, Vanilla: 68040").defineInRange("InfiniteMultiversalChemicalTankCapacity",6804000,1,Long.MAX_VALUE));
        builder.pop().push("PurifyingFactoryEMExtras");
        absoluteOverclockedPurifying    = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 3024000, Vanilla: 30240").defineInRange("AbsoluteOverclockedChemicalTankCapacity",3024000,1,Long.MAX_VALUE));
        supremeQuantumPurifying         = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 4116000, Vanilla: 41160").defineInRange("SupremeQuantumChemicalTankCapacity",4116000,1,Long.MAX_VALUE));
        cosmicDensePurifying            = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 5376000, Vanilla: 53760").defineInRange("CosmicDenseChemicalTankCapacity",5376000,1,Long.MAX_VALUE));
        infiniteMultiversalPurifying    = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 6804000, Vanilla: 68040").defineInRange("InfiniteMultiversalChemicalTankCapacity",6804000,1,Long.MAX_VALUE));
        builder.pop().push("InfusingFactoryEMExtras");
        absoluteOverclockedInfusingFactory = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB).").defineInRange("AbsoluteOverclockedInfusingFactory",144000,1,Long.MAX_VALUE));
        supremeQuantumInfusingFactory = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB).").defineInRange("SupremeQuantumInfusingFactory",196000,1,Long.MAX_VALUE));
        cosmicDenseInfusingFactory = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB).").defineInRange("CosmicDenseInfusingFactory",256000,1,Long.MAX_VALUE));
        infiniteMultiversalInfusingFactory = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB).").defineInRange("InfiniteMultiversalInfusingFactory",324000,1,Long.MAX_VALUE));
        builder.pop().push("CentrifugingFactoryEMExtras");
        absoluteOverclockedCentrifugingInput    = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 12000000, Vanilla: 120000").defineInRange("absoluteInputChemicalTankCapacity", 12000000, 1, Long.MAX_VALUE));
        supremeQuantumCentrifugingInput         = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 14000000, Vanilla: 140000").defineInRange("supremeInputChemicalTankCapacity", 14000000, 1, Long.MAX_VALUE));
        cosmicDenseCentrifugingInput            = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 16000000, Vanilla: 160000").defineInRange("cosmicInputChemicalTankCapacity", 16000000, 1, Long.MAX_VALUE));
        infiniteMultiversalCentrifugingInput    = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 18000000, Vanilla: 180000").defineInRange("infiniteInputChemicalTankCapacity", 18000000, 1, Long.MAX_VALUE));
        absoluteOverclockedCentrifugingOutput   = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 12000000, Vanilla: 120000").defineInRange("absoluteOutputChemicalTankCapacity", 12000000, 1, Long.MAX_VALUE));
        supremeQuantumCentrifugingOutput        = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 14000000, Vanilla: 140000").defineInRange("supremeOutputChemicalTankCapacity", 14000000, 1, Long.MAX_VALUE));
        cosmicDenseCentrifugingOutput           = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 16000000, Vanilla: 160000").defineInRange("cosmicOutputChemicalTankCapacity", 16000000, 1, Long.MAX_VALUE));
        infiniteMultiversalCentrifugingOutput   = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 18000000, Vanilla: 180000").defineInRange("infiniteOutputChemicalTankCapacity", 18000000, 1, Long.MAX_VALUE));
        builder.pop().push("WashingFactoryEMExtras");
        absoluteOverclockedWashingInput         = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 12000000, Vanilla: 120000").defineInRange("absoluteInputChemicalTankCapacity", 12000000, 1, Long.MAX_VALUE));
        supremeQuantumWashingInput              = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 14000000, Vanilla: 140000").defineInRange("supremeInputChemicalTankCapacity", 14000000, 1, Long.MAX_VALUE));
        cosmicDenseWashingInput                 = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 16000000, Vanilla: 160000").defineInRange("cosmicInputChemicalTankCapacity", 16000000, 1, Long.MAX_VALUE));
        infiniteMultiversalWashingInput         = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 18000000, Vanilla: 180000").defineInRange("infiniteInputChemicalTankCapacity", 18000000, 1, Long.MAX_VALUE));
        absoluteOverclockedWashingFluidInput    = CachedIntValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 12000000, Vanilla: 120000").defineInRange("absoluteInputFluidTankCapacity", 12000000, 1, Integer.MAX_VALUE));
        supremeQuantumWashingFluidInput         = CachedIntValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 14000000, Vanilla: 140000").defineInRange("supremeInputFluidTankCapacity", 14000000, 1, Integer.MAX_VALUE));
        cosmicDenseWashingFluidInput            = CachedIntValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 16000000, Vanilla: 160000").defineInRange("cosmicInputFluidTankCapacity", 16000000, 1, Integer.MAX_VALUE));
        infiniteMultiversalWashingFluidInput    = CachedIntValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 18000000, Vanilla: 180000").defineInRange("infiniteInputFluidTankCapacity", 18000000, 1, Integer.MAX_VALUE));
        absoluteOverclockedWashingOutput        = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 12000000, Vanilla: 120000").defineInRange("absoluteOutputChemicalTankCapacity", 12000000, 1, Long.MAX_VALUE));
        supremeQuantumWashingOutput             = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 14000000, Vanilla: 140000").defineInRange("supremeOutputChemicalTankCapacity", 14000000, 1, Long.MAX_VALUE));
        cosmicDenseWashingOutput                = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 16000000, Vanilla: 160000").defineInRange("cosmicOutputChemicalTankCapacity", 16000000, 1, Long.MAX_VALUE));
        infiniteMultiversalWashingOutput        = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 18000000, Vanilla: 180000").defineInRange("infiniteOutputChemicalTankCapacity", 18000000, 1, Long.MAX_VALUE));
        builder.pop().push("DissolvingFactoryEMExtras");
        absoluteOverclockedDissolvingInput      = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 12000000, Vanilla: 120000").defineInRange("absoluteInputChemicalTankCapacity", 12000000, 1, Long.MAX_VALUE));
        supremeQuantumDissolvingInput           = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 14000000, Vanilla: 140000").defineInRange("supremeInputChemicalTankCapacity", 14000000, 1, Long.MAX_VALUE));
        cosmicDenseDissolvingInput              = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 16000000, Vanilla: 160000").defineInRange("cosmicInputChemicalTankCapacity", 16000000, 1, Long.MAX_VALUE));
        infiniteMultiversalDissolvingInput      = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 18000000, Vanilla: 180000").defineInRange("infiniteInputChemicalTankCapacity", 18000000, 1, Long.MAX_VALUE));
        absoluteOverclockedDissolvingOutput     = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 12000000, Vanilla: 120000").defineInRange("absoluteOutputChemicalTankCapacity", 12000000, 1, Long.MAX_VALUE));
        supremeQuantumDissolvingOutput          = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 14000000, Vanilla: 140000").defineInRange("supremeOutputChemicalTankCapacity", 14000000, 1, Long.MAX_VALUE));
        cosmicDenseDissolvingOutput             = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 16000000, Vanilla: 160000").defineInRange("cosmicOutputChemicalTankCapacity", 16000000, 1, Long.MAX_VALUE));
        infiniteMultiversalDissolvingOutput     = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 18000000, Vanilla: 180000").defineInRange("infiniteOutputChemicalTankCapacity", 18000000, 1, Long.MAX_VALUE));
        builder.pop().push("OxidizingFactoryEMExtras");
        absoluteOverclockedOxidizing    = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 12000000, Vanilla: 120000").defineInRange("absoluteChemicalTankCapacity", 12000000, 1, Long.MAX_VALUE));
        supremeQuantumOxidizing         = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 14000000, Vanilla: 140000").defineInRange("supremeChemicalTankCapacity", 14000000, 1, Long.MAX_VALUE));
        cosmicDenseOxidizing            = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 16000000, Vanilla: 160000").defineInRange("cosmicChemicalTankCapacity", 16000000, 1, Long.MAX_VALUE));
        infiniteMultiversalOxidizing    = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 18000000, Vanilla: 180000").defineInRange("infiniteChemicalTankCapacity", 18000000, 1, Long.MAX_VALUE));
        builder.pop().push("CrystallizingFactoryEMExtras");
        absoluteOverclockedCrystallizing    = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 12000000, Vanilla: 120000").defineInRange("absoluteChemicalTankCapacity", 12000000, 1, Long.MAX_VALUE));
        supremeQuantumCrystallizing         = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 14000000, Vanilla: 140000").defineInRange("supremeChemicalTankCapacity", 14000000, 1, Long.MAX_VALUE));
        cosmicDenseCrystallizing            = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 16000000, Vanilla: 160000").defineInRange("cosmicChemicalTankCapacity", 16000000, 1, Long.MAX_VALUE));
        infiniteMultiversalCrystallizing    = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 18000000, Vanilla: 180000").defineInRange("infiniteChemicalTankCapacity", 18000000, 1, Long.MAX_VALUE));
        builder.pop().push("PigmentExtractingFactoryEMExtras");
        absoluteOverclockedPigmentExtracting    = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 12000000, Vanilla: 120000").defineInRange("absoluteChemicalTankCapacity", 12000000, 1, Long.MAX_VALUE));
        supremeQuantumPigmentExtracting         = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 14000000, Vanilla: 140000").defineInRange("supremeChemicalTankCapacity", 14000000, 1, Long.MAX_VALUE));
        cosmicDensePigmentExtracting            = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 16000000, Vanilla: 160000").defineInRange("cosmicChemicalTankCapacity", 16000000, 1, Long.MAX_VALUE));
        infiniteMultiversalPigmentExtracting    = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 18000000, Vanilla: 180000").defineInRange("infiniteChemicalTankCapacity", 18000000, 1, Long.MAX_VALUE));
        builder.pop().push("LiquifyingFactoryEMExtras");
        absoluteOverclockedLiquifying   = CachedIntValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 12000000, Vanilla: 120000").defineInRange("absoluteFluidTankCapacity", 12000000, 1, Integer.MAX_VALUE));
        supremeQuantumLiquifying        = CachedIntValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 14000000, Vanilla: 140000").defineInRange("supremeFluidTankCapacity", 14000000, 1, Integer.MAX_VALUE));
        cosmicDenseLiquifying           = CachedIntValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 16000000, Vanilla: 160000").defineInRange("cosmicFluidTankCapacity", 16000000, 1, Integer.MAX_VALUE));
        infiniteMultiversalLiquifying   = CachedIntValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 18000000, Vanilla: 180000").defineInRange("infiniteFluidTankCapacity", 18000000, 1, Integer.MAX_VALUE));
        builder.pop().push("PaintingFactoryEMExtras");
        absoluteOverclockedPainting     = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 18000000, Vanilla: 180000").defineInRange("absoluteChemicalTankCapacity", 18000000, 1, Long.MAX_VALUE));
        supremeQuantumPainting          = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 21000000, Vanilla: 210000").defineInRange("supremeChemicalTankCapacity", 21000000, 1, Long.MAX_VALUE));
        cosmicDensePainting             = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 24000000, Vanilla: 240000").defineInRange("cosmicChemicalTankCapacity", 24000000, 1, Long.MAX_VALUE));
        infiniteMultiversalPainting     = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 27000000, Vanilla: 270000").defineInRange("infiniteChemicalTankCapacity", 27000000, 1, Long.MAX_VALUE));
        builder.pop().push("PlantingFactoryEMExtras");
        absoluteOverclockedPlanting     = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 12000000, Vanilla: 120000").defineInRange("absoluteChemicalTankCapacity", 12000000, 1, Long.MAX_VALUE));
        supremeQuantumPlanting          = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 14000000, Vanilla: 140000").defineInRange("supremeChemicalTankCapacity", 14000000, 1, Long.MAX_VALUE));
        cosmicDensePlanting             = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 16000000, Vanilla: 160000").defineInRange("cosmicChemicalTankCapacity", 16000000, 1, Long.MAX_VALUE));
        infiniteMultiversalPlanting     = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 18000000, Vanilla: 180000").defineInRange("infiniteChemicalTankCapacity", 18000000, 1, Long.MAX_VALUE));
        builder.pop().push("PRCFactoryEMExtras");
        absoluteOverclockedPRCInput         = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 12000000, Vanilla: 120000").defineInRange("absoluteInputChemicalTankCapacity", 12000000, 1, Long.MAX_VALUE));
        supremeQuantumPRCInput              = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 14000000, Vanilla: 140000").defineInRange("supremeInputChemicalTankCapacity", 14000000, 1, Long.MAX_VALUE));
        cosmicDensePRCInput                 = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 16000000, Vanilla: 160000").defineInRange("cosmicInputChemicalTankCapacity", 16000000, 1, Long.MAX_VALUE));
        infiniteMultiversalPRCInput         = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 18000000, Vanilla: 180000").defineInRange("infiniteInputChemicalTankCapacity", 18000000, 1, Long.MAX_VALUE));
        absoluteOverclockedPRCFluidInput    = CachedIntValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 12000000, Vanilla: 120000").defineInRange("absoluteInputFluidTankCapacity", 12000000, 1, Integer.MAX_VALUE));
        supremeQuantumPRCFluidInput         = CachedIntValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 14000000, Vanilla: 140000").defineInRange("supremeInputFluidTankCapacity", 14000000, 1, Integer.MAX_VALUE));
        cosmicDensePRCFluidInput            = CachedIntValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 16000000, Vanilla: 160000").defineInRange("cosmicInputFluidTankCapacity", 16000000, 1, Integer.MAX_VALUE));
        infiniteMultiversalPRCFluidInput    = CachedIntValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 18000000, Vanilla: 180000").defineInRange("infiniteInputFluidTankCapacity", 18000000, 1, Integer.MAX_VALUE));
        absoluteOverclockedPRCOutput        = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 12000000, Vanilla: 120000").defineInRange("absoluteOutputChemicalTankCapacity", 12000000, 1, Long.MAX_VALUE));
        supremeQuantumPRCOutput             = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 14000000, Vanilla: 140000").defineInRange("supremeOutputChemicalTankCapacity", 14000000, 1, Long.MAX_VALUE));
        cosmicDensePRCOutput                = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 16000000, Vanilla: 160000").defineInRange("cosmicOutputChemicalTankCapacity", 16000000, 1, Long.MAX_VALUE));
        infiniteMultiversalPRCOutput        = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 18000000, Vanilla: 180000").defineInRange("infiniteOutputChemicalTankCapacity", 18000000, 1, Long.MAX_VALUE));
        builder.pop().push("ReplicatingFactoryEMExtras");
        absoluteOverclockedReplicating      = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 12000000, Vanilla: 120000").defineInRange("absoluteChemicalTankCapacity", 12000000, 1, Long.MAX_VALUE));
        supremeQuantumReplicating           = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 14000000, Vanilla: 140000").defineInRange("supremeChemicalTankCapacity", 14000000, 1, Long.MAX_VALUE));
        cosmicDenseReplicating              = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 16000000, Vanilla: 160000").defineInRange("cosmicChemicalTankCapacity", 16000000, 1, Long.MAX_VALUE));
        infiniteMultiversalReplicating      = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 18000000, Vanilla: 180000").defineInRange("infiniteChemicalTankCapacity", 18000000, 1, Long.MAX_VALUE));
        configSpec = builder.build();
    }

    @Override
    public String getFileName() {
        return "EvolvedMekanismExtras-More-Capacity";
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
