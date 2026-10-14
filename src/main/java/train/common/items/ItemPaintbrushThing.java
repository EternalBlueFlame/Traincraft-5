package train.common.items;

import net.minecraft.world.World;

import net.minecraft.client.util.ITooltipFlag;

import javax.annotation.Nullable;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import train.common.Traincraft;
import train.common.library.Info;

import java.util.ArrayList;
import java.util.List;

public class ItemPaintbrushThing extends Item implements train.common.items.ILegacyIcon {
    private ArrayList<String[]> info;

    public ItemPaintbrushThing() {
        super();
        maxStackSize = 64;
        setCreativeTab(Traincraft.tcTab);
    }


    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack par1ItemStack, @Nullable World world, List<String> par3List, ITooltipFlag flag) {
    	boolean par4 = flag.isAdvanced(); EntityPlayer par2EntityPlayer = Traincraft.proxy.getPlayer();
        par3List.add("\u00a77" + TextFormatting.GREEN + "Shift-right-click" + TextFormatting.GRAY + " on a train to open the texture menu.");
    }

    @Override
    public String legacyIcon(int meta) {
        return "tc:paintbrushThing";
    }
}
