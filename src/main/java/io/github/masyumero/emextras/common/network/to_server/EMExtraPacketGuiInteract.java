package io.github.masyumero.emextras.common.network.to_server;

import io.github.masyumero.emextras.EMExtras;
import io.github.masyumero.emextras.common.integration.mekaf.tile.factory.base.TileEntityEMExtraAdvancedFactoryBase;
import io.github.masyumero.emextras.common.integration.mekmm.tile.factory.TileEntityEMExtraMoreMachineFactory;
import io.github.masyumero.emextras.common.tile.factory.TileEntityEMExtraFactory;

import mekanism.api.functions.TriConsumer;
import mekanism.common.network.IMekanismPacket;
import mekanism.common.tile.base.TileEntityMekanism;
import mekanism.common.util.WorldUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ByIdMap;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import io.netty.buffer.ByteBuf;
import org.jetbrains.annotations.NotNull;

import java.util.function.IntFunction;


public class EMExtraPacketGuiInteract implements IMekanismPacket {

    public static final Type<EMExtraPacketGuiInteract> TYPE = new Type<>(EMExtras.rl("gui_interact"));
    public static final StreamCodec<RegistryFriendlyByteBuf, EMExtraPacketGuiInteract> STREAM_CODEC = EMExtraInteractionType.STREAM_CODEC.<RegistryFriendlyByteBuf>cast()
            .dispatch(packet -> packet.interactionType, type -> switch (type) {
                case ENTITY, ITEM -> null;
                case INT -> StreamCodec.composite(
                        EMExtraGuiInteraction.STREAM_CODEC, packet -> packet.interaction,
                        BlockPos.STREAM_CODEC, packet -> packet.tilePosition,
                        // TODO - 1.18?: Eventually we may want to try to make some form of this that can compact
                        // negatives better as well
                        ByteBufCodecs.VAR_INT, packet -> packet.extra,
                        EMExtraPacketGuiInteract::new);
                // case ITEM -> StreamCodec.composite(
                // GuiInteractionItem.STREAM_CODEC, packet -> packet.itemInteraction,
                // BlockPos.STREAM_CODEC, packet -> packet.tilePosition,
                // ItemStack.OPTIONAL_STREAM_CODEC, packet -> packet.extraItem,
                // EMExtraPacketGuiInteract::new
                // );
            });

    private final EMExtraInteractionType interactionType;

    private EMExtraGuiInteraction interaction;
    // private GuiInteractionItem itemInteraction;
    // private PacketGuiInteract.GuiInteractionEntity entityInteraction;
    private BlockPos tilePosition;
    private ItemStack extraItem;
    private int entityID;
    private int extra;

    // public PacketGuiInteract(PacketGuiInteract.GuiInteractionEntity interaction, Entity entity) {
    // this(interaction, entity, 0);
    // }
    //
    // public PacketGuiInteract(PacketGuiInteract.GuiInteractionEntity interaction, Entity entity, int extra) {
    // this(interaction, entity.getId(), extra);
    // }
    //
    // public PacketGuiInteract(PacketGuiInteract.GuiInteractionEntity interaction, int entityID, int extra) {
    // this.interactionType = PacketGuiInteract.MMInteractionType.ENTITY;
    // this.entityInteraction = interaction;
    // this.entityID = entityID;
    // this.extra = extra;
    // }

    public EMExtraPacketGuiInteract(EMExtraGuiInteraction interaction, BlockEntity tile) {
        this(interaction, tile.getBlockPos());
    }

    public EMExtraPacketGuiInteract(EMExtraGuiInteraction interaction, BlockEntity tile, int extra) {
        this(interaction, tile.getBlockPos(), extra);
    }

    public EMExtraPacketGuiInteract(EMExtraGuiInteraction interaction, BlockPos tilePosition) {
        this(interaction, tilePosition, 0);
    }

    public EMExtraPacketGuiInteract(EMExtraGuiInteraction interaction, BlockPos tilePosition, int extra) {
        this.interactionType = EMExtraInteractionType.INT;
        this.interaction = interaction;
        this.tilePosition = tilePosition;
        this.extra = extra;
    }

    // public EMExtraPacketGuiInteract(GuiInteractionItem interaction, BlockEntity tile, ItemStack stack) {
    // this(interaction, tile.getBlockPos(), stack);
    // }
    //
    // public EMExtraPacketGuiInteract(GuiInteractionItem interaction, BlockPos tilePosition, ItemStack stack) {
    // this.interactionType = ExtraInteractionType.ITEM;
    // this.itemInteraction = interaction;
    // this.tilePosition = tilePosition;
    // this.extraItem = stack;
    // }

    @Override
    public void handle(IPayloadContext context) {
        Player player = context.player();
        if (interactionType == EMExtraInteractionType.ENTITY) {
            Entity entity = player.level().getEntity(entityID);
            // if (entity != null) {
            // entityInteraction.consume(entity, player, extra);
            // }
        } else {
            TileEntityMekanism tile = WorldUtils.getTileEntity(TileEntityMekanism.class, player.level(), tilePosition);
            if (tile != null) {
                if (interactionType == EMExtraInteractionType.INT) {
                    interaction.consume(tile, player, extra);
                }
                // else if (interactionType == ExtraInteractionType.ITEM) {
                // itemInteraction.consume(tile, player, extraItem);
                // }
            }
        }
    }

    @Override
    public @NotNull Type<EMExtraPacketGuiInteract> type() {
        return TYPE;
    }

    // public enum GuiInteractionItem {
    // ;
    //
    // public static final IntFunction<GuiInteractionItem> BY_ID = ByIdMap.continuous(GuiInteractionItem::ordinal,
    // values(), ByIdMap.OutOfBoundsStrategy.WRAP);
    // public static final StreamCodec<ByteBuf, GuiInteractionItem> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID,
    // GuiInteractionItem::ordinal);
    //
    // private final TriConsumer<TileEntityMekanism, Player, ItemStack> consumerForTile;
    //
    // GuiInteractionItem(TriConsumer<TileEntityMekanism, Player, ItemStack> consumerForTile) {
    // this.consumerForTile = consumerForTile;
    // }
    //
    // public void consume(TileEntityMekanism tile, Player player, ItemStack stack) {
    // consumerForTile.accept(tile, player, stack);
    // }
    // }

    public enum EMExtraGuiInteraction {

        AUTO_SORT_BUTTON((tile, player, extra) -> {
            if (tile instanceof TileEntityEMExtraFactory<?> factory) {
                factory.toggleSorting();
            } else if (EMExtras.hooks.mekmm.isLoaded()) {
                if (tile instanceof TileEntityEMExtraAdvancedFactoryBase<?> factory) factory.toggleSorting();
                if (tile instanceof TileEntityEMExtraMoreMachineFactory<?> factory) factory.toggleSorting();
            }
        }),
        ;

        public static final IntFunction<EMExtraGuiInteraction> BY_ID = ByIdMap.continuous(EMExtraGuiInteraction::ordinal, values(), ByIdMap.OutOfBoundsStrategy.WRAP);
        public static final StreamCodec<ByteBuf, EMExtraGuiInteraction> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, EMExtraGuiInteraction::ordinal);

        private final TriConsumer<TileEntityMekanism, Player, Integer> consumerForTile;

        EMExtraGuiInteraction(TriConsumer<TileEntityMekanism, Player, Integer> consumerForTile) {
            this.consumerForTile = consumerForTile;
        }

        public void consume(TileEntityMekanism tile, Player player, int extra) {
            consumerForTile.accept(tile, player, extra);
        }
    }

    private enum EMExtraInteractionType {

        ENTITY,
        ITEM,
        INT;

        public static final IntFunction<EMExtraInteractionType> BY_ID = ByIdMap.continuous(EMExtraInteractionType::ordinal, values(), ByIdMap.OutOfBoundsStrategy.WRAP);
        public static final StreamCodec<ByteBuf, EMExtraInteractionType> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, EMExtraInteractionType::ordinal);
    }
}