package io.github.masyumero.emextras.common.registry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jerry.mekanism_extras.api.tier.IAdvancedTier;
import com.jerry.mekanism_extras.common.block.attribute.ExtraAttributeTier;
import com.jerry.mekanism_extras.common.block.prefab.BlockExtraFactoryMachine;
import com.jerry.mekanism_extras.common.content.blocktype.ExtraFactory;
import com.jerry.mekanism_extras.common.item.block.machine.ItemBlockExtraFactory;
import com.jerry.mekanism_extras.common.tier.ExtraFactoryTier;
import com.jerry.mekanism_extras.common.tile.factory.TileEntityExtraFactory;
import com.jerry.mekanism_extras.common.util.ExtraEnumUtils;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import io.github.masyumero.emextras.api.tier.IEMExtraTier;
import io.github.masyumero.emextras.common.block.transmitter.*;
import io.github.masyumero.emextras.common.item.block.EMExtraItemBlockInductionCell;
import io.github.masyumero.emextras.common.item.block.EMExtraItemBlockInductionProvider;
import io.github.masyumero.emextras.common.item.block.transmitter.*;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.common.block.attribute.EMExtraAttributeTier;
import io.github.masyumero.emextras.common.block.prefab.BlockEMExtraFactoryMachine;
import io.github.masyumero.emextras.common.content.blocktype.EMExtraFactory;
import io.github.masyumero.emextras.common.item.block.machine.ItemBlockEMExtraFactory;
import io.github.masyumero.emextras.common.tile.factory.TileEntityEMExtraFactory;
import io.github.masyumero.emextras.common.tile.multiblock.TileEntityEMExtraInductionCell;
import io.github.masyumero.emextras.common.tile.multiblock.TileEntityEMExtraInductionProvider;
import io.github.masyumero.emextras.common.util.EMExtraEnumUtils;
import mekanism.common.block.prefab.BlockTile;
import mekanism.common.content.blocktype.BlockType;
import mekanism.common.content.blocktype.BlockTypeTile;
import mekanism.common.content.blocktype.FactoryType;
import mekanism.common.registration.impl.BlockDeferredRegister;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.tier.*;
import mekanism.common.util.EnumUtils;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class EMExtraBlocks {
    public static final BlockDeferredRegister BLOCK = new BlockDeferredRegister(EMExtras.MODID);

    private static <BLOCK extends Block, ITEM extends BlockItem> BlockRegistryObject<BLOCK, ITEM> registerTieredBlock(String tierName, String suffix, Supplier<? extends BLOCK> blockSupplier, Function<BLOCK, ITEM> itemCreator) {
        return BLOCK.register(tierName + suffix, blockSupplier, itemCreator);
    }

    private static <BLOCK extends Block, ITEM extends BlockItem> BlockRegistryObject<BLOCK, ITEM> registerTieredBlock(BlockType type, String registerName, Function<MapColor, ? extends BLOCK> blockSupplier, Function<BLOCK, ITEM> itemCreator) {
        IEMExtraTier tier = Objects.requireNonNull(type.get(EMExtraAttributeTier.class)).tier();
        return BLOCK.register(registerName, () -> blockSupplier.apply(tier.getEMExtraTier().getMapColor()), itemCreator);
    }

    private static final Table<EMExtraFactoryTier, FactoryType, BlockRegistryObject<BlockEMExtraFactoryMachine.BlockEMExtraFactory<?>, ItemBlockEMExtraFactory>> FACTORIES = HashBasedTable.create();

    private static final Table<ExtraFactoryTier, FactoryType, BlockRegistryObject<BlockExtraFactoryMachine.BlockExtraFactory<?>, ItemBlockExtraFactory>> ADVANCED_FACTORIES = HashBasedTable.create();

    static {
        // factories
        for (EMExtraFactoryTier tier : EMExtraEnumUtils.EMEXTRA_FACTORY_TIERS) {
            for (FactoryType type : EnumUtils.FACTORY_TYPES) {
                FACTORIES.put(tier, type, registerFactory(EMExtraBlockTypes.getEMExtraFactory(tier, type)));
            }
        }
        for (ExtraFactoryTier tier : ExtraEnumUtils.EXTRA_FACTORY_TIERS) {
            ADVANCED_FACTORIES.put(tier, EMFactoryType.ALLOYING, registerExtraAlloyingFactory(EMExtraBlockTypes.getExtraFactory(tier, EMFactoryType.ALLOYING)));
        }
    }

    // Induction Cells
    public static final BlockRegistryObject<BlockTile<TileEntityEMExtraInductionCell, BlockTypeTile<TileEntityEMExtraInductionCell>>, EMExtraItemBlockInductionCell> ABSOLUTE_OVERCLOCKED_INDUCTION_CELL = registerInductionCell("absolute_overclocked", EMExtraBlockTypes.ABSOLUTE_OVERCLOCKED_INDUCTION_CELL);
    public static final BlockRegistryObject<BlockTile<TileEntityEMExtraInductionCell, BlockTypeTile<TileEntityEMExtraInductionCell>>, EMExtraItemBlockInductionCell> SUPREME_QUANTUM_INDUCTION_CELL = registerInductionCell("supreme_quantum", EMExtraBlockTypes.SUPREME_QUANTUM_INDUCTION_CELL);
    public static final BlockRegistryObject<BlockTile<TileEntityEMExtraInductionCell, BlockTypeTile<TileEntityEMExtraInductionCell>>, EMExtraItemBlockInductionCell> COSMIC_DENSE_INDUCTION_CELL = registerInductionCell("cosmic_dense", EMExtraBlockTypes.COSMIC_DENSE_INDUCTION_CELL);
    public static final BlockRegistryObject<BlockTile<TileEntityEMExtraInductionCell, BlockTypeTile<TileEntityEMExtraInductionCell>>, EMExtraItemBlockInductionCell> INFINITE_MULTIVERSAL_INDUCTION_CELL = registerInductionCell("infinite_multiversal", EMExtraBlockTypes.INFINITE_MULTIVERSAL_INDUCTION_CELL);
    // Induction Provide
    public static final BlockRegistryObject<BlockTile<TileEntityEMExtraInductionProvider, BlockTypeTile<TileEntityEMExtraInductionProvider>>, EMExtraItemBlockInductionProvider> ABSOLUTE_OVERCLOCKED_INDUCTION_PROVIDER = registerInductionProvider("absolute_overclocked", EMExtraBlockTypes.ABSOLUTE_OVERCLOCKED_INDUCTION_PROVIDER);
    public static final BlockRegistryObject<BlockTile<TileEntityEMExtraInductionProvider, BlockTypeTile<TileEntityEMExtraInductionProvider>>, EMExtraItemBlockInductionProvider> SUPREME_QUANTUM_INDUCTION_PROVIDER = registerInductionProvider("supreme_quantum", EMExtraBlockTypes.SUPREME_QUANTUM_INDUCTION_PROVIDER);
    public static final BlockRegistryObject<BlockTile<TileEntityEMExtraInductionProvider, BlockTypeTile<TileEntityEMExtraInductionProvider>>, EMExtraItemBlockInductionProvider> COSMIC_DENSE_INDUCTION_PROVIDER = registerInductionProvider("cosmic_dense", EMExtraBlockTypes.COSMIC_DENSE_INDUCTION_PROVIDER);
    public static final BlockRegistryObject<BlockTile<TileEntityEMExtraInductionProvider, BlockTypeTile<TileEntityEMExtraInductionProvider>>, EMExtraItemBlockInductionProvider> INFINITE_MULTIVERSAL_INDUCTION_PROVIDER = registerInductionProvider("infinite_multiversal", EMExtraBlockTypes.INFINITE_MULTIVERSAL_INDUCTION_PROVIDER);
    // Universal Cables
    public static final BlockRegistryObject<EMExtraBlockUniversalCable, EMExtraItemBlockUniversalCable> ABSOLUTE_OVERCLOCKED_UNIVERSAL_CABLE = registerUniversalCable("absolute_overclocked", CableTier.BASIC);
    public static final BlockRegistryObject<EMExtraBlockUniversalCable, EMExtraItemBlockUniversalCable> SUPREME_QUANTUM_UNIVERSAL_CABLE = registerUniversalCable("supreme_quantum", CableTier.ADVANCED);
    public static final BlockRegistryObject<EMExtraBlockUniversalCable, EMExtraItemBlockUniversalCable> COSMIC_DENSE_UNIVERSAL_CABLE = registerUniversalCable("cosmic_dense", CableTier.ELITE);
    public static final BlockRegistryObject<EMExtraBlockUniversalCable, EMExtraItemBlockUniversalCable> INFINITE_MULTIVERSAL_UNIVERSAL_CABLE = registerUniversalCable("infinite_multiversal", CableTier.ULTIMATE);
    // Mechanical Pipes
    public static final BlockRegistryObject<EMExtraBlockMechanicalPipe, EMExtraItemBlockMechanicalPipe> ABSOLUTE_OVERCLOCKED_MECHANICAL_PIPE = registerMechanicalPipe("absolute_overclocked", PipeTier.BASIC);
    public static final BlockRegistryObject<EMExtraBlockMechanicalPipe, EMExtraItemBlockMechanicalPipe> SUPREME_QUANTUM_MECHANICAL_PIPE = registerMechanicalPipe("supreme_quantum", PipeTier.ADVANCED);
    public static final BlockRegistryObject<EMExtraBlockMechanicalPipe, EMExtraItemBlockMechanicalPipe> COSMIC_DENSE_MECHANICAL_PIPE = registerMechanicalPipe("cosmic_dense", PipeTier.ELITE);
    public static final BlockRegistryObject<EMExtraBlockMechanicalPipe, EMExtraItemBlockMechanicalPipe> INFINITE_MULTIVERSAL_MECHANICAL_PIPE = registerMechanicalPipe("infinite_multiversal", PipeTier.ULTIMATE);
    // Pressurized Tubes
    public static final BlockRegistryObject<EMExtraBlockPressurizedTube, EMExtraItemBlockPressurizedTube> ABSOLUTE_OVERCLOCKED_PRESSURIZED_TUBE = registerPressurizedTube("absolute_overclocked", TubeTier.BASIC);
    public static final BlockRegistryObject<EMExtraBlockPressurizedTube, EMExtraItemBlockPressurizedTube> SUPREME_QUANTUM_PRESSURIZED_TUBE = registerPressurizedTube("supreme_quantum", TubeTier.ADVANCED);
    public static final BlockRegistryObject<EMExtraBlockPressurizedTube, EMExtraItemBlockPressurizedTube> COSMIC_DENSE_PRESSURIZED_TUBE = registerPressurizedTube("cosmic_dense", TubeTier.ELITE);
    public static final BlockRegistryObject<EMExtraBlockPressurizedTube, EMExtraItemBlockPressurizedTube> INFINITE_MULTIVERSAL_PRESSURIZED_TUBE = registerPressurizedTube("infinite_multiversal", TubeTier.ULTIMATE);
    // Logistical Transporters
    public static final BlockRegistryObject<EMExtraBlockLogisticalTransporter, EMExtraItemBlockLogisticalTransporter> ABSOLUTE_OVERCLOCKED_LOGISTICAL_TRANSPORTER = registerLogisticalTransporter("absolute_overclocked", TransporterTier.BASIC);
    public static final BlockRegistryObject<EMExtraBlockLogisticalTransporter, EMExtraItemBlockLogisticalTransporter> SUPREME_QUANTUM_LOGISTICAL_TRANSPORTER = registerLogisticalTransporter("supreme_quantum", TransporterTier.ADVANCED);
    public static final BlockRegistryObject<EMExtraBlockLogisticalTransporter, EMExtraItemBlockLogisticalTransporter> COSMIC_DENSE_LOGISTICAL_TRANSPORTER = registerLogisticalTransporter("cosmic_dense", TransporterTier.ELITE);
    public static final BlockRegistryObject<EMExtraBlockLogisticalTransporter, EMExtraItemBlockLogisticalTransporter> INFINITE_MULTIVERSAL_LOGISTICAL_TRANSPORTER = registerLogisticalTransporter("infinite_multiversal", TransporterTier.ULTIMATE);
    // Thermodynamic Conductors
    public static final BlockRegistryObject<EMExtraBlockThermodynamicConductor, EMExtraItemBlockThermodynamicConductor> ABSOLUTE_OVERCLOCKED_THERMODYNAMIC_CONDUCTOR = registerThermodynamicConductor("absolute_overclocked", ConductorTier.BASIC);
    public static final BlockRegistryObject<EMExtraBlockThermodynamicConductor, EMExtraItemBlockThermodynamicConductor> SUPREME_QUANTUM_THERMODYNAMIC_CONDUCTOR = registerThermodynamicConductor("supreme_quantum", ConductorTier.ADVANCED);
    public static final BlockRegistryObject<EMExtraBlockThermodynamicConductor, EMExtraItemBlockThermodynamicConductor> COSMIC_DENSE_THERMODYNAMIC_CONDUCTOR = registerThermodynamicConductor("cosmic_dense", ConductorTier.ELITE);
    public static final BlockRegistryObject<EMExtraBlockThermodynamicConductor, EMExtraItemBlockThermodynamicConductor> INFINITE_MULTIVERSAL_THERMODYNAMIC_CONDUCTOR = registerThermodynamicConductor("infinite_multiversal", ConductorTier.ULTIMATE);

    private static <TILE extends TileEntityEMExtraFactory<?>> BlockRegistryObject<BlockEMExtraFactoryMachine.BlockEMExtraFactory<?>, ItemBlockEMExtraFactory> registerFactory(EMExtraFactory<TILE> type) {
        IEMExtraTier tier = Objects.requireNonNull(type.get(EMExtraAttributeTier.class)).tier();
        return registerTieredBlock(tier.getEMExtraTier().getLowerName(), "_" + type.getFactoryType().getRegistryNameComponent() + "_factory", () -> new BlockEMExtraFactoryMachine.BlockEMExtraFactory<>(type), ItemBlockEMExtraFactory::new);
    }

    private static <TILE extends TileEntityExtraFactory<?>> BlockRegistryObject<BlockExtraFactoryMachine.BlockExtraFactory<?>, ItemBlockExtraFactory> registerExtraAlloyingFactory(ExtraFactory<TILE> type) {
        IAdvancedTier tier = Objects.requireNonNull(type.get(ExtraAttributeTier.class)).tier();
        return registerTieredBlock(tier.getAdvanceTier().getLowerName(), "_" + "alloying" + "_factory", () -> new BlockExtraFactoryMachine.BlockExtraFactory<>(type), ItemBlockExtraFactory::new);
    }

    public static BlockRegistryObject<BlockEMExtraFactoryMachine.BlockEMExtraFactory<?>, ItemBlockEMExtraFactory>  getEMExtraFactory(@NotNull EMExtraFactoryTier tier, @NotNull FactoryType type) {
        return FACTORIES.get(tier, type);
    }

    public static BlockRegistryObject<BlockExtraFactoryMachine.BlockExtraFactory<?>, ItemBlockExtraFactory>  getExtraFactory(@NotNull ExtraFactoryTier tier, @NotNull FactoryType type) {
        return ADVANCED_FACTORIES.get(tier, type);
    }

    private static BlockRegistryObject<BlockTile<TileEntityEMExtraInductionCell, BlockTypeTile<TileEntityEMExtraInductionCell>>, EMExtraItemBlockInductionCell> registerInductionCell(String tileName, BlockTypeTile<TileEntityEMExtraInductionCell> type) {
        return registerTieredBlock(type, tileName + "_induction_cell", color -> new BlockTile<>(type, properties -> properties.mapColor(color)), EMExtraItemBlockInductionCell::new);
    }

    private static BlockRegistryObject<BlockTile<TileEntityEMExtraInductionProvider, BlockTypeTile<TileEntityEMExtraInductionProvider>>, EMExtraItemBlockInductionProvider> registerInductionProvider(String tileName, BlockTypeTile<TileEntityEMExtraInductionProvider> type) {
        return registerTieredBlock(type, tileName + "_induction_provider", color -> new BlockTile<>(type, properties -> properties.mapColor(color)), EMExtraItemBlockInductionProvider::new);
    }

    private static BlockRegistryObject<EMExtraBlockUniversalCable, EMExtraItemBlockUniversalCable> registerUniversalCable(String tileName, CableTier tier) {
        return registerTieredBlock(tileName, "_universal_cable", () -> new EMExtraBlockUniversalCable(tier), EMExtraItemBlockUniversalCable::new);
    }

    private static BlockRegistryObject<EMExtraBlockMechanicalPipe, EMExtraItemBlockMechanicalPipe> registerMechanicalPipe(String tileName, PipeTier tier) {
        return registerTieredBlock(tileName, "_mechanical_pipe", () -> new EMExtraBlockMechanicalPipe(tier), EMExtraItemBlockMechanicalPipe::new);
    }

    private static BlockRegistryObject<EMExtraBlockPressurizedTube, EMExtraItemBlockPressurizedTube> registerPressurizedTube(String tileName, TubeTier tier) {
        return registerTieredBlock(tileName, "_pressurized_tube", () -> new EMExtraBlockPressurizedTube(tier), EMExtraItemBlockPressurizedTube::new);
    }

    private static BlockRegistryObject<EMExtraBlockLogisticalTransporter, EMExtraItemBlockLogisticalTransporter> registerLogisticalTransporter(String tileName, TransporterTier tier) {
        return registerTieredBlock(tileName, "_logistical_transporter", () -> new EMExtraBlockLogisticalTransporter(tier), EMExtraItemBlockLogisticalTransporter::new);
    }

    private static BlockRegistryObject<EMExtraBlockThermodynamicConductor, EMExtraItemBlockThermodynamicConductor> registerThermodynamicConductor(String tileName, ConductorTier tier) {
        return registerTieredBlock(tileName, "_thermodynamic_conductor", () -> new EMExtraBlockThermodynamicConductor(tier), EMExtraItemBlockThermodynamicConductor::new);
    }

    public static void register(IEventBus eventBus) {
        BLOCK.register(eventBus);
    }
}
