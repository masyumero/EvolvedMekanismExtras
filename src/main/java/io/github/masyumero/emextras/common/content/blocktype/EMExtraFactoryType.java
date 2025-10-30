package io.github.masyumero.emextras.common.content.blocktype;

import com.jerry.mekanism_extras.common.content.blocktype.AdvancedMachine;
import fr.iglee42.evolvedmekanism.jei.EMJEI;
import fr.iglee42.evolvedmekanism.registries.EMBlocks;
import io.github.masyumero.emextras.common.registry.EMExtrasBlockType;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.text.IHasTranslationKey;
import mekanism.client.jei.MekanismJEIRecipeType;
import mekanism.common.MekanismLang;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.registries.MekanismBlocks;

import java.util.Locale;
import java.util.function.Supplier;

@NothingNullByDefault
public enum EMExtraFactoryType implements IHasTranslationKey {
    ALLOYING("alloying", "factory.mekanism.alloying", () -> EMExtrasBlockType.ALLOYER, () -> EMBlocks.ALLOYER),
    ADVANCED_ALLOYING("alloying", "factory.mekanism.alloying", () -> EMExtrasBlockType.ADVANCED_ALLOYER, () -> EMBlocks.ALLOYER, true),
    SMELTING("smelting", MekanismLang.SMELTING.getTranslationKey(), () -> EMExtrasBlockType.ENERGIZED_SMELTER, () -> MekanismBlocks.ENERGIZED_SMELTER),
    ENRICHING("enriching", MekanismLang.ENRICHING.getTranslationKey(), () -> EMExtrasBlockType.ENRICHMENT_CHAMBER, () -> MekanismBlocks.ENRICHMENT_CHAMBER),
    CRUSHING("crushing", MekanismLang.CRUSHING.getTranslationKey(), () -> EMExtrasBlockType.CRUSHER, () -> MekanismBlocks.CRUSHER),
    COMPRESSING("compressing", MekanismLang.COMPRESSING.getTranslationKey(), () -> EMExtrasBlockType.OSMIUM_COMPRESSOR, () -> MekanismBlocks.OSMIUM_COMPRESSOR),
    COMBINING("combining", MekanismLang.COMBINING.getTranslationKey(), () -> EMExtrasBlockType.COMBINER, () -> MekanismBlocks.COMBINER),
    PURIFYING("purifying", MekanismLang.PURIFYING.getTranslationKey(), () -> EMExtrasBlockType.PURIFICATION_CHAMBER, () -> MekanismBlocks.PURIFICATION_CHAMBER),
    INJECTING("injecting", MekanismLang.INJECTING.getTranslationKey(), () -> EMExtrasBlockType.CHEMICAL_INJECTION_CHAMBER, () -> MekanismBlocks.CHEMICAL_INJECTION_CHAMBER),
    INFUSING("infusing", MekanismLang.INFUSING.getTranslationKey(), () -> EMExtrasBlockType.METALLURGIC_INFUSER, () -> MekanismBlocks.METALLURGIC_INFUSER),
    SAWING("sawing", MekanismLang.SAWING.getTranslationKey(), () -> EMExtrasBlockType.PRECISION_SAWMILL, () -> MekanismBlocks.PRECISION_SAWMILL);


    private final String registryNameComponent;
    private final String translationKey;
    private final Supplier<EMExtraMachine.EMExtraFactoryMachine<?>> baseMachine;
    private final Supplier<AdvancedMachine.AdvancedFactoryMachine<?>> advancedBaseMachine;
    private final Supplier<BlockRegistryObject<?, ?>> baseBlock;

    EMExtraFactoryType(String registryNameComponent, String translationKey, Supplier<EMExtraMachine.EMExtraFactoryMachine<?>> baseMachine, Supplier<BlockRegistryObject<?, ?>> baseBlock) {
        this.registryNameComponent = registryNameComponent;
        this.translationKey = translationKey;
        this.baseMachine = baseMachine;
        this.baseBlock = baseBlock;
        advancedBaseMachine = null;
    }

    EMExtraFactoryType(String registryNameComponent, String translationKey, Supplier<AdvancedMachine.AdvancedFactoryMachine<?>> advancedBaseMachine, Supplier<BlockRegistryObject<?, ?>> baseBlock, boolean bool) {
        this.registryNameComponent = registryNameComponent;
        this.translationKey = translationKey;
        this.advancedBaseMachine = advancedBaseMachine;
        this.baseBlock = baseBlock;
        baseMachine = null;
    }

    public MekanismJEIRecipeType<?> getRecipeType(EMExtraFactoryType factoryType) {
        return switch (factoryType) {
            case ALLOYING, ADVANCED_ALLOYING -> EMJEI.ALLOYING;
            case SMELTING -> MekanismJEIRecipeType.SMELTING;
            case ENRICHING -> MekanismJEIRecipeType.ENRICHING;
            case CRUSHING -> MekanismJEIRecipeType.CRUSHING;
            case COMPRESSING -> MekanismJEIRecipeType.COMPRESSING;
            case COMBINING -> MekanismJEIRecipeType.COMBINING;
            case PURIFYING -> MekanismJEIRecipeType.PURIFYING;
            case INJECTING -> MekanismJEIRecipeType.INJECTING;
            case INFUSING -> MekanismJEIRecipeType.METALLURGIC_INFUSING;
            case SAWING -> MekanismJEIRecipeType.SAWING;
        };
    }

    public String getRegistryNameComponent() {
        return registryNameComponent;
    }

    public String getRegistryNameComponentCapitalized() {
        String name = getRegistryNameComponent();
        return name.substring(0, 1).toUpperCase(Locale.ROOT) + name.substring(1);
    }

    public EMExtraMachine.EMExtraFactoryMachine<?> getBaseMachine() {
        return baseMachine.get();
    }

    public BlockRegistryObject<?, ?> getBaseBlock() {
        return baseBlock.get();
    }

    @Override
    public String getTranslationKey() {
        return translationKey;
    }
}