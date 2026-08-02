package io.github.masyumero.emextras.common.content.blocktype;

import com.jerry.mekaf.common.block.attribute.AttributeAdvancedFactoryType;
import com.jerry.mekaf.common.content.blocktype.AdvancedFactoryType;
import com.jerry.mekanism_extras.api.ExtraUpgrade;
import com.jerry.mekmm.common.block.attribute.AttributeMoreMachineFactoryType;
import com.jerry.mekmm.common.content.blocktype.MoreMachineFactoryType;

import io.github.masyumero.emextras.common.integration.mekaf.regisrty.EMExtraAdvancedFactoryBlocks;
import io.github.masyumero.emextras.common.integration.mekmm.registry.EMExtraMoreMachineBlocks;
import io.github.masyumero.emextras.common.registry.EMExtraBlocks;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import io.github.masyumero.emextras.common.block.attribute.EMExtraAttributeUpgradeable;

import mekanism.api.Upgrade;
import mekanism.api.text.ILangEntry;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.*;
import mekanism.common.content.blocktype.BlockTypeTile;
import mekanism.common.content.blocktype.FactoryType;
import mekanism.common.lib.math.Pos3D;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tile.base.TileEntityMekanism;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;

import java.util.EnumSet;
import java.util.Objects;
import java.util.function.Supplier;

public class EMExtraMachine<TILE extends TileEntityMekanism> extends BlockTypeTile<TILE> {

    public EMExtraMachine(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntitySupplier, ILangEntry description) {
        super(tileEntitySupplier, description);
        add((new AttributeParticleFX())
                .add(ParticleTypes.SMOKE, (rand) -> new Pos3D(rand.nextFloat() * 0.6F - 0.3F, rand.nextFloat() * 6.0F / 16.0F, 0.52))
                .add(DustParticleOptions.REDSTONE, (rand) -> new Pos3D(rand.nextFloat() * 0.6F - 0.3F, rand.nextFloat() * 6.0F / 16.0F, 0.52)));
        add(Attributes.ACTIVE_LIGHT, new AttributeStateFacing(), Attributes.INVENTORY, Attributes.SECURITY, Attributes.REDSTONE, Attributes.COMPARATOR);
    }

    public static class EMExtraFactoryMachine<TILE extends TileEntityMekanism> extends EMExtraMachine<TILE> {

        public EMExtraFactoryMachine(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntitySupplier, MekanismLang description, FactoryType factoryType) {
            super(tileEntitySupplier, description);
            add(new AttributeUpgradeSupport(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, ExtraUpgrade.STACK, ExtraUpgrade.CREATIVE)));
            add(new AttributeFactoryType(factoryType), new EMExtraAttributeUpgradeable(() -> EMExtraBlocks.getEMExtraFactory(EMExtraFactoryTier.ABSOLUTE_OVERCLOCKED, getFactoryType())));
        }

        public EMExtraFactoryMachine(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntitySupplier, ILangEntry description, MoreMachineFactoryType factoryType) {
            super(tileEntitySupplier, description);
            add(new AttributeUpgradeSupport(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, ExtraUpgrade.CREATIVE)));
            add(new AttributeMoreMachineFactoryType(factoryType), new EMExtraAttributeUpgradeable(() -> EMExtraMoreMachineBlocks.getEMExtraMoreMachineFactory(EMExtraFactoryTier.ABSOLUTE_OVERCLOCKED, getMoreMachineFactoryType())));
        }

        public EMExtraFactoryMachine(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntitySupplier, ILangEntry description, AdvancedFactoryType factoryType) {
            super(tileEntitySupplier, description);
            add(new AttributeUpgradeSupport(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, ExtraUpgrade.CREATIVE)));
            add(new AttributeAdvancedFactoryType(factoryType), new EMExtraAttributeUpgradeable(() -> EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactory(EMExtraFactoryTier.ABSOLUTE_OVERCLOCKED, getAdvancedFactoryType())));
        }

        public FactoryType getFactoryType() {
            return Objects.requireNonNull(get(AttributeFactoryType.class)).getFactoryType();
        }

        public MoreMachineFactoryType getMoreMachineFactoryType() {
            return Objects.requireNonNull(get(AttributeMoreMachineFactoryType.class)).getMoreMachineFactoryType();
        }

        public AdvancedFactoryType getAdvancedFactoryType() {
            return Objects.requireNonNull(get(AttributeAdvancedFactoryType.class)).getAdvancedFactoryType();
        }
    }

    public static class EMExtraMachineBuilder<MACHINE extends EMExtraFactoryMachine<TILE>, TILE extends TileEntityMekanism, T extends EMExtraMachineBuilder<MACHINE, TILE, T>> extends BlockTypeTile.BlockTileBuilder<MACHINE, TILE, T> {

        protected EMExtraMachineBuilder(MACHINE holder) {
            super(holder);
        }

        public static <TILE extends TileEntityMekanism> EMExtraMachineBuilder<EMExtraFactoryMachine<TILE>, TILE, ?> createEMExtraFactoryMachine(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar,
                                                                                                                                                MekanismLang description, FactoryType factoryType) {
            return new EMExtraMachineBuilder<>(new EMExtraFactoryMachine<>(tileEntityRegistrar, description, factoryType));
        }

        public static <TILE extends TileEntityMekanism> EMExtraMachineBuilder<EMExtraFactoryMachine<TILE>, TILE, ?> createEMExtraMoreMachineFactoryMachine(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar,
                                                                                                                                                         ILangEntry description, MoreMachineFactoryType factoryType) {
            return new EMExtraMachineBuilder<>(new EMExtraFactoryMachine<>(tileEntityRegistrar, description, factoryType));
        }

        public static <TILE extends TileEntityMekanism> EMExtraMachineBuilder<EMExtraFactoryMachine<TILE>, TILE, ?> createEMExtraAdvancedFactoryMachine(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar,
                                                                                                                                                  ILangEntry description, AdvancedFactoryType factoryType) {
            return new EMExtraMachineBuilder<>(new EMExtraFactoryMachine<>(tileEntityRegistrar, description, factoryType));
        }
    }
}
