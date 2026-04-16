package io.github.masyumero.emextras.datagen.client.models.block;

import com.jerry.mekanism_extras.common.tier.AdvancedFactoryTier;
import com.jerry.mekanism_extras.common.util.ExtraEnumUtils;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.common.content.blocktype.EMExtraFactoryType;
import io.github.masyumero.emextras.common.registry.EMExtrasBlock;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import io.github.masyumero.emextras.common.util.EMExtraEnumUtils;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;

public class EMExtraBlockModelProvider extends BaseBlockModelsProvider {

    public EMExtraBlockModelProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, EMExtras.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        for (EMExtraFactoryTier tier : EMExtraEnumUtils.EMEXTRA_FACTORY_TIERS) {
            for (EMExtraFactoryType type : EMExtraEnumUtils.EMEXTRA_FACTORY_TYPES) {
                if (type == EMExtraFactoryType.ADVANCED_ALLOYING) {
                    continue;
                }
                simpleFactoryMachineBlock(EMExtrasBlock.getEMExtraFactory(tier, type));
            }
        }
        for (AdvancedFactoryTier tier : ExtraEnumUtils.ADVANCED_FACTORY_TIERS) {
            alloyingFactoryMachineBlock(EMExtrasBlock.getAdvancedFactory(tier, EMFactoryType.ALLOYING));
        }
        inductionCellAndProvider(EMExtrasBlock.ABSOLUTE_OVERCLOCKED_INDUCTION_CELL, EMExtrasBlock.ABSOLUTE_OVERCLOCKED_INDUCTION_PROVIDER);
        inductionCellAndProvider(EMExtrasBlock.SUPREME_QUANTUM_INDUCTION_CELL, EMExtrasBlock.SUPREME_QUANTUM_INDUCTION_PROVIDER);
        inductionCellAndProvider(EMExtrasBlock.COSMIC_DENSE_INDUCTION_CELL, EMExtrasBlock.COSMIC_DENSE_INDUCTION_PROVIDER);
        inductionCellAndProvider(EMExtrasBlock.INFINITE_MULTIVERSAL_INDUCTION_CELL, EMExtrasBlock.INFINITE_MULTIVERSAL_INDUCTION_PROVIDER);
    }
}
