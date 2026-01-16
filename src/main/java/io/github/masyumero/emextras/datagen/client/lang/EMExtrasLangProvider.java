package io.github.masyumero.emextras.datagen.client.lang;

import com.jerry.mekanism_extras.common.tier.AdvancedFactoryTier;
import com.jerry.mekanism_extras.common.util.ExtraEnumUtils;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.EMExtrasLang;
import io.github.masyumero.emextras.common.content.blocktype.EMExtraFactoryType;
import io.github.masyumero.emextras.common.registry.EMExtrasBlock;
import io.github.masyumero.emextras.common.registry.EMExtrasItem;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import io.github.masyumero.emextras.common.util.EMExtraEnumUtils;
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
        for (EMExtraFactoryTier tier : EMExtraEnumUtils.EMEXTRA_FACTORY_TIERS) {
            for (EMExtraFactoryType type : EMExtraEnumUtils.EMEXTRA_FACTORY_TYPES) {
                if (type != EMExtraFactoryType.ADVANCED_ALLOYING) {
                    var name = tier.getEMExtraTier().getSimpleName().replace("_", " ") + " " + type.getRegistryNameComponentCapitalized() + " Factory";
                    add(EMExtrasBlock.getEMExtraFactory(tier, type), name);
                }
            }
        }
        for (AdvancedFactoryTier tier : ExtraEnumUtils.ADVANCED_FACTORY_TIERS) {
            var name = tier.getAdvanceTier().getSimpleName() + " " +  "Alloying Factory";
            add(EMExtrasBlock.getAdvancedFactory(tier, EMFactoryType.ALLOYING), name);
        }
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
