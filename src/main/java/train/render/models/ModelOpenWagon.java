package train.render.models;

import ebf.tim.entities.GenericRailTransport;
import ebf.tim.utility.ItemStackSlot;
import fexcraft.tmt.slim.ModelBase;
import fexcraft.tmt.slim.ModelRendererTurbo;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;
import train.render.CustomModelRenderer;

public class ModelOpenWagon extends ModelBase {
	
	public CustomModelRenderer box;
	public CustomModelRenderer box0;
	public CustomModelRenderer box1;
	public CustomModelRenderer box10;
	public CustomModelRenderer box11;
	public CustomModelRenderer box12;
	public CustomModelRenderer box13;
	public CustomModelRenderer box14;
	public CustomModelRenderer box2;
	public CustomModelRenderer box24;
	public CustomModelRenderer box25;
	public CustomModelRenderer box4;
	public CustomModelRenderer box5;
	public CustomModelRenderer box6;
	public CustomModelRenderer box7;
	public CustomModelRenderer box8;
	public CustomModelRenderer box9;

	public ModelOpenWagon() {
		box = new CustomModelRenderer(this, 85, 1, 128, 128);
		box.addBox(0F, 0F, 0F, 7, 3, 14);
		box.setPosition(11F, 2F, -7F);

		box0 = new CustomModelRenderer(this, 2, 0, 128, 128);
		box0.addBox(0F, 0F, 0F, 15, 2, 14);
		box0.setPosition(-22F, 6F, -7F);

		box1 = new CustomModelRenderer(this, 1, 1, 128, 128);
		box1.addBox(0F, 0F, 0F, 15, 2, 14);
		box1.setPosition(7F, 6F, -7F);

		box10 = new CustomModelRenderer(this, 7, 90, 128, 128);
		box10.addBox(0F, 0F, 0F, 44, 1, 16);
		box10.setPosition(-22F, 22F, -8F);

		box11 = new CustomModelRenderer(this, 114, 83, 128, 128);
		box11.addBox(0F, 0F, 0F, 1, 16, 4);
		box11.setPosition(23F, 9F, -7F);

		box12 = new CustomModelRenderer(this, 1, 1, 128, 128);
		box12.addBox(0F, 0F, 0F, 58, 3, 4);
		box12.setPosition(-29F, 6F, -2F);

		box13 = new CustomModelRenderer(this, 114, 83, 128, 128);
		box13.addBox(0F, 0F, 0F, 1, 16, 4);
		box13.setPosition(-23F, 9F, 7F);
		box13.rotateAngleY = -3.141592653589793F;

		box14 = new CustomModelRenderer(this, 86, 1, 128, 128);
		box14.addBox(0F, 0F, 0F, 7, 3, 14);
		box14.setPosition(-18F, 2F, -7F);

		box2 = new CustomModelRenderer(this, 1, 40, 128, 128);
		box2.addBox(0F, 0F, 0F, 46, 7, 12);
		box2.setPosition(-23F, 0F, -6F);

		box24 = new CustomModelRenderer(this, 1, 20, 128, 128);
		box24.addBox(0F, 0F, 0F, 46, 2, 16);
		box24.setPosition(-23F, 8F, -8F);

		box25 = new CustomModelRenderer(this, 10, 109, 128, 128);
		box25.addBox(0F, 0F, 0F, 46, 7, 10);
		box25.setPosition(-23F, 0F, -5F);

		box4 = new CustomModelRenderer(this, 36, 0, 128, 128);
		box4.addBox(0F, 0F, 0F, 14, 5, 8);
		box4.setPosition(-21F, 3F, -4F);

		box5 = new CustomModelRenderer(this, 19, 60, 128, 128);
		box5.addBox(0F, 0F, 0F, 48, 18, 1);
		box5.setPosition(-24F, 7F, -9F);

		box6 = new CustomModelRenderer(this, 19, 60, 128, 128);
		box6.addBox(0F, 0F, 0F, 48, 18, 1);
		box6.setPosition(24F, 7F, 9F);
		box6.rotateAngleY = -3.141592653589793F;

		box7 = new CustomModelRenderer(this, 0, 64, 128, 128);
		box7.addBox(0F, 0F, 0F, 1, 16, 16);
		box7.setPosition(-23F, 9F, -8F);

		box8 = new CustomModelRenderer(this, 0, 64, 128, 128);
		box8.addBox(0F, 0F, 0F, 1, 16, 16);
		box8.setPosition(22F, 9F, -8F);

		box9 = new CustomModelRenderer(this, 5, 1, 128, 128);
		box9.addBox(0F, 0F, 0F, 14, 5, 8);
		box9.setPosition(8F, 3F, -4F);

		bodyModel=new ModelRendererTurbo[]{box,
				box0,box1,box2,box4,box5,box6,box7,box8,box9,
				box11,box12,box13,box14,box24,box25
		};

		fixRotation(bodyModel);
	}
	
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		int cargo = 0;

		for (ItemStackSlot s : ((GenericRailTransport) entity).inventory){
			if(s.getStack()!=null){
				cargo++;
			}
		}
		if (cargo != 0) {
			GL11.glPushMatrix();
			GL11.glTranslatef(0, -0.8f+cargo*0.023f, 0);
			box10.render();
			GL11.glPopMatrix();
		}
		super.render(entity, f, f1, f2, f3, f4, f5);
	}

}