package io.github.masyumero.emextras.api;

import com.jerry.mekanism_extras.api.tier.ExtraAlloyTier;

import mekanism.api.tier.AlloyTier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;
import org.jetbrains.annotations.NotNull;

@AutoRegisterCapability
public interface IEMExtraAlloyInteraction {

    void onEMExtraAlloyInteraction(Player player, ItemStack stack, @NotNull ExtraAlloyTier extraTier, @NotNull AlloyTier evolvedTier);
}
