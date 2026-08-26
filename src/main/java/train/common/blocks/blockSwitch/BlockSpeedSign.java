package train.common.blocks.blockSwitch;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import train.common.Traincraft;
import train.common.api.blocks.BlockDynamic;
import train.common.tile.switchStand.TileSpeedSign;

import java.util.List;

public class BlockSpeedSign extends BlockDynamic {
	private IIcon texture;
	private int skinstate = 0;

	public BlockSpeedSign() {
		super(Material.IRON, 0);
		this.setTickRandomly(true);
		// TODO 1.12: block bounds via IBlockState
		// setBlockBounds(0.2F,0.0F,0.2F,0.8F,1.25F,0.8F);
	}

	@Override
	public boolean hasTileEntity() {
		return true;
	}

	public void addCollisionBoxesToList(World p_149743_1_, int p_149743_2_, int p_149743_3_, int p_149743_4_, AxisAlignedBB p_149743_5_, List p_149743_6_, Entity p_149743_7_)
	{
	}


	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}


	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entityliving, ItemStack stack) {
		super.onBlockPlacedBy(world, pos, state, entityliving, stack);
		TileSpeedSign te = (TileSpeedSign) world.getTileEntity(pos);
		if (te != null) {
			int dir = MathHelper.floor((double) ((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
			te.setFacing(EnumFacing.byHorizontalIndex(dir == 0 ? 2 : dir == 1 ? 5 : dir == 2 ? 3 : 4));
			te.setSkinstate(0);
			world.notifyBlockUpdate(pos, state, state, 3);


		}
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer entityplayer, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileSpeedSign te = (TileSpeedSign) world.getTileEntity(pos);
		te.increaseSkinState();
		world.notifyBlockUpdate(pos, state, state, 3);


		return super.onBlockActivated(world, pos, state, entityplayer, hand, side, hitX, hitY, hitZ);
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileSpeedSign(this);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileSpeedSign(this);
	}



	@Override
	public int getRenderType() {
		return -1;
	}







	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		super.breakBlock(worldIn, pos, state);
		if(worldIn.getTileEntity(pos)!=null){
			worldIn.removeTileEntity(pos);
		}
	}






	@Override
	public IIcon getIcon(int i, int j) {
		return texture;
	}
}
