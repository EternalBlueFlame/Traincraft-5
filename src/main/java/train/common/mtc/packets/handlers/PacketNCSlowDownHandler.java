package train.common.mtc.packets.handlers;


import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.Entity;
import train.common.api.Locomotive;
import train.common.mtc.packets.PacketNCSlowDown;

public class PacketNCSlowDownHandler implements IMessageHandler<PacketNCSlowDown, IMessage> {
    @Override
    public IMessage onMessage(PacketNCSlowDown message, MessageContext ctx) {
        Entity trainEntity = ctx.getServerHandler().playerEntity.getEntityWorld().getEntityByID(message.entity);
        if(trainEntity != null) {


           trainEntity.motionX *= ((Locomotive)trainEntity).brake;

            trainEntity.motionZ *=((Locomotive)trainEntity).brake;
        }


        return null;
    }
}
