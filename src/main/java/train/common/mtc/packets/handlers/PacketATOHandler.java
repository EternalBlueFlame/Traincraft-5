package train.common.mtc.packets.handlers;


import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import train.common.api.Locomotive;
import train.common.mtc.packets.PacketATO;

public class PacketATOHandler implements IMessageHandler<PacketATO, IMessage> {

   public IMessage onMessage(final PacketATO message, final MessageContext ctx) {
       // 1.12 handles packets on the netty thread; touching the world/GUIs there races with the game thread
       net.minecraftforge.fml.common.FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handleOnMainThread(message, ctx));
       return null;
   }

   private IMessage handleOnMainThread(PacketATO message, MessageContext ctx) {
      Entity theTrain = Minecraft.getMinecraft().world.getEntityByID(message.entity);
      if(theTrain instanceof Locomotive) {
         ((Locomotive)theTrain).atoStatus = message.status;
      }

      return null;
   }
}
