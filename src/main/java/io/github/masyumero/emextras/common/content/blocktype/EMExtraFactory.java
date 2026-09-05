package io.github.masyumero.emextras.common.content.blocktype;

import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
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
import mekanism.common.content.blocktype.FactoryType;
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
        setFrom(origMachine, AttributeSound.class, AttributeFactoryType.class, AttributeUpgradeSupport.class);
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

        public static <TILE extends TileEntityEMExtraFactory<?>> EMExtraFactoryBuilder<EMExtraFactory<TILE>, TILE, ?> createFactory(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar, FactoryType type,
                                                                                                                                    EMExtraFactoryTier tier) {
            EMExtraFactoryBuilder<EMExtraFactory<TILE>, TILE, ?> builder = getAdvancedFactoryTILEAdvancedFactoryBuilder(tileEntityRegistrar, type, tier);

            builder.withCustomShape(EMExtraBlockShapes.getShape(type));
            builder.with(type == EMFactoryType.ALLOYING ? AttributeSideConfig.ELECTRIC_MACHINE : switch (type) {
                case SMELTING, ENRICHING, CRUSHING, COMBINING, SAWING -> AttributeSideConfig.ELECTRIC_MACHINE;
                case COMPRESSING, INJECTING, PURIFYING, INFUSING -> AttributeSideConfig.ADVANCED_ELECTRIC_MACHINE;
            });
            builder.replace(new AttributeParticleFX().addDense(ParticleTypes.SMOKE, 5, rand -> new Pos3D(
                    rand.nextFloat() * 0.7F - 0.3F,
                    rand.nextFloat() * 0.1F + 0.7F,
                    rand.nextFloat() * 0.7F - 0.3F)));
            return builder;
        }

        private static <TILE extends TileEntityEMExtraFactory<?>> @NotNull EMExtraFactoryBuilder<EMExtraFactory<TILE>, TILE, ?> getAdvancedFactoryTILEAdvancedFactoryBuilder(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar, FactoryType type, EMExtraFactoryTier tier) {
            EMExtraFactoryBuilder<EMExtraFactory<TILE>, TILE, ?> builder = new EMExtraFactoryBuilder<>(new EMExtraFactory<>(tileEntityRegistrar,
                    () -> EMExtraContainerTypes.FACTORY,
                    type == EMFactoryType.ALLOYING ? EMExtraBlockTypes.ALLOYER : switch (type) {
                        case SMELTING -> EMExtraBlockTypes.ENERGIZED_SMELTER;
                        case ENRICHING -> EMExtraBlockTypes.ENRICHMENT_CHAMBER;
                        case CRUSHING -> EMExtraBlockTypes.CRUSHER;
                        case SAWING -> EMExtraBlockTypes.PRECISION_SAWMILL;
                        case INFUSING -> EMExtraBlockTypes.METALLURGIC_INFUSER;
                        case COMBINING -> EMExtraBlockTypes.COMBINER;
                        case INJECTING -> EMExtraBlockTypes.CHEMICAL_INJECTION_CHAMBER;
                        case PURIFYING -> EMExtraBlockTypes.PURIFICATION_CHAMBER;
                        case COMPRESSING -> EMExtraBlockTypes.OSMIUM_COMPRESSOR;
                    },
                    tier)
            );

            builder.withComputerSupport(tier.getEMExtraTier().getLowerName() + type.getRegistryNameComponentCapitalized() + "Factory");
            return builder;
        }
    }
}