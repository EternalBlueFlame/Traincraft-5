package train.common.api;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import train.common.core.handlers.ConfigHandler;

/*
 * Convert between internal TC velocity and minecart velocity
 **/
public class SpeedHandler {

	public static double convertSpeed(double speed) {
		speed /= ConfigHandler.REAL_TRAIN_SPEED?2f:6f;
		speed /= 36f; // /10 for minecraft speed
		return speed;
	}

	public static double convertSpeedInv(double speed) {
		speed *= ConfigHandler.REAL_TRAIN_SPEED ? 2 : 6;
		speed *= 36; // *10 for minecraft speed
		return speed;
	}
}