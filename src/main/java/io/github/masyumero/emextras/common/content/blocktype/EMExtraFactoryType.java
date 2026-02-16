package io.github.masyumero.emextras.common.content.blocktype;

import com.jerry.mekextras.common.content.blocktype.ExtraMachine;
import fr.iglee42.evolvedmekanism.registries.EMBlocks;
import io.github.masyumero.emextras.common.registry.EMExtraBlockTypes;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.text.IHasTranslationKey;
import mekanism.common.MekanismLang;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.registries.MekanismBlocks;

import java.util.Locale;
import java.util.function.Supplier;

@NothingNullByDefault
public enum EMExtraFactoryType implements IHasTranslationKey {
    ALLOYING("alloying", "factory.mekanism.alloying", () -> EMExtraBlockTypes.ALLOYER, () -> EMBlocks.ALLOYER),
    EXTRA_ALLOYING("alloying", "factory.mekanism.alloying", () -> EMExtraBlockTypes.EXTRA_ALLOYER, () -> EMBlocks.ALLOYER, true),
    SMELTING("smelting", MekanismLang.SMELTING.getTranslationKey(), () -> EMExtraBlockTypes.ENERGIZED_SMELTER, () -> MekanismBlocks.ENERGIZED_SMELTER),
    ENRICHING("enriching", MekanismLang.ENRICHING.getTranslationKey(), () -> EMExtraBlockTypes.ENRICHMENT_CHAMBER, () -> MekanismBlocks.ENRICHMENT_CHAMBER),
    CRUSHING("crushing", MekanismLang.CRUSHING.getTranslationKey(), () -> EMExtraBlockTypes.CRUSHER, () -> MekanismBlocks.CRUSHER),
    COMPRESSING("compressing", MekanismLang.COMPRESSING.getTranslationKey(), () -> EMExtraBlockTypes.OSMIUM_COMPRESSOR, () -> MekanismBlocks.OSMIUM_COMPRESSOR),
    COMBINING("combining", MekanismLang.COMBINING.getTranslationKey(), () -> EMExtraBlockTypes.COMBINER, () -> MekanismBlocks.COMBINER),
    PURIFYING("purifying", MekanismLang.PURIFYING.getTranslationKey(), () -> EMExtraBlockTypes.PURIFICATION_CHAMBER, () -> MekanismBlocks.PURIFICATION_CHAMBER),
    INJECTING("injecting", MekanismLang.INJECTING.getTranslationKey(), () -> EMExtraBlockTypes.CHEMICAL_INJECTION_CHAMBER, () -> MekanismBlocks.CHEMICAL_INJECTION_CHAMBER),
    INFUSING("infusing", MekanismLang.INFUSING.getTranslationKey(), () -> EMExtraBlockTypes.METALLURGIC_INFUSER, () -> MekanismBlocks.METALLURGIC_INFUSER),
    SAWING("sawing", MekanismLang.SAWING.getTranslationKey(), () -> EMExtraBlockTypes.PRECISION_SAWMILL, () -> MekanismBlocks.PRECISION_SAWMILL);


    private final String registryNameComponent;
    private final String translationKey;
    private final Supplier<EMExtraMachine.EMExtraFactoryMachine<?>> baseMachine;
    private final Supplier<ExtraMachine.ExtraFactoryMachine<?>> extraBaseMachine;
    private final Supplier<BlockRegistryObject<?, ?>> baseBlock;

    EMExtraFactoryType(String registryNameComponent, String translationKey, Supplier<EMExtraMachine.EMExtraFactoryMachine<?>> baseMachine, Supplier<BlockRegistryObject<?, ?>> baseBlock) {
        this.registryNameComponent = registryNameComponent;
        this.translationKey = translationKey;
        this.baseMachine = baseMachine;
        this.baseBlock = baseBlock;
        extraBaseMachine = null;
    }

    EMExtraFactoryType(String registryNameComponent, String translationKey, Supplier<ExtraMachine.ExtraFactoryMachine<?>> extraBaseMachine, Supplier<BlockRegistryObject<?, ?>> baseBlock, boolean bool) {
        this.registryNameComponent = registryNameComponent;
        this.translationKey = translationKey;
        this.extraBaseMachine = extraBaseMachine;
        this.baseBlock = baseBlock;
        baseMachine = null;
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