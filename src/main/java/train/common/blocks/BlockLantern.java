package train.common.blocks;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
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
		super(Material.rock,0);
		this.setTickRandomly(true);
		setLightLevel(0.98F);
	}

	@Override
	public float[] hitboxShape(){return new float[]{0.3f,0,0.3f,0.7f,0.9f,0.7f};}

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
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {

		par1World.spawnParticle("smoke",  par2 + 0.5F, par3 + 0.2199999988079071D, par4 + 0.5F, 0.0D, 0.0D, 0.0D);
		par1World.spawnParticle("flame",  par2 + 0.5F, par3 + 0.2199999988079071D, par4 + 0.5F, 0.0D, 0.0D, 0.0D);

	}
	@Override
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer player, int par6, float par7, float par8, float par9) {
		TileEntity te = world.getTileEntity(i, j, k);
		if (player.isSneaking()) {
			return false;
		}
		if(player!=null && player.getCurrentEquippedItem()!=null && player.getCurrentEquippedItem().getItem() instanceof ItemWrench)
		if (te instanceof TileLantern) {
			player.openGui(Traincraft.instance, GuiIDs.LANTERN, world, i, j, k);
		}
		return true;
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		texture = iconRegister.registerIcon(Info.modID.toLowerCase() + ":lantern");
	}

	@Override
	public IIcon getIcon(int i, int j) {
		return texture;
	}
}
