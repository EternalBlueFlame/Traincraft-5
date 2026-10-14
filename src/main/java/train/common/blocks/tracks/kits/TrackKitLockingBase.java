package train.common.blocks.tracks.kits;

import mods.railcraft.api.carts.CartToolsAPI;
import mods.railcraft.api.events.CartLockdownEvent;
import mods.railcraft.api.tracks.ITrackKitInstance;
import mods.railcraft.api.tracks.ITrackKitLockdown;
import mods.railcraft.api.tracks.ITrackKitPowered;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;

import javax.annotation.Nullable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Holds a cart and launches it again in the direction it came from, at its previous speed.
 * Base of the locomotive holding track and the station track.
 */
public abstract class TrackKitLockingBase extends TrackKitTraincraft implements ITrackKitLockdown, ITrackKitPowered {
    protected static final float DIR_THRESHOLD = 0.01F;

    protected double prevSpeedX = 0;
    protected double prevSpeedZ = 0;
    protected boolean powered = false;
    protected boolean launchForward = true;
    protected byte reset = 0;
    protected int prevDelay = 0;
    protected int delay = 0;
    @Nullable
    private EntityMinecart lockedCart;

    /** whether this kit acts on the given cart at all */
    protected abstract boolean acceptsCart(EntityMinecart cart);

    /** whether the track is currently letting carts through */
    protected abstract boolean isReleasing();

    protected int getDelayTime() {
        return 3;
    }

    @Nullable
    public EntityMinecart getCurrentCart() {
        return this.lockedCart;
    }

    protected void setCurrentCart(@Nullable EntityMinecart cart) {
        if (this.prevSpeedX == 0 && cart != null) {
            this.prevSpeedX = cart.motionX;
        }
        if (this.prevSpeedZ == 0 && cart != null) {
            this.prevSpeedZ = cart.motionZ;
        }
        this.lockedCart = cart;
    }

    @Override
    public void onBlockRemoved() {
        super.onBlockRemoved();
        setCurrentCart(null);
    }

    protected void lockCart(EntityMinecart cart) {
        MinecraftForge.EVENT_BUS.post(new CartLockdownEvent.Lock(cart, getPos()));
    }

    protected void releaseCart(EntityMinecart cart) {
        MinecraftForge.EVENT_BUS.post(new CartLockdownEvent.Release(cart, getPos()));
    }

    protected void checkCart(EntityMinecart cart) {
        if (this.delay > 0 && (cart != getCurrentCart() || this.reset == 0)) {
            setCurrentCart(cart);
            setLaunchDirection(cart);
        }
        this.reset = 10;
    }

    protected void setLaunchDirection(EntityMinecart cart) {
        double speed = CartToolsAPI.getCartSpeedUncapped(cart);
        if (speed > DIR_THRESHOLD) {
            boolean launch = isNorthSouth() ? cart.motionZ > 0.0D : cart.motionX > 0.0D;
            if (this.launchForward != launch) {
                this.launchForward = launch;
                sendUpdateToClient();
            }
        }
    }

    @Override
    public void onMinecartPass(EntityMinecart cart) {
        if (!acceptsCart(cart)) {
            return;
        }
        checkCart(cart);
        if (isReleasing()) {
            releaseCart(cart);
            double boostX = Math.abs(this.prevSpeedX);
            double boostZ = Math.abs(this.prevSpeedZ);
            this.prevSpeedX = 0;
            this.prevSpeedZ = 0;
            if (isNorthSouth()) {
                if (boostZ != 0) {
                    cart.motionZ = this.launchForward ? boostZ : -boostZ;
                }
            } else if (boostX != 0) {
                cart.motionX = this.launchForward ? boostX : -boostX;
            }
        } else {
            if (getCurrentCart() == null) {
                setCurrentCart(cart);
                setLaunchDirection(cart);
            }
            if (getCurrentCart() == cart) {
                lockCart(cart);
                cart.motionX = 0.0D;
                cart.motionZ = 0.0D;
                if (isNorthSouth()) {
                    cart.setPosition(cart.posX, cart.posY, getPos().getZ() + 0.5D);
                } else {
                    cart.setPosition(getPos().getX() + 0.5D, cart.posY, cart.posZ);
                }
            }
        }
    }

    /** Forgets carts that died or left (the 1.7.10 code had this check inverted). */
    protected void dropDeadCart() {
        if (this.lockedCart != null && !this.lockedCart.isEntityAlive()) {
            setCurrentCart(null);
        }
    }

    @Override
    public void releaseCart() {
        this.delay = 10;
    }

    @Override
    public boolean isCartLockedDown(EntityMinecart cart) {
        return !this.powered && this.lockedCart == cart && this.delay == 0;
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
        return 0;
    }

    @Override
    public boolean canPropagatePowerTo(ITrackKitInstance track) {
        return false;
    }

    @Override
    public void writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setBoolean("powered", this.powered);
        data.setDouble("prevSpeedX", this.prevSpeedX);
        data.setDouble("prevSpeedZ", this.prevSpeedZ);
        data.setBoolean("launchForward", this.launchForward);
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        this.powered = data.getBoolean("powered");
        this.prevSpeedX = data.getDouble("prevSpeedX");
        this.prevSpeedZ = data.getDouble("prevSpeedZ");
        this.launchForward = !data.hasKey("launchForward") || data.getBoolean("launchForward");
    }

    @Override
    public void writePacketData(DataOutputStream data) throws IOException {
        super.writePacketData(data);
        data.writeBoolean(this.powered);
        data.writeInt(this.delay);
        data.writeBoolean(this.launchForward);
    }

    @Override
    public void readPacketData(DataInputStream data) throws IOException {
        super.readPacketData(data);
        this.powered = data.readBoolean();
        this.delay = data.readInt();
        this.launchForward = data.readBoolean();
        markBlockNeedsUpdate();
    }
}
