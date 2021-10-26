package ebf.tim.networking;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import ebf.tim.entities.GenericRailTransport;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.DimensionManager;

/**
 * <h1>Remove entity packet</h1>
 * used to remove an entity from the world. this is necessary because the entity interaction is through client only hitboxes.
 * @author Eternal Blue Flame
 */
public class PacketRemove implements IMessage {
    /**the entity ID to define what entity to use the function on*/
    private int entityId, dimensionId, senderID;

    public PacketRemove() {}
    public PacketRemove(int target, int sender) {
        this.entityId = target;
        this.dimensionId= Minecraft.getMinecraft().thePlayer.worldObj.provider.dimensionId;
        this.senderID=sender;
    }
    /**reads the packet on server to get the variables from the Byte Buffer*/
    @Override
    public void fromBytes(ByteBuf bbuf) {
        entityId = bbuf.readInt();
        this.dimensionId=bbuf.readInt();
        senderID = bbuf.readInt();

        Entity entity = DimensionManager.getWorld(dimensionId).getEntityByID(entityId);
        Entity other = senderID==-1?null:DimensionManager.getWorld(dimensionId).getEntityByID(senderID);
        //if the entity was an instance of Generic Rail Transport, then spawn it's item and remove it from world.
        if (entity instanceof GenericRailTransport) {
            if (other instanceof EntityPlayer){
                entity.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) other),1);
            } else {
                entity.attackEntityFrom(null,1);
            }
        }
    }
    /**puts the variables into a Byte Buffer so they can be sent to server*/
    @Override
    public void toBytes(ByteBuf bbuf) {
        bbuf.writeInt(entityId);
        bbuf.writeInt(dimensionId);
        bbuf.writeInt(senderID);
    }
}
