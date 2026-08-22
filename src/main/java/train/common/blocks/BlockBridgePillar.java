package train.common.blocks;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import train.common.Traincraft;
import train.common.tile.TileBridgePillar;

public class BlockBridgePillar extends Block implements ITileEntityProvider {
	private IIcon texture;

	public BlockBridgePillar() {
		super(Material.WOOD);
		setCreativeTab(Traincraft.tcTab);
		setHarvestLevel("axe", 0);
		//this.setTickRandomly(true);
		//this.setBlockBounds(0.5F , 0.0F, 0.5F , 0.5F ,  2.0F, 0.5F);
	}

	@Override
	public boolean hasTileEntity() {
		return true;
	}


	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}


	public TileEntity createTileEntity(World world, int metadata) {
		return new TileBridgePillar();
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileBridgePillar();
	}


	public int getRenderType() {
		return -1;
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		super.breakBlock(world, pos, state);
		if (world.getTileEntity(pos) != null) {
			world.removeTileEntity(pos);
		}
	}



	public IIcon getIcon(int i, int j) {
		return texture;
	}
}
