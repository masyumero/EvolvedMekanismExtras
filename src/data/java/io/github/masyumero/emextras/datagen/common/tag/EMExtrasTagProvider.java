package io.github.masyumero.emextras.datagen.common.tag;

import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.api.tier.EMExtraTier;
import io.github.masyumero.emextras.common.EMExtraTags;
import io.github.masyumero.emextras.common.integration.Addons;
import io.github.masyumero.emextras.common.integration.mekaf.regisrty.EMExtraAdvancedFactoryBlocks;
import io.github.masyumero.emextras.common.integration.mekmm.registry.EMExtraMoreMachineBlocks;
import io.github.masyumero.emextras.common.registry.EMExtraBlocks;
import io.github.masyumero.emextras.common.registry.EMExtraItems;
import mekanism.api.providers.IBlockProvider;
import mekanism.common.tags.MekanismTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class EMExtrasTagProvider extends BaseTagProvider {

    public EMExtrasTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, EMExtras.MODID, existingFileHelper);
    }

    @Override
    protected void registerTags(HolderLookup.Provider registries) {
        addToTag(EMExtraItems.getCircuit(EMExtraTier.ABSOLUTE_OVERCLOCKED), MekanismTags.Items.CIRCUITS, EMExtraTags.Items.ABSOLUTE_OVERCLOCKED_CONTROL_CIRCUIT);
        addToTag(EMExtraItems.getCircuit(EMExtraTier.SUPREME_QUANTUM), MekanismTags.Items.CIRCUITS, EMExtraTags.Items.SUPREME_QUANTUM_CONTROL_CIRCUIT);
        addToTag(EMExtraItems.getCircuit(EMExtraTier.COSMIC_DENSE), MekanismTags.Items.CIRCUITS, EMExtraTags.Items.COSMIC_DENSE_CONTROL_CIRCUIT);
        addToTag(EMExtraItems.getCircuit(EMExtraTier.INFINITE_MULTIVERSAL), MekanismTags.Items.CIRCUITS, EMExtraTags.Items.INFINITE_MULTIVERSAL_CONTROL_CIRCUIT);

        EMExtraBlocks.BLOCK.getAllBlocks().forEach(this::addMineablePickaxeTag);
        if (Addons.MEKMM.isLoaded()) {
            EMExtraAdvancedFactoryBlocks.BLOCKS.getAllBlocks().forEach(this::addMineablePickaxeOptionalTag);
            EMExtraMoreMachineBlocks.BLOCKS.getAllBlocks().forEach(this::addMineablePickaxeOptionalTag);
        }
    }

    private void addMineablePickaxeOptionalTag(IBlockProvider... provider) {
        addToHarvestOptionalTag(BlockTags.MINEABLE_WITH_PICKAXE, provider);
    }

    private void addMineablePickaxeTag(IBlockProvider... provider) {
        addToHarvestTag(BlockTags.MINEABLE_WITH_PICKAXE, provider);
    }
}
