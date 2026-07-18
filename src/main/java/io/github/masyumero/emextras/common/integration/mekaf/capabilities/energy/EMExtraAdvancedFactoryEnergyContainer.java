package io.github.masyumero.emextras.common.integration.mekaf.capabilities.energy;

import io.github.masyumero.emextras.common.integration.mekaf.tile.factory.base.TileEntityEMExtraAdvancedFactoryBase;
import mekanism.api.AutomationType;
import mekanism.api.IContentsListener;
import mekanism.api.math.FloatingLong;
import mekanism.common.block.attribute.AttributeEnergy;
import mekanism.common.capabilities.energy.MachineEnergyContainer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class EMExtraAdvancedFactoryEnergyContainer extends MachineEnergyContainer<TileEntityEMExtraAdvancedFactoryBase<?>> {

    public static EMExtraAdvancedFactoryEnergyContainer input(TileEntityEMExtraAdvancedFactoryBase<?> tile, @Nullable IContentsListener listener) {
        AttributeEnergy electricBlock = validateBlock(tile);
        return new EMExtraAdvancedFactoryEnergyContainer(electricBlock.getStorage(), electricBlock.getUsage(), notExternal, alwaysTrue, tile, listener);
    }

    private EMExtraAdvancedFactoryEnergyContainer(FloatingLong maxEnergy, FloatingLong energyPerTick, Predicate<AutomationType> canExtract, Predicate<AutomationType> canInsert, TileEntityEMExtraAdvancedFactoryBase<?> tile, @Nullable IContentsListener listener) {
        super(maxEnergy, energyPerTick, canExtract, canInsert, tile, listener);
    }

    @NotNull
    public FloatingLong getBaseEnergyPerTick() {
        return super.getBaseEnergyPerTick().add(this.tile.getRecipeEnergyRequired());
    }
}
