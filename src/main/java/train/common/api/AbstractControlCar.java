package train.common.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import org.lwjgl.input.Keyboard;
import train.common.Traincraft;
import train.common.core.handlers.ConfigHandler;
import train.common.core.network.PacketKeyPress;
import train.common.enums.DataMemberName;
import train.common.library.EnumSounds;
import train.common.library.GuiIDs;
import train.common.library.Info;

import java.util.List;

public abstract class AbstractControlCar extends EntityRollingStock implements IInventory, IPassenger, IRollingStockLightControls
{
    public Locomotive connectedLocomotive;
    public int whistleDelay;

    //region inventory
    public int inventorySize;
    private int slotsFilled=0;
    protected ItemStack controlCarInventory[];
    public int numCargoSlots;
    public int numCargoSlots1;
    public int numCargoSlots2;
    //endregion inventory

    private boolean forwardPressed = false;
    private boolean backwardPressed = false;
    private boolean brakePressed = false;

    public boolean isLightsEnabled = false;
    public boolean isBeaconEnabled = false;
    public byte beaconCycleIndex = 0;
    public byte ditchLightMode = 0;

    public AbstractControlCar(World world)
    {
        super(world);
        numCargoSlots = 3;
        numCargoSlots1 = 3;
        numCargoSlots2 = 3;
        inventorySize = numCargoSlots + numCargoSlots2 + numCargoSlots1 + 1;
        controlCarInventory = new ItemStack[inventorySize];
        dataWatcher.addObject(28, lightingDetailsJSONString());
        if (connectedLocomotive == null)
        {
            dataWatcher.addObject(29, 0);
        }
        else
        {
            dataWatcher.addObject(29, connectedLocomotive.getEntityId());
        }
    }

    public AbstractControlCar(World world, double d, double d1, double d2)
    {
        super(world);
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        writeInventory(nbttagcompound);

        nbttagcompound.setString("lightingDetailsJSON", lightingDetailsJSONString());
    }

    private void writeInventory(NBTTagCompound nbttagcompound)
    {
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < controlCarInventory.length; i++)
        {
            if (controlCarInventory[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte) i);
                controlCarInventory[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }
        nbttagcompound.setTag("Items", nbttaglist);
    }



    @Override
    protected void readEntityFromNBT(NBTTagCompound ntc)
    {
        super.readEntityFromNBT(ntc);
        readInventory(ntc);

        JsonObject lightingDetailsJSONObject;
        try
        {
            lightingDetailsJSONObject = new JsonParser().parse(ntc.getString("lightingDetailsJSON")).getAsJsonObject();
        }
        catch (Exception e)
        {
            lightingDetailsJSONObject = lightingDetailsAsJSON();
        }

        isLightsEnabled = lightingDetailsJSONObject.get(DataMemberName.isLightsEnabled.AsString()).getAsBoolean();
        isBeaconEnabled = lightingDetailsJSONObject.get(DataMemberName.isBeaconEnabled.AsString()).getAsBoolean();
        ditchLightMode = lightingDetailsJSONObject.get(DataMemberName.ditchLightMode.AsString()).getAsByte();
        beaconCycleIndex = lightingDetailsJSONObject.get(DataMemberName.beaconCycleIndex.AsString()).getAsByte();
        dataWatcher.updateObject(28, lightingDetailsJSONString());
    }

    private void readInventory(NBTTagCompound ntc)
    {
        NBTTagList nbttaglist = ntc.getTagList("Items", Constants.NBT.TAG_COMPOUND);
        controlCarInventory = new ItemStack[getSizeInventory()];
        for (int i = 0; i < nbttaglist.tagCount(); i++)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 0xff;
            if (j >= 0 && j < controlCarInventory.length)
            {
                controlCarInventory[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
    }

    private void cycleBeaconIndex()
    {
        if (isBeaconEnabled && ticksExisted % 5 == 0)
        {
            beaconCycleIndex++;
            if (beaconCycleIndex == 4)
            {
                beaconCycleIndex = 0;
            }
        }
    }

    @Override
    public void onUpdate()
    {
        cycleBeaconIndex();

        if (worldObj.isRemote == false)
        {
            //Server side stuff.
            if (cartLinked1 != null)
            {
                if ((cartLinked1).train != null && (cartLinked1).train.getTrains().size() != 0)
                {
                    for (int j1 = 0; j1 < (cartLinked1).train.getTrains().size(); j1++)
                    {
                        AbstractTrains daRollingStock = (cartLinked1).train.getTrains().get(j1);
                        if (daRollingStock instanceof Locomotive)
                        {
                            connectedLocomotive = (Locomotive) daRollingStock;
                            break;
                        }
                    }
                }
            }
            else
            {
                connectedLocomotive = null;
            }

            dataWatcher.updateObject(29, connectedLocomotive == null ? 0 : connectedLocomotive.getEntityId());
        }
        else
        {
            //Client side stuff.
        }
        handleTrainMovement();
        if (whistleDelay > 0)
        {
            whistleDelay--;
        }

        super.onUpdate();
        if (!worldObj.isRemote)
        {
            dataWatcher.updateObject(28, lightingDetailsJSONString());
        }
    }

    public void soundHorn()
    {
        for (EnumSounds sounds : EnumSounds.values())
        {
            if (sounds.getEntityClass() != null && sounds.getEntityClass().equals(this.getClass()) && whistleDelay == 0)
            {
                worldObj.playSoundAtEntity(this, Info.resourceLocation + ":" + sounds.getHornString(), sounds.getHornVolume(), 1.0F);
                whistleDelay = 65;
            }
        }
        List entities = worldObj.getEntitiesWithinAABB(EntityAnimal.class, AxisAlignedBB.getBoundingBox(
                this.posX-20,this.posY-5,this.posZ-20,
                this.posX+20,this.posY+5,this.posZ+20));

        for(Object e : entities)
        {
            if(e instanceof EntityAnimal)
            {
                ((EntityAnimal) e).setTarget(this);
                ((EntityAnimal) e).getNavigator().setPath(null, 0);
            }
        }
    }
    @Override
    public void updateRiderPosition()
    {
        if(riddenByEntity!=null)
        {
            riddenByEntity.setPosition(posX, posY + getMountedYOffset() + riddenByEntity.getYOffset() + 0.2, posZ);
        }
    }

    @Override
    public void pressKey(int i)
    {
        if (i == 7 && riddenByEntity != null && riddenByEntity instanceof EntityPlayer)
        {
            ((EntityPlayer) riddenByEntity).openGui(Traincraft.instance, GuiIDs.CONTROL_CAR, worldObj, (int) this.posX, (int) this.posY, (int) this.posZ);
        }
    }

    @Override
    public void setDead()
    {
        super.setDead();
        isDead = true;
    }

    public boolean isNotOwner() {
        if (this.riddenByEntity instanceof EntityPlayer && !((EntityPlayer) this.riddenByEntity).getDisplayName().equalsIgnoreCase(this.getTrainOwner())) {
            return true;
        }
        if (this.seats.size() > 0 && this.seats.get(0).getPassenger() instanceof EntityPlayer && !((EntityPlayer) this.seats.get(0).getPassenger()).getDisplayName().equalsIgnoreCase(this.getTrainOwner())) {
            return true;
        }
        return false;
    }


    //I'm moving this to a separate function because it's really, really thick, and I want to try to make things look neater.
    public void handleTrainMovement()
    {
        if (worldObj.isRemote)
        {
            if (ticksExisted % 2 == 0 && !Minecraft.getMinecraft().ingameGUI.getChatGUI().getChatOpen())
            {

                if (Keyboard.isKeyDown(FMLClientHandler.instance().getClient().gameSettings.keyBindForward.getKeyCode())
                        && !forwardPressed)
                {
                    Traincraft.keyChannel.sendToServer(new PacketKeyPress(4));
                    forwardPressed = true;
                }
                else if (!Keyboard
                        .isKeyDown(FMLClientHandler.instance().getClient().gameSettings.keyBindForward.getKeyCode())
                        && forwardPressed)
                {
                    Traincraft.keyChannel.sendToServer(new PacketKeyPress(13));
                    forwardPressed = false;
                }

                if (Keyboard.isKeyDown(FMLClientHandler.instance().getClient().gameSettings.keyBindBack.getKeyCode())
                        && !backwardPressed)
                {
                    Traincraft.keyChannel.sendToServer(new PacketKeyPress(5));
                    backwardPressed = true;
                }
                else if (!Keyboard
                        .isKeyDown(FMLClientHandler.instance().getClient().gameSettings.keyBindBack.getKeyCode())
                        && backwardPressed)
                {
                    Traincraft.keyChannel.sendToServer(new PacketKeyPress(14));
                    backwardPressed = false;
                }

                if (Keyboard.isKeyDown(FMLClientHandler.instance().getClient().gameSettings.keyBindJump.getKeyCode())
                        && !brakePressed)
                {
                    Traincraft.keyChannel.sendToServer(new PacketKeyPress(12));
                    brakePressed = true;
                }
                else if (!Keyboard
                        .isKeyDown(FMLClientHandler.instance().getClient().gameSettings.keyBindJump.getKeyCode())
                        && brakePressed)
                {
                    Traincraft.keyChannel.sendToServer(new PacketKeyPress(15));
                    brakePressed = false;
                }
            }
        }
        else
        {
            //Server stuff.
            if (connectedLocomotive != null)
            {
                if (forwardPressed || backwardPressed)
                {
                    if (connectedLocomotive.getFuel() > 0 && connectedLocomotive.isLocoTurnedOn() && rand.nextInt(4) == 0 && !worldObj.isRemote)
                    {
                        if (this.getTrainLockedFromPacket() && !((EntityPlayer) this.riddenByEntity).getDisplayName()
                                .toLowerCase().equals(this.getTrainOwner().toLowerCase()))
                        {
                            return;
                        }
                        if (riddenByEntity != null && riddenByEntity instanceof EntityPlayer)
                        {
                            int dir = MathHelper
                                    .floor_double((((EntityPlayer) riddenByEntity).rotationYaw * 4F) / 360F + 0.5D) & 3;
                            //System.out.println(dir);
                            if (dir == 2)
                            {
                                if (forwardPressed)
                                {
                                    connectedLocomotive.motionZ -= 0.0075 * this.accelerate;
                                    //System.out.println("A");
                                }
                                else
                                {
                                    connectedLocomotive.motionZ += 0.0075 * this.accelerate;
                                    //System.out.println("B");
                                }
                            }
                            else if (dir == 0)
                            {
                                if (forwardPressed)
                                {
                                    connectedLocomotive.motionZ += 0.0075 * this.accelerate;
                                    //System.out.println("C");
                                }
                                else
                                {
                                    connectedLocomotive.motionZ -= 0.0075 * this.accelerate;
                                    //System.out.println("D");
                                }
                            }
                            else if (dir == 1)
                            {
                                if (forwardPressed)
                                {
                                    connectedLocomotive.motionX -= 0.0075 * this.accelerate;
                                    //System.out.println("E");
                                }
                                else
                                {
                                    connectedLocomotive.motionX += 0.0075 * this.accelerate;
                                    //System.out.println("F");
                                }
                            }
                            else
                                {
                                if (forwardPressed)
                                {
                                    connectedLocomotive.motionX += 0.0075 * this.accelerate;
                                    //System.out.println("G");
                                }
                                else
                                {
                                    connectedLocomotive.motionX -= 0.0075 * this.accelerate;
                                    //System.out.println("H");
                                }
                            }
                        }
                    }
                }
                else if (brakePressed)
                {
                    connectedLocomotive.motionX *= brake;
                    connectedLocomotive.motionZ *= brake;
                }
            }
        }
    }

    @Override
    public void keyHandlerFromPacket(int i)
    {
        if (this.getTrainLockedFromPacket())
        {
            if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer
                    && !((EntityPlayer) this.riddenByEntity).getDisplayName().toLowerCase()
                    .equals(this.getTrainOwner().toLowerCase()))
            {
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

    public String lightingDetailsJSONString()
    {
        JsonObject lightingDetailsJSONString = new JsonObject();
        lightingDetailsJSONString.addProperty(DataMemberName.isLightsEnabled.AsString(), isLightsEnabled);
        lightingDetailsJSONString.addProperty(DataMemberName.isBeaconEnabled.AsString(), isBeaconEnabled);
        lightingDetailsJSONString.addProperty(DataMemberName.beaconCycleIndex.AsString(), beaconCycleIndex);
        lightingDetailsJSONString.addProperty(DataMemberName.ditchLightMode.AsString(), ditchLightMode);
        return lightingDetailsJSONString.toString();
    }

    public JsonObject lightingDetailsAsJSON()
    {
        JsonObject lightingDetailsJSON = new JsonObject();
        lightingDetailsJSON.addProperty(DataMemberName.isLightsEnabled.AsString(), isLightsEnabled);
        lightingDetailsJSON.addProperty(DataMemberName.isBeaconEnabled.AsString(), isBeaconEnabled);
        lightingDetailsJSON.addProperty(DataMemberName.beaconCycleIndex.AsString(), beaconCycleIndex);
        lightingDetailsJSON.addProperty(DataMemberName.ditchLightMode.AsString(), ditchLightMode);
        return lightingDetailsJSON;
    }

    /**
     *
     * @param isLightsOn set 0 if lights is false, 1 if true
     */
    public void setPacketLights(boolean isLightsOn)
    {
        isLightsEnabled = isLightsOn;
    }

    /**
     *
     * @param isBeaconOn set 0 if beacon is false, 1 if true
     */
    public void setPacketBeacon(boolean isBeaconOn)
    {
        isBeaconEnabled = isBeaconOn;
    }

    /**Sets the Ditch light mode
     *
     * @param ditchLightMode set 0 for off,
     */
    public void setPacketDitchLightsMode(byte ditchLightMode)
    {
        this.ditchLightMode = ditchLightMode;
    }

    public int getLocomotiveBeingControlledEntityID()
    {
        return dataWatcher.getWatchableObjectInt(29);
    }

    public boolean isLightsEnabled()
    {
        return AsJsonObject(dataWatcher.getWatchableObjectString(28)).get(DataMemberName.isLightsEnabled.AsString()).getAsBoolean();
    }

    public boolean isBeaconEnabled()
    {
        return AsJsonObject(dataWatcher.getWatchableObjectString(28)).get(DataMemberName.isBeaconEnabled.AsString()).getAsBoolean();
    }

    public byte getBeaconCycleIndex()
    {
        return AsJsonObject(dataWatcher.getWatchableObjectString(28)).get(DataMemberName.beaconCycleIndex.AsString()).getAsByte();
    }

    public boolean isDitchLightsEnabled()
    {
        return AsJsonObject(dataWatcher.getWatchableObjectString(28)).get(DataMemberName.ditchLightMode.AsString()).getAsByte() > 0;
    }

    private JsonObject AsJsonObject(String string)
    {
        return new JsonParser().parse(string).getAsJsonObject();
    }

    @Override
    public boolean isPoweredCart() {
        return false;
    }


    //region Implement IInventory
    @Override
    public int getSizeInventory()
    {
        return inventorySize;
    }

    @Override
    public void markDirty() { }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
        return false;
    }

    @Override
    public void openInventory() {
    }

    @Override
    public void closeInventory() {
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public ItemStack[] getInventory() {
        return controlCarInventory;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return controlCarInventory[i];
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int par1) {
        if (this.controlCarInventory[par1] != null) {
            ItemStack var2 = this.controlCarInventory[par1];
            this.controlCarInventory[par1] = null;
            return var2;
        } else {
            return null;
        }
    }

    @Override
    public ItemStack decrStackSize(int i, int j) {
        if (controlCarInventory[i] != null) {
            if (controlCarInventory[i].stackSize <= j) {
                ItemStack itemstack = controlCarInventory[i];
                controlCarInventory[i] = null;
                return itemstack;
            }
            ItemStack itemstack1 = controlCarInventory[i].splitStack(j);
            if (controlCarInventory[i].stackSize == 0) {
                controlCarInventory[i] = null;
            }
            return itemstack1;

        } else {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack) {
        controlCarInventory[i] = itemstack;
        if (itemstack != null && itemstack.stackSize > getInventoryStackLimit())
        {
            itemstack.stackSize = getInventoryStackLimit();
        }
    }

    //endregion Implement IInventory
}
