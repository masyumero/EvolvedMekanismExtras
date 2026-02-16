package io.github.masyumero.emextras;

import lombok.Getter;
import mekanism.api.annotations.ParametersAreNotNullByDefault;
import mekanism.api.text.ILangEntry;
import net.minecraft.Util;

@Getter
@ParametersAreNotNullByDefault
public enum EMExtrasLang implements ILangEntry {
    TAB("constants", "mod_name");

    private final String translationKey;
    EMExtrasLang(String type, String path) {
        this(Util.makeDescriptionId(type, EMExtras.rl(path)));
    }

    EMExtrasLang(String translationKey) {
        this.translationKey = translationKey;
    }
}
