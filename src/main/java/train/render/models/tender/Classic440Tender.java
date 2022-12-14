//FMT-Marker FVTM-1.5
//Using PER-GROUP-INIT mode with limit '250'!
package train.render.models.tender;

import ebf.tim.render.GroupedModelRender;
import fexcraft.fvtm.RollingStockModel;
import fexcraft.fvtm.TurboList;
import fexcraft.tmt.slim.ModelRendererTurbo;
import fexcraft.tmt.slim.Vec3f;

/** This file was exported via the FVTM Exporter v1.5 of<br>
 *  FMT (Fex's Modelling Toolbox) v.2.6.6 &copy; 2021 - Fexcraft.net<br>
 *  All rights reserved. For this Model's License contact the Author/Creator.
 */
public class Classic440Tender extends RollingStockModel {

	private static final String cargo1 = GroupedModelRender.tagRenderBlockCargo + "1";
	private static final String cargo2 = GroupedModelRender.tagRenderBlockCargo + "2";
	private static final String cargo3 = GroupedModelRender.tagRenderBlockCargo + "3";
	private static final String cargo4 = GroupedModelRender.tagRenderBlockCargo + "4";
	public Classic440Tender(){
		super(); textureX = 256; textureY = 256;
		this.addToCreators("Broscolotos");
		//
		initGroup_group0();
		initGroup_clipboard();
		initGroup_group5();
	}

	private final void initGroup_group0(){
		TurboList group0 = new TurboList("group0");
		group0.add(new ModelRendererTurbo(group0, 0, 0, textureX, textureY)
			.addShapeBox(0, 0, 0, 52, 3, 22, 0, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, 0, 0, 0, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, 0, 0, 0)
			.setRotationPoint(14, 0.5f, -11).setRotationAngle(0, 0, 0)
		);
		group0.add(new ModelRendererTurbo(group0, 217, 65, textureX, textureY).newCylinderBuilder()
			.setPosition(0, 0, 0).setRadius(2, 1.0E-15f).setLength(1).setSegments(8, 2).setScale(1.25f, 1.25f).setDirection(1)
			.setTopOffset(null).setTopRotation(new Vec3f(0.0, 0.0, 0.0)).build()
			.setRotationPoint(24, -8, -11).setRotationAngle(0, 0, 180)
		);
		this.groups.add(group0);
	}

	private final void initGroup_clipboard(){
		TurboList clipboard = new TurboList("clipboard");
		clipboard.add(new ModelRendererTurbo(clipboard, 132, 39, textureX, textureY)
			.addShapeBox(0, 0, 0, 2, 2, 22, 0, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, 0, 0, 0, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, 0, 0, 0)
			.setRotationPoint(65.5f, 1.5f, -11).setRotationAngle(0, 0, 0)
		);
		clipboard.add(new ModelRendererTurbo(clipboard, 138, 109, textureX, textureY)
			.addShapeBox(0, 0, 0, 3, 3, 14, 0, 0, 0, 0, 0.25f, 0, 0, 0.25f, 0, 0, 0, 0, 0, 0, 0, 0, 0.25f, 0, 0, 0.25f, 0, 0, 0, 0, 0)
			.setRotationPoint(62, -2.5f, -7).setRotationAngle(0, 0, 0)
		);
		clipboard.add(new ModelRendererTurbo(clipboard, 76, 108, textureX, textureY)
			.addShapeBox(0, 0, 0, 3, 1, 14, 0, 0, 0.25f, 0, 0.25f, -1, 0, 0.25f, -1, 0, 0, 0.25f, 0, 0, 0, 0, 0.25f, 0, 0, 0.25f, 0, 0, 0, 0, 0)
			.setRotationPoint(62, -3.5f, -7).setRotationAngle(0, 0, 0)
		);
		clipboard.add(new ModelRendererTurbo(clipboard, 62, 44, textureX, textureY)
			.addShapeBox(0, 0, 0, 4, 1, 16, 0, 0, -0.5f, 0, 0, -2.25f, 0, 0, -2.25f, 0, 0, -0.5f, 0, 0, 0, 0, -0.25f, 1.5f, 0, -0.25f, 1.5f, 0, 0, 0, 0)
			.setRotationPoint(62, -4.75f, -8).setRotationAngle(0, 0, 0)
		);
		clipboard.add(new ModelRendererTurbo(clipboard, 159, 43, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 9, 22, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(61, -8.5f, -11).setRotationAngle(0, 0, 0)
		);
		clipboard.add(new ModelRendererTurbo(clipboard, 91, 28, textureX, textureY)
			.addShapeBox(0, 0, 0, 44, 9, 1, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(17, -8.5f, -11).setRotationAngle(0, 0, 0)
		);
		clipboard.add(new ModelRendererTurbo(clipboard, 0, 26, textureX, textureY)
			.addShapeBox(0, 0, 0, 44, 9, 1, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(17, -8.5f, 10).setRotationAngle(0, 0, 0)
		);
		clipboard.add(new ModelRendererTurbo(clipboard, 37, 42, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 3, 22, 0, -0.75f, -0.5f, 0, 0.75f, -0.5f, 0, 0.75f, -0.5f, 0, -0.75f, -0.5f, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(61, -11, -11).setRotationAngle(0, 0, 0)
		);
		clipboard.add(new ModelRendererTurbo(clipboard, 77, 39, textureX, textureY)
			.addShapeBox(0, 0, 0, 37, 3, 1, 0, 0, -0.5f, 0, 0.75f, -0.5f, 0, 0.75f, -0.5f, 0, 0, -0.5f, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(24, -11, -11).setRotationAngle(0, 0, 0)
		);
		clipboard.add(new ModelRendererTurbo(clipboard, 138, 64, textureX, textureY).newCylinderBuilder()
			.setPosition(0, 0, 0).setRadius(2, 1.0E-15f).setLength(1).setSegments(8, 2).setScale(1.25f, 1.25f).setDirection(1)
			.setTopOffset(null).setTopRotation(new Vec3f(0.0, 0.0, 0.0)).build()
			.setRotationPoint(24, -8, 10).setRotationAngle(0, 0, 180)
		);
		clipboard.add(new ModelRendererTurbo(clipboard, 0, 37, textureX, textureY)
			.addShapeBox(0, 0, 0, 37, 3, 1, 0, 0, -0.5f, 0, 0.75f, -0.5f, 0, 0.75f, -0.5f, 0, 0, -0.5f, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(24, -11, 10).setRotationAngle(0, 0, 0)
		);
		clipboard.add(new ModelRendererTurbo(clipboard, 125, 79, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 9, 5, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(17, -8.5f, -10).setRotationAngle(0, 0, 0)
		);
		clipboard.add(new ModelRendererTurbo(clipboard, 241, 65, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 9, 5, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(17, -8.5f, 5).setRotationAngle(0, 0, 0)
		);
		clipboard.add(new ModelRendererTurbo(clipboard, 149, 14, textureX, textureY)
			.addShapeBox(0, 0, 0, 43, 8, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(18, -7.5f, -10).setRotationAngle(0, 0, 0)
		);
		clipboard.add(new ModelRendererTurbo(clipboard, 127, 0, textureX, textureY)
			.addShapeBox(0, 0, 0, 43, 8, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(18, -7.5f, 5).setRotationAngle(0, 0, 0)
		);
		clipboard.add(new ModelRendererTurbo(clipboard, 184, 43, textureX, textureY)
			.addShapeBox(0, 0, 0, 10, 8, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(51, -7.5f, -5).setRotationAngle(0, 0, 0)
		);
		clipboard.add(new ModelRendererTurbo(clipboard, 246, 19, textureX, textureY).addCylinder(0, 0, 0, 2, 4, 8, 1.125f, 1.125f, 4, null)
			.setRotationPoint(53.875f, -7.25f, 0).setRotationAngle(0, 0, 180)
		);
		clipboard.add(new ModelRendererTurbo(clipboard, 11, 9, textureX, textureY).addCylinder(0, 0, 0, 2, 1, 8, 1.25f, 1.25f, 4, new Vec3f(0.0, -0.6875, 0.0))
			.setRotationPoint(53.875f, -11.25f, 0).setRotationAngle(0, 0, 180)
		);
		clipboard.add(new ModelRendererTurbo(clipboard, 196, 82, textureX, textureY)
			.addShapeBox(0, 0, 0, 8, 5, 5, 0, 0, -0.5f, -0.25f, -0.125f, -0.5f, -0.25f, -0.125f, -0.5f, -0.25f, 0, -0.5f, -0.25f, 0, -0.125f, -0.25f, -0.125f, -0.125f, -0.25f, -0.125f, -0.125f, -0.25f, 0, -0.125f, -0.25f)
			.setRotationPoint(24.25f, -12.375f, -10).setRotationAngle(0, 0, 0)
		);
		clipboard.add(new ModelRendererTurbo(clipboard, 224, 7, textureX, textureY)
			.addShapeBox(0, 0, 0, 8, 1, 5, 0, 0, 0, 0, 0.125f, 0, 0, 0.125f, 0, 0, 0, 0, 0, 0, -0.0625f, 0, 0.125f, -0.0625f, 0, 0.125f, -0.0625f, 0, 0, -0.0625f, 0)
			.setRotationPoint(24.125f, -12.8125f, -10).setRotationAngle(0, 0, 0)
		);
		clipboard.add(new ModelRendererTurbo(clipboard, 112, 44, textureX, textureY)
			.addShapeBox(0, 0, 0, 8, 5, 5, 0, 0, -0.5f, -0.25f, -0.125f, -0.5f, -0.25f, -0.125f, -0.5f, -0.25f, 0, -0.5f, -0.25f, 0, -0.125f, -0.25f, -0.125f, -0.125f, -0.25f, -0.125f, -0.125f, -0.25f, 0, -0.125f, -0.25f)
			.setRotationPoint(24.25f, -12.375f, 5).setRotationAngle(0, 0, 0)
		);
		clipboard.add(new ModelRendererTurbo(clipboard, 224, 0, textureX, textureY)
			.addShapeBox(0, 0, 0, 8, 1, 5, 0, 0, 0, 0, 0.125f, 0, 0, 0.125f, 0, 0, 0, 0, 0, 0, -0.0625f, 0, 0.125f, -0.0625f, 0, 0.125f, -0.0625f, 0, 0, -0.0625f, 0)
			.setRotationPoint(24.125f, -12.8125f, 5).setRotationAngle(0, 0, 0)
		);
		clipboard.add(new ModelRendererTurbo(clipboard, 170, 43, textureX, textureY)
			.addShapeBox(0, 0, 0, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(62.5f, 1.5f, -11).setRotationAngle(0, 0, 0)
		);
		clipboard.add(new ModelRendererTurbo(clipboard, 144, 14, textureX, textureY)
			.addShapeBox(0, 0, 0, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(14, 1.5f, -11).setRotationAngle(0, 0, 0)
		);
		clipboard.add(new ModelRendererTurbo(clipboard, 246, 7, textureX, textureY)
			.addShapeBox(0, 0, 0, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(62.5f, 1.5f, 11).setRotationAngle(0, 0, 0)
		);
		clipboard.add(new ModelRendererTurbo(clipboard, 246, 0, textureX, textureY)
			.addShapeBox(0, 0, 0, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(14, 1.5f, 11).setRotationAngle(0, 0, 0)
		);
		clipboard.add(new ModelRendererTurbo(clipboard, 0, 49, textureX, textureY)
			.addShapeBox(0, 0, 0, 2, 1, 4, 0, 0.25f, 0, 0, 0.25f, 0, 0, 0.25f, 0, 0, 0.25f, 0, 0, 0.25f, 0, 0, 0.25f, 0, 0, 0.25f, 0, 0, 0.25f, 0, 0)
			.setRotationPoint(26, 3.5f, -2).setRotationAngle(0, 0, 0)
		);
		clipboard.add(new ModelRendererTurbo(clipboard, 137, 19, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 1, 1, 0, 0.25f, 0, 0, 0.25f, 0, 0, 0.25f, 0, -0.5f, 0.25f, 0, -0.5f, 0.25f, -1, 0, 0.25f, 0, 0, 0.25f, 0, -0.5f, 0.25f, -1, -0.5f)
			.setRotationPoint(24.5f, 3.5f, -1).setRotationAngle(0, 0, 0)
		);
		clipboard.add(new ModelRendererTurbo(clipboard, 132, 19, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 1, 1, 0, 0.25f, 0, 0, 0.25f, 0, 0, 0.25f, 0, -0.5f, 0.25f, 0, -0.5f, 0.25f, -1, 0, 0.25f, 0, 0, 0.25f, 0, -0.5f, 0.25f, -1, -0.5f)
			.setRotationPoint(24.5f, 3.5f, 0.5f).setRotationAngle(0, 0, 0)
		);
		clipboard.add(new ModelRendererTurbo(clipboard, 127, 19, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 1, 1, 0, 0.25f, 0, 0, 0.25f, 0, 0, 0.25f, 0, -0.5f, 0.25f, 0, -0.5f, 0.25f, 0, 0, 0.25f, -1, 0, 0.25f, -1, -0.5f, 0.25f, 0, -0.5f)
			.setRotationPoint(28.5f, 3.5f, -1).setRotationAngle(0, 0, 0)
		);
		clipboard.add(new ModelRendererTurbo(clipboard, 17, 18, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 1, 1, 0, 0.25f, 0, 0, 0.25f, 0, 0, 0.25f, 0, -0.5f, 0.25f, 0, -0.5f, 0.25f, 0, 0, 0.25f, -1, 0, 0.25f, -1, -0.5f, 0.25f, 0, -0.5f)
			.setRotationPoint(28.5f, 3.5f, 0.5f).setRotationAngle(0, 0, 0)
		);
		clipboard.add(new ModelRendererTurbo(clipboard, 223, 35, textureX, textureY)
			.addShapeBox(0, 0, 0, 2, 1, 4, 0, 0.25f, 0, 0, 0.25f, 0, 0, 0.25f, 0, 0, 0.25f, 0, 0, 0.25f, 0, 0, 0.25f, 0, 0, 0.25f, 0, 0, 0.25f, 0, 0)
			.setRotationPoint(50.5f, 3.5f, -2).setRotationAngle(0, 0, 0)
		);
		clipboard.add(new ModelRendererTurbo(clipboard, 17, 15, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 1, 1, 0, 0.25f, 0, 0, 0.25f, 0, 0, 0.25f, 0, -0.5f, 0.25f, 0, -0.5f, 0.25f, -1, 0, 0.25f, 0, 0, 0.25f, 0, -0.5f, 0.25f, -1, -0.5f)
			.setRotationPoint(49, 3.5f, -1).setRotationAngle(0, 0, 0)
		);
		clipboard.add(new ModelRendererTurbo(clipboard, 224, 7, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 1, 1, 0, 0.25f, 0, 0, 0.25f, 0, 0, 0.25f, 0, -0.5f, 0.25f, 0, -0.5f, 0.25f, -1, 0, 0.25f, 0, 0, 0.25f, 0, -0.5f, 0.25f, -1, -0.5f)
			.setRotationPoint(49, 3.5f, 0.5f).setRotationAngle(0, 0, 0)
		);
		clipboard.add(new ModelRendererTurbo(clipboard, 11, 3, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 1, 1, 0, 0.25f, 0, 0, 0.25f, 0, 0, 0.25f, 0, -0.5f, 0.25f, 0, -0.5f, 0.25f, 0, 0, 0.25f, -1, 0, 0.25f, -1, -0.5f, 0.25f, 0, -0.5f)
			.setRotationPoint(53, 3.5f, -1).setRotationAngle(0, 0, 0)
		);
		clipboard.add(new ModelRendererTurbo(clipboard, 127, 0, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 1, 1, 0, 0.25f, 0, 0, 0.25f, 0, 0, 0.25f, 0, -0.5f, 0.25f, 0, -0.5f, 0.25f, 0, 0, 0.25f, -1, 0, 0.25f, -1, -0.5f, 0.25f, 0, -0.5f)
			.setRotationPoint(53, 3.5f, 0.5f).setRotationAngle(0, 0, 0)
		);
		clipboard.add(new ModelRendererTurbo(clipboard, 0, 18, textureX, textureY)
			.addShapeBox(0, 0, 0, 6, 1, 2, 0, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, 0, 0, 0, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, 0, 0, 0)
			.setRotationPoint(10, 1, -1).setRotationAngle(0, 0, 0)
		);
		clipboard.add(new ModelRendererTurbo(clipboard, 241, 14, textureX, textureY)
			.addShapeBox(0, 0, 0, 5, 2, 2, 0, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, 0, 0, 0, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, 0, 0, 0)
			.setRotationPoint(66.5f, 2, -1).setRotationAngle(0, 0, 0)
		);
		this.groups.add(clipboard);
	}

	private final void initGroup_group5(){
		TurboList group5 = new TurboList("group5");
		group5.add(new ModelRendererTurbo(group5, 50, 147, textureX, textureY)
			.addShapeBox(0, 0, -6, 2, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(17.5f, -1.5f, 4).setRotationAngle(0, -14, 0)/*.setName(cargo1)*/
		);
		group5.add(new ModelRendererTurbo(group5, 164, 145, textureX, textureY)
				.addShapeBox(0, 0, 0, 2, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
				.setRotationPoint(47, -3.5f, -4).setRotationAngle(0, 0, 0)/*.setName(cargo1)*/
		);
		group5.add(new ModelRendererTurbo(group5, 147, 145, textureX, textureY)
			.addShapeBox(0, 0, 0, 2, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(49, -3.5f, -3.5f).setRotationAngle(0, 0, 0)/*.setName(cargo1)*/
		);
		group5.add(new ModelRendererTurbo(group5, 94, 144, textureX, textureY)
			.addShapeBox(0, 0, 0, 2, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(43, -3.5f, -4).setRotationAngle(0, 0, 0)/*.setName(cargo1)*/
		);
		group5.add(new ModelRendererTurbo(group5, 0, 144, textureX, textureY)
			.addShapeBox(0, 0, 0, 2, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(45, -3.5f, -3.5f).setRotationAngle(0, 0, 0)/*.setName(cargo1)*/
		);
		group5.add(new ModelRendererTurbo(group5, 217, 143, textureX, textureY)
			.addShapeBox(0, 0, 0, 2, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(39, -3.5f, -2.5f).setRotationAngle(0, 0, 0).setName("Wood5")
		);
		group5.add(new ModelRendererTurbo(group5, 200, 143, textureX, textureY)
			.addShapeBox(0, 0, 0, 2, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(41, -3.5f, -3.5f).setRotationAngle(0, 0, 0).setName("Wood6")
		);
		group5.add(new ModelRendererTurbo(group5, 130, 143, textureX, textureY)
			.addShapeBox(0, 0, 0, 2, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(35, -3.5f, -1).setRotationAngle(0, 0, 0).setName("Wood7")
		);
		group5.add(new ModelRendererTurbo(group5, 39, 142, textureX, textureY)
			.addShapeBox(0, 0, 0, 2, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(37, -3.5f, -1.5f).setRotationAngle(0, 0, 0).setName("Wood8")
		);
		group5.add(new ModelRendererTurbo(group5, 22, 142, textureX, textureY)
			.addShapeBox(0, 0, 0, 2, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(31, -3.5f, -1).setRotationAngle(0, 0, 0).setName("Wood9")
		);
		group5.add(new ModelRendererTurbo(group5, 183, 141, textureX, textureY)
			.addShapeBox(0, 0, 0, 2, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(33, -3.5f, -1.5f).setRotationAngle(0, 0, 0).setName("Wood10")
		);
		group5.add(new ModelRendererTurbo(group5, 113, 141, textureX, textureY)
			.addShapeBox(0, 0, 0, 2, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(27, -3.5f, -1).setRotationAngle(0, 0, 0).setName("Wood11")
		);
		group5.add(new ModelRendererTurbo(group5, 83, 141, textureX, textureY)
			.addShapeBox(0, 0, 0, 2, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(29, -3.5f, -1.5f).setRotationAngle(0, 0, 0).setName("Wood12")
		);
		group5.add(new ModelRendererTurbo(group5, 66, 141, textureX, textureY)
			.addShapeBox(0, 0, 0, 2, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(23, -3.5f, -1.5f).setRotationAngle(0, 0, 0).setName("Wood13")
		);
		group5.add(new ModelRendererTurbo(group5, 233, 140, textureX, textureY)
			.addShapeBox(0, 0, 0, 2, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(25, -3.5f, -0.5f).setRotationAngle(0, 0, 0).setName("Wood14")
		);
		group5.add(new ModelRendererTurbo(group5, 11, 139, textureX, textureY)
			.addShapeBox(0, 0, 0, 2, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(19, -3.5f, -2).setRotationAngle(0, 0, 0).setName("Wood15")
		);
		group5.add(new ModelRendererTurbo(group5, 55, 138, textureX, textureY)
			.addShapeBox(0, 0, 0, 2, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(21, -3.5f, -1).setRotationAngle(0, 0, 0).setName("Wood16")
		);
		group5.add(new ModelRendererTurbo(group5, 172, 140, textureX, textureY)
			.addShapeBox(0, 0, -1, 6, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(17.625f, -1.5f, -3.75f).setRotationAngle(0, -12.5f, -36.625f).setName("Wood17")
		);
		group5.add(new ModelRendererTurbo(group5, 155, 140, textureX, textureY)
			.addShapeBox(-0.125f, 0, -1, 6, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(23.5f, -4.5f, -2.5f).setRotationAngle(0, 15, -17).setName("Wood18")
		);
		group5.add(new ModelRendererTurbo(group5, 102, 139, textureX, textureY)
			.addShapeBox(-0.125f, 0, -1, 6, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(44.5f, -3.5f, 3.5f).setRotationAngle(0, -4.5f, 0).setName("Wood19")
		);
		group5.add(new ModelRendererTurbo(group5, 0, 139, textureX, textureY)
			.addShapeBox(-0.125f, 0, -1, 6, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(44.5f, -5.5f, -2.5f).setRotationAngle(1, 15, 0).setName("Wood20")
		);
		group5.add(new ModelRendererTurbo(group5, 138, 138, textureX, textureY)
			.addShapeBox(-0.125f, 0, -1, 6, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(38, -5.5f, -2.5f).setRotationAngle(1, 10, 0).setName("Wood21")
		);
		group5.add(new ModelRendererTurbo(group5, 55, 133, textureX, textureY)
			.addShapeBox(-0.125f, 0, -1, 6, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(31.5f, -5.5f, -4).setRotationAngle(1, -5, 0).setName("Wood22")
		);
		group5.add(new ModelRendererTurbo(group5, 90, 130, textureX, textureY)
			.addShapeBox(-0.125f, -2, -1, 6, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(43, -3.5f, -0.5f).setRotationAngle(1, 44.5f, -51.5f).setName("Wood23")
		);
		group5.add(new ModelRendererTurbo(group5, 222, 134, textureX, textureY)
			.addShapeBox(0, 0, 0, 2, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(47, -1.5f, -1.5f).setRotationAngle(0, 0, 0).setName("Wood24")
		);
		group5.add(new ModelRendererTurbo(group5, 205, 134, textureX, textureY)
			.addShapeBox(0, 0, 0, 2, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(49, -1.5f, -1).setRotationAngle(0, 0, 0).setName("Wood25")
		);
		group5.add(new ModelRendererTurbo(group5, 44, 133, textureX, textureY)
			.addShapeBox(0, 0, 0, 2, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(43, -1.5f, -1.5f).setRotationAngle(0, 0, 0).setName("Wood26")
		);
		group5.add(new ModelRendererTurbo(group5, 27, 133, textureX, textureY)
			.addShapeBox(0, 0, 0, 2, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(45, -1.5f, -1).setRotationAngle(0, 0, 0).setName("Wood27")
		);
		group5.add(new ModelRendererTurbo(group5, 188, 132, textureX, textureY)
			.addShapeBox(0, 0, 0, 2, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(39, -1.5f, -1).setRotationAngle(0, 0, 0).setName("Wood28")
		);
		group5.add(new ModelRendererTurbo(group5, 121, 132, textureX, textureY)
			.addShapeBox(0, 0, 0, 2, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(41, -1.5f, -2).setRotationAngle(0, 0, 0).setName("Wood29")
		);
		group5.add(new ModelRendererTurbo(group5, 238, 131, textureX, textureY)
			.addShapeBox(0, 0, 0, 2, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(35, -1.5f, -1).setRotationAngle(0, 0, 0).setName("Wood30")
		);
		group5.add(new ModelRendererTurbo(group5, 171, 131, textureX, textureY)
			.addShapeBox(0, 0, 0, 2, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(37, -1.5f, -1.5f).setRotationAngle(0, 0, 0).setName("Wood31")
		);
		group5.add(new ModelRendererTurbo(group5, 154, 131, textureX, textureY)
			.addShapeBox(0, 0, 0, 2, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(31, -1.5f, -1.5f).setRotationAngle(0, 0, 0).setName("Wood32")
		);
		group5.add(new ModelRendererTurbo(group5, 104, 130, textureX, textureY)
			.addShapeBox(0, 0, 0, 2, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(33, -1.5f, -0.5f).setRotationAngle(0, 0, 0).setName("Wood33")
		);
		group5.add(new ModelRendererTurbo(group5, 143, 127, textureX, textureY)
			.addShapeBox(0, 0, 0, 2, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(27, -1.5f, -1.5f).setRotationAngle(0, 0, 0).setName("Wood34")
		);
		group5.add(new ModelRendererTurbo(group5, 230, 122, textureX, textureY)
			.addShapeBox(0, 0, 0, 2, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(29, -1.5f, -1).setRotationAngle(0, 0, 0).setName("Wood35")
		);
		group5.add(new ModelRendererTurbo(group5, 40, 121, textureX, textureY)
			.addShapeBox(0, 0, 0, 2, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(23, -1.5f, -1).setRotationAngle(0, 0, 0).setName("Wood36")
		);
		group5.add(new ModelRendererTurbo(group5, 189, 118, textureX, textureY)
			.addShapeBox(0, 0, 0, 2, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(25, -1.5f, -2).setRotationAngle(0, 0, 0).setName("Wood37")
		);
		group5.add(new ModelRendererTurbo(group5, 51, 117, textureX, textureY)
			.addShapeBox(0, 0, 0, 2, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(21, -1.5f, -1.5f).setRotationAngle(0, 0, 0).setName("Wood38")
		);
		group5.add(new ModelRendererTurbo(group5, 178, 119, textureX, textureY)
			.addShapeBox(-0.125f, 0, -1, 6, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(45, -1.5f, -2.5f).setRotationAngle(0, 15, 0).setName("Wood39")
		);
		group5.add(new ModelRendererTurbo(group5, 230, 117, textureX, textureY)
			.addShapeBox(-0.125f, 0, -1, 6, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(38.75f, -1.5f, -4).setRotationAngle(0, -10, 0).setName("Wood40")
		);
		group5.add(new ModelRendererTurbo(group5, 73, 117, textureX, textureY)
			.addShapeBox(0, 0, -1, 6, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(33.375f, -1.5f, -2.5f).setRotationAngle(0, 10, -24).setName("Wood41")
		);
		group5.add(new ModelRendererTurbo(group5, 40, 116, textureX, textureY)
			.addShapeBox(-0.125f, 0, -1, 6, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(27, -1.5f, -4).setRotationAngle(0, -15, 0).setName("Wood42")
		);
		group5.add(new ModelRendererTurbo(group5, 135, 115, textureX, textureY)
			.addShapeBox(-0.125f, 0, -1, 6, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(20.75f, -1.5f, -2.5f).setRotationAngle(0, 10, 0).setName("Wood43")
		);
		group5.add(new ModelRendererTurbo(group5, 178, 114, textureX, textureY)
			.addShapeBox(-0.125f, 0, -1, 6, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(27, -3.5f, -2.5f).setRotationAngle(0, 15, 0).setName("Wood44")
		);
		group5.add(new ModelRendererTurbo(group5, 40, 109, textureX, textureY)
			.addShapeBox(-0.125f, 0, -1, 6, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(44.5f, -5.5f, 1).setRotationAngle(1, 15, 0).setName("Wood45")
		);
		group5.add(new ModelRendererTurbo(group5, 185, 106, textureX, textureY)
			.addShapeBox(-0.125f, 0, -1, 6, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(44.5f, -5.5f, 3.5f).setRotationAngle(0, 8, 0).setName("Wood46")
		);
		group5.add(new ModelRendererTurbo(group5, 40, 104, textureX, textureY)
			.addShapeBox(-0.125f, 0, -1, 6, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(37, -5.5f, 4).setRotationAngle(1, 0, 0).setName("Wood47")
		);
		group5.add(new ModelRendererTurbo(group5, 75, 100, textureX, textureY)
			.addShapeBox(-0.125f, 0, -1, 6, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(35, -5.5f, 2).setRotationAngle(1, 0, 0).setName("Wood48")
		);
		group5.add(new ModelRendererTurbo(group5, 40, 99, textureX, textureY)
			.addShapeBox(-0.125f, 0, -1, 6, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(34, -5.5f, -1).setRotationAngle(1, -8.5f, 0).setName("Wood49")
		);
		group5.add(new ModelRendererTurbo(group5, 137, 98, textureX, textureY)
			.addShapeBox(0, 0, 0, 2, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(31, -5.5f, -1.5f).setRotationAngle(0, 17, 0).setName("Wood50")
		);
		group5.add(new ModelRendererTurbo(group5, 196, 97, textureX, textureY)
			.addShapeBox(-0.125f, 0, -1, 6, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(26, -5.5f, 4).setRotationAngle(1, 0, 0).setName("Wood51")
		);
		group5.add(new ModelRendererTurbo(group5, 233, 87, textureX, textureY)
			.addShapeBox(-0.125f, 0, -1, 6, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(25, -5.5f, 2).setRotationAngle(0, 2.5f, 0).setName("Wood52")
		);
		group5.add(new ModelRendererTurbo(group5, 77, 83, textureX, textureY)
			.addShapeBox(-0.125f, 0, -1, 6, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(23.5f, -5.5f, -1).setRotationAngle(1, -7.5f, 0).setName("Wood53")
		);
		group5.add(new ModelRendererTurbo(group5, 185, 97, textureX, textureY)
			.addShapeBox(0, 0, 0, 2, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(20.5f, -5.5f, -1.5f).setRotationAngle(0, -17, 0).setName("Wood54")
		);
		group5.add(new ModelRendererTurbo(group5, 55, 83, textureX, textureY)
			.addShapeBox(0, -2, 0, 2, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(23, -3.5f, 2).setRotationAngle(27.5f, 51.5f, 0).setName("Wood55")
		);
		group5.add(new ModelRendererTurbo(group5, 44, 83, textureX, textureY)
			.addShapeBox(-0.125f, -2, -1, 6, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(24, -4.5f, -3).setRotationAngle(0, -22.5f, -37.5f).setName("Wood56")
		);
		group5.add(new ModelRendererTurbo(group5, 233, 82, textureX, textureY)
			.addShapeBox(-0.125f, 0, -1, 6, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(30.5f, -7.5f, -2.5f).setRotationAngle(0, 10, 0).setName("Wood57")
		);
		group5.add(new ModelRendererTurbo(group5, 145, 81, textureX, textureY)
			.addShapeBox(-0.125f, 0, -1, 6, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(44.5f, -7.5f, 3.5f).setRotationAngle(0, -5, 0).setName("Wood58")
		);
		group5.add(new ModelRendererTurbo(group5, 176, 75, textureX, textureY)
			.addShapeBox(-0.125f, 0, -1, 6, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(47.5f, -9.75f, 0.25f).setRotationAngle(0, 90, 26).setName("Wood59")
		);
		group5.add(new ModelRendererTurbo(group5, 48, 73, textureX, textureY)
			.addShapeBox(-0.125f, 0, -1, 6, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(42.5f, -10.5f, 6.5f).setRotationAngle(0, 110, 29).setName("Wood60")
		);
		group5.add(new ModelRendererTurbo(group5, 48, 68, textureX, textureY)
			.addShapeBox(-0.125f, 0, -1, 6, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(33, -9.5f, -5.5f).setRotationAngle(0, 35, 0).setName("Wood61")
		);
		group5.add(new ModelRendererTurbo(group5, 41, 57, textureX, textureY)
			.addShapeBox(-0.125f, 0, -1, 6, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(42.5f, -7.5f, 2).setRotationAngle(0, 21, 0).setName("Wood62")
		);
		group5.add(new ModelRendererTurbo(group5, 24, 57, textureX, textureY)
			.addShapeBox(-0.125f, -2, -1, 6, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(33.5f, -10, -6).setRotationAngle(0, -43, 25.5f).setName("Wood63")
		);
		group5.add(new ModelRendererTurbo(group5, 85, 62, textureX, textureY)
			.addShapeBox(0, 0, 0, 2, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(36.5f, -7.5f, -4).setRotationAngle(0, 17, 0).setName("Wood64")
		);
		group5.add(new ModelRendererTurbo(group5, 236, 55, textureX, textureY)
			.addShapeBox(-0.125f, 0, -1, 6, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(25.5f, -7.5f, 0.5f).setRotationAngle(0, 2.5f, 0).setName("Wood65")
		);
		group5.add(new ModelRendererTurbo(group5, 159, 52, textureX, textureY)
			.addShapeBox(-0.125f, 0, -1, 6, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(34.5f, -7.5f, 2.5f).setRotationAngle(1, -7.5f, 0).setName("Wood66")
		);
		group5.add(new ModelRendererTurbo(group5, 133, 49, textureX, textureY)
			.addShapeBox(0, 0, 0, 2, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(32.5f, -7.5f, -1.5f).setRotationAngle(0, -17, 0).setName("Wood67")
		);
		group5.add(new ModelRendererTurbo(group5, 236, 50, textureX, textureY)
			.addShapeBox(-0.125f, 0, -1, 6, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(50, -11.75f, -4.75f).setRotationAngle(0, 90, 26).setName("Wood68")
		);
		group5.add(new ModelRendererTurbo(group5, 236, 45, textureX, textureY)
			.addShapeBox(-0.125f, 0, -1, 6, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(51.25f, -9.25f, 0.5f).setRotationAngle(0, 110, 29).setName("Wood69")
		);
		group5.add(new ModelRendererTurbo(group5, 134, 44, textureX, textureY)
			.addShapeBox(-0.125f, 0, -1, 6, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(33, -9.5f, 5.5f).setRotationAngle(0, 35, 0).setName("Wood70")
		);
		group5.add(new ModelRendererTurbo(group5, 127, 14, textureX, textureY)
			.addShapeBox(-0.125f, -2, -1, 6, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(33.5f, -10, 5).setRotationAngle(0, -43, 25.5f).setName("Wood71")
		);
		group5.add(new ModelRendererTurbo(group5, 159, 43, textureX, textureY)
			.addShapeBox(0, 0, 0, 2, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(45.25f, -9.5f, -9.25f).setRotationAngle(0, 17, 0).setName("Wood72")
		);
		group5.add(new ModelRendererTurbo(group5, 0, 9, textureX, textureY)
			.addShapeBox(0, 0, 0, 2, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(52, -9.5f, -9.75f).setRotationAngle(0, -17, 0).setName("Wood73")
		);
		group5.add(new ModelRendererTurbo(group5, 0, 0, textureX, textureY)
			.addShapeBox(0, -2, 0, 2, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(40.75f, -7.5f, -7.25f).setRotationAngle(23, 66, 0).setName("Wood74")
		);
		this.groups.add(group5);
	}

}
