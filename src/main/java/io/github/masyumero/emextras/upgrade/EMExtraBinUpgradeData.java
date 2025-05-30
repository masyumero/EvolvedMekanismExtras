package io.github.masyumero.emextras.upgrade;

import io.github.masyumero.emextras.common.inventory.slot.EMExtraBinInventorySlot;
import mekanism.common.upgrade.IUpgradeData;

public record EMExtraBinUpgradeData(boolean redstone, EMExtraBinInventorySlot binSlot) implements IUpgradeData {
}
