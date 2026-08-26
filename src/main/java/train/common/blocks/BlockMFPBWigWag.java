package train.common.blocks;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.util.EnumFacing;
import train.common.Traincraft;
import train.common.api.blocks.BlockSignal;
import train.common.tile.TileMFPBWigWag;

public class BlockMFPBWigWag extends BlockSignal {

	public BlockMFPBWigWag() {
		super(Material.ROCK,0);
		setCreativeTab(Traincraft.tcTab);
		this.setTickRandomly(true);
	}

	@Override
	public float[] hitboxShape(){return new float[]{0,0,0,1,2,1};}

	@Override
	public boolean hasTileEntity() {
		return true;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	public TileEntity createTileEntity(World world, int metadata) {
		return new TileMFPBWigWag(this);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileMFPBWigWag(this);
	}



	public int getRenderType() {
		return -1;
	}



	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entityliving, ItemStack stack) {
		super.onBlockPlacedBy(world, pos, state, entityliving, stack);
		TileMFPBWigWag te = null;
		if (world.getTileEntity(pos) instanceof TileMFPBWigWag) {
			te = (TileMFPBWigWag) world.getTileEntity(pos);
		}
		if (te != null) {
			int dir = MathHelper.floor((double) ((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
			te.setFacing(EnumFacing.byHorizontalIndex(dir == 0 ? 2 : dir == 1 ? 5 : dir == 2 ? 3 : 4));
			world.notifyBlockUpdate(pos, state, state, 3);
		}
	}
}
