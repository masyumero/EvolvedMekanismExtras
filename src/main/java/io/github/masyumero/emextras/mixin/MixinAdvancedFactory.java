package io.github.masyumero.emextras.mixin;

import com.jerry.mekaf.common.content.blocktype.AdvancedFactory;
import com.jerry.mekaf.common.tile.factory.base.TileEntityAdvancedFactoryBase;
import com.jerry.mekmm.common.content.blocktype.MoreMachineFactoryType;
import com.jerry.mekmm.common.content.blocktype.MoreMachineMachine;

import io.github.masyumero.emextras.common.block.attribute.EMExtraAttributeUpgradeable;
import io.github.masyumero.emextras.common.integration.mekaf.registries.EMExtraAdvancedFactoryBlocks;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;

import mekanism.api.text.ILangEntry;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tier.FactoryTier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

@Mixin(value = AdvancedFactory.class, remap = false)
public class MixinAdvancedFactory<TILE extends TileEntityAdvancedFactoryBase<?>> extends MoreMachineMachine.MoreMachineFactoryMachine<TILE> {

    public MixinAdvancedFactory(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntitySupplier, ILangEntry description, MoreMachineFactoryType factoryType) {
        super(tileEntitySupplier, description, factoryType);
    }

    @Inject(method = "<init>", at = @At(value = "RETURN"))
    private void initInject(Supplier tileEntityRegistrar, Supplier containerRegistrar, MoreMachineFactoryMachine origMachine, FactoryTier tier, CallbackInfo ci) {
        if (tier == FactoryTier.ULTIMATE) {
            add(new EMExtraAttributeUpgradeable(() -> EMExtraAdvancedFactoryBlocks.getEMExtraAdvancedFactory(EMExtraFactoryTier.ABSOLUTE_OVERCLOCKED, origMachine.getAdvancedFactoryType())));
        }
    }
}
