package io.github.masyumero.emextras.common;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class EMExtraTags {

    public static void init() {
        Items.init();
    }

    public static class Items {

        private static void init() {}

        public static final TagKey<Item> ABSOLUTE_OVERCLOCKED_CONTROL_CIRCUIT = forgeTag("circuits/absolute_overclocked");
        public static final TagKey<Item> SUPREME_QUANTUM_CONTROL_CIRCUIT = forgeTag("circuits/supreme_quantum");
        public static final TagKey<Item> COSMIC_DENSE_CONTROL_CIRCUIT = forgeTag("circuits/cosmic_dense");
        public static final TagKey<Item> INFINITE_MULTIVERSAL_CONTROL_CIRCUIT = forgeTag("circuits/infinite_multiversal");

        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath("forge", name));
        }
    }
}
