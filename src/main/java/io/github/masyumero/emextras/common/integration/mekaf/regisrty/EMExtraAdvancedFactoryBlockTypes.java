package io.github.masyumero.emextras.common.integration.mekaf.regisrty;

import com.jerry.mekanism_extras.api.ExtraUpgrade;
import io.github.masyumero.emextras.common.content.blocktype.EMExtraMachine.EMExtraFactoryMachine;
import io.github.masyumero.emextras.common.content.blocktype.EMExtraMachine.EMExtraMachineBuilder;
import io.github.masyumero.emextras.common.integration.mekaf.content.blocktype.EMExtraAdvancedFactory;
import io.github.masyumero.emextras.common.integration.mekaf.content.blocktype.EMExtraAdvancedFactory.EMExtraAdvancedFactoryBuilder;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import io.github.masyumero.emextras.common.util.EMExtraEnumUtils;
import mekanism.api.Upgrade;
import mekanism.common.MekanismLang;
import mekanism.common.config.MekanismConfig;
import mekanism.common.registries.MekanismSounds;
import mekanism.common.registries.MekanismTileEntityTypes;
import mekanism.common.tile.machine.TileEntityChemicalCrystallizer;
import mekanism.common.tile.machine.TileEntityChemicalDissolutionChamber;
import mekanism.common.tile.machine.TileEntityChemicalOxidizer;
import mekanism.common.tile.machine.TileEntityChemicalWasher;
import mekanism.common.tile.machine.TileEntityIsotopicCentrifuge;
import mekanism.common.tile.machine.TileEntityNutritionalLiquifier;
import mekanism.common.tile.machine.TileEntityPaintingMachine;
import mekanism.common.tile.machine.TileEntityPigmentExtractor;
import mekanism.common.tile.machine.TileEntityPressurizedReactionChamber;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jerry.mekaf.common.content.blocktype.AdvancedFactoryType;
import com.jerry.mekmm.common.util.MoreMachineEnumUtils;

import java.util.EnumSet;

public class EMExtraAdvancedFactoryBlockTypes {

    private EMExtraAdvancedFactoryBlockTypes() {
    }

    private static final Table<EMExtraFactoryTier, AdvancedFactoryType, EMExtraAdvancedFactory<?>> FACTORIES = HashBasedTable.create();

    public static final EMExtraFactoryMachine<TileEntityChemicalOxidizer> CHEMICAL_OXIDIZER = EMExtraMachineBuilder
            .createEMExtraAdvancedFactoryMachine(() -> MekanismTileEntityTypes.CHEMICAL_OXIDIZER, MekanismLang.DESCRIPTION_CHEMICAL_OXIDIZER, AdvancedFactoryType.OXIDIZING)
            .withSound(MekanismSounds.CHEMICAL_OXIDIZER)
            .withEnergyConfig(MekanismConfig.usage.oxidationChamber, MekanismConfig.storage.oxidationChamber)
            .withSupportedUpgrades(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, ExtraUpgrade.STACK, ExtraUpgrade.CREATIVE))
            .build();

    public static final EMExtraFactoryMachine<TileEntityChemicalDissolutionChamber> CHEMICAL_DISSOLUTION_CHAMBER = EMExtraMachineBuilder
            .createEMExtraAdvancedFactoryMachine(() -> MekanismTileEntityTypes.CHEMICAL_DISSOLUTION_CHAMBER, MekanismLang.DESCRIPTION_CHEMICAL_DISSOLUTION_CHAMBER, AdvancedFactoryType.DISSOLVING)
            .withSound(MekanismSounds.CHEMICAL_DISSOLUTION_CHAMBER)
            .withEnergyConfig(MekanismConfig.usage.chemicalDissolutionChamber, MekanismConfig.storage.chemicalDissolutionChamber)
            .withSupportedUpgrades(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, Upgrade.GAS, ExtraUpgrade.STACK, ExtraUpgrade.CREATIVE))
            .build();

    public static final EMExtraFactoryMachine<TileEntityChemicalWasher> CHEMICAL_WASHER = EMExtraMachineBuilder
            .createEMExtraAdvancedFactoryMachine(() -> MekanismTileEntityTypes.CHEMICAL_WASHER, MekanismLang.DESCRIPTION_CHEMICAL_WASHER, AdvancedFactoryType.WASHING)
            .withSound(MekanismSounds.CHEMICAL_WASHER)
            .withEnergyConfig(MekanismConfig.usage.chemicalWasher, MekanismConfig.storage.chemicalWasher)
            .build();

    public static final EMExtraFactoryMachine<TileEntityChemicalCrystallizer> CHEMICAL_CRYSTALLIZER = EMExtraMachineBuilder
            .createEMExtraAdvancedFactoryMachine(() -> MekanismTileEntityTypes.CHEMICAL_CRYSTALLIZER, MekanismLang.DESCRIPTION_CHEMICAL_CRYSTALLIZER, AdvancedFactoryType.CRYSTALLIZING)
            .withSound(MekanismSounds.CHEMICAL_CRYSTALLIZER)
            .withEnergyConfig(MekanismConfig.usage.chemicalCrystallizer, MekanismConfig.storage.chemicalCrystallizer)
            .build();

    public static final EMExtraFactoryMachine<TileEntityPressurizedReactionChamber> PRESSURIZED_REACTION_CHAMBER = EMExtraMachineBuilder
            .createEMExtraAdvancedFactoryMachine(() -> MekanismTileEntityTypes.PRESSURIZED_REACTION_CHAMBER, MekanismLang.DESCRIPTION_PRESSURIZED_REACTION_CHAMBER, AdvancedFactoryType.PRESSURISED_REACTING)
            .withSound(MekanismSounds.PRESSURIZED_REACTION_CHAMBER)
            .withEnergyConfig(MekanismConfig.usage.pressurizedReactionBase, MekanismConfig.storage.pressurizedReactionBase)
            .withSupportedUpgrades(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, ExtraUpgrade.STACK, ExtraUpgrade.CREATIVE))
            .build();

    public static final EMExtraFactoryMachine<TileEntityIsotopicCentrifuge> ISOTOPIC_CENTRIFUGE = EMExtraMachineBuilder
            .createEMExtraAdvancedFactoryMachine(() -> MekanismTileEntityTypes.ISOTOPIC_CENTRIFUGE, MekanismLang.DESCRIPTION_ISOTOPIC_CENTRIFUGE, AdvancedFactoryType.CENTRIFUGING)
            .withSound(MekanismSounds.ISOTOPIC_CENTRIFUGE)
            .withEnergyConfig(MekanismConfig.usage.isotopicCentrifuge, MekanismConfig.storage.isotopicCentrifuge)
            .build();

    public static final EMExtraFactoryMachine<TileEntityNutritionalLiquifier> NUTRITIONAL_LIQUIFIER = EMExtraMachineBuilder
            .createEMExtraAdvancedFactoryMachine(() -> MekanismTileEntityTypes.NUTRITIONAL_LIQUIFIER, MekanismLang.DESCRIPTION_NUTRITIONAL_LIQUIFIER, AdvancedFactoryType.LIQUIFYING)
            .withSound(MekanismSounds.NUTRITIONAL_LIQUIFIER)
            .withEnergyConfig(MekanismConfig.usage.nutritionalLiquifier, MekanismConfig.storage.nutritionalLiquifier)
            .withSupportedUpgrades(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, ExtraUpgrade.STACK, ExtraUpgrade.CREATIVE))
            .build();

    public static final EMExtraFactoryMachine<TileEntityPigmentExtractor> PIGMENT_EXTRACTOR = EMExtraMachineBuilder
            .createEMExtraAdvancedFactoryMachine(() -> MekanismTileEntityTypes.PIGMENT_EXTRACTOR, MekanismLang.DESCRIPTION_PIGMENT_EXTRACTOR, AdvancedFactoryType.PIGMENT_EXTRACTING)
            .withSound(MekanismSounds.PIGMENT_EXTRACTOR)
            .withEnergyConfig(MekanismConfig.usage.pigmentExtractor, MekanismConfig.storage.pigmentExtractor)
            .withSupportedUpgrades(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, ExtraUpgrade.STACK, ExtraUpgrade.CREATIVE))
            .build();

    public static final EMExtraFactoryMachine<TileEntityPaintingMachine> PAINTING_MACHINE = EMExtraMachineBuilder
            .createEMExtraAdvancedFactoryMachine(() -> MekanismTileEntityTypes.PAINTING_MACHINE, MekanismLang.DESCRIPTION_PAINTING_MACHINE, AdvancedFactoryType.PAINTING)
            .withSound(MekanismSounds.PAINTING_MACHINE)
            .withEnergyConfig(MekanismConfig.usage.paintingMachine, MekanismConfig.storage.paintingMachine)
            .withSupportedUpgrades(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, ExtraUpgrade.STACK, ExtraUpgrade.CREATIVE))
            .build();

    static {
        for (EMExtraFactoryTier tier : EMExtraEnumUtils.EMEXTRA_FACTORY_TIERS) {
            for (AdvancedFactoryType type : MoreMachineEnumUtils.ADVANCED_FACTORY_TYPES) {
                FACTORIES.put(tier, type, EMExtraAdvancedFactoryBuilder.createAdvancedFactory(() -> EMExtraAdvancedFactoryTileEntityTypes.getEMExtraAdvancedFactoryTile(tier, type), type, tier).build());
            }
        }
    }

    public static EMExtraAdvancedFactory<?> getEMExtraAdvancedFactory(EMExtraFactoryTier tier, AdvancedFactoryType type) {
        return FACTORIES.get(tier, type);
    }
}
