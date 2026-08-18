package io.github.masyumero.emextras.common.integration.mekmm.item.block.machine;

import com.jerry.mekmm.common.attachments.component.MoreMachineAttachedSideConfig;
import com.jerry.mekmm.common.block.attribute.MoreMachineAttributeFactoryType;

import io.github.masyumero.emextras.common.block.attribute.EMExtraAttribute;
import io.github.masyumero.emextras.common.integration.mekmm.block.prefab.EMExtraMoreMachineBlockFactoryMachine;
import io.github.masyumero.emextras.common.item.block.ItemBlockEMExtraTooltip;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;

import mekanism.api.text.EnumColor;
import mekanism.common.MekanismLang;
import mekanism.common.attachments.component.AttachedEjector;
import mekanism.common.attachments.component.AttachedSideConfig;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.block.prefab.BlockTile;
import mekanism.common.registries.MekanismDataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ItemBlockEMExtraMoreMachineFactory extends ItemBlockEMExtraTooltip<BlockTile<?, ?>> {

    private static AttachedSideConfig getSideConfig(EMExtraMoreMachineBlockFactoryMachine.BlockEMExtraMoreMachineFactory<?> block) {
        return switch (Attribute.getOrThrow(block.builtInRegistryHolder(), MoreMachineAttributeFactoryType.class).getMoreMachineFactoryType()) {
            case CNC_STAMPING -> AttachedSideConfig.EXTRA_MACHINE;
            case RECYCLING, CNC_LATHING, CNC_ROLLING_MILL -> AttachedSideConfig.ELECTRIC_MACHINE;
            case PLANTING_STATION, REPLICATING -> AttachedSideConfig.ADVANCED_MACHINE_INPUT_ONLY;
            case PRESSING -> MoreMachineAttachedSideConfig.PRESSER;
        };
    }

    public ItemBlockEMExtraMoreMachineFactory(EMExtraMoreMachineBlockFactoryMachine.BlockEMExtraMoreMachineFactory<?> block, Properties properties) {
        super(block, true, properties
                .component(MekanismDataComponents.SORTING, false)
                .component(MekanismDataComponents.EJECTOR, AttachedEjector.DEFAULT)
                .component(MekanismDataComponents.SIDE_CONFIG, getSideConfig(block)));
    }

    @Override
    public EMExtraFactoryTier getEMExtraTier() {
        return EMExtraAttribute.getEMExtraTier(getBlock(), EMExtraFactoryTier.class);
    }

    @Override
    protected void addTypeDetails(@NotNull ItemStack stack, @NotNull Item.TooltipContext context, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        // Should always be present but validate it just in case
        MoreMachineAttributeFactoryType factoryType = Attribute.get(getBlock(), MoreMachineAttributeFactoryType.class);
        if (factoryType != null) {
            tooltip.add(MekanismLang.FACTORY_TYPE.translateColored(EnumColor.INDIGO, EnumColor.GRAY, factoryType.getMoreMachineFactoryType()));
        }
        super.addTypeDetails(stack, context, tooltip, flag);
    }
}