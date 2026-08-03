package io.github.masyumero.emextras.common.registry;

import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.api.tier.EMExtraTier;
import io.github.masyumero.emextras.common.item.EMExtraItemTierInstaller;
import io.github.masyumero.emextras.common.item.EMExtraTieredItem;
import io.github.masyumero.emextras.common.util.EMExtraEnumUtils;

import mekanism.common.registration.impl.ItemDeferredRegister;
import mekanism.common.registration.impl.ItemRegistryObject;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class EMExtraItems {
    public static final ItemDeferredRegister ITEM = new ItemDeferredRegister(EMExtras.MODID);

    private static final Map<EMExtraTier, ItemRegistryObject<EMExtraTieredItem>> CIRCUITS = new HashMap<>();

    public static final ItemRegistryObject<Item> BASE_CONTROL_CIRCUIT = ITEM.register("base_control_circuit", properties -> new Item(properties.rarity(Rarity.COMMON)));

    static {
        for (EMExtraTier tier : EMExtraEnumUtils.EMEXTRA_TIERS) {
            CIRCUITS.put(tier, registerCircuit(tier));
        }
    }

    public static final ItemRegistryObject<EMExtraItemTierInstaller> ABSOLUTE_OVERCLOCKED_TIER_INSTALLER = registerInstaller(null, EMExtraTier.ABSOLUTE_OVERCLOCKED);
    public static final ItemRegistryObject<EMExtraItemTierInstaller> SUPREME_QUANTUM_TIER_INSTALLER = registerInstaller(EMExtraTier.ABSOLUTE_OVERCLOCKED, EMExtraTier.SUPREME_QUANTUM);
    public static final ItemRegistryObject<EMExtraItemTierInstaller> COSMIC_DENSE_TIER_INSTALLER = registerInstaller(EMExtraTier.SUPREME_QUANTUM, EMExtraTier.COSMIC_DENSE);
    public static final ItemRegistryObject<EMExtraItemTierInstaller> INFINITE_MULTIVERSAL_TIER_INSTALLER = registerInstaller(EMExtraTier.COSMIC_DENSE, EMExtraTier.INFINITE_MULTIVERSAL);

    private static ItemRegistryObject<EMExtraTieredItem> registerCircuit(EMExtraTier tier) {
        return ITEM.register(tier.getLowerName() + "_control_circuit", properties -> new EMExtraTieredItem(tier, properties));
    }

    private static ItemRegistryObject<EMExtraItemTierInstaller> registerInstaller(@Nullable EMExtraTier fromTier, @NotNull EMExtraTier toTier) {
        // Ensure the name is lower case as with concatenating with values from enums it may not be
        return ITEM.register(toTier.getLowerName() + "_tier_installer", properties -> new EMExtraItemTierInstaller(fromTier, toTier, properties));
    }

    public static ItemRegistryObject<EMExtraTieredItem> getCircuit(EMExtraTier tier) {
        return CIRCUITS.get(tier);
    }

    public static void register(IEventBus eventBus) {
        ITEM.register(eventBus);
    }
}
