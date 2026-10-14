package train.common.items;

import net.minecraft.util.EnumHand;

import net.minecraft.util.EnumActionResult;

import net.minecraft.util.ActionResult;

import net.minecraft.client.util.ITooltipFlag;

import javax.annotation.Nullable;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import train.common.Traincraft;
import train.common.library.GuiIDs;
import train.common.library.Info;

import java.util.List;

public class ItemRecipeBook extends Item implements train.common.items.ILegacyIcon {
	
	public static int	page	= 0;
	public static int	recipe	= 0;

	public ItemRecipeBook() {
		super();
		maxStackSize = 1;
		setCreativeTab(Traincraft.tcTab);
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	 */
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		NBTTagCompound var3 = stack.getTagCompound();
		if (var3 == null) {
			var3 = new NBTTagCompound();
			stack.setTagCompound(var3);
			stack.getTagCompound().setInteger("currPage", ItemRecipeBook.page);
			stack.getTagCompound().setInteger("currRecipe", ItemRecipeBook.recipe);
		}
		else {
			stack.getTagCompound().setInteger("currPage", ItemRecipeBook.page);
			stack.getTagCompound().setInteger("currRecipe", ItemRecipeBook.recipe);
		}
		player.openGui(Traincraft.instance, GuiIDs.RECIPE_BOOK, world, (int) player.posX, (int) player.posY, (int) player.posZ);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
	}
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack par1ItemStack, @Nullable World world, List<String> par3List, ITooltipFlag flag) {
		boolean par4 = flag.isAdvanced(); EntityPlayer par2EntityPlayer = Traincraft.proxy.getPlayer();
		par3List.add("\u00a77" + "Contains everything");
		par3List.add("\u00a77" + "you should know");
		par3List.add("\u00a77" + "about Traincraft.");
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



    @Override
    public String legacyIcon(int meta) {
        return "tc:parts/item_book_blue";
    }
}
