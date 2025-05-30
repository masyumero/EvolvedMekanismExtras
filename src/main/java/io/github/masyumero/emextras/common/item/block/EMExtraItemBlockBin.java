package io.github.masyumero.emextras.common.item.block;

import io.github.masyumero.emextras.EMExtrasLang;
import io.github.masyumero.emextras.common.block.attribute.EMExtraAttribute;
import io.github.masyumero.emextras.common.block.basic.EMExtraBlockBin;
import io.github.masyumero.emextras.common.inventory.EMExtraBinMekanismInventory;
import io.github.masyumero.emextras.common.inventory.slot.EMExtraBinInventorySlot;
import io.github.masyumero.emextras.common.tier.EMExtraBTier;
import mekanism.api.text.EnumColor;
import mekanism.common.MekanismLang;
import mekanism.common.item.interfaces.IItemSustainedInventory;
import mekanism.common.util.text.TextUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class EMExtraItemBlockBin extends EMExtraItemBlockTooltip<EMExtraBlockBin> implements IItemSustainedInventory {
    public EMExtraItemBlockBin(EMExtraBlockBin block) {
        super(block, new Item.Properties().stacksTo(1));
    }

    @Override
    public EMExtraBTier getEMExtraTier() {
        return EMExtraAttribute.getTier(getBlock(), EMExtraBTier.class);
    }

    @Override
    protected void addStats(@NotNull ItemStack stack, Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        EMExtraBinMekanismInventory inventory = EMExtraBinMekanismInventory.create(stack);
        EMExtraBTier tier = getEMExtraTier();
        if (inventory != null && tier != null) {
            EMExtraBinInventorySlot slot = inventory.getBinSlot();
            if (slot.isEmpty()) {
                tooltip.add(MekanismLang.EMPTY.translateColored(EnumColor.DARK_RED));
            } else {
                tooltip.add(MekanismLang.STORING.translateColored(EnumColor.BRIGHT_GREEN, EnumColor.GRAY, slot.getStack()));
                tooltip.add(MekanismLang.ITEM_AMOUNT.translateColored(EnumColor.PURPLE, EnumColor.GRAY, TextUtils.format(slot.getCount())));
            }
            if (slot.isLocked()) {
                tooltip.add(MekanismLang.LOCKED.translateColored(EnumColor.AQUA, EnumColor.GRAY, slot.getLockStack()));
            }
            if (tier == EMExtraBTier.INFINITE_MULTIVERSAL) {
                tooltip.add(EMExtrasLang.INFINITE_MULTIVERSAL_BIN.translateColored(EnumColor.RED));
            }
            tooltip.add(MekanismLang.CAPACITY_ITEMS.translateColored(EnumColor.INDIGO, EnumColor.GRAY, TextUtils.format(tier.getStorage())));
        }
    }

    @Override
    public boolean canContentsDrop(ItemStack stack) {
        return true;
    }
}