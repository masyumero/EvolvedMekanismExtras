package io.github.masyumero.emextras.common.util;

import io.github.masyumero.emextras.api.tier.EMExtraTier;
import io.github.masyumero.emextras.common.content.network.transmitter.EMExtraLogisticalTransporter;

import mekanism.api.text.EnumColor;
import mekanism.api.tier.BaseTier;

import java.util.List;

public class EMExtraTransporterUtils {

    public static final List<EnumColor> colors = List.of(EnumColor.DARK_BLUE, EnumColor.DARK_GREEN, EnumColor.DARK_AQUA, EnumColor.DARK_RED, EnumColor.PURPLE,
            EnumColor.INDIGO, EnumColor.BRIGHT_GREEN, EnumColor.AQUA, EnumColor.RED, EnumColor.PINK, EnumColor.YELLOW, EnumColor.BLACK);

    public static void incrementColor(EMExtraLogisticalTransporter tile) {
        EnumColor color = tile.getColor();
        if (color == null) {
            tile.setColor(colors.get(0));
        } else {
            int index = colors.indexOf(color);
            if (index == colors.size() - 1) {
                tile.setColor(null);
            } else {
                tile.setColor(colors.get(index + 1));
            }
        }
    }

    public static EMExtraTier baseToEMExtraTier(BaseTier tier) {
        if (!(tier.ordinal() < 4)) {
            return EMExtraTier.ABSOLUTE_OVERCLOCKED;
        }
        return switch (tier) {
            case ADVANCED -> EMExtraTier.SUPREME_QUANTUM;
            case ELITE -> EMExtraTier.COSMIC_DENSE;
            case ULTIMATE -> EMExtraTier.INFINITE_MULTIVERSAL;
            default -> EMExtraTier.ABSOLUTE_OVERCLOCKED; //BASIC & CREATIVE
        };
    }
}
