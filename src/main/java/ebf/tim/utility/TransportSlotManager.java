package ebf.tim.utility;

import ebf.tim.blocks.TileEntityStorage;
import ebf.tim.entities.EntityTrainCore;
import ebf.tim.entities.GenericRailTransport;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import train.blocks.TCBlocks;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author EternalBlueFlame
 */
public class TransportSlotManager extends net.minecraft.inventory.Container {

    public IInventory hostInventory;

    public List<ItemStackSlot> inventory = new ArrayList<>();

    //todo: add support for some way to define slot filters
    public void addSlots(ItemStackSlot slot){
        this.inventory.add(slot);
        this.inventorySlots.add(slot);
        this.inventoryItemStacks.add(slot.getStack());
    }

    /**
     * <h2>Server-side inventory GUI for trains and rollingstock</h2>
     * works as the middleman between the client GUI and the entity on client and server.
     *
     * this mostly just runs loops to add and place all the inventory slots that will appear and can be used on client.
     */
    public TransportSlotManager(InventoryPlayer iinventory, GenericRailTransport entityTrain) {
        //transport reference
        hostInventory = entityTrain;

        for(ItemStackSlot slot : entityTrain.inventory){
            addSlots(slot);
        }
        //player toolbar
        for (int iT = 0; iT < 9; iT++) {
            addSlots(new ItemStackSlot(iinventory, iT, null).setCoords( 113 + (iT * 18), 142).setPlayerSlot());
        }
        //player inventory
        for (int ic = 0; ic < 9; ic++) {
            for (int ir = 0; ir < 3; ir++) {
                addSlots(new ItemStackSlot(iinventory, ((ir * 9) + ic) + 9, 113 + (ic * 18), 84 + (ir * 18)).setPlayerSlot());
            }
        }
    }

    public TransportSlotManager(InventoryPlayer iinventory, TileEntityStorage block) {
        //tile entity reference
        hostInventory = block;

        //train table layout
        if (block.assemblyTableTier==TCBlocks.trainTableTier1 || block.assemblyTableTier==TCBlocks.trainTableTier2
        || block.assemblyTableTier==TCBlocks.trainTableTier3) { //it is an assembly table, move slots lower. (but only for the traincraft asm tables)
            //player hotbar
            for (int iT = 0; iT < 9; iT++) {
                addSlots(new ItemStackSlot(iinventory, iT, 8 + iT * 18, 232).setPlayerSlot());
            }

            //player inventory
            for (int ir = 0; ir < 3; ir++) {
                for (int ic = 0; ic < 9; ic++) {
                    addSlots(new ItemStackSlot(iinventory, ((ir * 9) + ic) + 9, 8 + (ic * 18), 174 + (ir * 18)).setPlayerSlot());
                }
            }
        }
        //train crafter layout
        else {
            //player toolbar
            for (int iT = 0; iT < 9; iT++) {
                addSlots(new ItemStackSlot(iinventory, iT, 8 + iT * 18, 142).setPlayerSlot());
            }

            //player inventory
            for (int ir = 0; ir < 3; ir++) {
                for (int ic = 0; ic < 9; ic++) {
                    addSlots(new ItemStackSlot(iinventory, ((ir * 9) + ic) + 9, 8 + (ic * 18), 84 + (ir * 18)).setPlayerSlot());
                }
            }
        }

        for(ItemStackSlot slot : block.inventory){
            addSlots(slot);
        }

        detectAndSendChanges();
    }

    /**
     * Used by NEI, seems to work on our internal ID systems for some reason
     */
    @Override
    public Slot getSlot(int p_75139_1_) {
        if(p_75139_1_>99){
            return getSlotByID(p_75139_1_);
        }
        return this.inventory.get(p_75139_1_);
    }


    @Override
    public NonNullList<ItemStack> getInventory() {
        NonNullList<ItemStack> arraylist = NonNullList.<ItemStack>create();

        for (ItemStackSlot inventorySlot : this.inventory) {
            arraylist.add(inventorySlot.getStack());
        }

        return arraylist;
    }

    @SideOnly(Side.CLIENT)
    public void setAll(List<ItemStack> slots) {
        for (int i = 0; i < slots.size(); ++i) {
            this.inventory.get(i).setStack(slots.get(i));
            this.inventorySlots.get(i).putStack(slots.get(i));
            this.inventoryItemStacks.set(i, slots.get(i));
        }
    }
    /**
     * <h2>Inventory sorting and shift-clicking</h2>
     * sorts items from the players inventory to the entity's inventory, and the reverse.
     * This happens with shift click and during some other circumstances.
     * We manage player inventory first because we bound it first, plus it's more reliable to be the size we expected.
     */
    @Override
    @Deprecated
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        DebugUtil.println("something is using transfer stack, this is bad");
        return ItemStack.EMPTY;
    }

    /**modified from 1.7.10 version to check if the item is valid for the slot*/
    @Override
    protected boolean mergeItemStack(ItemStack itemStack, int startIndex, int endIndex, boolean reverseDirection) {
        DebugUtil.println("something is using merge stack, this is bad");
        return false;
    }


    /**
     * normally relied on getSlot, but can't use it because this method does not function on our internal IDs
     * */
    @SideOnly(Side.CLIENT)
    public void setStacksInSlots(ItemStack[] p_75131_1_) {
        for (int i = 0; i < p_75131_1_.length; ++i) {
            this.getSlot(i).putStack(p_75131_1_[i]);
        }
    }

   /* @Override
    public void putStackInSlot(int slot, ItemStack stack){
        if(getSlot(slot)!=null){
            getSlot(slot).putStack(stack);
        }
    }*/




    private int dragEvent;
    private final List<ItemStackSlot> dragSlots = new ArrayList<ItemStackSlot>();
    private int dragMode =-1;


    private ItemStackSlot getSlotByID(int id){
        for(ItemStackSlot slot: inventory){
            if (slot.getSlotIndex() ==id){
                return slot;
            }
        }
        return null;
    }

    /*a heavily modified replica of the 1.12 version*/
    @Override
    public ItemStack slotClick(int slotId, int dragType, ClickType clickType, EntityPlayer player) {

        int clickTypeIn=0;
        switch (clickType){
            case PICKUP: {clickTypeIn=0; break;}//normal pickup and put down
            case QUICK_MOVE: {clickTypeIn=1; break;}//shift click
            case SWAP: {clickTypeIn=2; break;}//how does this work?
            case CLONE: {clickTypeIn=3; break;}//middle mouse button
            case THROW: {clickTypeIn=4; break;}//click outside GUI
            case QUICK_CRAFT: {clickTypeIn=5; break;}//click and drag?
            case PICKUP_ALL: {clickTypeIn=6; break;}//double-click item to pickup all
        }

        if (hostInventory instanceof TileEntityStorage &&
                ((TileEntityStorage) hostInventory).assemblyTableTier != TCBlocks.partTable) {
            this.detectAndSendChanges();
        }

        /*
        switch (clickType){
            case PICKUP: {clickTypeIn=0; break;}//normal pickup and put down
            case QUICK_MOVE: {clickTypeIn=1; break;}//shift click
            case SWAP: {clickTypeIn=2; break;}//how does this work?
            case CLONE: {clickTypeIn=3; break;}//middle mouse button
            case THROW: {clickTypeIn=4; break;}//click outside GUI
            case QUICK_CRAFT: {clickTypeIn=5; break;}//click and drag?
            case PICKUP_ALL: {clickTypeIn=6; break;}//double-click item to pickup all
        }
         */

        if (clickTypeIn == 4){
            clickTypeIn = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) ? 1 ://cover shift click
                    player.inventory.getItemStack() != ItemStack.EMPTY ? 4 : //cover if the cursor is carrying an item
                            (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL))?3://cover CTRL clicking
                                    0;//cover everything else
        }

        ItemStack itemstack =ItemStack.EMPTY;
        ItemStackSlot slot = getSlotByID(slotId);
        int hostType = (hostInventory instanceof TileEntityStorage? ((TileEntityStorage) hostInventory).storageType:0);
        switch (clickTypeIn) {
            case 0: {/*ClickType.PICKUP    aka normal pickup and put down*/
                if (slotId == -999) { //if the slot was the cursor
                    if (player.inventory.getItemStack() != ItemStack.EMPTY) {
                        if (dragType == 0) {
                            player.entityDropItem(player.inventory.getItemStack(), player.inventory.getItemStack().getCount());
                            player.inventory.setItemStack(ItemStack.EMPTY);
                        } else if (dragType == 1) {
                            player.entityDropItem(player.inventory.getItemStack(), 1);
                            break;
                        }
                    }
                } else {
                    if (slot == null) {
                        break;
                    } else if(player.inventory.getItemStack()!=ItemStack.EMPTY) {
                        if(dragType!=1) {
                            if(slot.getStack()!=ItemStack.EMPTY && !slot.contentEquals(player.inventory.getItemStack())){ //swap stacks
                                if (slot.isCraftingOutput()) {
                                    //don't swap, or do anything really
                                    break;
                                }
                                ItemStack old = player.inventory.getItemStack();
                                ItemStack old2 = slot.getStack().copy();

                                slot.setStack(old.copy());
                                player.inventory.setItemStack(old2.copy());
                                this.detectAndSendChanges();
                                break;
                            } else {
                                if (!slot.isCraftingOutput()) {
                                    player.inventory.setItemStack(slot.mergeStack(player.inventory.getItemStack(), inventory, 1));
                                    slot.onCrafting(hostType, inventory, 1);
                                    slot.onCraftMatrixChanged(hostInventory, inventory);
                                } else {
                                    //crafting output, so we should craft one more for the player.
                                    //  first check if same item (don't want to add when click on empty slot)
                                    if (slot.contentEquals(player.inventory.getItemStack())) {
                                        if (player.inventory.getItemStack().getCount() == 64) {
                                            //can't take another, holding full stack
                                            break;
                                        } else {
                                            player.inventory.setItemStack(new ItemStack(player.inventory.getItemStack().getItem(), player.inventory.getItemStack().getCount() + 1));
                                            slot.onCrafting(hostType, inventory, 1);
                                            slot.onCraftMatrixChanged(hostInventory, inventory);
                                        }
                                    }
                                }
                                this.detectAndSendChanges();
                                break;
                            }
                        } else if (slot.isItemValid(player.inventory.getItemStack().copy())) {
                            if (!slot.isCraftingOutput() && slot.getStack() ==ItemStack.EMPTY || (slot.getStack()!=ItemStack.EMPTY && slot.contentEquals(player.inventory.getItemStack()))) {
                                ItemStack s = player.inventory.getItemStack().copy();
                                ItemStack s2 = s.copy();
                                s2.setCount(1);
                                slot.mergeStack(s2, inventory, 1);
                                s.setCount(s.getCount()-1);
                                player.inventory.setItemStack(s);
                            }
                            slot.onCraftMatrixChanged(hostInventory, inventory);
                            this.detectAndSendChanges();
                        }
                        break;
                    } else if (slot.getStack()!=ItemStack.EMPTY){
                        if(dragType==0) { //todo: why does pressing q end up here and not throwing?
                            player.inventory.setItemStack(slot.getStack().copy());
                            slot.onCrafting(hostType, inventory, 1);
                            slot.setSlotContents(ItemStack.EMPTY, inventory);
                        } else if(dragType==1) {
                            ItemStack stack = slot.getStack().copy();
                            //make it round up.
                            if (stack.getCount() % 2 != 0) {
                                stack.setCount(stack.getCount()/2);
                                stack.setCount(stack.getCount() + 1);
                            } else {
                                stack.setCount(stack.getCount() / 2);
                            }
                            player.inventory.setItemStack(stack);
                            slot.decrStackSize(stack.getCount());
                            slot.onCrafting(hostType, inventory, 1);
                        }
                        slot.onCraftMatrixChanged(hostInventory, inventory);
                        this.detectAndSendChanges();
                    }
                    break;
                }
                break;
            }
            case 1:{/*ClickType.QUICK_MOVE    aka shift click*/
                if (slot == null) {
                    return ItemStack.EMPTY;
                }
                if(slot.isCraftingOutput()){
                    int maxCraft=slot.getMaxCraft(hostInventory,inventory);
                    slot.setSlotStackSize(maxCraft);
                }

                if(slotId<36 || slotId==-999){//if the selected slot was in player inventory or on the cursor
                    //try the crafting slots
                    for(ItemStackSlot s : inventory){
                        if(s.getSlotID()>399 && !s.isCraftingOutput()){
                            slot.setSlotContents(s.mergeStack(slot,inventory, hostType), inventory);
                            if(slot.getStack()==ItemStack.EMPTY || slot.getStackSize()==0) {
                                this.detectAndSendChanges();
                                slot.onCrafting(hostType, inventory, 1);
                                slot.onCraftMatrixChanged(hostInventory, inventory);
                                return ItemStack.EMPTY;
                            }
                        }
                    }
                    //try the storage
                    for(ItemStackSlot s : inventory){
                        if(s.getSlotID()>35 && s.getSlotID()<400){
                            slot.setSlotContents(s.mergeStack(slot,inventory, hostType), inventory);
                            if(slot.getStack()==ItemStack.EMPTY || slot.getStackSize()==0) {
                                this.detectAndSendChanges();
                                slot.onCrafting(hostType, inventory, 1);
                                slot.onCraftMatrixChanged(hostInventory, inventory);
                                return ItemStack.EMPTY;
                            }
                        }
                    }
                    //all else fails, go back to the players...
                    for(ItemStackSlot s : inventory){
                        if(s.getSlotID()<36 && slotId != s.getSlotID()){ //prevent slot from trying to merge into itself
                            slot.setSlotContents(s.mergeStack(slot,inventory, hostType), inventory);
                            if(slot.getStack()==ItemStack.EMPTY || slot.getStackSize()==0) {
                                this.detectAndSendChanges();
                                slot.onCrafting(hostType, inventory, 1);
                                slot.onCraftMatrixChanged(hostInventory, inventory);
                                return ItemStack.EMPTY;
                            }
                        }
                    }

                } else if(!slot.isCraftingOutput()){//if the selected slot is in the tileentity but not an output
                    for (int k = 0; k < 4; k++) { //loop twice. once to combine, the next to put in any available slots
                        for (int l = 0; l < this.inventory.size(); l++) {
                            Slot slotToAddInto = getSlot(l);

                            if (slotToAddInto instanceof ItemStackSlot && ((ItemStackSlot) slotToAddInto).isCraftingOutput()) continue;

                            //if the slots are in the same inventory during the first two loops, skip the slot
                            //for the last two loops we let it merge into the same inventory because there's nowhere else to go.
                            if(k<2 && slotToAddInto instanceof ItemStackSlot
                                    && slot.isPlayerSlot()==((ItemStackSlot)slotToAddInto).isPlayerSlot()){continue;}

                            //during the first and third loop, see if there's an existing stack we can merge with.
                            if (k == 0 || k==2) {
                                if (slotToAddInto != null
                                        && slotToAddInto.getHasStack()
                                        && canAddItemToSlot(slotToAddInto, slot.getStack())
                                        && slotToAddInto.canTakeStack(player)
                                        && slot != slotToAddInto) {
                                    //if the held is combine-able with the slot in question, combine slot into canAddItemToSlot

                                    int openSpace = slotToAddInto.getSlotStackLimit() - slotToAddInto.getStack().getCount();
                                    int amountToAdd = Math.min(slot.getStackSize(), openSpace);

                                    if (amountToAdd == slot.getStackSize()) {
                                        slot.setStack(ItemStack.EMPTY);
                                        slotToAddInto.getStack().setCount(slotToAddInto.getStack().getCount() + amountToAdd);
                                        k=4;
                                    } else {
                                        slotToAddInto.getStack().setCount(slotToAddInto.getStack().getCount() + amountToAdd);
                                        slot.getStack().setCount(slot.getStack().getCount() - amountToAdd);
                                    }
                                }
                            } else {
                                //put in any empty slot
                                if (slotToAddInto != null && !slotToAddInto.getHasStack() && slotToAddInto.canTakeStack(player)) {
                                    if (slot.getStackSize() <= slotToAddInto.getSlotStackLimit()) {
                                        slotToAddInto.putStack(slot.getStack().copy());
                                        slot.setStack(ItemStack.EMPTY);
                                    } else {
                                        //put as much as can into it.
                                        int amountToAdd = slotToAddInto.getSlotStackLimit();
                                        ItemStack newStack = slot.getStack().copy();
                                        newStack.setCount(amountToAdd);
                                        slotToAddInto.putStack(newStack);
                                        slot.decrStackSize(amountToAdd);
                                    }
                                }
                            }
                            if (!slot.getHasStack() || slot.getStackSize() == 0) {
                                slot.setStack(ItemStack.EMPTY);
                                this.detectAndSendChanges();
                                slot.onCrafting(hostType, inventory, 1);
                                slot.onCraftMatrixChanged(hostInventory, inventory);
                                return ItemStack.EMPTY;
                            }
                        }
                    }
                } else {//if the selected slot was in a crafting output slot
                    //TODO: what happens if there are some slots to move items into but not enough for them all?
                    boolean hasStorage = false;
                    //try the players
                    for(ItemStackSlot s : inventory){
                        if(s.getSlotID()<36){
                            if(s.mergeStack(slot,inventory, hostType)==ItemStack.EMPTY) {
                                slot.setStack(ItemStack.EMPTY);
                                this.detectAndSendChanges();
                                slot.onCrafting(hostType, inventory, 1);
                                slot.onCraftMatrixChanged(hostInventory, inventory);
                                return ItemStack.EMPTY;
                            }
                        } else if (s.getSlotID()>35){
                            hasStorage=true;
                        }
                    }
                    if(hasStorage) {
                        //try the storage
                        for (ItemStackSlot s : inventory) {
                            if (s.getSlotID() > 35){
                                if(s.mergeStack(slot,inventory, hostType)==ItemStack.EMPTY) {
                                    slot.setStack(ItemStack.EMPTY);
                                    this.detectAndSendChanges();
                                    slot.onCrafting(hostType, inventory, 1);
                                    slot.onCraftMatrixChanged(hostInventory, inventory);
                                    return ItemStack.EMPTY;
                                }
                            }
                        }
                    }
                    this.detectAndSendChanges();

                }
                break;
            }
            case 2: {/*ClickType.SWAP*/
                if (dragType >= 0 && dragType < 9){
                    itemstack = player.inventory.getStackInSlot(dragType);
                    if (itemstack != ItemStack.EMPTY || (slot!= null && slot.getStack() != ItemStack.EMPTY)) {
                        if (itemstack == ItemStack.EMPTY) {
                            if (slot.canTakeStack(player)) {
                                player.inventory.setInventorySlotContents(dragType, slot.getStack().copy());
                                slot.setSlotContents(ItemStack.EMPTY,inventory);
                            }
                        } else if (slot != null) {
                            int amountToShrink = Math.min(slot.getSlotStackLimit(), itemstack.getCount());
                            player.inventory.setInventorySlotContents(dragType, slot.mergeStack(itemstack.splitStack(amountToShrink),inventory, hostType));
                        }
                    }
                }
                this.detectAndSendChanges();
                break;
            }
            case 3: { /*ClickType.CLONE (middle mouse button) */
                if (player.capabilities.isCreativeMode && player.inventory.getItemStack() == ItemStack.EMPTY && slotId >= 0 && slot != null && slot.getHasStack()) {
                    itemstack = slot.getStack().copy();
                    itemstack.setCount(itemstack.getMaxStackSize());
                    player.inventory.setItemStack(itemstack);
                    return itemstack;
                }
                break;
            }
            case 4: { /*ClickType.THROW*/
                if (player.inventory.getItemStack() == ItemStack.EMPTY && slotId >= 0 && slot != null && slot.getHasStack() && slot.canTakeStack(player)) {
                    itemstack = slot.decrStackSize(dragType == 0 ? 1 : slot.getStack().getCount());
                    if (!player.world.isRemote && itemstack!=ItemStack.EMPTY) {
                        player.entityDropItem(itemstack, itemstack.getCount());
                    }
                }
                break;
            }
            case 5 : {/*ClickType.QUICK_CRAFT (dragging) */

                //the following stops crafting output slots from eating items.
                //  Not perfect, as it does still appear to be going into slots from client pov, but that is solely client side.
                //  See GuiContainer.java's drawScreen(int, int, float) function, lines 139 - 162
                if (slot != null && slot.isCraftingOutput()) {
                    break;
                }

                //TODO: if crafting slot, loop until slot output is null.
                    int j1 = this.dragEvent;
                    this.dragEvent = dragType & 3;

                    if (((j1 != 1 || this.dragEvent != 2) && j1 != this.dragEvent) || player.inventory.getItemStack() == ItemStack.EMPTY) {
                        this.dragEvent = 0;
                        this.dragSlots.clear();
                    } else if (this.dragEvent == 0) {
                        this.dragMode = dragType >> 2 & 3;

                        if ((this.dragMode <= 1 || (this.dragMode == 2 && player.capabilities.isCreativeMode))) {
                            this.dragEvent = 1;
                            this.dragSlots.clear();
                        } else {
                            this.dragEvent = 0;
                            this.dragSlots.clear();
                        }
                    } else if (this.dragEvent == 1) {
                        if (slot != null &&
                                slot.isItemValid(player.inventory.getItemStack()) && (this.dragMode == 2 || player.inventory.getItemStack().getCount() > this.dragSlots.size())) {
                            this.dragSlots.add(slot);
                        }
                    } else if (this.dragEvent == 2) {
                        if (!this.dragSlots.isEmpty()) {
                            int k1 = player.inventory.getItemStack().getCount();

                            for (ItemStackSlot slot2 : this.dragSlots) {
                                if (slot2 != null && (this.dragMode == 2 || player.inventory.getItemStack().getCount() >= this.dragSlots.size())) {
                                    ItemStack itemstack14 = player.inventory.getItemStack().copy();
                                    int j3 = slot2.getHasStack() ? slot2.getStack().getCount() : 0;
                                    computeStackSize(this.dragSlots, this.dragMode, itemstack14, j3);
                                    int k3 = Math.min(itemstack14.getMaxStackSize(), slot2.getSlotStackLimit());

                                    if (itemstack14.getCount() > k3) {
                                        itemstack14.setCount(k3);
                                    }

                                    k1 -= itemstack14.getCount() - j3;
                                    slot2.setSlotContents(itemstack14,inventory);
                                }
                            }
                            if (k1 !=0) {
                                player.inventory.getItemStack().setCount(k1);
                            } else {
                                player.inventory.setItemStack(ItemStack.EMPTY);
                            }
                        }
                        this.dragEvent = 0;
                        this.dragSlots.clear();
                    } else {
                        this.dragEvent = 0;
                        this.dragSlots.clear();
                    }
                    break;
                }
                default:{
                    if (this.dragEvent != 0) {
                        this.dragEvent = 0;

                        this.dragSlots.clear();
                    }
                }
                this.detectAndSendChanges();
            case 6: { // double clicking on an item (second click, putting down item normally)
                if (slotId >= 0) {
                    ItemStack heldStack = player.inventory.getItemStack();

                    //if player holding stack and either: slot clicked on is empty, doesn't have stack, can't take stack
                    if (heldStack != ItemStack.EMPTY && (slot == null || !slot.getHasStack() || !slot.canTakeStack(player))) {

                        //i and j say where to start and what direction to search
                        //  if dragType is 0, then start at inventory spot 0 --> end
                        //  if dragType isn't 0, then start at end and go backwards.
                        int i = dragType == 0 ? 0 : this.inventory.size() - 1;
                        int j = dragType == 0 ? 1 : -1;

                        for (int k = 0; k < 2; ++k) { //loop twice. once to check combining, the next to put in any available slots
                            for (int l = i; l >= 0 && l < this.inventory.size() && heldStack.getCount() < heldStack.getMaxStackSize(); l += j) {
                                //for loop loops either backwards or forwards through all inv slots, see large comment above

                                //problem is that getting the slot via id results in trying to get 400+ but actually getting like 40 because it counts and doesn't skip like slotID
                                Slot slot1 = getSlot(l);
                                //prev: getSlotByID

                                if (slot1 instanceof ItemStackSlot && ((ItemStackSlot) slot1).isCraftingOutput()) {
                                    continue;
                                }

                                if (slot1 != null && slot1.getHasStack() && canAddItemToSlot(slot1, heldStack) && slot1.canTakeStack(player)) {
                                    //if the held is combine-able with the slot in question

                                    ItemStack itemstack2 = slot1.getStack();

                                    if (k != 0 || itemstack2.getCount() != itemstack2.getMaxStackSize()) {
                                        int i1 = Math.min(heldStack.getMaxStackSize() - heldStack.getCount(), itemstack2.getCount());
                                        ItemStack itemstack3 = slot1.decrStackSize(i1);
                                        heldStack.setCount(heldStack.getCount() + i1);

                                        if (itemstack3 == null) {
                                            slot1.putStack(ItemStack.EMPTY);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    this.detectAndSendChanges();
                }
            }


        }


        if (player.inventory.getItemStack() != ItemStack.EMPTY && player.inventory.getItemStack().getCount() ==0){
            player.inventory.setItemStack(ItemStack.EMPTY);
        }
        return itemstack;

    }

    @Override
    public void detectAndSendChanges() {
        boolean clientStackChanged;
        ItemStack itemstack, itemstack1;
        Item air = Item.getItemFromBlock(Blocks.AIR);
        for (int i = 0; i < this.inventorySlots.size(); ++i) {
            itemstack = (this.inventorySlots.get(i)).getStack();
            itemstack1 = this.inventoryItemStacks.get(i);
            if(itemstack.getItem()== air){
                itemstack=ItemStack.EMPTY;
            }
            if(itemstack1==null || itemstack1.getItem()==air){
                itemstack1=ItemStack.EMPTY;
            }

            if (!ItemStack.areItemStacksEqual(itemstack1, itemstack)) {
                clientStackChanged = !ItemStack.areItemStacksEqualUsingNBTShareTag(itemstack1, itemstack);
                itemstack1 = itemstack.isEmpty() ? ItemStack.EMPTY : itemstack.copy();
                this.inventoryItemStacks.set(i, itemstack1);

                if (clientStackChanged)
                    for (IContainerListener listener : this.listeners) {
                        listener.sendSlotContents(this, i, itemstack1);
                    }
            }
        }
        this.dragEvent = 0;
        this.dragSlots.clear();
    }
    /*a modified replica of the 1.12 version*/
    public static boolean canAddItemToSlot(@Nullable Slot slotIn, ItemStack stack) {
        boolean flag = slotIn == null || stack==null || !slotIn.getHasStack();

        if (!flag && stack.isItemEqual(slotIn.getStack()) &&
                ItemStack.areItemStackTagsEqual(slotIn.getStack(), stack)) {
            return slotIn.getStack().getCount() <= stack.getMaxStackSize();
        } else {
            return flag;
        }
    }

    /*a modified replica of the 1.12 version*/
    public static void computeStackSize(List<ItemStackSlot> dragSlotsIn, int dragModeIn, ItemStack stack, int slotStackSize) {
        switch (dragModeIn) {
            case 0: {
                stack.setCount(MathHelper.floor((float)stack.getCount() / dragSlotsIn.size()));
                break;
            }
            case 1: {
                stack.setCount(1);
                break;
            }
            case 2: {
                stack.setCount(stack.getMaxStackSize());
            }
        }
        stack.setCount(stack.getCount() + slotStackSize);
    }


    @Override
    public void onContainerClosed(EntityPlayer p_75134_1_) {
        super.onContainerClosed(p_75134_1_);
        this.hostInventory.closeInventory(p_75134_1_);
    }

    /**
     * <h2>can interact with inventory</h2>
     * just a simple return true/false if the train is dead, or if the owner locked the transport and the player trying to access isn't the owner.
     * it's also a null check to be sure that no one tries to interact with an errored entity.
     */
    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return (hostInventory instanceof GenericRailTransport?((GenericRailTransport)hostInventory).getPermissions(player, hostInventory instanceof EntityTrainCore, false): hostInventory!=null);
    }
}
