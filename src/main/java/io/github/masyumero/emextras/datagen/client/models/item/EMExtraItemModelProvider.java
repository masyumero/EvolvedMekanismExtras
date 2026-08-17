package io.github.masyumero.emextras.datagen.client.models.item;

import com.jerry.mekaf.common.content.blocktype.AdvancedFactoryType;
import com.jerry.mekextras.common.tier.ExtraFactoryTier;
import com.jerry.mekextras.common.util.ExtraEnumUtils;
import com.jerry.mekmm.common.content.blocktype.MoreMachineFactoryType;
import com.jerry.mekmm.common.util.MoreMachineEnumUtils;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import io.github.masyumero.emextras.common.integration.mekmm.EMExtraMoreMachineFactoryTypes;
import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.common.content.blocktype.EMExtraFactoryType;
import io.github.masyumero.emextras.common.integration.mekaf.registries.EMExtraAdvancedFactoryBlocks;
import io.github.masyumero.emextras.common.integration.mekmm.registries.EMExtraMoreMachineBlocks;
import io.github.masyumero.emextras.common.registry.EMExtraBlocks;
import io.github.masyumero.emextras.common.registry.EMExtraItems;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import io.github.masyumero.emextras.common.util.EMExtraEnumUtils;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class EMExtraItemModelProvider extends BaseItemModelProvider{

    public EMExtraItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, EMExtras.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        EMExtraItems.ITEM.getEntries().forEach(item -> basicItem(item.get()));
        for (EMExtraFactoryTier tier : EMExtraEnumUtils.EMEXTRA_FACTORY_TIERS) {
            for (EMExtraFactoryType type : EMExtraEnumUtils.EMEXTRA_FACTORY_TYPES) {
                factoryBlock(EMExtraBlocks.getEMExtraFactory(tier, type));
            }
            for (AdvancedFactoryType type : MoreMachineEnumUtils.ADVANCED_FACTORY_TYPES) {
                if (type == AdvancedFactoryType.CENTRIFUGING) {
                    continue;
                }
                advancedFactoryBlock(EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactory(tier, type));
            }
            for (MoreMachineFactoryType type : EMExtraMoreMachineFactoryTypes.SUPPORTED_FACTORY_TYPES) {
                if (type == MoreMachineFactoryType.PLANTING_STATION) {
                    continue;
                }
                moreMachineFactoryBlock(EMExtraMoreMachineBlocks.getEMExtraMoreMachineFactory(tier, type));
            }
        }
        for (ExtraFactoryTier tier : ExtraEnumUtils.EXTRA_FACTORY_TIERS) {
            extraFactoryBlock(EMExtraBlocks.getExtraFactory(tier, EMFactoryType.ALLOYING));
        }
    }
}
