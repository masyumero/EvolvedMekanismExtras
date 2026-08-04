package io.github.masyumero.emextras.datagen.client.lang;

import java.util.Arrays;
import java.util.stream.Collectors;

public interface IEnglishToAnyLanguageProvider {

    String getAnyLangWord(String enKey);

    void addWord(String en, String any);

    default String replaceEN(String en) {
        return Arrays.stream(en.split(" ")).map(String::toLowerCase).map(this::getAnyLangWord).collect(Collectors.joining());
    }

    void registerWords();
}
