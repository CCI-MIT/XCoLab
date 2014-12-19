package com.ext.portlet.service.impl;


import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.junit.Test;

import java.text.ParseException;
import static org.junit.Assert.assertTrue;

public class PhaseTransitionTest extends XCoLabTest {

    @Test
    public void randomTest() throws SystemException, PortalException, ParseException, NoSuchFieldException, IllegalAccessException {
        this.setupBasicDataset();
        GlobalContestPhaseTransitionSimulator.initSimulatorWithTestEnvironment(this);
        GlobalContestPhaseTransitionSimulator gcs = new GlobalContestPhaseTransitionSimulator();

        for (int j = 0; j < 10; j++) {
            gcs.initializeContests(
                    350,
                    0,
                    1, //firstPhase
                    true, //side contests are timed like global contest
                    GlobalContestSimulator.randomInt(0, 50),
                    GlobalContestSimulator.randomInt(0, 15),
                    GlobalContestSimulator.randomInt(0, 50),
                    new Double(GlobalContestSimulator.randomInt(0, 100))/100.0,
                    new Double(GlobalContestSimulator.randomInt(0, 100))/100.0,
                    new Double(GlobalContestSimulator.randomInt(0, 100))/100.0
            );
            gcs.setupJudgesAndFellowsForContests();

            while (gcs.currentPhase < 6) {
                gcs.advanceAllContestsToNextPhase(false);
            }

            //reset
            gcs.deleteContestsAndProposals();
        }
    }

    @Test
     public void debugTestAlwaysFails() {
        assertTrue(false);
    }
    @Test
    public void debugTestAlwaysSucceeds() {
        assertTrue(true);
    }
}
