package io.github.masyumero.emextras.common.content.blocktype;

import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import mekanism.common.content.blocktype.BlockShapes;
import mekanism.common.content.blocktype.FactoryType;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class EMExtraBlockShapes {
    public static VoxelShape[] getShape(FactoryType type) {
        return type == EMFactoryType.ALLOYING ? BlockShapes.SMELTING_FACTORY : switch (type) {
            case SMELTING -> BlockShapes.SMELTING_FACTORY;
            case ENRICHING -> BlockShapes.ENRICHING_FACTORY;
            case CRUSHING -> BlockShapes.CRUSHING_FACTORY;
            case COMPRESSING -> BlockShapes.COMPRESSING_FACTORY;
            case COMBINING -> BlockShapes.COMBINING_FACTORY;
            case PURIFYING -> BlockShapes.PURIFYING_FACTORY;
            case INJECTING -> BlockShapes.INJECTING_FACTORY;
            case INFUSING -> BlockShapes.INFUSING_FACTORY;
            case SAWING -> BlockShapes.SAWING_FACTORY;
        };
    }
}
