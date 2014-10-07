package com.ext.portlet.service.impl;

import org.xcolab.utils.Clock;

import java.util.*;

import static org.junit.Assert.assertTrue;

/**
 * Created by Manuel Thurner on 22/09/14.
 */
public class GlobalContestPhaseTransitionSimulator extends GlobalContestSimulator {
    class CustomClock implements Clock {
        public Date date;

        @Override
        public Date now() {
            return date;

        }
    }

    public GlobalContestPhaseTransitionSimulator() {
        CustomClock testClock = new CustomClock();
        testClock.date = new Date();
        testInstance.contestPhaseLocalService.overrideClock((Clock)testClock);
    }

    public void advanceAllContestsToNextPhase() {
        assertTrue(areSideContestsTimedLikeGlobalContest);

        if (currentPhase >= 6) {
            return;
        }




        //for (
    }
}
