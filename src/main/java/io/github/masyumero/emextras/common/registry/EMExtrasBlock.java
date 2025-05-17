package io.github.masyumero.emextras.common.registry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jerry.mekanism_extras.api.tier.IAdvancedTier;
import com.jerry.mekanism_extras.common.tier.AdvancedFactoryTier;
import com.jerry.mekanism_extras.common.util.ExtraEnumUtils;
import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.common.block.attribute.EMExtraAttributeTier;
import io.github.masyumero.emextras.common.block.prefab.BlockEMExtraFactoryMachine;
import io.github.masyumero.emextras.common.content.blocktype.EMExtraFactory;
import io.github.masyumero.emextras.common.content.blocktype.EMExtraFactoryType;
import io.github.masyumero.emextras.common.item.block.machine.ItemBlockEMExtraFactory;
import io.github.masyumero.emextras.common.tile.factory.TileEntityEMExtraFactory;
import io.github.masyumero.emextras.common.util.EMExtraEnumUtils;
import mekanism.common.registration.impl.BlockDeferredRegister;
import mekanism.common.registration.impl.BlockRegistryObject;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
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

    private static final Table<AdvancedFactoryTier, EMExtraFactoryType, BlockRegistryObject<BlockEMExtraFactoryMachine.BlockEMExtraFactory<?>, ItemBlockEMExtraFactory>> FACTORIES = HashBasedTable.create();

    static {
        // factories
        for (AdvancedFactoryTier tier : ExtraEnumUtils.ADVANCED_FACTORY_TIERS) {
            for (EMExtraFactoryType type : EMExtraEnumUtils.FACTORY_TYPES) {
                FACTORIES.put(tier, type, registerFactory(EMExtrasBlockType.getEMExtraFactory(tier, type)));
            }
        }
    }

    private static <TILE extends TileEntityEMExtraFactory<?>> BlockRegistryObject<BlockEMExtraFactoryMachine.BlockEMExtraFactory<?>, ItemBlockEMExtraFactory> registerFactory(EMExtraFactory<TILE> type) {
        IAdvancedTier tier = Objects.requireNonNull(type.get(EMExtraAttributeTier.class)).tier();
        return registerTieredBlock(tier.getAdvanceTier().getLowerName(), "_" + type.getFactoryType().getRegistryNameComponent() + "_factory", () -> new BlockEMExtraFactoryMachine.BlockEMExtraFactory<>(type), ItemBlockEMExtraFactory::new);
    }

    public static BlockRegistryObject<BlockEMExtraFactoryMachine.BlockEMExtraFactory<?>, ItemBlockEMExtraFactory> getEMExtraFactory(@NotNull AdvancedFactoryTier tier, @NotNull EMExtraFactoryType type) {
        return FACTORIES.get(tier, type);
    }

    public static void register(IEventBus eventBus) {
        BLOCK.register(eventBus);
    }
}
