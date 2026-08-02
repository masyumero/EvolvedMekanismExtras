package io.github.masyumero.emextras.common.registry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jerry.mekanism_extras.common.tier.ExtraFactoryTier;
import com.jerry.mekanism_extras.common.tile.factory.TileEntityExtraFactory;
import com.jerry.mekanism_extras.common.util.ExtraEnumUtils;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import fr.iglee42.evolvedmekanism.registries.EMTileEntityTypes;
import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.common.tile.factory.*;
import io.github.masyumero.emextras.common.tile.multiblock.TileEntityEMExtraInductionCell;
import io.github.masyumero.emextras.common.tile.multiblock.TileEntityEMExtraInductionProvider;
import io.github.masyumero.emextras.common.tile.transmitter.*;
import io.github.masyumero.emextras.common.util.EMExtraEnumUtils;
import mekanism.common.content.blocktype.FactoryType;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.registration.impl.TileEntityTypeDeferredRegister;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tile.base.TileEntityMekanism;
import mekanism.common.tile.transmitter.TileEntityTransmitter;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;

public class EMExtraTileEntityTypes {

    public static final TileEntityTypeDeferredRegister TILE_ENTITY_TYPES = new TileEntityTypeDeferredRegister(EMExtras.MODID);

    private static final Table<EMExtraFactoryTier, FactoryType, TileEntityTypeRegistryObject<? extends TileEntityEMExtraFactory<?>>> FACTORIES = HashBasedTable.create();

    private static final Table<ExtraFactoryTier, FactoryType, TileEntityTypeRegistryObject<? extends TileEntityExtraFactory<?>>> ADVANCED_FACTORIES = HashBasedTable.create();

    static {
        for (EMExtraFactoryTier tier : EMExtraEnumUtils.EMEXTRA_FACTORY_TIERS) {
            FACTORIES.put(tier, FactoryType.SMELTING, TILE_ENTITY_TYPES.register(EMExtraBlocks.getEMExtraFactory(tier, FactoryType.SMELTING), (pos, state) -> new TileEntityItemStackToItemStackEMExtraFactory(EMExtraBlocks.getEMExtraFactory(tier, FactoryType.SMELTING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, FactoryType.CRUSHING, TILE_ENTITY_TYPES.register(EMExtraBlocks.getEMExtraFactory(tier, FactoryType.CRUSHING), (pos, state) -> new TileEntityItemStackToItemStackEMExtraFactory(EMExtraBlocks.getEMExtraFactory(tier, FactoryType.CRUSHING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, FactoryType.ENRICHING, TILE_ENTITY_TYPES.register(EMExtraBlocks.getEMExtraFactory(tier, FactoryType.ENRICHING), (pos, state) -> new TileEntityItemStackToItemStackEMExtraFactory(EMExtraBlocks.getEMExtraFactory(tier, FactoryType.ENRICHING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, FactoryType.COMPRESSING, TILE_ENTITY_TYPES.register(EMExtraBlocks.getEMExtraFactory(tier, FactoryType.COMPRESSING), (pos, state) -> new TileEntityItemStackGasToItemStackEMExtraFactory(EMExtraBlocks.getEMExtraFactory(tier, FactoryType.COMPRESSING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, FactoryType.INJECTING, TILE_ENTITY_TYPES.register(EMExtraBlocks.getEMExtraFactory(tier, FactoryType.INJECTING), (pos, state) -> new TileEntityItemStackGasToItemStackEMExtraFactory(EMExtraBlocks.getEMExtraFactory(tier, FactoryType.INJECTING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, FactoryType.PURIFYING, TILE_ENTITY_TYPES.register(EMExtraBlocks.getEMExtraFactory(tier, FactoryType.PURIFYING), (pos, state) -> new TileEntityItemStackGasToItemStackEMExtraFactory(EMExtraBlocks.getEMExtraFactory(tier, FactoryType.PURIFYING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, FactoryType.INFUSING, TILE_ENTITY_TYPES.register(EMExtraBlocks.getEMExtraFactory(tier, FactoryType.INFUSING), (pos, state) -> new TileEntityMetallurgicInfuserEMExtraFactory(EMExtraBlocks.getEMExtraFactory(tier, FactoryType.INFUSING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, FactoryType.COMBINING, TILE_ENTITY_TYPES.register(EMExtraBlocks.getEMExtraFactory(tier, FactoryType.COMBINING), (pos, state) -> new TileEntityCombiningEMExtraFactory(EMExtraBlocks.getEMExtraFactory(tier, FactoryType.COMBINING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, FactoryType.SAWING, TILE_ENTITY_TYPES.register(EMExtraBlocks.getEMExtraFactory(tier, FactoryType.SAWING), (pos, state) -> new TileEntitySawingEMExtraFactory(EMExtraBlocks.getEMExtraFactory(tier, FactoryType.SAWING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, EMFactoryType.ALLOYING, EMTileEntityTypes.TILE_ENTITY_TYPES.register(EMExtraBlocks.getEMExtraFactory(tier, EMFactoryType.ALLOYING), (pos, state) -> new TileEntityEMExtraAlloyingFactory(EMExtraBlocks.getEMExtraFactory(tier, EMFactoryType.ALLOYING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
        }
        for (ExtraFactoryTier tier  : ExtraEnumUtils.EXTRA_FACTORY_TIERS) {
            ADVANCED_FACTORIES.put(tier, EMFactoryType.ALLOYING, EMTileEntityTypes.TILE_ENTITY_TYPES.register(EMExtraBlocks.getExtraFactory(tier, EMFactoryType.ALLOYING), (pos, state) -> new TileEntityAdvancedAlloyingFactory(EMExtraBlocks.getExtraFactory(tier, EMFactoryType.ALLOYING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
        }
    }

    private static <BE extends TileEntityTransmitter> TileEntityTypeRegistryObject<BE> registerTransmitter(BlockRegistryObject<?, ?> block, BlockEntityType.BlockEntitySupplier<? extends BE> factory) {
        // Note: There is no data fixer type as forge does not currently have a way exposing data fixers to mods yet
        return TILE_ENTITY_TYPES.<BE>builder(block, factory).serverTicker(TileEntityTransmitter::tickServer).build();
    }

    // Induction Cells
    public static final TileEntityTypeRegistryObject<TileEntityEMExtraInductionCell> ABSOLUTE_OVERCLOCKED_INDUCTION_CELL = TILE_ENTITY_TYPES.register(EMExtraBlocks.ABSOLUTE_OVERCLOCKED_INDUCTION_CELL, (pos, state) -> new TileEntityEMExtraInductionCell(EMExtraBlocks.ABSOLUTE_OVERCLOCKED_INDUCTION_CELL, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<TileEntityEMExtraInductionCell> SUPREME_QUANTUM_INDUCTION_CELL = TILE_ENTITY_TYPES.register(EMExtraBlocks.SUPREME_QUANTUM_INDUCTION_CELL, (pos, state) -> new TileEntityEMExtraInductionCell(EMExtraBlocks.SUPREME_QUANTUM_INDUCTION_CELL, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<TileEntityEMExtraInductionCell> COSMIC_DENSE_INDUCTION_CELL = TILE_ENTITY_TYPES.register(EMExtraBlocks.COSMIC_DENSE_INDUCTION_CELL, (pos, state) -> new TileEntityEMExtraInductionCell(EMExtraBlocks.COSMIC_DENSE_INDUCTION_CELL, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<TileEntityEMExtraInductionCell> INFINITE_MULTIVERSAL_INDUCTION_CELL = TILE_ENTITY_TYPES.register(EMExtraBlocks.INFINITE_MULTIVERSAL_INDUCTION_CELL, (pos, state) -> new TileEntityEMExtraInductionCell(EMExtraBlocks.INFINITE_MULTIVERSAL_INDUCTION_CELL, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    // Induction Providers
    public static final TileEntityTypeRegistryObject<TileEntityEMExtraInductionProvider> ABSOLUTE_OVERCLOCKED_INDUCTION_PROVIDER = TILE_ENTITY_TYPES.register(EMExtraBlocks.ABSOLUTE_OVERCLOCKED_INDUCTION_PROVIDER, (pos, state) -> new TileEntityEMExtraInductionProvider(EMExtraBlocks.ABSOLUTE_OVERCLOCKED_INDUCTION_PROVIDER, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<TileEntityEMExtraInductionProvider> SUPREME_QUANTUM_INDUCTION_PROVIDER = TILE_ENTITY_TYPES.register(EMExtraBlocks.SUPREME_QUANTUM_INDUCTION_PROVIDER, (pos, state) -> new TileEntityEMExtraInductionProvider(EMExtraBlocks.SUPREME_QUANTUM_INDUCTION_PROVIDER, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<TileEntityEMExtraInductionProvider> COSMIC_DENSE_INDUCTION_PROVIDER = TILE_ENTITY_TYPES.register(EMExtraBlocks.COSMIC_DENSE_INDUCTION_PROVIDER, (pos, state) -> new TileEntityEMExtraInductionProvider(EMExtraBlocks.COSMIC_DENSE_INDUCTION_PROVIDER, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<TileEntityEMExtraInductionProvider> INFINITE_MULTIVERSAL_INDUCTION_PROVIDER = TILE_ENTITY_TYPES.register(EMExtraBlocks.INFINITE_MULTIVERSAL_INDUCTION_PROVIDER, (pos, state) -> new TileEntityEMExtraInductionProvider(EMExtraBlocks.INFINITE_MULTIVERSAL_INDUCTION_PROVIDER, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    // universal cables
    public static final TileEntityTypeRegistryObject<TileEntityEMExtraUniversalCable> ABSOLUTE_OVERCLOCKED_UNIVERSAL_CABLE = registerTransmitter(EMExtraBlocks.ABSOLUTE_OVERCLOCKED_UNIVERSAL_CABLE, (pos, state) -> new TileEntityEMExtraUniversalCable(EMExtraBlocks.ABSOLUTE_OVERCLOCKED_UNIVERSAL_CABLE, pos, state));
    public static final TileEntityTypeRegistryObject<TileEntityEMExtraUniversalCable> SUPREME_QUANTUM_UNIVERSAL_CABLE = registerTransmitter(EMExtraBlocks.SUPREME_QUANTUM_UNIVERSAL_CABLE, (pos, state) -> new TileEntityEMExtraUniversalCable(EMExtraBlocks.SUPREME_QUANTUM_UNIVERSAL_CABLE, pos, state));
    public static final TileEntityTypeRegistryObject<TileEntityEMExtraUniversalCable> COSMIC_DENSE_UNIVERSAL_CABLE = registerTransmitter(EMExtraBlocks.COSMIC_DENSE_UNIVERSAL_CABLE, (pos, state) -> new TileEntityEMExtraUniversalCable(EMExtraBlocks.COSMIC_DENSE_UNIVERSAL_CABLE, pos, state));
    public static final TileEntityTypeRegistryObject<TileEntityEMExtraUniversalCable> INFINITE_MULTIVERSAL_UNIVERSAL_CABLE = registerTransmitter(EMExtraBlocks.INFINITE_MULTIVERSAL_UNIVERSAL_CABLE, (pos, state) -> new TileEntityEMExtraUniversalCable(EMExtraBlocks.INFINITE_MULTIVERSAL_UNIVERSAL_CABLE, pos, state));
    // mechanical pipes
    public static final TileEntityTypeRegistryObject<TileEntityEMExtraMechanicalPipe> ABSOLUTE_OVERCLOCKED_MECHANICAL_PIPE = registerTransmitter(EMExtraBlocks.ABSOLUTE_OVERCLOCKED_MECHANICAL_PIPE, (pos, state) -> new TileEntityEMExtraMechanicalPipe(EMExtraBlocks.ABSOLUTE_OVERCLOCKED_MECHANICAL_PIPE, pos, state));
    public static final TileEntityTypeRegistryObject<TileEntityEMExtraMechanicalPipe> SUPREME_QUANTUM_MECHANICAL_PIPE = registerTransmitter(EMExtraBlocks.SUPREME_QUANTUM_MECHANICAL_PIPE, (pos, state) -> new TileEntityEMExtraMechanicalPipe(EMExtraBlocks.SUPREME_QUANTUM_MECHANICAL_PIPE, pos, state));
    public static final TileEntityTypeRegistryObject<TileEntityEMExtraMechanicalPipe> COSMIC_DENSE_MECHANICAL_PIPE = registerTransmitter(EMExtraBlocks.COSMIC_DENSE_MECHANICAL_PIPE, (pos, state) -> new TileEntityEMExtraMechanicalPipe(EMExtraBlocks.COSMIC_DENSE_MECHANICAL_PIPE, pos, state));
    public static final TileEntityTypeRegistryObject<TileEntityEMExtraMechanicalPipe> INFINITE_MULTIVERSAL_MECHANICAL_PIPE = registerTransmitter(EMExtraBlocks.INFINITE_MULTIVERSAL_MECHANICAL_PIPE, (pos, state) -> new TileEntityEMExtraMechanicalPipe(EMExtraBlocks.INFINITE_MULTIVERSAL_MECHANICAL_PIPE, pos, state));
    // pressurized tubes
    public static final TileEntityTypeRegistryObject<TileEntityEMExtraPressurizedTube> ABSOLUTE_OVERCLOCKED_PRESSURIZED_TUBE = registerTransmitter(EMExtraBlocks.ABSOLUTE_OVERCLOCKED_PRESSURIZED_TUBE, (pos, state) -> new TileEntityEMExtraPressurizedTube(EMExtraBlocks.ABSOLUTE_OVERCLOCKED_PRESSURIZED_TUBE, pos, state));
    public static final TileEntityTypeRegistryObject<TileEntityEMExtraPressurizedTube> SUPREME_QUANTUM_PRESSURIZED_TUBE = registerTransmitter(EMExtraBlocks.SUPREME_QUANTUM_PRESSURIZED_TUBE, (pos, state) -> new TileEntityEMExtraPressurizedTube(EMExtraBlocks.SUPREME_QUANTUM_PRESSURIZED_TUBE, pos, state));
    public static final TileEntityTypeRegistryObject<TileEntityEMExtraPressurizedTube> COSMIC_DENSE_PRESSURIZED_TUBE = registerTransmitter(EMExtraBlocks.COSMIC_DENSE_PRESSURIZED_TUBE, (pos, state) -> new TileEntityEMExtraPressurizedTube(EMExtraBlocks.COSMIC_DENSE_PRESSURIZED_TUBE, pos, state));
    public static final TileEntityTypeRegistryObject<TileEntityEMExtraPressurizedTube> INFINITE_MULTIVERSAL_PRESSURIZED_TUBE = registerTransmitter(EMExtraBlocks.INFINITE_MULTIVERSAL_PRESSURIZED_TUBE, (pos, state) -> new TileEntityEMExtraPressurizedTube(EMExtraBlocks.INFINITE_MULTIVERSAL_PRESSURIZED_TUBE, pos, state));
    // logistic transporters
    public static final TileEntityTypeRegistryObject<TileEntityEMExtraLogisticalTransporter> ABSOLUTE_OVERCLOCKED_LOGISTICAL_TRANSPORTER = TILE_ENTITY_TYPES.builder(EMExtraBlocks.ABSOLUTE_OVERCLOCKED_LOGISTICAL_TRANSPORTER, (pos, state) -> new TileEntityEMExtraLogisticalTransporter(EMExtraBlocks.ABSOLUTE_OVERCLOCKED_LOGISTICAL_TRANSPORTER, pos, state)).clientTicker(TileEntityEMExtraLogisticalTransporterBase::tickClient).serverTicker(TileEntityEMExtraTransmitter::extraTickServer).build();
    public static final TileEntityTypeRegistryObject<TileEntityEMExtraLogisticalTransporter> SUPREME_QUANTUM_LOGISTICAL_TRANSPORTER = TILE_ENTITY_TYPES.builder(EMExtraBlocks.SUPREME_QUANTUM_LOGISTICAL_TRANSPORTER, (pos, state) -> new TileEntityEMExtraLogisticalTransporter(EMExtraBlocks.SUPREME_QUANTUM_LOGISTICAL_TRANSPORTER, pos, state)).clientTicker(TileEntityEMExtraLogisticalTransporterBase::tickClient).serverTicker(TileEntityEMExtraTransmitter::extraTickServer).build();
    public static final TileEntityTypeRegistryObject<TileEntityEMExtraLogisticalTransporter> COSMIC_DENSE_LOGISTICAL_TRANSPORTER = TILE_ENTITY_TYPES.builder(EMExtraBlocks.COSMIC_DENSE_LOGISTICAL_TRANSPORTER, (pos, state) -> new TileEntityEMExtraLogisticalTransporter(EMExtraBlocks.COSMIC_DENSE_LOGISTICAL_TRANSPORTER, pos, state)).clientTicker(TileEntityEMExtraLogisticalTransporterBase::tickClient).serverTicker(TileEntityEMExtraTransmitter::extraTickServer).build();
    public static final TileEntityTypeRegistryObject<TileEntityEMExtraLogisticalTransporter> INFINITE_MULTIVERSAL_LOGISTICAL_TRANSPORTER = TILE_ENTITY_TYPES.builder(EMExtraBlocks.INFINITE_MULTIVERSAL_LOGISTICAL_TRANSPORTER, (pos, state) -> new TileEntityEMExtraLogisticalTransporter(EMExtraBlocks.INFINITE_MULTIVERSAL_LOGISTICAL_TRANSPORTER, pos, state)).clientTicker(TileEntityEMExtraLogisticalTransporterBase::tickClient).serverTicker(TileEntityEMExtraTransmitter::extraTickServer).build();
    // thermodynamic conductors
    public static final TileEntityTypeRegistryObject<TileEntityEMExtraThermodynamicConductor> ABSOLUTE_OVERCLOCKED_THERMODYNAMIC_CONDUCTOR = registerTransmitter(EMExtraBlocks.ABSOLUTE_OVERCLOCKED_THERMODYNAMIC_CONDUCTOR, (pos, state) -> new TileEntityEMExtraThermodynamicConductor(EMExtraBlocks.ABSOLUTE_OVERCLOCKED_THERMODYNAMIC_CONDUCTOR, pos, state));
    public static final TileEntityTypeRegistryObject<TileEntityEMExtraThermodynamicConductor> SUPREME_QUANTUM_THERMODYNAMIC_CONDUCTOR = registerTransmitter(EMExtraBlocks.SUPREME_QUANTUM_THERMODYNAMIC_CONDUCTOR, (pos, state) -> new TileEntityEMExtraThermodynamicConductor(EMExtraBlocks.SUPREME_QUANTUM_THERMODYNAMIC_CONDUCTOR, pos, state));
    public static final TileEntityTypeRegistryObject<TileEntityEMExtraThermodynamicConductor> COSMIC_DENSE_THERMODYNAMIC_CONDUCTOR = registerTransmitter(EMExtraBlocks.COSMIC_DENSE_THERMODYNAMIC_CONDUCTOR, (pos, state) -> new TileEntityEMExtraThermodynamicConductor(EMExtraBlocks.COSMIC_DENSE_THERMODYNAMIC_CONDUCTOR, pos, state));
    public static final TileEntityTypeRegistryObject<TileEntityEMExtraThermodynamicConductor> INFINITE_MULTIVERSAL_THERMODYNAMIC_CONDUCTOR = registerTransmitter(EMExtraBlocks.INFINITE_MULTIVERSAL_THERMODYNAMIC_CONDUCTOR, (pos, state) -> new TileEntityEMExtraThermodynamicConductor(EMExtraBlocks.INFINITE_MULTIVERSAL_THERMODYNAMIC_CONDUCTOR, pos, state));
    
    public static TileEntityTypeRegistryObject<? extends TileEntityEMExtraFactory<?>> getEMExtraFactoryTile(EMExtraFactoryTier tier, FactoryType type) {
        return FACTORIES.get(tier, type);
    }

    public static TileEntityTypeRegistryObject<? extends TileEntityExtraFactory<?>> getExtraFactoryTile(ExtraFactoryTier tier, FactoryType type) {
        return ADVANCED_FACTORIES.get(tier, type);
    }

    public static void register(IEventBus eventBus) {
        TILE_ENTITY_TYPES.register(eventBus);
    }
}
