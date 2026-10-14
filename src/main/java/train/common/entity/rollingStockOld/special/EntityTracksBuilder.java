package train.common.entity.rollingStockOld.special;

import train.common.core.util.StackUtil;

import net.minecraft.util.EnumFacing;

import net.minecraft.util.math.BlockPos;

import net.minecraft.util.EnumHand;

import net.minecraft.util.math.Vec3d;
import com.mojang.authlib.GameProfile;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import ebf.tim.utility.CommonUtil;
import mods.railcraft.api.items.ITrackItem;
import mods.railcraft.api.tracks.TrackToolsAPI;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.BlockRail;
import net.minecraft.block.BlockRailBase;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.world.BlockEvent;
import train.common.Traincraft;
import train.common.adminbook.ServerLogger;
import train.common.api.EntityRollingStock;
import train.common.api.Freight;
import train.common.blocks.BlockTCRail;
import train.common.blocks.BlockTCRailGag;
import train.common.core.handlers.FuelHandler;
import train.common.core.plugins.PluginRailcraft;
import train.common.items.ItemTCRail;
import train.common.library.GuiIDs;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EntityTracksBuilder extends EntityRollingStock implements IInventory {
	public ItemStack item;
	private ItemStack BuilderInvent[];

	public int numBuilderSlots;
	public int numBuilderSlots1;
	public int numBuilderSlots2;
	public int numBuilderSlots3;
	public int inventoryBuilderSize;

	public double pushX;
	public double pushZ;

	public int MoreBuilderInvent;

	public int trackfuel;

	public double currentHeight;
	public int plannedHeight;

	EntityPlayer playerEntity;
	/** Affected by the button */
	public boolean followTracks;
	/** stack representing the tracks in the inventory */
	private int maxFuel = 5000;
	private int miningTickCounter = 0;
	
	private int lastFace = -1;
	private boolean skipTick = true;
	private FakePlayer fakeplayer;

	private static final int slotId_Rail = 1;

	private static final int slotId_UnderBlock2 = 2;
	private static final int slotId_UnderBlock  = 3;

	private static final int slotId_UpperBlockRight  = 4;
	private static final int slotId_UpperBlockCenter = 5;
	private static final int slotId_UpperBlockLeft   = 6;

	private static final int slotId_WallBlock = 7;

	public EntityTracksBuilder(World world) {
		super(world);
		initBuilder();
		
		if(world instanceof WorldServer)
			fakeplayer=new FakePlayer((WorldServer) world, getOwner()!=null?getOwner():new GameProfile(UUID.nameUUIDFromBytes(trainOwner==null||trainOwner.length()<1?"[Traincraft]".getBytes(): trainOwner.getBytes()),trainOwner==null||trainOwner.length()<1?"[Traincraft]": trainOwner));
	}

	public void initBuilder() {
		numBuilderSlots = 8;
		numBuilderSlots1 = 8;
		numBuilderSlots2 = 8;
		numBuilderSlots3 = 8;
		MoreBuilderInvent = 20;
		inventoryBuilderSize = numBuilderSlots + numBuilderSlots2 + numBuilderSlots1 + numBuilderSlots3 + MoreBuilderInvent;
		BuilderInvent = new ItemStack[inventoryBuilderSize];
		trackfuel = 0;
		followTracks = true;
		getDataWatcher().addObject(24, fuelTrain);
		getDataWatcher().addObject(26, plannedHeight);
		getDataWatcher().addObject(27, 1);
		getDataWatcher().addObject(28, 0);
	}



	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void setDead() {
		super.setDead();
		isDead = true;
	}
	@Override
	public void onUpdate() {
		
			// Position update
		super.onUpdate();

		/* smoke+fuel */
		if (rand.nextInt(4) == 0) {
			Smoke();// creates smoke
		}
		
		//register current elevation = poY
		currentHeight = posY;
		if (world.isRemote)
			return;
		
		updateFuel();
		
		moveStacks();

		updatePushForces();
		
		int i = CommonUtil.floorDouble(posX);
		int j = CommonUtil.floorDouble(posY);
		int k = CommonUtil.floorDouble(posZ);
		
		if(this.skipTick) {
			this.skipTick = false;
			this.motionX = 0;
			this.motionZ = 0;
		}
		else if (canDigg()) {
			updateState(true);
			applyDragAndPushForces();
			this.digBuilder(i, j, k);
		}
		else {
			updateState(false);
			this.motionX = 0;
			this.motionZ = 0;
		}

		if(ticksExisted%4 == 0) {
			fillLinkedInventory();
		}
				
	}

	private void fillLinkedInventory() {
		if (getInventory() != null){
			Freight link;
			if(frontLink instanceof Freight && ((Freight) frontLink).getInventory() !=null) {
				link = (Freight) frontLink;

			} else if (backLink instanceof Freight && ((Freight) backLink).getInventory() !=null) {
				link = (Freight) backLink;
			} else {
				return;
			}
			for (int index = 1; index < 11; index++) {
				if (getInventory()[index] != null && getInventory()[index].getItem() != null && getInventory()[index].getCount() < getInventory()[index].getMaxStackSize()) {

					for (int f=0; f<link.getInventory().length;f++) {
						if (link.getInventory()[f] != null && link.getInventory()[f].getItem() == getInventory()[index].getItem()){
							link.decrStackSize(f,1);
							getInventory()[index].grow(1);
							break;
						}
					}
				}
			}
		}
	}

	private void updateFuel() {
		//manage fuel
		if (getFuel() < maxFuel) {
			if (BuilderInvent[0] != null) {
				if ((FuelHandler.steamFuelLast(BuilderInvent[0])>0) && getFuel() + 300 < maxFuel) {
					fuelTrain += 300;
					decrStackSize(0, 1);
				}
				if (BuilderInvent[0] != null && !PluginRailcraft.RailcraftParts.INGOT_STEEL.getStack().isEmpty() && BuilderInvent[0].isItemEqual(PluginRailcraft.RailcraftParts.INGOT_STEEL.getStack()) && getFuel() + 800 < maxFuel) {
					fuelTrain += 800;
					decrStackSize(0, 1);
				}
				getDataWatcher().updateObject(24, fuelTrain);
			}
		}
	}
		
	@Override
	public ItemStack[] getInventory(){return BuilderInvent;}

	private boolean canDigg() {
		return (checkForBallast() && checkForTracks() && getFuel() > 0);
	}
	@Override
	protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		super.writeEntityToNBT(nbttagcompound);
		nbttagcompound.setInteger("plannedHeight", plannedHeight);
		nbttagcompound.setBoolean("followTracks", followTracks);

		nbttagcompound.setDouble("PushX", pushX);
		nbttagcompound.setDouble("PushZ", pushZ);
		nbttagcompound.setShort("Trackfuel", (short) trackfuel);
		nbttagcompound.setShort("fuel", (short) getFuel());
		NBTTagList nbttaglist = new NBTTagList();
		for (int i = 0; i < BuilderInvent.length; i++) {
			if (BuilderInvent[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				BuilderInvent[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}
		nbttagcompound.setTag("Items", nbttaglist);
	}
	@Override
	protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		super.readEntityFromNBT(nbttagcompound);

		pushX = nbttagcompound.getDouble("PushX");
		pushZ = nbttagcompound.getDouble("PushZ");
		if (nbttagcompound.getInteger("plannedHeight") < 4) {
			plannedHeight = (int) currentHeight;
			setPlannedHeight(plannedHeight);
		}
		else {
			setPlannedHeight(nbttagcompound.getInteger("plannedHeight"));
		}
		followTracks = nbttagcompound.getBoolean("followTracks");
		trackfuel = nbttagcompound.getShort("Trackfuel");
		fuelTrain = nbttagcompound.getShort("fuel");
		NBTTagList nbttaglist = nbttagcompound.getTagList("Items", Constants.NBT.TAG_COMPOUND);
		BuilderInvent = new ItemStack[getSizeInventory()];
		for (int i = 0; i < nbttaglist.tagCount(); i++) {
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			int j = nbttagcompound1.getByte("Slot") & 0xff;
			if (j >= 0 && j < BuilderInvent.length) {
				BuilderInvent[j] = new ItemStack(nbttagcompound1);
			}
		}
	}

	@Override
	public int getSizeInventory() {
		return inventoryBuilderSize;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return StackUtil.nn(BuilderInvent[i]);
	}

	@Override
	public ItemStack removeStackFromSlot(int par1) {
		if (this.BuilderInvent[par1] != null) {
			ItemStack var2 = this.BuilderInvent[par1];
			this.BuilderInvent[par1] = null;
			return StackUtil.nn(var2);
		}
		else {
			return ItemStack.EMPTY;
		}
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if (BuilderInvent[i] != null) {
			if (BuilderInvent[i].getCount() <= j) {
				ItemStack itemstack = BuilderInvent[i];
				BuilderInvent[i] = null;
				return StackUtil.nn(itemstack);
			}
			ItemStack itemstack1 = BuilderInvent[i].splitStack(j);
			if (BuilderInvent[i].getCount() == 0) {
				BuilderInvent[i] = null;
			}
			return StackUtil.nn(itemstack1);
		} else {
			return ItemStack.EMPTY;
		}
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) { if (itemstack != null && itemstack.isEmpty()) itemstack = null;
		BuilderInvent[i] = itemstack;
		if (itemstack != null && itemstack.getCount() > getInventoryStackLimit()) {
			itemstack.setCount(getInventoryStackLimit());
		}
	}

	@Override
	public String getName() {
		return "Tracks Builder";
	}

	@Override
	public boolean processInitialInteract(EntityPlayer entityplayer, EnumHand hand) {
		playerEntity = entityplayer;
		if ((super.processInitialInteract(entityplayer, hand))) {
			return false;
		}
		if (world.isRemote) {
			return true;
		}

		entityplayer.openGui(Traincraft.instance, GuiIDs.BUILDER, getWorld(), this.getEntityId(), -1, (int) this.posZ);
		pushZ = (posZ - entityplayer.posZ);
		pushX = (posX - entityplayer.posX);
		applyDragAndPushForces();
		return true;
	}

	@Override
	public void openInventory(EntityPlayer p) {}

	@Override
	public void closeInventory(EntityPlayer p) {}

	public int scaleMaxFuel(int i) {
		return (this.getFuel() * i) / maxFuel;
	}

	// TODO: Convert to cleaner EntityRollingStock drag?
	@Deprecated
	protected void applyDragAndPushForces() {
		double d26 = MathHelper.sqrt(pushX * pushX + pushZ * pushZ);
		if (d26 > 0.01D) {
			pushX /= d26;
			pushZ /= d26;
			double d28 = 0.029D;
			motionX *= 0.4D;
			motionY *= 0.0D;
			motionZ *= 0.4D;
			if (getFuel() > 0) {
				motionX += pushX * d28;
				motionZ += pushZ * d28;
			}
		}
		else {
			motionX *= 0.69999997615814209D;
			motionY *= 0.0D;
			motionZ *= 0.69999997615814209D;
		}
		motionX *= 0.98d;
		motionY *= 0.0D;
		motionZ *= 0.98d;
	}

	protected void updatePushForces() {
		double push = MathHelper.sqrt(pushX * pushX + pushZ * pushZ);
		if (push > 0.01D && motionX * motionX + motionZ * motionZ > 0.001D) {
			pushX /= push;
			pushZ /= push;
			if (pushX * motionX + pushZ * motionZ < 0.0D) {
				pushX = 0.0D;
				pushZ = 0.0D;
			}
			else {
				pushX = motionX;
				pushZ = motionZ;
			}
		}
	}

	public boolean canInteractWith(EntityPlayer entityplayer) {
		return !isDead && entityplayer.getDistanceSq(this) <= 64D;
	}
	@Override
	public boolean isUsableByPlayer(EntityPlayer entityplayer) {
		return !isDead && entityplayer.getDistanceSq(this) <= 64D;
	}

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		float dist = 1.6F;
		return (dist + 0.5F);
	}

	@Override
	public boolean canBeAdjusted(EntityMinecart cart) {
		return false;
	}

	public void Smoke() {
		if (getFuel() > 0) {
			fuelTrain--;
			if (!world.isRemote) {
				getDataWatcher().updateObject(24, fuelTrain);
			}
		}
	}

	public int getFuel() {
		if (world.isRemote) {
			return (getDataWatcher().getWatchableObjectInt(24));
		}
		return fuelTrain;
	}

	private void updateState(boolean state) {
		if (state)
			getDataWatcher().updateObject(28, 1);
		if (!state)
			getDataWatcher().updateObject(28, 0);
	}

	//render-state
	public boolean getState() {
		return getDataWatcher().getWatchableObjectInt(28) == 1;
	}

	public void setPlannedHeightFromPacket(int packet) {
		int currentPlanned = getPlannedHeight();
		int request = packet + currentPlanned;
		if (request > 256) {
			request = 256;
		}
		if (request < 4) {
			request = 4;
		}
		setPlannedHeight(request);

	}

	public void setPlannedHeight(int plannedHeight) {
		//System.out.println("set watcher: "+ plannedHeight);
		getDataWatcher().updateObject(26, plannedHeight);
		//System.out.println("watcher: "+getDataWatcher().getWatchableObjectInt(26));
		this.plannedHeight = plannedHeight;
	}

	public int getPlannedHeight() {
		//System.out.println("get watcher: "+getDataWatcher().getWatchableObjectInt(26) + " "+ world.isRemote + " plannedHeight "+plannedHeight);
		return (getDataWatcher().getWatchableObjectInt(26));
	}

	public void setFollowTracksFromPacket(int packet) {
		setFollowTracks(packet);
	}

	public void setFollowTracks(int follow) {
		getDataWatcher().updateObject(27, follow);
		if (follow == 1)
			this.followTracks = true;
		if (follow == 0)
			this.followTracks = false;
	}

	public int getFollowTracks() {
		return (getDataWatcher().getWatchableObjectInt(27));
	}

	public void putInInvent(ItemStack itemdug) {
		boolean hasBeenPlaced = false;
		boolean noFreight = true;
		// ItemStack itemDug = (new ItemStack(blockNow, 1, 0));
		@SuppressWarnings("rawtypes") List lis = world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().expand(4.60000000298023224D, 4.60000000298023224D, 4.60000000298023224D));

		if (lis != null && lis.size() > 0) {
			for (int j1 = 0; j1 < lis.size(); j1++) {
				Entity entity = (Entity) lis.get(j1);
				if ((entity instanceof Freight) && (isAttached || isLinked()) && ((Freight) entity).cargoItems!=null && ((Freight) entity).cargoItems.length>0) {
					noFreight = false;
					for (int u = 0; u < ((Freight) entity).cargoItems.length; u++) {// checks the inventory
						ItemStack itemInside = ((IInventory) entity).getStackInSlot(u);
						if ((itemInside != null) && (((Freight) entity).cargoItems[u].getCount() != 0) && (((Freight) entity).cargoItems[u].getItem() == itemdug.getItem()) && (((Freight) entity).cargoItems[u].getItemDamage() == itemdug.getItemDamage()) && ((Freight) entity).cargoItems[u].getCount() != getInventoryStackLimit()) {// a stack is already in there and there is room
							((Freight) entity).cargoItems[u].grow(1);
							hasBeenPlaced = true;
							u = ((Freight) entity).cargoItems.length;
							j1 = lis.size();
						}
						else if (itemInside == null) {// slot is empty
							if (((Freight) entity).cargoItems[u] == null) {
								((Freight) entity).cargoItems[u] = itemdug;
								hasBeenPlaced = true;
								u = ((Freight) entity).cargoItems.length;
								j1 = lis.size();
							}
						}
						else if ((!hasBeenPlaced) && (u == ((Freight) entity).cargoItems.length)) {// inventory is full, drop stack
							noFreight = true;
						}
					}
				}
				else {
					noFreight = true;
				}
			}
		}
		else {
			noFreight = true;
		}
		if (noFreight) {
			for (int u = MoreBuilderInvent; u < BuilderInvent.length; u++) {// checks the inventory
				ItemStack itemInside = getStackInSlot(u);
				if ((itemInside != null) && (BuilderInvent[u].getCount() != 0) && (BuilderInvent[u].getItem() == itemdug.getItem()) && (BuilderInvent[u].getItemDamage() == itemdug.getItemDamage()) && BuilderInvent[u].getCount() != getInventoryStackLimit()) {// a stack is already in there and there is room
					BuilderInvent[u].grow(1);
					hasBeenPlaced = true;
					u = BuilderInvent.length;
				}
				else if (itemInside == null) {// slot is empty
					if (BuilderInvent[u] == null) {
						BuilderInvent[u] = itemdug;
						hasBeenPlaced = true;
						u = BuilderInvent.length;
					}
				}
				else if ((!hasBeenPlaced) && (u == BuilderInvent.length - 1)) {// inventory is full, drop stack
					// dropItemWithOffset(itemdug.itemID, 1, 1.0F);//has to be fixed
					entityDropItem(itemdug, 1.0F);
					u = BuilderInvent.length;
				}
			}
		}
	}

	/** can this block be used as ballast */
	public static boolean canBeBallast(ItemStack stack) {
		if(stack == null || stack.getItem() == null)
			return false;
		
			// allow gravel and sand ...
		if (stack.getItem() instanceof ItemBlock) {
			Block block = Block.getBlockFromItem(stack.getItem());
			if(block instanceof BlockFalling) {
				return true;
			}
		}

		return canBeTunnel(stack);
	}

	/** can this block be used for the tunnel */
	public static boolean canBeTunnel(ItemStack stack) {
		if (stack == null || stack.getItem() == null)
			return false;
		if (!(stack.getItem() instanceof ItemBlock))
			return false;
		if (stack.getItem()!= null) {
			Block block = Block.getBlockFromItem(stack.getItem());
			if (block.hasTileEntity(block.getStateFromMeta(stack.getItem().getMetadata(stack.getItemDamage()))))
				return false;
			if(block.getDefaultState().getRenderType()!=net.minecraft.util.EnumBlockRenderType.MODEL || !block.getDefaultState().isFullCube())
				return false;
			if(block instanceof BlockFalling)
				return false;
			return true;
		}
		return false;
	}

	private void moveStacks() {
		if (BuilderInvent[1] == null) {
			for (int i = 8; i <= 10; i++) {
				if (BuilderInvent[i] != null && (TrackToolsAPI.isTrackItem(BuilderInvent[i]) || Item.getIdFromItem(BuilderInvent[i].getItem()) == Block.getIdFromBlock(Blocks.RAIL) || Item.getIdFromItem(BuilderInvent[i].getItem()) == Block.getIdFromBlock(Block.getBlockFromName("railPowered")) || (Item.getIdFromItem(BuilderInvent[i].getItem()) == Block.getIdFromBlock(Block.getBlockFromName("railDetector.blockID"))))) {
					BuilderInvent[1] = BuilderInvent[i].copy();
					decrStackSize(i, 64);
					break;
				}
			}
		}
		if (BuilderInvent[3] == null) {
			for (int i = 11; i <= 13; i++) {
				if (this.canBeBallast(BuilderInvent[i])) {
					BuilderInvent[3] = BuilderInvent[i].copy();
					decrStackSize(i, 64);
					break;
				}
			}
		}
		if (BuilderInvent[2] == null) {
			for (int i = 14; i <= 16; i++) {
				if (this.canBeBallast(BuilderInvent[i])) {
					BuilderInvent[2] = BuilderInvent[i].copy();
					decrStackSize(i, 64);
					break;
				}
			}
		}
		if (BuilderInvent[7] == null) {
			for (int i = 17; i <= 19; i++) {
				if (this.canBeTunnel(BuilderInvent[i])) {
					BuilderInvent[7] = BuilderInvent[i].copy();
					decrStackSize(i, 64);
					break;
				}
			}
		}

	}
	
	/**
	 * Spawn breaking particles for blockparticles
	 *
	 * @param pos position
	 * @param block_index index of the block in mining list
	 */
	private void playMiningEffect(Vec3d pos, int block_index) {
		miningTickCounter++;

		if (!FMLCommonHandler.instance().getMinecraftServerInstance().isDedicatedServer() && pos != null && world !=null) {
			Block block = CommonUtil.getBlockAt(world, (int) pos.x, (int) pos.y, (int) pos.z);
			if (miningTickCounter % 8 == 0 && block != null && !world.isRemote && Minecraft.getMinecraft() != null) {
				CommonUtil.playSound(this.world, (int) pos.x + 0.5F, (int) pos.y + 0.5F, (int) pos.z + 0.5F, block.getSoundType().getBreakSound(), 1.0F, block.getSoundType().getPitch() * 0.5F, 0);
			}
			if (miningTickCounter % 8 == 0 && block_index != 0 && block != null && FMLClientHandler.instance().getClient() != null ) {
				FMLClientHandler.instance().getClient().effectRenderer.addBlockHitEffects(new BlockPos((int) pos.x, (int) pos.y, (int) pos.z), EnumFacing.byIndex(block_index < 4 ? getSideFromYaw() : (block_index < 6 ? 1 : 0)));
			}
		}
	}

	private int getSideFromYaw() {
		if (rotationYaw == 0) {
			return 5;
		}
		if (rotationYaw == 90) {
			return 3;
		}
		if (rotationYaw == 180) {
			return 4;
		}
		if (rotationYaw == 270) {
			return 2;
		}
		return 1;
	}

	private boolean checkForBallast() {

		if (BuilderInvent[3] != null && canBeBallast(BuilderInvent[3])) {
			return true;
		}
		return false;
	}

	private boolean checkForTracks() {

		if (BuilderInvent[1] != null) {
			trackfuel = 1;
			return true;
		}
		else {
			trackfuel = 0;
			return false;
		}
	}

	/**
	 * north = 0 west = 3 east = 1 south = 2
	 *
	 * @return orientation
	 */
	private int getFacing() {
		if (!world.isRemote) {
			if(d7 == 0. && d6 == 0.) {
				if(lastFace == -1) {
					//lastFace = ((int) Math.round(rotationYaw/90) + 5)%4;
				}
				return lastFace;
			}
			rotation = CommonUtil.atan2degreesf(d7,d6);
			lastFace = CommonUtil.floorDouble(rotation * 4.0F / 360.0F + 0.5D) & 3;
		}
		else {
			rotation = (CommonUtil.atan2degreesf(0 - motionX, 0 - motionZ));
		}
		return CommonUtil.floorDouble(rotation * 4.0F / 360.0F + 0.5D) & 3;
	}

	/** Compares the currentHeight with given height in GUI */
	private int checkForHeight() {
		if(BuilderInvent[slotId_Rail] !=null
				&& BuilderInvent[slotId_Rail].getItem() instanceof ItemTCRail){
			return 0;
		}else {
			return getPlannedHeight() - (int) currentHeight;
		}
	}

	@Override
	public boolean attackEntityFrom(DamageSource damagesource, float i) {
		if (world.isRemote) {
			return true;
		}
		if(canBeDestroyedByPlayer(damagesource))return true;
		super.attackEntityFrom(damagesource, i);
		setRollingDirection(-getRollingDirection());
		setRollingAmplitude(10);
		markVelocityChanged();
		setDamage(getDamage() + i * 10);
		if (getDamage() > 40) {
			if (ebf.tim.utility.CommonUtil.rider(this) != null) {
				this.removePassengers(); // 1.7.10 mountEntity(this) dismounted, 1.12 startRiding would mount
			}
			this.setDead();
			ServerLogger.deleteWagon(this);
			if(damagesource.damageType.equals("player")) {
				dropCartAsItem(damagesource.isCreativePlayer());
				for(ItemStack stack : BuilderInvent){
					if (stack != null) {
						entityDropItem(stack, 0);
					}
				}
			}
		}
		return true;
	}

	/**
	 * This handles the Builder's digging process
	 */
	private void digBuilder(int i, int j, int k) {
		if (world.isRemote) {
			return;
		}
		/** +1/-1 on X axis, used to know where to dig */
		int iX = 0;
		/** +1/-1 on Z axis, used to know where to dig */
		int kZ = 0;

		//checks the tracks in slot 1
		checkForTracks();

		//checks the height, if it is supposed to continue up/down/flat
		int hY = checkForHeight();

		if (trackfuel >= 1) {// is fueled

			int face = getFacing();

			// setup Face
			if (face == 1) {
				iX = 0;
				kZ = -1;
			}
			else if (face == 2) {
				iX = 1;
				kZ = 0;
			}
			else if (face == 3) {
				iX = 0;
				kZ = 1;
			}
			else if (face == 0) {
				iX = -1;
				kZ = 0;
			}
			else {
				return;
			}

			// Dig
			boolean ret = digAndPlace(i, j, k, iX, hY, kZ);
			
			if(!ret) {
				this.motionX = 0;
				this.motionY = 0;
				this.motionZ = 0;
			}
		}
			
	}

	private void harvestBlock(int i, int j, int k) {

		Block block =  CommonUtil.getBlockAt(world, i, j, k);

		int meta = CommonUtil.getBlockFacing(world, i, j, k);

		if (block.getDrops(getWorld(), new BlockPos(i, j, k), block.getStateFromMeta(meta), 0).size()>0) {

			ArrayList<ItemStack> stacks = new ArrayList<ItemStack>(block.getDrops(getWorld(), new BlockPos(i, j, k), block.getStateFromMeta(meta), 0));

			for (ItemStack s : stacks) {
				// we do not destroy rails
				//if( (BlockRailBase.func_150051_a(Block.getBlockFromItem(s.getItem()))))return;

				//if (Item.getIdFromItem(s.getItem()) != 0
				//		&& (s.getItem() != Item.getItemFromBlock(Block.getBlockFromName("glass")))) {
					//&& (Item.getIdFromItem(s.getItem())) != Item.getIdFromItem(tunnelBlockStack.getItem())) {

				//if ((Block.getIdFromBlock(world.getBlock(i, j, k)) != Item.getIdFromItem(tunnelBlockStack.getItem()))) {
					putInInvent(s);
				//}
				//}
			}
		}
		// mining effect
		if (!world.isRemote) {
			int id= Block.getIdFromBlock(block);
			this.playMiningEffect(new Vec3d(i, j, k), id);
			world.playEvent(2001, new BlockPos(i, j, k), id + (meta << 12));
		}

	}

	private boolean replaceBlockAt(int i, int j, int k, int inventoryId) {

		Block newblock;
		int newmeta = 0;

		boolean consumeBlock = false;

			// if inventorySlot is empty, we can return
		if(inventoryId > 0 && inventoryId < BuilderInvent.length) {
			 if(BuilderInvent[inventoryId] == null
					 || BuilderInvent[inventoryId].getItem() == null
					 || BuilderInvent[inventoryId].getCount() <= 0)
				 return true;

			 newblock = Block.getBlockFromItem(BuilderInvent[inventoryId].getItem());
			 newmeta = BuilderInvent[inventoryId].getItemDamage(); // TODO check

			 consumeBlock = true;

			 if(newblock == null)
				 return true;
		}else {
			newblock = Blocks.AIR;
		}


		Block block = CommonUtil.getBlockAt(world, i, j, k);
		int metadata = CommonUtil.getBlockFacing(world, i, j, k);

		if(block == newblock && metadata == newmeta)
			return true;

		BlockEvent.BreakEvent event = new BlockEvent.BreakEvent(getWorld(), new BlockPos(i, j, k), block.getStateFromMeta(metadata), this.fakeplayer);


		MinecraftForge.EVENT_BUS.post(event);

		if(!event.isCanceled()) {

			// get the drop from the previous block (and play animation)
			harvestBlock(i, j, k);

			// place new block
			CommonUtil.setBlock(world, i, j, k, newblock);
			CommonUtil.setBlockMeta(world, i, j, k, newmeta, 3);

			if(consumeBlock) {
				BuilderInvent[inventoryId].shrink(1); // ok ?

				if(BuilderInvent[inventoryId].getCount() == 0)
					BuilderInvent[inventoryId] = null;
			}

			return true;
		}else {
			return false;
		}
	}

	private boolean placeRailAt(int i, int j, int k) {

		int inventoryId = slotId_Rail;

		if(BuilderInvent[inventoryId] == null)
			return false;

		Block block = CommonUtil.getBlockAt(world, i, j, k);
		int metadata = CommonUtil.getBlockFacing(world, i, j, k);

			// check if we need to place rails and if we can
		if(BlockRailBase.isRailBlock((block).getDefaultState())
				|| block instanceof BlockTCRail
				|| block instanceof BlockTCRailGag) {
			return true;
		}

		BlockEvent.BreakEvent event = new BlockEvent.BreakEvent(getWorld(), new BlockPos(i, j, k), block.getStateFromMeta(metadata), this.fakeplayer);


		MinecraftForge.EVENT_BUS.post(event);

		if(!event.isCanceled()) {

				// get the drop from the previous block (and play animation)
			harvestBlock(i, j, k);

				// place new block
			world.setBlockToAir(new BlockPos(i, j, k));
			boolean success = placeRailAt(this, BuilderInvent[inventoryId], getWorld(), i, j, k);

			if(success) {
				BuilderInvent[inventoryId].shrink(1); // ok ?

				if(BuilderInvent[inventoryId].getCount() == 0)
					BuilderInvent[inventoryId] = null;

				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}

	private boolean digAndPlace(int i, int j, int k, int iX, int hY, int kZ) {

		int cX = kZ;
		int cZ = iX;

			// rail position
		int u = i + iX;
		int v = j;
		int w = k + kZ;

		if(hY < 0) {
			Block block = CommonUtil.getBlockAt(world, u - iX, v - 1, w - kZ);
			if(BlockRailBase.isRailBlock((block).getDefaultState()) || block instanceof BlockTCRail || block instanceof BlockTCRailGag) {


				v--;
				hY++;
			}
		}else if(hY > 0) {
			Block block = CommonUtil.getBlockAt(world, u - iX, v, w - kZ);
			if(BlockRailBase.isRailBlock((block).getDefaultState()) || block instanceof BlockTCRail || block instanceof BlockTCRailGag) {


				v++;
				hY--;
			}
		}

		if(hY != 0)
			hY/=Math.abs(hY);

		v+=hY;

		if(hY > 0) {
			u += iX;
			w += kZ;
		}

			// place underblock
		 boolean success = true;

			// place air blocks
		for(int d = -1; d < 2; d++) {
			for(int dy = 1; dy < 4 + Math.abs(hY); dy++) {
				success &= replaceBlockAt(u + d*cX, v + dy, w + d*cZ, -1);
			}
		}
		success &= replaceBlockAt(u-cX, v, w-cZ, -1);
		success &= replaceBlockAt(u+cX, v, w+cZ, -1);

			// place wall
		for(int h = 0; h < 4; h++) {
			success &= replaceBlockAt(u-2*cX, v + h, w-2*cZ, this.slotId_WallBlock);
			success &= replaceBlockAt(u+2*cX, v + h, w+2*cZ, this.slotId_WallBlock);
		}

			// place roof
		success &= replaceBlockAt(u - cX, v + 4, w - cZ, this.slotId_UpperBlockRight);
		success &= replaceBlockAt(u	 , v + 4, w, this.slotId_UpperBlockCenter);
		success &= replaceBlockAt(u + cX, v + 4, w + cZ, this.slotId_UpperBlockLeft);

			// place underblock2
		success &= replaceBlockAt(u, v - 2, w, this.slotId_UnderBlock2);
		success &= replaceBlockAt(u, v - 1, w, this.slotId_UnderBlock);

		// check if underBlock will fall before placing rail
		Block underBlock = CommonUtil.getBlockAt(world, u, v-1, w);

		if(underBlock instanceof BlockFalling && BlockFalling.canFallThrough(getWorld().getBlockState(new BlockPos(u, v-2, w)))) {
			this.skipTick = true;
			return false;
		}

			// place rails
		success &= placeRailAt(u, v, w);

		return success;
	}

	public static boolean placeRailAt(EntityTracksBuilder builder, ItemStack stack, World world, int i, int j, int k) {
		if (stack == null)
			return false;
		if (stack.getItem() instanceof ITrackItem)
			return ((ITrackItem) stack.getItem()).placeTrack(stack.copy(), null, world, new net.minecraft.util.math.BlockPos(i, j, k), null);
		if (stack.getItem() instanceof ItemBlock) {
			Block block = ((ItemBlock) stack.getItem()).getBlock();
			if (BlockRailBase.isRailBlock((block).getDefaultState())) {
				boolean success = CommonUtil.setBlock(world, i, j, k, block);
				if (success)
					world.playSound(null, (float) i + 0.5F, (float) j + 0.5F, (float) k + 0.5F, block.getSoundType().getPlaceSound(), net.minecraft.util.SoundCategory.BLOCKS, (block.getSoundType().getVolume() + 1.0F) / 2.0F, block.getSoundType().getPitch() * 0.8F);
				return success;
			}
		} if (stack.getItem() instanceof ItemTCRail){
			ItemTCRail rail = (ItemTCRail) stack.getItem();
			return rail.placeFromBuilder(stack, world, i, j, k, builder.rotationYaw+90);
		}
		return world instanceof net.minecraft.world.WorldServer && TrackToolsAPI.placeRailAt(stack, (net.minecraft.world.WorldServer) world, new net.minecraft.util.math.BlockPos(i, j, k));
	}

	@Override
	public void markDirty() {}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return true;
	}

	@Override
	public GameProfile getOwner(){return null;}

}