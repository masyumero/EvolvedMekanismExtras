package io.github.masyumero.emextras.common.registry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jerry.mekanism_extras.common.tier.AdvancedFactoryTier;
import com.jerry.mekanism_extras.common.util.ExtraEnumUtils;
import fr.iglee42.evolvedmekanism.EvolvedMekanismLang;
import fr.iglee42.evolvedmekanism.registries.EMContainerTypes;
import fr.iglee42.evolvedmekanism.registries.EMTileEntityTypes;
import fr.iglee42.evolvedmekanism.tiles.machine.TileEntityAlloyer;
import io.github.masyumero.emextras.common.content.blocktype.EMExtraFactory;
import io.github.masyumero.emextras.common.content.blocktype.EMExtraFactoryType;
import io.github.masyumero.emextras.common.content.blocktype.EMExtraMachine;
import io.github.masyumero.emextras.common.util.EMExtraEnumUtils;
import mekanism.common.config.MekanismConfig;
import mekanism.common.content.blocktype.Factory;
import mekanism.common.registries.MekanismSounds;
import mekanism.common.tier.FactoryTier;

public class EMExtrasBlockType {
    private static final Table<AdvancedFactoryTier, EMExtraFactoryType, EMExtraFactory<?>> FACTORIES = HashBasedTable.create();

    public static final EMExtraMachine.EMExtraFactoryMachine<TileEntityAlloyer> ALLOYER = EMExtraMachine.EMExtraMachineBuilder
            .createEMExtraFactoryMachine(() -> EMTileEntityTypes.ALLOYER, EvolvedMekanismLang.DESCRIPTION_ALLOYER, EMExtraFactoryType.ALLOYING)
            .withGui(() -> EMContainerTypes.ALLOYER)
            .withSound(MekanismSounds.COMBINER)
            .withEnergyConfig(MekanismConfig.usage.combiner, MekanismConfig.storage.combiner)
            .withComputerSupport("alloyer")
            .build();

    static {
        for (AdvancedFactoryTier tier : ExtraEnumUtils.ADVANCED_FACTORY_TIERS) {
            for (EMExtraFactoryType type : EMExtraEnumUtils.FACTORY_TYPES) {
                FACTORIES.put(tier, type, EMExtraFactory.EMExtraFactoryBuilder.createFactory(() -> EMExtrasTileEntityTypes.getEMExtraFactoryTile(tier, type), type, tier).build());
            }
        }
    }
    public static EMExtraFactory<?> getEMExtraFactory(AdvancedFactoryTier tier, EMExtraFactoryType type) {
        return FACTORIES.get(tier, type);
    }
}
