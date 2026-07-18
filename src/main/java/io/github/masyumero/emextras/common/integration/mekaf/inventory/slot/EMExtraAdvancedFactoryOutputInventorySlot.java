package io.github.masyumero.emextras.common.integration.mekaf.inventory.slot;

import io.github.masyumero.emextras.common.integration.mekaf.tile.factory.base.TileEntityEMExtraAdvancedFactoryBase;
import mekanism.api.IContentsListener;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.common.inventory.container.slot.ContainerSlotType;
import mekanism.common.inventory.slot.BasicInventorySlot;

import net.minecraft.world.item.ItemStack;

import org.jetbrains.annotations.Nullable;

@NothingNullByDefault
public class EMExtraAdvancedFactoryOutputInventorySlot extends BasicInventorySlot {

    private final TileEntityEMExtraAdvancedFactoryBase<?> factory;

    public static EMExtraAdvancedFactoryOutputInventorySlot at(TileEntityEMExtraAdvancedFactoryBase<?> factory, @Nullable IContentsListener listener, int x, int y) {
        return new EMExtraAdvancedFactoryOutputInventorySlot(factory, listener, x, y);
    }

    private EMExtraAdvancedFactoryOutputInventorySlot(TileEntityEMExtraAdvancedFactoryBase<?> factory, @Nullable IContentsListener listener, int x, int y) {
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