package io.github.masyumero.emextras.common.inventory.slot;

import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import mekanism.api.IContentsListener;
import mekanism.api.functions.ConstantPredicates;
import mekanism.common.inventory.container.slot.ContainerSlotType;
import mekanism.common.inventory.slot.InputInventorySlot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.Predicate;

public class EMExtraStackableInputInventorySlot extends InputInventorySlot {

    private static EMExtraFactoryTier isTier = EMExtraFactoryTier.ABSOLUTE_OVERCLOCKED;

    public static EMExtraStackableInputInventorySlot at(EMExtraFactoryTier tier, Predicate<@NotNull ItemStack> isItemValid, @Nullable IContentsListener listener, int x, int y) {
        return at(tier, ConstantPredicates.alwaysTrue(), isItemValid, listener, x, y);
    }

    public static EMExtraStackableInputInventorySlot at(EMExtraFactoryTier tier, Predicate<@NotNull ItemStack> insertPredicate, Predicate<@NotNull ItemStack> isItemValid, @Nullable IContentsListener listener,
                                                 int x, int y) {
        Objects.requireNonNull(insertPredicate, "Insertion check cannot be null");
        Objects.requireNonNull(isItemValid, "Item validity check cannot be null");
        return new EMExtraStackableInputInventorySlot(tier, insertPredicate, isItemValid, listener, x, y);
    }

    protected EMExtraStackableInputInventorySlot(EMExtraFactoryTier tier, Predicate<@NotNull ItemStack> insertPredicate, Predicate<@NotNull ItemStack> isItemValid, @Nullable IContentsListener listener, int x, int y) {
        super(insertPredicate, isItemValid, listener, x, y);
        isTier = tier;
        setSlotType(ContainerSlotType.EXTRA);
    }

    @Override
    public int getLimit(ItemStack stack) {
        int process = super.getLimit(stack) * isTier.processes;
        return switch (isTier) {
            case ABSOLUTE_OVERCLOCKED -> process * 8;
            case SUPREME_QUANTUM -> process * 16;
            case COSMIC_DENSE -> process * 32;
            case INFINITE_MULTIVERSAL -> process * 64;
        };
    }
}