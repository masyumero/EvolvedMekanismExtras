package io.github.masyumero.emextras.common.item.block.machine;

import com.jerry.mekanism_extras.common.tier.AdvancedFactoryTier;
import io.github.masyumero.emextras.common.block.attribute.EMExtraAttribute;
import io.github.masyumero.emextras.common.block.attribute.EMExtraAttributeFactoryType;
import io.github.masyumero.emextras.common.block.prefab.BlockEMExtraFactoryMachine;
import mekanism.api.text.EnumColor;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.Attribute;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ItemBlockEMExtraFactory extends EMExtraItemBlockMachine {

    public ItemBlockEMExtraFactory(BlockEMExtraFactoryMachine.BlockEMExtraFactory<?> block) {
        super(block);
    }

    @Override
    public AdvancedFactoryTier getAdvanceTier() {
        return EMExtraAttribute.getTier(getBlock(), AdvancedFactoryTier.class);
    }

    @Override
    protected void addTypeDetails(@NotNull ItemStack stack, Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        //Should always be present but validate it just in case
        Attribute.ifPresent(getBlock(), EMExtraAttributeFactoryType.class, attribute -> tooltip.add(MekanismLang.FACTORY_TYPE.translateColored(EnumColor.INDIGO, EnumColor.GRAY,
                attribute.getFactoryType())));
        super.addTypeDetails(stack, world, tooltip, flag);
    }
}