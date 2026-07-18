package io.github.masyumero.emextras.common.integration.mekaf.regisrty;

import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.api.tier.IEMExtraTier;
import io.github.masyumero.emextras.common.block.attribute.EMExtraAttributeTier;
import io.github.masyumero.emextras.common.integration.mekaf.block.prefab.BlockEMExtraAdvancedFactory;
import io.github.masyumero.emextras.common.integration.mekaf.content.blocktype.EMExtraAdvancedFactory;
import io.github.masyumero.emextras.common.integration.mekaf.item.block.machine.ItemBlockEMExtraAdvancedFactory;
import io.github.masyumero.emextras.common.integration.mekaf.tile.factory.base.TileEntityEMExtraAdvancedFactoryBase;
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
import com.jerry.mekaf.common.content.blocktype.AdvancedFactoryType;
import com.jerry.mekmm.common.util.MoreMachineEnumUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class EMExtraAdvancedFactoryBlocks {

    private EMExtraAdvancedFactoryBlocks() {}

    public static final BlockDeferredRegister BLOCKS = new BlockDeferredRegister(EMExtras.MODID);

    private static final Table<EMExtraFactoryTier, AdvancedFactoryType, BlockRegistryObject<BlockEMExtraAdvancedFactory<?>, ItemBlockEMExtraAdvancedFactory>> FACTORIES = HashBasedTable.create();

    static {
        for (EMExtraFactoryTier tier : EMExtraEnumUtils.EMEXTRA_FACTORY_TIERS) {
            for (AdvancedFactoryType type : MoreMachineEnumUtils.ADVANCED_FACTORY_TYPES) {
                FACTORIES.put(tier, type, registerFactory(EMExtraAdvancedFactoryBlockTypes.getEMExtraAdvancedFactory(tier, type)));
            }
        }
    }

    private static <TILE extends TileEntityEMExtraAdvancedFactoryBase<?>> BlockRegistryObject<BlockEMExtraAdvancedFactory<?>, ItemBlockEMExtraAdvancedFactory> registerFactory(EMExtraAdvancedFactory<TILE> type) {
        return registerTieredBlock(type, "_" + type.getAdvancedFactoryType().getRegistryNameComponent() + "_factory", () -> new BlockEMExtraAdvancedFactory<>(type), ItemBlockEMExtraAdvancedFactory::new);
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
    public static BlockRegistryObject<BlockEMExtraAdvancedFactory<?>, ItemBlockEMExtraAdvancedFactory> getEMExtraAdvancedFactory(@NotNull EMExtraFactoryTier tier, @NotNull AdvancedFactoryType type) {
        return FACTORIES.get(tier, type);
    }

    @SuppressWarnings("unchecked")
    public static BlockRegistryObject<BlockEMExtraAdvancedFactory<?>, ItemBlockEMExtraAdvancedFactory>[] getEMExtraAdvancedFactoryBlocks() {
        return FACTORIES.values().toArray(new BlockRegistryObject[0]);
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
