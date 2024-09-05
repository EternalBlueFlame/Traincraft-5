package ebf.tim.networking;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import ebf.tim.entities.EntitySeat;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import train.common.Traincraft;
import train.common.api.EntityRollingStock;

/**
 * <h1>Seat Packet</h1>
 * bi-directional packet for managing seat changes while in a piece of stock.
 * @author broscolotos
 * @author 02Skaplan
 */
public class PacketSeatUpdate implements IMessage {
    /**the ID of the entity to dismount from*/
    private int rollingStockId, playerId, oldSeatIndex, newSeatIndex, dimension;

    public PacketSeatUpdate() {}
    public PacketSeatUpdate(int rollingStockId, int playerId, int oldSeatIndex, int newSeatIndex, int dimension) {
        this.rollingStockId = rollingStockId;
        this.playerId = playerId;
        this.oldSeatIndex = oldSeatIndex;
        this.newSeatIndex = newSeatIndex;
        this.dimension = dimension;
    }
    /**reads the packet on server to get the variables from the Byte Buffer*/
    @Override
    public void fromBytes(ByteBuf bbuf) {
        rollingStockId= bbuf.readInt();
        playerId=bbuf.readInt();
        oldSeatIndex=bbuf.readInt();
        newSeatIndex=bbuf.readInt();
        dimension=bbuf.readInt();
    }
    /**puts the variables into a Byte Buffer so they can be sent to server*/
    @Override
    public void toBytes(ByteBuf bbuf) {
        bbuf.writeInt(rollingStockId);
        bbuf.writeInt(playerId);
        bbuf.writeInt(oldSeatIndex);
        bbuf.writeInt(newSeatIndex);
        bbuf.writeInt(dimension);
    }

    public static class Handler implements IMessageHandler<PacketSeatUpdate,IMessage> {
        @Override public IMessage onMessage(PacketSeatUpdate message, MessageContext ctx) {
            EntityRollingStock rollingStockEntity;
            Entity playerEntity;
            EntitySeat oldSeat;
            EntitySeat newSeat;
            if (ctx.side == Side.SERVER) {
                rollingStockEntity = (EntityRollingStock) ctx.getServerHandler().playerEntity.getWorld().getEntityByID(message.rollingStockId);
                playerEntity = ctx.getServerHandler().playerEntity.getWorld().getEntityByID(message.playerId);

            } else {
                rollingStockEntity = (EntityRollingStock) Minecraft.getMinecraft().world.getEntityByID(message.rollingStockId);
                playerEntity = Minecraft.getMinecraft().world.getEntityByID(message.playerId);
            }
            oldSeat = rollingStockEntity.seats.get(message.oldSeatIndex);
            newSeat = rollingStockEntity.seats.get(message.newSeatIndex);
            oldSeat.removePassenger(playerEntity);
            newSeat.addPassenger(playerEntity);
            playerEntity.mountEntity(newSeat);
            if (ctx.side == Side.SERVER) {
                Traincraft.updateChannel.sendToAllAround(new PacketSeatUpdate(message.rollingStockId,message.playerId,message.oldSeatIndex,message.newSeatIndex, message.dimension),
                        new NetworkRegistry.TargetPoint(message.dimension,rollingStockEntity.posX,rollingStockEntity.posY,rollingStockEntity.posZ,256D));
            }
            return null;
        }
    }
}