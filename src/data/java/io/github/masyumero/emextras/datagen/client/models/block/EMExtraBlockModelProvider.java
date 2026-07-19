package io.github.masyumero.emextras.datagen.client.models.block;

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
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import io.github.masyumero.emextras.common.util.EMExtraEnumUtils;

import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
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
                simpleFactoryMachineBlock(EMExtraBlocks.getEMExtraFactory(tier, type));
            }
            for (AdvancedFactoryType type : MoreMachineEnumUtils.ADVANCED_FACTORY_TYPES) {
                if (type == AdvancedFactoryType.CENTRIFUGING) {
                    continue;
                }
                simpleAdvancedFactoryMachineBlock(EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactory(tier, type));
            }
            for (MoreMachineFactoryType type : MoreMachineEnumUtils.MM_FACTORY_TYPES) {
                if (type == MoreMachineFactoryType.PLANTING) {
                    continue;
                }
                simpleMoreMachineFactoryMachineBlock(EMExtraMoreMachineBlocks.getEMExtraMoreMachineFactory(tier, type));
            }
        }
        for (ExtraFactoryTier tier : ExtraEnumUtils.EXTRA_FACTORY_TIERS) {
            alloyingFactoryMachineBlock(EMExtraBlocks.getExtraFactory(tier, EMFactoryType.ALLOYING));
        }
        inductionCellAndProvider(EMExtraBlocks.ABSOLUTE_OVERCLOCKED_INDUCTION_CELL, EMExtraBlocks.ABSOLUTE_OVERCLOCKED_INDUCTION_PROVIDER);
        inductionCellAndProvider(EMExtraBlocks.SUPREME_QUANTUM_INDUCTION_CELL, EMExtraBlocks.SUPREME_QUANTUM_INDUCTION_PROVIDER);
        inductionCellAndProvider(EMExtraBlocks.COSMIC_DENSE_INDUCTION_CELL, EMExtraBlocks.COSMIC_DENSE_INDUCTION_PROVIDER);
        inductionCellAndProvider(EMExtraBlocks.INFINITE_MULTIVERSAL_INDUCTION_CELL, EMExtraBlocks.INFINITE_MULTIVERSAL_INDUCTION_PROVIDER);

        transporter(EMExtraBlocks.ABSOLUTE_OVERCLOCKED_LOGISTICAL_TRANSPORTER);
        transporter(EMExtraBlocks.SUPREME_QUANTUM_LOGISTICAL_TRANSPORTER);
        transporter(EMExtraBlocks.COSMIC_DENSE_LOGISTICAL_TRANSPORTER);
        transporter(EMExtraBlocks.INFINITE_MULTIVERSAL_LOGISTICAL_TRANSPORTER);

        pipe(EMExtraBlocks.ABSOLUTE_OVERCLOCKED_MECHANICAL_PIPE);
        pipe(EMExtraBlocks.SUPREME_QUANTUM_MECHANICAL_PIPE);
        pipe(EMExtraBlocks.COSMIC_DENSE_MECHANICAL_PIPE);
        pipe(EMExtraBlocks.INFINITE_MULTIVERSAL_MECHANICAL_PIPE);

        tube(EMExtraBlocks.ABSOLUTE_OVERCLOCKED_PRESSURIZED_TUBE);
        tube(EMExtraBlocks.SUPREME_QUANTUM_PRESSURIZED_TUBE);
        tube(EMExtraBlocks.COSMIC_DENSE_PRESSURIZED_TUBE);
        tube(EMExtraBlocks.INFINITE_MULTIVERSAL_PRESSURIZED_TUBE);

        conductor(EMExtraBlocks.ABSOLUTE_OVERCLOCKED_THERMODYNAMIC_CONDUCTOR);
        conductor(EMExtraBlocks.SUPREME_QUANTUM_THERMODYNAMIC_CONDUCTOR);
        conductor(EMExtraBlocks.COSMIC_DENSE_THERMODYNAMIC_CONDUCTOR);
        conductor(EMExtraBlocks.INFINITE_MULTIVERSAL_THERMODYNAMIC_CONDUCTOR);

        cable(EMExtraBlocks.ABSOLUTE_OVERCLOCKED_UNIVERSAL_CABLE);
        cable(EMExtraBlocks.SUPREME_QUANTUM_UNIVERSAL_CABLE);
        cable(EMExtraBlocks.COSMIC_DENSE_UNIVERSAL_CABLE);
        cable(EMExtraBlocks.INFINITE_MULTIVERSAL_UNIVERSAL_CABLE);
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
