package io.github.masyumero.emextras.datagen.common.tag;

import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.common.registry.EMExtraItems;
import io.github.masyumero.emextras.common.tags.EMExtraTags;
import mekanism.common.tags.MekanismTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class EMExtrasTagProvider extends BaseTagProvider {

    public EMExtrasTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, EMExtras.MODID, existingFileHelper);
    }

    @Override
    protected void registerTags(HolderLookup.Provider registries) {
        addCircuits();
    }

    private void addCircuits() {
        getBuilder(EMExtraTags.Items.CIRCUITS_ABSOLUTE_OVERCLOCKED).add(EMExtraItems.ABSOLUTE_OVERCLOCKED_CONTROL_CIRCUIT);
        getBuilder(EMExtraTags.Items.CIRCUITS_SUPREME_QUANTUM).add(EMExtraItems.SUPREME_QUANTUM_CONTROL_CIRCUIT);
        getBuilder(EMExtraTags.Items.CIRCUITS_COSMIC_DENSE).add(EMExtraItems.COSMIC_DENSE_CONTROL_CIRCUIT);
        getBuilder(EMExtraTags.Items.CIRCUITS_INFINITE_MULTIVERSAL).add(EMExtraItems.INFINITE_MULTIVERSAL_CONTROL_CIRCUIT);
        getBuilder(MekanismTags.Items.CIRCUITS).add(EMExtraItems.ABSOLUTE_OVERCLOCKED_CONTROL_CIRCUIT, EMExtraItems.SUPREME_QUANTUM_CONTROL_CIRCUIT,
                EMExtraItems.COSMIC_DENSE_CONTROL_CIRCUIT, EMExtraItems.INFINITE_MULTIVERSAL_CONTROL_CIRCUIT);
    }
}
