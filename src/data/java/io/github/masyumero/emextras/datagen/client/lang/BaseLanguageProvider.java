package io.github.masyumero.emextras.datagen.client.lang;

import io.github.masyumero.emextras.common.util.EMExtraTextUtils;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import mekanism.api.gear.ModuleData;
import mekanism.api.providers.IBaseProvider;
import mekanism.api.providers.IBlockProvider;
import mekanism.api.providers.IModuleDataProvider;
import mekanism.api.text.IHasTranslationKey;
import mekanism.common.advancements.MekanismAdvancement;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.block.attribute.AttributeGui;
import mekanism.common.registration.impl.FluidRegistryObject;
import mekanism.common.util.RegistryUtils;
import net.minecraft.Util;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public abstract class BaseLanguageProvider extends LanguageProvider {

    private final String modid;
    public static final Map<IHasTranslationKey, ENJP> LANGS = new Object2ObjectOpenHashMap<>();

    protected BaseLanguageProvider(PackOutput output, String modid) {
        this(output, modid, "en_us");
    }

    protected BaseLanguageProvider(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
        this.modid = modid;
    }

    @NotNull
    @Override
    public String getName() {
        return super.getName() + ": " + modid;
    }

    protected static void addENJP(IHasTranslationKey key, ENJP enjp) {
        if (LANGS.containsKey(key)) throw new IllegalArgumentException("Duplicate key: " + key);
        LANGS.put(key, enjp);
    }

    protected void addENJP(IHasTranslationKey key, String jp) {
        if (key instanceof IBaseProvider provider) {
            addENJP(key, new ENJP(EMExtraTextUtils.toEnglishName(provider.getName()), jp));
        }
    }

    protected void addENJP(IHasTranslationKey key, String en, String jp) {
        addENJP(key, new ENJP(en, jp));
    }

    protected void addENJP(IHasTranslationKey key) {
        if (key instanceof IBaseProvider baseProvider) {
            var en = EMExtraTextUtils.toEnglishName(baseProvider.getName());
            addENJP(key, new ENJP(en, JapaneseLangProvider.replaceEN(en)));
        }
    }

    protected void add(IHasTranslationKey key) {
        if (key instanceof IBaseProvider baseProvider) {
            add(key, EMExtraTextUtils.toEnglishName(baseProvider.getName()));
        }
    }

    protected void add(IHasTranslationKey key, String value) {
        if (key instanceof IBlockProvider blockProvider) {
            Block block = blockProvider.getBlock();
            if (Attribute.matches(block, AttributeGui.class, attribute -> !attribute.hasCustomName())) {
                add(Util.makeDescriptionId("container", RegistryUtils.getName(block)), value);
            }
        }
        add(key.getTranslationKey(), value);
    }

    protected void add(IBlockProvider blockProvider, String value, String containerName) {
        Block block = blockProvider.getBlock();
        if (Attribute.matches(block, AttributeGui.class, attribute -> !attribute.hasCustomName())) {
            add(Util.makeDescriptionId("container", RegistryUtils.getName(block)), containerName);
            add(blockProvider.getTranslationKey(), value);
        } else {
            throw new IllegalArgumentException("Block " + blockProvider.getRegistryName() + " does not have a container name set.");
        }
    }

    protected void add(IModuleDataProvider<?> moduleDataProvider, String name, String description) {
        ModuleData<?> moduleData = moduleDataProvider.getModuleData();
        add(moduleData.getTranslationKey(), name);
        add(moduleData.getDescriptionTranslationKey(), description);
    }

    protected void addFluid(FluidRegistryObject<?, ?, ?, ?, ?> fluidRO, String name) {
        add(fluidRO.getBlock(), name);
        add(fluidRO.getBucket(), name + " Bucket");
    }

    protected void add(MekanismAdvancement advancement, String title, String description) {
        add(advancement.title(), title);
        add(advancement.description(), description);
    }

    @Override
    public void add(@NotNull String key, @NotNull String value) {
        if (value.contains("%s")) {
            throw new IllegalArgumentException("Values containing substitutions should use explicit numbered indices: " + key + " - " + value);
        }
        super.add(key, value);
    }

    public record ENJP(String en, String jp) {

        @Override
        public boolean equals(Object o) {
            if (o instanceof ENJP enjp) {
                return enjp.en.equals(en) && enjp.jp.equals(jp);
            }
            return false;
        }
    }
}
