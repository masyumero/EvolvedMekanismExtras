package io.github.masyumero.emextras.datagen.client.models.item;

import com.jerry.mekaf.common.block.attribute.AttributeAdvancedFactoryType;
import com.jerry.mekaf.common.content.blocktype.AdvancedFactoryType;
import com.jerry.mekextras.common.block.attribute.ExtraAttribute;
import com.jerry.mekextras.common.tier.ExtraFactoryTier;
import com.jerry.mekmm.common.block.attribute.MoreMachineAttributeFactoryType;
import com.jerry.mekmm.common.content.blocktype.MoreMachineFactoryType;

import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.common.block.attribute.EMExtraAttribute;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;

import mekanism.common.block.attribute.Attribute;
import mekanism.common.block.attribute.AttributeFactoryType;
import mekanism.common.content.blocktype.FactoryType;
import mekanism.common.registration.impl.BlockRegistryObject;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public abstract class BaseItemModelProvider extends ItemModelProvider {

    public BaseItemModelProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    public ItemModelBuilder factoryBlock(BlockRegistryObject<?, ?> blockRO) {
        FactoryType type = Attribute.get(blockRO, AttributeFactoryType.class).getFactoryType();
        EMExtraFactoryTier tier = EMExtraAttribute.getEMExtraTier(blockRO, EMExtraFactoryTier.class);

        return this.withExistingParent(blockRO.getName(), EMExtras.rl("block/factory/" + type.getRegistryNameComponent() +  "/" + tier.getEMExtraTier().getLowerName()));
    }

    public ItemModelBuilder extraFactoryBlock(BlockRegistryObject<?, ?> blockRO) {
        FactoryType type = Attribute.get(blockRO, AttributeFactoryType.class).getFactoryType();
        ExtraFactoryTier tier = ExtraAttribute.getAdvancedTier(blockRO, ExtraFactoryTier.class);

        return this.withExistingParent(blockRO.getName(), EMExtras.rl("block/factory/" + type.getRegistryNameComponent() +  "/" + tier.getAdvanceTier().getLowerName()));
    }

    public ItemModelBuilder advancedFactoryBlock(BlockRegistryObject<?, ?> blockRO) {
        AdvancedFactoryType type = Attribute.get(blockRO, AttributeAdvancedFactoryType.class).getAdvancedFactoryType();
        EMExtraFactoryTier tier = EMExtraAttribute.getEMExtraTier(blockRO, EMExtraFactoryTier.class);

        return this.withExistingParent(blockRO.getName(), EMExtras.rl("block/factory/" + type.getRegistryNameComponent() +  "/" + tier.getEMExtraTier().getLowerName()));
    }

    public ItemModelBuilder moreMachineFactoryBlock(BlockRegistryObject<?, ?> blockRO) {
        MoreMachineFactoryType type = Attribute.get(blockRO, MoreMachineAttributeFactoryType.class).getMoreMachineFactoryType();
        EMExtraFactoryTier tier = EMExtraAttribute.getEMExtraTier(blockRO, EMExtraFactoryTier.class);

        return this.withExistingParent(blockRO.getName(), EMExtras.rl("block/factory/" + type.getRegistryNameComponent() +  "/" + tier.getEMExtraTier().getLowerName()));
    }
}
