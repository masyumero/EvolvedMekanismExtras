package io.github.masyumero.emextras.common.registry;

import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.EMExtrasLang;
import io.github.masyumero.emextras.common.integration.Addons;
import io.github.masyumero.emextras.common.integration.mekaf.regisrty.EMExtraAdvancedFactoryBlocks;
import io.github.masyumero.emextras.common.integration.mekmm.registry.EMExtraMoreMachineBlocks;
import mekanism.common.registration.impl.CreativeTabDeferredRegister;
import mekanism.common.registration.impl.CreativeTabRegistryObject;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class EMExtraTab {
    public static void register(IEventBus eventBus) {
        TAB.register(eventBus);
    }

    public static final CreativeTabDeferredRegister TAB = new CreativeTabDeferredRegister(EMExtras.MODID, EMExtraTab::addToExistingTabs);
    public static final CreativeTabRegistryObject EVOLVED_MEKANISM_EXTRAS_TAB = TAB.registerMain(EMExtrasLang.TAB, EMExtrasItem.INFINITE_MULTIVERSAL_CONTROL_CIRCUIT, builder ->
            builder.displayItems((displayParameters, output) -> {
                CreativeTabDeferredRegister.addToDisplay(EMExtrasBlock.BLOCK, output);
                CreativeTabDeferredRegister.addToDisplay(EMExtrasItem.ITEM, output);
                if (Addons.MEKMM.isLoaded()) {
                    CreativeTabDeferredRegister.addToDisplay(EMExtraAdvancedFactoryBlocks.BLOCKS, output);
                    CreativeTabDeferredRegister.addToDisplay(EMExtraMoreMachineBlocks.BLOCKS, output);
                }
            })
    );

    private static void addToExistingTabs(BuildCreativeModeTabContentsEvent event) {
    }
}
