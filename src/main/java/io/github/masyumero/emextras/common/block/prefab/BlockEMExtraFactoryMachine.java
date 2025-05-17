package io.github.masyumero.emextras.common.block.prefab;

import io.github.masyumero.emextras.common.content.blocktype.EMExtraFactory;
import io.github.masyumero.emextras.common.content.blocktype.EMExtraMachine;
import io.github.masyumero.emextras.common.tile.factory.TileEntityEMExtraFactory;
import mekanism.common.block.prefab.BlockTile;
import mekanism.common.block.states.IStateFluidLoggable;
import mekanism.common.resource.BlockResourceInfo;
import mekanism.common.tile.base.TileEntityMekanism;

import java.util.function.UnaryOperator;

public class BlockEMExtraFactoryMachine<TILE extends TileEntityMekanism, MACHINE extends EMExtraMachine.EMExtraFactoryMachine<TILE>> extends BlockTile<TILE, MACHINE> {

    public BlockEMExtraFactoryMachine(MACHINE machineType, UnaryOperator<Properties> propertiesModifier) {
        super(machineType, propertiesModifier);
    }

    public static class BlockEMExtraFactoryMachineModel<TILE extends TileEntityMekanism, MACHINE extends EMExtraMachine.EMExtraFactoryMachine<TILE>> extends BlockEMExtraFactoryMachine<TILE, MACHINE> {

        public BlockEMExtraFactoryMachineModel(MACHINE machineType, UnaryOperator<Properties> propertiesModifier) {
            super(machineType, propertiesModifier);
        }
    }

    public static class BlockEMExtraFactory<TILE extends TileEntityEMExtraFactory<?>> extends BlockEMExtraFactoryMachineModel<TILE, EMExtraFactory<TILE>> {

        public BlockEMExtraFactory(EMExtraFactory<TILE> factoryType) {
            super(factoryType, properties -> properties.mapColor(BlockResourceInfo.STEEL.getMapColor()));
        }
    }

}
