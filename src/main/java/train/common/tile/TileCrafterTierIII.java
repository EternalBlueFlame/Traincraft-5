package train.common.tile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.Constants;
import train.common.api.blocks.TileTraincraft;
import train.common.core.interfaces.ITier;
import train.common.core.managers.TierRecipe;
import train.common.core.managers.TierRecipeManager;
import train.common.library.Info;

import java.util.ArrayList;
import java.util.List;

public class TileCrafterTierIII extends TileTraincraft implements ITier {

	private final int Tier = 3;
	private List<Item>			resultList;
	private static List<Item> knownRecipes = new ArrayList<Item>();
	private static int[] slotSelected;

	public TileCrafterTierIII() {
		super(26);
		this.resultList = new ArrayList<Item>();
		slotSelected = new int[8];
	}

	@Override
	public List<Item> getResultList() {
		return resultList;
	}

	@Override
	public String getInventoryName() {
		return "TierIII";
	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTag) {
		super.readFromNBT(nbtTag);
		slotSelected = nbtTag.getIntArray("Selected");

		NBTTagList nbttaglist2 = nbtTag.getTagList("Known", Constants.NBT.TAG_COMPOUND);
		for (int i = 0; i < nbttaglist2.tagCount(); i++) {
			NBTTagCompound nbttagcompound2 = nbttaglist2.getCompoundTagAt(i);
			byte byte1 = nbttagcompound2.getByte("Recipe");
			if (byte1 >= 0) {
				ItemStack stack = new ItemStack(nbttagcompound2);

				if (stack!=null && !listContainsItem(knownRecipes, stack.getItem())) {

					knownRecipes.add(stack.getItem());
				}
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTag) {
		super.writeToNBT(nbtTag);
		nbtTag.setIntArray("Selected", slotSelected);

		NBTTagList nbttaglist2 = new NBTTagList();
		if (knownRecipes != null) {
			for (int i = 0; i < knownRecipes.size(); i++) {
				NBTTagCompound nbttagcompound2 = new NBTTagCompound();
				nbttagcompound2.setByte("Recipe", (byte) i);
				new ItemStack(knownRecipes.get(i)).writeToNBT(nbttagcompound2);
				nbttaglist2.appendTag(nbttagcompound2);
			}
			nbtTag.setTag("Known", nbttaglist2);
		}
	}

	@Override
	public void markDirty() {
		resultList.clear();
		for (int i = 10; i < slots.length - 8; i++) {
			slots[i] = null;
		}

		List<TierRecipe> recipes = TierRecipeManager.getInstance().getTierRecipeList(this.Tier());
		int count = 0;
		for (int j = 0; j < recipes.size(); j++) {
			ItemStack stack = recipes.get(j).hasComponents(slots);
			if (stack != null && !resultList.contains(stack.getItem()) && (count + 10) < slots.length - 8) {
				resultList.add(stack.getItem());
				slots[count + 10] = stack;
				count++;
			}
		}

		for (int i = 0; i < resultList.size(); i++) {
			if (!listContainsItem(knownRecipes, resultList.get(i))) {
				knownRecipes.add(resultList.get(i));
			}
		}
	}

	private boolean listContainsItem(List<Item> list, Item stack) {
		for (int i = 0; i < list.size(); i++) {
			if (Item.getIdFromItem(list.get(i)) == Item.getIdFromItem(stack)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int Tier() {
		return Tier;
	}

	@Override
	public List<Item> knownRecipes() {
		return knownRecipes;
	}

	@Override
	public int[] getSlotSelected() {
		return slotSelected;
	}

	@Override
	public void setSlotSelected(int[] selected) {
		this.slotSelected = selected;
	}

	@Override
	public String getGUIName() {
		return "Advanced age";
	}

	@Override
	public String getGUITexture() {
		return Info.guiPrefix + "gui_tierIII_advancedAge.png";
	}

	@Override
	public boolean hasCustomInventoryName() {return false;}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack stack) {
		if(i>17)
			return true;
		if(i>9)
			return false;
		
		List<TierRecipe> recipeList = TierRecipeManager.getInstance().getTierRecipeList(this.Tier);
		for(TierRecipe recipe : recipeList){
			ItemStack stack2 = recipe.getInput().get(i);
			if (stack2 != null && TierRecipe.areItemsIdentical(stack, stack2)) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side) {
		return isItemValidForSlot(slot, stack);
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ResourceLocation getTexture(int x, int y, int z){
		return texture;
	}


	static final ResourceLocation texture = new ResourceLocation(Info.modID, "textures/blocks/assembly_3.png");

}