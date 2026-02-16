package io.github.masyumero.emextras.client.recipe_viewer.emi;

import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import io.github.masyumero.emextras.common.registry.EMExtraBlocks;
import mekanism.client.recipe_viewer.emi.MekanismEmi;

@EmiEntrypoint
public class EMExtraEMI implements EmiPlugin {

    @Override
    public void register(EmiRegistry registry) {
        MekanismEmi.registerItemSubtypes(registry, EMExtraBlocks.BLOCK.getSecondaryEntries());
    }
}
