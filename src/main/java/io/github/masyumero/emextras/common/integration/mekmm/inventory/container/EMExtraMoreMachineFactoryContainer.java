package io.github.masyumero.emextras.common.integration.mekmm.inventory.container;

import io.github.masyumero.emextras.common.integration.mekmm.registry.EMExtraMoreMachineContainerTypes;
import io.github.masyumero.emextras.common.integration.mekmm.tile.TileEntityEMExtraMoreMachineFactory;
import io.github.masyumero.emextras.common.integration.mekmm.tile.TileEntityEMExtraPlantingFactory;
import mekanism.common.inventory.container.tile.MekanismTileContainer;

import net.minecraft.world.entity.player.Inventory;

import org.jetbrains.annotations.NotNull;

public class EMExtraMoreMachineFactoryContainer extends MekanismTileContainer<TileEntityEMExtraMoreMachineFactory<?>> {

    public EMExtraMoreMachineFactoryContainer(int id, Inventory inv, @NotNull TileEntityEMExtraMoreMachineFactory<?> tile) {
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
