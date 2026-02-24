package io.github.masyumero.emextras.mixin.datagen;

import com.google.common.base.Preconditions;
import com.google.gson.JsonObject;
import com.llamalad7.mixinextras.sugar.Local;
import io.github.masyumero.emextras.api.mixin.IMixinCompositeModelBuilder;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.CustomLoaderBuilder;
import net.neoforged.neoforge.client.model.generators.ModelBuilder;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.loaders.CompositeModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
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

    protected MixinCompositeModelBuilder(ResourceLocation loaderId, T parent, ExistingFileHelper existingFileHelper, boolean allowInlineElements) {
        super(loaderId, parent, existingFileHelper, allowInlineElements);
    }

    @Inject(method = "toJson", at = @At(value = "INVOKE", target = "Ljava/util/Set;iterator()Ljava/util/Iterator;"))
    private void toJsonInject(JsonObject json, CallbackInfoReturnable<JsonObject> cir, @Local(name = "children") JsonObject children) {
        var var8 = this.emextras$childModelFiles.entrySet().iterator();
        while (var8.hasNext()) {
            Map.Entry<String, ModelFile> entry = var8.next();
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("parent", entry.getValue().getUncheckedLocation().toString());
            children.add(entry.getKey(), jsonObject);
        }
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
