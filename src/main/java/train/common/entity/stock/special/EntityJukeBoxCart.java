package train.common.entity.stock.special;

import fexcraft.tmt.slim.ModelBase;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ebf.tim.api.SkinRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import train.common.Traincraft;
import train.common.adminbook.ServerLogger;
import train.common.api.*;
import train.common.core.util.MP3Player;
import train.common.items.ItemRollingStock;
import train.common.library.Info;
import train.common.library.ItemIDs;
import train.common.library.GuiIDs;

import java.util.Locale;

public class EntityJukeBoxCart extends EntityRollingStock {

    public boolean isPlaying = false;
    public boolean isInvalid = false;
    public String streamURL = "";
    private Side side;
    public float volume = 1.0f;
    public MP3Player player;

    public static final Item thisItem = new ItemRollingStock(new EntityJukeBoxCart(null), Info.modID+":+train_jukebox", Traincraft.tcTab); 

    public EntityJukeBoxCart(World world) {
        super(world);
        if (world != null) {
            dataWatcher.addObject(22, streamURL);
            dataWatcher.addObject(23, 0);
        }
        side = FMLCommonHandler.instance().getEffectiveSide();
    }
    //main stats
    @Override
    public String transportName(){return "JukeBox Cart";}
    @Override
    public String transportcountry(){return "";}
    @Override
    public String transportYear(){return "";}
    @Override
    public boolean isFictional(){return false;}

    @Override
    public void registerSkins(){
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/jukebox.png" , new String[]{} ,"default", "");
    }

    @Override
    public String getDefaultSkin(){return "default";}

    @Override
    public float transportTopSpeed(){return 0;}

    @Override
    public int getInventoryRows(){return 0;}

	@Override
	public float getPlayerScale(){ 	return 0.65f;}

	@Override
	public float transportMetricHorsePower(){return 0;}

	@Override
	public String[] additionalItemText() { return new String[] {"Supports MP3/OGG format M3U/PLS links, like Shoutcast"};}

	@Override
	public float weightKg(){ return 400.0f;}

	@Override
	public float[] rotationPoints(){ return new float[]{1.85f, -1.85f};}

    @Override
    public ItemStack[] getRecipe() {
        return new ItemStack[]{
                new ItemStack(Items.IRON_INGOT, 5),
new ItemStack(ItemIDs.bogie.item, 2), 
new ItemStack(ItemIDs.steelframe.item, 2), 
new ItemStack(Items.IRON_INGOT, 2),
null,
 null, 
null, 
null, 
new ItemStack(Blocks.jukebox, 1),
new ItemStack(thisItem)
        };
    }


    @Override
    public Item getItem(){return thisItem;}
    @Override
    public int getTier(){
return 3;
}
    //Model stuff
    @Override
    public ModelBase[] getModel(){return new ModelBase[]{new train.client.render.models.ModelJukeBox()};}
    @Override
    public float[][] modelOffsets(){return new float[][]{{0.0f, -0.08000001f, 0.0f}};}
@Override
    public float[][] modelRotations(){return new float[][]{{0f,180f,180f}};}
    @Override
    public float[][] getRenderScale(){return new float[][]{null};}
    //these are separated for being fiddly.
    @Override
    public float[][] getRiderOffsets(){return new float[][]{{0,1.2f, 0f}};}
    @Override
    public float[] getHitboxSize(){return new float[]{3.700000047683716f,2.1f,1.1f};}
    public TrainParticle[] getEffects(){
    return new TrainParticle[]{
    };
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (worldObj != null && worldObj.isRemote) return true;
        if (canBeDestroyedByPlayer(source)) return true;
        super.attackEntityFrom(source, amount);
        setRollingDirection(-getRollingDirection());
        setRollingAmplitude(10);
        setBeenAttacked();
        setDamage(getDamage() + amount * 10);
        if (getDamage() > 40) {
            if (riddenByEntity != null) riddenByEntity.mountEntity(this);
            setDead();
            ServerLogger.deleteWagon(this);
            if (source.getEntity() instanceof EntityPlayer) {
                dropCartAsItem(((EntityPlayer) source.getEntity()).capabilities.isCreativeMode);
            }
        }
        return true;
    }

    @Override
    public void setDead() {
        stopStream();
        super.setDead();
        isDead = true;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (worldObj == null) return;
        if (!worldObj.isRemote && ticksExisted % 10 == 0) {
            dataWatcher.updateObject(22, streamURL);
            dataWatcher.updateObject(23, isPlaying ? 1 : 0);
        }
        if (side == Side.CLIENT) updateClientPlayback();
    }

    @SideOnly(Side.CLIENT)
    private void updateClientPlayback() {
        if (ticksExisted % 10 == 0 && !isPlaying() && dataWatcher.getWatchableObjectInt(23) != 0) {
            streamURL = dataWatcher.getWatchableObjectString(22);
            startStream();
        }
        if (Minecraft.getMinecraft().thePlayer != null && player != null && !isInvalid) {
            float distance = (float) getDistanceSq(Minecraft.getMinecraft().thePlayer.posX,
                    Minecraft.getMinecraft().thePlayer.posY, Minecraft.getMinecraft().thePlayer.posZ);
            if (distance >= volume * 1000.0F) {
                player.setVolume(0.0F);
            } else if (distance > 0) {
                float attenuated = 100.0F / distance;
                player.setVolume(Math.min(volume, attenuated));
            }
            if (distance == 0) invalidate();
            if (isPlaying && rand.nextInt(5) == 0 && player.isPlaying()) {
                int note = rand.nextInt(24) + 1;
                worldObj.spawnParticle("note", posX, posY + 1.2D, posZ, note / 24.0D, 0.0D, 0.0D);
            }
        }
    }

    public void recievePacket(String url, boolean playing) {
        streamURL = url;
        isPlaying = playing;
    }

    @SideOnly(Side.CLIENT)
    public void invalidate() {
        isInvalid = true;
        stopStream();
    }

    public void startStream() {
        if (!isPlaying) {
            isPlaying = true;
            if (side == Side.CLIENT) {
                player = new MP3Player(streamURL, worldObj, getEntityId());
                player.setVolume(0);
                Traincraft.proxy.playerList.add(player);
            }
        }
    }

    public void stopStream() {
        if (isPlaying) {
            isPlaying = false;
            if (side == Side.CLIENT && player != null) {
                player.stop();
                Traincraft.proxy.playerList.remove(player);
                player = null;
            }
        }
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    @Override
    public boolean interactFirst(EntityPlayer player) {
        playerEntity = player;
        if (super.interactFirst(player)) return false;
        if (locked && !player.getDisplayName().toLowerCase(Locale.ROOT).equals(trainOwner.toLowerCase(Locale.ROOT))) {
            if (!worldObj.isRemote) ebf.tim.utility.CommonUtil.sendChat(player, "this train is locked");
            return true;
        }
        player.openGui(Traincraft.instance, GuiIDs.JUKEBOX, worldObj, getEntityId(), -1, (int) posZ);
        return true;
    }

    @Override
    public boolean isStorageCart() {
        return false;
    }

    @Override
    public boolean isPoweredCart() {
        return false;
    }

    @Override
    public float getOptimalDistance(EntityMinecart cart) {
        return 1.85F;
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);
        tag.setString("StreamUrl", streamURL);
        tag.setBoolean("isPlaying", isPlaying);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound tag) {
        super.readEntityFromNBT(tag);
        streamURL = tag.getString("StreamUrl");
        isPlaying = tag.getBoolean("isPlaying");
        if (worldObj != null) {
            dataWatcher.updateObject(22, streamURL);
            dataWatcher.updateObject(23, isPlaying ? 1 : 0);
        }
    }
}


    //these only change in very specific use cases.
