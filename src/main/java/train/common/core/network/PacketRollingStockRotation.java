package train.common.core.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import ebf.tim.utility.DebugUtil;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import train.common.api.EntityRollingStock;

/**
 * Sent to nearby clients to update the rotation of rolling stock.<p>
 * <p>
 * Field names adapted from 1.6 Packet code.
 */
public class PacketRollingStockRotation implements IMessage {

    int entityID;
    float rotationYawServer;
    int anglePitch;
    int posY;
    double frontx,frontz,backx,backz;

    public PacketRollingStockRotation() {
    }

    public PacketRollingStockRotation(EntityRollingStock entity, int anglePitch) {
        this.entityID = entity.getEntityId();
        this.rotationYawServer = entity.rotationYaw; // Don't even ASK ME why we do this. Probably an attempt to reduce Packet size, but at what cost of precision..?
        this.anglePitch = anglePitch;
        this.posY = Float.floatToIntBits((float) entity.posY); // improved accuracy with no usage increase
        this.frontx=entity.bogieFront.posX;
        this.frontz=entity.bogieFront.posZ;
        this.backx=entity.bogieBack.posX;
        this.backz=entity.bogieBack.posZ;
    }

    @Override
    public void fromBytes(ByteBuf bbuf) {
        this.entityID = bbuf.readInt();
        this.rotationYawServer = bbuf.readFloat();
        this.anglePitch = bbuf.readInt();
        this.posY = bbuf.readInt();
        if(DebugUtil.dev) {
            this.frontx = bbuf.readDouble();
            this.frontz = bbuf.readDouble();
            this.backx = bbuf.readDouble();
            this.backz = bbuf.readDouble();
        }
    }

    @Override
    public void toBytes(ByteBuf bbuf) {
        bbuf.writeInt(this.entityID);
        bbuf.writeFloat(this.rotationYawServer);
        bbuf.writeInt(this.anglePitch);
        bbuf.writeInt(this.posY);
        if(DebugUtil.dev) {
            bbuf.writeDouble(frontx);
            bbuf.writeDouble(frontz);
            bbuf.writeDouble(backx);
            bbuf.writeDouble(backz);
        }
    }

    public static class Handler implements IMessageHandler<PacketRollingStockRotation, IMessage> {
        @Override
        public IMessage onMessage(PacketRollingStockRotation message, MessageContext context) {
            Minecraft mc = Minecraft.getMinecraft();
            if (mc.theWorld != null) {
                Entity entity = mc.theWorld.getEntityByID(message.entityID);
                if (entity instanceof EntityRollingStock) {
                    EntityRollingStock rollingStock = (EntityRollingStock) entity;
                    rollingStock.rotationYaw = message.rotationYawServer;
                    rollingStock.rotationPitch = message.anglePitch;
                    rollingStock.posYFromServer= Float.intBitsToFloat(message.posY);
                    if(DebugUtil.dev && rollingStock.bogieFront!=null && rollingStock.bogieBack!=null) {
                        rollingStock.bogieFront.setPosition(message.frontx, rollingStock.bogieFront.posY, message.frontz);
                        rollingStock.bogieBack.setPosition(message.backx, rollingStock.bogieBack.posY, message.backz);
                    }
                }
            }

            return null;
        }
    }
}