package train.common.mtc.packets.handlers;

import ebf.tim.utility.CommonUtil;


import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import train.common.api.Locomotive;
import train.common.mtc.TileInfoTransmitterSpeed;
import train.common.mtc.packets.PacketSetSpeed;

public  class PacketSetSpeedHandler implements IMessageHandler<PacketSetSpeed, IMessage> {

    @Override
    public IMessage onMessage(final PacketSetSpeed message, final MessageContext context) {
        // 1.12 handles packets on the netty thread; touching the world/GUIs there races with the game thread
        net.minecraftforge.fml.common.FMLCommonHandler.instance().getWorldThread(context.netHandler).addScheduledTask(() -> handleOnMainThread(message, context));
        return null;
    }

    private IMessage handleOnMainThread(PacketSetSpeed message, MessageContext context) {

        Entity theTrain = Minecraft.getMinecraft().world.getEntityByID(message.entity);
        //if (message.x == 0 && message.y == 0 && message.z == 0) {
         //   ((Locomotive) theTrain).speedLimit = "0";
         //   ((Locomotive) theTrain).speedChanged();
//
       //     return null;
      //  }

        TileEntity its =  CommonUtil.getTileEntity(Minecraft.getMinecraft().world, message.x, message.y, message.z);


        /* "instanceof" is null-safe, but we check to avoid four unnecessary instanceof checks for when the value is null anyways. */
        if (its != null && its instanceof TileInfoTransmitterSpeed) {

            ((TileInfoTransmitterSpeed) its).updateSpeed(message.speed);

            //  ExampleMod.itaChannel.sendTo(new PacketSetspeed(message.speed, message.entity, message.x, message.y, message.z));
        }


        if (theTrain != null && theTrain instanceof Locomotive) {

            ((Locomotive) theTrain).speedLimit = message.speed;


        }

        return null;
    }
}