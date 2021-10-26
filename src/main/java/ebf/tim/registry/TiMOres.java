package ebf.tim.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import ebf.tim.TrainsInMotion;
import ebf.tim.blocks.OreGen;
import ebf.tim.blocks.SimpleBlock;
import ebf.tim.utility.CommonProxy;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static cpw.mods.fml.common.registry.GameRegistry.addRecipe;
import static cpw.mods.fml.common.registry.GameRegistry.addShapelessRecipe;
import static ebf.tim.registry.TiMGenericRegistry.registerOreGen;
import static net.minecraftforge.oredict.OreDictionary.registerOre;

public class TiMOres {

    public static Item ingotSteel, ingotCopper, ingotAluminum, dustPlastic, dustCoal;

    public static SimpleBlock blockCopper;
    public static SimpleBlock blockAluminum;
    public static SimpleBlock blockSteel;

    public static SimpleBlock oreCopper;
    public static SimpleBlock oreAluminum;
    public static SimpleBlock oreSteel;


    public static void registerOres(){

        //create all items/blocks

        blockCopper = new SimpleBlock(4.0F, 4.0F, "blockCopper", TrainsInMotion.MODID + ":ores/block.copper");
        blockAluminum = new SimpleBlock(4.0F, 4.0F, "blockAluminum", TrainsInMotion.MODID + ":ores/block.aluminum");
        blockSteel = new SimpleBlock(5.0F, 5.0F, "blockSteel", TrainsInMotion.MODID + ":ores/block.steel");

        oreCopper = new SimpleBlock(3.0F, 3.0F, "oreCopper", TrainsInMotion.MODID + ":ores/ore.copper");
        oreAluminum = new SimpleBlock(3.0F, 3.0F, "oreAluminum", TrainsInMotion.MODID + ":ores/ore.aluminum");
        oreSteel = new SimpleBlock(3.0F, 3.0F, "oreSteel", TrainsInMotion.MODID + ":ores/ore.steel");

        ingotSteel = createItem("ingot.steel", "ingotSteel").setTextureName(TrainsInMotion.MODID + ":materials/ingot.steel");
        dustPlastic = createItem("dust.plastic", "dustPlastic").setTextureName(TrainsInMotion.MODID + ":materials/dust.plastic");
        dustCoal = createItem("dust.coal", "dustCoal").setTextureName(TrainsInMotion.MODID + ":materials/dust.coal");
        ingotCopper = createItem("ingot.copper", "ingotCopper").setTextureName(TrainsInMotion.MODID + ":materials/ingot.copper");
        ingotAluminum = createItem("ingot.aluminum", "ingotAluminum").setTextureName(TrainsInMotion.MODID + ":materials/ingot.aluminum");


        //add recipes and smelting

        addRecipe(new ItemStack(blockCopper), "III","III","III", 'I', ingotCopper);
        addRecipe(new ItemStack(blockSteel), "III","III","III", 'I', ingotSteel);
        addRecipe(new ItemStack(blockAluminum), "III","III","III", 'I', ingotAluminum);

        addShapelessRecipe(new ItemStack(ingotCopper, 9), blockCopper);
        addShapelessRecipe(new ItemStack(ingotAluminum, 9), blockAluminum);
        addShapelessRecipe(new ItemStack(ingotSteel, 9), blockSteel);

        GameRegistry.addSmelting(oreCopper, new ItemStack(ingotCopper), 0.7f);
        GameRegistry.addSmelting(oreSteel, new ItemStack(ingotSteel), 1.2f);
        GameRegistry.addSmelting(oreAluminum, new ItemStack(ingotAluminum), 0.5f);


        //register into ore dictionary

        registerOre("oreCopper", new ItemStack(oreCopper));
        registerOre("oreAluminium", new ItemStack(oreAluminum));
        registerOre("oreAluminum", new ItemStack(oreAluminum));
        registerOre("oreSteel", new ItemStack(oreSteel));

        registerOre("blockCopper", new ItemStack(blockCopper));
        registerOre("blockAluminium", new ItemStack(blockAluminum));
        registerOre("blockAluminum", new ItemStack(blockAluminum));
        registerOre("blockSteel", new ItemStack(blockSteel));

        registerOre("ingotCopper", new ItemStack(ingotCopper));
        registerOre("ingotSteel", new ItemStack(ingotSteel));
        registerOre("ingotAluminum", new ItemStack(ingotAluminum));
        registerOre("ingotAluminium", new ItemStack(ingotAluminum)); //what spelling is accepted?

        registerOre("itemPlastic", new ItemStack(dustPlastic));
        registerOre("dustPlastic", new ItemStack(dustPlastic)); //MFR support


        //register copper for generation
        if (CommonProxy.doCopperGeneration) {
            registerOreGen(3, new OreGen(oreCopper, 5, 50, 6, 4, 6));
        }
        if (CommonProxy.doSteelGeneration) {
            //nether ore, lava oceans gen at y=31
            OreGen steelGen = new OreGen(oreSteel, 31, 50, 8, 4, 5).setDimensions(new Integer[]{-1}).setFiller(Blocks.netherrack);
            registerOreGen(5, steelGen);
            //make steel especially common under lava. Want high generation because it is used a lot.
            OreGen steelGen2 = new OreGen(oreSteel, 1, 30, 4, 3, 10).setDimensions(new Integer[]{-1}).setFiller(Blocks.netherrack);
            registerOreGen(5, steelGen2);
        }
        if (CommonProxy.doAluminumGeneration) {
            registerOreGen(5, new OreGen(oreAluminum, 12, 30, 6, 3, 4));
        }
    }

    private static Item createItem(String unlocalizedName, String oredictName) {
        Item item = new Item();
        TiMGenericRegistry.RegisterItem(item, TrainsInMotion.MODID, unlocalizedName, oredictName, TrainsInMotion.creativeTabCrafting, null, null);
        return item;
    }
}
