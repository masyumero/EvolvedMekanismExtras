package io.github.masyumero.emextras.common.registry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import io.github.masyumero.emextras.api.tier.IEMExtraTier;
import io.github.masyumero.emextras.common.block.basic.EMExtraBlockBin;
import io.github.masyumero.emextras.common.item.block.EMExtraItemBlockBin;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.common.block.attribute.EMExtraAttributeTier;
import io.github.masyumero.emextras.common.block.prefab.BlockEMExtraFactoryMachine;
import io.github.masyumero.emextras.common.content.blocktype.EMExtraFactory;
import io.github.masyumero.emextras.common.content.blocktype.EMExtraFactoryType;
import io.github.masyumero.emextras.common.item.block.machine.ItemBlockEMExtraFactory;
import io.github.masyumero.emextras.common.tile.factory.EMExtraTileEntityBin;
import io.github.masyumero.emextras.common.tile.factory.TileEntityEMExtraFactory;
import io.github.masyumero.emextras.common.util.EMExtraEnumUtils;
import mekanism.common.content.blocktype.BlockType;
import mekanism.common.content.blocktype.BlockTypeTile;
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

    private static <BLOCK extends Block, ITEM extends BlockItem> BlockRegistryObject<BLOCK, ITEM> registerTieredBlock_1(BlockType type, String registerName, Function<MapColor, ? extends BLOCK> blockSupplier, Function<BLOCK, ITEM> itemCreator) {
        IEMExtraTier tier = Objects.requireNonNull(type.get(EMExtraAttributeTier.class)).tier();
        return BLOCK.register(registerName, () -> blockSupplier.apply(tier.getEMExtraTier().getMapColor()), itemCreator);
    }

    private static final Table<EMExtraFactoryTier, EMExtraFactoryType, BlockRegistryObject<BlockEMExtraFactoryMachine.BlockEMExtraFactory<?>, ItemBlockEMExtraFactory>> FACTORIES = HashBasedTable.create();

    static {
        // factories
        for (EMExtraFactoryTier tier : EMExtraEnumUtils.EMEXTRA_FACTORY_TIERS) {
            for (EMExtraFactoryType type : EMExtraEnumUtils.EMEXTRA_FACTORY_TYPES) {
                if (type != EMExtraFactoryType.ALLOYING) {
                    if (tier.isEvolved()) {
                        FACTORIES.put(tier, type, registerFactory(EMExtrasBlockType.getEMExtraFactory(tier, type)));
                    }
                } else {
                    FACTORIES.put(tier, type, registerFactory(EMExtrasBlockType.getEMExtraFactory(tier, type)));
                }
            }
        }
    }

    public static final BlockRegistryObject<EMExtraBlockBin, EMExtraItemBlockBin> ABSOLUTE_OVERCLOCKED_BIN = registerBin("absolute_overclocked", EMExtrasBlockType.ABSOLUTE_OVERCLOCKED_BIN);
    public static final BlockRegistryObject<EMExtraBlockBin, EMExtraItemBlockBin> SUPREME_QUANTUM_BIN = registerBin("supreme_quantum", EMExtrasBlockType.SUPREME_QUANTUM_BIN);
    public static final BlockRegistryObject<EMExtraBlockBin, EMExtraItemBlockBin> COSMIC_DENSE_BIN = registerBin("cosmic_dense", EMExtrasBlockType.COSMIC_DENSE_BIN);
    public static final BlockRegistryObject<EMExtraBlockBin, EMExtraItemBlockBin> INFINITE_MULTIVERSAL_BIN = registerBin("infinite_multiversal", EMExtrasBlockType.INFINITE_MULTIVERSAL_BIN);

    private static BlockRegistryObject<EMExtraBlockBin, EMExtraItemBlockBin> registerBin(String tileName, BlockTypeTile<EMExtraTileEntityBin> type) {
        return registerTieredBlock_1(type, tileName + "_bin", color -> new EMExtraBlockBin(type, properties -> properties.mapColor(color)), EMExtraItemBlockBin::new);
    }

    private static <TILE extends TileEntityEMExtraFactory<?>> BlockRegistryObject<BlockEMExtraFactoryMachine.BlockEMExtraFactory<?>, ItemBlockEMExtraFactory> registerFactory(EMExtraFactory<TILE> type) {
        IEMExtraTier tier = Objects.requireNonNull(type.get(EMExtraAttributeTier.class)).tier();
        return registerTieredBlock(tier.getEMExtraTier().getLowerName(), "_" + type.getFactoryType().getRegistryNameComponent() + "_factory", () -> new BlockEMExtraFactoryMachine.BlockEMExtraFactory<>(type), ItemBlockEMExtraFactory::new);
    }

    public static BlockRegistryObject<BlockEMExtraFactoryMachine.BlockEMExtraFactory<?>, ItemBlockEMExtraFactory>  getEMExtraFactory(@NotNull EMExtraFactoryTier tier, @NotNull EMExtraFactoryType type) {
        return FACTORIES.get(tier, type);
    }

    public static void register(IEventBus eventBus) {
        BLOCK.register(eventBus);
    }
}
