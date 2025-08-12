package train.common.core.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import train.common.Traincraft;
import train.common.api.AbstractTrains;
import train.common.api.EntityRollingStock;
import train.common.entity.TrustedPlayer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PacketSetTrainLockedToClient implements IMessage {

	boolean	bool;
    boolean requestPacket;
	int	entityID;
    int playerEntityID;
    boolean propagate;
    List<TrustedPlayer> trustedList = new ArrayList<>();
    public PacketSetTrainLockedToClient(){}

    @Deprecated
	public PacketSetTrainLockedToClient(boolean bool, int trainEntity) {
        this.bool = bool;
		this.entityID = trainEntity;
    }


    /**
     * <p>Client <-> Server communication packet to update lock and trusted list.</p>
     * @param bool Locked status. True for locked, false for unlocked.
     * @param trustedList Trusted players list.
     * @param propagate Whether to propagate the changes throughout the consist.
     */
    public PacketSetTrainLockedToClient(boolean bool, List<TrustedPlayer> trustedList, int trainEntity, boolean propagate) {
        this.bool = bool;
        this.entityID = trainEntity;
        this.trustedList = trustedList;
        this.propagate = propagate;
        requestPacket = false;
    }

    /**
     * <p>Client -> Server communication packet to request lock and trusted list from server.</p>
     */
    public PacketSetTrainLockedToClient(int trainEntity, int playerEntityID) {
        this.entityID = trainEntity;
        this.playerEntityID = playerEntityID;
        requestPacket = true;
    }

    @Override
    public void fromBytes(ByteBuf bbuf) {
        this.entityID = bbuf.readInt();
        if (!bbuf.readBoolean()) {
            requestPacket = false;
            this.bool = bbuf.readBoolean();
            int numberOfTrustedPlayers = bbuf.readInt();
            for (int i = 0; i < numberOfTrustedPlayers; i++) {
                trustedList.add(new TrustedPlayer(ByteBufUtils.readUTF8String(bbuf), bbuf.readBoolean()));
            }
            propagate = bbuf.readBoolean();
        } else {
             requestPacket = true;
            this.playerEntityID = bbuf.readInt();
        }
    }

    @Override
    public void toBytes(ByteBuf bbuf) {
        bbuf.writeInt(this.entityID);
        bbuf.writeBoolean(requestPacket);
        if (!requestPacket) {
            bbuf.writeBoolean(this.bool);
            bbuf.writeInt(trustedList.size());
            for (TrustedPlayer trustedPlayer : trustedList) {
                ByteBufUtils.writeUTF8String(bbuf, trustedPlayer.getDisplayName());
                bbuf.writeBoolean(trustedPlayer.hasBreakAccess());
            }
            bbuf.writeBoolean(propagate);
        } else {
            bbuf.writeInt(playerEntityID);
        }
    }

    public static class Handler implements IMessageHandler<PacketSetTrainLockedToClient, IMessage> {

        @Override
        public IMessage onMessage(PacketSetTrainLockedToClient message, MessageContext context) {
            if (context.side.isServer()) {
                Entity TrainEntity = context.getServerHandler().playerEntity.worldObj.getEntityByID(message.entityID);
                if (!message.requestPacket) {
                    if (TrainEntity instanceof AbstractTrains) {
                        ((AbstractTrains) TrainEntity).setTrainLockedFromPacket(message.bool);
                        ((AbstractTrains) TrainEntity).setTrustedList(message.trustedList);
                            Traincraft.lockChannel.sendToAllAround(new PacketSetTrainLockedToClient(message.bool, message.trustedList, message.entityID, false), new NetworkRegistry.TargetPoint(TrainEntity.dimension, TrainEntity.posX, TrainEntity.posY, TrainEntity.posZ, 256D));
                        if (message.propagate)
                            propagateChanges((EntityRollingStock) TrainEntity, message.trustedList);

                    }
                } else {
                    if (TrainEntity instanceof AbstractTrains) {
                        if (context.getServerHandler().playerEntity.worldObj.getEntityByID(message.playerEntityID) != null) {
                            Traincraft.lockChannel.sendTo(new PacketSetTrainLockedToClient(((AbstractTrains) TrainEntity).getTrainLockedFromPacket(), ((AbstractTrains) TrainEntity).getTrustedList(), message.entityID, false), ((EntityPlayerMP) context.getServerHandler().playerEntity.worldObj.getEntityByID(message.playerEntityID)));
                        }
                    }
                }
            } else {
                if (!message.requestPacket) {
                    Entity TrainEntity = Minecraft.getMinecraft().theWorld.getEntityByID(message.entityID);
                    if (TrainEntity instanceof AbstractTrains) {
                        ((AbstractTrains) TrainEntity).setTrustedList(message.trustedList);
                        ((AbstractTrains) TrainEntity).setTrainLockedFromPacket(message.bool);
                    }
                }
            }
            return null;
        }

        /**
         * <p>Server side method used to update the lock status and trusted players list for all cars in a given consist
         * belonging to the train owner.</p>
         * @author 02skaplan
         * @param rollingStock The main rolling stock entity being updated and from which we should propogate changes.
         * @param trustedPlayerList List of trusted players.
         */
        private static void propagateChanges(EntityRollingStock rollingStock, List<TrustedPlayer> trustedPlayerList) {
            boolean locked = rollingStock.getTrainLockedFromPacket();
            for (AbstractTrains car : rollingStock.consist) {
                if (car != rollingStock && car.getTrainOwner().equalsIgnoreCase(rollingStock.getTrainOwner())) {
                    car.setTrainLockedFromPacket(locked);
                    car.setTrustedList(trustedPlayerList);
                    Traincraft.lockChannel.sendToAllAround(new PacketSetTrainLockedToClient(locked, trustedPlayerList, car.getEntityId(), false),
                            new NetworkRegistry.TargetPoint(car.dimension, car.posX, car.posY, car.posZ, 256D));
                }
            }
        }
    }
}