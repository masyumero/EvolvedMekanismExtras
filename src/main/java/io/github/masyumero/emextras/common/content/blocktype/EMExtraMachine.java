package io.github.masyumero.emextras.common.content.blocktype;

import com.jerry.mekaf.common.block.attribute.AttributeAdvancedFactoryType;
import com.jerry.mekaf.common.content.blocktype.AdvancedFactoryType;
import com.jerry.mekmm.common.block.attribute.MoreMachineAttributeFactoryType;
import com.jerry.mekmm.common.content.blocktype.MoreMachineFactoryType;
import io.github.masyumero.emextras.common.block.attribute.EMExtraAttributeFactoryType;
import mekanism.api.text.ILangEntry;
import mekanism.common.content.blocktype.BlockTypeTile;
import mekanism.common.content.blocktype.Machine;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tile.base.TileEntityMekanism;

import java.util.Objects;
import java.util.function.Supplier;

public class EMExtraMachine {

    public static class EMExtraFactoryMachine<TILE extends TileEntityMekanism> extends Machine<TILE> {

        public EMExtraFactoryMachine(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntitySupplier, ILangEntry description, EMExtraFactoryType factoryType) {
            super(tileEntitySupplier, description);
            add(new EMExtraAttributeFactoryType(factoryType));
        }

        public EMExtraFactoryMachine(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntitySupplier, ILangEntry description, AdvancedFactoryType factoryType) {
            super(tileEntitySupplier, description);
            add(new AttributeAdvancedFactoryType(factoryType));
        }

        public EMExtraFactoryMachine(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntitySupplier, ILangEntry description, MoreMachineFactoryType factoryType) {
            super(tileEntitySupplier, description);
            add(new MoreMachineAttributeFactoryType(factoryType));
        }

        public EMExtraFactoryType getFactoryType() {
            return Objects.requireNonNull(get(EMExtraAttributeFactoryType.class)).getFactoryType();
        }

        public AdvancedFactoryType getAdvancedFactoryType() {
            return Objects.requireNonNull(get(AttributeAdvancedFactoryType.class)).getAdvancedFactoryType();
        }

        public MoreMachineFactoryType getMoreMachineFactoryType() {
            return Objects.requireNonNull(get(MoreMachineAttributeFactoryType.class)).getMoreMachineFactoryType();
        }
    }

    public static class EMExtraMachineBuilder<MACHINE extends Machine<TILE>, TILE extends TileEntityMekanism, T extends EMExtraMachineBuilder<MACHINE, TILE, T>> extends BlockTypeTile.BlockTileBuilder<MACHINE, TILE, T> {

        protected EMExtraMachineBuilder(MACHINE holder) {
            super(holder);
        }

        public static <TILE extends TileEntityMekanism> EMExtraMachineBuilder<EMExtraFactoryMachine<TILE>, TILE, ?> createEMExtraFactoryMachine(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar,
                                                                                                                                                ILangEntry description, EMExtraFactoryType factoryType) {
            return new EMExtraMachineBuilder<>(new EMExtraFactoryMachine<>(tileEntityRegistrar, description, factoryType));
        }

        public static <TILE extends TileEntityMekanism> EMExtraMachineBuilder<EMExtraFactoryMachine<TILE>, TILE, ?> createEMExtraAdvancedFactoryMachine(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar,
                                                                                                                                                        ILangEntry description, AdvancedFactoryType factoryType) {
            return new EMExtraMachineBuilder<>(new EMExtraFactoryMachine<>(tileEntityRegistrar, description, factoryType));
        }

        public static <TILE extends TileEntityMekanism> EMExtraMachineBuilder<EMExtraFactoryMachine<TILE>, TILE, ?> createEMExtraMoreMachineFactoryMachine(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar,
                                                                                                                                                           ILangEntry description, MoreMachineFactoryType factoryType) {
            return new EMExtraMachineBuilder<>(new EMExtraFactoryMachine<>(tileEntityRegistrar, description, factoryType));
        }
    }

}
