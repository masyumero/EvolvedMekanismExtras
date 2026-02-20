package io.github.masyumero.emextras.common.registry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jerry.mekextras.api.tier.IAdvancedTier;
import com.jerry.mekextras.common.block.attribute.ExtraAttributeTier;
import com.jerry.mekextras.common.block.prefab.BlockExtraFactoryMachine;
import com.jerry.mekextras.common.content.blocktype.ExtraFactory;
import com.jerry.mekextras.common.item.block.machine.ItemBlockExtraFactory;
import com.jerry.mekextras.common.tier.ExtraFactoryTier;
import com.jerry.mekextras.common.tile.factory.TileEntityExtraFactory;
import com.jerry.mekextras.common.util.ExtraEnumUtils;
import fr.iglee42.evolvedmekanism.interfaces.EMInputRecipeCache;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import fr.iglee42.evolvedmekanism.registries.EMRecipeType;
import io.github.masyumero.emextras.api.tier.IEMExtraTier;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.common.block.attribute.EMExtraAttributeTier;
import io.github.masyumero.emextras.common.block.prefab.BlockEMExtraFactoryMachine;
import io.github.masyumero.emextras.common.content.blocktype.EMExtraFactory;
import io.github.masyumero.emextras.common.content.blocktype.EMExtraFactoryType;
import io.github.masyumero.emextras.common.item.block.machine.ItemBlockEMExtraFactory;
import io.github.masyumero.emextras.common.tile.factory.TileEntityEMExtraFactory;
import io.github.masyumero.emextras.common.util.EMExtraEnumUtils;
import mekanism.common.attachments.containers.ContainerType;
import mekanism.common.attachments.containers.chemical.ChemicalTanksBuilder;
import mekanism.common.attachments.containers.item.ItemSlotsBuilder;
import mekanism.common.content.blocktype.FactoryType;
import mekanism.common.recipe.MekanismRecipeType;
import mekanism.common.recipe.lookup.cache.InputRecipeCache;
import mekanism.common.registration.impl.BlockDeferredRegister;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.tile.machine.TileEntityMetallurgicInfuser;
import mekanism.common.tile.prefab.TileEntityAdvancedElectricMachine;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class EMExtraBlocks {
    public static final BlockDeferredRegister BLOCK = new BlockDeferredRegister(EMExtras.MODID);

    private static final Table<EMExtraFactoryTier, EMExtraFactoryType, BlockRegistryObject<BlockEMExtraFactoryMachine.BlockEMExtraFactory<?>, ItemBlockEMExtraFactory>> FACTORIES = HashBasedTable.create();

    private static final Table<ExtraFactoryTier, FactoryType, BlockRegistryObject<BlockExtraFactoryMachine.BlockExtraFactory<?>, ItemBlockExtraFactory>> EXTRA_FACTORIES = HashBasedTable.create();

    static {
        // factories
        for (EMExtraFactoryTier tier : EMExtraEnumUtils.EMEXTRA_FACTORY_TIERS) {
            for (EMExtraFactoryType type : EMExtraEnumUtils.EMEXTRA_FACTORY_TYPES) {
                FACTORIES.put(tier, type, registerEMExtraFactory(EMExtraBlockTypes.getEMExtraFactory(tier, type)));
            }
        }
        for (ExtraFactoryTier tier : ExtraEnumUtils.EXTRA_FACTORY_TIERS) {
            EXTRA_FACTORIES.put(tier, EMFactoryType.ALLOYING, registerExtraFactory(EMExtraBlockTypes.getExtraFactory(tier, EMFactoryType.ALLOYING)));
        }
    }

    private static <BLOCK extends Block, ITEM extends BlockItem> BlockRegistryObject<BLOCK, ITEM> registerTieredBlock(IAdvancedTier tier, String suffix,
                                                                                                                      Supplier<? extends BLOCK> blockSupplier, BiFunction<BLOCK, Item.Properties, ITEM> itemCreator) {
        return BLOCK.register(tier.getAdvanceTier().getLowerName() + suffix, blockSupplier, itemCreator);
    }

    private static <BLOCK extends Block, ITEM extends BlockItem> BlockRegistryObject<BLOCK, ITEM> registerTieredBlock(IEMExtraTier tier, String suffix,
                                                                                                                      Supplier<? extends BLOCK> blockSupplier, BiFunction<BLOCK, Item.Properties, ITEM> itemCreator) {
        return BLOCK.register(tier.getEMExtraTier().getLowerName() + suffix, blockSupplier, itemCreator);
    }

    private static <TILE extends TileEntityExtraFactory<?>> BlockRegistryObject<BlockExtraFactoryMachine.BlockExtraFactory<?>, ItemBlockExtraFactory> registerExtraFactory(ExtraFactory<TILE> type) {
        ExtraFactoryTier tier = (ExtraFactoryTier) type.get(ExtraAttributeTier.class).tier();
        BlockRegistryObject<BlockExtraFactoryMachine.BlockExtraFactory<?>, ItemBlockExtraFactory> factory = registerTieredBlock(tier, "_" + type.getFactoryType().getRegistryNameComponent() + "_factory", () -> new BlockExtraFactoryMachine.BlockExtraFactory<>(type), ItemBlockExtraFactory::new);
        factory.forItemHolder(holder -> {
            int processes = tier.processes;
            Predicate<ItemStack> recipeInputPredicate = type.getFactoryType().equals(EMFactoryType.ALLOYING) ? s -> EMRecipeType.ALLOYING.getInputCache().containsInputA(null, s) : switch (type.getFactoryType()) {
                case SMELTING -> s -> MekanismRecipeType.SMELTING.getInputCache().containsInput(null, s);
                case ENRICHING -> s -> MekanismRecipeType.ENRICHING.getInputCache().containsInput(null, s);
                case CRUSHING -> s -> MekanismRecipeType.CRUSHING.getInputCache().containsInput(null, s);
                case COMPRESSING -> s -> MekanismRecipeType.COMPRESSING.getInputCache().containsInputA(null, s);
                case COMBINING -> s -> MekanismRecipeType.COMBINING.getInputCache().containsInputA(null, s);
                case PURIFYING -> s -> MekanismRecipeType.PURIFYING.getInputCache().containsInputA(null, s);
                case INJECTING -> s -> MekanismRecipeType.INJECTING.getInputCache().containsInputA(null, s);
                case INFUSING -> s -> MekanismRecipeType.METALLURGIC_INFUSING.getInputCache().containsInputA(null, s);
                case SAWING -> s -> MekanismRecipeType.SAWING.getInputCache().containsInput(null, s);
            };
            if (type.getFactoryType().equals(EMFactoryType.ALLOYING)) {
                holder.addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                        .addBasicFactorySlots(processes, recipeInputPredicate)
                        .addInput(EMRecipeType.ALLOYING, EMInputRecipeCache.TripleItem::containsInputB)
                        .addInput(EMRecipeType.ALLOYING, EMInputRecipeCache.TripleItem::containsInputC)
                        .addEnergy()
                        .build());
            }
            switch (type.getFactoryType()) {
                case SMELTING, ENRICHING, CRUSHING -> holder.addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                        .addBasicFactorySlots(processes, recipeInputPredicate)
                        .addEnergy()
                        .build());
                case COMPRESSING, INJECTING, PURIFYING -> holder
                        .addAttachmentOnlyContainers(ContainerType.CHEMICAL, () -> ChemicalTanksBuilder.builder()
                                .addBasic(TileEntityAdvancedElectricMachine.MAX_GAS * processes * processes, switch (type.getFactoryType()) {
                                    case COMPRESSING -> MekanismRecipeType.COMPRESSING;
                                    case INJECTING -> MekanismRecipeType.INJECTING;
                                    case PURIFYING -> MekanismRecipeType.PURIFYING;
                                    default -> throw new IllegalStateException("Factory type doesn't have a known gas recipe");
                                }, InputRecipeCache.ItemChemical::containsInputB)
                                .build())
                        .addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                                .addBasicFactorySlots(processes, recipeInputPredicate)
                                .addChemicalFillOrConvertSlot(0)
                                .addEnergy()
                                .build());
                case COMBINING -> holder.addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                        .addBasicFactorySlots(processes, recipeInputPredicate)
                        .addInput(MekanismRecipeType.COMBINING, InputRecipeCache.DoubleItem::containsInputB)
                        .addEnergy()
                        .build());
                case INFUSING -> holder
                        .addAttachmentOnlyContainers(ContainerType.CHEMICAL, () -> ChemicalTanksBuilder.builder()
                                .addBasic(TileEntityMetallurgicInfuser.MAX_INFUSE * processes * processes, MekanismRecipeType.METALLURGIC_INFUSING, InputRecipeCache.ItemChemical::containsInputB)
                                .build())
                        .addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                                .addBasicFactorySlots(processes, recipeInputPredicate)
                                .addInfusionFillOrConvertSlot(0)
                                .addEnergy()
                                .build());
                case SAWING -> holder.addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                        .addBasicFactorySlots(processes, recipeInputPredicate, true)
                        .addEnergy()
                        .build());
            }
        });
        return factory;
    }

    private static <TILE extends TileEntityEMExtraFactory<?>> BlockRegistryObject<BlockEMExtraFactoryMachine.BlockEMExtraFactory<?>, ItemBlockEMExtraFactory> registerEMExtraFactory(EMExtraFactory<TILE> type) {
        EMExtraFactoryTier tier = (EMExtraFactoryTier) type.get(EMExtraAttributeTier.class).tier();
        BlockRegistryObject<BlockEMExtraFactoryMachine.BlockEMExtraFactory<?>, ItemBlockEMExtraFactory> factory = registerTieredBlock(tier, "_" + type.getFactoryType().getRegistryNameComponent() + "_factory", () -> new BlockEMExtraFactoryMachine.BlockEMExtraFactory<>(type), ItemBlockEMExtraFactory::new);
        factory.forItemHolder(holder -> {
            int processes = tier.processes;
            Predicate<ItemStack> recipeInputPredicate = switch (type.getFactoryType()) {
                case ALLOYING -> s -> EMRecipeType.ALLOYING.getInputCache().containsInputA(null, s);
                case SMELTING -> s -> MekanismRecipeType.SMELTING.getInputCache().containsInput(null, s);
                case ENRICHING -> s -> MekanismRecipeType.ENRICHING.getInputCache().containsInput(null, s);
                case CRUSHING -> s -> MekanismRecipeType.CRUSHING.getInputCache().containsInput(null, s);
                case COMPRESSING -> s -> MekanismRecipeType.COMPRESSING.getInputCache().containsInputA(null, s);
                case COMBINING -> s -> MekanismRecipeType.COMBINING.getInputCache().containsInputA(null, s);
                case PURIFYING -> s -> MekanismRecipeType.PURIFYING.getInputCache().containsInputA(null, s);
                case INJECTING -> s -> MekanismRecipeType.INJECTING.getInputCache().containsInputA(null, s);
                case INFUSING -> s -> MekanismRecipeType.METALLURGIC_INFUSING.getInputCache().containsInputA(null, s);
                case SAWING -> s -> MekanismRecipeType.SAWING.getInputCache().containsInput(null, s);
            };
            switch (type.getFactoryType()) {
                case ALLOYING -> holder.addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                        .addBasicFactorySlots(processes, recipeInputPredicate)
                        .addInput(EMRecipeType.ALLOYING, EMInputRecipeCache.TripleItem::containsInputB)
                        .addInput(EMRecipeType.ALLOYING, EMInputRecipeCache.TripleItem::containsInputC)
                        .addEnergy()
                        .build());
                case SMELTING, ENRICHING, CRUSHING -> holder.addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                        .addBasicFactorySlots(processes, recipeInputPredicate)
                        .addEnergy()
                        .build());
                case COMPRESSING, INJECTING, PURIFYING -> holder
                        .addAttachmentOnlyContainers(ContainerType.CHEMICAL, () -> ChemicalTanksBuilder.builder()
                                .addBasic(TileEntityAdvancedElectricMachine.MAX_GAS * processes * processes, switch (type.getFactoryType()) {
                                    case COMPRESSING -> MekanismRecipeType.COMPRESSING;
                                    case INJECTING -> MekanismRecipeType.INJECTING;
                                    case PURIFYING -> MekanismRecipeType.PURIFYING;
                                    default -> throw new IllegalStateException("Factory type doesn't have a known gas recipe");
                                }, InputRecipeCache.ItemChemical::containsInputB)
                                .build())
                        .addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                                .addBasicFactorySlots(processes, recipeInputPredicate)
                                .addChemicalFillOrConvertSlot(0)
                                .addEnergy()
                                .build());
                case COMBINING -> holder.addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                        .addBasicFactorySlots(processes, recipeInputPredicate)
                        .addInput(MekanismRecipeType.COMBINING, InputRecipeCache.DoubleItem::containsInputB)
                        .addEnergy()
                        .build());
                case INFUSING -> holder
                        .addAttachmentOnlyContainers(ContainerType.CHEMICAL, () -> ChemicalTanksBuilder.builder()
                                .addBasic(TileEntityMetallurgicInfuser.MAX_INFUSE * processes * processes, MekanismRecipeType.METALLURGIC_INFUSING, InputRecipeCache.ItemChemical::containsInputB)
                                .build())
                        .addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                                .addBasicFactorySlots(processes, recipeInputPredicate)
                                .addInfusionFillOrConvertSlot(0)
                                .addEnergy()
                                .build());
                case SAWING -> holder.addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                        .addBasicFactorySlots(processes, recipeInputPredicate, true)
                        .addEnergy()
                        .build());
            }
        });
        return factory;
    }

    public static BlockRegistryObject<BlockEMExtraFactoryMachine.BlockEMExtraFactory<?>, ItemBlockEMExtraFactory>  getEMExtraFactory(@NotNull EMExtraFactoryTier tier, @NotNull EMExtraFactoryType type) {
        return FACTORIES.get(tier, type);
    }

    public static BlockRegistryObject<BlockExtraFactoryMachine.BlockExtraFactory<?>, ItemBlockExtraFactory> getExtraFactory(@NotNull ExtraFactoryTier tier, @NotNull FactoryType type) {
        return EXTRA_FACTORIES.get(tier, type);
    }

    public static void register(IEventBus modEventBus) {
        BLOCK.register(modEventBus);
    }
}
