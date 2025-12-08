package train.common.entity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class TrustedPlayer {
    private String displayName;
    private boolean breakAccess;
    public TrustedPlayer(String displayName, boolean breakAccess) {
        this.displayName = displayName;
        this.breakAccess = breakAccess;
    }
    public String getDisplayName() {
        return displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    public boolean hasBreakAccess() {
        return breakAccess;
    }
    public void setBreakAccess(boolean breakAccess) {
        this.breakAccess = breakAccess;
    }
    public static void exportToNBT(List<TrustedPlayer> trustedPlayerList, NBTTagCompound nbtTagCompound) {
        if (!trustedPlayerList.isEmpty()) {
            NBTTagList trustedList = new NBTTagList();
            for (TrustedPlayer trustedPlayer : trustedPlayerList) {
                NBTTagCompound trustedPlayerTag = new NBTTagCompound();
                trustedPlayerTag.setString("playerName", trustedPlayer.getDisplayName());
                trustedPlayerTag.setBoolean("breakAccess", trustedPlayer.hasBreakAccess());
                trustedList.appendTag(trustedPlayerTag);
            }
            nbtTagCompound.setTag("trustedList", trustedList);
        }
    }

    /**
     * <p>Import a trusted player list from a given NBT tag.</p>
     * @param nbttagcompound NBT tag from which to import trusted list.
     */
    public static List<TrustedPlayer> importTrustedListFromNBT(NBTTagCompound nbttagcompound, String owner) {
        List<TrustedPlayer> trustedPlayerList = new ArrayList<>();
        if (nbttagcompound.hasKey("trustedList")) {
            NBTTagList trustedList = nbttagcompound.getTagList("trustedList", Constants.NBT.TAG_COMPOUND);
            for (int i = 0; i < trustedList.tagCount(); i++) {
                if (!trustedList.getCompoundTagAt(i).getString("playerName").equalsIgnoreCase(owner)) // Check to ensure we're not adding the current owner to the trusted list...
                    trustedPlayerList.add(new TrustedPlayer(trustedList.getCompoundTagAt(i).getString("playerName"), trustedList.getCompoundTagAt(i).getBoolean("breakAccess")));
            }
            if (nbttagcompound.hasKey("trustedListPreviousOwner")) { // If the previous owner is not the one who placed down the piece of rolling stock...
                if (!nbttagcompound.getString("trustedListPreviousOwner").equalsIgnoreCase(owner)) {
                    trustedPlayerList.add(new TrustedPlayer(nbttagcompound.getString("trustedListPreviousOwner"), true));
                }
            }
        }
        return trustedPlayerList;
    }

    /**
     * <p>Returns whether or not a player is trusted to a piece of rolling stock.</p>
     * @param displayName Case-insensitive display name of player.
     * @return True if the player is trusted, false if the player is not trusted.
     */
    public static boolean isPlayerTrusted(String displayName, List<TrustedPlayer> trustedPlayerList) {
        for (TrustedPlayer trustedPlayer : trustedPlayerList) {
            if (trustedPlayer.getDisplayName().equalsIgnoreCase(displayName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * <p>Returns whether or not a player is trusted to break a piece of rolling stock.</p>
     * @param displayName Case-insensitive display name of player.
     * @return True if player has break access, false if player does not have break access.
     */
    public static boolean isPlayerTrustedToBreak(String displayName, List<TrustedPlayer> trustedPlayerList) {
        for (TrustedPlayer trustedPlayer : trustedPlayerList) {
            if (trustedPlayer.getDisplayName().equalsIgnoreCase(displayName)) {
                return trustedPlayer.hasBreakAccess();
            }
        }
        return false;
    }
}
