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
public class Classic440TenderStandardRear extends RollingStockModel {

	public Classic440TenderStandardRear(){
		super(); textureX = 256; textureY = 256;
		this.addToCreators("Unregistered Account");
		//
		initGroup_Jupand119TenderRearTruck();
	}

	private final void initGroup_Jupand119TenderRearTruck(){
		TurboList Jupand119TenderRearTruck = new TurboList("Jupand119TenderRearTruck");
		Jupand119TenderRearTruck.add(new ModelRendererTurbo(Jupand119TenderRearTruck, 0, 42, textureX, textureY)
			.addShapeBox(-3, -3, 0, 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(-6, 7, 6).setRotationAngle(0, 0, 0).setName(StaticModelAnimator.tagWheel)
		);
		Jupand119TenderRearTruck.add(new ModelRendererTurbo(Jupand119TenderRearTruck, 241, 35, textureX, textureY)
			.addShapeBox(-3, -3, 0, 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(6, 7, 6).setRotationAngle(0, 0, 0).setName(StaticModelAnimator.tagWheel)
		);
		Jupand119TenderRearTruck.add(new ModelRendererTurbo(Jupand119TenderRearTruck, 0, 89, textureX, textureY).addCylinder(0, 0, 0, 1, 12, 6, 0.5f, 0.5f, 1, null)
			.setRotationPoint(6, 7, -6).setRotationAngle(0, 0, 0)
		);
		Jupand119TenderRearTruck.add(new ModelRendererTurbo(Jupand119TenderRearTruck, 0, 0, textureX, textureY).addCylinder(0, 0, 0, 1, 3, 6, 1, 1, 5, new Vec3f(0.0, 0.375, 0.0))
			.setRotationPoint(0, 4.125f, 0).setRotationAngle(0, 0, 0)
		);
		Jupand119TenderRearTruck.add(new ModelRendererTurbo(Jupand119TenderRearTruck, 250, 82, textureX, textureY).addCylinder(0, 0, 0, 1, 12, 6, 0.5f, 0.5f, 1, null)
			.setRotationPoint(-6, 7, -6).setRotationAngle(0, 0, 0).setName(" sunnamedcp")
		);
		Jupand119TenderRearTruck.add(new ModelRendererTurbo(Jupand119TenderRearTruck, 241, 28, textureX, textureY)
			.addShapeBox(-3, -3, 0, 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(-6, 7, -6).setRotationAngle(0, 0, 0).setName(StaticModelAnimator.tagWheel)
		);
		Jupand119TenderRearTruck.add(new ModelRendererTurbo(Jupand119TenderRearTruck, 223, 28, textureX, textureY)
			.addShapeBox(-3, -3, 0, 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(6, 7, -6).setRotationAngle(0, 0, 0).setName(StaticModelAnimator.tagWheel)
		);
		Jupand119TenderRearTruck.add(new ModelRendererTurbo(Jupand119TenderRearTruck, 125, 81, textureX, textureY)
			.addShapeBox(-1, 0, 0, 2, 1, 15, 0, 0, -0.25f, 0, 0, -0.25f, 0, 0, -0.25f, 0, 0, -0.25f, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f)
			.setRotationPoint(-6, 7, -7.5f).setRotationAngle(0, 0, 0)
		);
		Jupand119TenderRearTruck.add(new ModelRendererTurbo(Jupand119TenderRearTruck, 176, 80, textureX, textureY)
			.addShapeBox(-1, -0.5f, 0, 2, 1, 15, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0, -0.25f, 0, 0, -0.25f, 0, 0, -0.25f, 0, 0, -0.25f, 0)
			.setRotationPoint(-6, 6.75f, -7.5f).setRotationAngle(0, 0, 0)
		);
		Jupand119TenderRearTruck.add(new ModelRendererTurbo(Jupand119TenderRearTruck, 105, 79, textureX, textureY)
			.addShapeBox(-1, 0, 0, 2, 1, 15, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(-6, 6.25f, -7.5f).setRotationAngle(0, 0, 0)
		);
		Jupand119TenderRearTruck.add(new ModelRendererTurbo(Jupand119TenderRearTruck, 156, 75, textureX, textureY)
			.addShapeBox(-1, 0, 0, 2, 1, 15, 0, 0, -0.25f, 0, 0, -0.25f, 0, 0, -0.25f, 0, 0, -0.25f, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f)
			.setRotationPoint(6, 7, -7.5f).setRotationAngle(0, 0, 0)
		);
		Jupand119TenderRearTruck.add(new ModelRendererTurbo(Jupand119TenderRearTruck, 136, 64, textureX, textureY)
			.addShapeBox(-1, -0.5f, 0, 2, 1, 15, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0, -0.25f, 0, 0, -0.25f, 0, 0, -0.25f, 0, 0, -0.25f, 0)
			.setRotationPoint(6, 6.75f, -7.5f).setRotationAngle(0, 0, 0)
		);
		Jupand119TenderRearTruck.add(new ModelRendererTurbo(Jupand119TenderRearTruck, 221, 28, textureX, textureY)
			.addShapeBox(-1, 0, 0, 2, 1, 15, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(6, 6.25f, -7.5f).setRotationAngle(0, 0, 0)
		);
		Jupand119TenderRearTruck.add(new ModelRendererTurbo(Jupand119TenderRearTruck, 192, 115, textureX, textureY)
			.addShapeBox(0, 0, 0, 2, 2, 14, 0, 0, 0, 0, 0, 0, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, -0.25f, 0, 0, -0.25f, 0, 0, -0.25f, -0.5f, 0, -0.25f, -0.5f)
			.setRotationPoint(5, 4.5f, -6.75f).setRotationAngle(0, 0, 0)
		);
		Jupand119TenderRearTruck.add(new ModelRendererTurbo(Jupand119TenderRearTruck, 159, 114, textureX, textureY)
			.addShapeBox(0, 0, 0, 2, 2, 14, 0, 0, 0, 0, 0, 0, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, -0.25f, 0, 0, -0.25f, 0, 0, -0.25f, -0.5f, 0, -0.25f, -0.5f)
			.setRotationPoint(-7, 4.5f, -6.75f).setRotationAngle(0, 0, 0)
		);
		Jupand119TenderRearTruck.add(new ModelRendererTurbo(Jupand119TenderRearTruck, 97, 113, textureX, textureY)
			.addShapeBox(0, 0, 0, 2, 2, 14, 0, 0, 0, 0, 0, 0, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0.5f, 0, 0, 0.5f, 0, 0, 0.5f, -0.5f, 0, 0.5f, -0.5f)
			.setRotationPoint(-1, 6.125f, -6.75f).setRotationAngle(0, 0, 0)
		);
		Jupand119TenderRearTruck.add(new ModelRendererTurbo(Jupand119TenderRearTruck, 85, 75, textureX, textureY)
			.addShapeBox(0, 0, 0, 4, 2, 13, 0, 0, -0.75f, 0, 0, -0.375f, 0, 0, -0.375f, 0, 0, -0.75f, 0, 0, 0.25f, 0, 0, 0.875f, 0, 0, 0.875f, 0, 0, 0.25f, 0)
			.setRotationPoint(-5, 5.75f, -6.5f).setRotationAngle(0, 0, 0)
		);
		Jupand119TenderRearTruck.add(new ModelRendererTurbo(Jupand119TenderRearTruck, 0, 73, textureX, textureY)
			.addShapeBox(0, 0, 0, 4, 2, 13, 0, 0, -0.375f, 0, 0, -0.75f, 0, 0, -0.75f, 0, 0, -0.375f, 0, 0, 0.875f, 0, 0, 0.25f, 0, 0, 0.25f, 0, 0, 0.875f, 0)
			.setRotationPoint(1, 5.75f, -6.5f).setRotationAngle(0, 0, 0)
		);
		Jupand119TenderRearTruck.add(new ModelRendererTurbo(Jupand119TenderRearTruck, 73, 124, textureX, textureY)
			.addShapeBox(0, 0, 0, 1, 2, 14, 0, 0, 0, 0, 0, 0, 0, 0, 0, -0.5f, 0, 0, -0.5f, 0.5f, 0, 0, 0.5f, 0, 0, 0.5f, 0, -0.5f, 0.5f, 0, -0.5f)
			.setRotationPoint(-0.5f, 4.125f, -6.75f).setRotationAngle(0, 0, 0)
		);
		Jupand119TenderRearTruck.add(new ModelRendererTurbo(Jupand119TenderRearTruck, 0, 57, textureX, textureY)
			.addShapeBox(0, -0.5f, 0, 5, 2, 13, 0, 0, 0, 0, 0, 0.375f, 0, 0, 0.375f, -0.25f, 0, 0, -0.25f, 0, -1, 0, 0, -1.375f, 0, 0, -1.375f, -0.25f, 0, -1, -0.25f)
			.setRotationPoint(-5.25f, 5.125f, -6.375f).setRotationAngle(0, 0, 0)
		);
		Jupand119TenderRearTruck.add(new ModelRendererTurbo(Jupand119TenderRearTruck, 212, 49, textureX, textureY)
			.addShapeBox(0, -0.5f, 0, 5, 2, 13, 0, 0, 0.375f, 0, 0, 0, 0, 0, 0, -0.25f, 0, 0.375f, -0.25f, 0, -1.375f, 0, 0, -1, 0, 0, -1, -0.25f, 0, -1.375f, -0.25f)
			.setRotationPoint(0, 5.125f, -6.375f).setRotationAngle(0, 0, 0)
		);
		this.groups.add(Jupand119TenderRearTruck);
	}

}
