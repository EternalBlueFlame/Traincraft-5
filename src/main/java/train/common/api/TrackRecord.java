package train.common.api;

import train.common.items.TCRailTypes;
import train.common.library.ItemIDs;

public interface TrackRecord  {

    String getLabel();

    TCRailTypes.RailTypes getRailType();

    String getVariant();
    IItemIDs getItem();

    int getSwitchSize();

    String getItemToolTip();


}
