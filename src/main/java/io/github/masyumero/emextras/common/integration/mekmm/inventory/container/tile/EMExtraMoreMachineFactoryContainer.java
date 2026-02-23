package io.github.masyumero.emextras.common.integration.mekmm.inventory.container.tile;

import io.github.masyumero.emextras.common.integration.mekmm.registries.EMExtraMoreMachineContainerTypes;
import io.github.masyumero.emextras.common.integration.mekmm.tile.factory.TileEntityEMExtraMoreMachineFactory;
import io.github.masyumero.emextras.common.integration.mekmm.tile.factory.TileEntityEMExtraPlantingFactory;

import mekanism.common.inventory.container.tile.MekanismTileContainer;
import net.minecraft.world.entity.player.Inventory;

public class EMExtraMoreMachineFactoryContainer extends MekanismTileContainer<TileEntityEMExtraMoreMachineFactory<?>> {

    public EMExtraMoreMachineFactoryContainer(int id, Inventory inv, TileEntityEMExtraMoreMachineFactory<?> tile) {
        super(EMExtraMoreMachineContainerTypes.MORE_MACHINE_FACTORY, id, inv, tile);
    }

    @Override
    protected int getInventoryYOffset() {
        if (tile.hasSecondaryResourceBar()) {
            return tile instanceof TileEntityEMExtraPlantingFactory ? 115 : 95;
        }
        return 85;
    }

    @Override
    protected int getInventoryXOffset() {
        int index = tile.tier.ordinal();
        return (22 * (index + 2)) - (3 * index);
    }
}