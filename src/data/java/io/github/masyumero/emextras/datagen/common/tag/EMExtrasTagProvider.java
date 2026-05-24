package io.github.masyumero.emextras.datagen.common.tag;

import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.common.EMExtraTags;
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
        addToTag(EMExtrasItem.ABSOLUTE_OVERCLOCKED_CONTROL_CIRCUIT, MekanismTags.Items.CIRCUITS, EMExtraTags.Items.ABSOLUTE_OVERCLOCKED_CONTROL_CIRCUIT);
        addToTag(EMExtrasItem.SUPREME_QUANTUM_CONTROL_CIRCUIT, MekanismTags.Items.CIRCUITS, EMExtraTags.Items.SUPREME_QUANTUM_CONTROL_CIRCUIT);
        addToTag(EMExtrasItem.COSMIC_DENSE_CONTROL_CIRCUIT, MekanismTags.Items.CIRCUITS, EMExtraTags.Items.COSMIC_DENSE_CONTROL_CIRCUIT);
        addToTag(EMExtrasItem.INFINITE_MULTIVERSAL_CONTROL_CIRCUIT, MekanismTags.Items.CIRCUITS, EMExtraTags.Items.INFINITE_MULTIVERSAL_CONTROL_CIRCUIT);
    }
}
