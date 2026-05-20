package io.github.masyumero.emextras.common.block.transmitter;

import io.github.masyumero.emextras.common.registry.EMExtrasTileEntityTypes;
import io.github.masyumero.emextras.common.tile.transmitter.TileEntityEMExtraUniversalCable;

import lombok.Getter;
import mekanism.common.block.attribute.AttributeTier;
import mekanism.common.block.interfaces.IHasTileEntity;
import mekanism.common.block.interfaces.ITypeBlock;
import mekanism.common.block.transmitter.BlockSmallTransmitter;
import mekanism.common.content.blocktype.BlockType;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tier.CableTier;

public class EMExtraBlockUniversalCable extends BlockSmallTransmitter implements ITypeBlock, IHasTileEntity<TileEntityEMExtraUniversalCable> {

    @Getter
    private final CableTier tier;

    public EMExtraBlockUniversalCable(CableTier tier) {
        super(properties -> properties.mapColor(tier.getBaseTier().getMapColor()));
        this.tier = tier;
    }

    @Override
    public BlockType getType() {
        return AttributeTier.getPassthroughType(tier);
    }

    @Override
    public TileEntityTypeRegistryObject<TileEntityEMExtraUniversalCable> getTileType() {
        return switch (tier) {
            case BASIC -> EMExtrasTileEntityTypes.ABSOLUTE_OVERCLOCKED_UNIVERSAL_CABLE;
            case ADVANCED -> EMExtrasTileEntityTypes.SUPREME_QUANTUM_UNIVERSAL_CABLE;
            case ELITE -> EMExtrasTileEntityTypes.COSMIC_DENSE_UNIVERSAL_CABLE;
            case ULTIMATE -> EMExtrasTileEntityTypes.INFINITE_MULTIVERSAL_UNIVERSAL_CABLE;
        };
    }
}