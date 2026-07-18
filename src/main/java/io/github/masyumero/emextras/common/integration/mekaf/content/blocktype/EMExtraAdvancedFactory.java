package io.github.masyumero.emextras.common.integration.mekaf.content.blocktype;

import io.github.masyumero.emextras.common.block.attribute.EMExtraAttributeTier;
import io.github.masyumero.emextras.common.block.attribute.EMExtraAttributeUpgradeable;
import io.github.masyumero.emextras.common.content.blocktype.EMExtraMachine.EMExtraFactoryMachine;
import io.github.masyumero.emextras.common.integration.mekaf.regisrty.EMExtraAdvancedFactoryBlockTypes;
import io.github.masyumero.emextras.common.integration.mekaf.regisrty.EMExtraAdvancedFactoryBlocks;
import io.github.masyumero.emextras.common.integration.mekaf.regisrty.EMExtraAdvancedFactoryContainerTypes;
import io.github.masyumero.emextras.common.integration.mekaf.tile.factory.base.TileEntityEMExtraAdvancedFactoryBase;

import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import io.github.masyumero.emextras.common.util.EMExtraEnumUtils;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.AttributeEnergy;
import mekanism.common.block.attribute.AttributeGui;
import mekanism.common.block.attribute.AttributeParticleFX;
import mekanism.common.block.attribute.AttributeSound;
import mekanism.common.block.attribute.AttributeUpgradeSupport;
import mekanism.common.inventory.container.MekanismContainer;
import mekanism.common.lib.math.Pos3D;
import mekanism.common.registration.impl.ContainerTypeRegistryObject;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;

import net.minecraft.core.particles.ParticleTypes;

import com.jerry.mekaf.common.block.attribute.AttributeAdvancedFactoryType;
import com.jerry.mekaf.common.content.blocktype.AdvancedFactoryBlockShapes;
import com.jerry.mekaf.common.content.blocktype.AdvancedFactoryType;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class EMExtraAdvancedFactory<TILE extends TileEntityEMExtraAdvancedFactoryBase<?>> extends EMExtraFactoryMachine<TILE> {

    private final EMExtraFactoryMachine<?> origMachine;

    public EMExtraAdvancedFactory(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar, Supplier<ContainerTypeRegistryObject<? extends MekanismContainer>> containerRegistrar,
                                EMExtraFactoryMachine<?> origMachine, EMExtraFactoryTier tier) {
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
            add(new AttributeEnergy(origEnergy::getUsage, () -> origEnergy.getConfigStorage().max(origEnergy.getUsage()).multiply(tier.processes)));
        }
    }

    public static class EMExtraAdvancedFactoryBuilder<FACTORY extends EMExtraAdvancedFactory<TILE>, TILE extends TileEntityEMExtraAdvancedFactoryBase<?>, T extends EMExtraMachineBuilder<FACTORY, TILE, T>>
            extends BlockTileBuilder<FACTORY, TILE, T> {

        protected EMExtraAdvancedFactoryBuilder(FACTORY holder) {
            super(holder);
        }

        @SuppressWarnings("unchecked")
        public static <TILE extends TileEntityEMExtraAdvancedFactoryBase<?>> EMExtraAdvancedFactoryBuilder<EMExtraAdvancedFactory<TILE>, TILE, ?> createAdvancedFactory(Supplier<?> tileEntityRegistrar,
                                                                                                                                                                  AdvancedFactoryType type, EMExtraFactoryTier tier) {
            EMExtraAdvancedFactoryBuilder<EMExtraAdvancedFactory<TILE>, TILE, ?> builder = getEMExtraAdvancedFactoryBuilder((Supplier<TileEntityTypeRegistryObject<TILE>>) tileEntityRegistrar, type, tier);
            builder.withCustomShape(AdvancedFactoryBlockShapes.getShape(type));
            if (type == AdvancedFactoryType.CENTRIFUGING) {
                builder.withBounding((pos, state, builderPos) -> builderPos.add(pos.above()));
            }
            builder.replace(new AttributeParticleFX().addDense(ParticleTypes.SMOKE, 5, rand -> new Pos3D(
                    rand.nextFloat() * 0.7F - 0.3F,
                    rand.nextFloat() * 0.1F + 0.7F,
                    rand.nextFloat() * 0.7F - 0.3F)));
            return builder;
        }
    }

    private static <TILE extends TileEntityEMExtraAdvancedFactoryBase<?>> @NotNull EMExtraAdvancedFactoryBuilder<EMExtraAdvancedFactory<TILE>, TILE, ?> getEMExtraAdvancedFactoryBuilder(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar, AdvancedFactoryType type, EMExtraFactoryTier tier) {
        EMExtraAdvancedFactoryBuilder<EMExtraAdvancedFactory<TILE>, TILE, ?> builder = new EMExtraAdvancedFactoryBuilder<>(new EMExtraAdvancedFactory<>(tileEntityRegistrar,
                () -> EMExtraAdvancedFactoryContainerTypes.ADVANCED_FACTORY,
                switch (type) {
                    case OXIDIZING -> EMExtraAdvancedFactoryBlockTypes.CHEMICAL_OXIDIZER;
                    case DISSOLVING -> EMExtraAdvancedFactoryBlockTypes.CHEMICAL_DISSOLUTION_CHAMBER;
                    case WASHING -> EMExtraAdvancedFactoryBlockTypes.CHEMICAL_WASHER;
                    case CRYSTALLIZING -> EMExtraAdvancedFactoryBlockTypes.CHEMICAL_CRYSTALLIZER;
                    case PRESSURISED_REACTING -> EMExtraAdvancedFactoryBlockTypes.PRESSURIZED_REACTION_CHAMBER;
                    case CENTRIFUGING -> EMExtraAdvancedFactoryBlockTypes.ISOTOPIC_CENTRIFUGE;
                    case LIQUIFYING -> EMExtraAdvancedFactoryBlockTypes.NUTRITIONAL_LIQUIFIER;
                    case PIGMENT_EXTRACTING -> EMExtraAdvancedFactoryBlockTypes.PIGMENT_EXTRACTOR;
                    case PAINTING -> EMExtraAdvancedFactoryBlockTypes.PAINTING_MACHINE;
                },
                tier));
        builder.withComputerSupport(tier.getEMExtraTier().getLowerName() + type.getRegistryNameComponentCapitalized() + "Factory");
        return builder;
    }
}