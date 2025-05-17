package io.github.masyumero.emextras.common.registry;

import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.EMExtrasLang;
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
            })
    );

    private static void addToExistingTabs(BuildCreativeModeTabContentsEvent event) {
    }
}
