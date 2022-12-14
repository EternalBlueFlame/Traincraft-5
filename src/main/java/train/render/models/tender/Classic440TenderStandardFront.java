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
public class Classic440TenderStandardFront extends RollingStockModel {

	public Classic440TenderStandardFront(){
		super(); textureX = 256; textureY = 256;
		this.addToCreators("Unregistered Account");
		//
		initGroup_Jup_and_119_Tender_Front_Truck();
	}

	private final void initGroup_Jup_and_119_Tender_Front_Truck(){
		TurboList Jup_and_119_Tender_Front_Truck = new TurboList("Jup_and_119_Tender_Front_Truck");
		Jup_and_119_Tender_Front_Truck.add(new ModelRendererTurbo(Jup_and_119_Tender_Front_Truck, 62, 49, textureX, textureY)
			.addShapeBox(-3, -3, 0, 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(-6, 7, 6).setRotationAngle(0, 0, 0).setName(StaticModelAnimator.tagWheel)
		);
		Jup_and_119_Tender_Front_Truck.add(new ModelRendererTurbo(Jup_and_119_Tender_Front_Truck, 87, 44, textureX, textureY)
			.addShapeBox(-3, -3, 0, 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(6, 7, 6).setRotationAngle(0, 0, 0).setName(StaticModelAnimator.tagWheel)
		);
		Jup_and_119_Tender_Front_Truck.add(new ModelRendererTurbo(Jup_and_119_Tender_Front_Truck, 10, 89, textureX, textureY).addCylinder(0, 0, 0, 1, 12, 6, 0.5f, 0.5f, 1, null)
			.setRotationPoint(6, 7, -6).setRotationAngle(0, 0, 0)
		);
		Jup_and_119_Tender_Front_Truck.add(new ModelRendererTurbo(Jup_and_119_Tender_Front_Truck, 17, 3, textureX, textureY).addCylinder(0, 0, 0, 1, 3, 6, 1, 1, 5, new Vec3f(0.0, 0.375, 0.0))
			.setRotationPoint(0, 4.125f, 0).setRotationAngle(0, 0, 0)
		);
		Jup_and_119_Tender_Front_Truck.add(new ModelRendererTurbo(Jup_and_119_Tender_Front_Truck, 5, 89, textureX, textureY).addCylinder(0, 0, 0, 1, 12, 6, 0.5f, 0.5f, 1, null)
			.setRotationPoint(-6, 7, -6).setRotationAngle(0, 0, 0).setName(" sunnamed")
		);
		Jup_and_119_Tender_Front_Truck.add(new ModelRendererTurbo(Jup_and_119_Tender_Front_Truck, 62, 42, textureX, textureY)
			.addShapeBox(-3, -3, 0, 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(-6, 7, -6).setRotationAngle(0, 0, 0).setName(StaticModelAnimator.tagWheel)
		);
		Jup_and_119_Tender_Front_Truck.add(new ModelRendererTurbo(Jup_and_119_Tender_Front_Truck, 46, 42, textureX, textureY)
			.addShapeBox(-3, -3, 0, 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(6, 7, -6).setRotationAngle(0, 0, 0).setName(StaticModelAnimator.tagWheel)
		);
		Jup_and_119_Tender_Front_Truck.add(new ModelRendererTurbo(Jup_and_119_Tender_Front_Truck, 165, 97, textureX, textureY)
			.addShapeBox(-1, 0, 0, 2, 1, 15, 0, 0, -0.25f, 0, 0, -0.25f, 0, 0, -0.25f, 0, 0, -0.25f, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f)
			.setRotationPoint(-6, 7, -7.5f).setRotationAngle(0, 0, 0)
		);
		Jup_and_119_Tender_Front_Truck.add(new ModelRendererTurbo(Jup_and_119_Tender_Front_Truck, 97, 96, textureX, textureY)
			.addShapeBox(-1, -0.5f, 0, 2, 1, 15, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0, -0.25f, 0, 0, -0.25f, 0, 0, -0.25f, 0, 0, -0.25f, 0)
			.setRotationPoint(-6, 6.75f, -7.5f).setRotationAngle(0, 0, 0)
		);
		Jup_and_119_Tender_Front_Truck.add(new ModelRendererTurbo(Jup_and_119_Tender_Front_Truck, 145, 92, textureX, textureY)
			.addShapeBox(-1, 0, 0, 2, 1, 15, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(-6, 6.25f, -7.5f).setRotationAngle(0, 0, 0)
		);
		Jup_and_119_Tender_Front_Truck.add(new ModelRendererTurbo(Jup_and_119_Tender_Front_Truck, 77, 91, textureX, textureY)
			.addShapeBox(-1, 0, 0, 2, 1, 15, 0, 0, -0.25f, 0, 0, -0.25f, 0, 0, -0.25f, 0, 0, -0.25f, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f)
			.setRotationPoint(6, 7, -7.5f).setRotationAngle(0, 0, 0)
		);
		Jup_and_119_Tender_Front_Truck.add(new ModelRendererTurbo(Jup_and_119_Tender_Front_Truck, 0, 89, textureX, textureY)
			.addShapeBox(-1, -0.5f, 0, 2, 1, 15, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0, -0.25f, 0, 0, -0.25f, 0, 0, -0.25f, 0, 0, -0.25f, 0)
			.setRotationPoint(6, 6.75f, -7.5f).setRotationAngle(0, 0, 0)
		);
		Jup_and_119_Tender_Front_Truck.add(new ModelRendererTurbo(Jup_and_119_Tender_Front_Truck, 57, 83, textureX, textureY)
			.addShapeBox(-1, 0, 0, 2, 1, 15, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(6, 6.25f, -7.5f).setRotationAngle(0, 0, 0)
		);
		Jup_and_119_Tender_Front_Truck.add(new ModelRendererTurbo(Jup_and_119_Tender_Front_Truck, 21, 116, textureX, textureY)
			.addShapeBox(0, 0, 0, 2, 2, 14, 0, 0, 0, 0, 0, 0, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0.5f, -0.5f, 0, 0.5f, -0.5f)
			.setRotationPoint(-1, 6.125f, -6.75f).setRotationAngle(0, 0, 0)
		);
		Jup_and_119_Tender_Front_Truck.add(new ModelRendererTurbo(Jup_and_119_Tender_Front_Truck, 22, 83, textureX, textureY)
			.addShapeBox(0, 0, 0, 4, 2, 13, 0, 0, -0.75f, 0, 0, -0.375f, 0, 0, -0.375f, 0, 0, -0.75f, 0, 0, 0.25f, 0, 0, 0.875f, 0, 0, 0.875f, 0, 0, 0.25f, 0)
			.setRotationPoint(-5, 5.75f, -6.5f).setRotationAngle(0, 0, 0)
		);
		Jup_and_119_Tender_Front_Truck.add(new ModelRendererTurbo(Jup_and_119_Tender_Front_Truck, 211, 82, textureX, textureY)
			.addShapeBox(0, 0, 0, 4, 2, 13, 0, 0, -0.375f, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, -0.375f, 0, 0, 0.875f, 0, 0, 0.25f, 0, 0, 0.25f, 0, 0, 0.875f, 0)
			.setRotationPoint(1, 5.75f, -6.5f).setRotationAngle(0, 0, 0)
		);
		this.groups.add(Jup_and_119_Tender_Front_Truck);
	}

}
