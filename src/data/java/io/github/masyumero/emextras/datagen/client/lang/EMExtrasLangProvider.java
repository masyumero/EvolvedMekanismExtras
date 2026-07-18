package io.github.masyumero.emextras.datagen.client.lang;

import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.EMExtrasLang;
import io.github.masyumero.emextras.common.integration.mekaf.regisrty.EMExtraAdvancedFactoryBlocks;
import io.github.masyumero.emextras.common.integration.mekmm.registry.EMExtraMoreMachineBlocks;
import io.github.masyumero.emextras.common.registry.EMExtrasBlock;
import io.github.masyumero.emextras.common.registry.EMExtrasItem;
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
        EMExtrasBlock.BLOCK.getAllBlocks().forEach(this::add);
        EMExtraAdvancedFactoryBlocks.BLOCKS.getAllBlocks().forEach(this::add);
        EMExtraMoreMachineBlocks.BLOCKS.getAllBlocks().forEach(this::add);
    }

    private void addItem() {
        add(EMExtrasItem.ABSOLUTE_OVERCLOCKED_TIER_INSTALLER, "Absolute Overclocked Tier Installer");
        add(EMExtrasItem.SUPREME_QUANTUM_TIER_INSTALLER, "Supreme Quantum Tier Installer");
        add(EMExtrasItem.COSMIC_DENSE_TIER_INSTALLER, "Cosmic Dense Tier Installer");
        add(EMExtrasItem.INFINITE_MULTIVERSAL_TIER_INSTALLER, "Infinite Multiversal Tier Installer");
        add(EMExtrasItem.BASE_CONTROL_CIRCUIT, "Base Control Circuit");
        add(EMExtrasItem.ABSOLUTE_OVERCLOCKED_CONTROL_CIRCUIT, "Absolute Overclocked Control Circuit");
        add(EMExtrasItem.SUPREME_QUANTUM_CONTROL_CIRCUIT, "Supreme Quantum Control Circuit");
        add(EMExtrasItem.COSMIC_DENSE_CONTROL_CIRCUIT, "Cosmic Dense Control Circuit");
        add(EMExtrasItem.INFINITE_MULTIVERSAL_CONTROL_CIRCUIT, "Infinite Multiversal Control Circuit");
    }

    private void addMisc() {
        add(EMExtrasLang.TAB, "Evolved Mekanism Extras");
    }
}
