package train.common.api;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import train.common.library.ItemIDs;

public class TrainsOnClick {
    public boolean onClickWithStake(AbstractTrains train, ItemStack itemstack, EntityPlayer playerEntity, World world) {
        if (itemstack != null && itemstack.getItem() == ItemIDs.stake.item && !world.isRemote &&
                (FMLCommonHandler.instance().getMinecraftServerInstance().isSinglePlayer() || !train.isLinked() || train.getTrainOwner().equals(playerEntity.getDisplayName()) || train.getTrainOwner().isEmpty() || train.getTrainOwner() == null)) {
            if (playerEntity.isSneaking() && train instanceof Locomotive) {
                if (!train.canBeAdjusted(train)) {
                    playerEntity.addChatMessage(new ChatComponentText(((EntityRollingStock) train).getTrainName() + " can be pulled, don't forget to fuel it!"));
                    playerEntity.addChatMessage(new ChatComponentText("Attach the BACK of this locomotive to the BACK of another locomotive. Otherwise you will encounter weird problems on turns"));
                    ((Locomotive) train).setCanBeAdjusted(true);
                    ((Locomotive) train).canBePulled = true;
                    ((Locomotive) train).disconnectFromServer();
                } else {
                    playerEntity.addChatMessage(new ChatComponentText(((EntityRollingStock) train).getTrainName() + " can pull"));
                    ((Locomotive) train).setCanBeAdjusted(false);
                    ((Locomotive) train).canBePulled = false;
                }
                return true;
            }

            if (!train.isAttaching) {
                train.isAttaching = true;
                playerEntity.addChatMessage(new ChatComponentText("Attaching mode on for: " + ((EntityRollingStock) train).getTrainName()));
                itemstack.damageItem(1, playerEntity);
            } else {
                playerEntity.addChatMessage(new ChatComponentText("Reset, click again to couple new cart to this one"));
                train.Link1 = -1;
                train.Link2 = -1;
                if (train.frontLink != null && train.frontLink.Link1 == train.getUniqueTrainID()) {
					train.frontLink.Link1 = -1;
				}

                if (train.frontLink != null && train.frontLink.Link2 == train.getUniqueTrainID()) {
					train.frontLink.Link2 = -1;
				}

                if (train.backLink != null && train.backLink.Link1 == train.getUniqueTrainID()) {
					train.backLink.Link1 = -1;
				}

                if (train.backLink != null && train.backLink.Link2 == train.getUniqueTrainID()) {
					train.backLink.Link2 = -1;
				}

                if (train.frontLink != null && train.frontLink.frontLink != null && train.frontLink.frontLink.equals(train)) {
					train.frontLink.frontLink = null;
				}

                if (train.frontLink != null && train.frontLink.backLink != null && train.frontLink.backLink.equals(train)) {
					train.frontLink.backLink = null;
				}

                if (train.backLink != null && train.backLink.backLink != null && train.backLink.backLink.equals(train)) {
					train.backLink.backLink = null;
				}

                if (train.backLink != null && train.backLink.frontLink != null && train.backLink.frontLink.equals(train)) {
					train.backLink.frontLink = null;
				}

                train.frontLink = null;
                train.backLink = null;
                train.isAttaching = false;
                train.isAttached = false;

                if (train.train != null) {
                    train.train.resetTrain();
                }

                if (train.train != null && train.train.getTrains().size() <= 1) {
                    /** no more @RollingStocks in the train then remove the train object from the global list */
                    EntityRollingStock.allTrains.remove(train.train);
                    //System.out.println("Train is destroyed, remove it from the global array");
                }
            }
            return true;
        } else {
            return false;
        }
    }
}
