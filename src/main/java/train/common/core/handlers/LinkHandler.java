package train.common.core.handlers;

import mods.railcraft.api.tracks.RailTools;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import train.common.api.AbstractTrains;
import train.common.api.EntityRollingStock;

public class LinkHandler {

	private World worldObj;

	public LinkHandler(World world) {
		worldObj = world;
	}


	/**
	 * obvious
	 *
	 * @param entity
	 */
	private void freeLink1(Entity entity) {
		if (entity instanceof EntityRollingStock) {
			((AbstractTrains) entity).Link1 = 0;
			((AbstractTrains) entity).frontLink = null;
			((EntityRollingStock) entity).consist.clear();
			// System.out.println("free link1 "+entity);
		}

	}

	/**
	 * same
	 *
	 * @param entity
	 */
	private void freeLink2(Entity entity) {
		if (entity instanceof EntityRollingStock) {
			((AbstractTrains) entity).Link2 = 0;
			((AbstractTrains) entity).backLink = null;
			((EntityRollingStock) entity).consist.clear();
			// System.out.println("free link2 "+entity);
		}
	}


	/**
	 * Attaching to colliding carts
	 */
	public static void addStake(EntityRollingStock cart1, EntityRollingStock cart2, boolean byPlayer) {
		if (cart1.worldObj.isRemote) {
			return;
		}
			if (cart2.isAttaching && cart1.isAttaching) {


				double d=0;
				double d1=0;

				double distancesX[] = new double[4];
				double distancesZ[] = new double[4];
				double euclidian[] = new double[4];

				distancesX[0] = cart1.posX - cart2.posX ;
				distancesZ[0] = cart1.posZ - cart2.posZ ;
				distancesX[1] = cart1.bogieFront.posX - cart2.posX ;
				distancesZ[1] = cart1.bogieFront.posZ - cart2.posZ ;
				distancesX[2] = cart1.posX - cart2.bogieFront.posX ;
				distancesZ[2] = cart1.posZ - cart2.bogieFront.posZ ;
				distancesX[3] = cart1.bogieFront.posX - cart2.bogieFront.posX ;
				distancesZ[3] = cart1.bogieFront.posZ - cart2.bogieFront.posZ ;

				for(int i = 0; i< distancesX.length;i++){
					euclidian[i] = MathHelper.sqrt_double((distancesX[i] * distancesX[i]) + (distancesZ[i] * distancesZ[i]));
				}

				double minX = euclidian[0];
				int minIndex=0;
				for ( int k=0; k<euclidian.length; k++ )
				{
					if ( Math.abs(euclidian[k]) < Math.abs(minX)){
						minX = euclidian[k];
						minIndex = k;
					}
				}

				d = distancesX[minIndex];
				d1 = distancesZ[minIndex];


				//System.out.println(d2);
				if (MathHelper.sqrt_double((d * d) + (d1 * d1)) <= cart1.getLinkageDistance(cart1)) {
					/**
					 * attach only if the link is free, each cart has two link obviously
					 */
					
					
					
					if (cart1.Link1 == 0 || cart1.Link1 == -1) {
						cart1.Link1 = cart2.getUniqueTrainID();
						//System.out.println(cart1.Link1+" 1 "+cart2.getUniqueTrainID());

					}
					else if (cart1.Link2 == 0 || cart1.Link2 == -1) {
						cart1.Link2 = cart2.getUniqueTrainID();
						//System.out.println(cart1.Link2+" 2 "+cart2.getUniqueTrainID());
					}
					if (cart1.frontLink == null) {
						cart1.frontLink = cart2;
					}
					else if (cart1.backLink == null) {
						cart1.backLink = cart2;
					}

					if (cart2.Link1 == 0 || cart2.Link1 == -1) {
						cart2.Link1 = cart1.getUniqueTrainID();
					}
					else if (cart2.Link2 == 0 || cart2.Link2 == -1) {
						cart2.Link2 = cart1.getUniqueTrainID();
					}

					if (cart2.frontLink == null) {
						cart2.frontLink = cart1;
					}
					else if (cart2.backLink == null) {
						cart2.backLink = cart1;
					}

					if(!cart1.consist.contains(cart2)){
						cart1.consist.add(cart2);
					}

					if(!cart2.consist.contains(cart1)){
						cart2.consist.add(cart1);
					}

					cart2.isAttached = true;

					cart2.isAttaching = false;

					cart1.isAttaching = false;


					cart1.isAttached = true;

					if (cart2.frontLink.train != null) {
						EntityRollingStock.allTrains.remove(cart2.frontLink.train);
						cart2.frontLink.train.getTrains().clear();
						//System.out.println("clearing linked 1");
					}
					if (cart2.backLink != null && cart2.backLink.train != null) {
						EntityRollingStock.allTrains.remove(cart2.backLink.train);
						cart2.backLink.train.getTrains().clear();
						//System.out.println("clearing linked 2");
					}


					EntityPlayer entityplayer = cart1.worldObj.getClosestPlayerToEntity(cart1, 20);//
					if (entityplayer != null && byPlayer) {
						entityplayer.addChatMessage(new ChatComponentText("attached!"));
					}
				}
			}
	}

	/**
	 * getting the optimal distance for each cart
	 * 
	 * @param cart1
	 * @param cart2
	 * @return
	 */
	public static float getOptimalDistance(AbstractTrains cart1, AbstractTrains cart2) {
		return cart1.getOptimalDistance(cart2)+cart2.getOptimalDistance(cart1);
	}

	/**
	 * testing if the cart can pull or not
	 *
	 * @param cart1
	 * @param cart2
	 * @return
	 */
	private static boolean canCartBeAdjustedBy(Entity cart1, Entity cart2) {
		if (cart1 == cart2) {
			return false;
		}
		// System.out.println("cart "+cart1+"  cart adjusted "+((EntityRollingStock)cart1).canBeAdjusted((EntityMinecart)cart2));
		if (((cart1 instanceof EntityRollingStock)) && (!((EntityRollingStock) cart1).canBeAdjusted((EntityMinecart) cart2))) {
			return false;
		}
		return !RailTools.isCartLockedDown((EntityMinecart) cart1);
	}

	/**
	 * Handles the cart coupling physics
	 */
	public static void StakePhysic(AbstractTrains cart1, AbstractTrains other) {
		if (cart1.worldObj.isRemote || cart1.bogieFront==null || other.bogieFront==null) {
			return;
		}


		boolean adj1 = canCartBeAdjustedBy(cart1, other);
		boolean adj2 = canCartBeAdjustedBy(other, cart1);

		double d=0;
		double d1=0;
		double vecX=0;
		double vecZ=0;
		int minIndex=0;


		double distancesX[] = new double[4];
		double distancesZ[] = new double[4];
		double euclidian[] = new double[4];

		distancesX[0] = cart1.posX - other.posX;
		distancesZ[0] = cart1.posZ - other.posZ;
		distancesX[1] = cart1.bogieFront.posX - other.posX ;
		distancesZ[1] = cart1.bogieFront.posZ - other.posZ ;
		distancesX[2] = cart1.posX - other.bogieFront.posX ;
		distancesZ[2] = cart1.posZ - other.bogieFront.posZ ;
		distancesX[3] = cart1.bogieFront.posX - other.bogieFront.posX ;
		distancesZ[3] = cart1.bogieFront.posZ - other.bogieFront.posZ ;

		for(int i = 0; i< distancesX.length;i++){
			euclidian[i] = MathHelper.sqrt_double(((distancesX[i]) * (distancesX[i])) + ((distancesZ[i]) * (distancesZ[i])));
		}
		double minX = euclidian[0];
		for ( int k=0; k<euclidian.length; k++ )
		{
			if ( Math.abs(euclidian[k]) < Math.abs(minX)){
				minX = euclidian[k];
				minIndex = k;
			}
		}

		d = distancesX[minIndex];
		d1 = distancesZ[minIndex];
		vecX=d*0.0625;
		vecZ=d1*0.0625;


		double d2 = MathHelper.sqrt_double((d * d) + (d1 * d1));
		double vecNorm = MathHelper.sqrt_double(vecX * vecX + vecZ * vecZ);

		double unitX = vecX / vecNorm;
		double unitZ = vecZ / vecNorm;

		double stretch = d2 -getOptimalDistance(cart1, other);
		//System.out.println("stretch "+stretch);

		double springX = limitForce(0.1D * stretch * vecX * -1);
		double springZ = limitForce(0.1D * stretch * vecZ * -1);



		if (adj1) {
			cart1.addVelocity(springX,0,springZ);
		}
		if (adj2) {
			other.addVelocity(-springX,0,-springZ);
		}

		double dot = (cart1.motionX - other.motionX) * unitX + (cart1.motionZ - other.motionZ) * unitZ;

		double dampX = limitForce(0.4D * dot * unitX * -1);// 0.4
		double dampZ = limitForce(0.4D * dot * unitZ * -1);

		if (adj1) {
			cart1.addVelocity(dampX,0,dampZ);
		}
		if (adj2) {
			other.addVelocity(-dampX,0,-dampZ);
		}
	}

	private static double limitForce(double force) {
		return Math.copySign(Math.abs(Math.min(Math.abs(force), 14.0D)), force);
	}
}