package io.github.masyumero.emextras.mixin;

import com.jerry.mekextras.common.block.attribute.ExtraAttributeUpgradeable;
import com.jerry.mekextras.common.content.blocktype.ExtraFactory;
import com.jerry.mekextras.common.content.blocktype.ExtraMachine;
import com.jerry.mekextras.common.registries.ExtraBlockTypes;
import com.jerry.mekextras.common.registries.ExtraContainerTypes;
import com.jerry.mekextras.common.tier.ExtraFactoryTier;
import com.jerry.mekextras.common.tile.factory.TileEntityExtraFactory;
import com.jerry.mekextras.common.util.ExtraEnumUtils;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import io.github.masyumero.emextras.common.registry.EMExtraBlockTypes;
import io.github.masyumero.emextras.common.registry.EMExtraBlocks;
import io.github.masyumero.emextras.mixin.invoker.InvokerExtraFactoryBuilder;
import mekanism.api.text.ILangEntry;
import mekanism.common.content.blocktype.FactoryType;
import mekanism.common.content.blocktype.Machine;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tile.base.TileEntityMekanism;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Supplier;

@Mixin(value = ExtraFactory.class, remap = false)
public abstract class MixinExtraFactory<TILE extends TileEntityMekanism> extends Machine<TILE> {

    public MixinExtraFactory(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntitySupplier, ILangEntry description, FactoryType factoryType) {
        super(tileEntitySupplier, description);
    }

    @Inject(method = "<init>", at = @At(value = "RETURN"))
    private void initInject(Supplier tileEntityRegistrar, Supplier containerRegistrar, ExtraMachine.ExtraFactoryMachine origMachine, ExtraFactoryTier tier, CallbackInfo ci) {
        if (tier.ordinal() < ExtraEnumUtils.EXTRA_FACTORY_TIERS.length) {
            if (origMachine.getFactoryType().equals(EMFactoryType.ALLOYING)) {
                add(new ExtraAttributeUpgradeable(() -> EMExtraBlocks.getExtraFactory(ExtraEnumUtils.EXTRA_FACTORY_TIERS[tier.ordinal() + 1], EMFactoryType.ALLOYING)));
            }
        }
    }

    @Inject(method = "getAdvancedFactoryTILEAdvancedFactoryBuilder", at = @At(value = "HEAD"), cancellable = true)
    private static <TILE extends TileEntityExtraFactory<?>> void getAdvancedFactoryTILEAdvancedFactoryBuilderInject(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar, FactoryType type, ExtraFactoryTier tier, CallbackInfoReturnable<ExtraFactory.ExtraFactoryBuilder<ExtraFactory<TILE>, TILE, ?>> cir) {
        ExtraFactory.ExtraFactoryBuilder<ExtraFactory<TILE>, TILE, ?> builder = InvokerExtraFactoryBuilder.newExtraFactoryBuilder(new ExtraFactory<>(tileEntityRegistrar,
                () -> ExtraContainerTypes.FACTORY,
                type.equals(EMFactoryType.ALLOYING) ? EMExtraBlockTypes.EXTRA_ALLOYER : switch (type) {
                    case SAWING -> ExtraBlockTypes.PRECISION_SAWMILL;
                    case SMELTING -> ExtraBlockTypes.ENERGIZED_SMELTER;
                    case ENRICHING -> ExtraBlockTypes.ENRICHMENT_CHAMBER;
                    case CRUSHING -> ExtraBlockTypes.CRUSHER;
                    case COMPRESSING -> ExtraBlockTypes.OSMIUM_COMPRESSOR;
                    case COMBINING -> ExtraBlockTypes.COMBINER;
                    case PURIFYING -> ExtraBlockTypes.PURIFICATION_CHAMBER;
                    case INJECTING -> ExtraBlockTypes.CHEMICAL_INJECTION_CHAMBER;
                    case INFUSING -> ExtraBlockTypes.METALLURGIC_INFUSER;
                },
                tier));
        // Note, we can't just return the builder here as then it gets all confused about object types, so we just
        // assign the value here, and then return the builder itself as it is the same object
        if (type.equals(EMFactoryType.ALLOYING)) {
            builder.withComputerSupport(tier.getAdvanceTier().getLowerName() + type.getRegistryNameComponentCapitalized() + "Factory");
        } else {
            builder.withComputerSupport(tier.getAdvanceTier().getLowerName() + "AlloyingFactory");
        }
        cir.setReturnValue(builder);
    }
}