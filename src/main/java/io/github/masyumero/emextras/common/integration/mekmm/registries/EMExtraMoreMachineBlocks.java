package io.github.masyumero.emextras.common.integration.mekmm.registries;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jerry.mekmm.common.content.blocktype.MoreMachineFactoryType;
import com.jerry.mekmm.common.recipe.MoreMachineRecipeType;
import com.jerry.mekmm.common.recipe.lookup.cache.MoreMachineInputRecipeCache.TripleItem;
import com.jerry.mekmm.common.tile.factory.TileEntityReplicatingFactory;
import com.jerry.mekmm.common.tile.machine.TileEntityPlantingStation;
import com.jerry.mekmm.common.tile.machine.TileEntityReplicator;
import com.jerry.mekmm.common.util.MoreMachineEnumUtils;
import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.api.tier.IEMExtraTier;
import io.github.masyumero.emextras.common.block.attribute.EMExtraAttributeTier;
import io.github.masyumero.emextras.common.integration.mekmm.block.prefab.EMExtraMoreMachineBlockFactoryMachine;
import io.github.masyumero.emextras.common.integration.mekmm.content.blocktype.EMExtraMoreMachineFactory;
import io.github.masyumero.emextras.common.integration.mekmm.item.block.machine.ItemBlockEMExtraMoreMachineFactory;
import io.github.masyumero.emextras.common.integration.mekmm.tile.factory.TileEntityEMExtraMoreMachineFactory;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import io.github.masyumero.emextras.common.util.EMExtraEnumUtils;
import mekanism.common.attachments.containers.ContainerType;
import mekanism.common.attachments.containers.chemical.ChemicalTanksBuilder;
import mekanism.common.attachments.containers.item.ItemSlotsBuilder;
import mekanism.common.recipe.MekanismRecipeType;
import mekanism.common.recipe.lookup.cache.InputRecipeCache;
import mekanism.common.registration.impl.BlockDeferredRegister;
import mekanism.common.registration.impl.BlockRegistryObject;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class EMExtraMoreMachineBlocks {

    private EMExtraMoreMachineBlocks() {}

    public static final BlockDeferredRegister MM_BLOCKS = new BlockDeferredRegister(EMExtras.MODID);

    private static final Table<EMExtraFactoryTier, MoreMachineFactoryType, BlockRegistryObject<EMExtraMoreMachineBlockFactoryMachine.BlockEMExtraMoreMachineFactory<?>, ItemBlockEMExtraMoreMachineFactory>> MM_FACTORIES = HashBasedTable.create();

    static {
        // factories
        for (EMExtraFactoryTier tier : EMExtraEnumUtils.EMEXTRA_FACTORY_TIERS) {
            for (MoreMachineFactoryType type : MoreMachineEnumUtils.MM_FACTORY_TYPES) {
                MM_FACTORIES.put(tier, type, registerMoreMachineFactory(EMExtraMoreMachineBlockTypes.getEMExtraMoreMachineFactory(tier, type)));
            }
        }
    }

    private static <TILE extends TileEntityEMExtraMoreMachineFactory<?>> BlockRegistryObject<EMExtraMoreMachineBlockFactoryMachine.BlockEMExtraMoreMachineFactory<?>, ItemBlockEMExtraMoreMachineFactory> registerMoreMachineFactory(EMExtraMoreMachineFactory<TILE> type) {
        EMExtraFactoryTier tier = (EMExtraFactoryTier) Objects.requireNonNull(type.get(EMExtraAttributeTier.class)).tier();
        BlockRegistryObject<EMExtraMoreMachineBlockFactoryMachine.BlockEMExtraMoreMachineFactory<?>, ItemBlockEMExtraMoreMachineFactory> factory = registerTieredBlock(tier, "_" + type.getMoreMachineFactoryType().getRegistryNameComponent() + "_factory", () -> new EMExtraMoreMachineBlockFactoryMachine.BlockEMExtraMoreMachineFactory<>(type), ItemBlockEMExtraMoreMachineFactory::new);
        factory.forItemHolder(holder -> {
            int processes = tier.processes;
            Predicate<ItemStack> recipeInputPredicate = switch (type.getMoreMachineFactoryType()) {
                case RECYCLING -> s -> MoreMachineRecipeType.RECYCLING.getInputCache().containsInput(null, s);
                case PLANTING_STATION -> s -> MoreMachineRecipeType.PLANTING_STATION.getInputCache().containsInputA(null, s);
                case CNC_STAMPING -> s -> MoreMachineRecipeType.STAMPING.getInputCache().containsInputA(null, s);
                case CNC_LATHING -> s -> MoreMachineRecipeType.LATHING.getInputCache().containsInput(null, s);
                case CNC_ROLLING_MILL -> s -> MoreMachineRecipeType.ROLLING_MILL.getInputCache().containsInput(null, s);
                case PRESSING -> s -> MoreMachineRecipeType.PRESSING.getInputCache().containsInputA(null, s);
                case REPLICATING -> TileEntityReplicator::isValidItemInput;
            };
            switch (type.getMoreMachineFactoryType()) {
                case CNC_STAMPING -> holder.addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                        .addBasicFactorySlots(processes, recipeInputPredicate)
                        .addInput(MekanismRecipeType.COMBINING, InputRecipeCache.DoubleItem::containsInputB)
                        .addEnergy()
                        .build());
                case CNC_LATHING, CNC_ROLLING_MILL, RECYCLING -> holder.addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                        .addBasicFactorySlots(processes, recipeInputPredicate)
                        .addEnergy()
                        .build());
                case PLANTING_STATION -> holder
                        .addAttachmentOnlyContainers(ContainerType.CHEMICAL, () -> ChemicalTanksBuilder.builder()
                                .addBasic(TileEntityPlantingStation.MAX_GAS * processes * processes, switch (type.getMoreMachineFactoryType()) {
                                    case PLANTING_STATION -> MoreMachineRecipeType.PLANTING_STATION;
                                    default -> throw new IllegalStateException("Factory type doesn't have a known gas recipe.");
                                }, InputRecipeCache.ItemChemical::containsInputB)
                                .build())
                        .addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                                .addBasicFactorySlots(processes, recipeInputPredicate, true)
                                .addChemicalFillOrConvertSlot(1)
                                .addEnergy()
                                .build());
                case PRESSING -> holder.addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                        .addBasicFactorySlots(processes, recipeInputPredicate)
                        .addInput(MoreMachineRecipeType.PRESSING, TripleItem::containsInputB)
                        .addInput(MoreMachineRecipeType.PRESSING, TripleItem::containsInputC)
                        .addEnergy()
                        .build());
                case REPLICATING -> holder.addAttachmentOnlyContainers(ContainerType.CHEMICAL, () -> ChemicalTanksBuilder.builder()
                        .addBasic(TileEntityReplicatingFactory.MAX_GAS * processes * processes, TileEntityReplicatingFactory::isValidChemicalInput)
                        .build()).addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                        .addBasicFactorySlots(processes, recipeInputPredicate)
                        .addChemicalFillOrConvertSlot(0)
                        .addEnergy()
                        .build());
            }
        });
        return factory;
    }

    private static <BLOCK extends Block, ITEM extends BlockItem> BlockRegistryObject<BLOCK, ITEM> registerTieredBlock(IEMExtraTier tier, String suffix,
                                                                                                                      Supplier<? extends BLOCK> blockSupplier, BiFunction<BLOCK, Item.Properties, ITEM> itemCreator) {
        return MM_BLOCKS.register(tier.getEMExtraTier().getLowerName() + suffix, blockSupplier, itemCreator);
    }

    /**
     * Retrieves a Factory with a defined tier and recipe type.
     *
     * @param tier - tier to add to the Factory
     * @param type - recipe type to add to the Factory
     * @return factory with defined tier and recipe type
     */
    public static BlockRegistryObject<EMExtraMoreMachineBlockFactoryMachine.BlockEMExtraMoreMachineFactory<?>, ItemBlockEMExtraMoreMachineFactory> getEMExtraMoreMachineFactory(@NotNull EMExtraFactoryTier tier, @NotNull MoreMachineFactoryType type) {
        return MM_FACTORIES.get(tier, type);
    }

    @SuppressWarnings("unchecked")
    public static BlockRegistryObject<EMExtraMoreMachineBlockFactoryMachine.BlockEMExtraMoreMachineFactory<?>, ItemBlockEMExtraMoreMachineFactory>[] getEMExtraMoreMachineFactoryBlocks() {
        return MM_FACTORIES.values().toArray(new BlockRegistryObject[0]);
    }

    public static void register(IEventBus modEventBus) {
        MM_BLOCKS.register(modEventBus);
    }
}
