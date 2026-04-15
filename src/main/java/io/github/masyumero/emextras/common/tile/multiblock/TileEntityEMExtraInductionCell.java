package io.github.masyumero.emextras.common.tile.multiblock;

import io.github.masyumero.emextras.common.block.attribute.EMExtraAttribute;
import io.github.masyumero.emextras.common.tier.EMExtraICTier;
import mekanism.api.IContentsListener;
import mekanism.api.providers.IBlockProvider;
import mekanism.common.capabilities.energy.MachineEnergyContainer;
import mekanism.common.capabilities.holder.energy.EnergyContainerHelper;
import mekanism.common.capabilities.holder.energy.IEnergyContainerHolder;
import mekanism.common.integration.energy.EnergyCompatUtils;
import mekanism.common.tile.prefab.TileEntityInternalMultiblock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class TileEntityEMExtraInductionCell extends TileEntityInternalMultiblock {

    private MachineEnergyContainer<TileEntityEMExtraInductionCell> energyContainer;
    public EMExtraICTier tier;

    public TileEntityEMExtraInductionCell(IBlockProvider blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state);
        //Never externally expose the energy capability
        addDisabledCapabilities(EnergyCompatUtils.getEnabledEnergyCapabilities());
    }

    @NotNull
    @Override
    protected IEnergyContainerHolder getInitialEnergyContainers(IContentsListener listener) {
        EnergyContainerHelper builder = EnergyContainerHelper.forSide(this::getDirection);
        builder.addContainer(energyContainer = MachineEnergyContainer.internal(this, listener));
        return builder.build();
    }

    @Override
    protected void presetVariables() {
        super.presetVariables();
        tier = EMExtraAttribute.getTier(getBlockType(), EMExtraICTier.class);
    }

    public MachineEnergyContainer<TileEntityEMExtraInductionCell> getEnergyContainer() {
        return energyContainer;
    }
}
