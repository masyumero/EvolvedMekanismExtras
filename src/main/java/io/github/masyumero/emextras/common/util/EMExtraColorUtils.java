package io.github.masyumero.emextras.common.util;

public class EMExtraColorUtils {
    public static int red(int color) {
        return color >> 16 & 255;
    }

    public static int green(int color) {
        return color >> 8 & 255;
    }

    public static int blue(int color) {
        return color & 255;
    }

    public static int[] getRGBColor(int color) {
        return new int[] {red(color), green(color), blue(color)};
    }

    public static int getInterpolatedColor(int[] color1, int[] color2, float ratio) {
        int red1 = color1[0];
        int green1 = color1[1];
        int blue1 = color1[2];

        int red2 = color2[0];
        int green2 = color2[1];
        int blue2 = color2[2];

        int red = (int) (red1 + ratio * (red2 - red1));
        int green = (int) (green1 + ratio * (green2 - green1));
        int blue = (int) (blue1 + ratio * (blue2 - blue1));

        return (red << 16) | (green << 8) | blue;
    }
}
