package io.github.masyumero.emextras.common.integration.mekmm.registry;

import com.jerry.mekanism_extras.api.ExtraUpgrade;

import io.github.masyumero.emextras.common.content.blocktype.EMExtraMachine.EMExtraFactoryMachine;
import io.github.masyumero.emextras.common.content.blocktype.EMExtraMachine.EMExtraMachineBuilder;
import io.github.masyumero.emextras.common.integration.mekmm.content.blocktype.EMExtraMoreMachineFactory;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;

import io.github.masyumero.emextras.common.util.EMExtraEnumUtils;
import mekanism.api.Upgrade;
import mekanism.common.registries.MekanismSounds;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jerry.mekmm.common.MoreMachineLang;
import com.jerry.mekmm.common.config.MoreMachineConfig;
import com.jerry.mekmm.common.content.blocktype.MoreMachineFactoryType;
import com.jerry.mekmm.common.registries.MoreMachineContainerTypes;
import com.jerry.mekmm.common.registries.MoreMachineTileEntityTypes;
import com.jerry.mekmm.common.tile.machine.*;
import com.jerry.mekmm.common.util.MoreMachineEnumUtils;

import java.util.EnumSet;

public class EMExtraMoreMachineBlockTypes {

    private EMExtraMoreMachineBlockTypes() {}

    private static final Table<EMExtraFactoryTier, MoreMachineFactoryType, EMExtraMoreMachineFactory<?>> FACTORIES = HashBasedTable.create();

    // Recycler
    public static final EMExtraFactoryMachine<TileEntityRecycler> RECYCLER = EMExtraMachineBuilder
            .createEMExtraMoreMachineFactoryMachine(() -> MoreMachineTileEntityTypes.RECYCLER, MoreMachineLang.DESCRIPTION_RECYCLER, MoreMachineFactoryType.RECYCLING)
            .withSound(MekanismSounds.PRECISION_SAWMILL)
            .withEnergyConfig(MoreMachineConfig.usage.recycler, MoreMachineConfig.storage.recycler)
            .build();
    // Planting Station
    public static final EMExtraFactoryMachine<TileEntityPlantingStation> PLANTING_STATION = EMExtraMachineBuilder
            .createEMExtraMoreMachineFactoryMachine(() -> MoreMachineTileEntityTypes.PLANTING_STATION, MoreMachineLang.DESCRIPTION_PLANTING_STATION, MoreMachineFactoryType.PLANTING)
            .withSound(MekanismSounds.PRECISION_SAWMILL)
            .withEnergyConfig(MoreMachineConfig.usage.plantingStation, MoreMachineConfig.storage.plantingStation)
            .withSupportedUpgrades(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, Upgrade.GAS, ExtraUpgrade.STACK, ExtraUpgrade.CREATIVE))
            .withBounding((pos, state, builder) -> builder.add(pos.above()))
            .build();
    // CNC Stamper
    public static final EMExtraFactoryMachine<TileEntityStamper> CNC_STAMPER = EMExtraMachineBuilder
            .createEMExtraMoreMachineFactoryMachine(() -> MoreMachineTileEntityTypes.CNC_STAMPER, MoreMachineLang.DESCRIPTION_CNC_STAMPER, MoreMachineFactoryType.CNC_STAMPING)
            .withSound(MekanismSounds.CRUSHER)
            .withEnergyConfig(MoreMachineConfig.usage.cnc_stamper, MoreMachineConfig.storage.cnc_stamper)
            .withSupportedUpgrades(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, ExtraUpgrade.STACK, ExtraUpgrade.CREATIVE))
            .build();
    // CNC Lathe
    public static final EMExtraFactoryMachine<TileEntityLathe> CNC_LATHE = EMExtraMachineBuilder
            .createEMExtraMoreMachineFactoryMachine(() -> MoreMachineTileEntityTypes.CNC_LATHE, MoreMachineLang.DESCRIPTION_CNC_LATHE, MoreMachineFactoryType.CNC_LATHING)
            .withSound(MekanismSounds.CRUSHER)
            .withEnergyConfig(MoreMachineConfig.usage.cnc_lathe, MoreMachineConfig.storage.cnc_lathe)
            .withSupportedUpgrades(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, ExtraUpgrade.STACK, ExtraUpgrade.CREATIVE))
            .build();
    // CNC Rolling Mill
    public static final EMExtraFactoryMachine<TileEntityRollingMill> CNC_ROLLING_MILL = EMExtraMachineBuilder
            .createEMExtraMoreMachineFactoryMachine(() -> MoreMachineTileEntityTypes.CNC_ROLLING_MILL, MoreMachineLang.DESCRIPTION_CNC_ROLLING_MILL, MoreMachineFactoryType.CNC_ROLLING_MILL)
            .withSound(MekanismSounds.CRUSHER)
            .withEnergyConfig(MoreMachineConfig.usage.cnc_rollingMill, MoreMachineConfig.storage.cnc_rollingMill)
            .withSupportedUpgrades(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, ExtraUpgrade.STACK, ExtraUpgrade.CREATIVE))
            .build();
    // Replicator
    public static final EMExtraFactoryMachine<TileEntityReplicator> REPLICATOR = EMExtraMachineBuilder
            .createEMExtraMoreMachineFactoryMachine(() -> MoreMachineTileEntityTypes.REPLICATOR, MoreMachineLang.DESCRIPTION_REPLICATOR, MoreMachineFactoryType.REPLICATING)
            .withGui(() -> MoreMachineContainerTypes.REPLICATOR)
            .withSound(MekanismSounds.PRECISION_SAWMILL)
            .withEnergyConfig(MoreMachineConfig.usage.itemReplicator, MoreMachineConfig.storage.itemReplicator)
            .withSupportedUpgrades(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, ExtraUpgrade.STACK, ExtraUpgrade.CREATIVE))
            .build();

    static {
        for (EMExtraFactoryTier tier : EMExtraEnumUtils.EMEXTRA_FACTORY_TIERS) {
            for (MoreMachineFactoryType type : MoreMachineEnumUtils.MM_FACTORY_TYPES) {
                FACTORIES.put(tier, type, EMExtraMoreMachineFactory.EMExtraMoreMachineFactoryBuilder.createEMExtraMoreMachineFactory(() -> EMExtraMoreMachineTileEntityTypes.getEMExtraMoreMachineFactoryTile(tier, type), type, tier).build());
            }
        }
    }

    public static EMExtraMoreMachineFactory<?> getEMExtraMoreMachineFactory(EMExtraFactoryTier tier, MoreMachineFactoryType type) {
        return FACTORIES.get(tier, type);
    }
}