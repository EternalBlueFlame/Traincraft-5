package train.common.blocks.tracks.kits;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.passive.EntityAnimal;
import train.common.entity.rollingStockOld.special.EntityStockCar;
import train.common.entity.rollingStockOld.special.EntityStockCarDRWG;

import java.util.List;

/**
 * Puts a nearby animal (or any mob) into an empty stock car, or empties it (disembark).
 * Replaces BlockAnimalBoardingTrack, BlockMobBoardingTrack and BlockDisembarkTrack.
 *
 * @author Spitfire4466
 */
public abstract class TrackKitBoarding extends TrackKitDetectorBase {

    protected static boolean isStockCar(EntityMinecart cart) {
        return cart instanceof EntityStockCar || cart instanceof EntityStockCarDRWG;
    }

    /** Boards the first entity accepted by {@link #canBoard(Entity)} within 4 blocks. */
    protected void boardNearby(EntityMinecart cart) {
        if (!isStockCar(cart) || cart.isBeingRidden()) {
            return;
        }
        List<Entity> list = getWorld().getEntitiesWithinAABBExcludingEntity(cart, cart.getEntityBoundingBox().grow(4, 4, 4));
        for (Entity entity : list) {
            if (canBoard(entity)) {
                entity.startRiding(cart);
                setTrackPowering();
                return;
            }
        }
    }

    protected boolean canBoard(Entity entity) {
        return false;
    }

    public static class Animal extends TrackKitBoarding {
        @Override
        public TCTrackKits getKitType() {
            return TCTrackKits.BOARDING_ANIMAL;
        }

        @Override
        protected boolean canBoard(Entity entity) {
            return entity instanceof EntityAnimal;
        }

        @Override
        public void onMinecartPass(EntityMinecart cart) {
            boardNearby(cart);
        }
    }

    public static class Mob extends TrackKitBoarding {
        @Override
        public TCTrackKits getKitType() {
            return TCTrackKits.BOARDING_MOB;
        }

        @Override
        protected boolean canBoard(Entity entity) {
            return entity instanceof EntityCreature;
        }

        @Override
        public void onMinecartPass(EntityMinecart cart) {
            boardNearby(cart);
        }
    }

    public static class Disembark extends TrackKitBoarding {
        @Override
        public TCTrackKits getKitType() {
            return TCTrackKits.DISEMBARK;
        }

        @Override
        public void onMinecartPass(EntityMinecart cart) {
            if (isStockCar(cart) && cart.isBeingRidden()) {
                cart.removePassengers();
                setTrackPowering();
            }
        }
    }
}
