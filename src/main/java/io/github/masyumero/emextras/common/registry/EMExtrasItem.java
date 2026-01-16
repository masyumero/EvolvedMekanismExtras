package io.github.masyumero.emextras.common.registry;

import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.api.tier.EMExtraTier;
import io.github.masyumero.emextras.common.item.EMExtraItemTierInstaller;
import mekanism.common.registration.impl.ItemDeferredRegister;
import mekanism.common.registration.impl.ItemRegistryObject;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EMExtrasItem {
    public static final ItemDeferredRegister ITEM = new ItemDeferredRegister(EMExtras.MODID);

    public static final ItemRegistryObject<Item> BASE_CONTROL_CIRCUIT = registerCircuit("base", Rarity.COMMON);
    public static final ItemRegistryObject<Item> ABSOLUTE_OVERCLOCKED_CONTROL_CIRCUIT = registerCircuit("absolute_overclocked", Rarity.COMMON);
    public static final ItemRegistryObject<Item> SUPREME_QUANTUM_CONTROL_CIRCUIT = registerCircuit("supreme_quantum", Rarity.UNCOMMON);
    public static final ItemRegistryObject<Item> COSMIC_DENSE_CONTROL_CIRCUIT = registerCircuit("cosmic_dense", Rarity.RARE);
    public static final ItemRegistryObject<Item> INFINITE_MULTIVERSAL_CONTROL_CIRCUIT = registerCircuit("infinite_multiversal", Rarity.EPIC);

    public static final ItemRegistryObject<EMExtraItemTierInstaller> ABSOLUTE_OVERCLOCKED_TIER_INSTALLER = registerInstaller(null, EMExtraTier.ABSOLUTE_OVERCLOCKED);
    public static final ItemRegistryObject<EMExtraItemTierInstaller> SUPREME_QUANTUM_TIER_INSTALLER = registerInstaller(EMExtraTier.ABSOLUTE_OVERCLOCKED, EMExtraTier.SUPREME_QUANTUM);
    public static final ItemRegistryObject<EMExtraItemTierInstaller> COSMIC_DENSE_TIER_INSTALLER = registerInstaller(EMExtraTier.SUPREME_QUANTUM, EMExtraTier.COSMIC_DENSE);
    public static final ItemRegistryObject<EMExtraItemTierInstaller> INFINITE_MULTIVERSAL_TIER_INSTALLER = registerInstaller(EMExtraTier.COSMIC_DENSE, EMExtraTier.INFINITE_MULTIVERSAL);

    private static ItemRegistryObject<Item> registerCircuit(String name, Rarity rarity) {
        return ITEM.register(name + "_control_circuit", properties -> new Item(properties.rarity(rarity)));
    }

    private static ItemRegistryObject<EMExtraItemTierInstaller> registerInstaller(@Nullable EMExtraTier fromTier, @NotNull EMExtraTier toTier) {
        // Ensure the name is lower case as with concatenating with values from enums it may not be
        return ITEM.register(toTier.getLowerName() + "_tier_installer", properties -> new EMExtraItemTierInstaller(fromTier, toTier, properties));
    }

    public static void register(IEventBus eventBus) {
        ITEM.register(eventBus);
    }
}
