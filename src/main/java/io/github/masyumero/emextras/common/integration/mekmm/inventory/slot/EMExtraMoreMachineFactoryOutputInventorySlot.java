package io.github.masyumero.emextras.common.integration.mekmm.inventory.slot;

import io.github.masyumero.emextras.common.integration.mekmm.tile.factory.TileEntityEMExtraMoreMachineFactory;

import mekanism.api.IContentsListener;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.functions.ConstantPredicates;
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
        super(ConstantPredicates.alwaysTrueBi(), ConstantPredicates.internalOnly(), ConstantPredicates.alwaysTrue(), listener, x, y);
        setSlotType(ContainerSlotType.OUTPUT);
        this.factory = factory;
    }

    @Override
    public int getLimit(ItemStack stack) {
        return switch (factory.tier) {
            case ABSOLUTE_OVERCLOCKED -> super.getLimit(stack) * 8;
            case SUPREME_QUANTUM -> super.getLimit(stack) * 16;
            case COSMIC_DENSE -> super.getLimit(stack) * 32;
            case INFINITE_MULTIVERSAL -> super.getLimit(stack) * 64;
        };
    }
}