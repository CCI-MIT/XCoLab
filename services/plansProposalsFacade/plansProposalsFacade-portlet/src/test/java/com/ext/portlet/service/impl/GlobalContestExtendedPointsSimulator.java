package com.ext.portlet.service.impl;

/**
 * This class extends the basic GlobalContestPointsSimulator class.
 * It supports multiple different contest levels as found in 2015 contest rounds.
 *
 * Created by Klemens Mang on 17/09/15.
 */
public class GlobalContestExtendedPointsSimulator extends GlobalContestPointsSimulator {

//    /**
//     * Keeps a reference to the ContestTierDataStructure of each used contest tier.
//     */
//    Map<Integer, ContestTierDataStructure> contestTierToDataStructureMap;
//
//    private int amountOfContestTiers;
//
//
//    public GlobalContestExtendedPointsSimulator() {
//    }
//
//    public void initializeContests(int amountOfUsers,
//                                   double pointsToBeDistributed,
//                                   boolean hasContestEnded,
//                                   int amountOfContestTiers,
//                                   int amountOfGlobalProposals,
//                                   int amountOfSideContests,
//                                   int amountOfProposalsPerSideContest,
//                                   double probabilityToLinkToOtherProposal,
//                                   double probabilityOfBeingAdvancedToNextPhase,
//                                   double probabilityOfAdditionalTeamMember
//    ) throws SystemException, PortalException, ParseException {
//        this.initializeContests(amountOfUsers,
//                pointsToBeDistributed,
//                hasContestEnded ? 6 : 5,
//                false,
//                amountOfContestTiers,
//                amountOfGlobalProposals,
//                amountOfSideContests,
//                amountOfProposalsPerSideContest,
//                probabilityToLinkToOtherProposal,
//                probabilityOfBeingAdvancedToNextPhase,
//                probabilityOfAdditionalTeamMember);
//    }
//
//    public void initializeContests(int amountOfUsers,
//                                   double pointsToBeDistributed,
//                                   int startPhase,
//                                   boolean areSideContestsTimedLikeGlobalContest,
//                                   int amountOfContestTiers,
//                                   int amountOfGlobalProposals,
//                                   int amountOfSideContests,
//                                   int amountOfProposalsPerSideContest,
//                                   double probabilityToLinkToOtherProposal,
//                                   double probabilityOfBeingAdvancedToNextPhase,
//                                   double probabilityOfAdditionalTeamMember
//    ) throws SystemException, PortalException, ParseException {
//        this.amountOfUsers = amountOfUsers;
//        this.pointsToBeDistributed = pointsToBeDistributed;
//        this.areSideContestsTimedLikeGlobalContest = areSideContestsTimedLikeGlobalContest;
//        this.startPhase = startPhase;
//        this.currentPhase = startPhase;
//        this.hasContestEnded = (startPhase == 6);
//        this.amountOfContestTiers = amountOfContestTiers;
//        this.amountOfGlobalProposals = amountOfGlobalProposals;
//        this.amountOfSideContests = amountOfSideContests;
//        this.amountOfProposalsPerSideContest = amountOfProposalsPerSideContest;
//        this.probabilityToLinkToOtherProposal = probabilityToLinkToOtherProposal;
//        this.probabilityOfBeingAdvancedToNextPhase = probabilityOfBeingAdvancedToNextPhase;
//        this.probabilityOfAdditionalTeamMember = probabilityOfAdditionalTeamMember;
//
//        if (users == null || users.isEmpty()) {
//            this.createUsers();
//        }
//
//        contestTierToDataStructureMap = new HashMap<>(amountOfContestTiers);
//        contestTierToDataStructureMap.put(amountOfContestTiers - 1, new ContestTierDataStructure(amountOfGlobalProposals));
//        for (int i = 0; i < amountOfContestTiers - 1; i++) {
//            contestTierToDataStructureMap.put(i, new ContestTierDataStructure(amountOfProposalsPerSideContest));
//        }
//
//        this.createGlobalContestAndProposals();
//        this.createSideContestsAndProposals();
//        this.createLinksBetweenProposals();
//    }
//
//    protected void createGlobalContestAndProposals() throws ParseException, PortalException, SystemException {
//        super.createGlobalContestAndProposals();
//
//        ContestTierDataStructure globalContestDataStructure = contestTierToDataStructureMap.get(amountOfContestTiers - 1);
//        for (Integer proposalNr : globalProposalsTeamMembers.keySet()) {
//            globalContestDataStructure.getProposalTeamMembers(0).setProposalTeamMembers(proposalNr, globalProposalsTeamMembers.get(proposalNr));
//        }
//
//        globalContestDataStructure.getContests().add(globalContest);
//        globalContestDataStructure.getProposals(0).addAll(globalProposals);
//        globalContestDataStructure.getContestPhases(0).addAll(globalContestPhases);
//        globalContestDataStructure.getProposalsInLastPhase(0).addAll(globalProposalsInLastPhase);
//    }
//
//    /**
//     * Set distributions of empty/non-empty ProposalDistributionConfiguration
//     *
//     * @param probabilityOfEmptyGlobalProposalConfiguration
//     * @param probabilityOfEmptySideProposalConfiguration
//     * @param probabilityOfPointsDistributionAdditionalNonTeamMembers
//     * @throws NoSuchUserException
//     * @throws SystemException
//     */
//    public void setPointsDistributions(
//            double probabilityOfEmptyGlobalProposalConfiguration,
//            double probabilityOfEmptySideProposalConfiguration,
//            double probabilityOfPointsDistributionAdditionalNonTeamMembers
//    ) throws NoSuchUserException, SystemException {
//        globalProposalsDistributionTargets = new HashMap<>();
//
//        this.probabilityOfPointsDistributionAdditionalNonTeamMembers = probabilityOfPointsDistributionAdditionalNonTeamMembers;
//
//        // Iterate over all contest tiers; start from global contest
//        for (int tierIdx = amountOfContestTiers - 1; tierIdx >= 0; tierIdx--) {
//            final ContestTierDataStructure tierDataStructure = contestTierToDataStructureMap.get(tierIdx);
//
//            // contests loop
//            for (int contestIdx = 0; contestIdx < tierDataStructure.getContests().size(); contestIdx++) {
//                for (int proposalIdx = 0; proposalIdx < tierDataStructure.getAmountOfProposals(); proposalIdx++) {
//
//                    if (!doWithProbability(tierIdx == amountOfContestTiers - 1 ? probabilityOfEmptyGlobalProposalConfiguration : probabilityOfEmptySideProposalConfiguration)) {
//                        DistributionConfiguration config = this.setPointsDistributionForProposal(tierDataStructure.getProposals(contestIdx).get(proposalIdx),
//                                tierDataStructure.getProposalTeamMembers(contestIdx).getProposalTeamMembers(proposalIdx),
//                                tierIdx != 0);
//                        tierDataStructure.addPointsDistributionConfiguration(contestIdx, proposalIdx, config);
//                    }
//                }
//            }
//        }
//    }
//
//    /**
//     * Set random pointDistributionTargets to all proposals
//     * @throws SystemException
//     */
//    public void setPointDistributionTargets() throws SystemException {
//        double sumOfPoints = 0;
//
//        final ContestTierDataStructure globalTierDataStructure = contestTierToDataStructureMap.get(amountOfContestTiers - 1);
//        final List<Integer> globalProposalsInLastPhase = globalTierDataStructure.getProposalsInLastPhase(0);
//        if (globalProposalsInLastPhase.size() > 0) {
//            for (long i = 0; sumOfPoints < pointsToBeDistributed; i++) {
//                //create random distribution target
//                int points;
//                //either create random points or finish the distribution by assigning the rest of points
//                if (doWithProbability(0.5)) {
//                    //sumOfPoints[n-1] < sumOfPoints[n] <= pointsToBeDistributed
//                    points = randomInt(1, (int) (pointsToBeDistributed - sumOfPoints) + 1);
//                } else {
//                    //sumOfPoints[n] == pointsToBeDistributed
//                    points = (int) (pointsToBeDistributed - sumOfPoints);
//                }
//
//                sumOfPoints += points;
//
//                //choose a random proposal, which has not been picked before
//                boolean randomProposalChosen = false;
//                int proposalIndex = 0;
//                while (!randomProposalChosen) {
//                    proposalIndex = randomInt(0, globalProposalsInLastPhase.size());
//                    if (globalTierDataStructure.getProposalPointsDistributionConfigurations(0).getDistributionConfigurations(globalProposalsInLastPhase.get(proposalIndex)) == null) {
//                        randomProposalChosen = true;
//                    }
//                }
//
//                Proposal targetProposal = globalTierDataStructure.getProposals(0).get(proposalIndex);
//
//                PointDistributionTarget pdt = testInstance.pointDistributionTargetService.createPointDistributionTarget(i + 1);
//                pdt.setContestId(globalContest.getContestPK());
//                pdt.setProposalId(targetProposal.getProposalId());
//                pdt.setNumberOfPoints(points);
//                testInstance.pointDistributionTargetService.addPointDistributionTarget(pdt);
//                globalTierDataStructure.addPointDistributionTarget(0, proposalIndex, pdt);
//            }
//        }
//    }
//
//    /**
//     * Starts the assertion of distributed points. Recursively evaluates all distributed points originating at the contest with the highest tier
//     * @throws SystemException
//     * @throws PortalException
//     */
//    public void assertPointDistributions() throws SystemException, PortalException {
//        //Assure that the individual Point data entries are correct
//        this.points = new ArrayList<Points>(testInstance.pointsLocalService.getPointses(0, Integer.MAX_VALUE));
//
//        final ContestTierDataStructure globalContestDataStructure = contestTierToDataStructureMap.get(amountOfContestTiers - 1);
//        final Contest globalContest = globalContestDataStructure.getContest(0);
//        final ContestTierDataStructure.ProposalPointDistributionTargets distributionTargets = globalContestDataStructure.getProposalPointDistributionTargets(0);
//
//        //if the contest has ended, and the targets are not defined, the winner will materialize all points.
//        if (hasContestEnded && distributionTargets.getSize() == 0) {
//            //if there are no distribution targets assigned, the winning proposal (if exists) gets all points.
//            Proposal winningProposal = testInstance.contestLocalService.getWinnerProposal(globalContest.getContestPK());
//            if (winningProposal != null) {
//                //create artificial pdt, this lets us treat the winning case and the target-defined case equally afterwards.
//                PointDistributionTarget pdt = testInstance.pointDistributionTargetService.createPointDistributionTarget(1);
//                pdt.setContestId(globalContest.getContestPK());
//                pdt.setProposalId(winningProposal.getProposalId());
//                pdt.setNumberOfPoints(pointsToBeDistributed);
//                //note that we do not save the pdt in the db
//                globalContestDataStructure.addPointDistributionTarget(0, this.getProposalIndexForProposal(winningProposal), pdt);
//            }
//        }
//
//        List<Integer> globalProposalsInLastPhase = globalContestDataStructure.getProposalsInLastPhase(0);
//        for (int proposalIdx : globalProposalsInLastPhase) {
//            double materializedPoints = 0L;
//            if (distributionTargets.getSize() > 0 && distributionTargets.getPointDistributionTargets(proposalIdx) != null) {
//                // Do we need multiple pointDistributionTargets?
//                materializedPoints = distributionTargets.getPointDistributionTargets(proposalIdx).get(0).getNumberOfPoints();
//            }
//            this.assertDistributionForProposal(amountOfContestTiers - 1, 0, proposalIdx, 0L, pointsToBeDistributed, materializedPoints);
//        }
//
//        //all points should have been popped and verified after this. so the array should be empty.
//        assertTrue(points.isEmpty());
//    }
//
//    /**
//     * Clean up all created model objects that have been used for the test run. This method should be invoked whenever the
//     * test of the created contest/proposal structure should be cleaned up
//     * @throws SystemException
//     * @throws PortalException
//     */
//    public void cleanupPointsSimulator() throws SystemException, PortalException {
//        for (int tierIdx = 0; tierIdx < contestTierToDataStructureMap.size(); tierIdx++) {
//            final ContestTierDataStructure tierDataStructure = contestTierToDataStructureMap.get(tierIdx);
//
//            for (int contestIdx = 0; contestIdx < tierDataStructure.getContests().size(); contestIdx++) {
//                //delete all contestPhases
//                for (ContestPhase cp : tierDataStructure.getContestPhases(contestIdx)) {
//                    testInstance.contestPhaseLocalService.deleteContestPhase(cp);
//                }
//
//                //delete links between proposals
//                List<ProposalAttribute> proposalAttributes = testInstance.proposalAttributeLocalService.getProposalAttributes(0, Integer.MAX_VALUE);
//                for (ProposalAttribute pa: proposalAttributes) {
//                    testInstance.proposalAttributeLocalService.deleteProposalAttribute(pa);
//                }
//
//                //delete Proposal 2 phases
//                List<Proposal2Phase> p2ps = testInstance.proposal2PhaseLocalService.getProposal2Phases(0, Integer.MAX_VALUE);
//                for (Proposal2Phase p2p: p2ps) {
//                    testInstance.proposal2PhaseLocalService.deleteProposal2Phase(p2p);
//                }
//
//                //Delete proposals
//                for (int proposalIdx = 0; proposalIdx < tierDataStructure.getAmountOfProposals(); proposalIdx++) {
//                    Proposal p = tierDataStructure.getProposals(contestIdx).get(proposalIdx);
//                    testInstance.userLocalService.deleteGroupUsers(p.getGroupId(), tierDataStructure.getProposalTeamMembers(contestIdx).getProposalTeamMembers(proposalIdx));
//                            testInstance.proposalLocalService.deleteProposal(p);
//                }
//
//                //delete contests
//                testInstance.contestLocalService.deleteContest(tierDataStructure.getContest(contestIdx));
//            }
//        }
//
//        // Delete all created users for this test
//        deleteUsers();
//
//        // Clean up remaning data structures
//        globalProposalLinksToGlobalProposals = null;
//        globalProposalLinksToSideProposals = null;
//        globalContestPhases = null;
//        sideContests = null;
//        contestTierToDataStructureMap = null;
//        users = null;
//        sideContests = null;
//        globalContest = null;
//        globalProposalsTeamMembers = null;
//        sideProposalsTeamMembers = null;
//
//        globalContestPhases = null;
//        sideContestPhases = null;
//
//        globalProposalLinksToGlobalProposals = null;
//        globalProposalLinksToSideProposals = null;
//    }
//
//    /**
//     * Resets the set of random point distributions. Invoke this method before you set new DistributionTargets and DistributionConfigurations
//     * @throws SystemException
//     */
//    public void deletePointDistributions() throws SystemException {
//        super.deletePointDistributions();
//        for (ContestTierDataStructure tierDataStructure : contestTierToDataStructureMap.values()) {
//            for (int contestIdx = 0; contestIdx < tierDataStructure.getContests().size(); contestIdx++) {
//                tierDataStructure.removeAllPointDistributionTargets(contestIdx);
//                tierDataStructure.removeAllProposalPointsDistributionConfigurations(contestIdx);
//            }
//        }
//    }
//
//    /**
//     * Recursively resolves the scaling factor that should be applied when distributing points to team or non-team members
//     *
//     * @param parentPointTypeId                         The parent point type of the contest
//     * @param receiverLimitationStrategyIdentifier      The identifier that specifies the receiver of the points
//     *                                                  (ReceiverLimitationStrategy.ANY_TEAM_MEMBER or ReceiverLimitationStrategy.ANY_NON_TEAM_MEMBER)
//     * @return
//     * @throws SystemException
//     */
//    private double getPointsFactorForReceiverDistributionStrategy(long parentPointTypeId, String receiverLimitationStrategyIdentifier) throws SystemException {
//
//       /* for (PointType pointType : testInstance.pointTypePersistence.findByParentPointTypeId(parentPointTypeId)) {
//            // Leaf point type
//            if (pointType.getDistributionStrategy().equals(DistributionStrategy.USER_DEFINED.name()) &&
//                    pointType.getReceiverLimitationStrategy().equals(receiverLimitationStrategyIdentifier)) {
//
//                return pointType.getPercentageOfParent();
//            }
//            // Recursively explore sub-tree
//            if (pointType.getDistributionStrategy().equals(DistributionStrategy.DEFINED_BY_CHILDREN_PERCENTAGES.name()) &&
//                    pointType.getReceiverLimitationStrategy().equals(ReceiverLimitationStrategy.ANY_USER.name())) {
//
//                // Cut all digits 6 positions after the comma
//                return Math.floor(pointType.getPercentageOfParent() * getPointsFactorForReceiverDistributionStrategy(pointType.getId(), receiverLimitationStrategyIdentifier) * 1000000) / 1000000;
//            }
//        }
//        */
//        throw new RuntimeException("Could not resolve points factor for parentPointTypeId " + parentPointTypeId);
//
//    }
//
//    private void assertDistributionForProposal(int contestTierIdx, int contestIdx, int proposalIndex, long sourceId, double hypotheticalPoints, double materializedPoints) throws SystemException {
//        /*
//        if (hypotheticalPoints < 1 && materializedPoints < 1) {
//            return;
//        }
//
//        ContestTierDataStructure currentTierDataStructure = contestTierToDataStructureMap.get(contestTierIdx);
//        Proposal proposal = currentTierDataStructure.getProposals(contestIdx).get(proposalIndex);
//
//        DistributionConfiguration config = null;
//        List<DistributionConfiguration> proposalDistributionConfigurations = currentTierDataStructure.getProposalPointsDistributionConfigurations(contestIdx).getDistributionConfigurations(proposalIndex);
//        if (proposalDistributionConfigurations != null) {
//            config = proposalDistributionConfigurations.get(0);
//        }
//
//        Contest contest = currentTierDataStructure.getContest(contestIdx);
//        double teamPointsFactor = getPointsFactorForReceiverDistributionStrategy(contest.getDefaultParentPointType(), ReceiverLimitationStrategy.ANY_TEAM_MEMBER.name());
//        double nonTeamPointsFactor = getPointsFactorForReceiverDistributionStrategy(contest.getDefaultParentPointType(), ReceiverLimitationStrategy.ANY_NON_TEAM_MEMBER.name());;
//        double subProposalFactor = 1.0;
//
//        // Get the subProposal factor
//        for (PointType pointType : testInstance.pointTypePersistence.findByParentPointTypeId(contest.getDefaultParentPointType())) {
//            if (pointType.getReceiverLimitationStrategy().equals(ReceiverLimitationStrategy.SUBPROPOSALS.name())) {
//                subProposalFactor = pointType.getPercentageOfParent();
//            }
//        }
//
//        int amountOfSubProposals = currentTierDataStructure.getProposalLinks(contestIdx).getProposalLinkList(proposalIndex).size();
//        double hypPointsPerSubProposal = amountOfSubProposals > 0 ? ( Math.ceil(hypotheticalPoints)*subProposalFactor)/amountOfSubProposals : 0;
//        double matPointsPerSubProposal = amountOfSubProposals > 0 ? ( Math.ceil(materializedPoints)*subProposalFactor)/amountOfSubProposals : 0;
//
//        double hypTeamPoints = teamPointsFactor * hypotheticalPoints;
//        double hypNonTeamPoints = nonTeamPointsFactor * hypotheticalPoints;
//        double matTeamPoints = teamPointsFactor * materializedPoints;
//        double matNonTeamPoints = nonTeamPointsFactor * materializedPoints;
//
//        //TEAM
//        List<User> teamMembers = currentTierDataStructure.getProposalTeamMembers(contestIdx).getProposalTeamMembers(proposalIndex);
//        for (int userIdx = 0; userIdx < teamMembers.size(); userIdx++) {
//            User teamMember = teamMembers.get(userIdx);
//            double share;
//            if (config != null) {
//                share = config.teamMemberShares.get(userIdx);
//            } else {
//                //no config is present. shares are distributed equally
//                share = 1.00 / teamMembers.size();
//            }
//
//            if (hypTeamPoints * share >= 1) {
//                assertNotNull(popPointEntryInList(points, proposal.getProposalId(), teamMember.getUserId(), sourceId, Math.ceil(matTeamPoints * share), Math.ceil(hypTeamPoints * share)));
//            }
//            // Less than 1 point should not have been distributed
//            else {
//                assertNull(popPointEntryInList(points, proposal.getProposalId(), teamMember.getUserId(), sourceId, Math.ceil(matTeamPoints * share), Math.ceil(hypTeamPoints * share)));
//            }
//
//        }
//
//        //NON-TEAM
//        if (config != null) {
//            for (int userIndex: config.additionalShares.keySet()) {
//                double share = config.additionalShares.get(userIndex);
//                if (hypNonTeamPoints * share >= 1) {
//                    assertNotNull(popPointEntryInList(points, proposal.getProposalId(), users.get(userIndex).getUserId(), sourceId, Math.ceil(matNonTeamPoints * share), Math.ceil(hypNonTeamPoints * share)));
//                }
//                // Less than 1 point should not have been distributed
//                else {
//                    assertNull(popPointEntryInList(points, proposal.getProposalId(), users.get(userIndex).getUserId(), sourceId, Math.ceil(matNonTeamPoints * share), Math.ceil(hypNonTeamPoints * share)));
//                }
//            }
//        }
//
//        //SUB-PROPOSALS
//        List<ContestTierDataStructure.ProposalLink> proposalLinks = currentTierDataStructure.getProposalLinks(contestIdx).getProposalLinkList(proposalIndex);
//        // We do not distribute points to contests beyond and including Tier 1
//        if (hypPointsPerSubProposal >= 1 && amountOfSubProposals > 0 && contestTierIdx > 0) { // Note: Change this values whenever the points distribution changes
//            //now go through all linked proposals and find their origin
//            for (int i = 0; i < amountOfSubProposals; i++) {
//                //find the related subProposal Point entry
//                Points subProposalSourcePoints = popPointEntryInList(points, proposal.getProposalId(), 0, sourceId, Math.ceil(matPointsPerSubProposal), Math.ceil(hypPointsPerSubProposal));
//                if (hypPointsPerSubProposal >= 1) {
//                    assertNotNull(subProposalSourcePoints);
//
//                    //find the proposalId which has the sub-proposal Point entry as a parent
//                    Long proposalId = getProposalIdByPointsSourceIdInList(points, subProposalSourcePoints.getId());
//
//                    // proposalId could be null in the case that the pointsPerSubproposal are >= 1 but the points distributed to author, non-team members and subproposals are < 1
//                    // then we do not have a points entry for the subproposal and we do get null here.
//                    if (proposalId != null) {
//                        // Todo remove proposal links from proposalLinks
//                        ContestTierDataStructure.ProposalLink proposalLink = getProposalLinkWithProposalId(proposalLinks, proposalId);
//                        this.assertDistributionForProposal(proposalLink.getToTierNr(), proposalLink.getToContestNr(), proposalLink.getToProposalNr(), subProposalSourcePoints.getId(), Math.ceil(hypPointsPerSubProposal), Math.ceil(matPointsPerSubProposal));
//                    }
//                } else {
//                    assertNull(subProposalSourcePoints);
//                }
//            }
//        }
//        */
//        // todo we are done. the proposal links array should be empty by now.
//    }
//
//    private ContestTierDataStructure.ProposalLink getProposalLinkWithProposalId(List<ContestTierDataStructure.ProposalLink> proposalLinks, long proposalId) {
//        for (ContestTierDataStructure.ProposalLink proposalLink : proposalLinks) {
//            if (proposalLink.getToProposalId() == proposalId) {
//                return proposalLink;
//            }
//        }
//
//        return null;
//    }
//
//    /**
//     * Creates a contest and proposal structure for all contest tiers except the highest one (usually global tier)
//     *
//     * @throws SystemException
//     * @throws PortalException
//     * @throws ParseException
//     */
//    private void createSideContestsAndProposals() throws SystemException, PortalException, ParseException {
//        // Create Contests and proposals for all remaining contest tiers
//        for (int tierIdx = amountOfContestTiers - 2; tierIdx >= 0; tierIdx--) {
//            ContestTierDataStructure contestTierDataStructure = contestTierToDataStructureMap.get(tierIdx);
//
//            //create side contests
//            for (int contestIdx = 0; contestIdx < amountOfSideContests; contestIdx++) {
//                final List<Proposal>contestProposals = contestTierDataStructure.getProposals(contestIdx);
//                final ContestTierDataStructure.ProposalTeamMembers proposalTeamMembers = contestTierDataStructure.getProposalTeamMembers(contestIdx);
//                final List<ContestPhase> contestPhases = contestTierDataStructure.getContestPhases(contestIdx);
//                final List<Integer> contestProposalsInLastPhase = contestTierDataStructure.getProposalsInLastPhase(contestIdx);
//
//                Contest contest = testInstance.contestLocalService.createNewContest(testInstance.adminId, "Tier " + (tierIdx+1) + " Contest " + (contestIdx+1));
//                contest.setPoints(0);
//                // We assume that all contests except the ones on the lowest tier use the parent point type 1 (as used in 2015 rounds of contests)
//                contest.setDefaultParentPointType((tierIdx + 1) == 1 ? 6 : 1);
//                testInstance.contestLocalService.updateContest(contest);
//
//                if (this.areSideContestsTimedLikeGlobalContest) {
//                    contestPhases.addAll(this.createPhasesForContest(contest, globalContestPhases, startPhase));
//                } else {
//                    contestPhases.addAll(this.createPhasesForContest(contest, null, startPhase));
//                }
//
//                // Add proposal author and team members
//                for (int proposalIdx = 0; proposalIdx < amountOfProposalsPerSideContest; proposalIdx++) {
//                    User author = users.get(randomInt(0, amountOfUsers));
//                    Proposal proposal = testInstance.proposalLocalService.create(author.getUserId(), contestPhases.get(0).getContestPhasePK());
//                    contestProposals.add(proposal);
//                    proposalTeamMembers.setProposalTeamMembers(proposalIdx, setTeamMembers(proposal, author));
//
//                    testInstance.proposalAttributeLocalService.setAttribute(proposal.getAuthorId(), proposal.getProposalId(), ProposalAttributeKeys.NAME, 0, "Tier " + (tierIdx + 1) + " Contest " + (contestIdx + 1) + " Proposal " + (proposalIdx + 1));
//
//                    initializePhasesForProposal(proposal, proposalIdx, startPhase, contestPhases, contestProposalsInLastPhase);
//
//                }
//                contestTierDataStructure.addContest(contest);
//            }
//        }
//    }
//
//    private void createLinksBetweenProposals() throws SystemException, PortalException {
//        // Create links from the highest tier to the lowest - do not create links originating from the lowest tier
//        for (int tierIdx = amountOfContestTiers - 1; tierIdx >= 1; tierIdx--) {
//            final ContestTierDataStructure contestTierDataStructure = contestTierToDataStructureMap.get(tierIdx);
//            final ContestTierDataStructure nextContestTierDataStructure = contestTierToDataStructureMap.get(tierIdx - 1);
//
//            for (int contestIdx = 0; contestIdx < contestTierDataStructure.getContests().size(); contestIdx++) {
//                final Contest contest = contestTierDataStructure.getContests().get(contestIdx);
//                final List<Proposal> contestProposals = contestTierDataStructure.getProposals(contestIdx);
//
//                // Create proposal links for each proposal of a contest
//                for (int fromProposalIdx = 0; fromProposalIdx < contestTierDataStructure.getAmountOfProposals(); fromProposalIdx++) {
//                    String sectionText = "These are the proposals we link to:\n";
//
//                    // Iterate over all contests and proposals of the contest tier below and create a link with probability
//                    for (int toContestIdx = 0; toContestIdx < nextContestTierDataStructure.getContests().size(); toContestIdx++) {
//                        final Contest toContest = nextContestTierDataStructure.getContests().get(toContestIdx);
//                        for (int toProposalIdx = 0; toProposalIdx < nextContestTierDataStructure.getAmountOfProposals(); toProposalIdx++) {
//                            final Proposal toProposal = nextContestTierDataStructure.getProposals(toContestIdx).get(toProposalIdx);
//                            if (doWithProbability(probabilityToLinkToOtherProposal)) {
//                                sectionText += "http://127.0.0.1:8080" + ProposalLocalServiceUtil.getProposalLinkUrl(toContest, toProposal) + "\n\n";
//                                contestTierDataStructure.getProposalLinks(contestIdx).addProposalLink(fromProposalIdx, tierIdx - 1, toContestIdx, toProposalIdx, toProposal.getProposalId());
//                            }
//                        }
//                    }
//
//                    //1300907 is the sub proposal plan section definition
//                    testInstance.proposalAttributeLocalService.setAttribute(
//                            contestProposals.get(fromProposalIdx).getAuthorId(),
//                            contestProposals.get(fromProposalIdx).getProposalId(),
//                            ProposalAttributeKeys.SECTION,
//                            1300907L,
//                            sectionText);
//                }
//
//            }
//        }
//    }
}
