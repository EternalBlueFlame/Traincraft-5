package train.common.core;

import com.mojang.authlib.GameProfile;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;

import javax.annotation.Nullable;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * Traincraft's fake player (used by the snow plow and the rotative digger to break blocks).
 * Replaces the hand-written 1.7.10 FakePlayer class with Forge's {@link FakePlayerFactory}.
 */
public final class TraincraftFakePlayer {
    public static final GameProfile PROFILE =
            new GameProfile(UUID.nameUUIDFromBytes("[Traincraft]".getBytes(StandardCharsets.UTF_8)), "[Traincraft]");

    private TraincraftFakePlayer() {
    }

    /** @return the fake player for this world, or {@code null} on the client */
    @Nullable
    public static FakePlayer get(@Nullable World world) {
        return world instanceof WorldServer ? FakePlayerFactory.get((WorldServer) world, PROFILE) : null;
    }
}
