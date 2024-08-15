package train.common.enums;

/**Enums that cause special rendering to occur
 *
 */
public enum BoxName
{
    cull("cull"),
    lamp("lamp"),
    ditch("ditch"),
    prime1("prime1"),
    prime2("prime2"),
    prime3("prime3"),
    prime4("prime4"),
    commander("commander");


    public final String BoxName;

    /**Constructor for BoxName
     *
     * @param boxName
     */
    BoxName(String boxName)
    {
        BoxName = boxName;
    }

    public String AsString()
    {
        return this.BoxName;
    }
}