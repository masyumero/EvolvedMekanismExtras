package io.github.masyumero.emextras.common.item.block;

import io.github.masyumero.emextras.api.tier.IEMExtraTier;
import mekanism.api.text.TextComponentUtil;
import mekanism.common.block.interfaces.IColoredBlock;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

import org.jetbrains.annotations.NotNull;

public class ItemBlockEMExtra<BLOCK extends Block> extends BlockItem {

    public ItemBlockEMExtra(Block block, Properties properties) {
        super(block, properties);
    }

    @NotNull
    @Override
    @SuppressWarnings("unchecked")
    public BLOCK getBlock() {
        return (BLOCK) super.getBlock();
    }

    public IEMExtraTier getEMExtraTier() {
        return null;
    }

    @NotNull
    @Override
    public Component getName(@NotNull ItemStack stack) {
        if (getBlock() instanceof IColoredBlock coloredBlock) {
            return TextComponentUtil.build(coloredBlock.getColor(), super.getName(stack));
        }
        IEMExtraTier tier = getEMExtraTier();
        if (tier == null) {
            return super.getName(stack);
        }
        TextColor color = TextColor.fromRgb(tier.getEMExtraTier().getRgbSupplier().getAsInt());
        return TextComponentUtil.build(color, super.getName(stack));
    }
}