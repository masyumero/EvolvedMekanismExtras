package io.github.masyumero.emextras.common.registry;

import io.github.masyumero.emextras.EMExtras;
import mekanism.common.registration.impl.ItemDeferredRegister;
import mekanism.common.registration.impl.ItemRegistryObject;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;

public class EMExtrasItem {
    public static final ItemDeferredRegister ITEM = new ItemDeferredRegister(EMExtras.MODID);

    public static final ItemRegistryObject<Item> BASE_CONTROL_CIRCUIT = registerCircuit("base", Rarity.COMMON);
    public static final ItemRegistryObject<Item> ABSOLUTE_OVERCLOCKED_CONTROL_CIRCUIT = registerCircuit("absolute_overclocked", Rarity.COMMON);
    public static final ItemRegistryObject<Item> SUPREME_QUANTUM_CONTROL_CIRCUIT = registerCircuit("supreme_quantum", Rarity.UNCOMMON);
    public static final ItemRegistryObject<Item> COSMIC_DENSE_CONTROL_CIRCUIT = registerCircuit("cosmic_dense", Rarity.RARE);
    public static final ItemRegistryObject<Item> INFINITE_MULTIVERSAL_CONTROL_CIRCUIT = registerCircuit("infinite_multiversal", Rarity.EPIC);

    private static ItemRegistryObject<Item> registerCircuit(String name, Rarity rarity) {
        return ITEM.register(name + "_control_circuit", properties -> new Item(properties.rarity(rarity)));
    }

    public static void register(IEventBus eventBus) {
        ITEM.register(eventBus);
    }
}
