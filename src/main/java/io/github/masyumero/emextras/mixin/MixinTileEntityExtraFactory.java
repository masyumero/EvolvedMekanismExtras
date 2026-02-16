package io.github.masyumero.emextras.mixin;

import com.jerry.mekextras.common.tile.factory.TileEntityExtraFactory;
import fr.iglee42.evolvedmekanism.interfaces.IGetEnergySlot;
import mekanism.common.inventory.slot.EnergyInventorySlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = TileEntityExtraFactory.class, remap = false)
public class MixinTileEntityExtraFactory implements IGetEnergySlot {

    @Shadow
    EnergyInventorySlot energySlot;

    @Override
    public EnergyInventorySlot getEnergySlot() {
        return energySlot;
    }
}
