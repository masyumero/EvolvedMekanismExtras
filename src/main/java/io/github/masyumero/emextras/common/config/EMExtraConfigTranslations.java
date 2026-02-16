package io.github.masyumero.emextras.common.config;

import io.github.masyumero.emextras.EMExtras;
import lombok.Getter;
import mekanism.common.config.IConfigTranslation;
import mekanism.common.config.TranslationPreset;
import net.minecraft.Util;
import org.jetbrains.annotations.Nullable;

public enum EMExtraConfigTranslations implements IConfigTranslation {
    ENERGY_STORAGE_ALLOYING_FACTORY(TranslationPreset.ENERGY_STORAGE, "Alloying Factory"),
    ENERGY_USAGE_ALLOYING_FACTORY(TranslationPreset.ENERGY_USAGE, "Alloying Factory");

    @Getter
    private final String translationKey;
    private final String title;
    private final String tooltip;
    @Nullable
    private final String button;

    EMExtraConfigTranslations(TranslationPreset preset, String type) {
        this(preset.path(type), preset.title(type), preset.tooltip(type));
    }

    EMExtraConfigTranslations(TranslationPreset preset, String type, String tooltipSuffix) {
        this(preset.path(type), preset.title(type), preset.tooltip(type) + tooltipSuffix);
    }

    EMExtraConfigTranslations(String path, String title, String tooltip) {
        this(path, title, tooltip, false);
    }

    EMExtraConfigTranslations(String path, String title, String tooltip, boolean isSection) {
        this(path, title, tooltip, IConfigTranslation.getSectionTitle(title, isSection));
    }

    EMExtraConfigTranslations(String path, String title, String tooltip, @Nullable String button) {
        this.translationKey = Util.makeDescriptionId("configuration", EMExtras.rl(path));
        this.title = title;
        this.tooltip = tooltip;
        this.button = button;
    }

    @Override
    public String title() {
        return title;
    }

    @Override
    public String tooltip() {
        return tooltip;
    }

    @Nullable
    @Override
    public String button() {
        return button;
    }
}
