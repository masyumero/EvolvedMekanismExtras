package io.github.masyumero.emextras.common.block.transmitter;

import io.github.masyumero.emextras.common.registry.EMExtrasTileEntityTypes;
import io.github.masyumero.emextras.common.tile.transmitter.TileEntityEMExtraMechanicalPipe;

import lombok.Getter;
import mekanism.common.block.attribute.AttributeTier;
import mekanism.common.block.interfaces.IHasTileEntity;
import mekanism.common.block.interfaces.ITypeBlock;
import mekanism.common.block.transmitter.BlockLargeTransmitter;
import mekanism.common.content.blocktype.BlockType;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tier.PipeTier;

public class EMExtraBlockMechanicalPipe extends BlockLargeTransmitter implements ITypeBlock, IHasTileEntity<TileEntityEMExtraMechanicalPipe> {

    @Getter
    private final PipeTier tier;

    public EMExtraBlockMechanicalPipe(PipeTier tier) {
        super(properties -> properties.mapColor(tier.getBaseTier().getMapColor()));
        this.tier = tier;
    }

    public BlockType getType() {
        return AttributeTier.getPassthroughType(this.tier);
    }

    public TileEntityTypeRegistryObject<TileEntityEMExtraMechanicalPipe> getTileType() {
        return switch (this.tier) {
            case BASIC -> EMExtrasTileEntityTypes.ABSOLUTE_OVERCLOCKED_MECHANICAL_PIPE;
            case ADVANCED -> EMExtrasTileEntityTypes.SUPREME_QUANTUM_MECHANICAL_PIPE;
            case ELITE -> EMExtrasTileEntityTypes.COSMIC_DENSE_MECHANICAL_PIPE;
            case ULTIMATE -> EMExtrasTileEntityTypes.INFINITE_MULTIVERSAL_MECHANICAL_PIPE;
        };
    }
}