package io.github.masyumero.emextras;

import com.mojang.logging.LogUtils;
import io.github.masyumero.emextras.common.config.LoadConfig;
import io.github.masyumero.emextras.common.integration.EMExtraHooks;
import io.github.masyumero.emextras.common.network.EMExtraPacketHandler;
import io.github.masyumero.emextras.common.registry.EMExtraBlocks;
import io.github.masyumero.emextras.common.registry.EMExtraContainerTypes;
import io.github.masyumero.emextras.common.registry.EMExtraItems;
import io.github.masyumero.emextras.common.registry.EMExtraTabs;
import io.github.masyumero.emextras.common.registry.EMExtraTileEntityTypes;
import mekanism.common.base.IModModule;
import mekanism.common.lib.Version;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(EMExtras.MODID)
public class EMExtras implements IModModule {

    public static final String MODID = "emextras";
    public static final String MOD_NAME = "EvolvedMekanismExtras";

    private final EMExtraPacketHandler emExtraPacketHandler;

    public final Version versionNumber;

    public static EMExtras instance;

    public static EMExtraHooks hooks = new EMExtraHooks();

    public static final Logger LOGGER = LogUtils.getLogger();

    public EMExtras(ModContainer modContainer, IEventBus modEventBus) {
        instance = this;
        versionNumber = new Version(modContainer);
        LoadConfig.registerConfig(modContainer);
        modEventBus.addListener(LoadConfig::onConfigLoad);
        addRegistrationListeners(modEventBus);

        emExtraPacketHandler = new EMExtraPacketHandler(modEventBus, versionNumber);
    }

    private void addRegistrationListeners(IEventBus modEventBus) {
        EMExtraItems.register(modEventBus);
        EMExtraBlocks.register(modEventBus);
        EMExtraTileEntityTypes.register(modEventBus);
        EMExtraContainerTypes.register(modEventBus);
        EMExtraTabs.register(modEventBus);
    }

    public static EMExtraPacketHandler emExtraPacketHandler() {
        return instance.emExtraPacketHandler;
    }

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

    @Override
    public Version getVersion() {
        return versionNumber;
    }

    @Override
    public String getName() {
        return "EMExtra";
    }
}
