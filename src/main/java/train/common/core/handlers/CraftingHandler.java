/*******************************************************************************
 * Copyright (c) 2012 Mrbrutal. All rights reserved.
 * 
 * @name TrainCraft
 * @author Mrbrutal
 ******************************************************************************/

package train.common.core.handlers;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraft.item.Item;
import train.common.items.ItemRollingStock;

public class CraftingHandler
{
  
  @SubscribeEvent
  public void onCrafting(PlayerEvent.ItemCraftedEvent event)
  {
		if ((event.crafting.getItem() instanceof ItemRollingStock)) {
			if (!event.player.world.isRemote) {
        if (FMLCommonHandler.instance().getMinecraftServerInstance() != null) {
					ItemRollingStock stock = (ItemRollingStock) event.crafting.getItem();
          //TraincraftSaveHandler.createFile(FMLCommonHandler.instance().getMinecraftServerInstance());
          //int readID = TraincraftSaveHandler.readInt(FMLCommonHandler.instance().getMinecraftServerInstance(), "numberOfTrains:");
          //int newID = stock.setNewUniqueID(event.crafting, event.player, readID);
            stock.setNewUniqueID(event.crafting, event.player, -1);
          //TraincraftSaveHandler.writeValue(FMLCommonHandler.instance().getMinecraftServerInstance(), "numberOfTrains:", "" + newID);
        }
      }
    }
  }
  
  @SubscribeEvent
	public void onSmelting(PlayerEvent.ItemSmeltedEvent event)
  {
		// achievements were removed in 1.12 (see AchievementIDs)
  }
}