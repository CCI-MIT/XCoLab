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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Manuel Thurner on 22/09/14.
 */
public class GlobalContestPointsSimulator extends GlobalContestSimulator {
    private double probabilityOfPointsDistributionAdditionalNonTeamMembers;
    protected Map<Integer, DistributionConfiguration> globalProposalsDistributions;
    protected Map<Integer, Map<Integer, DistributionConfiguration>> sideProposalsDistributions;
    protected List<Points> points;

    class DistributionConfiguration {
        //the teamMemberShares indizes are the indizes in the proposalTeamMembers field
        public Map<Integer, Double> teamMemberShares = new HashMap<Integer, Double>();
        //in additionalShares, the indizes are the indizes of the users field
        public Map<Integer, Double> additionalShares = new HashMap<Integer, Double>();
    }

    public void deletePointDistributions() throws SystemException {
        //delete points
        List<Points> points = testInstance.pointsLocalService.getPointses(0, Integer.MAX_VALUE);
        for (Points p: points) {
            testInstance.pointsLocalService.deletePoints(p);
        }

        //delete configurations
        List<PointsDistributionConfiguration> configs = testInstance.pointsDistributionConfigurationService.getPointsDistributionConfigurations(0, Integer.MAX_VALUE);
        for (PointsDistributionConfiguration pdc: configs) {
            testInstance.pointsDistributionConfigurationService.deletePointsDistributionConfiguration(pdc);
        }
        globalProposalsDistributions = null;
        sideProposalsDistributions = null;
        this.points = null;
        probabilityOfPointsDistributionAdditionalNonTeamMembers = 0;
    }

    public void setPointsDistributions(
            double probabilityOfEmptyGlobalProposalConfiguration,
            double probabilityOfEmptySideProposalConfiguration,
            double probabilityOfPointsDistributionAdditionalNonTeamMembers
    ) throws NoSuchUserException, SystemException {
        globalProposalsDistributions = new HashMap<>();
        sideProposalsDistributions = new HashMap<>();

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
            sideProposalsDistributions.put(i, new HashMap<Integer, DistributionConfiguration>());
            for (int j = 0; j < amountOfProposalsPerSideContest; j++) {
                if (!doWithProbability(probabilityOfEmptySideProposalConfiguration)) {
                    DistributionConfiguration config = this.setPointsDistributionForProposal(sideProposals.get(i).get(j), sideProposalsTeamMembers.get(i).get(j), false);
                    sideProposalsDistributions.get(i).put(j, config);
                }
            }
        }
    }

    public void runPointDistributionAlgorithm() throws SystemException, PortalException {
        testInstance.pointsLocalService.distributePoints(globalContest.getContestPK());
    }


    private int getRandomNonTeamUser(List<User> teamMembers, List <User> alreadyChosenMembers) {
        int randomUserIndex = randomInt(0, amountOfUsers);
        User randomUser = users.get(randomUserIndex);
        if (teamMembers.contains(randomUser) || alreadyChosenMembers.contains(randomUser)) {
            return getRandomNonTeamUser(teamMembers, alreadyChosenMembers);
        } else {
            return randomUserIndex;
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
     * @throws com.liferay.portal.NoSuchUserException
     * @throws com.liferay.portal.kernel.exception.SystemException
     */
    private DistributionConfiguration setPointsDistributionForProposal(Proposal proposal, List<User> teamMembers, boolean isGlobal) throws NoSuchUserException, SystemException {
        DistributionConfiguration config = new DistributionConfiguration();

        //ANY TEAM MEMBER
        User author = teamMembers.get(0);
        double sumOfShares = 0.0;
        for (int i = 0; i < teamMembers.size(); i++) {
            User teamMember = teamMembers.get(i);
            //distribute randomly until the last member. he will get the rest
            double share = 0;
            if (i < teamMembers.size()-1) {
                if (sumOfShares < 1) {
                    do {
                        share = generateProbabilityForTeamMembers(teamMembers.size());
                    } while (share + sumOfShares > 1.0);
                }
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
        List<User> chosenRandomUsers = new ArrayList<User>();
        for (int i = 1; doWithProbability(probabilityOfPointsDistributionAdditionalNonTeamMembers/i); i++) {
            int randomUserIndex = getRandomNonTeamUser(teamMembers, chosenRandomUsers);
            User randomUser = users.get(randomUserIndex);

            double share = 0;
            if (sumOfShares < 1) {
                do {
                    share = generateProbabilityForTeamMembers(i);
                } while (share + sumOfShares > 1.0);

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
                    chosenRandomUsers.add(randomUser);
                }
            }
        }
        assertTrue(sumOfShares <= 1);

        if (sumOfShares >= 0 && sumOfShares < 1) {
            int randomUserIndex = getRandomNonTeamUser(teamMembers, chosenRandomUsers);
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
            chosenRandomUsers.add(randomUser);
        }

        assertTrue(sumOfShares == 1);
        //make sure again that additional shares are 1
        if (config.additionalShares.size() > 0) {
            double sum = 0.0;
            for (Double value: config.additionalShares.values()) {
                sum += value;
            }
            if (sum != 1.0) {
                //put a break point in this row. usually it's a rounding issue, still has to be inspected.
                System.out.println("SUM DOES NOT EQUAL 1.0: "+sum);
            }
        }

        return config;
    }

    private void assertDistributionForGlobalProposal(int proposalIndex, long sourceId, double pointsToBeDistributed) {
        DistributionConfiguration config = globalProposalsDistributions.get(proposalIndex);
        Proposal p = globalProposals.get(proposalIndex);
        double teamPoints =  Math.ceil(pointsToBeDistributed)*0.2*0.9;
        double nonTeamPoints =  Math.ceil(pointsToBeDistributed)*0.2*0.1;

        int amountOfSubProposals = globalProposalLinksToGlobalProposals.get(proposalIndex).size();
        for (int i = 0; i < globalProposalLinksToSideProposals.get(proposalIndex).size(); i++) {
            amountOfSubProposals += globalProposalLinksToSideProposals.get(proposalIndex).get(i).size();
        }
        double pointsPerSubProposal = amountOfSubProposals > 0 ? ( Math.ceil(pointsToBeDistributed)*0.8)/amountOfSubProposals : 0;

        //TEAM
        if (teamPoints >= 1) {
            for (int j = 0; j < globalProposalsTeamMembers.get(proposalIndex).size(); j++) {
                User teamMember = globalProposalsTeamMembers.get(proposalIndex).get(j);
                double share;
                if (config != null) {
                    share = config.teamMemberShares.get(j);
                } else {
                    //no config is present. shares are distributed equally
                    share = 1.00 / globalProposalsTeamMembers.get(proposalIndex).size();
                }
                assertNotNull(popPointEntryInList(points, p.getProposalId(), teamMember.getUserId(), sourceId, 0, Math.ceil(teamPoints * share)));
            }
        }

        //NON-TEAM
        if (nonTeamPoints >= 1 && config != null) {
            for (int userIndex: config.additionalShares.keySet()) {
                double share = config.additionalShares.get(userIndex);
                assertNotNull(popPointEntryInList(points, p.getProposalId(), users.get(userIndex).getUserId(), sourceId, 0, Math.ceil(nonTeamPoints*share)));
            }
        }

        //SUB-PROPOSALS
        if (Math.ceil(pointsToBeDistributed)*0.8 >= 1 && amountOfSubProposals > 0) {
            //first gather all linked proposal ids and where they are located in our fields
            List<Long> childrenProposalIds = new ArrayList<Long>();
            List<Boolean> childrenProposalIsGlobal = new ArrayList<Boolean>();
            List<Integer> childrenGlobalProposalIndizes = new ArrayList<Integer>();
            List<Integer[]> childrenSideProposalIndizes = new ArrayList<Integer[]>();
            for (Integer subProposalIndex : globalProposalLinksToGlobalProposals.get(proposalIndex)) {
                childrenProposalIds.add(globalProposals.get(subProposalIndex).getProposalId());
                childrenGlobalProposalIndizes.add(subProposalIndex);
                childrenSideProposalIndizes.add(new Integer[]{});
                childrenProposalIsGlobal.add(true);
            }
            for (int i = 0; i < globalProposalLinksToSideProposals.get(proposalIndex).size(); i++) {
                for (Integer subProposalIndex : globalProposalLinksToSideProposals.get(proposalIndex).get(i)) {
                    childrenProposalIds.add(sideProposals.get(i).get(subProposalIndex).getProposalId());
                    childrenProposalIsGlobal.add(false);
                    childrenGlobalProposalIndizes.add(-1);
                    childrenSideProposalIndizes.add(new Integer[]{i, subProposalIndex});
                }
            }

            //now go through all linked proposals and find their origin
            for (int i = 0; i < amountOfSubProposals; i++) {
                //find the related subProposal Point entry
                Points subProposalSourcePoints = popPointEntryInList(points, p.getProposalId(), 0, sourceId, 0, Math.ceil(pointsPerSubProposal));
                assertNotNull(subProposalSourcePoints);

                //find the proposalId which has the sub-proposal Point entry as a parent
                Long proposalId = getProposalIdByPointsSourceIdInList(points, subProposalSourcePoints.getId());
                assertNotNull(proposalId);

                //remove
                int indexOfProposalId = childrenProposalIds.indexOf(proposalId);
                Boolean isProposalGlobal = childrenProposalIsGlobal.get(indexOfProposalId);
                if (indexOfProposalId > -1) {
                    childrenProposalIds.remove(indexOfProposalId);
                    childrenProposalIsGlobal.remove(indexOfProposalId);
                } else {
                    throw new RuntimeException("Wrong sub-proposal " + proposalId);
                }
                //distinguish between proposal types
                if (isProposalGlobal) {
                    int globalProposalIndex = childrenGlobalProposalIndizes.get(indexOfProposalId);
                    childrenGlobalProposalIndizes.remove(indexOfProposalId);
                    childrenSideProposalIndizes.remove(indexOfProposalId);

                    this.assertDistributionForGlobalProposal(globalProposalIndex, subProposalSourcePoints.getId(), pointsPerSubProposal);
                } else {
                    Integer[] sideProposalIndex = childrenSideProposalIndizes.get(indexOfProposalId);
                    childrenGlobalProposalIndizes.remove(indexOfProposalId);
                    childrenSideProposalIndizes.remove(indexOfProposalId);

                    this.assertDistributionForSideProposal(sideProposalIndex, subProposalSourcePoints.getId(), pointsPerSubProposal);
                }
            }
            // we are done. all the generated arrays should be empty by now.
            assertTrue(childrenProposalIds.isEmpty());
            assertTrue(childrenProposalIsGlobal.isEmpty());
            assertTrue(childrenGlobalProposalIndizes.isEmpty());
            assertTrue(childrenSideProposalIndizes.isEmpty());
        }
    }


    private void assertDistributionForSideProposal(Integer[] sideProposalIndex, long sourceId, double pointsToBeDistributed) {
        assertTrue(sideProposalIndex.length == 2);
        int contestIndex = sideProposalIndex[0];
        int proposalIndex = sideProposalIndex[1];

        DistributionConfiguration config = sideProposalsDistributions.get(contestIndex).get(proposalIndex);
        Proposal p = sideProposals.get(contestIndex).get(proposalIndex);
        double teamPoints = Math.ceil(pointsToBeDistributed)*0.8;
        double nonTeamPoints = Math.ceil(pointsToBeDistributed)*0.2;

        //TEAM
        if (teamPoints >= 1) {
            List<User> teamMembers = sideProposalsTeamMembers.get(contestIndex).get(proposalIndex);
            for (int j = 0; j < teamMembers.size(); j++) {
                User teamMember = teamMembers.get(j);
                double share;
                if (config != null) {
                    share = config.teamMemberShares.get(j);
                } else {
                    //no config is present. shares are distributed equally
                    share = 1.00 / teamMembers.size();
                }
                assertNotNull(popPointEntryInList(points, p.getProposalId(), teamMember.getUserId(), sourceId, 0, Math.ceil(teamPoints * share)));
            }
        }

        //NON-TEAM
        if (nonTeamPoints >= 1 && config != null) {
            for (int userIndex: config.additionalShares.keySet()) {
                double share = config.additionalShares.get(userIndex);
                assertNotNull(popPointEntryInList(points, p.getProposalId(), users.get(userIndex).getUserId(), sourceId, 0, Math.ceil(nonTeamPoints*share)));
            }
        }
    }

    public void assertPointDistributions() throws SystemException {
        //Assure that the individual Point data entries are correct
        this.points = new ArrayList<Points>(testInstance.pointsLocalService.getPointses(0, Integer.MAX_VALUE));

        for (int i: globalProposalsInLastPhase) {
            this.assertDistributionForGlobalProposal(i, 0L, pointsToBeDistributed);
        }

        //all points should have been popped and verified after this. so the array should be empty.
        assertTrue(points.isEmpty());
    }

    private Long getProposalIdByPointsSourceIdInList(List<Points> points, long pointsSourceId) {
        for (Points p : points) {
            if (p.getPointsSourceId() == pointsSourceId) {
                return p.getProposalId();
            }
        }
        return null;
    }

    private Points popPointEntryInList(List<Points> points, long proposalId, long userId, long pointsSourceId, double materializedPoints, double hypotheticalPoints) {
        Points find = null;
        for (Points p : points) {
            if (p.getProposalId() == proposalId &&
                    p.getUserId() == userId &&
                    p.getMaterializedPoints() == materializedPoints &&
                    p.getHypotheticalPoints() == hypotheticalPoints &&
                    p.getPointsSourceId() == pointsSourceId) {
                find = p;
                break;
            }
        }
        if (find != null) points.remove(find);
        return find; //put a breakpoint here with condition find == null
    }
}
