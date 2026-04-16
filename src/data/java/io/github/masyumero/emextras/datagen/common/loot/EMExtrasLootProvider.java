package io.github.masyumero.emextras.datagen.common.loot;

import io.github.masyumero.emextras.datagen.common.loot.table.EMExtrasBlockLootProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;

public class EMExtrasLootProvider extends BaseLootProvider {

    public EMExtrasLootProvider(PackOutput output) {
        super(output, List.of(
                new SubProviderEntry(EMExtrasBlockLootProvider::new, LootContextParamSets.BLOCK)
        ));
    }
}
