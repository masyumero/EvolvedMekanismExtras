package io.github.masyumero.emextras.datagen.common.tag;

import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.common.integration.mekaf.registries.EMExtraAdvancedFactoryBlocks;
import io.github.masyumero.emextras.common.integration.mekmm.registries.EMExtraMoreMachineBlocks;
import io.github.masyumero.emextras.common.registry.EMExtraBlocks;
import io.github.masyumero.emextras.common.registry.EMExtraItems;
import io.github.masyumero.emextras.common.tags.EMExtraTags;
import mekanism.common.tags.MekanismTags;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class EMExtraTagProvider extends BaseTagProvider {

    public EMExtraTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, EMExtras.MODID, existingFileHelper);
    }

    @Override
    protected void registerTags(HolderLookup.Provider registries) {
        addCircuits();

        addMineablePickaxeTag(EMExtraBlocks.getEMExtraFactoryBlocks());
        if (EMExtras.hooks.mekmm.isLoaded()) {
            addMineablePickaxeOptionalTag(EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactoryBlocks());
            addMineablePickaxeOptionalTag(EMExtraMoreMachineBlocks.getEMExtraMoreMachineFactoryBlocks());
        }
    }

    @SafeVarargs
    private void addMineablePickaxeOptionalTag(Holder<Block>... provider) {
        addToHarvestOptionalTag(BlockTags.MINEABLE_WITH_PICKAXE, provider);
    }

    @SafeVarargs
    private void addMineablePickaxeTag(Holder<Block>... provider) {
        addToHarvestTag(BlockTags.MINEABLE_WITH_PICKAXE, provider);
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
