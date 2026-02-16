package io.github.masyumero.emextras.api.tier;

import lombok.Getter;
import mekanism.api.SupportsColorMap;
import mekanism.api.math.MathUtils;
import net.minecraft.network.chat.TextColor;
import net.minecraft.util.FastColor;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public enum EMExtraTier implements StringRepresentable, SupportsColorMap {
    ABSOLUTE_OVERCLOCKED("Absolute_Overclocked", new int[]{95, 255, 184}, MapColor.COLOR_LIGHT_GREEN),
    SUPREME_QUANTUM("Supreme_Quantum", new int[]{255, 128, 106}, MapColor.TERRACOTTA_PINK),
    COSMIC_DENSE("Cosmic_Dense", new int[]{75, 248, 255}, MapColor.DIAMOND),
    INFINITE_MULTIVERSAL("Infinite_Multiversal", new int[]{247, 135, 255}, MapColor.COLOR_MAGENTA);

    private static final EMExtraTier[] TIERS = values();

    private final String name;
    @Getter
    private final MapColor mapColor;
    private TextColor textColor;
    private int argb;
    private int[] rgbCode;

    EMExtraTier(String name, int[] rgbCode, MapColor mapColor) {
        this.name = name;
        this.mapColor = mapColor;
        setColorFromAtlas(rgbCode);
    }

    public String getSimpleName() {
        return name;
    }

    public String getLowerName() {
        return getSimpleName().toLowerCase(Locale.ROOT);
    }

    @Override
    public int getPackedColor() {
        return argb;
    }

    @Override
    public int[] getRgbCode() {
        return rgbCode;
    }

    @Override
    public void setColorFromAtlas(int[] color) {
        this.rgbCode = color;
        this.argb = FastColor.ARGB32.color(this.rgbCode[0], this.rgbCode[1], this.rgbCode[2]);
        this.textColor = TextColor.fromRgb(rgbCode[0] << 16 | rgbCode[1] << 8 | rgbCode[2]);
    }

    public TextColor getColor() {
        return this.textColor;
    }

    @NotNull
    @Override
    public String getSerializedName() {
        return name().toLowerCase(Locale.ROOT);
    }

    public static EMExtraTier byIndexStatic(int index) {
        return MathUtils.getByIndexMod(TIERS, index);
    }
}