package io.github.masyumero.emextras.datagen.common.registries;

import io.github.masyumero.emextras.EMExtras;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.PackOutput;

import java.util.concurrent.CompletableFuture;

public class EMExtrasDatapackRegistryProvider extends BaseDatapackRegistryProvider {

    public EMExtrasDatapackRegistryProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, EMExtras.MODID);
    }

    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder();
}
