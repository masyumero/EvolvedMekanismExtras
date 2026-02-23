package io.github.masyumero.emextras.datagen.common.loot.table;

import io.github.masyumero.emextras.common.integration.mekaf.registries.EMExtraAdvancedFactoryBlocks;
import io.github.masyumero.emextras.common.integration.mekmm.registries.EMExtraMoreMachineBlocks;
import io.github.masyumero.emextras.common.registry.EMExtraBlocks;
import net.minecraft.core.HolderLookup;

public class EMExtrasBlockLootTables extends BaseBlockLootTables {

    public EMExtrasBlockLootTables(HolderLookup.Provider provider) {
        super(provider);
    }

    @Override
    protected void generate() {
        dropSelfWithContents(EMExtraBlocks.BLOCK.getPrimaryEntries());
        dropSelfWithContents(EMExtraAdvancedFactoryBlocks.AF_BLOCKS.getPrimaryEntries());
        dropSelfWithContents(EMExtraMoreMachineBlocks.MM_BLOCKS.getPrimaryEntries());
    }
}
