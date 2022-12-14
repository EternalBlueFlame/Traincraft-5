//FMT-Marker FVTM-1.5
//Using PER-GROUP-INIT mode with limit '250'!
package train.render.models.tender;

import ebf.tim.render.StaticModelAnimator;
import fexcraft.fvtm.RollingStockModel;
import fexcraft.fvtm.TurboList;
import fexcraft.tmt.slim.ModelRendererTurbo;
import fexcraft.tmt.slim.Vec3f;

/** This file was exported via the FVTM Exporter v1.5 of<br>
 *  FMT (Fex's Modelling Toolbox) v.2.6.6 &copy; 2021 - Fexcraft.net<br>
 *  All rights reserved. For this Model's License contact the Author/Creator.
 */
public class Classic440TenderTruckVT extends RollingStockModel {

	public Classic440TenderTruckVT(){
		super(); textureX = 256; textureY = 256;
		this.addToCreators("Unregistered Account");
		//
		initGroup_VT_Truck();
	}

	private final void initGroup_VT_Truck(){
		TurboList VT_Truck = new TurboList("VT_Truck");
		VT_Truck.add(new ModelRendererTurbo(VT_Truck, 24, 62, textureX, textureY)
			.addShapeBox(-3, -3, 0, 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(-6, 7, 6).setRotationAngle(0, 0, 0).setName(StaticModelAnimator.tagWheel)
		);
		VT_Truck.add(new ModelRendererTurbo(VT_Truck, 114, 60, textureX, textureY)
			.addShapeBox(-3, -3, 0, 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(6, 7, 6).setRotationAngle(0, 0, 0).setName(StaticModelAnimator.tagWheel)
		);
		VT_Truck.add(new ModelRendererTurbo(VT_Truck, 61, 68, textureX, textureY)
			.addShapeBox(0, 0, 0, 5, 1, 13, 0, 0, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, 0, 0, 0, 0, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, 0, 0, 0)
			.setRotationPoint(4, 5.375f, -6.5f).setRotationAngle(0, 0, 0)
		);
		VT_Truck.add(new ModelRendererTurbo(VT_Truck, 24, 68, textureX, textureY)
			.addShapeBox(0, 0, 0, 5, 1, 13, 0, 0, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, 0, 0, 0, -0.75f, 0, 0, -1.5f, 0, 0, -1.5f, 0, 0, -0.75f, 0, 0)
			.setRotationPoint(4, 6.375f, -6.5f).setRotationAngle(0, 0, 0)
		);
		VT_Truck.add(new ModelRendererTurbo(VT_Truck, 217, 67, textureX, textureY)
			.addShapeBox(0, 0, 0, 5, 1, 13, 0, -0.75f, 0, 0, -1.5f, 0, 0, -1.5f, 0, 0, -0.75f, 0, 0, 0, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, 0, 0, 0)
			.setRotationPoint(4, 7.375f, -6.5f).setRotationAngle(0, 0, 0)
		);
		VT_Truck.add(new ModelRendererTurbo(VT_Truck, 0, 42, textureX, textureY)
			.addShapeBox(0, 0, 0, 16, 1, 13, 0, 0.25f, 0, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0.25f, 0, 0, 0.25f, -0.75f, 0, 0.5f, -0.75f, 0, 0.5f, -0.75f, 0, 0.25f, -0.75f, 0)
			.setRotationPoint(-8, 8.375f, -6.5f).setRotationAngle(0, 0, 0)
		);
		VT_Truck.add(new ModelRendererTurbo(VT_Truck, 169, 28, textureX, textureY)
			.addShapeBox(0, 0, 0, 20, 1, 13, 0, 0.5f, 0, 0, 0, 0, 0, 0, 0, 0.25f, 0.5f, 0, 0.25f, 0.5f, -0.75f, 0, 0, -0.75f, 0, 0, -0.75f, 0.25f, 0.5f, -0.75f, 0.25f)
			.setRotationPoint(-9.75f, 5.125f, -6.625f).setRotationAngle(0, 0, 0)
		);
		VT_Truck.add(new ModelRendererTurbo(VT_Truck, 0, 123, textureX, textureY)
			.addShapeBox(0, 0, 0, 2, 1, 14, 0, 0, 0, 0, 0, 0, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, -0.25f, 0, 0, -0.25f, 0, 0, -0.25f, -0.5f, 0, -0.25f, -0.5f)
			.setRotationPoint(5, 5.5f, -6.75f).setRotationAngle(0, 0, 0)
		);
		VT_Truck.add(new ModelRendererTurbo(VT_Truck, 87, 44, textureX, textureY)
			.addShapeBox(0, 0, 0, 5, 1, 14, 0, 0.5f, 0, 0, 0, 0, 0, 0, 0, -0.5f, 0.5f, 0, -0.5f, 0.5f, -0.25f, 0, 0, -0.25f, 0, 0, -0.25f, -0.5f, 0.5f, -0.25f, -0.5f)
			.setRotationPoint(-2.25f, 6.75f, -6.75f).setRotationAngle(0, 0, 0)
		);
		VT_Truck.add(new ModelRendererTurbo(VT_Truck, 211, 118, textureX, textureY)
			.addShapeBox(0, 0, 0, 2, 1, 14, 0, 0.25f, -1.25f, 0, 0, 0, 0, 0, 0, -0.5f, 0.25f, -1.25f, -0.5f, 0.25f, 1, 0, 0, -0.25f, 0, 0, -0.25f, -0.5f, 0.25f, 1, -0.5f)
			.setRotationPoint(3, 5.5f, -6.75f).setRotationAngle(0, 0, 0)
		);
		VT_Truck.add(new ModelRendererTurbo(VT_Truck, 54, 117, textureX, textureY)
			.addShapeBox(0, 0, 0, 2, 1, 14, 0, 0, 0, 0, 0, 0, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, -0.25f, 0, 0, -0.25f, 0, 0, -0.25f, -0.5f, 0, -0.25f, -0.5f)
			.setRotationPoint(-7, 5.5f, -6.75f).setRotationAngle(0, 0, 0)
		);
		VT_Truck.add(new ModelRendererTurbo(VT_Truck, 116, 116, textureX, textureY)
			.addShapeBox(0, 0, 0, 2, 1, 14, 0, 0, 0, 0, 0.25f, -1.25f, 0, 0.25f, -1.25f, -0.5f, 0, 0, -0.5f, 0, -0.25f, 0, 0.25f, 1, 0, 0.25f, 1, -0.5f, 0, -0.25f, -0.5f)
			.setRotationPoint(-5, 5.5f, -6.75f).setRotationAngle(0, 0, 0)
		);
		VT_Truck.add(new ModelRendererTurbo(VT_Truck, 165, 92, textureX, textureY).addCylinder(0, 0, 0, 1, 12, 6, 0.5f, 0.5f, 1, null)
			.setRotationPoint(6, 7, -6).setRotationAngle(0, 0, 0)
		);
		VT_Truck.add(new ModelRendererTurbo(VT_Truck, 0, 9, textureX, textureY).addCylinder(0, 0, 0, 1, 3, 6, 1, 1, 5, new Vec3f(0.0, 0.375, 0.0))
			.setRotationPoint(0, 4.125f, 0).setRotationAngle(0, 0, 0)
		);
		VT_Truck.add(new ModelRendererTurbo(VT_Truck, 193, 65, textureX, textureY)
			.addShapeBox(0, 0, 0, 5, 1, 13, 0, 0, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, 0, 0, 0, 0, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, 0, 0, 0)
			.setRotationPoint(-8, 5.375f, -6.5f).setRotationAngle(0, 0, 0)
		);
		VT_Truck.add(new ModelRendererTurbo(VT_Truck, 114, 64, textureX, textureY)
			.addShapeBox(0, 0, 0, 5, 1, 13, 0, 0, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, 0, 0, 0, -0.75f, 0, 0, -1.5f, 0, 0, -1.5f, 0, 0, -0.75f, 0, 0)
			.setRotationPoint(-8, 6.375f, -6.5f).setRotationAngle(0, 0, 0)
		);
		VT_Truck.add(new ModelRendererTurbo(VT_Truck, 90, 60, textureX, textureY)
			.addShapeBox(0, 0, 0, 5, 1, 13, 0, -0.75f, 0, 0, -1.5f, 0, 0, -1.5f, 0, 0, -0.75f, 0, 0, 0, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, 0, 0, 0)
			.setRotationPoint(-8, 7.375f, -6.5f).setRotationAngle(0, 0, 0)
		);
		VT_Truck.add(new ModelRendererTurbo(VT_Truck, 97, 91, textureX, textureY).addCylinder(0, 0, 0, 1, 12, 6, 0.5f, 0.5f, 1, null)
			.setRotationPoint(-6, 7, -6).setRotationAngle(0, 0, 0).setName(" sunnamedcp")
		);
		VT_Truck.add(new ModelRendererTurbo(VT_Truck, 0, 57, textureX, textureY)
			.addShapeBox(-3, -3, 0, 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(-6, 7, -6).setRotationAngle(0, 0, 0).setName(StaticModelAnimator.tagWheel)
		);
		VT_Truck.add(new ModelRendererTurbo(VT_Truck, 87, 51, textureX, textureY)
			.addShapeBox(-3, -3, 0, 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(6, 7, -6).setRotationAngle(0, 0, 0).setName(StaticModelAnimator.tagWheel)
		);
		VT_Truck.add(new ModelRendererTurbo(VT_Truck, 0, 106, textureX, textureY)
			.addShapeBox(-1, 0, 0, 2, 1, 15, 0, 0, -0.25f, 0, 0, -0.25f, 0, 0, -0.25f, 0, 0, -0.25f, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f)
			.setRotationPoint(-6, 7, -7.5f).setRotationAngle(0, 0, 0)
		);
		VT_Truck.add(new ModelRendererTurbo(VT_Truck, 220, 100, textureX, textureY)
			.addShapeBox(-1, -0.5f, 0, 2, 1, 15, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0, -0.25f, 0, 0, -0.25f, 0, 0, -0.25f, 0, 0, -0.25f, 0)
			.setRotationPoint(-6, 6.75f, -7.5f).setRotationAngle(0, 0, 0)
		);
		VT_Truck.add(new ModelRendererTurbo(VT_Truck, 55, 100, textureX, textureY)
			.addShapeBox(-1, 0, 0, 2, 1, 15, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(-6, 6.25f, -7.5f).setRotationAngle(0, 0, 0)
		);
		VT_Truck.add(new ModelRendererTurbo(VT_Truck, 20, 99, textureX, textureY)
			.addShapeBox(-1, 0, 0, 2, 1, 15, 0, 0, -0.25f, 0, 0, -0.25f, 0, 0, -0.25f, 0, 0, -0.25f, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f)
			.setRotationPoint(6, 7, -7.5f).setRotationAngle(0, 0, 0)
		);
		VT_Truck.add(new ModelRendererTurbo(VT_Truck, 200, 98, textureX, textureY)
			.addShapeBox(-1, -0.5f, 0, 2, 1, 15, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0, -0.25f, 0, 0, -0.25f, 0, 0, -0.25f, 0, 0, -0.25f, 0)
			.setRotationPoint(6, 6.75f, -7.5f).setRotationAngle(0, 0, 0)
		);
		VT_Truck.add(new ModelRendererTurbo(VT_Truck, 117, 98, textureX, textureY)
			.addShapeBox(-1, 0, 0, 2, 1, 15, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(6, 6.25f, -7.5f).setRotationAngle(0, 0, 0)
		);
		VT_Truck.add(new ModelRendererTurbo(VT_Truck, 219, 0, textureX, textureY)
			.addShapeBox(0, -3, 0, 4, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(-1.875f, 8.125f, -6.25f).setRotationAngle(0, 0, 0)
		);
		VT_Truck.add(new ModelRendererTurbo(VT_Truck, 11, 0, textureX, textureY)
			.addShapeBox(0, -3, 0, 4, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(-1.875f, 8.125f, 6.25f).setRotationAngle(0, 0, 0)
		);
		this.groups.add(VT_Truck);
	}

}
