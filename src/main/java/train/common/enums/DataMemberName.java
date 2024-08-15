package train.common.enums;

/**
 * Enum that contains all of the data member names for data watchers and NBTTagCompound
 */
public enum DataMemberName
{
    isLightsEnabled("isLightsEnabled"),
    isBeaconEnabled("isBeaconEnabled"),
    beaconCycleIndex("beaconCycleIndex"),
    ditchLightMode("ditchLightMode"),
    lightingDetailsJSONString("lightingDetailsJSONString");

    public final String MemberName;

    /**Constructor for DataMemberName
     *
     * @param memberName
     */
    DataMemberName(String memberName)
    {
        MemberName = memberName;
    }

    public String AsString()
    {
        return this.MemberName;
    }
}
