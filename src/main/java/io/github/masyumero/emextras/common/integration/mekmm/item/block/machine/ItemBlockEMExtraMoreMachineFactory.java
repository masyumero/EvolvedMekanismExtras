package io.github.masyumero.emextras.common.integration.mekmm.item.block.machine;

import io.github.masyumero.emextras.api.tier.IEMExtraTier;
import io.github.masyumero.emextras.common.block.attribute.EMExtraAttribute;
import io.github.masyumero.emextras.common.integration.mekmm.block.prefab.BlockEMExtraMoreMachineFactory;
import io.github.masyumero.emextras.common.item.block.machine.EMExtraItemBlockMachine;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;

import mekanism.api.text.EnumColor;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.Attribute;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import com.jerry.mekmm.common.block.attribute.AttributeMoreMachineFactoryType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ItemBlockEMExtraMoreMachineFactory extends EMExtraItemBlockMachine {

    public ItemBlockEMExtraMoreMachineFactory(BlockEMExtraMoreMachineFactory<?> block) {
        super(block);
    }

    @Override
    public IEMExtraTier getEMExtraTier() {
        return EMExtraAttribute.getTier(getBlock(), EMExtraFactoryTier.class);
    }

    @Override
    protected void addTypeDetails(@NotNull ItemStack stack, Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        Attribute.ifPresent(getBlock(), AttributeMoreMachineFactoryType.class, attribute -> tooltip.add(MekanismLang.FACTORY_TYPE.translateColored(EnumColor.INDIGO, EnumColor.GRAY,
                attribute.getMoreMachineFactoryType())));
        super.addTypeDetails(stack, world, tooltip, flag);
    }
}