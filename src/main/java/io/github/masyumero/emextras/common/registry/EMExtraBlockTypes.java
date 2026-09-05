package io.github.masyumero.emextras.common.registry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jerry.mekextras.common.block.attribute.ExtraAttributeUpgradeSupport;
import com.jerry.mekextras.common.content.blocktype.ExtraFactory;
import com.jerry.mekextras.common.content.blocktype.ExtraMachine;
import com.jerry.mekextras.common.tier.ExtraFactoryTier;
import com.jerry.mekextras.common.util.ExtraEnumUtils;
import fr.iglee42.evolvedmekanism.EvolvedMekanismLang;
import fr.iglee42.evolvedmekanism.registries.EMContainerTypes;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import fr.iglee42.evolvedmekanism.registries.EMTileEntityTypes;
import fr.iglee42.evolvedmekanism.tiles.machine.TileEntityAlloyer;
import io.github.masyumero.emextras.common.config.LoadConfig;
import io.github.masyumero.emextras.common.content.blocktype.EMExtraFactory;
import io.github.masyumero.emextras.common.content.blocktype.EMExtraMachine;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import io.github.masyumero.emextras.common.util.EMExtraEnumUtils;
import mekanism.common.MekanismLang;
import mekanism.common.config.MekanismConfig;
import mekanism.common.content.blocktype.BlockShapes;
import mekanism.common.content.blocktype.FactoryType;
import mekanism.common.registries.MekanismContainerTypes;
import mekanism.common.registries.MekanismSounds;
import mekanism.common.registries.MekanismTileEntityTypes;
import mekanism.common.tile.machine.*;

public class EMExtraBlockTypes {
    private static final Table<EMExtraFactoryTier, FactoryType, EMExtraFactory<?>> FACTORIES = HashBasedTable.create();

    private static final Table<ExtraFactoryTier, FactoryType, ExtraFactory<?>> EXTRA_FACTORIES = HashBasedTable.create();

    public static final EMExtraMachine.EMExtraFactoryMachine<TileEntityAlloyer> ALLOYER = EMExtraMachine.EMExtraMachineBuilder
            .createEMExtraFactoryMachine(() -> EMTileEntityTypes.ALLOYER, EvolvedMekanismLang.DESCRIPTION_ALLOYER, EMFactoryType.ALLOYING)
            .withGui(() -> EMContainerTypes.ALLOYER)
            .withSound(MekanismSounds.COMBINER)
            .withEnergyConfig(LoadConfig.emExtraUsageConfig.alloyingFactory, LoadConfig.emExtraStorageConfig.alloyingFactory)
            .with(ExtraAttributeUpgradeSupport.EXTRA_ADVANCED_MACHINE_UPGRADES)
            .build();

    public static final ExtraMachine.ExtraFactoryMachine<TileEntityAlloyer> EXTRA_ALLOYER = ExtraMachine.ExtraMachineBuilder
            .createExtraFactoryMachine(() -> EMTileEntityTypes.ALLOYER, EvolvedMekanismLang.DESCRIPTION_ALLOYER, EMFactoryType.ALLOYING)
            .withGui(() -> EMContainerTypes.ALLOYER)
            .withSound(MekanismSounds.COMBINER)
            .withEnergyConfig(LoadConfig.emExtraUsageConfig.alloyingFactory, LoadConfig.emExtraStorageConfig.alloyingFactory)
            .with(ExtraAttributeUpgradeSupport.EXTRA_ADVANCED_MACHINE_UPGRADES)
            .build();

    public static final EMExtraMachine.EMExtraFactoryMachine<TileEntityEnergizedSmelter> ENERGIZED_SMELTER = EMExtraMachine.EMExtraMachineBuilder
            .createEMExtraFactoryMachine(() -> MekanismTileEntityTypes.ENERGIZED_SMELTER, MekanismLang.DESCRIPTION_ENERGIZED_SMELTER, FactoryType.SMELTING)
            .withGui(() -> MekanismContainerTypes.ENERGIZED_SMELTER)
            .withSound(MekanismSounds.ENERGIZED_SMELTER)
            .withEnergyConfig(MekanismConfig.usage.energizedSmelter, MekanismConfig.storage.energizedSmelter)
            .with(ExtraAttributeUpgradeSupport.EXTRA_MACHINE_UPGRADES)
            .build();

    public static final EMExtraMachine.EMExtraFactoryMachine<TileEntityEnrichmentChamber> ENRICHMENT_CHAMBER = EMExtraMachine.EMExtraMachineBuilder
            .createEMExtraFactoryMachine(() -> MekanismTileEntityTypes.ENRICHMENT_CHAMBER, MekanismLang.DESCRIPTION_ENRICHMENT_CHAMBER, FactoryType.ENRICHING)
            .withGui(() -> MekanismContainerTypes.ENRICHMENT_CHAMBER)
            .withSound(MekanismSounds.ENRICHMENT_CHAMBER)
            .withEnergyConfig(MekanismConfig.usage.enrichmentChamber, MekanismConfig.storage.enrichmentChamber)
            .with(ExtraAttributeUpgradeSupport.EXTRA_MACHINE_UPGRADES)
            .build();

    public static final EMExtraMachine.EMExtraFactoryMachine<TileEntityCrusher> CRUSHER = EMExtraMachine.EMExtraMachineBuilder
            .createEMExtraFactoryMachine(() -> MekanismTileEntityTypes.CRUSHER, MekanismLang.DESCRIPTION_CRUSHER, FactoryType.CRUSHING)
            .withGui(() -> MekanismContainerTypes.CRUSHER)
            .withSound(MekanismSounds.CRUSHER)
            .withEnergyConfig(MekanismConfig.usage.crusher, MekanismConfig.storage.crusher)
            .with(ExtraAttributeUpgradeSupport.EXTRA_MACHINE_UPGRADES)
            .build();

    public static final EMExtraMachine.EMExtraFactoryMachine<TileEntityOsmiumCompressor> OSMIUM_COMPRESSOR = EMExtraMachine.EMExtraMachineBuilder
            .createEMExtraFactoryMachine(() -> MekanismTileEntityTypes.OSMIUM_COMPRESSOR, MekanismLang.DESCRIPTION_OSMIUM_COMPRESSOR, FactoryType.COMPRESSING)
            .withGui(() -> MekanismContainerTypes.OSMIUM_COMPRESSOR)
            .withSound(MekanismSounds.OSMIUM_COMPRESSOR)
            .withEnergyConfig(MekanismConfig.usage.osmiumCompressor, MekanismConfig.storage.osmiumCompressor)
            .with(ExtraAttributeUpgradeSupport.EXTRA_MACHINE_UPGRADES)
            .build();

    public static final EMExtraMachine.EMExtraFactoryMachine<TileEntityCombiner> COMBINER = EMExtraMachine.EMExtraMachineBuilder
            .createEMExtraFactoryMachine(() -> MekanismTileEntityTypes.COMBINER, MekanismLang.DESCRIPTION_COMBINER, FactoryType.COMBINING)
            .withGui(() -> MekanismContainerTypes.COMBINER)
            .withSound(MekanismSounds.COMBINER)
            .withEnergyConfig(MekanismConfig.usage.combiner, MekanismConfig.storage.combiner)
            .with(ExtraAttributeUpgradeSupport.EXTRA_MACHINE_UPGRADES)
            .build();

    public static final EMExtraMachine.EMExtraFactoryMachine<TileEntityPurificationChamber> PURIFICATION_CHAMBER = EMExtraMachine.EMExtraMachineBuilder
            .createEMExtraFactoryMachine(() -> MekanismTileEntityTypes.PURIFICATION_CHAMBER, MekanismLang.DESCRIPTION_PURIFICATION_CHAMBER, FactoryType.PURIFYING)
            .withGui(() -> MekanismContainerTypes.PURIFICATION_CHAMBER)
            .withSound(MekanismSounds.PURIFICATION_CHAMBER)
            .withEnergyConfig(MekanismConfig.usage.purificationChamber, MekanismConfig.storage.purificationChamber)
            .with(ExtraAttributeUpgradeSupport.EXTRA_ADVANCED_MACHINE_UPGRADES)
            .build();

    public static final EMExtraMachine.EMExtraFactoryMachine<TileEntityChemicalInjectionChamber> CHEMICAL_INJECTION_CHAMBER = EMExtraMachine.EMExtraMachineBuilder
            .createEMExtraFactoryMachine(() -> MekanismTileEntityTypes.CHEMICAL_INJECTION_CHAMBER, MekanismLang.DESCRIPTION_CHEMICAL_INJECTION_CHAMBER, FactoryType.INJECTING)
            .withGui(() -> MekanismContainerTypes.CHEMICAL_INJECTION_CHAMBER)
            .withSound(MekanismSounds.CHEMICAL_INJECTION_CHAMBER)
            .withEnergyConfig(MekanismConfig.usage.chemicalInjectionChamber, MekanismConfig.storage.chemicalInjectionChamber)
            .with(ExtraAttributeUpgradeSupport.EXTRA_ADVANCED_MACHINE_UPGRADES)
            .build();

    public static final EMExtraMachine.EMExtraFactoryMachine<TileEntityMetallurgicInfuser> METALLURGIC_INFUSER = EMExtraMachine.EMExtraMachineBuilder
            .createEMExtraFactoryMachine(() -> MekanismTileEntityTypes.METALLURGIC_INFUSER, MekanismLang.DESCRIPTION_METALLURGIC_INFUSER, FactoryType.INFUSING)
            .withGui(() -> MekanismContainerTypes.METALLURGIC_INFUSER)
            .withSound(MekanismSounds.METALLURGIC_INFUSER)
            .withEnergyConfig(MekanismConfig.usage.metallurgicInfuser, MekanismConfig.storage.metallurgicInfuser)
            .withCustomShape(BlockShapes.METALLURGIC_INFUSER)
            .with(ExtraAttributeUpgradeSupport.EXTRA_MACHINE_UPGRADES)
            .build();

    public static final EMExtraMachine.EMExtraFactoryMachine<TileEntityPrecisionSawmill> PRECISION_SAWMILL = EMExtraMachine.EMExtraMachineBuilder
            .createEMExtraFactoryMachine(() -> MekanismTileEntityTypes.PRECISION_SAWMILL, MekanismLang.DESCRIPTION_PRECISION_SAWMILL, FactoryType.SAWING)
            .withGui(() -> MekanismContainerTypes.PRECISION_SAWMILL)
            .withSound(MekanismSounds.PRECISION_SAWMILL)
            .withEnergyConfig(MekanismConfig.usage.precisionSawmill, MekanismConfig.storage.precisionSawmill)
            .with(ExtraAttributeUpgradeSupport.EXTRA_MACHINE_UPGRADES)
            .build();

    static {
        for (EMExtraFactoryTier tier : EMExtraEnumUtils.EMEXTRA_FACTORY_TIERS) {
            for (FactoryType type : EMExtraEnumUtils.FACTORY_TYPES) {
                FACTORIES.put(tier, type, EMExtraFactory.EMExtraFactoryBuilder.createFactory(() -> EMExtraTileEntityTypes.getEMExtraFactoryTile(tier, type), type, tier).build());
            }
        }
        for (ExtraFactoryTier tier : ExtraEnumUtils.EXTRA_FACTORY_TIERS) {
            EXTRA_FACTORIES.put(tier, EMFactoryType.ALLOYING, ExtraFactory.ExtraFactoryBuilder.createFactory(() -> EMExtraTileEntityTypes.getExtraFactoryTile(tier, EMFactoryType.ALLOYING), EMFactoryType.ALLOYING, tier).build());
        }
    }
    public static EMExtraFactory<?> getEMExtraFactory(EMExtraFactoryTier tier, FactoryType type) {
        return FACTORIES.get(tier, type);
    }

    public static ExtraFactory<?> getExtraFactory(ExtraFactoryTier tier, FactoryType type) {
        return EXTRA_FACTORIES.get(tier, type);
    }
}
