package io.github.masyumero.emextras.datagen.common.loot.table;

import io.github.masyumero.emextras.common.registry.EMExtraBlocks;
import net.minecraft.core.HolderLookup;

public class EMExtrasBlockLootTables extends BaseBlockLootTables {

    public EMExtrasBlockLootTables(HolderLookup.Provider provider) {
        super(provider);
    }

    @Override
    protected void generate() {
        dropSelfWithContents(EMExtraBlocks.BLOCK.getPrimaryEntries());
    }
}
