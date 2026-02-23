package io.github.masyumero.emextras.common.integration.mekmm.registries;

import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.common.integration.mekmm.inventory.container.tile.EMExtraMoreMachineFactoryContainer;
import io.github.masyumero.emextras.common.integration.mekmm.tile.factory.TileEntityEMExtraMoreMachineFactory;

import mekanism.common.inventory.container.tile.MekanismTileContainer;
import mekanism.common.registration.impl.ContainerTypeDeferredRegister;
import mekanism.common.registration.impl.ContainerTypeRegistryObject;
import net.neoforged.bus.api.IEventBus;

public class EMExtraMoreMachineContainerTypes {

    private EMExtraMoreMachineContainerTypes() {}

    public static final ContainerTypeDeferredRegister MM_CONTAINER_TYPES = new ContainerTypeDeferredRegister(EMExtras.MODID);

    public static final ContainerTypeRegistryObject<MekanismTileContainer<TileEntityEMExtraMoreMachineFactory<?>>> MORE_MACHINE_FACTORY = MM_CONTAINER_TYPES.register("more_machine_factory", factoryClass(), EMExtraMoreMachineFactoryContainer::new);

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static Class<TileEntityEMExtraMoreMachineFactory<?>> factoryClass() {
        return (Class) TileEntityEMExtraMoreMachineFactory.class;
    }

    public static void register(IEventBus modEventBus) {
        MM_CONTAINER_TYPES.register(modEventBus);
    }
}
