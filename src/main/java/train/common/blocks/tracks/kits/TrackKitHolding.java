package train.common.blocks.tracks.kits;

import net.minecraft.entity.item.EntityMinecart;
import train.common.api.Locomotive;

/**
 * Unpowered: holds any Traincraft locomotive.
 * Powered: releases it and launches it at its previous speed. Won't boost passing locomotives.
 */
public class TrackKitHolding extends TrackKitLockingBase {

    @Override
    public TCTrackKits getKitType() {
        return TCTrackKits.HOLDING;
    }

    @Override
    protected boolean acceptsCart(EntityMinecart cart) {
        return cart instanceof Locomotive
                || (cart.getClass().getSuperclass() != null && cart.getClass().getSuperclass().getName().endsWith("EntityLocomotive"));
    }

    @Override
    protected boolean isReleasing() {
        return isPowered() || this.delay > 0;
    }

    @Override
    protected void checkCart(EntityMinecart cart) {
        if (this.delay > 0 && (cart != getCurrentCart() || this.reset == 0)) {
            this.delay = 0;
            setCurrentCart(cart);
            setLaunchDirection(cart);
        }
        this.reset = 10;
    }

    @Override
    public void update() {
        dropDeadCart();
        if (isServer()) {
            if (isPowered()) {
                this.delay = getDelayTime();
            } else if (this.delay > 0) {
                this.delay--;
                if (this.delay == 0) {
                    setCurrentCart(null);
                }
            }
            if (this.reset > 0) {
                this.reset--;
            }
            if ((this.prevDelay == 0) != (this.delay == 0)) {
                sendUpdateToClient();
            }
        }
        this.prevDelay = this.delay;
    }

    @Override
    public int getRenderState() {
        return isReleasing() ? 1 : 0;
    }
}
