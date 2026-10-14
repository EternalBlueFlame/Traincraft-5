package train.common.core;

import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.MovingSoundMinecart;
import net.minecraft.client.audio.MovingSoundMinecartRiding;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import train.common.api.AbstractTrains;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Client sound hooks (registered from ClientProxy only).
 * <p>
 * 1.12's WorldClient starts the vanilla minecart rolling sound for every EntityMinecart that joins the world,
 * and EntityPlayerSP starts the "riding a minecart" sound when mounting one. Traincraft rolling stock extends
 * EntityMinecart, so every train sounded like a minecart. On 1.7.10 these sounds were only started for vanilla
 * minecart spawn packets, so Traincraft stock never had them; they are dropped here again.
 */
@SideOnly(Side.CLIENT)
public class Traincraft_EventSounds {

    private static final Map<Class<?>, Field> MINECART_FIELDS = new HashMap<>();

    @SubscribeEvent
    public void onPlaySound(PlaySoundEvent event) {
        ISound sound = event.getResultSound();
        if (sound instanceof MovingSoundMinecart || sound instanceof MovingSoundMinecartRiding) {
            EntityMinecart cart = getMinecart(sound);
            if (cart instanceof AbstractTrains) {
                train.common.Traincraft.tcLog.debug("Dropped vanilla minecart sound for " + cart.getName());
                event.setResultSound(null);
            }
        }
    }

    private static EntityMinecart getMinecart(ISound sound) {
        Field field = MINECART_FIELDS.computeIfAbsent(sound.getClass(), cls -> {
            for (Class<?> c = cls; c != null && c != Object.class; c = c.getSuperclass()) {
                for (Field f : c.getDeclaredFields()) {
                    if (EntityMinecart.class.isAssignableFrom(f.getType())) {
                        f.setAccessible(true);
                        return f;
                    }
                }
            }
            return null;
        });
        if (field == null) return null;
        try {
            return (EntityMinecart) field.get(sound);
        } catch (IllegalAccessException e) {
            return null;
        }
    }
}
