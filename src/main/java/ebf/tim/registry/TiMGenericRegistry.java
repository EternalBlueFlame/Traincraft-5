package ebf.tim.registry;


import buildcraft.api.fuels.BuildcraftFuelRegistry;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ebf.tim.TrainsInMotion;
import ebf.tim.blocks.BlockDynamic;
import ebf.tim.blocks.BlockTrainFluid;
import ebf.tim.blocks.OreGen;
import ebf.tim.blocks.TileRenderFacing;
import ebf.tim.entities.GenericRailTransport;
import ebf.tim.items.CustomItemModel;
import ebf.tim.items.ItemBlockTiM;
import ebf.tim.items.ItemCraftGuide;
import ebf.tim.items.ItemTransport;
import ebf.tim.utility.*;
import fexcraft.tmt.slim.ModelBase;
import mods.railcraft.api.fuel.FuelManager;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static ebf.tim.utility.RecipeManager.getRecipe;

/**
 * <h1>Train registry</h1>
 * this class lists the data necessary to register trains and rollingstock.
 * this is not intended to be a way to get specific trains from the mod. (use the unlocalized entity name, or a class instanceof check)
 * @author Eternal Blue Flame
 */
public class TiMGenericRegistry {

    public GenericRailTransport transport;
    public Item[] recipe;

    //todo:purge redundancy checks on postinit
    private static List<String> usedNames = new ArrayList<>();
    private static List<String> redundantTiles = new ArrayList<>();
    private static List<String> redundantBlocks = new ArrayList<>();
    public static int registryPosition = 18;

    public TiMGenericRegistry(GenericRailTransport transport, Item[] recipe) {
        this.transport = transport;
        this.recipe = recipe;
    }

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
            return registerBlock(block, tab, MODID, unlocalizedName, oreDictionaryName, TrainsInMotion.proxy.getTESR(), (ModelBase) render, unlocalizedName);
        } else {
            return registerBlock(block, tab, MODID, unlocalizedName, oreDictionaryName, render, null, unlocalizedName);
        }
    }
    public static Block registerBlock(Block block, CreativeTabs tab, String MODID, String unlocalizedName, @Nullable String oreDictionaryName, @Nullable Object render, String textureName) {
        if (render instanceof ModelBase) {
            return registerBlock(block, tab, MODID, unlocalizedName, oreDictionaryName, TrainsInMotion.proxy.getTESR(), (ModelBase) render, textureName);
        } else {
            return registerBlock(block, tab, MODID, unlocalizedName, oreDictionaryName, render, null, textureName);
        }
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
                RegisterItem(new ItemBlockTiM(block), MODID, unlocalizedName, oreDictionaryName + ".item", tab, null, ebf.tim.items.CustomItemModel.instance, textureName);
                if(TrainsInMotion.proxy.isClient() && TESR==null){
                    TESR=new ebf.tim.render.BlockTESR();
                }
            } else {
                RegisterItem(new ItemBlockTiM(block), MODID, unlocalizedName, oreDictionaryName + ".item", tab, null, null, textureName);
            }
            usedNames.add(unlocalizedName);
        } else {
            DebugUtil.println("ERROR: ", "attempted to register Block with no unlocalizedName");
            DebugUtil.throwStackTrace();
        }

        if (TrainsInMotion.proxy.isClient() && MODID != null) {
            block.setBlockTextureName(MODID + ":" + textureName);
        }
        if (oreDictionaryName != null) {
            OreDictionary.registerOre(oreDictionaryName, block);
        }
        if (DebugUtil.dev && TrainsInMotion.proxy.isClient() && block.getUnlocalizedName().equals(StatCollector.translateToLocal(block.getUnlocalizedName()))) {
            DebugUtil.println("Block missing lang entry: " + block.getUnlocalizedName());
        }
        if (block instanceof ITileEntityProvider) {
            Class<? extends TileEntity> tile = ((ITileEntityProvider) block).createNewTileEntity(null, 0).getClass();
            if (!redundantTiles.contains(tile.getName())) {
                GameRegistry.registerTileEntity(tile, unlocalizedName + "tile");
                redundantTiles.add(tile.getName());
                redundantTiles.add(unlocalizedName + "tile");
                if (TrainsInMotion.proxy.isClient() && TESR!=null) {
                    regTileRender(MODID,unlocalizedName,block,tile,model,TESR);
                }
            } else if(!redundantTiles.contains(unlocalizedName + "tile")) {
                if (TrainsInMotion.proxy.isClient() && TESR != null) {
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
        if (DebugUtil.dev && TrainsInMotion.proxy != null && TrainsInMotion.proxy.isClient() && itm.getUnlocalizedName().equals(StatCollector.translateToLocal(itm.getUnlocalizedName()))) {
            DebugUtil.println("Item missing lang entry: " + itm.getUnlocalizedName());
        }
        if (TrainsInMotion.proxy.isClient() && itemRender != null) {
            MinecraftForgeClient.registerItemRenderer(itm, (IItemRenderer) itemRender);
            if (ClientProxy.preRenderModels) {
                ebf.tim.items.CustomItemModel.instance.renderItem(IItemRenderer.ItemRenderType.INVENTORY, new ItemStack(itm));
            }
        } else if (TrainsInMotion.proxy.isClient() && itm instanceof ItemTransport) {
            MinecraftForgeClient.registerItemRenderer(itm, ebf.tim.items.CustomItemModel.instance);
            if (ClientProxy.preRenderModels) {
                ebf.tim.items.CustomItemModel.instance.renderItem(IItemRenderer.ItemRenderType.INVENTORY, new ItemStack(itm));
            }
        } else if(TrainsInMotion.proxy.isClient()){
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

        Block block = new BlockTrainFluid(fluid, new MaterialLiquid(color)).setBlockName("block." + unlocalizedName.replace(".item", "")).setBlockTextureName(MODID + ":block_" + unlocalizedName);
        ((BlockTrainFluid) block).setModID(MODID);
        GameRegistry.registerBlock(block, "block." + unlocalizedName);
        if (TrainsInMotion.proxy.isClient()) {
            block.setBlockTextureName(MODID + ":" + unlocalizedName);
        }
        fluid.setBlock(block);


        Item bucket = new ItemBucket(block).setCreativeTab(tab).setContainerItem(Items.bucket);
        if (TrainsInMotion.proxy.isClient()) {
            bucket.setTextureName(MODID + ":bucket_" + unlocalizedName);
        }
        bucket.setUnlocalizedName(unlocalizedName + ".bucket");
        GameRegistry.registerItem(bucket, "fluid." + unlocalizedName + ".bucket");
        FluidContainerRegistry.registerFluidContainer(fluid, new ItemStack(bucket), new ItemStack(Items.bucket));


        if (DebugUtil.dev && TrainsInMotion.proxy.isClient()) {
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


    public static void registerTransports(String MODID, List<GenericRailTransport> entities, Object entityRender) {
        registerTransports(MODID,entities.toArray(new GenericRailTransport[]{}), entityRender);
    }

    public static void registerTransports(String MODID, GenericRailTransport[] entities, Object entityRender) {
        if (registryPosition == -1) {
            DebugUtil.println("ERROR", "ADDING TRANSPORT REGISTRY ITEMS OUTSIDE MOD INIT", "PLEASE REGISTER YOUR ENTITIES IN THE FOLLOWING EVENT:",
                    "@Mod.EventHandler public void init(FMLInitializationEvent event)");
        }
        for (GenericRailTransport registry : entities) {
            if (DebugUtil.dev && usedNames.contains(registry.transportName())) {
                DebugUtil.println(registry.getClass().getName(), "is trying to register under the name", usedNames.contains(registry.transportName()), "which is already used");
                DebugUtil.throwStackTrace();
            }
            cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(
                    registry.getClass(),
                    registry.transportName().replace(" ", "") + ".entity",
                    registryPosition, TrainsInMotion.instance, 1600, 3, true);
            GameRegistry.registerItem(registry.getCartItem().getItem(), registry.getCartItem().getItem().getUnlocalizedName());
            if (registry.getRecipe() != null) {
                if (!CommonProxy.recipesInMods.containsKey(MODID)) {
                    CommonProxy.recipesInMods.put(MODID, new ArrayList<Recipe>());
                }
                CommonProxy.recipesInMods.get(MODID).add(getRecipe(registry.getRecipe(), registry.getCartItem()));

            }
            registry.registerSkins();
            if (registry.getRecipe() != null) {
                RecipeManager.registerRecipe(registry.getRecipe(), registry.getCartItem(), registry.getCraftingTable());
            }
            ItemCraftGuide.itemEntries.add(registry.getClass());
            if (TrainsInMotion.proxy.isClient()) {
                regEntityRender(registry, entityRender);
            }
            usedNames.add(registry.transportName());
            registryPosition++;
        }
    }

    @SideOnly(Side.CLIENT)
    private static void regEntityRender(GenericRailTransport registry, Object entityRender){

        if (ClientProxy.hdTransportItems) {
            MinecraftForgeClient.registerItemRenderer(registry.getCartItem().getItem(), ebf.tim.items.CustomItemModel.instance);
        }

        if (entityRender == null) {
            cpw.mods.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler(registry.getClass(), (net.minecraft.client.renderer.entity.Render) TrainsInMotion.proxy.getEntityRender());
            if (ClientProxy.preRenderModels) {
                ((net.minecraft.client.renderer.entity.Render) TrainsInMotion.proxy.getEntityRender()).doRender(registry, 0, 0, 0, 0, 0);
            }
        } else {
            cpw.mods.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler(registry.getClass(), (net.minecraft.client.renderer.entity.Render) entityRender);
            if (ClientProxy.preRenderModels) {
                ((net.minecraft.client.renderer.entity.Render) entityRender).doRender(registry, 0, 0, 0, 0, 0);
            }
        }
        if (ClientProxy.preRenderModels && ClientProxy.hdTransportItems) {
            ebf.tim.items.CustomItemModel.instance.renderItem(IItemRenderer.ItemRenderType.INVENTORY, registry.getCartItem());
        }
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
            cpw.mods.fml.client.registry.ClientRegistry.bindTileEntitySpecialRenderer(tile, (TileEntitySpecialRenderer) TrainsInMotion.proxy.getTESR());
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
