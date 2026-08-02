package io.github.masyumero.emextras.datagen.client.models.block;

import com.jerry.mekaf.common.block.attribute.AttributeAdvancedFactoryType;
import com.jerry.mekaf.common.content.blocktype.AdvancedFactoryType;
import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.common.block.attribute.ExtraAttribute;
import com.jerry.mekanism_extras.common.tier.ExtraFactoryTier;
import com.jerry.mekmm.Mekmm;
import com.jerry.mekmm.common.block.attribute.AttributeMoreMachineFactoryType;
import com.jerry.mekmm.common.content.blocktype.MoreMachineFactoryType;
import fr.iglee42.evolvedmekanism.EvolvedMekanism;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.api.tier.EMExtraTier;
import io.github.masyumero.emextras.common.block.attribute.EMExtraAttribute;
import io.github.masyumero.emextras.common.tier.EMExtraFactoryTier;
import io.github.masyumero.emextras.common.tier.EMExtraICTier;
import io.github.masyumero.emextras.common.tier.EMExtraIPTier;
import io.github.masyumero.emextras.common.util.EMExtraTransporterUtils;
import mekanism.api.tier.ITier;
import mekanism.common.Mekanism;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.block.attribute.AttributeFactoryType;
import mekanism.common.block.states.BlockStateHelper;
import mekanism.common.content.blocktype.FactoryType;
import mekanism.common.registration.impl.BlockRegistryObject;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.client.model.generators.loaders.CompositeModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

public abstract class BaseBlockModelsProvider extends BlockStateProvider {

    public BaseBlockModelsProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, modid, exFileHelper);
    }

    protected void transmitters(BlockRegistryObject<?, ?> transmitter, String type, ITier tier, ResourceLocation parent, boolean isSmall) {
        ConfiguredModel.Builder<?> builder = ConfiguredModel.builder();
        EMExtraTier emExtraTier = EMExtraTransporterUtils.baseToEMExtraTier(tier.getBaseTier());
        ResourceLocation path = EMExtras.rl("block/transmitter/" + (isSmall ? "small/" : "large/") + type + "/" + emExtraTier.getLowerName());
        String name = transmitter.getName();

        simpleBlockItem(transmitter.getBlock(),
                models().withExistingParent(path.getPath(), parent)
                .texture("side", EMExtras.rl("block/models/multipart/" + name + "_vertical"))
                .texture("center_down", EMExtras.rl("block/models/multipart/" + name))
                .texture("side_opaque",  EMExtras.rl("block/models/multipart/opaque/" + name + "_vertical"))
                .texture("center_opaque", EMExtras.rl("block/models/multipart/" + (isSmall ? "" : "opaque/") + name)));

        getVariantBuilder(transmitter.getBlock())
                .forAllStatesExcept(state -> builder.modelFile(models().getExistingFile(path)).build());
    }

    protected void inductionCellAndProvider(BlockRegistryObject<?, ?> cellBlockRO, BlockRegistryObject<?, ?> providerBlockRO) {
        ConfiguredModel.Builder<?> builder = ConfiguredModel.builder();
        EMExtraTier cellTier = EMExtraAttribute.getTier(cellBlockRO.getBlock(), EMExtraICTier.class).getEMExtraTier();
        EMExtraTier providerTier = EMExtraAttribute.getTier(providerBlockRO.getBlock(), EMExtraIPTier.class).getEMExtraTier();
        ResourceLocation cellPath = EMExtras.rl("block/induction/cell/" + cellTier.getLowerName());
        ResourceLocation providerPath = EMExtras.rl("block/induction/provider/" + providerTier.getLowerName());

        ResourceLocation cellTexture = EMExtras.rl("block/" + cellBlockRO.getName());
        ResourceLocation providerTexture = EMExtras.rl("block/" + providerBlockRO.getName());

        simpleBlockItem(cellBlockRO.getBlock(),
                models().withExistingParent(cellPath.getPath() , Mekanism.rl("block/induction/cell/basic"))
                .renderType(mcLoc("cutout"))
                .texture("particle", cellTexture)
                .texture("all", cellTexture));

        getVariantBuilder(cellBlockRO.getBlock())
                .forAllStatesExcept(state -> builder.modelFile(models().getExistingFile(cellPath)).build());

        simpleBlockItem(providerBlockRO.getBlock(),
                models().withExistingParent(providerPath.getPath() , Mekanism.rl("block/induction/provider/base"))
                .renderType(mcLoc("cutout"))
                .texture("particle", providerTexture)
                .texture("all", providerTexture)
                .texture("glow", Mekanism.rl("block/induction_provider_glow"))
                .texture("led", providerTexture + "_led"));

        getVariantBuilder(providerBlockRO.getBlock())
                .forAllStatesExcept(state -> builder.modelFile(models().getExistingFile(providerPath)).build());
    }

    protected void simpleFactoryMachineBlock(BlockRegistryObject<?, ?> blockRO) {
        FactoryType type = Attribute.get(blockRO, AttributeFactoryType.class).getFactoryType();
        EMExtraFactoryTier tier = EMExtraAttribute.getTier(blockRO.getBlock(), EMExtraFactoryTier.class);

        String blockPath = "block/factory/" + type.getRegistryNameComponent();

        factoryMachineState(blockRO, getActiveFactoryBlockModel(blockPath, tier, type), getFactoryBlockModel(blockPath, tier, type));
    }

    private ModelFile getFactoryBlockModel(String blockPath, EMExtraFactoryTier tier, FactoryType emExtraFactoryType) {
        return models().withExistingParent(blockPath + "/" + tier.getEMExtraTier().getLowerName(), this.mcLoc("block/block"))
                .texture("particle", Mekanism.rl("block/factory/factory_front_back"))
                .customLoader(CompositeModelBuilder::begin)
                .child("base", models().nested().parent(new ModelFile.UncheckedModelFile(emExtraFactoryType == EMFactoryType.ALLOYING ? EvolvedMekanism.rl("block/factory/" + emExtraFactoryType.getRegistryNameComponent() + "/base") : Mekanism.rl("block/factory/" + emExtraFactoryType.getRegistryNameComponent() + "/base"))))
                .child("front_led", models().nested().parent(new ModelFile.UncheckedModelFile((EMExtras.rl("block/factory/front_led/" + tier.getEMExtraTier().getLowerName()))))).end();
    }

    private ModelFile getActiveFactoryBlockModel(String blockPath, EMExtraFactoryTier tier, FactoryType emExtraFactoryType) {
        return models().withExistingParent(blockPath + "/active/" + tier.getEMExtraTier().getLowerName(), this.mcLoc("block/block"))
                .texture("particle", Mekanism.rl("block/factory/factory_front_back"))
                .customLoader(CompositeModelBuilder::begin)
                .child("base", models().nested().parent(new ModelFile.UncheckedModelFile(emExtraFactoryType == EMFactoryType.ALLOYING ? EvolvedMekanism.rl("block/factory/" + emExtraFactoryType.getRegistryNameComponent() + "/base") : Mekanism.rl("block/factory/" + emExtraFactoryType.getRegistryNameComponent() + "/base"))))
                .child("front_led", models().nested().parent(new ModelFile.UncheckedModelFile(EMExtras.rl("block/factory/front_led/active/" + tier.getEMExtraTier().getLowerName())))).end();
    }

    protected void alloyingFactoryMachineBlock(BlockRegistryObject<?, ?> blockRO) {
        ExtraFactoryTier tier = ExtraAttribute.getTier(blockRO.getBlock(), ExtraFactoryTier.class);

        String blockPath = "block/factory/alloying";

        factoryMachineState(blockRO, getActiveExtraAlloyingFactoryBlockModel(blockPath, tier, EMFactoryType.ALLOYING), getExtraAlloyingFactoryBlockModel(blockPath, tier, EMFactoryType.ALLOYING));
    }

    private ModelFile getExtraAlloyingFactoryBlockModel(String blockPath, ExtraFactoryTier tier, FactoryType factoryType) {
        return models().withExistingParent(blockPath + "/" + tier.getAdvanceTier().getLowerName(), this.mcLoc("block/block"))
                .texture("particle", Mekanism.rl("block/factory/factory_front_back"))
                .customLoader(CompositeModelBuilder::begin)
                .child("base", models().nested().parent(new ModelFile.UncheckedModelFile(EvolvedMekanism.rl("block/factory/alloying/base"))))
                .child("front_led", models().nested().parent(new ModelFile.UncheckedModelFile(MekanismExtras.rl("block/factory/front_led/" + tier.getAdvanceTier().getLowerName())))).end();
    }

    private ModelFile getActiveExtraAlloyingFactoryBlockModel(String blockPath, ExtraFactoryTier tier, FactoryType factoryType) {
        return models().withExistingParent(blockPath + "/active/" + tier.getAdvanceTier().getLowerName(), this.mcLoc("block/block"))
                .texture("particle", Mekanism.rl("block/factory/factory_front_back"))
                .customLoader(CompositeModelBuilder::begin)
                .child("base", models().nested().parent(new ModelFile.UncheckedModelFile(EvolvedMekanism.rl("block/factory/alloying/base"))))
                .child("front_led", models().nested().parent(new ModelFile.UncheckedModelFile(MekanismExtras.rl("block/factory/front_led/active/" + tier.getAdvanceTier().getLowerName())))).end();
    }

    public void simpleAdvancedFactoryMachineBlock(BlockRegistryObject<?, ?> blockRO) {
        AdvancedFactoryType type = Attribute.get(blockRO, AttributeAdvancedFactoryType.class).getAdvancedFactoryType();
        EMExtraFactoryTier tier = EMExtraAttribute.getTier(blockRO.getBlock(), EMExtraFactoryTier.class);

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
        MoreMachineFactoryType type = Attribute.get(blockRO, AttributeMoreMachineFactoryType.class).getMoreMachineFactoryType();
        EMExtraFactoryTier tier = EMExtraAttribute.getTier(blockRO.getBlock(), EMExtraFactoryTier.class);

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

    private void factoryMachineState(BlockRegistryObject<?, ?> blockRO, ModelFile activeBlockModel, ModelFile blockModel) {
        ConfiguredModel.Builder<?> builder = ConfiguredModel.builder();
        VariantBlockStateBuilder variantBuilder = getVariantBuilder(blockRO.getBlock());

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
