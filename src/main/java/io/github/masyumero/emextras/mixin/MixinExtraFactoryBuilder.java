package io.github.masyumero.emextras.mixin;

import com.jerry.mekextras.common.content.blocktype.ExtraFactory;
import com.jerry.mekextras.common.tier.ExtraFactoryTier;
import com.jerry.mekextras.common.tile.factory.TileEntityExtraFactory;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import io.github.masyumero.emextras.mixin.invoker.InvokerExtraFactory;
import mekanism.common.block.attribute.AttributeParticleFX;
import mekanism.common.block.attribute.AttributeSideConfig;
import mekanism.common.content.blocktype.BlockShapes;
import mekanism.common.content.blocktype.FactoryType;
import mekanism.common.lib.math.Pos3D;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import net.minecraft.core.particles.ParticleTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Supplier;

@Mixin(value = ExtraFactory.ExtraFactoryBuilder.class, remap = false)
public abstract class MixinExtraFactoryBuilder {

    @Inject(method = "createFactory", at = @At(value = "HEAD"), cancellable = true)
    private static <TILE extends TileEntityExtraFactory<?>> void createFactoryInject(Supplier<?> tileEntityRegistrar, FactoryType type, ExtraFactoryTier tier, CallbackInfoReturnable<ExtraFactory.ExtraFactoryBuilder<ExtraFactory<TILE>, TILE, ?>> cir) {
        ExtraFactory.ExtraFactoryBuilder<ExtraFactory<TILE>, TILE, ?> builder = InvokerExtraFactory.getAdvancedFactoryTILEAdvancedFactoryBuilderInvoker((Supplier<TileEntityTypeRegistryObject<TILE>>) tileEntityRegistrar, type, tier);
        builder.withCustomShape(BlockShapes.getShape(null, type));
        if (type.equals(EMFactoryType.ALLOYING)) {
            builder.with(AttributeSideConfig.ELECTRIC_MACHINE);
        } else {
            builder.with(switch (type) {
                case SMELTING, ENRICHING, CRUSHING, COMBINING, SAWING -> AttributeSideConfig.ELECTRIC_MACHINE;
                case COMPRESSING, INJECTING, PURIFYING, INFUSING -> AttributeSideConfig.ADVANCED_ELECTRIC_MACHINE;
            });
        }
        builder.replace(new AttributeParticleFX().addDense(ParticleTypes.SMOKE, 5, rand -> new Pos3D(
                rand.nextFloat() * 0.7F - 0.3F,
                rand.nextFloat() * 0.1F + 0.7F,
                rand.nextFloat() * 0.7F - 0.3F)));
        cir.setReturnValue(builder);
    }
}
