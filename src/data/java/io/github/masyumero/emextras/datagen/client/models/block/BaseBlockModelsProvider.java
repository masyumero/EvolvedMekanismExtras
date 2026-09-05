package io.github.masyumero.emextras.datagen.client.models.block;

import com.jerry.mekaf.common.block.attribute.AttributeAdvancedFactoryType;
import com.jerry.mekaf.common.content.blocktype.AdvancedFactoryType;
import com.jerry.mekextras.MekanismExtras;
import com.jerry.mekextras.common.block.attribute.ExtraAttribute;
import com.jerry.mekextras.common.tier.ExtraFactoryTier;
import com.jerry.mekmm.Mekmm;
import com.jerry.mekmm.common.block.attribute.MoreMachineAttributeFactoryType;
import com.jerry.mekmm.common.content.blocktype.MoreMachineFactoryType;
import fr.iglee42.evolvedmekanism.EvolvedMekanism;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.common.block.attribute.EMExtraAttribute;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import mekanism.common.Mekanism;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.block.attribute.AttributeFactoryType;
import mekanism.common.block.states.BlockStateHelper;
import mekanism.common.content.blocktype.FactoryType;
import mekanism.common.registration.impl.BlockRegistryObject;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.VariantBlockStateBuilder;
import net.neoforged.neoforge.client.model.generators.loaders.CompositeModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public abstract class BaseBlockModelsProvider extends BlockStateProvider {

    protected BaseBlockModelsProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, modid, exFileHelper);
    }

    public void simpleFactoryMachineBlock(BlockRegistryObject<?, ?> blockRO) {
        FactoryType type = Attribute.get(blockRO, AttributeFactoryType.class).getFactoryType();
        EMExtraFactoryTier tier = EMExtraAttribute.getEMExtraTier(blockRO, EMExtraFactoryTier.class);

        String blockPath = "block/factory/" + type.getRegistryNameComponent();

        factoryMachineState(blockRO, getActiveFactoryBlockModel(blockPath, tier, type), getFactoryBlockModel(blockPath, tier, type));
    }

    private ModelFile getFactoryBlockModel(String blockPath, EMExtraFactoryTier tier, FactoryType FactoryType) {
        return models().withExistingParent(blockPath + "/" + tier.getEMExtraTier().getLowerName(), this.mcLoc("block/block"))
                .texture("particle", Mekanism.rl("block/factory/factory_front_back"))
                .customLoader(CompositeModelBuilder::begin)
                .child("base", models().nested().parent(new ModelFile.UncheckedModelFile(FactoryType == EMFactoryType.ALLOYING ? EvolvedMekanism.rl("block/factory/" + FactoryType.getRegistryNameComponent() + "/base") : Mekanism.rl("block/factory/" + FactoryType.getRegistryNameComponent() + "/base"))))
                .child("front_led", models().nested().parent(new ModelFile.UncheckedModelFile(EMExtras.rl("block/factory/front_led/" + tier.getEMExtraTier().getLowerName())))).end();
    }

    private ModelFile getActiveFactoryBlockModel(String blockPath, EMExtraFactoryTier tier, FactoryType FactoryType) {
        return models().withExistingParent(blockPath + "/active/" + tier.getEMExtraTier().getLowerName(), this.mcLoc("block/block"))
                .texture("particle", Mekanism.rl("block/factory/factory_front_back"))
                .customLoader(CompositeModelBuilder::begin)
                .child("base", models().nested().parent(new ModelFile.UncheckedModelFile(FactoryType == EMFactoryType.ALLOYING ? EvolvedMekanism.rl("block/factory/" + FactoryType.getRegistryNameComponent() + "/base") : Mekanism.rl("block/factory/" + FactoryType.getRegistryNameComponent() + "/base"))))
                .child("front_led", models().nested().parent(new ModelFile.UncheckedModelFile(EMExtras.rl("block/factory/front_led/active/" + tier.getEMExtraTier().getLowerName())))).end();
    }

    public void simpleExtraFactoryMachineBlock(BlockRegistryObject<?, ?> blockRO) {
        FactoryType type = Attribute.get(blockRO, AttributeFactoryType.class).getFactoryType();
        ExtraFactoryTier tier = ExtraAttribute.getAdvancedTier(blockRO, ExtraFactoryTier.class);

        String blockPath = "block/factory/" + type.getRegistryNameComponent();

        factoryMachineState(blockRO, getActiveExtraFactoryBlockModel(blockPath, tier, type), getExtraFactoryBlockModel(blockPath, tier, type));
    }

    private ModelFile getExtraFactoryBlockModel(String blockPath, ExtraFactoryTier tier, FactoryType factoryType) {
        return models().withExistingParent(blockPath + "/" + tier.getAdvanceTier().getLowerName(), this.mcLoc("block/block"))
                .texture("particle", Mekanism.rl("block/factory/factory_front_back"))
                .customLoader(CompositeModelBuilder::begin)
                .child("base", models().nested().parent(new ModelFile.UncheckedModelFile(EvolvedMekanism.rl("block/factory/" + factoryType.getRegistryNameComponent() + "/base"))))
                .child("front_led", models().nested().parent(new ModelFile.UncheckedModelFile(MekanismExtras.rl("block/factory/front_led/" + tier.getAdvanceTier().getLowerName())))).end();
    }

    private ModelFile getActiveExtraFactoryBlockModel(String blockPath, ExtraFactoryTier tier, FactoryType factoryType) {
        return models().withExistingParent(blockPath + "/active/" + tier.getAdvanceTier().getLowerName(), this.mcLoc("block/block"))
                .texture("particle", Mekanism.rl("block/factory/factory_front_back"))
                .customLoader(CompositeModelBuilder::begin)
                .child("base", models().nested().parent(new ModelFile.UncheckedModelFile(EvolvedMekanism.rl("block/factory/" + factoryType.getRegistryNameComponent() + "/base"))))
                .child("front_led", models().nested().parent(new ModelFile.UncheckedModelFile(MekanismExtras.rl("block/factory/front_led/active/" + tier.getAdvanceTier().getLowerName())))).end();
    }

    public void simpleAdvancedFactoryMachineBlock(BlockRegistryObject<?, ?> blockRO) {
        AdvancedFactoryType type = Attribute.get(blockRO, AttributeAdvancedFactoryType.class).getAdvancedFactoryType();
        EMExtraFactoryTier tier = EMExtraAttribute.getEMExtraTier(blockRO, EMExtraFactoryTier.class);

        String blockPath = "block/factory/" + type.getRegistryNameComponent();

        factoryMachineState(blockRO, getActiveAdvancedFactoryBlockModel(blockPath, tier, type), getAdvancedFactoryBlockModel(blockPath, tier, type));
    }

    private ModelFile getAdvancedFactoryBlockModel(String blockPath, EMExtraFactoryTier tier, AdvancedFactoryType advancedFactoryType) {
        return models().withExistingParent(blockPath + "/" + tier.getEMExtraTier().getLowerName(), this.mcLoc("block/block"))
                .texture("particle", Mekanism.rl("block/factory/factory_front_back"))
                .customLoader(CompositeModelBuilder::begin)
                .child("base", models().nested().parent(new ModelFile.UncheckedModelFile(Mekmm.rl("block/factory/" + advancedFactoryType.getRegistryNameComponent() + "/base"))))
                .child("front_led", models().nested().parent(new ModelFile.UncheckedModelFile(EMExtras.rl("block/factory/front_led/" + tier.getEMExtraTier().getLowerName())))).end();
    }

    private ModelFile getActiveAdvancedFactoryBlockModel(String blockPath, EMExtraFactoryTier tier, AdvancedFactoryType advancedFactoryType) {
        return models().withExistingParent(blockPath + "/active/" + tier.getEMExtraTier().getLowerName(), this.mcLoc("block/block"))
                .texture("particle", Mekanism.rl("block/factory/factory_front_back"))
                .customLoader(CompositeModelBuilder::begin)
                .child("base", models().nested().parent(new ModelFile.UncheckedModelFile(Mekmm.rl("block/factory/" + advancedFactoryType.getRegistryNameComponent() + "/base"))))
                .child("front_led", models().nested().parent(new ModelFile.UncheckedModelFile(EMExtras.rl("block/factory/front_led/active/" + tier.getEMExtraTier().getLowerName())))).end();
    }

    public void simpleMoreMachineFactoryMachineBlock(BlockRegistryObject<?, ?> blockRO) {
        MoreMachineFactoryType type = Attribute.get(blockRO, MoreMachineAttributeFactoryType.class).getMoreMachineFactoryType();
        EMExtraFactoryTier tier = EMExtraAttribute.getEMExtraTier(blockRO, EMExtraFactoryTier.class);

        String blockPath = "block/factory/" + type.getRegistryNameComponent();

        factoryMachineState(blockRO, getActiveMoreMachineFactoryBlockModel(blockPath, tier, type), getMoreMachineFactoryBlockModel(blockPath, tier, type));
    }

    private ModelFile getMoreMachineFactoryBlockModel(String blockPath, EMExtraFactoryTier tier, MoreMachineFactoryType moreMachineFactoryType) {
        return models().withExistingParent(blockPath + "/" + tier.getEMExtraTier().getLowerName(), this.mcLoc("block/block"))
                .texture("particle", Mekanism.rl("block/factory/factory_front_back"))
                .customLoader(CompositeModelBuilder::begin)
                .child("base", models().nested().parent(new ModelFile.UncheckedModelFile(Mekmm.rl("block/factory/" + moreMachineFactoryType.getRegistryNameComponent() + "/base"))))
                .child("front_led", models().nested().parent(new ModelFile.UncheckedModelFile(EMExtras.rl("block/factory/front_led/" + tier.getEMExtraTier().getLowerName())))).end();
    }

    private ModelFile getActiveMoreMachineFactoryBlockModel(String blockPath, EMExtraFactoryTier tier, MoreMachineFactoryType moreMachineFactoryType) {
        return models().withExistingParent(blockPath + "/active/" + tier.getEMExtraTier().getLowerName(), this.mcLoc("block/block"))
                .texture("particle", Mekanism.rl("block/factory/factory_front_back"))
                .customLoader(CompositeModelBuilder::begin)
                .child("base", models().nested().parent(new ModelFile.UncheckedModelFile(Mekmm.rl("block/factory/" + moreMachineFactoryType.getRegistryNameComponent() + "/base"))))
                .child("front_led", models().nested().parent(new ModelFile.UncheckedModelFile(EMExtras.rl("block/factory/front_led/active/" + tier.getEMExtraTier().getLowerName())))).end();
    }

    public void factoryMachineState(BlockRegistryObject<?, ?> blockRO, ModelFile activeBlockModel, ModelFile blockModel) {
        ConfiguredModel.Builder<?> builder = ConfiguredModel.builder();
        VariantBlockStateBuilder variantBuilder = getVariantBuilder(blockRO.get());

        variantBuilder.forAllStatesExcept(state -> {
            var yRot = switch ((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot()) {
                case 0 -> 180;
                case 90 -> -90;
                case 180 -> 0;
                case 270 -> 90;
                default ->
                        throw new IllegalStateException("Unexpected value: " + (int) state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot());
            };
            if (Attribute.isActive(state)) {
                return builder
                        .modelFile(activeBlockModel)
                        .rotationY(yRot).build();
            } else {
                return builder
                        .modelFile(blockModel)
                        .rotationY(yRot).build();
            }
        }, BlockStateHelper.FLUID_LOGGED);
    }
}
