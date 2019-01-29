package org.xcolab.client.contest.proposals.enums.points;

import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.IPointsDistributionConfiguration;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.PointTypeWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.util.enums.contest.ContestTier;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public enum ReceiverLimitationStrategy {
    ANY_USER(Type.USER, (proposal, pointType, distributionStrategy) -> {
        // check if there is any configuration, if there is create appropriate targets
        List<PointsTarget> targets = new ArrayList<>();
        if (distributionStrategy == DistributionStrategy.USER_DEFINED) {
            for (IPointsDistributionConfiguration pdc: StaticProposalContext.getPointsClient()
                    .getPointsDistributionByProposalIdPointTypeId(proposal.getId(), pointType.getId())) {
                if (pdc.getTargetUserId() > 0) {
                    PointsTarget target = new PointsTarget();
                    target.setUserId(pdc.getTargetUserId());
                    target.setPercentage(pdc.getPercentage());
                    targets.add(target);
                }
            }
        }
        return targets;
    }),

    ANY_NON_TEAM_MEMBER(Type.USER, (proposal, pointType, distributionStrategy) -> {
        List<PointsTarget> targets = new ArrayList<>();

        if (distributionStrategy == DistributionStrategy.USER_DEFINED) {
            for (IPointsDistributionConfiguration pdc: StaticProposalContext.getPointsClient()
                    .getPointsDistributionByProposalIdPointTypeId(proposal.getId(), pointType.getId())) {
                if (pdc.getTargetUserId() > 0 && !StaticProposalContext.getProposalClient()
                        .isUserInProposalTeam(proposal.getId(), pdc.getTargetUserId())) {
                    PointsTarget target = new PointsTarget();
                    target.setUserId(pdc.getTargetUserId());
                    target.setPercentage(pdc.getPercentage());
                    targets.add(target);
                }
            }
        }
        return targets;
    }),
    ANY_TEAM_MEMBER(Type.USER, (proposal, pointType, distributionStrategy) -> {
        List<PointsTarget> targets = new ArrayList<>();

        if (distributionStrategy == DistributionStrategy.USER_DEFINED) {
            for (IPointsDistributionConfiguration pdc: StaticProposalContext.getPointsClient()
                    .getPointsDistributionByProposalIdPointTypeId(proposal.getId(), pointType.getId())) {
                if (pdc.getTargetUserId() > 0 && StaticProposalContext.getProposalClient()
                        .isUserInProposalTeam(proposal.getId(), pdc.getTargetUserId())) {
                    PointsTarget target = new PointsTarget();
                    target.setUserId(pdc.getTargetUserId());
                    target.setPercentage(pdc.getPercentage());
                    targets.add(target);
                }
            }
            if (targets.isEmpty()) {
                return PointsDistributionUtil.distributeEquallyAmongContributors(proposal.getId());
            }
        } else if (distributionStrategy == DistributionStrategy.EQUAL_DIVISION) {
            return PointsDistributionUtil.distributeEquallyAmongContributors(proposal.getId());
        }
        return targets;
    }),
    SUBPROPOSALS(Type.SUB_PROPOSAL, (proposal, pointType, distributionStrategy) -> {
        List<ProposalWrapper> subProposals = StaticProposalContext.getProposalClient()
                .getSubproposals(proposal.getId(), false);
        Set<Long> subProposalIds = new HashSet<>();
        for (ProposalWrapper subProposal : subProposals) {
            subProposalIds.add(subProposal.getId());
        }
        return PointsDistributionUtil.distributeAmongProposals(distributionStrategy, proposal, pointType, subProposalIds);
    }),
    REGIONAL_SUBPROPOSALS(Type.SUB_PROPOSAL, (proposal, pointType, distributionStrategy) -> {
        try {
            List<ProposalWrapper> subProposals = StaticProposalContext.getProposalClient()
                    .getSubproposals(proposal.getId(), false);
            Set<Long> subProposalIds = new HashSet<>();
            for (ProposalWrapper subProposal : subProposals) {
                final ContestWrapper latestProposalContest = StaticProposalContext.getProposalClient()
                        .getCurrentContestForProposal(subProposal.getId());
                final ContestTier contestTier = ContestTier.getContestTierByTierType(latestProposalContest.getContestTier());
                if (contestTier == ContestTier.REGION_AGGREGATE) {
                    subProposalIds.add(subProposal.getId());
                }
            }
            subProposalIds.remove(proposal.getId());
            return PointsDistributionUtil.distributeAmongProposals(distributionStrategy, proposal, pointType, subProposalIds);
        } catch (ContestNotFoundException ignored) {

        }
        return null;
    }),
    BASIC_SUBPROPOSALS(Type.SUB_PROPOSAL, (proposal, pointType, distributionStrategy) -> {
        List<ProposalWrapper> subProposals = StaticProposalContext.getProposalClient()
                .getSubproposals(proposal.getId(), false);
        Set<Long> subProposalIds = new HashSet<>();
        for (ProposalWrapper subProposal : subProposals) {
            try {
                final ContestWrapper latestProposalContest = StaticProposalContext.getProposalClient()
                        .getCurrentContestForProposal(subProposal.getId());
                final ContestTier contestTier = ContestTier.getContestTierByTierType(latestProposalContest.getContestTier());
                if (contestTier == ContestTier.BASIC || contestTier == ContestTier.NONE) {
                    subProposalIds.add(subProposal.getId());
                }
            }catch (ContestNotFoundException ignored){

            }
        }
        subProposalIds.remove(proposal.getId());
        return PointsDistributionUtil.distributeAmongProposals(distributionStrategy, proposal, pointType, subProposalIds);
    }),
    NONE(Type.OTHER, (proposal, pointType, distributionStrategy) -> null);

    private final Type type;
    private final ReceiverLimitationTargetsPickerAlgorithm targetsPickerAlgorithm;

    ReceiverLimitationStrategy(Type type, ReceiverLimitationTargetsPickerAlgorithm algorithm) {
        this.type = type;
        targetsPickerAlgorithm = algorithm;
    }

    public List<PointsTarget> getTargets(ProposalWrapper proposal, PointTypeWrapper pointType, DistributionStrategy distributionStrategy) {
        return targetsPickerAlgorithm.getPointTargets(proposal, pointType, distributionStrategy);

    }

    public Type getType() {
        return type;
    }

    public interface ReceiverLimitationTargetsPickerAlgorithm {
        List<PointsTarget> getPointTargets(ProposalWrapper proposal, PointTypeWrapper pointType,
                DistributionStrategy distributionStrategy) ;
    }

    public enum Type {
        USER, SUB_PROPOSAL, OTHER
    }
}
