package train.common.core.network;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import train.common.api.Locomotive;

/**
 * Sent to nearby clients to update a train's inventory fill status.
 */
public class PacketSlotsFilled implements IMessage {

    /**
     * The amount of slots filled.
     */
    int slotsFilled;

    /**
     * The ID of the entity.
     */
    int entityID;

    public PacketSlotsFilled() {
    }

    public PacketSlotsFilled(Locomotive train, int slotsFilled) {
        this.slotsFilled = slotsFilled;
        this.entityID = train.getEntityId();
    }

    @Override
    public void fromBytes(ByteBuf bbuf) {
        this.slotsFilled = bbuf.readInt();
        this.entityID = bbuf.readInt();
    }

    @Override
    public void toBytes(ByteBuf bbuf) {
        bbuf.writeInt(this.slotsFilled);
        bbuf.writeInt(this.entityID);
    }

    public static class Handler implements IMessageHandler<PacketSlotsFilled, IMessage> {
        @Override
        public IMessage onMessage(final PacketSlotsFilled message, final MessageContext context) {
            // 1.12 handles packets on the netty thread; touching the world/GUIs there races with the game thread
            net.minecraftforge.fml.common.FMLCommonHandler.instance().getWorldThread(context.netHandler).addScheduledTask(() -> handleOnMainThread(message, context));
            return null;
        }

        private IMessage handleOnMainThread(PacketSlotsFilled message, MessageContext context) {
            Entity entity = Minecraft.getMinecraft().world.getEntityByID(message.entityID);

            if (entity instanceof Locomotive) {
                ((Locomotive) entity).recieveSlotsFilled(message.slotsFilled);
            }

            return null;
        }
    }
}