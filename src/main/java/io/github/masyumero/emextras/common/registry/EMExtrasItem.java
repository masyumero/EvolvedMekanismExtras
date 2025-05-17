package io.github.masyumero.emextras.common.registry;

import io.github.masyumero.emextras.EMExtras;
import mekanism.common.registration.impl.ItemDeferredRegister;
import mekanism.common.registration.impl.ItemRegistryObject;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;

public class EMExtrasItem {
    public static final ItemDeferredRegister ITEM = new ItemDeferredRegister(EMExtras.MODID);

    public static final ItemRegistryObject<Item> INFINITE_MULTIVERSAL_CONTROL_CIRCUIT = registerCircuit("infinite_multiversal", Rarity.EPIC);

    private static ItemRegistryObject<Item> registerCircuit(String name, Rarity rarity) {
        return ITEM.register(name + "_control_circuit", properties -> new Item(properties.rarity(rarity)));
    }

    public static void register(IEventBus eventBus) {
        ITEM.register(eventBus);
    }
}
