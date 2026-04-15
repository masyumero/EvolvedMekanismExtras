package io.github.masyumero.emextras.common.registry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jerry.mekanism_extras.common.tier.AdvancedFactoryTier;
import com.jerry.mekanism_extras.common.tile.factory.TileEntityExtraFactory;
import com.jerry.mekanism_extras.common.util.ExtraEnumUtils;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import fr.iglee42.evolvedmekanism.registries.EMTileEntityTypes;
import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.common.content.blocktype.EMExtraFactoryType;
import io.github.masyumero.emextras.common.tile.factory.*;
import io.github.masyumero.emextras.common.tile.multiblock.TileEntityEMExtraInductionCell;
import io.github.masyumero.emextras.common.tile.multiblock.TileEntityEMExtraInductionProvider;
import io.github.masyumero.emextras.common.util.EMExtraEnumUtils;
import mekanism.common.content.blocktype.FactoryType;
import mekanism.common.registration.impl.TileEntityTypeDeferredRegister;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tile.base.TileEntityMekanism;
import net.minecraftforge.eventbus.api.IEventBus;

public class EMExtrasTileEntityTypes {

    public static final TileEntityTypeDeferredRegister TILE_ENTITY_TYPES = new TileEntityTypeDeferredRegister(EMExtras.MODID);

    private static final Table<EMExtraFactoryTier, EMExtraFactoryType, TileEntityTypeRegistryObject<? extends TileEntityEMExtraFactory<?>>> FACTORIES = HashBasedTable.create();

    private static final Table<AdvancedFactoryTier, FactoryType, TileEntityTypeRegistryObject<? extends TileEntityExtraFactory<?>>> ADVANCED_FACTORIES = HashBasedTable.create();

    static {
        for (EMExtraFactoryTier tier : EMExtraEnumUtils.EMEXTRA_FACTORY_TIERS) {
            FACTORIES.put(tier, EMExtraFactoryType.SMELTING, TILE_ENTITY_TYPES.register(EMExtrasBlock.getEMExtraFactory(tier, EMExtraFactoryType.SMELTING), (pos, state) -> new TileEntityItemStackToItemStackEMExtraFactory(EMExtrasBlock.getEMExtraFactory(tier, EMExtraFactoryType.SMELTING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, EMExtraFactoryType.CRUSHING, TILE_ENTITY_TYPES.register(EMExtrasBlock.getEMExtraFactory(tier, EMExtraFactoryType.CRUSHING), (pos, state) -> new TileEntityItemStackToItemStackEMExtraFactory(EMExtrasBlock.getEMExtraFactory(tier, EMExtraFactoryType.CRUSHING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, EMExtraFactoryType.ENRICHING, TILE_ENTITY_TYPES.register(EMExtrasBlock.getEMExtraFactory(tier, EMExtraFactoryType.ENRICHING), (pos, state) -> new TileEntityItemStackToItemStackEMExtraFactory(EMExtrasBlock.getEMExtraFactory(tier, EMExtraFactoryType.ENRICHING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, EMExtraFactoryType.COMPRESSING, TILE_ENTITY_TYPES.register(EMExtrasBlock.getEMExtraFactory(tier, EMExtraFactoryType.COMPRESSING), (pos, state) -> new TileEntityItemStackGasToItemStackEMExtraFactory(EMExtrasBlock.getEMExtraFactory(tier, EMExtraFactoryType.COMPRESSING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, EMExtraFactoryType.INJECTING, TILE_ENTITY_TYPES.register(EMExtrasBlock.getEMExtraFactory(tier, EMExtraFactoryType.INJECTING), (pos, state) -> new TileEntityItemStackGasToItemStackEMExtraFactory(EMExtrasBlock.getEMExtraFactory(tier, EMExtraFactoryType.INJECTING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, EMExtraFactoryType.PURIFYING, TILE_ENTITY_TYPES.register(EMExtrasBlock.getEMExtraFactory(tier, EMExtraFactoryType.PURIFYING), (pos, state) -> new TileEntityItemStackGasToItemStackEMExtraFactory(EMExtrasBlock.getEMExtraFactory(tier, EMExtraFactoryType.PURIFYING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, EMExtraFactoryType.INFUSING, TILE_ENTITY_TYPES.register(EMExtrasBlock.getEMExtraFactory(tier, EMExtraFactoryType.INFUSING), (pos, state) -> new TileEntityMetallurgicInfuserEMExtraFactory(EMExtrasBlock.getEMExtraFactory(tier, EMExtraFactoryType.INFUSING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, EMExtraFactoryType.COMBINING, TILE_ENTITY_TYPES.register(EMExtrasBlock.getEMExtraFactory(tier, EMExtraFactoryType.COMBINING), (pos, state) -> new TileEntityCombiningEMExtraFactory(EMExtrasBlock.getEMExtraFactory(tier, EMExtraFactoryType.COMBINING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, EMExtraFactoryType.SAWING, TILE_ENTITY_TYPES.register(EMExtrasBlock.getEMExtraFactory(tier, EMExtraFactoryType.SAWING), (pos, state) -> new TileEntitySawingEMExtraFactory(EMExtrasBlock.getEMExtraFactory(tier, EMExtraFactoryType.SAWING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, EMExtraFactoryType.ALLOYING, EMTileEntityTypes.TILE_ENTITY_TYPES.register(EMExtrasBlock.getEMExtraFactory(tier, EMExtraFactoryType.ALLOYING), (pos, state) -> new TileEntityEMExtraAlloyingFactory(EMExtrasBlock.getEMExtraFactory(tier, EMExtraFactoryType.ALLOYING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
        }
        for (AdvancedFactoryTier tier  : ExtraEnumUtils.ADVANCED_FACTORY_TIERS) {
            ADVANCED_FACTORIES.put(tier, EMFactoryType.ALLOYING, EMTileEntityTypes.TILE_ENTITY_TYPES.register(EMExtrasBlock.getAdvancedFactory(tier, EMFactoryType.ALLOYING), (pos, state) -> new TileEntityAdvancedAlloyingFactory(EMExtrasBlock.getAdvancedFactory(tier, EMFactoryType.ALLOYING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
        }
    }
    // Induction Cells
    public static final TileEntityTypeRegistryObject<TileEntityEMExtraInductionCell> ABSOLUTE_OVERCLOCKED_INDUCTION_CELL = TILE_ENTITY_TYPES.register(EMExtrasBlock.ABSOLUTE_OVERCLOCKED_INDUCTION_CELL, (pos, state) -> new TileEntityEMExtraInductionCell(EMExtrasBlock.ABSOLUTE_OVERCLOCKED_INDUCTION_CELL, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<TileEntityEMExtraInductionCell> SUPREME_QUANTUM_INDUCTION_CELL = TILE_ENTITY_TYPES.register(EMExtrasBlock.SUPREME_QUANTUM_INDUCTION_CELL, (pos, state) -> new TileEntityEMExtraInductionCell(EMExtrasBlock.SUPREME_QUANTUM_INDUCTION_CELL, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<TileEntityEMExtraInductionCell> COSMIC_DENSE_INDUCTION_CELL = TILE_ENTITY_TYPES.register(EMExtrasBlock.COSMIC_DENSE_INDUCTION_CELL, (pos, state) -> new TileEntityEMExtraInductionCell(EMExtrasBlock.COSMIC_DENSE_INDUCTION_CELL, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<TileEntityEMExtraInductionCell> INFINITE_MULTIVERSAL_INDUCTION_CELL = TILE_ENTITY_TYPES.register(EMExtrasBlock.INFINITE_MULTIVERSAL_INDUCTION_CELL, (pos, state) -> new TileEntityEMExtraInductionCell(EMExtrasBlock.INFINITE_MULTIVERSAL_INDUCTION_CELL, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    // Induction Providers
    public static final TileEntityTypeRegistryObject<TileEntityEMExtraInductionProvider> ABSOLUTE_OVERCLOCKED_INDUCTION_PROVIDER = TILE_ENTITY_TYPES.register(EMExtrasBlock.ABSOLUTE_OVERCLOCKED_INDUCTION_PROVIDER, (pos, state) -> new TileEntityEMExtraInductionProvider(EMExtrasBlock.ABSOLUTE_OVERCLOCKED_INDUCTION_PROVIDER, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<TileEntityEMExtraInductionProvider> SUPREME_QUANTUM_INDUCTION_PROVIDER = TILE_ENTITY_TYPES.register(EMExtrasBlock.SUPREME_QUANTUM_INDUCTION_PROVIDER, (pos, state) -> new TileEntityEMExtraInductionProvider(EMExtrasBlock.SUPREME_QUANTUM_INDUCTION_PROVIDER, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<TileEntityEMExtraInductionProvider> COSMIC_DENSE_INDUCTION_PROVIDER = TILE_ENTITY_TYPES.register(EMExtrasBlock.COSMIC_DENSE_INDUCTION_PROVIDER, (pos, state) -> new TileEntityEMExtraInductionProvider(EMExtrasBlock.COSMIC_DENSE_INDUCTION_PROVIDER, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<TileEntityEMExtraInductionProvider> INFINITE_MULTIVERSAL_INDUCTION_PROVIDER = TILE_ENTITY_TYPES.register(EMExtrasBlock.INFINITE_MULTIVERSAL_INDUCTION_PROVIDER, (pos, state) -> new TileEntityEMExtraInductionProvider(EMExtrasBlock.INFINITE_MULTIVERSAL_INDUCTION_PROVIDER, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);

    public static TileEntityTypeRegistryObject<? extends TileEntityEMExtraFactory<?>> getEMExtraFactoryTile(EMExtraFactoryTier tier, EMExtraFactoryType type) {
        return FACTORIES.get(tier, type);
    }

    public static TileEntityTypeRegistryObject<? extends TileEntityExtraFactory<?>> getAdvancedFactoryTile(AdvancedFactoryTier tier, FactoryType type) {
        return ADVANCED_FACTORIES.get(tier, type);
    }

    public static void register(IEventBus eventBus) {
        TILE_ENTITY_TYPES.register(eventBus);
    }
}
