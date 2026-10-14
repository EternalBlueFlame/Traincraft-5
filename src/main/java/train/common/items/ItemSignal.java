package train.common.items;

import net.minecraft.util.math.BlockPos;

import ebf.tim.utility.CommonUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import train.common.Traincraft;

public class ItemSignal extends Item { // implements IBoxable TODO when IC2
	private Block spawnID;

	public ItemSignal(Block block) {
		super();
		spawnID = block;
		setCreativeTab(Traincraft.tcTab);
	}

	public boolean canBeStoredInToolbox(ItemStack itemstack) {
		return true;
	}

	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l) {
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
			return false;
		}*/
		if (itemstack.getCount() == 0) {
			return false;
		}

		Block block = spawnID;
		if (CommonUtil.setBlockMeta(world, i, j, k, Block.getIdFromBlock(spawnID), 0)) {
			if (CommonUtil.getBlockAt(world, i, j, k) == spawnID) {
				spawnID.onBlockPlacedBy(world, new BlockPos(i, j, k), world.getBlockState(new BlockPos(i, j, k)), entityplayer, new ItemStack(spawnID));
			}
			world.playSound(null, (float) i + 0.5F, (float) j + 0.5F, (float) k + 0.5F, block.getSoundType().getPlaceSound(), net.minecraft.util.SoundCategory.BLOCKS, (block.getSoundType().getVolume() + 1.0F) / 2.0F, block.getSoundType().getPitch() * 0.8F);
			itemstack.shrink(1);
		}
		return true;
	}
}
