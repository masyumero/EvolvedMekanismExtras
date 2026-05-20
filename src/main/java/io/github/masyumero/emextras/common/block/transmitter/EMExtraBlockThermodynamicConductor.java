package io.github.masyumero.emextras.common.block.transmitter;

import io.github.masyumero.emextras.common.registry.EMExtrasTileEntityTypes;
import io.github.masyumero.emextras.common.tile.transmitter.TileEntityEMExtraThermodynamicConductor;

import lombok.Getter;
import mekanism.common.block.attribute.AttributeTier;
import mekanism.common.block.interfaces.IHasTileEntity;
import mekanism.common.block.interfaces.ITypeBlock;
import mekanism.common.block.transmitter.BlockSmallTransmitter;
import mekanism.common.content.blocktype.BlockType;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tier.ConductorTier;

public class EMExtraBlockThermodynamicConductor extends BlockSmallTransmitter implements ITypeBlock, IHasTileEntity<TileEntityEMExtraThermodynamicConductor> {

    @Getter
    private final ConductorTier tier;

    public EMExtraBlockThermodynamicConductor(ConductorTier tier) {
        super(properties -> properties.mapColor(tier.getBaseTier().getMapColor()));
        this.tier = tier;
    }

    @Override
    public BlockType getType() {
        return AttributeTier.getPassthroughType(tier);
    }

    @Override
    public TileEntityTypeRegistryObject<TileEntityEMExtraThermodynamicConductor> getTileType() {
        return switch (tier) {
            case BASIC -> EMExtrasTileEntityTypes.ABSOLUTE_OVERCLOCKED_THERMODYNAMIC_CONDUCTOR;
            case ADVANCED -> EMExtrasTileEntityTypes.SUPREME_QUANTUM_THERMODYNAMIC_CONDUCTOR;
            case ELITE -> EMExtrasTileEntityTypes.COSMIC_DENSE_THERMODYNAMIC_CONDUCTOR;
            case ULTIMATE -> EMExtrasTileEntityTypes.INFINITE_MULTIVERSAL_THERMODYNAMIC_CONDUCTOR;
        };
    }
}