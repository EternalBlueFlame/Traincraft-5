package train.common.core.network;

import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import train.common.api.Locomotive;

public class PacketUpdateTrainID implements IMessage {
    public int entityID;
    public String trainID;

    public PacketUpdateTrainID() {
    }

    public PacketUpdateTrainID(Integer entityID, String trainID) {
        this.entityID = entityID;
        this.trainID = trainID;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.entityID = buf.readInt();
        trainID = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(entityID);
        ByteBufUtils.writeUTF8String(buf, trainID);
    }

    public static class Handler implements IMessageHandler<PacketUpdateTrainID, IMessage> {
        @Override
        public IMessage onMessage(final PacketUpdateTrainID message, final MessageContext context) {
            // 1.12 handles packets on the netty thread; touching the world/GUIs there races with the game thread
            net.minecraftforge.fml.common.FMLCommonHandler.instance().getWorldThread(context.netHandler).addScheduledTask(() -> handleOnMainThread(message, context));
            return null;
        }

        private IMessage handleOnMainThread(PacketUpdateTrainID message, MessageContext context) {
            Entity TrainEntity = context.getServerHandler().player.world.getEntityByID(message.entityID);
            if (TrainEntity instanceof Locomotive) {
                ((Locomotive) TrainEntity).trainID = message.trainID;
            }
            return null;
        }
    }
}
