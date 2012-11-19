package org.xcolab.core.contests;

import java.util.Calendar;

/**
 * User: jintrone
 * Date: 11/19/12
 * Time: 1:11 PM
 */
public class ContestPhaseTimingException extends Exception {

    public ContestPhaseTimingException(Calendar[] phaseTiming) {
        super("Contest Phase cannot be adjusted to "+phaseTiming[0].toString()+" - "+phaseTiming[1].toString());
    }

}
