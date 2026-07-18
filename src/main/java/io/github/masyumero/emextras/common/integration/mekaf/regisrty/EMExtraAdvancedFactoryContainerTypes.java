package io.github.masyumero.emextras.common.integration.mekaf.regisrty;

import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.common.integration.mekaf.inventory.container.EMExtraAdvancedFactoryContainer;
import io.github.masyumero.emextras.common.integration.mekaf.tile.factory.base.TileEntityEMExtraAdvancedFactoryBase;

import mekanism.common.inventory.container.tile.MekanismTileContainer;
import mekanism.common.registration.impl.ContainerTypeDeferredRegister;
import mekanism.common.registration.impl.ContainerTypeRegistryObject;

import net.minecraftforge.eventbus.api.IEventBus;

public class EMExtraAdvancedFactoryContainerTypes {

    private EMExtraAdvancedFactoryContainerTypes() {}

    public static final ContainerTypeDeferredRegister CONTAINER_TYPES = new ContainerTypeDeferredRegister(EMExtras.MODID);

    public static final ContainerTypeRegistryObject<MekanismTileContainer<TileEntityEMExtraAdvancedFactoryBase<?>>> ADVANCED_FACTORY = CONTAINER_TYPES.register("advanced_factory", factoryClass(), EMExtraAdvancedFactoryContainer::new);

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static Class<TileEntityEMExtraAdvancedFactoryBase<?>> factoryClass() {
        return (Class) TileEntityEMExtraAdvancedFactoryBase.class;
    }

    public static void register(IEventBus eventBus) {
        CONTAINER_TYPES.register(eventBus);
    }
}