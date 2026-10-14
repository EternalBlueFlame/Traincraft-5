package train.common.blocks;

import net.minecraft.util.math.BlockPos;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ebf.tim.utility.CommonUtil;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import train.common.library.Info;

public class BlockTraincraftFluid extends BlockFluidClassic {
	
		protected boolean flammable;
	protected int flammability = 0;

	public String MODID=Info.modID;

	public void setModID(String i){
		MODID=i;
	}

	public BlockTraincraftFluid(Fluid fluid, Material material) {
		super(fluid, material);
	}



	public BlockTraincraftFluid setFlammable(boolean flammable) {
		this.flammable = flammable;
		return this;
	}

	public BlockTraincraftFluid setFlammability(int flammability) {
		this.flammability = flammability;
		return this;
	}

	@Override
	public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) {
		int x = pos.getX(), y = pos.getY(), z = pos.getZ();
		return flammable ? 300 : 0;
	}

	@Override
	public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
		int x = pos.getX(), y = pos.getY(), z = pos.getZ();
		return flammability;
	}

	@Override
	public boolean isFlammable(IBlockAccess world, BlockPos pos, EnumFacing face) {
		int x = pos.getX(), y = pos.getY(), z = pos.getZ();
		return flammable;
	}

	@Override
	public boolean isFireSource(World world, BlockPos pos, EnumFacing side) {
		int x = pos.getX(), y = pos.getY(), z = pos.getZ();
		return flammable && flammability == 0;
	}

	@Override
	public boolean canDisplace(IBlockAccess world, BlockPos pos) {
		int x = pos.getX(), y = pos.getY(), z = pos.getZ();
		return !world.getBlockState(pos).getMaterial().isLiquid() && super.canDisplace(world, pos);
	}
	
	@Override
	public boolean displaceIfPossible(World world, BlockPos pos) {
		int x = pos.getX(), y = pos.getY(), z = pos.getZ();
		return !world.getBlockState(pos).getMaterial().isLiquid() && super.displaceIfPossible(world, pos);
	}
	
	@Override
	public String getTranslationKey() {
		return "fluid." + MODID + ":" + fluidName;
	}
}
