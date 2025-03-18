package train.common.core.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import train.common.api.AbstractTrains;
import train.common.api.EntityRollingStock;

public class LinkHandler {

	public LinkHandler(World world) {}



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


					EntityPlayer entityplayer = cart1.getWorld().getClosestPlayerToEntity(cart1, 20);//
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
}