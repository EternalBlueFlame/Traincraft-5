package train.common.items;

import net.minecraft.world.World;

import net.minecraft.client.util.ITooltipFlag;

import javax.annotation.Nullable;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import train.common.Traincraft;
import train.common.library.Info;
import train.common.library.ItemIDs;

import java.util.List;

public class ItemStacked extends Item implements train.common.items.ILegacyIcon {

	public ItemStacked(int maxUse) {
		super();
		maxStackSize = 1;
		this.setMaxDamage(maxUse);
		setCreativeTab(Traincraft.tcTab);
	}

	@Override
	public boolean isFull3D() {
		return true;
	}

	@Override
	public boolean shouldRotateAroundWhenRendering() {
		return true;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack par1ItemStack, @Nullable World world, List<String> par3List, ITooltipFlag flag) {
		boolean par4 = flag.isAdvanced(); EntityPlayer par2EntityPlayer = Traincraft.proxy.getPlayer();
		par3List.add("\u00a77" + "Right click on a rolling stock");
		par3List.add("\u00a77" + " to enter attaching mode.");
		par3List.add("\u00a77" + "Click a few time to reset links.");
		par3List.add("\u00a77" + "Sneak+Right click on a locomotive");
		par3List.add("\u00a77" + " to set mode: 'Can pull/Can be pulled'");
	}
	

    @Override
    public String legacyIcon(int meta) {
        return "tc:parts/" + train.common.library.ItemIDs.stake.iconName;
    }
}