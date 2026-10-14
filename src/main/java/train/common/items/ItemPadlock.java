package train.common.items;

import net.minecraft.world.IBlockAccess;

import net.minecraft.util.math.BlockPos;

import net.minecraft.util.EnumHand;

import net.minecraft.util.EnumFacing;

import net.minecraft.util.EnumActionResult;

import net.minecraft.client.util.ITooltipFlag;

import javax.annotation.Nullable;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import train.common.Traincraft;
import train.common.library.Info;
import train.common.library.ItemIDs;

import java.util.List;

public class ItemPadlock extends ItemPart {

	public ItemPadlock() {
		super(ItemIDs.padlock.iconName);
		maxStackSize = 1;
		setCreativeTab(Traincraft.tcTab);
	}


	@Override
	public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand); int x = pos.getX(), y = pos.getY(), z = pos.getZ(); int side = facing.getIndex();
		return EnumActionResult.PASS;
	}
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack par1ItemStack, @Nullable World world, List<String> par3List, ITooltipFlag flag) {
		boolean par4 = flag.isAdvanced(); EntityPlayer par2EntityPlayer = Traincraft.proxy.getPlayer();
		par3List.add("\u00a77" + "Use it to lock/unlock train cars.");
		par3List.add("\u00a77" + "Use it to trust players to locked train cars.");
		par3List.add("\u00a77" + "Use it to lock & unlock switches.");
	}

	@Override
	public boolean doesSneakBypassUse(ItemStack stack, IBlockAccess world, BlockPos pos, EntityPlayer player) {
		int x = pos.getX(), y = pos.getY(), z = pos.getZ();
		return true;
	}

    @Override
    public String legacyIcon(int meta) {
        return "tc:padlock";
    }
}