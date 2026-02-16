package io.github.masyumero.emextras.common.content.blocktype;

import io.github.masyumero.emextras.common.block.attribute.EMExtraAttributeFactoryType;
import io.github.masyumero.emextras.common.block.attribute.EMExtraAttributeTier;
import io.github.masyumero.emextras.common.block.attribute.EMExtraAttributeUpgradeable;
import io.github.masyumero.emextras.common.registry.EMExtraBlockTypes;
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

import org.jetbrains.annotations.NotNull;

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

        @SuppressWarnings("unchecked")
        public static <TILE extends TileEntityEMExtraFactory<?>> EMExtraFactoryBuilder<EMExtraFactory<TILE>, TILE, ?> createFactory(Supplier<?> tileEntityRegistrar, EMExtraFactoryType type,
                                                                                                                                    EMExtraFactoryTier tier) {
            EMExtraFactoryBuilder<EMExtraFactory<TILE>, TILE, ?> builder = getEMExtraFactoryTILEEMExtraFactoryBuilder((Supplier<TileEntityTypeRegistryObject<TILE>>) tileEntityRegistrar, type, tier);
            builder.withCustomShape(EMExtraBlockShapes.getShape(type));
            builder.with(switch (type) {
                case ALLOYING, EXTRA_ALLOYING, SMELTING, ENRICHING, CRUSHING, COMBINING, SAWING -> AttributeSideConfig.ELECTRIC_MACHINE;
                case COMPRESSING, INJECTING, PURIFYING, INFUSING -> AttributeSideConfig.ADVANCED_ELECTRIC_MACHINE;
            });
            builder.replace(new AttributeParticleFX().addDense(ParticleTypes.SMOKE, 5, rand -> new Pos3D(
                    rand.nextFloat() * 0.7F - 0.3F,
                    rand.nextFloat() * 0.1F + 0.7F,
                    rand.nextFloat() * 0.7F - 0.3F)));
            return builder;
        }
    }

    private static <TILE extends TileEntityEMExtraFactory<?>> @NotNull EMExtraFactoryBuilder<EMExtraFactory<TILE>, TILE, ?> getEMExtraFactoryTILEEMExtraFactoryBuilder(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar, EMExtraFactoryType type, EMExtraFactoryTier tier) {
        EMExtraFactoryBuilder<EMExtraFactory<TILE>, TILE, ?> builder = new EMExtraFactoryBuilder<>(new EMExtraFactory<>(tileEntityRegistrar,
                () -> EMExtraContainerTypes.FACTORY,
                switch (type) {
                    case ALLOYING, EXTRA_ALLOYING -> EMExtraBlockTypes.ALLOYER;
                    case SAWING -> EMExtraBlockTypes.PRECISION_SAWMILL;
                    case SMELTING -> EMExtraBlockTypes.ENERGIZED_SMELTER;
                    case ENRICHING -> EMExtraBlockTypes.ENRICHMENT_CHAMBER;
                    case CRUSHING -> EMExtraBlockTypes.CRUSHER;
                    case COMPRESSING -> EMExtraBlockTypes.OSMIUM_COMPRESSOR;
                    case COMBINING -> EMExtraBlockTypes.COMBINER;
                    case PURIFYING -> EMExtraBlockTypes.PURIFICATION_CHAMBER;
                    case INJECTING -> EMExtraBlockTypes.CHEMICAL_INJECTION_CHAMBER;
                    case INFUSING -> EMExtraBlockTypes.METALLURGIC_INFUSER;
                },
                tier));
        // Note, we can't just return the builder here as then it gets all confused about object types, so we just
        // assign the value here, and then return the builder itself as it is the same object
        builder.withComputerSupport(tier.getEMExtraTier().getLowerName() + type.getRegistryNameComponentCapitalized() + "Factory");
        return builder;
    }
}