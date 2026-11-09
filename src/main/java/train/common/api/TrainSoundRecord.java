package train.common.api;

/**
 * Optional sound configuration for an entity class.
 */
public interface TrainSoundRecord {

    Class getEntityClass();

    String getHornString();

    String getRunString();

    String getIdleString();

    Float getHornVolume();

    Float getRunVolume();

    Float getIdleVolume();

    int getRunSoundLength();

    int getIdleSoundLength();

    boolean getSoundChangeWithSpeed();
}
