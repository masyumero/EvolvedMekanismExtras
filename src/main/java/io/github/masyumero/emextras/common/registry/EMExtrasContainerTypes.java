package io.github.masyumero.emextras.common.registry;

import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.common.inventory.container.tile.EMExtraFactoryContainer;
import io.github.masyumero.emextras.common.tile.factory.TileEntityEMExtraFactory;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import mekanism.common.registration.impl.ContainerTypeDeferredRegister;
import mekanism.common.registration.impl.ContainerTypeRegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;

public class EMExtrasContainerTypes {
    public static final ContainerTypeDeferredRegister CONTAINER_TYPES = new ContainerTypeDeferredRegister(EMExtras.MODID);

    public static final ContainerTypeRegistryObject<MekanismTileContainer<TileEntityEMExtraFactory<?>>> FACTORY = CONTAINER_TYPES.register("factory", factoryClass(), EMExtraFactoryContainer::new);

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static Class<TileEntityEMExtraFactory<?>> factoryClass() {
        return (Class) TileEntityEMExtraFactory.class;
    }

    public static void register(IEventBus eventBus) {
        CONTAINER_TYPES.register(eventBus);
    }
}