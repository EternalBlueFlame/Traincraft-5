package train.common.items;

import net.minecraft.util.math.BlockPos;

import net.minecraft.util.EnumHand;

import net.minecraft.util.EnumFacing;

import net.minecraft.util.EnumActionResult;

import net.minecraft.client.util.ITooltipFlag;

import com.mojang.authlib.GameProfile;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ebf.tim.api.SkinRegistry;
import ebf.tim.utility.CommonUtil;
import ebf.tim.utility.DebugUtil;
import mods.railcraft.api.carts.IMinecart;
import mods.railcraft.api.items.IMinecartItem;
import net.minecraft.block.BlockRailBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemMinecart;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import train.common.Traincraft;
import train.common.api.*;
import train.common.core.handlers.ConfigHandler;
import train.common.entity.rollingStockOld.special.EntityTracksBuilder;
import train.common.library.BlockIDs;
import train.common.library.EnumTracks;
import train.common.tile.TileTCRail;
import train.common.tile.TileTCRailGag;

import javax.annotation.Nullable;
import java.util.List;

public class ItemRollingStock extends ItemMinecart implements train.common.items.ILegacyIcon, IMinecart, IMinecartItem {

	private String iconName = "";
	private ResourceLocation itemTexture;
	private String trainCreator;

	public AbstractTrains entity=null;

	private AbstractTrains getEntity(){
		if(entity==null){
			train.common.api.TrainRecord record = Traincraft.instance.traincraftRegistry.findTrainRecordByItem(this);
			entity = record == null ? null : record.getEntity(null);
			if (entity != null && net.minecraftforge.fml.common.registry.EntityRegistry.getEntry(entity.getClass()) == null) {
				AbstractTrains registered = train.common.library.TraincraftRegistry.findRegisteredTransport(record.getInternalName());
				if (registered != null) {
					entity = registered;
				}
			}
		}
		return entity;
	}

	public ItemRollingStock(String iconName) {
		super(net.minecraft.entity.item.EntityMinecart.Type.RIDEABLE);
		this.iconName = iconName;
		itemTexture=new ResourceLocation(iconName);
		maxStackSize = 1;
		if(!ConfigHandler.SPLIT_CREATIVE) {
			setCreativeTab(Traincraft.tcTab);
		} else {
			setCreativeTab(Traincraft.tcTrainTab);
		}
	}

	public ResourceLocation getIconResource() {
		return itemTexture;
	}

	public ItemRollingStock(String iconName, CreativeTabs tab) {
		super(net.minecraft.entity.item.EntityMinecart.Type.RIDEABLE);
		this.iconName = iconName;
		maxStackSize = 1;
		setCreativeTab(tab);
	}

	public ItemRollingStock(AbstractTrains train, String modid, CreativeTabs tab){
		this(modid+":trains/"+train.transportName().replace(' ','_'),tab);
		setTranslationKey(train.transportName());
		entity=train;
		if(tab ==Traincraft.tcTab && ConfigHandler.SPLIT_CREATIVE) {
			setCreativeTab(Traincraft.tcTrainTab);
		}
	}

	@Deprecated
	public ItemRollingStock(String name, String iconName, String modid){
		this(modid+":trains/" +iconName);
		setTranslationKey(name);
		if(!ConfigHandler.SPLIT_CREATIVE) {
			setCreativeTab(Traincraft.tcTab);
		} else {
			setCreativeTab(Traincraft.tcTrainTab);
		}
    }


	public int setNewUniqueID(ItemStack stack, EntityPlayer player, int numberOfTrains) {
		NBTTagCompound var3 = stack.getTagCompound();
		if (numberOfTrains <= 0) {
			numberOfTrains = AbstractTrains.uniqueIDs++;
		}
		else {
			AbstractTrains.uniqueIDs = numberOfTrains++;
		}
		if (var3 == null) {
			var3 = new NBTTagCompound();
			stack.setTagCompound(var3);
		}
		stack.getTagCompound().setInteger("uniqueID", numberOfTrains);
		stack.getTagCompound().setString("trainCreator", player.getName());
		return numberOfTrains;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack par1ItemStack, @Nullable World world, List<String> par3List, ITooltipFlag flag) {
		boolean par4 = flag.isAdvanced(); EntityPlayer par2EntityPlayer = Traincraft.proxy.getPlayer();
		if (par1ItemStack.hasTagCompound()) {
			NBTTagCompound var5 = par1ItemStack.getTagCompound();
			trainCreator = var5.getString("trainCreator");
			if (!trainCreator.isEmpty()) {
				par3List.add("\u00a77" + "Creator: " + trainCreator);
			}
			if (var5.hasKey("train_Color")) {
				par3List.add("\u00a77" + "Color: " + var5.getString("train_Color"));
			}

		}

		if(getEntity()!=null){
			//year is the tell for if the TC4.5 API was used in favor of 4.3's.
			if(getEntity().transportYear() != null && !getEntity().transportYear().equals("")) {
				par3List.add(TextFormatting.GRAY + t("menu.item.year") + ": " + getEntity().transportYear());
			}
			if(getEntity().transportcountry()!=null && getEntity().transportcountry().length()>1) {
				par3List.add(TextFormatting.GRAY + t("menu.item.country") + ": " +
						t("menu.item." + getEntity().transportcountry().toLowerCase()));
			}


			StringBuilder s = new StringBuilder();
			s.append(t("menu.item.types"));
			s.append(": ");
			if (getEntity() instanceof Locomotive){
				s.append(t("menu.item.locomotive")+", ");
				if(getEntity().getRiderOffsets()!=null && getEntity().getRiderOffsets().length>0){
					s.append(t("menu.item.passenger")+", ");
				}
				if(getEntity().getInventoryRows()>0){
					s.append(t("menu.item.freight")+", ");
				}
			} else {
				s.append(t("menu.item.rollingstock")+", ");
				if(getEntity() instanceof IPassenger){
					s.append(t("menu.item.passenger")+", ");
				}
				if(getEntity() instanceof Tender){
					s.append(t("menu.item.tender")+", ");
				} else if(getEntity() instanceof LiquidTank){
					s.append(t("menu.item.tanker")+", ");
				}
				if(getEntity() instanceof AbstractWorkCart){
					s.append(t("menu.item.workcart")+", ");
				}
				if(getEntity() instanceof Freight){
					s.append(t("menu.item.freight")+", ");
				}
			}
			s.delete(s.lastIndexOf(", "),s.length());

			par3List.add(TextFormatting.RED +s.toString());

			if(getEntity().transportFuelType()!=null && !getEntity().transportFuelType().equals("")) {
				par3List.add(TextFormatting.RED + t("menu.item.fueltype") + ": " +
						t("menu.item."+getEntity().transportFuelType().toLowerCase()));
			}

			par3List.add(TextFormatting.GREEN + t("menu.item.weight") +": " + getEntity().weightKg() + "kg");
			if (getEntity().transportTopSpeed()!=0){
				par3List.add(TextFormatting.GREEN + t("menu.item.speed") +": " + getEntity().transportTopSpeed() +" km/h");

				if (getEntity().transportMetricHorsePower() !=0){
					par3List.add(TextFormatting.GREEN +t("menu.item.mhp") +": " + getEntity().transportMetricHorsePower());
				}
				if (getEntity().transportTractiveEffort() != 0){
					par3List.add(TextFormatting.GREEN + t("menu.item.tractiveeffort") +": " + getEntity().transportTractiveEffort() + " lbf");
				}
			}
			if(getEntity().getInventoryRows()>0){
				par3List.add(TextFormatting.BLUE +t("menu.item.isizeof")+ ": " + (getEntity().getInventoryRows()*9) + " " + t("menu.item.slots"));
			}
			if(getEntity().getRiderOffsets()!=null){
				par3List.add(TextFormatting.BLUE +t("menu.item.seats")+ ": " + getEntity().getRiderOffsets().length);
			}
			if (getEntity().isFictional()){
				par3List.add(TextFormatting.WHITE +t("menu.item.fictional"));
			}
			if (getEntity().additionalItemText()!=null){
				for (String a : getEntity().additionalItemText()) {
					if(!a.equals("")) {
						par3List.add(TextFormatting.LIGHT_PURPLE + a);
					}
				}
			}
		}

	}
	/**
	 * Train items from the TC 4.5 registry use the transport name as translation key, which has no lang entry;
	 * their names live under "entity.tc.&lt;transport name&gt;.name" (the entity's name), so fall back to that.
	 */
	@Override
	@SuppressWarnings("deprecation")
	public String getItemStackDisplayName(ItemStack stack) {
		String key = getTranslationKey(stack) + ".name";
		if (net.minecraft.util.text.translation.I18n.canTranslate(key)) {
			return net.minecraft.util.text.translation.I18n.translateToLocal(key).trim();
		}
		AbstractTrains transport = getEntity();
		if (transport != null && transport.transportName() != null) {
			String entityKey = "entity." + train.common.library.Info.modID + "." + transport.transportName() + ".name";
			if (net.minecraft.util.text.translation.I18n.canTranslate(entityKey)) {
				return net.minecraft.util.text.translation.I18n.translateToLocal(entityKey).trim();
			}
			return transport.transportName();
		}
		return super.getItemStackDisplayName(stack);
	}

	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.RARE;
	}

	public String getTrainType() {
		return Traincraft.instance.traincraftRegistry.findTrainRecordByItem(this).getTrainType();
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer par2EntityPlayer, World par3World, BlockPos pos, EnumHand hand, EnumFacing facing, float par8, float par9, float par10) {
		ItemStack par1ItemStack = par2EntityPlayer.getHeldItem(hand); int par4 = pos.getX(), par5 = pos.getY(), par6 = pos.getZ(); int par7 = facing.getIndex();
		int meta = CommonUtil.getBlockFacing(par3World, par4, par5, par6);
		TileEntity tileentity = CommonUtil.getTileEntity(par3World, par4, par5, par6);
		//System.out.println(meta);
		if (par3World.isRemote) {
			return EnumActionResult.PASS;
		}
		if(tileentity instanceof TileTCRail){
			TileTCRail tile = (TileTCRail) tileentity;
			if (tile.getType().equals(EnumTracks.SMALL_STRAIGHT.getLabel())
					||tile.getType().equals(EnumTracks.MEDIUM_STRAIGHT.getLabel())
					|| tile.getType().equals(EnumTracks.LONG_STRAIGHT.getLabel())
					|| tile.getType().equals(EnumTracks.VERY_LONG_STRAIGHT.getLabel())
					|| tile.getType().equals(EnumTracks.EMBEDDED_SMALL_STRAIGHT.getLabel())
					|| tile.getType().equals(EnumTracks.EMBEDDED_MEDIUM_STRAIGHT.getLabel())
					|| tile.getType().equals(EnumTracks.EMBEDDED_LONG_STRAIGHT.getLabel())
					|| tile.getType().equals(EnumTracks.EMBEDDED_VERY_LONG_STRAIGHT.getLabel())
					|| tile.getType().equals(EnumTracks.SMALL_ROAD_CROSSING.getLabel())
					|| tile.getType().equals(EnumTracks.SMALL_ROAD_CROSSING_1.getLabel())
					|| tile.getType().equals(EnumTracks.SMALL_ROAD_CROSSING_2.getLabel())
					|| tile.getType().equals(EnumTracks.SMALL_ROAD_CROSSING_DYNAMIC.getLabel())
					|| tile.getType().equals(EnumTracks.SMALL_DIAGONAL_STRAIGHT.getLabel())
					|| tile.getType().equals(EnumTracks.MEDIUM_DIAGONAL_STRAIGHT.getLabel())
					|| tile.getType().equals(EnumTracks.LONG_DIAGONAL_STRAIGHT.getLabel())
					|| tile.getType().equals(EnumTracks.VERY_LONG_DIAGONAL_STRAIGHT.getLabel())
					|| tile.getType().equals(EnumTracks.EMBEDDED_SMALL_DIAGONAL_STRAIGHT.getLabel())
					|| tile.getType().equals(EnumTracks.EMBEDDED_MEDIUM_DIAGONAL_STRAIGHT.getLabel())
					|| tile.getType().equals(EnumTracks.EMBEDDED_LONG_DIAGONAL_STRAIGHT.getLabel())
					|| tile.getType().equals(EnumTracks.EMBEDDED_VERY_LONG_DIAGONAL_STRAIGHT.getLabel())

			)
			{
				this.placeCart(par2EntityPlayer, par1ItemStack, par3World, par4, par5, par6);
				return EnumActionResult.SUCCESS;
			}

			CommonUtil.sendChat(par2EntityPlayer, "Place me on a straight piece of track !");
			return EnumActionResult.PASS;
		}
		else if(tileentity instanceof TileTCRailGag){

			TileTCRailGag tileGag = (TileTCRailGag) tileentity;
			if(((TileTCRailGag) tileentity).originX.size()<1 || CommonUtil.getTileEntity(par3World, tileGag.originX.get(0), tileGag.originY.get(0), tileGag.originZ.get(0)) ==null){
				return EnumActionResult.PASS;
			}
			TileTCRail tile = (TileTCRail) CommonUtil.getTileEntity(par3World, tileGag.originX.get(0), tileGag.originY.get(0), tileGag.originZ.get(0));

			if(tile!=null  && (tile.getType().equals(EnumTracks.MEDIUM_STRAIGHT.getLabel())
					|| tile.getType().equals(EnumTracks.LONG_STRAIGHT.getLabel())
					|| tile.getType().equals(EnumTracks.VERY_LONG_STRAIGHT.getLabel())
					|| tile.getType().equals(EnumTracks.EMBEDDED_MEDIUM_STRAIGHT.getLabel())
					|| tile.getType().equals(EnumTracks.EMBEDDED_LONG_STRAIGHT.getLabel())
					|| tile.getType().equals(EnumTracks.EMBEDDED_VERY_LONG_STRAIGHT.getLabel())
					|| tile.getType().equals(EnumTracks.MEDIUM_DIAGONAL_STRAIGHT.getLabel())
					|| tile.getType().equals(EnumTracks.LONG_DIAGONAL_STRAIGHT.getLabel())
					|| tile.getType().equals(EnumTracks.VERY_LONG_DIAGONAL_STRAIGHT.getLabel())
					|| tile.getType().equals(EnumTracks.EMBEDDED_MEDIUM_DIAGONAL_STRAIGHT.getLabel())
					|| tile.getType().equals(EnumTracks.EMBEDDED_LONG_DIAGONAL_STRAIGHT.getLabel())
					|| tile.getType().equals(EnumTracks.EMBEDDED_VERY_LONG_DIAGONAL_STRAIGHT.getLabel()))){
				if (tile.getTrackFromName().getRailType().equals(TCRailTypes.RailTypes.DIAGONAL)){
					if (tileGag.canPlaceRollingstock) {
						this.placeCart(par2EntityPlayer, par1ItemStack, par3World, par4, par5, par6);
						return EnumActionResult.SUCCESS;
					}
					else {
						CommonUtil.sendChat(par2EntityPlayer, "Place me on the middle of the track! (also try replacing old tracks if that does not work)");
						return EnumActionResult.PASS;
					}

				}


				this.placeCart(par2EntityPlayer, par1ItemStack, par3World, par4, par5, par6);
				return EnumActionResult.SUCCESS;
			}
			CommonUtil.sendChat(par2EntityPlayer, "Place me on a straight piece of track !");
			return EnumActionResult.PASS;
		}
		else if (CommonUtil.isRailBlockAt(par3World, par4, par5, par6) && (meta < 2 || meta > 5)) {
			this.placeCart(par2EntityPlayer, par1ItemStack, par3World, par4, par5, par6);
			return EnumActionResult.SUCCESS;
		}
		else {
			return EnumActionResult.PASS;
		}
	}

	public boolean canPlaceRollingStock(World world, EntityPlayer player, AbstractTrains rollingstock, int i, int j, int k, int l){

		// count distance between end points
		// create array of track blocks to check
		// loop through the array and check if block is instance of tcRail or tcRailGag
		// if all blocks are valid (or one is not) return corresponding value

		int trackLength = (int) Math.abs(Math.floor(rollingstock.rotationPoints()[0] - (rollingstock.rotationPoints()[1]) ) + 1);
		int[][] directions = {
				{0, 1},  // l == 0
				{-1, 0}, // l == 1
				{-1, 0}, // l == 2
				{-1, -1},// l == 3
				{0, -1}, // l == 4
				{1, -1}, // l == 5
				{1, 0},  // l == 6
				{1, 1}   // l == 7
		};

		int xDir = directions[l][0];
		int zDir = directions[l][1];


		for (int check = 0; check <= trackLength ; check++){


			if (!(CommonUtil.getBlockAt(world, (int) (i - ((rollingstock.rotationPoints()[1] + check) * xDir)), j, (int) (k - ((rollingstock.rotationPoints()[1] + check) * zDir)) ) == BlockIDs.tcRail.block
				|| !(CommonUtil.getBlockAt(world, (int) (i - ((rollingstock.rotationPoints()[1] + check) * xDir)), j, (int) (k - ((rollingstock.rotationPoints()[1] + check) * zDir)) ) == BlockIDs.tcRailGag.block)
				|| !(BlockRailBase.isRailBlock(CommonUtil.getBlockAt(world, (int) (i - ((rollingstock.rotationPoints()[1] + check) * xDir)), j, (int) (k - ((rollingstock.rotationPoints()[1] + check) * zDir)) ).getDefaultState())))){


				return false;
			}

		}

		return true;
	}



	public EntityMinecart placeCart(EntityPlayer player, ItemStack itemstack, World world, int i, int j, int k) {
		AbstractTrains rollingStock = null;



		try {
			rollingStock=getEntity().getClass().getConstructor(World.class)
					.newInstance(world);
		} catch (Exception e){
			if(DebugUtil.dev){
				e.printStackTrace();
			}
			DebugUtil.log("Failed to cast : " + getEntity().toString() + "to a new generic transport entity");
		}


		if (rollingStock != null) {
			rollingStock.setPosition( i + 0.5D , j + train.common.api.EntityBogie.RAIL_HEIGHT, k + 0.5D);
			if (SkinRegistry.get(rollingStock).size()>0) {
				rollingStock.setColor(rollingStock.getDefaultSkin());
			}
			if (!world.isRemote) {

				if ((rollingStock instanceof SteamTrain && !ConfigHandler.ENABLE_STEAM) || (rollingStock instanceof ElectricTrain && !ConfigHandler.ENABLE_ELECTRIC) || (rollingStock instanceof DieselTrain && !ConfigHandler.ENABLE_DIESEL) || (rollingStock instanceof EntityTracksBuilder && !ConfigHandler.ENABLE_BUILDER) || (rollingStock instanceof Tender && !ConfigHandler.ENABLE_TENDER)) {
					if (player != null)
						CommonUtil.sendChat(player, "This type of train has been deactivated by the OP");
					rollingStock.setDead();
					return rollingStock;
				}

				int dir = 0;
				int meta;
				if (CommonUtil.getBlockAt(world, i, j, k) instanceof BlockRailBase) {
					meta = CommonUtil.getRailMeta(world, rollingStock, i, j, k);
				} else {
					meta = CommonUtil.getBlockFacing(world, i, j, k);
				}

				if (player != null)
					dir = CommonUtil.floorDouble((player.rotationYaw * 8F) / 360F + 0.5D) & 7;
				// 0    = 0 = SOUTH
				// 45   = 1 = SOUTH-WEST
				// 90   = 2 = WEST
				// 135  = 3 = NORTH-WEST
				// 180  = 4 = NORTH
				// -135 = 5 = NORTH-EAST
				// -90  = 6 = EAST
				// -45  = 7 = SOUTH-EAST


				if (dir == 0) {

					rollingStock.rotationYaw = (meta == 2 || meta == 0) ? 90 : 0;

					if (CommonUtil.getBlockAt(world, i, j, k) == BlockIDs.tcRail.block || CommonUtil.getBlockAt(world, i, j, k) == BlockIDs.tcRailGag.block) {

						if (meta == 0 || meta == 2) {
							//rollingStock.rotationYaw = -90; // LEFT
							rollingStock.rotationYaw = 90;
						}
						else if (meta == 6 || meta == 4) {
							rollingStock.rotationYaw = 135; // LEFT;
						}
						else if (meta == 5 || meta == 7){
							rollingStock.rotationYaw = 45;
						}
						else {
							CommonUtil.sendChat(player, "Place me on a straight piece of track!");
							rollingStock.setDead();
							return rollingStock;
						}
					}


				}

				if (dir == 1) {
					rollingStock.rotationYaw = (meta == 2 || meta == 0) ? 90 : 0;

					if (CommonUtil.getBlockAt(world, i, j, k) == BlockIDs.tcRail.block || CommonUtil.getBlockAt(world, i, j, k) == BlockIDs.tcRailGag.block) {
						if (meta == 6 || meta == 4) {
							rollingStock.rotationYaw = 135; // LEFT
						} else if (meta == 2 || meta == 0) {
							rollingStock.rotationYaw = 90; // LEFT
						} else if (meta == 1 || meta == 3) {
							rollingStock.rotationYaw = 180; // LEFT
						} else {
							CommonUtil.sendChat(player, "Place me on a straight piece of track!");
							rollingStock.setDead();
							return rollingStock;
						}
					}

				}

				if (dir == 2) {
					rollingStock.rotationYaw = (meta == 1 || meta == 3) ? 180 : 90;
					if (CommonUtil.getBlockAt(world, i, j, k) == BlockIDs.tcRail.block || CommonUtil.getBlockAt(world, i, j, k) == BlockIDs.tcRailGag.block) {
						if (meta == 1 || meta == 3){
							rollingStock.rotationYaw = 180; // LEFT
						}
						else if(meta == 5 || meta == 7){
							rollingStock.rotationYaw = -135;
						}
						else if (meta == 6 || meta == 4){
							rollingStock.rotationYaw = 135;

						}
						else {
							CommonUtil.sendChat(player, "Place me on a straight piece of track!");
							rollingStock.setDead();
							return rollingStock;
						}
					}

				}

				if (dir == 3) {
					rollingStock.rotationYaw = (meta == 2 || meta == 0) ? -90 : 180;

					if (CommonUtil.getBlockAt(world, i, j, k) == BlockIDs.tcRail.block || CommonUtil.getBlockAt(world, i, j, k) == BlockIDs.tcRailGag.block) {
						if (meta == 5 || meta == 7) {
							rollingStock.rotationYaw = -135; // LEFT
						}
						else if (meta == 2 || meta == 0) {
							rollingStock.rotationYaw = -90;
						}
						else if (meta == 1 || meta == 3){
							rollingStock.rotationYaw = 180; // LEFT
						}
						else {
							CommonUtil.sendChat(player, "Place me on a straight piece of track!");
							rollingStock.setDead();
							return rollingStock;
						}
					}

				}

				if (dir == 4) {
					rollingStock.rotationYaw = (meta == 2 || meta == 0) ? -90 : 180;

					if (CommonUtil.getBlockAt(world, i, j, k) == BlockIDs.tcRail.block || CommonUtil.getBlockAt(world, i, j, k) == BlockIDs.tcRailGag.block) {
						if (meta == 0 || meta == 2) {
							rollingStock.rotationYaw = -90; // RIGHT
						}
						else if (meta == 5 || meta == 7) {
							rollingStock.rotationYaw = -135; // LEFT
						}
						else if (meta == 6 || meta == 4){
							rollingStock.rotationYaw = -45;
						}

						else {
							CommonUtil.sendChat(player, "Place me on a straight piece of track!");
							rollingStock.setDead();
							return rollingStock;
						}

					}


				}

				if (dir == 5) {
					rollingStock.rotationYaw = (meta == 2 || meta == 0) ? -90 : 0;

					if (CommonUtil.getBlockAt(world, i, j, k) == BlockIDs.tcRail.block || CommonUtil.getBlockAt(world, i, j, k) == BlockIDs.tcRailGag.block) {
						if (meta == 6 || meta == 4) {
							rollingStock.rotationYaw = -45; // LEFT
						}
						else if (meta == 0 || meta == 2) {
							rollingStock.rotationYaw = -90; // LEFT
						}
						else if (meta == 1 || meta == 3){
							rollingStock.rotationYaw = 0; // LEFT
						}
						else {
							CommonUtil.sendChat(player, "Place me on a straight piece of track!");
							rollingStock.setDead();
							return rollingStock;
						}
					}

				}

				if (dir == 6) {
					rollingStock.rotationYaw = (meta == 0 || meta == 2) ? -90 : 0;
				
					if (CommonUtil.getBlockAt(world, i, j, k) == BlockIDs.tcRail.block || CommonUtil.getBlockAt(world, i, j, k) == BlockIDs.tcRailGag.block) {
						if (meta == 1 || meta == 3) {
							rollingStock.rotationYaw = 0;
						}
						else if (meta == 6 || meta == 4) {
							rollingStock.rotationYaw = -45; // LEFT
						}
						else if (meta == 5 || meta == 7) {
							rollingStock.rotationYaw = 45; // LEFT
						}else {
							CommonUtil.sendChat(player, "Place me on a straight piece of track!");
							rollingStock.setDead();
							return rollingStock;
						}
					}

				}

				if (dir == 7) {
					rollingStock.rotationYaw = (meta == 0 || meta == 2) ? 90 : 0;

					if (CommonUtil.getBlockAt(world, i, j, k) == BlockIDs.tcRail.block || CommonUtil.getBlockAt(world, i, j, k) == BlockIDs.tcRailGag.block) {
						if (meta == 5 || meta == 7) {
							rollingStock.rotationYaw = 45; // LEFT
						}
						else if (meta == 1 || meta == 3) {
							rollingStock.rotationYaw = 0; // LEFT
						}
						else if (meta == 0 || meta == 2) {
							rollingStock.rotationYaw = 90; // LEFT
						}
						else {
							CommonUtil.sendChat(player, "Place me on a straight piece of track!");
							rollingStock.setDead();
							return rollingStock;
						}
					}
					
				}

				rollingStock.trainName = (itemstack.getItem()).getItemStackDisplayName(itemstack);
				if (player != null) {
					rollingStock.trainOwner = player.getName();
				}
				rollingStock.mass = rollingStock.weightKg()*0.1;

				int uniID = -1;
				if (itemstack.hasTagCompound()) {
					NBTTagCompound var5 = itemstack.getTagCompound();
					uniID = var5.getInteger("uniqueID");
					if (uniID != -1)
						rollingStock.uniqueID = uniID;
					if (uniID != -1)
						rollingStock.getEntityData().setInteger("uniqueID", uniID);
					trainCreator = var5.getString("trainCreator");
					if (var5.hasKey("train_Color"))
						rollingStock.setColor(var5.getString("train_Color"));
					rollingStock.trainCreator = trainCreator;
					if (var5.hasKey("overlayTextureConfigTag")) // Import overlay configuration from NBT and apply it to the entity.
						rollingStock.getOverlayTextureContainer().importFromConfigTag(var5.getCompoundTag("overlayTextureConfigTag"));
					rollingStock.importTrustedListFromNBT(var5);
				}
				if (player != null)
					rollingStock.setInformation(player.getName(), trainCreator, (itemstack.getItem()).getItemStackDisplayName(itemstack), uniID);
				if (player == null)
					rollingStock.setInformation("", trainCreator, (itemstack.getItem()).getItemStackDisplayName(itemstack), uniID);

				if (ConfigHandler.SHOW_POSSIBLE_COLORS && SkinRegistry.get(rollingStock).size()>0) {
					String concatColors = ": ";
					for (String cols : SkinRegistry.get(rollingStock).keySet()) {
						if (!cols.equals("Empty") && !cols.equals("Full"))
							concatColors+=cols+", ";
					}
					if (concatColors.length() > 4) {
						if (player != null) {
							CommonUtil.sendChat(player, "Possible colors" + concatColors);
							CommonUtil.sendChat(player, "To paint, click me with the right (vanilla) dye");
						}
					}
				}
				ebf.tim.utility.CommonUtil.spawnEntity(world, rollingStock);
			}
		}
		itemstack.shrink(1);

    itemstack.shrink(1);
    return rollingStock;
}


	public static ItemStack setPersistentData(@Nullable ItemStack oldStack, @Nullable AbstractTrains train, @Nullable Integer trainID, @Nullable String player, @Nullable String creator, String color) {

		ItemStack stack = oldStack;

		if (train != null){
			stack = (new ItemStack(train.getItem()));
		}
		if(stack!=null) {
			NBTTagCompound tag = stack.getTagCompound();
			if(tag==null){
				tag=new NBTTagCompound();
			}
			if(train!=null) {
				tag.setString("puuid", train.getPersistentUUID());
				tag.setString("trainCreator", creator==null?train.getEntityData().getString("theCreator"):creator);
				if(player!=null && player.length()>1) {
					tag.setString("theOwner", player);
				}
				tag.setString("train_Color",color);
				train.exportTrustedListToNBT(tag);
			} else {
				tag.setString("trainCreator", creator!=null && creator.length()>1?creator:"Creative");
			}
			tag.setInteger("uniqueID", trainID==null?AbstractTrains.uniqueIDs++:trainID);

			stack.setTagCompound(tag);
		} else {
			return null;//THIS SHOULD NEVER HAPPEN, but compensate anyway because java is stupid and forge is unreliable.
		}
		return stack;

	}

	private static String t(String translate){
		return translate==null?"":CommonUtil.translate(translate);
	}

	@Override
	public boolean canBePlacedByNonPlayer(ItemStack cart) {
		return true;
	}

	@Override
	public EntityMinecart placeCart(GameProfile owner, ItemStack cart, World world, net.minecraft.util.math.BlockPos pos) {
		return placeCart((EntityPlayer) null, cart, world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public boolean doesCartMatchFilter(ItemStack stack, EntityMinecart cart) {
		return false;
	}


    @Override
    public String legacyIcon(int meta) {
        // TiM style "tc:+train_br80:trains/loco_steam_br80": the icon is the part after '+'
        int plus = this.iconName.indexOf(":+");
        if (plus >= 0) {
            String icon = this.iconName.substring(plus + 2);
            int end = icon.indexOf(':');
            return this.iconName.substring(0, plus) + ":trains/" + (end >= 0 ? icon.substring(0, end) : icon);
        }
        return (this.iconName.contains(":") ? this.iconName : "tc:" + this.iconName);
    }
}
