package io.github.masyumero.emextras.mixin;

import com.jerry.mekanism_extras.common.block.attribute.ExtraAttributeUpgradeable;
import com.jerry.mekanism_extras.common.registry.ExtraBlock;
import com.jerry.mekanism_extras.common.tier.AdvancedFactoryTier;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import io.github.masyumero.emextras.common.block.attribute.EMExtraAttributeUpgradeable;
import io.github.masyumero.emextras.common.registry.EMExtrasBlock;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import io.github.masyumero.emextras.common.util.EMExtraBlockUtils;
import mekanism.api.text.ILangEntry;
import mekanism.common.content.blocktype.BlockType;
import mekanism.common.content.blocktype.Factory;
import mekanism.common.content.blocktype.Machine;
import mekanism.common.tier.FactoryTier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

@Mixin(value = Factory.class, remap = false, priority = 1500)
public abstract class MixinFactory extends BlockType {

    public MixinFactory(ILangEntry description) {
        super(description);
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void initInject(Supplier tileEntityRegistrar, Supplier containerRegistrar, Machine.FactoryMachine origMachine, FactoryTier tier, CallbackInfo ci) {
        if (tier == FactoryTier.ULTIMATE) {
            add(new EMExtraAttributeUpgradeable(() -> EMExtraBlockUtils.getEMExtraFactory(EMExtraFactoryTier.ABSOLUTE_OVERCLOCKED, origMachine.getFactoryType())));
            if (origMachine.getFactoryType() != EMFactoryType.ALLOYING) {
                add(new ExtraAttributeUpgradeable(() -> ExtraBlock.getAdvancedFactory(AdvancedFactoryTier.ABSOLUTE, origMachine.getFactoryType())));
            } else {
                add(new ExtraAttributeUpgradeable(() -> EMExtrasBlock.getAdvancedFactory(AdvancedFactoryTier.ABSOLUTE, EMFactoryType.ALLOYING)));
            }
        }
    }
}
