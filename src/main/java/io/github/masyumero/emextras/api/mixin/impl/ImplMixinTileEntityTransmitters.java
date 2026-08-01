package io.github.masyumero.emextras.api.mixin.impl;

import com.jerry.mekanism_extras.api.tier.ExtraAlloyTier;
import com.jerry.mekanism_extras.common.content.network.transmitter.IExtraUpgradeableTransmitter;
import io.github.masyumero.emextras.api.tier.EMExtraTier;
import io.github.masyumero.emextras.common.registry.EMExtraBlocks;
import mekanism.api.tier.AlloyTier;
import mekanism.common.block.states.BlockStateHelper;
import mekanism.common.block.states.TransmitterType;
import mekanism.common.content.network.transmitter.IUpgradeableTransmitter;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class ImplMixinTileEntityTransmitters {

    public static boolean emextras$canUpgrade(IUpgradeableTransmitter<?> upgradeableTransmitter, ExtraAlloyTier extraAlloyTier) {
        return extraAlloyTier.getAdvanceTier().ordinal() + 6 == upgradeableTransmitter.getTier().getBaseTier().ordinal() + 1;
    }

    public static boolean emextras$canUpgrade(IExtraUpgradeableTransmitter<?> upgradeableTransmitter, AlloyTier evolvedAlloyTier) {
        return evolvedAlloyTier.getBaseTier().ordinal() == upgradeableTransmitter.getTier().getBaseTier().ordinal() + 6 - 1;
    }

    @NotNull
    public static BlockState emextras$upgradeResult(@NotNull BlockState current, @NotNull EMExtraTier tier, @NotNull TransmitterType type) {
        return BlockStateHelper.copyStateData(current, switch (type) {
            case UNIVERSAL_CABLE -> switch (tier) {
                case ABSOLUTE_OVERCLOCKED -> EMExtraBlocks.ABSOLUTE_OVERCLOCKED_UNIVERSAL_CABLE;
                case SUPREME_QUANTUM -> EMExtraBlocks.SUPREME_QUANTUM_UNIVERSAL_CABLE;
                case COSMIC_DENSE -> EMExtraBlocks.COSMIC_DENSE_UNIVERSAL_CABLE;
                case INFINITE_MULTIVERSAL -> EMExtraBlocks.INFINITE_MULTIVERSAL_UNIVERSAL_CABLE;
            };
            case MECHANICAL_PIPE -> switch (tier) {
                case ABSOLUTE_OVERCLOCKED -> EMExtraBlocks.ABSOLUTE_OVERCLOCKED_MECHANICAL_PIPE;
                case SUPREME_QUANTUM -> EMExtraBlocks.SUPREME_QUANTUM_MECHANICAL_PIPE;
                case COSMIC_DENSE -> EMExtraBlocks.COSMIC_DENSE_MECHANICAL_PIPE;
                case INFINITE_MULTIVERSAL -> EMExtraBlocks.INFINITE_MULTIVERSAL_MECHANICAL_PIPE;
            };
            case PRESSURIZED_TUBE -> switch (tier) {
                case ABSOLUTE_OVERCLOCKED -> EMExtraBlocks.ABSOLUTE_OVERCLOCKED_PRESSURIZED_TUBE;
                case SUPREME_QUANTUM -> EMExtraBlocks.SUPREME_QUANTUM_PRESSURIZED_TUBE;
                case COSMIC_DENSE -> EMExtraBlocks.COSMIC_DENSE_PRESSURIZED_TUBE;
                case INFINITE_MULTIVERSAL -> EMExtraBlocks.INFINITE_MULTIVERSAL_PRESSURIZED_TUBE;
            };
            case LOGISTICAL_TRANSPORTER -> switch (tier) {
                case ABSOLUTE_OVERCLOCKED -> EMExtraBlocks.ABSOLUTE_OVERCLOCKED_LOGISTICAL_TRANSPORTER;
                case SUPREME_QUANTUM -> EMExtraBlocks.SUPREME_QUANTUM_LOGISTICAL_TRANSPORTER;
                case COSMIC_DENSE -> EMExtraBlocks.COSMIC_DENSE_LOGISTICAL_TRANSPORTER;
                case INFINITE_MULTIVERSAL -> EMExtraBlocks.INFINITE_MULTIVERSAL_LOGISTICAL_TRANSPORTER;
            };
            case THERMODYNAMIC_CONDUCTOR -> switch (tier) {
                case ABSOLUTE_OVERCLOCKED -> EMExtraBlocks.ABSOLUTE_OVERCLOCKED_THERMODYNAMIC_CONDUCTOR;
                case SUPREME_QUANTUM -> EMExtraBlocks.SUPREME_QUANTUM_THERMODYNAMIC_CONDUCTOR;
                case COSMIC_DENSE -> EMExtraBlocks.COSMIC_DENSE_THERMODYNAMIC_CONDUCTOR;
                case INFINITE_MULTIVERSAL -> EMExtraBlocks.INFINITE_MULTIVERSAL_THERMODYNAMIC_CONDUCTOR;
            };
            default -> throw new IllegalStateException("Unexpected value: " + type);
        });
    }
}
