package io.github.masyumero.emextras.datagen.common.tag;

import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.common.registry.EMExtrasItem;
import mekanism.common.tags.MekanismTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class EMExtrasTagProvider extends BaseTagProvider {

    public EMExtrasTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, EMExtras.MODID, existingFileHelper);
    }

    @Override
    protected void registerTags(HolderLookup.Provider registries) {
        addToTag(MekanismTags.Items.CIRCUITS, EMExtrasItem.ABSOLUTE_OVERCLOCKED_CONTROL_CIRCUIT);
        addToTag(MekanismTags.Items.CIRCUITS, EMExtrasItem.SUPREME_QUANTUM_CONTROL_CIRCUIT);
        addToTag(MekanismTags.Items.CIRCUITS, EMExtrasItem.COSMIC_DENSE_CONTROL_CIRCUIT);
        addToTag(MekanismTags.Items.CIRCUITS, EMExtrasItem.INFINITE_MULTIVERSAL_CONTROL_CIRCUIT);
    }
}
