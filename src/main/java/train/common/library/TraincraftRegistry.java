package train.common.library;

import java.util.ArrayList;

import buildcraft.api.fuels.BuildcraftFuelRegistry;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.SideOnly;
import ebf.tim.render.CustomItemModel;
import ebf.tim.utility.DebugUtil;
import ebf.tim.utility.OreGen;
import fexcraft.tmt.slim.ModelBase;
import mods.railcraft.api.fuel.FuelManager;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;
import train.client.core.ClientProxy;
import train.common.Traincraft;
import train.common.api.*;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.item.Item;
import train.client.render.RenderEnum;
import train.common.api.blocks.BlockDynamic;
import train.common.api.blocks.TileRenderFacing;
import train.common.blocks.BlockTraincraftFluid;
import train.common.core.handlers.EntityHandler;
import train.common.core.managers.TierRecipeManager;
import train.common.items.ItemRollingStock;

import javax.annotation.Nullable;

public class TraincraftRegistry {

    private List<TrainRecord> trainRecords = new ArrayList<>();
    private Map<Item, TrainRecord> trainRecordsByItem = new HashMap<>();

    private List<TrackRecord> trackRecords = new ArrayList<>();
    private Map<Object, TrackRecord> trackRecordByItem = new HashMap<Object, TrackRecord>();
    private Map<Class<?>, TrainRenderRecord> trainRenderRecords = new HashMap<>();
    private List<TrainSoundRecord> trainSoundRecords = new ArrayList<>();

    public TraincraftRegistry() {
    }

    public void init() {
        for (TrainRecord train : EnumTrains.trains()) {
            TraincraftRegistry.this.registerTrainRecord(train);
        }

        for (EnumTracks tracks : EnumTracks.values()){
            TraincraftRegistry.this.registerTrackRecord(tracks);
        }


        Side side = FMLCommonHandler.instance().getEffectiveSide();
        if (side == Side.CLIENT) {
            for (RenderEnum render : RenderEnum.values()) {
                TraincraftRegistry.this.registerTrainRenderRecord(render);
            }
        }

        for (TrainSoundRecord sound : EnumSounds.values()) {
            TraincraftRegistry.this.registerTrainSoundRecord(sound);
        }
    }



    public TrainRecord getTrainRecord(Class<?> entityClass) {
        if (entityClass == null) return null;

        for (TrainRecord record : trainRecords) {
            if (entityClass.equals(record.getEntityClass())) {
                return record;
            }
        }
        for(TrainRecord fallback : EnumTrains.trains()){
            if (entityClass.equals(fallback.getEntityClass())) {
                return fallback;
            }
        }
        return null;
    }

    public TrainSoundRecord getTrainSoundRecord(Class<?> entityClass) {
        if (entityClass == null) return null;

        for (TrainSoundRecord record : trainSoundRecords) {
            if (entityClass.equals(record.getEntityClass())) {
                return record;
            }
        }
        return null;
    }


    public TrackRecord findTrackRecordByItem(Item item){
        return trackRecordByItem.get(item);
    }


    public void registerTrackRecord(TrackRecord trackRecord){
        trackRecords.add(trackRecord);
        trackRecordByItem.put(trackRecord.getItem(), trackRecord);
    }

    public TrainRecord findTrainRecordByItem(Item item) {
        return trainRecordsByItem.get(item);
    }

    public TrainRenderRecord getTrainRenderRecord(Class<?> entityClass) {
        return trainRenderRecords.get(entityClass);
    }

    public void registerTrainRecord(TrainRecord record) {
        trainRecords.add(record);
        trainRecordsByItem.put(record.getItem(), record);
    }

    public void registerTrainRenderRecord(TrainRenderRecord record) {
        trainRenderRecords.put(record.getEntityClass(), record);
    }

    public void registerTrainSoundRecord(TrainSoundRecord sound) {
        trainSoundRecords.add(sound);
    }

    public void addLivery(Class<?> entityClass, String name){
        for (TrainRecord record : trainRecords) {
            if (entityClass.equals(record.getEntityClass()) && !record.getLiveries().contains(name)) {
                record.skins.add(name);
            }
        }
    }



    public static int trainID= 32;
    public static void registerTransport(TrainRecord record){
        EntityRegistry.registerModEntity(record.getEntityClass(), record.getInternalName(), trainID, Traincraft.instance, 512, 1, true);
        AbstractTrains entity = record.getEntity(null);
        if(entity!=null) {
            entity.registerSkins();
            if(entity.getRecipe()!=null){
                TierRecipeManager.getInstance().addRecipe(entity.getTier(),
                        entity.getRecipe()[0],entity.getRecipe()[1],entity.getRecipe()[2],entity.getRecipe()[3],
                        entity.getRecipe()[4],entity.getRecipe()[5],entity.getRecipe()[6],entity.getRecipe()[7],
                        entity.getRecipe()[8],entity.getRecipe()[9], entity.getCartItem(),1);
            }
        }
        trainID++;
        if(trainID== 112 || trainID==51){
            trainID++;
        }
    }

    public static void registerTransports(String MODID, AbstractTrains[] entities) {
        for(final AbstractTrains trains : entities){
            EntityRegistry.registerModEntity(trains.getClass(), MODID+":"+trains.transportName(), trainID, Traincraft.instance, 512, 1, true);
            trains.registerSkins();
            GameRegistry.registerItem(trains.getItem(), MODID+":entity/"+trains.transportName());
            trainID+=1;
            if(trains.getRecipe()!=null){
                TierRecipeManager.getInstance().addRecipe(trains.getTier(),
                        trains.getRecipe()[0],trains.getRecipe()[1],trains.getRecipe()[2],trains.getRecipe()[3],
                        trains.getRecipe()[4],trains.getRecipe()[5],trains.getRecipe()[6],trains.getRecipe()[7],
                        trains.getRecipe()[8],trains.getRecipe()[9], trains.getCartItem(),1);
            }
            //todo:this part should be unnecessary? double-check.
            if(Traincraft.proxy.isClient()){
                Traincraft.instance.traincraftRegistry.registerTrainRenderRecord(new TrainRenderRecord() {
                    @Override
                    public Class<? extends AbstractTrains> getEntityClass() {
                        return trains.getClass();
                    }

                    @Override
                    public ModelBase getModel() {
                        return trains.getModel()[0];
                    }

                    @Override
                    public boolean hasSmoke() {
                        return false;
                    }

                    @Override
                    public boolean hasSmokeOnSlopes() {
                        return false;
                    }

                    @Override
                    public String getSmokeType() {
                        return null;
                    }

                    @Override
                    public ArrayList<double[]> getSmokeFX() {
                        return null;
                    }

                    @Override
                    public String getExplosionType() {
                        return null;
                    }

                    @Override
                    public boolean hasExplosion() {
                        return false;
                    }

                    @Override
                    public ArrayList<double[]> getExplosionFX() {
                        return null;
                    }

                    @Override
                    public float[] getTrans() {
                        return new float[]{0,0,0};
                    }

                    @Override
                    public float[] getRotate() {
                        return new float[]{0,0,0};
                    }

                    @Override
                    public float[] getScale() {
                        return new float[]{0,0,0};
                    }

                    @Override
                    public ResourceLocation getTextureFile(String colorString) {
                        return null;
                    }

                    @Override
                    public int getSmokeIterations() {
                        return 0;
                    }

                    @Override
                    public int getExplosionFXIterations() {
                        return 0;
                    }
                });
            }
        }
    }

    private static String typeDecor="decorative",typeDiesel="diesel", typeSteam="steam",typeElectric="electric",typePassenger="passenger",typeTender="tender", typeWork="work",typeFreight="freight",typeTank="tank";
    public static String findTrainType(AbstractTrains t){
        if(t instanceof SteamTrain){
            return typeSteam;
        }
        if(t instanceof DieselTrain){
            return typeDiesel;
        }
        if(t instanceof ElectricTrain){
            return typeElectric;
        }
        if(t instanceof Tender){
            return typeTender;
        }
        if(t instanceof AbstractWorkCart){
            return typeWork;
        }
        if(t instanceof Freight){
            return typeFreight;
        }
        if(t instanceof IPassenger){
            return typePassenger;
        }
        if(t instanceof LiquidTank){
            return typeTank;
        }
        return typeDecor;
    }


    //todo:purge redundancy checks on postinit
    private static List<String> usedNames = new ArrayList<>();
    private static List<String> redundantTiles = new ArrayList<>();
    private static List<String> redundantBlocks = new ArrayList<>();
    public static int registryPosition = 18;

    public static Map<Block, Item> fluidMap = new HashMap<>();

    /**
     * @param block             the block in question
     * @param tab               the creative tab to put the block's item into, leave null for no creative tab entry.
     * @param MODID             modid of the host add-on, used for translations, texture names, and generic identification.
     * @param unlocalizedName   unlocalized name, used for translations, texture names, and generic identification.
     * @param oreDictionaryName ore directory name, used for other mods to identify type, mainly used for ingots, ores, and wood.
     * @param render            a ModelBase instance for rendering the tile entity.
     *                          Can instead be a TESR instance for more rendering control.
     *                          Null will fallback to a normal textured block render.
     * @return
     */
    public static Block registerBlock(Block block, CreativeTabs tab, String MODID, String unlocalizedName, @Nullable String oreDictionaryName, @Nullable Object render) {
        if (render instanceof ModelBase) {
            return registerBlock(block, tab, MODID, unlocalizedName, oreDictionaryName, Traincraft.proxy.getTESR(), (ModelBase) render, unlocalizedName);
        } else {
            return registerBlock(block, tab, MODID, unlocalizedName, oreDictionaryName, render, null, unlocalizedName);
        }
    }
    public static Block registerBlock(Block block, CreativeTabs tab, String MODID, String unlocalizedName, @Nullable String oreDictionaryName, @Nullable Object render, String textureName) {
        if (render instanceof ModelBase) {
            return registerBlock(block, tab, MODID, unlocalizedName, oreDictionaryName, Traincraft.proxy.getTESR(), (ModelBase) render, textureName);
        } else {
            return registerBlock(block, tab, MODID, unlocalizedName, oreDictionaryName, render, null, textureName);
        }
    }

    public static Block registerBlock(Block block, CreativeTabs tab, String modid, String unlocalizedName) {
        return registerBlock(block, tab, modid, unlocalizedName, null, Traincraft.proxy.getTESR());
    }

    public static Block registerBlock(Block block, CreativeTabs tab, String modid, String unlocalizedName, @Nullable String oreDictionaryName) {
        return registerBlock(block, tab, modid, unlocalizedName, oreDictionaryName, Traincraft.proxy.getTESR());
    }

    public static Block registerBlock(Block block, CreativeTabs tab, String unlocalizedName, @Nullable String oreDictionaryName, @Nullable Object render) {
        return registerBlock(block, tab, null, unlocalizedName, oreDictionaryName, render);
    }

    public static Block registerBlock(Block block, CreativeTabs tab, String MODID, String unlocalizedName, @Nullable String oreDictionaryName, @Nullable Object TESR, @Nullable ModelBase model) {
        return registerBlock(block, tab, MODID, unlocalizedName, oreDictionaryName, TESR, model, unlocalizedName);
    }

    public static Block registerBlock(Block block, CreativeTabs tab, String MODID, String unlocalizedName, @Nullable String oreDictionaryName, @Nullable Object TESR, @Nullable ModelBase model, String textureName) {
        if (usedNames.contains(unlocalizedName)) {
            DebugUtil.println("ERROR: ", "attempted to register Block with a used unlocalizedName", unlocalizedName);
            DebugUtil.throwStackTrace();
        }
        if (tab != null) {
            block.setCreativeTab(tab);
        }
        if (unlocalizedName.length() > 0) {
            block.setBlockName(unlocalizedName);
            GameRegistry.registerBlock(block, null, unlocalizedName);
            if(model!=null || (block instanceof ITileEntityProvider && ((ITileEntityProvider) block).createNewTileEntity(null,0) instanceof TileRenderFacing)) {
                RegisterItem(new ItemBlock(block), MODID, unlocalizedName, oreDictionaryName + ".item", tab, null, ebf.tim.render.CustomItemModel.instance, textureName);
                if(Traincraft.proxy.isClient() && TESR==null){
                    TESR=new ebf.tim.render.BlockTESR();
                }
            } else {
                RegisterItem(new ItemBlock(block), MODID, unlocalizedName, oreDictionaryName + ".item", tab, null, null, textureName);
            }
            usedNames.add(unlocalizedName);
        } else {
            DebugUtil.println("ERROR: ", "attempted to register Block with no unlocalizedName");
            DebugUtil.throwStackTrace();
        }

        if (Traincraft.proxy.isClient() && MODID != null) {
            block.setBlockTextureName(MODID + ":" + textureName);
        }
        if (oreDictionaryName != null) {
            OreDictionary.registerOre(oreDictionaryName, block);
        }
        if (DebugUtil.dev && Traincraft.proxy.isClient() && block.getUnlocalizedName().equals(StatCollector.translateToLocal(block.getUnlocalizedName() +".name"))) {
            DebugUtil.println("Block missing lang entry: " + block.getUnlocalizedName());
        }
        if (block instanceof ITileEntityProvider) {
            Class<? extends TileEntity> tile = ((ITileEntityProvider) block).createNewTileEntity(null, 0).getClass();
            if (!redundantTiles.contains(tile.getName())) {
                GameRegistry.registerTileEntity(tile, unlocalizedName + "tile");
                redundantTiles.add(tile.getName());
                redundantTiles.add(unlocalizedName + "tile");
                if (Traincraft.proxy.isClient() && TESR!=null) {
                    regTileRender(MODID,unlocalizedName,block,tile,model,TESR);
                }
            } else if(!redundantTiles.contains(unlocalizedName + "tile")) {
                if (Traincraft.proxy.isClient() && TESR != null) {
                    regTileRender(MODID,unlocalizedName,block, tile, model, TESR);
                }
            } else {
                DebugUtil.println("redundant tile name found",tile.getName(), unlocalizedName + "tile");
                DebugUtil.printStackTrace();
            }
        }
        return block;
    }

    public static Item RegisterItem(Item itm, String MODID, String unlocalizedName, CreativeTabs tab) {
        return RegisterItem(itm, MODID, unlocalizedName, null, tab, null, null);
    }

    public static Item RegisterItem(Item itm, String MODID, String unlocalizedName, CreativeTabs tab, String textureName) {
        return RegisterItem(itm, MODID, unlocalizedName, null, tab, null, null, textureName);
    }

    public static Item RegisterItem(Item itm, String MODID, String unlocalizedName, @Nullable String oreDictionaryName, @Nullable CreativeTabs tab, @Nullable Item container, @Nullable Object itemRender) {
        return RegisterItem(itm, MODID, unlocalizedName, oreDictionaryName, tab, container, itemRender, unlocalizedName.replace("item.", ""));
    }

    public static Item RegisterItem(Item itm, String MODID, String unlocalizedName, @Nullable String oreDictionaryName, @Nullable CreativeTabs tab, @Nullable Item container, @Nullable Object itemRender, String textureName) {
        if (usedNames.contains(unlocalizedName)) {
            DebugUtil.println("ERROR: ", "attempted to register Item with a used unlocalizedName", unlocalizedName);
            DebugUtil.throwStackTrace();
        }
        if (tab != null) {
            itm.setCreativeTab(tab);
        }
        if (container != null) {
            itm.setContainerItem(container);
        }
        if (!unlocalizedName.equals("")) {
            itm.setUnlocalizedName(unlocalizedName);
            usedNames.add(unlocalizedName);
        } else {
            DebugUtil.println("ERROR: ", "attempted to register Item with no unlocalizedName");
            DebugUtil.throwStackTrace();
        }
        GameRegistry.registerItem(itm, unlocalizedName);
        if (oreDictionaryName != null) {
            OreDictionary.registerOre(oreDictionaryName, itm);
        }
        if (DebugUtil.dev && Traincraft.proxy != null && Traincraft.proxy.isClient() && itm.getUnlocalizedName().equals(StatCollector.translateToLocal(itm.getUnlocalizedName()+".name"))) {
            DebugUtil.println("Item missing lang entry: " + itm.getUnlocalizedName());
        }
        if (Traincraft.proxy.isClient() && itemRender != null) {
            MinecraftForgeClient.registerItemRenderer(itm, (IItemRenderer) itemRender);
            if (ClientProxy.preRenderModels) {
                ebf.tim.render.CustomItemModel.instance.renderItem(IItemRenderer.ItemRenderType.INVENTORY, new ItemStack(itm));
            }
        } else if (Traincraft.proxy.isClient() && itm instanceof ItemRollingStock) {
            MinecraftForgeClient.registerItemRenderer(itm, ebf.tim.render.CustomItemModel.instance);
            if (ClientProxy.preRenderModels) {
                ebf.tim.render.CustomItemModel.instance.renderItem(IItemRenderer.ItemRenderType.INVENTORY, new ItemStack(itm));
            }
        } else if(Traincraft.proxy.isClient()){
            itm.setTextureName(MODID+ ":" + textureName);
        }
        return itm;
    }


    public static Item RegisterFluid(Fluid fluid, String MODID, String unlocalizedName, boolean isGaseous, int density, MapColor color, @Nullable CreativeTabs tab) {
        if (usedNames.contains(unlocalizedName)) {
            DebugUtil.println("ERROR: ", "attempted to register Fluid with a used unlocalizedName", unlocalizedName);
            DebugUtil.throwStackTrace();
        }
        if (!unlocalizedName.equals("")) {
            fluid.setUnlocalizedName(unlocalizedName);
            usedNames.add(unlocalizedName);
        } else {
            DebugUtil.println("ERROR: ", "attempted to register Fluid with no unlocalizedName");
            DebugUtil.throwStackTrace();
        }
        fluid.setGaseous(isGaseous).setDensity(density);
        FluidRegistry.registerFluid(fluid);

        Block block = new BlockTraincraftFluid(fluid, Material.water).setBlockName("block." + unlocalizedName.replace(".item", "")).setBlockTextureName(MODID + ":block_" + unlocalizedName);
        ((BlockTraincraftFluid) block).setModID(MODID);
        GameRegistry.registerBlock(block, "block." + unlocalizedName);
        if (Traincraft.proxy.isClient()) {
            block.setBlockTextureName(MODID + ":" + unlocalizedName);
        }
        fluid.setBlock(block);


        Item bucket = new ItemBucket(block).setCreativeTab(tab).setContainerItem(Items.bucket);
        if (Traincraft.proxy.isClient()) {
            bucket.setTextureName(MODID + ":bucket_" + unlocalizedName);
        }
        bucket.setUnlocalizedName(unlocalizedName + ".bucket");
        GameRegistry.registerItem(bucket, "fluid." + unlocalizedName + ".bucket");
        FluidContainerRegistry.registerFluidContainer(fluid, new ItemStack(bucket), new ItemStack(Items.bucket));

        fluidMap.put(block, bucket);

        if (DebugUtil.dev && Traincraft.proxy.isClient()) {
            if (fluid.getUnlocalizedName().equals(StatCollector.translateToLocal(fluid.getUnlocalizedName()))) {
                DebugUtil.println("Fluid missing lang entry: " + fluid.getUnlocalizedName());
            }
            if (bucket.getUnlocalizedName().equals(StatCollector.translateToLocal(block.getUnlocalizedName()))) {
                DebugUtil.println("Item missing lang entry: " + bucket.getUnlocalizedName());
            }
            if (block.getUnlocalizedName().equals(StatCollector.translateToLocal(block.getUnlocalizedName()))) {
                DebugUtil.println("Block missing lang entry: " + block.getUnlocalizedName());
            }

        }
        return bucket;
    }

    @SideOnly(Side.CLIENT)
    private static void regTileRender(String MODID, String unlocalizedName, Block block, Class<? extends TileEntity> tile, ModelBase model, Object TESR) {

        if (block instanceof BlockDynamic) {
            if (model != null) {
                ((BlockDynamic) block).setModel(model);
            } else if (TESR != null) {
                ((BlockDynamic) block).setTESR(TESR);
            }
        }
        if (TESR != null) {
            cpw.mods.fml.client.registry.ClientRegistry.bindTileEntitySpecialRenderer(tile, (TileEntitySpecialRenderer) TESR);
            MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(block), CustomItemModel.instance);
            CustomItemModel.registerBlockTextures(Item.getItemFromBlock(block), ((ITileEntityProvider) block).createNewTileEntity(null, 0));
        } else {
            cpw.mods.fml.client.registry.ClientRegistry.bindTileEntitySpecialRenderer(tile, (TileEntitySpecialRenderer) Traincraft.proxy.getTESR());
            MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(block), CustomItemModel.instance);
            CustomItemModel.registerBlockTextures(Item.getItemFromBlock(block), ((ITileEntityProvider) block).createNewTileEntity(null, 0));
        }
    }


    /**
     * @param priority the priority to generate, higher numbers tend to generate after other mods.
     */
    public static void registerOreGen(int priority, OreGen veinConfig) {
        GameRegistry.registerWorldGenerator(veinConfig, priority);
    }


    public static void endRegistration() {
        usedNames = null;
        registryPosition = -1;
        redundantTiles = null;
    }


    //todo:add support for buildcraft/railcraft burnable fluids

    @Optional.Method(modid = "BuildCraft|Energy")
    static void registerBCFluid(Fluid f, int powerPerCycle, int totalBurningTime) {
        BuildcraftFuelRegistry.fuel.addFuel(f, powerPerCycle, totalBurningTime);
    }

    @Optional.Method(modid = "Railcraft")
    static void registerRCFluid(Fluid f, int totalBurningTime) {
        FuelManager.addBoilerFuel(f, totalBurningTime);
    }

}
