package io.github.masyumero.emextras.common.registry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jerry.mekanism_extras.api.ExtraUpgrade;
import com.jerry.mekanism_extras.common.content.blocktype.ExtraFactory;
import com.jerry.mekanism_extras.common.content.blocktype.ExtraMachine;
import com.jerry.mekanism_extras.common.tier.ExtraFactoryTier;
import com.jerry.mekanism_extras.common.util.ExtraEnumUtils;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import io.github.masyumero.emextras.common.block.attribute.EMExtraAttributeTier;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import fr.iglee42.evolvedmekanism.EvolvedMekanismLang;
import fr.iglee42.evolvedmekanism.registries.EMContainerTypes;
import fr.iglee42.evolvedmekanism.registries.EMTileEntityTypes;
import fr.iglee42.evolvedmekanism.tiles.machine.TileEntityAlloyer;
import io.github.masyumero.emextras.common.config.LoadConfig;
import io.github.masyumero.emextras.common.content.blocktype.EMExtraFactory;
import io.github.masyumero.emextras.common.content.blocktype.EMExtraFactoryType;
import io.github.masyumero.emextras.common.content.blocktype.EMExtraMachine;
import io.github.masyumero.emextras.common.tier.EMExtraICTier;
import io.github.masyumero.emextras.common.tier.EMExtraIPTier;
import io.github.masyumero.emextras.common.tile.multiblock.TileEntityEMExtraInductionCell;
import io.github.masyumero.emextras.common.tile.multiblock.TileEntityEMExtraInductionProvider;
import io.github.masyumero.emextras.common.util.EMExtraEnumUtils;
import mekanism.api.Upgrade;
import mekanism.common.MekanismLang;
import mekanism.common.config.MekanismConfig;
import mekanism.common.content.blocktype.BlockShapes;
import mekanism.common.content.blocktype.BlockTypeTile;
import mekanism.common.content.blocktype.FactoryType;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.registries.MekanismContainerTypes;
import mekanism.common.registries.MekanismSounds;
import mekanism.common.registries.MekanismTileEntityTypes;
import mekanism.common.tile.machine.*;

import java.util.EnumSet;
import java.util.function.Supplier;

public class EMExtrasBlockType {
    private static final Table<EMExtraFactoryTier, EMExtraFactoryType, EMExtraFactory<?>> FACTORIES = HashBasedTable.create();

    private static final Table<ExtraFactoryTier, FactoryType, ExtraFactory<?>> ADVANCED_FACTORIES = HashBasedTable.create();

    public static final EMExtraMachine.EMExtraFactoryMachine<TileEntityAlloyer> ALLOYER = EMExtraMachine.EMExtraMachineBuilder
            .createEMExtraFactoryMachine(() -> EMTileEntityTypes.ALLOYER, EvolvedMekanismLang.DESCRIPTION_ALLOYER, EMExtraFactoryType.ALLOYING)
            .withGui(() -> EMContainerTypes.ALLOYER)
            .withSound(MekanismSounds.COMBINER)
            .withEnergyConfig(LoadConfig.emExtraUsageConfig.alloyer, LoadConfig.emExtraStorageConfig.alloyer)
            .withComputerSupport("alloyer")
            .build();

    public static final ExtraMachine.ExtraFactoryMachine<TileEntityAlloyer> ADVANCED_ALLOYER = ExtraMachine.ExtraMachineBuilder
            .createExtraFactoryMachine(() -> EMTileEntityTypes.ALLOYER, EvolvedMekanismLang.DESCRIPTION_ALLOYER, EMFactoryType.ALLOYING)
            .withGui(() -> EMContainerTypes.ALLOYER)
            .withSound(MekanismSounds.COMBINER)
            .withEnergyConfig(LoadConfig.emExtraUsageConfig.alloyer, LoadConfig.emExtraStorageConfig.alloyer)
            .withComputerSupport("alloyer")
            .build();

    public static final EMExtraMachine.EMExtraFactoryMachine<TileEntityEnergizedSmelter> ENERGIZED_SMELTER = EMExtraMachine.EMExtraMachineBuilder
            .createEMExtraFactoryMachine(() -> MekanismTileEntityTypes.ENERGIZED_SMELTER, MekanismLang.DESCRIPTION_ENERGIZED_SMELTER, EMExtraFactoryType.SMELTING)
            .withGui(() -> MekanismContainerTypes.ENERGIZED_SMELTER)
            .withSound(MekanismSounds.ENERGIZED_SMELTER)
            .withEnergyConfig(MekanismConfig.usage.energizedSmelter, MekanismConfig.storage.energizedSmelter)
            .withComputerSupport("energizedSmelter")
            .build();

    public static final EMExtraMachine.EMExtraFactoryMachine<TileEntityEnrichmentChamber> ENRICHMENT_CHAMBER = EMExtraMachine.EMExtraMachineBuilder
            .createEMExtraFactoryMachine(() -> MekanismTileEntityTypes.ENRICHMENT_CHAMBER, MekanismLang.DESCRIPTION_ENRICHMENT_CHAMBER, EMExtraFactoryType.ENRICHING)
            .withGui(() -> MekanismContainerTypes.ENRICHMENT_CHAMBER)
            .withSound(MekanismSounds.ENRICHMENT_CHAMBER)
            .withEnergyConfig(MekanismConfig.usage.enrichmentChamber, MekanismConfig.storage.enrichmentChamber)
            .withComputerSupport("enrichmentChamber")
            .build();

    public static final EMExtraMachine.EMExtraFactoryMachine<TileEntityCrusher> CRUSHER = EMExtraMachine.EMExtraMachineBuilder
            .createEMExtraFactoryMachine(() -> MekanismTileEntityTypes.CRUSHER, MekanismLang.DESCRIPTION_CRUSHER, EMExtraFactoryType.CRUSHING)
            .withGui(() -> MekanismContainerTypes.CRUSHER)
            .withSound(MekanismSounds.CRUSHER)
            .withEnergyConfig(MekanismConfig.usage.crusher, MekanismConfig.storage.crusher)
            .withComputerSupport("crusher")
            .build();

    public static final EMExtraMachine.EMExtraFactoryMachine<TileEntityOsmiumCompressor> OSMIUM_COMPRESSOR = EMExtraMachine.EMExtraMachineBuilder
            .createEMExtraFactoryMachine(() -> MekanismTileEntityTypes.OSMIUM_COMPRESSOR, MekanismLang.DESCRIPTION_OSMIUM_COMPRESSOR, EMExtraFactoryType.COMPRESSING)
            .withGui(() -> MekanismContainerTypes.OSMIUM_COMPRESSOR)
            .withSound(MekanismSounds.OSMIUM_COMPRESSOR)
            .withEnergyConfig(MekanismConfig.usage.osmiumCompressor, MekanismConfig.storage.osmiumCompressor)
            .withSupportedUpgrades(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, Upgrade.GAS, ExtraUpgrade.STACK, ExtraUpgrade.CREATIVE))
            .withComputerSupport("osmiumCompressor")
            .build();

    public static final EMExtraMachine.EMExtraFactoryMachine<TileEntityCombiner> COMBINER = EMExtraMachine.EMExtraMachineBuilder
            .createEMExtraFactoryMachine(() -> MekanismTileEntityTypes.COMBINER, MekanismLang.DESCRIPTION_COMBINER, EMExtraFactoryType.COMBINING)
            .withGui(() -> MekanismContainerTypes.COMBINER)
            .withSound(MekanismSounds.COMBINER)
            .withEnergyConfig(MekanismConfig.usage.combiner, MekanismConfig.storage.combiner)
            .withComputerSupport("combiner")
            .build();

    public static final EMExtraMachine.EMExtraFactoryMachine<TileEntityPurificationChamber> PURIFICATION_CHAMBER = EMExtraMachine.EMExtraMachineBuilder
            .createEMExtraFactoryMachine(() -> MekanismTileEntityTypes.PURIFICATION_CHAMBER, MekanismLang.DESCRIPTION_PURIFICATION_CHAMBER, EMExtraFactoryType.PURIFYING)
            .withGui(() -> MekanismContainerTypes.PURIFICATION_CHAMBER)
            .withSound(MekanismSounds.PURIFICATION_CHAMBER)
            .withEnergyConfig(MekanismConfig.usage.purificationChamber, MekanismConfig.storage.purificationChamber)
            .withSupportedUpgrades(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, Upgrade.GAS, ExtraUpgrade.STACK, ExtraUpgrade.CREATIVE))
            .withComputerSupport("purificationChamber")
            .build();

    public static final EMExtraMachine.EMExtraFactoryMachine<TileEntityChemicalInjectionChamber> CHEMICAL_INJECTION_CHAMBER = EMExtraMachine.EMExtraMachineBuilder
            .createEMExtraFactoryMachine(() -> MekanismTileEntityTypes.CHEMICAL_INJECTION_CHAMBER, MekanismLang.DESCRIPTION_CHEMICAL_INJECTION_CHAMBER, EMExtraFactoryType.INJECTING)
            .withGui(() -> MekanismContainerTypes.CHEMICAL_INJECTION_CHAMBER)
            .withSound(MekanismSounds.CHEMICAL_INJECTION_CHAMBER)
            .withEnergyConfig(MekanismConfig.usage.chemicalInjectionChamber, MekanismConfig.storage.chemicalInjectionChamber)
            .withSupportedUpgrades(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, Upgrade.GAS, ExtraUpgrade.STACK, ExtraUpgrade.CREATIVE))
            .withComputerSupport("chemicalInjectionChamber")
            .build();

    public static final EMExtraMachine.EMExtraFactoryMachine<TileEntityMetallurgicInfuser> METALLURGIC_INFUSER = EMExtraMachine.EMExtraMachineBuilder
            .createEMExtraFactoryMachine(() -> MekanismTileEntityTypes.METALLURGIC_INFUSER, MekanismLang.DESCRIPTION_METALLURGIC_INFUSER, EMExtraFactoryType.INFUSING)
            .withGui(() -> MekanismContainerTypes.METALLURGIC_INFUSER)
            .withSound(MekanismSounds.METALLURGIC_INFUSER)
            .withEnergyConfig(MekanismConfig.usage.metallurgicInfuser, MekanismConfig.storage.metallurgicInfuser)
            .withCustomShape(BlockShapes.METALLURGIC_INFUSER)
            .withComputerSupport("metallurgicInfuser")
            .build();

    public static final EMExtraMachine.EMExtraFactoryMachine<TileEntityPrecisionSawmill> PRECISION_SAWMILL = EMExtraMachine.EMExtraMachineBuilder
            .createEMExtraFactoryMachine(() -> MekanismTileEntityTypes.PRECISION_SAWMILL, MekanismLang.DESCRIPTION_PRECISION_SAWMILL, EMExtraFactoryType.SAWING)
            .withGui(() -> MekanismContainerTypes.PRECISION_SAWMILL)
            .withSound(MekanismSounds.PRECISION_SAWMILL)
            .withEnergyConfig(MekanismConfig.usage.precisionSawmill, MekanismConfig.storage.precisionSawmill)
            .withComputerSupport("precisionSawmill")
            .build();

    // Induction Cells
    public static final BlockTypeTile<TileEntityEMExtraInductionCell> ABSOLUTE_OVERCLOCKED_INDUCTION_CELL = createInductionCell(EMExtraICTier.ABSOLUTE_OVERCLOCKED, () -> EMExtrasTileEntityTypes.ABSOLUTE_OVERCLOCKED_INDUCTION_CELL);
    public static final BlockTypeTile<TileEntityEMExtraInductionCell> SUPREME_QUANTUM_INDUCTION_CELL = createInductionCell(EMExtraICTier.SUPREME_QUANTUM, () -> EMExtrasTileEntityTypes.SUPREME_QUANTUM_INDUCTION_CELL);
    public static final BlockTypeTile<TileEntityEMExtraInductionCell> COSMIC_DENSE_INDUCTION_CELL = createInductionCell(EMExtraICTier.COSMIC_DENSE, () -> EMExtrasTileEntityTypes.COSMIC_DENSE_INDUCTION_CELL);
    public static final BlockTypeTile<TileEntityEMExtraInductionCell> INFINITE_MULTIVERSAL_INDUCTION_CELL = createInductionCell(EMExtraICTier.INFINITE_MULTIVERSAL, () -> EMExtrasTileEntityTypes.INFINITE_MULTIVERSAL_INDUCTION_CELL);
    // Induction Provide
    public static final BlockTypeTile<TileEntityEMExtraInductionProvider> ABSOLUTE_OVERCLOCKED_INDUCTION_PROVIDER = createInductionProvider(EMExtraIPTier.ABSOLUTE_OVERCLOCKED, () -> EMExtrasTileEntityTypes.ABSOLUTE_OVERCLOCKED_INDUCTION_PROVIDER);
    public static final BlockTypeTile<TileEntityEMExtraInductionProvider> SUPREME_QUANTUM_INDUCTION_PROVIDER = createInductionProvider(EMExtraIPTier.SUPREME_QUANTUM, () -> EMExtrasTileEntityTypes.SUPREME_QUANTUM_INDUCTION_PROVIDER);
    public static final BlockTypeTile<TileEntityEMExtraInductionProvider> COSMIC_DENSE_INDUCTION_PROVIDER = createInductionProvider(EMExtraIPTier.COSMIC_DENSE, () -> EMExtrasTileEntityTypes.COSMIC_DENSE_INDUCTION_PROVIDER);
    public static final BlockTypeTile<TileEntityEMExtraInductionProvider> INFINITE_MULTIVERSAL_INDUCTION_PROVIDER = createInductionProvider(EMExtraIPTier.INFINITE_MULTIVERSAL, () -> EMExtrasTileEntityTypes.INFINITE_MULTIVERSAL_INDUCTION_PROVIDER);

    static {
        for (EMExtraFactoryTier tier : EMExtraEnumUtils.EMEXTRA_FACTORY_TIERS) {
            for (EMExtraFactoryType type : EMExtraEnumUtils.EMEXTRA_FACTORY_TYPES) {
                if (type != EMExtraFactoryType.ADVANCED_ALLOYING) {
                    FACTORIES.put(tier, type, EMExtraFactory.EMExtraFactoryBuilder.createFactory(() -> EMExtrasTileEntityTypes.getEMExtraFactoryTile(tier, type), type, tier).build());
                }
            }
        }
        for (ExtraFactoryTier tier : ExtraEnumUtils.EXTRA_FACTORY_TIERS) {
            ADVANCED_FACTORIES.put(tier, EMFactoryType.ALLOYING, ExtraFactory.AdvancedFactoryBuilder.createFactory(() -> EMExtrasTileEntityTypes.getExtraFactoryTile(tier, EMFactoryType.ALLOYING), EMFactoryType.ALLOYING, tier).build());
        }
    }

    public static EMExtraFactory<?> getEMExtraFactory(EMExtraFactoryTier tier, EMExtraFactoryType type) {
        return FACTORIES.get(tier, type);
    }

    public static ExtraFactory<?> getExtraFactory(ExtraFactoryTier tier, FactoryType type) {
        return ADVANCED_FACTORIES.get(tier, type);
    }

    private static <TILE extends TileEntityEMExtraInductionCell> BlockTypeTile<TILE> createInductionCell(EMExtraICTier tier, Supplier<TileEntityTypeRegistryObject<TILE>> tile) {
        return BlockTypeTile.BlockTileBuilder.createBlock(tile, MekanismLang.DESCRIPTION_INDUCTION_CELL)
                .withEnergyConfig(tier::getMaxEnergy)
                .with(new EMExtraAttributeTier<>(tier))
                .internalMultiblock()
                .build();
    }

    private static <TILE extends TileEntityEMExtraInductionProvider> BlockTypeTile<TILE> createInductionProvider(EMExtraIPTier tier, Supplier<TileEntityTypeRegistryObject<TILE>> tile) {
        return BlockTypeTile.BlockTileBuilder.createBlock(tile, MekanismLang.DESCRIPTION_INDUCTION_PROVIDER)
                .with(new EMExtraAttributeTier<>(tier))
                .internalMultiblock()
                .build();
    }
}
