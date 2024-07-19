package train.common.core.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import ebf.tim.entities.EntitySeat;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import train.common.api.Locomotive;

public class PacketSetLocoTurnedOn implements IMessage {

    /**
     * The key that was pressed.
     */
    Boolean LocoOn;

    public PacketSetLocoTurnedOn() {
    }

    public PacketSetLocoTurnedOn(Boolean LocoOn) {
        this.LocoOn = LocoOn;
    }

    @Override
    public void fromBytes(ByteBuf bbuf) {
        this.LocoOn = bbuf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf bbuf) {
        bbuf.writeBoolean(this.LocoOn);
    }

    public static class Handler implements IMessageHandler<PacketSetLocoTurnedOn, IMessage> {
        @Override
        public IMessage onMessage(PacketSetLocoTurnedOn message, MessageContext context) {
            Entity trainEntity = context.getServerHandler().playerEntity.ridingEntity;
            if (trainEntity instanceof EntitySeat) {
                trainEntity = ((EntitySeat)context.getServerHandler().playerEntity.ridingEntity).parent;
            }

            /* "instanceof" is null-safe, but we check to avoid four unnecessary instanceof checks for when the value is null anyways. */
            if (trainEntity != null) {
                if (trainEntity instanceof Locomotive) {
                    ((Locomotive) trainEntity).setLocoTurnedOnFromPacket(message.LocoOn);
                }
            }

            return null;
        }
    }
}