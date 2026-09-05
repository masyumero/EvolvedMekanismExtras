package io.github.masyumero.emextras.common.integration.mekmm.registries;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jerry.mekmm.common.content.blocktype.MoreMachineFactoryType;
import com.jerry.mekmm.common.tile.factory.TileEntityMoreMachineFactory;
import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.common.integration.mekmm.block.prefab.EMExtraMoreMachineBlockFactoryMachine;
import io.github.masyumero.emextras.common.integration.mekmm.item.block.machine.ItemBlockEMExtraMoreMachineFactory;
import io.github.masyumero.emextras.common.integration.mekmm.tile.factory.*;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import io.github.masyumero.emextras.common.util.EMExtraEnumUtils;
import mekanism.common.capabilities.Capabilities;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.registration.impl.TileEntityTypeDeferredRegister;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tile.base.TileEntityMekanism;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.IEventBus;

public class EMExtraMoreMachineTileEntityTypes {

    private EMExtraMoreMachineTileEntityTypes() {}

    public static final TileEntityTypeDeferredRegister MM_TILE_ENTITY_TYPES = new TileEntityTypeDeferredRegister(EMExtras.MODID);

    private static final Table<EMExtraFactoryTier, MoreMachineFactoryType, TileEntityTypeRegistryObject<? extends TileEntityEMExtraMoreMachineFactory<?>>> MM_FACTORIES = HashBasedTable.create();

    static {
        for (EMExtraFactoryTier tier : EMExtraEnumUtils.EMEXTRA_FACTORY_TIERS) {
            registerFactory(tier, MoreMachineFactoryType.RECYCLING, TileEntityEMExtraRecyclingFactory::new);
            registerFactory(tier, MoreMachineFactoryType.PLANTING_STATION, TileEntityEMExtraPlantingFactory::new);
            registerFactory(tier, MoreMachineFactoryType.CNC_STAMPING, TileEntityEMExtraStampingFactory::new);
            registerFactory(tier, MoreMachineFactoryType.CNC_LATHING, TileEntityEMExtraItemStackToItemStackMoreMachineFactory::new);
            registerFactory(tier, MoreMachineFactoryType.CNC_ROLLING_MILL, TileEntityEMExtraItemStackToItemStackMoreMachineFactory::new);
            registerFactory(tier, MoreMachineFactoryType.PRESSING, TileEntityEMExtraPressingFactory::new);
            registerFactory(tier, MoreMachineFactoryType.REPLICATING, TileEntityEMExtraReplicatingFactory::new);
        }
    }

    private static void registerFactory(EMExtraFactoryTier tier, MoreMachineFactoryType type, MMBlockEntityFactory<? extends TileEntityEMExtraMoreMachineFactory<?>> factoryConstructor) {
        BlockRegistryObject<EMExtraMoreMachineBlockFactoryMachine.BlockEMExtraMoreMachineFactory<?>, ItemBlockEMExtraMoreMachineFactory> block = EMExtraMoreMachineBlocks.getEMExtraMoreMachineFactory(tier, type);
        TileEntityTypeRegistryObject<? extends TileEntityEMExtraMoreMachineFactory<?>> tileRO = MM_TILE_ENTITY_TYPES.mekBuilder(block, (pos, state) -> factoryConstructor.create(block, pos, state))
                .clientTicker(TileEntityMekanism::tickClient)
                .serverTicker(TileEntityMekanism::tickServer)
                .withSimple(Capabilities.CONFIG_CARD)
                .build();
        MM_FACTORIES.put(tier, type, tileRO);
    }

    public static TileEntityTypeRegistryObject<? extends TileEntityEMExtraMoreMachineFactory<?>> getEMExtraMoreMachineFactoryTile(EMExtraFactoryTier tier, MoreMachineFactoryType type) {
        return MM_FACTORIES.get(tier, type);
    }

    @SuppressWarnings("unchecked")
    public static TileEntityTypeRegistryObject<? extends TileEntityMoreMachineFactory<?>>[] getEMExtraMoreMachineFactoryTiles() {
        return MM_FACTORIES.values().toArray(new TileEntityTypeRegistryObject[0]);
    }

    @FunctionalInterface
    private interface MMBlockEntityFactory<BE extends BlockEntity> {

        BE create(Holder<Block> block, BlockPos pos, BlockState state);
    }

    public static void register(IEventBus modEventBus) {
        MM_TILE_ENTITY_TYPES.register(modEventBus);
    }
}
