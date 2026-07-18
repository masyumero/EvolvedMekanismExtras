package io.github.masyumero.emextras.common.integration.mekmm.registry;

import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.api.tier.IEMExtraTier;
import io.github.masyumero.emextras.common.block.attribute.EMExtraAttributeTier;
import io.github.masyumero.emextras.common.integration.mekmm.block.prefab.BlockEMExtraMoreMachineFactory;
import io.github.masyumero.emextras.common.integration.mekmm.content.blocktype.EMExtraMoreMachineFactory;
import io.github.masyumero.emextras.common.integration.mekmm.item.block.machine.ItemBlockEMExtraMoreMachineFactory;
import io.github.masyumero.emextras.common.integration.mekmm.tile.TileEntityEMExtraMoreMachineFactory;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import io.github.masyumero.emextras.common.util.EMExtraEnumUtils;

import mekanism.common.content.blocktype.BlockType;
import mekanism.common.registration.impl.BlockDeferredRegister;
import mekanism.common.registration.impl.BlockRegistryObject;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jerry.mekmm.common.content.blocktype.MoreMachineFactoryType;
import com.jerry.mekmm.common.util.MoreMachineEnumUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class EMExtraMoreMachineBlocks {

    private EMExtraMoreMachineBlocks() {}

    public static final BlockDeferredRegister BLOCKS = new BlockDeferredRegister(EMExtras.MODID);

    private static final Table<EMExtraFactoryTier, MoreMachineFactoryType, BlockRegistryObject<BlockEMExtraMoreMachineFactory<?>, ItemBlockEMExtraMoreMachineFactory>> FACTORIES = HashBasedTable.create();

    static {
        // factories
        for (EMExtraFactoryTier tier : EMExtraEnumUtils.EMEXTRA_FACTORY_TIERS) {
            for (MoreMachineFactoryType type : MoreMachineEnumUtils.MM_FACTORY_TYPES) {
                FACTORIES.put(tier, type, registerFactory(EMExtraMoreMachineBlockTypes.getEMExtraMoreMachineFactory(tier, type)));
            }
        }
    }

    private static <TILE extends TileEntityEMExtraMoreMachineFactory<?>> BlockRegistryObject<BlockEMExtraMoreMachineFactory<?>, ItemBlockEMExtraMoreMachineFactory> registerFactory(EMExtraMoreMachineFactory<TILE> type) {
        return registerTieredBlock(type, "_" + type.getMoreMachineFactoryType().getRegistryNameComponent() + "_factory", () -> new BlockEMExtraMoreMachineFactory<>(type), ItemBlockEMExtraMoreMachineFactory::new);
    }

    private static <BLOCK extends Block, ITEM extends BlockItem> BlockRegistryObject<BLOCK, ITEM> registerTieredBlock(BlockType type, String suffix,
                                                                                                                      Supplier<? extends BLOCK> blockSupplier, Function<BLOCK, ITEM> itemCreator) {
        return registerTieredBlock(Objects.requireNonNull(type.get(EMExtraAttributeTier.class)).tier(), suffix, blockSupplier, itemCreator);
    }

    private static <BLOCK extends Block, ITEM extends BlockItem> BlockRegistryObject<BLOCK, ITEM> registerTieredBlock(IEMExtraTier tier, String suffix,
                                                                                                                      Supplier<? extends BLOCK> blockSupplier, Function<BLOCK, ITEM> itemCreator) {
        return BLOCKS.register(tier.getEMExtraTier().getLowerName() + suffix, blockSupplier, itemCreator);
    }

    /**
     * Retrieves a Factory with a defined tier and recipe type.
     *
     * @param tier - tier to add to the Factory
     * @param type - recipe type to add to the Factory
     *
     * @return factory with defined tier and recipe type
     */
    public static BlockRegistryObject<BlockEMExtraMoreMachineFactory<?>, ItemBlockEMExtraMoreMachineFactory> getEMExtraMoreMachineFactory(@NotNull EMExtraFactoryTier tier, @NotNull MoreMachineFactoryType type) {
        return FACTORIES.get(tier, type);
    }

    @SuppressWarnings("unchecked")
    public static BlockRegistryObject<BlockEMExtraMoreMachineFactory<?>, ItemBlockEMExtraMoreMachineFactory>[] getEMExtraMoreMachineFactoryBlocks() {
        return FACTORIES.values().toArray(new BlockRegistryObject[0]);
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
