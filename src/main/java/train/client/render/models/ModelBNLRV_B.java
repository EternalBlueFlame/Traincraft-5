package train.client.render.models;



import tmt.ModelConverter;
import tmt.ModelRendererTurbo;

/** This file was exported via the (Default) FlansMod Exporter of<br>
 *  FMT (Fex's Modelling Toolbox) v.2.7.1 &copy; 2022 - Fexcraft.net<br>
 *  All rights reserved. For this Model's License contact the Author/Creator.
 */
public class ModelBNLRV_B extends ModelConverter {

 int textureX = 512;
  int textureY = 256;

    public ModelBNLRV_B(){
        bodyModel = new ModelRendererTurbo[226];
        //
        bodyModel[0] = new ModelRendererTurbo(this, 1, 1, textureX, textureY); // Box 0
        bodyModel[0].addBox(0, 0, 0, 32, 1, 18, 0f);
        bodyModel[0].setRotationPoint(-19.0f, 3.0f, -9.0f);

        bodyModel[1] = new ModelRendererTurbo(this, 105, 1, textureX, textureY); // Box 1
        bodyModel[1].addShapeBox(0, 0, 0, 1, 21, 18, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        bodyModel[1].setRotationPoint(-29.99f, -15.0f, -9.0f);

        bodyModel[2] = new ModelRendererTurbo(this, 89, 1, textureX, textureY); // Box 2
        bodyModel[2].addShapeBox(0, 0, 0, 11, 6, 1, 0, -1, -0.5f, 0.5f, 0, -0.5f, 0, 0, -0.5f, 3, -1, -0.5f, -1, -1.25f, 0, 0.5f, 0, 0, 0, 0, 0, 3, -1.25f, 0, -1);
        bodyModel[2].setRotationPoint(-40.0f, -4.0f, 6.0f);

        bodyModel[3] = new ModelRendererTurbo(this, 129, 1, textureX, textureY); // Box 5
        bodyModel[3].addShapeBox(0, 0, 0, 11, 6, 1, 0, -1, -0.5f, -1, 0, -0.5f, 3, 0, -0.5f, 0, -1, -0.5f, 0.5f, -1.25f, 0, -1, 0, 0, 3, 0, 0, 0, -1.25f, 0, 0.5f);
        bodyModel[3].setRotationPoint(-40.0f, -4.0f, -7.0f);

        bodyModel[4] = new ModelRendererTurbo(this, 145, 1, textureX, textureY); // Box 8
        bodyModel[4].addShapeBox(0, 0, 0, 1, 6, 12, 0, 0, -0.5f, -0.5f, 0, -0.5f, -0.5f, 0, -0.5f, -0.5f, 0, -0.5f, -0.5f, -0.25f, 0, -0.5f, 0.5f, 0, -0.5f, 0.5f, 0, -0.5f, -0.25f, 0, -0.5f);
        bodyModel[4].setRotationPoint(-39.0f, -4.0f, -6.0f);

        bodyModel[5] = new ModelRendererTurbo(this, 1, 1, textureX, textureY); // Box 9
        bodyModel[5].addShapeBox(0, 0, 0, 2, 12, 1, 0, 0, 0, -2.45f, 0, 0, -3, 0, 0, 3, 0, 0, 2.28f, 0, 0, -2.45f, 0, 0, -3, 0, 0, 3, 0, 0, 2.2f);
        bodyModel[5].setRotationPoint(-31.0f, -15.5f, 6.0f);

        bodyModel[6] = new ModelRendererTurbo(this, 9, 1, textureX, textureY); // Box 10
        bodyModel[6].addShapeBox(0, 0, 0, 2, 12, 1, 0, 0, 0, 2.28f, 0, 0, 3, 0, 0, -3, 0, 0, -2.45f, 0, 0, 2.2f, 0, 0, 3, 0, 0, -3, 0, 0, -2.45f);
        bodyModel[6].setRotationPoint(-31.0f, -15.5f, -7.0f);

        bodyModel[7] = new ModelRendererTurbo(this, 177, 1, textureX, textureY); // Box 10
        bodyModel[7].addShapeBox(0, 0, 0, 5, 20, 1, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        bodyModel[7].setRotationPoint(-29.0f, -15.0f, -9.9f);

        bodyModel[8] = new ModelRendererTurbo(this, 193, 1, textureX, textureY); // Box 11
        bodyModel[8].addBox(0, 0, 0, 10, 2, 14, 0f);
        bodyModel[8].setRotationPoint(-29.0f, 3.0f, -7.0f);

        bodyModel[9] = new ModelRendererTurbo(this, 249, 1, textureX, textureY); // Box 12
        bodyModel[9].addShapeBox(0, 0, 0, 5, 20, 1, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        bodyModel[9].setRotationPoint(-24.0f, -15.0f, -9.9f);

        bodyModel[10] = new ModelRendererTurbo(this, 265, 1, textureX, textureY); // Box 14
        bodyModel[10].addShapeBox(0, 0, 0, 6, 2, 2, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0);
        bodyModel[10].setRotationPoint(-27.0f, 3.0f, 7.0f);

        bodyModel[11] = new ModelRendererTurbo(this, 289, 1, textureX, textureY); // Box 15
        bodyModel[11].addShapeBox(0, 0, 0, 6, 2, 2, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0);
        bodyModel[11].setRotationPoint(-27.0f, 3.0f, -9.0f);

        bodyModel[12] = new ModelRendererTurbo(this, 297, 1, textureX, textureY); // Box 16
        bodyModel[12].addBox(0, 0, 0, 10, 2, 14, 0f);
        bodyModel[12].setRotationPoint(13.0f, 3.0f, -7.0f);

        bodyModel[13] = new ModelRendererTurbo(this, 337, 1, textureX, textureY); // Box 17
        bodyModel[13].addShapeBox(0, 0, 0, 6, 2, 2, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0);
        bodyModel[13].setRotationPoint(15.0f, 3.0f, 7.0f);

        bodyModel[14] = new ModelRendererTurbo(this, 361, 1, textureX, textureY); // Box 18
        bodyModel[14].addShapeBox(0, 0, 0, 6, 2, 2, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0);
        bodyModel[14].setRotationPoint(15.0f, 3.0f, -9.0f);

        bodyModel[15] = new ModelRendererTurbo(this, 361, 1, textureX, textureY); // Box 22
        bodyModel[15].addBox(0, 0, 0, 12, 1, 18, 0f);
        bodyModel[15].setRotationPoint(23.0f, 3.0f, -9.0f);

        bodyModel[16] = new ModelRendererTurbo(this, 145, 25, textureX, textureY); // Box 23
        bodyModel[16].addShapeBox(0, 0, 0, 64, 1, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -0.3f, 0, 0, -0.3f, 0, 0, -0.3f, 0, 0, -0.3f);
        bodyModel[16].setRotationPoint(-29.0f, 5.0f, -10.0f);

        bodyModel[17] = new ModelRendererTurbo(this, 425, 1, textureX, textureY); // Box 26
        bodyModel[17].addShapeBox(0, 0, 0, 5, 20, 1, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        bodyModel[17].setRotationPoint(-24.0f, -15.0f, 8.9f);

        bodyModel[18] = new ModelRendererTurbo(this, 441, 1, textureX, textureY); // Box 27
        bodyModel[18].addShapeBox(0, 0, 0, 5, 20, 1, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        bodyModel[18].setRotationPoint(-29.0f, -15.0f, 8.9f);

        bodyModel[19] = new ModelRendererTurbo(this, 457, 1, textureX, textureY); // Box 28
        bodyModel[19].addShapeBox(0, 0, 0, 5, 20, 1, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        bodyModel[19].setRotationPoint(13.0f, -15.0f, 8.9f);

        bodyModel[20] = new ModelRendererTurbo(this, 473, 1, textureX, textureY); // Box 29
        bodyModel[20].addShapeBox(0, 0, 0, 5, 20, 1, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        bodyModel[20].setRotationPoint(18.0f, -15.0f, 8.9f);

        bodyModel[21] = new ModelRendererTurbo(this, 489, 1, textureX, textureY); // Box 30
        bodyModel[21].addShapeBox(0, 0, 0, 5, 20, 1, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        bodyModel[21].setRotationPoint(18.0f, -15.0f, -9.9f);

        bodyModel[22] = new ModelRendererTurbo(this, 345, 17, textureX, textureY); // Box 31
        bodyModel[22].addShapeBox(0, 0, 0, 5, 20, 1, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        bodyModel[22].setRotationPoint(13.0f, -15.0f, -9.9f);

        bodyModel[23] = new ModelRendererTurbo(this, 1, 25, textureX, textureY); // Box 32
        bodyModel[23].addShapeBox(0, 0, 0, 7, 3, 20, 0, 0.5f, 0, -2.75f, 0, 0, 0, 0, 0, 0, 0.5f, 0, -2.75f, 0.25f, 0, -2.5f, 0, 0, 0, 0, 0, 0, 0.25f, 0, -2.5f);
        bodyModel[23].setRotationPoint(-36.0f, -18.5f, -10.0f);

        bodyModel[24] = new ModelRendererTurbo(this, 361, 25, textureX, textureY); // Box 33
        bodyModel[24].addShapeBox(0, 0, 0, 64, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -3, 0, 0, -3);
        bodyModel[24].setRotationPoint(-29.0f, -18.5f, -10.0f);

        bodyModel[25] = new ModelRendererTurbo(this, 145, 33, textureX, textureY); // Box 34
        bodyModel[25].addShapeBox(0, 0, 0, 32, 20, 1, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        bodyModel[25].setRotationPoint(-19.0f, -15.0f, -10.0f);

        bodyModel[26] = new ModelRendererTurbo(this, 217, 33, textureX, textureY); // Box 36
        bodyModel[26].addShapeBox(0, 0, 0, 32, 20, 1, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        bodyModel[26].setRotationPoint(-19.0f, -15.0f, 9.0f);

        bodyModel[27] = new ModelRendererTurbo(this, 57, 25, textureX, textureY); // Box 37
        bodyModel[27].addShapeBox(0, 0, 0, 12, 20, 1, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        bodyModel[27].setRotationPoint(23.0f, -15.0f, 9.0f);

        bodyModel[28] = new ModelRendererTurbo(this, 289, 25, textureX, textureY); // Box 38
        bodyModel[28].addShapeBox(0, 0, 0, 12, 20, 1, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        bodyModel[28].setRotationPoint(23.0f, -15.0f, -10.0f);

        bodyModel[29] = new ModelRendererTurbo(this, 297, 41, textureX, textureY); // Box 40
        bodyModel[29].addShapeBox(0, 0, 0, 64, 1, 20, 0, 0, 0, -0.3f, 0, 0, -0.3f, 0, 0, -0.3f, 0, 0, -0.3f, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        bodyModel[29].setRotationPoint(-29.0f, -19.5f, -10.0f);

        bodyModel[30] = new ModelRendererTurbo(this, 65, 33, textureX, textureY); // Box 41
        bodyModel[30].addShapeBox(0, 0, 0, 7, 1, 20, 0, 0.2f, 0, -3, 0, 0, -0.3f, 0, 0, -0.3f, 0.2f, 0, -3, 0.5f, 0, -2.75f, 0, 0, 0, 0, 0, 0, 0.5f, 0, -2.75f);
        bodyModel[30].setRotationPoint(-36.0f, -19.5f, -10.0f);

        bodyModel[31] = new ModelRendererTurbo(this, 449, 33, textureX, textureY); // Box 44
        bodyModel[31].addShapeBox(0, 0, 0, 8, 1, 18, 0, 0, 0, -3, 0, 0, 0, 0, 0, 0, 0, 0, -3, -1, 0, -3, 0, 0, 0, 0, 0, 0, -1, 0, -3);
        bodyModel[31].setRotationPoint(-38.0f, 1.0f, -9.0f);

        bodyModel[32] = new ModelRendererTurbo(this, 89, 9, textureX, textureY); // Box 45
        bodyModel[32].addBox(0, 0, 0, 10, 2, 2, 0f);
        bodyModel[32].setRotationPoint(-40.0f, 3.5f, -1.0f);

        bodyModel[33] = new ModelRendererTurbo(this, 161, 1, textureX, textureY); // Box 46
        bodyModel[33].addShapeBox(0, 0, 0, 2, 1, 4, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        bodyModel[33].setRotationPoint(-41.25f, 2.5f, -2.0f);

        bodyModel[34] = new ModelRendererTurbo(this, 265, 9, textureX, textureY); // Box 47
        bodyModel[34].addBox(0, 0, 0, 3, 1, 7, 0f);
        bodyModel[34].setRotationPoint(-18.0f, -2.0f, 2.0f);

        bodyModel[35] = new ModelRendererTurbo(this, 1, 25, textureX, textureY); // Box 48
        bodyModel[35].addBox(0, 0, 0, 1, 4, 7, 0f);
        bodyModel[35].setRotationPoint(-18.5f, -6.0f, 2.0f);

        bodyModel[36] = new ModelRendererTurbo(this, 321, 25, textureX, textureY); // Box 49
        bodyModel[36].addBox(0, 0, 0, 3, 1, 7, 0f);
        bodyModel[36].setRotationPoint(-12.0f, -2.0f, 2.0f);

        bodyModel[37] = new ModelRendererTurbo(this, 449, 33, textureX, textureY); // Box 50
        bodyModel[37].addBox(0, 0, 0, 1, 4, 7, 0f);
        bodyModel[37].setRotationPoint(-9.0f, -6.0f, 2.0f);

        bodyModel[38] = new ModelRendererTurbo(this, 489, 33, textureX, textureY); // Box 51
        bodyModel[38].addBox(0, 0, 0, 3, 1, 7, 0f);
        bodyModel[38].setRotationPoint(-8.0f, -2.0f, 2.0f);

        bodyModel[39] = new ModelRendererTurbo(this, 105, 41, textureX, textureY); // Box 52
        bodyModel[39].addBox(0, 0, 0, 1, 4, 7, 0f);
        bodyModel[39].setRotationPoint(1.0f, -6.0f, 2.0f);

        bodyModel[40] = new ModelRendererTurbo(this, 1, 49, textureX, textureY); // Box 53
        bodyModel[40].addBox(0, 0, 0, 3, 1, 7, 0f);
        bodyModel[40].setRotationPoint(-2.0f, -2.0f, 2.0f);

        bodyModel[41] = new ModelRendererTurbo(this, 25, 49, textureX, textureY); // Box 54
        bodyModel[41].addBox(0, 0, 0, 3, 1, 7, 0f);
        bodyModel[41].setRotationPoint(2.0f, -2.0f, 2.0f);

        bodyModel[42] = new ModelRendererTurbo(this, 49, 49, textureX, textureY); // Box 55
        bodyModel[42].addBox(0, 0, 0, 3, 1, 7, 0f);
        bodyModel[42].setRotationPoint(9.0f, -2.0f, 2.0f);

        bodyModel[43] = new ModelRendererTurbo(this, 121, 49, textureX, textureY); // Box 56
        bodyModel[43].addBox(0, 0, 0, 1, 4, 7, 0f);
        bodyModel[43].setRotationPoint(11.5f, -6.0f, 2.0f);

        bodyModel[44] = new ModelRendererTurbo(this, 193, 1, textureX, textureY); // Box 57
        bodyModel[44].addBox(0, 0, 0, 1, 4, 4, 0f);
        bodyModel[44].setRotationPoint(11.5f, -6.0f, -9.0f);

        bodyModel[45] = new ModelRendererTurbo(this, 233, 1, textureX, textureY); // Box 58
        bodyModel[45].addBox(0, 0, 0, 1, 4, 4, 0f);
        bodyModel[45].setRotationPoint(1.0f, -6.0f, -9.0f);

        bodyModel[46] = new ModelRendererTurbo(this, 409, 1, textureX, textureY); // Box 59
        bodyModel[46].addBox(0, 0, 0, 1, 4, 4, 0f);
        bodyModel[46].setRotationPoint(-9.0f, -6.0f, -9.0f);

        bodyModel[47] = new ModelRendererTurbo(this, 129, 9, textureX, textureY); // Box 60
        bodyModel[47].addBox(0, 0, 0, 1, 4, 4, 0f);
        bodyModel[47].setRotationPoint(-18.5f, -6.0f, -9.0f);

        bodyModel[48] = new ModelRendererTurbo(this, 281, 9, textureX, textureY); // Box 61
        bodyModel[48].addBox(0, 0, 0, 3, 1, 4, 0f);
        bodyModel[48].setRotationPoint(-18.0f, -2.0f, -9.0f);

        bodyModel[49] = new ModelRendererTurbo(this, 337, 9, textureX, textureY); // Box 62
        bodyModel[49].addBox(0, 0, 0, 3, 1, 4, 0f);
        bodyModel[49].setRotationPoint(-12.0f, -2.0f, -9.0f);

        bodyModel[50] = new ModelRendererTurbo(this, 353, 9, textureX, textureY); // Box 63
        bodyModel[50].addBox(0, 0, 0, 3, 1, 4, 0f);
        bodyModel[50].setRotationPoint(-8.0f, -2.0f, -9.0f);

        bodyModel[51] = new ModelRendererTurbo(this, 41, 25, textureX, textureY); // Box 64
        bodyModel[51].addBox(0, 0, 0, 3, 1, 4, 0f);
        bodyModel[51].setRotationPoint(-2.0f, -2.0f, -9.0f);

        bodyModel[52] = new ModelRendererTurbo(this, 89, 25, textureX, textureY); // Box 65
        bodyModel[52].addBox(0, 0, 0, 3, 1, 4, 0f);
        bodyModel[52].setRotationPoint(2.0f, -2.0f, -9.0f);

        bodyModel[53] = new ModelRendererTurbo(this, 41, 33, textureX, textureY); // Box 66
        bodyModel[53].addBox(0, 0, 0, 3, 1, 4, 0f);
        bodyModel[53].setRotationPoint(9.0f, -2.0f, -9.0f);

        bodyModel[54] = new ModelRendererTurbo(this, 289, 49, textureX, textureY); // Box 67
        bodyModel[54].addBox(0, 0, 0, 3, 4, 5, 0f);
        bodyModel[54].setRotationPoint(-18.0f, -1.0f, 4.0f);

        bodyModel[55] = new ModelRendererTurbo(this, 65, 57, textureX, textureY); // Box 68
        bodyModel[55].addBox(0, 0, 0, 7, 4, 5, 0f);
        bodyModel[55].setRotationPoint(-12.0f, -1.0f, 4.0f);

        bodyModel[56] = new ModelRendererTurbo(this, 97, 57, textureX, textureY); // Box 69
        bodyModel[56].addBox(0, 0, 0, 7, 4, 5, 0f);
        bodyModel[56].setRotationPoint(-2.0f, -1.0f, 4.0f);

        bodyModel[57] = new ModelRendererTurbo(this, 137, 57, textureX, textureY); // Box 70
        bodyModel[57].addBox(0, 0, 0, 3, 4, 5, 0f);
        bodyModel[57].setRotationPoint(9.0f, -1.0f, 4.0f);

        bodyModel[58] = new ModelRendererTurbo(this, 361, 33, textureX, textureY); // Box 71
        bodyModel[58].addBox(0, 0, 0, 3, 4, 3, 0f);
        bodyModel[58].setRotationPoint(9.0f, -1.0f, -9.0f);

        bodyModel[59] = new ModelRendererTurbo(this, 377, 33, textureX, textureY); // Box 72
        bodyModel[59].addBox(0, 0, 0, 7, 4, 3, 0f);
        bodyModel[59].setRotationPoint(-2.0f, -1.0f, -9.0f);

        bodyModel[60] = new ModelRendererTurbo(this, 401, 33, textureX, textureY); // Box 73
        bodyModel[60].addBox(0, 0, 0, 7, 4, 3, 0f);
        bodyModel[60].setRotationPoint(-12.0f, -1.0f, -9.0f);

        bodyModel[61] = new ModelRendererTurbo(this, 425, 33, textureX, textureY); // Box 74
        bodyModel[61].addBox(0, 0, 0, 3, 4, 3, 0f);
        bodyModel[61].setRotationPoint(-18.0f, -1.0f, -9.0f);

        bodyModel[62] = new ModelRendererTurbo(this, 161, 57, textureX, textureY); // Box 80
        bodyModel[62].addBox(0, 0, 0, 8, 9, 3, 0f);
        bodyModel[62].setRotationPoint(25.0f, -6.0f, -9.0f);

        bodyModel[63] = new ModelRendererTurbo(this, 129, 41, textureX, textureY); // Box 81
        bodyModel[63].addBox(-1, 0, 0, 3, 4, 3, 0f);
        bodyModel[63].setRotationPoint(30.0f, -1.0f, 6.0f);

        bodyModel[64] = new ModelRendererTurbo(this, 441, 33, textureX, textureY); // Box 82
        bodyModel[64].addBox(0, 0, 0, 3, 1, 4, 0f);
        bodyModel[64].setRotationPoint(29.0f, -2.0f, 5.0f);

        bodyModel[65] = new ModelRendererTurbo(this, 185, 57, textureX, textureY); // Box 83
        bodyModel[65].addBox(0, 0, 0, 1, 4, 4, 0f);
        bodyModel[65].setRotationPoint(32.0f, -6.0f, 5.0f);

        bodyModel[66] = new ModelRendererTurbo(this, 201, 57, textureX, textureY); // Box 84
        bodyModel[66].addBox(0, 0, 0, 3, 4, 3, 0f);
        bodyModel[66].setRotationPoint(24.0f, -1.0f, 6.0f);

        bodyModel[67] = new ModelRendererTurbo(this, 17, 49, textureX, textureY); // Box 85
        bodyModel[67].addBox(0, 0, 0, 3, 1, 4, 0f);
        bodyModel[67].setRotationPoint(24.0f, -2.0f, 5.0f);

        bodyModel[68] = new ModelRendererTurbo(this, 217, 57, textureX, textureY); // Box 86
        bodyModel[68].addBox(0, 0, 0, 1, 4, 4, 0f);
        bodyModel[68].setRotationPoint(23.5f, -6.0f, 5.0f);

        bodyModel[69] = new ModelRendererTurbo(this, 233, 57, textureX, textureY); // Box 79
        bodyModel[69].addShapeBox(0, 0, 0, 9, 12, 1, 0, -4, 0, 0.5f, 0, 0, 2.28f, 0, 0, -2.45f, -4, 0, -1, -1, 0, -1, 0, 0, 2.2f, 0, 0, -2.45f, -1, 0, 0.5f);
        bodyModel[69].setRotationPoint(-40.0f, -15.5f, -7.0f);

        bodyModel[70] = new ModelRendererTurbo(this, 41, 49, textureX, textureY); // Box 80
        bodyModel[70].addBox(0, 0, 0, 3, 1, 4, 0f);
        bodyModel[70].setRotationPoint(-34.9f, -3.0f, -2.0f);

        bodyModel[71] = new ModelRendererTurbo(this, 257, 57, textureX, textureY); // Box 81
        bodyModel[71].addShapeBox(0, 0, 0, 9, 12, 1, 0, -4, 0, -1, 0, 0, -2.45f, 0, 0, 2.28f, -4, 0, 0.5f, -1, 0, 0.5f, 0, 0, -2.45f, 0, 0, 2.2f, -1, 0, -1);
        bodyModel[71].setRotationPoint(-40.0f, -15.5f, 6.0f);

        bodyModel[72] = new ModelRendererTurbo(this, 9, 57, textureX, textureY); // Box 82
        bodyModel[72].addShapeBox(0, 0, 0, 1, 12, 13, 0, -3, 0, 1, 3, 0, 1, 3, 0, 1, -3, 0, 1, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f);
        bodyModel[72].setRotationPoint(-39.0f, -15.5f, -6.5f);

        bodyModel[73] = new ModelRendererTurbo(this, 505, 1, textureX, textureY); // Box 86
        bodyModel[73].addShapeBox(0, 0, 0, 1, 18, 2, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        bodyModel[73].setRotationPoint(33.0f, -15.0f, -9.0f);

        bodyModel[74] = new ModelRendererTurbo(this, 505, 49, textureX, textureY); // Box 89
        bodyModel[74].addShapeBox(0, 0, 0, 1, 18, 2, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        bodyModel[74].setRotationPoint(33.0f, -15.0f, 7.0f);

        bodyModel[75] = new ModelRendererTurbo(this, 81, 57, textureX, textureY); // Box 90
        bodyModel[75].addBox(0, 0, 0, 1, 2, 12, 0f);
        bodyModel[75].setRotationPoint(33.0f, -18.5f, -6.0f);

        bodyModel[76] = new ModelRendererTurbo(this, 265, 9, textureX, textureY); // Box 91
        bodyModel[76].addShapeBox(0, 0, 0, 1, 3, 2, 0, 0, -2, 1, 0, -2, 1, 0, 0, -3, 0, 0, -3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        bodyModel[76].setRotationPoint(33.0f, -18.5f, 7.0f);

        bodyModel[77] = new ModelRendererTurbo(this, 297, 9, textureX, textureY); // Box 92
        bodyModel[77].addShapeBox(0, 0, 0, 1, 3, 2, 0, 0, 0, -3, 0, 0, -3, 0, -2, 1, 0, -2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        bodyModel[77].setRotationPoint(33.0f, -18.5f, -9.0f);

        bodyModel[78] = new ModelRendererTurbo(this, 281, 65, textureX, textureY); // Box 93
        bodyModel[78].addShapeBox(0, 0, 0, 64, 1, 1, 0, 0, 1, -1, 0, 1, -1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        bodyModel[78].setRotationPoint(-29.0f, -20.5f, -8.0f);

        bodyModel[79] = new ModelRendererTurbo(this, 41, 73, textureX, textureY); // Box 94
        bodyModel[79].addShapeBox(0, 0, 0, 64, 1, 1, 0, 0, 1, 1, 0, 1, 1, 0, 1, -1, 0, 1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        bodyModel[79].setRotationPoint(-29.0f, -20.5f, 7.0f);

        bodyModel[80] = new ModelRendererTurbo(this, 465, 57, textureX, textureY); // Box 95
        bodyModel[80].addBox(0, 0, 0, 7, 2, 8, 0f);
        bodyModel[80].setRotationPoint(-3.0f, -22.0f, -4.0f);

        bodyModel[81] = new ModelRendererTurbo(this, 409, 65, textureX, textureY); // Box 96
        bodyModel[81].addBox(0, 0, 0, 10, 3, 10, 0f);
        bodyModel[81].setRotationPoint(6.0f, -23.0f, -5.0f);

        bodyModel[82] = new ModelRendererTurbo(this, 41, 65, textureX, textureY); // Box 97
        bodyModel[82].addBox(0, 0, 0, 5, 1, 6, 0f);
        bodyModel[82].setRotationPoint(-34.0f, -20.0f, -3.0f);

        bodyModel[83] = new ModelRendererTurbo(this, 137, 9, textureX, textureY); // Box 98
        bodyModel[83].addBox(0, 0, 0, 4, 1, 1, 0f);
        bodyModel[83].setRotationPoint(-34.9f, -5.0f, -3.0f);

        bodyModel[84] = new ModelRendererTurbo(this, 193, 65, textureX, textureY); // Box 99
        bodyModel[84].addBox(0, 0, 0, 1, 5, 4, 0f);
        bodyModel[84].setRotationPoint(-31.9f, -8.0f, -2.0f);

        bodyModel[85] = new ModelRendererTurbo(this, 369, 9, textureX, textureY); // Box 100
        bodyModel[85].addBox(0, 0, 0, 1, 2, 3, 0f);
        bodyModel[85].setRotationPoint(-31.9f, -11.0f, -1.5f);

        bodyModel[86] = new ModelRendererTurbo(this, 161, 9, textureX, textureY); // Box 101
        bodyModel[86].addBox(0, 0, 0, 4, 1, 1, 0f);
        bodyModel[86].setRotationPoint(-34.9f, -5.0f, 2.0f);

        bodyModel[87] = new ModelRendererTurbo(this, 193, 65, textureX, textureY); // Box 102
        bodyModel[87].addShapeBox(0, 0, 0, 3, 1, 12, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        bodyModel[87].setRotationPoint(-38.0f, -5.0f, -6.0f);

        bodyModel[88] = new ModelRendererTurbo(this, 201, 1, textureX, textureY); // Box 131
        bodyModel[88].addShapeBox(0, 0, 0, 1, 1, 1, 0, -0.3f, 0, -1.5f, -0.3f, 0, -1.5f, -0.3f, -0.5f, 0.5f, -0.3f, -0.5f, 0.5f, -0.3f, 0, 0, -0.3f, 0, 0, -0.3f, 0, -0.5f, -0.3f, 0, -0.5f);
        bodyModel[88].setRotationPoint(32.0f, -7.0f, 5.0f);

        bodyModel[89] = new ModelRendererTurbo(this, 241, 1, textureX, textureY); // Box 135
        bodyModel[89].addShapeBox(0, 0, 0, 1, 1, 1, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0);
        bodyModel[89].setRotationPoint(-18.5f, -7.3f, -7.5f);

        bodyModel[90] = new ModelRendererTurbo(this, 417, 1, textureX, textureY); // Box 136
        bodyModel[90].addShapeBox(0, 0, 0, 1, 1, 1, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0);
        bodyModel[90].setRotationPoint(-9.0f, -7.3f, -7.5f);

        bodyModel[91] = new ModelRendererTurbo(this, 305, 9, textureX, textureY); // Box 137
        bodyModel[91].addShapeBox(0, 0, 0, 1, 1, 1, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0);
        bodyModel[91].setRotationPoint(1.0f, -7.3f, -7.5f);

        bodyModel[92] = new ModelRendererTurbo(this, 241, 17, textureX, textureY); // Box 138
        bodyModel[92].addShapeBox(0, 0, 0, 1, 1, 1, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0);
        bodyModel[92].setRotationPoint(11.5f, -7.3f, -7.5f);

        bodyModel[93] = new ModelRendererTurbo(this, 281, 57, textureX, textureY); // Box 139
        bodyModel[93].addShapeBox(0, 0, 0, 1, 1, 4, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0);
        bodyModel[93].setRotationPoint(11.5f, -7.3f, 3.5f);

        bodyModel[94] = new ModelRendererTurbo(this, 121, 65, textureX, textureY); // Box 140
        bodyModel[94].addShapeBox(0, 0, 0, 1, 1, 4, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0);
        bodyModel[94].setRotationPoint(1.0f, -7.3f, 3.5f);

        bodyModel[95] = new ModelRendererTurbo(this, 441, 65, textureX, textureY); // Box 141
        bodyModel[95].addShapeBox(0, 0, 0, 1, 1, 4, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0);
        bodyModel[95].setRotationPoint(-9.0f, -7.3f, 3.5f);

        bodyModel[96] = new ModelRendererTurbo(this, 457, 65, textureX, textureY); // Box 142
        bodyModel[96].addShapeBox(0, 0, 0, 1, 1, 4, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0);
        bodyModel[96].setRotationPoint(-18.5f, -7.3f, 3.5f);

        bodyModel[97] = new ModelRendererTurbo(this, 1, 25, textureX, textureY); // Box 143
        bodyModel[97].addShapeBox(0, 0, 0, 1, 1, 1, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0);
        bodyModel[97].setRotationPoint(23.5f, -7.3f, 6.5f);

        bodyModel[98] = new ModelRendererTurbo(this, 281, 25, textureX, textureY); // Box 144
        bodyModel[98].addShapeBox(0, 0, 0, 1, 1, 1, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0, 0, -0.2f, 0);
        bodyModel[98].setRotationPoint(32.0f, -7.3f, 6.5f);

        bodyModel[99] = new ModelRendererTurbo(this, 321, 25, textureX, textureY); // Box 145
        bodyModel[99].addShapeBox(0, 0, 0, 1, 1, 1, 0, -0.3f, -0.5f, 0.5f, -0.3f, -0.5f, 0.5f, -0.3f, 0, -1.5f, -0.3f, 0, -1.5f, -0.3f, 0, -0.5f, -0.3f, 0, -0.5f, -0.3f, 0, 0, -0.3f, 0, 0);
        bodyModel[99].setRotationPoint(32.0f, -7.0f, 8.0f);

        bodyModel[100] = new ModelRendererTurbo(this, 337, 25, textureX, textureY); // Box 146
        bodyModel[100].addShapeBox(0, 0, 0, 1, 1, 1, 0, -0.3f, 0, -1.5f, -0.3f, 0, -1.5f, -0.3f, -0.5f, 0.5f, -0.3f, -0.5f, 0.5f, -0.3f, 0, 0, -0.3f, 0, 0, -0.3f, 0, -0.5f, -0.3f, 0, -0.5f);
        bodyModel[100].setRotationPoint(23.5f, -7.0f, 5.0f);

        bodyModel[101] = new ModelRendererTurbo(this, 489, 33, textureX, textureY); // Box 147
        bodyModel[101].addShapeBox(0, 0, 0, 1, 1, 1, 0, -0.3f, -0.5f, 0.5f, -0.3f, -0.5f, 0.5f, -0.3f, 0, -1.5f, -0.3f, 0, -1.5f, -0.3f, 0, -0.5f, -0.3f, 0, -0.5f, -0.3f, 0, 0, -0.3f, 0, 0);
        bodyModel[101].setRotationPoint(23.5f, -7.0f, 8.0f);

        bodyModel[102] = new ModelRendererTurbo(this, 505, 33, textureX, textureY); // Box 148
        bodyModel[102].addShapeBox(0, 0, 0, 1, 1, 1, 0, -0.3f, -0.5f, 0.5f, -0.3f, -0.5f, 0.5f, -0.3f, 0, -1.5f, -0.3f, 0, -1.5f, -0.3f, 0, -0.5f, -0.3f, 0, -0.5f, -0.3f, 0, 0, -0.3f, 0, 0);
        bodyModel[102].setRotationPoint(11.5f, -7.0f, 8.0f);

        bodyModel[103] = new ModelRendererTurbo(this, 41, 41, textureX, textureY); // Box 149
        bodyModel[103].addShapeBox(0, 0, 0, 1, 1, 1, 0, -0.3f, 0, -1.5f, -0.3f, 0, -1.5f, -0.3f, -0.5f, 0.5f, -0.3f, -0.5f, 0.5f, -0.3f, 0, 0, -0.3f, 0, 0, -0.3f, 0, -0.5f, -0.3f, 0, -0.5f);
        bodyModel[103].setRotationPoint(11.5f, -7.0f, 2.0f);

        bodyModel[104] = new ModelRendererTurbo(this, 49, 41, textureX, textureY); // Box 150
        bodyModel[104].addShapeBox(0, 0, 0, 1, 1, 1, 0, -0.3f, 0, -1.5f, -0.3f, 0, -1.5f, -0.3f, -0.5f, 0.5f, -0.3f, -0.5f, 0.5f, -0.3f, 0, 0, -0.3f, 0, 0, -0.3f, 0, -0.5f, -0.3f, 0, -0.5f);
        bodyModel[104].setRotationPoint(1.0f, -7.0f, 2.0f);

        bodyModel[105] = new ModelRendererTurbo(this, 105, 41, textureX, textureY); // Box 151
        bodyModel[105].addShapeBox(0, 0, 0, 1, 1, 1, 0, -0.3f, -0.5f, 0.5f, -0.3f, -0.5f, 0.5f, -0.3f, 0, -1.5f, -0.3f, 0, -1.5f, -0.3f, 0, -0.5f, -0.3f, 0, -0.5f, -0.3f, 0, 0, -0.3f, 0, 0);
        bodyModel[105].setRotationPoint(1.0f, -7.0f, 8.0f);

        bodyModel[106] = new ModelRendererTurbo(this, 121, 41, textureX, textureY); // Box 152
        bodyModel[106].addShapeBox(0, 0, 0, 1, 1, 1, 0, -0.3f, -0.5f, 0.5f, -0.3f, -0.5f, 0.5f, -0.3f, 0, -1.5f, -0.3f, 0, -1.5f, -0.3f, 0, -0.5f, -0.3f, 0, -0.5f, -0.3f, 0, 0, -0.3f, 0, 0);
        bodyModel[106].setRotationPoint(-18.5f, -7.0f, 8.0f);

        bodyModel[107] = new ModelRendererTurbo(this, 1, 49, textureX, textureY); // Box 153
        bodyModel[107].addShapeBox(0, 0, 0, 1, 1, 1, 0, -0.3f, 0, -1.5f, -0.3f, 0, -1.5f, -0.3f, -0.5f, 0.5f, -0.3f, -0.5f, 0.5f, -0.3f, 0, 0, -0.3f, 0, 0, -0.3f, 0, -0.5f, -0.3f, 0, -0.5f);
        bodyModel[107].setRotationPoint(-18.5f, -7.0f, 2.0f);

        bodyModel[108] = new ModelRendererTurbo(this, 65, 49, textureX, textureY); // Box 154
        bodyModel[108].addShapeBox(0, 0, 0, 1, 1, 1, 0, -0.3f, 0, -1.5f, -0.3f, 0, -1.5f, -0.3f, -0.5f, 0.5f, -0.3f, -0.5f, 0.5f, -0.3f, 0, 0, -0.3f, 0, 0, -0.3f, 0, -0.5f, -0.3f, 0, -0.5f);
        bodyModel[108].setRotationPoint(-18.5f, -7.0f, -9.0f);

        bodyModel[109] = new ModelRendererTurbo(this, 73, 49, textureX, textureY); // Box 155
        bodyModel[109].addShapeBox(0, 0, 0, 1, 1, 1, 0, -0.3f, -0.5f, 0.5f, -0.3f, -0.5f, 0.5f, -0.3f, 0, -1.5f, -0.3f, 0, -1.5f, -0.3f, 0, -0.5f, -0.3f, 0, -0.5f, -0.3f, 0, 0, -0.3f, 0, 0);
        bodyModel[109].setRotationPoint(-18.5f, -7.0f, -6.0f);

        bodyModel[110] = new ModelRendererTurbo(this, 137, 49, textureX, textureY); // Box 156
        bodyModel[110].addShapeBox(0, 0, 0, 1, 1, 1, 0, -0.3f, -0.5f, 0.5f, -0.3f, -0.5f, 0.5f, -0.3f, 0, -1.5f, -0.3f, 0, -1.5f, -0.3f, 0, -0.5f, -0.3f, 0, -0.5f, -0.3f, 0, 0, -0.3f, 0, 0);
        bodyModel[110].setRotationPoint(-9.0f, -7.0f, -6.0f);

        bodyModel[111] = new ModelRendererTurbo(this, 289, 49, textureX, textureY); // Box 157
        bodyModel[111].addShapeBox(0, 0, 0, 1, 1, 1, 0, -0.3f, -0.5f, 0.5f, -0.3f, -0.5f, 0.5f, -0.3f, 0, -1.5f, -0.3f, 0, -1.5f, -0.3f, 0, -0.5f, -0.3f, 0, -0.5f, -0.3f, 0, 0, -0.3f, 0, 0);
        bodyModel[111].setRotationPoint(-9.0f, -7.0f, 8.0f);

        bodyModel[112] = new ModelRendererTurbo(this, 305, 49, textureX, textureY); // Box 158
        bodyModel[112].addShapeBox(0, 0, 0, 1, 1, 1, 0, -0.3f, 0, -1.5f, -0.3f, 0, -1.5f, -0.3f, -0.5f, 0.5f, -0.3f, -0.5f, 0.5f, -0.3f, 0, 0, -0.3f, 0, 0, -0.3f, 0, -0.5f, -0.3f, 0, -0.5f);
        bodyModel[112].setRotationPoint(-9.0f, -7.0f, 2.0f);

        bodyModel[113] = new ModelRendererTurbo(this, 97, 57, textureX, textureY); // Box 159
        bodyModel[113].addShapeBox(0, 0, 0, 1, 1, 1, 0, -0.3f, -0.5f, 0.5f, -0.3f, -0.5f, 0.5f, -0.3f, 0, -1.5f, -0.3f, 0, -1.5f, -0.3f, 0, -0.5f, -0.3f, 0, -0.5f, -0.3f, 0, 0, -0.3f, 0, 0);
        bodyModel[113].setRotationPoint(1.0f, -7.0f, -6.0f);

        bodyModel[114] = new ModelRendererTurbo(this, 153, 57, textureX, textureY); // Box 161
        bodyModel[114].addShapeBox(0, 0, 0, 1, 1, 1, 0, -0.3f, 0, -1.5f, -0.3f, 0, -1.5f, -0.3f, -0.5f, 0.5f, -0.3f, -0.5f, 0.5f, -0.3f, 0, 0, -0.3f, 0, 0, -0.3f, 0, -0.5f, -0.3f, 0, -0.5f);
        bodyModel[114].setRotationPoint(-9.0f, -7.0f, -9.0f);

        bodyModel[115] = new ModelRendererTurbo(this, 193, 57, textureX, textureY); // Box 162
        bodyModel[115].addShapeBox(0, 0, 0, 1, 1, 1, 0, -0.3f, 0, -1.5f, -0.3f, 0, -1.5f, -0.3f, -0.5f, 0.5f, -0.3f, -0.5f, 0.5f, -0.3f, 0, 0, -0.3f, 0, 0, -0.3f, 0, -0.5f, -0.3f, 0, -0.5f);
        bodyModel[115].setRotationPoint(1.0f, -7.0f, -9.0f);

        bodyModel[116] = new ModelRendererTurbo(this, 225, 57, textureX, textureY); // Box 163
        bodyModel[116].addShapeBox(0, 0, 0, 1, 1, 1, 0, -0.3f, 0, -1.5f, -0.3f, 0, -1.5f, -0.3f, -0.5f, 0.5f, -0.3f, -0.5f, 0.5f, -0.3f, 0, 0, -0.3f, 0, 0, -0.3f, 0, -0.5f, -0.3f, 0, -0.5f);
        bodyModel[116].setRotationPoint(11.5f, -7.0f, -9.0f);

        bodyModel[117] = new ModelRendererTurbo(this, 449, 57, textureX, textureY); // Box 164
        bodyModel[117].addShapeBox(0, 0, 0, 1, 1, 1, 0, -0.3f, -0.5f, 0.5f, -0.3f, -0.5f, 0.5f, -0.3f, 0, -1.5f, -0.3f, 0, -1.5f, -0.3f, 0, -0.5f, -0.3f, 0, -0.5f, -0.3f, 0, 0, -0.3f, 0, 0);
        bodyModel[117].setRotationPoint(11.5f, -7.0f, -6.0f);

        bodyModel[118] = new ModelRendererTurbo(this, 1, 65, textureX, textureY); // Box 165
        bodyModel[118].addShapeBox(0, 0, 0, 1, 13, 1, 0, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f);
        bodyModel[118].setRotationPoint(-18.5f, -18.4f, -5.75f);

        bodyModel[119] = new ModelRendererTurbo(this, 497, 65, textureX, textureY); // Box 166
        bodyModel[119].addShapeBox(0, 0, 0, 1, 13, 1, 0, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f);
        bodyModel[119].setRotationPoint(-9.0f, -18.4f, -5.75f);

        bodyModel[120] = new ModelRendererTurbo(this, 177, 73, textureX, textureY); // Box 167
        bodyModel[120].addShapeBox(0, 0, 0, 1, 13, 1, 0, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f);
        bodyModel[120].setRotationPoint(1.0f, -18.4f, -5.75f);

        bodyModel[121] = new ModelRendererTurbo(this, 185, 73, textureX, textureY); // Box 168
        bodyModel[121].addShapeBox(0, 0, 0, 1, 13, 1, 0, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f);
        bodyModel[121].setRotationPoint(11.5f, -18.0f, -5.75f);

        bodyModel[122] = new ModelRendererTurbo(this, 225, 73, textureX, textureY); // Box 169
        bodyModel[122].addShapeBox(0, 0, 0, 1, 13, 1, 0, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f);
        bodyModel[122].setRotationPoint(11.5f, -18.4f, 1.9f);

        bodyModel[123] = new ModelRendererTurbo(this, 233, 73, textureX, textureY); // Box 170
        bodyModel[123].addShapeBox(0, 0, 0, 1, 13, 1, 0, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f);
        bodyModel[123].setRotationPoint(1.0f, -18.4f, 1.9f);

        bodyModel[124] = new ModelRendererTurbo(this, 241, 73, textureX, textureY); // Box 171
        bodyModel[124].addShapeBox(0, 0, 0, 1, 13, 1, 0, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f);
        bodyModel[124].setRotationPoint(-9.0f, -18.4f, 1.9f);

        bodyModel[125] = new ModelRendererTurbo(this, 249, 73, textureX, textureY); // Box 172
        bodyModel[125].addShapeBox(0, 0, 0, 1, 13, 1, 0, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f);
        bodyModel[125].setRotationPoint(-18.5f, -18.4f, 1.9f);

        bodyModel[126] = new ModelRendererTurbo(this, 257, 73, textureX, textureY); // Box 173
        bodyModel[126].addShapeBox(0, 0, 0, 1, 13, 1, 0, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f);
        bodyModel[126].setRotationPoint(23.5f, -18.4f, 4.9f);

        bodyModel[127] = new ModelRendererTurbo(this, 265, 73, textureX, textureY); // Box 174
        bodyModel[127].addShapeBox(0, 0, 0, 1, 13, 1, 0, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f);
        bodyModel[127].setRotationPoint(32.0f, -18.4f, 4.9f);

        bodyModel[128] = new ModelRendererTurbo(this, 273, 73, textureX, textureY); // Box 175
        bodyModel[128].addShapeBox(0, 0, 0, 1, 22, 1, 0, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f);
        bodyModel[128].setRotationPoint(12.5f, -19.0f, 4.25f);

        bodyModel[129] = new ModelRendererTurbo(this, 281, 73, textureX, textureY); // Box 176
        bodyModel[129].addShapeBox(0, 0, 0, 1, 22, 1, 0, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f);
        bodyModel[129].setRotationPoint(22.5f, -18.0f, 4.25f);

        bodyModel[130] = new ModelRendererTurbo(this, 289, 73, textureX, textureY); // Box 177
        bodyModel[130].addShapeBox(0, 0, 0, 1, 22, 1, 0, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f);
        bodyModel[130].setRotationPoint(24.5f, -18.4f, -5.75f);

        bodyModel[131] = new ModelRendererTurbo(this, 297, 73, textureX, textureY); // Box 178
        bodyModel[131].addShapeBox(0, 0, 0, 1, 22, 1, 0, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f);
        bodyModel[131].setRotationPoint(12.5f, -18.4f, -5.75f);

        bodyModel[132] = new ModelRendererTurbo(this, 305, 73, textureX, textureY); // Box 179
        bodyModel[132].addShapeBox(0, 0, 0, 1, 22, 1, 0, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f);
        bodyModel[132].setRotationPoint(-24.5f, -18.0f, -5.75f);

        bodyModel[133] = new ModelRendererTurbo(this, 313, 73, textureX, textureY); // Box 180
        bodyModel[133].addShapeBox(0, 0, 0, 1, 23, 1, 0, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f, -0.3f, 0, -0.3f);
        bodyModel[133].setRotationPoint(-24.5f, -19.0f, 4.25f);

        bodyModel[134] = new ModelRendererTurbo(this, 9, 65, textureX, textureY); // Box 164
        bodyModel[134].addBox(0, 0, 0, 1, 1, 3, 0f);
        bodyModel[134].setRotationPoint(-31.9f, -9.0f, -1.5f);

        bodyModel[135] = new ModelRendererTurbo(this, 25, 65, textureX, textureY); // Box 165
        bodyModel[135].addShapeBox(0, 0, 0, 8, 3, 1, 0, 0, 0, -0.3f, 0, 0, 3, 0, 0, -3, 0, 0, -0.1f, 0, 0, -0.35f, 0, 0, 3, 0, 0, -3, 0, 0, -0.2f);
        bodyModel[135].setRotationPoint(-37.0f, 2.0f, -7.0f);

        bodyModel[136] = new ModelRendererTurbo(this, 321, 73, textureX, textureY); // Box 166
        bodyModel[136].addShapeBox(0, 0, 0, 8, 1, 1, 0, 0, 0, -0.37f, 0, 0, 3, 0, 0, -3, 0, 0, -0.2f, -0.5f, 0, -0.5f, 0, 0, 2.7f, 0, 0, -3, -0.5f, 0, -0.2f);
        bodyModel[136].setRotationPoint(-37.0f, 5.0f, -7.0f);

        bodyModel[137] = new ModelRendererTurbo(this, 409, 65, textureX, textureY); // Box 166
        bodyModel[137].addShapeBox(0, 0, 0, 1, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.5f, 0, 0, 1.5f, 0, 0, 0, 0, 0, 0, 0, 0, 1.5f, 0, 0, 1.5f);
        bodyModel[137].setRotationPoint(-39.0f, -0.25f, -2.3f);

        bodyModel[138] = new ModelRendererTurbo(this, 345, 73, textureX, textureY); // Box 167
        bodyModel[138].addShapeBox(0, 0, 0, 8, 3, 1, 0, 0, 0, -0.1f, 0, 0, -3, 0, 0, 3, 0, 0, -0.3f, 0, 0, -0.2f, 0, 0, -3, 0, 0, 3, 0, 0, -0.35f);
        bodyModel[138].setRotationPoint(-37.0f, 2.0f, 6.0f);

        bodyModel[139] = new ModelRendererTurbo(this, 369, 73, textureX, textureY); // Box 168
        bodyModel[139].addShapeBox(0, 0, 0, 8, 1, 1, 0, 0, 0, -0.2f, 0, 0, -3, 0, 0, 3, 0, 0, -0.37f, -0.5f, 0, -0.2f, 0, 0, -3, 0, 0, 2.7f, -0.5f, 0, -0.5f);
        bodyModel[139].setRotationPoint(-37.0f, 5.0f, 6.0f);

        bodyModel[140] = new ModelRendererTurbo(this, 321, 81, textureX, textureY); // Box 170
        bodyModel[140].addShapeBox(0, 0, 0, 64, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -3, 0, 0, -3, 0, 0, 0, 0, 0, 0);
        bodyModel[140].setRotationPoint(-29.0f, -18.5f, 6.0f);

        bodyModel[141] = new ModelRendererTurbo(this, 1, 89, textureX, textureY); // Box 161
        bodyModel[141].addShapeBox(0, 0, 0, 64, 1, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -0.3f, 0, 0, -0.3f, 0, 0, -0.3f, 0, 0, -0.3f);
        bodyModel[141].setRotationPoint(-29.0f, 5.0f, 6.0f);

        bodyModel[142] = new ModelRendererTurbo(this, 465, 73, textureX, textureY); // Box 162
        bodyModel[142].addBox(0, 0, 0, 1, 20, 1, 0f);
        bodyModel[142].setRotationPoint(12.0f, -17.0f, 8.0f);

        bodyModel[143] = new ModelRendererTurbo(this, 473, 73, textureX, textureY); // Box 163
        bodyModel[143].addBox(0, 0, 0, 1, 20, 1, 0f);
        bodyModel[143].setRotationPoint(23.0f, -17.0f, -9.0f);

        bodyModel[144] = new ModelRendererTurbo(this, 385, 73, textureX, textureY); // Box 165
        bodyModel[144].addShapeBox(0, 0, 0, 1, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.25f, 0, 0, 0.25f, 0, 0, 0, 0, 0, 0, 0, 0, 0.25f, 0, 0, 0.25f);
        bodyModel[144].setRotationPoint(-39.0f, -0.25f, -5.75f);

        bodyModel[145] = new ModelRendererTurbo(this, 401, 73, textureX, textureY); // Box 167
        bodyModel[145].addBox(0, 0, 0, 2, 3, 1, 0f);
        bodyModel[145].setRotationPoint(-41.5f, 3.0f, -3.0f);

        bodyModel[146] = new ModelRendererTurbo(this, 457, 73, textureX, textureY); // Box 168
        bodyModel[146].addBox(0, 0, 0, 2, 3, 1, 0f);
        bodyModel[146].setRotationPoint(-41.5f, 3.0f, 2.0f);

        bodyModel[147] = new ModelRendererTurbo(this, 1, 81, textureX, textureY); // Box 169
        bodyModel[147].addBox(0, 0, 0, 2, 2, 2, 0f);
        bodyModel[147].setRotationPoint(-42.0f, 3.25f, 0.0f);

        bodyModel[148] = new ModelRendererTurbo(this, 41, 81, textureX, textureY); // Box 170
        bodyModel[148].addBox(0, 0, 0, 2, 2, 2, 0f);
        bodyModel[148].setRotationPoint(-42.0f, 4.25f, -2.0f);

        bodyModel[149] = new ModelRendererTurbo(this, 305, 57, textureX, textureY); // Box 171
        bodyModel[149].addBox(0, 0, 0, 1, 1, 2, 0f);
        bodyModel[149].setRotationPoint(-41.0f, 3.25f, -2.0f);

        bodyModel[150] = new ModelRendererTurbo(this, 457, 57, textureX, textureY); // Box 172
        bodyModel[150].addBox(0, 0, 0, 1, 1, 2, 0f);
        bodyModel[150].setRotationPoint(-41.0f, 5.25f, 0.0f);

        bodyModel[151] = new ModelRendererTurbo(this, 145, 81, textureX, textureY); // Box 2
        bodyModel[151].addShapeBox(0, 0, 0, 1, 1, 12, 0, 0, 0, 2, 0, 0, 2, 0, 0, 2, 0, 0, 2, 0, 0, 2, 0, 0, 2, 0, 0, 2, 0, 0, 2);
        bodyModel[151].setRotationPoint(-17.0f, 6.0f, -6.0f);

        bodyModel[152] = new ModelRendererTurbo(this, 465, 57, textureX, textureY); // Box 5
        bodyModel[152].addShapeBox(0, 0, 0, 1, 1, 1, 0, -0.6f, 0.6f, -0.5f, 0.4f, 0.2f, -0.5f, 0.4f, 0.2f, 0, -0.6f, 0.6f, 0, -0.6f, -0.2f, -0.5f, 0.4f, 0.2f, -0.5f, 0.4f, 0.2f, 0, -0.6f, -0.2f, 0);
        bodyModel[152].setRotationPoint(-18.6f, 6.0f, -8.0f);

        bodyModel[153] = new ModelRendererTurbo(this, 489, 57, textureX, textureY); // Box 6
        bodyModel[153].addShapeBox(0, 0, 0, 1, 1, 1, 0, -0.6f, -0.2f, -0.5f, 0.4f, 0.2f, -0.5f, 0.4f, 0.2f, 0, -0.6f, -0.2f, 0, -0.6f, 0.6f, -0.5f, 0.4f, 0.2f, -0.5f, 0.4f, 0.2f, 0, -0.6f, 0.6f, 0);
        bodyModel[153].setRotationPoint(-16.4f, 5.6f, -8.0f);

        bodyModel[154] = new ModelRendererTurbo(this, 489, 73, textureX, textureY); // Box 7
        bodyModel[154].addShapeBox(0, 0, 0, 1, 2, 1, 0, -0.8f, 0, -0.5f, 0.2f, 0, -0.5f, 0.2f, 0, 0, -0.8f, 0, 0, -0.8f, 0, -0.5f, 0.2f, 0, -0.5f, 0.2f, 0, 0, -0.8f, 0, 0);
        bodyModel[154].setRotationPoint(-19.2f, 5.5f, -8.0f);

        bodyModel[155] = new ModelRendererTurbo(this, 505, 73, textureX, textureY); // Box 8
        bodyModel[155].addShapeBox(0, 0, 0, 1, 2, 1, 0, -0.8f, 0, -0.5f, 0.2f, 0, -0.5f, 0.2f, 0, 0, -0.8f, 0, 0, -0.8f, 0, -0.5f, 0.2f, 0, -0.5f, 0.2f, 0, 0, -0.8f, 0, 0);
        bodyModel[155].setRotationPoint(-15.8f, 5.5f, -8.0f);

        bodyModel[156] = new ModelRendererTurbo(this, 449, 65, textureX, textureY); // Box 9
        bodyModel[156].addShapeBox(0, 0, 0, 4, 1, 1, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, 0, 0, 0, 0, 0, -0.5f, -0.5f, 0, -0.5f, -0.5f, 0, -0.5f, 0, 0, -0.5f, 0);
        bodyModel[156].setRotationPoint(-18.5f, 7.5f, -8.0f);

        bodyModel[157] = new ModelRendererTurbo(this, 57, 81, textureX, textureY); // Box 15
        bodyModel[157].addShapeBox(0, 0, 0, 1, 2, 1, 0, -0.8f, 0, -0.5f, 0.2f, 0, -0.5f, 0.2f, 0, 0, -0.8f, 0, 0, -0.8f, 0, -0.5f, 0.2f, 0, -0.5f, 0.2f, 0, 0, -0.8f, 0, 0);
        bodyModel[157].setRotationPoint(-2.95f, 5.5f, -8.0f);

        bodyModel[158] = new ModelRendererTurbo(this, 65, 81, textureX, textureY); // Box 16
        bodyModel[158].addShapeBox(0, 0, 0, 4, 1, 1, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, 0, 0, 0, 0, 0, -0.5f, -0.5f, 0, -0.5f, -0.5f, 0, -0.5f, 0, 0, -0.5f, 0);
        bodyModel[158].setRotationPoint(-5.65f, 7.5f, -8.0f);

        bodyModel[159] = new ModelRendererTurbo(this, 17, 65, textureX, textureY); // Box 17
        bodyModel[159].addShapeBox(0, 0, 0, 1, 1, 1, 0, -0.6f, -0.2f, -0.5f, 0.4f, 0.2f, -0.5f, 0.4f, 0.2f, 0, -0.6f, -0.2f, 0, -0.6f, 0.6f, -0.5f, 0.4f, 0.2f, -0.5f, 0.4f, 0.2f, 0, -0.6f, 0.6f, 0);
        bodyModel[159].setRotationPoint(-3.55f, 5.6f, -8.0f);

        bodyModel[160] = new ModelRendererTurbo(this, 129, 65, textureX, textureY); // Box 18
        bodyModel[160].addShapeBox(0, 0, 0, 1, 1, 1, 0, 0.2f, 0.2f, 0, 0.2f, 0.2f, 0, 0.2f, 0.2f, 0, 0.2f, 0.2f, 0, 0.3f, 0.2f, 0, 0.3f, 0.2f, 0, 0.3f, 0.2f, 0, 0.3f, 0.2f, 0);
        bodyModel[160].setRotationPoint(-4.15f, 6.0f, -7.7f);

        bodyModel[161] = new ModelRendererTurbo(this, 185, 81, textureX, textureY); // Box 19
        bodyModel[161].addShapeBox(0, 0, 0, 1, 1, 12, 0, 0, 0, 2, 0, 0, 2, 0, 0, 2, 0, 0, 2, 0, 0, 2, 0, 0, 2, 0, 0, 2, 0, 0, 2);
        bodyModel[161].setRotationPoint(-4.15f, 6.0f, -6.0f);

        bodyModel[162] = new ModelRendererTurbo(this, 217, 73, textureX, textureY); // Box 20
        bodyModel[162].addShapeBox(0, 0, 0, 1, 1, 1, 0, -0.6f, 0.6f, -0.5f, 0.4f, 0.2f, -0.5f, 0.4f, 0.2f, 0, -0.6f, 0.6f, 0, -0.6f, -0.2f, -0.5f, 0.4f, 0.2f, -0.5f, 0.4f, 0.2f, 0, -0.6f, -0.2f, 0);
        bodyModel[162].setRotationPoint(-5.75f, 6.0f, -8.0f);

        bodyModel[163] = new ModelRendererTurbo(this, 81, 81, textureX, textureY); // Box 21
        bodyModel[163].addShapeBox(0, 0, 0, 1, 2, 1, 0, -0.8f, 0, -0.5f, 0.2f, 0, -0.5f, 0.2f, 0, 0, -0.8f, 0, 0, -0.8f, 0, -0.5f, 0.2f, 0, -0.5f, 0.2f, 0, 0, -0.8f, 0, 0);
        bodyModel[163].setRotationPoint(-6.35f, 5.5f, -8.0f);

        bodyModel[164] = new ModelRendererTurbo(this, 89, 81, textureX, textureY); // Box 23
        bodyModel[164].addBox(-2.5f, -2.5f, 0, 5, 5, 0, 0f);
        bodyModel[164].setRotationPoint(-16.5f, 6.5f, 5.0f);

        bodyModel[165] = new ModelRendererTurbo(this, 105, 81, textureX, textureY); // Box 24
        bodyModel[165].addShapeBox(0, 0, 0, 1, 2, 1, 0, -0.8f, 0, -0.5f, 0.2f, 0, -0.5f, 0.2f, 0, 0, -0.8f, 0, 0, -0.8f, 0, -0.5f, 0.2f, 0, -0.5f, 0.2f, 0, 0, -0.8f, 0, 0);
        bodyModel[165].setRotationPoint(-15.8f, 5.5f, 6.5f);

        bodyModel[166] = new ModelRendererTurbo(this, 393, 73, textureX, textureY); // Box 25
        bodyModel[166].addShapeBox(0, 0, 0, 1, 1, 1, 0, -0.6f, -0.2f, -0.5f, 0.4f, 0.2f, -0.5f, 0.4f, 0.2f, 0, -0.6f, -0.2f, 0, -0.6f, 0.6f, -0.5f, 0.4f, 0.2f, -0.5f, 0.4f, 0.2f, 0, -0.6f, 0.6f, 0);
        bodyModel[166].setRotationPoint(-16.4f, 5.6f, 6.5f);

        bodyModel[167] = new ModelRendererTurbo(this, 113, 81, textureX, textureY); // Box 27
        bodyModel[167].addShapeBox(0, 0, 0, 1, 1, 1, 0, -0.6f, 0.6f, -0.5f, 0.4f, 0.2f, -0.5f, 0.4f, 0.2f, 0, -0.6f, 0.6f, 0, -0.6f, -0.2f, -0.5f, 0.4f, 0.2f, -0.5f, 0.4f, 0.2f, 0, -0.6f, -0.2f, 0);
        bodyModel[167].setRotationPoint(-18.6f, 6.0f, 6.5f);

        bodyModel[168] = new ModelRendererTurbo(this, 121, 81, textureX, textureY); // Box 28
        bodyModel[168].addShapeBox(0, 0, 0, 1, 2, 1, 0, -0.8f, 0, -0.5f, 0.2f, 0, -0.5f, 0.2f, 0, 0, -0.8f, 0, 0, -0.8f, 0, -0.5f, 0.2f, 0, -0.5f, 0.2f, 0, 0, -0.8f, 0, 0);
        bodyModel[168].setRotationPoint(-19.2f, 5.5f, 6.5f);

        bodyModel[169] = new ModelRendererTurbo(this, 129, 81, textureX, textureY); // Box 29
        bodyModel[169].addShapeBox(0, 0, 0, 4, 1, 1, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, 0, 0, 0, 0, 0, -0.5f, -0.5f, 0, -0.5f, -0.5f, 0, -0.5f, 0, 0, -0.5f, 0);
        bodyModel[169].setRotationPoint(-18.5f, 7.5f, 6.5f);

        bodyModel[170] = new ModelRendererTurbo(this, 161, 81, textureX, textureY); // Box 32
        bodyModel[170].addShapeBox(0, 0, 0, 4, 1, 2, 0, -0.6f, 2.1f, 0, 0.4f, 2.1f, 0, 0.4f, 2.1f, 0, -0.6f, 2.1f, 0, -0.6f, -0.2f, 0, 0.4f, -0.2f, 0, 0.4f, -0.2f, 0, -0.6f, -0.2f, 0);
        bodyModel[170].setRotationPoint(-12.6f, 6.9f, 6.5f);

        bodyModel[171] = new ModelRendererTurbo(this, 145, 81, textureX, textureY); // Box 35
        bodyModel[171].addShapeBox(0, 0, 0, 1, 2, 1, 0, -0.8f, 0, -0.5f, 0.2f, 0, -0.5f, 0.2f, 0, 0, -0.8f, 0, 0, -0.8f, 0, -0.5f, 0.2f, 0, -0.5f, 0.2f, 0, 0, -0.8f, 0, 0);
        bodyModel[171].setRotationPoint(-6.35f, 5.5f, 6.5f);

        bodyModel[172] = new ModelRendererTurbo(this, 201, 81, textureX, textureY); // Box 36
        bodyModel[172].addShapeBox(0, 0, 0, 4, 1, 1, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, 0, 0, 0, 0, 0, -0.5f, -0.5f, 0, -0.5f, -0.5f, 0, -0.5f, 0, 0, -0.5f, 0);
        bodyModel[172].setRotationPoint(-5.65f, 7.5f, 6.5f);

        bodyModel[173] = new ModelRendererTurbo(this, 217, 81, textureX, textureY); // Box 38
        bodyModel[173].addShapeBox(0, 0, 0, 1, 1, 1, 0, -0.6f, 0.6f, -0.5f, 0.4f, 0.2f, -0.5f, 0.4f, 0.2f, 0, -0.6f, 0.6f, 0, -0.6f, -0.2f, -0.5f, 0.4f, 0.2f, -0.5f, 0.4f, 0.2f, 0, -0.6f, -0.2f, 0);
        bodyModel[173].setRotationPoint(-5.75f, 6.0f, 6.5f);

        bodyModel[174] = new ModelRendererTurbo(this, 457, 81, textureX, textureY); // Box 39
        bodyModel[174].addShapeBox(0, 0, 0, 1, 1, 1, 0, -0.6f, -0.2f, -0.5f, 0.4f, 0.2f, -0.5f, 0.4f, 0.2f, 0, -0.6f, -0.2f, 0, -0.6f, 0.6f, -0.5f, 0.4f, 0.2f, -0.5f, 0.4f, 0.2f, 0, -0.6f, 0.6f, 0);
        bodyModel[174].setRotationPoint(-3.55f, 5.6f, 6.5f);

        bodyModel[175] = new ModelRendererTurbo(this, 489, 81, textureX, textureY); // Box 40
        bodyModel[175].addShapeBox(0, 0, 0, 1, 2, 1, 0, -0.8f, 0, -0.5f, 0.2f, 0, -0.5f, 0.2f, 0, 0, -0.8f, 0, 0, -0.8f, 0, -0.5f, 0.2f, 0, -0.5f, 0.2f, 0, 0, -0.8f, 0, 0);
        bodyModel[175].setRotationPoint(-2.95f, 5.5f, 6.5f);

        bodyModel[176] = new ModelRendererTurbo(this, 489, 81, textureX, textureY); // Box 44
        bodyModel[176].addBox(0, 0, 0, 3, 3, 8, 0f);
        bodyModel[176].setRotationPoint(-18.0f, 5.0f, -4.0f);

        bodyModel[177] = new ModelRendererTurbo(this, 169, 89, textureX, textureY); // Box 45
        bodyModel[177].addBox(0, 0, 0, 3, 3, 6, 0f);
        bodyModel[177].setRotationPoint(-5.0f, 5.0f, -3.0f);

        bodyModel[178] = new ModelRendererTurbo(this, 217, 89, textureX, textureY); // Box 46
        bodyModel[178].addBox(0, 0, 0, 8, 3, 4, 0f);
        bodyModel[178].setRotationPoint(-12.0f, 5.0f, -2.0f);

        bodyModel[179] = new ModelRendererTurbo(this, 137, 89, textureX, textureY); // Box 47
        bodyModel[179].addBox(0, 0, 0, 5, 2, 1, 0f);
        bodyModel[179].setRotationPoint(-12.5f, 6.7f, -5.0f);

        bodyModel[180] = new ModelRendererTurbo(this, 161, 89, textureX, textureY); // Box 48
        bodyModel[180].addBox(0, 0, 0, 5, 2, 1, 0f);
        bodyModel[180].setRotationPoint(-12.5f, 6.7f, 4.0f);

        bodyModel[181] = new ModelRendererTurbo(this, 241, 89, textureX, textureY); // Box 49
        bodyModel[181].addShapeBox(0, 0, 0, 1, 1, 8, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1);
        bodyModel[181].setRotationPoint(-9.0f, 5.5f, -4.0f);

        bodyModel[182] = new ModelRendererTurbo(this, 321, 89, textureX, textureY); // Box 50
        bodyModel[182].addShapeBox(0, 0, 0, 1, 1, 8, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1);
        bodyModel[182].setRotationPoint(-12.0f, 5.5f, -4.0f);

        bodyModel[183] = new ModelRendererTurbo(this, 337, 89, textureX, textureY); // Box 54
        bodyModel[183].addBox(0, 0, 0, 8, 3, 4, 0f);
        bodyModel[183].setRotationPoint(-16.0f, 5.0f, -2.0f);

        bodyModel[184] = new ModelRendererTurbo(this, 201, 89, textureX, textureY); // Box 55
        bodyModel[184].addShapeBox(0, 0, 0, 4, 1, 2, 0, -0.6f, 2.1f, 0, 0.4f, 2.1f, 0, 0.4f, 2.1f, 0, -0.6f, 2.1f, 0, -0.6f, -0.2f, 0, 0.4f, -0.2f, 0, 0.4f, -0.2f, 0, -0.6f, -0.2f, 0);
        bodyModel[184].setRotationPoint(-12.6f, 6.9f, -8.5f);

        bodyModel[185] = new ModelRendererTurbo(this, 505, 81, textureX, textureY); // Box 60
        bodyModel[185].addShapeBox(0, 0, 0, 1, 1, 1, 0, 0.2f, 0.2f, 0, 0.2f, 0.2f, 0, 0.2f, 0.2f, 0, 0.2f, 0.2f, 0, 0.3f, 0.2f, 0, 0.3f, 0.2f, 0, 0.3f, 0.2f, 0, 0.3f, 0.2f, 0);
        bodyModel[185].setRotationPoint(-17.0f, 6.0f, -7.7f);

        bodyModel[186] = new ModelRendererTurbo(this, 185, 89, textureX, textureY); // Box 61
        bodyModel[186].addShapeBox(0, 0, 0, 1, 1, 1, 0, 0.2f, 0.2f, 0, 0.2f, 0.2f, 0, 0.2f, 0.2f, 0, 0.2f, 0.2f, 0, 0.3f, 0.2f, 0, 0.3f, 0.2f, 0, 0.3f, 0.2f, 0, 0.3f, 0.2f, 0);
        bodyModel[186].setRotationPoint(-4.15f, 6.0f, 6.7f);

        bodyModel[187] = new ModelRendererTurbo(this, 241, 89, textureX, textureY); // Box 62
        bodyModel[187].addShapeBox(0, 0, 0, 1, 1, 1, 0, 0.2f, 0.2f, 0, 0.2f, 0.2f, 0, 0.2f, 0.2f, 0, 0.2f, 0.2f, 0, 0.3f, 0.2f, 0, 0.3f, 0.2f, 0, 0.3f, 0.2f, 0, 0.3f, 0.2f, 0);
        bodyModel[187].setRotationPoint(-17.0f, 6.0f, 6.7f);

        bodyModel[188] = new ModelRendererTurbo(this, 257, 89, textureX, textureY); // Box 55
        bodyModel[188].addBox(-2.5f, -2.5f, 0, 5, 5, 0, 0f);
        bodyModel[188].setRotationPoint(-16.5f, 6.5f, -5.0f);

        bodyModel[189] = new ModelRendererTurbo(this, 369, 89, textureX, textureY); // Box 56
        bodyModel[189].addBox(-2.5f, -2.5f, 0, 5, 5, 0, 0f);
        bodyModel[189].setRotationPoint(-3.75f, 6.5f, -5.0f);

        bodyModel[190] = new ModelRendererTurbo(this, 385, 89, textureX, textureY); // Box 57
        bodyModel[190].addBox(-2.5f, -2.5f, 0, 5, 5, 0, 0f);
        bodyModel[190].setRotationPoint(-3.75f, 6.5f, 5.0f);

        bodyModel[191] = new ModelRendererTurbo(this, 153, 65, textureX, textureY); // Box 30
        bodyModel[191].addShapeBox(0, 0, 0, 1, 1, 2, 0, -0.6f, 2.1f, 0, 1.4f, 2.1f, 0, 1.4f, 2.1f, 0, -0.6f, 2.1f, 0, -0.6f, -0.2f, 0, 1.4f, -0.2f, 0, 1.4f, -0.2f, 0, -0.6f, -0.2f, 0);
        bodyModel[191].setRotationPoint(-15.2f, 6.5f, 6.5f);

        bodyModel[192] = new ModelRendererTurbo(this, 321, 89, textureX, textureY); // Box 31
        bodyModel[192].addShapeBox(0, 0, 0, 1, 1, 2, 0, -0.6f, 2.1f, 0, 0.4f, 1.7f, 0, 0.4f, 1.7f, 0, -0.6f, 2.1f, 0, -0.6f, -0.2f, 0, 0.4f, 0.2f, 0, 0.4f, 0.2f, 0, -0.6f, -0.2f, 0);
        bodyModel[192].setRotationPoint(-13.4f, 6.5f, 6.5f);

        bodyModel[193] = new ModelRendererTurbo(this, 361, 89, textureX, textureY); // Box 33
        bodyModel[193].addShapeBox(0, 0, 0, 1, 1, 2, 0, 0.4f, 1.7f, 0, -0.6f, 2.1f, 0, -0.6f, 2.1f, 0, 0.4f, 1.7f, 0, 0.4f, 0.2f, 0, -0.6f, -0.2f, 0, -0.6f, -0.2f, 0, 0.4f, 0.2f, 0);
        bodyModel[193].setRotationPoint(-7.8f, 6.5f, 6.5f);

        bodyModel[194] = new ModelRendererTurbo(this, 401, 89, textureX, textureY); // Box 34
        bodyModel[194].addShapeBox(0, 0, 0, 1, 1, 2, 0, -0.6f, 2.1f, 0, 1.45f, 2.1f, 0, 1.45f, 2.1f, 0, -0.6f, 2.1f, 0, -0.6f, -0.2f, 0, 1.45f, -0.2f, 0, 1.45f, -0.2f, 0, -0.6f, -0.2f, 0);
        bodyModel[194].setRotationPoint(-8.0f, 6.5f, 6.5f);

        bodyModel[195] = new ModelRendererTurbo(this, 409, 89, textureX, textureY); // Box 326
        bodyModel[195].addShapeBox(0, 0, 0, 1, 1, 2, 0, -0.6f, 2.1f, 0, 1.4f, 2.1f, 0, 1.4f, 2.1f, 0, -0.6f, 2.1f, 0, -0.6f, -0.2f, 0, 1.4f, -0.2f, 0, 1.4f, -0.2f, 0, -0.6f, -0.2f, 0);
        bodyModel[195].setRotationPoint(-15.2f, 6.5f, -8.5f);

        bodyModel[196] = new ModelRendererTurbo(this, 417, 89, textureX, textureY); // Box 327
        bodyModel[196].addShapeBox(0, 0, 0, 1, 1, 2, 0, -0.6f, 2.1f, 0, 0.4f, 1.7f, 0, 0.4f, 1.7f, 0, -0.6f, 2.1f, 0, -0.6f, -0.2f, 0, 0.4f, 0.2f, 0, 0.4f, 0.2f, 0, -0.6f, -0.2f, 0);
        bodyModel[196].setRotationPoint(-13.4f, 6.5f, -8.5f);

        bodyModel[197] = new ModelRendererTurbo(this, 425, 89, textureX, textureY); // Box 328
        bodyModel[197].addShapeBox(0, 0, 0, 1, 1, 2, 0, 0.4f, 1.7f, 0, -0.6f, 2.1f, 0, -0.6f, 2.1f, 0, 0.4f, 1.7f, 0, 0.4f, 0.2f, 0, -0.6f, -0.2f, 0, -0.6f, -0.2f, 0, 0.4f, 0.2f, 0);
        bodyModel[197].setRotationPoint(-7.8f, 6.5f, -8.5f);

        bodyModel[198] = new ModelRendererTurbo(this, 433, 89, textureX, textureY); // Box 329
        bodyModel[198].addShapeBox(0, 0, 0, 1, 1, 2, 0, -0.6f, 2.1f, 0, 1.45f, 2.1f, 0, 1.45f, 2.1f, 0, -0.6f, 2.1f, 0, -0.6f, -0.2f, 0, 1.45f, -0.2f, 0, 1.45f, -0.2f, 0, -0.6f, -0.2f, 0);
        bodyModel[198].setRotationPoint(-8.0f, 6.5f, -8.5f);

        bodyModel[199] = new ModelRendererTurbo(this, 1, 97, textureX, textureY); // Box 221
        bodyModel[199].addBox(0, 0, 0, 10, 3, 12, 0f);
        bodyModel[199].setRotationPoint(0.0f, 5.5f, -6.0f);

        bodyModel[200] = new ModelRendererTurbo(this, 49, 97, textureX, textureY); // Box 222
        bodyModel[200].addBox(0, 0, 0, 10, 2, 12, 0f);
        bodyModel[200].setRotationPoint(10.0f, 5.5f, -6.0f);

        bodyModel[201] = new ModelRendererTurbo(this, 185, 105, textureX, textureY); // Box 271
        bodyModel[201].addShapeBox(0, 0, 0, 1, 2, 1, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, -0.75f, 0.25f, 0, -0.75f, 0.25f, 0, 0, 0, 0, 0, 0, 0, 0, 0.25f, 0, 0, 0.25f);
        bodyModel[201].setRotationPoint(-39.1f, -0.5f, -2.1f);

        bodyModel[202] = new ModelRendererTurbo(this, 193, 105, textureX, textureY); // Box 272
        bodyModel[202].addShapeBox(0, 0, 0, 1, 2, 1, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, -0.75f, 0.25f, 0, -0.75f, 0.25f, 0, 0, 0, 0, 0, 0, 0, 0, 0.25f, 0, 0, 0.25f);
        bodyModel[202].setRotationPoint(-39.1f, -0.5f, 0.7f);

        bodyModel[203] = new ModelRendererTurbo(this, 201, 105, textureX, textureY); // Box 273
        bodyModel[203].addShapeBox(0, 0, 0, 1, 2, 1, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        bodyModel[203].setRotationPoint(-39.1f, -0.5f, -0.6f);

        bodyModel[204] = new ModelRendererTurbo(this, 209, 105, textureX, textureY); // Box 274
        bodyModel[204].addShapeBox(0, 0, 0, 1, 2, 1, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, -0.75f, 0.25f, 0, -0.75f, 0.25f, 0, 0, 0, 0, 0, 0, 0, 0, 0.25f, 0, 0, 0.25f);
        bodyModel[204].setRotationPoint(-39.1f, -0.5f, -4.0f);

        bodyModel[205] = new ModelRendererTurbo(this, 217, 105, textureX, textureY); // Box 275
        bodyModel[205].addShapeBox(0, 0, 0, 1, 2, 1, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, -0.75f, 0.25f, 0, -0.75f, 0.25f, 0, 0, 0, 0, 0, 0, 0, 0, 0.25f, 0, 0, 0.25f);
        bodyModel[205].setRotationPoint(-39.1f, -0.5f, -5.5f);

        bodyModel[206] = new ModelRendererTurbo(this, 209, 81, textureX, textureY); // Box 276
        bodyModel[206].addShapeBox(0, 0, 0, 1, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.25f, 0, 0, 0.25f, 0, 0, 0, 0, 0, 0, 0, 0, 0.25f, 0, 0, 0.25f);
        bodyModel[206].setRotationPoint(-39.0f, -0.25f, 2.5f);

        bodyModel[207] = new ModelRendererTurbo(this, 225, 105, textureX, textureY); // Box 277
        bodyModel[207].addShapeBox(0, 0, 0, 1, 2, 1, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, -0.75f, 0.25f, 0, -0.75f, 0.25f, 0, 0, 0, 0, 0, 0, 0, 0, 0.25f, 0, 0, 0.25f);
        bodyModel[207].setRotationPoint(-39.1f, -0.5f, 4.25f);

        bodyModel[208] = new ModelRendererTurbo(this, 233, 105, textureX, textureY); // Box 278
        bodyModel[208].addShapeBox(0, 0, 0, 1, 2, 1, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, -0.75f, 0.25f, 0, -0.75f, 0.25f, 0, 0, 0, 0, 0, 0, 0, 0, 0.25f, 0, 0, 0.25f);
        bodyModel[208].setRotationPoint(-39.1f, -0.5f, 2.75f);

        bodyModel[209] = new ModelRendererTurbo(this, 161, 113, textureX, textureY); // Box 307
        bodyModel[209].addBox(0, 0, 0, 1, 4, 2, 0f);
        bodyModel[209].setRotationPoint(-36.5f, 2.0f, -5.0f);

        bodyModel[210] = new ModelRendererTurbo(this, 161, 113, textureX, textureY); // Box 308
        bodyModel[210].addBox(0, 0, 0, 1, 1, 14, 0f);
        bodyModel[210].setRotationPoint(-35.5f, 2.0f, -7.0f);

        bodyModel[211] = new ModelRendererTurbo(this, 177, 113, textureX, textureY); // Box 309
        bodyModel[211].addBox(0, 0, 0, 2, 2, 16, 0f);
        bodyModel[211].setRotationPoint(-32.5f, 2.0f, -8.0f);

        bodyModel[212] = new ModelRendererTurbo(this, 281, 1, textureX, textureY); // Box 478
        bodyModel[212].addShapeBox(0, 0, 0, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0.5f, 0, 0, -0.5f, 0, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0.5f, 0, -0.5f, -0.5f, 0, -0.5f, 0);
        bodyModel[212].setRotationPoint(-38.0f, 6.75f, -2.4f);

        bodyModel[213] = new ModelRendererTurbo(this, 161, 1, textureX, textureY); // Box 479
        bodyModel[213].addShapeBox(0, 0, 0, 1, 1, 0, 0, -0.5f, 0.25f, 0, 0, 0, 0, 0, 0, 0, -0.5f, 0.25f, 0, 1, 0, -0.5f, -2, 0.5f, -0.5f, -2, 0.5f, 0.5f, 1, 0, 0.5f);
        bodyModel[213].setRotationPoint(-35.0f, 5.75f, -3.4f);

        bodyModel[214] = new ModelRendererTurbo(this, 193, 1, textureX, textureY); // Box 480
        bodyModel[214].addShapeBox(0, 0, 0, 1, 1, 0, 0, 0, 0, 0, -0.5f, 0.25f, 0, -0.5f, 0.25f, 0, 0, 0, 0, -2, 0.5f, 0.5f, 1, 0, 0.5f, 1, 0, -0.5f, -2, 0.5f, -0.5f);
        bodyModel[214].setRotationPoint(-40.0f, 5.75f, -1.9f);

        bodyModel[215] = new ModelRendererTurbo(this, 305, 1, textureX, textureY); // Box 486
        bodyModel[215].addShapeBox(0, 0, 0, 2, 1, 0, 0, 0, 0, 0, 0, 0, -0.5f, 0, 0, 0.5f, 0, 0, 0, 0, -0.5f, 0, 0, -0.5f, -0.5f, 0, -0.5f, 0.5f, 0, -0.5f, 0);
        bodyModel[215].setRotationPoint(-38.0f, 6.75f, 2.5f);

        bodyModel[216] = new ModelRendererTurbo(this, 233, 1, textureX, textureY); // Box 487
        bodyModel[216].addShapeBox(0, 0, 0, 1, 1, 0, 0, 0, 0, 0, -0.5f, 0.25f, 0, -0.5f, 0.25f, 0, 0, 0, 0, -2, 0.5f, -0.5f, 1, 0, -0.5f, 1, 0, 0.5f, -2, 0.5f, 0.5f);
        bodyModel[216].setRotationPoint(-40.0f, 5.75f, 2.0f);

        bodyModel[217] = new ModelRendererTurbo(this, 353, 1, textureX, textureY); // Box 488
        bodyModel[217].addShapeBox(0, 0, 0, 1, 1, 0, 0, -0.5f, 0.25f, 0, 0, 0, 0, 0, 0, 0, -0.5f, 0.25f, 0, 1, 0, 0.5f, -2, 0.5f, 0.5f, -2, 0.5f, -0.5f, 1, 0, -0.5f);
        bodyModel[217].setRotationPoint(-35.0f, 5.75f, 3.5f);

        bodyModel[218] = new ModelRendererTurbo(this, 201, 113, textureX, textureY); // Box 316
        bodyModel[218].addBox(0, 0, 0, 2, 2, 8, 0f);
        bodyModel[218].setRotationPoint(-34.5f, 3.5f, -4.0f);

        bodyModel[219] = new ModelRendererTurbo(this, 395, 109, textureX, textureY); // Box 181
        bodyModel[219].addShapeBox(0, 0, 0, 1, 9, 33, 0, 0.4f, -0.25f, -23, 0, -0.25f, -23, 0, -0.25f, 0, 0.4f, -0.25f, 0, 0.25f, -6.5f, -23, 0, -6.5f, -23, 0, -6.5f, 0, 0.25f, -6.5f, 0);
        bodyModel[219].setRotationPoint(-36.1f, -18.6f, -28.0f);

        bodyModel[220] = new ModelRendererTurbo(this, 465, 73, textureX, textureY); // Box 379
        bodyModel[220].addBox(0, 0, 0, 1, 20, 1, 0f);
        bodyModel[220].setRotationPoint(12.0f, -17.0f, -9.0f);

        bodyModel[221] = new ModelRendererTurbo(this, 481, 73, textureX, textureY); // Box 380
        bodyModel[221].addBox(0, 0, 0, 1, 20, 1, 0f);
        bodyModel[221].setRotationPoint(-19.0f, -17.0f, -9.0f);

        bodyModel[222] = new ModelRendererTurbo(this, 465, 73, textureX, textureY); // Box 381
        bodyModel[222].addBox(0, 0, 0, 1, 20, 1, 0f);
        bodyModel[222].setRotationPoint(23.0f, -17.0f, 8.0f);

        bodyModel[223] = new ModelRendererTurbo(this, 465, 73, textureX, textureY); // Box 382
        bodyModel[223].addBox(0, 0, 0, 1, 20, 1, 0f);
        bodyModel[223].setRotationPoint(-19.0f, -17.0f, 8.0f);

        bodyModel[224] = new ModelRendererTurbo(this, 0, 141, textureX, textureY); // Box 397
        bodyModel[224].addShapeBox(0, 0, 0, 0, 8, 4, 0, 0, 0, 0, 0, 0, 0, -3, 0, -0.5f, 3, 0, -0.5f, 0, 0, 0, 0, 0, 0, -3, 0, -0.5f, 3, 0, -0.5f);
        bodyModel[224].setRotationPoint(-33.0f, -18.5f, -10.8f);

        bodyModel[225] = new ModelRendererTurbo(this, 0, 133, textureX, textureY); // Box 398
        bodyModel[225].addShapeBox(0, 0, 0, 0, 8, 4, 0, 3, 0, -0.5f, -3, 0, -0.5f, 0, 0, 0, 0, 0, 0, 3, 0, -0.5f, -3, 0, -0.5f, 0, 0, 0, 0, 0, 0);
        bodyModel[225].setRotationPoint(-33.0f, -18.5f, 6.8f);

        flipAll();
    }

}
