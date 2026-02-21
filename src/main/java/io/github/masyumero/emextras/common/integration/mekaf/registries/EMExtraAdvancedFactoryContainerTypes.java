package io.github.masyumero.emextras.common.integration.mekaf.registries;

import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.common.integration.mekaf.inventory.container.tile.EMExtraAdvancedFactoryContainer;
import io.github.masyumero.emextras.common.integration.mekaf.tile.factory.TileEntityEMExtraAdvancedBase;

import mekanism.common.inventory.container.tile.MekanismTileContainer;
import mekanism.common.registration.impl.ContainerTypeDeferredRegister;
import mekanism.common.registration.impl.ContainerTypeRegistryObject;
import net.neoforged.bus.api.IEventBus;

public class EMExtraAdvancedFactoryContainerTypes {

    private EMExtraAdvancedFactoryContainerTypes() {}

    public static final ContainerTypeDeferredRegister AF_CONTAINER_TYPES = new ContainerTypeDeferredRegister(EMExtras.MODID);

    public static ContainerTypeRegistryObject<MekanismTileContainer<TileEntityEMExtraAdvancedBase<?>>> ADVANCED_FACTORY = AF_CONTAINER_TYPES.register("advanced_factory", advancedFactoryClass(), EMExtraAdvancedFactoryContainer::new);

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static Class<TileEntityEMExtraAdvancedBase<?>> advancedFactoryClass() {
        return (Class) TileEntityEMExtraAdvancedBase.class;
    }

    public static void register(IEventBus modEventBus) {
        AF_CONTAINER_TYPES.register(modEventBus);
    }
}
