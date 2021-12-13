package train.core.handlers;

import ebf.tim.items.ItemTransport;
import net.minecraft.block.Block;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import train.generation.ComponentVillageTrainstation;
import train.library.ItemIDs;

import java.util.List;
import java.util.Random;

public class VillagerTraincraftHandler implements VillagerRegistry.IVillageCreationHandler, EntityVillager.ITradeList {

	//private Random rand = new Random();
	@Override
	public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
		recipeList.add(new MerchantRecipe(getRandomSizedStack(Blocks.RAIL, random,20), Items.EMERALD));
		recipeList.add(new MerchantRecipe(getRandomSizedItemStack(Items.EMERALD, random,20), new ItemStack(Blocks.RAIL)));
		
		recipeList.add(new MerchantRecipe(getRandomSizedStack(Blocks.ACTIVATOR_RAIL, random,4), Items.EMERALD));
		recipeList.add(new MerchantRecipe(getRandomSizedItemStack(Items.EMERALD, random,4), new ItemStack(Blocks.ACTIVATOR_RAIL)));
		
		recipeList.add(new MerchantRecipe(getRandomSizedStack(Blocks.DETECTOR_RAIL, random,4), Items.EMERALD));
		recipeList.add(new MerchantRecipe(getRandomSizedItemStack(Items.EMERALD, random,4), new ItemStack(Blocks.DETECTOR_RAIL)));
		
		recipeList.add(new MerchantRecipe(getRandomSizedStack(Blocks.GOLDEN_RAIL, random,10), Items.EMERALD));
		recipeList.add(new MerchantRecipe(getRandomSizedItemStack(Items.EMERALD, random,10), new ItemStack(Blocks.GOLDEN_RAIL)));
		
		recipeList.add(new MerchantRecipe(getRandomSizedStack(Blocks.CLAY, random,10), Items.EMERALD));
		recipeList.add(new MerchantRecipe(getRandomSizedItemStack(Items.EMERALD, random,10), new ItemStack(Blocks.BRICK_BLOCK)));

		recipeList.add(new MerchantRecipe(getRandomSizedItemStack(Items.MINECART, random,2), Items.EMERALD));
		recipeList.add(new MerchantRecipe(getRandomSizedItemStack(Items.EMERALD, random,2), new ItemStack(Items.MINECART)));

		recipeList.add(new MerchantRecipe(getRandomSizedItemStack(Items.CHEST_MINECART, random,4), Items.EMERALD));
		recipeList.add(new MerchantRecipe(getRandomSizedItemStack(Items.EMERALD, random,4), new ItemStack(Items.CHEST_MINECART)));

		recipeList.add(new MerchantRecipe(getRandomSizedItemStack(Items.HOPPER_MINECART, random,1), Items.EMERALD));
		recipeList.add(new MerchantRecipe(getRandomSizedItemStack(Items.EMERALD, random,1), new ItemStack(Items.HOPPER_MINECART)));

		recipeList.add(new MerchantRecipe(getRandomSizedItemStack(Items.FURNACE_MINECART, random,2), Items.EMERALD));
		recipeList.add(new MerchantRecipe(getRandomSizedItemStack(Items.EMERALD, random,2), new ItemStack(Items.FURNACE_MINECART)));

		recipeList.add(new MerchantRecipe(getRandomSizedItemStack(Items.TNT_MINECART, random,1), Items.EMERALD));
		recipeList.add(new MerchantRecipe(getRandomSizedItemStack(Items.EMERALD, random,1), new ItemStack(Items.TNT_MINECART)));
		
		for(ItemIDs item : ItemIDs.values()){
			if(item!=null && item.item!=null){
				if(item.item instanceof ItemTransport){
					recipeList.add(new MerchantRecipe(new ItemStack(item.item), new ItemStack(Items.EMERALD,item.amountForEmerald)));
					recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD,item.amountForEmerald), item.item));
				}else if(item.amountForEmerald>0){
					if(!(item.item instanceof ItemTransport) && item.amountForEmerald>0){
						recipeList.add(new MerchantRecipe(new ItemStack(item.item,item.amountForEmerald), Items.EMERALD));
						recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD), new ItemStack(item.item,item.amountForEmerald)));
					}
				}
			}
		}
	}
	private ItemStack getRandomSizedStack(Block par0, Random random, int amount)
    {
        return new ItemStack(par0, random.nextInt(amount)+1, 0);
    }
	private ItemStack getRandomSizedItemStack(Item par0, Random random, int amount)
	{
		return new ItemStack(par0, random.nextInt(amount)+1, 0);
	}
	
	@Override
	public StructureVillagePieces.PieceWeight getVillagePieceWeight(Random random, int size) {
		 return new StructureVillagePieces.PieceWeight(ComponentVillageTrainstation.class, 15, size + random.nextInt(1));
	}
	@Override
	public Class<?> getComponentClass() {
		return ComponentVillageTrainstation.class;
	}
	@Override
	public StructureVillagePieces.Village buildComponent(StructureVillagePieces.PieceWeight villagePiece, StructureVillagePieces.Start startPiece, List<StructureComponent> pieces, Random random, int p1, int p2, int p3, EnumFacing facing, int p5) {
		return ComponentVillageTrainstation.buildComponent(startPiece, pieces, random, p1, p2, p3, facing, p5);
	}
}
