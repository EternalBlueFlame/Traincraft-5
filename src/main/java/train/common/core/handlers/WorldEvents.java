package train.common.core.handlers;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.world.ChunkEvent;
import train.common.api.AbstractTrains;
import train.common.api.Locomotive;
import train.common.entity.ai.EntityAIFearHorn;
import train.common.entity.rollingStockOld.special.EntityJukeBoxCart;

import java.util.Random;

public class WorldEvents {
    private int windTicker = 0;
    private static final Random rand = new Random();
    public static int windStrength = 10 + rand.nextInt(10);

    @SubscribeEvent
    public void onWorldTick(TickEvent.WorldTickEvent handler) {
        if (handler.world.isRemote) {
            if (windTicker % 128 == 0) {
                updateWind();
                windTicker = 0;
            }
            windTicker++;
        }
    }

    private static void updateWind() {
        int upChance = 10;
        int downChance = 10;
        if (windStrength > 20) {
            upChance -= windStrength - 20;
        } else if (windStrength < 10) {
            downChance -= 10 - windStrength;
        }

        if (rand.nextInt(100) <= upChance) {
            windStrength += 1;
        }

        if (rand.nextInt(100) <= downChance) {
            windStrength -= 1;
        }
    }

    @SubscribeEvent
    public void entitySpawn(EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof EntityAnimal) {
            ((EntityAnimal) event.getEntity()).tasks.addTask(0, new EntityAIFearHorn(((EntityAnimal) event.getEntity())));
        }
    }

    @SubscribeEvent
    public void playerQuitEvent(PlayerEvent.PlayerLoggedOutEvent event) {
        if (event.player.getRidingEntity() instanceof AbstractTrains) {
            if (event.player.getRidingEntity() instanceof Locomotive) {
                ((Locomotive) event.player.getRidingEntity()).isBraking = true;
                ((Locomotive) event.player.getRidingEntity()).parkingBrake = true;
            }

            event.player.dismountRidingEntity();
        }
    }

    @SubscribeEvent
    public void chunkUnloadEvent(ChunkEvent.Unload event) {
        for (net.minecraft.util.ClassInheritanceMultiMap<net.minecraft.entity.Entity> section : event.getChunk().getEntityLists()) {
            for (EntityJukeBoxCart o : section.getByClass(EntityJukeBoxCart.class)) {
                if (o.isPlaying) {
                    o.player.stop();
                    o.player.setVolume(0);
                }
            }
        }
    }

    @SubscribeEvent
    public void EntityStruckByLightningEvent(EntityStruckByLightningEvent event) {
        if (event.getEntity() instanceof AbstractTrains) {
            event.setCanceled(true);
        }
    }

}
