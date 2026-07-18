package io.github.masyumero.emextras.common.integration.mekmm.registry;

import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.common.integration.mekmm.tile.*;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import io.github.masyumero.emextras.common.util.EMExtraEnumUtils;

import mekanism.common.registration.impl.TileEntityTypeDeferredRegister;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tile.base.TileEntityMekanism;

import net.minecraftforge.eventbus.api.IEventBus;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jerry.mekmm.common.content.blocktype.MoreMachineFactoryType;

public class EMExtraMoreMachineTileEntityTypes {

    private EMExtraMoreMachineTileEntityTypes() {}

    public static final TileEntityTypeDeferredRegister TILE_ENTITY_TYPES = new TileEntityTypeDeferredRegister(EMExtras.MODID);

    private static final Table<EMExtraFactoryTier, MoreMachineFactoryType, TileEntityTypeRegistryObject<? extends TileEntityEMExtraMoreMachineFactory<?>>> FACTORIES = HashBasedTable.create();

    static {
        for (EMExtraFactoryTier tier : EMExtraEnumUtils.EMEXTRA_FACTORY_TIERS) {
            FACTORIES.put(tier, MoreMachineFactoryType.RECYCLING, TILE_ENTITY_TYPES.register(EMExtraMoreMachineBlocks.getEMExtraMoreMachineFactory(tier, MoreMachineFactoryType.RECYCLING), (pos, state) -> new TileEntityEMExtraRecyclingFactory(EMExtraMoreMachineBlocks.getEMExtraMoreMachineFactory(tier, MoreMachineFactoryType.RECYCLING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, MoreMachineFactoryType.PLANTING, TILE_ENTITY_TYPES.register(EMExtraMoreMachineBlocks.getEMExtraMoreMachineFactory(tier, MoreMachineFactoryType.PLANTING), (pos, state) -> new TileEntityEMExtraPlantingFactory(EMExtraMoreMachineBlocks.getEMExtraMoreMachineFactory(tier, MoreMachineFactoryType.PLANTING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, MoreMachineFactoryType.CNC_STAMPING, TILE_ENTITY_TYPES.register(EMExtraMoreMachineBlocks.getEMExtraMoreMachineFactory(tier, MoreMachineFactoryType.CNC_STAMPING), (pos, state) -> new TileEntityEMExtraStampingFactory(EMExtraMoreMachineBlocks.getEMExtraMoreMachineFactory(tier, MoreMachineFactoryType.CNC_STAMPING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, MoreMachineFactoryType.CNC_LATHING, TILE_ENTITY_TYPES.register(EMExtraMoreMachineBlocks.getEMExtraMoreMachineFactory(tier, MoreMachineFactoryType.CNC_LATHING), (pos, state) -> new TileEntityEMExtraItemStackToItemStackMoreMachineFactory(EMExtraMoreMachineBlocks.getEMExtraMoreMachineFactory(tier, MoreMachineFactoryType.CNC_LATHING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, MoreMachineFactoryType.CNC_ROLLING_MILL, TILE_ENTITY_TYPES.register(EMExtraMoreMachineBlocks.getEMExtraMoreMachineFactory(tier, MoreMachineFactoryType.CNC_ROLLING_MILL), (pos, state) -> new TileEntityEMExtraItemStackToItemStackMoreMachineFactory(EMExtraMoreMachineBlocks.getEMExtraMoreMachineFactory(tier, MoreMachineFactoryType.CNC_ROLLING_MILL), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, MoreMachineFactoryType.REPLICATING, TILE_ENTITY_TYPES.register(EMExtraMoreMachineBlocks.getEMExtraMoreMachineFactory(tier, MoreMachineFactoryType.REPLICATING), (pos, state) -> new TileEntityEMExtraReplicatingFactory(EMExtraMoreMachineBlocks.getEMExtraMoreMachineFactory(tier, MoreMachineFactoryType.REPLICATING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
        }
    }

    public static TileEntityTypeRegistryObject<? extends TileEntityEMExtraMoreMachineFactory<?>> getEMExtraMoreMachineFactoryTile(EMExtraFactoryTier tier, MoreMachineFactoryType type) {
        return FACTORIES.get(tier, type);
    }

    @SuppressWarnings("unchecked")
    public static TileEntityTypeRegistryObject<? extends TileEntityEMExtraMoreMachineFactory<?>>[] getEMExtraMoreMachineFactoryTiles() {
        return FACTORIES.values().toArray(new TileEntityTypeRegistryObject[0]);
    }

    public static void register(IEventBus eventBus) {
        TILE_ENTITY_TYPES.register(eventBus);
    }
}
