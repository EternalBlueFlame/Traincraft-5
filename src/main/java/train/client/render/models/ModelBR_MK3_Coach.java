//FMT-Marker DFM-1.0

package train.client.render.models;

import tmt.ModelConverter;
import tmt.ModelRendererTurbo;

/** This file was exported via the (Default) FlansMod Exporter of<br>
 *  FMT (Fex's Modelling Toolbox) v.2.7.1 &copy; 2022 - Fexcraft.net<br>
 *  All rights reserved. For this Model's License contact the Author/Creator.
 */
public class ModelBR_MK3_Coach extends ModelConverter {

	 int textureX = 512;
	 int textureY = 512;

	public ModelBR_MK3_Coach(){
		bodyModel = new ModelRendererTurbo[271];
		//
		bodyModel[0] = new ModelRendererTurbo(this, 1, 1, textureX, textureY); // 01
		bodyModel[0].addShapeBox(0, 0, 0, 92, 1, 20, 0, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0.5f, 0, -0.5f, 0.4f, 0, -0.5f, 0.4f, 0, -0.5f, 0.4f, 0, -0.5f, 0.4f);
		bodyModel[0].setRotationPoint(-48.0f, 0.0f, -10.0f);

		bodyModel[1] = new ModelRendererTurbo(this, 209, 1, textureX, textureY); // 02
		bodyModel[1].addShapeBox(0, 0, 0, 92, 4, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, -0.9f, 0, 0, -0.9f, 0, -0.5f, -0.5f, 0, -0.5f, -0.5f, 0, -0.5f, -0.4f, 0, -0.5f, -0.4f);
		bodyModel[1].setRotationPoint(-48.0f, -3.0f, -11.0f);

		bodyModel[2] = new ModelRendererTurbo(this, 209, 9, textureX, textureY); // 03
		bodyModel[2].addShapeBox(0, 0, 0, 92, 4, 1, 0, 0, 0, -0.9f, 0, 0, -0.9f, 0, 0, 0, 0, 0, 0, 0, -0.5f, -0.4f, 0, -0.5f, -0.4f, 0, -0.5f, -0.5f, 0, -0.5f, -0.5f);
		bodyModel[2].setRotationPoint(-48.0f, -3.0f, 10.0f);

		bodyModel[3] = new ModelRendererTurbo(this, 233, 17, textureX, textureY); // 04
		bodyModel[3].addShapeBox(0, 0, 0, 92, 14, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, -0.9f, 0, 0, -0.9f, 0, 0, 0, 0, 0, 0, 0, 0, -0.9f, 0, 0, -0.9f);
		bodyModel[3].setRotationPoint(-48.0f, -17.0f, -11.0f);

		bodyModel[4] = new ModelRendererTurbo(this, 1, 25, textureX, textureY); // 05
		bodyModel[4].addShapeBox(0, 0, 0, 92, 14, 1, 0, 0, 0, -0.9f, 0, 0, -0.9f, 0, 0, 0, 0, 0, 0, 0, 0, -0.9f, 0, 0, -0.9f, 0, 0, 0, 0, 0, 0);
		bodyModel[4].setRotationPoint(-48.0f, -17.0f, 10.0f);

		bodyModel[5] = new ModelRendererTurbo(this, 193, 33, textureX, textureY); // 06
		bodyModel[5].addShapeBox(0, 0, 0, 92, 1, 1, 0, 0, 0, -1, 0, 0, -1, 0, -0.1f, 0, 0, -0.1f, 0, 0, 0, 0, 0, 0, 0, 0, 0, -0.9f, 0, 0, -0.9f);
		bodyModel[5].setRotationPoint(-48.0f, -18.0f, -11.0f);

		bodyModel[6] = new ModelRendererTurbo(this, 1, 41, textureX, textureY); // 07
		bodyModel[6].addShapeBox(0, 0, 0, 92, 1, 1, 0, 0, -0.1f, 0, 0, -0.1f, 0, 0, 0, -1, 0, 0, -1, 0, 0, -0.9f, 0, 0, -0.9f, 0, 0, 0, 0, 0, 0);
		bodyModel[6].setRotationPoint(-48.0f, -18.0f, 10.0f);

		bodyModel[7] = new ModelRendererTurbo(this, 185, 41, textureX, textureY); // 08
		bodyModel[7].addShapeBox(0, 0, 0, 92, 2, 3, 0, 0, 0, -3, 0, 0, -3, 0, -0.1f, 0, 0, -0.1f, 0, 0, 0, 0, 0, 0, 0, 0, 0.1f, -3, 0, 0.1f, -3);
		bodyModel[7].setRotationPoint(-48.0f, -20.0f, -10.0f);

		bodyModel[8] = new ModelRendererTurbo(this, 1, 49, textureX, textureY); // 09
		bodyModel[8].addShapeBox(0, 0, 0, 92, 2, 3, 0, 0, -0.1f, 0, 0, -0.1f, 0, 0, 0, -3, 0, 0, -3, 0, 0.1f, -3, 0, 0.1f, -3, 0, 0, 0, 0, 0, 0);
		bodyModel[8].setRotationPoint(-48.0f, -20.0f, 7.0f);

		bodyModel[9] = new ModelRendererTurbo(this, 185, 49, textureX, textureY); // 10
		bodyModel[9].addShapeBox(0, 0, 0, 92, 2, 7, 0, 0, 0, -6, 0, 0, -6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.1f, -7, 0, 0.1f, -7);
		bodyModel[9].setRotationPoint(-48.0f, -22.0f, -7.0f);

		bodyModel[10] = new ModelRendererTurbo(this, 1, 65, textureX, textureY); // 11
		bodyModel[10].addShapeBox(0, 0, 0, 92, 2, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, -6, 0, 0, -6, 0, 0.1f, -7, 0, 0.1f, -7, 0, 0, 0, 0, 0, 0);
		bodyModel[10].setRotationPoint(-48.0f, -22.0f, 0.0f);

		bodyModel[11] = new ModelRendererTurbo(this, 425, 1, textureX, textureY); // 12
		bodyModel[11].addShapeBox(0, 0, 0, 1, 4, 22, 0, 0, 0, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, 0, 0, -0.5f, -0.5f, 0, -0.5f, -1, 0, -0.5f, -1, 0, -0.5f, -0.5f);
		bodyModel[11].setRotationPoint(44.0f, -3.0f, -11.0f);

		bodyModel[12] = new ModelRendererTurbo(this, 457, 9, textureX, textureY); // 13
		bodyModel[12].addShapeBox(0, 0, 0, 1, 14, 22, 0, 0, 0, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, 0, 0, 0, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, 0);
		bodyModel[12].setRotationPoint(44.0f, -17.0f, -11.0f);

		bodyModel[13] = new ModelRendererTurbo(this, 1, 1, textureX, textureY); // 14
		bodyModel[13].addShapeBox(0, 0, 0, 1, 3, 4, 0, 0, -2, -1, 0, -3, -0.5f, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -0.5f, 0, 0, 0, 0, 0, 0);
		bodyModel[13].setRotationPoint(44.0f, -20.0f, -11.0f);

		bodyModel[14] = new ModelRendererTurbo(this, 401, 1, textureX, textureY); // 15
		bodyModel[14].addShapeBox(0, 0, 0, 1, 3, 4, 0, 0, 0, 0, -1, 0, 0, 0, -3, -0.5f, 0, -2, -1, 0, 0, 0, 0, 0, 0, 0, 0, -0.5f, 0, 0, 0);
		bodyModel[14].setRotationPoint(44.0f, -20.0f, 7.0f);

		bodyModel[15] = new ModelRendererTurbo(this, 417, 1, textureX, textureY); // 16
		bodyModel[15].addShapeBox(0, 0, 0, 1, 5, 6, 0, 0, -2, 0, -1, -2, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		bodyModel[15].setRotationPoint(44.0f, -22.0f, -7.0f);

		bodyModel[16] = new ModelRendererTurbo(this, 457, 1, textureX, textureY); // 17
		bodyModel[16].addShapeBox(0, 0, 0, 1, 5, 6, 0, 0, 0, 0, -1, 0, 0, -1, -2, 0, 0, -2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		bodyModel[16].setRotationPoint(44.0f, -22.0f, 1.0f);

		bodyModel[17] = new ModelRendererTurbo(this, 433, 1, textureX, textureY); // 18
		bodyModel[17].addShapeBox(0, 0, 0, 1, 5, 2, 0, 0, 0, 0, -1, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		bodyModel[17].setRotationPoint(44.0f, -22.0f, -1.0f);

		bodyModel[18] = new ModelRendererTurbo(this, 377, 33, textureX, textureY); // 19
		bodyModel[18].addShapeBox(0, 0, 0, 2, 2, 20, 0, -0.5f, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0, -0.5f, -0.5f, 0, -0.5f, 0, -1, 0, 0, -1, 0, 0, -1, -0.5f, 0, -1);
		bodyModel[18].setRotationPoint(43.0f, 0.0f, -10.0f);

		bodyModel[19] = new ModelRendererTurbo(this, 441, 1, textureX, textureY); // 20
		bodyModel[19].addShapeBox(0, 0, 0, 1, 18, 1, 0, 0.4f, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, 0.4f, 0, 0, 0, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, 0, 0, 0);
		bodyModel[19].setRotationPoint(45.0f, -18.0f, -5.0f);

		bodyModel[20] = new ModelRendererTurbo(this, 473, 1, textureX, textureY); // 21
		bodyModel[20].addShapeBox(0, 0, 0, 1, 18, 1, 0, 0.4f, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, 0.4f, 0, 0, 0, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, 0, 0, 0);
		bodyModel[20].setRotationPoint(45.0f, -18.0f, 4.0f);

		bodyModel[21] = new ModelRendererTurbo(this, 489, 1, textureX, textureY); // 22
		bodyModel[21].addShapeBox(0, 0, 0, 1, 2, 8, 0, 0.6f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0.6f, 0, 0, 0, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0, 0, 0);
		bodyModel[21].setRotationPoint(45.0f, -19.0f, -4.0f);

		bodyModel[22] = new ModelRendererTurbo(this, 505, 17, textureX, textureY); // 23
		bodyModel[22].addShapeBox(0, 0, 0, 1, 18, 1, 0, 0, 0, -0.5f, 0.5f, 0, -0.5f, 0.5f, 0, 0, 0, 0, 0, 0, 0, -0.5f, 0.5f, 0, -0.5f, 0.5f, 0, 0, 0, 0, 0);
		bodyModel[22].setRotationPoint(45.0f, -18.0f, -5.0f);

		bodyModel[23] = new ModelRendererTurbo(this, 385, 33, textureX, textureY); // 24
		bodyModel[23].addShapeBox(0, 0, 0, 1, 18, 1, 0, 0, 0, 0, 0.5f, 0, 0, 0.5f, 0, -0.5f, 0, 0, -0.5f, 0, 0, 0, 0.5f, 0, 0, 0.5f, 0, -0.5f, 0, 0, -0.5f);
		bodyModel[23].setRotationPoint(45.0f, -18.0f, 4.0f);

		bodyModel[24] = new ModelRendererTurbo(this, 1, 9, textureX, textureY); // 25
		bodyModel[24].addShapeBox(0, 0, 0, 1, 1, 8, 0, 0, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0, 0, 0, 0, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0, 0, 0);
		bodyModel[24].setRotationPoint(45.0f, 0.0f, -4.0f);

		bodyModel[25] = new ModelRendererTurbo(this, 9, 1, textureX, textureY); // 26
		bodyModel[25].addShapeBox(0, 0, 0, 1, 1, 1, 0, 0, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, 0, 0, 0, 0, 0, -1, -0.75f, 0, -1, -0.75f, 0, 0, 0, 0, 0);
		bodyModel[25].setRotationPoint(45.0f, 0.0f, -5.0f);

		bodyModel[26] = new ModelRendererTurbo(this, 409, 1, textureX, textureY); // 27
		bodyModel[26].addShapeBox(0, 0, 0, 1, 1, 1, 0, 0, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, 0, 0, 0, 0, 0, 0, -0.75f, 0, 0, -0.75f, 0, -1, 0, 0, -1);
		bodyModel[26].setRotationPoint(45.0f, 0.0f, 4.0f);

		bodyModel[27] = new ModelRendererTurbo(this, 417, 1, textureX, textureY); // 28
		bodyModel[27].addShapeBox(0, 0, 0, 1, 1, 1, 0, 0, 0, -0.5f, 0.5f, 0, -0.5f, 0.5f, 0, 0, 0, 0, 0, 0, 0, -1, 0.5f, 0, -1, 0.5f, 0, 0, 0, 0, 0);
		bodyModel[27].setRotationPoint(45.0f, 0.0f, -5.0f);

		bodyModel[28] = new ModelRendererTurbo(this, 457, 1, textureX, textureY); // 29
		bodyModel[28].addShapeBox(0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0.5f, 0, 0, 0.5f, 0, -0.5f, 0, 0, -0.5f, 0, 0, 0, 0.5f, 0, 0, 0.5f, 0, -1, 0, 0, -1);
		bodyModel[28].setRotationPoint(45.0f, 0.0f, 4.0f);

		bodyModel[29] = new ModelRendererTurbo(this, 481, 1, textureX, textureY); // 30
		bodyModel[29].addShapeBox(0, 0, 0, 1, 1, 1, 0, 0.5f, 0, -1, -0.75f, 0, -1, -0.75f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, 0.5f, 0, 0);
		bodyModel[29].setRotationPoint(45.0f, -19.0f, -5.0f);

		bodyModel[30] = new ModelRendererTurbo(this, 489, 1, textureX, textureY); // 31
		bodyModel[30].addShapeBox(0, 0, 0, 1, 1, 1, 0, 0.5f, 0, 0, -0.75f, 0, 0, -0.75f, 0, -1, 0.5f, 0, -1, 0.5f, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, 0.5f, 0, 0);
		bodyModel[30].setRotationPoint(45.0f, -19.0f, 4.0f);

		bodyModel[31] = new ModelRendererTurbo(this, 505, 1, textureX, textureY); // 32
		bodyModel[31].addShapeBox(0, 0, 0, 1, 1, 1, 0, 0, 0, -1, 0.5f, 0, -1, 0.5f, 0, 0, 0, 0, 0, 0, 0, -0.5f, 0.5f, 0, -0.5f, 0.5f, 0, 0, 0, 0, 0);
		bodyModel[31].setRotationPoint(45.0f, -19.0f, -5.0f);

		bodyModel[32] = new ModelRendererTurbo(this, 1, 9, textureX, textureY); // 33
		bodyModel[32].addShapeBox(0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0.5f, 0, 0, 0.5f, 0, -1, 0, 0, -1, 0, 0, 0, 0.5f, 0, 0, 0.5f, 0, -0.5f, 0, 0, -0.5f);
		bodyModel[32].setRotationPoint(45.0f, -19.0f, 4.0f);

		bodyModel[33] = new ModelRendererTurbo(this, 401, 9, textureX, textureY); // 34
		bodyModel[33].addShapeBox(0, 0, 0, 3, 1, 1, 0, 0, -0.8f, 0.5f, 0, -0.8f, 0.5f, 0, -0.8f, -0.5f, 0, -0.8f, -0.5f, 0, 0.5f, 0.5f, 0, 0.5f, 0.5f, 0, 0.5f, -0.5f, 0, 0.5f, -0.5f);
		bodyModel[33].setRotationPoint(44.0f, 1.0f, 0.0f);

		bodyModel[34] = new ModelRendererTurbo(this, 1, 1, textureX, textureY); // 35
		bodyModel[34].addShapeBox(0, 0, 0, 0, 1, 1, 0, 0.1f, -0.4f, 0.7f, 0, -0.4f, 0.7f, 0, -0.4f, -0.3f, 0.1f, -0.4f, -0.3f, 0.1f, 0.8f, 0.7f, 0, 0.8f, 0.7f, 0, 0.8f, -0.3f, 0.1f, 0.8f, -0.3f);
		bodyModel[34].setRotationPoint(47.0f, 1.0f, 0.0f);

		bodyModel[35] = new ModelRendererTurbo(this, 433, 9, textureX, textureY); // 36
		bodyModel[35].addShapeBox(0, 0, 0, 1, 1, 1, 0, 0, -0.5f, 0.25f, -0.5f, -0.5f, 0.25f, -0.5f, -0.5f, -0.75f, 0, -0.5f, -0.75f, 0, 0, 0.25f, -0.5f, 0, 0.25f, -0.5f, 0, -0.75f, 0, 0, -0.75f);
		bodyModel[35].setRotationPoint(47.0f, 1.0f, 0.0f);

		bodyModel[36] = new ModelRendererTurbo(this, 209, 17, textureX, textureY); // 37
		bodyModel[36].addShapeBox(0, 0, 0, 1, 1, 1, 0, 0, -0.2f, 0.25f, -0.5f, -0.2f, 0.25f, -0.5f, -0.2f, -0.75f, 0, -0.2f, -0.75f, 0, -0.3f, 0.25f, -0.5f, -0.3f, 0.25f, -0.5f, -0.3f, -0.75f, 0, -0.3f, -0.75f);
		bodyModel[36].setRotationPoint(47.0f, 2.0f, 0.0f);

		bodyModel[37] = new ModelRendererTurbo(this, 425, 33, textureX, textureY); // 38
		bodyModel[37].addShapeBox(0, 0, 0, 1, 4, 22, 0, 0, 0, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, 0, 0, -0.5f, -0.5f, 0, -0.5f, -1, 0, -0.5f, -1, 0, -0.5f, -0.5f);
		bodyModel[37].setRotationPoint(-48.0f, -3.0f, 11.0f);
		bodyModel[37].rotateAngleY = 3.1415927f;

		bodyModel[38] = new ModelRendererTurbo(this, 457, 49, textureX, textureY); // 39
		bodyModel[38].addShapeBox(0, 0, 0, 1, 14, 22, 0, 0, 0, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, 0, 0, 0, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, 0);
		bodyModel[38].setRotationPoint(-48.0f, -17.0f, 11.0f);
		bodyModel[38].rotateAngleY = 3.1415927f;

		bodyModel[39] = new ModelRendererTurbo(this, 409, 9, textureX, textureY); // 40
		bodyModel[39].addShapeBox(0, 0, 0, 1, 3, 4, 0, 0, -2, -1, 0, -3, -0.5f, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -0.5f, 0, 0, 0, 0, 0, 0);
		bodyModel[39].setRotationPoint(-48.0f, -20.0f, 11.0f);
		bodyModel[39].rotateAngleY = 3.1415927f;

		bodyModel[40] = new ModelRendererTurbo(this, 489, 17, textureX, textureY); // 41
		bodyModel[40].addShapeBox(0, 0, 0, 1, 3, 4, 0, 0, 0, 0, -1, 0, 0, 0, -3, -0.5f, 0, -2, -1, 0, 0, 0, 0, 0, 0, 0, 0, -0.5f, 0, 0, 0);
		bodyModel[40].setRotationPoint(-48.0f, -20.0f, -7.0f);
		bodyModel[40].rotateAngleY = 3.1415927f;

		bodyModel[41] = new ModelRendererTurbo(this, 409, 33, textureX, textureY); // 42
		bodyModel[41].addShapeBox(0, 0, 0, 1, 5, 6, 0, 0, -2, 0, -1, -2, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		bodyModel[41].setRotationPoint(-48.0f, -22.0f, 7.0f);
		bodyModel[41].rotateAngleY = 3.1415927f;

		bodyModel[42] = new ModelRendererTurbo(this, 425, 33, textureX, textureY); // 43
		bodyModel[42].addShapeBox(0, 0, 0, 1, 5, 6, 0, 0, 0, 0, -1, 0, 0, -1, -2, 0, 0, -2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		bodyModel[42].setRotationPoint(-48.0f, -22.0f, -1.0f);
		bodyModel[42].rotateAngleY = 3.1415927f;

		bodyModel[43] = new ModelRendererTurbo(this, 193, 25, textureX, textureY); // 44
		bodyModel[43].addShapeBox(0, 0, 0, 1, 5, 2, 0, 0, 0, 0, -1, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		bodyModel[43].setRotationPoint(-48.0f, -22.0f, 1.0f);
		bodyModel[43].rotateAngleY = 3.1415927f;

		bodyModel[44] = new ModelRendererTurbo(this, 369, 57, textureX, textureY); // 45
		bodyModel[44].addShapeBox(0, 0, 0, 2, 2, 20, 0, -0.5f, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0, -0.5f, -0.5f, 0, -0.5f, 0, -1, 0, 0, -1, 0, 0, -1, -0.5f, 0, -1);
		bodyModel[44].setRotationPoint(-47.0f, 0.0f, 10.0f);
		bodyModel[44].rotateAngleY = 3.1415927f;

		bodyModel[45] = new ModelRendererTurbo(this, 441, 33, textureX, textureY); // 46
		bodyModel[45].addShapeBox(0, 0, 0, 1, 18, 1, 0, 0.4f, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, 0.4f, 0, 0, 0, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, 0, 0, 0);
		bodyModel[45].setRotationPoint(-49.0f, -18.0f, 5.0f);
		bodyModel[45].rotateAngleY = 3.1415927f;

		bodyModel[46] = new ModelRendererTurbo(this, 505, 41, textureX, textureY); // 47
		bodyModel[46].addShapeBox(0, 0, 0, 1, 18, 1, 0, 0.4f, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, 0.4f, 0, 0, 0, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, 0, 0, 0);
		bodyModel[46].setRotationPoint(-49.0f, -18.0f, -4.0f);
		bodyModel[46].rotateAngleY = 3.1415927f;

		bodyModel[47] = new ModelRendererTurbo(this, 401, 57, textureX, textureY); // 48
		bodyModel[47].addShapeBox(0, 0, 0, 1, 2, 8, 0, 0.6f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0.6f, 0, 0, 0, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0, 0, 0);
		bodyModel[47].setRotationPoint(-49.0f, -19.0f, 4.0f);
		bodyModel[47].rotateAngleY = 3.1415927f;

		bodyModel[48] = new ModelRendererTurbo(this, 473, 49, textureX, textureY); // 49
		bodyModel[48].addShapeBox(0, 0, 0, 1, 18, 1, 0, 0, 0, -0.5f, 0.5f, 0, -0.5f, 0.5f, 0, 0, 0, 0, 0, 0, 0, -0.5f, 0.5f, 0, -0.5f, 0.5f, 0, 0, 0, 0, 0);
		bodyModel[48].setRotationPoint(-49.0f, -18.0f, 5.0f);
		bodyModel[48].rotateAngleY = 3.1415927f;

		bodyModel[49] = new ModelRendererTurbo(this, 489, 49, textureX, textureY); // 50
		bodyModel[49].addShapeBox(0, 0, 0, 1, 18, 1, 0, 0, 0, 0, 0.5f, 0, 0, 0.5f, 0, -0.5f, 0, 0, -0.5f, 0, 0, 0, 0.5f, 0, 0, 0.5f, 0, -0.5f, 0, 0, -0.5f);
		bodyModel[49].setRotationPoint(-49.0f, -18.0f, -4.0f);
		bodyModel[49].rotateAngleY = 3.1415927f;

		bodyModel[50] = new ModelRendererTurbo(this, 201, 65, textureX, textureY); // 51
		bodyModel[50].addShapeBox(0, 0, 0, 1, 1, 8, 0, 0, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0, 0, 0, 0, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0, 0, 0);
		bodyModel[50].setRotationPoint(-49.0f, 0.0f, 4.0f);
		bodyModel[50].rotateAngleY = 3.1415927f;

		bodyModel[51] = new ModelRendererTurbo(this, 217, 17, textureX, textureY); // 52
		bodyModel[51].addShapeBox(0, 0, 0, 1, 1, 1, 0, 0, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, 0, 0, 0, 0, 0, -1, -0.75f, 0, -1, -0.75f, 0, 0, 0, 0, 0);
		bodyModel[51].setRotationPoint(-49.0f, 0.0f, 5.0f);
		bodyModel[51].rotateAngleY = 3.1415927f;

		bodyModel[52] = new ModelRendererTurbo(this, 225, 17, textureX, textureY); // 53
		bodyModel[52].addShapeBox(0, 0, 0, 1, 1, 1, 0, 0, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, 0, 0, 0, 0, 0, 0, -0.75f, 0, 0, -0.75f, 0, -1, 0, 0, -1);
		bodyModel[52].setRotationPoint(-49.0f, 0.0f, -4.0f);
		bodyModel[52].rotateAngleY = 3.1415927f;

		bodyModel[53] = new ModelRendererTurbo(this, 425, 17, textureX, textureY); // 54
		bodyModel[53].addShapeBox(0, 0, 0, 1, 1, 1, 0, 0, 0, -0.5f, 0.5f, 0, -0.5f, 0.5f, 0, 0, 0, 0, 0, 0, 0, -1, 0.5f, 0, -1, 0.5f, 0, 0, 0, 0, 0);
		bodyModel[53].setRotationPoint(-49.0f, 0.0f, 5.0f);
		bodyModel[53].rotateAngleY = 3.1415927f;

		bodyModel[54] = new ModelRendererTurbo(this, 433, 17, textureX, textureY); // 55
		bodyModel[54].addShapeBox(0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0.5f, 0, 0, 0.5f, 0, -0.5f, 0, 0, -0.5f, 0, 0, 0, 0.5f, 0, 0, 0.5f, 0, -1, 0, 0, -1);
		bodyModel[54].setRotationPoint(-49.0f, 0.0f, -4.0f);
		bodyModel[54].rotateAngleY = 3.1415927f;

		bodyModel[55] = new ModelRendererTurbo(this, 457, 17, textureX, textureY); // 56
		bodyModel[55].addShapeBox(0, 0, 0, 1, 1, 1, 0, 0.5f, 0, -1, -0.75f, 0, -1, -0.75f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, 0.5f, 0, 0);
		bodyModel[55].setRotationPoint(-49.0f, -19.0f, 5.0f);
		bodyModel[55].rotateAngleY = 3.1415927f;

		bodyModel[56] = new ModelRendererTurbo(this, 465, 17, textureX, textureY); // 57
		bodyModel[56].addShapeBox(0, 0, 0, 1, 1, 1, 0, 0.5f, 0, 0, -0.75f, 0, 0, -0.75f, 0, -1, 0.5f, 0, -1, 0.5f, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, 0.5f, 0, 0);
		bodyModel[56].setRotationPoint(-49.0f, -19.0f, -4.0f);
		bodyModel[56].rotateAngleY = 3.1415927f;

		bodyModel[57] = new ModelRendererTurbo(this, 497, 17, textureX, textureY); // 58
		bodyModel[57].addShapeBox(0, 0, 0, 1, 1, 1, 0, 0, 0, -1, 0.5f, 0, -1, 0.5f, 0, 0, 0, 0, 0, 0, 0, -0.5f, 0.5f, 0, -0.5f, 0.5f, 0, 0, 0, 0, 0);
		bodyModel[57].setRotationPoint(-49.0f, -19.0f, 5.0f);
		bodyModel[57].rotateAngleY = 3.1415927f;

		bodyModel[58] = new ModelRendererTurbo(this, 201, 25, textureX, textureY); // 59
		bodyModel[58].addShapeBox(0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0.5f, 0, 0, 0.5f, 0, -1, 0, 0, -1, 0, 0, 0, 0.5f, 0, 0, 0.5f, 0, -0.5f, 0, 0, -0.5f);
		bodyModel[58].setRotationPoint(-49.0f, -19.0f, -4.0f);
		bodyModel[58].rotateAngleY = 3.1415927f;

		bodyModel[59] = new ModelRendererTurbo(this, 209, 25, textureX, textureY); // 60
		bodyModel[59].addShapeBox(0, 0, 0, 3, 1, 1, 0, 0, -0.8f, 0.5f, 0, -0.8f, 0.5f, 0, -0.8f, -0.5f, 0, -0.8f, -0.5f, 0, 0.5f, 0.5f, 0, 0.5f, 0.5f, 0, 0.5f, -0.5f, 0, 0.5f, -0.5f);
		bodyModel[59].setRotationPoint(-48.0f, 1.0f, 0.0f);
		bodyModel[59].rotateAngleY = 3.1415927f;

		bodyModel[60] = new ModelRendererTurbo(this, 17, 1, textureX, textureY); // 61
		bodyModel[60].addShapeBox(0, 0, 0, 0, 1, 1, 0, 0.1f, -0.4f, 0.7f, 0, -0.4f, 0.7f, 0, -0.4f, -0.3f, 0.1f, -0.4f, -0.3f, 0.1f, 0.8f, 0.7f, 0, 0.8f, 0.7f, 0, 0.8f, -0.3f, 0.1f, 0.8f, -0.3f);
		bodyModel[60].setRotationPoint(-51.0f, 1.0f, 0.0f);
		bodyModel[60].rotateAngleY = 3.1415927f;

		bodyModel[61] = new ModelRendererTurbo(this, 225, 25, textureX, textureY); // 62
		bodyModel[61].addShapeBox(0, 0, 0, 1, 1, 1, 0, 0, -0.5f, 0.25f, -0.5f, -0.5f, 0.25f, -0.5f, -0.5f, -0.75f, 0, -0.5f, -0.75f, 0, 0, 0.25f, -0.5f, 0, 0.25f, -0.5f, 0, -0.75f, 0, 0, -0.75f);
		bodyModel[61].setRotationPoint(-51.0f, 1.0f, 0.0f);
		bodyModel[61].rotateAngleY = 3.1415927f;

		bodyModel[62] = new ModelRendererTurbo(this, 473, 25, textureX, textureY); // 63
		bodyModel[62].addShapeBox(0, 0, 0, 1, 1, 1, 0, 0, -0.2f, 0.25f, -0.5f, -0.2f, 0.25f, -0.5f, -0.2f, -0.75f, 0, -0.2f, -0.75f, 0, -0.3f, 0.25f, -0.5f, -0.3f, 0.25f, -0.5f, -0.3f, -0.75f, 0, -0.3f, -0.75f);
		bodyModel[62].setRotationPoint(-51.0f, 2.0f, 0.0f);
		bodyModel[62].rotateAngleY = 3.1415927f;

		bodyModel[63] = new ModelRendererTurbo(this, 489, 25, textureX, textureY); // 64
		bodyModel[63].addBox(0, 0, 0, 5, 5, 0, 0f);
		bodyModel[63].setRotationPoint(-41.0f, 2.0f, -6.0f);

		bodyModel[64] = new ModelRendererTurbo(this, 425, 49, textureX, textureY); // 65
		bodyModel[64].addBox(0, 0, 0, 5, 5, 0, 0f);
		bodyModel[64].setRotationPoint(-41.0f, 2.0f, 6.0f);

		bodyModel[65] = new ModelRendererTurbo(this, 457, 49, textureX, textureY); // 66
		bodyModel[65].addBox(0, 0, 0, 5, 5, 0, 0f);
		bodyModel[65].setRotationPoint(-27.0f, 2.0f, -6.0f);

		bodyModel[66] = new ModelRendererTurbo(this, 1, 57, textureX, textureY); // 67
		bodyModel[66].addBox(0, 0, 0, 5, 5, 0, 0f);
		bodyModel[66].setRotationPoint(-27.0f, 2.0f, 6.0f);

		bodyModel[67] = new ModelRendererTurbo(this, 409, 49, textureX, textureY); // 68
		bodyModel[67].addShapeBox(0, 0, 0, 5, 1, 1, 0, 1, 0, 0, 0, 0.2f, 0, 0, 0.2f, 0, 1, 0, 0, 1, 0.2f, 0, 0, 0.2f, 0, 0, 0.2f, 0, 1, 0.2f, 0);
		bodyModel[67].setRotationPoint(-41.0f, 2.0f, -8.0f);

		bodyModel[68] = new ModelRendererTurbo(this, 17, 57, textureX, textureY); // 69
		bodyModel[68].addShapeBox(0, 0, 0, 5, 1, 1, 0, 1, 0, 0, 0, 0.2f, 0, 0, 0.2f, 0, 1, 0, 0, 1, 0.2f, 0, 0, 0.2f, 0, 0, 0.2f, 0, 1, 0.2f, 0);
		bodyModel[68].setRotationPoint(-41.0f, 2.0f, 7.0f);

		bodyModel[69] = new ModelRendererTurbo(this, 33, 57, textureX, textureY); // 70
		bodyModel[69].addShapeBox(0, 0, 0, 5, 1, 1, 0, 0, 0.2f, 0, 1, 0, 0, 1, 0, 0, 0, 0.2f, 0, 0, 0.2f, 0, 1, 0.2f, 0, 1, 0.2f, 0, 0, 0.2f, 0);
		bodyModel[69].setRotationPoint(-27.0f, 2.0f, -8.0f);

		bodyModel[70] = new ModelRendererTurbo(this, 49, 57, textureX, textureY); // 71
		bodyModel[70].addShapeBox(0, 0, 0, 5, 1, 1, 0, 0, 0.2f, 0, 1, 0, 0, 1, 0, 0, 0, 0.2f, 0, 0, 0.2f, 0, 1, 0.2f, 0, 1, 0.2f, 0, 0, 0.2f, 0);
		bodyModel[70].setRotationPoint(-27.0f, 2.0f, 7.0f);

		bodyModel[71] = new ModelRendererTurbo(this, 65, 57, textureX, textureY); // 72
		bodyModel[71].addShapeBox(0, 0, 0, 9, 1, 1, 0, 0, 0.2f, 0, 0, 0.2f, 0, 0, 0.2f, 0, 0, 0.2f, 0, 0, 0.2f, 0, 0, 0.2f, 0, 0, 0.2f, 0, 0, 0.2f, 0);
		bodyModel[71].setRotationPoint(-36.0f, 2.0f, -8.0f);

		bodyModel[72] = new ModelRendererTurbo(this, 89, 57, textureX, textureY); // 73
		bodyModel[72].addShapeBox(0, 0, 0, 9, 1, 1, 0, 0, 0.2f, 0, 0, 0.2f, 0, 0, 0.2f, 0, 0, 0.2f, 0, 0, 0.2f, 0, 0, 0.2f, 0, 0, 0.2f, 0, 0, 0.2f, 0);
		bodyModel[72].setRotationPoint(-36.0f, 2.0f, 7.0f);

		bodyModel[73] = new ModelRendererTurbo(this, 209, 65, textureX, textureY); // 74
		bodyModel[73].addShapeBox(0, 0, 0, 1, 1, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		bodyModel[73].setRotationPoint(-39.0f, 4.0f, -8.0f);

		bodyModel[74] = new ModelRendererTurbo(this, 249, 65, textureX, textureY); // 75
		bodyModel[74].addShapeBox(0, 0, 0, 1, 1, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		bodyModel[74].setRotationPoint(-25.0f, 4.0f, -8.0f);

		bodyModel[75] = new ModelRendererTurbo(this, 409, 33, textureX, textureY); // 76
		bodyModel[75].addShapeBox(0, 0, 0, 1, 2, 1, 0, 0, 0, -0.2f, -0.5f, 0, -0.2f, -0.5f, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, -0.5f, 0, -0.2f, -0.5f, 0, -0.2f, 0, 0, -0.2f);
		bodyModel[75].setRotationPoint(-42.0f, 3.0f, -8.0f);

		bodyModel[76] = new ModelRendererTurbo(this, 425, 33, textureX, textureY); // 77
		bodyModel[76].addShapeBox(0, 0, 0, 1, 2, 1, 0, 0, 0, -0.2f, -0.5f, 0, -0.2f, -0.5f, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, -0.5f, 0, -0.2f, -0.5f, 0, -0.2f, 0, 0, -0.2f);
		bodyModel[76].setRotationPoint(-42.0f, 3.0f, 7.0f);

		bodyModel[77] = new ModelRendererTurbo(this, 377, 41, textureX, textureY); // 78
		bodyModel[77].addShapeBox(0, 0, 0, 1, 2, 1, 0, -0.5f, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, -0.5f, 0, -0.2f, -0.5f, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, -0.5f, 0, -0.2f);
		bodyModel[77].setRotationPoint(-22.0f, 3.0f, -8.0f);

		bodyModel[78] = new ModelRendererTurbo(this, 377, 49, textureX, textureY); // 79
		bodyModel[78].addShapeBox(0, 0, 0, 1, 2, 1, 0, -0.5f, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, -0.5f, 0, -0.2f, -0.5f, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, -0.5f, 0, -0.2f);
		bodyModel[78].setRotationPoint(-22.0f, 3.0f, 7.0f);

		bodyModel[79] = new ModelRendererTurbo(this, 113, 57, textureX, textureY); // 80
		bodyModel[79].addShapeBox(0, 0, 0, 4, 1, 1, 0, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, -0.5f, -0.5f, -0.2f, 0, -0.5f, -0.2f, 0, -0.5f, -0.2f, -0.5f, -0.5f, -0.2f);
		bodyModel[79].setRotationPoint(-42.0f, 5.0f, -8.0f);

		bodyModel[80] = new ModelRendererTurbo(this, 129, 57, textureX, textureY); // 81
		bodyModel[80].addShapeBox(0, 0, 0, 4, 1, 1, 0, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, -0.5f, -0.5f, -0.2f, 0, -0.5f, -0.2f, 0, -0.5f, -0.2f, -0.5f, -0.5f, -0.2f);
		bodyModel[80].setRotationPoint(-42.0f, 5.0f, 7.0f);

		bodyModel[81] = new ModelRendererTurbo(this, 145, 57, textureX, textureY); // 82
		bodyModel[81].addShapeBox(0, 0, 0, 4, 1, 1, 0, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, -0.5f, -0.2f, -0.5f, -0.5f, -0.2f, -0.5f, -0.5f, -0.2f, 0, -0.5f, -0.2f);
		bodyModel[81].setRotationPoint(-25.0f, 5.0f, -8.0f);

		bodyModel[82] = new ModelRendererTurbo(this, 161, 57, textureX, textureY); // 83
		bodyModel[82].addShapeBox(0, 0, 0, 4, 1, 1, 0, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, -0.5f, -0.2f, -0.5f, -0.5f, -0.2f, -0.5f, -0.5f, -0.2f, 0, -0.5f, -0.2f);
		bodyModel[82].setRotationPoint(-25.0f, 5.0f, 7.0f);

		bodyModel[83] = new ModelRendererTurbo(this, 497, 49, textureX, textureY); // 84
		bodyModel[83].addShapeBox(0, 0, 0, 1, 2, 1, 0, -0.5f, -0.2f, 0, 0.5f, -0.2f, 0, 0.5f, -0.2f, 0, -0.5f, -0.2f, 0, -0.5f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, -0.5f, 0, 0);
		bodyModel[83].setRotationPoint(-41.0f, 3.0f, -8.0f);

		bodyModel[84] = new ModelRendererTurbo(this, 177, 57, textureX, textureY); // 85
		bodyModel[84].addShapeBox(0, 0, 0, 1, 2, 1, 0, -0.5f, -0.2f, 0, 0.5f, -0.2f, 0, 0.5f, -0.2f, 0, -0.5f, -0.2f, 0, -0.5f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, -0.5f, 0, 0);
		bodyModel[84].setRotationPoint(-24.0f, 3.0f, -8.0f);

		bodyModel[85] = new ModelRendererTurbo(this, 193, 65, textureX, textureY); // 86
		bodyModel[85].addShapeBox(0, 0, 0, 3, 1, 1, 0, 0, 0, -0.2f, 0, -0.2f, -0.2f, 0, -0.2f, -0.2f, 0, 0, -0.2f, 0, 0.5f, -0.2f, 0, -0.5f, -0.2f, 0, -0.5f, -0.2f, 0, 0.5f, -0.2f);
		bodyModel[85].setRotationPoint(-38.0f, 4.0f, -8.0f);

		bodyModel[86] = new ModelRendererTurbo(this, 233, 65, textureX, textureY); // 87
		bodyModel[86].addShapeBox(0, 0, 0, 3, 1, 1, 0, 0, -0.2f, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, -0.2f, -0.2f, 0, -0.5f, -0.2f, 0, 0.5f, -0.2f, 0, 0.5f, -0.2f, 0, -0.5f, -0.2f);
		bodyModel[86].setRotationPoint(-28.0f, 4.0f, -8.0f);

		bodyModel[87] = new ModelRendererTurbo(this, 249, 65, textureX, textureY); // 88
		bodyModel[87].addShapeBox(0, 0, 0, 3, 1, 1, 0, 0, 0, -0.2f, 0, -0.2f, -0.2f, 0, -0.2f, -0.2f, 0, 0, -0.2f, 0, 0.5f, -0.2f, 0, -0.5f, -0.2f, 0, -0.5f, -0.2f, 0, 0.5f, -0.2f);
		bodyModel[87].setRotationPoint(-38.0f, 4.0f, 7.0f);

		bodyModel[88] = new ModelRendererTurbo(this, 273, 65, textureX, textureY); // 89
		bodyModel[88].addShapeBox(0, 0, 0, 3, 1, 1, 0, 0, -0.2f, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, -0.2f, -0.2f, 0, -0.5f, -0.2f, 0, 0.5f, -0.2f, 0, 0.5f, -0.2f, 0, -0.5f, -0.2f);
		bodyModel[88].setRotationPoint(-28.0f, 4.0f, 7.0f);

		bodyModel[89] = new ModelRendererTurbo(this, 273, 65, textureX, textureY); // 90
		bodyModel[89].addShapeBox(0, 0, 0, 1, 1, 14, 0, 0, -0.5f, 0.2f, -0.5f, -0.5f, 0.2f, -0.5f, -0.5f, 0.2f, 0, -0.5f, 0.2f, 0, 0, 0.2f, -0.5f, 0, 0.2f, -0.5f, 0, 0.2f, 0, 0, 0.2f);
		bodyModel[89].setRotationPoint(-42.0f, 4.0f, -7.0f);

		bodyModel[90] = new ModelRendererTurbo(this, 305, 65, textureX, textureY); // 91
		bodyModel[90].addShapeBox(0, 0, 0, 1, 1, 14, 0, -0.5f, -0.5f, 0.2f, 0, -0.5f, 0.2f, 0, -0.5f, 0.2f, -0.5f, -0.5f, 0.2f, -0.5f, 0, 0.2f, 0, 0, 0.2f, 0, 0, 0.2f, -0.5f, 0, 0.2f);
		bodyModel[90].setRotationPoint(-22.0f, 4.0f, -7.0f);

		bodyModel[91] = new ModelRendererTurbo(this, 329, 65, textureX, textureY); // 92
		bodyModel[91].addBox(0, 0, 0, 15, 1, 2, 0f);
		bodyModel[91].setRotationPoint(-39.0f, 3.0f, -1.0f);

		bodyModel[92] = new ModelRendererTurbo(this, 401, 57, textureX, textureY); // 93
		bodyModel[92].addShapeBox(0, 0, 0, 1, 3, 2, 0, 0.5f, -0.1f, 0, 0.5f, -0.1f, 0, 0.5f, -0.1f, 0, 0.5f, -0.1f, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0);
		bodyModel[92].setRotationPoint(-32.0f, 0.0f, -1.0f);

		bodyModel[93] = new ModelRendererTurbo(this, 417, 57, textureX, textureY); // 94
		bodyModel[93].addShapeBox(0, 0, 0, 2, 2, 1, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, -0.5f, -0.4f, 0, -0.5f, -0.4f, 0, -0.5f, -0.4f, 0, -0.5f, -0.4f, 0);
		bodyModel[93].setRotationPoint(-36.0f, 3.0f, -8.0f);

		bodyModel[94] = new ModelRendererTurbo(this, 497, 57, textureX, textureY); // 95
		bodyModel[94].addShapeBox(0, 0, 0, 2, 2, 1, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, -0.5f, -0.4f, 0, -0.5f, -0.4f, 0, -0.5f, -0.4f, 0, -0.5f, -0.4f, 0);
		bodyModel[94].setRotationPoint(-29.0f, 3.0f, -8.0f);

		bodyModel[95] = new ModelRendererTurbo(this, 1, 65, textureX, textureY); // 96
		bodyModel[95].addShapeBox(0, 0, 0, 2, 2, 1, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, -0.5f, -0.4f, 0, -0.5f, -0.4f, 0, -0.5f, -0.4f, 0, -0.5f, -0.4f, 0);
		bodyModel[95].setRotationPoint(-36.0f, 3.0f, 7.0f);

		bodyModel[96] = new ModelRendererTurbo(this, 217, 65, textureX, textureY); // 97
		bodyModel[96].addShapeBox(0, 0, 0, 2, 2, 1, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, -0.5f, -0.4f, 0, -0.5f, -0.4f, 0, -0.5f, -0.4f, 0, -0.5f, -0.4f, 0);
		bodyModel[96].setRotationPoint(-29.0f, 3.0f, 7.0f);

		bodyModel[97] = new ModelRendererTurbo(this, 297, 65, textureX, textureY); // 98
		bodyModel[97].addShapeBox(0, 0, 0, 2, 1, 2, 0, 0.5f, 0.5f, -0.7f, -0.5f, 0.5f, 0, -0.5f, 0.5f, 0, 0.5f, 0.5f, -0.7f, 0.5f, -0.5f, -0.7f, -0.5f, -0.5f, 0, -0.5f, -0.5f, 0, 0.5f, -0.5f, -0.7f);
		bodyModel[97].setRotationPoint(-33.0f, 4.0f, -8.0f);

		bodyModel[98] = new ModelRendererTurbo(this, 369, 65, textureX, textureY); // 99
		bodyModel[98].addShapeBox(0, 0, 0, 2, 1, 2, 0, -0.5f, 0.5f, 0, 0.5f, 0.5f, -0.7f, 0.5f, 0.5f, -0.7f, -0.5f, 0.5f, 0, -0.5f, -0.5f, 0, 0.5f, -0.5f, -0.7f, 0.5f, -0.5f, -0.7f, -0.5f, -0.5f, 0);
		bodyModel[98].setRotationPoint(-32.0f, 4.0f, -8.0f);

		bodyModel[99] = new ModelRendererTurbo(this, 425, 65, textureX, textureY); // 100
		bodyModel[99].addShapeBox(0, 0, 0, 2, 1, 2, 0, 0.5f, 0.5f, -0.7f, -0.5f, 0.5f, 0, -0.5f, 0.5f, 0, 0.5f, 0.5f, -0.7f, 0.5f, -0.5f, -0.7f, -0.5f, -0.5f, 0, -0.5f, -0.5f, 0, 0.5f, -0.5f, -0.7f);
		bodyModel[99].setRotationPoint(-33.0f, 4.0f, 6.0f);

		bodyModel[100] = new ModelRendererTurbo(this, 441, 65, textureX, textureY); // 101
		bodyModel[100].addShapeBox(0, 0, 0, 2, 1, 2, 0, -0.5f, 0.5f, 0, 0.5f, 0.5f, -0.7f, 0.5f, 0.5f, -0.7f, -0.5f, 0.5f, 0, -0.5f, -0.5f, 0, 0.5f, -0.5f, -0.7f, 0.5f, -0.5f, -0.7f, -0.5f, -0.5f, 0);
		bodyModel[100].setRotationPoint(-32.0f, 4.0f, 6.0f);

		bodyModel[101] = new ModelRendererTurbo(this, 313, 65, textureX, textureY); // 102
		bodyModel[101].addShapeBox(0, 0, 0, 1, 1, 1, 0, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, 0, 0, 0, 0, 0, -0.5f, -0.2f, 0, -0.5f, -0.2f, 0, -0.5f, 0, 0, -0.5f, 0);
		bodyModel[101].setRotationPoint(-32.0f, 3.0f, -8.0f);

		bodyModel[102] = new ModelRendererTurbo(this, 457, 65, textureX, textureY); // 103
		bodyModel[102].addShapeBox(0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, -0.2f, 0, 0, -0.2f, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, -0.2f, 0, -0.5f, -0.2f);
		bodyModel[102].setRotationPoint(-32.0f, 3.0f, 7.0f);

		bodyModel[103] = new ModelRendererTurbo(this, 497, 65, textureX, textureY); // 104
		bodyModel[103].addShapeBox(0, 0, 0, 5, 1, 1, 0, 0, 0.2f, 0.5f, 0, 0.2f, 0.5f, 0, 0.2f, 0, 0, 0.2f, 0, 0, -0.5f, 0.5f, 0, -0.5f, 0.5f, 0, -0.5f, 0, 0, -0.5f, 0);
		bodyModel[103].setRotationPoint(-34.0f, 5.0f, -7.0f);

		bodyModel[104] = new ModelRendererTurbo(this, 233, 73, textureX, textureY); // 105
		bodyModel[104].addShapeBox(0, 0, 0, 5, 1, 1, 0, 0, 0.2f, 0, 0, 0.2f, 0, 0, 0.2f, 0.5f, 0, 0.2f, 0.5f, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0.5f, 0, -0.5f, 0.5f);
		bodyModel[104].setRotationPoint(-34.0f, 5.0f, 6.0f);

		bodyModel[105] = new ModelRendererTurbo(this, 465, 65, textureX, textureY); // 106
		bodyModel[105].addShapeBox(0, 0, 0, 1, 2, 1, 0, -0.5f, -0.2f, 0, 0.5f, -0.2f, 0, 0.5f, -0.2f, 0, -0.5f, -0.2f, 0, -0.5f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, -0.5f, 0, 0);
		bodyModel[105].setRotationPoint(-41.0f, 3.0f, 7.0f);

		bodyModel[106] = new ModelRendererTurbo(this, 249, 73, textureX, textureY); // 107
		bodyModel[106].addShapeBox(0, 0, 0, 1, 2, 1, 0, -0.5f, -0.2f, 0, 0.5f, -0.2f, 0, 0.5f, -0.2f, 0, -0.5f, -0.2f, 0, -0.5f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, -0.5f, 0, 0);
		bodyModel[106].setRotationPoint(-24.0f, 3.0f, 7.0f);

		bodyModel[107] = new ModelRendererTurbo(this, 329, 73, textureX, textureY); // 108
		bodyModel[107].addBox(0, 0, 0, 1, 1, 14, 0f);
		bodyModel[107].setRotationPoint(-32.0f, 2.0f, -7.0f);

		bodyModel[108] = new ModelRendererTurbo(this, 257, 73, textureX, textureY); // 109
		bodyModel[108].addShapeBox(0, 0, 0, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0.5f, -0.5f, 0, 0.5f, -0.5f);
		bodyModel[108].setRotationPoint(-32.0f, 3.0f, -6.0f);

		bodyModel[109] = new ModelRendererTurbo(this, 273, 73, textureX, textureY); // 110
		bodyModel[109].addShapeBox(0, 0, 0, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.5f, -0.5f, 0, 0.5f, -0.5f, 0, 0.5f, 0, 0, 0.5f, 0);
		bodyModel[109].setRotationPoint(-32.0f, 3.0f, 5.0f);

		bodyModel[110] = new ModelRendererTurbo(this, 281, 73, textureX, textureY); // 111
		bodyModel[110].addShapeBox(0, 0, 0, 1, 1, 1, 0, -0.5f, 0, -0.5f, 0, 0, -0.5f, 0, 0, 0, -0.5f, 0, 0, -0.5f, -0.5f, -0.5f, 0, -0.5f, -0.5f, 0, -0.5f, 0, -0.5f, -0.5f, 0);
		bodyModel[110].setRotationPoint(-28.0f, 3.0f, -9.0f);

		bodyModel[111] = new ModelRendererTurbo(this, 297, 73, textureX, textureY); // 112
		bodyModel[111].addShapeBox(0, 0, 0, 3, 2, 1, 0, 0, -1.3f, -1, 0.9f, -0.8f, -0.3f, 0.9f, -0.8f, -0.5f, 0, -1.3f, 0.2f, 0, -0.5f, -1, 0.9f, -1, -0.3f, 0.9f, -1, -0.5f, 0, -0.5f, 0.2f);
		bodyModel[111].setRotationPoint(-31.0f, 3.0f, -9.0f);

		bodyModel[112] = new ModelRendererTurbo(this, 313, 73, textureX, textureY); // 113
		bodyModel[112].addShapeBox(0, 0, 0, 1, 1, 1, 0, -0.6f, 0, -0.8f, -0.1f, 0, -0.8f, -0.1f, 0, 0, -0.6f, 0, 0, -0.6f, 0, -0.3f, -0.1f, 0, -0.3f, -0.1f, 0, -0.5f, -0.6f, 0, -0.5f);
		bodyModel[112].setRotationPoint(-28.0f, 3.0f, -9.0f);

		bodyModel[113] = new ModelRendererTurbo(this, 329, 73, textureX, textureY); // 114
		bodyModel[113].addShapeBox(0, 0, 0, 1, 1, 1, 0, 0, 0, -0.5f, -0.5f, 0, -0.5f, -0.5f, 0, 0, 0, 0, 0, 0, -0.5f, -0.5f, -0.5f, -0.5f, -0.5f, -0.5f, -0.5f, 0, 0, -0.5f, 0);
		bodyModel[113].setRotationPoint(-27.0f, 3.0f, 9.0f);
		bodyModel[113].rotateAngleY = 3.1415927f;

		bodyModel[114] = new ModelRendererTurbo(this, 353, 73, textureX, textureY); // 115
		bodyModel[114].addShapeBox(0, 0, 0, 3, 2, 1, 0, 0, -0.8f, -0.3f, 0.9f, -1.3f, -1, 0.9f, -1.3f, 0.2f, 0, -0.8f, -0.5f, 0, -1, -0.3f, 0.9f, -0.5f, -1, 0.9f, -0.5f, 0.2f, 0, -1, -0.5f);
		bodyModel[114].setRotationPoint(-27.0f, 3.0f, 9.0f);
		bodyModel[114].rotateAngleY = 3.1415927f;

		bodyModel[115] = new ModelRendererTurbo(this, 337, 73, textureX, textureY); // 116
		bodyModel[115].addShapeBox(0, 0, 0, 1, 1, 1, 0, -0.1f, 0, -0.8f, -0.6f, 0, -0.8f, -0.6f, 0, 0, -0.1f, 0, 0, -0.1f, 0, -0.3f, -0.6f, 0, -0.3f, -0.6f, 0, -0.5f, -0.1f, 0, -0.5f);
		bodyModel[115].setRotationPoint(-27.0f, 3.0f, 9.0f);
		bodyModel[115].rotateAngleY = 3.1415927f;

		bodyModel[116] = new ModelRendererTurbo(this, 417, 73, textureX, textureY); // 117
		bodyModel[116].addBox(0, 0, 0, 5, 5, 0, 0f);
		bodyModel[116].setRotationPoint(18.0f, 2.0f, -6.0f);

		bodyModel[117] = new ModelRendererTurbo(this, 433, 73, textureX, textureY); // 118
		bodyModel[117].addBox(0, 0, 0, 5, 5, 0, 0f);
		bodyModel[117].setRotationPoint(18.0f, 2.0f, 6.0f);

		bodyModel[118] = new ModelRendererTurbo(this, 1, 81, textureX, textureY); // 119
		bodyModel[118].addBox(0, 0, 0, 5, 5, 0, 0f);
		bodyModel[118].setRotationPoint(32.0f, 2.0f, -6.0f);

		bodyModel[119] = new ModelRendererTurbo(this, 17, 81, textureX, textureY); // 120
		bodyModel[119].addBox(0, 0, 0, 5, 5, 0, 0f);
		bodyModel[119].setRotationPoint(32.0f, 2.0f, 6.0f);

		bodyModel[120] = new ModelRendererTurbo(this, 369, 73, textureX, textureY); // 121
		bodyModel[120].addShapeBox(0, 0, 0, 5, 1, 1, 0, 1, 0, 0, 0, 0.2f, 0, 0, 0.2f, 0, 1, 0, 0, 1, 0.2f, 0, 0, 0.2f, 0, 0, 0.2f, 0, 1, 0.2f, 0);
		bodyModel[120].setRotationPoint(18.0f, 2.0f, -8.0f);

		bodyModel[121] = new ModelRendererTurbo(this, 401, 73, textureX, textureY); // 122
		bodyModel[121].addShapeBox(0, 0, 0, 5, 1, 1, 0, 1, 0, 0, 0, 0.2f, 0, 0, 0.2f, 0, 1, 0, 0, 1, 0.2f, 0, 0, 0.2f, 0, 0, 0.2f, 0, 1, 0.2f, 0);
		bodyModel[121].setRotationPoint(18.0f, 2.0f, 7.0f);

		bodyModel[122] = new ModelRendererTurbo(this, 33, 81, textureX, textureY); // 123
		bodyModel[122].addShapeBox(0, 0, 0, 5, 1, 1, 0, 0, 0.2f, 0, 1, 0, 0, 1, 0, 0, 0, 0.2f, 0, 0, 0.2f, 0, 1, 0.2f, 0, 1, 0.2f, 0, 0, 0.2f, 0);
		bodyModel[122].setRotationPoint(32.0f, 2.0f, -8.0f);

		bodyModel[123] = new ModelRendererTurbo(this, 49, 81, textureX, textureY); // 124
		bodyModel[123].addShapeBox(0, 0, 0, 5, 1, 1, 0, 0, 0.2f, 0, 1, 0, 0, 1, 0, 0, 0, 0.2f, 0, 0, 0.2f, 0, 1, 0.2f, 0, 1, 0.2f, 0, 0, 0.2f, 0);
		bodyModel[123].setRotationPoint(32.0f, 2.0f, 7.0f);

		bodyModel[124] = new ModelRendererTurbo(this, 65, 81, textureX, textureY); // 125
		bodyModel[124].addShapeBox(0, 0, 0, 9, 1, 1, 0, 0, 0.2f, 0, 0, 0.2f, 0, 0, 0.2f, 0, 0, 0.2f, 0, 0, 0.2f, 0, 0, 0.2f, 0, 0, 0.2f, 0, 0, 0.2f, 0);
		bodyModel[124].setRotationPoint(23.0f, 2.0f, -8.0f);

		bodyModel[125] = new ModelRendererTurbo(this, 89, 81, textureX, textureY); // 126
		bodyModel[125].addShapeBox(0, 0, 0, 9, 1, 1, 0, 0, 0.2f, 0, 0, 0.2f, 0, 0, 0.2f, 0, 0, 0.2f, 0, 0, 0.2f, 0, 0, 0.2f, 0, 0, 0.2f, 0, 0, 0.2f, 0);
		bodyModel[125].setRotationPoint(23.0f, 2.0f, 7.0f);

		bodyModel[126] = new ModelRendererTurbo(this, 433, 73, textureX, textureY); // 127
		bodyModel[126].addShapeBox(0, 0, 0, 1, 1, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		bodyModel[126].setRotationPoint(20.0f, 4.0f, -8.0f);

		bodyModel[127] = new ModelRendererTurbo(this, 97, 81, textureX, textureY); // 128
		bodyModel[127].addShapeBox(0, 0, 0, 1, 1, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		bodyModel[127].setRotationPoint(34.0f, 4.0f, -8.0f);

		bodyModel[128] = new ModelRendererTurbo(this, 505, 73, textureX, textureY); // 129
		bodyModel[128].addShapeBox(0, 0, 0, 1, 2, 1, 0, 0, 0, -0.2f, -0.5f, 0, -0.2f, -0.5f, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, -0.5f, 0, -0.2f, -0.5f, 0, -0.2f, 0, 0, -0.2f);
		bodyModel[128].setRotationPoint(17.0f, 3.0f, -8.0f);

		bodyModel[129] = new ModelRendererTurbo(this, 121, 81, textureX, textureY); // 130
		bodyModel[129].addShapeBox(0, 0, 0, 1, 2, 1, 0, 0, 0, -0.2f, -0.5f, 0, -0.2f, -0.5f, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, -0.5f, 0, -0.2f, -0.5f, 0, -0.2f, 0, 0, -0.2f);
		bodyModel[129].setRotationPoint(17.0f, 3.0f, 7.0f);

		bodyModel[130] = new ModelRendererTurbo(this, 129, 81, textureX, textureY); // 131
		bodyModel[130].addShapeBox(0, 0, 0, 1, 2, 1, 0, -0.5f, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, -0.5f, 0, -0.2f, -0.5f, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, -0.5f, 0, -0.2f);
		bodyModel[130].setRotationPoint(37.0f, 3.0f, -8.0f);

		bodyModel[131] = new ModelRendererTurbo(this, 137, 81, textureX, textureY); // 132
		bodyModel[131].addShapeBox(0, 0, 0, 1, 2, 1, 0, -0.5f, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, -0.5f, 0, -0.2f, -0.5f, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, -0.5f, 0, -0.2f);
		bodyModel[131].setRotationPoint(37.0f, 3.0f, 7.0f);

		bodyModel[132] = new ModelRendererTurbo(this, 145, 81, textureX, textureY); // 133
		bodyModel[132].addShapeBox(0, 0, 0, 4, 1, 1, 0, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, -0.5f, -0.5f, -0.2f, 0, -0.5f, -0.2f, 0, -0.5f, -0.2f, -0.5f, -0.5f, -0.2f);
		bodyModel[132].setRotationPoint(17.0f, 5.0f, -8.0f);

		bodyModel[133] = new ModelRendererTurbo(this, 161, 81, textureX, textureY); // 134
		bodyModel[133].addShapeBox(0, 0, 0, 4, 1, 1, 0, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, -0.5f, -0.5f, -0.2f, 0, -0.5f, -0.2f, 0, -0.5f, -0.2f, -0.5f, -0.5f, -0.2f);
		bodyModel[133].setRotationPoint(17.0f, 5.0f, 7.0f);

		bodyModel[134] = new ModelRendererTurbo(this, 177, 81, textureX, textureY); // 135
		bodyModel[134].addShapeBox(0, 0, 0, 4, 1, 1, 0, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, -0.5f, -0.2f, -0.5f, -0.5f, -0.2f, -0.5f, -0.5f, -0.2f, 0, -0.5f, -0.2f);
		bodyModel[134].setRotationPoint(34.0f, 5.0f, -8.0f);

		bodyModel[135] = new ModelRendererTurbo(this, 193, 81, textureX, textureY); // 136
		bodyModel[135].addShapeBox(0, 0, 0, 4, 1, 1, 0, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, -0.5f, -0.2f, -0.5f, -0.5f, -0.2f, -0.5f, -0.5f, -0.2f, 0, -0.5f, -0.2f);
		bodyModel[135].setRotationPoint(34.0f, 5.0f, 7.0f);

		bodyModel[136] = new ModelRendererTurbo(this, 289, 81, textureX, textureY); // 137
		bodyModel[136].addShapeBox(0, 0, 0, 1, 2, 1, 0, -0.5f, -0.2f, 0, 0.5f, -0.2f, 0, 0.5f, -0.2f, 0, -0.5f, -0.2f, 0, -0.5f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, -0.5f, 0, 0);
		bodyModel[136].setRotationPoint(18.0f, 3.0f, -8.0f);

		bodyModel[137] = new ModelRendererTurbo(this, 297, 81, textureX, textureY); // 138
		bodyModel[137].addShapeBox(0, 0, 0, 1, 2, 1, 0, -0.5f, -0.2f, 0, 0.5f, -0.2f, 0, 0.5f, -0.2f, 0, -0.5f, -0.2f, 0, -0.5f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, -0.5f, 0, 0);
		bodyModel[137].setRotationPoint(35.0f, 3.0f, -8.0f);

		bodyModel[138] = new ModelRendererTurbo(this, 305, 81, textureX, textureY); // 139
		bodyModel[138].addShapeBox(0, 0, 0, 3, 1, 1, 0, 0, 0, -0.2f, 0, -0.2f, -0.2f, 0, -0.2f, -0.2f, 0, 0, -0.2f, 0, 0.5f, -0.2f, 0, -0.5f, -0.2f, 0, -0.5f, -0.2f, 0, 0.5f, -0.2f);
		bodyModel[138].setRotationPoint(21.0f, 4.0f, -8.0f);

		bodyModel[139] = new ModelRendererTurbo(this, 321, 81, textureX, textureY); // 140
		bodyModel[139].addShapeBox(0, 0, 0, 3, 1, 1, 0, 0, -0.2f, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, -0.2f, -0.2f, 0, -0.5f, -0.2f, 0, 0.5f, -0.2f, 0, 0.5f, -0.2f, 0, -0.5f, -0.2f);
		bodyModel[139].setRotationPoint(31.0f, 4.0f, -8.0f);

		bodyModel[140] = new ModelRendererTurbo(this, 353, 81, textureX, textureY); // 141
		bodyModel[140].addShapeBox(0, 0, 0, 3, 1, 1, 0, 0, 0, -0.2f, 0, -0.2f, -0.2f, 0, -0.2f, -0.2f, 0, 0, -0.2f, 0, 0.5f, -0.2f, 0, -0.5f, -0.2f, 0, -0.5f, -0.2f, 0, 0.5f, -0.2f);
		bodyModel[140].setRotationPoint(21.0f, 4.0f, 7.0f);

		bodyModel[141] = new ModelRendererTurbo(this, 369, 81, textureX, textureY); // 142
		bodyModel[141].addShapeBox(0, 0, 0, 3, 1, 1, 0, 0, -0.2f, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, -0.2f, -0.2f, 0, -0.5f, -0.2f, 0, 0.5f, -0.2f, 0, 0.5f, -0.2f, 0, -0.5f, -0.2f);
		bodyModel[141].setRotationPoint(31.0f, 4.0f, 7.0f);

		bodyModel[142] = new ModelRendererTurbo(this, 369, 81, textureX, textureY); // 143
		bodyModel[142].addShapeBox(0, 0, 0, 1, 1, 14, 0, 0, -0.5f, 0.2f, -0.5f, -0.5f, 0.2f, -0.5f, -0.5f, 0.2f, 0, -0.5f, 0.2f, 0, 0, 0.2f, -0.5f, 0, 0.2f, -0.5f, 0, 0.2f, 0, 0, 0.2f);
		bodyModel[142].setRotationPoint(17.0f, 4.0f, -7.0f);

		bodyModel[143] = new ModelRendererTurbo(this, 401, 81, textureX, textureY); // 144
		bodyModel[143].addShapeBox(0, 0, 0, 1, 1, 14, 0, -0.5f, -0.5f, 0.2f, 0, -0.5f, 0.2f, 0, -0.5f, 0.2f, -0.5f, -0.5f, 0.2f, -0.5f, 0, 0.2f, 0, 0, 0.2f, 0, 0, 0.2f, -0.5f, 0, 0.2f);
		bodyModel[143].setRotationPoint(37.0f, 4.0f, -7.0f);

		bodyModel[144] = new ModelRendererTurbo(this, 1, 89, textureX, textureY); // 145
		bodyModel[144].addBox(0, 0, 0, 15, 1, 2, 0f);
		bodyModel[144].setRotationPoint(20.0f, 3.0f, -1.0f);

		bodyModel[145] = new ModelRendererTurbo(this, 393, 81, textureX, textureY); // 146
		bodyModel[145].addShapeBox(0, 0, 0, 1, 3, 2, 0, 0.5f, -0.1f, 0, 0.5f, -0.1f, 0, 0.5f, -0.1f, 0, 0.5f, -0.1f, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0);
		bodyModel[145].setRotationPoint(27.0f, 0.0f, -1.0f);

		bodyModel[146] = new ModelRendererTurbo(this, 401, 81, textureX, textureY); // 147
		bodyModel[146].addShapeBox(0, 0, 0, 2, 2, 1, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, -0.5f, -0.4f, 0, -0.5f, -0.4f, 0, -0.5f, -0.4f, 0, -0.5f, -0.4f, 0);
		bodyModel[146].setRotationPoint(22.0f, 3.0f, -8.0f);

		bodyModel[147] = new ModelRendererTurbo(this, 425, 81, textureX, textureY); // 148
		bodyModel[147].addShapeBox(0, 0, 0, 2, 2, 1, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, -0.5f, -0.4f, 0, -0.5f, -0.4f, 0, -0.5f, -0.4f, 0, -0.5f, -0.4f, 0);
		bodyModel[147].setRotationPoint(30.0f, 3.0f, -8.0f);

		bodyModel[148] = new ModelRendererTurbo(this, 433, 81, textureX, textureY); // 149
		bodyModel[148].addShapeBox(0, 0, 0, 2, 2, 1, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, -0.5f, -0.4f, 0, -0.5f, -0.4f, 0, -0.5f, -0.4f, 0, -0.5f, -0.4f, 0);
		bodyModel[148].setRotationPoint(23.0f, 3.0f, 7.0f);

		bodyModel[149] = new ModelRendererTurbo(this, 441, 81, textureX, textureY); // 150
		bodyModel[149].addShapeBox(0, 0, 0, 2, 2, 1, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, -0.5f, -0.4f, 0, -0.5f, -0.4f, 0, -0.5f, -0.4f, 0, -0.5f, -0.4f, 0);
		bodyModel[149].setRotationPoint(30.0f, 3.0f, 7.0f);

		bodyModel[150] = new ModelRendererTurbo(this, 41, 89, textureX, textureY); // 151
		bodyModel[150].addShapeBox(0, 0, 0, 2, 1, 2, 0, 0.5f, 0.5f, -0.7f, -0.5f, 0.5f, 0, -0.5f, 0.5f, 0, 0.5f, 0.5f, -0.7f, 0.5f, -0.5f, -0.7f, -0.5f, -0.5f, 0, -0.5f, -0.5f, 0, 0.5f, -0.5f, -0.7f);
		bodyModel[150].setRotationPoint(26.0f, 4.0f, -8.0f);

		bodyModel[151] = new ModelRendererTurbo(this, 57, 89, textureX, textureY); // 152
		bodyModel[151].addShapeBox(0, 0, 0, 2, 1, 2, 0, -0.5f, 0.5f, 0, 0.5f, 0.5f, -0.7f, 0.5f, 0.5f, -0.7f, -0.5f, 0.5f, 0, -0.5f, -0.5f, 0, 0.5f, -0.5f, -0.7f, 0.5f, -0.5f, -0.7f, -0.5f, -0.5f, 0);
		bodyModel[151].setRotationPoint(27.0f, 4.0f, -8.0f);

		bodyModel[152] = new ModelRendererTurbo(this, 73, 89, textureX, textureY); // 153
		bodyModel[152].addShapeBox(0, 0, 0, 2, 1, 2, 0, 0.5f, 0.5f, -0.7f, -0.5f, 0.5f, 0, -0.5f, 0.5f, 0, 0.5f, 0.5f, -0.7f, 0.5f, -0.5f, -0.7f, -0.5f, -0.5f, 0, -0.5f, -0.5f, 0, 0.5f, -0.5f, -0.7f);
		bodyModel[152].setRotationPoint(26.0f, 4.0f, 6.0f);

		bodyModel[153] = new ModelRendererTurbo(this, 89, 89, textureX, textureY); // 154
		bodyModel[153].addShapeBox(0, 0, 0, 2, 1, 2, 0, -0.5f, 0.5f, 0, 0.5f, 0.5f, -0.7f, 0.5f, 0.5f, -0.7f, -0.5f, 0.5f, 0, -0.5f, -0.5f, 0, 0.5f, -0.5f, -0.7f, 0.5f, -0.5f, -0.7f, -0.5f, -0.5f, 0);
		bodyModel[153].setRotationPoint(27.0f, 4.0f, 6.0f);

		bodyModel[154] = new ModelRendererTurbo(this, 337, 81, textureX, textureY); // 155
		bodyModel[154].addShapeBox(0, 0, 0, 1, 1, 1, 0, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, 0, 0, 0, 0, 0, -0.5f, -0.2f, 0, -0.5f, -0.2f, 0, -0.5f, 0, 0, -0.5f, 0);
		bodyModel[154].setRotationPoint(27.0f, 3.0f, -8.0f);

		bodyModel[155] = new ModelRendererTurbo(this, 409, 81, textureX, textureY); // 156
		bodyModel[155].addShapeBox(0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, -0.2f, 0, 0, -0.2f, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, -0.2f, 0, -0.5f, -0.2f);
		bodyModel[155].setRotationPoint(27.0f, 3.0f, 7.0f);

		bodyModel[156] = new ModelRendererTurbo(this, 121, 89, textureX, textureY); // 157
		bodyModel[156].addShapeBox(0, 0, 0, 5, 1, 1, 0, 0, 0.2f, 0.5f, 0, 0.2f, 0.5f, 0, 0.2f, 0, 0, 0.2f, 0, 0, -0.5f, 0.5f, 0, -0.5f, 0.5f, 0, -0.5f, 0, 0, -0.5f, 0);
		bodyModel[156].setRotationPoint(25.0f, 5.0f, -7.0f);

		bodyModel[157] = new ModelRendererTurbo(this, 137, 89, textureX, textureY); // 158
		bodyModel[157].addShapeBox(0, 0, 0, 5, 1, 1, 0, 0, 0.2f, 0, 0, 0.2f, 0, 0, 0.2f, 0.5f, 0, 0.2f, 0.5f, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0.5f, 0, -0.5f, 0.5f);
		bodyModel[157].setRotationPoint(25.0f, 5.0f, 6.0f);

		bodyModel[158] = new ModelRendererTurbo(this, 505, 81, textureX, textureY); // 159
		bodyModel[158].addShapeBox(0, 0, 0, 1, 2, 1, 0, -0.5f, -0.2f, 0, 0.5f, -0.2f, 0, 0.5f, -0.2f, 0, -0.5f, -0.2f, 0, -0.5f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, -0.5f, 0, 0);
		bodyModel[158].setRotationPoint(18.0f, 3.0f, 7.0f);

		bodyModel[159] = new ModelRendererTurbo(this, 105, 89, textureX, textureY); // 160
		bodyModel[159].addShapeBox(0, 0, 0, 1, 2, 1, 0, -0.5f, -0.2f, 0, 0.5f, -0.2f, 0, 0.5f, -0.2f, 0, -0.5f, -0.2f, 0, -0.5f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, -0.5f, 0, 0);
		bodyModel[159].setRotationPoint(35.0f, 3.0f, 7.0f);

		bodyModel[160] = new ModelRendererTurbo(this, 137, 89, textureX, textureY); // 161
		bodyModel[160].addBox(0, 0, 0, 1, 1, 14, 0f);
		bodyModel[160].setRotationPoint(27.0f, 2.0f, -7.0f);

		bodyModel[161] = new ModelRendererTurbo(this, 161, 89, textureX, textureY); // 162
		bodyModel[161].addShapeBox(0, 0, 0, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0.5f, -0.5f, 0, 0.5f, -0.5f);
		bodyModel[161].setRotationPoint(27.0f, 3.0f, -6.0f);

		bodyModel[162] = new ModelRendererTurbo(this, 169, 89, textureX, textureY); // 163
		bodyModel[162].addShapeBox(0, 0, 0, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.5f, -0.5f, 0, 0.5f, -0.5f, 0, 0.5f, 0, 0, 0.5f, 0);
		bodyModel[162].setRotationPoint(27.0f, 3.0f, 5.0f);

		bodyModel[163] = new ModelRendererTurbo(this, 177, 89, textureX, textureY); // 164
		bodyModel[163].addShapeBox(0, 0, 0, 1, 1, 1, 0, -0.5f, 0, -0.5f, 0, 0, -0.5f, 0, 0, 0, -0.5f, 0, 0, -0.5f, -0.5f, -0.5f, 0, -0.5f, -0.5f, 0, -0.5f, 0, -0.5f, -0.5f, 0);
		bodyModel[163].setRotationPoint(24.0f, 3.0f, 9.0f);
		bodyModel[163].rotateAngleY = 3.1415927f;

		bodyModel[164] = new ModelRendererTurbo(this, 185, 89, textureX, textureY); // 165
		bodyModel[164].addShapeBox(0, 0, 0, 3, 2, 1, 0, 0, -1.3f, -1, 0.9f, -0.8f, -0.3f, 0.9f, -0.8f, -0.5f, 0, -1.3f, 0.2f, 0, -0.5f, -1, 0.9f, -1, -0.3f, 0.9f, -1, -0.5f, 0, -0.5f, 0.2f);
		bodyModel[164].setRotationPoint(27.0f, 3.0f, 9.0f);
		bodyModel[164].rotateAngleY = 3.1415927f;

		bodyModel[165] = new ModelRendererTurbo(this, 201, 89, textureX, textureY); // 166
		bodyModel[165].addShapeBox(0, 0, 0, 1, 1, 1, 0, -0.6f, 0, -0.8f, -0.1f, 0, -0.8f, -0.1f, 0, 0, -0.6f, 0, 0, -0.6f, 0, -0.3f, -0.1f, 0, -0.3f, -0.1f, 0, -0.5f, -0.6f, 0, -0.5f);
		bodyModel[165].setRotationPoint(24.0f, 3.0f, 9.0f);
		bodyModel[165].rotateAngleY = 3.1415927f;

		bodyModel[166] = new ModelRendererTurbo(this, 209, 89, textureX, textureY); // 167
		bodyModel[166].addShapeBox(0, 0, 0, 1, 1, 1, 0, 0, 0, -0.5f, -0.5f, 0, -0.5f, -0.5f, 0, 0, 0, 0, 0, 0, -0.5f, -0.5f, -0.5f, -0.5f, -0.5f, -0.5f, -0.5f, 0, 0, -0.5f, 0);
		bodyModel[166].setRotationPoint(23.0f, 3.0f, -9.0f);

		bodyModel[167] = new ModelRendererTurbo(this, 217, 89, textureX, textureY); // 168
		bodyModel[167].addShapeBox(0, 0, 0, 3, 2, 1, 0, 0, -0.8f, -0.3f, 0.9f, -1.3f, -1, 0.9f, -1.3f, 0.2f, 0, -0.8f, -0.5f, 0, -1, -0.3f, 0.9f, -0.5f, -1, 0.9f, -0.5f, 0.2f, 0, -1, -0.5f);
		bodyModel[167].setRotationPoint(23.0f, 3.0f, -9.0f);

		bodyModel[168] = new ModelRendererTurbo(this, 233, 89, textureX, textureY); // 169
		bodyModel[168].addShapeBox(0, 0, 0, 1, 1, 1, 0, -0.1f, 0, -0.8f, -0.6f, 0, -0.8f, -0.6f, 0, 0, -0.1f, 0, 0, -0.1f, 0, -0.3f, -0.6f, 0, -0.3f, -0.6f, 0, -0.5f, -0.1f, 0, -0.5f);
		bodyModel[168].setRotationPoint(23.0f, 3.0f, -9.0f);

		bodyModel[169] = new ModelRendererTurbo(this, 225, 89, textureX, textureY); // Box 169
		bodyModel[169].addShapeBox(0, 0, 0, 36, 5, 20, 0, 0, -0.1f, 0, 0, -0.1f, 0, 0, -0.1f, 0, 0, -0.1f, 0, -1, 0, -2, -1, 0, -2, -1, 0, -2, -1, 0, -2);
		bodyModel[169].setRotationPoint(-20.0f, 0.0f, -10.0f);

		bodyModel[170] = new ModelRendererTurbo(this, 321, 89, textureX, textureY); // Box 171
		bodyModel[170].addShapeBox(0, 0, 0, 1, 5, 1, 0, 0, -0.1f, -0.5f, -0.8f, -0.1f, -0.5f, -0.8f, -0.1f, 0, 0, -0.1f, 0, 0, 0, -0.8f, -0.8f, 0, -0.8f, -0.8f, 0, 0, 0, 0, 0);
		bodyModel[170].setRotationPoint(-45.0f, 0.0f, -9.0f);

		bodyModel[171] = new ModelRendererTurbo(this, 329, 89, textureX, textureY); // Box 171
		bodyModel[171].addShapeBox(0, 0, 0, 1, 5, 1, 0, -0.8f, -0.1f, -0.5f, 0, -0.1f, -0.5f, 0, -0.1f, 0, -0.8f, -0.1f, 0, -0.8f, 0, -0.8f, 0, 0, -0.8f, 0, 0, 0, -0.8f, 0, 0);
		bodyModel[171].setRotationPoint(-44.0f, 0.0f, -9.0f);

		bodyModel[172] = new ModelRendererTurbo(this, 337, 89, textureX, textureY); // Box 173
		bodyModel[172].addShapeBox(0, 0, 0, 2, 1, 1, 0, 0, -0.6f, -0.8f, 0, -0.6f, -0.8f, 0, -0.6f, 0, 0, -0.6f, 0, 0, 0, -0.8f, 0, 0, -0.8f, 0, 0, 0, 0, 0, 0);
		bodyModel[172].setRotationPoint(-45.0f, 4.0f, -9.0f);

		bodyModel[173] = new ModelRendererTurbo(this, 345, 89, textureX, textureY); // Box 173
		bodyModel[173].addShapeBox(0, 0, 0, 2, 1, 1, 0, 0, -0.6f, -0.7f, 0, -0.6f, -0.7f, 0, -0.6f, 0, 0, -0.6f, 0, 0, 0, -0.7f, 0, 0, -0.7f, 0, 0, 0, 0, 0, 0);
		bodyModel[173].setRotationPoint(-45.0f, 2.0f, -9.0f);

		bodyModel[174] = new ModelRendererTurbo(this, 353, 89, textureX, textureY); // Box 171
		bodyModel[174].addShapeBox(0, 0, 0, 1, 5, 1, 0, 0, -0.1f, -0.5f, -0.8f, -0.1f, -0.5f, -0.8f, -0.1f, 0, 0, -0.1f, 0, 0, 0, -0.8f, -0.8f, 0, -0.8f, -0.8f, 0, 0, 0, 0, 0);
		bodyModel[174].setRotationPoint(41.0f, 0.0f, 9.0f);
		bodyModel[174].rotateAngleY = 3.1415927f;

		bodyModel[175] = new ModelRendererTurbo(this, 361, 89, textureX, textureY); // Box 171
		bodyModel[175].addShapeBox(0, 0, 0, 1, 5, 1, 0, -0.8f, -0.1f, -0.5f, 0, -0.1f, -0.5f, 0, -0.1f, 0, -0.8f, -0.1f, 0, -0.8f, 0, -0.8f, 0, 0, -0.8f, 0, 0, 0, -0.8f, 0, 0);
		bodyModel[175].setRotationPoint(40.0f, 0.0f, 9.0f);
		bodyModel[175].rotateAngleY = 3.1415927f;

		bodyModel[176] = new ModelRendererTurbo(this, 369, 89, textureX, textureY); // Box 173
		bodyModel[176].addShapeBox(0, 0, 0, 2, 1, 1, 0, 0, -0.6f, -0.8f, 0, -0.6f, -0.8f, 0, -0.6f, 0, 0, -0.6f, 0, 0, 0, -0.8f, 0, 0, -0.8f, 0, 0, 0, 0, 0, 0);
		bodyModel[176].setRotationPoint(41.0f, 4.0f, 9.0f);
		bodyModel[176].rotateAngleY = 3.1415927f;

		bodyModel[177] = new ModelRendererTurbo(this, 393, 89, textureX, textureY); // Box 173
		bodyModel[177].addShapeBox(0, 0, 0, 2, 1, 1, 0, 0, -0.6f, -0.7f, 0, -0.6f, -0.7f, 0, -0.6f, 0, 0, -0.6f, 0, 0, 0, -0.7f, 0, 0, -0.7f, 0, 0, 0, 0, 0, 0);
		bodyModel[177].setRotationPoint(41.0f, 2.0f, 9.0f);
		bodyModel[177].rotateAngleY = 3.1415927f;

		bodyModel[178] = new ModelRendererTurbo(this, 401, 89, textureX, textureY); // Box 179
		bodyModel[178].addShapeBox(0, 0, 0, 3, 2, 1, 0, -0.5f, -0.5f, 0.5f, 0, -0.5f, 0.5f, 0, -0.5f, -0.5f, -0.5f, -0.5f, -0.5f, -0.5f, -0.2f, 0.5f, 0, -0.2f, 0.5f, 0, -0.2f, -0.5f, -0.5f, -0.2f, -0.5f);
		bodyModel[178].setRotationPoint(-48.0f, 1.0f, 8.0f);

		bodyModel[179] = new ModelRendererTurbo(this, 425, 89, textureX, textureY); // Box 179
		bodyModel[179].addShapeBox(0, 0, 0, 2, 2, 1, 0, 0, -1.3f, 0.2f, 0, 0.7f, 0.2f, 0, 0.7f, -1, 0, -1.3f, -1, 0, -0.5f, 0.2f, 0, -2.5f, 0.2f, 0, -2.5f, -1, 0, -0.5f, -1);
		bodyModel[179].setRotationPoint(-45.0f, 1.0f, 8.0f);

		bodyModel[180] = new ModelRendererTurbo(this, 473, 89, textureX, textureY); // Box 179
		bodyModel[180].addShapeBox(0, 0, 0, 3, 2, 1, 0, -0.5f, -0.5f, 0.5f, 0, -0.5f, 0.5f, 0, -0.5f, -0.5f, -0.5f, -0.5f, -0.5f, -0.5f, -0.2f, 0.5f, 0, -0.2f, 0.5f, 0, -0.2f, -0.5f, -0.5f, -0.2f, -0.5f);
		bodyModel[180].setRotationPoint(44.0f, 1.0f, -8.0f);
		bodyModel[180].rotateAngleY = 3.1415927f;

		bodyModel[181] = new ModelRendererTurbo(this, 489, 89, textureX, textureY); // Box 179
		bodyModel[181].addShapeBox(0, 0, 0, 2, 2, 1, 0, 0, -1.3f, 0.2f, 0, 0.7f, 0.2f, 0, 0.7f, -1, 0, -1.3f, -1, 0, -0.5f, 0.2f, 0, -2.5f, 0.2f, 0, -2.5f, -1, 0, -0.5f, -1);
		bodyModel[181].setRotationPoint(41.0f, 1.0f, -8.0f);
		bodyModel[181].rotateAngleY = 3.1415927f;

		bodyModel[182] = new ModelRendererTurbo(this, 1, 97, textureX, textureY); // Box 170
		bodyModel[182].addShapeBox(0, 0, 0, 1, 3, 22, 0, 0, 0, -0.1f, -0.8f, 0, -0.1f, -0.8f, 0, -0.1f, 0, 0, -0.1f, 0, 0, -0.5f, -0.8f, 0, -0.5f, -0.8f, 0, -0.5f, 0, 0, -0.5f);
		bodyModel[182].setRotationPoint(-42.0f, -3.0f, -11.0f);

		bodyModel[183] = new ModelRendererTurbo(this, 49, 97, textureX, textureY); // Box 170
		bodyModel[183].addShapeBox(0, 0, 0, 1, 14, 22, 0, 0, 0, -0.1f, -0.8f, 0, -0.1f, -0.8f, 0, -0.1f, 0, 0, -0.1f, 0, 0, -0.1f, -0.8f, 0, -0.1f, -0.8f, 0, -0.1f, 0, 0, -0.1f);
		bodyModel[183].setRotationPoint(-42.0f, -17.0f, -11.0f);

		bodyModel[184] = new ModelRendererTurbo(this, 153, 155, textureX, textureY); // Box 170
		bodyModel[184].addShapeBox(0, 0, 0, 1, 1, 7, 0, 0, 0, -1.1f, -0.8f, 0, -1.1f, -0.8f, 0, 0, 0, 0, 0, 0, 0, -0.1f, -0.8f, 0, -0.1f, -0.8f, 0, 0, 0, 0, 0);
		bodyModel[184].setRotationPoint(-42.0f, -18.0f, -11.0f);

		bodyModel[185] = new ModelRendererTurbo(this, 449, 152, textureX, textureY); // Box 170
		bodyModel[185].addShapeBox(0, 0, 0, 1, 2, 6, 0, 0, 0, -3.1f, -0.8f, 0, -3.1f, -0.8f, 0, 0, 0, 0, 0, 0, 0, -0.1f, -0.8f, 0, -0.1f, -0.8f, 0, 0, 0, 0, 0);
		bodyModel[185].setRotationPoint(-42.0f, -20.0f, -10.0f);

		bodyModel[186] = new ModelRendererTurbo(this, 33, 97, textureX, textureY); // Box 170
		bodyModel[186].addShapeBox(0, 0, 0, 1, 2, 14, 0, 0, -0.05f, -6.1f, -0.8f, -0.05f, -6.1f, -0.8f, -0.05f, -6.1f, 0, -0.05f, -6.1f, 0, 0, -0.1f, -0.8f, 0, -0.1f, -0.8f, 0, -0.1f, 0, 0, -0.1f);
		bodyModel[186].setRotationPoint(-42.0f, -22.0f, -7.0f);

		bodyModel[187] = new ModelRendererTurbo(this, 321, 97, textureX, textureY); // Box 170
		bodyModel[187].addShapeBox(0, 0, 0, 1, 3, 22, 0, -0.8f, 0, -0.1f, 0, 0, -0.1f, 0, 0, -0.1f, -0.8f, 0, -0.1f, -0.8f, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, -0.8f, 0, -0.5f);
		bodyModel[187].setRotationPoint(37.0f, -3.0f, -11.0f);

		bodyModel[188] = new ModelRendererTurbo(this, 369, 97, textureX, textureY); // Box 170
		bodyModel[188].addShapeBox(0, 0, 0, 1, 14, 22, 0, -0.8f, 0, -0.1f, 0, 0, -0.1f, 0, 0, -0.1f, -0.8f, 0, -0.1f, -0.8f, 0, -0.1f, 0, 0, -0.1f, 0, 0, -0.1f, -0.8f, 0, -0.1f);
		bodyModel[188].setRotationPoint(37.0f, -17.0f, -11.0f);

		bodyModel[189] = new ModelRendererTurbo(this, 424, 141, textureX, textureY); // Box 170
		bodyModel[189].addShapeBox(0, 0, 0, 1, 1, 7, 0, -0.8f, 0, -1.1f, 0, 0, -1.1f, 0, 0, 0, -0.8f, 0, 0, -0.8f, 0, -0.1f, 0, 0, -0.1f, 0, 0, 0, -0.8f, 0, 0);
		bodyModel[189].setRotationPoint(37.0f, -18.0f, -11.0f);

		bodyModel[190] = new ModelRendererTurbo(this, 201, 160, textureX, textureY); // Box 170
		bodyModel[190].addShapeBox(0, 0, 0, 1, 2, 6, 0, -0.8f, 0, -3.1f, 0, 0, -3.1f, 0, 0, 0, -0.8f, 0, 0, -0.8f, 0, -0.1f, 0, 0, -0.1f, 0, 0, 0, -0.8f, 0, 0);
		bodyModel[190].setRotationPoint(37.0f, -20.0f, -10.0f);

		bodyModel[191] = new ModelRendererTurbo(this, 185, 97, textureX, textureY); // Box 170
		bodyModel[191].addShapeBox(0, 0, 0, 1, 2, 14, 0, -0.8f, -0.05f, -6.1f, 0, -0.05f, -6.1f, 0, -0.05f, -6.1f, -0.8f, -0.05f, -6.1f, -0.8f, 0, -0.1f, 0, 0, -0.1f, 0, 0, -0.1f, -0.8f, 0, -0.1f);
		bodyModel[191].setRotationPoint(37.0f, -22.0f, -7.0f);

		bodyModel[192] = new ModelRendererTurbo(this, 353, 97, textureX, textureY); // Box 170
		bodyModel[192].addShapeBox(0, 0, 0, 4, 1, 8, 0, 0, 0, -0.3f, 0.1f, 0, -0.3f, 0.1f, 0, 0, 0, 0, 0, 0, 0, -0.3f, 0, 0, -0.3f, 0, 0, 0, 0, 0, 0);
		bodyModel[192].setRotationPoint(-38.0f, -4.0f, -11.0f);

		bodyModel[193] = new ModelRendererTurbo(this, 489, 89, textureX, textureY); // Box 170
		bodyModel[193].addShapeBox(0, 0, 0, 1, 8, 8, 0, -1, 0, -0.3f, 0.5f, 0, -0.3f, 0.5f, 0, 0, -1, 0, 0, 0, 0, -0.3f, -0.5f, 0, -0.3f, -0.5f, 0, 0, 0, 0, 0);
		bodyModel[193].setRotationPoint(-34.0f, -11.0f, -11.0f);

		bodyModel[194] = new ModelRendererTurbo(this, 377, 89, textureX, textureY); // Box 170
		bodyModel[194].addBox(0, 0, 0, 1, 3, 1, 0f);
		bodyModel[194].setRotationPoint(-36.0f, -3.0f, -9.0f);

		bodyModel[195] = new ModelRendererTurbo(this, 505, 89, textureX, textureY); // Box 170
		bodyModel[195].addBox(0, 0, 0, 1, 3, 1, 0f);
		bodyModel[195].setRotationPoint(-36.0f, -3.0f, -6.0f);

		bodyModel[196] = new ModelRendererTurbo(this, 401, 97, textureX, textureY); // Box 170
		bodyModel[196].addShapeBox(0, 0, 0, 4, 1, 8, 0, 0, 0, 0, 0.1f, 0, 0, 0.1f, 0, -0.3f, 0, 0, -0.3f, 0, 0, 0, 0, 0, 0, 0, 0, -0.3f, 0, 0, -0.3f);
		bodyModel[196].setRotationPoint(-38.0f, -4.0f, 3.0f);

		bodyModel[197] = new ModelRendererTurbo(this, 1, 97, textureX, textureY); // Box 170
		bodyModel[197].addShapeBox(0, 0, 0, 1, 8, 8, 0, -1, 0, 0, 0.5f, 0, 0, 0.5f, 0, -0.3f, -1, 0, -0.3f, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, -0.3f, 0, 0, -0.3f);
		bodyModel[197].setRotationPoint(-34.0f, -11.0f, 3.0f);

		bodyModel[198] = new ModelRendererTurbo(this, 1, 97, textureX, textureY); // Box 170
		bodyModel[198].addBox(0, 0, 0, 1, 3, 1, 0f);
		bodyModel[198].setRotationPoint(-36.0f, -3.0f, 5.0f);

		bodyModel[199] = new ModelRendererTurbo(this, 17, 97, textureX, textureY); // Box 170
		bodyModel[199].addBox(0, 0, 0, 1, 3, 1, 0f);
		bodyModel[199].setRotationPoint(-36.0f, -3.0f, 8.0f);

		bodyModel[200] = new ModelRendererTurbo(this, 81, 105, textureX, textureY); // Box 170
		bodyModel[200].addShapeBox(0, 0, 0, 4, 1, 8, 0, 0, 0, -0.3f, 0.1f, 0, -0.3f, 0.1f, 0, 0, 0, 0, 0, 0, 0, -0.3f, 0, 0, -0.3f, 0, 0, 0, 0, 0, 0);
		bodyModel[200].setRotationPoint(-29.0f, -4.0f, -11.0f);

		bodyModel[201] = new ModelRendererTurbo(this, 113, 105, textureX, textureY); // Box 170
		bodyModel[201].addShapeBox(0, 0, 0, 1, 8, 8, 0, -1, 0, -0.3f, 0.5f, 0, -0.3f, 0.5f, 0, 0, -1, 0, 0, 0, 0, -0.3f, -0.5f, 0, -0.3f, -0.5f, 0, 0, 0, 0, 0);
		bodyModel[201].setRotationPoint(-25.0f, -11.0f, -11.0f);

		bodyModel[202] = new ModelRendererTurbo(this, 33, 97, textureX, textureY); // Box 170
		bodyModel[202].addBox(0, 0, 0, 1, 3, 1, 0f);
		bodyModel[202].setRotationPoint(-27.0f, -3.0f, -9.0f);

		bodyModel[203] = new ModelRendererTurbo(this, 41, 97, textureX, textureY); // Box 170
		bodyModel[203].addBox(0, 0, 0, 1, 3, 1, 0f);
		bodyModel[203].setRotationPoint(-27.0f, -3.0f, -6.0f);

		bodyModel[204] = new ModelRendererTurbo(this, 137, 105, textureX, textureY); // Box 170
		bodyModel[204].addShapeBox(0, 0, 0, 4, 1, 8, 0, 0, 0, 0, 0.1f, 0, 0, 0.1f, 0, -0.3f, 0, 0, -0.3f, 0, 0, 0, 0, 0, 0, 0, 0, -0.3f, 0, 0, -0.3f);
		bodyModel[204].setRotationPoint(-29.0f, -4.0f, 3.0f);

		bodyModel[205] = new ModelRendererTurbo(this, 457, 113, textureX, textureY); // Box 170
		bodyModel[205].addShapeBox(0, 0, 0, 1, 8, 8, 0, -1, 0, 0, 0.5f, 0, 0, 0.5f, 0, -0.3f, -1, 0, -0.3f, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, -0.3f, 0, 0, -0.3f);
		bodyModel[205].setRotationPoint(-25.0f, -11.0f, 3.0f);

		bodyModel[206] = new ModelRendererTurbo(this, 57, 97, textureX, textureY); // Box 170
		bodyModel[206].addBox(0, 0, 0, 1, 3, 1, 0f);
		bodyModel[206].setRotationPoint(-27.0f, -3.0f, 5.0f);

		bodyModel[207] = new ModelRendererTurbo(this, 65, 97, textureX, textureY); // Box 170
		bodyModel[207].addBox(0, 0, 0, 1, 3, 1, 0f);
		bodyModel[207].setRotationPoint(-27.0f, -3.0f, 8.0f);

		bodyModel[208] = new ModelRendererTurbo(this, 481, 113, textureX, textureY); // Box 170
		bodyModel[208].addShapeBox(0, 0, 0, 4, 1, 8, 0, 0, 0, -0.3f, 0.1f, 0, -0.3f, 0.1f, 0, 0, 0, 0, 0, 0, 0, -0.3f, 0, 0, -0.3f, 0, 0, 0, 0, 0, 0);
		bodyModel[208].setRotationPoint(-20.0f, -4.0f, -11.0f);

		bodyModel[209] = new ModelRendererTurbo(this, 97, 121, textureX, textureY); // Box 170
		bodyModel[209].addShapeBox(0, 0, 0, 1, 8, 8, 0, -1, 0, -0.3f, 0.5f, 0, -0.3f, 0.5f, 0, 0, -1, 0, 0, 0, 0, -0.3f, -0.5f, 0, -0.3f, -0.5f, 0, 0, 0, 0, 0);
		bodyModel[209].setRotationPoint(-16.0f, -11.0f, -11.0f);

		bodyModel[210] = new ModelRendererTurbo(this, 81, 97, textureX, textureY); // Box 170
		bodyModel[210].addBox(0, 0, 0, 1, 3, 1, 0f);
		bodyModel[210].setRotationPoint(-18.0f, -3.0f, -9.0f);

		bodyModel[211] = new ModelRendererTurbo(this, 89, 97, textureX, textureY); // Box 170
		bodyModel[211].addBox(0, 0, 0, 1, 3, 1, 0f);
		bodyModel[211].setRotationPoint(-18.0f, -3.0f, -6.0f);

		bodyModel[212] = new ModelRendererTurbo(this, 129, 121, textureX, textureY); // Box 170
		bodyModel[212].addShapeBox(0, 0, 0, 4, 1, 8, 0, 0, 0, 0, 0.1f, 0, 0, 0.1f, 0, -0.3f, 0, 0, -0.3f, 0, 0, 0, 0, 0, 0, 0, 0, -0.3f, 0, 0, -0.3f);
		bodyModel[212].setRotationPoint(-20.0f, -4.0f, 3.0f);

		bodyModel[213] = new ModelRendererTurbo(this, 161, 121, textureX, textureY); // Box 170
		bodyModel[213].addShapeBox(0, 0, 0, 1, 8, 8, 0, -1, 0, 0, 0.5f, 0, 0, 0.5f, 0, -0.3f, -1, 0, -0.3f, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, -0.3f, 0, 0, -0.3f);
		bodyModel[213].setRotationPoint(-16.0f, -11.0f, 3.0f);

		bodyModel[214] = new ModelRendererTurbo(this, 137, 97, textureX, textureY); // Box 170
		bodyModel[214].addBox(0, 0, 0, 1, 3, 1, 0f);
		bodyModel[214].setRotationPoint(-18.0f, -3.0f, 5.0f);

		bodyModel[215] = new ModelRendererTurbo(this, 145, 97, textureX, textureY); // Box 170
		bodyModel[215].addBox(0, 0, 0, 1, 3, 1, 0f);
		bodyModel[215].setRotationPoint(-18.0f, -3.0f, 8.0f);

		bodyModel[216] = new ModelRendererTurbo(this, 185, 121, textureX, textureY); // Box 170
		bodyModel[216].addShapeBox(0, 0, 0, 4, 1, 8, 0, 0, 0, -0.3f, 0.1f, 0, -0.3f, 0.1f, 0, 0, 0, 0, 0, 0, 0, -0.3f, 0, 0, -0.3f, 0, 0, 0, 0, 0, 0);
		bodyModel[216].setRotationPoint(-11.0f, -4.0f, -11.0f);

		bodyModel[217] = new ModelRendererTurbo(this, 217, 121, textureX, textureY); // Box 170
		bodyModel[217].addShapeBox(0, 0, 0, 1, 8, 8, 0, -1, 0, -0.3f, 0.5f, 0, -0.3f, 0.5f, 0, 0, -1, 0, 0, 0, 0, -0.3f, -0.5f, 0, -0.3f, -0.5f, 0, 0, 0, 0, 0);
		bodyModel[217].setRotationPoint(-7.0f, -11.0f, -11.0f);

		bodyModel[218] = new ModelRendererTurbo(this, 161, 97, textureX, textureY); // Box 170
		bodyModel[218].addBox(0, 0, 0, 1, 3, 1, 0f);
		bodyModel[218].setRotationPoint(-9.0f, -3.0f, -9.0f);

		bodyModel[219] = new ModelRendererTurbo(this, 169, 97, textureX, textureY); // Box 170
		bodyModel[219].addBox(0, 0, 0, 1, 3, 1, 0f);
		bodyModel[219].setRotationPoint(-9.0f, -3.0f, -6.0f);

		bodyModel[220] = new ModelRendererTurbo(this, 241, 121, textureX, textureY); // Box 170
		bodyModel[220].addShapeBox(0, 0, 0, 4, 1, 8, 0, 0, 0, 0, 0.1f, 0, 0, 0.1f, 0, -0.3f, 0, 0, -0.3f, 0, 0, 0, 0, 0, 0, 0, 0, -0.3f, 0, 0, -0.3f);
		bodyModel[220].setRotationPoint(-11.0f, -4.0f, 3.0f);

		bodyModel[221] = new ModelRendererTurbo(this, 273, 121, textureX, textureY); // Box 170
		bodyModel[221].addShapeBox(0, 0, 0, 1, 8, 8, 0, -1, 0, 0, 0.5f, 0, 0, 0.5f, 0, -0.3f, -1, 0, -0.3f, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, -0.3f, 0, 0, -0.3f);
		bodyModel[221].setRotationPoint(-7.0f, -11.0f, 3.0f);

		bodyModel[222] = new ModelRendererTurbo(this, 185, 97, textureX, textureY); // Box 170
		bodyModel[222].addBox(0, 0, 0, 1, 3, 1, 0f);
		bodyModel[222].setRotationPoint(-9.0f, -3.0f, 5.0f);

		bodyModel[223] = new ModelRendererTurbo(this, 193, 97, textureX, textureY); // Box 170
		bodyModel[223].addBox(0, 0, 0, 1, 3, 1, 0f);
		bodyModel[223].setRotationPoint(-9.0f, -3.0f, 8.0f);

		bodyModel[224] = new ModelRendererTurbo(this, 297, 121, textureX, textureY); // Box 170
		bodyModel[224].addShapeBox(0, 0, 0, 4, 1, 8, 0, 0, 0, -0.3f, 0.1f, 0, -0.3f, 0.1f, 0, 0, 0, 0, 0, 0, 0, -0.3f, 0, 0, -0.3f, 0, 0, 0, 0, 0, 0);
		bodyModel[224].setRotationPoint(0.0f, -4.0f, -11.0f);

		bodyModel[225] = new ModelRendererTurbo(this, 417, 121, textureX, textureY); // Box 170
		bodyModel[225].addShapeBox(0, 0, 0, 1, 8, 8, 0, -1, 0, -0.3f, 0.5f, 0, -0.3f, 0.5f, 0, 0, -1, 0, 0, 0, 0, -0.3f, -0.5f, 0, -0.3f, -0.5f, 0, 0, 0, 0, 0);
		bodyModel[225].setRotationPoint(4.0f, -11.0f, -11.0f);

		bodyModel[226] = new ModelRendererTurbo(this, 209, 97, textureX, textureY); // Box 170
		bodyModel[226].addBox(0, 0, 0, 1, 3, 1, 0f);
		bodyModel[226].setRotationPoint(2.0f, -3.0f, -9.0f);

		bodyModel[227] = new ModelRendererTurbo(this, 225, 97, textureX, textureY); // Box 170
		bodyModel[227].addBox(0, 0, 0, 1, 3, 1, 0f);
		bodyModel[227].setRotationPoint(2.0f, -3.0f, -6.0f);

		bodyModel[228] = new ModelRendererTurbo(this, 1, 129, textureX, textureY); // Box 170
		bodyModel[228].addShapeBox(0, 0, 0, 4, 1, 8, 0, 0, 0, 0, 0.1f, 0, 0, 0.1f, 0, -0.3f, 0, 0, -0.3f, 0, 0, 0, 0, 0, 0, 0, 0, -0.3f, 0, 0, -0.3f);
		bodyModel[228].setRotationPoint(0.0f, -4.0f, 3.0f);

		bodyModel[229] = new ModelRendererTurbo(this, 33, 129, textureX, textureY); // Box 170
		bodyModel[229].addShapeBox(0, 0, 0, 1, 8, 8, 0, -1, 0, 0, 0.5f, 0, 0, 0.5f, 0, -0.3f, -1, 0, -0.3f, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, -0.3f, 0, 0, -0.3f);
		bodyModel[229].setRotationPoint(4.0f, -11.0f, 3.0f);

		bodyModel[230] = new ModelRendererTurbo(this, 233, 97, textureX, textureY); // Box 170
		bodyModel[230].addBox(0, 0, 0, 1, 3, 1, 0f);
		bodyModel[230].setRotationPoint(2.0f, -3.0f, 5.0f);

		bodyModel[231] = new ModelRendererTurbo(this, 321, 97, textureX, textureY); // Box 170
		bodyModel[231].addBox(0, 0, 0, 1, 3, 1, 0f);
		bodyModel[231].setRotationPoint(2.0f, -3.0f, 8.0f);

		bodyModel[232] = new ModelRendererTurbo(this, 321, 129, textureX, textureY); // Box 170
		bodyModel[232].addShapeBox(0, 0, 0, 4, 1, 8, 0, 0, 0, -0.3f, 0.1f, 0, -0.3f, 0.1f, 0, 0, 0, 0, 0, 0, 0, -0.3f, 0, 0, -0.3f, 0, 0, 0, 0, 0, 0);
		bodyModel[232].setRotationPoint(9.0f, -4.0f, -11.0f);

		bodyModel[233] = new ModelRendererTurbo(this, 353, 129, textureX, textureY); // Box 170
		bodyModel[233].addShapeBox(0, 0, 0, 1, 8, 8, 0, -1, 0, -0.3f, 0.5f, 0, -0.3f, 0.5f, 0, 0, -1, 0, 0, 0, 0, -0.3f, -0.5f, 0, -0.3f, -0.5f, 0, 0, 0, 0, 0);
		bodyModel[233].setRotationPoint(13.0f, -11.0f, -11.0f);

		bodyModel[234] = new ModelRendererTurbo(this, 329, 97, textureX, textureY); // Box 170
		bodyModel[234].addBox(0, 0, 0, 1, 3, 1, 0f);
		bodyModel[234].setRotationPoint(11.0f, -3.0f, -9.0f);

		bodyModel[235] = new ModelRendererTurbo(this, 337, 97, textureX, textureY); // Box 170
		bodyModel[235].addBox(0, 0, 0, 1, 3, 1, 0f);
		bodyModel[235].setRotationPoint(11.0f, -3.0f, -6.0f);

		bodyModel[236] = new ModelRendererTurbo(this, 473, 129, textureX, textureY); // Box 170
		bodyModel[236].addShapeBox(0, 0, 0, 4, 1, 8, 0, 0, 0, 0, 0.1f, 0, 0, 0.1f, 0, -0.3f, 0, 0, -0.3f, 0, 0, 0, 0, 0, 0, 0, 0, -0.3f, 0, 0, -0.3f);
		bodyModel[236].setRotationPoint(9.0f, -4.0f, 3.0f);

		bodyModel[237] = new ModelRendererTurbo(this, 441, 129, textureX, textureY); // Box 170
		bodyModel[237].addShapeBox(0, 0, 0, 1, 8, 8, 0, -1, 0, 0, 0.5f, 0, 0, 0.5f, 0, -0.3f, -1, 0, -0.3f, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, -0.3f, 0, 0, -0.3f);
		bodyModel[237].setRotationPoint(13.0f, -11.0f, 3.0f);

		bodyModel[238] = new ModelRendererTurbo(this, 353, 97, textureX, textureY); // Box 170
		bodyModel[238].addBox(0, 0, 0, 1, 3, 1, 0f);
		bodyModel[238].setRotationPoint(11.0f, -3.0f, 5.0f);

		bodyModel[239] = new ModelRendererTurbo(this, 377, 97, textureX, textureY); // Box 170
		bodyModel[239].addBox(0, 0, 0, 1, 3, 1, 0f);
		bodyModel[239].setRotationPoint(11.0f, -3.0f, 8.0f);

		bodyModel[240] = new ModelRendererTurbo(this, 57, 137, textureX, textureY); // Box 170
		bodyModel[240].addShapeBox(0, 0, 0, 4, 1, 8, 0, 0, 0, -0.3f, 0.1f, 0, -0.3f, 0.1f, 0, 0, 0, 0, 0, 0, 0, -0.3f, 0, 0, -0.3f, 0, 0, 0, 0, 0, 0);
		bodyModel[240].setRotationPoint(18.0f, -4.0f, -11.0f);

		bodyModel[241] = new ModelRendererTurbo(this, 113, 137, textureX, textureY); // Box 170
		bodyModel[241].addShapeBox(0, 0, 0, 1, 8, 8, 0, -1, 0, -0.3f, 0.5f, 0, -0.3f, 0.5f, 0, 0, -1, 0, 0, 0, 0, -0.3f, -0.5f, 0, -0.3f, -0.5f, 0, 0, 0, 0, 0);
		bodyModel[241].setRotationPoint(22.0f, -11.0f, -11.0f);

		bodyModel[242] = new ModelRendererTurbo(this, 385, 97, textureX, textureY); // Box 170
		bodyModel[242].addBox(0, 0, 0, 1, 3, 1, 0f);
		bodyModel[242].setRotationPoint(20.0f, -3.0f, -9.0f);

		bodyModel[243] = new ModelRendererTurbo(this, 401, 97, textureX, textureY); // Box 170
		bodyModel[243].addBox(0, 0, 0, 1, 3, 1, 0f);
		bodyModel[243].setRotationPoint(20.0f, -3.0f, -6.0f);

		bodyModel[244] = new ModelRendererTurbo(this, 137, 137, textureX, textureY); // Box 170
		bodyModel[244].addShapeBox(0, 0, 0, 4, 1, 8, 0, 0, 0, 0, 0.1f, 0, 0, 0.1f, 0, -0.3f, 0, 0, -0.3f, 0, 0, 0, 0, 0, 0, 0, 0, -0.3f, 0, 0, -0.3f);
		bodyModel[244].setRotationPoint(18.0f, -4.0f, 3.0f);

		bodyModel[245] = new ModelRendererTurbo(this, 177, 137, textureX, textureY); // Box 170
		bodyModel[245].addShapeBox(0, 0, 0, 1, 8, 8, 0, -1, 0, 0, 0.5f, 0, 0, 0.5f, 0, -0.3f, -1, 0, -0.3f, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, -0.3f, 0, 0, -0.3f);
		bodyModel[245].setRotationPoint(22.0f, -11.0f, 3.0f);

		bodyModel[246] = new ModelRendererTurbo(this, 425, 97, textureX, textureY); // Box 170
		bodyModel[246].addBox(0, 0, 0, 1, 3, 1, 0f);
		bodyModel[246].setRotationPoint(20.0f, -3.0f, 5.0f);

		bodyModel[247] = new ModelRendererTurbo(this, 433, 97, textureX, textureY); // Box 170
		bodyModel[247].addBox(0, 0, 0, 1, 3, 1, 0f);
		bodyModel[247].setRotationPoint(20.0f, -3.0f, 8.0f);

		bodyModel[248] = new ModelRendererTurbo(this, 233, 137, textureX, textureY); // Box 170
		bodyModel[248].addShapeBox(0, 0, 0, 4, 1, 8, 0, 0, 0, -0.3f, 0.1f, 0, -0.3f, 0.1f, 0, 0, 0, 0, 0, 0, 0, -0.3f, 0, 0, -0.3f, 0, 0, 0, 0, 0, 0);
		bodyModel[248].setRotationPoint(27.0f, -4.0f, -11.0f);

		bodyModel[249] = new ModelRendererTurbo(this, 201, 137, textureX, textureY); // Box 170
		bodyModel[249].addShapeBox(0, 0, 0, 1, 8, 8, 0, -1, 0, -0.3f, 0.5f, 0, -0.3f, 0.5f, 0, 0, -1, 0, 0, 0, 0, -0.3f, -0.5f, 0, -0.3f, -0.5f, 0, 0, 0, 0, 0);
		bodyModel[249].setRotationPoint(31.0f, -11.0f, -11.0f);

		bodyModel[250] = new ModelRendererTurbo(this, 449, 97, textureX, textureY); // Box 170
		bodyModel[250].addBox(0, 0, 0, 1, 3, 1, 0f);
		bodyModel[250].setRotationPoint(29.0f, -3.0f, -9.0f);

		bodyModel[251] = new ModelRendererTurbo(this, 457, 97, textureX, textureY); // Box 170
		bodyModel[251].addBox(0, 0, 0, 1, 3, 1, 0f);
		bodyModel[251].setRotationPoint(29.0f, -3.0f, -6.0f);

		bodyModel[252] = new ModelRendererTurbo(this, 289, 137, textureX, textureY); // Box 170
		bodyModel[252].addShapeBox(0, 0, 0, 4, 1, 8, 0, 0, 0, 0, 0.1f, 0, 0, 0.1f, 0, -0.3f, 0, 0, -0.3f, 0, 0, 0, 0, 0, 0, 0, 0, -0.3f, 0, 0, -0.3f);
		bodyModel[252].setRotationPoint(27.0f, -4.0f, 3.0f);

		bodyModel[253] = new ModelRendererTurbo(this, 377, 137, textureX, textureY); // Box 170
		bodyModel[253].addShapeBox(0, 0, 0, 1, 8, 8, 0, -1, 0, 0, 0.5f, 0, 0, 0.5f, 0, -0.3f, -1, 0, -0.3f, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, -0.3f, 0, 0, -0.3f);
		bodyModel[253].setRotationPoint(31.0f, -11.0f, 3.0f);

		bodyModel[254] = new ModelRendererTurbo(this, 473, 97, textureX, textureY); // Box 170
		bodyModel[254].addBox(0, 0, 0, 1, 3, 1, 0f);
		bodyModel[254].setRotationPoint(29.0f, -3.0f, 5.0f);

		bodyModel[255] = new ModelRendererTurbo(this, 481, 97, textureX, textureY); // Box 170
		bodyModel[255].addBox(0, 0, 0, 1, 3, 1, 0f);
		bodyModel[255].setRotationPoint(29.0f, -3.0f, 8.0f);

		bodyModel[256] = new ModelRendererTurbo(this, 233, 159, textureX, textureY); // Box 170
		bodyModel[256].addShapeBox(0, 0, 0, 80, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, -0.05f, 0, 0, -0.05f, 0, -0.5f, 0, 0, -0.5f, 0, 0, 0, -0.05f, 0, 0, -0.05f);
		bodyModel[256].setRotationPoint(-42.0f, -16.0f, 8.0f);

		bodyModel[257] = new ModelRendererTurbo(this, 233, 169, textureX, textureY); // Box 170
		bodyModel[257].addShapeBox(0, 0, 0, 80, 1, 3, 0, 0, 0, -0.05f, 0, 0, -0.05f, 0, 0, 0, 0, 0, 0, 0, 0, -0.05f, 0, 0, -0.05f, 0, -0.5f, 0, 0, -0.5f, 0);
		bodyModel[257].setRotationPoint(-42.0f, -16.0f, -11.0f);

		bodyModel[258] = new ModelRendererTurbo(this, 225, 179, textureX, textureY); // Box 170
		bodyModel[258].addShapeBox(0, 0, 0, 1, 3, 4, 0, -0.8f, 0, -0.1f, 0, 0, -0.1f, 0, 0, 0, -0.8f, 0, 0, -0.8f, 0, -0.6f, 0, 0, -0.6f, 0, 0, 0, -0.8f, 0, 0);
		bodyModel[258].setRotationPoint(-4.0f, -3.0f, -11.0f);

		bodyModel[259] = new ModelRendererTurbo(this, 225, 188, textureX, textureY); // Box 170
		bodyModel[259].addShapeBox(0, 0, 0, 1, 14, 4, 0, -0.8f, 0, -0.1f, 0, 0, -0.1f, 0, 2.9f, 0, -0.8f, 2.9f, 0, -0.8f, 0, -0.1f, 0, 0, -0.1f, 0, 0, 0, -0.8f, 0, 0);
		bodyModel[259].setRotationPoint(-4.0f, -17.0f, -11.0f);

		bodyModel[260] = new ModelRendererTurbo(this, 225, 179, textureX, textureY); // Box 170
		bodyModel[260].addShapeBox(0, 0, 0, 1, 3, 4, 0, -0.8f, 0, 0, 0, 0, 0, 0, 0, -0.1f, -0.8f, 0, -0.1f, -0.8f, 0, 0, 0, 0, 0, 0, 0, -0.6f, -0.8f, 0, -0.6f);
		bodyModel[260].setRotationPoint(-4.0f, -3.0f, 7.0f);

		bodyModel[261] = new ModelRendererTurbo(this, 225, 188, textureX, textureY); // Box 170
		bodyModel[261].addShapeBox(0, 0, 0, 1, 14, 4, 0, -0.8f, 2.9f, 0, 0, 2.9f, 0, 0, 0, -0.1f, -0.8f, 0, -0.1f, -0.8f, 0, 0, 0, 0, 0, 0, 0, -0.1f, -0.8f, 0, -0.1f);
		bodyModel[261].setRotationPoint(-4.0f, -17.0f, 7.0f);

		bodyModel[262] = new ModelRendererTurbo(this, 337, 181, textureX, textureY); // Box 170
		bodyModel[262].addShapeBox(0, 0, 0, 80, 1, 10, 0, 0, -0.4f, 0, 0, -0.4f, 0, 0, -0.4f, 0, 0, -0.4f, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		bodyModel[262].setRotationPoint(-42.0f, -21.0f, -5.0f);

		bodyModel[263] = new ModelRendererTurbo(this, 469, 152, textureX, textureY); // Box 170
		bodyModel[263].addShapeBox(0, 0, 0, 1, 2, 3, 0, 0, 0, 0, -0.8f, 0, 0, -0.8f, 0, -0.1f, 0, 0, -0.1f, 0, 0, 0, -0.8f, 0, 0, -0.8f, 0, 2.9f, 0, 0, 2.9f);
		bodyModel[263].setRotationPoint(-42.0f, -20.0f, 4.0f);

		bodyModel[264] = new ModelRendererTurbo(this, 461, 162, textureX, textureY); // Box 170
		bodyModel[264].addShapeBox(0, 0, 0, 1, 2, 8, 0, 0, 0, 0, -0.8f, 0, 0, -0.8f, 0, 0, 0, 0, 0, 0, 0, 0, -0.8f, 0, 0, -0.8f, 0, 0, 0, 0, 0);
		bodyModel[264].setRotationPoint(-42.0f, -20.0f, -4.0f);

		bodyModel[265] = new ModelRendererTurbo(this, 174, 157, textureX, textureY); // Box 170
		bodyModel[265].addShapeBox(0, 0, 0, 1, 1, 7, 0, 0, 0, 0, -0.8f, 0, 0, -0.8f, 0, -1.1f, 0, 0, -1.1f, 0, 0, 0, -0.8f, 0, 0, -0.8f, 0, -0.1f, 0, 0, -0.1f);
		bodyModel[265].setRotationPoint(-42.0f, -18.0f, 4.0f);

		bodyModel[266] = new ModelRendererTurbo(this, 153, 169, textureX, textureY); // Box 170
		bodyModel[266].addShapeBox(0, 0, 0, 1, 1, 8, 0, 0, 0, 0, -0.8f, 0, 0, -0.8f, 0, 0, 0, 0, 0, 0, 0, 0, -0.8f, 0, 0, -0.8f, 0, 0, 0, 0, 0);
		bodyModel[266].setRotationPoint(-42.0f, -18.0f, -4.0f);

		bodyModel[267] = new ModelRendererTurbo(this, 424, 159, textureX, textureY); // Box 170
		bodyModel[267].addShapeBox(0, 0, 0, 1, 1, 7, 0, -0.8f, 0, 0, 0, 0, 0, 0, 0, -1.1f, -0.8f, 0, -1.1f, -0.8f, 0, 0, 0, 0, 0, 0, 0, -0.1f, -0.8f, 0, -0.1f);
		bodyModel[267].setRotationPoint(37.0f, -18.0f, 4.0f);

		bodyModel[268] = new ModelRendererTurbo(this, 404, 152, textureX, textureY); // Box 170
		bodyModel[268].addShapeBox(0, 0, 0, 1, 1, 8, 0, -0.8f, 0, 0, 0, 0, 0, 0, 0, 0, -0.8f, 0, 0, -0.8f, 0, 0, 0, 0, 0, 0, 0, 0, -0.8f, 0, 0);
		bodyModel[268].setRotationPoint(37.0f, -18.0f, -4.0f);

		bodyModel[269] = new ModelRendererTurbo(this, 201, 175, textureX, textureY); // Box 170
		bodyModel[269].addShapeBox(0, 0, 0, 1, 2, 8, 0, -0.8f, 0, 0, 0, 0, 0, 0, 0, 0, -0.8f, 0, 0, -0.8f, 0, 0, 0, 0, 0, 0, 0, 0, -0.8f, 0, 0);
		bodyModel[269].setRotationPoint(37.0f, -20.0f, -4.0f);

		bodyModel[270] = new ModelRendererTurbo(this, 201, 193, textureX, textureY); // Box 170
		bodyModel[270].addShapeBox(0, 0, 0, 1, 2, 6, 0, -0.8f, 0, 0, 0, 0, 0, 0, 0, -3.1f, -0.8f, 0, -3.1f, -0.8f, 0, 0, 0, 0, 0, 0, 0, -0.1f, -0.8f, 0, -0.1f);
		bodyModel[270].setRotationPoint(37.0f, -20.0f, 4.0f);

		flipAll();
	}

}
