package io.github.masyumero.emextras.api.tier;

import com.jerry.mekextras.api.tier.AdvancedTier;
import com.jerry.mekextras.common.tier.TierColor;
import io.github.masyumero.emextras.common.util.EMExtraColorUtils;
import mekanism.api.functions.FloatSupplier;

import java.util.function.IntSupplier;

public class EMExtraTierColorMap {

    private static FloatSupplier spot = () -> {
        float spot = (float) ((System.currentTimeMillis() / 25) % 100) / 100;
        if (spot > 0.5) {
            spot = 1 - spot;
        }
        return spot * 2;
    };

    public static IntSupplier absoluteOverclockedColor = () -> EMExtraColorUtils.getInterpolatedColor(TierColor.getRgb(AdvancedTier.ABSOLUTE), new int[]{0, 221, 0}, spot.getAsFloat());
    public static IntSupplier supremeQuantumColor = () -> EMExtraColorUtils.getInterpolatedColor(TierColor.getRgb(AdvancedTier.SUPREME), new int[]{252, 158, 250}, spot.getAsFloat());
    public static IntSupplier cosmicDenseColor = () -> EMExtraColorUtils.getInterpolatedColor(TierColor.getRgb(AdvancedTier.COSMIC), new int[]{253, 245, 95}, spot.getAsFloat());
    public static IntSupplier infiniteMultiversalColor = () -> EMExtraColorUtils.getInterpolatedColor(TierColor.getRgb(AdvancedTier.INFINITE), new int[]{90, 87, 90}, spot.getAsFloat());
}
