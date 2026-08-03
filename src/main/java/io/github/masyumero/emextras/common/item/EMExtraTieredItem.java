package io.github.masyumero.emextras.common.item;

import io.github.masyumero.emextras.api.tier.EMExtraTier;
import lombok.Getter;
import mekanism.api.text.TextComponentUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

@Getter
public class EMExtraTieredItem extends Item {

    private final EMExtraTier tier;

    public EMExtraTieredItem(EMExtraTier tier, Properties pProperties) {
        super(pProperties);
        this.tier = tier;
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        TextColor color = TextColor.fromRgb(tier.getRgbSupplier().getAsInt());
        return TextComponentUtil.build(color, super.getName(stack));
    }
}
