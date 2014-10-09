package com.ext.portlet.service.impl;


import com.ext.portlet.ProposalContestPhaseAttributeKeys;
import com.ext.portlet.model.*;
import com.ext.portlet.service.ContestTeamMemberLocalService;
import com.ext.portlet.service.ContestTeamMemberLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import org.xcolab.utils.Clock;

import java.text.ParseException;
import java.util.*;

import static org.junit.Assert.assertTrue;

/**
 * Created by Manuel Thurner on 22/09/14.
 */
public class GlobalContestPhaseTransitionSimulator extends GlobalContestSimulator {
    private CustomClock testClock;
    protected Map<Integer, Contest> allContests;
    protected Map<Integer, List<User>> contestFellows;
    protected Map<Integer, List<User>> contestJudges;
    private static final long FELLOW_ROLE_ID = 193261;
    private static final long JUDGE_ROLE_ID = 1251483;

    class CustomClock implements Clock {
        private Date date;

        public void setDate(Date date) {
            this.date = date;
        }

        @Override
        public Date now() {
            return date;

        }
    }

    @Override
    public void initializeContests(int amountOfUsers,
                                   double pointsToBeDistributed,
                                   int startPhase,
                                   boolean areSideContestsTimedLikeGlobalContest,
                                   int amountOfGlobalProposals,
                                   int amountOfSideContests,
                                   int amountOfProposalsPerSideContest,
                                   double probabilityToLinkToOtherProposal,
                                   double probabilityOfBeingAdvancedToNextPhase,
                                   double probabilityOfAdditionalTeamMember
    ) throws SystemException, PortalException, ParseException {
        super.initializeContests(
                amountOfUsers, pointsToBeDistributed, startPhase, areSideContestsTimedLikeGlobalContest,
                amountOfGlobalProposals, amountOfSideContests, amountOfProposalsPerSideContest,
                probabilityToLinkToOtherProposal, probabilityOfBeingAdvancedToNextPhase, probabilityOfAdditionalTeamMember);

        allContests = new LinkedHashMap<Integer, Contest>();
        allContests.put(-1, globalContest);
        for (int i = 0; i < sideContests.size(); i++) {
            allContests.put(i, sideContests.get(i));
        }
    }

    public GlobalContestPhaseTransitionSimulator() {
        this.testClock = new CustomClock();
        testClock.date = new Date();
        testInstance.contestPhaseLocalService.overrideClock((Clock)testClock);
    }

    public static User getRandomUserNotInList(List<User> users) {
        User u = users.get(randomInt(0, users.size()));
        if (users.contains(u)) {
            return getRandomUserNotInList(users);
        } else {
            return u;
        }
    }

    public void setupJudgesAndFellowsForContests() throws SystemException {
        //Set judges and fellows for every contest
        for (Integer cI: allContests.keySet()) {
            Contest contest = allContests.get(cI);

            //fellows
            List<User> fellows = new ArrayList<>();
            do {
                User randomUser = getRandomUserNotInList(fellows);
                fellows.add(randomUser);
                testInstance.userLocalService.addRoleUser(FELLOW_ROLE_ID, randomUser.getUserId());
                testInstance.contestTeamMemberLocalService.addContestTeamMember(randomUser.getUserId(), contest.getContestPK(), "Fellow");
            } while (doWithProbability(0.6));

            //judges
            List<User> judges = new ArrayList<>();
            do {
                User randomUser = getRandomUserNotInList(judges);
                judges.add(randomUser);
                testInstance.userLocalService.addRoleUser(JUDGE_ROLE_ID, randomUser.getUserId());
                testInstance.contestTeamMemberLocalService.addContestTeamMember(randomUser.getUserId(), contest.getContestPK(), "Judge");
            } while (doWithProbability(0.6));

            contestFellows.put(cI, fellows);
            contestJudges.put(cI, judges);
        }
    }

    public void advanceAllContestsToNextPhase() throws SystemException, PortalException {
        assertTrue(areSideContestsTimedLikeGlobalContest);


        Map<Integer, List<Proposal>> proposalsInCurrentPhase = new LinkedHashMap<>();
        Map<Integer, List<Proposal>> proposalsInNextPhase = new LinkedHashMap<>();

        for (Integer cI: allContests.keySet()) {
            Contest contest = allContests.get(cI);
            List<ContestPhase> contestPhases = cI >= 0 ? sideContestPhases.get(cI) : globalContestPhases;

            //assert current contest phase
            assertTrue(contestPhases.get(currentPhase - 1).getContestPhasePK() == testInstance.contestPhaseLocalService.getActivePhaseForContest(contest).getContestPhasePK());
            //get proposals in current phase
            proposalsInCurrentPhase.put(cI, testInstance.proposalLocalService.getProposalsInContestPhase(globalContestPhases.get(currentPhase - 1).getContestPhasePK()));

            if (currentPhase >= 6) {
                return;
            }

            if (currentPhase == 1 || currentPhase == 3) {
                //judging phases! Let's assign some judging results.
                proposalsInNextPhase.put(cI, this.createJudgingForContestPhase(proposalsInCurrentPhase.get(cI), contestPhases.get(currentPhase - 1)));
            } else {
                proposalsInNextPhase.put(cI, proposalsInCurrentPhase.get(cI));
            }
        }

        //add some time after the new phase start
        testClock.setDate(getDatePlusNMinutes(globalContestPhases.get(currentPhase).getPhaseStartDate(), 10));
        //run the autoPromote mechanism
        testInstance.contestPhaseLocalService.autoPromoteProposals();
    }

    private List<Proposal> createJudgingForContestPhase(List<Proposal> proposals, Integer contestIndex, ContestPhase contestPhase) throws SystemException, PortalException {
        //Judging: If a proposal is not judged by judges, but set by fellows to be advanced to judges, the proposal prohibits the promotion of the contest.
        for (Proposal p : proposals) {
            /* set fellow action
            possible fellow actions:

            NO_DECISION(0, "No decision made yet", false, false),
            INCOMPLETE(1, "Do Not Advance: incomplete", true, false),
            OFFTOPIC(2, "Do Not Advance: off-topic", true, false),
            PASS_TO_JUDGES(3, "Advance", false, true);

             */
            if (doWithProbability(0.8)) {
                int fellowAction = randomInt(0, 4);
                User judgingFellow = contestFellows.get(contestIndex).get(randomInt(0, contestFellows.get(contestIndex).size()));
                testInstance.proposalLocalService.setAttribute(judgingFellow.getUserId(), p.getProposalId(), ProposalContestPhaseAttributeKeys.FELLOW_ACTION, fellowAction);

                if (fellowAction == 3) {
                    Set<Long> selectedJudgeIds = new HashSet<>();
                    do {
                        selectedJudgeIds.add(contestJudges.get(contestIndex).get(randomInt(0, contestJudges.get(contestIndex).size())).getUserId());
                    } while (doWithProbability(0.7));
                    /*set judging action: select judges to be forwarded:*/
                    testInstance.proposalContestPhaseAttributeLocalService.persistSelectedJudgesAttribute(
                            p.getProposalId(),
                            contestPhase.getContestPhasePK(),
                            new ArrayList<Long>(selectedJudgeIds)
                    );

                    //now actually set the judges' ratings


                }
            }
        }
    }

    private static Date getDatePlusNMinutes(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, GlobalContestSimulator.randomInt(1, n));

        return cal.getTime();
    }

}
