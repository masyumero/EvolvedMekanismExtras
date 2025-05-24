package io.github.masyumero.emextras.common.util;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.Level;

public class EMExtraWorldUtils {

    public static boolean isWorldLoaded(Level level) {
        if (level == null) {
            return false;
        }

        MinecraftServer server = level.getServer();
        if (server != null) {
            return !server.isStopped();
        }

        return level.isClientSide();
    }
}
