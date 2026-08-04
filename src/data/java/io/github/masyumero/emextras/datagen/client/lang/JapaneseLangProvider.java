package io.github.masyumero.emextras.datagen.client.lang;

import io.github.masyumero.emextras.EMExtrasLang;
import net.minecraft.data.PackOutput;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class JapaneseLangProvider extends BaseLanguageProvider implements IEnglishToAnyLanguageProvider {

    private static final Map<String, String> EN_JP_WORDS = new HashMap<>();

    public JapaneseLangProvider(PackOutput output) {
        super(output, "ja_jp");
    }

    public void addWord(String en, String jp) {
        EN_JP_WORDS.put(en.toLowerCase(Locale.ROOT), jp);
    }

    @Override
    protected void addTranslations() {
        LANGS.forEach((key, en) -> add(key, replaceEN(en)));

        add(EMExtrasLang.HINT_TIER_INSTALLER, "オフハンドに%1$sを持つ必要があります。");
    }

    @Override
    public String getAnyLangWord(String enKey) {
        return EN_JP_WORDS.get(enKey);
    }

    public void registerWords() {
        // Tier
        addWord("absolute", "絶対");
        addWord("overclocked", "超速");
        addWord("supreme", "至高");
        addWord("quantum", "量子");
        addWord("cosmic", "宇宙");
        addWord("dense", "高密");
        addWord("infinite", "無限");
        addWord("multiversal", "多元");
        // Factory
        addWord("factory", "ファクトリー");
        addWord("alloying", "合金");
        addWord("centrifuging", "分離");
        addWord("combining", "結合");
        addWord("compressing", "圧縮");
        addWord("crushing", "粉砕");
        addWord("crystallizing", "結晶");
        addWord("dissolving", "溶解");
        addWord("enriching", "濃縮");
        addWord("infusing", "吹込");
        addWord("injecting", "注入");
        addWord("lathing", "旋盤");
        addWord("liquifying", "液化");
        addWord("oxidizing", "酸化");
        addWord("painting", "塗装");
        addWord("pigment", "顔料");
        addWord("extracting", "抽出");
        addWord("planting", "栽培");
        addWord("pressurised", "");
        addWord("reacting", "反応");
        addWord("purifying", "浄化");
        addWord("recycling", "再環");
        addWord("replicating", "複製");
        addWord("rolling", "圧延");
        addWord("mill", "");
        addWord("sawing", "製材");
        addWord("smelting", "精錬");
        addWord("stamping", "打圧");
        addWord("washing", "洗浄");
        // Transporter
        addWord("logistical", "物流");
        addWord("mechanical", "メカニカル");
        addWord("pressurized", "加圧");
        addWord("thermodynamic", "熱力学的");
        addWord("universal", "ユニバーサル");
        addWord("transporter", "トランスポーター");
        addWord("pipe", "パイプ");
        addWord("tube", "チューブ");
        addWord("conductor", "コンダクター");
        addWord("cable", "ケーブル");
        // Induction
        addWord("induction", "インダクション");
        addWord("cell", "セル");
        addWord("provider", "プロバイダ");
        // Tier Installer
        addWord("tier", "ティア");
        addWord("installer", "インストーラー");
        // Circuit
        addWord("base", "基礎");
        addWord("control", "制御");
        addWord("circuit", "回路");
    }
}
