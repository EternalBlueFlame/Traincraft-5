package train.common.blocks.switchStand;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import train.common.Traincraft;
import train.common.api.blocks.BlockSwitch;
import train.common.tile.switchStand.TileSwitchStand;

public class BlockSwitchStand extends BlockSwitch {

	public BlockSwitchStand() {
		super(Material.wood, 0);
		setCreativeTab(Traincraft.tcTab);
		this.setTickRandomly(true);
		setHardness(1.75F);
		setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 0.75F, 0.8F);
		setStepSound(soundTypeMetal);
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
		return new TileSwitchStand(this);
	}
	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileSwitchStand(this);
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@SideOnly(Side.CLIENT)



	@Override
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack stack) {
		super.onBlockPlacedBy(world, i, j, k, entityliving, stack);
		TileSwitchStand te = (TileSwitchStand) world.getTileEntity(i, j, k);
		if (te != null) {
			int dir = MathHelper.floor_double((double) ((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
			te.setFacing(ForgeDirection.getOrientation(dir == 0 ? 2 : dir == 1 ? 5 : dir == 2 ? 3 : 4));
			world.markBlockForUpdate(i, j, k);
		}
	}

	/**
	 * Can this block provide power. Only wire currently seems to have this change based on its state.
	 */
	public boolean canProvidePower()
	{
		return true;
	}

}
