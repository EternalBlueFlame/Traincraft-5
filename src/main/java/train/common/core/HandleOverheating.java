package train.common.core;

import net.minecraft.util.math.MathHelper;
import train.common.api.EntityRollingStock;
import train.common.api.Locomotive;
import train.common.api.SteamTrain;

public class HandleOverheating {

    /**
     * a delayer to avoid instant overheating
     */
    private int breakDelay = 0;

    /**
     * constructor
     *
     * @param entity the entity to handle
     */
    public HandleOverheating(EntityRollingStock entity) {

    }

    /**
     * Handle the overheating for locomotives
     */
    public void HandleHeatLevel(EntityRollingStock entity) {

        entity.overheatLevel = entity.getOverheatLevel();

        if (entity instanceof Locomotive && entity.canOverheat()) {
            double locoActualSpeed = MathHelper.sqrt(entity.motionX * entity.motionX + entity.motionZ * entity.motionZ);

            /**
             * speed is low, overheat goes down to normal
             */
            if(!((Locomotive) entity).getState().equals("broken")) {
                if ((locoActualSpeed <= 0 + 0.10) && !entity.isBraking && entity.overheatLevel > entity.getAverageOverheat() && (entity.world.rand.nextInt(10) == 0)) {
                    entity.overheatLevel--;
                    if (locoActualSpeed <= 0 + 0.05) {
                        entity.overheatLevel--;
                    }
                }
                /**
                 * fuel is empty, heat level goes down
                 */
                if (entity.fuelTrain < 1 && entity.overheatLevel > 0 && (entity.world.rand.nextInt(10) == 0)) {
                    entity.overheatLevel--;

                }
                /**
                 * Heat goes down with time
                 */
                if ((entity.overheatLevel > (entity.getOverheatTime() + 30) / 2) && (entity.world.rand.nextInt(30) == 0)) {
                    entity.overheatLevel--;
                }
            }
            /**
             * train is fueled => heat level goes up to normal
             */
            if (((Locomotive) entity).isLocoTurnedOn() && entity.fuelTrain > 1 && (entity.overheatLevel < (entity.getOverheatTime() + 30) / 2) && (entity.world.rand.nextInt(7) == 0)) {
                entity.overheatLevel++;
            }

            /**
             * train is breaking, increment a delayer break won't overheat too quickly
             */
            if (entity.isBraking) {
                breakDelay++;
            } else {
                breakDelay=0;
            }

            /**
             * Delayer has reached max and speed is not 0: overheat
             */
            if (entity.isBraking && breakDelay > 40 && (Math.abs(entity.motionX) + Math.abs(entity.motionZ)) > 0.05) {
                if (entity.world.rand.nextInt(10) == 0) {
                    entity.overheatLevel += 2;
                }
            }

            if (entity instanceof SteamTrain) {
                int waterLevel = ((SteamTrain) entity).getWater();
                /**
                 * water is empty => overheats
                 */
                if ((waterLevel < 1) && entity.fuelTrain > 10) {
                    if (entity.world.rand.nextInt(10) == 0) {
                        entity.overheatLevel += 3;
                    }
                }
                int maxWaterLevel = ((SteamTrain) entity).getCartTankCapacity();
                if ((waterLevel > maxWaterLevel - (maxWaterLevel / 2)) && entity.overheatLevel > entity.getAverageOverheat() && !((Locomotive) entity).getState().equals("broken")) {
                    entity.overheatLevel--;
                }
            }

            if (entity.overheatLevel < entity.getAverageOverheat() - (overheatRatio(17, entity))) {
                ((Locomotive) entity).setState("cold");
            }

            if (entity.overheatLevel < entity.getAverageOverheat() - (overheatRatio(4, entity)) && entity.overheatLevel > entity.getAverageOverheat() - (overheatRatio(17, entity))) {
                ((Locomotive) entity).setState("warm");
            }

            if (entity.overheatLevel > entity.getAverageOverheat() - (overheatRatio(4, entity)) && entity.overheatLevel < entity.getAverageOverheat() + (overheatRatio(12, entity))) {
                ((Locomotive) entity).setState("hot");
            }

            if (entity.overheatLevel > entity.getAverageOverheat() + (overheatRatio(12, entity))) {
                ((Locomotive) entity).setState("very hot");
            }

            if (entity.overheatLevel > entity.getAverageOverheat() + (overheatRatio(17, entity))) {
                ((Locomotive) entity).setState("too hot");
            }

            if (entity.overheatLevel > entity.getAverageOverheat() + (overheatRatio(24, entity))) {
                ((Locomotive) entity).setState("broken");
            }
            entity.setOverheatLevel(entity.overheatLevel);
        }

    }

    /**
     * locomotives have different heating time this convert to the size of the heat bar in the HUD
     *
     * @param pixels the size of the heat bar in the HUD
     * @return the ratio of the heat bar
     */
    private double overheatRatio(double pixels, EntityRollingStock entity) {
        /**
         * size in pixels of the heat bar
         */
        double barSize = 50;
        return (entity.getOverheatTime() * (pixels / barSize));
    }
}