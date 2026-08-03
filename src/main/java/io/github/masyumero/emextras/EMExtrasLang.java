package io.github.masyumero.emextras;

import mekanism.api.text.ILangEntry;
import net.minecraft.Util;
import org.jetbrains.annotations.NotNull;

public enum EMExtrasLang implements ILangEntry {
    TAB("constants", "mod_name"),
    HINT_TIER_INSTALLER("hint", "tier_installer");


    private final String key;
    EMExtrasLang(String type, String path) {
        this(Util.makeDescriptionId(type, EMExtras.rl(path)));
    }

    EMExtrasLang(String key) {
        this.key = key;
    }

    @Override
    public @NotNull String getTranslationKey() {
        return key;
    }
}
