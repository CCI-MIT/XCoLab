package com.ext.portlet.service.impl;

import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.*;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertTrue;

/**
 * Created by Manuel Thurner on 22/09/14.
 */
public class GlobalContestSimulator {
    private static XCoLabTest testInstance;

    private int contestPhaseIdCount = 0;
    private Map<Integer, List<Proposal>> sideProposals;
    private List<Proposal> globalProposals;
    private List<Proposal> globalProposalsInLastPhase;
    private Contest globalContest;
    private List<Contest> sideContests;
    private List<User> users;
    protected static SimpleDateFormat dateFormat;
    protected static Random random;
    private Map<Integer, List<User>> globalProposalsTeamMembers;
    private Map<Integer, DistributionConfiguration> globalProposalsDistributions;
    private Map<Integer, Map<Integer, List<User>>> sideProposalsTeamMembers;
    private Map<Integer, Map<Integer, DistributionConfiguration>> sideProposalsDistributions;

    private int amountOfUsers;
    private double pointsToBeDistributed;
    private boolean hasContestEnded;
    private int amountOfGlobalProposals;
    private int amountOfSideContests;
    private int amountOfProposalsPerSideContest;
    private double probabilityToLinkToOtherProposal;
    private double probabilityOfBeingAdvancedToNextPhase;
    private double probabilityOfAdditionalTeamMember;

    private double probabilityOfPointsDistributionAdditionalNonTeamMembers;

    class DistributionConfiguration {
        //the teamMemberShares indizes are the indizes in the proposalTeamMembers field
        public Map<Integer, Double> teamMemberShares = new HashMap<Integer, Double>();
        //in additionalShares, the indizes are the indizes of the users field
        public Map<Integer, Double> additionalShares = new HashMap<Integer, Double>();
    }

    public static void initSimulatorWithTestEnvironment(XCoLabTest testInstance) {
        GlobalContestSimulator.testInstance = testInstance;
        dateFormat = new SimpleDateFormat("yyyy-M-d H:m:s", Locale.ENGLISH);
        random = new Random();

    }

    public GlobalContestSimulator(
            int amountOfUsers,
            double pointsToBeDistributed,
            boolean hasContestEnded,
            int amountOfGlobalProposals,
            int amountOfSideContests,
            int amountOfProposalsPerSideContest,
            double probabilityToLinkToOtherProposal,
            double probabilityOfBeingAdvancedToNextPhase,
            double probabilityOfAdditionalTeamMember
    ) throws SystemException, PortalException, ParseException {
        this.amountOfUsers = amountOfUsers;
        this.pointsToBeDistributed = pointsToBeDistributed;
        this.hasContestEnded = hasContestEnded;
        this.amountOfGlobalProposals = amountOfGlobalProposals;
        this.amountOfSideContests = amountOfSideContests;
        this.amountOfProposalsPerSideContest = amountOfProposalsPerSideContest;
        this.probabilityToLinkToOtherProposal = probabilityToLinkToOtherProposal;
        this.probabilityOfBeingAdvancedToNextPhase = probabilityOfBeingAdvancedToNextPhase;
        this.probabilityOfAdditionalTeamMember = probabilityOfAdditionalTeamMember;

        this.createUsers();
        this.createGlobalContestAndProposals();
        this.createSideContestsAndProposals();
        this.createLinksBetweenProposals();
    }



    public void setPointsDistributions(
            double probabilityOfEmptyGlobalProposalConfiguration,
            double probabilityOfEmptySideProposalConfiguration,
            double probabilityOfPointsDistributionAdditionalNonTeamMembers
    ) throws NoSuchUserException, SystemException {
        this.probabilityOfPointsDistributionAdditionalNonTeamMembers = probabilityOfPointsDistributionAdditionalNonTeamMembers;

        //Global Proposals
        for (int i = 0; i < amountOfGlobalProposals; i++) {
            if (!doWithProbability(probabilityOfEmptyGlobalProposalConfiguration)) {
                DistributionConfiguration config = this.setPointsDistributionForProposal(globalProposals.get(i), globalProposalsTeamMembers.get(i), true);
                globalProposalsDistributions.put(i, config);
            }
        }

        //Side Proposals
        for (int i = 0; i < amountOfSideContests; i++) {
            for (int j = 0; j < amountOfProposalsPerSideContest; j++) {
                sideProposalsDistributions.put(i, new HashMap<Integer, DistributionConfiguration>());
                if (!doWithProbability(probabilityOfEmptySideProposalConfiguration)) {
                    DistributionConfiguration config = this.setPointsDistributionForProposal(sideProposals.get(i).get(j), sideProposalsTeamMembers.get(i).get(j), true);
                    sideProposalsDistributions.get(i).put(j, config);
                }
            }
        }
    }

    public void runDistributionAlgorithm() throws SystemException, PortalException {
        testInstance.pointsLocalService.distributePoints(globalContest.getContestPK());
    }



    private void createUsers() throws SystemException {
        users = new ArrayList<User>();
        for (int i = 0; i < amountOfUsers; i++) {
            users.add(testInstance.createUser(i));
        }
    }

    /**
     * Sets a random point distribution configuration for the given proposal and its team members.
     * Dependent on the fields probabilityOfPointsDistributionAdditionalNonTeamMembers and random dice throws,
     * a configuration is saved in the database and a DistributionConfiguration object returned, which can be
     * used for later verification of the distributed points.
     *
     * @param proposal
     * @param teamMembers
     * @param isGlobal
     * @return
     * @throws NoSuchUserException
     * @throws SystemException
     */
    private DistributionConfiguration setPointsDistributionForProposal(Proposal proposal, List<User> teamMembers, boolean isGlobal) throws NoSuchUserException, SystemException {
        DistributionConfiguration config = new DistributionConfiguration();

        //ANY TEAM MEMBER
        User author = teamMembers.get(0);
        double sumOfShares = 0.0;
        for (int i = 0; i < teamMembers.size(); i++) {
            User teamMember = teamMembers.get(i);
            //distribute randomly until the last member. he will get the rest
            double share;
            if (i < teamMembers.size()-1) {
                do {
                    share = generateProbabilityForTeamMembers(teamMembers.size());
                } while (share+sumOfShares > 1.0);
            } else {
                share = 1-sumOfShares;
            }

            config.teamMemberShares.put(i, share);

            testInstance.pointsDistributionConfigurationService.addDistributionConfiguration(
                    proposal.getProposalId(),
                    isGlobal ? 2 : 7, //team member point type
                    teamMember.getUserId(),
                    0L,
                    share,
                    author.getUserId()
            );
            sumOfShares += share;
        }
        assertTrue(sumOfShares == 1);

        sumOfShares = 0.0;
        //ANY NON-TEAM-MEMBER
        for (int i = 1; doWithProbability(probabilityOfPointsDistributionAdditionalNonTeamMembers/i); i++) {
            int randomUserIndex = randomInt(0, amountOfUsers);
            User randomUser = users.get(randomUserIndex);

            double share;
            do {
                share = generateProbabilityForTeamMembers(i);
            } while (share+sumOfShares > 1.0);
            if (share > 0) {
                testInstance.pointsDistributionConfigurationService.addDistributionConfiguration(
                        proposal.getProposalId(),
                        isGlobal ? 5 : 8, //any non-team-member
                        randomUser.getUserId(),
                        0L,
                        share,
                        author.getUserId()
                );
                config.additionalShares.put(randomUserIndex, share);
                sumOfShares += share;
            }
        }
        assertTrue(sumOfShares <= 1);

        if (sumOfShares > 0 && sumOfShares < 1) {
            int randomUserIndex = randomInt(0, amountOfUsers);
            User randomUser = users.get(randomUserIndex);
            double share = 1-sumOfShares;
            //add another one to make the sum total 1
            testInstance.pointsDistributionConfigurationService.addDistributionConfiguration(
                    proposal.getProposalId(),
                    isGlobal ? 5 : 8, //any non-team-member
                    randomUser.getUserId(),
                    0L,
                    share,
                    author.getUserId()
            );
            config.additionalShares.put(randomUserIndex, share);
            sumOfShares += share;
        }

        assertTrue(sumOfShares == 1);

        return config;
    }

    private List<User> setTeamMembers(Proposal proposal, User author) throws SystemException, PortalException {
        //sometimes the admin user is still in the user group
        testInstance.userLocalService.deleteGroupUser(proposal.getGroupId(), testInstance.adminId);

        List<User> teamMembers = new ArrayList<User>();
        teamMembers.add(author);
        for (int i = 1; doWithProbability(probabilityOfAdditionalTeamMember/i); i++) {
            teamMembers.add(users.get(randomInt(0, amountOfUsers)));
        }
        testInstance.userLocalService.addGroupUsers(proposal.getGroupId(), teamMembers);

        return teamMembers;
    }

    private void createGlobalContestAndProposals() throws SystemException, PortalException, ParseException {
        //create global contest
        globalContest = testInstance.contestLocalService.createNewContest(testInstance.adminId, "Test-Global-Contest");
        globalContest.setPoints(pointsToBeDistributed);
        globalContest.setDefaultParentPointType(1);
        testInstance.contestLocalService.updateContest(globalContest);
        //create phases
        ContestPhase gCp1 = createContestPhase(globalContest, 1, false, "PROMOTE_DONE",  "2014-08-01 00:00:00", "2014-08-10 00:00:00");
        ContestPhase gCp2 = createContestPhase(globalContest, 16, true, "PROMOTE_DONE",  "2014-08-10 00:00:01", "2014-08-14 00:00:00");
        ContestPhase gCp3 = createContestPhase(globalContest, 18, false, "PROMOTE_DONE", "2014-08-14 00:00:01", "2014-08-16 00:00:00");
        ContestPhase gCp4 = createContestPhase(globalContest, 19, true, "PROMOTE_DONE",  "2014-08-16 00:00:01", "2014-08-20 00:00:00");
        ContestPhase gCp5 = createContestPhase(globalContest, 15, false, "PROMOTE_DONE", "2014-08-20 00:00:01", "2015-09-24 00:00:00");
        ContestPhase gCp6;
        if (hasContestEnded) {
            gCp6 = createContestPhase(globalContest, 17, false, "", "2015-09-20 00:00:01", null);
        } else {
            gCp6 = createContestPhase(globalContest, 17, false, "", "2015-09-30 00:00:01", null);
        }


        //create proposals, authored by a random user, advance them with a probability to the next phases
        globalProposals = new ArrayList<Proposal>();
        for (int i = 0; i < amountOfGlobalProposals; i++) {
            User author = users.get(randomInt(0, amountOfUsers));
            Proposal proposal = testInstance.proposalLocalService.create(author.getUserId(), gCp1.getContestPhasePK());

            //create team members
            globalProposalsTeamMembers.put(i, setTeamMembers(proposal, author));
            globalProposals.add(proposal);

            //copy to first phase
            copyProposalToPhase(proposal, gCp2);
            //copy half of the proposals to other phases
            if (doWithProbability(probabilityOfBeingAdvancedToNextPhase)) {
                copyProposalToPhase(proposal, gCp3);
                copyProposalToPhase(proposal, gCp4);
                if (doWithProbability(probabilityOfBeingAdvancedToNextPhase)) {
                    copyProposalToPhase(proposal, gCp5);
                    if (hasContestEnded) {
                        copyProposalToPhase(proposal, gCp6);
                    }
                    globalProposalsInLastPhase.add(proposal);
                }
            }
        }
    }

    private void createSideContestsAndProposals() throws SystemException, PortalException, ParseException {
        //create side contests
        sideProposals = new HashMap<Integer, List<Proposal>>();
        sideContests = new ArrayList<Contest>();
        for (int i = 0; i < amountOfSideContests; i++) {
            Contest sideContest = testInstance.contestLocalService.createNewContest(testInstance.adminId, "Test-Side-Contest-"+(i+1));
            sideContest.setPoints(0);
            sideContest.setDefaultParentPointType(6);
            testInstance.contestLocalService.updateContest(sideContest);
            //create phases
            ContestPhase sCp1 = createContestPhase(sideContest, 1, false, "PROMOTE_DONE", "2014-08-01 00:00:00", "2014-08-10 00:00:00");
            ContestPhase sCp2 = createContestPhase(sideContest, 16, true, "PROMOTE_DONE", "2014-08-10 00:00:01", "2014-08-14 00:00:00");
            ContestPhase sCp3 = createContestPhase(sideContest, 18, false, "PROMOTE_DONE", "2014-08-14 00:00:01", "2014-08-16 00:00:00");
            ContestPhase sCp4 = createContestPhase(sideContest, 19, true, "PROMOTE_DONE", "2014-08-16 00:00:01", "2014-08-20 00:00:00");
            ContestPhase sCp5 = createContestPhase(sideContest, 15, false, "PROMOTE_DONE", "2014-08-20 00:00:01", "2015-09-24 00:00:00");
            ContestPhase sCp6;
            if (doWithProbability(0.5)) {
                //this contest did not expire yet.
                sCp6 = createContestPhase(sideContest, 17, false, "", "2015-09-24 00:00:01", null);
            } else {
                sCp6 = createContestPhase(sideContest, 17, false, "", "2015-09-24 00:00:01", null);
            }

            sideProposals.put(i, new ArrayList<Proposal>());
            sideProposalsTeamMembers.put(i, new HashMap<Integer, List<User>>());

            for (int j = 0; j < amountOfProposalsPerSideContest; j++) {
                User author = users.get(randomInt(0, amountOfUsers));
                Proposal proposal = testInstance.proposalLocalService.create(author.getUserId(), sCp1.getContestPhasePK());
                sideProposals.get(j).add(proposal);
                sideProposalsTeamMembers.get(j).put(i, setTeamMembers(proposal, author));

                //copy to second phase
                copyProposalToPhase(proposal, sCp2);
                if (doWithProbability(probabilityOfBeingAdvancedToNextPhase)) {
                    copyProposalToPhase(proposal, sCp3);
                    copyProposalToPhase(proposal, sCp4);
                    if (doWithProbability(probabilityOfBeingAdvancedToNextPhase)) {
                        copyProposalToPhase(proposal, sCp5);
                        if (hasContestEnded) {
                            copyProposalToPhase(proposal, sCp6);
                        }
                    }
                }
            }
            sideContests.add(sideContest);
        }
    }

    private void createLinksBetweenProposals() throws SystemException, PortalException {
        for (int i = 0; i < amountOfGlobalProposals; i++) {
            String sectionText = "These are the proposals we link to:\n";
            for (int j = 0; j < amountOfGlobalProposals; i++) {
                if (doWithProbability(probabilityToLinkToOtherProposal)) {
                    sectionText += "http://127.0.0.1:8080/web/guest/plans/-/plans/contestId/" + globalContest.getContestPK() + "/planId/" + globalProposals.get(j).getProposalId() + "\n\n";
                }
            }
            for (int j = 0; j < amountOfSideContests; j++) {
                for (int k = 0; k < amountOfProposalsPerSideContest; k++) {
                    if (doWithProbability(probabilityToLinkToOtherProposal)) {
                        sectionText += "http://127.0.0.1:8080/web/guest/plans/-/plans/contestId/" + sideContests.get(j).getContestPK() + "/planId/" + sideProposals.get(j).get(k).getProposalId() + "\n\n";
                    }
                }
            }

            //1300907 is the sub proposal plan section definition
            testInstance.proposalLocalService.setAttribute(globalProposals.get(i).getAuthorId(), globalProposals.get(i).getProposalId(), ProposalAttributeKeys.SECTION, 1300907L, sectionText);
        }
    }

    private ContestPhase createContestPhase(Contest c, long type, boolean fellowScreeningActive, String autoPromote, String startDate, String endDate) throws SystemException, ParseException {
        ContestPhase cp = testInstance.contestPhaseLocalService.createContestPhase(100000+contestPhaseIdCount++);
        cp.setContestPK(c.getContestPK());
        cp.setContestPhaseType(type);
        cp.setFellowScreeningActive(fellowScreeningActive);
        cp.setContestPhaseAutopromote(autoPromote);
        cp.setPhaseActiveOverride(false);
        cp.setPhaseInactiveOverride(false);
        cp.setPhaseStartDate(dateFormat.parse(startDate));
        if (endDate != null) {
            cp.setPhaseEndDate(dateFormat.parse(endDate));
        }
        testInstance.contestPhaseLocalService.updateContestPhase(cp);

        return cp;
    }

    private static void copyProposalToPhase(Proposal p, ContestPhase cp) throws SystemException {
        Proposal2Phase p2p = testInstance.proposal2PhaseLocalService.create(p.getProposalId(), cp.getContestPhasePK());
        p2p.setVersionFrom(1);
        p2p.setVersionTo(1);
        testInstance.proposal2PhaseLocalService.updateProposal2Phase(p2p);
    }

    //returns a random int between min and max (inclusive min, exclusive max)
    public static int randomInt(int min, int max) {
        return random.nextInt(max - min) + min;
    }

    public static boolean doWithProbability(double probability) {
        return (random.nextDouble() <= probability);
    }

    public static double generateProbabilityForTeamMembers(int teamSize) {
        //bias the probability towards getting 1
        //also take into account team size and divide by two thirds of the team size.
        return doWithProbability(0.1) ? 1 : random.nextDouble()/(teamSize*2/3);
    }

}
