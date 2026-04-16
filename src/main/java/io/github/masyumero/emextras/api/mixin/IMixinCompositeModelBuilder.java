package io.github.masyumero.emextras.api.mixin;

import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;

public interface IMixinCompositeModelBuilder<T extends ModelBuilder<T>> {

    IMixinCompositeModelBuilder<T> emextras$childParent(String name, ModelFile parentModelFile);
}
