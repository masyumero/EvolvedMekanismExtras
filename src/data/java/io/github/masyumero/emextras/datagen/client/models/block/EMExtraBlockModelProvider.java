package io.github.masyumero.emextras.datagen.client.models.block;

import com.jerry.mekaf.common.content.blocktype.AdvancedFactoryType;
import com.jerry.mekextras.common.tier.ExtraFactoryTier;
import com.jerry.mekextras.common.util.ExtraEnumUtils;
import com.jerry.mekmm.common.content.blocktype.MoreMachineFactoryType;
import com.jerry.mekmm.common.util.MoreMachineEnumUtils;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.common.content.blocktype.EMExtraFactoryType;
import io.github.masyumero.emextras.common.integration.mekaf.registries.EMExtraAdvancedFactoryBlocks;
import io.github.masyumero.emextras.common.integration.mekmm.registries.EMExtraMoreMachineBlocks;
import io.github.masyumero.emextras.common.registry.EMExtraBlocks;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import io.github.masyumero.emextras.common.util.EMExtraEnumUtils;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class EMExtraBlockModelProvider extends BaseBlockModelsProvider {

    public EMExtraBlockModelProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, EMExtras.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        for (EMExtraFactoryTier tier : EMExtraEnumUtils.EMEXTRA_FACTORY_TIERS) {
            for (AdvancedFactoryType type : MoreMachineEnumUtils.ADVANCED_FACTORY_TYPES) {
                if (type == AdvancedFactoryType.CENTRIFUGING) {
                    continue;
                }
                simpleAdvancedFactoryMachineBlock(EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactory(tier, type));
            }
            for (MoreMachineFactoryType type : MoreMachineEnumUtils.MM_FACTORY_TYPES) {
                if (type == MoreMachineFactoryType.PLANTING_STATION) {
                    continue;
                }
                simpleMoreMachineFactoryMachineBlock(EMExtraMoreMachineBlocks.getEMExtraMoreMachineFactory(tier, type));
            }
            for (EMExtraFactoryType type : EMExtraEnumUtils.EMEXTRA_FACTORY_TYPES) {
                simpleFactoryMachineBlock(EMExtraBlocks.getEMExtraFactory(tier, type));
            }
        }
        for (ExtraFactoryTier tier : ExtraEnumUtils.EXTRA_FACTORY_TIERS) {
            simpleExtraFactoryMachineBlock(EMExtraBlocks.getExtraFactory(tier, EMFactoryType.ALLOYING));
        }
    }
}
