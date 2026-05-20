package io.github.masyumero.emextras.common.content.network.transmitter;

import com.jerry.mekanism_extras.api.tier.ExtraAlloyTier;
import mekanism.api.tier.AlloyTier;
import mekanism.api.tier.ITier;
import mekanism.common.upgrade.transmitter.TransmitterUpgradeData;
import org.jetbrains.annotations.NotNull;

public interface IEMExtraUpgradeableTransmitter<DATA extends TransmitterUpgradeData> {

    DATA getUpgradeData();

    boolean dataTypeMatches(@NotNull TransmitterUpgradeData data);

    void parseUpgradeData(@NotNull DATA data);

    ITier getTier();

    default boolean canUpgrade(ExtraAlloyTier extraAlloyTier, AlloyTier evolvedAlloyTier) {
        return extraAlloyTier.getAdvanceTier().ordinal() == getTier().getBaseTier().ordinal() + 1 &&
                evolvedAlloyTier.getBaseTier().ordinal() == getTier().getBaseTier().ordinal() + 6;
    }
}
