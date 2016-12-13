package com.ext.portlet.service.impl;


public class PointsTest extends XCoLabTest {

//    private static final int NUMBER_OF_FIXED_TEST_RUNS = 3;
//    private static final int NUMBER_OF_RANDOM_TEST_RUNS = 1;
//    /*
//     Useful commands and snippets for debugging:
//
//     Redeploy service layer:
//     cd $XCOLAB_HOME/services/plansProposalsFacade/plansProposalsFacade-portlet
//     mvn compile package liferay:deploy -DskipTests=true
//
//    Watches in Debugger Tab:
//
//    Get all points generated:
//    testInstance.pointsLocalService.getPointses(0, Integer.MAX_VALUE)
//
//     */
//
//    /**
//     * This method does a test using a non-finished global contest to only test the distribution of hypothetical points.
//     * The test only considers at max one recursion level in terms of subproposals and uses a fixed probability of proposal adavancements,
//     * linked subproposals and multiple proposal team members
//     *
//     *
//     *
//     * @throws SystemException
//     * @throws PortalException
//     * @throws ParseException
//     * @throws NoSuchFieldException
//     * @throws IllegalAccessException
//     */
//    @Test
//    public void fixedHypotheticalExtendedTest() throws SystemException, PortalException, ParseException, NoSuchFieldException, IllegalAccessException {
//        GlobalContestExtendedPointsSimulator.initSimulatorWithTestEnvironment(this);
//        GlobalContestExtendedPointsSimulator gcs = new GlobalContestExtendedPointsSimulator();
//        for (int j = 0; j < NUMBER_OF_FIXED_TEST_RUNS; j++) {
//            gcs.initializeContests(
//                    100,
//                    50000,
//                    false,
//                    4,
//                    5,
//                    6,
//                    30,
//                    0.2,
//                    0.30,
//                    0.15
//            );
//            //Test 50 different distributions per contest
//            for (int i = 0; i < NUMBER_OF_FIXED_TEST_RUNS; i++) {
//                gcs.setPointsDistributions(0.3, 0.6, 0.2);
//                try {
//                    gcs.runPointDistributionAlgorithm();
//                } catch (StackOverflowError e) {
//                    //here, a stack overflow exception might occur. set breakpoint here to inspect the system setup
//                    e.printStackTrace();
//                }
//                gcs.assertPointDistributions();
//                //reset distribution for next run
//                gcs.deletePointDistributions();
//            }
//            //reset
//            gcs.cleanupPointsSimulator();
//        }
//    }
//
//    @Test
//    public void fixedHypotheticalTest() throws SystemException, PortalException, ParseException, NoSuchFieldException, IllegalAccessException {
//        GlobalContestPointsSimulator.initSimulatorWithTestEnvironment(this);
//        GlobalContestExtendedPointsSimulator gcs = new GlobalContestExtendedPointsSimulator();
//        for (int j = 0; j < NUMBER_OF_FIXED_TEST_RUNS; j++) {
//            gcs.initializeContests(
//                    200,
//                    20000,
//                    false,
//                    2,
//                    20,
//                    2,
//                    5,
//                    0.2,
//                    0.40,
//                    0.25
//            );
//            //Test 50 different distributions per contest
//            for (int i = 0; i < NUMBER_OF_FIXED_TEST_RUNS; i++) {
//                gcs.setPointsDistributions(0.3, 0.6, 0.2);
//                try {
//                    gcs.runPointDistributionAlgorithm();
//                } catch (StackOverflowError e) {
//                    //here, a stack overflow exception might occur. set breakpoint here to inspect the system setup
//                    e.printStackTrace();
//                }
//                gcs.assertPointDistributions();
//                //reset distribution for next run
//                gcs.deletePointDistributions();
//            }
//            //reset
//            gcs.cleanupPointsSimulator();
//        }
//    }
//
//    /**
//     * Performs a random points test, randomly setting the probability of proposal adavancements,
//     * linked subproposals and multiple proposal team members.
//     *
//     * @param hasContestEnded   Indicates whether materialized points should be distributed in additon to hypothetical ones
//     *
//     * @throws SystemException
//     * @throws PortalException
//     * @throws ParseException
//     * @throws NoSuchFieldException
//     * @throws IllegalAccessException
//     */
//    private void performRandomTest(boolean hasContestEnded) throws SystemException, PortalException, ParseException, NoSuchFieldException, IllegalAccessException {
//        GlobalContestPointsSimulator.initSimulatorWithTestEnvironment(this);
//        GlobalContestExtendedPointsSimulator gcs = new GlobalContestExtendedPointsSimulator();
//        int amountOfUsers = GlobalContestSimulator.randomInt(50, 1500);
//        for (int j = 0; j < NUMBER_OF_RANDOM_TEST_RUNS; j++) {
//            gcs.initializeContests(
//                    amountOfUsers,
//                    GlobalContestSimulator.randomInt(0, 50000),
//                    hasContestEnded,
//                    2,
//                    GlobalContestSimulator.randomInt(0, 50),
//                    GlobalContestSimulator.randomInt(0, 15),
//                    GlobalContestSimulator.randomInt(0, 50),
//                    new Double(GlobalContestSimulator.randomInt(0, 100))/100.0,
//                    new Double(GlobalContestSimulator.randomInt(0, 100))/100.0,
//                    new Double(GlobalContestSimulator.randomInt(0, 100))/100.0
//            );
//            //Test 50 different distributions per contest
//            for (int i = 0; i < NUMBER_OF_RANDOM_TEST_RUNS; i++) {
//                gcs.setPointsDistributions(
//                        new Double(GlobalContestSimulator.randomInt(0, 100))/100.0,
//                        new Double(GlobalContestSimulator.randomInt(0, 100))/100.0,
//                        new Double(GlobalContestSimulator.randomInt(0, 100))/100.0
//                );
//                //set distribution targets
//                if (hasContestEnded) {
//                    if (gcs.doWithProbability(0.5)) {
//                        gcs.setPointDistributionTargets();
//                    }
//                }
//
//                try {
//                    gcs.runPointDistributionAlgorithm();
//                } catch (StackOverflowError e) {
//                    //here, a stack overflow exception might occur. set breakpoint here to inspect the system setup
//                    e.printStackTrace();
//                }
//                gcs.assertPointDistributions();
//                //reset distribution for next run
//                gcs.deletePointDistributions();
//            }
//            //reset
//            gcs.cleanupPointsSimulator();
//        }
//    }
//
//    /**
//     * Performs a random points test, randomly setting the probability of proposal adavancements,
//     * linked subproposals and multiple proposal team members.
//     *
//     * @param hasContestEnded   Indicates whether materialized points should be distributed in additon to hypothetical ones
//     *
//     * @throws SystemException
//     * @throws PortalException
//     * @throws ParseException
//     * @throws NoSuchFieldException
//     * @throws IllegalAccessException
//     */
//    private void performRandomExtendedTest(boolean hasContestEnded) throws SystemException, PortalException, ParseException, NoSuchFieldException, IllegalAccessException {
//        GlobalContestPointsSimulator.initSimulatorWithTestEnvironment(this);
//        GlobalContestExtendedPointsSimulator gcs = new GlobalContestExtendedPointsSimulator();
//        int amountOfUsers = GlobalContestSimulator.randomInt(50, 1500);
//        for (int j = 0; j < NUMBER_OF_RANDOM_TEST_RUNS; j++) {
//            gcs.initializeContests(
//                    amountOfUsers,
//                    GlobalContestSimulator.randomInt(0, 50000),
//                    hasContestEnded,
//                    4,
//                    GlobalContestSimulator.randomInt(0, 50),
//                    GlobalContestSimulator.randomInt(0, 15),
//                    GlobalContestSimulator.randomInt(0, 50),
//                    new Double(GlobalContestSimulator.randomInt(0, 100))/100.0,
//                    new Double(GlobalContestSimulator.randomInt(0, 100))/100.0,
//                    new Double(GlobalContestSimulator.randomInt(0, 100))/100.0
//            );
//            //Test 50 different distributions per contest
//            for (int i = 0; i < NUMBER_OF_RANDOM_TEST_RUNS; i++) {
//                gcs.setPointsDistributions(
//                        new Double(GlobalContestSimulator.randomInt(0, 100))/100.0,
//                        new Double(GlobalContestSimulator.randomInt(0, 100))/100.0,
//                        new Double(GlobalContestSimulator.randomInt(0, 100))/100.0
//                );
//                //set distribution targets
//                if (hasContestEnded) {
//                    if (gcs.doWithProbability(0.5)) {
//                        gcs.setPointDistributionTargets();
//                    }
//                }
//
//                try {
//                    gcs.runPointDistributionAlgorithm();
//                } catch (StackOverflowError e) {
//                    //here, a stack overflow exception might occur. set breakpoint here to inspect the system setup
//                    e.printStackTrace();
//                }
//                gcs.assertPointDistributions();
//                //reset distribution for next run
//                gcs.deletePointDistributions();
//            }
//            //reset
//            gcs.cleanupPointsSimulator();
//        }
//    }
//
//    /**
//     * Performs a random (hypothetical only) points test, randomly setting the probability of proposal adavancements,
//     * linked subproposals and multiple proposal team members.
//     *
//     * @throws SystemException
//     * @throws PortalException
//     * @throws ParseException
//     * @throws NoSuchFieldException
//     * @throws IllegalAccessException
//     */
//    @Test
//    public void randomHypotheticalTest() throws SystemException, PortalException, ParseException, NoSuchFieldException, IllegalAccessException {
//        this.performRandomTest(false);
//    }
//
//    /**
//     * Performs a random points test, randomly setting the probability of proposal adavancements,
//     * linked subproposals and multiple proposal team members.
//     *
//     * @throws SystemException
//     * @throws PortalException
//     * @throws ParseException
//     * @throws NoSuchFieldException
//     * @throws IllegalAccessException
//     */
//    @Test
//    public void randomMaterializedTest() throws SystemException, PortalException, ParseException, NoSuchFieldException, IllegalAccessException {
//        this.performRandomTest(true);
//    }
//
//    @Test
//    public void randomHypotheticalExtendedTest() throws NoSuchFieldException, PortalException, IllegalAccessException, ParseException, SystemException {
//        performRandomExtendedTest(false);
//    }
//
//    @Test
//    public void randomMaterializedExtendedTest() throws NoSuchFieldException, PortalException, IllegalAccessException, ParseException, SystemException {
//        performRandomExtendedTest(true);
//    }
}
