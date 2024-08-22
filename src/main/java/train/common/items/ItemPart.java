package train.common.items;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import train.common.Traincraft;
import train.common.library.Info;

/**
 * @author canitzp
 */
public class ItemPart extends Item{

    protected String iconName = "";
    protected String folder = "parts";

    protected String modID = "";

    public ItemPart(String iconName, String modID){
        this.iconName = iconName;
        this.setMaxStackSize(64);
        this.setCreativeTab(Traincraft.tcTab);
        this.modID = modID;
    }

    public ItemPart overridePath(String newFolder){
        this.folder = newFolder;
        return this;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon(this.modID.toLowerCase() + ":" + this.folder + "/" + this.iconName);
    }

}
