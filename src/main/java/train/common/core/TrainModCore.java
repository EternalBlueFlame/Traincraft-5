package train.common.core;
import train.common.blocks.tracks.kits.ModuleTraincraftTracks;

import train.common.Traincraft;

public class TrainModCore {

    public static void ModsLoaded() {
        if (Traincraft.hasRailcraft()) {
            // track kits and track types are registered by ModuleTraincraftTracks,
            // which Railcraft loads on its own
            Traincraft.tcLog.info("Railcraft found, Traincraft track kits are provided through the tc:tracks Railcraft module");
        }
    }

}
