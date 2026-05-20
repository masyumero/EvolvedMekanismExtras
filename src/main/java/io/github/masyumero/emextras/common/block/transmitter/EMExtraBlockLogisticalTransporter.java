package io.github.masyumero.emextras.common.block.transmitter;

import io.github.masyumero.emextras.common.registry.EMExtrasTileEntityTypes;
import io.github.masyumero.emextras.common.tile.transmitter.TileEntityEMExtraLogisticalTransporter;
import io.github.masyumero.emextras.common.tile.transmitter.TileEntityEMExtraLogisticalTransporterBase;

import lombok.Getter;
import mekanism.common.block.attribute.AttributeTier;
import mekanism.common.block.interfaces.IHasTileEntity;
import mekanism.common.block.interfaces.ITypeBlock;
import mekanism.common.block.transmitter.BlockLargeTransmitter;
import mekanism.common.content.blocktype.BlockType;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tier.TransporterTier;


public class EMExtraBlockLogisticalTransporter extends BlockLargeTransmitter implements ITypeBlock, IHasTileEntity<TileEntityEMExtraLogisticalTransporterBase> {

    @Getter
    private final TransporterTier tier;

    public EMExtraBlockLogisticalTransporter(TransporterTier tier) {
        super(properties -> properties.mapColor(tier.getBaseTier().getMapColor()));
        this.tier = tier;
    }

    @Override
    public BlockType getType() {
        return AttributeTier.getPassthroughType(this.tier);
    }

    public TileEntityTypeRegistryObject<TileEntityEMExtraLogisticalTransporter> getTileType() {
        return switch (this.tier) {
            case BASIC -> EMExtrasTileEntityTypes.ABSOLUTE_OVERCLOCKED_LOGISTICAL_TRANSPORTER;
            case ADVANCED -> EMExtrasTileEntityTypes.SUPREME_QUANTUM_LOGISTICAL_TRANSPORTER;
            case ELITE -> EMExtrasTileEntityTypes.COSMIC_DENSE_LOGISTICAL_TRANSPORTER;
            case ULTIMATE -> EMExtrasTileEntityTypes.INFINITE_MULTIVERSAL_LOGISTICAL_TRANSPORTER;
        };
    }
}