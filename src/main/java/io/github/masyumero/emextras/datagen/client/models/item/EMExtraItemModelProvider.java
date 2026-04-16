package io.github.masyumero.emextras.datagen.client.models.item;

import com.jerry.mekanism_extras.common.tier.AdvancedFactoryTier;
import com.jerry.mekanism_extras.common.util.ExtraEnumUtils;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.common.content.blocktype.EMExtraFactoryType;
import io.github.masyumero.emextras.common.registry.EMExtrasBlock;
import io.github.masyumero.emextras.common.registry.EMExtrasItem;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import io.github.masyumero.emextras.common.util.EMExtraEnumUtils;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;

public class EMExtraItemModelProvider extends BaseItemModelProvider {

    public EMExtraItemModelProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, EMExtras.MODID, exFileHelper);
    }

    @Override
    protected void registerModels() {
        EMExtrasItem.ITEM.getAllItems().forEach(item -> basicItem(item.getRegistryName()));
        for (EMExtraFactoryTier tier : EMExtraEnumUtils.EMEXTRA_FACTORY_TIERS) {
            for (EMExtraFactoryType type : EMExtraEnumUtils.EMEXTRA_FACTORY_TYPES) {
                if (type == EMExtraFactoryType.ADVANCED_ALLOYING) {
                    continue;
                }
                factoryBlock(EMExtrasBlock.getEMExtraFactory(tier, type));
            }
        }
        for (AdvancedFactoryTier tier : ExtraEnumUtils.ADVANCED_FACTORY_TIERS) {
            extraAlloyingFactoryBlock(EMExtrasBlock.getAdvancedFactory(tier, EMFactoryType.ALLOYING));
        }
    }
}
