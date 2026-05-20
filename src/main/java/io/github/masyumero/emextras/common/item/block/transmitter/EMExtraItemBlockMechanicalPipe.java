package io.github.masyumero.emextras.common.item.block.transmitter;

import io.github.masyumero.emextras.common.block.transmitter.EMExtraBlockMechanicalPipe;
import io.github.masyumero.emextras.common.tier.transmitter.EMExtraPTier;

import io.github.masyumero.emextras.common.util.EMExtraTransporterUtils;
import mekanism.api.text.EnumColor;
import mekanism.api.text.TextComponentUtil;
import mekanism.client.key.MekKeyHandler;
import mekanism.client.key.MekanismKeyHandler;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.item.block.ItemBlockMekanism;
import mekanism.common.tier.PipeTier;
import mekanism.common.util.text.TextUtils;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

import javax.annotation.Nonnull;

public class EMExtraItemBlockMechanicalPipe extends ItemBlockMekanism<EMExtraBlockMechanicalPipe> {

    public EMExtraItemBlockMechanicalPipe(EMExtraBlockMechanicalPipe block) {
        super(block, new Item.Properties());
    }

    @Nonnull
    public PipeTier getTier() {
        return Objects.requireNonNull(Attribute.getTier(this.getBlock(), PipeTier.class));
    }

    @NotNull
    @Override
    public Component getName(@NotNull ItemStack stack) {
        return TextComponentUtil.build(TextColor.fromRgb(EMExtraTransporterUtils.baseToEMExtraTier(getTier().getBaseTier()).getRgbSupplier().getAsInt()), super.getName(stack));
    }

    public void appendHoverText(@Nonnull ItemStack stack, Level world, @Nonnull List<Component> tooltip, @Nonnull TooltipFlag flag) {
        if (MekKeyHandler.isKeyPressed(MekanismKeyHandler.detailsKey)) {
            tooltip.add(MekanismLang.CAPABLE_OF_TRANSFERRING.translateColored(EnumColor.DARK_GRAY));
            tooltip.add(MekanismLang.FLUIDS.translateColored(EnumColor.PURPLE, EnumColor.GRAY, MekanismLang.FORGE));
        } else {
            tooltip.add(MekanismLang.CAPACITY_MB_PER_TICK.translateColored(EnumColor.INDIGO, EnumColor.GRAY, TextUtils.format(EMExtraPTier.getPipeCapacity(this.getTier()))));
            tooltip.add(MekanismLang.PUMP_RATE_MB.translateColored(EnumColor.INDIGO, EnumColor.GRAY, TextUtils.format(EMExtraPTier.getPipePullAmount(this.getTier()))));
            tooltip.add(MekanismLang.HOLD_FOR_DETAILS.translateColored(EnumColor.GRAY, EnumColor.INDIGO, MekanismKeyHandler.detailsKey.getTranslatedKeyMessage()));
        }
    }
}