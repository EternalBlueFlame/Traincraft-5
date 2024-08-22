package train.common.blocks.slabs;

import java.util.List;
import java.util.Random;

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

public class BlockSnowGravelSlab extends BlockSlab
{
    public BlockSnowGravelSlab(boolean par2)
    {
        super(par2, Material.rock);
        this.useNeighborBrightness = true;
        this.setStepSound(soundTypeStone);
        this.setResistance(5.0F);
        this.setHardness(2.0F);
        this.setBlockName("snow gravel slab");
    }

    /************
     * STUFF
     ************/
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list)
    {
        if (item != Item.getItemFromBlock(BlockIDs.snowGravelDoubleSlab.block))
        {
            list.add(new ItemStack(item, 1, 0));
        }
    }

    @Override
    public String func_150002_b(int par1)
    {
        return super.getUnlocalizedName();
    }

    @SideOnly(Side.CLIENT)
    private static boolean isBlockSingleSlab(Block block)
    {
        return block == BlockIDs.snowGravelSlab.block;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World par1World, int par2, int par3, int par4)
    {
        return isBlockSingleSlab(this) ? Item.getItemFromBlock(this) : (this == BlockIDs.snowGravelDoubleSlab.block ? Item.getItemFromBlock(BlockIDs.snowGravelSlab.block) : Item.getItemFromBlock(BlockIDs.snowGravel.block));
    }

    /************
     * DROPS
     ************/
    @Override
    public Item getItemDropped(int par1, Random par2Random, int par3)
    {
        return Item.getItemFromBlock(BlockIDs.snowGravelSlab.block);
    }

    protected ItemStack createStackedBlock(int par1)
    {
        return new ItemStack(BlockIDs.snowGravelSlab.block, 2, 0);
    }

    /************
     * TEXTURES
     ************/
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return BlockIDs.snowGravel.block.getIcon(side, 2);
    }
}