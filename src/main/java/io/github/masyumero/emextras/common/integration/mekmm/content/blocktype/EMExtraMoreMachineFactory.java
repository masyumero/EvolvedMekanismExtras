package io.github.masyumero.emextras.common.integration.mekmm.content.blocktype;

import com.jerry.mekmm.common.block.attribute.MoreMachineAttributeFactoryType;
import com.jerry.mekmm.common.content.blocktype.MoreMachineBlockShapes;
import com.jerry.mekmm.common.content.blocktype.MoreMachineFactoryType;

import io.github.masyumero.emextras.common.block.attribute.EMExtraAttributeTier;
import io.github.masyumero.emextras.common.block.attribute.EMExtraAttributeUpgradeable;
import io.github.masyumero.emextras.common.content.blocktype.EMExtraMachine;
import io.github.masyumero.emextras.common.integration.mekmm.registries.EMExtraMoreMachineBlockTypes;
import io.github.masyumero.emextras.common.integration.mekmm.registries.EMExtraMoreMachineBlocks;
import io.github.masyumero.emextras.common.integration.mekmm.registries.EMExtraMoreMachineContainerTypes;
import io.github.masyumero.emextras.common.integration.mekmm.tile.factory.TileEntityEMExtraMoreMachineFactory;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import io.github.masyumero.emextras.common.util.EMExtraEnumUtils;

import mekanism.api.math.MathUtils;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.*;
import mekanism.common.content.blocktype.BlockTypeTile;
import mekanism.common.inventory.container.MekanismContainer;
import mekanism.common.lib.math.Pos3D;
import mekanism.common.registration.impl.ContainerTypeRegistryObject;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import net.minecraft.core.particles.ParticleTypes;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class EMExtraMoreMachineFactory<TILE extends TileEntityEMExtraMoreMachineFactory<?>> extends EMExtraMachine.EMExtraFactoryMachine<TILE> {

    private final EMExtraMachine.EMExtraFactoryMachine<?> origMachine;

    public EMExtraMoreMachineFactory(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar, Supplier<ContainerTypeRegistryObject<? extends MekanismContainer>> containerRegistrar,
                                     EMExtraMachine.EMExtraFactoryMachine<?> origMachine, EMExtraFactoryTier tier) {
        super(tileEntityRegistrar, MekanismLang.DESCRIPTION_FACTORY, origMachine.getMoreMachineFactoryType());
        this.origMachine = origMachine;
        setMachineData(tier);
        add(new AttributeGui(containerRegistrar, null), new EMExtraAttributeTier<>(tier));

        if (tier.ordinal() < EMExtraEnumUtils.EMEXTRA_FACTORY_TIERS.length - 1) {
            add(new EMExtraAttributeUpgradeable(() -> EMExtraMoreMachineBlocks.getEMExtraMoreMachineFactory(EMExtraEnumUtils.EMEXTRA_FACTORY_TIERS[tier.ordinal() + 1], origMachine.getMoreMachineFactoryType())));
        }
    }

    private void setMachineData(EMExtraFactoryTier tier) {
        setFrom(origMachine, AttributeSound.class, MoreMachineAttributeFactoryType.class, AttributeUpgradeSupport.class);
        AttributeEnergy origEnergy = origMachine.get(AttributeEnergy.class);
        if (origEnergy != null) {
            add(new AttributeEnergy(origEnergy::getUsage, () -> MathUtils.clampToLong(Math.max(origEnergy.getConfigStorage(), origEnergy.getUsage()) * tier.processes)));
        }
    }

    public static class EMExtraMoreMachineFactoryBuilder<FACTORY extends EMExtraMoreMachineFactory<TILE>, TILE extends TileEntityEMExtraMoreMachineFactory<?>, T extends EMExtraMachine.EMExtraMachineBuilder<FACTORY, TILE, T>>
            extends BlockTypeTile.BlockTileBuilder<FACTORY, TILE, T> {

        protected EMExtraMoreMachineFactoryBuilder(FACTORY holder) {
            super(holder);
        }

        @SuppressWarnings("unchecked")
        public static <TILE extends TileEntityEMExtraMoreMachineFactory<?>> EMExtraMoreMachineFactoryBuilder<EMExtraMoreMachineFactory<TILE>, TILE, ?> createMoreMachineFactory(Supplier<?> tileEntityRegistrar, MoreMachineFactoryType type,
                                                                                                                                                                              EMExtraFactoryTier tier) {
            // this is dirty but unfortunately necessary for things to play right
            EMExtraMoreMachineFactoryBuilder<EMExtraMoreMachineFactory<TILE>, TILE, ?> builder = getEMExtraMoreMachineFactoryTILEMoreMachineFactoryBuilder((Supplier<TileEntityTypeRegistryObject<TILE>>) tileEntityRegistrar, type, tier);
            builder.withCustomShape(MoreMachineBlockShapes.getShape(type));
            builder.with(switch (type) {
                case RECYCLING, CNC_STAMPING, CNC_LATHING, CNC_ROLLING_MILL, PRESSING -> AttributeSideConfig.ELECTRIC_MACHINE;
                case PLANTING_STATION, REPLICATING -> AttributeSideConfig.ADVANCED_ELECTRIC_MACHINE;
            });
            // 如果有Bounding属性就添加，但或许会有更复杂的形状
            if (getBaseMachine(type).has(AttributeHasBounding.class)) {
                builder.with(AttributeHasBounding.ABOVE_ONLY);
            }
            builder.replace(new AttributeParticleFX().addDense(ParticleTypes.SMOKE, 5, rand -> new Pos3D(
                    rand.nextFloat() * 0.7F - 0.3F,
                    rand.nextFloat() * 0.1F + 0.7F,
                    rand.nextFloat() * 0.7F - 0.3F)));
            return builder;
        }

        private static <TILE extends TileEntityEMExtraMoreMachineFactory<?>> @NotNull EMExtraMoreMachineFactoryBuilder<EMExtraMoreMachineFactory<TILE>, TILE, ?> getEMExtraMoreMachineFactoryTILEMoreMachineFactoryBuilder(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar, MoreMachineFactoryType type, EMExtraFactoryTier tier) {
            EMExtraMoreMachineFactoryBuilder<EMExtraMoreMachineFactory<TILE>, TILE, ?> builder = new EMExtraMoreMachineFactoryBuilder<>(new EMExtraMoreMachineFactory<>(tileEntityRegistrar,
                    () -> EMExtraMoreMachineContainerTypes.MORE_MACHINE_FACTORY,
                    getBaseMachine(type),
                    tier));
            builder.withComputerSupport(tier.getEMExtraTier().getLowerName() + type.getRegistryNameComponentCapitalized() + "Factory");
            return builder;
        }

        private static EMExtraMachine.EMExtraFactoryMachine<?> getBaseMachine(MoreMachineFactoryType type) {
            return switch (type) {
                case RECYCLING -> EMExtraMoreMachineBlockTypes.RECYCLER;
                case PLANTING_STATION -> EMExtraMoreMachineBlockTypes.PLANTING_STATION;
                case CNC_STAMPING -> EMExtraMoreMachineBlockTypes.CNC_STAMPER;
                case CNC_LATHING -> EMExtraMoreMachineBlockTypes.CNC_LATHE;
                case CNC_ROLLING_MILL -> EMExtraMoreMachineBlockTypes.CNC_ROLLING_MILL;
                case PRESSING -> EMExtraMoreMachineBlockTypes.PRESSER;
                case REPLICATING -> EMExtraMoreMachineBlockTypes.REPLICATOR;
            };
        }
    }
}