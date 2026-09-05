package io.github.masyumero.emextras.common.registry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jerry.mekextras.common.block.prefab.BlockExtraFactoryMachine;
import com.jerry.mekextras.common.item.block.machine.ItemBlockExtraFactory;
import com.jerry.mekextras.common.tier.ExtraFactoryTier;
import com.jerry.mekextras.common.tile.factory.TileEntityExtraFactory;
import com.jerry.mekextras.common.util.ExtraEnumUtils;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.common.block.prefab.BlockEMExtraFactoryMachine;
import io.github.masyumero.emextras.common.item.block.machine.ItemBlockEMExtraFactory;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import io.github.masyumero.emextras.common.tile.factory.*;
import io.github.masyumero.emextras.common.util.EMExtraEnumUtils;
import mekanism.common.capabilities.Capabilities;
import mekanism.common.content.blocktype.FactoryType;
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

public class EMExtraTileEntityTypes {

    public static final TileEntityTypeDeferredRegister TILE_ENTITY_TYPES = new TileEntityTypeDeferredRegister(EMExtras.MODID);

    private static final Table<EMExtraFactoryTier, FactoryType, TileEntityTypeRegistryObject<? extends TileEntityEMExtraFactory<?>>> FACTORIES = HashBasedTable.create();

    private static final Table<ExtraFactoryTier, FactoryType, TileEntityTypeRegistryObject<? extends TileEntityExtraFactory<?>>> EXTRA_FACTORIES = HashBasedTable.create();

    static {
        for (EMExtraFactoryTier tier : EMExtraEnumUtils.EMEXTRA_FACTORY_TIERS) {
            registerFactory(tier, FactoryType.SMELTING, TileEntityEMExtraItemStackToItemStackFactory::new);
            registerFactory(tier, FactoryType.CRUSHING, TileEntityEMExtraItemStackToItemStackFactory::new);
            registerFactory(tier, FactoryType.ENRICHING, TileEntityEMExtraItemStackToItemStackFactory::new);
            registerFactory(tier, FactoryType.COMPRESSING, TileEntityEMExtraItemStackChemicalToItemStackFactory::new);
            registerFactory(tier, FactoryType.INJECTING, TileEntityEMExtraItemStackChemicalToItemStackFactory::new);
            registerFactory(tier, FactoryType.PURIFYING, TileEntityEMExtraItemStackChemicalToItemStackFactory::new);
            registerFactory(tier, FactoryType.INFUSING, TileEntityEMExtraItemStackChemicalToItemStackFactory::new);
            registerFactory(tier, FactoryType.COMBINING, TileEntityEMExtraCombiningFactory::new);
            registerFactory(tier, FactoryType.SAWING, TileEntityEMExtraSawingFactory::new);
            registerFactory(tier, EMFactoryType.ALLOYING, TileEntityEMExtraAlloyingFactory::new);
        }
        for (ExtraFactoryTier tier  : ExtraEnumUtils.EXTRA_FACTORY_TIERS) {
            registerAdvancedFactory(tier, EMFactoryType.ALLOYING, TileEntityExtraAlloyingFactory::new);
        }
    }

    private static void registerFactory(EMExtraFactoryTier tier, FactoryType type, BlockEntityFactory<? extends TileEntityEMExtraFactory<?>> factoryConstructor) {
        BlockRegistryObject<BlockEMExtraFactoryMachine.BlockEMExtraFactory<?>, ItemBlockEMExtraFactory> block = EMExtraBlocks.getEMExtraFactory(tier, type);
        TileEntityTypeRegistryObject<? extends TileEntityEMExtraFactory<?>> tileRO = TILE_ENTITY_TYPES.mekBuilder(block, (pos, state) -> factoryConstructor.create(block, pos, state))
                .clientTicker(TileEntityMekanism::tickClient)
                .serverTicker(TileEntityMekanism::tickServer)
                .withSimple(Capabilities.CONFIG_CARD)
                .build();
        FACTORIES.put(tier, type, tileRO);
    }

    private static void registerAdvancedFactory(ExtraFactoryTier tier, FactoryType type, BlockEntityFactory<? extends TileEntityExtraFactory<?>> factoryConstructor) {
        BlockRegistryObject<BlockExtraFactoryMachine.BlockExtraFactory<?>, ItemBlockExtraFactory> block = EMExtraBlocks.getExtraFactory(tier, type);
        TileEntityTypeRegistryObject<? extends TileEntityExtraFactory<?>> tileRO = TILE_ENTITY_TYPES.mekBuilder(block, (pos, state) -> factoryConstructor.create(block, pos, state))
                .clientTicker(TileEntityMekanism::tickClient)
                .serverTicker(TileEntityMekanism::tickServer)
                .withSimple(Capabilities.CONFIG_CARD)
                .build();
        EXTRA_FACTORIES.put(tier, type, tileRO);
    }

    public static TileEntityTypeRegistryObject<? extends TileEntityEMExtraFactory<?>> getEMExtraFactoryTile(EMExtraFactoryTier tier, FactoryType type) {
        return FACTORIES.get(tier, type);
    }

    public static TileEntityTypeRegistryObject<? extends TileEntityExtraFactory<?>> getExtraFactoryTile(ExtraFactoryTier tier, FactoryType type) {
        return EXTRA_FACTORIES.get(tier, type);
    }

    public static void register(IEventBus modEventBus) {
        TILE_ENTITY_TYPES.register(modEventBus);
    }


    @FunctionalInterface
    private interface BlockEntityFactory<BE extends BlockEntity> {

        BE create(Holder<Block> block, BlockPos pos, BlockState state);
    }
}
