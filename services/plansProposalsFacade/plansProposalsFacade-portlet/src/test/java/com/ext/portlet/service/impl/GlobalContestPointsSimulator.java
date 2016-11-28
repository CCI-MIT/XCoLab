package com.ext.portlet.service.impl;

/**
 * Created by Manuel Thurner on 22/09/14.
 */
public class GlobalContestPointsSimulator extends GlobalContestSimulator {
//    protected double probabilityOfPointsDistributionAdditionalNonTeamMembers;
//    protected Map<Integer, DistributionConfiguration> globalProposalsDistributions;
//    protected Map<Integer, PointDistributionTarget> globalProposalsDistributionTargets;
//    protected Map<Integer, Map<Integer, DistributionConfiguration>> sideProposalsDistributions;
//    protected List<Points> points;
//
//    public void deletePointDistributions() throws SystemException {
//        //delete points
//        List<Points> points = testInstance.pointsLocalService.getPointses(0, Integer.MAX_VALUE);
//        for (Points p: points) {
//            testInstance.pointsLocalService.deletePoints(p);
//        }
//
//        //delete configurations
//        List<PointsDistributionConfiguration> configs = testInstance.pointsDistributionConfigurationService.getPointsDistributionConfigurations(0, Integer.MAX_VALUE);
//        for (PointsDistributionConfiguration pdc: configs) {
//            testInstance.pointsDistributionConfigurationService.deletePointsDistributionConfiguration(pdc);
//        }
//        //delete configurations
//        List<PointDistributionTarget> targets = testInstance.pointDistributionTargetService.getPointDistributionTargets(0, Integer.MAX_VALUE);
//        for (PointDistributionTarget pdt: targets) {
//            testInstance.pointDistributionTargetService.deletePointDistributionTarget(pdt);
//        }
//
//        globalProposalsDistributions = null;
//        sideProposalsDistributions = null;
//        this.points = null;
//        probabilityOfPointsDistributionAdditionalNonTeamMembers = 0;
//    }
//
//    public void setPointsDistributions(
//            double probabilityOfEmptyGlobalProposalConfiguration,
//            double probabilityOfEmptySideProposalConfiguration,
//            double probabilityOfPointsDistributionAdditionalNonTeamMembers
//    ) throws NoSuchUserException, SystemException {
//        globalProposalsDistributions = new HashMap<>();
//        sideProposalsDistributions = new HashMap<>();
//        globalProposalsDistributionTargets = new HashMap<>();
//
//        this.probabilityOfPointsDistributionAdditionalNonTeamMembers = probabilityOfPointsDistributionAdditionalNonTeamMembers;
//
//        //Global Proposals
//        for (int i = 0; i < amountOfGlobalProposals; i++) {
//            if (!doWithProbability(probabilityOfEmptyGlobalProposalConfiguration)) {
//                DistributionConfiguration config = this.setPointsDistributionForProposal(globalProposals.get(i), globalProposalsTeamMembers.get(i), true);
//                globalProposalsDistributions.put(i, config);
//            }
//        }
//
//        //Side Proposals
//        for (int i = 0; i < amountOfSideContests; i++) {
//            sideProposalsDistributions.put(i, new HashMap<Integer, DistributionConfiguration>());
//            for (int j = 0; j < amountOfProposalsPerSideContest; j++) {
//                if (!doWithProbability(probabilityOfEmptySideProposalConfiguration)) {
//                    DistributionConfiguration config = this.setPointsDistributionForProposal(sideProposals.get(i).get(j), sideProposalsTeamMembers.get(i).get(j), false);
//                    sideProposalsDistributions.get(i).put(j, config);
//                }
//            }
//        }
//    }
//
//    public void setPointDistributionTargets() throws SystemException {
//        double sumOfPoints = 0;
//
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
//                    if (globalProposalsDistributionTargets.get(globalProposalsInLastPhase.get(proposalIndex)) == null) {
//                        randomProposalChosen = true;
//                    }
//                }
//
//                PointDistributionTarget pdt = testInstance.pointDistributionTargetService.createPointDistributionTarget(i + 1);
//                pdt.setContestId(globalContest.getContestPK());
//                pdt.setProposalId(globalProposals.get(globalProposalsInLastPhase.get(proposalIndex)).getProposalId());
//                pdt.setNumberOfPoints(points);
//                testInstance.pointDistributionTargetService.addPointDistributionTarget(pdt);
//                globalProposalsDistributionTargets.put(globalProposalsInLastPhase.get(proposalIndex), pdt);
//            }
//        }
//    }
//
//    public void runPointDistributionAlgorithm() throws SystemException, PortalException {
//        testInstance.pointsLocalService.distributePoints(globalContest.getContestPK());
//    }
//
//
//    private int getRandomNonTeamUser(List<User> teamMembers, List <User> alreadyChosenMembers) {
//        int randomUserIndex = randomInt(0, amountOfUsers);
//        User randomUser = users.get(randomUserIndex);
//        if (teamMembers.contains(randomUser) || alreadyChosenMembers.contains(randomUser)) {
//            return getRandomNonTeamUser(teamMembers, alreadyChosenMembers);
//        } else {
//            return randomUserIndex;
//        }
//    }
//
//    /**
//     * Sets a random point distribution configuration for the given proposal and its team members.
//     * Dependent on the fields probabilityOfPointsDistributionAdditionalNonTeamMembers and random dice throws,
//     * a configuration is saved in the database and a DistributionConfiguration object returned, which can be
//     * used for later verification of the distributed points.
//     *
//     * @param proposal
//     * @param teamMembers
//     * @param isGlobal
//     * @return
//     * @throws com.liferay.portal.NoSuchUserException
//     * @throws com.liferay.portal.kernel.exception.SystemException
//     */
//    protected DistributionConfiguration setPointsDistributionForProposal(Proposal proposal, List<User> teamMembers, boolean isGlobal) throws NoSuchUserException, SystemException {
//        DistributionConfiguration config = new DistributionConfiguration();
//
//        //ANY TEAM MEMBER
//        User author = teamMembers.get(0);
//        double sumOfShares = 0.0;
//        for (int i = 0; i < teamMembers.size(); i++) {
//            User teamMember = teamMembers.get(i);
//            //distribute randomly until the last member. he will get the rest
//            double share = 0;
//            if (i < teamMembers.size()-1) {
//                if (sumOfShares < 1) {
//                    do {
//                        share = generateProbabilityForTeamMembers(teamMembers.size());
//                    } while (share + sumOfShares > 1.0);
//                }
//            } else {
//                share = 1-sumOfShares;
//            }
//
//            config.teamMemberShares.put(i, share);
//
//            testInstance.pointsDistributionConfigurationService.addDistributionConfiguration(
//                    proposal.getProposalId(),
//                    isGlobal ? 2 : 7, //team member point type
//                    teamMember.getUserId(),
//                    0L,
//                    share,
//                    author.getUserId()
//            );
//            sumOfShares += share;
//        }
//        assertTrue(sumOfShares == 1);
//
//        sumOfShares = 0.0;
//        //ANY NON-TEAM-MEMBER
//        List<User> chosenRandomUsers = new ArrayList<User>();
//        for (int i = 1; doWithProbability(probabilityOfPointsDistributionAdditionalNonTeamMembers/i); i++) {
//            int randomUserIndex = getRandomNonTeamUser(teamMembers, chosenRandomUsers);
//            User randomUser = users.get(randomUserIndex);
//
//            double share = 0;
//            if (sumOfShares < 1) {
//                do {
//                    share = generateProbabilityForTeamMembers(i);
//                } while (share + sumOfShares > 1.0);
//
//                if (share > 0) {
//                    testInstance.pointsDistributionConfigurationService.addDistributionConfiguration(
//                            proposal.getProposalId(),
//                            isGlobal ? 5 : 8, //any non-team-member
//                            randomUser.getUserId(),
//                            0L,
//                            share,
//                            author.getUserId()
//                    );
//                    config.additionalShares.put(randomUserIndex, share);
//                    sumOfShares += share;
//                    chosenRandomUsers.add(randomUser);
//                }
//            }
//        }
//        assertTrue(sumOfShares <= 1);
//
//        if (sumOfShares >= 0 && sumOfShares < 1) {
//            int randomUserIndex = getRandomNonTeamUser(teamMembers, chosenRandomUsers);
//            User randomUser = users.get(randomUserIndex);
//            double share = 1-sumOfShares;
//            //add another one to make the sum total 1
//            testInstance.pointsDistributionConfigurationService.addDistributionConfiguration(
//                    proposal.getProposalId(),
//                    isGlobal ? 5 : 8, //any non-team-member
//                    randomUser.getUserId(),
//                    0L,
//                    share,
//                    author.getUserId()
//            );
//            config.additionalShares.put(randomUserIndex, share);
//            sumOfShares += share;
//            chosenRandomUsers.add(randomUser);
//        }
//
//        assertTrue(sumOfShares == 1);
//        //make sure again that additional shares are 1
//        if (config.additionalShares.size() > 0) {
//            double sum = 0.0;
//            for (Double value: config.additionalShares.values()) {
//                sum += value;
//            }
//            if (sum != 1.0) {
//                //put a break point in this row. usually it's a rounding issue, still has to be inspected.
//                System.out.println("SUM DOES NOT EQUAL 1.0: "+sum);
//            }
//        }
//
//        return config;
//    }
//
//    private void assertDistributionForGlobalProposal(int proposalIndex, long sourceId, double hypotheticalPoints, double materializedPoints) {
//        DistributionConfiguration config = globalProposalsDistributions.get(proposalIndex);
//        Proposal p = globalProposals.get(proposalIndex);
//        double matTeamPoints =  Math.ceil(materializedPoints)*0.2*0.9;
//        double hypTeamPoints =  Math.ceil(hypotheticalPoints)*0.2*0.9;
//        double matNonTeamPoints =  Math.ceil(materializedPoints)*0.2*0.1;
//        double hypNonTeamPoints =  Math.ceil(hypotheticalPoints)*0.2*0.1;
//
//        int amountOfSubProposals = 0;
//
//        for (int i = 0; i < globalProposalLinksToSideProposals.get(proposalIndex).size(); i++) {
//            amountOfSubProposals += globalProposalLinksToSideProposals.get(proposalIndex).get(i).size();
//        }
//        double hypPointsPerSubProposal = amountOfSubProposals > 0 ? ( Math.ceil(hypotheticalPoints)*0.8)/amountOfSubProposals : 0;
//        double matPointsPerSubProposal = amountOfSubProposals > 0 ? ( Math.ceil(materializedPoints)*0.8)/amountOfSubProposals : 0;
//
//        //TEAM
//        if (hypTeamPoints >= 1) {
//            for (int j = 0; j < globalProposalsTeamMembers.get(proposalIndex).size(); j++) {
//                User teamMember = globalProposalsTeamMembers.get(proposalIndex).get(j);
//                double share;
//                if (config != null) {
//                    share = config.teamMemberShares.get(j);
//                } else {
//                    //no config is present. shares are distributed equally
//                    share = 1.00 / globalProposalsTeamMembers.get(proposalIndex).size();
//                }
//                assertNotNull(popPointEntryInList(points, p.getProposalId(), teamMember.getUserId(), sourceId, Math.ceil(matTeamPoints * share), Math.ceil(hypTeamPoints * share)));
//            }
//        }
//
//        //NON-TEAM
//        if (hypNonTeamPoints >= 1 && config != null) {
//            for (int userIndex: config.additionalShares.keySet()) {
//                double share = config.additionalShares.get(userIndex);
//                assertNotNull(popPointEntryInList(points, p.getProposalId(), users.get(userIndex).getUserId(), sourceId, Math.ceil(matNonTeamPoints*share), Math.ceil(hypNonTeamPoints*share)));
//            }
//        }
//
//        //SUB-PROPOSALS
//        if (hypPointsPerSubProposal >= 1 && amountOfSubProposals > 0) {
//            //first gather all linked proposal ids and where they are located in our fields
//            List<Long> childrenProposalIds = new ArrayList<Long>();
//            List<Integer[]> childrenSideProposalIndizes = new ArrayList<Integer[]>();
//
//            for (int i = 0; i < globalProposalLinksToSideProposals.get(proposalIndex).size(); i++) {
//                for (Integer subProposalIndex : globalProposalLinksToSideProposals.get(proposalIndex).get(i)) {
//                    childrenProposalIds.add(sideProposals.get(i).get(subProposalIndex).getProposalId());
//                    childrenSideProposalIndizes.add(new Integer[]{i, subProposalIndex});
//                }
//            }
//
//            //now go through all linked proposals and find their origin
//            for (int i = 0; i < amountOfSubProposals; i++) {
//                //find the related subProposal Point entry
//                Points subProposalSourcePoints = popPointEntryInList(points, p.getProposalId(), 0, sourceId, Math.ceil(matPointsPerSubProposal), Math.ceil(hypPointsPerSubProposal));
//                assertNotNull(subProposalSourcePoints);
//
//                //find the proposalId which has the sub-proposal Point entry as a parent
//                Long proposalId = getProposalIdByPointsSourceIdInList(points, subProposalSourcePoints.getId());
//                assertNotNull(proposalId);
//
//                //remove
//                int indexOfProposalId = childrenProposalIds.indexOf(proposalId);
//                if (indexOfProposalId > -1) {
//                    childrenProposalIds.remove(indexOfProposalId);
//                } else {
//                    throw new RuntimeException("Wrong sub-proposal " + proposalId);
//                }
//                Integer[] sideProposalIndex = childrenSideProposalIndizes.get(indexOfProposalId);
//                childrenSideProposalIndizes.remove(indexOfProposalId);
//
//                this.assertDistributionForSideProposal(sideProposalIndex, subProposalSourcePoints.getId(), hypPointsPerSubProposal, matPointsPerSubProposal);
//            }
//            // we are done. all the generated arrays should be empty by now.
//            assertTrue(childrenProposalIds.isEmpty());
//            assertTrue(childrenSideProposalIndizes.isEmpty());
//        }
//    }
//
//
//    private void assertDistributionForSideProposal(Integer[] sideProposalIndex, long sourceId, double hypotheticalPoints, double materializedPoints) {
//        assertTrue(sideProposalIndex.length == 2);
//        int contestIndex = sideProposalIndex[0];
//        int proposalIndex = sideProposalIndex[1];
//
//        DistributionConfiguration config = sideProposalsDistributions.get(contestIndex).get(proposalIndex);
//        Proposal p = sideProposals.get(contestIndex).get(proposalIndex);
//        double matTeamPoints = Math.ceil(materializedPoints)*0.8;
//        double hypTeamPoints = Math.ceil(hypotheticalPoints)*0.8;
//        double matNonTeamPoints = Math.ceil(materializedPoints)*0.2;
//        double hypNonTeamPoints = Math.ceil(hypotheticalPoints)*0.2;
//
//        //TEAM
//        if (hypTeamPoints >= 1) {
//            List<User> teamMembers = sideProposalsTeamMembers.get(contestIndex).get(proposalIndex);
//            for (int j = 0; j < teamMembers.size(); j++) {
//                User teamMember = teamMembers.get(j);
//                double share;
//                if (config != null) {
//                    share = config.teamMemberShares.get(j);
//                } else {
//                    //no config is present. shares are distributed equally
//                    share = 1.00 / teamMembers.size();
//                }
//                assertNotNull(popPointEntryInList(points, p.getProposalId(), teamMember.getUserId(), sourceId, Math.ceil(matTeamPoints * share), Math.ceil(hypTeamPoints * share)));
//            }
//        }
//
//        //NON-TEAM
//        if (hypNonTeamPoints >= 1 && config != null) {
//            for (int userIndex: config.additionalShares.keySet()) {
//                double share = config.additionalShares.get(userIndex);
//                assertNotNull(popPointEntryInList(points, p.getProposalId(), users.get(userIndex).getUserId(), sourceId, Math.ceil(matNonTeamPoints * share), Math.ceil(hypNonTeamPoints*share)));
//            }
//        }
//    }
//
//    public void assertPointDistributions() throws SystemException, PortalException {
//        //Assure that the individual Point data entries are correct
//        this.points = new ArrayList<Points>(testInstance.pointsLocalService.getPointses(0, Integer.MAX_VALUE));
//
//        //if the contest has ended, and the targets are not defined, the winner will materialize all points.
//        if (hasContestEnded && globalProposalsDistributionTargets.size() == 0) {
//            //if there are no distribution targets assigned, the winning proposal (if exists) gets all points.
//            Proposal winningProposal = testInstance.contestLocalService.getWinnerProposal(globalContest.getContestPK());
//            if (winningProposal != null) {
//                //create artificial pdt, this lets us treat the winning case and the target-defined case equally afterwards.
//                PointDistributionTarget pdt = testInstance.pointDistributionTargetService.createPointDistributionTarget(1);
//                pdt.setContestId(globalContest.getContestPK());
//                pdt.setProposalId(winningProposal.getProposalId());
//                pdt.setNumberOfPoints(pointsToBeDistributed);
//                //note that we do not save the pdt in the db
//                globalProposalsDistributionTargets.put(this.getProposalIndexForProposal(winningProposal), pdt);
//            }
//        }
//
//        for (int i : globalProposalsInLastPhase) {
//            double materializedPoints = 0L;
//            if (globalProposalsDistributionTargets.size() > 0 && globalProposalsDistributionTargets.get(i) != null) {
//                materializedPoints = globalProposalsDistributionTargets.get(i).getNumberOfPoints();
//            }
//            this.assertDistributionForGlobalProposal(i, 0L, pointsToBeDistributed, materializedPoints);
//        }
//
//        //all points should have been popped and verified after this. so the array should be empty.
//        assertTrue(points.isEmpty());
//    }
//
//    protected int getProposalIndexForProposal(Proposal proposal) {
//        //find out which index this proposal has
//        int index = -1;
//        for (int i = 0; i < amountOfGlobalProposals; i++) {
//            if (globalProposals.get(i).getProposalId() == proposal.getProposalId()) {
//                index = i;
//                break;
//            }
//        }
//        return index;
//    }
//
//    protected Long getProposalIdByPointsSourceIdInList(List<Points> points, long pointsSourceId) {
//        for (Points p : points) {
//            if (p.getPointsSourceId() == pointsSourceId) {
//                return p.getProposalId();
//            }
//        }
//        return null;
//    }
//
//    protected Points popPointEntryInList(List<Points> points, long proposalId, long userId, long pointsSourceId, double materializedPoints, double hypotheticalPoints) {
//        Points find = null;
//        for (Points p : points) {
//            if (p.getProposalId() == proposalId &&
//                    p.getUserId() == userId &&
//                    p.getMaterializedPoints() == materializedPoints &&
//                    p.getHypotheticalPoints() == hypotheticalPoints &&
//                    p.getPointsSourceId() == pointsSourceId) {
//                find = p;
//                break;
//            }
//        }
//        if (find != null) points.remove(find);
//        return find; //put a breakpoint here with condition find == null
//    }
}
