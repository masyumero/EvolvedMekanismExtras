package io.github.masyumero.emextras.common.integration.mekaf.content.blocktype;

import io.github.masyumero.emextras.common.block.attribute.EMExtraAttributeTier;
import io.github.masyumero.emextras.common.block.attribute.EMExtraAttributeUpgradeable;
import io.github.masyumero.emextras.common.content.blocktype.EMExtraMachine;
import io.github.masyumero.emextras.common.integration.mekaf.registries.EMExtraAdvancedFactoryBlockTypes;
import io.github.masyumero.emextras.common.integration.mekaf.registries.EMExtraAdvancedFactoryBlocks;
import io.github.masyumero.emextras.common.integration.mekaf.registries.EMExtraAdvancedFactoryContainerTypes;
import io.github.masyumero.emextras.common.integration.mekaf.tile.factory.TileEntityEMExtraAdvancedBase;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import io.github.masyumero.emextras.common.util.EMExtraEnumUtils;

import mekanism.api.math.MathUtils;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.*;
import mekanism.common.inventory.container.MekanismContainer;
import mekanism.common.lib.math.Pos3D;
import mekanism.common.lib.transmitter.TransmissionType;
import mekanism.common.registration.impl.ContainerTypeRegistryObject;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;

import net.minecraft.core.particles.ParticleTypes;

import com.jerry.mekaf.common.block.attribute.AttributeAdvancedFactoryType;
import com.jerry.mekaf.common.content.blocktype.AdvancedFactoryBlockShapes;
import com.jerry.mekaf.common.content.blocktype.AdvancedFactoryType;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class EMExtraAdvancedFactory<TILE extends TileEntityEMExtraAdvancedBase<?>> extends EMExtraMachine.EMExtraFactoryMachine<TILE> {

    private final EMExtraMachine.EMExtraFactoryMachine<?> origMachine;

    public EMExtraAdvancedFactory(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar, Supplier<ContainerTypeRegistryObject<? extends MekanismContainer>> containerRegistrar,
                                  EMExtraMachine.EMExtraFactoryMachine<?> origMachine, EMExtraFactoryTier tier) {
        super(tileEntityRegistrar, MekanismLang.DESCRIPTION_FACTORY, origMachine.getAdvancedFactoryType());
        this.origMachine = origMachine;
        setMachineData(tier);
        add(new AttributeGui(containerRegistrar, null), new EMExtraAttributeTier<>(tier));

        if (tier.ordinal() < EMExtraEnumUtils.EMEXTRA_FACTORY_TIERS.length - 1) {
            add(new EMExtraAttributeUpgradeable(() -> EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactory(EMExtraEnumUtils.EMEXTRA_FACTORY_TIERS[tier.ordinal() + 1], origMachine.getAdvancedFactoryType())));
        }
    }

    private void setMachineData(EMExtraFactoryTier tier) {
        setFrom(origMachine, AttributeSound.class, AttributeAdvancedFactoryType.class, AttributeUpgradeSupport.class);
        AttributeEnergy origEnergy = origMachine.get(AttributeEnergy.class);
        if (origEnergy != null) {
            // 相比于原版，这里将0.5的乘数去除
            add(new AttributeEnergy(origEnergy::getUsage, () -> MathUtils.clampToLong(Math.max(origEnergy.getConfigStorage(), origEnergy.getUsage()) * tier.processes * tier.processes)));
        }
    }

    public static class EMExtraAdvancedFactoryBuilder<FACTORY extends EMExtraAdvancedFactory<TILE>, TILE extends TileEntityEMExtraAdvancedBase<?>, T extends EMExtraMachine.EMExtraMachineBuilder<FACTORY, TILE, T>>
            extends BlockTileBuilder<FACTORY, TILE, T> {

        protected EMExtraAdvancedFactoryBuilder(FACTORY holder) {
            super(holder);
        }

        @SuppressWarnings("unchecked")
        public static <TILE extends TileEntityEMExtraAdvancedBase<?>> EMExtraAdvancedFactoryBuilder<EMExtraAdvancedFactory<TILE>, TILE, ?> createAdvancedFactory(Supplier<?> tileEntityRegistrar, AdvancedFactoryType type,
                                                                                                                                                                 EMExtraFactoryTier tier) {
            // this is dirty but unfortunately necessary for things to play right
            EMExtraAdvancedFactoryBuilder<EMExtraAdvancedFactory<TILE>, TILE, ?> builder = getEMExtraAdvancedFactoryTILEAdvancedFactoryBuilder((Supplier<TileEntityTypeRegistryObject<TILE>>) tileEntityRegistrar, type, tier);
            builder.withComputerSupport(tier.getEMExtraTier().getLowerName() + type.getRegistryNameComponentCapitalized() + "Factory");
            builder.withCustomShape(AdvancedFactoryBlockShapes.getShape(type));
            builder.with(switch (type) {
                case OXIDIZING, DISSOLVING, CRYSTALLIZING -> AttributeSideConfig.ADVANCED_ELECTRIC_MACHINE;
                case CHEMICAL_INFUSING, CENTRIFUGING -> AttributeSideConfig.create(TransmissionType.CHEMICAL, TransmissionType.ITEM, TransmissionType.ENERGY);
                case WASHING -> AttributeSideConfig.create(TransmissionType.CHEMICAL, TransmissionType.FLUID, TransmissionType.ITEM, TransmissionType.ENERGY);
                case PRESSURISED_REACTING -> AttributeSideConfig.create(TransmissionType.ITEM, TransmissionType.CHEMICAL, TransmissionType.FLUID, TransmissionType.ENERGY);
                case LIQUIFYING -> AttributeSideConfig.create(TransmissionType.FLUID, TransmissionType.ITEM, TransmissionType.ENERGY);
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
    }

    private static <TILE extends TileEntityEMExtraAdvancedBase<?>> @NotNull EMExtraAdvancedFactoryBuilder<EMExtraAdvancedFactory<TILE>, TILE, ?> getEMExtraAdvancedFactoryTILEAdvancedFactoryBuilder(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar, AdvancedFactoryType type, EMExtraFactoryTier tier) {
        EMExtraAdvancedFactoryBuilder<EMExtraAdvancedFactory<TILE>, TILE, ?> builder = new EMExtraAdvancedFactoryBuilder<>(new EMExtraAdvancedFactory<>(tileEntityRegistrar,
                () -> EMExtraAdvancedFactoryContainerTypes.ADVANCED_FACTORY,
                getBaseMachine(type),
                tier));

        return builder;
    }

    private static EMExtraMachine.EMExtraFactoryMachine<?> getBaseMachine(AdvancedFactoryType type) {
        return switch (type) {
            case OXIDIZING -> EMExtraAdvancedFactoryBlockTypes.CHEMICAL_OXIDIZER;
            case CHEMICAL_INFUSING -> EMExtraAdvancedFactoryBlockTypes.CHEMICAL_INFUSER;
            case DISSOLVING -> EMExtraAdvancedFactoryBlockTypes.CHEMICAL_DISSOLUTION_CHAMBER;
            case WASHING -> EMExtraAdvancedFactoryBlockTypes.CHEMICAL_WASHER;
            case CRYSTALLIZING -> EMExtraAdvancedFactoryBlockTypes.CHEMICAL_CRYSTALLIZER;
            case PRESSURISED_REACTING -> EMExtraAdvancedFactoryBlockTypes.PRESSURIZED_REACTION_CHAMBER;
            case CENTRIFUGING -> EMExtraAdvancedFactoryBlockTypes.ISOTOPIC_CENTRIFUGE;
            case LIQUIFYING -> EMExtraAdvancedFactoryBlockTypes.NUTRITIONAL_LIQUIFIER;
        };
    }
}