package ebf.tim.networking;

import ebf.tim.TrainsInMotion;
import ebf.tim.entities.EntitySeat;
import ebf.tim.entities.GenericRailTransport;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

/**
 * <h1>Seat Packet</h1>
 * bi-directional packet for managing seat changes while in a piece of stock.
 * @author broscolotos
 * @author 02Skaplan
 */
public class PacketSeatUpdate implements IMessage {
    /**the ID of the entity to dismount from*/
    private int rollingStockId, playerId, oldSeatIndex, newSeatIndex;

    public PacketSeatUpdate() {}
    public PacketSeatUpdate(int rollingStockId, int playerId, int oldSeatIndex, int newSeatIndex) {
        this.rollingStockId = rollingStockId;
        this.playerId = playerId;
        this.oldSeatIndex = oldSeatIndex;
        this.newSeatIndex = newSeatIndex;
    }
    /**reads the packet on server to get the variables from the Byte Buffer*/
    @Override
    public void fromBytes(ByteBuf bbuf) {
        rollingStockId= bbuf.readInt();
        playerId=bbuf.readInt();
        oldSeatIndex=bbuf.readInt();
        newSeatIndex=bbuf.readInt();
    }
    /**puts the variables into a Byte Buffer so they can be sent to server*/
    @Override
    public void toBytes(ByteBuf bbuf) {
        bbuf.writeInt(rollingStockId);
        bbuf.writeInt(playerId);
        bbuf.writeInt(oldSeatIndex);
        bbuf.writeInt(newSeatIndex);
    }

    public static class Handler implements IMessageHandler<PacketSeatUpdate,IMessage> {
        @Override public IMessage onMessage(PacketSeatUpdate message, MessageContext ctx) {
            GenericRailTransport rollingStockEntity;
            Entity playerEntity;
            EntitySeat oldSeat;
            EntitySeat newSeat;
            if (ctx.side == Side.SERVER) {
                rollingStockEntity = (GenericRailTransport) ctx.getServerHandler().player.world.getEntityByID(message.rollingStockId);
                playerEntity = ctx.getServerHandler().player.world.getEntityByID(message.playerId);

            } else {
                rollingStockEntity = (GenericRailTransport) Minecraft.getMinecraft().world.getEntityByID(message.rollingStockId);
                playerEntity = Minecraft.getMinecraft().world.getEntityByID(message.playerId);
            }
            oldSeat = rollingStockEntity.seats.get(message.oldSeatIndex);
            newSeat = rollingStockEntity.seats.get(message.newSeatIndex);
            newSeat.addPassenger(playerEntity);
            oldSeat.removePassenger(playerEntity);
            //playerEntity.getPassengers(newSeat);
            if (ctx.side == Side.SERVER) {
                TrainsInMotion.updateChannel.sendToAllAround(new PacketSeatUpdate(message.rollingStockId,message.playerId,message.oldSeatIndex,message.newSeatIndex),
                        new NetworkRegistry.TargetPoint(playerEntity.dimension,rollingStockEntity.posX,rollingStockEntity.posY,rollingStockEntity.posZ,256D));
            }
            return null;
        }
    }
}
