package io.github.masyumero.emextras.common.integration.mekmm.registries;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jerry.mekextras.common.block.attribute.ExtraAttributeUpgradeSupport;
import com.jerry.mekmm.common.MoreMachineLang;
import com.jerry.mekmm.common.config.MoreMachineConfig;
import com.jerry.mekmm.common.content.blocktype.MoreMachineFactoryType;
import com.jerry.mekmm.common.registries.MoreMachineContainerTypes;
import com.jerry.mekmm.common.registries.MoreMachineTileEntityTypes;
import com.jerry.mekmm.common.tile.machine.*;
import com.jerry.mekmm.common.util.MoreMachineEnumUtils;
import io.github.masyumero.emextras.common.content.blocktype.EMExtraMachine;
import io.github.masyumero.emextras.common.integration.mekmm.content.blocktype.EMExtraMoreMachineFactory;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import io.github.masyumero.emextras.common.util.EMExtraEnumUtils;
import mekanism.common.block.attribute.AttributeHasBounding;
import mekanism.common.block.attribute.AttributeSideConfig;
import mekanism.common.registries.MekanismSounds;

public class EMExtraMoreMachineBlockTypes {

    private EMExtraMoreMachineBlockTypes() {}

    private static final Table<EMExtraFactoryTier, MoreMachineFactoryType, EMExtraMoreMachineFactory<?>> MM_FACTORIES = HashBasedTable.create();

    // Recycler
    public static final EMExtraMachine.EMExtraFactoryMachine<TileEntityRecycler> RECYCLER = EMExtraMachine.EMExtraMachineBuilder
            .createEMExtraMoreMachineFactoryMachine(() -> MoreMachineTileEntityTypes.RECYCLER, MoreMachineLang.DESCRIPTION_RECYCLER, MoreMachineFactoryType.RECYCLING)
            .withGui(() -> MoreMachineContainerTypes.RECYCLER)
            .withSound(MekanismSounds.PRECISION_SAWMILL)
            .withEnergyConfig(MoreMachineConfig.usage.recycler, MoreMachineConfig.storage.recycler)
            .with(ExtraAttributeUpgradeSupport.EXTRA_MACHINE_NO_STACK_UPGRADES)
            .build();

    // Planting Station
    public static final EMExtraMachine.EMExtraFactoryMachine<TileEntityPlantingStation> PLANTING_STATION = EMExtraMachine.EMExtraMachineBuilder
            .createEMExtraMoreMachineFactoryMachine(() -> MoreMachineTileEntityTypes.PLANTING_STATION, MoreMachineLang.DESCRIPTION_PLANTING_STATION, MoreMachineFactoryType.PLANTING_STATION)
            .withGui(() -> MoreMachineContainerTypes.PLANTING_STATION)
            .withEnergyConfig(MoreMachineConfig.usage.plantingStation, MoreMachineConfig.storage.plantingStation)
            .with(ExtraAttributeUpgradeSupport.EXTRA_ADVANCED_MACHINE_UPGRADES)
            .withSound(MekanismSounds.ENRICHMENT_CHAMBER)
            .with(AttributeHasBounding.ABOVE_ONLY)
            .build();

    // CNC Stamper
    public static final EMExtraMachine.EMExtraFactoryMachine<TileEntityStamper> CNC_STAMPER = EMExtraMachine.EMExtraMachineBuilder
            .createEMExtraMoreMachineFactoryMachine(() -> MoreMachineTileEntityTypes.CNC_STAMPER, MoreMachineLang.DESCRIPTION_CNC_STAMPER, MoreMachineFactoryType.CNC_STAMPING)
            .withGui(() -> MoreMachineContainerTypes.CNC_STAMPER)
            .withEnergyConfig(MoreMachineConfig.usage.cnc_stamper, MoreMachineConfig.storage.cnc_stamper)
            .with(ExtraAttributeUpgradeSupport.EXTRA_MACHINE_UPGRADES)
            .withSound(MekanismSounds.CRUSHER)
            .build();

    // CNC Lathe
    public static final EMExtraMachine.EMExtraFactoryMachine<TileEntityLathe> CNC_LATHE = EMExtraMachine.EMExtraMachineBuilder
            .createEMExtraMoreMachineFactoryMachine(() -> MoreMachineTileEntityTypes.CNC_LATHE, MoreMachineLang.DESCRIPTION_CNC_LATHE, MoreMachineFactoryType.CNC_LATHING)
            .withGui(() -> MoreMachineContainerTypes.CNC_LATHE)
            .withEnergyConfig(MoreMachineConfig.usage.cnc_lathe, MoreMachineConfig.storage.cnc_lathe)
            .with(ExtraAttributeUpgradeSupport.EXTRA_MACHINE_UPGRADES)
            .withSound(MekanismSounds.OSMIUM_COMPRESSOR)
            .build();

    // CNC Rolling Mill
    public static final EMExtraMachine.EMExtraFactoryMachine<TileEntityRollingMill> CNC_ROLLING_MILL = EMExtraMachine.EMExtraMachineBuilder
            .createEMExtraMoreMachineFactoryMachine(() -> MoreMachineTileEntityTypes.CNC_ROLLING_MILL, MoreMachineLang.DESCRIPTION_CNC_ROLLING_MILL, MoreMachineFactoryType.CNC_ROLLING_MILL)
            .withGui(() -> MoreMachineContainerTypes.CNC_ROLLING_MILL)
            .withEnergyConfig(MoreMachineConfig.usage.cnc_rollingMill, MoreMachineConfig.storage.cnc_rollingMill)
            .with(ExtraAttributeUpgradeSupport.EXTRA_MACHINE_UPGRADES)
            .withSound(MekanismSounds.COMBINER)
            .build();

    // Presser
    public static final EMExtraMachine.EMExtraFactoryMachine<TileEntityPresser> PRESSER = EMExtraMachine.EMExtraMachineBuilder
            .createEMExtraMoreMachineFactoryMachine(() -> MoreMachineTileEntityTypes.PRESSER, MoreMachineLang.DESCRIPTION_PRESSER, MoreMachineFactoryType.PRESSING)
            .withGui(() -> MoreMachineContainerTypes.PRESSER)
            .withSound(MekanismSounds.COMBINER)
            .withEnergyConfig(MoreMachineConfig.usage.presser, MoreMachineConfig.storage.presser)
            .with(AttributeSideConfig.ELECTRIC_MACHINE)
            .withComputerSupport("presser")
            .build();

    // Replicator
    public static final EMExtraMachine.EMExtraFactoryMachine<TileEntityReplicator> REPLICATOR = EMExtraMachine.EMExtraMachineBuilder
            .createEMExtraMoreMachineFactoryMachine(() -> MoreMachineTileEntityTypes.REPLICATOR, MoreMachineLang.DESCRIPTION_REPLICATOR, MoreMachineFactoryType.REPLICATING)
            .withGui(() -> MoreMachineContainerTypes.REPLICATOR)
            .withEnergyConfig(MoreMachineConfig.usage.itemReplicator, MoreMachineConfig.storage.itemReplicator)
            .with(ExtraAttributeUpgradeSupport.EXTRA_MACHINE_UPGRADES)
            .withSound(MekanismSounds.PURIFICATION_CHAMBER)
            .build();

    static {
        for (EMExtraFactoryTier tier : EMExtraEnumUtils.EMEXTRA_FACTORY_TIERS) {
            for (MoreMachineFactoryType type : MoreMachineEnumUtils.MM_FACTORY_TYPES) {
                MM_FACTORIES.put(tier, type, EMExtraMoreMachineFactory.EMExtraMoreMachineFactoryBuilder.createMoreMachineFactory(() -> EMExtraMoreMachineTileEntityTypes.getEMExtraMoreMachineFactoryTile(tier, type), type, tier).build());
            }
        }
    }

    public static EMExtraMoreMachineFactory<?> getEMExtraMoreMachineFactory(EMExtraFactoryTier tier, MoreMachineFactoryType type) {
        return MM_FACTORIES.get(tier, type);
    }
}