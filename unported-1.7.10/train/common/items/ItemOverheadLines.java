package train.common.items;

import net.minecraft.util.math.BlockPos;

import net.minecraft.util.EnumHand;

import net.minecraft.util.EnumFacing;

import net.minecraft.util.EnumActionResult;

import ebf.tim.utility.CommonUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import train.common.Traincraft;

public class ItemOverheadLines extends Item {
	private Block spawnID;

	public ItemOverheadLines(Block block) {
		super();
		spawnID = block;
		setCreativeTab(Traincraft.tcTab);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer entityplayer, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float par8, float par9, float par10) {
		ItemStack itemstack = entityplayer.getHeldItem(hand); int i = pos.getX(), j = pos.getY(), k = pos.getZ(); int l = facing.getIndex();
		Block i1 = CommonUtil.getBlockAt(world, i, j, k);
		if (i1 == Blocks.SNOW) {
			l = 0;
		}
		else if (i1 != Blocks.VINE) {
			if (l == 0) {
				j--;
			}
			if (l == 1) {
				j++;
			}
			if (l == 2) {
				k--;
			}
			if (l == 3) {
				k++;
			}
			if (l == 4) {
				i--;
			}
			if (l == 5) {
				i++;
			}
		}
		/*if (!entityplayer.canCurrentToolHarvestBlock(i, j, k)) {
			return EnumActionResult.PASS;
		}*/
		if (itemstack.getCount() == 0) {
			return EnumActionResult.PASS;
		}

		Block block = spawnID;
		if (CommonUtil.setBlockMeta(world, i, j, k, Block.getIdFromBlock(spawnID), 0)) {
			if (CommonUtil.getBlockAt(world, i, j, k) == spawnID) {
				spawnID.onBlockPlacedBy(world, i, j, k, entityplayer, new ItemStack(spawnID));
			}
			world.playSoundEffect((float) i + 0.5F, (float) j + 0.5F, (float) k + 0.5F, block.getSoundType().getStepResourcePath(), (block.getSoundType().getVolume() + 1.0F) / 2.0F, block.getSoundType().getPitch() * 0.8F);
			itemstack.shrink(1);
		}
		return EnumActionResult.SUCCESS;
	}
}
