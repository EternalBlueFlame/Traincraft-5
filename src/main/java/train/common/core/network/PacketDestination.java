package train.common.core.network;

import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import train.common.api.Locomotive;

public class PacketDestination implements IMessage {
    public int entityID;
    public String destination;

    public PacketDestination() {
    }

    public PacketDestination(Integer entityID, String destination) {
        this.entityID = entityID;
        this.destination = destination;
    }


    @Override
    public void fromBytes(ByteBuf buf) {
        this.entityID = buf.readInt();
        destination = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(entityID);
        ByteBufUtils.writeUTF8String(buf, destination);
    }

    public static class Handler implements IMessageHandler<PacketDestination, IMessage> {
        @Override
        public IMessage onMessage(final PacketDestination message, final MessageContext context) {
            // 1.12 handles packets on the netty thread; touching the world/GUIs there races with the game thread
            net.minecraftforge.fml.common.FMLCommonHandler.instance().getWorldThread(context.netHandler).addScheduledTask(() -> handleOnMainThread(message, context));
            return null;
        }

        private IMessage handleOnMainThread(PacketDestination message, MessageContext context) {
            Entity TrainEntity = context.getServerHandler().player.world.getEntityByID(message.entityID);
            if (TrainEntity instanceof Locomotive) {
                ((Locomotive) TrainEntity).destination = message.destination;
            }

            return null;
        }
    }
}
