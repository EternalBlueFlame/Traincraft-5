package train.common.api;

/**
 * Optional sound configuration for an entity class.
 */
public interface TrainSoundRecord {

    Class getEntityClass();

    String getHornString();

    String getBellString();

    String getRunString();

    String getIdleString();

    Float getHornVolume();

    Float getBellVolume();

    Float getRunVolume();

    Float getIdleVolume();

    int getRunSoundLength();

    int getIdleSoundLength();

    boolean getSoundChangeWithSpeed();
}
