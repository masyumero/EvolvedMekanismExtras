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
        add(EMExtraItems.ABSOLUTE_OVERCLOCKED_TIER_INSTALLER, "Absolute Overclocked Tier Installer");
        add(EMExtraItems.SUPREME_QUANTUM_TIER_INSTALLER, "Supreme Quantum Tier Installer");
        add(EMExtraItems.COSMIC_DENSE_TIER_INSTALLER, "Cosmic Dense Tier Installer");
        add(EMExtraItems.INFINITE_MULTIVERSAL_TIER_INSTALLER, "Infinite Multiversal Tier Installer");
        add(EMExtraItems.BASE_CONTROL_CIRCUIT, "Base Control Circuit");
        add(EMExtraItems.ABSOLUTE_OVERCLOCKED_CONTROL_CIRCUIT, "Absolute Overclocked Control Circuit");
        add(EMExtraItems.SUPREME_QUANTUM_CONTROL_CIRCUIT, "Supreme Quantum Control Circuit");
        add(EMExtraItems.COSMIC_DENSE_CONTROL_CIRCUIT, "Cosmic Dense Control Circuit");
        add(EMExtraItems.INFINITE_MULTIVERSAL_CONTROL_CIRCUIT, "Infinite Multiversal Control Circuit");
    }

    private void addMisc() {
        add(EMExtrasLang.TAB, "Evolved Mekanism Extras");
    }
}
