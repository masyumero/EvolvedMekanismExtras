package io.github.masyumero.emextras.common.content.blocktype;

import com.jerry.mekanism_extras.common.tier.AdvancedFactoryTier;
import com.jerry.mekanism_extras.common.util.ExtraEnumUtils;
import io.github.masyumero.emextras.common.block.attribute.EMExtraAttributeFactoryType;
import io.github.masyumero.emextras.common.block.attribute.EMExtraAttributeTier;
import io.github.masyumero.emextras.common.block.attribute.EMExtraAttributeUpgradeable;
import io.github.masyumero.emextras.common.registry.EMExtrasBlock;
import io.github.masyumero.emextras.common.registry.EMExtrasBlockType;
import io.github.masyumero.emextras.common.registry.EMExtrasContainerTypes;
import io.github.masyumero.emextras.common.tile.factory.TileEntityEMExtraFactory;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.*;
import mekanism.common.content.blocktype.BlockShapes;
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
                          EMExtraMachine.EMExtraFactoryMachine<?> origMachine, AdvancedFactoryTier tier) {
        super(tileEntityRegistrar, MekanismLang.DESCRIPTION_FACTORY, origMachine.getFactoryType());
        this.origMachine = origMachine;;
        setMachineData(tier);
        add(new AttributeGui(containerRegistrar, null), new EMExtraAttributeTier<>(tier));

        if (tier.ordinal() < ExtraEnumUtils.ADVANCED_FACTORY_TIERS.length - 1) {
            add(new EMExtraAttributeUpgradeable(() -> EMExtrasBlock.getEMExtraFactory(ExtraEnumUtils.ADVANCED_FACTORY_TIERS[tier.ordinal() + 1], origMachine.getFactoryType())));
        }
    }

    private void setMachineData(AdvancedFactoryTier tier) {
        setFrom(origMachine, AttributeSound.class, EMExtraAttributeFactoryType.class, AttributeUpgradeSupport.class);
        AttributeEnergy origEnergy = origMachine.get(AttributeEnergy.class);
        add(new AttributeEnergy(origEnergy::getUsage, () -> origEnergy.getConfigStorage().multiply(0.5).max(origEnergy.getUsage()).multiply(tier.processes)));
    }

    public static class EMExtraFactoryBuilder<FACTORY extends EMExtraFactory<TILE>, TILE extends TileEntityEMExtraFactory<?>, T extends EMExtraMachine.EMExtraMachineBuilder<FACTORY, TILE, T>>
            extends BlockTileBuilder<FACTORY, TILE, T> {

        protected EMExtraFactoryBuilder(FACTORY holder) {
            super(holder);
        }

        @SuppressWarnings("unchecked")
        public static <TILE extends TileEntityEMExtraFactory<?>> EMExtraFactoryBuilder<EMExtraFactory<TILE>, TILE, ?> createFactory(Supplier<?> tileEntityRegistrar, EMExtraFactoryType type,
                                                                                                                                    AdvancedFactoryTier tier) {

            EMExtraFactoryBuilder<EMExtraFactory<TILE>, TILE, ?> builder = getAdvancedFactoryTILEAdvancedFactoryBuilder((Supplier<TileEntityTypeRegistryObject<TILE>>) tileEntityRegistrar, type, tier);
            if(type == EMExtraFactoryType.ALLOYING) {
                builder.withCustomShape(BlockShapes.SMELTING_FACTORY);
            }
//            builder.with(switch (type) {
//                case SMELTING, ENRICHING, CRUSHING, COMBINING, SAWING -> AttributeSideConfig.ELECTRIC_MACHINE;
//                case COMPRESSING, INJECTING, PURIFYING, INFUSING -> AttributeSideConfig.ADVANCED_ELECTRIC_MACHINE;
//            });
            builder.replace(new AttributeParticleFX().addDense(ParticleTypes.SMOKE, 5, rand -> new Pos3D(
                    rand.nextFloat() * 0.7F - 0.3F,
                    rand.nextFloat() * 0.1F + 0.7F,
                    rand.nextFloat() * 0.7F - 0.3F
            )));
            return builder;
        }
    }

    private static <TILE extends TileEntityEMExtraFactory<?>> @NotNull EMExtraFactoryBuilder<EMExtraFactory<TILE>, TILE, ?> getAdvancedFactoryTILEAdvancedFactoryBuilder(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar, EMExtraFactoryType type, AdvancedFactoryTier tier) {

        EMExtraFactoryBuilder<EMExtraFactory<TILE>, TILE, ?> builder = new EMExtraFactoryBuilder<>(new EMExtraFactory<>(tileEntityRegistrar,
                () -> EMExtrasContainerTypes.FACTORY,
                switch (type) {
                    case ALLOYING -> EMExtrasBlockType.ALLOYER;
                },
                tier)
        );
        //Note, we can't just return the builder here as then it gets all confused about object types, so we just
        // assign the value here, and then return the builder itself as it is the same object
        builder.withComputerSupport(tier.getAdvanceTier().getLowerName() + type.getRegistryNameComponentCapitalized() + "Factory");
        return builder;
    }
}