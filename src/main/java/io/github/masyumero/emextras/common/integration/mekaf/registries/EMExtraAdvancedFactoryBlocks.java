package io.github.masyumero.emextras.common.integration.mekaf.registries;

import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.api.tier.IEMExtraTier;
import io.github.masyumero.emextras.common.block.attribute.EMExtraAttributeTier;
import io.github.masyumero.emextras.common.block.prefab.BlockEMExtraAdvancedFactoryMachine.BlockEMExtraAdvancedFactory;
import io.github.masyumero.emextras.common.integration.mekaf.content.blocktype.EMExtraAdvancedFactory;
import io.github.masyumero.emextras.common.integration.mekaf.item.block.machine.ItemBlockEMExtraAdvancedFactory;
import io.github.masyumero.emextras.common.integration.mekaf.tile.factory.TileEntityEMExtraAdvancedBase;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import io.github.masyumero.emextras.common.util.EMExtraEnumUtils;

import mekanism.api.chemical.ChemicalStack;
import mekanism.common.attachments.containers.ContainerType;
import mekanism.common.attachments.containers.fluid.FluidTanksBuilder;
import mekanism.common.attachments.containers.item.ItemSlotsBuilder;
import mekanism.common.recipe.MekanismRecipeType;
import mekanism.common.recipe.lookup.cache.InputRecipeCache;
import mekanism.common.registration.impl.BlockDeferredRegister;
import mekanism.common.registration.impl.BlockRegistryObject;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jerry.mekaf.common.attachments.containers.chemical.AFChemicalTanksBuilder;
import com.jerry.mekaf.common.attachments.containers.item.AFItemSlotsBuilder;
import com.jerry.mekaf.common.content.blocktype.AdvancedFactoryType;
import com.jerry.mekaf.common.tile.factory.TileEntityAdvancedFactoryBase;
import com.jerry.mekaf.common.tile.factory.TileEntityLiquifyingFactory;
import com.jerry.mekmm.common.util.MoreMachineEnumUtils;
import net.neoforged.bus.api.IEventBus;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class EMExtraAdvancedFactoryBlocks {

    private EMExtraAdvancedFactoryBlocks() {}

    public static final BlockDeferredRegister AF_BLOCKS = new BlockDeferredRegister(EMExtras.MODID);

    private static final Table<EMExtraFactoryTier, AdvancedFactoryType, BlockRegistryObject<BlockEMExtraAdvancedFactory<?>, ItemBlockEMExtraAdvancedFactory>> AF_FACTORIES = HashBasedTable.create();

    static {
        // factories
        for (EMExtraFactoryTier tier : EMExtraEnumUtils.EMEXTRA_FACTORY_TIERS) {
            for (AdvancedFactoryType type : MoreMachineEnumUtils.ADVANCED_FACTORY_TYPES) {
                AF_FACTORIES.put(tier, type, registerAdvancedFactory(EMExtraAdvancedFactoryBlockTypes.getEMExtraAdvancedFactory(tier, type)));
            }
        }
    }

    private static <TILE extends TileEntityEMExtraAdvancedBase<?>> BlockRegistryObject<BlockEMExtraAdvancedFactory<?>, ItemBlockEMExtraAdvancedFactory> registerAdvancedFactory(EMExtraAdvancedFactory<TILE> type) {
        EMExtraFactoryTier tier = (EMExtraFactoryTier) Objects.requireNonNull(type.get(EMExtraAttributeTier.class)).tier();
        BlockRegistryObject<BlockEMExtraAdvancedFactory<?>, ItemBlockEMExtraAdvancedFactory> factory = registerTieredBlock(tier, "_" + type.getAdvancedFactoryType().getRegistryNameComponent() + "_factory", () -> new BlockEMExtraAdvancedFactory<>(type), ItemBlockEMExtraAdvancedFactory::new);
        factory.forItemHolder(holder -> {
            int processes = tier.processes;
            Predicate<ItemStack> recipeItemInputPredicate = switch (type.getAdvancedFactoryType()) {
                case OXIDIZING -> s -> MekanismRecipeType.OXIDIZING.getInputCache().containsInput(null, s);
                case DISSOLVING -> s -> MekanismRecipeType.DISSOLUTION.getInputCache().containsInputA(null, s);
                case PRESSURISED_REACTING -> s -> MekanismRecipeType.REACTION.getInputCache().containsInputA(null, s);
                case LIQUIFYING -> TileEntityLiquifyingFactory::isValidInputStatic;
                default -> null;
            };
            Predicate<ChemicalStack> recipeChemicalInputPredicate = switch (type.getAdvancedFactoryType()) {
                case CHEMICAL_INFUSING -> s -> MekanismRecipeType.CHEMICAL_INFUSING.getInputCache().containsInput(null, s);
                case DISSOLVING -> s -> MekanismRecipeType.DISSOLUTION.getInputCache().containsInputB(null, s);
                case WASHING -> s -> MekanismRecipeType.WASHING.getInputCache().containsInputB(null, s);
                case CRYSTALLIZING -> s -> MekanismRecipeType.CRYSTALLIZING.getInputCache().containsInput(null, s);
                case PRESSURISED_REACTING -> s -> MekanismRecipeType.REACTION.getInputCache().containsInputC(null, s);
                case CENTRIFUGING -> s -> MekanismRecipeType.CENTRIFUGING.getInputCache().containsInput(null, s);
                default -> null;
            };
            switch (type.getAdvancedFactoryType()) {
                // 没问题
                case OXIDIZING -> holder
                        .addAttachmentOnlyContainers(ContainerType.CHEMICAL, () -> AFChemicalTanksBuilder.builder()
                                // 化学品输出（多个）
                                .addOutputFactoryTank(processes, TileEntityAdvancedFactoryBase.MAX_CHEMICAL * processes * processes)
                                .build())
                        .addAttachmentOnlyContainers(ContainerType.ITEM, () -> AFItemSlotsBuilder.builder()
                                // 物品输入（多个）
                                .addInputFactorySlots(processes, recipeItemInputPredicate)
                                .addEnergy()
                                .build());
                // 输入储罐错位，输出储罐气体消失
                case DISSOLVING -> holder
                        .addAttachmentOnlyContainers(ContainerType.CHEMICAL, () -> AFChemicalTanksBuilder.builder()
                                // 化学品输入
                                .addBasic(TileEntityAdvancedFactoryBase.MAX_CHEMICAL * processes * processes, recipeChemicalInputPredicate)
                                // 化学品输出（多个）
                                .addOutputFactoryTank(processes, TileEntityAdvancedFactoryBase.MAX_CHEMICAL * processes * processes)
                                .build())
                        .addAttachmentOnlyContainers(ContainerType.ITEM, () -> AFItemSlotsBuilder.builder()
                                .addInputFactorySlots(processes, recipeItemInputPredicate)
                                .addChemicalFillOrConvertSlot(0)
                                .addEnergy()
                                .build());
                // 输出储罐错位
                case CHEMICAL_INFUSING -> holder
                        .addAttachmentOnlyContainers(ContainerType.CHEMICAL, () -> AFChemicalTanksBuilder.builder()
                                // Left
                                .addInputFactoryTank(processes, TileEntityAdvancedFactoryBase.MAX_CHEMICAL * processes, recipeChemicalInputPredicate)
                                .addOutputFactoryTank(processes, TileEntityAdvancedFactoryBase.MAX_CHEMICAL * processes)
                                // Right
                                .addBasic(TileEntityAdvancedFactoryBase.MAX_CHEMICAL * processes, recipeChemicalInputPredicate)
                                .build())
                        .addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                                // 将右侧的储罐槽保留
                                .addChemicalFillOrConvertSlot(1)
                                .addEnergy()
                                .build());
                // 没问题
                case WASHING -> holder
                        .addAttachmentOnlyContainers(ContainerType.FLUID, () -> FluidTanksBuilder.builder()
                                .addBasic(TileEntityAdvancedFactoryBase.MAX_FLUID * processes, MekanismRecipeType.WASHING, InputRecipeCache.FluidChemical::containsInputA)
                                .build())
                        .addAttachmentOnlyContainers(ContainerType.CHEMICAL, () -> AFChemicalTanksBuilder.builder()
                                .addInputFactoryTank(processes, TileEntityAdvancedFactoryBase.MAX_CHEMICAL * processes, recipeChemicalInputPredicate)
                                .addOutputFactoryTank(processes, TileEntityAdvancedFactoryBase.MAX_CHEMICAL * processes)
                                .build())
                        .addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                                .addFluidFillSlot(0)
                                .addOutput()
                                .addEnergy()
                                .build());
                // 使用工作台合成升级机器导致能量槽错位（mek原生bug）
                case PRESSURISED_REACTING -> holder
                        .addAttachmentOnlyContainers(ContainerType.FLUID, () -> FluidTanksBuilder.builder()
                                .addBasic(TileEntityAdvancedFactoryBase.MAX_FLUID * processes * processes, MekanismRecipeType.REACTION, InputRecipeCache.ItemFluidChemical::containsInputB)
                                .build())
                        .addAttachmentOnlyContainers(ContainerType.CHEMICAL, () -> AFChemicalTanksBuilder.builder()
                                .addBasic(TileEntityAdvancedFactoryBase.MAX_CHEMICAL * processes * processes, recipeChemicalInputPredicate)
                                .addBasic(TileEntityAdvancedFactoryBase.MAX_CHEMICAL * processes * processes)
                                .build())
                        .addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                                .addBasicFactorySlots(processes, recipeItemInputPredicate)
                                .addEnergy()
                                .build());
                // 使用工作台合成升级机器导致能量槽错位（mek原生bug）
                case CRYSTALLIZING -> holder.addAttachmentOnlyContainers(ContainerType.CHEMICAL, () -> AFChemicalTanksBuilder.builder()
                        .addInputFactoryTank(processes, TileEntityAdvancedFactoryBase.MAX_CHEMICAL * processes, recipeChemicalInputPredicate)
                        .build()).addAttachmentOnlyContainers(ContainerType.ITEM, () -> AFItemSlotsBuilder.builder()
                        .addOutputFactorySlots(tier.processes)
                        .addEnergy()
                        .build());
                // 没问题
                case CENTRIFUGING -> holder
                        .addAttachmentOnlyContainers(ContainerType.CHEMICAL, () -> AFChemicalTanksBuilder.builder()
                                .addInputFactoryTank(processes, TileEntityAdvancedFactoryBase.MAX_CHEMICAL * processes, recipeChemicalInputPredicate)
                                .addOutputFactoryTank(processes, TileEntityAdvancedFactoryBase.MAX_CHEMICAL * processes)
                                .build())
                        .addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                                .addEnergy()
                                .build());
                // 偶现升级后槽位不可以的情况
                case LIQUIFYING -> holder
                        .addAttachmentOnlyContainers(ContainerType.FLUID, () -> FluidTanksBuilder.builder()
                                .addBasic(TileEntityLiquifyingFactory.MAX_FLUID * processes * processes)
                                .build())
                        .addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                                .addBasicFactorySlots(processes, recipeItemInputPredicate)
                                .addEnergy()
                                .build());
            }
        });
        return factory;
    }

    private static <BLOCK extends Block, ITEM extends BlockItem> BlockRegistryObject<BLOCK, ITEM> registerTieredBlock(IEMExtraTier tier, String suffix,
                                                                                                                      Supplier<? extends BLOCK> blockSupplier, BiFunction<BLOCK, Item.Properties, ITEM> itemCreator) {
        return AF_BLOCKS.register(tier.getEMExtraTier().getLowerName() + suffix, blockSupplier, itemCreator);
    }

    /**
     * Retrieves a Factory with a defined tier and recipe type.
     *
     * @param tier - tier to add to the Factory
     * @param type - recipe type to add to the Factory
     * @return factory with defined tier and recipe type
     */
    public static BlockRegistryObject<BlockEMExtraAdvancedFactory<?>, ItemBlockEMExtraAdvancedFactory> getEMExtraAdvancedFactory(@NotNull EMExtraFactoryTier tier, @NotNull AdvancedFactoryType type) {
        return AF_FACTORIES.get(tier, type);
    }

    @SuppressWarnings("unchecked")
    public static BlockRegistryObject<BlockEMExtraAdvancedFactory<?>, ItemBlockEMExtraAdvancedFactory>[] getEMExtraAdvancedFactoryBlocks() {
        return AF_FACTORIES.values().toArray(new BlockRegistryObject[0]);
    }

    public static void register(IEventBus modEventBus) {
        AF_BLOCKS.register(modEventBus);
    }
}