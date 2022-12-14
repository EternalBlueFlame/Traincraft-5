package ebf.tim.utility;

import ebf.XmlBuilder;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Recipe {

    /**
     * All the items, whether it be transports, parts, etc. that this recipe can craft.
     */
    public List<ItemStack> result = new ArrayList<>();

    /**
     * A list of all ingredients that the recipe requires to be able to craft the items in result. Generally in order
     * from left-to-right, top-to-bottom, as if you were reading the slots. Size depends on the tier of table you want
     * to use to craft, but can be less. The TC assembly tables is 10slots, but TiM table is 9.
     * If the size of input is less than the number of crafting slots in the table, the missing slots are null.
     */
    public final List<List<ItemStack>> input;


    private int[] displayItem=new int[]{0,0,0,0,0,0,0,0,0,0}; //idk what this for, but it will have to be changed for supporting 10 input slots


    public Recipe(List<ItemStack> results, List<List<ItemStack>> cost) {
        this.result = results;
        this.input = cost;
    }

    public Recipe(ItemStack results, List<List<ItemStack>> cost) {
        this.result = Collections.singletonList(results);
        this.input = cost;
    }

    public void addResults(List<ItemStack> results){
        result.addAll(results);
    }

    @Deprecated
    public Recipe(ItemStack[] resultItems,
            ItemStack[] topLeft, ItemStack[] topCenter, ItemStack[] topRight,
                  ItemStack[] middleLeft, ItemStack[] middleCenter, ItemStack[] middleRight,
                  ItemStack[] bottomLeft, ItemStack[] bottomCenter, ItemStack[] bottomRight){

        result.addAll(Arrays.asList(resultItems));

        List<List<ItemStack>> recipeInProgress = new ArrayList<>();
        recipeInProgress.add(Arrays.asList(topLeft));
        recipeInProgress.add(Arrays.asList(topCenter));
        recipeInProgress.add(Arrays.asList(topRight));

        recipeInProgress.add(Arrays.asList(middleLeft));
        recipeInProgress.add(Arrays.asList(middleCenter));
        recipeInProgress.add(Arrays.asList(middleRight));

        recipeInProgress.add(Arrays.asList(bottomLeft));
        recipeInProgress.add(Arrays.asList(bottomCenter));
        recipeInProgress.add(Arrays.asList(bottomRight));

        this.input = recipeInProgress;
    }

    @Deprecated
    public Recipe(ItemStack result,
                  ItemStack topLeft, ItemStack topCenter, ItemStack topRight,
                  ItemStack middleLeft, ItemStack middleCenter, ItemStack middleRight,
                  ItemStack bottomLeft, ItemStack bottomCenter, ItemStack bottomRight){

        this.result.add(result);

        List<List<ItemStack>> recipeInProgress = new ArrayList<>();

        recipeInProgress.add(Collections.singletonList(topLeft));
        recipeInProgress.add(Collections.singletonList(topCenter));
        recipeInProgress.add(Collections.singletonList(topRight));

        recipeInProgress.add(Collections.singletonList(middleLeft));
        recipeInProgress.add(Collections.singletonList(middleCenter));
        recipeInProgress.add(Collections.singletonList(middleRight));

        recipeInProgress.add(Collections.singletonList(bottomLeft));
        recipeInProgress.add(Collections.singletonList(bottomCenter));
        recipeInProgress.add(Collections.singletonList(bottomRight));

        this.input = recipeInProgress;
    }

    //gets the individual stacks to check for crafting matches
    public List<ItemStack> topLeft(){return input.get(0);}
    public List<ItemStack> topCenter(){return input.get(1);}
    public List<ItemStack> topRight(){return input.get(2);}

    public List<ItemStack> middleLeft(){return input.get(3);}
    public List<ItemStack> middleCenter(){return input.get(4);}
    public List<ItemStack> middleRight(){return input.get(5);}

    public List<ItemStack> bottomLeft(){return input.get(6);}
    public List<ItemStack> bottomCenter(){return input.get(7);}
    public List<ItemStack> bottomRight(){return input.get(8);}

    public List<ItemStack> getresult(){return result;}

    public List<List<ItemStack>> getRecipeItems() {
        return input;
    }

    public void nextDisplayItem(){
        for(int i=0;i<input.size();i++) {
            displayItem[i]++;
            if (displayItem[i] >= input.get(i).size()) {
                displayItem[i] = 0;
            }
        }
    }

    //gets the list of itemstacks that should display on the crafting guide
    public ItemStack[] getDisplayArray(){
        return new ItemStack[]{
                input.get(0).get(displayItem[0]),input.get(1).get(displayItem[1]),input.get(2).get(displayItem[2]),
                input.get(3).get(displayItem[3]),input.get(4).get(displayItem[4]),input.get(5).get(displayItem[5]),
                input.get(6).get(displayItem[6]),input.get(7).get(displayItem[7]),input.get(8).get(displayItem[8]),
                result.get(displayItem[9])
        };
    }


    /**
     * The recipe checking when player is using the table. Simpler than the other because the player can only put one
     * itemstack in each slot.
     *
     * @param stacks The stacks that the player has put in.
     * @return If the stacks parameter matches the recipe's input and can be crafted.
     */
    public boolean inputMatches(List<ItemStack> stacks){
        //first make sure that stacks isn't too small, ie. recipe for 9 slots and stacks is 10
        if (stacks.size() < input.size()) return false;

        int i=0;
        for(List<ItemStack> slot : input){ //each ing
            boolean isMatching = false;
            if(slot!=null) {
                for (ItemStack s : slot) { //possible items for ing

                    isMatching = compareItemsAndSize(s, stacks.get(i));

                    if (isMatching) break;
                }
                if (!isMatching) return false;
            }
            i++;
        }
        return true;
    }


    /**
     * This is the input matching that is used in the RecipeManager when checking to see if to add a transport to this
     * recipe.
     * @param stacks
     * @return
     */
    public boolean recipeInputMatches(List<List<ItemStack>> stacks){ //is this correctly comparing?
        int i=0;
        boolean slotClear=false;
        for(List<ItemStack> slot : input){//iterate through the recipe's ingredients
            if (stacks.size() <= i){return false;} //recipes are variable length, terminate if it gets to the end without success
            for(ItemStack s : slot){//iterate through the items that fit as the recipe's ingredient
                for(ItemStack stak : stacks.get(i)) { //iterate the items that fit as that ingredient in stacks
                    slotClear = compareItemsAndSize(s, stak);
                }
                if(!slotClear){return false;}
            }
            i++;
        }
        return true;
    }

    /**
     * Compares the toCompare item to the target item. Compares if the the items are equivalent and if the toCompare size
     * is greater than the target's size. Also checks if the target is a wildcard item, and adjusts accordingly. Also
     * returns true if both are null.
     * @param target target ItemStack, can be a wildcard, and toCompare should be at least this size
     * @param toCompare ItemStack being compared.
     * @return true if toCompare is the same item and is equal to or bigger than target.
     */
    private static boolean compareItemsAndSize(ItemStack target, ItemStack toCompare) {
        //checking to make sure both null or both not null
        if(target==null && toCompare == null) {
            //both must be null otherwise stacks.get(i) could have something and it falsely matches
            return true;
        } else if (target == null || toCompare == null){
            return false;
        }

        //items not equal or stack isn't big enough, or stack not big enough
        return OreDictionary.itemMatches(target, toCompare, false) && target.stackSize <= toCompare.stackSize;
    }



    public String saveRecipe(){
        XmlBuilder xml = new XmlBuilder();
        XmlBuilder slot;
        int ii=0,i=0;
        for (List<ItemStack> slots : input){
            slot = new XmlBuilder();
            for(ItemStack s : slots){
                slot.putItemStack("variant "+ii,s);
                ii++;
            }
            xml.putXml("slot " +i, slot);
            i++;
        }
        return xml.toXMLString();
    }

    //TODO: this. What is the intended use?
//    public Recipe loadRecipe(String s){
//        XmlBuilder xml = new XmlBuilder(s);
//        input = new ArrayList<>();
//        for (int i=0;i<9;i++){
//            List<ItemStack> list=new ArrayList<>();
//
//            XmlBuilder builder = xml.getXml("slot "+i);
//            for(String stack : builder.itemMap.keySet()) {
//                list.add(builder.getItemStack(stack));
//            }
//            input.add(list);
//
//        }
//
//        return this;
//    }

}
