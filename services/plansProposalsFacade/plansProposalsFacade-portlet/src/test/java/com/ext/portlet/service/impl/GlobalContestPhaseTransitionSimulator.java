package com.ext.portlet.service.impl;


/**
 * Created by Manuel Thurner on 22/09/14.
 */
public class GlobalContestPhaseTransitionSimulator extends GlobalContestSimulator {
//    private CustomClock testClock;
//    protected Map<Integer, Contest> allContests;
//    protected Map<Integer, Boolean> contestIsBlockedFromAdvancement;
//    protected Map<Integer, List<User>> contestFellows;
//    protected Map<Integer, List<User>> contestJudges;
//    private static final long FELLOW_ROLE_ID = 193261;
//    private static final long JUDGE_ROLE_ID = 1251483;
//
//    class CustomClock implements Clock {
//        private Date date;
//
//        public void setDate(Date date) {
//            this.date = date;
//        }
//
//        @Override
//        public Date now() {
//            return date;
//
//        }
//    }
//
//    @Override
//    public void initializeContests(int amountOfUsers,
//                                   double pointsToBeDistributed,
//                                   int startPhase,
//                                   boolean areSideContestsTimedLikeGlobalContest,
//                                   int amountOfGlobalProposals,
//                                   int amountOfSideContests,
//                                   int amountOfProposalsPerSideContest,
//                                   double probabilityToLinkToOtherProposal,
//                                   double probabilityOfBeingAdvancedToNextPhase,
//                                   double probabilityOfAdditionalTeamMember
//    ) throws SystemException, PortalException, ParseException {
//        super.initializeContests(
//                amountOfUsers, pointsToBeDistributed, startPhase, areSideContestsTimedLikeGlobalContest,
//                amountOfGlobalProposals, amountOfSideContests, amountOfProposalsPerSideContest,
//                probabilityToLinkToOtherProposal, probabilityOfBeingAdvancedToNextPhase, probabilityOfAdditionalTeamMember);
//
//        allContests = new LinkedHashMap<Integer, Contest>();
//        allContests.put(-1, globalContest);
//        for (int i = 0; i < sideContests.size(); i++) {
//            allContests.put(i, sideContests.get(i));
//        }
//        contestIsBlockedFromAdvancement = new HashMap<>();
//    }
//
//    public GlobalContestPhaseTransitionSimulator() {
//        this.testClock = new CustomClock();
//        testClock.date = new Date();
//        testInstance.contestPhaseLocalService.overrideClock((Clock)testClock);
//    }
//
//    @Override
//    public void cleanupPointsSimulator() throws SystemException, PortalException {
//        super.cleanupPointsSimulator();
//        //reset date
//        testClock.date = new Date();
//        for (Integer cI: allContests.keySet()) {
//            for (User u : contestFellows.get(cI)) {
//                testInstance.userLocalService.deleteRoleUser(FELLOW_ROLE_ID, u.getUserId());
//            }
//            for (User u : contestJudges.get(cI)) {
//                testInstance.userLocalService.deleteRoleUser(JUDGE_ROLE_ID, u.getUserId());
//            }
//        }
//        for (ContestTeamMember ctm : testInstance.contestTeamMemberLocalService.getContestTeamMembers(0, Integer.MAX_VALUE)) {
//            testInstance.contestTeamMemberLocalService.delete(ctm);
//        }
//
//
//    }
//
//    public User getRandomUserNotInList(List<User> users) {
//        User u = this.users.get(randomInt(0, this.users.size()));
//        if (users.contains(u)) {
//            return getRandomUserNotInList(users);
//        } else {
//            return u;
//        }
//    }
//
//    public void setupJudgesAndFellowsForContests() throws SystemException {
//        contestFellows = new HashMap<>();
//        contestJudges = new HashMap<>();
//        //Set judges and fellows for every contest
//        for (Integer cI: allContests.keySet()) {
//            Contest contest = allContests.get(cI);
//
//            //fellows
//            List<User> fellows = new ArrayList<>();
//            do {
//                User randomUser = getRandomUserNotInList(fellows);
//                fellows.add(randomUser);
//                testInstance.userLocalService.addRoleUser(FELLOW_ROLE_ID, randomUser.getUserId());
//                testInstance.contestTeamMemberLocalService.addContestTeamMember(randomUser.getUserId(), contest.getContestPK(), MemberRole.FELLOW);
//            } while (doWithProbability(0.6));
//
//            //judges
//            List<User> judges = new ArrayList<>();
//            do {
//                User randomUser = getRandomUserNotInList(judges);
//                judges.add(randomUser);
//                testInstance.userLocalService.addRoleUser(JUDGE_ROLE_ID, randomUser.getUserId());
//                testInstance.contestTeamMemberLocalService.addContestTeamMember(randomUser.getUserId(), contest.getContestPK(), MemberRole.JUDGE);
//
//
//            } while (doWithProbability(0.6));
//
//            contestFellows.put(cI, fellows);
//            contestJudges.put(cI, judges);
//        }
//    }
//
//    private void advanceThroughConcurrentTask() {
//        //spawn 2 concurrent processes
//        final Date targetTime = getDatePlusNMinutes(new Date(), 1);
//        Runnable r = new Runnable() {
//            @Override
//            public void run() {
//                while(new Date().before(targetTime)) {
//                    try {
//                        Thread.sleep(100);
//                        ProposalContestPhaseAutopromoteTask task = new ProposalContestPhaseAutopromoteTask();
//                        task.receive(null);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    } catch (MessageListenerException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        };
//        Thread t1 = new Thread(r);
//        Thread t2 = new Thread(r);
//
//        t1.start();
//        try {
//            Thread.sleep(50);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        t2.start();
//
//        //wait until the threads are done with their work
//        while(new Date().before(targetTime)) {
//            try {
//                Thread.sleep(150);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        t1.stop();
//        t2.stop();
//        return;
//    }
//
//    public void advanceAllContestsToNextPhase(boolean advanceThroughConcurrentTask) throws SystemException, PortalException {
//        assertTrue(areSideContestsTimedLikeGlobalContest);
//
//
//        Map<Integer, List<Proposal>> proposalsInCurrentPhase = new LinkedHashMap<>();
//        Map<Integer, List<Proposal>> proposalsInNextPhase = new LinkedHashMap<>();
//
//        for (Integer cI: allContests.keySet()) {
//            proposalsInNextPhase.put(cI, null);
//            if (contestIsBlockedFromAdvancement.get(cI) != null && contestIsBlockedFromAdvancement.get(cI)) {
//                continue;
//            }
//            Contest contest = allContests.get(cI);
//            List<ContestPhase> contestPhases = cI >= 0 ? sideContestPhases.get(cI) : globalContestPhases;
//
//            //assert current contest phase
//            assertTrue(contestPhases.get(currentPhase - 1).getContestPhasePK() == testInstance.contestPhaseLocalService.getActivePhaseForContest(contest).getContestPhasePK());
//            //get proposals in current phase
//            proposalsInCurrentPhase.put(cI, testInstance.proposalLocalService.getProposalsInContestPhase(contestPhases.get(currentPhase - 1).getContestPhasePK()));
//
//            if (currentPhase >= 6) {
//                return;
//            }
//
//            if (currentPhase == 2 || currentPhase == 4) {
//                //judging phases! Let's assign some judging results.
//                proposalsInNextPhase.put(cI, this.createJudgingForContestPhase(proposalsInCurrentPhase.get(cI), cI, contestPhases.get(currentPhase - 1)));
//            } else {
//                proposalsInNextPhase.put(cI, proposalsInCurrentPhase.get(cI));
//            }
//            //TODO: add some edits of the proposals if the phase is open at the moment
//        }
//
//        //add some time after the new phase start
//        testClock.setDate(getDatePlusNMinutes(globalContestPhases.get(currentPhase).getPhaseStartDate(), 10));
//        //run the autoPromote mechanism
//        if (advanceThroughConcurrentTask) {
//            this.advanceThroughConcurrentTask();
//        } else {
//            testInstance.contestPhaseLocalService.autoPromoteProposals();
//        }
//        currentPhase++;
//
//        //now assert, that the proposals in the next phase are those we calculated previously
//        for (Integer cI: allContests.keySet()) {
//            if (contestIsBlockedFromAdvancement.get(cI) != null && contestIsBlockedFromAdvancement.get(cI)) {
//                continue;
//            }
//            Contest contest = allContests.get(cI);
//            List<ContestPhase> contestPhases = cI >= 0 ? sideContestPhases.get(cI) : globalContestPhases;
//
//            //assert new phase is active
//            assertTrue(contestPhases.get(currentPhase - 1).getContestPhasePK() == testInstance.contestPhaseLocalService.getActivePhaseForContest(contest).getContestPhasePK());
//            List<Proposal> proposalsActuallyInNextPhase = testInstance.proposalLocalService.getProposalsInContestPhase(contestPhases.get(currentPhase - 1).getContestPhasePK());
//            for (Proposal p : proposalsInNextPhase.get(cI)) {
//                if (proposalsActuallyInNextPhase.contains(p)) {
//                    proposalsActuallyInNextPhase.remove(p);
//                } else {
//                    throw new AssertionError("Proposal "+p.getProposalId()+" of contest number "+cI+" is not in the list of advanced proposals!");
//                }
//            }
//            //by now, all proposals should have been removed.
//            assertTrue(proposalsActuallyInNextPhase.isEmpty());
//        }
//    }
//
//    private List<Proposal> createJudgingForContestPhase(List<Proposal> proposals, Integer contestIndex, ContestPhase contestPhase) throws SystemException, PortalException {
//        List<Proposal> proposalsToBeAdvanced = new ArrayList<Proposal>();
//        //Judging: If a proposal is not judged by judges, but set by fellows to be advanced to judges, the proposal prohibits the promotion of the contest.
//        for (Proposal p : proposals) {
//            /* set fellow action
//            possible fellow actions:
//
//            NO_DECISION(0, "No decision made yet", false, false),
//            INCOMPLETE(1, "Do Not Advance: incomplete", true, false),
//            OFFTOPIC(2, "Do Not Advance: off-topic", true, false),
//            NOT_ADVANCE_OTHER(2, "Do Not Advance: other", true, false),
//            PASS_TO_JUDGES(3, "Advance", false, true);
//
//             */
//            if (doWithProbability(0.8)) {
//                int fellowAction = randomInt(0, 5);
//                User judgingFellow = contestFellows.get(contestIndex).get(randomInt(0, contestFellows.get(contestIndex).size()));
//                testInstance.proposalAttributeLocalService.setAttribute(judgingFellow.getUserId(), p.getProposalId(), ProposalContestPhaseAttributeKeys.FELLOW_ACTION, fellowAction);
//
//                System.out.println("contestIndex: "+contestIndex+" proposal: "+p.getProposalId()+" fellowAction: "+fellowAction);
//
//                if (fellowAction == 3) {
//                    Set<Long> selectedJudgeIds = new HashSet<>();
//                    do {
//                        selectedJudgeIds.add(contestJudges.get(contestIndex).get(randomInt(0, contestJudges.get(contestIndex).size())).getUserId());
//                    } while (doWithProbability(0.7));
//                    /*set judging action: select judges to be forwarded:*/
//                    testInstance.proposalContestPhaseAttributeLocalService.persistSelectedJudgesAttribute(
//                            p.getProposalId(),
//                            contestPhase.getContestPhasePK(),
//                            new ArrayList<Long>(selectedJudgeIds)
//                    );
//                    System.out.println("contestIndex: "+contestIndex +" proposal: "+p.getProposalId()+" judges: "+selectedJudgeIds.size());
//
//                    //skip setting judging actions now. just set advance to true or false
//                    /* NO_DECISION(0, "No decision made yet"),
//                        DONT_MOVE_ON(1, "Do Not Advance"),
//                        MOVE_ON(2, "Advance"); */
//                    int advanceDecision = randomInt(0, 3);
//                    testInstance.proposalContestPhaseAttributeLocalService.persistAttribute(
//                            p.getProposalId(),
//                            contestPhase.getContestPhasePK(),
//                            ProposalContestPhaseAttributeKeys.JUDGE_DECISION,
//                            0,
//                            advanceDecision
//                    );
//                    System.out.println("contestIndex: "+contestIndex+ "proposal: "+p.getProposalId()+" advanceAction: "+advanceDecision);
//                    if (advanceDecision == 2) {
//                        proposalsToBeAdvanced.add(p);
//                    } else {
//                        //this contest cannot advance anymore due to missing judge input
//                        contestIsBlockedFromAdvancement.put(contestIndex, true);
//                    }
//
//                }
//            }
//        }
//        return proposalsToBeAdvanced;
//    }
//
//    private static Date getDatePlusNMinutes(Date date, int n) {
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(date);
//        cal.add(Calendar.MINUTE, GlobalContestSimulator.randomInt(1, n));
//
//        return cal.getTime();
//    }

}
