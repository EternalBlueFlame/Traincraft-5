//FMT-Marker FVTM-1.5
//Using PER-GROUP-INIT mode with limit '250'!
package train.render.models.steam;


import ebf.tim.render.StaticModelAnimator;
import fexcraft.fvtm.RollingStockModel;
import fexcraft.fvtm.TurboList;
import fexcraft.tmt.slim.ModelRendererTurbo;
import fexcraft.tmt.slim.Vec3f;

/** This file was exported via the FVTM Exporter v1.5 of<br>
 *  FMT (Fex's Modelling Toolbox) v.2.6.6 &copy; 2021 - Fexcraft.net<br>
 *  All rights reserved. For this Model's License contact the Author/Creator.
 */
public class Classic440 extends RollingStockModel {

	public Classic440(){
		super(); textureX = 256; textureY = 128;
		this.addToCreators("Broscolotos");
		//
		initGroup_Universal_4_4_0();
		initGroup_VT_12();
		initGroup_UP_119();
		initGroup_clipboard();
	}

	private final void initGroup_Universal_4_4_0(){
		TurboList Universal_4_4_0 = new TurboList("Universal_4_4_0");
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 219, 76, textureX, textureY).addBox(-6.5f, -6.5f, 0, 13, 13, 0)
			.setRotationPoint(-19.5f, 3.5f, -6.01f).setRotationAngle(0, 0, 0).setName(StaticModelAnimator.tagWheel)
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 111, 74, textureX, textureY).addBox(-6.5f, -6.5f, 0, 13, 13, 0)
			.setRotationPoint(1.5f, 3.5f, -6.01f).setRotationAngle(0, 0, 0).setName(StaticModelAnimator.tagWheel)
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 84, 74, textureX, textureY).addBox(-6.5f, -6.5f, 0, 13, 13, 0)
			.setRotationPoint(1.5f, 3.5f, 6.01f).setRotationAngle(0, 0, 0).setName(StaticModelAnimator.tagWheel)
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 0, 74, textureX, textureY).addBox(-6.5f, -6.5f, 0, 13, 13, 0)
			.setRotationPoint(-19.5f, 3.5f, 6.01f).setRotationAngle(0, 0, 0).setName(StaticModelAnimator.tagWheel)
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 115, 59, textureX, textureY).addBox(0, 0, 0, 14, 13, 1)
			.setRotationPoint(-3, -18, -11).setRotationAngle(0, 0, 0).setName("Box 41")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 84, 59, textureX, textureY).addBox(0, 0, 0, 14, 13, 1)
			.setRotationPoint(-3, -18, 10).setRotationAngle(0, 0, 0).setName("Box 42")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 41, 44, textureX, textureY).addBox(0, 0, 0, 1, 13, 20)
			.setRotationPoint(-3, -18, -10).setRotationAngle(0, 0, 0).setName("Box 43")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 107, 14, textureX, textureY)
			.addShapeBox(0, 0, 0, 24, 1, 12, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0)
			.setRotationPoint(-4, -19, 0).setRotationAngle(0, 0, 0).setName("Box 44")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 182, 2, textureX, textureY)
			.addShapeBox(0, 0, 0, 24, 1, 12, 0, 0, -1, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(-4, -19, -12).setRotationAngle(0, 0, 0).setName("Box 45")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 66, 39, textureX, textureY).addBox(0, 0, 0, 1, 1, 1)
			.setRotationPoint(-10, -21, -0.5f).setRotationAngle(0, 0, 0).setName("Box 69")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 180, 73, textureX, textureY).addBox(0, 0, 0, 1, 4, 1)
			.setRotationPoint(-8.5f, -24, -0.5f).setRotationAngle(0, 0, 0).setName("Box 70")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 168, 14, textureX, textureY).addBox(0, 0, 0, 4, 6, 4)
			.setRotationPoint(-56, -17.125f, -2).setRotationAngle(0, 0, 0).setName("Box 71")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 229, 61, textureX, textureY)
			.addShapeBox(0, 0, 0, 3, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2, 0, 0, -1, 0, 0, -1, 0, 0, -2, 0)
			.setRotationPoint(-56, -11.125f, -2).setRotationAngle(0, 0, 0).setName("Box 72")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 170, 51, textureX, textureY)
			.addShapeBox(0, 0, 0, 3, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2, 0, 0, -1, 0, 0, -1, 0, 0, -2, 0)
			.setRotationPoint(-56, -11.125f, 1).setRotationAngle(0, 0, 0).setName("Box 73")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 104, 22, textureX, textureY)
			.addShapeBox(0, 0, 0, 4, 1, 2, 0, -1, 0, 0, -1, 0, 0, -1, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(-56, -18.125f, -1).setRotationAngle(0, 0, 0).setName("Box 74")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 241, 23, textureX, textureY)
			.addShapeBox(0, 0, 0, 4, 1, 1, 0, -1, 0, -1, -1, 0, -1, -1, 0, 0, -1, 0, 0, -1, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(-56, -18.125f, -2).setRotationAngle(0, 0, 0).setName("Box 75")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 0, 22, textureX, textureY)
			.addShapeBox(0, 0, 0, 4, 1, 1, 0, -1, 0, 0, -1, 0, 0, -1, 0, -1, -1, 0, -1, 0, 0, 0, 0, 0, 0, -1, 0, 0, -1, 0, 0)
			.setRotationPoint(-56, -18.125f, 1).setRotationAngle(0, 0, 0).setName("Box 76")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 223, 47, textureX, textureY).addBox(0, 0, 0, 2, 1, 2)
			.setRotationPoint(-55, -19.125f, -1).setRotationAngle(0, 0, 0).setName("Box 77")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 139, 28, textureX, textureY).addBox(0, 0, 0, 7, 2, 20)
			.setRotationPoint(-59, 1.5f, -10).setRotationAngle(0, 0, 0).setName("Box 67")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 104, 38, textureX, textureY)
			.addShapeBox(0, 0, 0, 3, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2.5f, 0, 0, -2.5f, 0, 0, 0, 0, 0)
			.setRotationPoint(-58, 3.5f, -10).setRotationAngle(0, 0, 0).setName("Box 68")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 174, 34, textureX, textureY)
			.addShapeBox(0, 0, 0, 3, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2.5f, 0, 0, -2.5f, 0, 0, 0, 0, 0)
			.setRotationPoint(-58, 3.5f, 9).setRotationAngle(0, 0, 0).setName("Box 71")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 99, 14, textureX, textureY)
			.addShapeBox(0, 0, 0, 7, 1, 1, 0, 0, 0, -10, 0, 0, 0, 0, 0, 0, -1, 0, 9, 0, 0, -10, 0, 0, 0, 0, 0, 0, -1, 0, 9)
			.setRotationPoint(-66, 8.5f, -10).setRotationAngle(0, 0, 0).setName("Box 72")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 0, 14, textureX, textureY)
			.addShapeBox(0, 0, 0, 7, 1, 1, 0, -1, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, -10, -1, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, -10)
			.setRotationPoint(-66, 8.5f, 9).setRotationAngle(0, 0, 0).setName("Box 73")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 217, 41, textureX, textureY).addBox(0, 0, 0, 1, 1, 18)
			.setRotationPoint(-59, 3.5f, -9).setRotationAngle(0, 0, 0).setName("Box 74")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 21, 99, textureX, textureY).addBox(0, 0, 0, 1, 6, 1)
			.setRotationPoint(-59, 3.5f, 9).setRotationAngle(0, 0, 0).setName("Box 75")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 71, 97, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 6, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(-59, 3.5f, -10).setRotationAngle(0, 0, 0).setName("Box 76")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 196, 63, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 2, 1, 0, -0.25f, 0, -0.25f, -0.25f, 0, -0.25f, -0.25f, 0, -0.25f, -0.25f, 0, -0.25f, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(-58, -0.5f, -10).setRotationAngle(0, 0, 0).setName("Box 77")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 175, 63, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 2, 1, 0, -0.25f, 0, -0.25f, -0.25f, 0, -0.25f, -0.25f, 0, -0.25f, -0.25f, 0, -0.25f, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(-58, -0.5f, 9).setRotationAngle(0, 0, 0).setName("Box 79")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 239, 98, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0.5f, 0, -5, 0.5f, 0, -5, 0.5f, 0, 5, 0.5f, 0)
			.setRotationPoint(-53, -6, 6).setRotationAngle(0, 0, 0).setName("Box 80")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 253, 92, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0.5f, 2, -5, 0.5f, 2, -5, 0.5f, -2, 5, 0.5f, -2)
			.setRotationPoint(-53, -6, -6).setRotationAngle(0, 0, 0).setName("Box 81")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 0, 0, textureX, textureY).addBox(0, 0, 0, 39, 1, 20)
			.setRotationPoint(-42, -5, -10).setRotationAngle(0, 0, 0).setName("Box 82")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 229, 66, textureX, textureY).addBox(0, 0, 0, 8, 4, 5)
			.setRotationPoint(-52, 1.5f, -10).setRotationAngle(0, 0, 0).setName("Box 83")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 0, 7, textureX, textureY).addBox(0, 0, 0, 6, 3, 3)
			.setRotationPoint(-51, -1, -9).setRotationAngle(0, 0, 0).setName("Box 84")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 99, 17, textureX, textureY).addBox(0, 0, 0, 5, 1, 1)
			.setRotationPoint(-44, 3.5f, -8).setRotationAngle(0, 0, 0).setName(StaticModelAnimator.tagAdvancedPiston)
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 0, 57, textureX, textureY).addBox(0, 0, 0, 19, 1, 1)
			.setRotationPoint(-18.5f, 5.5f, -7).setRotationAngle(0, 0, 0).setName(StaticModelAnimator.tagConnectingRodOffset(3))
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 188, 56, textureX, textureY)
			.addShapeBox(0, 0, 0, 19, 1, 1, 0, 0, 0, -0.1f, 0, 0, -0.1f, 0, 0, -0.1f, 0, 0, -0.1f, 0, 0, -0.1f, 0, 0, -0.1f, 0, 0, -0.1f, 0, 0, -0.1f)
			.setRotationPoint(-39, 3.5f, -8).setRotationAngle(0, 0, 6).setName("Box 88")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 227, 38, textureX, textureY).addBox(0, 0, 0, 13, 1, 1)
			.setRotationPoint(-45, -0.5f, -8).setRotationAngle(0, 0, 0).setName("Box 89")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 0, 67, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0)
			.setRotationPoint(-33, -0.5f, -5).setRotationAngle(0, 0, 0).setName("Box 90")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 238, 61, textureX, textureY).addBox(0, 0, 0, 2, 2, 1)
			.setRotationPoint(-20.5f, 5, -8).setRotationAngle(0, 0, 0).setName("Box 91")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 36, 62, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 2, 1, 0, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, 0, 0, 0, 0, 0.5f, 0, -0.5f, 0.5f, 0, -0.5f, 0.5f, 0, 0, 0.5f, 0)
			.setRotationPoint(-34, 1, -9).setRotationAngle(0, 0, 0).setName("Box 92")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 147, 51, textureX, textureY)
			.addShapeBox(0, 0, 0, 10, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(-44, 2.5f, -9).setRotationAngle(0, 0, 0).setName("Box 93")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 135, 44, textureX, textureY)
			.addShapeBox(0, 0, 0, 10, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(-44, 4.5f, -9).setRotationAngle(0, 0, 0).setName("Box 94")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 180, 16, textureX, textureY).addBox(0, 0, 0, 25, 1, 10)
			.setRotationPoint(-52, 2, -5).setRotationAngle(0, 0, 0).setName("Box 95")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 207, 61, textureX, textureY).addBox(0, 0, 0, 8, 4, 5)
			.setRotationPoint(-52, 1.5f, 5).setRotationAngle(0, 0, 0).setName("Box 96")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 0, 0, textureX, textureY).addBox(0, 0, 0, 6, 3, 3)
			.setRotationPoint(-51, -1, 6).setRotationAngle(0, 0, 0).setName("Box 97")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 135, 41, textureX, textureY)
			.addShapeBox(0, 0, 0, 10, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(-44, 2.5f, 8).setRotationAngle(0, 0, 0).setName("Box 98")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 135, 38, textureX, textureY)
			.addShapeBox(0, 0, 0, 10, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(-44, 4.5f, 8).setRotationAngle(0, 0, 0).setName("Box 99")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 0, 17, textureX, textureY).addBox(0, 0, 0, 5, 1, 1)
			.setRotationPoint(-44, 3.5f, 7).setRotationAngle(0, 0, 0).setName(StaticModelAnimator.tagAdvancedPiston)
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 54, 60, textureX, textureY).addBox(0, 0, 0, 1, 1, 2)
			.setRotationPoint(-33, -0.5f, -7).setRotationAngle(0, 0, 0).setName("Box 105")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 217, 35, textureX, textureY).addBox(0, 0, 0, 13, 1, 1)
			.setRotationPoint(-45, -0.5f, 7).setRotationAngle(0, 0, 0).setName("Box 106")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 28, 62, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0)
			.setRotationPoint(-33, -0.5f, 4).setRotationAngle(0, 0, 0).setName("Box 107")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 196, 59, textureX, textureY).addBox(0, 0, 0, 1, 1, 2)
			.setRotationPoint(-33, -0.5f, 5).setRotationAngle(0, 0, 0).setName("Box 108")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 245, 61, textureX, textureY).addBox(0, 0, 0, 1, 3, 1)
			.setRotationPoint(-29, -1, -3).setRotationAngle(0, 0, 0).setName("Box 109")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 36, 47, textureX, textureY).addBox(0, 0, 0, 1, 3, 1)
			.setRotationPoint(-29, -1, 2).setRotationAngle(0, 0, 0).setName("Box 111")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 175, 73, textureX, textureY).addBox(0, 0, 0, 1, 4, 1)
			.setRotationPoint(-37, -2, -4).setRotationAngle(0, 0, 0).setName("Box 115")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 32, 72, textureX, textureY).addBox(0, 0, 0, 1, 4, 1)
			.setRotationPoint(-37, -2, 3).setRotationAngle(0, 0, 0).setName("Box 116")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 104, 28, textureX, textureY).addBox(0, 0, 0, 27, 9, 0)
			.setRotationPoint(-15, -4, -8).setRotationAngle(0, 0, 0).setName("Box 117")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 49, 22, textureX, textureY).addBox(0, 0, 0, 27, 9, 0)
			.setRotationPoint(-15, -4, 8).setRotationAngle(0, 0, 0).setName("Box 118")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 66, 97, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 6, 1, 0, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, 0, 0, 0, 0.7189f, -0.6126f, 0, -1, -0.8f, 0, -0.5f, -0.8f, 0, 0.0311f, -0.8f, 0)
			.setRotationPoint(-59, 3.5f, 7).setRotationAngle(0, 0, 15).setName("Box 117")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 61, 97, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 6, 1, 0, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, 0, 0, 0, 0.6561f, 0.075f, 0, -1, -0.3f, 0, -0.5f, -0.3f, 0, 0.0623f, -0.2688f, 0)
			.setRotationPoint(-59, 3.5f, 5).setRotationAngle(0, 0, 28.5f).setName("Box 120")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 213, 80, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 7, 1, 0, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, 0, 0, 0, 0.5623f, -0.0315f, 0, -1, -0.5f, 0, -0.5f, -0.5f, 0, 0.0311f, -0.4376f, 0)
			.setRotationPoint(-59, 3.5f, 3).setRotationAngle(0, 0, 39.5f).setName("Box 121")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 77, 78, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 8, 1, 0, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, 0, 0, 0, 0.4685f, 0.0312f, 0, -1, -0.5f, 0, -0.5f, -0.5f, 0, -2.0E-4f, -0.4689f, 0)
			.setRotationPoint(-59, 3.5f, 1).setRotationAngle(0, 0, 48).setName("Box 124")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 21, 88, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 9, 1, 0, 0, 0, -0.5f, -0.5f, 0, -0.5f, -0.5f, 0, 0, 0, 0, 0, 0.5124f, -0.4063f, -0.5f, -0.7f, -0.5f, -0.5f, -0.5f, -0.5f, 0, 0.281f, -0.6873f, 0)
			.setRotationPoint(-59, 3.5f, -0.5f).setRotationAngle(0, 0, 51).setName("Box 126")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 175, 59, textureX, textureY).addBox(0, 0, 0, 2, 2, 1)
			.setRotationPoint(-20.5f, 5, -7).setRotationAngle(0, 0, 0).setName(StaticModelAnimator.tagConnectingRodOffset(3))
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 95, 55, textureX, textureY).addBox(0, 0, 0, 2, 2, 1)
			.setRotationPoint(0.5f, 5, -7).setRotationAngle(0, 0, 0).setName(StaticModelAnimator.tagConnectingRodOffset(3))
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 147, 56, textureX, textureY).addBox(0, 0, 0, 19, 1, 1)
			.setRotationPoint(-18.5f, 5.5f, 6).setRotationAngle(0, 0, 0).setName(StaticModelAnimator.tagConnectingRodOffset(3))
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 53, 54, textureX, textureY).addBox(0, 0, 0, 2, 2, 1)
			.setRotationPoint(0.5f, 5, 6).setRotationAngle(0, 0, 0).setName(StaticModelAnimator.tagConnectingRodOffset(3))
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 95, 51, textureX, textureY).addBox(0, 0, 0, 2, 2, 1)
			.setRotationPoint(-20.5f, 5, 6).setRotationAngle(0, 0, 0).setName(StaticModelAnimator.tagConnectingRodOffset(3))
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 187, 44, textureX, textureY).addBox(0, 0, 0, 2, 2, 1)
			.setRotationPoint(-20.5f, 5, 7).setRotationAngle(0, 0, 0).setName("Box 132")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 0, 54, textureX, textureY)
			.addShapeBox(0, 0, 0, 19, 1, 1, 0, 0, 0, -0.1f, 0, 0, -0.1f, 0, 0, -0.1f, 0, 0, -0.1f, 0, 0, -0.1f, 0, 0, -0.1f, 0, 0, -0.1f, 0, 0, -0.1f)
			.setRotationPoint(-39, 3.5f, 7).setRotationAngle(0, 0, 6).setName("Box 133")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 175, 59, textureX, textureY)
			.addShapeBox(0, 0, 0, 6, 5, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0)
			.setRotationPoint(-51, -2.5f, -4).setRotationAngle(0, 0, 0).setName("Box 134")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 0, 22, textureX, textureY)
			.addShapeBox(0, 0, 0, 18, 12, 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(-21, -4, -6).setRotationAngle(0, 0, 0).setName("Box 137")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 0, 47, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.75f, -0.5f, 0.75f, 0.75f, -0.5f, 0.75f, 0.75f, -0.5f, 0.75f, 0.75f, -0.5f, 0.75f)
			.setRotationPoint(-38.5f, -17, -0.5f).setRotationAngle(0, 0, 0).setName("Box 135")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 243, 9, textureX, textureY)
			.addShapeBox(0, 0, 0, 3, 1, 3, 0, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, -0.5f, 0, 0, -0.5f, 0.5f, -0.1f, 0.5f, 0, -0.1f, 0.5f, 0, -0.1f, 0, 0.5f, -0.1f, 0)
			.setRotationPoint(-39.25f, -14.5f, -1.25f).setRotationAngle(0, 0, 0).setName("Box 136")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 61, 39, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 1, 1, 0, -0.125f, 0, 0, -0.125f, 0, 0, -0.125f, 0, 0, -0.125f, 0, 0, -0.125f, 0, 0, -0.125f, 0, 0, -0.125f, 0, 0, -0.125f, 0, 0)
			.setRotationPoint(-38.5f, -12.25f, -0.5f).setRotationAngle(0, 0, 0).setName("Box 137")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 246, 32, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 1, 3, 0, -0.125f, 0, 0, -0.125f, 0, 0, -0.125f, 0, 0, -0.125f, 0, 0, -0.125f, 0, 0, -0.125f, 0, 0, -0.125f, 0, 0, -0.125f, 0, 0)
			.setRotationPoint(-38.5f, -13.25f, -1.5f).setRotationAngle(0, 0, 0).setName("Box 138")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 223, 41, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 1, 4, 0, -0.5f, 0, 0, 0, 0, 0, 0, 0, 0, -0.5f, 0, 0, -0.5f, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0, -0.5f, -0.5f, 0)
			.setRotationPoint(-38.75f, -16.75f, -2).setRotationAngle(0, 0, 0).setName("Box 139")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 27, 72, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 4, 1, 0, -0.125f, 0, -0.25f, -0.125f, 0, -0.25f, -0.125f, 0, 0, -0.125f, 0, 0, -0.125f, 0, -0.25f, -0.125f, 0, -0.25f, -0.125f, 0, 0, -0.125f, 0, 0)
			.setRotationPoint(-38.5f, -17.25f, -3).setRotationAngle(0, 0, 0).setName("Box 140")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 187, 40, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 1, 2, 0, -0.125f, 0, -0.25f, -0.125f, 0, -0.25f, -0.125f, 0, -0.5f, -0.125f, 0, -0.5f, -0.125f, -0.5f, -0.75f, -0.125f, -0.5f, -0.75f, -0.125f, 0, -0.5f, -0.125f, 0, -0.5f)
			.setRotationPoint(-38.5f, -13.25f, -3).setRotationAngle(0, 0, 0).setName("Box 141")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 249, 26, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 1, 2, 0, -0.125f, 0, -0.5f, -0.125f, 0, -0.5f, -0.125f, 0, -0.25f, -0.125f, 0, -0.25f, -0.125f, 0, -0.5f, -0.125f, 0, -0.5f, -0.125f, -0.5f, -0.75f, -0.125f, -0.5f, -0.75f)
			.setRotationPoint(-38.5f, -13.25f, 1).setRotationAngle(0, 0, 0).setName("Box 142")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 207, 59, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 4, 1, 0, -0.125f, 0, 0, -0.125f, 0, 0, -0.125f, 0, -0.25f, -0.125f, 0, -0.25f, -0.125f, 0, 0, -0.125f, 0, 0, -0.125f, 0, -0.25f, -0.125f, 0, -0.25f)
			.setRotationPoint(-38.5f, -17.25f, 2).setRotationAngle(0, 0, 0).setName("Box 143")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 0, 47, textureX, textureY).addBox(0, 0, 0, 15, 1, 5)
			.setRotationPoint(-3, -5, -11).setRotationAngle(0, 0, 0).setName("Box 144")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 64, 44, textureX, textureY).addBox(0, 0, 0, 15, 1, 5)
			.setRotationPoint(-3, -5, 6).setRotationAngle(0, 0, 0).setName("Box 145")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 64, 51, textureX, textureY).addBox(0, 0, 0, 14, 5, 1)
			.setRotationPoint(-2, -5, -6).setRotationAngle(0, 0, 0).setName("Box 147")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 217, 28, textureX, textureY).addBox(0, 0, 0, 14, 5, 1)
			.setRotationPoint(-2, -5, 5).setRotationAngle(0, 0, 0).setName("Box 148")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 200, 80, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 4, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -5, 0, 0, -5)
			.setRotationPoint(11, -4, 6).setRotationAngle(0, 0, 0).setName("Box 149")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 28, 62, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 4, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -5, 0, 0, -5, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(11, -4, -11).setRotationAngle(0, 0, 0).setName("Box 150")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 192, 61, textureX, textureY).addBox(0, 0, 0, 1, 6, 12)
			.setRotationPoint(-3, -5, -6).setRotationAngle(0, 0, 0).setName("Box 151")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 102, 39, textureX, textureY)
			.addShapeBox(0, 0, 0, 10, 7, 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(-3, 1, -6).setRotationAngle(0, 0, 0).setName("Box 152")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 99, 7, textureX, textureY).addBox(0, 0, 0, 47, 6, 0)
			.setRotationPoint(-50, -14, -5).setRotationAngle(0, 0, 0).setName(StaticModelAnimator.tagAdvancedPiston)
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 99, 0, textureX, textureY).addBox(0, 0, 0, 47, 6, 0)
			.setRotationPoint(-50, -14, 5).setRotationAngle(0, 0, 0).setName(StaticModelAnimator.tagAdvancedPiston)
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 242, 86, textureX, textureY).addBox(0, 0, 0, 1, 13, 4)
			.setRotationPoint(10, -18, 6).setRotationAngle(0, 0, 0).setName("Box 150")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 163, 86, textureX, textureY).addBox(0, 0, 0, 1, 13, 4)
			.setRotationPoint(10, -18, -10).setRotationAngle(0, 0, 0).setName("Box 151")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 100, 44, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 2, 1, 0, 0, 0.5f, 0, -0.5f, 0.5f, 0, -0.5f, 0.5f, 0, 0, 0.5f, 0, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, 0, 0, 0)
			.setRotationPoint(-34, 5, -9).setRotationAngle(0, 0, 0).setName("Box 152")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 112, 17, textureX, textureY).addBox(0, 0, 0, 1, 1, 1)
			.setRotationPoint(-40, 3.5f, -9).setRotationAngle(0, 0, 0).setName("Box 153")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 13, 17, textureX, textureY).addBox(0, 0, 0, 1, 1, 1)
			.setRotationPoint(-40, 3.5f, 8).setRotationAngle(0, 0, 0).setName("Box 154")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 64, 44, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 2, 1, 0, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, 0, 0, 0, 0, 0.5f, 0, -0.5f, 0.5f, 0, -0.5f, 0.5f, 0, 0, 0.5f, 0)
			.setRotationPoint(-34, 1, 8).setRotationAngle(0, 0, 0).setName("Box 155")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 230, 41, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 2, 1, 0, 0, 0.5f, 0, -0.5f, 0.5f, 0, -0.5f, 0.5f, 0, 0, 0.5f, 0, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, 0, 0, 0)
			.setRotationPoint(-34, 5, 8).setRotationAngle(0, 0, 0).setName("Box 156")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 182, 40, textureX, textureY)
			.addShapeBox(0, 0, 0, 14, 3, 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0)
			.setRotationPoint(-2, 0, -6).setRotationAngle(0, 0, 0).setName("Box 146cp")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 138, 86, textureX, textureY).addCylinder(0, 0, 0, 6, 7, 8, 1.1666666f, 1, 2, null)
			.setRotationPoint(-22, -6, 0).setRotationAngle(0, 0, 0)
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 146, 59, textureX, textureY).addCylinder(0, 0, 0, 7, 12, 8, 1, 1, 2, null)
			.setRotationPoint(-15, -6, 0).setRotationAngle(0, 0, 0)
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 52, 97, textureX, textureY).newCylinderBuilder()
			.setPosition(0, 0, 0).setRadius(2, 1).setLength(9).setSegments(8, 0).setScale(1, 1).setDirection(5)
			.setTopOffset(null).setTopRotation(new Vec3f(0.0, 0.0, 0.0)).build()
			.setRotationPoint(-48, -20, 0).setRotationAngle(0, 0, 0)
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 119, 88, textureX, textureY).addCylinder(0, 0, 0, 4, 6, 8, 0.875f, 0.875f, 5, null)
			.setRotationPoint(-27.5f, -16, 0).setRotationAngle(0, 0, 0)
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 73, 58, textureX, textureY).addCylinder(0, 0, 0, 2, 1, 8, 1.75f, 1.25f, 5, null)
			.setRotationPoint(-27.5f, -17, 0).setRotationAngle(0, 0, 0)
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 64, 58, textureX, textureY).addCylinder(0, 0, 0, 2, 1, 8, 1.25f, 1.25f, 5, null)
			.setRotationPoint(-8.5f, -20, 0).setRotationAngle(0, 0, 0)
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 238, 41, textureX, textureY).addCylinder(0, 0, 0, 4, 7, 8, 0.875f, 0.875f, 5, null)
			.setRotationPoint(-8.5f, -18, 0).setRotationAngle(0, 0, 0)
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 44, 54, textureX, textureY).addCylinder(0, 0, 0, 2, 1, 8, 1.75f, 1.25f, 5, null)
			.setRotationPoint(-8.5f, -19, 0).setRotationAngle(0, 0, 0)
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 175, 80, textureX, textureY).addCylinder(0, 0, 0, 6, 31, 8, 1, 1, 2, null)
			.setRotationPoint(-53, -6, 0).setRotationAngle(0, 0, 0)
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 200, 90, textureX, textureY).addCylinder(0, 0, 0, 5, 1, 8, 1.0625f, 0.625f, 2, new Vec3f(0.1875, 0.0, 0.0))
			.setRotationPoint(-54, -6, 0).setRotationAngle(0, 0, 0)
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 98, 88, textureX, textureY).addCylinder(0, 0, 0, 5, 1, 8, 0.625f, 0.5f, 2, new Vec3f(0.1875, 0.0, 0.0))
			.setRotationPoint(-54.8125f, -6, 0).setRotationAngle(0, 0, 0)
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 77, 88, textureX, textureY).addCylinder(0, 0, 0, 5, 1, 8, 0.625f, 0.625f, 2, new Vec3f(0.875, 0.0, 0.0))
			.setRotationPoint(-55.625f, -6, 0).setRotationAngle(0, 0, 0)
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 15, 60, textureX, textureY).addCylinder(0, 0, 0, 3, 1, 8, 0.5625f, 0.5625f, 2, new Vec3f(0.8125, 0.0, 0.0))
			.setRotationPoint(-57, -14.25f, 0).setRotationAngle(0, 0, 0)
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 0, 0, textureX, textureY).addBox(0, -0.5f, -0.5f, 0, 1, 1)
			.setRotationPoint(-56.1875f, -14.25f, 0).setRotationAngle(0, 0, 0).setName("Light")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 248, 0, textureX, textureY).addBox(-0.5f, 0, -0.5f, 1, 0, 1)
			.setRotationPoint(-48, -25.5f, 0).setRotationAngle(0, 0, 0).setName("Smoke")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 251, 76, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 6, 1, 0, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, 0, 0, 0, 0.0311f, -0.8f, 0, -0.5f, -0.8f, 0, -1, -0.8f, 0, 0.7189f, -0.6126f, 0)
			.setRotationPoint(-59, 3.5f, -8).setRotationAngle(0, 0, 15).setName("Box 117cp")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 246, 76, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 6, 1, 0, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, 0, 0, 0, 0.0623f, -0.2688f, 0, -0.5f, -0.3f, 0, -1, -0.3f, 0, 0.6561f, 0.075f, 0)
			.setRotationPoint(-59, 3.5f, -6).setRotationAngle(0, 0, 28.5f).setName("Box 120cp")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 251, 61, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 7, 1, 0, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, 0, 0, 0, 0.0311f, -0.4376f, 0, -0.5f, -0.5f, 0, -1, -0.5f, 0, 0.5623f, -0.0315f, 0)
			.setRotationPoint(-59, 3.5f, -4).setRotationAngle(0, 0, 39.5f).setName("Box 121cp")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 185, 16, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 8, 1, 0, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, 0, 0, 0, -2.0E-4f, -0.4689f, 0, -0.5f, -0.5f, 0, -1, -0.5f, 0, 0.4685f, 0.0312f, 0)
			.setRotationPoint(-59, 3.5f, -2).setRotationAngle(0, 0, 48).setName("Box 124cp")
		);
		Universal_4_4_0.add(new ModelRendererTurbo(Universal_4_4_0, 138, 74, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 9, 1, 0, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, -0.5f, 0, 0, -0.5f, 0.281f, -0.6873f, 0, -0.5f, -0.5f, 0, -0.7f, -0.5f, -0.5f, 0.5124f, -0.4063f, -0.5f)
			.setRotationPoint(-59, 3.5f, -0.5f).setRotationAngle(0, 0, 51).setName("Box 126cp")
		);
		this.groups.add(Universal_4_4_0);
	}

	private final void initGroup_VT_12(){
		TurboList VT_12 = new TurboList("VT_12");
		VT_12.add(new ModelRendererTurbo(VT_12, 52, 78, textureX, textureY).newCylinderBuilder()
			.setPosition(0, 0, 0).setRadius(6, 3).setLength(3).setSegments(8, 0).setScale(1, 1).setDirection(5)
			.setTopOffset(null).setTopRotation(new Vec3f(0.0, 0.0, 0.0)).build()
			.setRotationPoint(-48, -27, 0).setRotationAngle(0, 0, 0)
		);
		VT_12.add(new ModelRendererTurbo(VT_12, 27, 78, textureX, textureY).newCylinderBuilder()
			.setPosition(0, 0, 0).setRadius(6, 3).setLength(4).setSegments(8, 0).setScale(0.3333334f, 1).setDirection(5)
			.setTopOffset(null).setTopRotation(new Vec3f(0.0, 0.0, 0.0)).build()
			.setRotationPoint(-48, -24, 0).setRotationAngle(0, 0, 0)
		);
		this.groups.add(VT_12);
	}

	private final void initGroup_UP_119(){
		TurboList UP_119 = new TurboList("UP_119");
		UP_119.add(new ModelRendererTurbo(UP_119, 230, 90, textureX, textureY).newCylinderBuilder()
			.setPosition(0, 0, 0).setRadius(2, 1).setLength(6).setSegments(8, 0).setScale(1, 1).setDirection(5)
			.setTopOffset(null).setTopRotation(new Vec3f(0.0, 0.0, 0.0)).build()
			.setRotationPoint(-48, -26, 0).setRotationAngle(0, 0, 0)
		);
		UP_119.add(new ModelRendererTurbo(UP_119, 221, 90, textureX, textureY).newCylinderBuilder()
			.setPosition(0, 0, 0).setRadius(2, 0.875f).setLength(4).setSegments(8, 0).setScale(1.125f, 1.125f).setDirection(5)
			.setTopOffset(new Vec3f(0.0, 0.5, 0.0)).setTopRotation(new Vec3f(0.0, 0.0, 0.0)).build()
			.setRotationPoint(-48, -27.5f, 0).setRotationAngle(0, 0, 0)
		);
		UP_119.add(new ModelRendererTurbo(UP_119, 0, 60, textureX, textureY).newCylinderBuilder()
			.setPosition(0, 0, 0).setRadius(2, 0.9375f).setLength(1).setSegments(8, 0).setScale(1.3125f, 1.125f).setDirection(5)
			.setTopOffset(null).setTopRotation(new Vec3f(0.0, 0.0, 0.0)).build()
			.setRotationPoint(-48, -26.5f, 0).setRotationAngle(0, 0, 0)
		);
		UP_119.add(new ModelRendererTurbo(UP_119, 105, 44, textureX, textureY).newCylinderBuilder()
			.setPosition(0, 0, 0).setRadius(2, 0.9375f).setLength(1).setSegments(8, 0).setScale(1.4375f, 1.4375f).setDirection(5)
			.setTopOffset(new Vec3f(0.0, 0.5, 0.0)).setTopRotation(new Vec3f(0.0, 0.0, 0.0)).build()
			.setRotationPoint(-48, -26, 0).setRotationAngle(0, 0, 0)
		);
		UP_119.add(new ModelRendererTurbo(UP_119, 61, 32, textureX, textureY).newCylinderBuilder()
			.setPosition(0, 0, 0).setRadius(2, 0.9375f).setLength(1).setSegments(8, 0).setScale(1.125f, 1.3125f).setDirection(5)
			.setTopOffset(null).setTopRotation(new Vec3f(0.0, 0.0, 0.0)).build()
			.setRotationPoint(-48, -25, 0).setRotationAngle(0, 0, 0)
		);
		this.groups.add(UP_119);
	}

	private final void initGroup_clipboard(){
		TurboList clipboard = new TurboList("clipboard");
		clipboard.add(new ModelRendererTurbo(clipboard, 239, 90, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0.5f, 0, -5, 0.5f, 0, -5, 0.5f, 0, 5, 0.5f, 0)
			.setRotationPoint(-53, -6, -6).setRotationAngle(0, 0, 0).setName("Box 80cp")
		);
		clipboard.add(new ModelRendererTurbo(clipboard, 253, 84, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0.5f, -2, -5, 0.5f, -2, -5, 0.5f, 2, 5, 0.5f, 2)
			.setRotationPoint(-53, -6, 6).setRotationAngle(0, 0, 0).setName("Box 81cp")
		);
		clipboard.add(new ModelRendererTurbo(clipboard, 143, 74, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0.5f, 2, -5, 0.5f, 2, -5, 0.5f, -2, 5, 0.5f, -2)
			.setRotationPoint(-53, -6, 6).setRotationAngle(0, 0, 0).setName("Box 81cp")
		);
		clipboard.add(new ModelRendererTurbo(clipboard, 9, 60, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0.5f, -2, -5, 0.5f, -2, -5, 0.5f, 2, 5, 0.5f, 2)
			.setRotationPoint(-53, -6, -6).setRotationAngle(0, 0, 0).setName("Box 81cp")
		);
		clipboard.add(new ModelRendererTurbo(clipboard, 0, 88, textureX, textureY).newCylinderBuilder()
			.setPosition(0, 0, 0).setRadius(5, 4.25f).setLength(1).setSegments(8, 0).setScale(0.625f, 0.625f).setDirection(2)
			.setTopOffset(new Vec3f(1.0, 0.0, 0.0)).setTopRotation(new Vec3f(0.0, 0.0, 0.0)).build()
			.setRotationPoint(-55.755f, -6, 0).setRotationAngle(0, 0, 0)
		);
		clipboard.add(new ModelRendererTurbo(clipboard, 0, 22, textureX, textureY).addBox(0, -0.5f, -0.5f, 0, 5, 5)
			.setRotationPoint(-54.751f, -8, -2).setRotationAngle(0, 0, 0).setName("Number F")
		);
		this.groups.add(clipboard);
	}

}
