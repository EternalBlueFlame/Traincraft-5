package train.common.mtc.packets.handlers;


import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import train.common.api.Locomotive;
import train.common.mtc.packets.PacketATOSetStopPoint;

public class PacketATOSetStopPointHandler implements IMessageHandler<PacketATOSetStopPoint, IMessage> {

   public IMessage onMessage(final PacketATOSetStopPoint message, final MessageContext ctx) {
       // 1.12 handles packets on the netty thread; touching the world/GUIs there races with the game thread
       net.minecraftforge.fml.common.FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handleOnMainThread(message, ctx));
       return null;
   }

   private IMessage handleOnMainThread(PacketATOSetStopPoint message, MessageContext ctx) {
      Entity trainEntity = Minecraft.getMinecraft().player.getEntityWorld().getEntityByID(message.entity);
      if(trainEntity != null) {
         Locomotive theTrain = (Locomotive)trainEntity;
         theTrain.xFromStopPoint = message.xPos;
         theTrain.yFromStopPoint = message.yPos;
         theTrain.zFromStopPoint = message.zPos;
         theTrain.xStationStop = message.sXPos;
         theTrain.yStationStop = message.sYPos;
         theTrain.zStationStop = message.sZPos;
      }

      return null;
   }
}
