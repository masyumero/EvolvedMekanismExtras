package io.github.masyumero.emextras.common.item.block.machine;

import com.jerry.mekanism_extras.common.item.block.ExtraItemBlockTooltip;
import mekanism.common.block.prefab.BlockTile;
import mekanism.common.item.interfaces.IItemSustainedInventory;

public class EMExtraItemBlockMachine extends ExtraItemBlockTooltip<BlockTile<?, ?>> implements IItemSustainedInventory {

    public EMExtraItemBlockMachine(BlockTile<?, ?> block) {
        super(block);
    }
}
