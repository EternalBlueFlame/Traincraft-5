package src.train.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.IBoxable;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import src.train.common.Traincraft;
import src.train.common.library.Info;

import java.util.List;

public class ItemChunkLoaderActivator extends Item implements IBoxable {

	public ItemChunkLoaderActivator() {
		this.setMaxStackSize(1);
		this.setCreativeTab(Traincraft.tcTab);
		this.setMaxDamage(10);
	}

	@Override
	public boolean canBeStoredInToolbox(ItemStack itemstack) {
		return true;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		par3List.add("\u00a77" + "Right click on a Locomotive");
		par3List.add("\u00a77" + " to start/stop chunk loading.");
		par3List.add("\u00a77" + "Locomotives will load chunks");
		par3List.add("\u00a77" + "around attached carts.");
	}
}
