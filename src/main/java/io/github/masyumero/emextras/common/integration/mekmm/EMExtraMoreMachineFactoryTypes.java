package io.github.masyumero.emextras.common.integration.mekmm;

import com.jerry.mekmm.common.content.blocktype.MoreMachineFactoryType;

import java.util.List;

public final class EMExtraMoreMachineFactoryTypes {

    private EMExtraMoreMachineFactoryTypes() {
    }

    public static final List<MoreMachineFactoryType> SUPPORTED_FACTORY_TYPES = List.of(
            MoreMachineFactoryType.RECYCLING,
            MoreMachineFactoryType.PLANTING_STATION,
            MoreMachineFactoryType.CNC_STAMPING,
            MoreMachineFactoryType.CNC_LATHING,
            MoreMachineFactoryType.CNC_ROLLING_MILL,
            MoreMachineFactoryType.REPLICATING
    );

    public static boolean isSupported(MoreMachineFactoryType type) {
        return SUPPORTED_FACTORY_TYPES.contains(type);
    }
}
