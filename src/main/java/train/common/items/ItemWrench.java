package train.common.items;

import net.minecraft.world.IBlockAccess;

import net.minecraft.util.math.BlockPos;

import net.minecraft.util.EnumHand;

import net.minecraft.util.EnumActionResult;

import net.minecraft.client.util.ITooltipFlag;

import javax.annotation.Nullable;

import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ebf.tim.utility.CommonUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.util.EnumFacing;
import train.common.Traincraft;
import train.common.library.ItemIDs;

import java.util.List;
public class ItemWrench extends ItemPart {

	public ItemWrench() {
		super(ItemIDs.composite_wrench.iconName);
		maxStackSize = 1;
		setCreativeTab(Traincraft.tcTab);
	}

	@Override
	public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand); int x = pos.getX(), y = pos.getY(), z = pos.getZ(); int side = facing.getIndex();
		Block blockId = CommonUtil.getBlockAt(world, x, y, z);
		if (blockId.rotateBlock(world, pos, facing)) {
			player.swingArm(hand);
			return (!world.isRemote) ? EnumActionResult.SUCCESS : EnumActionResult.PASS;
		}
		return EnumActionResult.PASS;
	}
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack par1ItemStack, @Nullable World world, List<String> par3List, ITooltipFlag flag) {
		boolean par4 = flag.isAdvanced(); EntityPlayer par2EntityPlayer = Traincraft.proxy.getPlayer();
		par3List.add("\u00a77" + "Works same as a BuildCraft wrench.");
		par3List.add("\u00a77" + "Use it to change lantern color.");
		par3List.add("\u00a77" + "Use it to lock/unlock certain carts (passenger)");
		par3List.add("\u00a77" + "Use it to remove locked trains (OP only)");
	}


	@Override
	public boolean doesSneakBypassUse(ItemStack stack, IBlockAccess world, BlockPos pos, EntityPlayer player) {
		int x = pos.getX(), y = pos.getY(), z = pos.getZ();
		return true;
	}
}