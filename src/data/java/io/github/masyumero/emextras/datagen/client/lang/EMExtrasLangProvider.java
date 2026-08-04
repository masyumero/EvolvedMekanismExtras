package io.github.masyumero.emextras.datagen.client.lang;

import io.github.masyumero.emextras.EMExtrasLang;
import io.github.masyumero.emextras.common.integration.mekaf.regisrty.EMExtraAdvancedFactoryBlocks;
import io.github.masyumero.emextras.common.integration.mekmm.registry.EMExtraMoreMachineBlocks;
import io.github.masyumero.emextras.common.registry.EMExtraBlocks;
import io.github.masyumero.emextras.common.registry.EMExtraItems;
import net.minecraft.data.PackOutput;

public class EMExtrasLangProvider extends BaseLanguageProvider {

    public EMExtrasLangProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void addTranslations() {
        addBlocks();
        addItem();
        addMisc();

        LANGS.forEach(this::add);
    }

    private void addBlocks() {
        EMExtraBlocks.BLOCK.getAllBlocks().forEach(this::addENAny);
        EMExtraAdvancedFactoryBlocks.BLOCKS.getAllBlocks().forEach(this::addENAny);
        EMExtraMoreMachineBlocks.BLOCKS.getAllBlocks().forEach(this::addENAny);
    }

    private void addItem() {
        EMExtraItems.ITEM.getAllItems().forEach(this::addENAny);
    }

    private void addMisc() {
        add(EMExtrasLang.HINT_TIER_INSTALLER, "You must have %1$s in your off-hand slot.");
        add(EMExtrasLang.TAB, "Evolved Mekanism Extras");
    }
}
