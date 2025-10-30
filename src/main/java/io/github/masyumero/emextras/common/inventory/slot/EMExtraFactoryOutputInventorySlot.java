package io.github.masyumero.emextras.common.inventory.slot;

import io.github.masyumero.emextras.common.tile.factory.TileEntityEMExtraFactory;
import mekanism.api.IContentsListener;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.common.inventory.container.slot.ContainerSlotType;
import mekanism.common.inventory.slot.BasicInventorySlot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

@NothingNullByDefault
public class EMExtraFactoryOutputInventorySlot extends BasicInventorySlot {

    private final TileEntityEMExtraFactory<?> factory;

    public static EMExtraFactoryOutputInventorySlot at(TileEntityEMExtraFactory<?> factory, @Nullable IContentsListener listener, int x, int y) {
        return new EMExtraFactoryOutputInventorySlot(factory, listener, x, y);
    }

    private EMExtraFactoryOutputInventorySlot(TileEntityEMExtraFactory<?> factory, @Nullable IContentsListener listener, int x, int y) {
        super(alwaysTrueBi, internalOnly, alwaysTrue, listener, x, y);
        setSlotType(ContainerSlotType.OUTPUT);
        this.factory = factory;
    }

    @Override
    public int getLimit(ItemStack stack) {
        if (factory != null) {
            return switch (factory.tier) {
                case ABSOLUTE_OVERCLOCKED -> super.getLimit(stack) * 8;
                case SUPREME_QUANTUM -> super.getLimit(stack) * 16;
                case COSMIC_DENSE -> super.getLimit(stack) * 32;
                case INFINITE_MULTIVERSAL -> super.getLimit(stack) * 64;
            };
        }
        return super.getLimit(stack);
    }
}
