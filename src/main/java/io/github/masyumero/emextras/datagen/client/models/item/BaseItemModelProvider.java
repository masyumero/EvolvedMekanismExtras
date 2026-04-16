package io.github.masyumero.emextras.datagen.client.models.item;

import com.jerry.mekanism_extras.common.block.attribute.ExtraAttribute;
import com.jerry.mekanism_extras.common.tier.AdvancedFactoryTier;
import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.common.block.attribute.EMExtraAttribute;
import io.github.masyumero.emextras.common.block.attribute.EMExtraAttributeFactoryType;
import io.github.masyumero.emextras.common.content.blocktype.EMExtraFactoryType;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.block.attribute.AttributeFactoryType;
import mekanism.common.content.blocktype.FactoryType;
import mekanism.common.registration.impl.BlockRegistryObject;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public abstract class BaseItemModelProvider extends ItemModelProvider {

    public BaseItemModelProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    public ItemModelBuilder factoryBlock(BlockRegistryObject<?, ?> blockRO) {
        EMExtraFactoryType type = Attribute.get(blockRO, EMExtraAttributeFactoryType.class).getFactoryType();
        EMExtraFactoryTier tier = EMExtraAttribute.getTier(blockRO.getBlock(), EMExtraFactoryTier.class);

        return this.withExistingParent(blockRO.getName(), EMExtras.rl("block/factory/" + type.getRegistryNameComponent() +  "/" + tier.getEMExtraTier().getLowerName()));
    }

    public ItemModelBuilder extraAlloyingFactoryBlock(BlockRegistryObject<?, ?> blockRO) {
        AdvancedFactoryTier tier = ExtraAttribute.getTier(blockRO.getBlock(), AdvancedFactoryTier.class);

        return this.withExistingParent(blockRO.getName(), EMExtras.rl("block/factory/alloying/" + tier.getAdvanceTier().getLowerName()));
    }
}
