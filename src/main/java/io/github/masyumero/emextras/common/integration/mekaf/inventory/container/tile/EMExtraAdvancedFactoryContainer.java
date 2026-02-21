package io.github.masyumero.emextras.common.integration.mekaf.inventory.container.tile;

import io.github.masyumero.emextras.common.integration.mekaf.registries.EMExtraAdvancedFactoryContainerTypes;
import io.github.masyumero.emextras.common.integration.mekaf.tile.factory.TileEntityEMExtraAdvancedBase;
import io.github.masyumero.emextras.common.integration.mekaf.tile.factory.TileEntityEMExtraChemicalToChemicalFactory;
import io.github.masyumero.emextras.common.integration.mekaf.tile.factory.TileEntityEMExtraLiquifyingFactory;
import io.github.masyumero.emextras.common.integration.mekaf.tile.factory.TileEntityEMExtraPRCFactory;

import mekanism.common.inventory.container.tile.MekanismTileContainer;

import net.minecraft.world.entity.player.Inventory;

public class EMExtraAdvancedFactoryContainer extends MekanismTileContainer<TileEntityEMExtraAdvancedBase<?>> {

    public EMExtraAdvancedFactoryContainer(int id, Inventory inv, TileEntityEMExtraAdvancedBase<?> tile) {
        super(EMExtraAdvancedFactoryContainerTypes.ADVANCED_FACTORY, id, inv, tile);
    }

    @Override
    protected int getInventoryYOffset() {
        if (tile.hasExtraResourceBar()) {
            return tile instanceof TileEntityEMExtraChemicalToChemicalFactory<?> ? 121 : tile instanceof TileEntityEMExtraPRCFactory ? 103 : 108;
        }
        return tile instanceof TileEntityEMExtraChemicalToChemicalFactory<?> ? 112 : tile instanceof TileEntityEMExtraLiquifyingFactory ? 85 : 98;
    }

    @Override
    protected int getInventoryXOffset() {
        int index = tile.tier.ordinal();
        return (22 * (index + 2)) - (3 * index);
    }
}