package io.github.masyumero.emextras.common.registry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jerry.mekanism_extras.common.tier.AdvancedFactoryTier;
import com.jerry.mekanism_extras.common.util.ExtraEnumUtils;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import fr.iglee42.evolvedmekanism.registries.EMTileEntityTypes;
import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.common.content.blocktype.EMExtraFactoryType;
import io.github.masyumero.emextras.common.tile.factory.TileEntityAdvancedAlloyingFactory;
import io.github.masyumero.emextras.common.tile.factory.TileEntityEMExtraFactory;
import mekanism.common.registration.impl.TileEntityTypeDeferredRegister;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tier.FactoryTier;

public class EMExtrasTileEntityTypes {

    public static final TileEntityTypeDeferredRegister TILE_ENTITY_TYPES = new TileEntityTypeDeferredRegister(EMExtras.MODID);

    private static final Table<AdvancedFactoryTier, EMExtraFactoryType, TileEntityTypeRegistryObject<? extends TileEntityEMExtraFactory<?>>> FACTORIES = HashBasedTable.create();

    static {
        for (AdvancedFactoryTier tier : ExtraEnumUtils.ADVANCED_FACTORY_TIERS) {
            FACTORIES.put(tier, EMExtraFactoryType.ALLOYING, EMTileEntityTypes.TILE_ENTITY_TYPES.register(EMExtrasBlock.getEMExtraFactory(tier, EMExtraFactoryType.ALLOYING), (pos, state) -> new TileEntityAdvancedAlloyingFactory(EMExtrasBlock.getEMExtraFactory(tier, EMExtraFactoryType.ALLOYING), pos, state)));
        }
    }

    public static TileEntityTypeRegistryObject<? extends TileEntityEMExtraFactory<?>> getEMExtraFactoryTile(AdvancedFactoryTier tier, EMExtraFactoryType type) {
        return FACTORIES.get(tier, type);
    }
}
