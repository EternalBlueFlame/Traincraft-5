/*******************************************************************************
 * Copyright (c) 2012 Mrbrutal. All rights reserved.
 * 
 * @name TrainCraft
 * @author Mrbrutal
 ******************************************************************************/

package train.blocks;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ebf.tim.blocks.BlockDynamic;
import ebf.tim.blocks.TileRenderFacing;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import train.Traincraft;
import train.library.Info;

public class BlockBridgePillar extends BlockDynamic {

	private IIcon texture;

	public BlockBridgePillar() {
		super(Material.wood, false);
		setCreativeTab(Traincraft.tcTab);
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
	public int getRenderType() {
		return RenderingRegistry.getNextAvailableRenderId();
	}

	@Override
	public IIcon getIcon(int i, int j) {
		return texture;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new tilePillar(this);
	}
	

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		texture = iconRegister.registerIcon(Info.modID.toLowerCase() + ":assembly_1_bottom");
	}


	public class tilePillar extends TileRenderFacing{

		public tilePillar(BlockDynamic block) {
			super(block);
		}
	}
}