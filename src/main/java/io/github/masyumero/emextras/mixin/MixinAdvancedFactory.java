package io.github.masyumero.emextras.mixin;

import com.jerry.mekanism_extras.common.block.attribute.ExtraAttributeUpgradeable;
import com.jerry.mekanism_extras.common.content.blocktype.AdvancedFactory;
import com.jerry.mekanism_extras.common.content.blocktype.AdvancedMachine;
import com.jerry.mekanism_extras.common.registry.ExtraBlock;
import com.jerry.mekanism_extras.common.registry.ExtraBlockType;
import com.jerry.mekanism_extras.common.registry.ExtraContainerTypes;
import com.jerry.mekanism_extras.common.tier.AdvancedFactoryTier;
import com.jerry.mekanism_extras.common.tile.factory.TileEntityExtraFactory;
import com.jerry.mekanism_extras.common.util.ExtraEnumUtils;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import io.github.masyumero.emextras.common.registry.EMExtrasBlock;
import io.github.masyumero.emextras.common.registry.EMExtrasBlockType;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.content.blocktype.FactoryType;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.reflect.Constructor;
import java.util.function.Supplier;

@Mixin(value = AdvancedFactory.class, remap = false)
public abstract class MixinAdvancedFactory<TILE extends TileEntityExtraFactory<?>> extends AdvancedMachine.AdvancedFactoryMachine<TILE> {

    public MixinAdvancedFactory(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntitySupplier, MekanismLang description, FactoryType factoryType) {
        super(tileEntitySupplier, description, factoryType);
    }

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lcom/jerry/mekanism_extras/common/content/blocktype/AdvancedFactory;add([Lmekanism/common/block/attribute/Attribute;)V", ordinal = 1))
    private void emextras$disabled(AdvancedFactory instance, Attribute[] attributes) {
    }

    @Inject(method = "<init>", at = @At(value = "RETURN"))
    private void initInject(Supplier tileEntityRegistrar, Supplier containerRegistrar, AdvancedMachine.AdvancedFactoryMachine origMachine, AdvancedFactoryTier tier, CallbackInfo ci) {
        if (tier.ordinal() < ExtraEnumUtils.ADVANCED_FACTORY_TIERS.length) {
            if (origMachine.getFactoryType() == EMFactoryType.ALLOYING) {
                add(new ExtraAttributeUpgradeable(() -> EMExtrasBlock.getAdvancedFactory(ExtraEnumUtils.ADVANCED_FACTORY_TIERS[tier.ordinal() + 1], EMFactoryType.ALLOYING)));
            } else {
                add(new ExtraAttributeUpgradeable(() -> ExtraBlock.getAdvancedFactory(ExtraEnumUtils.ADVANCED_FACTORY_TIERS[tier.ordinal() + 1], origMachine.getFactoryType())));
            }
        }
    }

    @Inject(method = "getAdvancedFactoryTILEAdvancedFactoryBuilder", at = @At("HEAD"), cancellable = true, remap = false)
    @SuppressWarnings("unchecked")
    private static <TILE extends TileEntityExtraFactory<?>> void onGetAdvancedFactoryTILEAdvancedFactoryBuilder(
            Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar, FactoryType type, AdvancedFactoryTier tier, CallbackInfoReturnable<AdvancedFactory.AdvancedFactoryBuilder<AdvancedFactory<TILE>, TILE, ?>> cir) {


        var factory = emextras$getAdvancedFactory(tileEntityRegistrar, type, tier);

        AdvancedFactory.AdvancedFactoryBuilder<AdvancedFactory<TILE>, TILE, ?> builder;
        try {
            Constructor<AdvancedFactory.AdvancedFactoryBuilder> ctor =
                    AdvancedFactory.AdvancedFactoryBuilder.class.getDeclaredConstructor(AdvancedFactory.class);
            ctor.setAccessible(true);
            builder = (AdvancedFactory.AdvancedFactoryBuilder<AdvancedFactory<TILE>, TILE, ?>) ctor.newInstance(factory);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Failed to instantiate AdvancedFactoryBuilder via reflection", e);
        }

        builder.withComputerSupport(tier.getAdvanceTier().getLowerName() + type.getRegistryNameComponentCapitalized() + "Factory");

        cir.setReturnValue(builder);
    }

    @Unique
    private static <TILE extends TileEntityExtraFactory<?>> @NotNull AdvancedFactory<TILE> emextras$getAdvancedFactory(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar, FactoryType type, AdvancedFactoryTier tier) {
        AdvancedMachine.AdvancedFactoryMachine<?> blockType;
        switch (type) {
            case SAWING -> blockType = ExtraBlockType.PRECISION_SAWMILL;
            case SMELTING -> blockType = ExtraBlockType.ENERGIZED_SMELTER;
            case ENRICHING -> blockType = ExtraBlockType.ENRICHMENT_CHAMBER;
            case CRUSHING -> blockType = ExtraBlockType.CRUSHER;
            case COMPRESSING -> blockType = ExtraBlockType.OSMIUM_COMPRESSOR;
            case COMBINING -> blockType = ExtraBlockType.COMBINER;
            case PURIFYING -> blockType = ExtraBlockType.PURIFICATION_CHAMBER;
            case INJECTING -> blockType = ExtraBlockType.CHEMICAL_INJECTION_CHAMBER;
            case INFUSING -> blockType = ExtraBlockType.METALLURGIC_INFUSER;
            default -> blockType = EMExtrasBlockType.ADVANCED_ALLOYER;
        }

        return new AdvancedFactory<>(tileEntityRegistrar, () -> ExtraContainerTypes.FACTORY, blockType, tier);
    }
}