package train.common.core.network;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import train.common.api.EntityRollingStock;
import train.common.api.Locomotive;

public class PacketParkingBrake implements IMessage {

    boolean ParkingBrake;
    int entityID;

    public PacketParkingBrake() {
    }

    public PacketParkingBrake(boolean parking, int trainEntity) {
        this.ParkingBrake = parking;
        this.entityID = trainEntity;
    }

    @Override
    public void fromBytes(ByteBuf bbuf) {
        this.ParkingBrake = bbuf.readBoolean();
        this.entityID = bbuf.readInt();
    }

    @Override
    public void toBytes(ByteBuf bbuf) {
        bbuf.writeBoolean(this.ParkingBrake);
        bbuf.writeInt(this.entityID);
    }

    public static class Handler implements IMessageHandler<PacketParkingBrake, IMessage> {
        @Override
        public IMessage onMessage(final PacketParkingBrake message, final MessageContext context) {
            // 1.12 handles packets on the netty thread; touching the world/GUIs there races with the game thread
            net.minecraftforge.fml.common.FMLCommonHandler.instance().getWorldThread(context.netHandler).addScheduledTask(() -> handleOnMainThread(message, context));
            return null;
        }

        private IMessage handleOnMainThread(PacketParkingBrake message, MessageContext context) {
            Entity TrainEntity = context.getServerHandler().player.world.getEntityByID(message.entityID);

            if (TrainEntity instanceof Locomotive) {
                ((Locomotive) TrainEntity).setParkingBrakeFromPacket(message.ParkingBrake);
            }

            if (TrainEntity instanceof EntityRollingStock) {
                ((EntityRollingStock) TrainEntity).isBraking = message.ParkingBrake;
            }

            return null;
        }
    }
}