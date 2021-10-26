package train.render.models;

import fexcraft.tmt.slim.ModelBase;
import fexcraft.tmt.slim.ModelRendererTurbo;
import train.render.CustomModelRenderer;

public class ModelBoxCartUS extends ModelBase {

	public ModelBoxCartUS() {
		box = new CustomModelRenderer(this, 10, 1, 128, 128);
		box.addBox(0F, 0F, 0F, 4, 3, 3);
		box.setPosition(13F, 1F, -7F);

		box0 = new CustomModelRenderer(this, 0, 34, 128, 128);
		box0.addBox(0F, -1F, 0F, 46, 2, 9);
		box0.setPosition(-23F, 32F, -9F);
		box0.rotateAngleX = -0.15707963267948966F;

		box1 = new CustomModelRenderer(this, 7, 2, 128, 128);
		box1.addBox(0F, 0F, 0F, 7, 3, 4);
		box1.setPosition(23F, 6F, -2F);

		box10 = new CustomModelRenderer(this, 0, 0, 128, 128);
		box10.addBox(0F, 0F, 0F, 2, 2, 10);
		box10.setPosition(-12F, 2F, -5F);

		box11 = new CustomModelRenderer(this, 0, 0, 128, 128);
		box11.addBox(0F, 0F, 0F, 2, 2, 10);
		box11.setPosition(-22F, 2F, -5F);

		box12 = new CustomModelRenderer(this, 3, 2, 128, 128);
		box12.addBox(0F, 0F, 0F, 4, 3, 3);
		box12.setPosition(-17F, 1F, 4F);

		box13 = new CustomModelRenderer(this, 5, 2, 128, 128);
		box13.addBox(0F, 0F, 0F, 4, 3, 3);
		box13.setPosition(-17F, 1F, -7F);

		box15 = new CustomModelRenderer(this, 1, 1, 128, 128);
		box15.addBox(0F, 0F, 0F, 20, 1, 1);
		box15.setPosition(-11F, 5F, 3F);

		box16 = new CustomModelRenderer(this, 1, 1, 128, 128);
		box16.addBox(0F, 0F, 0F, 20, 1, 1);
		box16.setPosition(-11F, 5F, -4F);

		box17 = new CustomModelRenderer(this, 93, 1, 128, 128);
		box17.addBox(0F, 0F, 0F, 16, 22, 1);
		box17.setPosition(-8F, 8F, -10F);

		box18 = new CustomModelRenderer(this, 93, 1, 128, 128);
		box18.addBox(0F, 0F, 0F, 16, 22, 1);
		box18.setPosition(8F, 8F, 10F);
		box18.rotateAngleY = -3.141592653589793F;

		box19 = new CustomModelRenderer(this, 0, 46, 128, 128);
		box19.addBox(0F, 0F, 0F, 46, 26, 1);
		box19.setPosition(-23F, 6F, -9F);

		box2 = new CustomModelRenderer(this, 0, 92, 128, 128);
		box2.addBox(0F, 0F, 0F, 48, 7, 12);
		box2.setPosition(-24F, 0F, -6F);

		box20 = new CustomModelRenderer(this, 0, 46, 128, 128);
		box20.addBox(0F, 0F, 0F, 46, 26, 1);
		box20.setPosition(23F, 6F, 9F);
		box20.rotateAngleY = -3.141592653589793F;

		box21 = new CustomModelRenderer(this, 94, 32, 128, 128);
		box21.addBox(0F, 0F, 0F, 1, 26, 16);
		box21.setPosition(-22F, 6F, 8F);
		box21.rotateAngleY = -3.141592653589793F;

		box22 = new CustomModelRenderer(this, 94, 32, 128, 128);
		box22.addBox(0F, 0F, 0F, 1, 26, 16);
		box22.setPosition(22F, 6F, -8F);

		box23 = new CustomModelRenderer(this, 1, 1, 128, 128);
		box23.addBox(0F, 0F, 0F, 7, 3, 4);
		box23.setPosition(-30F, 6F, -2F);

		box24 = new CustomModelRenderer(this, 0, 74, 128, 128);
		box24.addBox(0F, 0F, 0F, 44, 2, 16);
		box24.setPosition(-22F, 6F, -8F);

		box25 = new CustomModelRenderer(this, 0, 111, 128, 128);
		box25.addBox(0F, 0F, 0F, 48, 7, 10);
		box25.setPosition(-24F, 0F, -5F);

		box3 = new CustomModelRenderer(this, 0, 0, 128, 128);
		box3.addBox(0F, 0F, 0F, 2, 2, 10);
		box3.setPosition(20F, 2F, -5F);

		box4 = new CustomModelRenderer(this, 1, 0, 128, 128);
		box4.addBox(0F, 0F, 0F, 10, 2, 8);
		box4.setPosition(-21F, 4F, -4F);

		box5 = new CustomModelRenderer(this, 0, 0, 128, 128);
		box5.addBox(0F, 0F, 0F, 2, 2, 10);
		box5.setPosition(10F, 2F, -5F);

		box6 = new CustomModelRenderer(this, 0, 34, 128, 128);
		box6.addBox(0F, -1F, 0F, 46, 2, 9);
		box6.setPosition(-23F, 32F, 9F);
		box6.rotateAngleX = -2.9845130209103035F;

		box7 = new CustomModelRenderer(this, 1, 25, 128, 128);
		box7.addBox(0F, -1F, 0F, 48, 1, 4);
		box7.setPosition(-24F, 35F, -2F);

		box8 = new CustomModelRenderer(this, 6, 2, 128, 128);
		box8.addBox(0F, 0F, 0F, 4, 3, 3);
		box8.setPosition(13F, 1F, 4F);

		box9 = new CustomModelRenderer(this, 0, 0, 128, 128);
		box9.addBox(0F, 0F, 0F, 10, 2, 8);
		box9.setPosition(11F, 4F, -4F);

		bodyModel=new ModelRendererTurbo[]{box,
				box0,box1,box2,box3,box4,box5,box6,box7,box8,box9,
				box10,box11,box12,box13,box15,box16,box17,box18,box19,
				box20,box21,box22,box23,box24,box25
		};

		fixRotation(bodyModel);

	}

    // variables init:
	public CustomModelRenderer box;
	public CustomModelRenderer box0;
	public CustomModelRenderer box1;
	public CustomModelRenderer box10;
	public CustomModelRenderer box11;
	public CustomModelRenderer box12;
	public CustomModelRenderer box13;
	public CustomModelRenderer box15;
	public CustomModelRenderer box16;
	public CustomModelRenderer box17;
	public CustomModelRenderer box18;
	public CustomModelRenderer box19;
	public CustomModelRenderer box2;
	public CustomModelRenderer box20;
	public CustomModelRenderer box21;
	public CustomModelRenderer box22;
	public CustomModelRenderer box23;
	public CustomModelRenderer box24;
	public CustomModelRenderer box25;
	public CustomModelRenderer box3;
	public CustomModelRenderer box4;
	public CustomModelRenderer box5;
	public CustomModelRenderer box6;
	public CustomModelRenderer box7;
	public CustomModelRenderer box8;
	public CustomModelRenderer box9;

}
