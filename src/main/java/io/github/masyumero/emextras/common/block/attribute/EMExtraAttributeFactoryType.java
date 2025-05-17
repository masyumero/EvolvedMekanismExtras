package io.github.masyumero.emextras.common.block.attribute;

import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import io.github.masyumero.emextras.common.content.blocktype.EMExtraFactoryType;
import mekanism.common.block.attribute.Attribute;
import org.jetbrains.annotations.NotNull;

public class EMExtraAttributeFactoryType implements Attribute {

    private final EMExtraFactoryType type;

    public EMExtraAttributeFactoryType(EMExtraFactoryType type) {
        this.type = type;
    }

    @NotNull
    public EMExtraFactoryType getFactoryType() {
        return type;
    }
}
