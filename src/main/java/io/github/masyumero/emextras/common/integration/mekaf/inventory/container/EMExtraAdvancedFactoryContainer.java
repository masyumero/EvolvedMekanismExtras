package io.github.masyumero.emextras.common.integration.mekaf.inventory.container;

import io.github.masyumero.emextras.common.integration.mekaf.regisrty.EMExtraAdvancedFactoryContainerTypes;
import io.github.masyumero.emextras.common.integration.mekaf.tile.factory.TileEntityEMExtraPressurizedReactingFactory;
import io.github.masyumero.emextras.common.integration.mekaf.tile.factory.base.TileEntityEMExtraAdvancedFactoryBase;
import io.github.masyumero.emextras.common.integration.mekaf.tile.factory.base.TileEntityEMExtraGasToGasFactory;
import io.github.masyumero.emextras.common.integration.mekaf.tile.factory.base.TileEntityEMExtraItemToItemAdvancedFactory;
import io.github.masyumero.emextras.common.integration.mekaf.tile.factory.base.TileEntityEMExtraSlurryToSlurryFactory;
import mekanism.common.inventory.container.tile.MekanismTileContainer;

import net.minecraft.world.entity.player.Inventory;

import org.jetbrains.annotations.NotNull;

public class EMExtraAdvancedFactoryContainer extends MekanismTileContainer<TileEntityEMExtraAdvancedFactoryBase<?>> {

    public EMExtraAdvancedFactoryContainer(int id, Inventory inv, @NotNull TileEntityEMExtraAdvancedFactoryBase<?> tile) {
        super(EMExtraAdvancedFactoryContainerTypes.ADVANCED_FACTORY, id, inv, tile);
    }

    protected int getInventoryYOffset() {
        if (tile.hasExtrasResourceBar()) {
            if (tile instanceof TileEntityEMExtraGasToGasFactory<?> || tile instanceof TileEntityEMExtraSlurryToSlurryFactory<?>) {
                return 121;
            }
            if (tile instanceof TileEntityEMExtraItemToItemAdvancedFactory<?>) {
                return 95;
            } else {
                return tile instanceof TileEntityEMExtraPressurizedReactingFactory ? 103 : 108;
            }
        }
        if (tile instanceof TileEntityEMExtraGasToGasFactory<?> || tile instanceof TileEntityEMExtraSlurryToSlurryFactory<?>) {
            return 112;
        } else {
            return 98;
        }
    }

    protected int getInventoryXOffset() {
        int index = this.tile.tier.ordinal();
        return 22 * (index + 2) - 3 * index;
    }
}
