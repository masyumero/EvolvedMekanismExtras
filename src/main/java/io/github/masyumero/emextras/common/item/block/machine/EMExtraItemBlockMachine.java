package io.github.masyumero.emextras.common.item.block.machine;

import io.github.masyumero.emextras.common.item.block.EMExtraItemBlockTooltip;
import mekanism.common.block.prefab.BlockTile;
import mekanism.common.item.interfaces.IItemSustainedInventory;

public class EMExtraItemBlockMachine extends EMExtraItemBlockTooltip<BlockTile<?, ?>> implements IItemSustainedInventory {

    public EMExtraItemBlockMachine(BlockTile<?, ?> block) {
        super(block);
    }
}
