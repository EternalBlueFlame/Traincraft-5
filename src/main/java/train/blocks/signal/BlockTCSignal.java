package train.blocks.signal;

import ebf.tim.blocks.BlockSignal;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import train.Traincraft;

import java.util.Random;

public class BlockTCSignal extends BlockSignal {

	public BlockTCSignal() {
		super(Material.circuits,true);
		this.setLightLevel(1.0F);
		setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 2.6F, 0.8F);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileTCSignal(this);
	}

	@Override
	public TileEntity createTileEntity(World world, int meta) {
		return new TileTCSignal(this);
	}
}