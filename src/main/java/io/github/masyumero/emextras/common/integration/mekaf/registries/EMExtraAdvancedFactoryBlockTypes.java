package io.github.masyumero.emextras.common.integration.mekaf.registries;

import com.jerry.mekextras.common.block.attribute.ExtraAttributeUpgradeSupport;
import io.github.masyumero.emextras.common.content.blocktype.EMExtraMachine;
import io.github.masyumero.emextras.common.integration.mekaf.content.blocktype.EMExtraAdvancedFactory;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import io.github.masyumero.emextras.common.util.EMExtraEnumUtils;

import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.AttributeHasBounding;
import mekanism.common.block.attribute.AttributeSideConfig;
import mekanism.common.block.attribute.AttributeUpgradeSupport;
import mekanism.common.config.MekanismConfig;
import mekanism.common.registries.MekanismContainerTypes;
import mekanism.common.registries.MekanismSounds;
import mekanism.common.registries.MekanismTileEntityTypes;
import mekanism.common.tile.machine.*;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jerry.mekaf.common.content.blocktype.AdvancedFactoryType;
import com.jerry.mekmm.common.util.MoreMachineEnumUtils;

public class EMExtraAdvancedFactoryBlockTypes {

    private EMExtraAdvancedFactoryBlockTypes() {}

    private static final Table<EMExtraFactoryTier, AdvancedFactoryType, EMExtraAdvancedFactory<?>> AF_FACTORIES = HashBasedTable.create();

    // Chemical Oxidizer
    public static final EMExtraMachine.EMExtraFactoryMachine<TileEntityChemicalOxidizer> CHEMICAL_OXIDIZER = EMExtraMachine.EMExtraMachineBuilder
            .createEMExtraAdvancedFactoryMachine(() -> MekanismTileEntityTypes.CHEMICAL_OXIDIZER, MekanismLang.DESCRIPTION_CHEMICAL_OXIDIZER, AdvancedFactoryType.OXIDIZING)
            .withGui(() -> MekanismContainerTypes.CHEMICAL_OXIDIZER)
            .withSound(MekanismSounds.CHEMICAL_OXIDIZER)
            .withEnergyConfig(MekanismConfig.usage.chemicalOxidizer, MekanismConfig.storage.chemicalOxidizer)
            .with(ExtraAttributeUpgradeSupport.EXTRA_MACHINE_UPGRADES)
            .build();

    // Chemical Dissolution Chamber
    public static final EMExtraMachine.EMExtraFactoryMachine<TileEntityChemicalDissolutionChamber> CHEMICAL_DISSOLUTION_CHAMBER = EMExtraMachine.EMExtraMachineBuilder
            .createEMExtraAdvancedFactoryMachine(() -> MekanismTileEntityTypes.CHEMICAL_DISSOLUTION_CHAMBER, MekanismLang.DESCRIPTION_CHEMICAL_DISSOLUTION_CHAMBER, AdvancedFactoryType.DISSOLVING)
            .withGui(() -> MekanismContainerTypes.CHEMICAL_DISSOLUTION_CHAMBER)
            .withSound(MekanismSounds.CHEMICAL_DISSOLUTION_CHAMBER)
            .withEnergyConfig(MekanismConfig.usage.chemicalDissolutionChamber, MekanismConfig.storage.chemicalDissolutionChamber)
            .with(ExtraAttributeUpgradeSupport.EXTRA_ADVANCED_MACHINE_UPGRADES)
            .build();

    // Chemical Washer
    public static final EMExtraMachine.EMExtraFactoryMachine<TileEntityChemicalWasher> CHEMICAL_WASHER = EMExtraMachine.EMExtraMachineBuilder
            .createEMExtraAdvancedFactoryMachine(() -> MekanismTileEntityTypes.CHEMICAL_WASHER, MekanismLang.DESCRIPTION_CHEMICAL_WASHER, AdvancedFactoryType.WASHING)
            .withGui(() -> MekanismContainerTypes.CHEMICAL_WASHER)
            .withSound(MekanismSounds.CHEMICAL_WASHER)
            .withEnergyConfig(MekanismConfig.usage.chemicalWasher, MekanismConfig.storage.chemicalWasher)
            .with(ExtraAttributeUpgradeSupport.EXTRA_MACHINE_NO_STACK_UPGRADES)
            .build();

    // Pressurized Reaction Chamber
    public static final EMExtraMachine.EMExtraFactoryMachine<TileEntityPressurizedReactionChamber> PRESSURIZED_REACTION_CHAMBER = EMExtraMachine.EMExtraMachineBuilder
            .createEMExtraAdvancedFactoryMachine(() -> MekanismTileEntityTypes.PRESSURIZED_REACTION_CHAMBER, MekanismLang.DESCRIPTION_PRESSURIZED_REACTION_CHAMBER, AdvancedFactoryType.PRESSURISED_REACTING)
            .withGui(() -> MekanismContainerTypes.PRESSURIZED_REACTION_CHAMBER)
            .withSound(MekanismSounds.PRESSURIZED_REACTION_CHAMBER)
            .withEnergyConfig(MekanismConfig.usage.pressurizedReactionBase, MekanismConfig.storage.pressurizedReactionBase)
            .with(ExtraAttributeUpgradeSupport.EXTRA_MACHINE_UPGRADES)
            .build();

    // Chemical Crystallizer
    public static final EMExtraMachine.EMExtraFactoryMachine<TileEntityChemicalCrystallizer> CHEMICAL_CRYSTALLIZER = EMExtraMachine.EMExtraMachineBuilder
            .createEMExtraAdvancedFactoryMachine(() -> MekanismTileEntityTypes.CHEMICAL_CRYSTALLIZER, MekanismLang.DESCRIPTION_CHEMICAL_CRYSTALLIZER, AdvancedFactoryType.CRYSTALLIZING)
            .withGui(() -> MekanismContainerTypes.CHEMICAL_CRYSTALLIZER)
            .withSound(MekanismSounds.CHEMICAL_CRYSTALLIZER)
            .withEnergyConfig(MekanismConfig.usage.chemicalCrystallizer, MekanismConfig.storage.chemicalCrystallizer)
            .with(ExtraAttributeUpgradeSupport.EXTRA_MACHINE_UPGRADES)
            .build();

    // Isotopic Centrifuge
    public static final EMExtraMachine.EMExtraFactoryMachine<TileEntityIsotopicCentrifuge> ISOTOPIC_CENTRIFUGE = EMExtraMachine.EMExtraMachineBuilder
            .createEMExtraAdvancedFactoryMachine(() -> MekanismTileEntityTypes.ISOTOPIC_CENTRIFUGE, MekanismLang.DESCRIPTION_ISOTOPIC_CENTRIFUGE, AdvancedFactoryType.CENTRIFUGING)
            .withGui(() -> MekanismContainerTypes.ISOTOPIC_CENTRIFUGE)
            .withEnergyConfig(MekanismConfig.usage.isotopicCentrifuge, MekanismConfig.storage.isotopicCentrifuge)
            .with(ExtraAttributeUpgradeSupport.EXTRA_MACHINE_NO_STACK_UPGRADES)
            .withSound(MekanismSounds.ISOTOPIC_CENTRIFUGE)
            .with(AttributeHasBounding.ABOVE_ONLY)
            .build();

    // Nutritional Liquifier
    public static final EMExtraMachine.EMExtraFactoryMachine<TileEntityNutritionalLiquifier> NUTRITIONAL_LIQUIFIER = EMExtraMachine.EMExtraMachineBuilder
            .createEMExtraAdvancedFactoryMachine(() -> MekanismTileEntityTypes.NUTRITIONAL_LIQUIFIER, MekanismLang.DESCRIPTION_NUTRITIONAL_LIQUIFIER, AdvancedFactoryType.LIQUIFYING)
            .withGui(() -> MekanismContainerTypes.NUTRITIONAL_LIQUIFIER)
            .withEnergyConfig(MekanismConfig.usage.nutritionalLiquifier, MekanismConfig.storage.nutritionalLiquifier)
            .with(ExtraAttributeUpgradeSupport.EXTRA_MACHINE_UPGRADES)
            .withSound(MekanismSounds.NUTRITIONAL_LIQUIFIER)
            .build();

    // Pigment Extractor
    public static final EMExtraMachine.EMExtraFactoryMachine<TileEntityPigmentExtractor> PIGMENT_EXTRACTOR = EMExtraMachine.EMExtraMachineBuilder
            .createEMExtraAdvancedFactoryMachine(() -> MekanismTileEntityTypes.PIGMENT_EXTRACTOR, MekanismLang.DESCRIPTION_PIGMENT_EXTRACTOR, AdvancedFactoryType.PIGMENT_EXTRACTING)
            .withGui(() -> MekanismContainerTypes.PIGMENT_EXTRACTOR)
            .withSound(MekanismSounds.PIGMENT_EXTRACTOR)
            .withEnergyConfig(MekanismConfig.usage.pigmentExtractor, MekanismConfig.storage.pigmentExtractor)
            .with(AttributeUpgradeSupport.DEFAULT_MACHINE_UPGRADES)
            .with(AttributeSideConfig.ADVANCED_ELECTRIC_MACHINE)
            .withComputerSupport("pigmentExtractor")
            .build();

    // Painting Machine
    public static final EMExtraMachine.EMExtraFactoryMachine<TileEntityPaintingMachine> PAINTING_MACHINE = EMExtraMachine.EMExtraMachineBuilder
            .createEMExtraAdvancedFactoryMachine(() -> MekanismTileEntityTypes.PAINTING_MACHINE, MekanismLang.DESCRIPTION_PAINTING_MACHINE, AdvancedFactoryType.PAINTING)
            .withGui(() -> MekanismContainerTypes.PAINTING_MACHINE)
            .withSound(MekanismSounds.PAINTING_MACHINE)
            .withEnergyConfig(MekanismConfig.usage.paintingMachine, MekanismConfig.storage.paintingMachine)
            .with(AttributeUpgradeSupport.DEFAULT_MACHINE_UPGRADES)
            .with(AttributeSideConfig.ADVANCED_ELECTRIC_MACHINE)
            .withComputerSupport("paintingMachine")
            .build();

    static {
        for (EMExtraFactoryTier tier : EMExtraEnumUtils.EMEXTRA_FACTORY_TIERS) {
            for (AdvancedFactoryType type : MoreMachineEnumUtils.ADVANCED_FACTORY_TYPES) {
                AF_FACTORIES.put(tier, type, EMExtraAdvancedFactory.EMExtraAdvancedFactoryBuilder.createAdvancedFactory(() -> EMExtraAdvancedFactoryTileEntityTypes.getEMExtraAdvancedFactoryTile(tier, type), type, tier).build());
            }
        }
    }

    public static EMExtraAdvancedFactory<?> getEMExtraAdvancedFactory(EMExtraFactoryTier tier, AdvancedFactoryType type) {
        return AF_FACTORIES.get(tier, type);
    }
}