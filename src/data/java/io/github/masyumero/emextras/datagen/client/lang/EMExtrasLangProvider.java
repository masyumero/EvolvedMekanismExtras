package io.github.masyumero.emextras.datagen.client.lang;

import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.EMExtrasLang;
import io.github.masyumero.emextras.common.integration.mekaf.regisrty.EMExtraAdvancedFactoryBlocks;
import io.github.masyumero.emextras.common.integration.mekmm.registry.EMExtraMoreMachineBlocks;
import io.github.masyumero.emextras.common.registry.EMExtraBlocks;
import io.github.masyumero.emextras.common.registry.EMExtraItems;
import net.minecraft.data.PackOutput;

public class EMExtrasLangProvider extends BaseLanguageProvider {

    public EMExtrasLangProvider(PackOutput output) {
        super(output, EMExtras.MODID);
    }

    @Override
    protected void addTranslations() {
        addBlocks();
        addItem();
        addMisc();

        LANGS.forEach((key, enjp) -> add(key, enjp.en()));
    }

    private void addBlocks() {
        EMExtraBlocks.BLOCK.getAllBlocks().forEach(this::addENJP);
        EMExtraAdvancedFactoryBlocks.BLOCKS.getAllBlocks().forEach(this::addENJP);
        EMExtraMoreMachineBlocks.BLOCKS.getAllBlocks().forEach(this::addENJP);
    }

    private void addItem() {
        EMExtraItems.ITEM.getAllItems().forEach(this::addENJP);
    }

    private void addMisc() {
        addENJP(EMExtrasLang.TAB, "Evolved Mekanism Extras", "Evolved Mekanism Extras");
        addENJP(EMExtrasLang.HINT_TIER_INSTALLER, "You must have %1$s in your off-hand slot.", "オフハンドに%1$sを持つ必要があります。");
    }
}
