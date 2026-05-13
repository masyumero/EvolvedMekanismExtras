package io.github.masyumero.emextras.common.block.prefab;

import io.github.masyumero.emextras.common.content.blocktype.EMExtraMachine;
import io.github.masyumero.emextras.common.integration.mekaf.content.blocktype.EMExtraAdvancedFactory;
import io.github.masyumero.emextras.common.integration.mekaf.tile.factory.base.TileEntityEMExtraAdvancedFactoryBase;
import mekanism.common.block.prefab.BlockTile;
import mekanism.common.block.states.IStateFluidLoggable;
import mekanism.common.resource.BlockResourceInfo;
import mekanism.common.tile.base.TileEntityMekanism;

import java.util.function.UnaryOperator;

public class BlockEMExtraAdvancedFactoryMachine<TILE extends TileEntityMekanism, MACHINE extends EMExtraMachine.EMExtraFactoryMachine<TILE>> extends BlockTile<TILE, MACHINE> {

    public BlockEMExtraAdvancedFactoryMachine(MACHINE machine, UnaryOperator<Properties> propertiesModifier) {
        super(machine, propertiesModifier);
    }

    public static class MoreMachineBlockAdvancedFactoryMachineModel<TILE extends TileEntityMekanism, MACHINE extends EMExtraMachine.EMExtraFactoryMachine<TILE>> extends BlockEMExtraAdvancedFactoryMachine<TILE, MACHINE> implements IStateFluidLoggable {

        public MoreMachineBlockAdvancedFactoryMachineModel(MACHINE machineType, UnaryOperator<Properties> propertiesModifier) {
            super(machineType, propertiesModifier);
        }
    }

    public static class BlockEMExtraAdvancedFactory<TILE extends TileEntityEMExtraAdvancedFactoryBase<?>> extends MoreMachineBlockAdvancedFactoryMachineModel<TILE, EMExtraAdvancedFactory<TILE>> {

        public BlockEMExtraAdvancedFactory(EMExtraAdvancedFactory<TILE> factoryType) {
            super(factoryType, properties -> properties.mapColor(BlockResourceInfo.STEEL.getMapColor()));
        }
    }
}