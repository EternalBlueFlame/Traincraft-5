package train.library;

import ebf.tim.entities.GenericRailTransport;
import fexcraft.fcl.common.lang.ArrayList;
import train.core.handlers.ConfigHandler;
import train.entity.rollingStock.*;
import train.entity.trains.*;

import java.util.List;

public class TrainRegistry {

    public static List<GenericRailTransport> listSteam(){

        List<GenericRailTransport> transports = new ArrayList<GenericRailTransport>(){};

        if(ConfigHandler.REMASTERS!=2) {
            transports.add(new EntityLocoSteam4_4_0(null));
        }
        if(ConfigHandler.REMASTERS!=1){
            transports.add(new EntityLocoSteam4_4_0_Remaster(null));
        }

        transports.add(new EntityLocoSteamMallardA4(null));
        transports.add(new EntityLocoSteamHallClass(null));
        transports.add(new EntityLocoSteam040VB(null));
        transports.add(new EntityLocoSteam262T(null));
        transports.add(new EntityLocoSteamAdler(null));
        transports.add(new EntityLocoSteamAlcoSC4(null));
        transports.add(new EntityLocoSteamAlice0_4_0(null));
        transports.add(new EntityLocoSteamBerk765(null));
        transports.add(new EntityLocoSteamBerk1225(null));
        transports.add(new EntityLocoSteamBR01_DB(null));
        transports.add(new EntityLocoSteamBR80_DB(null));
        transports.add(new EntityLocoSteamC41(null));
        transports.add(new EntityLocoSteamC41_080(null));
        transports.add(new EntityLocoSteamC41T(null));
        transports.add(new EntityLocoSteamC62Class(null));
        transports.add(new EntityLocoSteamCherepanov(null));
        transports.add(new EntityLocoSteamClimax(null));
        transports.add(new EntityLocoSteamCoranationClass(null));
        transports.add(new EntityLocoSteamD51(null));
        transports.add(new EntityLocoSteamD51Long(null));
        transports.add(new EntityLocoSteamEr_Ussr(null));
        transports.add(new EntityLocoSteamForneyRed(null));
        transports.add(new EntityLocoSteamFowler(null));
        transports.add(new EntityLocoSteamGLYN042T(null));
        transports.add(new EntityLocoSteamGS4(null));
        transports.add(new EntityLocoSteamHeavy(null));
        transports.add(new EntityLocoSteamKingClass(null));
        transports.add(new EntityLocoSteamLSSP7(null));
        transports.add(new EntityLocoSteamMILWClassA(null));
        transports.add(new EntityLocoSteamMogulBlue(null));
        transports.add(new EntityLocoSteamPannier(null));
        transports.add(new EntityLocoSteamShay(null));
        transports.add(new EntityLocoSteamSmall(null));
        transports.add(new EntityLocoSteamSnowPlow(null));
        transports.add(new EntityLocoSteamSouthern1102(null));
        transports.add(new EntityLocoSteamUSATCUK(null));
        transports.add(new EntityLocoSteamUSATCUS(null));
        transports.add(new EntityLocoSteamVBShay(null));

        return transports;
    }


    public static GenericRailTransport[] listDiesel(){
        return new GenericRailTransport[]{
                new EntityLocoDiesel44TonSwitcher(null), new EntityLocoDieselBamboo(null),
                new EntityLocoDieselCD742(null), new EntityLocoDieselChME3(null),
                new EntityLocoDieselClass66(null), new EntityLocoDieselDD35A(null),
                new EntityLocoDieselDeltic(null), new EntityLocoDieselEMDF3(null),
                new EntityLocoDieselEMDF7(null), new EntityLocoDieselFOLM1(null),
                new EntityLocoDieselGP7Red(null), new EntityLocoDieselIC4_DSB_MG(null),
                new EntityLocoDieselKof_DB(null), new EntityLocoDieselMILW_H1044(null),
                new EntityLocoDieselSD40(null), new EntityLocoDieselSD70(null),
                new EntityLocoDieselShunter(null), new EntityLocoDieselV60_DB(null),
                new EntityLocoDieselWLs40(null)
        };
    }


    public static GenericRailTransport[] listElectric(){
        return new GenericRailTransport[]{
                new EntityLocoElectricBP4(null), new EntityLocoElectricBR185(null),
                new EntityLocoElectricBR_E69(null), new EntityLocoElectricCD151(null),
                new EntityLocoElectricClass85(null), new EntityLocoElectricE10_DB(null),
                new EntityLocoElectricE103(null), new EntityLocoElectricHighSpeedZeroED(null),
                new EntityLocoElectricICE1(null), new EntityLocoElectricMinetrain(null),
                new EntityLocoElectricTramNY(null), new EntityLocoElectricTramWood(null),
                new EntityLocoElectricVL10(null)
        };
    }


    public static GenericRailTransport[] listPassenger(){
        return new GenericRailTransport[]{
                new EntityPassenger2(null), new EntityPassenger5(null),
                new EntityPassenger7(null), new EntityPassenger_1class_DB(null),
                new EntityPassenger_2class_DB(null), new EntityPassengerAdler(null),
                new EntityPassengerBamboo(null), new EntityPassengerBlue(null),
                new EntityPassengerDBOriental(null), new EntityPassengerDenverRioGrande(null),
                new EntityPassengerDenverRioGrandeCombo(null), new EntityPassengerGS4(null),
                new EntityPassengerGS4_Observatory(null), new EntityPassengerGS4_Tail(null),
                new EntityPassengerHighSpeedCarZeroED(null), new EntityPassengerICE_1class(null),
                new EntityPassengerICE_2class(null), new EntityPassengerICE_Restaurant(null),
                new EntityPassengerMILW(null), new EntityPassengerMILWTail(null),
                new EntityPassengerRheingold(null), new EntityPassengerRheingoldDining1(null),
                new EntityPassengerRheingoldDining2(null), new EntityPassengerRheingoldPanorama(null),
                new EntityPassengerTramNY(null), new PassengerIC4_DSB_FG(null),
                new PassengerIC4_DSB_FH(null)
        };
    }

    public static GenericRailTransport[] listFreight(){
        return new GenericRailTransport[]{
                new EntityBoxCartUS(null), new EntityBoxCartPRR(null),
                new EntityBulkheadFlatCart(null), new EntityFlatCar_DB(null),
                new EntityFlatCarLogs_DB(null), new EntityFlatCarRails_DB(null),
                new EntityFlatCart(null), new EntityFlatCartSU(null),
                new EntityFlatCartUS(null), new EntityFlatCartWoodUS(null),
                new EntityFreight100TonHopper(null), new EntityFreightASTFAutorack(null),
                new EntityFreightBaggageMILW(null), new EntityFreightBamboo(null),
                new EntityFreightCart(null), new EntityFreightCart2(null),
                new EntityFreightCartL(null), new EntityFreightCartSmall(null),
                new EntityFreightCartUS(null), new EntityFreightCenterbeam_Empty(null),
                new EntityFreightCenterbeam_Wood_1(null), new EntityFreightCenterbeam_Wood_2(null),
                new EntityFreightClosed(null), new EntityFreightDenverRioGrande(null),
                new EntityFreightDepressedFlatbed(null), new EntityFreightGermanPost(null),
                new EntityFreightGondola_DB(null), new EntityFreightGrain(null),
                new EntityFreightGS4_Baggage(null), new EntityFreightGTNG(null),
                new EntityFreightHeavyweight(null), new EntityFreightHeavyweightBaggage(null),
                new EntityFreightHopperUS(null), new EntityFreightIceWagon(null),
                new EntityFreightKClassRailBox(null), new EntityFreightLongCoveredHopper(null),
                new EntityFreightMinetrain(null), new EntityFreightOpen2(null),
                new EntityFreightOpenWagon(null), new EntityFreightShortCoveredHopper(null),
                new EntityFreightSlateWagon(null), new EntityFreightTrailer(null),
                new EntityFreightWagenDB(null), new EntityFreightWellcar(null),
                new EntityFreightWood(null), new EntityFreightWood2(null),
                new EntityMailWagen_DB(null)

        };
    }


    public static GenericRailTransport[] listWorkCart(){
        return new GenericRailTransport[]{
                new EntityCaboose(null), new EntityCaboose3(null),
                new EntityCabooseLogging(null), new EntityCabooseLoggingPRR(null),
                new EntityCabooseWorkCart(null), new EntityGWRBrakeVan(null),
                new EntityWorkCart(null)
        };
    }

    public static List<GenericRailTransport> listTender(){
        List<GenericRailTransport> transports = new ArrayList<GenericRailTransport>(){};

        if(ConfigHandler.REMASTERS!=2) {
            transports.add(new EntityTender4_4_0(null));
        }
        if(ConfigHandler.REMASTERS!=1){
            transports.add(new EntityTender4_4_0_Remaster(null));
        }

        transports.add(new EntityTender4000(null));
        transports.add(new EntityTender_C41(null));
        transports.add(new EntityTender_Southern1102(null));
        transports.add(new EntityTenderA4(null));
        transports.add(new EntityTenderAdler(null));
        transports.add(new EntityTenderBerk1225(null));
        transports.add(new EntityTenderBR01_DB(null));
        transports.add(new EntityTenderC62Class(null));
        transports.add(new EntityTenderCoranationClass(null));
        transports.add(new EntityTenderD51(null));
        transports.add(new EntityTenderEr_Ussr(null));
        transports.add(new EntityTenderFowler4F(null));
        transports.add(new EntityTenderGS4(null));
        transports.add(new EntityTenderHeavy(null));
        transports.add(new EntityTenderMILW(null));
        transports.add(new EntityTenderSmall(null));

        return transports;
    }

    public static GenericRailTransport[] listTanker(){
        return new GenericRailTransport[]{
                new EntityTankLava(null), new EntityTankWagon(null),
                new EntityTankWagon2(null), new EntityTankWagon_DB(null),
                new EntityTankWagonThreeDome(null), new EntityTankWagonUS(null)
        };
    }


    public static GenericRailTransport[] listSpecial(){
        return new GenericRailTransport[]{
                new EntityBUnitDD35(null), new EntityBUnitEMDF3(null),
                new EntityBUnitEMDF7(null),

                new EntityPropagandaBritain(null),
                new EntityPropagandaJapan(null), new EntityPropagandaUS(null),
                new EntityPropagandaUSSR(null),

                new EntityStockCar(null), new EntityStockCarDRWG(null)
        };
    }
}
