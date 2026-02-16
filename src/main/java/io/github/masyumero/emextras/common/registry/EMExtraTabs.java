package io.github.masyumero.emextras.common.registry;

import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.EMExtrasLang;
import mekanism.common.registration.MekanismDeferredHolder;
import mekanism.common.registration.impl.CreativeTabDeferredRegister;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

public class EMExtraTabs {

    public static final CreativeTabDeferredRegister TAB = new CreativeTabDeferredRegister(EMExtras.MODID, EMExtraTabs::addToExistingTabs);

    public static final MekanismDeferredHolder<CreativeModeTab, CreativeModeTab> EVOLVED_MEKANISM_EXTRAS_TAB = TAB.registerMain(EMExtrasLang.TAB, EMExtraItems.INFINITE_MULTIVERSAL_CONTROL_CIRCUIT, builder ->
            builder.displayItems((displayParameters, output) -> {
                CreativeTabDeferredRegister.addToDisplay(EMExtraBlocks.BLOCK, output);
                CreativeTabDeferredRegister.addToDisplay(EMExtraItems.ITEM, output);
            })
    );

    private static void addToExistingTabs(BuildCreativeModeTabContentsEvent event) {
    }

    public static void register(IEventBus modEventBus) {
        TAB.register(modEventBus);
    }
}
