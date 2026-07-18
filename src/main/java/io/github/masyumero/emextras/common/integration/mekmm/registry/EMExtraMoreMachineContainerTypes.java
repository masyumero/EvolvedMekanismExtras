package io.github.masyumero.emextras.common.integration.mekmm.registry;

import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.common.integration.mekmm.inventory.container.EMExtraMoreMachineFactoryContainer;
import io.github.masyumero.emextras.common.integration.mekmm.tile.TileEntityEMExtraMoreMachineFactory;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import mekanism.common.registration.impl.ContainerTypeDeferredRegister;
import mekanism.common.registration.impl.ContainerTypeRegistryObject;

import net.minecraftforge.eventbus.api.IEventBus;

public class EMExtraMoreMachineContainerTypes {

    private EMExtraMoreMachineContainerTypes() {}

    public static final ContainerTypeDeferredRegister CONTAINER_TYPES = new ContainerTypeDeferredRegister(EMExtras.MODID);

    public static final ContainerTypeRegistryObject<MekanismTileContainer<TileEntityEMExtraMoreMachineFactory<?>>> MORE_MACHINE_FACTORY = CONTAINER_TYPES.register("more_machine_factory", factoryClass(), EMExtraMoreMachineFactoryContainer::new);

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static Class<TileEntityEMExtraMoreMachineFactory<?>> factoryClass() {
        return (Class) TileEntityEMExtraMoreMachineFactory.class;
    }

    public static void register(IEventBus eventBus) {
        CONTAINER_TYPES.register(eventBus);
    }
}