package io.github.masyumero.emextras.common.integration.mekaf.block.prefab;

import io.github.masyumero.emextras.common.block.prefab.BlockEMExtraFactoryMachine;
import io.github.masyumero.emextras.common.integration.mekaf.content.blocktype.EMExtraAdvancedFactory;
import io.github.masyumero.emextras.common.integration.mekaf.tile.factory.base.TileEntityEMExtraAdvancedFactoryBase;

import mekanism.common.resource.BlockResourceInfo;

public class BlockEMExtraAdvancedFactory<TILE extends TileEntityEMExtraAdvancedFactoryBase<?>> extends BlockEMExtraFactoryMachine.BlockEMExtraFactoryMachineModel<TILE, EMExtraAdvancedFactory<TILE>> {

    public BlockEMExtraAdvancedFactory(EMExtraAdvancedFactory<TILE> factoryType) {
        super(factoryType, properties -> properties.mapColor(BlockResourceInfo.STEEL.getMapColor()));
    }
}
