package train.blocks.lantern;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ebf.tim.blocks.BlockDynamic;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import train.Traincraft;
import train.items.ItemWrench;
import train.library.GuiIDs;

import java.util.Random;

public class BlockLantern extends BlockDynamic {

	public BlockLantern() {
		super(Material.rock,false);
		setCreativeTab(Traincraft.tcTab);
		this.setTickRandomly(true);
		float f = 0.3F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 3.0F, 0.5F + f);
	}

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
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileLantern(this);
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
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		double d0 = (double) ((float) par2 + 0.5F);
		double d2 = (double) ((float) par4 + 0.5F);
		double d3 = 0.2199999988079071D;

		par1World.spawnParticle("smoke", d0, par3 + d3, d2, 0.0D, 0.0D, 0.0D);
		par1World.spawnParticle("flame", d0, par3 + d3, d2, 0.0D, 0.0D, 0.0D);

	}

	@SideOnly(Side.CLIENT)
	@Override
	public Object getGUI(EntityPlayer player, TileEntity te){
		return new train.blocks.lantern.GuiLantern(player, (TileLantern) te);
	}

	@Override
	public Object getInventoryManager(EntityPlayer player, TileEntity te){
		return null;//lantern has no server-sided inventory, GUI works from packets to the tile directly
	}
}
