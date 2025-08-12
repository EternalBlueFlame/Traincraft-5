package train.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import train.common.Traincraft;
import train.common.api.EntityRollingStock;
import train.common.core.network.PacketSetTrainLockedToClient;

/**
 * @author 02skaplan
 * <p>Lock and Trusted Players Menu</p>
 * <p>Allows players to lock and unlock a piece of rolling stock and add & remove trusted individuals from using the rolling stock.</p>
 */
@SideOnly(Side.CLIENT)
public class GuiLockMenu extends GuiLockMenuAbstract {
    private final EntityRollingStock rollingStock;

    /**
     * @author 02skaplan
     */
    public GuiLockMenu(EntityPlayer editingPlayer, EntityRollingStock rollingStock) {
        super(editingPlayer);
        this.rollingStock = rollingStock;
        currentTrustees.addAll(rollingStock.getTrustedList());
    }

    @Override
    public boolean getLocked() {
        return rollingStock.getTrainLockedFromPacket();
    }

    @Override
    public void setLocked(boolean locked) {
        rollingStock.setTrainLockedFromPacket(locked);
    }

    @Override
    public void sendUpdatePacket(boolean propagate) {
        Traincraft.lockChannel.sendToServer(new PacketSetTrainLockedToClient(rollingStock.locked, exportTrustedPlayers(), rollingStock.getEntityId(), propagate));
    }
}
