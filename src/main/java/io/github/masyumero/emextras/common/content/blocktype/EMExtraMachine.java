package io.github.masyumero.emextras.common.content.blocktype;

import com.jerry.mekanism_extras.api.ExtraUpgrade;
import com.jerry.mekanism_extras.common.block.attribute.ExtraAttributeUpgradeable;
import com.jerry.mekanism_extras.common.tier.AdvancedFactoryTier;
import io.github.masyumero.emextras.common.block.attribute.EMExtraAttributeFactoryType;
import io.github.masyumero.emextras.common.registry.EMExtrasBlock;
import mekanism.api.Upgrade;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.AttributeUpgradeSupport;
import mekanism.common.content.blocktype.BlockTypeTile;
import mekanism.common.content.blocktype.Machine;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tile.base.TileEntityMekanism;

import java.util.EnumSet;
import java.util.Objects;
import java.util.function.Supplier;

public class EMExtraMachine {

    public static class EMExtraFactoryMachine<TILE extends TileEntityMekanism> extends Machine<TILE> {
        public EMExtraFactoryMachine(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntitySupplier, MekanismLang description, EMExtraFactoryType factoryType) {
            super(tileEntitySupplier, description);
            add(new AttributeUpgradeSupport(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, ExtraUpgrade.STACK, ExtraUpgrade.CREATIVE)));
            add(new EMExtraAttributeFactoryType(factoryType), new ExtraAttributeUpgradeable(() -> EMExtrasBlock.getEMExtraFactory(AdvancedFactoryTier.ABSOLUTE, getFactoryType())));
        }

        public EMExtraFactoryType getFactoryType() {
            return Objects.requireNonNull(get(EMExtraAttributeFactoryType.class)).getFactoryType();
        }
    }

    public static class EMExtraMachineBuilder<MACHINE extends Machine<TILE>, TILE extends TileEntityMekanism, T extends EMExtraMachineBuilder<MACHINE, TILE, T>> extends BlockTypeTile.BlockTileBuilder<MACHINE, TILE, T> {

        protected EMExtraMachineBuilder(MACHINE holder) {
            super(holder);
        }

        public static <TILE extends TileEntityMekanism> EMExtraMachineBuilder<EMExtraFactoryMachine<TILE>, TILE, ?> createEMExtraFactoryMachine(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar,
                                                                                                                                                MekanismLang description, EMExtraFactoryType factoryType) {
            return new EMExtraMachineBuilder<>(new EMExtraFactoryMachine<>(tileEntityRegistrar, description, factoryType));
        }
    }

}
