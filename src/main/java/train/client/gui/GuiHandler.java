package train.client.gui;



import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (player.getRidingEntity()== null && (ID != 1 && ID != 2)) {
            return null;
        }

        return ID == 1 ? new GuiMTCInfo(player.getPassengers().get(0)) : new GuiSpeedTransmitter(world.getTileEntity(new BlockPos(x, y, z)));
    }
}
