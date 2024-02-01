//This File was created with the Minecraft-SMP Modelling Toolbox 2.3.0.0
// Copyright (C) 2024 Minecraft-SMP.de
// This file is for Flan's Flying Mod Version 4.0.x+

// Model: DRWGStockCar
// Model Creator: 
// Created on: -
// Last changed on: -

package train.client.render.models; //Path where the model is located

import fexcraft.tmt.slim.ModelConverter;
import fexcraft.tmt.slim.ModelRendererTurbo;

public class ModelDRWGStockCar extends ModelConverter //Same as Filename
{
	int textureX = 256;
	int textureY = 256;

	public ModelDRWGStockCar() //Same as Filename
	{
		bodyModel = new ModelRendererTurbo[34];

		initbodyModel_1();

		translateAll(0F, 0F, 0F);


		flipAll();
	}

	private void initbodyModel_1()
	{
		bodyModel[0] = new ModelRendererTurbo(this, 1, 1, textureX, textureY); // Import Importwheel
		bodyModel[1] = new ModelRendererTurbo(this, 17, 1, textureX, textureY); // Import Importwheel
		bodyModel[2] = new ModelRendererTurbo(this, 33, 1, textureX, textureY); // Import ImportBox10
		bodyModel[3] = new ModelRendererTurbo(this, 57, 1, textureX, textureY); // Import Importwheel
		bodyModel[4] = new ModelRendererTurbo(this, 73, 1, textureX, textureY); // Import Importwheel
		bodyModel[5] = new ModelRendererTurbo(this, 89, 1, textureX, textureY); // Import ImportBox17
		bodyModel[6] = new ModelRendererTurbo(this, 113, 1, textureX, textureY); // Import Importwheel
		bodyModel[7] = new ModelRendererTurbo(this, 129, 1, textureX, textureY); // Import Importwheel
		bodyModel[8] = new ModelRendererTurbo(this, 145, 1, textureX, textureY); // Import ImportBox21
		bodyModel[9] = new ModelRendererTurbo(this, 169, 1, textureX, textureY); // Import Importwheel
		bodyModel[10] = new ModelRendererTurbo(this, 185, 1, textureX, textureY); // Import Importwheel
		bodyModel[11] = new ModelRendererTurbo(this, 201, 1, textureX, textureY); // Import ImportBox24
		bodyModel[12] = new ModelRendererTurbo(this, 1, 9, textureX, textureY); // Import ImportWallRight
		bodyModel[13] = new ModelRendererTurbo(this, 209, 1, textureX, textureY); // Import ImportBox34
		bodyModel[14] = new ModelRendererTurbo(this, 105, 9, textureX, textureY); // Import ImportBox36
		bodyModel[15] = new ModelRendererTurbo(this, 113, 9, textureX, textureY); // Import ImportWalkway
		bodyModel[16] = new ModelRendererTurbo(this, 113, 17, textureX, textureY); // Import ImportBox38
		bodyModel[17] = new ModelRendererTurbo(this, 121, 17, textureX, textureY); // Import ImportBox39
		bodyModel[18] = new ModelRendererTurbo(this, 161, 17, textureX, textureY); // Import ImportBox40
		bodyModel[19] = new ModelRendererTurbo(this, 201, 25, textureX, textureY); // Import ImportBox45
		bodyModel[20] = new ModelRendererTurbo(this, 1, 41, textureX, textureY); // Import ImportWallLeft
		bodyModel[21] = new ModelRendererTurbo(this, 121, 41, textureX, textureY); // Import ImportBox47
		bodyModel[22] = new ModelRendererTurbo(this, 1, 73, textureX, textureY); // Import ImportBox51
		bodyModel[23] = new ModelRendererTurbo(this, 185, 49, textureX, textureY); // Import ImportEnd1
		bodyModel[24] = new ModelRendererTurbo(this, 1, 81, textureX, textureY); // Import ImportBox75
		bodyModel[25] = new ModelRendererTurbo(this, 1, 89, textureX, textureY); // Import ImportBox76
		bodyModel[26] = new ModelRendererTurbo(this, 57, 89, textureX, textureY); // Import ImportBox77
		bodyModel[27] = new ModelRendererTurbo(this, 113, 89, textureX, textureY); // Import ImportBox78
		bodyModel[28] = new ModelRendererTurbo(this, 209, 73, textureX, textureY); // Import ImportBox79
		bodyModel[29] = new ModelRendererTurbo(this, 225, 49, textureX, textureY); // Import ImportBox80
		bodyModel[30] = new ModelRendererTurbo(this, 177, 97, textureX, textureY); // Import ImportBox81
		bodyModel[31] = new ModelRendererTurbo(this, 1, 105, textureX, textureY); // Import ImportBox82
		bodyModel[32] = new ModelRendererTurbo(this, 41, 113, textureX, textureY); // Import ImportBox34
		bodyModel[33] = new ModelRendererTurbo(this, 41, 129, textureX, textureY); // Import ImportBox35

		bodyModel[0].addBox(0F, 0F, 0F, 6, 6, 1, 0F); // Import Importwheel
		bodyModel[0].setRotationPoint(1F, -6F, -6F);

		bodyModel[1].addBox(0F, 0F, 0F, 6, 6, 1, 0F); // Import Importwheel
		bodyModel[1].setRotationPoint(-6F, -6F, -6F);

		bodyModel[2].addBox(0F, 0F, 0F, 9, 2, 1, 0F); // Import ImportBox10
		bodyModel[2].setRotationPoint(-4F, -4F, -7F);

		bodyModel[3].addBox(0F, 0F, 0F, 6, 6, 1, 0F); // Import Importwheel
		bodyModel[3].setRotationPoint(-6F, -6F, 6F);

		bodyModel[4].addBox(0F, 0F, 0F, 6, 6, 1, 0F); // Import Importwheel
		bodyModel[4].setRotationPoint(1F, -6F, 6F);

		bodyModel[5].addBox(0F, 0F, 0F, 9, 2, 1, 0F); // Import ImportBox17
		bodyModel[5].setRotationPoint(-4F, -4F, 7F);

		bodyModel[6].addBox(0F, 0F, 0F, 6, 6, 1, 0F); // Import Importwheel
		bodyModel[6].setRotationPoint(32F, -6F, -6F);

		bodyModel[7].addBox(0F, 0F, 0F, 6, 6, 1, 0F); // Import Importwheel
		bodyModel[7].setRotationPoint(25F, -6F, -6F);

		bodyModel[8].addBox(0F, 0F, 0F, 9, 2, 1, 0F); // Import ImportBox21
		bodyModel[8].setRotationPoint(27F, -4F, -7F);

		bodyModel[9].addBox(0F, 0F, 0F, 6, 6, 1, 0F); // Import Importwheel
		bodyModel[9].setRotationPoint(25F, -6F, 6F);

		bodyModel[10].addBox(0F, 0F, 0F, 6, 6, 1, 0F); // Import Importwheel
		bodyModel[10].setRotationPoint(32F, -6F, 6F);

		bodyModel[11].addBox(0F, 0F, 0F, 9, 2, 1, 0F); // Import ImportBox24
		bodyModel[11].setRotationPoint(27F, -4F, 7F);

		bodyModel[12].addBox(0F, 0F, 0F, 49, 23, 1, 0F); // Import ImportWallRight
		bodyModel[12].setRotationPoint(-8.5F, -30F, 9F);

		bodyModel[13].addBox(0F, 0F, 0F, 2, 2, 20, 0F); // Import ImportBox34
		bodyModel[13].setRotationPoint(-6.5F, -7F, -9.5F);
		bodyModel[13].rotateAngleZ = -1.57079633F;

		bodyModel[14].addBox(0F, 0F, 0F, 2, 46, 1, 0F); // Import ImportBox36
		bodyModel[14].setRotationPoint(39F, -7F, 9F);
		bodyModel[14].rotateAngleZ = -1.57079633F;

		bodyModel[15].addBox(0F, 0F, 0F, 51, 1, 4, 0F); // Import ImportWalkway
		bodyModel[15].setRotationPoint(-9.5F, -31.6F, -1.5F);

		bodyModel[16].addBox(0F, 0F, 0F, 2, 46, 1, 0F); // Import ImportBox38
		bodyModel[16].setRotationPoint(39F, -7F, -9F);
		bodyModel[16].rotateAngleZ = -1.57079633F;

		bodyModel[17].addBox(0F, 0F, 0F, 1, 1, 17, 0F); // Import ImportBox39
		bodyModel[17].setRotationPoint(39F, -6F, -8F);
		bodyModel[17].rotateAngleZ = -1.57079633F;

		bodyModel[18].addBox(0F, 0F, 0F, 1, 23, 17, 0F); // Import ImportBox40
		bodyModel[18].setRotationPoint(39F, -30F, -8F);

		bodyModel[19].addBox(0F, 0F, 0F, 2, 2, 20, 0F); // Import ImportBox45
		bodyModel[19].setRotationPoint(40.5F, -7F, -9.5F);
		bodyModel[19].rotateAngleZ = -1.57079633F;

		bodyModel[20].addBox(0F, 0F, 0F, 49, 23, 1, 0F); // Import ImportWallLeft
		bodyModel[20].setRotationPoint(-8.5F, -30F, -9F);

		bodyModel[21].addBox(0F, 0F, 0F, 1, 23, 17, 0F); // Import ImportBox47
		bodyModel[21].setRotationPoint(-8F, -30F, -8F);

		bodyModel[22].addBox(0F, 0F, 0F, 50, 1, 1, 0F); // Import ImportBox51
		bodyModel[22].setRotationPoint(-9F, -31F, 0F);

		bodyModel[23].addBox(0F, 0F, 0F, 1, 23, 17, 0F); // Import ImportEnd1
		bodyModel[23].setRotationPoint(-8.5F, -7F, 9F);
		bodyModel[23].rotateAngleX = -3.14159265F;

		bodyModel[24].addBox(0F, 0F, 0F, 54, 2, 3, 0F); // Import ImportBox75
		bodyModel[24].setRotationPoint(-11.5F, -7F, -1F);

		bodyModel[25].addBox(0F, 0F, 0F, 15, 4, 11, 0F); // Import ImportBox76
		bodyModel[25].setRotationPoint(-7F, -5F, -5F);

		bodyModel[26].addBox(0F, 0F, 0F, 15, 4, 11, 0F); // Import ImportBox77
		bodyModel[26].setRotationPoint(24F, -5F, -5F);

		bodyModel[27].addBox(0F, 0F, 0F, 16, 3, 13, 0F); // Import ImportBox78
		bodyModel[27].setRotationPoint(8F, -5F, -6F);

		bodyModel[28].addBox(0F, 0F, 0F, 1, 23, 17, 0F); // Import ImportBox79
		bodyModel[28].setRotationPoint(39.5F, -7F, 9F);
		bodyModel[28].rotateAngleX = 3.14159265F;

		bodyModel[29].addBox(0F, 0F, 0F, 14, 19, 1, 0F); // Import ImportBox80
		bodyModel[29].setRotationPoint(9.5F, -27F, 10F);

		bodyModel[30].addBox(0F, 0F, 0F, 14, 19, 1, 0F); // Import ImportBox81
		bodyModel[30].setRotationPoint(9.5F, -27F, -10F);

		bodyModel[31].addBox(0F, 0F, 0F, 1, 45, 17, 0F); // Import ImportBox82
		bodyModel[31].setRotationPoint(38.5F, -8F, -8F);
		bodyModel[31].rotateAngleZ = -1.57079633F;

		bodyModel[32].addShapeBox(0F, 0F, -9F, 50, 1, 9, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Import ImportBox34
		bodyModel[32].setRotationPoint(-9F, -31F, 10F);

		bodyModel[33].addShapeBox(0F, 0F, -9F, 50, 1, 9, 0F,0F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Import ImportBox35
		bodyModel[33].setRotationPoint(-9F, -31F, 0F);
	}
}