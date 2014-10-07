package com.ext.portlet.service.impl;


import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.junit.Test;

import java.text.ParseException;


public class PhaseTransitionTest extends XCoLabTest {

    @Test
    public void randomTest() throws SystemException, PortalException, ParseException, NoSuchFieldException, IllegalAccessException {
        this.setupBasicDataset();
        GlobalContestSimulator.initSimulatorWithTestEnvironment(this);
        GlobalContestSimulator gcs = new GlobalContestSimulator();
        for (int j = 0; j < 50; j++) {
            gcs.initializeContests(
                    GlobalContestSimulator.randomInt(50, 1500),
                    0,
                    0, //firstPhase
                    true, //side contests are timed like global contest
                    GlobalContestSimulator.randomInt(0, 50),
                    GlobalContestSimulator.randomInt(0, 15),
                    GlobalContestSimulator.randomInt(0, 50),
                    new Double(GlobalContestSimulator.randomInt(0, 100))/100.0,
                    new Double(GlobalContestSimulator.randomInt(0, 100))/100.0,
                    new Double(GlobalContestSimulator.randomInt(0, 100))/100.0
            );

            //reset
            gcs.deleteContestsAndProposals();
        }
    }
}
