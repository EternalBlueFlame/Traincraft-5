package train.common.api;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;
import train.common.Traincraft;
import train.common.adminbook.ServerLogger;
import train.common.core.handlers.ConfigHandler;
import train.common.core.network.PacketKeyPress;
import train.common.core.network.PacketSlotsFilled;
import train.common.library.EnumSounds;
import train.common.library.Info;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public abstract class ControlCar extends EntityRollingStock implements IInventory {
    public Locomotive drivingLoco;
    private boolean forwardPressed = false;
    private boolean backwardPressed = false;
    private boolean brakePressed = false;
    public int dlEntityID = 0;
    private int whistleDelay = 0;
    private TrainsOnClick trainsOnClick = new TrainsOnClick();
    public int numCargoSlots = 5;
    public int numCargoSlots1 = 5;
    public int numCargoSlots2 = 20;
    public int inventorySize;
    protected ItemStack locoInvent[];
    private int slotsFilled = 0;

    public ControlCar(World world) {
        super(world);
        inventorySize = numCargoSlots + numCargoSlots2 + numCargoSlots1;
        dataWatcher.addObject(3, destination);
        dataWatcher.addObject(31, dlEntityID);


    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setString("destination", destination);
        nbttagcompound.setInteger("dlEntityID", dlEntityID);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
        super.readEntityFromNBT(nbttagcompound);
        destination = nbttagcompound.getString("destination");
        dlEntityID = nbttagcompound.getInteger("dlEntityID");
    }

    public String getDestinationGUI() {
        if (worldObj.isRemote) { return (this.dataWatcher.getWatchableObjectString(3)); }
        return destination;
    }
    @SuppressWarnings("Duplicates")
    public void onUpdate() {

        if (worldObj.isRemote && ticksExisted % 2 == 0 && !Minecraft.getMinecraft().ingameGUI.getChatGUI().getChatOpen()) {

            if (Keyboard.isKeyDown(FMLClientHandler.instance().getClient().gameSettings.keyBindForward.getKeyCode())
                    && !forwardPressed) {
                Traincraft.keyChannel.sendToServer(new PacketKeyPress(4));
                forwardPressed = true;
            } else if (!Keyboard
                    .isKeyDown(FMLClientHandler.instance().getClient().gameSettings.keyBindForward.getKeyCode())
                    && forwardPressed) {
                Traincraft.keyChannel.sendToServer(new PacketKeyPress(13));
                forwardPressed = false;
            }
            if (Keyboard.isKeyDown(FMLClientHandler.instance().getClient().gameSettings.keyBindBack.getKeyCode())
                    && !backwardPressed) {
                Traincraft.keyChannel.sendToServer(new PacketKeyPress(5));
                backwardPressed = true;
            } else if (!Keyboard
                    .isKeyDown(FMLClientHandler.instance().getClient().gameSettings.keyBindBack.getKeyCode())
                    && backwardPressed) {
                Traincraft.keyChannel.sendToServer(new PacketKeyPress(14));
                backwardPressed = false;
            }
            if (Keyboard.isKeyDown(FMLClientHandler.instance().getClient().gameSettings.keyBindJump.getKeyCode())
                    && !brakePressed) {
                Traincraft.keyChannel.sendToServer(new PacketKeyPress(12));
                brakePressed = true;
            } else if (!Keyboard
                    .isKeyDown(FMLClientHandler.instance().getClient().gameSettings.keyBindJump.getKeyCode())
                    && brakePressed) {
                Traincraft.keyChannel.sendToServer(new PacketKeyPress(15));
                brakePressed = false;
            }

        }
        if (drivingLoco != null) {
            if (forwardPressed || backwardPressed) {

                if (drivingLoco.getFuel() > 0 && drivingLoco.isLocoTurnedOn() && rand.nextInt(4) == 0 && !worldObj.isRemote) {
                    if (this.getTrainLockedFromPacket() && !((EntityPlayer) this.riddenByEntity).getDisplayName()
                            .toLowerCase().equals(this.getTrainOwner().toLowerCase())) {
                        return;
                    }
                    if (riddenByEntity != null && riddenByEntity instanceof EntityPlayer) {
                        int dir = MathHelper
                                .floor_double((((EntityPlayer) riddenByEntity).rotationYaw * 4F) / 360F + 0.5D) & 3;
                        if (dir == 2) {
                            if (forwardPressed) {
                                drivingLoco.motionZ -= 0.0075 * this.accelerate;
                            } else {
                                drivingLoco.motionZ += 0.0075 * this.accelerate;
                            }
                        } else if (dir == 0) {
                            if (forwardPressed) {
                                drivingLoco.motionZ += 0.0075 * this.accelerate;
                            } else {
                                drivingLoco.motionZ -= 0.0075 * this.accelerate;
                            }
                        } else if (dir == 1) {
                            if (forwardPressed) {
                                drivingLoco.motionX -= 0.0075 * this.accelerate;
                            } else {
                                drivingLoco.motionX += 0.0075 * this.accelerate;
                            }
                        } else if (dir == 3) {
                            if (forwardPressed) {
                                drivingLoco.motionX += 0.0075 * this.accelerate;
                            } else {
                                drivingLoco.motionX -= 0.0075 * this.accelerate;
                            }
                        }
                    }
                }
            } else if (brakePressed) {
                drivingLoco.motionX *= brake;
                drivingLoco.motionZ *= brake;
            }
        }
        super.onUpdate();
        if (cartLinked1 != null) {
            if ((cartLinked1).train != null && (cartLinked1).train.getTrains().size() != 0) {
                for (int j1 = 0; j1 < (cartLinked1).train.getTrains().size(); j1++) {
                    EntityRollingStock daRollingStock = (cartLinked1).train.getTrains().get(j1);
                    if (daRollingStock instanceof Locomotive) {
                        drivingLoco = (Locomotive) daRollingStock;

                        break;
                    }
                }
            }

        }
        if (drivingLoco != null) {

            dlEntityID = drivingLoco.getEntityId();
            dataWatcher.updateObject(31, dlEntityID);
            //  System.out.println(dataWatcher.getWatchableObjectInt(31));
        }

        if (!worldObj.isRemote) {
            //  dataWatcher.updateObject(3, destination);
        }
        if (whistleDelay > 0) {
            whistleDelay--;
        }
        if (this.drivingLoco != null) {
            if (updateTicks % 200 == 0) {
                this.slotsFilled = 0;
                for (int i = 0; i < getSizeInventory(); i++) {
                    ItemStack itemstack = getStackInSlot(i);
                    if (itemstack != null) {
                        slotsFilled++;
                    }
                }

                Traincraft.slotschannel.sendToAllAround(new PacketSlotsFilled(this.drivingLoco, slotsFilled), new NetworkRegistry.TargetPoint(this.worldObj.provider.dimensionId, this.posX, this.posY, this.posZ, 150.0D));
            }
        }
    }
    @Override
    public boolean setDestination(ItemStack ticket) {
        if (ticket != null) {
            destination = getTicketDestination(ticket);
            return true;
        }
        return false;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void keyHandlerFromPacket(int i) {
        if (this.getTrainLockedFromPacket()) {
            if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer
                    && !((EntityPlayer) this.riddenByEntity).getDisplayName().toLowerCase()
                    .equals(this.getTrainOwner().toLowerCase())) {
                return;
            }
        }
        pressKey(i);
        if (i == 8 && ConfigHandler.SOUNDS) {
            soundHorn();
        }
        if (i == 4) {
            forwardPressed = true;
        }
        if (i == 5) {
            backwardPressed = true;
        }
        if (i == 12) {
            brakePressed = true;
        }
        if (i == 13) {
            forwardPressed = false;
        }
        if (i == 14) {
            backwardPressed = false;
        }
        if (i == 15) {
            brakePressed = false;
        }
    }

    public void soundHorn() {
        //g
        for (EnumSounds sounds : EnumSounds.values()) {
            if (sounds.getEntityClass() != null && sounds.getEntityClass().equals(this.getClass()) && whistleDelay == 0) {
                worldObj.playSoundAtEntity(this, Info.resourceLocation + ":" + sounds.getHornString(), sounds.getHornVolume(), 1.0F);
                whistleDelay = 65;
            }
        }
        List entities = worldObj.getEntitiesWithinAABB(EntityAnimal.class, AxisAlignedBB.getBoundingBox(
                this.posX-20,this.posY-5,this.posZ-20,
                this.posX+20,this.posY+5,this.posZ+20));

        for(Object e : entities) {
            if(e instanceof EntityAnimal) {
                ((EntityAnimal) e).setTarget(this);
                ((EntityAnimal) e).getNavigator().setPath(null, 0);
            }
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource damagesource, float i) {
        if (worldObj.isRemote) {
            return true;
        }
        if(canBeDestroyedByPlayer(damagesource))return true;
        super.attackEntityFrom(damagesource, i);
        setRollingDirection(-getRollingDirection());
        setRollingAmplitude(10);
        setBeenAttacked();
        setDamage(getDamage() + i * 10);
        if (getDamage() > 40) {
            if (riddenByEntity != null) {
                riddenByEntity.mountEntity(this);
            }
            this.setDead();
            ServerLogger.deleteWagon(this);
            if(damagesource.getEntity() instanceof EntityPlayer) {
                dropCartAsItem(((EntityPlayer)damagesource.getEntity()).capabilities.isCreativeMode);
            }
        }
        return true;
    }


    public int getAmmountOfCargo() {
        return slotsFilled;
    }

    public void recieveSlotsFilled(int amount) {
        this.slotsFilled = amount;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public ItemStack[] getInventory(){return locoInvent;}

    @Override
    public ItemStack getStackInSlot(int i) {
        return locoInvent[i];
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int par1) {
        if (this.locoInvent[par1] != null) {
            ItemStack var2 = this.locoInvent[par1];
            this.locoInvent[par1] = null;
            return var2;
        }
        else {
            return null;
        }
    }

    @Override
    public ItemStack decrStackSize(int i, int j) {
        if (locoInvent[i] != null) {
            if (locoInvent[i].stackSize <= j) {
                ItemStack itemstack = locoInvent[i];
                locoInvent[i] = null;
                return itemstack;
            }
            ItemStack itemstack1 = locoInvent[i].splitStack(j);
            if (locoInvent[i].stackSize == 0) {
                locoInvent[i] = null;
            }
            return itemstack1;

        }
        else {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack) {
        locoInvent[i] = itemstack;
        if (itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
            itemstack.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public void openInventory() {}

    @Override
    public void closeInventory() {}

    @Override
    public void markDirty() {
         if (this.drivingLoco != null ) {
        if (!worldObj.isRemote) {

            this.slotsFilled = 0;

            for (int i = 0; i < getSizeInventory(); i++) {

                ItemStack itemstack = getStackInSlot(i);

                if (itemstack != null) {

                    slotsFilled++;
                }
            }

            Traincraft.slotschannel.sendToAllAround(new PacketSlotsFilled(this.drivingLoco, slotsFilled), new NetworkRegistry.TargetPoint(this.worldObj.provider.dimensionId, this.posX, this.posY, this.posZ, 150.0D));
        }
    }
}

    }