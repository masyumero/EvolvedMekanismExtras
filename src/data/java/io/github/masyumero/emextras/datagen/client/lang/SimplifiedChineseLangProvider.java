package io.github.masyumero.emextras.datagen.client.lang;

import io.github.masyumero.emextras.EMExtrasLang;
import net.minecraft.data.PackOutput;

import java.util.HashMap;
import java.util.Map;

public class SimplifiedChineseLangProvider extends BaseLanguageProvider implements IEnglishToAnyLanguageProvider{

    private static final Map<String, String> EN_CN_WORDS = new HashMap<>();

    public SimplifiedChineseLangProvider(PackOutput output) {
        super(output, "zh_cn");
    }

    @Override
    protected void addTranslations() {
        LANGS.forEach((key, en) -> add(key, replaceEN(en)));

        add(EMExtrasLang.HINT_TIER_INSTALLER, "You must have %1$s in your off-hand slot.");
        add(EMExtrasLang.TAB, "通用机械：进化扩展");
    }

    @Override
    public String getAnyLangWord(String enKey) {
        return EN_CN_WORDS.get(enKey);
    }

    @Override
    public void addWord(String en, String cn) {
        EN_CN_WORDS.put(en, cn);
    }

    @Override
    public void registerWords() {
        // Tier
        addWord("absolute", "绝对");
        addWord("overclocked", "超频");
        addWord("supreme", "至尊");
        addWord("quantum", "量子");
        addWord("cosmic", "宇宙");
        addWord("dense", "致密");
        addWord("infinite", "无限");
        addWord("multiversal", "多元");
        // Factory
        addWord("factory", "工厂");
        addWord("alloying", "合金");
        addWord("centrifuging", "离心");
        addWord("combining", "融合");
        addWord("compressing", "压缩");
        addWord("crushing", "粉碎");
        addWord("crystallizing", "结晶");
        addWord("dissolving", "溶解");
        addWord("enriching", "富集");
        addWord("infusing", "灌注");
        addWord("injecting", "压射");
        addWord("lathing", "车削");
        addWord("liquifying", "液化");
        addWord("oxidizing", "氧化");
        addWord("painting", "上色");
        addWord("pigment", "颜料");
        addWord("extracting", "提取");
        addWord("planting", "种植");
        addWord("pressurised", "加压");
        addWord("reacting", "反应");
        addWord("purifying", "提纯");
        addWord("recycling", "回收");
        addWord("replicating", "复制");
        addWord("rolling", "轧制");
        addWord("mill", "");
        addWord("sawing", "锯木");
        addWord("smelting", "熔炼");
        addWord("stamping", "压模");
        addWord("washing", "清洗");
        // Transporter
        addWord("logistical", "物流");
        addWord("mechanical", "机械");
        addWord("pressurized", "加压");
        addWord("thermodynamic", "热导");
        addWord("universal", "通用");
        addWord("transporter", "管道");
        addWord("pipe", "管道");
        addWord("tube", "管道");
        addWord("conductor", "线缆");
        addWord("cable", "线缆");
        // Induction
        addWord("induction", "感应");
        addWord("cell", "元件");
        addWord("provider", "供应器");
        // Tier Installer
        addWord("tier", "工厂");
        addWord("installer", "安装器");
        // Circuit
        addWord("base", "基础");
        addWord("control", "控制");
        addWord("circuit", "电路");
    }
}
