package ebf.tim.utility;

import ebf.tim.TrainsInMotion;
import ebf.tim.entities.EntitySeat;
import ebf.tim.entities.GenericRailTransport;
import ebf.tim.networking.PacketRemove;
import mods.railcraft.api.carts.IFluidCart;
import mods.railcraft.api.carts.ILinkableCart;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class CollisionBox extends EntityDragonPart implements IInventory, IFluidHandler, IFluidCart, ILinkableCart {
    public GenericRailTransport host;
    public CollisionBox(GenericRailTransport transport) {
        super(transport, HitboxDynamic.dragonBoxName, transport.getHitboxSize()[2],transport.getHitboxSize()[1]);
        host=transport;
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    public String getCommandSenderName(){
        return host.getCommandSenderName();
    }

    @Override
    public boolean interactFirst(EntityPlayer p_130002_1_) {
        return host.interactFirst(p_130002_1_);
    }
    //check often to be sure the host actually exists and didnt somehow get deleted in such a way that would make it skip hitbox removal.
    @Override
    public void onUpdate(){
        if(worldObj.isRemote && ticksExisted%10==0){
            if(Minecraft.getMinecraft().thePlayer.ridingEntity instanceof GenericRailTransport ||
                    Minecraft.getMinecraft().thePlayer.ridingEntity instanceof EntitySeat){
                this.boundingBox.maxX =0;
                this.boundingBox.maxZ =0;
                this.boundingBox.maxY =0;
            } else {
                this.boundingBox.maxX = this.boundingBox.minX + (double)this.width;
                this.boundingBox.maxZ = this.boundingBox.minZ + (double)this.width;
                this.boundingBox.maxY = this.boundingBox.minY + (double)this.height;
            }
        }
        if(ticksExisted%100==0){
            if(!(worldObj.getEntityByID(((Entity)entityDragonObj).getEntityId()) instanceof GenericRailTransport)){
                this.setDead();
                worldObj.removeEntity(this);
            }
        }
    }
    @Override
    public boolean attackEntityFrom(DamageSource damageSource, float p_70097_2_){
        if(worldObj.isRemote){
            TrainsInMotion.keyChannel.sendToServer(new PacketRemove(host.getEntityId(), damageSource==null?-1:damageSource.getEntity().getEntityId()));
        }
        return this.host.attackEntityFromPart(this, damageSource, p_70097_2_);
    }

    @Override
    public boolean canPassFluidRequests(Fluid fluid) {return host.canPassFluidRequests(fluid);}

    @Override
    public boolean canAcceptPushedFluid(EntityMinecart requester, Fluid fluid) {
        return host.canAcceptPushedFluid(requester,fluid);}

    @Override
    public boolean canProvidePulledFluid(EntityMinecart requester, Fluid fluid) {
        return host.canProvidePulledFluid(requester,fluid);}

    @Override
    public void setFilling(boolean filling) {host.setFilling(filling);}

    @Override
    public boolean isLinkable() {return host.isLinkable();}

    @Override
    public boolean canLinkWithCart(EntityMinecart cart) {return host.canLinkWithCart(cart);}

    @Override
    public boolean hasTwoLinks() {return host.hasTwoLinks();}

    @Override
    public float getLinkageDistance(EntityMinecart cart) {return host.getLinkageDistance(cart);}

    @Override
    public float getOptimalDistance(EntityMinecart cart) {return host.getOptimalDistance(cart);}

    @Override
    public boolean canBeAdjusted(EntityMinecart cart) {return host.canBeAdjusted(cart);}

    @Override
    public void onLinkCreated(EntityMinecart cart) {host.onLinkCreated(cart);}

    @Override
    public void onLinkBroken(EntityMinecart cart) {host.onLinkBroken(cart);}

    @Override
    public int getSizeInventory() {return host.getSizeInventory();}

    @Override
    public ItemStack getStackInSlot(int p_70301_1_) {return host.getStackInSlot(p_70301_1_);}

    @Override
    public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_) {
        return host.decrStackSize(p_70298_1_,p_70298_2_);}

    @Override
    public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
        return host.getStackInSlotOnClosing(p_70304_1_);}

    @Override
    public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_) {
        host.setInventorySlotContents(p_70299_1_,p_70299_2_);}

    @Override
    public String getInventoryName() {return host.getInventoryName();}

    @Override
    public boolean hasCustomInventoryName() {return host.hasCustomInventoryName();}

    @Override
    public int getInventoryStackLimit() {return host.getInventoryStackLimit();}

    @Override
    public void markDirty() {host.markDirty();}

    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {return host.isUseableByPlayer(p_70300_1_);}

    @Override
    public void openInventory() {host.openInventory();}

    @Override
    public void closeInventory() {host.closeInventory();}

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
        return host.isItemValidForSlot(p_94041_1_, p_94041_2_);}

    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        return host.fill(from, resource, doFill);}

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        return host.drain(from, resource, doDrain);}

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        return host.drain(from, maxDrain, doDrain);}

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {return host.canFill(from, fluid);}

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {return host.canDrain(from, fluid);}

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {return host.getTankInfo(from);}

    @Override
    /**
     * Called when a user uses the creative pick block button on this entity.
     *
     * @param target The full target the player is looking at
     * @return A ItemStack to add to the player's inventory, Null if nothing should be added.
     */
    public ItemStack getPickedResult(MovingObjectPosition target) {
        return host.getCartItem();
    }

    @Override
    public void setPosition(double p_70107_1_, double p_70107_3_, double p_70107_5_) {
        this.posX = p_70107_1_;
        this.posY = p_70107_3_;
        this.posZ = p_70107_5_;
        float f = this.width / 2.0F;
        float f1 = this.height;
        if(boundingBox.maxZ!=0) {
            this.boundingBox.setBounds(p_70107_1_ - (double) f, p_70107_3_ - (double) this.yOffset + (double) this.ySize, p_70107_5_ - (double) f, p_70107_1_ + (double) f, p_70107_3_ - (double) this.yOffset + (double) this.ySize + (double) f1, p_70107_5_ + (double) f);
        }
    }
}
