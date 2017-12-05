package train.common.core.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import train.common.api.AbstractTrains;
import train.common.api.EntityBogie;
import train.common.api.EntityRollingStock;


public class EventProperties {

@SubscribeEvent
public void onEntityConstructing(EntityConstructing event)
{
	
	if (event.entity instanceof EntityBogie){
		
   		if (event.entity.getExtendedProperties(EntityBogie.IEEP) == null) {   
   			
   			EntityBogie.register((EntityBogie) event.entity);
     }
   		}
	
   
	
   	if (event.entity instanceof EntityRollingStock){
	
   		if (event.entity.getExtendedProperties(AbstractTrains.IEEP) == null) {   
   			
   			AbstractTrains.register((EntityRollingStock) event.entity);
     }
   		}
	
	
}
}






