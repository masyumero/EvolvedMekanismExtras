package io.github.masyumero.emextras.common.integration.mekmm.content.blocktype;

import io.github.masyumero.emextras.common.block.attribute.EMExtraAttributeTier;
import io.github.masyumero.emextras.common.block.attribute.EMExtraAttributeUpgradeable;
import io.github.masyumero.emextras.common.content.blocktype.EMExtraMachine.EMExtraFactoryMachine;
import io.github.masyumero.emextras.common.integration.mekmm.registry.EMExtraMoreMachineBlockTypes;
import io.github.masyumero.emextras.common.integration.mekmm.registry.EMExtraMoreMachineBlocks;
import io.github.masyumero.emextras.common.integration.mekmm.registry.EMExtraMoreMachineContainerTypes;
import io.github.masyumero.emextras.common.integration.mekmm.tile.TileEntityEMExtraMoreMachineFactory;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import io.github.masyumero.emextras.common.util.EMExtraEnumUtils;

import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.*;
import mekanism.common.inventory.container.MekanismContainer;
import mekanism.common.lib.math.Pos3D;
import mekanism.common.registration.impl.ContainerTypeRegistryObject;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;

import net.minecraft.core.particles.ParticleTypes;

import com.jerry.mekmm.common.block.attribute.AttributeMoreMachineFactoryType;
import com.jerry.mekmm.common.content.blocktype.MoreMachineBlockShapes;
import com.jerry.mekmm.common.content.blocktype.MoreMachineFactoryType;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class EMExtraMoreMachineFactory<TILE extends TileEntityEMExtraMoreMachineFactory<?>> extends EMExtraFactoryMachine<TILE> {

    private final EMExtraFactoryMachine<?> origMachine;

    public EMExtraMoreMachineFactory(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar, Supplier<ContainerTypeRegistryObject<? extends MekanismContainer>> containerRegistrar,
                                   EMExtraFactoryMachine<?> origMachine, EMExtraFactoryTier tier) {
        super(tileEntityRegistrar, MekanismLang.DESCRIPTION_FACTORY, origMachine.getMoreMachineFactoryType());
        this.origMachine = origMachine;
        setMachineData(tier);
        add(new AttributeGui(containerRegistrar, null), new EMExtraAttributeTier<>(tier));

        // 添加升级后的方块
        if (tier.ordinal() < EMExtraEnumUtils.EMEXTRA_FACTORY_TIERS.length - 1) {
            add(new EMExtraAttributeUpgradeable(() -> EMExtraMoreMachineBlocks.getEMExtraMoreMachineFactory(EMExtraEnumUtils.EMEXTRA_FACTORY_TIERS[tier.ordinal() + 1], origMachine.getMoreMachineFactoryType())));
        }
    }

    private void setMachineData(EMExtraFactoryTier tier) {
        setFrom(origMachine, AttributeSound.class, AttributeMoreMachineFactoryType.class, AttributeUpgradeSupport.class);
        AttributeEnergy origEnergy = origMachine.get(AttributeEnergy.class);
        if (origEnergy != null) {
            add(new AttributeEnergy(origEnergy::getUsage, () -> origEnergy.getConfigStorage().multiply(0.5).max(origEnergy.getUsage()).multiply(tier.processes)));
        }
    }

    public static class EMExtraMoreMachineFactoryBuilder<FACTORY extends EMExtraMoreMachineFactory<TILE>, TILE extends TileEntityEMExtraMoreMachineFactory<?>, T extends EMExtraMachineBuilder<FACTORY, TILE, T>>
            extends BlockTileBuilder<FACTORY, TILE, T> {

        protected EMExtraMoreMachineFactoryBuilder(FACTORY holder) {
            super(holder);
        }

        @SuppressWarnings("unchecked")
        public static <TILE extends TileEntityEMExtraMoreMachineFactory<?>> EMExtraMoreMachineFactoryBuilder<EMExtraMoreMachineFactory<TILE>, TILE, ?> createEMExtraMoreMachineFactory(Supplier<?> tileEntityRegistrar, MoreMachineFactoryType type,
                                                                                                                                                                               EMExtraFactoryTier tier) {
            EMExtraMoreMachineFactoryBuilder<EMExtraMoreMachineFactory<TILE>, TILE, ?> builder = getEMExtraMoreMachineFactoryBuilder((Supplier<TileEntityTypeRegistryObject<TILE>>) tileEntityRegistrar, type, tier);
            builder.withCustomShape(MoreMachineBlockShapes.getShape(type));
            if (type == MoreMachineFactoryType.PLANTING) {
                builder.withBounding((pos, state, builderPos) -> builderPos.add(pos.above()));
            }
            builder.replace(new AttributeParticleFX().addDense(ParticleTypes.SMOKE, 5, rand -> new Pos3D(
                    rand.nextFloat() * 0.7F - 0.3F,
                    rand.nextFloat() * 0.1F + 0.7F,
                    rand.nextFloat() * 0.7F - 0.3F)));
            return builder;
        }
    }

    private static <TILE extends TileEntityEMExtraMoreMachineFactory<?>> @NotNull EMExtraMoreMachineFactoryBuilder<EMExtraMoreMachineFactory<TILE>, TILE, ?> getEMExtraMoreMachineFactoryBuilder(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar, MoreMachineFactoryType type, EMExtraFactoryTier tier) {
        EMExtraMoreMachineFactoryBuilder<EMExtraMoreMachineFactory<TILE>, TILE, ?> builder = new EMExtraMoreMachineFactoryBuilder<>(new EMExtraMoreMachineFactory<>(tileEntityRegistrar,
                () -> EMExtraMoreMachineContainerTypes.MORE_MACHINE_FACTORY,
                switch (type) {
                    case RECYCLING -> EMExtraMoreMachineBlockTypes.RECYCLER;
                    case PLANTING -> EMExtraMoreMachineBlockTypes.PLANTING_STATION;
                    case CNC_STAMPING -> EMExtraMoreMachineBlockTypes.CNC_STAMPER;
                    case CNC_LATHING -> EMExtraMoreMachineBlockTypes.CNC_LATHE;
                    case CNC_ROLLING_MILL -> EMExtraMoreMachineBlockTypes.CNC_ROLLING_MILL;
                    case REPLICATING -> EMExtraMoreMachineBlockTypes.REPLICATOR;
                },
                tier));
        builder.withComputerSupport(tier.getEMExtraTier().getLowerName() + type.getRegistryNameComponentCapitalized() + "Factory");
        return builder;
    }
}
