package train.common.mtc.packets.handlers;


import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import ebf.tim.utility.DebugUtil;
import train.common.Traincraft;
import train.common.api.Locomotive;
import train.common.core.util.TraincraftUtil;
import train.common.mtc.packets.PacketGetSomethingFromServer;
import train.common.mtc.packets.PacketThingFromServer;

public class PacketGetSomethingFromServerHandler implements IMessageHandler<PacketGetSomethingFromServer, IMessage> {

    @Override
    public PacketThingFromServer onMessage(PacketGetSomethingFromServer message, MessageContext ctx) {
        Locomotive trainEntity = (Locomotive)ctx.getServerHandler().playerEntity.getWorld().getEntityByID(message.entity);
        if (message.function == 1 && trainEntity !=null) {
           //Get all of the speed/stoppoint/speedchange data.

            PacketThingFromServer packetToSend = new PacketThingFromServer();
            assert packetToSend != null;
            packetToSend.speedLimit = Integer.valueOf(trainEntity.speedLimit);
            packetToSend.nextSpeedLimit = trainEntity.nextSpeedLimit;
            packetToSend.xFromStopPoint = trainEntity.xFromStopPoint;
            packetToSend.yFromStopPoint = trainEntity.yFromStopPoint;
            packetToSend.zFromStopPoint = trainEntity.zFromStopPoint;

            packetToSend.xFromSpeedChange = trainEntity.xSpeedLimitChange;
            packetToSend.yFromSpeedChange = trainEntity.ySpeedLimitChange;
            packetToSend.zFromSpeedChange = trainEntity.zSpeedLimitChange;
            DebugUtil.println("Completed creation!");
            DebugUtil.println(packetToSend.xFromSpeedChange);
            Traincraft.gsfsrChannel.sendToAll(packetToSend);

        }
        return null;
    }
}
