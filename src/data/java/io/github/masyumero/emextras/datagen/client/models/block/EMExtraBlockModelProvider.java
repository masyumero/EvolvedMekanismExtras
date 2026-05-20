package io.github.masyumero.emextras.datagen.client.models.block;

import com.jerry.mekanism_extras.common.tier.AdvancedFactoryTier;
import com.jerry.mekanism_extras.common.util.ExtraEnumUtils;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.common.content.blocktype.EMExtraFactoryType;
import io.github.masyumero.emextras.common.registry.EMExtrasBlock;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import io.github.masyumero.emextras.common.util.EMExtraEnumUtils;
import mekanism.api.tier.ITier;
import mekanism.common.Mekanism;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.tier.*;
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

        transporter(EMExtrasBlock.ABSOLUTE_OVERCLOCKED_LOGISTICAL_TRANSPORTER);
        transporter(EMExtrasBlock.SUPREME_QUANTUM_LOGISTICAL_TRANSPORTER);
        transporter(EMExtrasBlock.COSMIC_DENSE_LOGISTICAL_TRANSPORTER);
        transporter(EMExtrasBlock.INFINITE_MULTIVERSAL_LOGISTICAL_TRANSPORTER);

        pipe(EMExtrasBlock.ABSOLUTE_OVERCLOCKED_MECHANICAL_PIPE);
        pipe(EMExtrasBlock.SUPREME_QUANTUM_MECHANICAL_PIPE);
        pipe(EMExtrasBlock.COSMIC_DENSE_MECHANICAL_PIPE);
        pipe(EMExtrasBlock.INFINITE_MULTIVERSAL_MECHANICAL_PIPE);

        tube(EMExtrasBlock.ABSOLUTE_OVERCLOCKED_PRESSURIZED_TUBE);
        tube(EMExtrasBlock.SUPREME_QUANTUM_PRESSURIZED_TUBE);
        tube(EMExtrasBlock.COSMIC_DENSE_PRESSURIZED_TUBE);
        tube(EMExtrasBlock.INFINITE_MULTIVERSAL_PRESSURIZED_TUBE);

        conductor(EMExtrasBlock.ABSOLUTE_OVERCLOCKED_THERMODYNAMIC_CONDUCTOR);
        conductor(EMExtrasBlock.SUPREME_QUANTUM_THERMODYNAMIC_CONDUCTOR);
        conductor(EMExtrasBlock.COSMIC_DENSE_THERMODYNAMIC_CONDUCTOR);
        conductor(EMExtrasBlock.INFINITE_MULTIVERSAL_THERMODYNAMIC_CONDUCTOR);

        cable(EMExtrasBlock.ABSOLUTE_OVERCLOCKED_UNIVERSAL_CABLE);
        cable(EMExtrasBlock.SUPREME_QUANTUM_UNIVERSAL_CABLE);
        cable(EMExtrasBlock.COSMIC_DENSE_UNIVERSAL_CABLE);
        cable(EMExtrasBlock.INFINITE_MULTIVERSAL_UNIVERSAL_CABLE);
    }

    private void transporter(BlockRegistryObject<?, ?> transmitter) {
        TransporterTier tier = Attribute.getTier(transmitter, TransporterTier.class);
        transmitters(transmitter, "logistical_transporter", tier, Mekanism.rl("block/transmitter/large/logistical_transporter/transporter"), false);
    }

    private void pipe(BlockRegistryObject<?, ?> transmitter) {
        PipeTier tier = Attribute.getTier(transmitter, PipeTier.class);
        transmitters(transmitter, "mechanical_pipe", tier, Mekanism.rl("block/transmitter/large/large"), false);
    }

    private void tube(BlockRegistryObject<?, ?> transmitter) {
        TubeTier tier = Attribute.getTier(transmitter, TubeTier.class);
        smallTransmitter(transmitter, "pressurized_tube", tier);
    }

    private void conductor(BlockRegistryObject<?, ?> transmitter) {
        ConductorTier tier = Attribute.getTier(transmitter, ConductorTier.class);
        smallTransmitter(transmitter, "thermodynamic_conductor", tier);
    }

    private void cable(BlockRegistryObject<?, ?> transmitter) {
        CableTier tier = Attribute.getTier(transmitter, CableTier.class);
        smallTransmitter(transmitter, "universal_cable", tier);
    }

    private void smallTransmitter(BlockRegistryObject<?, ?> transmitter, String type, ITier tier) {
        transmitters(transmitter, type, tier, Mekanism.rl("block/transmitter/small/small"), true);
    }
}
