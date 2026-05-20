package io.github.masyumero.emextras.common.item.block.transmitter;

import io.github.masyumero.emextras.common.block.transmitter.EMExtraBlockUniversalCable;
import io.github.masyumero.emextras.common.tier.transmitter.EMExtraCTier;

import io.github.masyumero.emextras.common.util.EMExtraTransporterUtils;
import mekanism.api.text.EnumColor;
import mekanism.api.text.TextComponentUtil;
import mekanism.client.key.MekKeyHandler;
import mekanism.client.key.MekanismKeyHandler;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.item.block.ItemBlockMekanism;
import mekanism.common.tier.CableTier;
import mekanism.common.util.text.EnergyDisplay;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class EMExtraItemBlockUniversalCable extends ItemBlockMekanism<EMExtraBlockUniversalCable> {

    public EMExtraItemBlockUniversalCable(EMExtraBlockUniversalCable block) {
        super(block, new Item.Properties());
    }

    @NotNull
    @Override
    public CableTier getTier() {
        return Objects.requireNonNull(Attribute.getTier(getBlock(), CableTier.class));
    }

    @NotNull
    @Override
    public Component getName(@NotNull ItemStack stack) {
        return TextComponentUtil.build(TextColor.fromRgb(EMExtraTransporterUtils.baseToEMExtraTier(getTier().getBaseTier()).getRgbSupplier().getAsInt()), super.getName(stack));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        if (MekKeyHandler.isKeyPressed(MekanismKeyHandler.detailsKey)) {
            tooltip.add(MekanismLang.CAPABLE_OF_TRANSFERRING.translateColored(EnumColor.DARK_GRAY));
            tooltip.add(MekanismLang.GENERIC_TRANSFER.translateColored(EnumColor.PURPLE, MekanismLang.ENERGY_FORGE_SHORT, MekanismLang.FORGE));
            tooltip.add(MekanismLang.GENERIC_TRANSFER.translateColored(EnumColor.PURPLE, MekanismLang.ENERGY_EU_SHORT, MekanismLang.IC2));
            tooltip.add(MekanismLang.GENERIC_TRANSFER.translateColored(EnumColor.PURPLE, MekanismLang.ENERGY_JOULES_PLURAL, MekanismLang.MEKANISM));
        } else {
            tooltip.add(MekanismLang.CAPACITY_PER_TICK.translateColored(EnumColor.INDIGO, EnumColor.GRAY, EnergyDisplay.of(EMExtraCTier.getCapacityAsFloatingLong(this.getTier()))));
            tooltip.add(MekanismLang.HOLD_FOR_DETAILS.translateColored(EnumColor.GRAY, EnumColor.INDIGO, MekanismKeyHandler.detailsKey.getTranslatedKeyMessage()));
        }
    }
}