package io.github.masyumero.emextras.common.item.block.machine;

import io.github.masyumero.emextras.api.tier.IEMExtraTier;
import io.github.masyumero.emextras.common.block.attribute.EMExtraAttributeFactoryType;
import io.github.masyumero.emextras.common.item.block.ItemBlockEMExtraTooltip;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import io.github.masyumero.emextras.common.block.attribute.EMExtraAttribute;
import io.github.masyumero.emextras.common.block.prefab.BlockEMExtraFactoryMachine;

import mekanism.api.text.EnumColor;
import mekanism.api.text.TextComponentUtil;
import mekanism.common.MekanismLang;
import mekanism.common.attachments.component.AttachedEjector;
import mekanism.common.attachments.component.AttachedSideConfig;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.block.interfaces.IColoredBlock;
import mekanism.common.block.prefab.BlockTile;
import mekanism.common.registries.MekanismDataComponents;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ItemBlockEMExtraFactory extends ItemBlockEMExtraTooltip<BlockTile<?, ?>> {

    @SuppressWarnings("deprecation")
    private static AttachedSideConfig getSideConfig(BlockEMExtraFactoryMachine.BlockEMExtraFactory<?> block) {
        var factoryType = Attribute.getOrThrow(block.builtInRegistryHolder(), EMExtraAttributeFactoryType.class).getFactoryType();
        return switch (factoryType) {
            case ALLOYING, COMBINING -> AttachedSideConfig.EXTRA_MACHINE;
            case SMELTING, ENRICHING, CRUSHING, SAWING -> AttachedSideConfig.ELECTRIC_MACHINE;
            case COMPRESSING, INFUSING -> AttachedSideConfig.ADVANCED_MACHINE;
            case PURIFYING, INJECTING -> AttachedSideConfig.ADVANCED_MACHINE_INPUT_ONLY;
        };
    }

    public ItemBlockEMExtraFactory(BlockEMExtraFactoryMachine.BlockEMExtraFactory<?> block, Properties properties) {
        super(block, true, properties
                .component(MekanismDataComponents.SORTING, false)
                .component(MekanismDataComponents.EJECTOR, AttachedEjector.DEFAULT)
                .component(MekanismDataComponents.SIDE_CONFIG, getSideConfig(block)));
    }

    public EMExtraFactoryTier getEMExtraTier() {
        return EMExtraAttribute.getEMExtraTier(getBlock(), EMExtraFactoryTier.class);
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

    @Override
    protected void addTypeDetails(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        // Should always be present but validate it just in case
        EMExtraAttributeFactoryType factoryType = Attribute.get(getBlock(), EMExtraAttributeFactoryType.class);
        if (factoryType != null) {
            tooltip.add(MekanismLang.FACTORY_TYPE.translateColored(EnumColor.INDIGO, EnumColor.GRAY, factoryType.getFactoryType()));
        }
        super.addTypeDetails(stack, context, tooltip, flag);
    }
}