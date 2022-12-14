package ebf.tim.utility;

import cpw.mods.fml.common.registry.GameRegistry;
import ebf.tim.TrainsInMotion;
import ebf.tim.blocks.TileEntityStorage;
import ebf.tim.items.ItemRail;
import ebf.tim.items.ItemTransport;
import ebf.tim.registry.TiMItems;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import train.core.handlers.ConfigHandler;
import train.entity.rollingStock.EntityTracksBuilder;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class RecipeManager {

    private static HashMap<Block, List<Recipe>> recipeList = new HashMap<>();
    //private static List<Item> ingotDirectory = new ArrayList<>();


    public static void registerRecipe(Object[] recipe, ItemStack output, Block table){
        registerRecipe(getRecipe(recipe, output), table);
    }

    public static void registerRecipe(Recipe recipe, Block table){
        if(!recipeList.containsKey(table)){
            List<Recipe> r = new ArrayList<Recipe>();
            r.add(recipe);
            recipeList.put(table, r);
        } else {
            recipeList.get(table).add(recipe);
        }

        //todo: in later MC versions add function for IDE to write the recipe to a json in editor, and load it from json normally
    }


    public static Recipe getRecipe(ItemStack result, Block table){
        //if table is null, check all tables
        if(table==null){
            for(Block t : recipeList.keySet()){
                if(t!=null) {
                    Recipe r = getRecipe(result, t);
                    if (r != null) {
                        return r;
                    }
                }
            }
        }
        //if list is null, return null
        if(recipeList.get(table)==null){
            return null;
        }

        //check table list for recipe
        for(Recipe r : recipeList.get(table)){
            for(ItemStack stack : r.getresult()){
                if(stack==null || result==null){
                    if(stack==null && result==null){
                        return r;
                    }
                }
                else if(stack.getItem()==result.getItem()){
                    //TC enable/disable different types of stock functionality.
                    //faster to check the bools first, then check if it's actually valid, since most wont use this feature.
                    if((!ConfigHandler.ENABLE_DIESEL || !ConfigHandler.ENABLE_ELECTRIC || !ConfigHandler.ENABLE_STEAM)
                            && result.getItem() instanceof ItemTransport && ((ItemTransport)result.getItem()).types!=null) {
                        if (!ConfigHandler.ENABLE_DIESEL
                                && ((ItemTransport)result.getItem()).types.contains(TrainsInMotion.transportTypes.DIESEL)) {
                            return null;
                        }
                        if (!ConfigHandler.ENABLE_ELECTRIC
                                && ((ItemTransport)result.getItem()).types.contains(TrainsInMotion.transportTypes.ELECTRIC)) {
                            return null;
                        }
                        if (!ConfigHandler.ENABLE_STEAM
                                && ((ItemTransport)result.getItem()).types.contains(TrainsInMotion.transportTypes.STEAM)) {
                            return null;
                        }
                    }

                    return r;
                }
            }

        }
        return null;
    }

    public static Block getRecipeTable(ItemStack result){
        for(Block b : recipeList.keySet()) {
            for (Recipe r : recipeList.get(b)) {
                for (ItemStack stack : r.getresult()) {
                    if (stack == null || result == null) {
                        if (stack == null && result == null) {
                            return b;
                        }
                    } else if (stack.getItem() == result.getItem()) {
                        //TC enable/disable different types of stock functionality.
                        //faster to check the bools first, then check if it's actually valid, since most wont use this feature.
                        if ((!ConfigHandler.ENABLE_DIESEL || !ConfigHandler.ENABLE_ELECTRIC || !ConfigHandler.ENABLE_STEAM)
                                && result.getItem() instanceof ItemTransport && ((ItemTransport) result.getItem()).types != null) {
                            if (!ConfigHandler.ENABLE_DIESEL
                                    && ((ItemTransport) result.getItem()).types.contains(TrainsInMotion.transportTypes.DIESEL)) {
                                return null;
                            }
                            if (!ConfigHandler.ENABLE_ELECTRIC
                                    && ((ItemTransport) result.getItem()).types.contains(TrainsInMotion.transportTypes.ELECTRIC)) {
                                return null;
                            }
                            if (!ConfigHandler.ENABLE_STEAM
                                    && ((ItemTransport) result.getItem()).types.contains(TrainsInMotion.transportTypes.STEAM)) {
                                return null;
                            }
                        }

                        return b;
                    }
                }

            }
        }
        return null;
    }

    /**Compares and returns a list of trains that are craftable with the given array of ItemStacks (the inputted recipe)
     * Funnily enough, in wanting to have a tier-less traintable, I implemented a fourth tier, tier 0.
     *
     * @param recipe An array of ItemStacks that could be a valid recipe.
     * @param table The block that the crafting is being done on
     * @return A List of ItemStacks that are trains craftable with the recipe parameter. Null if nothing craftable.
     */
    public static List<ItemStack> getResult(ItemStack[] recipe, Block table){

        //more advanced inventory empty check because of variable recipe size possible
        boolean empty = true;
        for (ItemStack is : recipe) {
            if (is != null) {
                empty = false;
                break;
            }
        }
        if (empty) return null;

        List<ItemStack> retStacks = new ArrayList<>();
        boolean canAdd;

        if(recipeList.get(table)==null){
            return null;
        }
        for(Recipe r : recipeList.get(table)){
            if (r.inputMatches(Arrays.asList(recipe))) {
                for(ItemStack res : r.result) {
                    canAdd=true;
                    if(res.getItem() instanceof ItemTransport) {
                        if (!ConfigHandler.ENABLE_STEAM) {
                            canAdd=!((ItemTransport)res.getItem()).types.contains(TrainsInMotion.transportTypes.STEAM);
                        }
                        if (!ConfigHandler.ENABLE_DIESEL) {
                            canAdd=!((ItemTransport)res.getItem()).types.contains(TrainsInMotion.transportTypes.DIESEL);
                        }
                        if (!ConfigHandler.ENABLE_ELECTRIC) {
                            canAdd=!((ItemTransport)res.getItem()).types.contains(TrainsInMotion.transportTypes.ELECTRIC);
                        }
                        if (!ConfigHandler.ENABLE_TENDER) {
                            canAdd=!((ItemTransport)res.getItem()).types.contains(TrainsInMotion.transportTypes.TENDER);
                        }
                        if (!ConfigHandler.ENABLE_BUILDER) {
                            canAdd=res.getItem()!=EntityTracksBuilder.thisItem;
                        }
                    }
                    if(canAdd) {
                        retStacks.add(res);
                    }
                }
            }
        }
        if(retStacks.size()==0) {
            return null;
        } else {
            return retStacks;
        }
    }

    public static List<Recipe> getRecipesContaining(ItemStack itemStack, Block table) {
        ArrayList<Recipe> containing = new ArrayList<>();
        for (Recipe recipe : recipeList.get(table)) {
            boolean hasFound = false;
            for (List<ItemStack> ingredient : recipe.getRecipeItems()) {
                if (ingredient == null) continue;
                for (ItemStack permutation : ingredient) {
                    if (permutation == null) break;
                    if (OreDictionary.itemMatches(permutation, itemStack, false)) {
                        containing.add(recipe);
                        hasFound = true;
                        break;
                    }
                }
                if (hasFound) break;
            }
        }
        return containing;
    }

    /**
     * Crafting table related stuff
     */

    public static  @Nullable ItemStack getStackBallast(IInventory hostInventory){
        if(hostInventory.getStackInSlot(4)==null || hostInventory.getStackInSlot(4).getTagCompound()==null){
            return null;
        } else {
            return !hostInventory.getStackInSlot(4).getTagCompound().hasKey("ballast") ? null :
                    ItemStack.loadItemStackFromNBT(hostInventory.getStackInSlot(4).getTagCompound().getCompoundTag("ballast"));
        }
    }
    public static @Nullable ItemStack getStackTies(IInventory hostInventory){
        if(hostInventory.getStackInSlot(4)==null || hostInventory.getStackInSlot(4).getTagCompound()==null){
            return null;
        } else {
            return !hostInventory.getStackInSlot(4).getTagCompound().hasKey("ties") ? null :
                    ItemStack.loadItemStackFromNBT(hostInventory.getStackInSlot(4).getTagCompound().getCompoundTag("ties"));
        }
    }
    public static @Nullable ItemStack getStackIngot(IInventory hostInventory){
        if(hostInventory.getStackInSlot(4)==null || hostInventory.getStackInSlot(4).getTagCompound()==null){
            return null;
        } else {
            return !hostInventory.getStackInSlot(4).getTagCompound().hasKey("ingot") ? null :
                    ItemStack.loadItemStackFromNBT(hostInventory.getStackInSlot(4).getTagCompound().getCompoundTag("ingot"));
        }
    }


    public static List<ItemStack> getAcceptedRailItems(){
        List<ItemStack> Ores=new ArrayList<>();

        Ores.add(new ItemStack(Items.diamond));
        Ores.add(new ItemStack(Items.blaze_rod));

        for(String o: OreDictionary.getOreNames()) {
            if (o.contains("ingot") || o.contains("plank")) {
                Ores.addAll(OreDictionary.getOres(o));
            }
        }
        return Ores;
    }

    /**
     * Gets the stacks in the crafting part of the provided IInventory. This is only used by a TileEntityStorage
     * hostInventory but a fallback (original implementation) is left for safety.
     *
     * @precondition hostInventory, if a TileEntityStorage, must have all the crafting slots consecutively
     * @param hostInventory the inventory to get the recipe from.
     * @return an array of the ItemStacks that comprise the crafting recipe in the crafting input slots.
     */
    public static ItemStack[] getTransportRecipe(IInventory hostInventory){
        if (hostInventory instanceof TileEntityStorage) {
            TileEntityStorage hostInv = (TileEntityStorage) hostInventory;

            //generic algorithm to get all crafting slots without knowing amount crafting slots
            boolean isCrafting = false; //true if we are in the midst of recipes.
            ArrayList<ItemStack> craftingSlotsInterim = new ArrayList<>();
            for (ItemStackSlot slot : hostInv.inventory) { //iterate through each slot of inventory
                if (slot.isCraftingInput()) {
                    isCrafting = true;
                } else if (slot.isCraftingOutput()) {
                    break; //reached the end of crafting
                }
                if (isCrafting) { //cannot be else if
                    craftingSlotsInterim.add(slot.getStack());
                }
            }
            ItemStack[] recipe = new ItemStack[craftingSlotsInterim.size()];
            recipe = craftingSlotsInterim.toArray(recipe);
            return recipe;

        }
        return new ItemStack[]{
                hostInventory.getStackInSlot(0),hostInventory.getStackInSlot(1),hostInventory.getStackInSlot(2),
                hostInventory.getStackInSlot(3),hostInventory.getStackInSlot(4),hostInventory.getStackInSlot(5),
                hostInventory.getStackInSlot(6),hostInventory.getStackInSlot(7),hostInventory.getStackInSlot(8),
        };
    }

    public static ItemStack railRecipe(IInventory hostInventory){
        //handle adding to an existing stack
        if(hostInventory.getStackInSlot(5)!=null && hostInventory.getStackInSlot(5).getItem() instanceof ItemRail &&
                hostInventory.getStackInSlot(0)==getStackIngot(hostInventory) &&
                hostInventory.getStackInSlot(1)==getStackTies(hostInventory) &&
                hostInventory.getStackInSlot(2)==getStackBallast(hostInventory)){

            ItemStack rail = ItemRail.setStackData(new ItemStack(TiMItems.railItem),
                    hostInventory.getStackInSlot(0),hostInventory.getStackInSlot(1),hostInventory.getStackInSlot(2),
                    null);
            return rail;
        }
        //handle making a new stack
        if(hostInventory.getStackInSlot(0)!=null && ingotInDirectory(hostInventory.getStackInSlot(0).getItem())) {
            return ItemRail.setStackData(new ItemStack(TiMItems.railItem),
                    hostInventory.getStackInSlot(0),hostInventory.getStackInSlot(2),hostInventory.getStackInSlot(1),
                    null);
        }
        //todo: add support for augument slot
        return null;

    }

    public static boolean ingotInDirectory(Item i){
        for(ItemStack stack : getAcceptedRailItems()){
            if (stack !=null && stack.getItem()==i){
                return true;
            }
        }
        return false;
    }

    public static Recipe getRecipe(Object[] obj, ItemStack cartItem){
        List<ItemStack> result = new ArrayList<>();
        result.add(cartItem);
        List<List<ItemStack>> ingred = new ArrayList<>();
        for (Object ingredient : obj) {
            ingred.add(Arrays.asList(getItem(ingredient)));
        }
        return new Recipe(result, ingred);
    }


    //old getRecipe
//    public static Recipe getRecipe(Object[] obj, ItemStack cartItem){
//        Recipe r = new Recipe(new ItemStack[]{cartItem},
//                getItem(obj[0]),
//                getItem(obj[1]),
//                getItem(obj[2]),
//                getItem(obj[3]),
//                getItem(obj[4]),
//                getItem(obj[5]),
//                getItem(obj[6]),
//                getItem(obj[7]),
//                getItem(obj[8])
//        );
//        return r;
//    }

    public static ItemStack[] getItem(Object itm){
        ItemStack[] list = new ItemStack[1];
        if(itm==null){
            return list;
        }
        if(itm instanceof ItemStack){
            list=ODC((ItemStack)itm);
        }
        else if (itm instanceof ItemBlock){
            list=ODC(new ItemStack((ItemBlock)itm));
        }
        else if (itm instanceof Item){
            list=ODC(new ItemStack((Item)itm));
        }
        else if (itm instanceof Block) {
            list=ODC(new ItemStack(Item.getItemFromBlock((Block)itm)));
        }
        else if(itm instanceof String){
            String[] data = ((String) itm).split(" ");
            int stacksize = data.length>1?Integer.parseInt(data[1].trim()):1;
            //cover actual items
            if(data[0].contains(":")){
                list=ODC(GameRegistry.findItemStack(data[0].split(":")[0], data[0].split(":")[1], stacksize));
            } else {
                //cover ore directory values
                list=OreDictionary.getOres(data[0]).toArray(new ItemStack[]{});
                for(ItemStack s : list){
                    s.stackSize=stacksize;
                }
            }

        }
        return list;
    }

    /**
     * Ore Directory Converter
     * converts any input to the ore directory version so recipes will have automatic ore directory support
     *
     * todo: update this to not use depreciated func, make more robust/efficient
     */
    public static ItemStack[] ODC(ItemStack s){
        if(s==null){
            return null;
        }
        //cache the old size and set it to 1, the ore directory only contains entries with a stacksize of 1,
        //   anything else can break the equals check.
        int oldSize = s.stackSize;
        s.stackSize=1;

        List<ItemStack> dir = new ArrayList<>();
        //create a list of ore directory entries
        for(int oreID : OreDictionary.getOreIDs(s)){
            for (ItemStack ore : OreDictionary.getOres(oreID)) {
                dir.add(ore.copy());
            }
        }
        if(dir.size()>0) {
            for (ItemStack stack : dir) {
                stack.stackSize = oldSize;
            }
        } else {
            s.stackSize=oldSize;
            dir.add(s);
        }
        return dir.toArray(new ItemStack[]{});

    }


}
