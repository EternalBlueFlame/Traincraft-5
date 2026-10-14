package train.common.core.network;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import train.common.entity.rollingStockOld.special.EntityTracksBuilder;

public class PacketTrackBuilderFollow implements IMessage {

    /**
     * The key that was pressed.
     */
    public int set;
    public int id;

    public PacketTrackBuilderFollow() {
    }

    public PacketTrackBuilderFollow(int set, int id) {
        this.set = set;
        this.id = id;
    }

    @Override
    public void fromBytes(ByteBuf bbuf) {
        this.set = bbuf.readInt();
        this.id = bbuf.readInt();
    }

    @Override
    public void toBytes(ByteBuf bbuf) {
        bbuf.writeInt(this.set);
        bbuf.writeInt(id);
    }

    public static class Handler implements IMessageHandler<PacketTrackBuilderFollow, IMessage> {
        @Override
        public IMessage onMessage(final PacketTrackBuilderFollow message, final MessageContext context) {
            // 1.12 handles packets on the netty thread; touching the world/GUIs there races with the game thread
            net.minecraftforge.fml.common.FMLCommonHandler.instance().getWorldThread(context.netHandler).addScheduledTask(() -> handleOnMainThread(message, context));
            return null;
        }

        private IMessage handleOnMainThread(PacketTrackBuilderFollow message, MessageContext context) {
            Entity builderEntity = context.getServerHandler().player.world.getEntityByID(message.id);
            /*
             * "instanceof" is null-safe, but we check to avoid four unnecessary instanceof checks
             * for when the value is null anyways.
             */
            if (builderEntity != null) {
                if (builderEntity instanceof EntityTracksBuilder) {
                    ((EntityTracksBuilder) builderEntity).setFollowTracksFromPacket(message.set);
                }
            }

            return null;
        }
    }
}