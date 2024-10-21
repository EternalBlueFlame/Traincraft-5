/*******************************************************************************
 * Copyright (c) 2012 Mrbrutal. All rights reserved.
 * 
 * @name TrainCraft
 * @author Mrbrutal
 ******************************************************************************/

package train.common.core.handlers;

import cpw.mods.fml.common.registry.EntityRegistry;
import ebf.tim.entities.EntitySeat;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import train.common.Traincraft;
import train.common.api.EntityBogie;
import train.common.api.TrainRecord;
import train.common.core.EntityIds;
import train.common.entity.zeppelin.EntityZeppelinOneBalloon;
import train.common.entity.zeppelin.EntityZeppelinTwoBalloons;
import train.common.library.EnumTrains;
import train.common.library.TraincraftRegistry;

public class EntityHandler {
	public static void init() {	
		EntityRegistry.registerModEntity(EntityZeppelinTwoBalloons.class, "zeppelin", EntityIds.ZEPPELIN, Traincraft.instance, 512, 1, true);//zepplin
		EntityRegistry.registerModEntity(EntityBogie.class, "Entity Front Bogie", EntityIds.LOCOMOTIVE_BOGIE, Traincraft.instance, 512, 1, true);//front bogie
		EntityRegistry.registerModEntity(EntityZeppelinOneBalloon.class, "zeppelin big", EntityIds.ZEPPELIN_BIG, Traincraft.instance, 512, 1, true);//zepplin big
		EntityRegistry.registerModEntity(EntitySeat.class, "Seat", 16, Traincraft.instance,80,3,true);//seat
		for(TrainRecord trains : EnumTrains.trains()){
			TraincraftRegistry.registerTransport(trains);
		}
	}
}