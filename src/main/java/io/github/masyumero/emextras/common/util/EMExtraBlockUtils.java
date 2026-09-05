package io.github.masyumero.emextras.common.util;

import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import io.github.masyumero.emextras.common.registry.EMExtraBlocks;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import mekanism.common.content.blocktype.FactoryType;
import mekanism.common.registration.impl.BlockRegistryObject;
import org.jetbrains.annotations.NotNull;

public class EMExtraBlockUtils {

    public static BlockRegistryObject<?, ?> getEMExtraFactory(@NotNull EMExtraFactoryTier tier, @NotNull FactoryType type) {
        for (FactoryType factoryType : EMExtraEnumUtils.FACTORY_TYPES) {
            if (factoryType.getRegistryNameComponent().equals(type.getRegistryNameComponent())) {
                return EMExtraBlocks.getEMExtraFactory(tier, factoryType);
            }
        }
        return EMExtraBlocks.getEMExtraFactory(tier, EMFactoryType.ALLOYING);
    }
}
