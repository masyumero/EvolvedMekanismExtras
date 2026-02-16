package io.github.masyumero.emextras.common.content.blocktype;

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

        public EMExtraFactoryType getFactoryType() {
            return Objects.requireNonNull(get(EMExtraAttributeFactoryType.class)).getFactoryType();
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
    }

}
