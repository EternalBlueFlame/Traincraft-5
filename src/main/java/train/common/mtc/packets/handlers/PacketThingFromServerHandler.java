package train.common.mtc.packets.handlers;


import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import train.common.mtc.packets.PacketThingFromServer;

public class PacketThingFromServerHandler implements IMessageHandler<PacketThingFromServer, IMessage> {
    @Override
    public IMessage onMessage(final PacketThingFromServer message, final MessageContext ctx) {
        // 1.12 handles packets on the netty thread; touching the world/GUIs there races with the game thread
        net.minecraftforge.fml.common.FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handleOnMainThread(message, ctx));
        return null;
    }

    private IMessage handleOnMainThread(PacketThingFromServer message, MessageContext ctx) {

        return null;
}
}
