package io.github.masyumero.emextras.datagen.common.loot.table;

import io.github.masyumero.emextras.common.integration.mekaf.regisrty.EMExtraAdvancedFactoryBlocks;
import io.github.masyumero.emextras.common.integration.mekmm.registry.EMExtraMoreMachineBlocks;
import io.github.masyumero.emextras.common.registry.EMExtraBlocks;

public class EMExtrasBlockLootProvider extends BaseBlockLootTables {

    @Override
    protected void generate() {
        dropSelfWithContents(EMExtraBlocks.BLOCK.getAllBlocks());
        dropSelfWithContents(EMExtraAdvancedFactoryBlocks.BLOCKS.getAllBlocks());
        dropSelfWithContents(EMExtraMoreMachineBlocks.BLOCKS.getAllBlocks());
    }
}
