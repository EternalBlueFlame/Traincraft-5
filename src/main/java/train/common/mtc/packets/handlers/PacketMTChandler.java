package train.common.mtc.packets.handlers;


import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import train.common.api.Locomotive;
import train.common.mtc.packets.PacketMTC;

public class PacketMTChandler implements IMessageHandler<PacketMTC, IMessage> {
    @Override
    public IMessage onMessage(final PacketMTC message, final MessageContext ctx) {
        // 1.12 handles packets on the netty thread; touching the world/GUIs there races with the game thread
        net.minecraftforge.fml.common.FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handleOnMainThread(message, ctx));
        return null;
    }

    private IMessage handleOnMainThread(PacketMTC message, MessageContext ctx) {

            Entity theTrain = Minecraft.getMinecraft().world.getEntityByID(message.entity);
            if (theTrain != null && theTrain instanceof Locomotive) {

                ((Locomotive) theTrain).mtcStatus = message.mtcStatus;
                if (message.mode != 0) { ((Locomotive) theTrain).mtcType = message.mode;}
                //if (message.mtcStatus == 0) {
//
                //        ((Locomotive) theTrain).speedLimit = "0";
                //        ((Locomotive) theTrain).speedChanged();
                //        ExampleMod.itsChannel.sendToAll(new PacketSetSpeed(0, 0, 0, 0,  ((Locomotive) theTrain).getEntityId()));


              //  }

            }



            return null;
        }

}

