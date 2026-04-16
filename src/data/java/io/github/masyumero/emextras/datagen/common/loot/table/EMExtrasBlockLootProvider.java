package io.github.masyumero.emextras.datagen.common.loot.table;

import io.github.masyumero.emextras.common.registry.EMExtrasBlock;

public class EMExtrasBlockLootProvider extends BaseBlockLootTables {

    @Override
    protected void generate() {
        dropSelfWithContents(EMExtrasBlock.BLOCK.getAllBlocks());
    }
}
