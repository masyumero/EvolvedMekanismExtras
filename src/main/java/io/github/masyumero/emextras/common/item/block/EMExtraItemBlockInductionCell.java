package io.github.masyumero.emextras.common.item.block;

import io.github.masyumero.emextras.common.block.attribute.EMExtraAttribute;
import io.github.masyumero.emextras.common.tier.EMExtraICTier;
import io.github.masyumero.emextras.common.tile.multiblock.TileEntityEMExtraInductionCell;
import mekanism.api.text.EnumColor;
import mekanism.common.MekanismLang;
import mekanism.common.block.prefab.BlockTile;
import mekanism.common.content.blocktype.BlockTypeTile;
import mekanism.common.util.StorageUtils;
import mekanism.common.util.text.EnergyDisplay;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class EMExtraItemBlockInductionCell extends EMExtraItemBlockTooltip<BlockTile<TileEntityEMExtraInductionCell, BlockTypeTile<TileEntityEMExtraInductionCell>>> {

    public EMExtraItemBlockInductionCell(BlockTile<TileEntityEMExtraInductionCell, BlockTypeTile<TileEntityEMExtraInductionCell>> block) {
        super(block, new Properties());
    }

    @Override
    @NotNull
    public EMExtraICTier getEMExtraTier() {
        return Objects.requireNonNull(EMExtraAttribute.getTier(getBlock(), EMExtraICTier.class));
    }

    @Override
    protected void addStats(@NotNull ItemStack stack, Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        EMExtraICTier tier = getEMExtraTier();
        tooltip.add(MekanismLang.CAPACITY.translateColored(tier.getEMExtraTier().getColor(), EnumColor.GRAY, EnergyDisplay.of(tier.getMaxEnergy())));
        tooltip.add(MekanismLang.STORED_ENERGY.translateColored(EnumColor.BRIGHT_GREEN, EnumColor.GRAY, EnergyDisplay.of(StorageUtils.getStoredEnergyFromNBT(stack),
                tier.getMaxEnergy())));
    }
}
