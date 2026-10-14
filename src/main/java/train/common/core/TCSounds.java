package train.common.core;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import train.common.Traincraft;
import train.common.library.Info;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 1.12: sounds have to exist as registered {@link SoundEvent}s before the server can send them to clients
 * (1.7.10 just sent the file name). Every event listed in assets/tc/sounds.json is registered here, and the
 * 1.7.10-style sound names used all over the mod ("tc:steam_horn", "bell", "random.click") are resolved
 * to the matching SoundEvent.
 */
public final class TCSounds {

    private static final Map<String, SoundEvent> SOUNDS = new HashMap<>();
    private static final Map<String, String> LEGACY_VANILLA = new HashMap<>();
    private static final Set<String> REPORTED_MISSING = new HashSet<>();

    static {
        LEGACY_VANILLA.put("random.click", "ui.button.click");
        LEGACY_VANILLA.put("liquid.water", "block.water.ambient");
        LEGACY_VANILLA.put("minecart.inside", "entity.minecart.inside");
        LEGACY_VANILLA.put("minecart.base", "entity.minecart.riding");
        LEGACY_VANILLA.put("random.fuse", "entity.tnt.primed");
        LEGACY_VANILLA.put("random.explode", "entity.generic.explode");
        LEGACY_VANILLA.put("random.pop", "entity.item.pickup");
        LEGACY_VANILLA.put("random.orb", "entity.experience_orb.pickup");
        LEGACY_VANILLA.put("random.break", "entity.item.break");
        LEGACY_VANILLA.put("random.bow", "entity.arrow.shoot");
        LEGACY_VANILLA.put("random.door_open", "block.wooden_door.open");
        LEGACY_VANILLA.put("random.door_close", "block.wooden_door.close");
        LEGACY_VANILLA.put("fire.ignite", "item.flintandsteel.use");
    }

    private TCSounds() {}

    /** Registers one SoundEvent per entry of assets/tc/sounds.json. Call once, in preInit. */
    public static void registerAll() {
        if (!SOUNDS.isEmpty()) return;
        try (InputStream in = TCSounds.class.getResourceAsStream("/assets/" + Info.modID + "/sounds.json")) {
            if (in == null) {
                Traincraft.tcLog.error("sounds.json not found, train sounds are disabled");
                return;
            }
            JsonObject json = new JsonParser().parse(new InputStreamReader(in, StandardCharsets.UTF_8)).getAsJsonObject();
            for (Entry<String, JsonElement> entry : json.entrySet()) {
                ResourceLocation id = new ResourceLocation(Info.modID, entry.getKey());
                if (SOUNDS.containsKey(id.toString())) continue;
                SoundEvent event = new SoundEvent(id).setRegistryName(id);
                ForgeRegistries.SOUND_EVENTS.register(event);
                SOUNDS.put(id.toString(), event);
            }
            Traincraft.tcLog.info("Registered " + SOUNDS.size() + " sounds");
        } catch (Exception e) {
            Traincraft.tcLog.error("Failed to register Traincraft sounds", e);
        }
    }

    /**
     * Resolves a 1.7.10 sound name: "tc:steam_horn", "steam_horn", "tc:Silence" (case is ignored like 1.12 does),
     * or an old vanilla name such as "random.click". Returns null when there is no such sound.
     */
    public static SoundEvent get(String name) {
        if (name == null) return null;
        String n = name.trim();
        if (n.isEmpty()) return null;

        String vanilla = LEGACY_VANILLA.get(n.startsWith("minecraft:") ? n.substring(10) : n);
        if (vanilla != null) {
            return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("minecraft", vanilla));
        }

        int colon = n.lastIndexOf(':');
        String domain = colon < 0 ? null : n.substring(0, n.indexOf(':'));
        String path = (colon < 0 ? n : n.substring(colon + 1)).toLowerCase();

        if (domain == null || domain.equalsIgnoreCase(Info.modID)) {
            SoundEvent own = SOUNDS.get(Info.modID + ":" + path);
            if (own != null) return own;
        }
        SoundEvent other = ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(domain == null ? "minecraft" : domain.toLowerCase(), path));
        if (other == null && REPORTED_MISSING.add(n)) {
            Traincraft.tcLog.warn("Unknown sound '" + name + "', it will not be played");
        }
        return other;
    }
}
