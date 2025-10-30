package io.github.masyumero.emextras.mixin;

import com.bawnorton.mixinsquared.adjuster.tools.AdjustableAnnotationNode;
import com.bawnorton.mixinsquared.api.MixinAnnotationAdjuster;
import org.objectweb.asm.tree.MethodNode;
import org.spongepowered.asm.mixin.injection.Inject;

import java.util.List;
import java.util.Objects;

public class EMExtraMixinAnnotationAdjuster implements MixinAnnotationAdjuster {

    @Override
    public AdjustableAnnotationNode adjust(List<String> targetClassNames, String mixinClassName, MethodNode handlerNode, AdjustableAnnotationNode annotationNode) {
        System.out.println(mixinClassName);

        if(Objects.equals(mixinClassName, "com.jerry.mekanism_extras.mixin.MixinFactory") && annotationNode.is(Inject.class)) {
            handlerNode.name = "emextra$disabled";
            return null;
        }
        return annotationNode;
    }
}
