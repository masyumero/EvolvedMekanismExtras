package io.github.masyumero.emextras.common.inventory.container.tile;

import io.github.masyumero.emextras.common.registry.EMExtrasContainerTypes;
import io.github.masyumero.emextras.common.tile.factory.TileEntityEMExtraFactory;
import io.github.masyumero.emextras.common.tile.factory.TileEntitySawingEMExtraFactory;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import net.minecraft.world.entity.player.Inventory;

public class EMExtraFactoryContainer extends MekanismTileContainer<TileEntityEMExtraFactory<?>> {

    public EMExtraFactoryContainer(int id, Inventory inv, TileEntityEMExtraFactory<?> tile) {
        super(EMExtrasContainerTypes.FACTORY, id, inv, tile);
    }

    @Override
    protected int getInventoryYOffset() {
        if (tile.hasSecondaryResourceBar()) {
            return 95;
        } else if (tile instanceof TileEntitySawingEMExtraFactory) {
            return 105;
        }
        return 85;
    }

    @Override
    protected int getInventoryXOffset() {
        int index = tile.tier.ordinal();
        return tile.tier.inventoryLabelX;
    }
}