package io.github.masyumero.emextras.datagen.client.models.item;

import com.jerry.mekaf.common.block.attribute.AttributeAdvancedFactoryType;
import com.jerry.mekaf.common.content.blocktype.AdvancedFactoryType;
import com.jerry.mekanism_extras.common.block.attribute.ExtraAttribute;
import com.jerry.mekanism_extras.common.tier.ExtraFactoryTier;
import com.jerry.mekmm.common.block.attribute.AttributeMoreMachineFactoryType;
import com.jerry.mekmm.common.content.blocktype.MoreMachineFactoryType;
import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.common.block.attribute.EMExtraAttribute;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.block.attribute.AttributeFactoryType;
import mekanism.common.content.blocktype.FactoryType;
import mekanism.common.registration.impl.BlockRegistryObject;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public abstract class BaseItemModelProvider extends ItemModelProvider {

    public BaseItemModelProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    public ItemModelBuilder factoryBlock(BlockRegistryObject<?, ?> blockRO) {
        FactoryType type = Attribute.get(blockRO, AttributeFactoryType.class).getFactoryType();
        EMExtraFactoryTier tier = EMExtraAttribute.getTier(blockRO.getBlock(), EMExtraFactoryTier.class);

        return this.withExistingParent(blockRO.getName(), EMExtras.rl("block/factory/" + type.getRegistryNameComponent() +  "/" + tier.getEMExtraTier().getLowerName()));
    }

    public ItemModelBuilder extraAlloyingFactoryBlock(BlockRegistryObject<?, ?> blockRO) {
        ExtraFactoryTier tier = ExtraAttribute.getTier(blockRO.getBlock(), ExtraFactoryTier.class);

        return this.withExistingParent(blockRO.getName(), EMExtras.rl("block/factory/alloying/" + tier.getAdvanceTier().getLowerName()));
    }

    public ItemModelBuilder advancedFactoryBlock(BlockRegistryObject<?, ?> blockRO) {
        AdvancedFactoryType type = Attribute.get(blockRO, AttributeAdvancedFactoryType.class).getAdvancedFactoryType();
        EMExtraFactoryTier tier = EMExtraAttribute.getTier(blockRO.getBlock(), EMExtraFactoryTier.class);

        return this.withExistingParent(blockRO.getName(), EMExtras.rl("block/factory/" + type.getRegistryNameComponent() +  "/" + tier.getEMExtraTier().getLowerName()));
    }

    public ItemModelBuilder moreMachineFactoryBlock(BlockRegistryObject<?, ?> blockRO) {
        MoreMachineFactoryType type = Attribute.get(blockRO, AttributeMoreMachineFactoryType.class).getMoreMachineFactoryType();
        EMExtraFactoryTier tier = EMExtraAttribute.getTier(blockRO.getBlock(), EMExtraFactoryTier.class);

        return this.withExistingParent(blockRO.getName(), EMExtras.rl("block/factory/" + type.getRegistryNameComponent() +  "/" + tier.getEMExtraTier().getLowerName()));
    }

    public ItemModelBuilder doubleLayeredBlock(ItemModelBuilder modelBuilder) {
        return modelBuilder.transforms()
                .transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND)
                .rotation(75,45,0)
                .translation(0,2.5F,1.25F)
                .scale(0.375F)
                .end()
                .transform(ItemDisplayContext.THIRD_PERSON_LEFT_HAND)
                .rotation(75,45,0)
                .translation(0,2.5F,1.25F)
                .scale(0.375F)
                .end()
                .transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND)
                .rotation(0,45,0)
                .scale(0.4F)
                .end()
                .transform(ItemDisplayContext.FIRST_PERSON_LEFT_HAND)
                .rotation(0,225,0)
                .scale(0.4F)
                .end()
                .transform(ItemDisplayContext.GROUND)
                .translation(0, 3, 0)
                .scale(0.25F)
                .end()
                .transform(ItemDisplayContext.GUI)
                .rotation(30, 225, 0)
                .translation(0, -2.5F, 0)
                .scale(0.43F)
                .end()
                .transform(ItemDisplayContext.HEAD)
                .translation(0, 14, 0)
                .end()
                .transform(ItemDisplayContext.FIXED)
                .translation(0, -3.75F, 0)
                .scale(0.5F)
                .end()
                .end();
    }
}
