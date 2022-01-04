package train.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import train.Traincraft;
import train.core.handlers.ConfigHandler;
import train.entity.zeppelin.EntityZeppelinOneBalloon;
import train.entity.zeppelin.EntityZeppelinTwoBalloons;
import train.library.Info;

import java.util.List;

public class ItemZeppelins extends Item {
	private int type;
	public ItemZeppelins(int type) {
		super();
		maxStackSize = 5;
		setCreativeTab(Traincraft.tcTab);
		this.type=type;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer entityplayer, EnumHand hand) {
		float f = 1.0F;
		float f1 = entityplayer.prevRotationPitch + (entityplayer.rotationPitch - entityplayer.prevRotationPitch) * f;
		float f2 = entityplayer.prevRotationYaw + (entityplayer.rotationYaw - entityplayer.prevRotationYaw) * f;
		double d = entityplayer.prevPosX + (entityplayer.posX - entityplayer.prevPosX) * (double) f;
		double d1 = (entityplayer.prevPosY + (entityplayer.posY - entityplayer.prevPosY) * (double) f + 1.6200000000000001D) - (double) entityplayer.getYOffset();
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
		if (movingobjectposition == null) { return super.onItemRightClick(world,entityplayer,hand); }
		if (!world.isRemote && !ConfigHandler.ENABLE_ZEPPELIN) {
			if (entityplayer != null) entityplayer.sendMessage(new TextComponentString("Zeppelin has been deactivated by the OP"));
			return super.onItemRightClick(world,entityplayer,hand);
		}
		if (movingobjectposition.typeOfHit == RayTraceResult.Type.BLOCK) {
			int i = movingobjectposition.getBlockPos().getX();
			int j = movingobjectposition.getBlockPos().getY();
			int k = movingobjectposition.getBlockPos().getZ();
			if (!world.isRemote) {
				if(type==0)world.spawnEntity(new EntityZeppelinTwoBalloons(world, (float) i + 0.5F, (float) j + 1.5F, (float) k + 0.5F));
				if(type==1)world.spawnEntity(new EntityZeppelinOneBalloon(world, (float) i + 0.5F, (float) j + 1.5F, (float) k + 0.5F));
			}
			entityplayer.getHeldItem(hand).shrink(1);
		}
		return super.onItemRightClick(world,entityplayer,hand);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack par1ItemStack, World world, List<String> par3List, ITooltipFlag par4) {
		par3List.add("\u00a77" + "More info in the guidebook.");
	}

	/*@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		if(type==0)this.itemIcon = iconRegister.registerIcon(Info.modID.toLowerCase() + ":zeppelin");
		if(type==1)this.itemIcon = iconRegister.registerIcon(Info.modID.toLowerCase() + ":zeppelin_one_balloon");
	}*/
}
