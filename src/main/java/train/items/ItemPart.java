package train.items;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import train.Traincraft;
import train.library.Info;

/**
 * @author canitzp
 */
public class ItemPart extends Item{

    protected String iconName = "";
    protected String folder = "parts";

    public ItemPart(String iconName){
        this.iconName = iconName;
        this.setMaxStackSize(64);
        this.setCreativeTab(Traincraft.tcTab);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon(Info.modID.toLowerCase() + ":" + this.folder + "/" + this.iconName);
    }

}
