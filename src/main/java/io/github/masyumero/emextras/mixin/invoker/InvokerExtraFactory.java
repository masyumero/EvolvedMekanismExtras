package io.github.masyumero.emextras.mixin.invoker;

import com.jerry.mekextras.common.content.blocktype.ExtraFactory;
import com.jerry.mekextras.common.tier.ExtraFactoryTier;
import com.jerry.mekextras.common.tile.factory.TileEntityExtraFactory;
import mekanism.common.content.blocktype.FactoryType;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.function.Supplier;

@Mixin(value = ExtraFactory.class, remap = false)
public interface InvokerExtraFactory {

    @Invoker(value = "getAdvancedFactoryTILEAdvancedFactoryBuilder")
    static <TILE extends TileEntityExtraFactory<?>> ExtraFactory.ExtraFactoryBuilder<ExtraFactory<TILE>, TILE, ?> getAdvancedFactoryTILEAdvancedFactoryBuilderInvoker(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar, FactoryType type, ExtraFactoryTier tier) {
        throw new AssertionError();
    }
}
