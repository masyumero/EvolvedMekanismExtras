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
    }

    private void addBlocks() {
        EMExtraBlocks.BLOCK.getAllBlocks().forEach(this::add);
        EMExtraAdvancedFactoryBlocks.BLOCKS.getAllBlocks().forEach(this::add);
        EMExtraMoreMachineBlocks.BLOCKS.getAllBlocks().forEach(this::add);
    }

    private void addItem() {
        EMExtraItems.ITEM.getAllItems().forEach(this::add);
    }

    private void addMisc() {
        add(EMExtrasLang.TAB, "Evolved Mekanism Extras");
        add(EMExtrasLang.HINT_TIER_INSTALLER, "You must have %1$s in your off-hand slot.");
    }
}
