package io.github.masyumero.emextras.datagen.client.models.item;

import com.jerry.mekaf.common.content.blocktype.AdvancedFactoryType;
import com.jerry.mekanism_extras.common.tier.ExtraFactoryTier;
import com.jerry.mekanism_extras.common.util.ExtraEnumUtils;
import com.jerry.mekmm.common.content.blocktype.MoreMachineFactoryType;
import com.jerry.mekmm.common.util.MoreMachineEnumUtils;

import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.common.content.blocktype.EMExtraFactoryType;
import io.github.masyumero.emextras.common.integration.mekaf.regisrty.EMExtraAdvancedFactoryBlocks;
import io.github.masyumero.emextras.common.integration.mekmm.registry.EMExtraMoreMachineBlocks;
import io.github.masyumero.emextras.common.registry.EMExtraBlocks;
import io.github.masyumero.emextras.common.registry.EMExtraItems;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import io.github.masyumero.emextras.common.util.EMExtraEnumUtils;

import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;

public class EMExtraItemModelProvider extends BaseItemModelProvider {

    public EMExtraItemModelProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, EMExtras.MODID, exFileHelper);
    }

    @Override
    protected void registerModels() {
        EMExtraItems.ITEM.getAllItems().forEach(item -> basicItem(item.getRegistryName()));
        for (EMExtraFactoryTier tier : EMExtraEnumUtils.EMEXTRA_FACTORY_TIERS) {
            for (EMExtraFactoryType type : EMExtraEnumUtils.EMEXTRA_FACTORY_TYPES) {
                if (type == EMExtraFactoryType.ADVANCED_ALLOYING) {
                    continue;
                }
                factoryBlock(EMExtraBlocks.getEMExtraFactory(tier, type));
            }
            for (AdvancedFactoryType type : MoreMachineEnumUtils.ADVANCED_FACTORY_TYPES) {
                if (type == AdvancedFactoryType.CENTRIFUGING) {
                    continue;
                }
                advancedFactoryBlock(EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactory(tier, type));
            }
            for (MoreMachineFactoryType type : MoreMachineEnumUtils.MM_FACTORY_TYPES) {
                if (type == MoreMachineFactoryType.PLANTING) {
                    continue;
                }
                moreMachineFactoryBlock(EMExtraMoreMachineBlocks.getEMExtraMoreMachineFactory(tier, type));
            }
        }
        for (ExtraFactoryTier tier : ExtraEnumUtils.EXTRA_FACTORY_TIERS) {
            extraAlloyingFactoryBlock(EMExtraBlocks.getExtraFactory(tier, EMFactoryType.ALLOYING));
        }
    }
}
