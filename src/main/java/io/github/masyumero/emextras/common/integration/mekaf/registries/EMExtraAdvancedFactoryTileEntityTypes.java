package io.github.masyumero.emextras.common.integration.mekaf.registries;

import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.common.block.prefab.BlockEMExtraAdvancedFactoryMachine;
import io.github.masyumero.emextras.common.integration.mekaf.item.block.machine.ItemBlockEMExtraAdvancedFactory;
import io.github.masyumero.emextras.common.integration.mekaf.tile.factory.*;
import io.github.masyumero.emextras.common.integration.mekaf.tile.factory.base.*;
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

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jerry.mekaf.common.content.blocktype.AdvancedFactoryType;
import net.neoforged.bus.api.IEventBus;

public class EMExtraAdvancedFactoryTileEntityTypes {

    private EMExtraAdvancedFactoryTileEntityTypes() {}

    public static final TileEntityTypeDeferredRegister AF_TILE_ENTITY_TYPES = new TileEntityTypeDeferredRegister(EMExtras.MODID);

    private static final Table<EMExtraFactoryTier, AdvancedFactoryType, TileEntityTypeRegistryObject<? extends TileEntityEMExtraAdvancedFactoryBase<?>>> AF_FACTORIES = HashBasedTable.create();

    static {
        for (EMExtraFactoryTier tier : EMExtraEnumUtils.EMEXTRA_FACTORY_TIERS) {
            registerFactory(tier, AdvancedFactoryType.OXIDIZING, TileEntityEMExtraItemStackToChemicalStackFactory::new);
            registerFactory(tier, AdvancedFactoryType.DISSOLVING, TileEntityEMExtraDissolvingFactory::new);
            registerFactory(tier, AdvancedFactoryType.WASHING, TileEntityEMExtraWashingFactory::new);
            registerFactory(tier, AdvancedFactoryType.PRESSURISED_REACTING, TileEntityEMExtraPRCFactory::new);
            registerFactory(tier, AdvancedFactoryType.CRYSTALLIZING, TileEntityEMExtraCrystallizingFactory::new);
            registerFactory(tier, AdvancedFactoryType.CENTRIFUGING, TileEntityEMExtraCentrifugingFactory::new);
            registerFactory(tier, AdvancedFactoryType.LIQUIFYING, TileEntityEMExtraLiquifyingFactory::new);
            registerFactory(tier, AdvancedFactoryType.PIGMENT_EXTRACTING, TileEntityEMExtraItemStackToChemicalStackFactory::new);
            registerFactory(tier, AdvancedFactoryType.PAINTING, TileEntityEMExtraPaintingFactory::new);
        }
    }

    private static void registerFactory(EMExtraFactoryTier tier, AdvancedFactoryType type, EMExtraAdvancedBlockEntityFactory<? extends TileEntityEMExtraAdvancedFactoryBase<?>> factoryConstructor) {
        BlockRegistryObject<BlockEMExtraAdvancedFactoryMachine.BlockEMExtraAdvancedFactory<?>, ItemBlockEMExtraAdvancedFactory> block = EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactory(tier, type);
        TileEntityTypeRegistryObject<? extends TileEntityEMExtraAdvancedFactoryBase<?>> tileRO = AF_TILE_ENTITY_TYPES.mekBuilder(block, (pos, state) -> factoryConstructor.create(block, pos, state))
                .clientTicker(TileEntityMekanism::tickClient)
                .serverTicker(TileEntityMekanism::tickServer)
                .withSimple(Capabilities.CONFIG_CARD)
                .build();
        AF_FACTORIES.put(tier, type, tileRO);
    }

    public static TileEntityTypeRegistryObject<? extends TileEntityEMExtraAdvancedFactoryBase<?>> getEMExtraAdvancedFactoryTile(EMExtraFactoryTier tier, AdvancedFactoryType type) {
        return AF_FACTORIES.get(tier, type);
    }

    @SuppressWarnings("unchecked")
    public static TileEntityTypeRegistryObject<? extends TileEntityEMExtraAdvancedFactoryBase<?>>[] getEMExtraAdvancedFactoryTiles() {
        return AF_FACTORIES.values().toArray(new TileEntityTypeRegistryObject[0]);
    }

    @FunctionalInterface
    private interface EMExtraAdvancedBlockEntityFactory<BE extends BlockEntity> {

        BE create(Holder<Block> block, BlockPos pos, BlockState state);
    }

    public static void register(IEventBus modEventBus) {
        AF_TILE_ENTITY_TYPES.register(modEventBus);
    }
}
