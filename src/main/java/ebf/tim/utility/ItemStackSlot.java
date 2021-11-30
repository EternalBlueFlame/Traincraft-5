package ebf.tim.utility;

import ebf.tim.blocks.TileEntityStorage;
import ebf.tim.entities.GenericRailTransport;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eternal Blue Flame
 * TODO: add support for tile entities post 0.2.7.
 */
public class ItemStackSlot extends Slot {

    private ItemStack stack = null, overlay = null;
    private int slotID;
    private boolean isCraftingOutput = false, isCraftingInput = false;
    private int tierIn = 0; //The tier of assemblytable this slot is in, if applicable. Ignore if not applicable.

    public ItemStackSlot(IInventory host, int slot, int tier){
        super(host, slot, 0,0);
        this.slotNumber = slot;
        this.slotID = slot;
        this.tierIn = tier;
    }

    public ItemStackSlot(IInventory host, int slot){
        super(host, slot, 0,0);
        slotNumber=slot;
        slotID=slot;
    }

    public ItemStackSlot setCraftingOutput(boolean craft){
        isCraftingOutput =craft;
        return this;
    }

    public boolean isCraftingOutput() {
        return isCraftingOutput;
    }


    public ItemStackSlot setCraftingInput(boolean craft){
        isCraftingInput =craft;
        return this;
    }

    public boolean isCraftingInput() {
        return isCraftingInput;
    }

    public ItemStackSlot(IInventory host, int slot, int x, int y){
        super(host, slot, 0,0);
        slotID=slot;
        slotNumber=slot;
        xPos=x;
        yPos=y;
    }

    public ItemStackSlot setCoords(int x, int y){
        xPos=x;
        yPos=y;
        return this;
    }
    public ItemStackSlot setSlotID(int slot){
        slotID=slot;
        return this;
    }
    public ItemStackSlot setSlot(int slot){
        this.slotNumber=slot;
        return this;
    }

    public ItemStack getOverlay(){
        return overlay;
    }

    public ItemStackSlot setOverlay(ItemStack s){
        overlay=s;
        return this;
    }

    public ItemStackSlot setOverlay(Item s){
        overlay=new ItemStack(s);
        return this;
    }

    public ItemStackSlot setOverlay(Block s){
        overlay=new ItemStack(s);
        return this;
    }

    public Item getItem(){
        return getStack()!=null?getStack().getItem():null;
    }


    public NBTTagCompound writeToNBT(){
        return getStack()!=null?getStack().writeToNBT(new NBTTagCompound()):null;
    }

    public int getStackSize(){
        return getStack()!=null?getStack().getCount():0;
    }


    public ItemStack mergeStack(ItemStack itemStack, List<ItemStackSlot> hostInventory,int inventoryType){
        if (isItemValid(itemStack)) {

            if (!getHasStack()) {
                onCrafting(inventoryType,hostInventory,getStackSize());
                if(!setSlotContents(itemStack.copy(),hostInventory)){
                    return itemStack;
                }
                return null;
            } else if (contentEquals(itemStack) && getStackSize() < getStack().getMaxStackSize()) {
                if (itemStack.copy().getCount() + getStackSize() <= getStack().getMaxStackSize()) {
                    onCrafting(inventoryType,hostInventory,getStackSize());
                    setSlotStackSize(getStackSize() + itemStack.copy().getCount());
                    return null;
                } else {
                    itemStack.setCount(itemStack.getCount() - getStack().getMaxStackSize() - getStackSize());
                    setSlotStackSize(getStack().getMaxStackSize());
                    onCrafting(inventoryType,hostInventory,itemStack.getMaxStackSize()-itemStack.getCount());
                    if(itemStack.getCount()==0){
                        itemStack=null;
                    }
                    return itemStack;
                }
            }
        }
        return itemStack;
    }

    /**
     * Attempts to merge the ItemStack in given slot with itemstack (if any) in current slot. Returns what is left in the
     * slot it tried to take items out of (not this slot).
     *
     * @param itemStack ItemStackSlot containing item that should be moved into this slot.
     * @param hostInventory List of ItemStackSlots that is used to pass through to onCrafting function
     * @param storageType integer corresponding to the type of inventory it is moving in, passed through to onCrafting func
     * @return ItemStack of what is remaining in itemStack parameter.
     */
    public ItemStack mergeStack(ItemStackSlot itemStack, List<ItemStackSlot> hostInventory, int storageType){
        if (isItemValid(itemStack.getStack())) {

            if (!getHasStack()) { //no stack in current slot
                itemStack.onCrafting(storageType,hostInventory,itemStack.getStackSize());
                if(!setSlotContents(itemStack.getStack(),hostInventory)){
                    return itemStack.getStack();
                } else {
                    itemStack.setSlotContents(null, hostInventory);
                    return null;
                }
            } else {
                if (contentEquals(itemStack.getStack()) && getStack().getCount() < getStack().getMaxStackSize()) {
                    if(itemStack.getStackSize()+getStackSize()<=getStack().getMaxStackSize()){
                        itemStack.onCrafting(storageType,hostInventory,itemStack.getStackSize());
                        setSlotStackSize(getStack().getCount()+itemStack.getStackSize());
                        return null;
                    } else {
                        int difference=getStack().getMaxStackSize()-getStackSize();
                        itemStack.onCrafting(storageType,hostInventory,difference);
                        itemStack.decrStackSize(difference);
                        setSlotStackSize(getStack().getMaxStackSize());
                        return itemStack.getStack();
                    }
                }
            }
        }
        return itemStack.getStack();
    }


    public boolean contentEquals(ItemStack other){
        if(getStack()==null || other==null){
            return getStack()==null&& other==null;
        }
        return other.getItem()== getStack().getItem() && other.getTagCompound()== getStack().getTagCompound();
    }

    public int getMaxCraft(IInventory hostInventory, List<ItemStackSlot> hostSlots) {
        if (isCraftingOutput && hostInventory instanceof TileEntityStorage) {
            //must be an output slot, must be in an inventory that does crafting
            switch (((TileEntityStorage) hostInventory).storageType) {
                case 0:
                    //rail crafting table

                    //same as the original function's implementation
                    //  ie. out of non-zero stacks, return the size of the smallest stack

                    int smallestStack = ((TileEntityStorage) hostInventory).getSlotIndexByID(400).getStackSize();
                    if (smallestStack > 1) { //greater than 1 because if 1 the rest can't be smaller
                        for (int i = 0; i < 5; i++) {
                            int sizeOfStack = ((TileEntityStorage) hostInventory).getSlotIndexByID(400 + i).getStackSize();
                            if (sizeOfStack > 0 && sizeOfStack < smallestStack) {
                                smallestStack = sizeOfStack;
                            }
                        }
                    }
                    return smallestStack;
                case 1:
                    //train crafting system
                    Recipe recipe = RecipeManager.getRecipe(stack); //get the recipe that crafts item in this slot.

                    int largestAmountCanMake = 0;

                    if (recipe != null && recipe.getRecipeItems() != null) {
                        //make sure there is a recipe that crafts this.
                        largestAmountCanMake = 64;

                        if (recipe instanceof SizedRecipe) {
                            //for a sized recipe, things can be shifted all over, so we need to account for this

                            //get list of slots corresponding to crafting grid
                            List<ItemStackSlot> craftSlots = new ArrayList<>();
                            for (int i = 0; i < 9; i++) {
                                craftSlots.add(((TileEntityStorage) hostInventory).getSlotIndexByID(400 + i));
                            }

                            //get the stacks from slots
                            List<ItemStack> stacksToFindMin = new ArrayList<>();
                            for (ItemStackSlot slot : craftSlots) {
                                stacksToFindMin.add(slot.getStack());
                            }

                            //get amount to shift
                            int[] shiftHorVert = ((SizedRecipe) recipe).shiftSlotsBy(stacksToFindMin);

                            //subtract right amount of items from the craftSlots
                            for (int row = 0; row < ((SizedRecipe) recipe).getCraftHeight(); row++) {
                                for (int col = 0; col < ((SizedRecipe) recipe).getCraftWidth(); col++) {

                                    //get ingredient, item in inventory
                                    List<ItemStack> ingredient = recipe.input.get(row*((SizedRecipe) recipe).getCraftWidth() + col);
                                    if (ingredient == null) continue;

                                    int rowOfHostSlot = row + shiftHorVert[1];
                                    int colOfHostSlot = col + shiftHorVert[0];
                                    int realIndex = (rowOfHostSlot * 3) + colOfHostSlot;
                                    ItemStack itemInInv = craftSlots.get(realIndex).getStack();

                                    int amountCanMake = itemInInv.getCount() / ingredient.get(0).getCount();
                                    if (amountCanMake < largestAmountCanMake) {
                                        largestAmountCanMake = amountCanMake;
                                    }
                                }
                            }


                        } else {
                            for (int i = 0; i < recipe.getRecipeItems().size(); i++) {
                                //iterate over all ingredients in recipe

                                //both null means the counting doesn't matter for this ingredient.
                                if (recipe.getRecipeItems().get(i) == null && ((TileEntityStorage) hostInventory).getSlotIndexByID(400 + i).getStack() == null)
                                    continue;
                                if (recipe.getRecipeItems().get(i).get(0) == null && ((TileEntityStorage) hostInventory).getSlotIndexByID(400 + i).getStack() == null) {
                                    continue;
                                }

                                if (recipe.getRecipeItems().get(i).get(0) == null || recipe.getRecipeItems().get(i) == null) {
                                    //that's a problem, log it
                                    DebugUtil.println("There's a stack missing after the recipe has already been compared!");
                                    return 0; //can't craft any if this is the case
                                }

                                //for each ingredient, we want to see the most that can be crafted with the ingredient,
                                //  then take the lowest number, for the stack that can craft the least of that ingredient.
                                //  each ItemStack in the ingredient will have the same size, so we can use the first one because it's always there.
                                int amountCanMake = ((TileEntityStorage) hostInventory).getSlotIndexByID(400 + i).getStackSize() / recipe.getRecipeItems().get(i).get(0).getCount();
                                if (amountCanMake < largestAmountCanMake) {
                                    largestAmountCanMake = amountCanMake;
                                }
                            }
                        }
                    }
                    return largestAmountCanMake;
            }
        }
        return 0;
    }

    //seems to set the correct
    public void updatePage(TileEntityStorage hostInventory, List<ItemStackSlot> hostSlots) {
        List<ItemStack> slots = RecipeManager.getResult(RecipeManager.getTransportRecipe(hostInventory), this.tierIn);
        if (hostInventory.assemblyTableTier > 0) {
            putResultsInOutputSlots(hostInventory, hostSlots, slots, hostInventory.outputPage, 8);
        } else if (hostInventory.assemblyTableTier == 0) {
            putResultsInOutputSlots(hostInventory, hostSlots, slots, hostInventory.outputPage, 9);
        }
    }

    /**
     * Helper function to fill the output slots with the given stacks. This is a method to account for the 9 output slots
     * in TiM and the 8 in the Traincraft assemblytable.
     */
    private void putResultsInOutputSlots(IInventory hostInventory, List<ItemStackSlot> hostSlots, List<ItemStack> slots, int page, int numberSlots) {
        int startSlot = 409;
        if (tierIn > 0) startSlot = 410; //start one slot later for TC assemblytable
        if(slots==null){
            for (int i = 0; i < numberSlots; i++) {
                putStackInSlot(hostSlots,startSlot + i, null);
            }
            ((TileEntityStorage) hostInventory).pages = 1;
            ((TileEntityStorage) hostInventory).outputPage = 1;
        } else {
            if(slots.size() <= numberSlots) {
                for (int i = 0; i < numberSlots; i++) {
                    putStackInSlot(hostSlots,startSlot + i, i >= slots.size() ?null: slots.get(i));
                }
                ((TileEntityStorage)hostInventory).pages = 1;
                ((TileEntityStorage)hostInventory).outputPage = 1;
            } else {//skip 2 since buttons will be in their place.
                if (tierIn > 0) { //TC
                    for (int i = 0; i < 6; i++) {
                        if (i + (6 * (page - 1)) < slots.size()) { //if slot is in bounds
                            putStackInSlot(hostSlots, startSlot + i, slots.get(i + ((numberSlots - 2) * (page - 1))));
                        } else {
                            break;
                        }
                    }
                } else if (tierIn == 0) { //TiM
                    //TiM crafter, but buttons in awkward place coding-wise
                    int slotIncrementor = 409;
                    for (int i = 0; i < 7; i++) {
                        if (slotIncrementor == 412 || slotIncrementor == 414) { //skip these slots, fill with null
                            putStackInSlot(hostSlots, slotIncrementor, null);
                            slotIncrementor++;
                        }
                        if ((i + 7 * (page - 1)) < slots.size()) {
                            putStackInSlot(hostSlots,slotIncrementor, slots.get(i + 7 * (page - 1)));
                        } else {
                            break;
                        }

                        slotIncrementor++;
                    }
                }
                ((TileEntityStorage)hostInventory).pages = (slots.size()/(numberSlots-2)) + 1;
            }

            onSlotChanged();
        }
    }

    public void onCraftMatrixChanged(IInventory hostInventory, List<ItemStackSlot> hostSlots) {
        if((isCraftingInput || isCraftingOutput) && hostInventory instanceof TileEntityStorage && hostSlots.size() != 18) {
            int page = ((TileEntityStorage)hostInventory).outputPage;
            switch (((TileEntityStorage)hostInventory).storageType) {
                case 1: { //train crafting
                    List<ItemStack> slots = RecipeManager.getResult(RecipeManager.getTransportRecipe(hostInventory), this.tierIn);

                    if (CommonProxy.isTraincraft && tierIn > 0) {
                        putResultsInOutputSlots(hostInventory, hostSlots, slots, page, 8);
                    } else {
                        putResultsInOutputSlots(hostInventory, hostSlots, slots, page, 9);
                    }

                    ((TileEntityStorage) hostInventory).outputPage = 1;

                    break;
                }
                case 0: { //track crafting
                    putStackInSlot(hostSlots,406, RecipeManager.railRecipe(hostInventory));
                    break;
                }
            }
            onSlotChanged();
            //todo: vanilla crafting table support for workcarts.

        }
    }

    public void onCrafting(int storageType, List<ItemStackSlot> hostSlots, int stackSize){
        if(!isCraftingOutput){return;}

        switch (storageType) {
            case 1: { //train crafting table
                Recipe r = RecipeManager.getRecipe(getStack());
                if(r==null || r.input==null){return;}

                if (r instanceof SizedRecipe && ( ((SizedRecipe) r).getCraftHeight() != 3 || ((SizedRecipe) r).getCraftWidth() != 3) ) {
                    //sized recipe and one of the dimensions isn't 3, so we need to take into account positioning of recipe in grid

                    //get list of slots corresponding to crafting grid
                    List<ItemStackSlot> craftSlots = new ArrayList<>();
                    boolean isCrafting = false; //true if we are in the midst of recipes.
                    for (ItemStackSlot slot : hostSlots) { //iterate through each slot of inventory
                        if (slot.isCraftingInput()) {
                            isCrafting = true;
                        } else if (slot.isCraftingOutput()) {
                            break; //reached the end of crafting
                        }
                        if (isCrafting) { //cannot be else if
                            craftSlots.add(slot);
                        }
                    }

                    //get the stacks from slots
                    List<ItemStack> stacksToFindMin = new ArrayList<>();
                    for (ItemStackSlot slot : craftSlots) {
                        stacksToFindMin.add(slot.getStack());
                    }

                    //get amount to shift
                    int[] shiftHorVert = ((SizedRecipe) r).shiftSlotsBy(stacksToFindMin);

                    //subtract right amount of items from the craftSlots
                    for (int row = 0; row < ((SizedRecipe) r).getCraftHeight(); row++) {
                        for (int col = 0; col < ((SizedRecipe) r).getCraftWidth(); col++) {

                            //get ingredient, item in inventory
                            List<ItemStack> ingredient = r.input.get(row*((SizedRecipe) r).getCraftWidth() + col);
                            int rowOfHostSlot = row + shiftHorVert[1];
                            int colOfHostSlot = col + shiftHorVert[0];
                            int realIndex = (rowOfHostSlot * 3) + colOfHostSlot;
                            ItemStackSlot itemInInv = craftSlots.get(realIndex);

                            if (ingredient == null) continue;

                            for (ItemStack ingredStack : ingredient) {
                                //get the right host slot, compare if it matches, then subtract
                                if (OreDictionary.itemMatches(ingredStack, itemInInv.getStack(), false)) {
                                    int amountToSubtract = stackSize * ingredStack.getCount();
                                    if (amountToSubtract < 1) break;
                                    if(itemInInv.getStackSize()-amountToSubtract<1){
                                        itemInInv.setStack(null);
                                    } else {
                                        itemInInv.setSlotStackSize(itemInInv.getStackSize() - amountToSubtract);
                                    }
                                    break;
                                }
                            }
                        }
                    }
                } else {
                    //normal Recipe, continue as normal
                    for (int i = 0; i < 9; i++) {
                        if (r.input.get(i) != null) {
                            for (ItemStack s : r.input.get(i)) {
                                if (slotMatchesItem(hostSlots, 400 + i, s)) {
                                    shrinkStackInSlot(hostSlots, 400 + i, s == null ? 0 : stackSize * s.getCount());
                                    break;
                                }
                            }
                        }
                    }
                }

                break;
            }
            case 0:{
                for(int i=0;i<6;i++){
                    shrinkStackInSlot(hostSlots,400+i,stackSize);
                }
                break;
            }
        }
    }


    public static boolean slotMatchesItem(List<ItemStackSlot> hostSlots, int slot, ItemStack stack){
        ItemStackSlot stackSlot=null;
        for(ItemStackSlot stak: hostSlots){
            if (stak.getSlotIndex() ==slot){
                stackSlot=stak;
            }
        }
        if (stackSlot!=null) {
            if(stackSlot.getStack()==null || stack==null) {
                return stackSlot.getStack() == null && stack == null;
            } else {
                return stack.getItem()==stackSlot.getStack().getItem();
            }
        } else {
            return stack==null;
        }
    }

    public static void shrinkStackInSlot(List<ItemStackSlot> hostSlots, int slot, int size) {
        if(size<1){return;}
        for(ItemStackSlot stak: hostSlots){
            if (stak.getSlotIndex() ==slot){
                if(stak.getStackSize()-size<1){
                    stak.setStack(null);
                } else {
                    stak.setSlotStackSize(stak.getStackSize() - size);
                }
            }
        }
    }

    public void putStackInSlot(List<ItemStackSlot> hostSlots, int slot, ItemStack stack) {
        ItemStackSlot stackSlot=null;
        for(ItemStackSlot stak: hostSlots){
            if (stak.getSlotIndex() ==slot){
                stackSlot=stak;
            }
        }
        if (stackSlot!=null) {
            if (!(stackSlot.inventory instanceof GenericRailTransport) && !(stackSlot.inventory instanceof TileEntityStorage)) {
                stackSlot.inventory.setInventorySlotContents(slot, stack);
            } else {
                stackSlot.setStack(stack);
            }
        }
    }

    public boolean setSlotContents(@Nullable ItemStack stack, List<ItemStackSlot> hostInventory){
        if (isItemValid(stack) || stack == null) {
            if (!(inventory instanceof GenericRailTransport) && !(inventory instanceof TileEntityStorage)) {
                    inventory.setInventorySlotContents(slotNumber, stack==null?null:stack.copy());
            } else {
                this.stack = stack==null?null:stack.copy();
            }
            this.onSlotChanged();
            if(hostInventory!=null) {
                onCraftMatrixChanged(inventory, hostInventory);
            }
            return true;
        }
        return false;
    }


    public boolean setSlotStackSize(int size){
        if(getStack()!=null) {
            ItemStack s = getStack().copy();
            s.setCount(size);
            setStack(s);
            inventory.setInventorySlotContents(slotNumber, s);
        }
        this.onSlotChanged();
        return true;
    }

    /**
     * if par2 has more items than par1, onCrafting(item,countIncrease) is called
     */
    public void onSlotChange(ItemStack p_75220_1_, ItemStack p_75220_2_) {
        if (p_75220_1_ != null && p_75220_2_ != null && p_75220_1_.getItem() == p_75220_2_.getItem()) {
            int i = p_75220_2_.getCount() - p_75220_1_.getCount();

            if (i > 0) {
                this.onCrafting(p_75220_1_, i);
            }
        }
        this.inventory.markDirty();
    }

    /*
     * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood. Typically increases an
     * internal count then calls onCrafting(item).
     protected void onCrafting(ItemStack p_75210_1_, int p_75210_2_) {}
     */

    /*
     * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood.
     protected void onCrafting(ItemStack p_75208_1_) {}
     */

    /**
     * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
     */
    @Override
    public boolean isItemValid(ItemStack p_75214_1_) {
        if(p_75214_1_==null || p_75214_1_.getItem()==null){return true;}
        return inventory.isItemValidForSlot(getSlotID(), p_75214_1_);
    }

    /**
     * Helper fnct to get the stack in the slot.
     */
    @Override
    public ItemStack getStack() {
        return inventory instanceof GenericRailTransport || inventory instanceof TileEntityStorage ?stack:
                inventory!=null && inventory.getSizeInventory()>slotNumber?
                        inventory.getStackInSlot(slotNumber):null;
    }

    /**
     * Helper method to put a stack in the slot.
     * @deprecated use {@link #setSlotContents(ItemStack, List)} instead because it can return whether ot not it actually did it.
     */
    @Override
    @Deprecated
    public void putStack(ItemStack p_75215_1_) {
        setSlotContents(p_75215_1_,null);
    }

    public void setStack(ItemStack p_75215_1_) {
        stack=p_75215_1_;
    }

    /**
     * Called when the stack in a Slot changes, only for tile entities.
     */
    @Override
    public void onSlotChanged() {
        inventory.markDirty();
    }

    /**
     * Returns the maximum stack size for a given slot (usually the same as getInventoryStackLimit(), but 1 in the case
     * of armor slots)
     */
    @Override
    public int getSlotStackLimit() {
        return getHasStack()?getStack().getMaxStackSize():64;
    }

    /**
     * Decrease the size of the stack in slot (first int arg) by the amount of the second int arg. Returns the new
     * stack.
     */
    @Override
    public ItemStack decrStackSize(int p_75209_1_) {
        if(!getHasStack() ||p_75209_1_ >=getStack().getCount()){
            setSlotContents(null,null);
        } else {
            getStack().setCount(getStack().getCount() - p_75209_1_);
        }
        return getStack();
    }

    /**
     * returns if the target Iinventory is from the same class as this
     */
    @Override
    @Deprecated
    public boolean isSlotInInventory(IInventory p_75217_1_, int p_75217_2_) {
        return inventory.getClass() == p_75217_1_.getClass();
    }

    /**
     * Return whether this slot's stack can be taken from this slot.
     */
    @Override
    public boolean canTakeStack(EntityPlayer p_82869_1_) {
        return !(inventory instanceof GenericRailTransport) || ((GenericRailTransport) inventory).getPermissions(p_82869_1_, false, false);
    }

    /**
     * Retrieves the index in the inventory for this slot, this value should typically not
     * be used, but can be useful for some occasions.
     *
     * @return Index in associated inventory for this slot.
     */
    @Override
    public int getSlotIndex() { return slotNumber; }


    public int getSlotID(){return slotID;}

    /** Here, we use it to control whether or not to do the highlighting of slot when mousing over it.
     * I am 99% sure it is used for that based on usages of the function found via IntelliJ.
     * Function it overrides always returns true.
     *
     * @return boolean for if it should draw highlight
     */
    @Override
    public boolean func_111238_b() {
        //if the java ap exam taught me anything, it's short-circuit evaluation.
        if (inventory instanceof TileEntityStorage && ((TileEntityStorage) inventory).pages > 1) {
            if (tierIn > 0 && (slotID == 415 || slotID == 416)) {
                //traincraft assemblyTables
                return false;
            } else if (tierIn == 0 && (slotID == 412 || slotID == 414)) {
                //TiM traintable
                return false;
            }
        }
        return true;
    }
}
