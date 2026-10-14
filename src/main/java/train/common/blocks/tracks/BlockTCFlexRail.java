package train.common.blocks.tracks;

import net.minecraft.block.BlockRail;
import net.minecraft.block.SoundType;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Plain (flexible) rail with its own max speed: copper, steel and the snowy variants.
 * <p>
 * In 1.7.10 these were Railcraft track instances. In 1.12 they are normal Traincraft blocks
 * that work without Railcraft; when Railcraft is installed they are also registered as
 * Railcraft track types (see {@link train.common.blocks.tracks.kits.TCTrackTypes}),
 * so track kits can be put on them.
 */
public class BlockTCFlexRail extends BlockRail {
    private final float maxSpeed;

    public BlockTCFlexRail(float maxSpeed) {
        this.maxSpeed = maxSpeed;
        setHardness(0.7F);
        setSoundType(SoundType.METAL);
    }

    public float getMaxSpeed() {
        return this.maxSpeed;
    }

    @Override
    public float getRailMaxSpeed(World world, EntityMinecart cart, BlockPos pos) {
        return this.maxSpeed;
    }

    /** Max speeds, same values as the 1.7.10 SpeedController classes. */
    public static final float SPEED_COPPER = 0.25F;
    public static final float SPEED_DEFAULT = 0.4F;
    public static final float SPEED_STEEL = 0.55F;
}
