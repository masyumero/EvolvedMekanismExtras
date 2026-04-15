package io.github.masyumero.emextras.common.item.block;

import io.github.masyumero.emextras.common.block.attribute.EMExtraAttribute;
import io.github.masyumero.emextras.common.tier.EMExtraIPTier;
import io.github.masyumero.emextras.common.tile.multiblock.TileEntityEMExtraInductionProvider;
import mekanism.api.text.EnumColor;
import mekanism.common.MekanismLang;
import mekanism.common.block.prefab.BlockTile;
import mekanism.common.content.blocktype.BlockTypeTile;
import mekanism.common.util.text.EnergyDisplay;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class EMExtraItemBlockInductionProvider extends EMExtraItemBlockTooltip<BlockTile<TileEntityEMExtraInductionProvider, BlockTypeTile<TileEntityEMExtraInductionProvider>>> {

    public EMExtraItemBlockInductionProvider(BlockTile<TileEntityEMExtraInductionProvider, BlockTypeTile<TileEntityEMExtraInductionProvider>> block) {
        super(block, new Properties());
    }

    @Override
    @NotNull
    public EMExtraIPTier getEMExtraTier() {
        return Objects.requireNonNull(EMExtraAttribute.getTier(getBlock(), EMExtraIPTier.class));
    }

    @Override
    protected void addStats(@NotNull ItemStack stack, Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        EMExtraIPTier tier = getEMExtraTier();
        tooltip.add(MekanismLang.INDUCTION_PORT_OUTPUT_RATE.translateColored(tier.getEMExtraTier().getColor(), EnumColor.GRAY, EnergyDisplay.of(tier.getOutput())));
    }
}
