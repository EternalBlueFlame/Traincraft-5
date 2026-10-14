package train.common.mtc.packets.handlers;


import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.Entity;
import train.common.api.Locomotive;
import train.common.mtc.packets.PacketNCSlowDown;

public class PacketNCSlowDownHandler implements IMessageHandler<PacketNCSlowDown, IMessage> {
    @Override
    public IMessage onMessage(final PacketNCSlowDown message, final MessageContext ctx) {
        // 1.12 handles packets on the netty thread; touching the world/GUIs there races with the game thread
        net.minecraftforge.fml.common.FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handleOnMainThread(message, ctx));
        return null;
    }

    private IMessage handleOnMainThread(PacketNCSlowDown message, MessageContext ctx) {
        Entity trainEntity = ctx.getServerHandler().player.getEntityWorld().getEntityByID(message.entity);
        if(trainEntity != null) {


           trainEntity.motionX *= ((Locomotive)trainEntity).brake;

            trainEntity.motionZ *=((Locomotive)trainEntity).brake;
        }


        return null;
    }
}
