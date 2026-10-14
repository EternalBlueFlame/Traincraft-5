package train.common.blocks.slabs;

import net.minecraft.util.math.BlockPos;

import net.minecraft.util.NonNullList;

import net.minecraft.block.state.IBlockState;

import net.minecraft.block.SoundType;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import train.common.Traincraft;
import train.common.library.BlockIDs;

import java.util.List;
import java.util.Random;

public class BlockBallastSlab extends BlockTCSlab
{
    public BlockBallastSlab(boolean par2)
    {
        super(par2, Material.ROCK);
        this.useNeighborBrightness = true;
        this.setSoundType(SoundType.STONE);
        this.setResistance(5.0F);
        this.setHardness(2.0F);
        this.setTranslationKey("ballast slab");
    }

    /************
     * STUFF
     ************/
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
    	Item item = Item.getItemFromBlock(this);
        if (item != Item.getItemFromBlock(BlockIDs.ballastDoubleSlab.block))
        {
            list.add(new ItemStack(item, 1, 0));
        }
    }

    @SideOnly(Side.CLIENT)
    private static boolean isBlockSingleSlab(Block block)
    {
        return block == BlockIDs.ballastSlab.block;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getItem(World par1World, BlockPos pos, IBlockState state) {
    	int par2 = pos.getX(), par3 = pos.getY(), par4 = pos.getZ();
        return new ItemStack(isBlockSingleSlab(this) ? Item.getItemFromBlock(this) : (this == BlockIDs.ballastDoubleSlab.block ? Item.getItemFromBlock(BlockIDs.ballastSlab.block) : Item.getItemFromBlock(BlockIDs.oreTC.block)));
    }

    /************
     * DROPS
     ************/
    @Override
    public Item getItemDropped(IBlockState state, Random par2Random, int par3) {
    	int par1 = getMetaFromState(state);
        return Item.getItemFromBlock(BlockIDs.ballastSlab.block);
    }

    protected ItemStack createStackedBlock(int par1)
    {
        return new ItemStack(BlockIDs.ballastSlab.block, 2, 0);
    }

}