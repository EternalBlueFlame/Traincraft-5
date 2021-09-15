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

import javax.annotation.Nullable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecipeManager {

    private static List<Recipe> recipeList = new ArrayList<>();
    //private static List<Item> ingotDirectory = new ArrayList<>();


    public static void registerRecipe(Object[] recipe, ItemStack output, int tier){
        registerRecipe(getRecipe(recipe, output).setTier(tier));
    }
    public static void registerRecipe(Object[] recipe, ItemStack output){
        registerRecipe(getRecipe(recipe, output));
    }
    public static void registerRecipe(Object[] recipe, Item output){
        registerRecipe(getRecipe(recipe, new ItemStack(output)));
    }

    public static void registerRecipe(Recipe recipe){
/*        DebugUtil.println("REGISTERING RECIPE"
        , (recipe.topLeft()==null || recipe.topLeft().size()==0 || recipe.topLeft().get(0)==null?"null": recipe.topLeft().get(0).getDisplayName()),
                recipe.getresult().get(0).getDisplayName());*/

        // adds a result to the recipe if it already exists, rather than creating a new one.
        for(Recipe r : recipeList){
            if ((r instanceof SizedRecipe) == (recipe instanceof SizedRecipe)) { //either both sized or both not

                if (r instanceof SizedRecipe) { //both sized
                    if (((SizedRecipe) r).getCraftHeight() != ((SizedRecipe) recipe).getCraftHeight() ||
                            ((SizedRecipe) r).getCraftWidth() != ((SizedRecipe) recipe).getCraftWidth()) {
                        //one of dimensions don't match
                        continue;
                    } else {
                        if (((SizedRecipe) r).recipeInputMatches(recipe.input, ((SizedRecipe) r).getCraftWidth(), ((SizedRecipe) r).getCraftHeight())) {
                            if (r.getTier() == recipe.getTier()) {
                                r.addResults(recipe.result);
                                return;
                            }
                        }
                    }
                } else {
                    //not sized
                    if (r.recipeInputMatches(recipe.input)) {
                        if (r.getTier() == recipe.getTier()) {
                            r.addResults(recipe.result);
                            return;
                        }
                    }
                }
            }
        }

        recipeList.add(recipe);

        //todo: in later MC versions add function for IDE to write the recipe to a json in editor, and load it from json normally
    }


    public static Recipe getRecipe(ItemStack result){
        for(Recipe r : recipeList){
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

    /**Compares and returns a list of trains that are craftable with the given array of ItemStacks (the inputted recipe)
     * Funnily enough, in wanting to have a tier-less traintable, I implemented a fourth tier, tier 0.
     *
     * @param recipe An array of ItemStacks that could be a valid recipe.
     * @param tier The tier to compare recipes against. Will only look for results in given tier.
     * @return A List of ItemStacks that are trains craftable with the recipe parameter. Null if nothing craftable.
     */
    public static List<ItemStack> getResult(ItemStack[] recipe, int tier){

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
        for(Recipe r : recipeList){
            if(r.getTier() == tier) { //compare tier first for speed (and to avoid incorrect dimensions)
                if (r.inputMatches(Arrays.asList(recipe))) {
                    retStacks.addAll(r.result);
                }
            }
        }
        if(retStacks.size()==0) {
            return null;
        } else {
            return retStacks;
        }
    }

    public static List<Recipe> getRecipesContaining(ItemStack itemStack, int tier) {
        ArrayList<Recipe> containing = new ArrayList<>();
        for (Recipe recipe : recipeList) {
            boolean hasFound = false;
            if (recipe.getTier() != tier) continue;
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

    //This is necessary because the GenericRegistry will have to set the tier on the recipe somehow, in case it is set on the class.
    public static Recipe getRecipeWithTier(Object[] obj, ItemStack cartItem, int tier){
        Recipe r = new Recipe(new ItemStack[]{cartItem},
                getItem(obj[0]),
                getItem(obj[1]),
                getItem(obj[2]),
                getItem(obj[3]),
                getItem(obj[4]),
                getItem(obj[5]),
                getItem(obj[6]),
                getItem(obj[7]),
                getItem(obj[8])
        );
        r.setTier(tier);
        return r;
    }

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
