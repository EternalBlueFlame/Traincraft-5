package train.render.models;

import fexcraft.tmt.slim.ModelBase;
import fexcraft.tmt.slim.ModelRendererTurbo;
import train.render.CustomModelRenderer;

public class ModelGrain extends ModelBase {

	public ModelGrain() {

		// constructor:
		box = new CustomModelRenderer(this, 57, 28, 256, 128);
		box.addBox(0F, 0F, 0F, 7, 3, 14);
		box.setPosition(11F, 2F, -7F);

		box0 = new CustomModelRenderer(this, 4, 35, 256, 128);
		box0.addBox(0F, 0F, 0F, 16, 3, 16);
		box0.setPosition(-23F, 6F, -8F);

		box1 = new CustomModelRenderer(this, 4, 36, 256, 128);
		box1.addBox(0F, 0F, 0F, 16, 3, 16);
		box1.setPosition(7F, 6F, -8F);

		box10 = new CustomModelRenderer(this, 182, 93, 256, 128);
		box10.addBox(0F, 0F, 0F, 12, 7, 6);
		box10.setPosition(-6F, 3F, -4F);
		box10.rotateAngleX = -0.4363323129985824F;

		box11 = new CustomModelRenderer(this, 248, 98, 256, 128);
		box11.addBox(0F, 0F, 0F, 1, 21, 1);
		box11.setPosition(-22F, 8F, -9F);
		box11.rotateAngleZ = -0.4014257279586958F;

		box12 = new CustomModelRenderer(this, 2, 26, 256, 128);
		box12.addBox(0F, 0F, 0F, 7, 3, 4);
		box12.setPosition(-30F, 6F, -2F);

		box13 = new CustomModelRenderer(this, 248, 98, 256, 128);
		box13.addBox(0F, 0F, 0F, 1, 21, 1);
		box13.setPosition(-22F, 8F, 8F);
		box13.rotateAngleZ = -0.4014257279586958F;

		box14 = new CustomModelRenderer(this, 57, 28, 256, 128);
		box14.addBox(0F, 0F, 0F, 7, 3, 14);
		box14.setPosition(-18F, 2F, -7F);

		box15 = new CustomModelRenderer(this, 248, 98, 256, 128);
		box15.addBox(0F, 0F, 0F, 1, 21, 1);
		box15.setPosition(22F, 8F, 9F);
		box15.rotateAngleY = -3.141592653589793F;
		box15.rotateAngleZ = 0.4014257279586958F;

		box16 = new CustomModelRenderer(this, 248, 98, 256, 128);
		box16.addBox(0F, 0F, 0F, 1, 21, 1);
		box16.setPosition(22F, 8F, -8F);
		box16.rotateAngleY = -3.141592653589793F;
		box16.rotateAngleZ = 0.4014257279586958F;

		box17 = new CustomModelRenderer(this, 3, 63, 256, 128);
		box17.addBox(0F, 0F, 0F, 44, 1, 6);
		box17.setPosition(22F, 27F, 9F);
		box17.rotateAngleX = -0.17453292519943295F;
		box17.rotateAngleY = -3.141592653589793F;

		box18 = new CustomModelRenderer(this, 3, 63, 256, 128);
		box18.addBox(0F, 0F, 0F, 44, 1, 6);
		box18.setPosition(-22F, 27F, -9F);
		box18.rotateAngleX = -0.17453292519943295F;

		box19 = new CustomModelRenderer(this, 235, 101, 256, 128);
		box19.addBox(0F, 0F, 0F, 1, 14, 1);
		box19.setPosition(-18F, 9F, 1F);
		box19.rotateAngleX = -0.4188790204786391F;
		box19.rotateAngleY = -3.141592653589793F;

		box2 = new CustomModelRenderer(this, 114, 107, 256, 128);
		box2.addBox(0F, 0F, 0F, 46, 9, 12);
		box2.setPosition(-23F, 0F, -6F);

		box20 = new CustomModelRenderer(this, 235, 101, 256, 128);
		box20.addBox(0F, 0F, 0F, 1, 14, 1);
		box20.setPosition(19F, 9F, 1F);
		box20.rotateAngleX = 0.4188790204786391F;
		box20.rotateAngleY = 3.141592653589793F;

		box21 = new CustomModelRenderer(this, 235, 101, 256, 128);
		box21.addBox(0F, 0F, 0F, 1, 14, 1);
		box21.setPosition(18F, 9F, -1F);
		box21.rotateAngleX = 0.4188790204786391F;

		box22 = new CustomModelRenderer(this, 235, 101, 256, 128);
		box22.addBox(0F, 0F, 0F, 1, 14, 1);
		box22.setPosition(-19F, 9F, -1F);
		box22.rotateAngleX = -0.4188790204786391F;

		box23 = new CustomModelRenderer(this, 2, 26, 256, 128);
		box23.addBox(0F, 0F, 0F, 6, 3, 4);
		box23.setPosition(23F, 6F, -2F);

		box24 = new CustomModelRenderer(this, 171, 33, 256, 128);
		box24.addBox(0F, 0F, 0F, 26, 1, 16);
		box24.setPosition(-13F, 9F, -8F);

		box25 = new CustomModelRenderer(this, 0, 111, 256, 128);
		box25.addBox(0F, 0F, 0F, 46, 7, 10);
		box25.setPosition(-23F, 0F, -5F);

		box26 = new CustomModelRenderer(this, 182, 93, 256, 128);
		box26.addBox(0F, 0F, 0F, 12, 7, 6);
		box26.setPosition(6F, 3F, 5F);
		box26.rotateAngleX = -0.4363323129985824F;
		box26.rotateAngleY = -3.141592653589793F;

		box27 = new CustomModelRenderer(this, 221, 90, 256, 128);
		box27.addBox(0F, 0F, 0F, 1, 19, 4);
		box27.setPosition(21F, 9F, 0F);

		box28 = new CustomModelRenderer(this, 171, 40, 256, 128);
		box28.addBox(0F, 0F, 0F, 4, 4, 12);
		box28.setPosition(-18F, 9F, -6F);

		box29 = new CustomModelRenderer(this, 138, 60, 256, 128);
		box29.addBox(0F, 0F, 0F, 44, 1, 6);
		box29.setPosition(-22F, 28F, -3F);

		box3 = new CustomModelRenderer(this, 0, 76, 256, 128);
		box3.addBox(0F, 0F, 0F, 26, 18, 16);
		box3.setPosition(-13F, 10F, -8F);

		box30 = new CustomModelRenderer(this, 46, 25, 256, 128);
		box30.addBox(0F, 0F, 0F, 6, 2, 6);
		box30.setPosition(8F, 29F, -3F);

		box31 = new CustomModelRenderer(this, 44, 26, 256, 128);
		box31.addBox(0F, 0F, 0F, 6, 2, 6);
		box31.setPosition(-14F, 29F, -3F);

		box4 = new CustomModelRenderer(this, 104, 39, 256, 128);
		box4.addBox(0F, 0F, 0F, 14, 5, 8);
		box4.setPosition(-21F, 3F, -4F);

		box5 = new CustomModelRenderer(this, 93, 58, 256, 128);
		box5.addBox(0F, 0F, 0F, 8, 6, 16);
		box5.setPosition(-21F, 22F, -8F);

		box6 = new CustomModelRenderer(this, 84, 83, 256, 128);
		box6.addBox(0F, 0F, 0F, 8, 6, 16);
		box6.setPosition(13F, 22F, -8F);

		box7 = new CustomModelRenderer(this, 135, 77, 256, 128);
		box7.addBox(0F, 0F, 0F, 8, 14, 14);
		box7.setPosition(-13F, 10F, -7F);
		box7.rotateAngleZ = -5.707226654021458F;

		box8 = new CustomModelRenderer(this, 135, 77, 256, 128);
		box8.addBox(0F, 0F, 0F, 8, 14, 14);
		box8.setPosition(13F, 10F, 7F);
		box8.rotateAngleY = -3.141592653589793F;
		box8.rotateAngleZ = 5.707226654021458F;

		box9 = new CustomModelRenderer(this, 81, 30, 256, 128);
		box9.addBox(0F, 0F, 0F, 14, 5, 8);
		box9.setPosition(8F, 3F, -4F);


		bodyModel=new ModelRendererTurbo[]{box,
				box0,box1,box2,box3,box4,box5,box6,box7,box8,box9,
				box10,box11,box12,box13,box14,box15,box16,box17,box18,box19,
				box20,box21,box22,box23,box24,box25,box26,box27,box28,box29,
				box30,box31
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
	public CustomModelRenderer box14;
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
	public CustomModelRenderer box26;
	public CustomModelRenderer box27;
	public CustomModelRenderer box28;
	public CustomModelRenderer box29;
	public CustomModelRenderer box3;
	public CustomModelRenderer box30;
	public CustomModelRenderer box31;
	public CustomModelRenderer box4;
	public CustomModelRenderer box5;
	public CustomModelRenderer box6;
	public CustomModelRenderer box7;
	public CustomModelRenderer box8;
	public CustomModelRenderer box9;

}
