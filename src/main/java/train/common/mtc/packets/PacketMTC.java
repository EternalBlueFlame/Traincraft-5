package train.common.mtc.packets;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class PacketMTC implements IMessage {
    public int entity;
    public int mtcStatus;
    public int mode;

    public PacketMTC() {}
    public PacketMTC(int entity, int mtcStatus, int mode) {

        this.entity = entity;
        this.mtcStatus = mtcStatus;
        this.mode = mode;
    }
    @Override
    public void fromBytes(ByteBuf buf) {
        entity = buf.readInt();
        mtcStatus = buf.readInt();
        mode = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(entity);
        buf.writeInt(mtcStatus);
        buf.writeInt(mode);
    }

}
