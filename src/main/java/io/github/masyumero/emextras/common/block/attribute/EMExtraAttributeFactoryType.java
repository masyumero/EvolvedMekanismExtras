package io.github.masyumero.emextras.common.block.attribute;

import io.github.masyumero.emextras.common.content.blocktype.EMExtraFactoryType;
import lombok.Getter;
import mekanism.api.annotations.ParametersAreNotNullByDefault;
import mekanism.common.block.attribute.Attribute;

@Getter
@ParametersAreNotNullByDefault
public class EMExtraAttributeFactoryType implements Attribute {

    private final EMExtraFactoryType factoryType;

    public EMExtraAttributeFactoryType(EMExtraFactoryType factoryType) {
        this.factoryType = factoryType;
    }
}
