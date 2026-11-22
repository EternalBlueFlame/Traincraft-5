package train.common.blocks.slabs;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import train.common.Traincraft;
import train.common.library.BlockIDs;

import java.util.List;
import java.util.Random;

public class BlockAsphaltSlab extends BlockSlab
{
    public BlockAsphaltSlab(boolean par2)
    {
        super(par2, Material.ROCK);
        this.useNeighborBrightness = true;
        this.setSoundType(SoundType.STONE);
        this.setResistance(5.0F);
        this.setHardness(2.0F);
        this.setTranslationKey("asphalt slab");
    }

    /************
     * STUFF
     ************/
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> list)
    {
        list.add(new ItemStack(this, 1, 0));
    }

    @Override
    public String getTranslationKey(int par1)
    {
        return super.getTranslationKey();
    }

    @SideOnly(Side.CLIENT)
    private static boolean isBlockSingleSlab(Block block)
    {
        return block == BlockIDs.asphaltSlab.block;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return isBlockSingleSlab(this) ? new ItemStack(Item.getItemFromBlock(this)) : (this == BlockIDs.asphaltDoubleSlab.block ? new ItemStack(Item.getItemFromBlock(BlockIDs.asphaltSlab.block)) : new ItemStack(Item.getItemFromBlock(BlockIDs.asphalt.block)));
    }

    /************
     * DROPS
     ************/
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(BlockIDs.asphaltSlab.block);
    }

    protected ItemStack createStackedBlock(int par1)
    {
        return new ItemStack(BlockIDs.asphaltSlab.block, 2, 0);
    }

    /************
     * TEXTURES
     ************/
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return BlockIDs.asphalt.block.getIcon(side, 2);
    }
}