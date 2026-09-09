package train.common.blocks;

import ebf.tim.utility.CommonUtil;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import train.common.Traincraft;
import train.common.api.blocks.BlockSignal;
import train.common.tile.TileMFPBWigWag;

public class BlockMFPBWigWag extends BlockSignal {

	public BlockMFPBWigWag() {
		super(Material.rock,0);
		setCreativeTab(Traincraft.tcTab);
		this.setTickRandomly(true);
	}

	@Override
	public float[] hitboxShape(){return new float[]{0,0,0,1,2,1};}

	@Override
	public boolean hasTileEntity(int metadata) {
		return true;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileMFPBWigWag(this);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileMFPBWigWag(this);
	}


	@Override
	public int getRenderType() {
		return -1;
	}



	@Override
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack stack) {
		super.onBlockPlacedBy(world, i, j, k, entityliving, stack);
		TileMFPBWigWag te = null;
		if (world.getTileEntity(i,j,k) instanceof TileMFPBWigWag) {
			te = (TileMFPBWigWag) world.getTileEntity(i, j, k);
		}
		if (te != null) {
			int dir = CommonUtil.floorDouble((double) ((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
			te.setFacing(ForgeDirection.getOrientation(dir == 0 ? 2 : dir == 1 ? 5 : dir == 2 ? 3 : 4));
			CommonUtil.markBlockForUpdate(world, i, j, k);
		}
	}
}
