package io.github.masyumero.emextras.common.tile.transmitter;

import io.github.masyumero.emextras.api.tier.EMExtraTier;
import io.github.masyumero.emextras.common.content.network.transmitter.EMExtraLogisticalTransporter;
import io.github.masyumero.emextras.common.registry.EMExtraBlocks;

import mekanism.api.providers.IBlockProvider;
import mekanism.client.model.data.TransmitterModelData;
import mekanism.common.block.states.BlockStateHelper;
import mekanism.common.block.states.TransmitterType;
import mekanism.common.content.network.transmitter.LogisticalTransporterBase;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

import org.jetbrains.annotations.NotNull;

public class TileEntityEMExtraLogisticalTransporter extends TileEntityEMExtraLogisticalTransporterBase {

    public TileEntityEMExtraLogisticalTransporter(IBlockProvider blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state);
    }

    @Override
    protected LogisticalTransporterBase createTransmitter(IBlockProvider blockProvider) {
        return new EMExtraLogisticalTransporter(blockProvider, this);
    }

    @Override
    public EMExtraLogisticalTransporter getTransmitter() {
        return (EMExtraLogisticalTransporter) super.getTransmitter();
    }

    @Override
    public TransmitterType getTransmitterType() {
        return TransmitterType.LOGISTICAL_TRANSPORTER;
    }

    @Override
    protected void updateModelData(TransmitterModelData modelData) {
        super.updateModelData(modelData);
        modelData.setHasColor(getTransmitter().getColor() != null);
    }

    @NotNull
    @Override
    protected BlockState upgradeResult(@NotNull BlockState current, @NotNull EMExtraTier tier) {
        return BlockStateHelper.copyStateData(current, switch (tier) {
            case ABSOLUTE_OVERCLOCKED -> EMExtraBlocks.ABSOLUTE_OVERCLOCKED_LOGISTICAL_TRANSPORTER;
            case SUPREME_QUANTUM -> EMExtraBlocks.SUPREME_QUANTUM_LOGISTICAL_TRANSPORTER;
            case COSMIC_DENSE -> EMExtraBlocks.COSMIC_DENSE_LOGISTICAL_TRANSPORTER;
            case INFINITE_MULTIVERSAL -> EMExtraBlocks.INFINITE_MULTIVERSAL_LOGISTICAL_TRANSPORTER;
        });
    }
}