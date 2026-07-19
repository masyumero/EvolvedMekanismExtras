package io.github.masyumero.emextras.common.block.transmitter;

import io.github.masyumero.emextras.common.registry.EMExtraTileEntityTypes;
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
            case BASIC -> EMExtraTileEntityTypes.ABSOLUTE_OVERCLOCKED_THERMODYNAMIC_CONDUCTOR;
            case ADVANCED -> EMExtraTileEntityTypes.SUPREME_QUANTUM_THERMODYNAMIC_CONDUCTOR;
            case ELITE -> EMExtraTileEntityTypes.COSMIC_DENSE_THERMODYNAMIC_CONDUCTOR;
            case ULTIMATE -> EMExtraTileEntityTypes.INFINITE_MULTIVERSAL_THERMODYNAMIC_CONDUCTOR;
        };
    }
}