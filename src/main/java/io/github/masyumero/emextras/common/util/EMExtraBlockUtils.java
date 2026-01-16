package io.github.masyumero.emextras.common.util;

import io.github.masyumero.emextras.common.content.blocktype.EMExtraFactoryType;
import io.github.masyumero.emextras.common.registry.EMExtrasBlock;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import mekanism.common.content.blocktype.FactoryType;
import mekanism.common.registration.impl.BlockRegistryObject;
import org.jetbrains.annotations.NotNull;

public class EMExtraBlockUtils {

    public static BlockRegistryObject<?, ?> getEMExtraFactory(@NotNull EMExtraFactoryTier tier, @NotNull FactoryType type) {
        for (EMExtraFactoryType factoryType : EMExtraEnumUtils.EMEXTRA_FACTORY_TYPES) {
            if (factoryType.getRegistryNameComponent().equals(type.getRegistryNameComponent())) {
                return EMExtrasBlock.getEMExtraFactory(tier, factoryType);
            }
        }
        return EMExtrasBlock.getEMExtraFactory(tier, EMExtraFactoryType.ALLOYING);
    }
}
