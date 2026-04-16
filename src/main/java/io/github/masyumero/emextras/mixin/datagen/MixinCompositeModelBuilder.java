package io.github.masyumero.emextras.mixin.datagen;

import com.google.common.base.Preconditions;
import com.google.gson.JsonObject;
import io.github.masyumero.emextras.api.mixin.IMixinCompositeModelBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.CustomLoaderBuilder;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.loaders.CompositeModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Mixin(value = CompositeModelBuilder.class, remap = false)
public abstract class MixinCompositeModelBuilder<T extends ModelBuilder<T>> extends CustomLoaderBuilder<T> implements IMixinCompositeModelBuilder<T> {

    @Unique
    @Final private Map<String, ModelFile> emextras$childModelFiles = new LinkedHashMap<>();

    @Shadow @Final private List<String> itemRenderOrder;

    protected MixinCompositeModelBuilder(ResourceLocation loaderId, T parent, ExistingFileHelper existingFileHelper) {
        super(loaderId, parent, existingFileHelper);
    }

    @Inject(method = "toJson", at = @At(value = "TAIL"))
    private void toJsonInject(JsonObject json, CallbackInfoReturnable<JsonObject> cir) {
        JsonObject children = new JsonObject();
        for (Map.Entry<String, ModelFile> entry : emextras$childModelFiles.entrySet()) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("parent", entry.getValue().getUncheckedLocation().toString());
            children.add(entry.getKey(), jsonObject);
        }
        json.add("children", children);
    }

    @Unique
    @Override
    public IMixinCompositeModelBuilder<T> emextras$childParent(String name, ModelFile parentModelFile) {
        Preconditions.checkNotNull(name, "name must not be null");
        Preconditions.checkNotNull(parentModelFile, "parentModelFile must not be null");
        this.emextras$childModelFiles.put(name, parentModelFile);
        this.itemRenderOrder.add(name);
        return this;
    }
}
