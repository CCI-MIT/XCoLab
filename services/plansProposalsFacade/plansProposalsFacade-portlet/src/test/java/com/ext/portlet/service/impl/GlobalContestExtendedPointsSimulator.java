package com.ext.portlet.service.impl;

import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.PointDistributionTarget;
import com.ext.portlet.model.Points;
import com.ext.portlet.model.Proposal;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * This class extends the basic GlobalContestPointsSimulator class.
 * It supports multiple different contest levels as found in 2015 contest rounds.
 *
 * Created by Klemens Mang on 17/09/15.
 */
public class GlobalContestExtendedPointsSimulator extends GlobalContestPointsSimulator {

    /**
     * Keeps a reference to the ContestTierDataStructure of each used contest tier.
     */
    Map<Integer, ContestTierDataStructure> contestTierToDataStructureMap;

    private int amountOfContestTiers;

    class ContestTierDataStructure {
        class ProposalToCollection<T> {
            Map<Integer, List<T>> proposalToCollection;

            public ProposalToCollection() {
                proposalToCollection = new HashMap<Integer, List<T>>();
            }

            public void setProposal(int proposalNr) {
                proposalToCollection.put(proposalNr, new ArrayList<T>());
            }

            public void setProposalCollection(int proposalNumber, List<T> collection) {
                setProposal(proposalNumber);
                getProposalToCollection(proposalNumber).addAll(collection);
            }

            public List<T> getProposalToCollection(int proposalNr) {
                return proposalToCollection.get(proposalNr);
            }
        }

        class ProposalTeamMembers extends ProposalToCollection<User>{
            public void setProposalTeamMembers(int proposalNumber, List<User> teamMembers) {
                setProposal(proposalNumber);
                getProposalTeamMembers(proposalNumber).addAll(teamMembers);
            }

            public List<User> getProposalTeamMembers(int proposalNr) {
                return proposalToCollection.get(proposalNr);
            }
        }

        class ProposalPointsDistributionConfigurations extends ProposalToCollection<DistributionConfiguration> {
            public void setPointsDistributionConfigurations(int proposalNumber, List<DistributionConfiguration> configurations) {
                setProposal(proposalNumber);
                getProposalToCollection(proposalNumber).addAll(configurations);
            }

            public void addDistributionConfiguration(int proposalNumber, DistributionConfiguration configuration) {
                List<DistributionConfiguration> configurations = getProposalToCollection(proposalNumber);
                if (configurations == null) {
                    setProposal(proposalNumber);
                    configurations = getProposalToCollection(proposalNumber);
                }

                configurations.add(configuration);
            }

            public List<DistributionConfiguration> getDistributionConfigurations(int proposalNumber) {
                return proposalToCollection.get(proposalNumber);
            }

            public int getSize() {
                return proposalToCollection.size();
            }
        }

        class ProposalLink {
            int toTierNr;
            int toContestNr;
            int toProposalNr;
            long toProposalId;

            public ProposalLink(int toTierNr, int toContestNr, int toProposalNr, long toProposalId) {
                this.toTierNr = toTierNr;
                this.toContestNr = toContestNr;
                this.toProposalNr = toProposalNr;
                this.toProposalId = toProposalId;
            }

            public int getToTierNr() {
                return toTierNr;
            }

            public int getToContestNr() {
                return toContestNr;
            }

            public int getToProposalNr() {
                return toProposalNr;
            }

            public long getToProposalId() {
                return toProposalId;
            }
        }

        class ProposalLinks extends ProposalToCollection<ProposalLink> {
            public List<ProposalLink> getProposalLinkList(int proposalNr) {
                if (proposalToCollection.get(proposalNr) == null) {
                    proposalToCollection.put(proposalNr, new ArrayList<ProposalLink>());
                }
                return proposalToCollection.get(proposalNr);
            }

            public void addProposalLink(int fromProposalNr, int toTierNr, int toContestNr, int toProposalNr, long toProposalId) {
                getProposalLinkList(fromProposalNr).add(new ProposalLink(toTierNr, toContestNr, toProposalNr, toProposalId));
            }
        }

        class ProposalPointDistributionTargets extends ProposalToCollection<PointDistributionTarget> {
            public void setPointDistributionTargets(int proposalNumber, List<PointDistributionTarget> targets) {
                setProposal(proposalNumber);
                getProposalToCollection(proposalNumber).addAll(targets);
            }

            public void addPointDistributionTarget(int proposalNumber, PointDistributionTarget target) {
                List<PointDistributionTarget> targets = getProposalToCollection(proposalNumber);
                if (target == null) {
                    setProposal(proposalNumber);
                    targets = getProposalToCollection(proposalNumber);
                }

                targets.add(target);
            }

            public List<PointDistributionTarget> getPointDistributionTargets(int proposalNumber) {
                return proposalToCollection.get(proposalNumber);
            }

            public int getSize() {
                return proposalToCollection.size();
            }
        }

        Map<Integer, ProposalTeamMembers> contestToProposalTeamMembersMap;
        Map<Integer, List<ContestPhase>> contestToContestPhasesMap;
        Map<Integer, ProposalLinks> contestToProposalLinks;
        Map<Integer, List<Proposal>> contestToProposals;
        Map<Integer, List<Integer>> contestToProposalsInLastPhaseMap;
        Map<Integer, ProposalPointsDistributionConfigurations> contestToProposalPointsDistributionConfigurationsMap;
        Map<Integer, ProposalPointDistributionTargets> contestToProposalPointsDistributionTargetsMap;
        List<Contest> contests;
        int amountOfProposals;

        public ContestTierDataStructure(int amountOfProposals) {
            this.amountOfProposals = amountOfProposals;
            contestToProposalTeamMembersMap = new HashMap<>();
            contestToContestPhasesMap = new HashMap<>();
            contestToProposals = new HashMap<>();
            contestToProposalsInLastPhaseMap = new HashMap<>();
            contestToProposalPointsDistributionConfigurationsMap = new HashMap<>();
            contests = new ArrayList<Contest>();
        }

        public ProposalTeamMembers getProposalTeamMembers(int contestNr) {
            if (contestToProposalTeamMembersMap.get(contestNr) == null) {
                contestToProposalTeamMembersMap.put(contestNr, new ProposalTeamMembers());
            }
            return contestToProposalTeamMembersMap.get(contestNr);
        }

        public List<ContestPhase> getContestPhases(int contestNr) {
            if (contestToContestPhasesMap.get(contestNr) == null) {
                contestToContestPhasesMap.put(contestNr, new ArrayList<ContestPhase>());
            }
            return contestToContestPhasesMap.get(contestNr);
        }

        public ProposalLinks getProposalLinks(int contestNr) {
            if (contestToProposalLinks.get(contestNr) == null) {
                contestToProposalLinks.put((contestNr), new ProposalLinks());
            }
            return contestToProposalLinks.get(contestNr);
        }

        public List<Proposal> getProposals(int contestNr) {
            if (contestToProposals.get(contestNr) == null) {
                contestToProposals.put(contestNr, new ArrayList<Proposal>());
            }
            return contestToProposals.get(contestNr);
        }

        public List<Integer> getProposalsInLastPhase(int contestNr) {
            if (contestToProposalsInLastPhaseMap.get(contestNr) == null) {
                contestToProposalsInLastPhaseMap.put(contestNr, new ArrayList<Integer>());
            }
            return contestToProposalsInLastPhaseMap.get(contestNr);
        }

        public ProposalPointsDistributionConfigurations getProposalPointsDistributionConfigurations(int contestNr) {
            if (contestToProposalPointsDistributionConfigurationsMap.get(contestNr) == null) {
                contestToProposalPointsDistributionConfigurationsMap.put(contestNr, new ProposalPointsDistributionConfigurations());
            }
            return contestToProposalPointsDistributionConfigurationsMap.get(contestNr);
        }

        public List<Contest> getContests() {
            return contests;
        }

        public Contest getContest(int contestNr) {
            return contests.get(contestNr);
        }

        public int getAmountOfProposals() {
            return amountOfProposals;
        }

        public ProposalPointDistributionTargets getProposalPointDistributionTargets(int contestNr) {
            if (contestToProposalPointsDistributionTargetsMap.get(contestNr) == null) {
                contestToProposalPointsDistributionTargetsMap.put(contestNr, new ProposalPointDistributionTargets());
            }

            return contestToProposalPointsDistributionTargetsMap.get(contestNr);
        }

        public void addContest(Contest contest) {
            contests.add(contest);
        }

        public void addPointsDistributionConfiguration(int contestNr, int proposalNr, DistributionConfiguration configuration) {
            getProposalPointsDistributionConfigurations(contestNr).addDistributionConfiguration(proposalNr, configuration);
        }

        public void addPointDistributionTarget(int contestNr, int proposalNr, PointDistributionTarget target) {
            getProposalPointDistributionTargets(contestNr).addPointDistributionTarget(proposalNr, target);
        }
    }


    public GlobalContestExtendedPointsSimulator() {
    }

    public void initializeContests(int amountOfUsers,
                                   double pointsToBeDistributed,
                                   boolean hasContestEnded,
                                   int amountOfContestTiers,
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
                amountOfContestTiers,
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
                                   int amountOfContestTiers,
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
        this.amountOfContestTiers = amountOfContestTiers;
        this.amountOfGlobalProposals = amountOfGlobalProposals;
        this.amountOfSideContests = amountOfSideContests;
        this.amountOfProposalsPerSideContest = amountOfProposalsPerSideContest;
        this.probabilityToLinkToOtherProposal = probabilityToLinkToOtherProposal;
        this.probabilityOfBeingAdvancedToNextPhase = probabilityOfBeingAdvancedToNextPhase;
        this.probabilityOfAdditionalTeamMember = probabilityOfAdditionalTeamMember;

        if (users == null || users.isEmpty()) {
            this.createUsers();
        }

        contestTierToDataStructureMap = new HashMap<>(amountOfContestTiers);
        contestTierToDataStructureMap.put(amountOfContestTiers - 1, new ContestTierDataStructure(amountOfGlobalProposals));
        for (int i = 0; i < amountOfContestTiers - 2; i++) {
            contestTierToDataStructureMap.put(i, new ContestTierDataStructure(amountOfProposalsPerSideContest));
        }

        this.createGlobalContestAndProposals();
        this.createSideContestsAndProposals();
        this.createLinksBetweenProposals();
    }

    protected void createGlobalContestAndProposals() throws ParseException, PortalException, SystemException {
        super.createGlobalContestAndProposals();

        ContestTierDataStructure globalContestDataStructure = contestTierToDataStructureMap.get(amountOfContestTiers - 1);
        for (Integer proposalNr : globalProposalsTeamMembers.keySet()) {
            globalContestDataStructure.getProposalTeamMembers(0).setProposalTeamMembers(proposalNr, globalProposalsTeamMembers.get(proposalNr));
        }

        globalContestDataStructure.getProposals(0).addAll(globalProposals);
        globalContestDataStructure.getContestPhases(0).addAll(globalContestPhases);

    }

    public void setPointsDistributions(
            double probabilityOfEmptyGlobalProposalConfiguration,
            double probabilityOfEmptySideProposalConfiguration,
            double probabilityOfPointsDistributionAdditionalNonTeamMembers
    ) throws NoSuchUserException, SystemException {
        globalProposalsDistributionTargets = new HashMap<>();

        this.probabilityOfPointsDistributionAdditionalNonTeamMembers = probabilityOfPointsDistributionAdditionalNonTeamMembers;

        // Iterate over all contest tiers; start from global contest
        for (int tierIdx = amountOfContestTiers - 1; tierIdx >= 0; tierIdx--) {
            final ContestTierDataStructure tierDataStructure = contestTierToDataStructureMap.get(tierIdx);

            // contests loop
            for (int contestIdx = 0; contestIdx < tierDataStructure.getContests().size(); contestIdx++) {

                final ContestTierDataStructure.ProposalPointsDistributionConfigurations pointsDistributionConfigurations =
                        tierDataStructure.getProposalPointsDistributionConfigurations(contestIdx);
                for (int proposalIdx = 0; proposalIdx < tierDataStructure.amountOfProposals; proposalIdx++) {

                    if (!doWithProbability(tierIdx == amountOfContestTiers - 1 ? probabilityOfEmptyGlobalProposalConfiguration : probabilityOfEmptySideProposalConfiguration)) {
                        DistributionConfiguration config = this.setPointsDistributionForProposal(tierDataStructure.getProposals(contestIdx).get(proposalIdx),
                                tierDataStructure.getProposalTeamMembers(contestIdx).getProposalTeamMembers(proposalIdx),
                                true);
                        pointsDistributionConfigurations.addDistributionConfiguration(proposalIdx, config);
                    }
                }
            }
        }
    }

    // todo
    public void setPointDistributionTargets() throws SystemException {
        double sumOfPoints = 0;

        final ContestTierDataStructure globalTierDataStructure = contestTierToDataStructureMap.get(amountOfContestTiers - 1);
        final List<Integer> globalProposalsInLastPhase = globalTierDataStructure.getProposalsInLastPhase(0);
        if (globalProposalsInLastPhase.size() > 0) {
            for (long i = 0; sumOfPoints < pointsToBeDistributed; i++) {
                //create random distribution target
                int points;
                //either create random points or finish the distribution by assigning the rest of points
                if (doWithProbability(0.5)) {
                    //sumOfPoints[n-1] < sumOfPoints[n] <= pointsToBeDistributed
                    points = randomInt(1, (int) (pointsToBeDistributed - sumOfPoints) + 1);
                } else {
                    //sumOfPoints[n] == pointsToBeDistributed
                    points = (int) (pointsToBeDistributed - sumOfPoints);
                }

                sumOfPoints += points;

                //choose a random proposal, which has not been picked before
                boolean randomProposalChosen = false;
                int proposalIndex = 0;
                while (!randomProposalChosen) {
                    proposalIndex = randomInt(0, globalProposalsInLastPhase.size());
                    if (globalTierDataStructure.getProposalPointsDistributionConfigurations(0).getDistributionConfigurations(globalProposalsInLastPhase.get(proposalIndex)) == null) {
                        randomProposalChosen = true;
                    }
                }

                Proposal targetProposal = globalTierDataStructure.getProposals(0).get(proposalIndex);

                PointDistributionTarget pdt = testInstance.pointDistributionTargetService.createPointDistributionTarget(i + 1);
                pdt.setContestId(globalContest.getContestPK());
                pdt.setProposalId(targetProposal.getProposalId());
                pdt.setNumberOfPoints(points);
                testInstance.pointDistributionTargetService.addPointDistributionTarget(pdt);
                globalTierDataStructure.addPointDistributionTarget(0, proposalIndex, pdt);
            }
        }
    }

    public void assertPointDistributions() throws SystemException, PortalException {
        //Assure that the individual Point data entries are correct
        this.points = new ArrayList<Points>(testInstance.pointsLocalService.getPointses(0, Integer.MAX_VALUE));

        final ContestTierDataStructure globalContestDataStructure = contestTierToDataStructureMap.get(amountOfContestTiers - 1);
        final Contest globalContest = globalContestDataStructure.getContest(0);
        final ContestTierDataStructure.ProposalPointDistributionTargets distributionTargets = globalContestDataStructure.getProposalPointDistributionTargets(0);

        //if the contest has ended, and the targets are not defined, the winner will materialize all points.
        if (hasContestEnded && distributionTargets.getSize() == 0) {
            //if there are no distribution targets assigned, the winning proposal (if exists) gets all points.
            Proposal winningProposal = testInstance.contestLocalService.getWinnerProposal(globalContest.getContestPK());
            if (winningProposal != null) {
                //create artificial pdt, this lets us treat the winning case and the target-defined case equally afterwards.
                PointDistributionTarget pdt = testInstance.pointDistributionTargetService.createPointDistributionTarget(1);
                pdt.setContestId(globalContest.getContestPK());
                pdt.setProposalId(winningProposal.getProposalId());
                pdt.setNumberOfPoints(pointsToBeDistributed);
                //note that we do not save the pdt in the db
                globalContestDataStructure.addPointDistributionTarget(0, this.getProposalIndexForProposal(winningProposal), pdt);
            }
        }

        List<Integer> globalProposalsInLastPhase = globalContestDataStructure.getProposalsInLastPhase(0);
        for (int proposalIdx : globalProposalsInLastPhase) {
            double materializedPoints = 0L;
            if (distributionTargets.getSize() > 0 && distributionTargets.getPointDistributionTargets(proposalIdx) != null) {
                materializedPoints = distributionTargets.getPointDistributionTargets(proposalIdx).get(0).getNumberOfPoints(); // todo do we need multiple pointDistributionTargets per proposal?
            }
            this.assertDistributionForProposal(amountOfContestTiers, 0, proposalIdx, 0L, pointsToBeDistributed, materializedPoints);
        }

        //all points should have been popped and verified after this. so the array should be empty.
        assertTrue(points.isEmpty());
    }

    private void assertDistributionForProposal(int contestTier, int contestIdx, int proposalIndex, long sourceId, double hypotheticalPoints, double materializedPoints) {
        ContestTierDataStructure currentTierDataStructure = contestTierToDataStructureMap.get(contestTier - 1);
        DistributionConfiguration config = currentTierDataStructure.getProposalPointsDistributionConfigurations(contestIdx).getDistributionConfigurations(proposalIndex).get(0); // todo do we need a list?
        Proposal proposal = currentTierDataStructure.getProposals(contestIdx).get(proposalIndex);

        // Note: Change these values whenever the points distribution changes
        double matTeamPoints =  Math.ceil(materializedPoints)*0.2*0.9;
        double hypTeamPoints =  Math.ceil(hypotheticalPoints)*0.2*0.9;
        double matNonTeamPoints =  Math.ceil(materializedPoints)*0.2*0.1;
        double hypNonTeamPoints =  Math.ceil(hypotheticalPoints)*0.2*0.1;

        int amountOfSubProposals = currentTierDataStructure.getProposalLinks(contestIdx).getProposalLinkList(proposalIndex).size();
        double hypPointsPerSubProposal = amountOfSubProposals > 0 ? ( Math.ceil(hypotheticalPoints)*0.8)/amountOfSubProposals : 0;
        double matPointsPerSubProposal = amountOfSubProposals > 0 ? ( Math.ceil(materializedPoints)*0.8)/amountOfSubProposals : 0;

        //TEAM
        if (hypPointsPerSubProposal >= 1) {
            List<User> teamMembers = currentTierDataStructure.getProposalTeamMembers(contestIdx).getProposalTeamMembers(proposalIndex);
            for (int userIdx = 0; userIdx < teamMembers.size(); userIdx++) {
                User teamMember = teamMembers.get(userIdx);
                double share;
                if (config != null) {
                    share = config.teamMemberShares.get(userIdx);
                } else {
                    //no config is present. shares are distributed equally
                    share = 1.00 / teamMembers.size();
                }
                assertNotNull(popPointEntryInList(points, proposal.getProposalId(), teamMember.getUserId(), sourceId, Math.ceil(matTeamPoints * share), Math.ceil(hypTeamPoints * share)));
            }
        }

        //NON-TEAM
        if (hypNonTeamPoints >= 1 && config != null) {
            for (int userIndex: config.additionalShares.keySet()) {
                double share = config.additionalShares.get(userIndex);
                assertNotNull(popPointEntryInList(points, proposal.getProposalId(), users.get(userIndex).getUserId(), sourceId, Math.ceil(matNonTeamPoints * share), Math.ceil(hypNonTeamPoints * share)));
            }
        }

        //SUB-PROPOSALS
        // We do not distribute points to contests beyond and including Tier 1
        if (Math.ceil(hypotheticalPoints)*0.8 >= 1 && amountOfSubProposals > 0 && contestTier > 1) { // Note: Change this values whenever the points distribution changes
            List<ContestTierDataStructure.ProposalLink> proposalLinks = currentTierDataStructure.getProposalLinks(contestIdx).getProposalLinkList(proposalIndex);

            //now go through all linked proposals and find their origin
            for (int i = 0; i < amountOfSubProposals; i++) {
                //find the related subProposal Point entry
                Points subProposalSourcePoints = popPointEntryInList(points, proposal.getProposalId(), 0, sourceId, Math.ceil(matPointsPerSubProposal), Math.ceil(hypPointsPerSubProposal));
                assertNotNull(subProposalSourcePoints);

                //find the proposalId which has the sub-proposal Point entry as a parent
                Long proposalId = getProposalIdByPointsSourceIdInList(points, subProposalSourcePoints.getId());
                assertNotNull(proposalId);

                //remove
                ContestTierDataStructure.ProposalLink proposalLink = getProposalLinkWithProposalId(proposalLinks, proposalId);
                if (proposalLink.getToProposalNr() > -1) {
                    proposalLinks.remove(getProposalLinkWithProposalId(proposalLinks, proposalId));
                } else {
                    throw new RuntimeException("Wrong sub-proposal " + proposalId);
                }

                this.assertDistributionForProposal(contestTier - 1, proposalLink.getToContestNr(), proposalLink.getToProposalNr(), subProposalSourcePoints.getId(), hypPointsPerSubProposal, matPointsPerSubProposal);
            }
            // we are done. the proposal links array should be empty by now.
            assertTrue(proposalLinks.isEmpty());
        }
    }

    private ContestTierDataStructure.ProposalLink getProposalLinkWithProposalId(List<ContestTierDataStructure.ProposalLink> proposalLinks, long proposalId) {
        for (ContestTierDataStructure.ProposalLink proposalLink : proposalLinks) {
            if (proposalLink.toProposalId == proposalId) {
                return proposalLink;
            }
        }

        return null;
    }

    private void createSideContestsAndProposals() throws SystemException, PortalException, ParseException {
        // Create Contests and proposals for all remaining contest tiers
        for (int tierIdx = amountOfContestTiers - 2; tierIdx >= 0; tierIdx--) {
            ContestTierDataStructure contestTierDataStructure = contestTierToDataStructureMap.get(tierIdx);

            //create side contests
            for (int contestIdx = 0; contestIdx < amountOfSideContests; contestIdx++) {
                final List<Proposal>contestProposals = contestTierDataStructure.getProposals(contestIdx);
                final ContestTierDataStructure.ProposalTeamMembers proposalTeamMembers = contestTierDataStructure.getProposalTeamMembers(contestIdx);
                final List<ContestPhase> contestPhases = contestTierDataStructure.getContestPhases(contestIdx);
                final List<Integer> contestProposalsInLastPhase = contestTierDataStructure.getProposalsInLastPhase(contestIdx);

                Contest contest = testInstance.contestLocalService.createNewContest(testInstance.adminId, "Tier " + (tierIdx+1) + " Contest " + (contestIdx+1));
                contest.setPoints(0);
                contest.setDefaultParentPointType(1);
                testInstance.contestLocalService.updateContest(contest);

                if (this.areSideContestsTimedLikeGlobalContest) {
                    contestPhases.addAll(this.createPhasesForContest(contest, globalContestPhases, startPhase));
                } else {
                    contestPhases.addAll(this.createPhasesForContest(contest, null, startPhase));
                }

                for (int proposalIdx = 0; proposalIdx < amountOfProposalsPerSideContest; proposalIdx++) {
                    User author = users.get(randomInt(0, amountOfUsers));
                    Proposal proposal = testInstance.proposalLocalService.create(author.getUserId(), contestPhases.get(0).getContestPhasePK());
                    contestProposals.add(proposal);
                    proposalTeamMembers.setProposalTeamMembers(proposalIdx, setTeamMembers(proposal, author));

                    testInstance.proposalLocalService.setAttribute(proposal.getAuthorId(), proposal.getProposalId(), ProposalAttributeKeys.NAME, 0, "Tier " + (tierIdx + 1) + " Contest " + (contestIdx + 1) + " Proposal " + (proposalIdx + 1));

                    initializePhasesForProposal(proposal, proposalIdx, startPhase, contestPhases, contestProposalsInLastPhase);

                }
                contestTierDataStructure.addContest(contest);
            }
        }


    }

    private void createLinksBetweenProposals() {
        for (int tierIdx = amountOfContestTiers - 1; tierIdx >= 1; tierIdx--) {
            final ContestTierDataStructure contestTierDataStructure = contestTierToDataStructureMap.get(tierIdx);
            final ContestTierDataStructure nextContestTierDataStructure = contestTierToDataStructureMap.get(tierIdx - 1);

            for (int contestIdx = 0; contestIdx < contestTierDataStructure.getContests().size(); contestIdx++) {
                String sectionText = "These are the proposals we link to:\n";
                final Contest contest = contestTierDataStructure.getContests().get(contestIdx);
                final List<Proposal> contestProposals = contestTierDataStructure.getProposals(contestIdx);

                for (int fromProposalIdx = 0; fromProposalIdx < contestTierDataStructure.getAmountOfProposals(); fromProposalIdx++) {
                    for (int toProposalIdx = 0; toProposalIdx < contestTierDataStructure.getAmountOfProposals(); toProposalIdx++) {
                        if (doWithProbability(probabilityToLinkToOtherProposal)) {
                            sectionText += "http://127.0.0.1:8080/web/guest/plans/-/plans/contestId/" + contest.getContestPK() +
                                    "/planId/" + contestProposals.get(toProposalIdx).getProposalId() + "\n\n";

                            contestTierDataStructure.getProposalLinks(contestIdx).addProposalLink(fromProposalIdx, tierIdx, contestIdx, toProposalIdx, contestProposals.get(toProposalIdx).getProposalId());
                        }
                    }

                    for (int toContestIdx = 0; toContestIdx < nextContestTierDataStructure.getContests().size(); toContestIdx++) {
                        final Contest toContest = nextContestTierDataStructure.getContests().get(toContestIdx);
                        for (int toProposalIdx = 0; toProposalIdx < nextContestTierDataStructure.getAmountOfProposals(); toProposalIdx++) {
                            final Proposal toProposal = nextContestTierDataStructure.getProposals(contestIdx).get(toProposalIdx);
                            if (doWithProbability(probabilityToLinkToOtherProposal)) {
                                sectionText += "http://127.0.0.1:8080/web/guest/plans/-/plans/contestId/" + toContest.getContestPK() + "/planId/" + toProposal.getProposalId() + "\n\n";
                                contestTierDataStructure.getProposalLinks(contestIdx).addProposalLink(fromProposalIdx, tierIdx - 1, toContestIdx, toProposalIdx, toProposal.getProposalId());
                            }
                        }
                    }
                }

            }
        }
    }
}
