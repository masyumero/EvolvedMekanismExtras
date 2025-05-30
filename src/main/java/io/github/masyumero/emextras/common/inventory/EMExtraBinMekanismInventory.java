package io.github.masyumero.emextras.common.inventory;

import io.github.masyumero.emextras.common.inventory.slot.EMExtraBinInventorySlot;
import io.github.masyumero.emextras.common.item.block.EMExtraItemBlockBin;
import mekanism.api.inventory.IInventorySlot;
import mekanism.common.inventory.ItemStackMekanismInventory;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class EMExtraBinMekanismInventory extends ItemStackMekanismInventory {
    private EMExtraBinInventorySlot binSlot;

    private EMExtraBinMekanismInventory(@NotNull ItemStack stack) {
        super(stack);
    }

    @NotNull
    @Override
    protected List<IInventorySlot> getInitialInventory() {
        binSlot = EMExtraBinInventorySlot.create(this, ((EMExtraItemBlockBin) stack.getItem()).getEMExtraTier());
        return Collections.singletonList(binSlot);
    }

    @Nullable
    public static EMExtraBinMekanismInventory create(@NotNull ItemStack stack) {
        if (!stack.isEmpty() && stack.getItem() instanceof EMExtraItemBlockBin) {
            return new EMExtraBinMekanismInventory(stack);
        }
        return null;
    }

    public EMExtraBinInventorySlot getBinSlot() {
        return binSlot;
    }
}