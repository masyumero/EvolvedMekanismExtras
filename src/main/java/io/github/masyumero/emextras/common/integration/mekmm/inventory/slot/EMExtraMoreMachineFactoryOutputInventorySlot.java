package io.github.masyumero.emextras.common.integration.mekmm.inventory.slot;

import io.github.masyumero.emextras.common.integration.mekmm.tile.TileEntityEMExtraMoreMachineFactory;

import mekanism.api.IContentsListener;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.common.inventory.container.slot.ContainerSlotType;
import mekanism.common.inventory.slot.BasicInventorySlot;

import net.minecraft.world.item.ItemStack;

import org.jetbrains.annotations.Nullable;

@NothingNullByDefault
public class EMExtraMoreMachineFactoryOutputInventorySlot extends BasicInventorySlot {

    private final TileEntityEMExtraMoreMachineFactory<?> factory;

    public static EMExtraMoreMachineFactoryOutputInventorySlot at(TileEntityEMExtraMoreMachineFactory<?> factory, @Nullable IContentsListener listener, int x, int y) {
        return new EMExtraMoreMachineFactoryOutputInventorySlot(factory, listener, x, y);
    }

    private EMExtraMoreMachineFactoryOutputInventorySlot(TileEntityEMExtraMoreMachineFactory<?> factory, @Nullable IContentsListener listener, int x, int y) {
        super(alwaysTrueBi, internalOnly, alwaysTrue, listener, x, y);
        this.setSlotType(ContainerSlotType.OUTPUT);
        this.factory = factory;
    }

    @Override
    public int getLimit(ItemStack stack) {
        try {
            return Math.multiplyExact(super.getLimit(stack), 8 << factory.tier.ordinal());
        } catch (ArithmeticException ignored) {
            return Integer.MAX_VALUE;
        }
    }
}