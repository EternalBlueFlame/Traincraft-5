package train.common.api;

import com.google.gson.JsonObject;

public interface IRollingStockLightControls
{
    String lightingDetailsJSONString();

    JsonObject lightingDetailsAsJSON();

    /**
     *
     * @param isLightsOn set 0 if loco lights is false, 1 if true
     */
    void setPacketLights(boolean isLightsOn);

    /**
     *
     * @param isBeaconOn set 0 if loco beacon is false, 1 if true
     */
    void setPacketBeacon(boolean isBeaconOn);

    void setPacketDitchLightsMode(byte ditchLightMode);

    boolean isBeaconEnabled();
    boolean isLightsEnabled();
    byte getBeaconCycleIndex();
    boolean isDitchLightsEnabled();
}
