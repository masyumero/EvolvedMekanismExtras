package io.github.masyumero.emextras.common.block.attribute;

import com.jerry.mekanism_extras.api.tier.AdvancedTier;
import mekanism.common.block.states.BlockStateHelper;
import mekanism.common.registration.impl.BlockRegistryObject;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class EMExtraAttributeUpgradeable implements EMExtraAttribute {
    private final Supplier<BlockRegistryObject<?, ?>> upgradeBlock;

    public EMExtraAttributeUpgradeable(Supplier<BlockRegistryObject<?, ?>> upgradeBlock) {
        this.upgradeBlock = upgradeBlock;
    }

    @NotNull
    public BlockState upgradeResult(@NotNull BlockState current, @NotNull AdvancedTier tier) {
        return BlockStateHelper.copyStateData(current, upgradeBlock.get());
    }
}
