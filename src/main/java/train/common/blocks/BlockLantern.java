package train.common.blocks;
import train.common.core.util.LegacyRender;

import net.minecraft.util.EnumParticleTypes;

import net.minecraft.util.math.BlockPos;

import net.minecraft.util.EnumHand;

import net.minecraft.util.EnumFacing;

import net.minecraft.util.EnumBlockRenderType;

import net.minecraft.block.state.IBlockState;

import ebf.tim.utility.CommonUtil;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import train.common.Traincraft;
import train.common.api.blocks.BlockDynamic;
import train.common.items.ItemWrench;
import train.common.library.GuiIDs;
import train.common.library.Info;
import train.common.tile.TileLantern;

import java.util.Random;

public class BlockLantern extends BlockDynamic {

	public BlockLantern() {
		super(Material.ROCK,0);
		this.setTickRandomly(true);
		setLightLevel(0.98F);
	}

	@Override
	public float[] hitboxShape(){return new float[]{0.3f,0,0.3f,0.7f,0.9f,0.7f};}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		int metadata = getMetaFromState(state);
		return true;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		int metadata = getMetaFromState(state);
		return new TileLantern();
	}
	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileLantern();
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return LegacyRender.renderType(-1);
	}

	@SideOnly(Side.CLIENT)
	/**
	 * A randomly called display update to be able to add particles or other items for display
	 */
	@Override
	public void randomDisplayTick(IBlockState state, World par1World, BlockPos pos, Random par5Random) {
		int par2 = pos.getX(), par3 = pos.getY(), par4 = pos.getZ();

		par1World.spawnParticle(EnumParticleTypes.getByName("smoke"), par2 + 0.5F, par3 + 0.2199999988079071D, par4 + 0.5F, 0.0D, 0.0D, 0.0D);
		par1World.spawnParticle(EnumParticleTypes.getByName("flame"), par2 + 0.5F, par3 + 0.2199999988079071D, par4 + 0.5F, 0.0D, 0.0D, 0.0D);

	}
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float par7, float par8, float par9) {
		int i = pos.getX(), j = pos.getY(), k = pos.getZ(); int par6 = facing.getIndex();
		TileEntity te = CommonUtil.getTileEntity(world, i, j, k);
		if (player.isSneaking()) {
			return false;
		}
		if(player!=null && player.getHeldItemMainhand()!=null && player.getHeldItemMainhand().getItem() instanceof ItemWrench)
		if (te instanceof TileLantern) {
			player.openGui(Traincraft.instance, GuiIDs.LANTERN, world, i, j, k);
		}
		return true;
	}

}
