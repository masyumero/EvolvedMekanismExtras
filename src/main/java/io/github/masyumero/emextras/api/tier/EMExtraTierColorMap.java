package io.github.masyumero.emextras.api.tier;

import io.github.masyumero.emextras.common.util.EMExtraColorUtils;
import mekanism.api.functions.FloatSupplier;

import java.util.function.IntSupplier;

public class EMExtraTierColorMap {

    private static final FloatSupplier spot = () -> {float spot = (float) ((System.currentTimeMillis() / 250) % 10) / 10;
        if (spot > 0.5) {
            spot = 1 - spot;
        }
        return spot * 2;
    };

    public static IntSupplier absoluteOverclockedColor = () -> EMExtraColorUtils.getInterpolatedColor(new int[]{237, 238, 70}, new int[]{0, 221, 0}, spot.getAsFloat());
    public static IntSupplier supremeQuantumColor = () -> EMExtraColorUtils.getInterpolatedColor(new int[]{166, 0, 2}, new int[]{252, 158, 250}, spot.getAsFloat());
    public static IntSupplier cosmicDenseColor = () -> EMExtraColorUtils.getInterpolatedColor(new int[]{75, 248, 255}, new int[]{253, 245, 95}, spot.getAsFloat());
    public static IntSupplier infiniteMultiversalColor = () -> EMExtraColorUtils.getInterpolatedColor(new int[]{247, 135, 255}, new int[]{90, 87, 90}, spot.getAsFloat());
}