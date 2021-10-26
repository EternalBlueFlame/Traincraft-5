package ebf.tim.networking;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import ebf.XmlBuilder;
import ebf.tim.entities.GenericRailTransport;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

/**
 * <h1>Mount packet</h1>
 * This is intended to be a replacement for
 * @see net.minecraft.network.play.client.C02PacketUseEntity
 * because for whatever reason, the stupid thing refuses to send for our entities.
 * @author Eternal Blue Flame
 */
public class PacketUpdateClients implements IMessage {
    /**the ID of the entity to dismount from*/
    private int entityId;
    private String entityData;

    public PacketUpdateClients() {}
    public PacketUpdateClients(String key, Entity entity) {
        this.entityId = entity.getEntityId();
        this.entityData=key;

    }
    /**reads the packet on server to get the variables from the Byte Buffer*/
    @Override
    public void fromBytes(ByteBuf bbuf) {
        Entity e = Minecraft.getMinecraft().theWorld.getEntityByID(bbuf.readInt());
        if (e instanceof GenericRailTransport) {
            ((GenericRailTransport)e).entityData= new XmlBuilder(ByteBufUtils.readUTF8String(bbuf));
        }
    }
    /**puts the variables into a Byte Buffer so they can be sent to server*/
    @Override
    public void toBytes(ByteBuf bbuf) {
        bbuf.writeInt(entityId);
        ByteBufUtils.writeUTF8String(bbuf, entityData);
    }
}
