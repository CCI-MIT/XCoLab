package com.ext.portlet.service.impl;

import org.xcolab.client.proposals.enums.ProposalAttributeKeys;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.model.ProposalAttribute;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * Created by Manuel Thurner on 22/09/14.
 */
public class GlobalContestSimulator {
    protected static XCoLabTest testInstance;
    private static final long DAY_IN_MS = 1000 * 60 * 60 * 24;
    protected static SimpleDateFormat dateFormat;
    protected static Random random;

    protected int contestPhaseIdCount;
    protected Map<Integer, List<Proposal>> sideProposals;
    protected List<Proposal> globalProposals;
    protected List<Integer> globalProposalsInLastPhase;
    protected Map<Integer, List<Integer>> sideProposalsInLastPhase;

    protected Contest globalContest;
    protected List<Contest> sideContests;
    protected List<User> users;

    protected Map<Integer, List<User>> globalProposalsTeamMembers;
    protected Map<Integer, Map<Integer, List<User>>> sideProposalsTeamMembers;

    protected List<ContestPhase> globalContestPhases;
    protected Map<Integer, List<ContestPhase>> sideContestPhases;

    protected Map<Integer, List<Integer>> globalProposalLinksToGlobalProposals;
    protected Map<Integer, Map<Integer, List<Integer>>> globalProposalLinksToSideProposals;

    protected int amountOfUsers;
    protected double pointsToBeDistributed;
    protected boolean hasContestEnded;
    protected boolean areSideContestsTimedLikeGlobalContest;
    protected int startPhase;
    protected int currentPhase;
    protected int amountOfGlobalProposals;
    protected int amountOfSideContests;
    protected int amountOfProposalsPerSideContest;
    protected double probabilityToLinkToOtherProposal;
    protected double probabilityOfBeingAdvancedToNextPhase;
    protected double probabilityOfAdditionalTeamMember;



    public static void initSimulatorWithTestEnvironment(XCoLabTest testInstance) {
        GlobalContestSimulator.testInstance = testInstance;
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        random = new Random();

    }

    public GlobalContestSimulator() {
    }

    public void initializeContests(int amountOfUsers,
                                   double pointsToBeDistributed,
                                   boolean hasContestEnded,
                                   int amountOfGlobalProposals,
                                   int amountOfSideContests,
                                   int amountOfProposalsPerSideContest,
                                   double probabilityToLinkToOtherProposal,
                                   double probabilityOfBeingAdvancedToNextPhase,
                                   double probabilityOfAdditionalTeamMember
    ) throws SystemException, PortalException, ParseException {
        this.initializeContests(amountOfUsers,
        pointsToBeDistributed,
        hasContestEnded ? 6 : 5,
        false,
        amountOfGlobalProposals,
        amountOfSideContests,
        amountOfProposalsPerSideContest,
        probabilityToLinkToOtherProposal,
        probabilityOfBeingAdvancedToNextPhase,
        probabilityOfAdditionalTeamMember);
    }

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
        this.amountOfUsers = amountOfUsers;
        this.pointsToBeDistributed = pointsToBeDistributed;
        this.areSideContestsTimedLikeGlobalContest = areSideContestsTimedLikeGlobalContest;
        this.startPhase = startPhase;
        this.currentPhase = startPhase;
        this.hasContestEnded = (startPhase == 6);
        this.amountOfGlobalProposals = amountOfGlobalProposals;
        this.amountOfSideContests = amountOfSideContests;
        this.amountOfProposalsPerSideContest = amountOfProposalsPerSideContest;
        this.probabilityToLinkToOtherProposal = probabilityToLinkToOtherProposal;
        this.probabilityOfBeingAdvancedToNextPhase = probabilityOfBeingAdvancedToNextPhase;
        this.probabilityOfAdditionalTeamMember = probabilityOfAdditionalTeamMember;

        if (users == null || users.isEmpty()) {
            this.createUsers();
        }
        this.createGlobalContestAndProposals();
        this.createSideContestsAndProposals();
        this.createLinksBetweenProposals();
    }

    public void cleanupPointsSimulator() throws SystemException, PortalException {
        //delete all contestPhases
        for (ContestPhase cp : globalContestPhases) {
            XCoLabTest.contestPhaseLocalService.deleteContestPhase(cp);
        }
        for (int j = 0; j < sideContests.size(); j++) {
            for (ContestPhase cp : sideContestPhases.get(j)) {
                XCoLabTest.contestPhaseLocalService.deleteContestPhase(cp);
            }
        }
        globalContestPhases = null;

        //delete links between proposals
        List<ProposalAttribute> proposalAttributes = XCoLabTest.proposalAttributeLocalService.getProposalAttributes(0, Integer.MAX_VALUE);
        for (ProposalAttribute pa: proposalAttributes) {
            XCoLabTest.proposalAttributeLocalService.deleteProposalAttribute(pa);
        }
        globalProposalLinksToGlobalProposals = null;
        globalProposalLinksToSideProposals = null;

        //delete Proposal 2 phases
        List<Proposal2Phase> p2ps = XCoLabTest.proposal2PhaseLocalService.getProposal2Phases(0, Integer.MAX_VALUE);
        for (Proposal2Phase p2p: p2ps) {
            XCoLabTest.proposal2PhaseLocalService.deleteProposal2Phase(p2p);
        }

        //Delete proposals
        for (int i = 0; i < globalProposals.size(); i++) {
            Proposal p = globalProposals.get(i);
            XCoLabTest.userLocalService.deleteGroupUsers(p.getGroupId(), globalProposalsTeamMembers.get(i));
            XCoLabTest.proposalLocalService.deleteProposal(p);
        }
        for (int j = 0; j < sideContests.size(); j++) {
            for (int i = 0; i < amountOfProposalsPerSideContest; i++) {
                Proposal p = sideProposals.get(j).get(i);
                XCoLabTest.userLocalService.deleteGroupUsers(p.getGroupId(), sideProposalsTeamMembers.get(j).get(i));
                XCoLabTest.proposalLocalService.deleteProposal(p);
            }
        }

        //delete contests
        XCoLabTest.contestLocalService.deleteContest(globalContest);
        globalContest = null;
        for (Contest sideContest : sideContests) {
            XCoLabTest.contestLocalService.deleteContest(sideContest);
        }
        sideContests = null;
    }


    protected void createUsers() throws SystemException, PortalException {
        if (users != null && !users.isEmpty()) {
            deleteUsers();
        }

        users = testInstance.createUsers(amountOfUsers);
    }

    protected void deleteUsers() throws PortalException, SystemException {
        testInstance.deleteUsers();
    }


    protected List<User> setTeamMembers(Proposal proposal, User author) throws SystemException, PortalException {
        //sometimes the admin user is still in the user group
        XCoLabTest.userLocalService.deleteGroupUser(proposal.getGroupId(), XCoLabTest.adminId);

        // Use a set in order to prevent multiple user entries of the same user in a group
        Set<User> teamMembersSet = new HashSet<>();
        teamMembersSet.add(author);
        for (int i = 1; doWithProbability(probabilityOfAdditionalTeamMember/i); i++) {
            teamMembersSet.add(users.get(randomInt(0, amountOfUsers)));
        }
        List<User> teamMembersList = new ArrayList<>();
        teamMembersList.addAll(teamMembersSet);
        XCoLabTest.userLocalService.addGroupUsers(proposal.getGroupId(), teamMembersList);

        return teamMembersList;
    }



    protected void createGlobalContestAndProposals() throws SystemException, PortalException, ParseException {
        //create global contest
        globalContest = XCoLabTest.contestLocalService.createNewContest(XCoLabTest.adminId, "Test-Global-Contest");
        globalContest.setPoints(pointsToBeDistributed);
        globalContest.setDefaultParentPointType(1);
        XCoLabTest.contestLocalService.updateContest(globalContest);

        globalContestPhases = createPhasesForContest(globalContest, null, startPhase);

        //create proposals, authored by a random user, advance them with a probability to the next phases
        globalProposals = new ArrayList<>();
        globalProposalsTeamMembers = new HashMap<>();
        globalProposalsInLastPhase = new ArrayList<>();
        for (int i = 0; i < amountOfGlobalProposals; i++) {
            User author = users.get(randomInt(0, amountOfUsers));
            Proposal proposal = XCoLabTest.proposalLocalService.create(author.getUserId(), globalContestPhases.get(0).getContestPhasePK());

            //create team members
            globalProposalsTeamMembers.put(i, setTeamMembers(proposal, author));
            globalProposals.add(proposal);

            //create proposal name
            XCoLabTest.proposalAttributeLocalService.setAttribute(proposal.getAuthorId(), proposal.getProposalId(), ProposalAttributeKeys.NAME, 0, "Global Proposal "+i);

            initializePhasesForProposal(proposal, i, startPhase, globalContestPhases, globalProposalsInLastPhase);
        }

        //select winner
        if (hasContestEnded) {
            for (int i : globalProposalsInLastPhase) {
                //select this proposal as winner with a specific probability
                //note that this does not always select a winner. this is fine though, since we want some test cases without winner.
                if (doWithProbability(1/globalProposalsInLastPhase.size())) {
                    XCoLabTest.proposalContestPhaseAttributeLocalService.setProposalContestPhaseAttribute(globalProposals.get(i).getProposalId(), globalContestPhases.get(5).getContestPhasePK(),
                            ProposalContestPhaseAttributeKeys.RIBBON, 2L);
                    break;
                }
            }
        }

    }

    private void createSideContestsAndProposals() throws SystemException, PortalException, ParseException {
        //create side contests
        sideProposals = new HashMap<>();
        sideContests = new ArrayList<>();
        sideProposalsTeamMembers = new HashMap<>();
        sideContestPhases = new HashMap<>();
        sideProposalsInLastPhase = new HashMap<>();
        for (int i = 0; i < amountOfSideContests; i++) {
            Contest sideContest = XCoLabTest.contestLocalService.createNewContest(XCoLabTest.adminId, "Test-Side-Contest-"+(i+1));
            sideContest.setPoints(0);
            sideContest.setDefaultParentPointType(6);
            XCoLabTest.contestLocalService.updateContest(sideContest);

            if (this.areSideContestsTimedLikeGlobalContest) {
                sideContestPhases.put(i, this.createPhasesForContest(sideContest, globalContestPhases, startPhase));
            } else {
                sideContestPhases.put(i, this.createPhasesForContest(sideContest, null, startPhase));
            }

            sideProposals.put(i, new ArrayList<Proposal>());
            sideProposalsTeamMembers.put(i, new HashMap<Integer, List<User>>());
            sideProposalsInLastPhase.put(i, new ArrayList<Integer>());

            for (int j = 0; j < amountOfProposalsPerSideContest; j++) {
                User author = users.get(randomInt(0, amountOfUsers));
                Proposal proposal = XCoLabTest.proposalLocalService.create(author.getUserId(), sideContestPhases.get(i).get(0).getContestPhasePK());
                sideProposals.get(i).add(proposal);
                sideProposalsTeamMembers.get(i).put(j, setTeamMembers(proposal, author));

                XCoLabTest.proposalAttributeLocalService.setAttribute(proposal.getAuthorId(), proposal.getProposalId(), ProposalAttributeKeys.NAME, 0, "Side-Proposal "+i+"-"+j);

                initializePhasesForProposal(proposal, j, startPhase, sideContestPhases.get(i), sideProposalsInLastPhase.get(i));

            }
            sideContests.add(sideContest);
        }
    }

    private void createLinksBetweenProposals() throws SystemException, PortalException {
        globalProposalLinksToGlobalProposals = new HashMap<>();
        globalProposalLinksToSideProposals = new HashMap<>();

        for (int i = 0; i < amountOfGlobalProposals; i++) {
            globalProposalLinksToGlobalProposals.put(i, new ArrayList<Integer>());
            globalProposalLinksToSideProposals.put(i, new HashMap<Integer, List<Integer>>());
            String sectionText = "These are the proposals we link to:\n";

            for (int j = 0; j < amountOfGlobalProposals; j++) {
                if (doWithProbability(probabilityToLinkToOtherProposal)) {
                    sectionText += "http://127.0.0.1:8080" + ProposalLocalServiceUtil.getProposalLinkUrl(globalContest, globalProposals.get(j)) + "\n\n";
                    globalProposalLinksToGlobalProposals.get(i).add(j);
                }
            }
            for (int j = 0; j < amountOfSideContests; j++) {
                globalProposalLinksToSideProposals.get(i).put(j, new ArrayList<Integer>());
                for (int k = 0; k < amountOfProposalsPerSideContest; k++) {
                    if (doWithProbability(probabilityToLinkToOtherProposal)) {
                        sectionText += "http://127.0.0.1:8080" + ProposalLocalServiceUtil.getProposalLinkUrl(sideContests.get(j), sideProposals.get(j).get(k)) + "\n\n";
                        globalProposalLinksToSideProposals.get(i).get(j).add(k);
                    }
                }
            }

            //1300907 is the sub proposal plan section definition
            XCoLabTest.proposalAttributeLocalService.setAttribute(globalProposals.get(i).getAuthorId(), globalProposals.get(i).getProposalId(), ProposalAttributeKeys.SECTION, 1300907L, sectionText);
        }
    }



    protected List<ContestPhase> createPhasesForContest(Contest contest, List<ContestPhase> baseOnExistingPhases, Integer startPhase) throws ParseException, SystemException {
        List<ContestPhase> createdPhases = new ArrayList<>();
        String sCp1StartDate;
        String[] sCp1To2Transition;
        String[] sCp2To3Transition;
        String[] sCp3To4Transition;
        String[] sCp4To5Transition;
        String[] sCp5To6Transition;
        if (baseOnExistingPhases == null) {
            //calculate phases
            final int dayOffset;
            switch (startPhase) {
                case 0:
                    dayOffset = randomInt(0, 10) - 43;
                    break;
                case 1:
                    dayOffset = -26;
                    break;
                case 2:
                    dayOffset = -19;
                    break;
                case 3:
                    dayOffset = -12;
                    break;
                case 4:
                    dayOffset = -5;
                    break;
                case 5:
                    dayOffset = 0;
                    break;
                case 6:
                default:
                    dayOffset = randomInt(5, 30);
                    break;
            }

            final int dayDeviation = 2;
            sCp1StartDate = generateRandomIsoDatePair(30 + dayOffset, dayDeviation)[0];
            sCp1To2Transition = generateRandomIsoDatePair(24 + dayOffset, dayDeviation);
            sCp2To3Transition = generateRandomIsoDatePair(17 + dayOffset, dayDeviation);
            sCp3To4Transition = generateRandomIsoDatePair(10 + dayOffset, dayDeviation);
            sCp4To5Transition = generateRandomIsoDatePair(3 + dayOffset, dayDeviation);
            sCp5To6Transition = generateRandomIsoDatePair(-2 + dayOffset, dayDeviation);
        } else {
            //take the existing dates
            sCp1StartDate = dateFormat.format(baseOnExistingPhases.get(0).getPhaseStartDate());
            sCp1To2Transition = new String[]{
                    dateFormat.format(baseOnExistingPhases.get(0).getPhaseEndDate()),
                    dateFormat.format(baseOnExistingPhases.get(1).getPhaseStartDate()),
            };
            sCp2To3Transition = new String[]{
                    dateFormat.format(baseOnExistingPhases.get(1).getPhaseEndDate()),
                    dateFormat.format(baseOnExistingPhases.get(2).getPhaseStartDate()),
            };
            sCp3To4Transition = new String[]{
                    dateFormat.format(baseOnExistingPhases.get(2).getPhaseEndDate()),
                    dateFormat.format(baseOnExistingPhases.get(3).getPhaseStartDate()),
            };
            sCp4To5Transition = new String[]{
                    dateFormat.format(baseOnExistingPhases.get(3).getPhaseEndDate()),
                    dateFormat.format(baseOnExistingPhases.get(4).getPhaseStartDate()),
            };
            sCp5To6Transition = new String[]{
                    dateFormat.format(baseOnExistingPhases.get(4).getPhaseEndDate()),
                    dateFormat.format(baseOnExistingPhases.get(5).getPhaseStartDate()),
            };
        }
        String promoteDone = "PROMOTE_DONE";
        String alwaysPromote = "PROMOTE";
        String promoteJudged = "PROMOTE_JUDGED";
        createdPhases.add(createContestPhase(contest, 1, false, startPhase > 1 ? promoteDone : alwaysPromote, sCp1StartDate, sCp1To2Transition[0]));
        createdPhases.add(createContestPhase(contest, 16, true, startPhase > 2 ? promoteDone : promoteJudged, sCp1To2Transition[1], sCp2To3Transition[0]));
        createdPhases.add(createContestPhase(contest, 18, false, startPhase > 3 ? promoteDone : alwaysPromote, sCp2To3Transition[1], sCp3To4Transition[0]));
        createdPhases.add(createContestPhase(contest, 19, true, startPhase > 4 ? promoteDone : promoteJudged, sCp3To4Transition[1], sCp4To5Transition[0]));
        createdPhases.add(createContestPhase(contest, 15, false, startPhase > 5 ? promoteDone : alwaysPromote, sCp4To5Transition[1], sCp5To6Transition[0]));
        createdPhases.add(createContestPhase(contest, 17, false, "", sCp5To6Transition[1], null));

        //calculate the current phase and assert it afterwards, just to make sure that our date calculations were correct
        int currentPhase;
        Date now = new Date();
        if (now.after(dateFormat.parse(sCp5To6Transition[0]))) {
            currentPhase = 6;
        } else if (now.after(dateFormat.parse(sCp4To5Transition[0]))) {
            currentPhase = 5;
        } else if (now.after(dateFormat.parse(sCp3To4Transition[0]))) {
            currentPhase = 4;
        } else if (now.after(dateFormat.parse(sCp2To3Transition[0]))) {
            currentPhase = 3;
        } else if (now.after(dateFormat.parse(sCp1To2Transition[0]))) {
            currentPhase = 2;
        } else if (now.after(dateFormat.parse(sCp1StartDate))) {
            currentPhase = 1;
        } else {
            currentPhase = 0;
        }

        assertTrue(currentPhase == startPhase);
        return createdPhases;
    }


    protected void initializePhasesForProposal(Proposal proposal, int proposalIndex, int currentPhase, List<ContestPhase> contestPhases, List<Integer> proposalsInLastPhase) throws SystemException {
        if (currentPhase == 1) {
            proposalsInLastPhase.add(proposalIndex);
        } else if (currentPhase > 1) {
            //copy to second phase
            copyProposalToPhase(proposal, contestPhases.get(1));

            //copy some of the proposals to other phases
            if (currentPhase == 2) {
                proposalsInLastPhase.add(proposalIndex);
            } else if (currentPhase > 2 && doWithProbability(probabilityOfBeingAdvancedToNextPhase)) {
                copyProposalToPhase(proposal, contestPhases.get(2));
                if (currentPhase == 3) {
                    proposalsInLastPhase.add(proposalIndex);
                } else if (currentPhase > 3) {
                    copyProposalToPhase(proposal, contestPhases.get(3));
                    if (currentPhase == 4) {
                        proposalsInLastPhase.add(proposalIndex);
                    } else if (currentPhase > 4 && doWithProbability(probabilityOfBeingAdvancedToNextPhase)) {
                        copyProposalToPhase(proposal, contestPhases.get(4));
                        if (currentPhase == 5) {
                            proposalsInLastPhase.add(proposalIndex);
                        } else if (currentPhase > 5) {
                            proposalsInLastPhase.add(proposalIndex);
                            copyProposalToPhase(proposal, contestPhases.get(5));
                        }
                    }
                }
            }
        }
    }


    protected ContestPhase createContestPhase(Contest c, long type, boolean fellowScreeningActive, String autoPromote, String startDate, String endDate) throws SystemException, ParseException {
        ContestPhase cp = XCoLabTest.contestPhaseLocalService.createContestPhase(100000+contestPhaseIdCount++);
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
        XCoLabTest.contestPhaseLocalService.updateContestPhase(cp);

        return cp;
    }



    protected static void copyProposalToPhase(Proposal p, ContestPhase cp) throws SystemException {
        Proposal2Phase p2p = XCoLabTest.proposal2PhaseLocalService.create(p.getProposalId(), cp.getContestPhasePK());
        p2p.setVersionFrom(1);
        p2p.setVersionTo(1);
        XCoLabTest.proposal2PhaseLocalService.updateProposal2Phase(p2p);
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
        return doWithProbability(0.1) ? 1 : random.nextDouble()/(Math.ceil(teamSize*2.0/3.0));
    }

    public  static String[] generateRandomIsoDatePair(int daysAgo, int daysDeviation) {
        Long minTime = System.currentTimeMillis() - Math.round(((daysAgo+(daysDeviation*0.5)) * DAY_IN_MS));
        Long maxTime = System.currentTimeMillis() - Math.round(((daysAgo-(daysDeviation*0.5)) * DAY_IN_MS));
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(minTime + new Random().nextInt((int) Math.abs(maxTime - minTime)));

        String[] tuple = new String[2];
        tuple[0] = dateFormat.format(cal.getTime());
        cal.add(Calendar.SECOND, 1);
        tuple[1] = dateFormat.format(cal.getTime());
        return tuple;
    }

}
