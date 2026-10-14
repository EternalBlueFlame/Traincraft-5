package train.common.items;

import net.minecraft.util.EnumHand;

import net.minecraft.util.EnumActionResult;

import net.minecraft.util.ActionResult;

import net.minecraft.client.util.ITooltipFlag;

import javax.annotation.Nullable;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import train.common.Traincraft;
import train.common.core.handlers.ConfigHandler;
import train.common.entity.zeppelin.EntityZeppelinOneBalloon;
import train.common.entity.zeppelin.EntityZeppelinTwoBalloons;
import train.common.library.Info;

import java.util.List;

public class ItemZeppelins extends Item implements train.common.items.ILegacyIcon {
	private int type;
	public ItemZeppelins(int type) {
		super();
		maxStackSize = 5;
		if(!ConfigHandler.SPLIT_CREATIVE) {
			setCreativeTab(Traincraft.tcTab);
		} else {
			setCreativeTab(Traincraft.tcTrainTab);
		}
		this.type=type;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer entityplayer, EnumHand hand) {
		ItemStack itemstack = entityplayer.getHeldItem(hand);
		float f = 1.0F;
		float f1 = entityplayer.prevRotationPitch + (entityplayer.rotationPitch - entityplayer.prevRotationPitch) * f;
		float f2 = entityplayer.prevRotationYaw + (entityplayer.rotationYaw - entityplayer.prevRotationYaw) * f;
		double d = entityplayer.prevPosX + (entityplayer.posX - entityplayer.prevPosX) * (double) f;
		double d1 = (entityplayer.prevPosY + (entityplayer.posY - entityplayer.prevPosY) * (double) f + 1.6200000000000001D);
		double d2 = entityplayer.prevPosZ + (entityplayer.posZ - entityplayer.prevPosZ) * (double) f;
		Vec3d vec3d = new Vec3d(d, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.01745329F - 3.141593F);
		float f4 = MathHelper.sin(-f2 * 0.01745329F - 3.141593F);
		float f5 = -MathHelper.cos(-f1 * 0.01745329F);
		float f6 = MathHelper.sin(-f1 * 0.01745329F);
		float f7 = f4 * f5;
		float f9 = f3 * f5;
		double d3 = 5D;
		Vec3d vec3d1 = vec3d.add((double) f7 * d3, (double) f6 * d3, (double) f9 * d3);
		RayTraceResult movingobjectposition = world.rayTraceBlocks(vec3d, vec3d1, true);
		if (movingobjectposition == null) { return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack); }
		if (!world.isRemote && !ConfigHandler.ENABLE_ZEPPELIN) {
			if (entityplayer != null) ebf.tim.utility.CommonUtil.sendChat(entityplayer, "Zeppelin has been deactivated by the OP");
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
		}
		if (movingobjectposition.typeOfHit == RayTraceResult.Type.BLOCK) {
			int i = movingobjectposition.getBlockPos().getX();
			int j = movingobjectposition.getBlockPos().getY();
			int k = movingobjectposition.getBlockPos().getZ();
			if (!world.isRemote) {
				if(type==0)ebf.tim.utility.CommonUtil.spawnEntity(world, new EntityZeppelinTwoBalloons(world, (float) i + 0.5F, (float) j + 1.5F, (float) k + 0.5F));
				if(type==1)ebf.tim.utility.CommonUtil.spawnEntity(world, new EntityZeppelinOneBalloon(world, (float) i + 0.5F, (float) j + 1.5F, (float) k + 0.5F));
			}
			itemstack.shrink(1);
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack par1ItemStack, @Nullable World world, List<String> par3List, ITooltipFlag flag) {
		boolean par4 = flag.isAdvanced(); EntityPlayer par2EntityPlayer = Traincraft.proxy.getPlayer();
		par3List.add("\u00a77" + "More info in the guidebook.");
	}


    @Override
    public String legacyIcon(int meta) {
        return type == 1 ? "tc:zeppelin_one_balloon" : "tc:zeppelin";
    }
}
