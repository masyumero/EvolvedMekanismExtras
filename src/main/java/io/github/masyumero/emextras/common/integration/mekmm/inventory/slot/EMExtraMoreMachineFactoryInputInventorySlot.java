package io.github.masyumero.emextras.common.integration.mekmm.inventory.slot;

import io.github.masyumero.emextras.common.integration.mekmm.tile.TileEntityEMExtraMoreMachineFactory;

import mekanism.api.IContentsListener;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.inventory.IInventorySlot;
import mekanism.common.inventory.slot.InputInventorySlot;

import net.minecraft.world.item.ItemStack;

import org.jetbrains.annotations.Nullable;

import java.util.Objects;

@NothingNullByDefault
public class EMExtraMoreMachineFactoryInputInventorySlot extends InputInventorySlot {

    private final TileEntityEMExtraMoreMachineFactory<?> factory;

    public static EMExtraMoreMachineFactoryInputInventorySlot create(TileEntityEMExtraMoreMachineFactory<?> factory, int process, IInventorySlot outputSlot, @Nullable IContentsListener listener,
                                                                   int x, int y) {
        return create(factory, process, outputSlot, null, listener, x, y);
    }

    public static EMExtraMoreMachineFactoryInputInventorySlot create(TileEntityEMExtraMoreMachineFactory<?> factory, int process, IInventorySlot outputSlot, @Nullable IInventorySlot secondaryOutputSlot,
                                                                   @Nullable IContentsListener listener, int x, int y) {
        Objects.requireNonNull(factory, "Factory cannot be null");
        Objects.requireNonNull(outputSlot, "Primary output slot cannot be null");
        return new EMExtraMoreMachineFactoryInputInventorySlot(factory, process, outputSlot, secondaryOutputSlot, listener, x, y);
    }

    private EMExtraMoreMachineFactoryInputInventorySlot(TileEntityEMExtraMoreMachineFactory<?> factory, int process, IInventorySlot outputSlot, @Nullable IInventorySlot secondaryOutputSlot,
                                                      @Nullable IContentsListener listener, int x, int y) {
        super(stack -> factory.inputProducesOutput(process, stack, outputSlot, secondaryOutputSlot, false),
                factory::isValidInputItem, listener, x, y);
        this.factory = factory;
    }

    // Increase access level of setStackUnchecked
    @Override
    public void setStackUnchecked(ItemStack stack) {
        super.setStackUnchecked(stack);
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
