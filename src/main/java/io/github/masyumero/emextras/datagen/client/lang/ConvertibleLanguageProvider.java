package io.github.masyumero.emextras.datagen.client.lang;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

import java.util.List;

public abstract class ConvertibleLanguageProvider extends LanguageProvider {

    public ConvertibleLanguageProvider(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
    }

    public abstract void convert(String key, String raw, List<FormatSplitter.Component> splitEnglish);

    @Override
    protected void addTranslations() {
    }
}
