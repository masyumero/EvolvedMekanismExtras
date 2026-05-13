package io.github.masyumero.emextras.common.integration.mekaf.capabilites.energy;

import io.github.masyumero.emextras.common.integration.mekaf.tile.factory.base.TileEntityEMExtraAdvancedFactoryBase;

import mekanism.api.AutomationType;
import mekanism.api.IContentsListener;
import mekanism.api.functions.ConstantPredicates;
import mekanism.common.block.attribute.AttributeEnergy;
import mekanism.common.capabilities.energy.MachineEnergyContainer;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class EMExtraAdvancedFactoryEnergyContainer extends MachineEnergyContainer<TileEntityEMExtraAdvancedFactoryBase<?>> {

    public static EMExtraAdvancedFactoryEnergyContainer input(TileEntityEMExtraAdvancedFactoryBase<?> tile, @Nullable IContentsListener listener) {
        AttributeEnergy electricBlock = validateBlock(tile);
        return new EMExtraAdvancedFactoryEnergyContainer(electricBlock.getStorage(), electricBlock.getUsage(), notExternal, ConstantPredicates.alwaysTrue(), tile, listener);
    }

    private EMExtraAdvancedFactoryEnergyContainer(long maxEnergy, long energyPerTick, Predicate<@NotNull AutomationType> canExtract, Predicate<@NotNull AutomationType> canInsert, TileEntityEMExtraAdvancedFactoryBase<?> tile, @Nullable IContentsListener listener) {
        super(maxEnergy, energyPerTick, canExtract, canInsert, tile, listener);
    }

    public long getBaseEnergyPerTick() {
        return super.getBaseEnergyPerTick() + this.tile.getRecipeEnergyRequired();
    }
}
