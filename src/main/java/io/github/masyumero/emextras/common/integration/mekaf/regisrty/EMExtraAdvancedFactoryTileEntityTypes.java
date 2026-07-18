package io.github.masyumero.emextras.common.integration.mekaf.regisrty;

import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.common.integration.mekaf.tile.factory.*;
import io.github.masyumero.emextras.common.integration.mekaf.tile.factory.base.TileEntityEMExtraAdvancedFactoryBase;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import io.github.masyumero.emextras.common.util.EMExtraEnumUtils;

import mekanism.common.registration.impl.TileEntityTypeDeferredRegister;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tile.base.TileEntityMekanism;

import net.minecraftforge.eventbus.api.IEventBus;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jerry.mekaf.common.content.blocktype.AdvancedFactoryType;

public class EMExtraAdvancedFactoryTileEntityTypes {

    private EMExtraAdvancedFactoryTileEntityTypes() {}

    public static final TileEntityTypeDeferredRegister TILE_ENTITY_TYPES = new TileEntityTypeDeferredRegister(EMExtras.MODID);

    private static final Table<EMExtraFactoryTier, AdvancedFactoryType, TileEntityTypeRegistryObject<? extends TileEntityEMExtraAdvancedFactoryBase<?>>> FACTORIES = HashBasedTable.create();

    static {
        for (EMExtraFactoryTier tier : EMExtraEnumUtils.EMEXTRA_FACTORY_TIERS) {
            FACTORIES.put(tier, AdvancedFactoryType.OXIDIZING, TILE_ENTITY_TYPES.register(EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactory(tier, AdvancedFactoryType.OXIDIZING), (pos, state) -> new TileEntityEMExtraOxidizingFactory(EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactory(tier, AdvancedFactoryType.OXIDIZING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, AdvancedFactoryType.DISSOLVING, TILE_ENTITY_TYPES.register(EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactory(tier, AdvancedFactoryType.DISSOLVING), (pos, state) -> new TileEntityEMExtraDissolvingFactory(EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactory(tier, AdvancedFactoryType.DISSOLVING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, AdvancedFactoryType.WASHING, TILE_ENTITY_TYPES.register(EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactory(tier, AdvancedFactoryType.WASHING), (pos, state) -> new TileEntityEMExtraWashingFactory(EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactory(tier, AdvancedFactoryType.WASHING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, AdvancedFactoryType.CRYSTALLIZING, TILE_ENTITY_TYPES.register(EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactory(tier, AdvancedFactoryType.CRYSTALLIZING), (pos, state) -> new TileEntityEMExtraCrystallizingFactory(EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactory(tier, AdvancedFactoryType.CRYSTALLIZING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, AdvancedFactoryType.PRESSURISED_REACTING, TILE_ENTITY_TYPES.register(EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactory(tier, AdvancedFactoryType.PRESSURISED_REACTING), (pos, state) -> new TileEntityEMExtraPressurizedReactingFactory(EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactory(tier, AdvancedFactoryType.PRESSURISED_REACTING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, AdvancedFactoryType.CENTRIFUGING, TILE_ENTITY_TYPES.register(EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactory(tier, AdvancedFactoryType.CENTRIFUGING), (pos, state) -> new TileEntityEMExtraCentrifugingFactory(EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactory(tier, AdvancedFactoryType.CENTRIFUGING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, AdvancedFactoryType.LIQUIFYING, TILE_ENTITY_TYPES.register(EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactory(tier, AdvancedFactoryType.LIQUIFYING), (pos, state) -> new TileEntityEMExtraLiquifyingFactory(EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactory(tier, AdvancedFactoryType.LIQUIFYING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, AdvancedFactoryType.PIGMENT_EXTRACTING, TILE_ENTITY_TYPES.register(EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactory(tier, AdvancedFactoryType.PIGMENT_EXTRACTING), (pos, state) -> new TileEntityEMExtraPigmentEMExtractingFactory(EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactory(tier, AdvancedFactoryType.PIGMENT_EXTRACTING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, AdvancedFactoryType.PAINTING, TILE_ENTITY_TYPES.register(EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactory(tier, AdvancedFactoryType.PAINTING), (pos, state) -> new TileEntityEMExtraPaintingFactory(EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactory(tier, AdvancedFactoryType.PAINTING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
        }
    }

    public static TileEntityTypeRegistryObject<? extends TileEntityEMExtraAdvancedFactoryBase<?>> getEMExtraAdvancedFactoryTile(EMExtraFactoryTier tier, AdvancedFactoryType type) {
        return FACTORIES.get(tier, type);
    }

    @SuppressWarnings("unchecked")
    public static TileEntityTypeRegistryObject<? extends TileEntityEMExtraAdvancedFactoryBase<?>>[] getEMExtraAdvancedFactoryTiles() {
        return FACTORIES.values().toArray(new TileEntityTypeRegistryObject[0]);
    }

    public static void register(IEventBus eventBus) {
        TILE_ENTITY_TYPES.register(eventBus);
    }
}
