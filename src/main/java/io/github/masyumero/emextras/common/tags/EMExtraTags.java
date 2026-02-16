package io.github.masyumero.emextras.common.tags;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class EMExtraTags {

    public EMExtraTags () {
    }

    public static class Items {

        private Items() {
        }

        public static final TagKey<Item> CIRCUITS_ABSOLUTE_OVERCLOCKED = commonTag("circuits/absolute_overclocked");
        public static final TagKey<Item> CIRCUITS_SUPREME_QUANTUM = commonTag("circuits/supreme_quantum");
        public static final TagKey<Item> CIRCUITS_COSMIC_DENSE = commonTag("circuits/cosmic_dense");
        public static final TagKey<Item> CIRCUITS_INFINITE_MULTIVERSAL = commonTag("circuits/infinite_multiversal");

        private static TagKey<Item> commonTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", name));
        }
    }
}
