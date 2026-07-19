package io.github.masyumero.emextras.mixin;

import com.jerry.mekanism_extras.common.block.attribute.ExtraAttributeUpgradeable;
import com.jerry.mekanism_extras.common.content.blocktype.ExtraFactory;
import com.jerry.mekanism_extras.common.content.blocktype.ExtraMachine;
import com.jerry.mekanism_extras.common.registries.ExtraBlockTypes;
import com.jerry.mekanism_extras.common.registries.ExtraContainerTypes;
import com.jerry.mekanism_extras.common.tier.ExtraFactoryTier;
import com.jerry.mekanism_extras.common.tile.factory.TileEntityExtraFactory;
import com.jerry.mekanism_extras.common.util.ExtraEnumUtils;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import io.github.masyumero.emextras.common.registry.EMExtraBlocks;
import io.github.masyumero.emextras.common.registry.EMExtraBlockTypes;
import mekanism.api.text.ILangEntry;
import mekanism.common.content.blocktype.FactoryType;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.reflect.Constructor;
import java.util.function.Supplier;

@Mixin(value = ExtraFactory.class, remap = false)
public abstract class MixinExtraFactory<TILE extends TileEntityExtraFactory<?>> extends ExtraMachine.ExtraFactoryMachine<TILE> {

    public MixinExtraFactory(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntitySupplier, ILangEntry description, FactoryType factoryType) {
        super(tileEntitySupplier, description, factoryType);
    }

    @Inject(method = "<init>", at = @At(value = "RETURN"))
    private void initInject(Supplier tileEntityRegistrar, Supplier containerRegistrar, ExtraMachine.ExtraFactoryMachine origMachine, ExtraFactoryTier tier, CallbackInfo ci) {
        if (tier.ordinal() < ExtraEnumUtils.EXTRA_FACTORY_TIERS.length) {
            if (origMachine.getFactoryType() == EMFactoryType.ALLOYING) {
                add(new ExtraAttributeUpgradeable(() -> EMExtraBlocks.getExtraFactory(ExtraEnumUtils.EXTRA_FACTORY_TIERS[tier.ordinal() + 1], EMFactoryType.ALLOYING)));
            }
        }
    }

    @Inject(method = "getAdvancedFactoryTILEAdvancedFactoryBuilder", at = @At("HEAD"), cancellable = true, remap = false)
    @SuppressWarnings("unchecked")
    private static <TILE extends TileEntityExtraFactory<?>> void onGetExtraFactoryTILEExtraFactoryBuilder(
            Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar, FactoryType type, ExtraFactoryTier tier, CallbackInfoReturnable<ExtraFactory.AdvancedFactoryBuilder<ExtraFactory<TILE>, TILE, ?>> cir) {


        var factory = emextras$getExtraFactory(tileEntityRegistrar, type, tier);

        ExtraFactory.AdvancedFactoryBuilder<ExtraFactory<TILE>, TILE, ?> builder;
        try {
            Constructor<ExtraFactory.AdvancedFactoryBuilder> ctor =
                    ExtraFactory.AdvancedFactoryBuilder.class.getDeclaredConstructor(ExtraFactory.class);
            ctor.setAccessible(true);
            builder = (ExtraFactory.AdvancedFactoryBuilder<ExtraFactory<TILE>, TILE, ?>) ctor.newInstance(factory);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Failed to instantiate ExtraFactoryBuilder via reflection", e);
        }

        builder.withComputerSupport(tier.getAdvanceTier().getLowerName() + type.getRegistryNameComponentCapitalized() + "Factory");

        cir.setReturnValue(builder);
    }

    @Unique
    private static <TILE extends TileEntityExtraFactory<?>> @NotNull ExtraFactory<TILE> emextras$getExtraFactory(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar, FactoryType type, ExtraFactoryTier tier) {
        ExtraMachine.ExtraFactoryMachine<?> blockType;
        switch (type) {
            case SAWING -> blockType = ExtraBlockTypes.PRECISION_SAWMILL;
            case SMELTING -> blockType = ExtraBlockTypes.ENERGIZED_SMELTER;
            case ENRICHING -> blockType = ExtraBlockTypes.ENRICHMENT_CHAMBER;
            case CRUSHING -> blockType = ExtraBlockTypes.CRUSHER;
            case COMPRESSING -> blockType = ExtraBlockTypes.OSMIUM_COMPRESSOR;
            case COMBINING -> blockType = ExtraBlockTypes.COMBINER;
            case PURIFYING -> blockType = ExtraBlockTypes.PURIFICATION_CHAMBER;
            case INJECTING -> blockType = ExtraBlockTypes.CHEMICAL_INJECTION_CHAMBER;
            case INFUSING -> blockType = ExtraBlockTypes.METALLURGIC_INFUSER;
            default -> blockType = EMExtraBlockTypes.ADVANCED_ALLOYER;
        }

        return new ExtraFactory<>(tileEntityRegistrar, () -> ExtraContainerTypes.FACTORY, blockType, tier);
    }
}