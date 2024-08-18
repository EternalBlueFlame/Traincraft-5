package train.common.core.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import train.common.api.IRollingStockLightControls;

public class PacketRollingStockBeacon implements IMessage
{
    boolean beacon;
    int	entityID;

    public PacketRollingStockBeacon()
    {

    }

    public PacketRollingStockBeacon(boolean beacon, int trainEntity)
    {
    	this.beacon = beacon;
    	this.entityID = trainEntity;
    }

    @Override
    public void fromBytes(ByteBuf bbuf)
    {
		this.beacon = bbuf.readBoolean();
		this.entityID = bbuf.readInt();
    }

    @Override
    public void toBytes(ByteBuf bbuf)
    {
		bbuf.writeBoolean(this.beacon);
		bbuf.writeInt(this.entityID);
    }

    public static class Handler implements IMessageHandler<PacketRollingStockBeacon, IMessage>
    {
        @Override
        public IMessage onMessage(PacketRollingStockBeacon message, MessageContext context)
        {
			Entity TrainEntity = context.getServerHandler().playerEntity.worldObj.getEntityByID(message.entityID);
			
			if (TrainEntity instanceof IRollingStockLightControls)
            {
                ((IRollingStockLightControls) TrainEntity).setPacketBeacon(message.beacon);
            }

            return null;
        }
    }
}