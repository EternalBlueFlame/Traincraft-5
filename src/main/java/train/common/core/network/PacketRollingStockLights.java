package train.common.core.network;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
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
        public IMessage onMessage(final PacketRollingStockLights message, final MessageContext context) {
            // 1.12 handles packets on the netty thread; touching the world/GUIs there races with the game thread
            net.minecraftforge.fml.common.FMLCommonHandler.instance().getWorldThread(context.netHandler).addScheduledTask(() -> handleOnMainThread(message, context));
            return null;
        }

        private IMessage handleOnMainThread(PacketRollingStockLights message, MessageContext context) {
			Entity TrainEntity = context.getServerHandler().player.world.getEntityByID(message.entityID);
			
			if (TrainEntity instanceof IRollingStockLightControls)
            {
                ((IRollingStockLightControls) TrainEntity).setPacketLights(message.Lights);
            }

            return null;
        }
    }
}