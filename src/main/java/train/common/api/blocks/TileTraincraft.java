/*******************************************************************************
 * Copyright (c) 2012 Mrbrutal. All rights reserved.
 * 
 * @name TrainCraft
 * @author Mrbrutal
 ******************************************************************************/

package train.common.api.blocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;

public class TileTraincraft extends TileRenderFacing implements ISidedInventory{

    public ItemStack[] slots;

    public TileTraincraft(){}

    public TileTraincraft(int slotAmount){
        this.slots = new ItemStack[slotAmount];
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side){
        if(this.slots.length > 0){
            int[] theInt = new int[slots.length];
            for(int i = 0; i < theInt.length; i++){
                theInt[i] = i;
            }
            return theInt;
        } else {
            return new int[0];
        }
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack stack, int side){
        return false;
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack stack, int side){
        return false;
    }

    @Override
    public int getSizeInventory(){
        return this.slots==null?0:this.slots.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot){
        return this.slots.length >= slot ? this.slots[slot] : null;
    }

    @Override
    public ItemStack decrStackSize(int i, int j){
        if (this.slots.length >= i && this.slots[i] != null) {
            if (this.slots[i].stackSize <= j) {
                ItemStack itemstack = this.slots[i];
                this.slots[i] = null;
                return itemstack;
            }
            ItemStack itemstack1 = this.slots[i].splitStack(j);
            if (this.slots[i].stackSize == 0) {
                this.slots[i] = null;
            }
            return itemstack1;
        }
        else {
            return null;
        }
    }

    @Override
    public ItemStack removeStackFromSlot(int slot){
        if (this.slots[slot] != null) {
            ItemStack var2 = this.slots[slot];
            this.slots[slot] = null;
            return var2;
        }
        return null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack){
        this.slots[slot] = stack;
        if (stack != null && stack.stackSize > getInventoryStackLimit()) {
            stack.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public String getName(){
        return "traincraft.tile.generic";
    }

    @Override
    public boolean hasCustomInventoryName(){
        return false;
    }

    @Override
    public int getInventoryStackLimit(){
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player){
        return player.getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory(EntityPlayer p){

    }

    @Override
    public void closeInventory(EntityPlayer p){

    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack){
        return true;
    }

    @Override
    public final void writeToNBT(NBTTagCompound nbt){
        super.writeToNBT(nbt);
        this.writeToNBT(nbt, false);
    }

    @Override
    public final void readFromNBT(NBTTagCompound nbt){
        super.readFromNBT(nbt);
        this.readFromNBT(nbt, false);
    }

    public NBTTagCompound writeToNBT(NBTTagCompound nbt, boolean forSyncing){
        super.writeToNBT(nbt);
        if(!forSyncing){
            NBTTagList nbttaglist = new NBTTagList();
            for (int i = 0; i < this.slots.length; i++) {
                if (this.slots[i] != null) {
                    NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                    nbttagcompound1.setByte("Slot", (byte) i);
                    this.slots[i].writeToNBT(nbttagcompound1);
                    nbttaglist.appendTag(nbttagcompound1);
                }
            }
            nbt.setTag("Items", nbttaglist);
        }
        return nbt;
    }

    public void readFromNBT(NBTTagCompound nbt, boolean forSyncing){
        super.readFromNBT(nbt);
        if(!forSyncing){
            NBTTagList nbttaglist = nbt.getTagList("Items", Constants.NBT.TAG_COMPOUND);
            this.slots = new ItemStack[getSizeInventory()];
            for (int i = 0; i < nbttaglist.tagCount(); i++) {
                NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
                byte byte0 = nbttagcompound1.getByte("Slot");
                if (byte0 >= 0 && byte0 < this.slots.length) {
                    this.slots[byte0] = new ItemStack(nbttagcompound1);
                }
            }
        }
    }

    public void syncTileEntity(){
        for(Object o : this.getWorld().playerEntities){
            if(o instanceof EntityPlayerMP){
                EntityPlayerMP player = (EntityPlayerMP) o;
                if(player.getDistance(xCoord, yCoord, zCoord) <= 64) {
                    player.playerNetServerHandler.sendPacket(this.getDescriptionPacket());
                }
            }
        }
    }

    @Override
    public SPacketUpdateTileEntity getDescriptionPacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt, true);
        return new SPacketUpdateTileEntity(getPos(), 1, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt){
        if(pkt != null){
            this.readFromNBT(pkt.func_148857_g(), true);
        }
    }

}