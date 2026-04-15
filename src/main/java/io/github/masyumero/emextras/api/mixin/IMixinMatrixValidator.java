package io.github.masyumero.emextras.api.mixin;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import mekanism.common.lib.multiblock.FormationProtocol;
import mekanism.common.lib.multiblock.MultiblockData;
import net.minecraft.world.level.chunk.ChunkAccess;

public interface IMixinMatrixValidator {

    FormationProtocol.FormationResult emextras$postcheck(MultiblockData structure, Long2ObjectMap<ChunkAccess> chunkMap);
}
