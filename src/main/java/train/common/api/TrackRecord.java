package train.common.api;

import net.minecraft.item.Item;
import train.common.items.TCRailTypes;
import train.common.library.ItemIDs;

public interface TrackRecord  {

    String getLabel();

    TCRailTypes.RailTypes getRailType();

    ItemIDs getItem();

    int getSwitchSize();

    String getItemToolTip();


}
