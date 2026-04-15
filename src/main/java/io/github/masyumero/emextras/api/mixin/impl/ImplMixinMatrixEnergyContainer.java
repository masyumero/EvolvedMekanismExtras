package io.github.masyumero.emextras.api.mixin.impl;

import io.github.masyumero.emextras.common.tier.EMExtraIPTier;
import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.api.energy.IEnergyContainer;
import mekanism.api.math.FloatingLong;
import net.minecraft.core.BlockPos;

import java.util.Map;
import java.util.Set;

public class ImplMixinMatrixEnergyContainer {

    public static void emextras$invalidate(Map<BlockPos, EMExtraIPTier> providers, Map<BlockPos, IEnergyContainer> cells) {
        cells.clear();
        providers.clear();
    }

    public static void emextras$tick(Set<BlockPos> invalidPositions, Map<BlockPos, EMExtraIPTier> providers, Map<BlockPos, IEnergyContainer> cells) {
        if (!invalidPositions.isEmpty()) {
            for (BlockPos invalidPosition : invalidPositions) {
                cells.remove(invalidPosition);
                providers.remove(invalidPosition);
            }
            invalidPositions.clear();
        }
    }

    public static void emextras$addEnergy(FloatingLong energy, Map<BlockPos, IEnergyContainer> cells) {
        for (IEnergyContainer container : cells.values()) {
            //Note: inserting into the cell's energy container handles marking the cell for saving if it changes
            FloatingLong remainder = container.insert(energy, Action.EXECUTE, AutomationType.INTERNAL);
            if (remainder.smallerThan(energy)) {
                //Our cell accepted at least some energy
                if (remainder.isZero()) {
                    //Check less than equal rather than just equal in case something went wrong
                    // and break if we don't have any energy left to add
                    break;
                }
                energy = remainder;
            }
        }
    }

    public static void emextras$removeEnergy(FloatingLong energy, Map<BlockPos, IEnergyContainer> cells) {
        for (IEnergyContainer container : cells.values()) {
            //Note: extracting from the cell's energy container handles marking the cell for saving if it changes
            FloatingLong extracted = container.extract(energy, Action.EXECUTE, AutomationType.INTERNAL);
            if (!extracted.isZero()) {
                energy = energy.minusEqual(extracted);
                if (energy.isZero()) {
                    //Check less than equal rather than just equal in case something went wrong
                    // and break if we don't need to remove any more energy
                    break;
                }
            }
        }
    }

    public static int emextras$getCells() {
        return 0;
    }

    public static int emextras$getProviders() {
        return 0;
    }
}
