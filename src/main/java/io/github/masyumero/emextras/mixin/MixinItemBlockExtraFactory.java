package io.github.masyumero.emextras.mixin;

import com.jerry.mekextras.common.block.prefab.BlockExtraFactoryMachine;
import com.jerry.mekextras.common.item.block.machine.ItemBlockExtraFactory;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import mekanism.common.attachments.component.AttachedSideConfig;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.block.attribute.AttributeFactoryType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ItemBlockExtraFactory.class, remap = false)
public class MixinItemBlockExtraFactory {

    @SuppressWarnings("deprecation")
    @Inject(method = "getSideConfig", at = @At("HEAD"), cancellable = true)
    private static void getSideConfigInject(BlockExtraFactoryMachine.BlockExtraFactory<?> block, CallbackInfoReturnable<AttachedSideConfig> cir) {
        if (Attribute.getOrThrow(block.builtInRegistryHolder(), AttributeFactoryType.class).getFactoryType() == EMFactoryType.ALLOYING) {
            cir.setReturnValue(AttachedSideConfig.EXTRA_MACHINE);
        }
    }
}
