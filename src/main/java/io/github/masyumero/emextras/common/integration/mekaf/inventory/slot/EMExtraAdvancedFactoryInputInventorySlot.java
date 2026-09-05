package io.github.masyumero.emextras.common.integration.mekaf.inventory.slot;

import io.github.masyumero.emextras.common.integration.mekaf.tile.factory.TileEntityEMExtraLiquifyingFactory;
import io.github.masyumero.emextras.common.integration.mekaf.tile.factory.TileEntityEMExtraPRCFactory;
import io.github.masyumero.emextras.common.integration.mekaf.tile.factory.base.TileEntityEMExtraAdvancedFactoryBase;
import io.github.masyumero.emextras.common.integration.mekaf.tile.factory.base.TileEntityEMExtraItemToChemicalFactory;
import io.github.masyumero.emextras.common.integration.mekaf.tile.factory.base.TileEntityEMExtraItemToItemAdvancedFactory;
import mekanism.api.IContentsListener;
import mekanism.api.chemical.IChemicalTank;
import mekanism.api.fluid.IExtendedFluidTank;
import mekanism.api.inventory.IInventorySlot;
import mekanism.common.inventory.slot.InputInventorySlot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class EMExtraAdvancedFactoryInputInventorySlot extends InputInventorySlot {

    private final TileEntityEMExtraAdvancedFactoryBase<?> factory;

    public static EMExtraAdvancedFactoryInputInventorySlot create(TileEntityEMExtraItemToItemAdvancedFactory<?> factory, int process, IInventorySlot outputSlot, @Nullable IContentsListener listener, int x, int y) {
        Objects.requireNonNull(factory, "Factory cannot be null");
        Objects.requireNonNull(outputSlot, "Item output tank cannot be null");
        return new EMExtraAdvancedFactoryInputInventorySlot(factory, process, outputSlot, listener, x, y);
    }

    private EMExtraAdvancedFactoryInputInventorySlot(TileEntityEMExtraItemToItemAdvancedFactory<?> factory, int process, IInventorySlot outputSlot, @Nullable IContentsListener listener, int x, int y) {
        super(stack -> factory.isItemValidForSlot(stack) && factory.inputProducesOutput(process, stack, outputSlot, false),
                factory::isValidInputItem, listener, x, y);
        this.factory = factory;
    }

    public static EMExtraAdvancedFactoryInputInventorySlot create(TileEntityEMExtraItemToChemicalFactory<?> factory, int process, IChemicalTank outputTank, @Nullable IContentsListener listener, int x, int y) {
        Objects.requireNonNull(factory, "Factory cannot be null");
        Objects.requireNonNull(outputTank, "Chemical output tank cannot be null");
        return new EMExtraAdvancedFactoryInputInventorySlot(factory, process, outputTank, listener, x, y);
    }

    private EMExtraAdvancedFactoryInputInventorySlot(TileEntityEMExtraItemToChemicalFactory<?> factory, int process, IChemicalTank outputTank, @Nullable IContentsListener listener, int x, int y) {
        super(stack -> factory.isItemValidForSlot(stack) && factory.inputProducesOutput(process, stack, outputTank, false),
                factory::isValidInputItem, listener, x, y);
        this.factory = factory;
    }

    public static EMExtraAdvancedFactoryInputInventorySlot create(TileEntityEMExtraPRCFactory factory, int process, IInventorySlot outputSlot, IChemicalTank outputTank, @Nullable IContentsListener listener, int x, int y) {
        Objects.requireNonNull(factory, "Factory cannot be null");
        Objects.requireNonNull(outputTank, "Chemical output tank cannot be null");
        return new EMExtraAdvancedFactoryInputInventorySlot(factory, process, outputSlot, outputTank, listener, x, y);
    }

    private EMExtraAdvancedFactoryInputInventorySlot(TileEntityEMExtraPRCFactory factory, int process, IInventorySlot outputSlot, IChemicalTank outputTank, @Nullable IContentsListener listener, int x, int y) {
        super(stack -> factory.isItemValidForSlot(stack) && factory.inputProducesOutput(process, stack, outputSlot, outputTank, false),
                factory::isValidInputItem, listener, x, y);
        this.factory = factory;
    }

    public static EMExtraAdvancedFactoryInputInventorySlot create(TileEntityEMExtraLiquifyingFactory factory, int process, IInventorySlot outputSlot, IExtendedFluidTank outputTank, @Nullable IContentsListener listener, int x, int y) {
        Objects.requireNonNull(factory, "Factory cannot be null");
        Objects.requireNonNull(outputTank, "Fluid output tank cannot be null");
        return new EMExtraAdvancedFactoryInputInventorySlot(factory, process, outputSlot, outputTank, listener, x, y);
    }

    private EMExtraAdvancedFactoryInputInventorySlot(TileEntityEMExtraLiquifyingFactory factory, int process, IInventorySlot outputSlot, IExtendedFluidTank outputTank, @Nullable IContentsListener listener, int x, int y) {
        super(stack -> factory.isItemValidForSlot(stack) && factory.inputProducesOutput(process, stack, outputSlot, outputTank, false),
                factory::isValidInputItem, listener, x, y);
        this.factory = factory;
    }

    @Override
    public int getLimit(@NotNull ItemStack stack) {
        try {
            return Math.multiplyExact(super.getLimit(stack), 8 << factory.tier.ordinal());
        } catch (ArithmeticException ignored) {
            return Integer.MAX_VALUE;
        }
    }
}