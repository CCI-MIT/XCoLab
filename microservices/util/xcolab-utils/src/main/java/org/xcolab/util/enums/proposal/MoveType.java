package org.xcolab.util.enums.proposal;

public enum MoveType {
    MOVE_PERMANENTLY, COPY, FORK;


    public static MoveType valueOf(String stringValue, boolean allowArrays) {
        try {
            return valueOf(stringValue);
        } catch (IllegalArgumentException e) {
            if (allowArrays) {
                //attempt to recover if an array of values was given
                final String[] arguments = stringValue.split(",");
                if (arguments.length > 0) {
                    return valueOf(arguments[0]);
                }
            }
            throw e;
        }
    }
}
