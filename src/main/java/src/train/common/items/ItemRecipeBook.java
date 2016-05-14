package src.train.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.IBoxable;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import src.train.common.Traincraft;
import src.train.common.library.GuiIDs;
import src.train.common.library.Info;

import java.util.List;

public class ItemRecipeBook extends Item implements IBoxable {

	public ItemRecipeBook() {
		this.setMaxStackSize(1);
		this.setCreativeTab(Traincraft.tcTab);
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		NBTTagCompound var3 = stack.getTagCompound();
		if (var3 == null) {
			var3 = new NBTTagCompound();
			stack.setTagCompound(var3);
			stack.getTagCompound().setInteger("currPage", 0);
			stack.getTagCompound().setInteger("currRecipe", 0);
		}
		//System.out.println(world.isRemote +" "+stack.getTagCompound().getInteger("currPage"));
		if (!world.isRemote)
			return stack;
		player.openGui(Traincraft.instance, GuiIDs.RECIPE_BOOK, world, (int) player.posX, (int) player.posY, (int) player.posZ);
		return stack;
	}
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		par3List.add("\u00a77" + "Contains everything");
		par3List.add("\u00a77" + "you should know");
		par3List.add("\u00a77" + "about Traincraft.");
	}
	@Override
	public boolean canBeStoredInToolbox(ItemStack itemstack) {
		return true;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean hasEffect(ItemStack par1ItemStack) {
		return true;
	}

	/**
	 * If this function returns true (or the item is damageable), the ItemStack's NBT tag will be sent to the client.
	 */
	@Override
	public boolean getShareTag() {
		return true;
	}

}
