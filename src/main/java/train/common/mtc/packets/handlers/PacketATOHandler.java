package train.common.mtc.packets.handlers;


import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import train.common.api.Locomotive;
import train.common.mtc.packets.PacketATO;

public class PacketATOHandler implements IMessageHandler<PacketATO, IMessage> {

   public IMessage onMessage(PacketATO message, MessageContext ctx) {
      Entity theTrain = Minecraft.getMinecraft().world.getEntityByID(message.entity);
      if(theTrain instanceof Locomotive) {
         ((Locomotive)theTrain).atoStatus = message.status;
      }

      return null;
   }
}
