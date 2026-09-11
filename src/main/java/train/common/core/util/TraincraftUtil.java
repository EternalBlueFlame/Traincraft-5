package train.common.core.util;

import ebf.tim.utility.CommonUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraftforge.oredict.OreDictionary;
import train.common.Traincraft;
import train.common.api.EntityRollingStock;
import train.common.api.Locomotive;


public class TraincraftUtil {


    public static Item getItemFromName(String name) {
        if (Item.itemRegistry.containsKey(name)) {
            return (Item) Item.itemRegistry.getObject(name);
        } else {
            return null;
        }
    }

    public static ItemStack getItemFromUnlocalizedName(String itemName, int meta) {
        Item item = getItemFromName(itemName);
        if (item != null) {
            return new ItemStack(item, 1, meta);
        }
        return null;
    }

    public static boolean itemStackMatches(ItemStack item1, ItemStack item2) {
        return (item1.getItem() == item2.getItem()) &&
                (item1.getItemDamage() == item2.getItemDamage()
                        || item1.getItemDamage() == OreDictionary.WILDCARD_VALUE
                        || item2.getItemDamage() == OreDictionary.WILDCARD_VALUE);
    }

    public static final double degrees = (180d / Math.PI);
    public static final double radian = (Math.PI / 180.0D);

    public static void updateRider(EntityRollingStock transport, double distance, double yOffset) {
        if (transport.riddenByEntity == null) {
            return;
        }
        double pitchRads = transport.rotationPitch * radian;
        double rotationCos1 = Math.cos((transport.rotationYaw + ((transport instanceof Locomotive) ? 90 : 180)) * radian);
        double rotationSin1 = Math.sin((transport.rotationYaw + ((transport instanceof Locomotive) ? 90 : 180)) * radian);
        if (!Traincraft.proxy.isClient()) {
            rotationCos1 = Math.cos((transport.rotationYaw + 90) * radian);
            rotationSin1 = Math.sin((transport.rotationYaw + 90) * radian);
        }
        float pitch = (float) (transport.posY + ((Math.tan(pitchRads) * distance) + transport.getMountedYOffset())
                + transport.riddenByEntity.getYOffset() + yOffset);

        double bogieX1 = (transport.posX + (rotationCos1 * distance));
        double bogieZ1 = (transport.posZ + (rotationSin1 * distance));
        //System.out.println(rotationCos1+" "+rotationSin1);
        if (transport.rotationPitch > 20 && rotationCos1 == 1) {
            bogieX1 -= pitchRads * 2;
            pitch -= (float) (pitchRads * 1.2);
        }
        if (transport.rotationPitch > 20 && rotationSin1 == 1) {
            bogieZ1 -= pitchRads * 2;
            pitch -= (float) (pitchRads * 1.2);
        }
        if (pitchRads == 0.0) {
            transport.riddenByEntity.setPosition(bogieX1, (transport.posY + transport.getMountedYOffset() + transport.riddenByEntity.getYOffset() + yOffset), bogieZ1);
        }
        if (pitchRads > -1.01 && pitchRads < 1.01) {
            transport.riddenByEntity.setPosition(bogieX1, pitch, bogieZ1);
        }
    }

    public static Vec3 func_514_g(double d, double d1, double d2) {
        return Vec3.createVectorHelper(CommonUtil.floorDouble(d), CommonUtil.floorDouble(d1), CommonUtil.floorDouble(d2));
    }
}
