package io.github.masyumero.emextras.mixin.invoker;

import com.jerry.mekextras.common.content.blocktype.ExtraFactory;
import com.jerry.mekextras.common.content.blocktype.ExtraMachine;
import com.jerry.mekextras.common.tile.factory.TileEntityExtraFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value = ExtraFactory.ExtraFactoryBuilder.class, remap = false)
public interface InvokerExtraFactoryBuilder {

    @Invoker(value = "<init>")
    static <FACTORY extends ExtraFactory<TILE>, TILE extends TileEntityExtraFactory<?>, T extends ExtraMachine.ExtraMachineBuilder<FACTORY, TILE, T>>  ExtraFactory.ExtraFactoryBuilder<ExtraFactory<TILE>, TILE, ?> newExtraFactoryBuilder(FACTORY holder) {
        throw new AssertionError();
    }
}
