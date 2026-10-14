package train.common.wellcar;
import train.common.core.util.LegacyRender;

import javax.annotation.Nullable;

import net.minecraft.util.EnumHand;

import net.minecraft.util.EnumBlockRenderType;

import net.minecraft.block.state.IBlockState;

import net.minecraft.util.math.BlockPos;

import ebf.tim.utility.CommonUtil;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.util.EnumFacing;
import train.common.Traincraft;
import train.common.items.ItemPaintbrushThing;
import train.common.library.GuiIDs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockFiftyThreeFootContainer extends BlockContainer {

	// 1.12: keep the 1.7.10 metadata (facing/variant) in the block state, see LegacyMeta
	@Override
	protected net.minecraft.block.state.BlockStateContainer createBlockState() {
		return new net.minecraft.block.state.BlockStateContainer(this, train.common.api.blocks.LegacyMeta.META);
	}

	@Override
	public net.minecraft.block.state.IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(train.common.api.blocks.LegacyMeta.META, meta & 15);
	}

	@Override
	public int getMetaFromState(net.minecraft.block.state.IBlockState state) {
		return state.getValue(train.common.api.blocks.LegacyMeta.META);
	}


	/* 1.7.10 block bounds (setBlockBounds, minX..maxZ) on top of 1.12 getBoundingBox() */
	protected double minX = 0, minY = 0, minZ = 0, maxX = 1, maxY = 1, maxZ = 1;

	public void setBlockBounds(float x1, float y1, float z1, float x2, float y2, float z2) {
		minX = x1; minY = y1; minZ = z1; maxX = x2; maxY = y2; maxZ = z2;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(minX, minY, minZ, maxX, maxY, maxZ);
	}

    private final Random rand = new Random();
    private TileFortyFootContainer theTile;
    public BlockFiftyThreeFootContainer(Material p_i45386_1_) {
        super(p_i45386_1_);
        this.minX = -1.1F;
        this.minZ = -0.2F;
        this.minY = 0.0F;
        this.maxX = 2.2F;
        this.maxY = 2F;
        this.maxZ = 1.1F;
        this.setBlockBounds(-1.1F, 0.0F, -0.2F, 2.2F, 1.5F, 1.1F);

    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        if (theTile == null) {
            theTile = new TileFortyFootContainer();
        }
        return new TileFortyFootContainer();
    }
    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }


    public EnumBlockRenderType getRenderType(IBlockState state) {
        return LegacyRender.renderType(-1);
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World world, BlockPos pos, AxisAlignedBB bounds, List<AxisAlignedBB> list, @Nullable Entity entity, boolean isActualState) {
    	int x = pos.getX(), y = pos.getY(), z = pos.getZ();
        /*System.out.println("Adding??");
        this.setBlockBounds(-1.1F, 0.0F, -0.2F, 2.2F, 1.5F, 1.1F);
        AxisAlignedBB bounds1 = CommonUtil.createAABB(-1.1F, 0.0F, -0.2F, 2.2F, 1.5F, 1.1F);
        list.add(bounds1);
        super.addCollisionBoxesToList(world, x, y, z, bounds1, list, entity);
       // super.addCollisionBoxesToList(world, x, y, z, AxisAlignedBB.getBoundingBox(-1.1F, 0.0F, -0.2F, 2.2F, 1.5F, 1.1F), list, entity);
        this.setBlockBoundsForItemRender();*/
        /*this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F);
        super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
        this.setBlockBoundsForItemRender();*/
        AxisAlignedBB axisalignedbb1 = this.getCollisionBoundingBox(world.getBlockState(new BlockPos(x, y, z)), world, new BlockPos(x, y, z));

        if (axisalignedbb1 != null && bounds.intersects(axisalignedbb1))
        {
            list.add(axisalignedbb1);
        }
    }


    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float lx, float ly, float lz) {
    	int x = pos.getX(), y = pos.getY(), z = pos.getZ(); int side = facing.getIndex();

        TileEntity te = CommonUtil.getTileEntity(world, x, y, z);
        if (te instanceof TileFortyFootContainer && world.isRemote) {
            if (player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() instanceof ItemPaintbrushThing) {
                ((TileFortyFootContainer) te).goToNextColor();
            }
        }
        if (world.isRemote) return true;

        if (te instanceof TileFortyFootContainer) {
            if (player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() instanceof ItemPaintbrushThing) {
                ((TileFortyFootContainer)te).goToNextColor();
            } else {
                player.openGui(Traincraft.instance, GuiIDs.FORTY_FOOT_CONTAINER, world, x, y, z);
            }
            return true;
        }


        return false;
    }


    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess p_149668_1_, BlockPos pos) {
    	int p_149668_2_ = pos.getX(), p_149668_3_ = pos.getY(), p_149668_4_ = pos.getZ();
        return CommonUtil.createAABB(this.minX, this.minY, this.minZ, this.maxX + 7F, this.maxY, this.maxZ);
    }

    @Override
    public boolean isSideSolid(IBlockState baseState, IBlockAccess world, BlockPos pos, EnumFacing side) {
    	int i = pos.getX(), j = pos.getY(), k = pos.getZ();
        return true;


    }
    public ArrayList<ItemStack> getDropsLegacy(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> ret = new java.util.ArrayList<>(super.getDrops(world, new BlockPos(x, y, z), getStateFromMeta(metadata), fortune));
        ItemStack stack = new ItemStack(CommonUtil.getBlockAt(world, x, y, z), 1, metadata);

        TileFortyFootContainer te = world.getTileEntity(new BlockPos(x, y, z)) instanceof TileFortyFootContainer ? (TileFortyFootContainer)world.getTileEntity(new BlockPos(x, y, z)) : null;

        if (te != null)
        {

            NBTTagCompound tag = new NBTTagCompound();
            te.writeToNBT(tag);
            if (!stack.hasTagCompound())
            {
                stack.setTagCompound(tag);
            }
            ret.add(stack);
        }
        return ret;
    }

    @Override
    public void getDrops(net.minecraft.util.NonNullList<ItemStack> drops, net.minecraft.world.IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
    	if (world instanceof World) {
    		drops.addAll(getDropsLegacy((World) world, pos.getX(), pos.getY(), pos.getZ(), getMetaFromState(state), fortune));
    	} else {
    		super.getDrops(drops, world, pos, state, fortune);
    	}
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, @javax.annotation.Nullable net.minecraft.tileentity.TileEntity te, ItemStack tool) {
        super.harvestBlock(world, player, pos, state, te, tool);
        world.setBlockToAir(pos);
    }

    @Override
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
    	int x = pos.getX(), y = pos.getY(), z = pos.getZ();
        if (willHarvest)
        {
            return true; // If it will harvest, delay deletion of the block
            // until after getDrops
        }
        return super.removedByPlayer(state, world, pos, player, false);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortuneLevel) {
    	int metadata = getMetaFromState(state);
        return null;
    }

    /*@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortuneLevel) {
    	int metadata = getMetaFromState(state);
    *//*    ItemStack item = new ItemStack(new ItemFortyFootContainer(new BlockFortyFootContainer(Material.ROCK)));
        TileFortyFootContainer theContainerTile = theTile;
        NBTTagCompound nbt = new NBTTagCompound();
        NBTTagList list = new NBTTagList();

        for (int i = 0; i < theContainerTile.inventory.length; ++i)
        {
            if (theContainerTile.inventory[i] != null)
            {
                NBTTagCompound comp = new NBTTagCompound();
                comp.setByte("Slot", (byte)i);
                theContainerTile.inventory[i].writeToNBT(comp);
                list.appendTag(comp);
            }
        }

        nbt.setTag("Items", list);
        System.out.println("um.hellp?");
        item.setTagCompound(nbt);
        System.out.println("Faile?");
        System.out.println(item.toString());
        return new ItemFortyFootContainer(new BlockFortyFootContainer(Material.ROCK));*//*

    }*/

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase player, ItemStack stack) {
    	int x = pos.getX(), y = pos.getY(), z = pos.getZ();
        TileFortyFootContainer te = (TileFortyFootContainer) world.getTileEntity(new BlockPos(x, y, z));
        int playerYaw = CommonUtil.floorDouble((player.rotationYaw / 90.0F) + 2.5D) & 3;

        if (te != null && stack.getTagCompound() != null)
        {
            te.directionPlaced = playerYaw;
            stack.getTagCompound().setInteger("x", x);
            stack.getTagCompound().setInteger("y", y);
            stack.getTagCompound().setInteger("z", z);
            te.readFromNBT(stack.getTagCompound());
        }
    }
}
