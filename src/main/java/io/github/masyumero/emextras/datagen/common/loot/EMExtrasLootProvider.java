package io.github.masyumero.emextras.datagen.common.loot;

import io.github.masyumero.emextras.datagen.common.loot.table.EMExtrasBlockLootTables;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class EMExtrasLootProvider extends BaseLootProvider {

    public EMExtrasLootProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, List.of(
                new SubProviderEntry(EMExtrasBlockLootTables::new, LootContextParamSets.BLOCK)
        ), provider);
    }
}
