package train.common.items;

import train.common.tile.TileTCRail;

public class TCRailTypes {

    public enum RailTypes {

        STRAIGHT(),
        DIAGONAL(),
        TURN(),
        PARALLEL(),
        DIAGONALTURN(),
        CROSSING(),
        DIAGONAL_CROSSING(),
        SWITCH(),
        SLOPE(),
        CURVED_SLOPE()
    }

    public static boolean isStraightTrack(TileTCRail tile){
        if (tile == null || tile.getRailType() == null)
            return false;
        else
            return (tile.getRailType() == RailTypes.STRAIGHT);
    }
    public static boolean isDiagonalTrack(TileTCRail tile){
        if (tile == null || tile.getRailType() == null)
            return false;
        return (tile.getRailType() == RailTypes.DIAGONAL);
    }
    public static boolean isTurnTrack(TileTCRail tile){
        if (tile == null || tile.getRailType() == null)
            return false;
        return (tile.getRailType() == RailTypes.TURN || tile.getRailType() == RailTypes.DIAGONALTURN || tile.getRailType() == RailTypes.PARALLEL);
    }
    public static boolean isCrossingTrack(TileTCRail tile){
        if (tile == null || tile.getRailType() == null)
            return false;
        return (tile.getRailType() == RailTypes.CROSSING);
    }
    public static boolean isDiagonalCrossingTrack(TileTCRail tile){
        if (tile == null || tile.getRailType() == null)
            return false;
        return (tile.getRailType() == RailTypes.DIAGONAL_CROSSING);
    }
    public static boolean isSwitchTrack(TileTCRail tile){
        if (tile == null || tile.getRailType() == null)
            return false;
        return (tile.getRailType() == RailTypes.SWITCH);
    }
    public static boolean isSlopeTrack(TileTCRail tile){
        if (tile == null || tile.getRailType() == null)
            return false;
        return (tile.getRailType() == RailTypes.SLOPE);
    }
    public static boolean isCurvedSlopeTrack(TileTCRail tile){
        if (tile == null || tile.getRailType() == null)
            return false;
        return (tile.getRailType() == RailTypes.CURVED_SLOPE);
    }

}
