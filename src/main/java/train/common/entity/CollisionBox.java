package train.common.entity;

import mods.railcraft.api.carts.IFluidCart;
import mods.railcraft.api.carts.ILinkableCart;
import mods.railcraft.api.carts.IMinecart;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import train.common.Traincraft;
import train.common.api.EntityRollingStock;
import train.common.core.network.PacketInteract;
import train.common.core.network.PacketRemove;


public class CollisionBox extends MultiPartEntityPart implements IInventory, IFluidHandler, IMinecart, ILinkableCart, IFluidCart {

    static String dragonBoxName ="trainbox";
    public EntityRollingStock host;
    public float yOffset;
    public float ySize;

    //client side entity registration shenanagains. this lets us register the hitbox as a real entity
    public CollisionBox(final World w){
        super(new IEntityMultiPart() {
            @Override
            public World getWorld() {return w;}

            @Override
            public boolean attackEntityFromPart(MultiPartEntityPart p, DamageSource d, float i) {return false;}
        },dragonBoxName,1,1);
    }

    public CollisionBox(EntityRollingStock transport) {
        super(transport, dragonBoxName, transport.getHitboxSize()[2], transport.getHitboxSize()[1]);
        host = transport;
    }


    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    public String getName() {
        return host==null?"collisionBox":host.getName();
    }

    @Override
    public boolean hasCustomName() {
        return host != null && host.hasCustomName();
    }

    @Override
    public boolean processInitialInteract(EntityPlayer player, EnumHand hand) {
        if(world.isRemote){
            Traincraft.keyChannel.sendToServer(new PacketInteract(host.getEntityId()));
        }
        return host != null && host.processInitialInteract(player, EnumHand.MAIN_HAND);
    }

    //check often to be sure the host actually exists and didnt somehow get deleted in such a way that would make it skip hitbox removal.
    @Override
    public void onUpdate() {
        if(world==null){
            return;
        }
        if (ticksExisted % 100 == 0) {
            if (host ==null || !(world.getEntityByID(host.getEntityId()) instanceof EntityRollingStock)) {
                this.setDead();
                world.removeEntity(this);
            }
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource damageSource, float p_70097_2_) {
        if(world.isRemote){
            Traincraft.keyChannel.sendToServer(new PacketRemove(host.getEntityId(), damageSource==null?-1:damageSource.getTrueSource().getEntityId()));
            return true;
        }
        return host != null && host.attackEntityFromPart(this, damageSource, p_70097_2_);
    }

    @Override
    public boolean isLinkable() {
        return host.isLinkable();
    }

    @Override
    public boolean canLinkWithCart(EntityMinecart cart) {
        return host.canLinkWithCart(cart);
    }

    @Override
    public boolean hasTwoLinks() {
        return host.hasTwoLinks();
    }

    @Override
    public float getLinkageDistance(EntityMinecart cart) {
        return host.getLinkageDistance(cart);
    }

    @Override
    public float getOptimalDistance(EntityMinecart cart) {
        return host.getOptimalDistance(cart);
    }

    @Override
    public boolean canBeAdjusted(EntityMinecart cart) {
        return host.canBeAdjusted(cart);
    }

    @Override
    public void onLinkCreated(EntityMinecart cart) {
        host.onLinkCreated(cart);
    }

    @Override
    public void onLinkBroken(EntityMinecart cart) {
        host.onLinkBroken(cart);
    }

    @Override
    public int getSizeInventory() {
        return host.getSizeInventory();
    }

    @Override
    public ItemStack getStackInSlot(int p_70301_1_) {
        return host.getStackInSlot(p_70301_1_);
    }

    @Override
    public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_) {
        return host.decrStackSize(p_70298_1_, p_70298_2_);
    }

    @Override
    public ItemStack removeStackFromSlot(int p_70304_1_) {
        return host.removeStackFromSlot(p_70304_1_);
    }

    @Override
    public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_) {
        host.setInventorySlotContents(p_70299_1_, p_70299_2_);
    }

    @Override
    public int getInventoryStackLimit() {
        return host.getInventoryStackLimit();
    }

    @Override
    public void markDirty() {
        host.markDirty();
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer p_70300_1_) {
        return host.isUsableByPlayer(p_70300_1_);
    }

    @Override
    public void openInventory(EntityPlayer player) {
        host.openInventory(player);
    }

    @Override
    public void closeInventory(EntityPlayer player) {
        host.closeInventory(player);
    }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
        return host.isItemValidForSlot(p_94041_1_, p_94041_2_);
    }

    @Override
    public int getField(int id) {
        return host.getField(id);
    }

    @Override
    public void setField(int id, int value) {
        host.setField(id, value);
    }

    @Override
    public int getFieldCount() {
        return host.getFieldCount();
    }

    @Override
    public void clear() {
        host.clear();
    }

    @Override
    public boolean isEmpty() {
        return host.isEmpty();
    }

    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        return host.fill(resource, doFill);
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        return host.drain(resource, doDrain);
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        return host.drain(maxDrain, doDrain);
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return host.canFill(fluid);
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return host.canDrain(fluid);
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        IFluidTankProperties[] props = host.getTankProperties();
        if (props == null) {
            return new FluidTankInfo[0];
        }
        FluidTankInfo[] info = new FluidTankInfo[props.length];
        for (int i = 0; i < props.length; i++) {
            info[i] = new FluidTankInfo(props[i].getContents(), props[i].getCapacity());
        }
        return info;
    }

    @Override
    /**
     * Called when a user uses the creative pick block button on this entity.
     *
     * @param target The full target the player is looking at
     * @return A ItemStack to add to the player's inventory, Null if nothing should be added.
     */
    public ItemStack getPickedResult(RayTraceResult target) {
        return host.getCartItem();
    }

    @Override
    public void setPosition(double p_70107_1_, double p_70107_3_, double p_70107_5_) {
        this.prevPosX = this.posX = p_70107_1_;
        this.prevPosY = this.posY = p_70107_3_;
        this.prevPosZ = this.posZ = p_70107_5_;
        this.setEntityBoundingBox(new AxisAlignedBB(p_70107_1_ - (this.width*0.5), p_70107_3_ - (double) this.yOffset + (double) this.ySize, p_70107_5_ - (this.width*0.5), p_70107_1_ + (this.width*0.5), p_70107_3_ - (double) this.yOffset + (double) this.ySize + (double) this.height, p_70107_5_ + (this.width*0.5)));

    }

    @Override
    public boolean doesCartMatchFilter(ItemStack stack, EntityMinecart cart) {
        return host.doesCartMatchFilter(stack, cart);
    }

    @Override
    public boolean canPassFluidRequests(Fluid fluid) {
        return true;
    }

    @Override
    public boolean canAcceptPushedFluid(EntityMinecart requester, Fluid fluid) {
        return canFill(ForgeDirection.UNKNOWN,fluid);
    }

    @Override
    public boolean canProvidePulledFluid(EntityMinecart requester, Fluid fluid) {
        return canDrain(ForgeDirection.UNKNOWN,fluid);
    }

    @Override
    public void setFilling(boolean filling) {

    }

    @Override
    public boolean isEntityEqual(Entity p_70028_1_) {
        return this == p_70028_1_;
    }
}
