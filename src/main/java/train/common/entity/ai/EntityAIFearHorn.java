package train.common.entity.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.math.Vec3d;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * Animals run away from a locomotive that sounds its horn.
 * <p>
 * 1.7.10 stored the locomotive as the animal's "entity to attack"; in 1.12 attack targets must be living
 * entities, so the locomotive that scared an animal is kept in a weak map instead ({@link #scare}).
 */
public class EntityAIFearHorn extends EntityAIBase {
    private static final Map<EntityAnimal, Entity> SCARED_BY = new WeakHashMap<>();

    private final EntityAnimal entity;
    private double randPosX;
    private double randPosY;
    private double randPosZ;

    public EntityAIFearHorn(EntityAnimal e) {
        entity = e;
        this.setMutexBits(1);
    }

    /** Makes the animal flee from {@code source} the next time its AI runs. */
    public static void scare(EntityAnimal animal, Entity source) {
        SCARED_BY.put(animal, source);
        animal.getNavigator().clearPath();
    }

    @Override
    public boolean shouldExecute() {
        Entity loco = SCARED_BY.remove(entity);
        if (loco == null) {
            return false;
        }
        entity.detachHome();
        Vec3d vec3 = RandomPositionGenerator.findRandomTargetBlockAwayFrom(entity, 10, 8, new Vec3d(loco.posX, loco.posY, loco.posZ));
        if (vec3 == null) {
            return false;
        }
        this.randPosX = vec3.x;
        this.randPosY = vec3.y;
        this.randPosZ = vec3.z;
        return true;
    }

    @Override
    public void startExecuting() {
        entity.getNavigator().tryMoveToXYZ(this.randPosX, this.randPosY, this.randPosZ, 2.0D);
    }

    @Override
    public boolean shouldContinueExecuting() {
        return !this.entity.getNavigator().noPath();
    }
}
