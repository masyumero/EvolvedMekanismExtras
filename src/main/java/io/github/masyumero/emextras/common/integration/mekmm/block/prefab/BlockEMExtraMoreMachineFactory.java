package io.github.masyumero.emextras.common.integration.mekmm.block.prefab;

import io.github.masyumero.emextras.common.block.prefab.BlockEMExtraFactoryMachine.BlockEMExtraFactoryMachineModel;
import io.github.masyumero.emextras.common.integration.mekmm.content.blocktype.EMExtraMoreMachineFactory;
import io.github.masyumero.emextras.common.integration.mekmm.tile.TileEntityEMExtraMoreMachineFactory;

import mekanism.common.resource.BlockResourceInfo;

public class BlockEMExtraMoreMachineFactory<TILE extends TileEntityEMExtraMoreMachineFactory<?>> extends BlockEMExtraFactoryMachineModel<TILE, EMExtraMoreMachineFactory<TILE>> {

    public BlockEMExtraMoreMachineFactory(EMExtraMoreMachineFactory<TILE> factoryType) {
        super(factoryType, properties -> properties.mapColor(BlockResourceInfo.STEEL.getMapColor()));
    }
}
