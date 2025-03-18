package train.common.core.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.DimensionManager;
import train.common.api.AbstractTrains;

/**
 * <h1>Mount packet</h1>
 * This is intended to be a replacement for
 * @see net.minecraft.network.play.client.C02PacketUseEntity
 * because for whatever reason, the stupid thing refuses to send for our entities.
 * @author Eternal Blue Flame
 */
public class PacketInteract implements IMessage {
    /**the ID of the entity to dismount from*/
    private int entityId, dimensionId, playerId;

    public PacketInteract() {}
    public PacketInteract(int entityId) {
        this.entityId = entityId;
        this.dimensionId= Minecraft.getMinecraft().thePlayer.worldObj.provider.dimensionId;
        this.playerId=Minecraft.getMinecraft().thePlayer.getEntityId();

    }
    /**reads the packet on server to get the variables from the Byte Buffer*/
    @Override
    public void fromBytes(ByteBuf bbuf) {
        entityId = bbuf.readInt();
        dimensionId=bbuf.readInt();
        playerId=bbuf.readInt();
        Entity e = DimensionManager.getWorld(dimensionId).getEntityByID(entityId);
        Entity p = DimensionManager.getWorld(dimensionId).getEntityByID(playerId);
        if (e instanceof AbstractTrains && p instanceof EntityPlayer) {
            e.interactFirst((EntityPlayer)p);
        }
    }
    /**puts the variables into a Byte Buffer so they can be sent to server*/
    @Override
    public void toBytes(ByteBuf bbuf) {
        bbuf.writeInt(entityId);
        bbuf.writeInt(dimensionId);
        bbuf.writeInt(playerId);
    }
}
