package train.common.mtc.packets.handlers;


import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.Entity;
import train.common.api.Locomotive;
import train.common.mtc.packets.PacketMTCLevelUpdate;

public class PacketMTCLevelUpdateHandler implements IMessageHandler<PacketMTCLevelUpdate, IMessage> {
    @Override
    public IMessage onMessage(final PacketMTCLevelUpdate message, final MessageContext ctx) {
        // 1.12 handles packets on the netty thread; touching the world/GUIs there races with the game thread
        net.minecraftforge.fml.common.FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handleOnMainThread(message, ctx));
        return null;
    }

    private IMessage handleOnMainThread(PacketMTCLevelUpdate message, MessageContext ctx) {

        Entity trainEntity = ctx.getServerHandler().player.world.getEntityByID(message.entity);

        if (trainEntity != null ) {
            ((Locomotive) trainEntity).trainLevel = Integer.toString(message.level);
        }
        return null;
    }
}
