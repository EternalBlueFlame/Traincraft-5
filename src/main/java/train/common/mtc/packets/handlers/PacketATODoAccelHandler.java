package train.common.mtc.packets.handlers;


import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import train.common.api.Locomotive;
import train.common.mtc.packets.PacketDoAccel;

public class PacketATODoAccelHandler implements IMessageHandler<PacketDoAccel, IMessage> {

   public IMessage onMessage(final PacketDoAccel message, final MessageContext ctx) {
       // 1.12 handles packets on the netty thread; touching the world/GUIs there races with the game thread
       net.minecraftforge.fml.common.FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handleOnMainThread(message, ctx));
       return null;
   }

   private IMessage handleOnMainThread(PacketDoAccel message, MessageContext ctx) {
      Entity trainEntity = ctx.getServerHandler().player.getEntityWorld().getEntityByID(message.entity);
      if(trainEntity != null) {
         this.accel(trainEntity);
      }

      return null;
   }

   public void accel(Entity entity) {
      if(entity.world != null) {

         Locomotive theTrain = (Locomotive)entity;
         if((int)theTrain.getSpeed() - 2 <= theTrain.speedLimit) {
            if (ebf.tim.utility.CommonUtil.rider(theTrain) == null) {return;}
            int dir = MathHelper
                    .floor(((theTrain).rotationYaw * 4F) / 360F + 0.5D) & 3;
            if (dir == 2){

               entity.motionZ -= 0.0075 * theTrain.accelerate;



            } else if (dir == 0){

               entity.motionZ += 0.0075 * theTrain.accelerate;

            } else if (dir == 1){

               entity.motionX -= 0.0075 * theTrain.accelerate;

            } else if (dir == 3){

               entity.motionX += 0.0075 * theTrain.accelerate;

            }

         }
      }
   }
}
