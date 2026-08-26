package train.common.blocks;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IIcon;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import train.common.Traincraft;
import train.common.api.blocks.BlockDynamic;
import train.common.items.ItemWrench;
import train.common.library.GuiIDs;
import train.common.library.Info;
import train.common.tile.TileLantern;

import java.util.Random;

public class BlockLantern extends BlockDynamic {
	private IIcon texture;

	public BlockLantern() {
		super(Material.ROCK,0);
		this.setTickRandomly(true);
		setLightLevel(0.98F);
	}

	@Override
	public float[] hitboxShape(){return new float[]{0.3f,0,0.3f,0.7f,0.9f,0.7f};}

	@Override
	public boolean hasTileEntity() {
		return true;
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
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileLantern();
	}
	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileLantern();
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@SideOnly(Side.CLIENT)
	/**
	 * A randomly called display update to be able to add particles or other items for display
	 */
	@Override
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {

		world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, pos.getX() + 0.5D, pos.getY() + 0.2199999988079071D, pos.getZ() + 0.5D, 0.0D, 0.0D, 0.0D);
		world.spawnParticle(EnumParticleTypes.FLAME, pos.getX() + 0.5D, pos.getY() + 0.2199999988079071D, pos.getZ() + 0.5D, 0.0D, 0.0D, 0.0D);

	}
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileEntity te = world.getTileEntity(pos);
		if (player.isSneaking()) {
			return false;
		}
		if(player!=null && player.inventory.getCurrentItem()!=null && player.inventory.getCurrentItem().getItem() instanceof ItemWrench)
		if (te instanceof TileLantern) {
			player.openGui(Traincraft.instance, GuiIDs.LANTERN, world, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		texture = iconRegister.registerIcon(Info.modID.toLowerCase() + ":lantern");
	}

	@Override
	public IIcon getIcon(int i, int j) {
		return texture;
	}
}
