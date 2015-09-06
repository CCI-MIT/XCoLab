package com.ext.portlet.service.impl;


import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.service.persistence.PointsPersistence;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionCheckerUtil;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.*;

import static org.junit.Assert.*;


public class PointsTest extends XCoLabTest {

    /*
     Useful commands and snippets for debugging:

     Redeploy service layer:
     cd $XCOLAB_HOME/services/plansProposalsFacade/plansProposalsFacade-portlet
     mvn compile package liferay:deploy -DskipTests=true

    Watches in Debugger Tab:

    Get all points generated:
    testInstance.pointsLocalService.getPointses(0, Integer.MAX_VALUE)

     */

    @Test
    public void fixedHypotheticalTest() throws SystemException, PortalException, ParseException, NoSuchFieldException, IllegalAccessException {
        this.setupBasicDataset();
        GlobalContestPointsSimulator.initSimulatorWithTestEnvironment(this);
        GlobalContestPointsSimulator gcs = new GlobalContestPointsSimulator();
        for (int j = 0; j < 50; j++) {
            gcs.initializeContests(
                    200,
                    20000,
                    false,
                    20,
                    2,
                    5,
                    0.02,
                    0.40,
                    0.04
            );
            //Test 50 different distributions per contest
            for (int i = 0; i < 50; i++) {
                gcs.setPointsDistributions(0.3, 0.6, 0.2);
                try {
                    gcs.runPointDistributionAlgorithm();
                } catch (StackOverflowError e) {
                    //here, a stack overflow exception might occur. set breakpoint here to inspect the system setup
                    e.printStackTrace();
                }
                gcs.assertPointDistributions();
                //reset distribution for next run
                gcs.deletePointDistributions();
            }
            //reset
            gcs.deleteContestsAndProposals();
        }
    }

    private void performRandomTest(boolean hasContestEnded) throws SystemException, PortalException, ParseException, NoSuchFieldException, IllegalAccessException {
        this.setupBasicDataset();
        GlobalContestPointsSimulator.initSimulatorWithTestEnvironment(this);
        GlobalContestPointsSimulator gcs = new GlobalContestPointsSimulator();
        int amountOfUsers = GlobalContestSimulator.randomInt(50, 1500);
        for (int j = 0; j < 50; j++) {
            gcs.initializeContests(
                    amountOfUsers,
                    GlobalContestSimulator.randomInt(0, 50000),
                    hasContestEnded,
                    GlobalContestSimulator.randomInt(0, 50),
                    GlobalContestSimulator.randomInt(0, 15),
                    GlobalContestSimulator.randomInt(0, 50),
                    new Double(GlobalContestSimulator.randomInt(0, 100))/100.0,
                    new Double(GlobalContestSimulator.randomInt(0, 100))/100.0,
                    new Double(GlobalContestSimulator.randomInt(0, 100))/100.0
            );
            //Test 50 different distributions per contest
            for (int i = 0; i < 50; i++) {
                gcs.setPointsDistributions(
                        new Double(GlobalContestSimulator.randomInt(0, 100))/100.0,
                        new Double(GlobalContestSimulator.randomInt(0, 100))/100.0,
                        new Double(GlobalContestSimulator.randomInt(0, 100))/100.0
                );
                //set distribution targets
                if (hasContestEnded) {
                    if (gcs.doWithProbability(0.5)) {
                        gcs.setPointDistributionTargets();
                    }
                }

                try {
                    gcs.runPointDistributionAlgorithm();
                } catch (StackOverflowError e) {
                    //here, a stack overflow exception might occur. set breakpoint here to inspect the system setup
                    e.printStackTrace();
                }
                gcs.assertPointDistributions();
                //reset distribution for next run
                gcs.deletePointDistributions();
            }
            //reset
            gcs.deleteContestsAndProposals();
        }
    }

    @Test
    public void randomHypotheticalTest() throws SystemException, PortalException, ParseException, NoSuchFieldException, IllegalAccessException {
        this.performRandomTest(false);
    }
    @Test
    public void randomMaterializedTest() throws SystemException, PortalException, ParseException, NoSuchFieldException, IllegalAccessException {
        this.performRandomTest(true);
    }
}
