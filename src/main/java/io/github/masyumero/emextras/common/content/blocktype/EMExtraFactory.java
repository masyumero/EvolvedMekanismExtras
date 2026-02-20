package io.github.masyumero.emextras.common.content.blocktype;

import io.github.masyumero.emextras.common.block.attribute.EMExtraAttributeFactoryType;
import io.github.masyumero.emextras.common.block.attribute.EMExtraAttributeTier;
import io.github.masyumero.emextras.common.block.attribute.EMExtraAttributeUpgradeable;
import io.github.masyumero.emextras.common.registry.EMExtraBlocks;
import io.github.masyumero.emextras.common.registry.EMExtraContainerTypes;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import io.github.masyumero.emextras.common.tile.factory.TileEntityEMExtraFactory;
import io.github.masyumero.emextras.common.util.EMExtraEnumUtils;
import mekanism.api.math.MathUtils;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.*;
import mekanism.common.inventory.container.MekanismContainer;
import mekanism.common.lib.math.Pos3D;
import mekanism.common.registration.impl.ContainerTypeRegistryObject;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;

import net.minecraft.core.particles.ParticleTypes;

import java.util.function.Supplier;

public class EMExtraFactory<TILE extends TileEntityEMExtraFactory<?>> extends EMExtraMachine.EMExtraFactoryMachine<TILE> {

    private final EMExtraMachine.EMExtraFactoryMachine<?> origMachine;

    public EMExtraFactory(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar, Supplier<ContainerTypeRegistryObject<? extends MekanismContainer>> containerRegistrar,
                          EMExtraMachine.EMExtraFactoryMachine<?> origMachine, EMExtraFactoryTier tier) {
        super(tileEntityRegistrar, MekanismLang.DESCRIPTION_FACTORY, origMachine.getFactoryType());
        this.origMachine = origMachine;
        setMachineData(tier);
        add(new AttributeGui(containerRegistrar, null), new EMExtraAttributeTier<>(tier));

        if (tier.ordinal() < EMExtraEnumUtils.EMEXTRA_FACTORY_TIERS.length - 1) {
            add(new EMExtraAttributeUpgradeable(() -> EMExtraBlocks.getEMExtraFactory(EMExtraEnumUtils.EMEXTRA_FACTORY_TIERS[tier.ordinal() + 1], origMachine.getFactoryType())));
        }
    }

    private void setMachineData(EMExtraFactoryTier tier) {
        setFrom(origMachine, AttributeSound.class, EMExtraAttributeFactoryType.class, AttributeUpgradeSupport.class);
        AttributeEnergy origEnergy = origMachine.get(AttributeEnergy.class);
        // origEnergy.getConfigStorage()原本为0.5倍
        if (origEnergy != null) {
            add(new AttributeEnergy(origEnergy::getUsage, () -> MathUtils.clampToLong(Math.max(origEnergy.getConfigStorage(), origEnergy.getUsage()) * tier.processes)));
        }
    }

    public static class EMExtraFactoryBuilder<FACTORY extends EMExtraFactory<TILE>, TILE extends TileEntityEMExtraFactory<?>, T extends EMExtraMachine.EMExtraMachineBuilder<FACTORY, TILE, T>>
            extends BlockTileBuilder<FACTORY, TILE, T> {

        protected EMExtraFactoryBuilder(FACTORY holder) {
            super(holder);
        }

        public static <TILE extends TileEntityEMExtraFactory<?>> EMExtraFactoryBuilder<EMExtraFactory<TILE>, TILE, ?> createFactory(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar, EMExtraFactoryType type,
                                                                                                                                    EMExtraFactoryTier tier) {
            EMExtraFactoryBuilder<EMExtraFactory<TILE>, TILE, ?> builder = new EMExtraFactoryBuilder<>(new EMExtraFactory<>(tileEntityRegistrar,
                    () -> EMExtraContainerTypes.FACTORY, type.getBaseMachine(), tier));

            builder.withComputerSupport(tier.getEMExtraTier().getLowerName() + type.getRegistryNameComponentCapitalized() + "Factory");
            builder.withCustomShape(EMExtraBlockShapes.getShape(type));
            builder.with(switch (type) {
                case ALLOYING, SMELTING, ENRICHING, CRUSHING, COMBINING, SAWING -> AttributeSideConfig.ELECTRIC_MACHINE;
                case COMPRESSING, INJECTING, PURIFYING, INFUSING -> AttributeSideConfig.ADVANCED_ELECTRIC_MACHINE;
            });
            builder.replace(new AttributeParticleFX().addDense(ParticleTypes.SMOKE, 5, rand -> new Pos3D(
                    rand.nextFloat() * 0.7F - 0.3F,
                    rand.nextFloat() * 0.1F + 0.7F,
                    rand.nextFloat() * 0.7F - 0.3F)));
            return builder;
        }
    }
}