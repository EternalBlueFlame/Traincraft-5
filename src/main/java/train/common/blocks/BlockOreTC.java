package train.common.blocks;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import train.common.Traincraft;
import train.common.library.Info;

import java.util.List;
import java.util.Random;

public class BlockOreTC extends BlockFalling {

	private static IIcon texture1;
	private static IIcon texture2;
	private static IIcon texture3;
	private static IIcon texture4;

	public BlockOreTC() {
		super(Material.ROCK);
		setCreativeTab(Traincraft.tcTab);
	}

	public IIcon getIcon(int side, int metadata) {
		if (metadata == 0) return texture1;
		else if (metadata == 1) return texture2;
		else if (metadata == 2) return texture3;
		else return texture4;
	}

	@Override
	public int damageDropped(IBlockState state) {
		return state.getBlock().getMetaFromState(state);
	}

	@Override
	public int quantityDropped(Random random) {
		return 1;
	}

	@Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		if (state.getBlock().getMetaFromState(state) == 1) world.scheduleBlockUpdate(pos, this, this.tickRate(world), 0);
    }

	@Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if (state.getBlock().getMetaFromState(state) == 1) world.scheduleBlockUpdate(pos, this, this.tickRate(world), 0);
    }

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs tab, List subItems) {
		for (int i = 0; i < 4; i++) {
			subItems.add(new ItemStack(this, 1, i));
		}
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		texture1 = iconRegister.registerIcon(Info.modID.toLowerCase() + ":ores/ore_copper");
		texture2 = iconRegister.registerIcon(Info.modID.toLowerCase() + ":ores/ore_oilsands");
		texture3 = iconRegister.registerIcon(Info.modID.toLowerCase() + ":ores/ore_petroleum");
		texture4 = iconRegister.registerIcon(Info.modID.toLowerCase() + ":ballast_test");
	}

	public static IIcon getTexture1() {
		return texture1;
	}
}
