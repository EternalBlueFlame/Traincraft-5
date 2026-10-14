package train.common.items;

import net.minecraft.item.Item;

/** Icon-only item used for a creative tab's icon. */
public class ItemTabIcon extends Item implements ILegacyIcon {
    private final String icon;

    public ItemTabIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String legacyIcon(int meta) {
        return icon;
    }
}
