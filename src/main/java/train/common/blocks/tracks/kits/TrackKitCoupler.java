package train.common.blocks.tracks.kits;

import mods.railcraft.api.carts.CartToolsAPI;
import mods.railcraft.api.carts.ILinkageManager;
import mods.railcraft.api.tracks.ITrackKitInstance;
import mods.railcraft.api.tracks.ITrackKitPowered;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.nbt.NBTTagCompound;
import train.common.api.AbstractTrains;
import train.common.api.EntityRollingStock;
import train.common.core.handlers.LinkHandler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Powered: couples passing rolling stock (Railcraft carts too). Unpowered: uncouples.
 *
 * @author Spitfire4466
 */
public class TrackKitCoupler extends TrackKitTraincraft implements ITrackKitPowered {
    private EntityMinecart taggedCart;
    private boolean powered = false;

    @Override
    public TCTrackKits getKitType() {
        return TCTrackKits.COUPLER;
    }

    @Override
    public void onMinecartPass(EntityMinecart cart) {
        if (!(cart instanceof AbstractTrains)) {
            // Railcraft carts
            ILinkageManager lm = CartToolsAPI.linkageManager();
            if (this.taggedCart != null) {
                if (isPowered()) {
                    lm.createLink(this.taggedCart, cart);
                } else {
                    lm.breakLink(this.taggedCart, cart);
                }
            }
        } else if (isPowered()) {
            if (cart instanceof EntityRollingStock) {
                ((EntityRollingStock) cart).isAttaching = true;
                if (this.taggedCart instanceof EntityRollingStock) {
                    ((EntityRollingStock) this.taggedCart).isAttaching = true;
                    new LinkHandler(cart.getEntityWorld()).addStake((EntityRollingStock) this.taggedCart, (EntityRollingStock) cart, false);
                }
            }
        } else {
            ((AbstractTrains) cart).isAttached = false;
            if (this.taggedCart instanceof AbstractTrains) {
                ((AbstractTrains) this.taggedCart).isAttaching = false;
            }
        }
        this.taggedCart = cart;
    }

    @Override
    public boolean isPowered() {
        return this.powered;
    }

    @Override
    public void setPowered(boolean powered) {
        this.powered = powered;
    }

    @Override
    public int getPowerPropagation() {
        return 8;
    }

    @Override
    public boolean canPropagatePowerTo(ITrackKitInstance track) {
        return true;
    }

    @Override
    public int getRenderState() {
        return this.powered ? 1 : 0;
    }

    @Override
    public void writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setBoolean("powered", this.powered);
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        this.powered = data.getBoolean("powered");
    }

    @Override
    public void writePacketData(DataOutputStream data) throws IOException {
        super.writePacketData(data);
        data.writeBoolean(this.powered);
    }

    @Override
    public void readPacketData(DataInputStream data) throws IOException {
        super.readPacketData(data);
        boolean p = data.readBoolean();
        if (p != this.powered) {
            this.powered = p;
            markBlockNeedsUpdate();
        }
    }
}
