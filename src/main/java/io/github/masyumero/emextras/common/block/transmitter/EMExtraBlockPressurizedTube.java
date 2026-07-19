package io.github.masyumero.emextras.common.block.transmitter;

import io.github.masyumero.emextras.common.registry.EMExtraTileEntityTypes;
import io.github.masyumero.emextras.common.tile.transmitter.TileEntityEMExtraPressurizedTube;

import lombok.Getter;
import mekanism.common.block.attribute.AttributeTier;
import mekanism.common.block.interfaces.IHasTileEntity;
import mekanism.common.block.interfaces.ITypeBlock;
import mekanism.common.block.transmitter.BlockSmallTransmitter;
import mekanism.common.content.blocktype.BlockType;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tier.TubeTier;

public class EMExtraBlockPressurizedTube extends BlockSmallTransmitter implements ITypeBlock, IHasTileEntity<TileEntityEMExtraPressurizedTube> {

    @Getter
    private final TubeTier tier;

    public EMExtraBlockPressurizedTube(TubeTier tier) {
        super(properties -> properties.mapColor(tier.getBaseTier().getMapColor()));
        this.tier = tier;
    }

    @Override
    public BlockType getType() {
        return AttributeTier.getPassthroughType(tier);
    }

    @Override
    public TileEntityTypeRegistryObject<TileEntityEMExtraPressurizedTube> getTileType() {
        return switch (tier) {
            case BASIC -> EMExtraTileEntityTypes.ABSOLUTE_OVERCLOCKED_PRESSURIZED_TUBE;
            case ADVANCED -> EMExtraTileEntityTypes.SUPREME_QUANTUM_PRESSURIZED_TUBE;
            case ELITE -> EMExtraTileEntityTypes.COSMIC_DENSE_PRESSURIZED_TUBE;
            case ULTIMATE -> EMExtraTileEntityTypes.INFINITE_MULTIVERSAL_PRESSURIZED_TUBE;
        };
    }
}