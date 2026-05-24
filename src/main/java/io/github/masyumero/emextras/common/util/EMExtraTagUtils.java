package io.github.masyumero.emextras.common.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class EMExtraTagUtils {

    public static final TagKey<Item> ALLOYS_RADIANCE = alloyTag("absolute");
    public static final TagKey<Item> ALLOYS_THERMONUCLEAR = alloyTag("supreme");
    public static final TagKey<Item> ALLOYS_SHINING = alloyTag("cosmic");
    public static final TagKey<Item> ALLOYS_SPECTRUM = alloyTag("infinite");
    public static final TagKey<Item> CIRCUITS_ABSOLUTE = circuitTag("absolute");
    public static final TagKey<Item> CIRCUITS_SUPREME = circuitTag("supreme");
    public static final TagKey<Item> CIRCUITS_COSMIC = circuitTag("cosmic");
    public static final TagKey<Item> CIRCUITS_INFINITE = circuitTag("infinite");

    private static TagKey<Item> alloyTag(String alloyTierName) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("forge", "alloys/" + alloyTierName));
    }

    private static TagKey<Item> circuitTag(String circuitTierName) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("forge", "circuits/" + circuitTierName));
    }
}
