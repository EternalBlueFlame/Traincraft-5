package train.common.core.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import train.common.api.IRollingStockLightControls;

public class PacketRollingStockLights implements IMessage
{
    boolean Lights;
    int	entityID;

    public PacketRollingStockLights()
    {

    }

    public PacketRollingStockLights(boolean lights, int trainEntity)
    {
    	this.Lights = lights;
    	this.entityID = trainEntity;
    }

    @Override
    public void fromBytes(ByteBuf bbuf)
    {
		this.Lights = bbuf.readBoolean();
		this.entityID = bbuf.readInt();
    }

    @Override
    public void toBytes(ByteBuf bbuf)
    {
		bbuf.writeBoolean(this.Lights);
		bbuf.writeInt(this.entityID);
    }

    public static class Handler implements IMessageHandler<PacketRollingStockLights, IMessage>
    {
        @Override
        public IMessage onMessage(PacketRollingStockLights message, MessageContext context)
        {
			Entity TrainEntity = context.getServerHandler().playerEntity.worldObj.getEntityByID(message.entityID);
			
			if (TrainEntity instanceof IRollingStockLightControls)
            {
                ((IRollingStockLightControls) TrainEntity).setPacketLights(message.Lights);
            }

            return null;
        }
    }
}