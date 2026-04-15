package io.github.masyumero.emextras.common.registry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jerry.mekanism_extras.api.tier.IAdvancedTier;
import com.jerry.mekanism_extras.common.block.attribute.ExtraAttributeTier;
import com.jerry.mekanism_extras.common.block.prefab.BlockAdvancedFactoryMachine;
import com.jerry.mekanism_extras.common.content.blocktype.AdvancedFactory;
import com.jerry.mekanism_extras.common.item.block.machine.ItemBlockAdvancedFactory;
import com.jerry.mekanism_extras.common.tier.AdvancedFactoryTier;
import com.jerry.mekanism_extras.common.tile.factory.TileEntityExtraFactory;
import com.jerry.mekanism_extras.common.util.ExtraEnumUtils;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import io.github.masyumero.emextras.api.tier.IEMExtraTier;
import io.github.masyumero.emextras.common.item.block.EMExtraItemBlockInductionCell;
import io.github.masyumero.emextras.common.item.block.EMExtraItemBlockInductionProvider;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.common.block.attribute.EMExtraAttributeTier;
import io.github.masyumero.emextras.common.block.prefab.BlockEMExtraFactoryMachine;
import io.github.masyumero.emextras.common.content.blocktype.EMExtraFactory;
import io.github.masyumero.emextras.common.content.blocktype.EMExtraFactoryType;
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
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class EMExtrasBlock {
    public static final BlockDeferredRegister BLOCK = new BlockDeferredRegister(EMExtras.MODID);

    private static <BLOCK extends Block, ITEM extends BlockItem> BlockRegistryObject<BLOCK, ITEM> registerTieredBlock(String tierName, String suffix, Supplier<? extends BLOCK> blockSupplier, Function<BLOCK, ITEM> itemCreator) {
        return BLOCK.register(tierName + suffix, blockSupplier, itemCreator);
    }

    private static <BLOCK extends Block, ITEM extends BlockItem> BlockRegistryObject<BLOCK, ITEM> registerTieredBlock(BlockType type, String registerName, Function<MapColor, ? extends BLOCK> blockSupplier, Function<BLOCK, ITEM> itemCreator) {
        IEMExtraTier tier = Objects.requireNonNull(type.get(EMExtraAttributeTier.class)).tier();
        return BLOCK.register(registerName, () -> blockSupplier.apply(tier.getEMExtraTier().getMapColor()), itemCreator);
    }

    private static final Table<EMExtraFactoryTier, EMExtraFactoryType, BlockRegistryObject<BlockEMExtraFactoryMachine.BlockEMExtraFactory<?>, ItemBlockEMExtraFactory>> FACTORIES = HashBasedTable.create();

    private static final Table<AdvancedFactoryTier, FactoryType, BlockRegistryObject<BlockAdvancedFactoryMachine.BlockAdvancedFactory<?>, ItemBlockAdvancedFactory>> ADVANCED_FACTORIES = HashBasedTable.create();

    // Induction Cells
    public static final BlockRegistryObject<BlockTile<TileEntityEMExtraInductionCell, BlockTypeTile<TileEntityEMExtraInductionCell>>, EMExtraItemBlockInductionCell> ABSOLUTE_OVERCLOCKED_INDUCTION_CELL = registerInductionCell("absolute_overclocked", EMExtrasBlockType.ABSOLUTE_OVERCLOCKED_INDUCTION_CELL);
    public static final BlockRegistryObject<BlockTile<TileEntityEMExtraInductionCell, BlockTypeTile<TileEntityEMExtraInductionCell>>, EMExtraItemBlockInductionCell> SUPREME_QUANTUM_INDUCTION_CELL = registerInductionCell("supreme_quantum", EMExtrasBlockType.SUPREME_QUANTUM_INDUCTION_CELL);
    public static final BlockRegistryObject<BlockTile<TileEntityEMExtraInductionCell, BlockTypeTile<TileEntityEMExtraInductionCell>>, EMExtraItemBlockInductionCell> COSMIC_DENSE_INDUCTION_CELL = registerInductionCell("cosmic_dense", EMExtrasBlockType.COSMIC_DENSE_INDUCTION_CELL);
    public static final BlockRegistryObject<BlockTile<TileEntityEMExtraInductionCell, BlockTypeTile<TileEntityEMExtraInductionCell>>, EMExtraItemBlockInductionCell> INFINITE_MULTIVERSAL_INDUCTION_CELL = registerInductionCell("infinite_multiversal", EMExtrasBlockType.INFINITE_MULTIVERSAL_INDUCTION_CELL);
    // Induction Provide
    public static final BlockRegistryObject<BlockTile<TileEntityEMExtraInductionProvider, BlockTypeTile<TileEntityEMExtraInductionProvider>>, EMExtraItemBlockInductionProvider> ABSOLUTE_OVERCLOCKED_INDUCTION_PROVIDER = registerInductionProvider("absolute_overclocked", EMExtrasBlockType.ABSOLUTE_OVERCLOCKED_INDUCTION_PROVIDER);
    public static final BlockRegistryObject<BlockTile<TileEntityEMExtraInductionProvider, BlockTypeTile<TileEntityEMExtraInductionProvider>>, EMExtraItemBlockInductionProvider> SUPREME_QUANTUM_INDUCTION_PROVIDER = registerInductionProvider("supreme_quantum", EMExtrasBlockType.SUPREME_QUANTUM_INDUCTION_PROVIDER);
    public static final BlockRegistryObject<BlockTile<TileEntityEMExtraInductionProvider, BlockTypeTile<TileEntityEMExtraInductionProvider>>, EMExtraItemBlockInductionProvider> COSMIC_DENSE_INDUCTION_PROVIDER = registerInductionProvider("cosmic_dense", EMExtrasBlockType.COSMIC_DENSE_INDUCTION_PROVIDER);
    public static final BlockRegistryObject<BlockTile<TileEntityEMExtraInductionProvider, BlockTypeTile<TileEntityEMExtraInductionProvider>>, EMExtraItemBlockInductionProvider> INFINITE_MULTIVERSAL_INDUCTION_PROVIDER = registerInductionProvider("infinite_multiversal", EMExtrasBlockType.INFINITE_MULTIVERSAL_INDUCTION_PROVIDER);

    static {
        // factories
        for (EMExtraFactoryTier tier : EMExtraEnumUtils.EMEXTRA_FACTORY_TIERS) {
            for (EMExtraFactoryType type : EMExtraEnumUtils.EMEXTRA_FACTORY_TYPES) {
                if (type != EMExtraFactoryType.ADVANCED_ALLOYING) {
                    FACTORIES.put(tier, type, registerFactory(EMExtrasBlockType.getEMExtraFactory(tier, type)));
                }
            }
        }
        for (AdvancedFactoryTier tier : ExtraEnumUtils.ADVANCED_FACTORY_TIERS) {
            ADVANCED_FACTORIES.put(tier, EMFactoryType.ALLOYING, registerAdvancedAlloyingFactory(EMExtrasBlockType.getAdvancedFactory(tier, EMFactoryType.ALLOYING)));
        }
    }

    private static <TILE extends TileEntityEMExtraFactory<?>> BlockRegistryObject<BlockEMExtraFactoryMachine.BlockEMExtraFactory<?>, ItemBlockEMExtraFactory> registerFactory(EMExtraFactory<TILE> type) {
        IEMExtraTier tier = Objects.requireNonNull(type.get(EMExtraAttributeTier.class)).tier();
        return registerTieredBlock(tier.getEMExtraTier().getLowerName(), "_" + type.getFactoryType().getRegistryNameComponent() + "_factory", () -> new BlockEMExtraFactoryMachine.BlockEMExtraFactory<>(type), ItemBlockEMExtraFactory::new);
    }

    private static <TILE extends TileEntityExtraFactory<?>> BlockRegistryObject<BlockAdvancedFactoryMachine.BlockAdvancedFactory<?>, ItemBlockAdvancedFactory> registerAdvancedAlloyingFactory(AdvancedFactory<TILE> type) {
        IAdvancedTier tier = Objects.requireNonNull(type.get(ExtraAttributeTier.class)).tier();
        return registerTieredBlock(tier.getAdvanceTier().getLowerName(), "_" + "alloying" + "_factory", () -> new BlockAdvancedFactoryMachine.BlockAdvancedFactory<>(type), ItemBlockAdvancedFactory::new);
    }

    public static BlockRegistryObject<BlockEMExtraFactoryMachine.BlockEMExtraFactory<?>, ItemBlockEMExtraFactory>  getEMExtraFactory(@NotNull EMExtraFactoryTier tier, @NotNull EMExtraFactoryType type) {
        return FACTORIES.get(tier, type);
    }

    public static BlockRegistryObject<BlockAdvancedFactoryMachine.BlockAdvancedFactory<?>, ItemBlockAdvancedFactory>  getAdvancedFactory(@NotNull AdvancedFactoryTier tier, @NotNull FactoryType type) {
        return ADVANCED_FACTORIES.get(tier, type);
    }

    private static BlockRegistryObject<BlockTile<TileEntityEMExtraInductionCell, BlockTypeTile<TileEntityEMExtraInductionCell>>, EMExtraItemBlockInductionCell> registerInductionCell(String tileName, BlockTypeTile<TileEntityEMExtraInductionCell> type) {
        return registerTieredBlock(type, tileName + "_induction_cell", color -> new BlockTile<>(type, properties -> properties.mapColor(color)), EMExtraItemBlockInductionCell::new);
    }

    private static BlockRegistryObject<BlockTile<TileEntityEMExtraInductionProvider, BlockTypeTile<TileEntityEMExtraInductionProvider>>, EMExtraItemBlockInductionProvider> registerInductionProvider(String tileName, BlockTypeTile<TileEntityEMExtraInductionProvider> type) {
        return registerTieredBlock(type, tileName + "_induction_provider", color -> new BlockTile<>(type, properties -> properties.mapColor(color)), EMExtraItemBlockInductionProvider::new);
    }

    public static void register(IEventBus eventBus) {
        BLOCK.register(eventBus);
    }
}
