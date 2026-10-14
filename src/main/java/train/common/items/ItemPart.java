package train.common.items;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.item.Item;
import train.common.Traincraft;
import train.common.library.Info;

/**
 * @author canitzp
 */
public class ItemPart extends Item implements train.common.items.ILegacyIcon {

    protected String iconName = "";
    protected String folder = "parts";

    public ItemPart(String iconName){
        this.iconName = iconName;
        this.setMaxStackSize(64);
        this.setCreativeTab(Traincraft.tcTab);
    }

    public ItemPart overridePath(String newFolder){
        this.folder = newFolder;
        return this;
    }



    @Override
    public String legacyIcon(int meta) {
        return "tc:" + this.folder + "/" + this.iconName;
    }
}
