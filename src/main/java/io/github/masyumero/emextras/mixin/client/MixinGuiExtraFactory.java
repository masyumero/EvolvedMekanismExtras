package io.github.masyumero.emextras.mixin.client;

import com.jerry.mekanism_extras.client.gui.machine.GuiExtraFactory;
import com.jerry.mekanism_extras.common.tile.factory.TileEntityExtraFactory;
import fr.iglee42.evolvedmekanism.jei.EMJEI;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import mekanism.client.gui.GuiConfigurableTile;
import mekanism.client.gui.element.progress.GuiProgress;
import mekanism.client.jei.MekanismJEIRecipeType;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = GuiExtraFactory.class, remap = false)
public abstract class MixinGuiExtraFactory extends GuiConfigurableTile<TileEntityExtraFactory<?>, MekanismTileContainer<TileEntityExtraFactory<?>>> {

    protected MixinGuiExtraFactory(MekanismTileContainer<TileEntityExtraFactory<?>> container, Inventory inv, Component title) {
        super(container, inv, title);
    }

    @Inject(method = "addProgress", at = @At("HEAD"), cancellable = true)
    private void addProgressInject(GuiProgress progressBar, CallbackInfoReturnable<GuiProgress> cir) {
        MekanismJEIRecipeType<?> jeiType = tile.getFactoryType() == EMFactoryType.ALLOYING ? EMJEI.ALLOYING : switch (tile.getFactoryType()) {
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
        cir.setReturnValue(addRenderableWidget(progressBar.jeiCategories(jeiType)));
    }
}
