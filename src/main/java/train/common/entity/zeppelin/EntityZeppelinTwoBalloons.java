package train.common.entity.zeppelin;

import net.minecraft.item.ItemStack;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import train.common.library.ItemIDs;

public class EntityZeppelinTwoBalloons extends AbstractZeppelin{
	
	public EntityZeppelinTwoBalloons(World world) {
		super(world);
	}
	public EntityZeppelinTwoBalloons(World world, double d, double d1, double d2) {
		this(world);
		setPosition(d, d1 + getYOffset(), d2);
		motionX = 0.0D;
		motionY = 0.0D;
		motionZ = 0.0D;
		prevPosX = d;
		prevPosY = d1;
		prevPosZ = d2;
	}
	@Override
	public boolean attackEntityFrom(DamageSource damagesource, float i) {
		if (world.isRemote || isDead) {
			return true;
		}
		boatRockDirection = -boatRockDirection;
		boatTimeSinceHit = 10;
		boatCurrentDamage += i * 10;
		if (damagesource.damageType.equals("player")) {
			if (damagesource.damageType.equals("player") && damagesource.isCreativePlayer()) {
				this.boatCurrentDamage = 1000;
			}
		}
		if (boatCurrentDamage > 40) {
			if (damagesource.damageType.equals("player") && !(damagesource.isCreativePlayer())) {
				dropItem(ItemIDs.airship.item, 1);
			}
			setDead();
		}
		return true;
	}
	@Override
	public String getName() {
		return "Zeppelin Two Balloons";
	}

	/* 1.12 IInventory methods */
	@Override
	public void clear() {
		for (int i = 0; i < getSizeInventory(); i++) {
			setInventorySlotContents(i, ItemStack.EMPTY);
		}
	}

	/* 1.12 IInventory methods */
	@Override
	public int getFieldCount() {
		return 0;
	}

	/* 1.12 IInventory methods */
	@Override
	public void setField(int id, int value) {
	}

	/* 1.12 IInventory methods */
	@Override
	public int getField(int id) {
		return 0;
	}

	/* 1.12 IInventory methods */
	@Override
	public boolean isEmpty() {
		for (int i = 0; i < getSizeInventory(); i++) {
			ItemStack s = getStackInSlot(i);
			if (s != null && !s.isEmpty()) return false;
		}
		return true;
	}
}
