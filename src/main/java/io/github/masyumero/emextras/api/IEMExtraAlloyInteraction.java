package io.github.masyumero.emextras.api;

import com.jerry.mekanism_extras.api.tier.ExtraAlloyTier;

import mekanism.api.tier.AlloyTier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;
import org.jetbrains.annotations.Nullable;

@AutoRegisterCapability
public interface IEMExtraAlloyInteraction {

    void onEMExtraAlloyInteraction(Player player, ItemStack stack, @Nullable ExtraAlloyTier extraTier, @Nullable AlloyTier evolvedTier);
}
