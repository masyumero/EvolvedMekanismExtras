package io.github.masyumero.emextras.common.content.blocktype;

import fr.iglee42.evolvedmekanism.registries.EMBlocks;
import io.github.masyumero.emextras.common.registry.EMExtrasBlockType;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.text.IHasTranslationKey;
import mekanism.common.registration.impl.BlockRegistryObject;

import java.util.Locale;
import java.util.function.Supplier;

@NothingNullByDefault
public enum EMExtraFactoryType implements IHasTranslationKey {
    ALLOYING("alloying", "factory.mekanism.alloying", () -> EMExtrasBlockType.ALLOYER, () -> EMBlocks.ALLOYER);

    private final String registryNameComponent;
    private final String translationKey;
    private final Supplier<EMExtraMachine.EMExtraFactoryMachine<?>> baseMachine;
    private final Supplier<BlockRegistryObject<?, ?>> baseBlock;

    EMExtraFactoryType(String registryNameComponent, String translationKey, Supplier<EMExtraMachine.EMExtraFactoryMachine<?>> baseMachine, Supplier<BlockRegistryObject<?, ?>> baseBlock) {
        this.registryNameComponent = registryNameComponent;
        this.translationKey = translationKey;
        this.baseMachine = baseMachine;
        this.baseBlock = baseBlock;
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