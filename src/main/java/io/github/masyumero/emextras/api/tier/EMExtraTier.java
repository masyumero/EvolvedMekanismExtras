package io.github.masyumero.emextras.api.tier;

import io.github.masyumero.emextras.common.util.EMExtraColorUtils;
import lombok.Getter;
import mekanism.api.SupportsColorMap;
import mekanism.api.math.MathUtils;
import net.minecraft.network.chat.TextColor;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.function.IntSupplier;

public enum EMExtraTier implements StringRepresentable, SupportsColorMap {
    ABSOLUTE_OVERCLOCKED("Absolute_Overclocked", EMExtraTierColorMap.absoluteOverclockedColor, MapColor.COLOR_LIGHT_GREEN),
    SUPREME_QUANTUM("Supreme_Quantum", EMExtraTierColorMap.supremeQuantumColor, MapColor.TERRACOTTA_PINK),
    COSMIC_DENSE("Cosmic_Dense", EMExtraTierColorMap.cosmicDenseColor, MapColor.DIAMOND),
    INFINITE_MULTIVERSAL("Infinite_Multiversal", EMExtraTierColorMap.infiniteMultiversalColor, MapColor.COLOR_MAGENTA);

    private static final EMExtraTier[] TIERS = values();

    private final String name;
    @Getter
    private final MapColor mapColor;
    @Getter
    private final IntSupplier rgbSupplier;
    private TextColor textColor;
    private int[] rgbCode;

    EMExtraTier(String name, IntSupplier rgbCode, MapColor mapColor) {
        this.name = name;
        this.mapColor = mapColor;
        this.rgbSupplier = rgbCode;
        setColorFromAtlas(EMExtraColorUtils.getRGBColor(rgbCode.getAsInt()));
    }

    public String getSimpleName() {
        return name;
    }

    public String getLowerName() {
        return getSimpleName().toLowerCase(Locale.ROOT);
    }

    @Override
    public int[] getRgbCode() {
        return rgbCode;
    }

    @Override
    public void setColorFromAtlas(int[] color) {
        this.rgbCode = color;
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