package ebf.tim.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ebf.tim.TrainsInMotion;
import ebf.tim.blocks.RailTileEntity;
import ebf.tim.blocks.rails.BlockRailCore;
import ebf.tim.registry.TiMBlocks;
import ebf.tim.utility.CommonUtil;
import mods.railcraft.api.core.items.ITrackItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

import java.util.List;

/**
 * <h1>Key Item</h1>
 * the key used to allow people other than the owner to interact with a locked train or rollingstock.
 * @author Eternal Blue Flame
 */
public class ItemRail extends Item implements ITrackItem {

    public ItemRail(){}

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    @Deprecated
    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
        if(world.isRemote || player==null || stack.stackSize==0){return false;}
        net.minecraft.block.Block block = CommonUtil.getBlockAt(world, x, y, z);

        //for use from item, unlike external use, we want to give the player ability to "place ahead"
        if(block instanceof BlockRailBase){
            if (side==1){//side 1/up
                int rotation = CommonUtil.floorDouble(player.rotationYawHead * 4.0F / 360.0F + 0.5D) & 3;
                switch (rotation){
                    case 0:{z++;
                    if(CommonUtil.getBlockAt(world, x,y,z) instanceof BlockRailBase){z++;}
                        if(CommonUtil.getBlockAt(world, x,y,z) instanceof BlockRailBase){z++;}
                        if(CommonUtil.getBlockAt(world, x,y,z) instanceof BlockRailBase){z++;}
                        if(CommonUtil.getBlockAt(world, x,y,z) instanceof BlockRailBase){return false;}
                        break;
                    }//south
                    case 1:{x--;
                        if(CommonUtil.getBlockAt(world, x,y,z) instanceof BlockRailBase){x--;}
                        if(CommonUtil.getBlockAt(world, x,y,z) instanceof BlockRailBase){x--;}
                        if(CommonUtil.getBlockAt(world, x,y,z) instanceof BlockRailBase){x--;}
                        if(CommonUtil.getBlockAt(world, x,y,z) instanceof BlockRailBase){return false;}}//west
                    break;
                    case 2:{z--;
                        if(CommonUtil.getBlockAt(world, x,y,z) instanceof BlockRailBase){z--;}
                        if(CommonUtil.getBlockAt(world, x,y,z) instanceof BlockRailBase){z--;}
                        if(CommonUtil.getBlockAt(world, x,y,z) instanceof BlockRailBase){z--;}
                        if(CommonUtil.getBlockAt(world, x,y,z) instanceof BlockRailBase){return false;}}//north
                    break;
                    case 3:{x++;
                        if(CommonUtil.getBlockAt(world, x,y,z) instanceof BlockRailBase){x++;}
                        if(CommonUtil.getBlockAt(world, x,y,z) instanceof BlockRailBase){x++;}
                        if(CommonUtil.getBlockAt(world, x,y,z) instanceof BlockRailBase){x++;}
                        if(CommonUtil.getBlockAt(world, x,y,z) instanceof BlockRailBase){return false;}}//east
                }

            } else {//only allow placing on top of a block
                return false;
            }
        } else {//if it's not a track block, then the Y position will be too low, raise it one.
            y++;
        }

        //now be sure the player can place there, and the chunk position is loaded
        if (!player.canPlayerEdit(x,y,z, side, stack) || !world.getChunkProvider().chunkExists(
                x>>4, z>>4)) {
            return false;
        }

        //continue with normal placement
        placeTrack(stack, world, x, y, z,player);
        return true;
    }

    public boolean isPlacedTileEntity(ItemStack stack, TileEntity tile){
        return tile.getClass() == RailTileEntity.class;
    }

    public net.minecraft.block.Block getPlacedBlock(){
        return TiMBlocks.railBlock;
    }

    //redirect for 3rd party mods
    public boolean placeTrack(ItemStack stack, World world, int x, int y, int z){
        return placeTrack(stack, world, x, y, z,null);
    }

    /**
     * normal placement
     * this is meant mainly for add-ons like railcraft, and tools like the track builder
     */
    public boolean placeTrack(ItemStack stack, World world, int x, int y, int z, EntityPlayer p){
        if(world.isRemote){return true;}
        net.minecraft.block.Block block = CommonUtil.getBlockAt(world, x, y, z);

        if (!(World.doesBlockHaveSolidTopSurface(world ,x, y-1, z))){
            return false;
        }

        if(block.isReplaceable(world, x, y, z) || block instanceof IPlantable){
            block.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlockToAir(x, y, z);
        }
        world.removeTileEntity(x,y,z);

        int meta=world.getBlockMetadata(x,y,z);

        if (world.canPlaceEntityOnSide(getPlacedBlock(),x,y,z, false, meta, p, stack)) {

            if (CommonUtil.setBlock(world, x, y, z, getPlacedBlock(), 0)) {
                if (CommonUtil.getBlockAt(world, x, y, z) == getPlacedBlock()) {
                    getPlacedBlock().onBlockPlacedBy(world, x,y,z, world.getClosestPlayer(x,y,z,1000), stack);
                    ((BlockRailCore) CommonUtil.getBlockAt(world, x, y, z)).updateShape(x, y, z, world,
                            //set rail
                            stack.getTagCompound().getTag("rail")!=null?
                                    ItemStack.loadItemStackFromNBT(stack.getTagCompound().getCompoundTag("rail")):
                                    new ItemStack(Items.iron_ingot),
                            //set ties
                            stack.getTagCompound().getTag("ties")!=null?
                                    ItemStack.loadItemStackFromNBT(stack.getTagCompound().getCompoundTag("ties")):
                                    null,

                            //set ballast
                            stack.getTagCompound().getTag("ballast")!=null?
                                    ItemStack.loadItemStackFromNBT(stack.getTagCompound().getCompoundTag("ballast")):
                                    null,

                            //set wires
                            stack.getTagCompound().getTag("wires")!=null?
                                    ItemStack.loadItemStackFromNBT(stack.getTagCompound().getCompoundTag("wires")):
                                    null
                    );
                }

                world.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, getPlacedBlock().stepSound.func_150496_b(), (getPlacedBlock().stepSound.getVolume() + 1.0F) / 2.0F, getPlacedBlock().stepSound.getPitch() * 0.8F);
                --stack.stackSize;
                getPlacedBlock().onPostBlockPlaced(world, x, y, z, meta);
            }
        }

        return true;
    }

    /**
     * <h2>Description text</h2>
     * Allows items to add custom lines of information to the mouseover description, by adding new lines to stringList.
     * Each string added defines a new line.
     * We can cover the key and ticket description here, to simplify other classes.
     */
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List stringList, boolean p_77624_4_) {


        if( stack.hasTagCompound()){
            if(stack.getTagCompound().hasKey("rail")) {
                stringList.add(CommonUtil.translate("menu.rails") + " " + ItemStack.loadItemStackFromNBT(stack.getTagCompound().getCompoundTag("rail")).getDisplayName());
            } else {
                stringList.add("default rails");
            }

            //todo: for some reason i ill never understand, the lang file returns ties and ballast backwards.
            if(stack.getTagCompound().hasKey("ballast")) {
                stringList.add(CommonUtil.translate("menu.ballast")+ " " + ItemStack.loadItemStackFromNBT(stack.getTagCompound().getCompoundTag("ballast")).getDisplayName());
            } else {
                stringList.add(CommonUtil.translate("menu.noballast"));
            }
            if(stack.getTagCompound().hasKey("ties")) {
                stringList.add(CommonUtil.translate("menu.ties")+ " " + ItemStack.loadItemStackFromNBT(stack.getTagCompound().getCompoundTag("ties")).getDisplayName());
            } else {
                stringList.add(CommonUtil.translate("menu.noties"));
            }

            if(stack.getTagCompound().hasKey("wires")) {
                stringList.add(CommonUtil.translate("menu.wires") + " " +ItemStack.loadItemStackFromNBT(stack.getTagCompound().getCompoundTag("wires")).getDisplayName());
            } else {
                stringList.add(CommonUtil.translate("menu.nowires"));
            }
        }
    }

    public static ItemStack setStackData(ItemStack stack, ItemStack ingot, ItemStack ballast, ItemStack ties, ItemStack wires){
        //init stack NBT
        NBTTagCompound stackTag = new NBTTagCompound();
        //make a blank tag, write the item to it, then put it into the stack tag
        if(ingot!=null && ingot.getItem()!=null) {
            NBTTagCompound r =new NBTTagCompound();
            ingot.writeToNBT(r);
            stackTag.setTag("rail", r);
        }

        //rinse and repeat
        if(ties!=null && ties.getItem()!=null && !isItemBanned(ties)) {
            NBTTagCompound t =new NBTTagCompound();
            ties.writeToNBT(t);
            stackTag.setTag("ties", t);
        }
        if(ballast!=null && ballast.getItem()!=null && !isItemBanned(ballast)) {
            NBTTagCompound b =new NBTTagCompound();
            ballast.writeToNBT(b);
            stackTag.setTag("ballast", b);
        }
        if(wires!=null && wires.getItem()!=null && !isItemWires(wires)) {
            NBTTagCompound w =new NBTTagCompound();
            wires.writeToNBT(w);
            stackTag.setTag("wires", w);
        }
        //set the stack tag to the actual stack
        stack.setTagCompound(stackTag);
        return stack;
    }

    public static boolean isItemWires(ItemStack s){
        return true;
    }

    public static boolean isItemBanned(ItemStack s){
        return CommonUtil.stringContains(s.getItem().delegate.name(),"chisel") || Block.getBlockFromItem(s.getItem()).hasTileEntity(s.getItemDamage());
    }

    //adds custom versions of this to the creative menu, with the necessary NBT and metadata
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item p_150895_1_, CreativeTabs p_150895_2_, List tabItems) {
        if(p_150895_2_==TrainsInMotion.creativeTab) {
            if (p_150895_1_ instanceof ItemRail) {
                for (Item ingot : new Item[]{Items.iron_ingot, Items.gold_ingot}) {
                    for (Block b : new Block[]{null, Blocks.gravel, Blocks.stone}) {
                        for (Block t : new Block[]{Blocks.log, Blocks.planks, Blocks.double_stone_slab, null})
                            tabItems.add(setStackData(new ItemStack(p_150895_1_), new ItemStack(ingot), new ItemStack(b), new ItemStack(t), null));
                    }
                }
            } else {
                tabItems.add(new ItemStack(p_150895_1_));
            }
        }
    }
}