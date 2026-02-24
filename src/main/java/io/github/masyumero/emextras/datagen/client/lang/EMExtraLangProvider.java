package io.github.masyumero.emextras.datagen.client.lang;

import com.jerry.mekaf.common.content.blocktype.AdvancedFactoryType;
import com.jerry.mekextras.common.tier.ExtraFactoryTier;
import com.jerry.mekextras.common.util.ExtraEnumUtils;
import com.jerry.mekmm.common.content.blocktype.MoreMachineFactoryType;
import com.jerry.mekmm.common.util.MoreMachineEnumUtils;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.EMExtrasLang;
import io.github.masyumero.emextras.common.config.EMExtraConfigTranslations;
import io.github.masyumero.emextras.common.config.LoadConfig;
import io.github.masyumero.emextras.common.content.blocktype.EMExtraFactoryType;
import io.github.masyumero.emextras.common.integration.mekaf.registries.EMExtraAdvancedFactoryBlocks;
import io.github.masyumero.emextras.common.integration.mekmm.registries.EMExtraMoreMachineBlocks;
import io.github.masyumero.emextras.common.registry.EMExtraBlocks;
import io.github.masyumero.emextras.common.registry.EMExtraItems;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import io.github.masyumero.emextras.common.util.EMExtraEnumUtils;
import net.minecraft.data.PackOutput;

public class EMExtraLangProvider extends BaseLanguageProvider {

    public EMExtraLangProvider(PackOutput output) {
        super(output, EMExtras.MODID);
    }

    @Override
    protected void addTranslations() {
        addConfigs();
        addBlocks();
        addItem();
        addMisc();
    }

    private void addConfigs() {
        addConfigs(LoadConfig.getConfigs());
        addConfigs(EMExtraConfigTranslations.values());
    }

    private void addBlocks() {
        for (EMExtraFactoryTier tier : EMExtraEnumUtils.EMEXTRA_FACTORY_TIERS) {
            for (EMExtraFactoryType type : EMExtraEnumUtils.EMEXTRA_FACTORY_TYPES) {
                var name = tier.getEMExtraTier().getSimpleName().replace("_", " ") + " " + type.getRegistryNameComponentCapitalized() + " Factory";
                add(EMExtraBlocks.getEMExtraFactory(tier, type), name);
            }
            for (AdvancedFactoryType type : MoreMachineEnumUtils.ADVANCED_FACTORY_TYPES) {
                String  name;
                if (type == AdvancedFactoryType.CHEMICAL_INFUSING) {
                    name = tier.getEMExtraTier().getSimpleName().replace("_", " ") + " Chemical Infusing Factory";
                } else{
                    name = tier.getEMExtraTier().getSimpleName().replace("_", " ") + " " + type.getRegistryNameComponentCapitalized() + " Factory";
                }
                add(EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactory(tier, type), name);
            }
            for (MoreMachineFactoryType type : MoreMachineEnumUtils.MM_FACTORY_TYPES) {
                String  name;
                if (type == MoreMachineFactoryType.CNC_ROLLING_MILL) {
                    name = tier.getEMExtraTier().getSimpleName().replace("_", " ") + " Rolling Mill";
                } else{
                    name = tier.getEMExtraTier().getSimpleName().replace("_", " ") + " " + type.getRegistryNameComponentCapitalized() + " Factory";
                }
                add(EMExtraMoreMachineBlocks.getEMExtraMoreMachineFactory(tier, type), name);
            }
        }
        for (ExtraFactoryTier tier : ExtraEnumUtils.EXTRA_FACTORY_TIERS) {
            var name = tier.getAdvanceTier().getSimpleName() + " " +  "Alloying Factory";
            add(EMExtraBlocks.getExtraFactory(tier, EMFactoryType.ALLOYING), name);
        }
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
