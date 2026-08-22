package train.common.mtc.packets.handlers;


import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import train.common.mtc.packets.PacketThingFromServer;

public class PacketThingFromServerHandler implements IMessageHandler<PacketThingFromServer, IMessage> {
    @Override
    public IMessage onMessage(PacketThingFromServer message, MessageContext ctx) {

        return null;
}
}
