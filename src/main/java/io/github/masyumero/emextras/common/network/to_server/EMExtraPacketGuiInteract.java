package io.github.masyumero.emextras.common.network.to_server;

import io.github.masyumero.emextras.common.tile.factory.TileEntityEMExtraFactory;
import mekanism.api.functions.TriConsumer;
import mekanism.common.network.IMekanismPacket;
import mekanism.common.tile.base.TileEntityMekanism;
import mekanism.common.util.WorldUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;

public class EMExtraPacketGuiInteract implements IMekanismPacket {

    private final Type interactionType;

    private EMExtraGuiInteraction interaction;
    private GuiInteractionItem itemInteraction;
    private GuiInteractionEntity entityInteraction;
    private BlockPos tilePosition;
    private ItemStack emextraItem;
    private int entityID;
    private int extra;

    public EMExtraPacketGuiInteract(GuiInteractionEntity interaction, Entity entity) {
        this(interaction, entity, 0);
    }

    public EMExtraPacketGuiInteract(GuiInteractionEntity interaction, Entity entity, int extra) {
        this(interaction, entity.getId(), extra);
    }

    public EMExtraPacketGuiInteract(GuiInteractionEntity interaction, int entityID, int extra) {
        this.interactionType = Type.ENTITY;
        this.entityInteraction = interaction;
        this.entityID = entityID;
        this.extra = extra;
    }

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
        this.interactionType = Type.INT;
        this.interaction = interaction;
        this.tilePosition = tilePosition;
        this.extra = extra;
    }

    public EMExtraPacketGuiInteract(GuiInteractionItem interaction, BlockEntity tile, ItemStack stack) {
        this(interaction, tile.getBlockPos(), stack);
    }

    public EMExtraPacketGuiInteract(GuiInteractionItem interaction, BlockPos tilePosition, ItemStack stack) {
        this.interactionType = Type.ITEM;
        this.itemInteraction = interaction;
        this.tilePosition = tilePosition;
        this.emextraItem = stack;
    }

    @Override
    public void handle(NetworkEvent.Context context) {
        Player player = context.getSender();
        if (player != null) {
            if (interactionType == Type.ENTITY) {
                Entity entity = player.level().getEntity(entityID);
                if (entity != null) {
                    entityInteraction.consume(entity, player, extra);
                }
            } else {
                TileEntityMekanism tile = WorldUtils.getTileEntity(TileEntityMekanism.class, player.level(), tilePosition);
                if (tile != null) {
                    if (interactionType == Type.INT) {
                        interaction.consume(tile, player, extra);
                    } else if (interactionType == Type.ITEM) {
                        itemInteraction.consume(tile, player, emextraItem);
                    }
                }
            }
        }
    }

    @Override
    public void encode(FriendlyByteBuf buffer) {
        buffer.writeEnum(interactionType);
        switch (interactionType) {
            case ENTITY -> {
                buffer.writeEnum(entityInteraction);
                buffer.writeVarInt(entityID);
                buffer.writeVarInt(extra);
            }
            case INT -> {
                buffer.writeEnum(interaction);
                buffer.writeBlockPos(tilePosition);
                buffer.writeVarInt(extra);
            }
            case ITEM -> {
                buffer.writeEnum(itemInteraction);
                buffer.writeBlockPos(tilePosition);
                buffer.writeItem(emextraItem);
            }
        }
    }

    public static EMExtraPacketGuiInteract decode(FriendlyByteBuf buffer) {
        return switch (buffer.readEnum(Type.class)) {
            case ENTITY -> new EMExtraPacketGuiInteract(buffer.readEnum(GuiInteractionEntity.class), buffer.readVarInt(), buffer.readVarInt());
            case INT -> new EMExtraPacketGuiInteract(buffer.readEnum(EMExtraGuiInteraction.class), buffer.readBlockPos(), buffer.readVarInt());
            case ITEM -> new EMExtraPacketGuiInteract(buffer.readEnum(GuiInteractionItem.class), buffer.readBlockPos(), buffer.readItem());
        };
    }

    public enum GuiInteractionItem {
        ;

        private final TriConsumer<TileEntityMekanism, Player, ItemStack> consumerForTile;

        GuiInteractionItem(TriConsumer<TileEntityMekanism, Player, ItemStack> consumerForTile) {
            this.consumerForTile = consumerForTile;
        }

        public void consume(TileEntityMekanism tile, Player player, ItemStack stack) {
            consumerForTile.accept(tile, player, stack);
        }
    }

    public enum EMExtraGuiInteraction {
        AUTO_SORT_BUTTON((tile, player, extra) -> {
            if (tile instanceof TileEntityEMExtraFactory<?> factory) {
                factory.toggleSorting();
            }
        });

        private final TriConsumer<TileEntityMekanism, Player, Integer> consumerForTile;

        EMExtraGuiInteraction(TriConsumer<TileEntityMekanism, Player, Integer> consumerForTile) {
            this.consumerForTile = consumerForTile;
        }

        public void consume(TileEntityMekanism tile, Player player, int extra) {
            consumerForTile.accept(tile, player, extra);
        }
    }

    public enum GuiInteractionEntity {
        ;

        private final TriConsumer<Entity, Player, Integer> consumerForEntity;

        GuiInteractionEntity(TriConsumer<Entity, Player, Integer> consumerForEntity) {
            this.consumerForEntity = consumerForEntity;
        }

        public void consume(Entity entity, Player player, int extra) {
            consumerForEntity.accept(entity, player, extra);
        }
    }

    private enum Type {
        ENTITY,
        ITEM,
        INT
    }
}