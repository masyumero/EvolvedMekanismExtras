package io.github.masyumero.emextras.mixin;

import com.jerry.mekanism_extras.common.tile.factory.TileEntityExtraFactory;
import mekanism.common.inventory.slot.EnergyInventorySlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = TileEntityExtraFactory.class, remap = false)
public interface AccessorTileEntityExtraFactory {

    @Accessor(value = "energySlot")
    EnergyInventorySlot getEnergySlot();

}
