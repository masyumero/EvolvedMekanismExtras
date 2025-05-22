package io.github.masyumero.emextras.common.item.block;

import com.jerry.mekanism_extras.common.config.LoadConfig;
import io.github.masyumero.emextras.api.tier.IEMExtraTier;
import mekanism.api.AutomationType;
import mekanism.api.NBTConstants;
import mekanism.api.Upgrade;
import mekanism.api.math.FloatingLong;
import mekanism.api.math.FloatingLongSupplier;
import mekanism.api.text.TextComponentUtil;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.block.attribute.AttributeEnergy;
import mekanism.common.block.attribute.AttributeUpgradeSupport;
import mekanism.common.block.attribute.Attributes;
import mekanism.common.capabilities.ItemCapabilityWrapper;
import mekanism.common.capabilities.energy.BasicEnergyContainer;
import mekanism.common.capabilities.energy.item.RateLimitEnergyHandler;
import mekanism.common.capabilities.security.item.ItemStackSecurityObject;
import mekanism.common.util.ItemDataUtils;
import mekanism.common.util.MekanismUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ItemBlockEMExtra  <BLOCK extends Block> extends BlockItem {

    @NotNull
    private final BLOCK block;

    public ItemBlockEMExtra(@NotNull BLOCK block, Properties properties) {
        super(block, properties);
        this.block = block;
    }

    @NotNull
    @Override
    public BLOCK getBlock() {
        return block;
    }

    public IEMExtraTier getEMExtraTier() {
        return null;
    }

    public TextColor getTextColor(ItemStack stack) {
        IEMExtraTier tier = getEMExtraTier();
        return tier == null ? null : tier.getEMExtraTier().getColor();
    }

    @NotNull
    @Override
    public Component getName(@NotNull ItemStack stack) {
        TextColor color = getTextColor(stack);
        if (color == null) {
            return super.getName(stack);
        }
        return TextComponentUtil.build(color, super.getName(stack));
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        if (exposesEnergyCap(oldStack) && exposesEnergyCap(newStack)) {
            //Ignore NBT for energized items causing re-equip animations
            return slotChanged || oldStack.getItem() != newStack.getItem();
        }
        return super.shouldCauseReequipAnimation(oldStack, newStack, slotChanged);
    }

    @Override
    public boolean shouldCauseBlockBreakReset(ItemStack oldStack, ItemStack newStack) {
        if (exposesEnergyCap(oldStack) && exposesEnergyCap(newStack)) {
            //Ignore NBT for energized items causing block break reset
            return oldStack.getItem() != newStack.getItem();
        }
        return super.shouldCauseBlockBreakReset(oldStack, newStack);
    }

    protected void gatherCapabilities(List<ItemCapabilityWrapper.ItemCapability> capabilities, ItemStack stack, CompoundTag nbt) {
        if (Attribute.has(block, Attributes.AttributeSecurity.class)) {
            capabilities.add(new ItemStackSecurityObject());
        }
        if (exposesEnergyCap(stack)) {
            AttributeEnergy attributeEnergy = Attribute.get(block, AttributeEnergy.class);
            FloatingLongSupplier maxEnergy;
            if (Attribute.matches(block, AttributeUpgradeSupport.class, attribute -> attribute.supportedUpgrades().contains(Upgrade.ENERGY))) {
                //If our block supports energy upgrades, make a more dynamically updating cache for our item's max energy
                maxEnergy = new ItemBlockEMExtra.UpgradeBasedFloatingLongCache(stack, attributeEnergy::getStorage);
            } else {
                //Otherwise, just return that the max is what the base max is
                maxEnergy = attributeEnergy::getStorage;
            }
            capabilities.add(RateLimitEnergyHandler.create(maxEnergy, BasicEnergyContainer.manualOnly, getEnergyCapInsertPredicate()));
        }
    }

    protected Predicate<@NotNull AutomationType> getEnergyCapInsertPredicate() {
        return BasicEnergyContainer.alwaysTrue;
    }

    protected boolean exposesEnergyCap(ItemStack stack) {
        //Only expose it if the block can't stack
        return Attribute.has(block, AttributeEnergy.class);
    }

    protected boolean areCapabilityConfigsLoaded(ItemStack stack) {
        if (exposesEnergyCap(stack)) {
            return LoadConfig.extraStorage.isLoaded() && LoadConfig.extraConfig.isLoaded();
        }
        return true;
    }

    @Override
    public final ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag nbt) {
        if (!areCapabilityConfigsLoaded(stack)) {
            //Only expose the capabilities if the required configs are loaded
            return super.initCapabilities(stack, nbt);
        }
        List<ItemCapabilityWrapper.ItemCapability> capabilities = new ArrayList<>();
        gatherCapabilities(capabilities, stack, nbt);
        if (capabilities.isEmpty()) {
            return super.initCapabilities(stack, nbt);
        }
        return new ItemCapabilityWrapper(stack, capabilities.toArray(ItemCapabilityWrapper.ItemCapability[]::new));
    }

    private static class UpgradeBasedFloatingLongCache implements FloatingLongSupplier {

        private final ItemStack stack;
        private final FloatingLongSupplier baseStorage;
        @Nullable
        private CompoundTag lastNBT;
        private FloatingLong value;

        private UpgradeBasedFloatingLongCache(ItemStack stack, FloatingLongSupplier baseStorage) {
            this.stack = stack;
            if (ItemDataUtils.hasData(stack, NBTConstants.COMPONENT_UPGRADE, Tag.TAG_COMPOUND)) {
                this.lastNBT = ItemDataUtils.getCompound(stack, NBTConstants.COMPONENT_UPGRADE).copy();
            } else {
                this.lastNBT = null;
            }
            this.baseStorage = baseStorage;
            this.value = MekanismUtils.getMaxEnergy(this.stack, this.baseStorage.get());
        }

        @NotNull
        @Override
        public FloatingLong get() {
            if (ItemDataUtils.hasData(stack, NBTConstants.COMPONENT_UPGRADE, Tag.TAG_COMPOUND)) {
                CompoundTag upgrades = ItemDataUtils.getCompound(stack, NBTConstants.COMPONENT_UPGRADE);
                if (lastNBT == null || !lastNBT.equals(upgrades)) {
                    lastNBT = upgrades.copy();
                    value = MekanismUtils.getMaxEnergy(stack, baseStorage.get());
                }
            } else if (lastNBT != null) {
                lastNBT = null;
                value = MekanismUtils.getMaxEnergy(stack, baseStorage.get());
            }
            return value;
        }
    }
}
