package train.render.models;

import fexcraft.tmt.slim.ModelBase;
import fexcraft.tmt.slim.ModelRendererTurbo;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;
import train.core.ClientProxy;
import train.render.CustomModelRenderer;
import train.render.models.blocks.ModelRing;

public class ModelLocoC62Class extends ModelBase {
	
	private ModelRing ring;
	
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
	public CustomModelRenderer box32;
	public CustomModelRenderer box33;
	public CustomModelRenderer box34;
	public CustomModelRenderer box35;
	public CustomModelRenderer box36;
	public CustomModelRenderer box37;
	public CustomModelRenderer box38;
	public CustomModelRenderer box39;
	public CustomModelRenderer box4;
	public CustomModelRenderer box40;
	public CustomModelRenderer box41;
	public CustomModelRenderer box42;
	public CustomModelRenderer box43;
	public CustomModelRenderer box44;
	public CustomModelRenderer box45;
	public CustomModelRenderer box46;
	public CustomModelRenderer box47;
	public CustomModelRenderer box48;
	public CustomModelRenderer box49;
	public CustomModelRenderer box5;
	public CustomModelRenderer box50;
	public CustomModelRenderer box51;
	public CustomModelRenderer box52;
	public CustomModelRenderer box53;
	public CustomModelRenderer box54;
	public CustomModelRenderer box55;
	public CustomModelRenderer box56;
	public CustomModelRenderer box57;
	public CustomModelRenderer box58;
	public CustomModelRenderer box59;
	public CustomModelRenderer box6;
	public CustomModelRenderer box60;
	public CustomModelRenderer box61;
	public CustomModelRenderer box62;
	public CustomModelRenderer box63;
	public CustomModelRenderer box64;
	public CustomModelRenderer box65;
	public CustomModelRenderer box66;
	public CustomModelRenderer box67;
	public CustomModelRenderer box68;
	public CustomModelRenderer box69;
	public CustomModelRenderer box7;
	public CustomModelRenderer box70;
	public CustomModelRenderer box71;
	public CustomModelRenderer box72;
	public CustomModelRenderer box73;
	public CustomModelRenderer box74;
	public CustomModelRenderer box75;
	public CustomModelRenderer box76;
	public CustomModelRenderer box77;
	public CustomModelRenderer box78;
	public CustomModelRenderer box79;
	public CustomModelRenderer box8;
	public CustomModelRenderer box80;
	public CustomModelRenderer box81;
	public CustomModelRenderer box82;
	public CustomModelRenderer box83;
	public CustomModelRenderer box84;
	public CustomModelRenderer box85;
	public CustomModelRenderer box86;
	public CustomModelRenderer box87;
	public CustomModelRenderer box88;
	public CustomModelRenderer box89;
	public CustomModelRenderer box9;
	public CustomModelRenderer box90;
	public CustomModelRenderer box91;
	public CustomModelRenderer box92;
	public CustomModelRenderer box93;
	public CustomModelRenderer box94;
	public CustomModelRenderer box95;
	public CustomModelRenderer box96;
	public CustomModelRenderer box97;
	public CustomModelRenderer box98;
	public CustomModelRenderer box99;

	public ModelLocoC62Class() {

		ring = new ModelRing();
		
		box = new CustomModelRenderer(this, 1, 24, 132, 256);
		box.addBox(0F, 0F, 0F, 14, 14, 1);
		box.setPosition(-36F, 0F, -7F);
		box0 = new CustomModelRenderer(this, 110, 11, 132, 256);
		box0.addBox(0F, 0F, 0F, 8, 8, 1);
		box0.setPosition(-93F, 0F, -7F);
		box1 = new CustomModelRenderer(this, 110, 11, 132, 256);
		box1.addBox(0F, 0F, 0F, 8, 8, 1);
		box1.setPosition(-75F, 0F, -7F);
		box10 = new CustomModelRenderer(this, 1, 24, 132, 256);
		box10.addBox(0F, 0F, 0F, 14, 14, 1);
		box10.setPosition(-66F, 0F, 6F);
		box11 = new CustomModelRenderer(this, 0, 163, 132, 256);
		box11.addBox(0F, 0F, 0F, 40, 6, 14);
		box11.setPosition(-49F, 16F, -3F);
		box11.rotateAngleX = -0.7853981633974483F;
		box12 = new CustomModelRenderer(this, 0, 123, 132, 256);
		box12.addBox(0F, 0F, 0F, 40, 14, 6);
		box12.setPosition(-49F, 26F, -7F);
		box12.rotateAngleX = -4.71238898038469F;
		box13 = new CustomModelRenderer(this, 41, 81, 132, 256);
		box13.addBox(0F, 0F, 0F, 1, 5, 20);
		box13.setPosition(-96F, 4F, -10F);
		box14 = new CustomModelRenderer(this, 63, 77, 132, 256);
		box14.addBox(0F, 0F, 0F, 1, 4, 16);
		box14.setPosition(-3F, 2F, -8F);
		box15 = new CustomModelRenderer(this, 2, 2, 132, 256);
		box15.addBox(0F, 0F, 0F, 13, 1, 20);
		box15.setPosition(-49F, 18F, -10F);
		box16 = new CustomModelRenderer(this, 2, 1, 132, 256);
		box16.addBox(0F, 0F, 0F, 5, 7, 4);
		box16.setPosition(-83F, 27F, -2F);
		box17 = new CustomModelRenderer(this, 0, 61, 132, 256);
		box17.addBox(0F, 0F, 0F, 31, 6, 6);
		box17.setPosition(-69F, 26F, -3F);
		box18 = new CustomModelRenderer(this, 0, 73, 132, 256);
		box18.addBox(0F, 0F, 0F, 16, 12, 14);
		box18.setPosition(-21F, 8F, -7F);
		box18.rotateAngleZ = -6.1086523819801535F;
		box19 = new CustomModelRenderer(this, 93, 148, 132, 256);
		box19.addBox(0F, 0F, 0F, 16, 8, 1);
		box19.setPosition(-8F, 15F, -10F);
		box2 = new CustomModelRenderer(this, 88, 51, 132, 256);
		box2.addBox(-1F, 0F, 0F, 1, 12, 20);
		box2.setPosition(-91F, 9F, -10F);
		box2.rotateAngleZ = -0.6632251157578453F;
		box20 = new CustomModelRenderer(this, 1, 24, 132, 256);
		box20.addBox(0F, 0F, 0F, 14, 14, 1);
		box20.setPosition(-51F, 0F, 6F);
		box21 = new CustomModelRenderer(this, 41, 218, 132, 256);
		box21.addBox(0F, 0F, 0F, 5, 1, 20);
		box21.setPosition(-96F, 9F, -10F);
		box22 = new CustomModelRenderer(this, 86, 221, 132, 256);
		box22.addBox(0F, 0F, 0F, 2, 15, 20);
		box22.setPosition(-10F, 15F, -10F);
		box23 = new CustomModelRenderer(this, 0, 208, 132, 256);
		box23.addBox(0F, 0F, 0F, 7, 3, 20);
		box23.setPosition(1F, 12F, -10F);
		box24 = new CustomModelRenderer(this, 1, 24, 132, 256);
		box24.addBox(0F, 0F, 0F, 14, 14, 1);
		box24.setPosition(-36F, 0F, 6F);
		box25 = new CustomModelRenderer(this, 50, 183, 132, 256);
		box25.addBox(0F, 0F, 0F, 21, 1, 14);
		box25.setPosition(-10F, 33F, -7F);
		box26 = new CustomModelRenderer(this, 0, 99, 132, 256);
		box26.addBox(0F, 0F, 0F, 17, 7, 1);
		box26.setPosition(-20F, 2F, -8F);
		box27 = new CustomModelRenderer(this, 49, 24, 132, 256);
		box27.addBox(0F, 0F, 0F, 14, 6, 1);
		box27.setPosition(-90F, 22F, -10F);
		box28 = new CustomModelRenderer(this, 0, 99, 132, 256);
		box28.addBox(0F, 0F, 0F, 17, 7, 1);
		box28.setPosition(-20F, 2F, 7F);
		box29 = new CustomModelRenderer(this, 0, 39, 132, 256);
		box29.addBox(0F, 0F, 0F, 40, 10, 12);
		box29.setPosition(-64F, 3F, -6F);
		box3 = new CustomModelRenderer(this, 95, 111, 132, 256);
		box3.addBox(0F, 0F, 0F, 5, 5, 12);
		box3.setPosition(-94F, 10F, -6F);
		box30 = new CustomModelRenderer(this, 0, 236, 132, 256);
		box30.addBox(0F, 0F, 0F, 14, 7, 12);
		box30.setPosition(-9F, 8F, -6F);
		box31 = new CustomModelRenderer(this, 52, 239, 132, 256);
		box31.addBox(0F, 0F, 0F, 2, 3, 14);
		box31.setPosition(-10F, 30F, -7F);
		box32 = new CustomModelRenderer(this, 0, 202, 132, 256);
		box32.addBox(0F, 0F, -1F, 21, 5, 1);
		box32.setPosition(-10F, 30F, -10F);
		box32.rotateAngleX = -5.445427266222308F;
		box33 = new CustomModelRenderer(this, 0, 202, 132, 256);
		box33.addBox(0F, 0F, 0F, 21, 5, 1);
		box33.setPosition(-10F, 30F, 10F);
		box33.rotateAngleX = -0.8552113334772214F;
		box34 = new CustomModelRenderer(this, 49, 24, 132, 256);
		box34.addBox(0F, 0F, 0F, 14, 6, 1);
		box34.setPosition(-90F, 22F, 9F);
		box35 = new CustomModelRenderer(this, 38, 24, 132, 256);
		box35.addBox(0F, 0F, 0F, 4, 5, 1);
		box35.setPosition(-93F, 24F, 9F);
		box35.rotateAngleZ = -0.6632251157578453F;
		box36 = new CustomModelRenderer(this, 96, 137, 132, 256);
		box36.addBox(0F, 0F, 0F, 2, 5, 1);
		box36.setPosition(6F, 23F, -10F);
		box37 = new CustomModelRenderer(this, 96, 137, 132, 256);
		box37.addBox(0F, 0F, 0F, 2, 5, 1);
		box37.setPosition(1F, 23F, -10F);
		box38 = new CustomModelRenderer(this, 2, 2, 132, 256);
		box38.addBox(0F, 0F, 0F, 13, 1, 20);
		box38.setPosition(-23F, 18F, -10F);
		box39 = new CustomModelRenderer(this, 1, 24, 132, 256);
		box39.addBox(0F, 0F, 0F, 14, 14, 1);
		box39.setPosition(-51F, 0F, -7F);
		box4 = new CustomModelRenderer(this, 0, 107, 132, 256);
		box4.addBox(0F, 0F, 0F, 51, 15, 1);
		box4.setPosition(-76F, 3F, -8F);
		box40 = new CustomModelRenderer(this, 2, 2, 132, 256);
		box40.addBox(0F, 0F, 0F, 5, 1, 20);
		box40.setPosition(-36F, 18F, -10F);
		box40.rotateAngleZ = -5.497787143782138F;
		box41 = new CustomModelRenderer(this, 2, 2, 132, 256);
		box41.addBox(0F, -1F, 0F, 5, 1, 20);
		box41.setPosition(-23F, 18F, -10F);
		box41.rotateAngleZ = -3.9269908169872414F;
		box42 = new CustomModelRenderer(this, 2, 2, 132, 256);
		box42.addBox(0F, 0F, 0F, 7, 1, 20);
		box42.setPosition(-33F, 21F, -10F);
		box43 = new CustomModelRenderer(this, 93, 148, 132, 256);
		box43.addBox(0F, 0F, 0F, 16, 8, 1);
		box43.setPosition(-8F, 15F, 9F);
		box44 = new CustomModelRenderer(this, 96, 137, 132, 256);
		box44.addBox(0F, 0F, 0F, 2, 5, 1);
		box44.setPosition(1F, 23F, 9F);
		box45 = new CustomModelRenderer(this, 93, 23, 132, 256);
		box45.addBox(0F, 0F, 0F, 1, 14, 14);
		box45.setPosition(-90F, 16F, -7F);
		box46 = new CustomModelRenderer(this, 112, 1, 132, 256);
		box46.addBox(0F, 0F, 0F, 3, 4, 4);
		box46.setPosition(-92F, 29F, -2F);
		box47 = new CustomModelRenderer(this, 89, 24, 132, 256);
		box47.addBox(0F, 0F, 0F, 5, 3, 4);
		box47.setPosition(-101F, 5F, -2F);
		box48 = new CustomModelRenderer(this, 96, 137, 132, 256);
		box48.addBox(0F, 0F, 0F, 2, 5, 1);
		box48.setPosition(6F, 23F, 9F);
		box49 = new CustomModelRenderer(this, 0, 143, 132, 256);
		box49.addBox(0F, 0F, 0F, 40, 14, 6);
		box49.setPosition(-49F, 30F, -3F);
		box49.rotateAngleX = -3.9269908169872414F;
		box5 = new CustomModelRenderer(this, 1, 24, 132, 256);
		box5.addBox(0F, 0F, 0F, 14, 14, 1);
		box5.setPosition(-66F, 0F, -7F);
		box50 = new CustomModelRenderer(this, 0, 143, 132, 256);
		box50.addBox(0F, 0F, 0F, 40, 14, 6);
		box50.setPosition(-49F, 16F, -3F);
		box51 = new CustomModelRenderer(this, 86, 128, 132, 256);
		box51.addBox(0F, 0F, 0F, 1, 1, 18);
		box51.setPosition(-78F, 27F, -9F);
		box52 = new CustomModelRenderer(this, 110, 11, 132, 256);
		box52.addBox(0F, 0F, 0F, 8, 8, 1);
		box52.setPosition(-11F, 0F, -7F);
		box53 = new CustomModelRenderer(this, 1, 12, 132, 256);
		box53.addBox(0F, 0F, 0F, 1, 4, 4);
		box53.setPosition(-89F, 29F, -2F);
		box54 = new CustomModelRenderer(this, 110, 11, 132, 256);
		box54.addBox(0F, 0F, 0F, 8, 8, 1);
		box54.setPosition(-20F, 0F, -7F);
		box55 = new CustomModelRenderer(this, 0, 202, 132, 256);
		box55.addBox(0F, 0F, 0F, 21, 2, 1);
		box55.setPosition(-10F, 28F, 9F);
		box56 = new CustomModelRenderer(this, 0, 202, 132, 256);
		box56.addBox(0F, 0F, 0F, 21, 2, 1);
		box56.setPosition(-10F, 28F, -10F);
		box57 = new CustomModelRenderer(this, 0, 183, 132, 256);
		box57.addBox(0F, 0F, 0F, 16, 1, 18);
		box57.setPosition(-8F, 15F, -9F);
		box58 = new CustomModelRenderer(this, 0, 163, 132, 256);
		box58.addBox(0F, 0F, 0F, 40, 6, 14);
		box58.setPosition(-89F, 16F, -3F);
		box58.rotateAngleX = -0.7853981633974483F;
		box59 = new CustomModelRenderer(this, 0, 143, 132, 256);
		box59.addBox(0F, 0F, 0F, 40, 14, 6);
		box59.setPosition(-89F, 30F, -3F);
		box59.rotateAngleX = -3.9269908169872414F;
		box6 = new CustomModelRenderer(this, 110, 11, 132, 256);
		box6.addBox(0F, 0F, 0F, 8, 8, 1);
		box6.setPosition(-93F, 0F, 6F);
		box60 = new CustomModelRenderer(this, 0, 123, 132, 256);
		box60.addBox(0F, 0F, 0F, 40, 14, 6);
		box60.setPosition(-89F, 26F, -7F);
		box60.rotateAngleX = -4.71238898038469F;
		box61 = new CustomModelRenderer(this, 19, 2, 132, 256);
		box61.addBox(0F, 0F, 0F, 35, 1, 20);
		box61.setPosition(-84F, 18F, -10F);
		box62 = new CustomModelRenderer(this, 32, 24, 132, 256);
		box62.addBox(-1F, 0F, 0F, 2, 12, 1);
		box62.setPosition(-92F, 10F, -10F);
		box62.rotateAngleZ = -0.6632251157578453F;
		box63 = new CustomModelRenderer(this, 38, 24, 132, 256);
		box63.addBox(0F, 0F, 0F, 4, 5, 1);
		box63.setPosition(-93F, 24F, -10F);
		box63.rotateAngleZ = -0.6632251157578453F;
		box64 = new CustomModelRenderer(this, 80, 24, 132, 256);
		box64.addBox(0F, 0F, 0F, 3, 14, 1);
		box64.setPosition(-93F, 10F, -10F);
		box65 = new CustomModelRenderer(this, 38, 30, 132, 256);
		box65.addBox(0F, 0F, 0F, 4, 7, 1);
		box65.setPosition(-93F, 16F, -10F);
		box65.rotateAngleZ = -0.6632251157578453F;
		box66 = new CustomModelRenderer(this, 62, 61, 132, 256);
		box66.addBox(-1F, 0F, 0F, 1, 4, 12);
		box66.setPosition(-100F, 1F, 0F);
		box66.rotateAngleY = -3.5604716740684323F;
		box67 = new CustomModelRenderer(this, 62, 61, 132, 256);
		box67.addBox(0F, 0F, 0F, 1, 4, 12);
		box67.setPosition(-100F, 1F, 0F);
		box67.rotateAngleY = -5.846852994181004F;
		box68 = new CustomModelRenderer(this, 0, 143, 132, 256);
		box68.addBox(0F, 0F, 0F, 40, 14, 6);
		box68.setPosition(-89F, 16F, -3F);
		box69 = new CustomModelRenderer(this, 86, 128, 132, 256);
		box69.addBox(0F, 0F, 0F, 1, 1, 18);
		box69.setPosition(-88F, 27F, -9F);
		box7 = new CustomModelRenderer(this, 110, 11, 132, 256);
		box7.addBox(0F, 0F, 0F, 8, 8, 1);
		box7.setPosition(-75F, 0F, 6F);
		box70 = new CustomModelRenderer(this, 71, 219, 132, 256);
		box70.addBox(0F, 0F, 0F, 16, 11, 1);
		box70.setPosition(-9F, 4F, -9F);
		box71 = new CustomModelRenderer(this, 71, 219, 132, 256);
		box71.addBox(0F, 0F, 0F, 16, 11, 1);
		box71.setPosition(-9F, 4F, 8F);
		box72 = new CustomModelRenderer(this, 110, 11, 132, 256);
		box72.addBox(0F, 0F, 0F, 8, 8, 1);
		box72.setPosition(-20F, 0F, 6F);
		box73 = new CustomModelRenderer(this, 110, 11, 132, 256);
		box73.addBox(0F, 0F, 0F, 8, 8, 1);
		box73.setPosition(-11F, 0F, 6F);
		box74 = new CustomModelRenderer(this, 44, 202, 132, 256);
		box74.addBox(0F, 0F, 0F, 43, 15, 1);
		box74.setPosition(-66F, 6F, -9F);
		box75 = new CustomModelRenderer(this, 0, 39, 132, 256);
		box75.addBox(0F, 0F, 0F, 23, 4, 12);
		box75.setPosition(-92F, 2F, -6F);
		box76 = new CustomModelRenderer(this, 63, 77, 132, 256);
		box76.addBox(0F, 0F, 0F, 1, 4, 16);
		box76.setPosition(-21F, 2F, -8F);
		box77 = new CustomModelRenderer(this, 80, 24, 132, 256);
		box77.addBox(0F, 0F, 0F, 3, 14, 1);
		box77.setPosition(-93F, 10F, 9F);
		box78 = new CustomModelRenderer(this, 38, 30, 132, 256);
		box78.addBox(0F, 0F, 0F, 4, 7, 1);
		box78.setPosition(-93F, 16F, 9F);
		box78.rotateAngleZ = -0.6632251157578453F;
		box79 = new CustomModelRenderer(this, 32, 24, 132, 256);
		box79.addBox(-1F, 0F, 0F, 2, 12, 1);
		box79.setPosition(-92F, 10F, 9F);
		box79.rotateAngleZ = -0.6632251157578453F;
		box8 = new CustomModelRenderer(this, 100, 83, 132, 256);
		box8.addBox(0F, 0F, 0F, 8, 15, 7);
		box8.setPosition(-84F, 3F, 3F);
		box80 = new CustomModelRenderer(this, 0, 107, 132, 256);
		box80.addBox(0F, 0F, 0F, 51, 15, 1);
		box80.setPosition(-76F, 3F, 7F);
		box81 = new CustomModelRenderer(this, 44, 202, 132, 256);
		box81.addBox(0F, 0F, 0F, 43, 15, 1);
		box81.setPosition(-66F, 6F, 8F);
		box82 = new CustomModelRenderer(this, 94, 157, 132, 256);
		box82.addBox(0F, 0F, 0F, 2, 16, 3);
		box82.setPosition(6F, 16F, 6F);
		box83 = new CustomModelRenderer(this, 94, 157, 132, 256);
		box83.addBox(0F, 0F, 0F, 2, 16, 3);
		box83.setPosition(6F, 16F, -9F);
		box84 = new CustomModelRenderer(this, 49, 32, 132, 256);
		box84.addBox(0F, 0F, 0F, 6, 3, 1);
		box84.setPosition(-90F, 19F, -10F);
		box85 = new CustomModelRenderer(this, 64, 34, 132, 256);
		box85.addBox(0F, 0F, 0F, 1, 3, 1);
		box85.setPosition(-77F, 19F, -10F);
		box86 = new CustomModelRenderer(this, 49, 32, 132, 256);
		box86.addBox(0F, 0F, 0F, 6, 3, 1);
		box86.setPosition(-90F, 19F, 9F);
		box87 = new CustomModelRenderer(this, 64, 34, 132, 256);
		box87.addBox(0F, 0F, 0F, 1, 3, 1);
		box87.setPosition(-77F, 19F, 9F);
		box88 = new CustomModelRenderer(this, 117, 105, 132, 256);
		box88.addBox(0F, 0F, 0F, 1, 5, 5);
		box88.setPosition(-85F, 3F, -10F);
		box89 = new CustomModelRenderer(this, 117, 105, 132, 256);
		box89.addBox(0F, 0F, 0F, 1, 5, 5);
		box89.setPosition(-76F, 3F, -10F);
		box9 = new CustomModelRenderer(this, 100, 83, 132, 256);
		box9.addBox(0F, 0F, 0F, 8, 15, 7);
		box9.setPosition(-84F, 3F, -10F);
		box90 = new CustomModelRenderer(this, 107, 105, 132, 256);
		box90.addBox(0F, 0F, 0F, 2, 3, 3);
		box90.setPosition(-76F, 10F, -10F);
		box91 = new CustomModelRenderer(this, 107, 105, 132, 256);
		box91.addBox(0F, 0F, 0F, 2, 3, 3);
		box91.setPosition(-86F, 10F, -10F);
		box92 = new CustomModelRenderer(this, 0, 39, 132, 256);
		box92.addBox(0F, 0F, 0F, 26, 4, 12);
		box92.setPosition(-90F, 10F, -6F);
		box93 = new CustomModelRenderer(this, 117, 105, 132, 256);
		box93.addBox(0F, 0F, 0F, 1, 5, 5);
		box93.setPosition(-85F, 3F, 5F);
		box94 = new CustomModelRenderer(this, 117, 105, 132, 256);
		box94.addBox(0F, 0F, 0F, 1, 5, 5);
		box94.setPosition(-76F, 3F, 5F);
		box95 = new CustomModelRenderer(this, 107, 105, 132, 256);
		box95.addBox(0F, 0F, 0F, 2, 3, 3);
		box95.setPosition(-76F, 10F, 7F);
		box96 = new CustomModelRenderer(this, 107, 105, 132, 256);
		box96.addBox(0F, 0F, 0F, 2, 3, 3);
		box96.setPosition(-86F, 10F, 7F);
		box97 = new CustomModelRenderer(this, 110, 51, 132, 256);
		box97.addBox(0F, 0F, 0F, 2, 3, 3);
		box97.setPosition(-91F, 29F, 3F);
		box98 = new CustomModelRenderer(this, 121, 51, 132, 256);
		box98.addBox(0F, 0F, 0F, 2, 4, 2);
		box98.setPosition(-34F, 29F, -1F);
		box99 = new CustomModelRenderer(this, 121, 51, 132, 256);
		box99.addBox(0F, 0F, 0F, 2, 4, 2);
		box99.setPosition(-31F, 29F, -1F);

		bodyModel=new ModelRendererTurbo[]{box,
				box0,box1,box2,box3,box4,box5,box6,box7,box8,box9,
				box10,box11,box12,box13,box14,box15,box16,box17,box18,box19,
				box20,box21,box22,box23,box24,box25,box26,box27,box28,box29,
				box30,box31,box32,box33,box34,box35,box36,box37,box38,box39,
				box40,box41,box42,box43,box44,box45,box46,box47,box48,box49,
				box50,box51,box52,box53,box54,box55,box56,box57,box58,box59,
				box60,box61,box62,box63,box64,box65,box66,box67,box68,box69,
				box70,box71,box72,box73,box74,box75,box76,box77,box78,box79,
				box80,box81,box82,box83,box84,box85,box86,box87,box88,box89,
				box90,box91,box92,box93,box94,box95,box96,box97,box98,box99
		};

		fixRotation(bodyModel);
	}
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		
		if (ClientProxy.isHoliday()) {
			GL11.glPushMatrix();
			GL11.glTranslatef(-5.70f, 1.35f, 0);
			GL11.glRotatef(180, 0, 1, 0);
			GL11.glScalef(0.7f, 0.7f, 0.7f);
			ring.render(5);
			GL11.glPopMatrix();
		}
	}

}