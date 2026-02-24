package io.github.masyumero.emextras.api.mixin;

import net.neoforged.neoforge.client.model.generators.ModelBuilder;
import net.neoforged.neoforge.client.model.generators.ModelFile;

public interface IMixinCompositeModelBuilder<T extends ModelBuilder<T>> {

    IMixinCompositeModelBuilder<T> emextras$childParent(String name, ModelFile parentModelFile);
}
