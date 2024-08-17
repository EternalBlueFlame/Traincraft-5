package train.common.core.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import train.common.api.IRollingStockLightControls;

public class PacketRollingStockDitchLights implements IMessage
{
    byte ditchLightsMode;
    int	entityID;

    public PacketRollingStockDitchLights()
    {

    }

    public PacketRollingStockDitchLights(byte ditchLightsMode, int trainEntity)
    {
    	this.ditchLightsMode = ditchLightsMode;
    	this.entityID = trainEntity;
    }

    @Override
    public void fromBytes(ByteBuf bbuf)
    {
		this.ditchLightsMode = bbuf.readByte();
		this.entityID = bbuf.readInt();
    }

    @Override
    public void toBytes(ByteBuf bbuf)
    {
		bbuf.writeByte(this.ditchLightsMode);
		bbuf.writeInt(this.entityID);
    }

    public static class Handler implements IMessageHandler<PacketRollingStockDitchLights, IMessage>
    {
        @Override
        public IMessage onMessage(PacketRollingStockDitchLights message, MessageContext context)
        {
			Entity TrainEntity = context.getServerHandler().playerEntity.worldObj.getEntityByID(message.entityID);
			
			if (TrainEntity instanceof IRollingStockLightControls)
            {
                ((IRollingStockLightControls) TrainEntity).setPacketDitchLightsMode(message.ditchLightsMode);
            }

            return null;
        }
    }
}