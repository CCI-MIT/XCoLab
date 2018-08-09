package org.xcolab.service.proposal.enums;

import org.springframework.beans.factory.annotation.Autowired;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.model.tables.pojos.PointType;
import org.xcolab.model.tables.pojos.PointsDistributionConfiguration;
import org.xcolab.model.tables.pojos.Proposal;
import org.xcolab.service.proposal.service.pointsdistributionconfiguration.PointsDistributionConfigurationService;
import org.xcolab.service.proposal.service.proposal.ProposalService;
import org.xcolab.util.enums.contest.ContestTier;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public enum ReceiverLimitationStrategy {

	ANY_USER(Type.USER, new ReceiverLimitationTargetsPickerAlgorithm() {

		@Autowired
		PointsDistributionConfigurationService pointsDistributionConfigurationService;

		@Override
		public List<PointsTarget> getPointTargets(Proposal proposal, PointType pointType, DistributionStrategy distributionStrategy) {
			// check if there is any configuration, if there is create appropriate targets
			List<PointsTarget> targets = new ArrayList<>();
			if (distributionStrategy == DistributionStrategy.USER_DEFINED) {

				for (PointsDistributionConfiguration pdc: pointsDistributionConfigurationService.getPointsDistributionConfiguration(proposal.getId(), pointType.getId())) {
					if (pdc.getTargetUserId() > 0) {
						PointsTarget target = new PointsTarget();
						target.setUserId(pdc.getTargetUserId());
						target.setPercentage(pdc.getPercentage());
						targets.add(target);
					}
				}
			}
			return targets;
		}
		
	}), 
	
	ANY_NON_TEAM_MEMBER(Type.USER, new ReceiverLimitationTargetsPickerAlgorithm() {

		@Autowired
		PointsDistributionConfigurationService pointsDistributionConfigurationService;
		@Autowired
		ProposalService proposalService;

		@Override
		public List<PointsTarget> getPointTargets(Proposal proposal,
				PointType pointType, DistributionStrategy distributionStrategy) {
			List<PointsTarget> targets = new ArrayList<>();
			
			if (distributionStrategy == DistributionStrategy.USER_DEFINED) {
				for (PointsDistributionConfiguration pdc: pointsDistributionConfigurationService.getPointsDistributionConfiguration(proposal.getId(), pointType.getId())) {
					if (pdc.getTargetUserId() > 0 && !proposalService.isUserAMember(proposal.getId(), pdc.getTargetUserId())) {
						PointsTarget target = new PointsTarget();
						target.setUserId(pdc.getTargetUserId());
						target.setPercentage(pdc.getPercentage());
						targets.add(target);
					}
				}
			}
			return targets;
		}
		
	}),
	ANY_TEAM_MEMBER(Type.USER, new ReceiverLimitationTargetsPickerAlgorithm() {

        @Autowired
        PointsDistributionConfigurationService pointsDistributionConfigurationService;
        @Autowired
        ProposalService proposalService;

		@Override
		public List<PointsTarget> getPointTargets(Proposal proposal,
				PointType pointType, DistributionStrategy distributionStrategy)  {
			List<PointsTarget> targets = new ArrayList<>();

			if (distributionStrategy == DistributionStrategy.USER_DEFINED) {
				for (PointsDistributionConfiguration pdc: pointsDistributionConfigurationService.getPointsDistributionConfiguration(proposal.getId(), pointType.getId())) {
					if (pdc.getTargetUserId() > 0 && proposalService.isUserAMember(proposal.getId(), pdc.getTargetUserId())) {
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
		}
		
	}),
    SUBPROPOSALS(Type.SUB_PROPOSAL, new ReceiverLimitationTargetsPickerAlgorithm() {

        @Autowired
        ProposalService proposalService;

        @Override
        public List<PointsTarget> getPointTargets(Proposal proposal,
                                                  PointType pointType, DistributionStrategy distributionStrategy)  {
            List<Proposal> subProposals = proposalService.getSubproposals(proposal.getId(), false);
            Set<Long> subProposalIds = new HashSet<>();
            for(Proposal subProposal : subProposals) {
                subProposalIds.add(subProposal.getId());
            }
            return PointsDistributionUtil.distributeAmongProposals(distributionStrategy, proposal, pointType, subProposalIds);
        }

    }),
    REGIONAL_SUBPROPOSALS(Type.SUB_PROPOSAL, new ReceiverLimitationTargetsPickerAlgorithm() {

        @Autowired
        ProposalService proposalService;

        @Override
        public List<PointsTarget> getPointTargets(Proposal proposal,
                                                  PointType pointType, DistributionStrategy distributionStrategy) {
            List<Proposal> subProposals = proposalService.getSubproposals(proposal.getId(), false);
            Set<Long> subProposalIds = new HashSet<>();
            for (Proposal subProposal : subProposals) {
                final Contest latestProposalContest = proposalService.getLatestProposalContest(subProposal.getId());
                final ContestTier contestTier = ContestTier.getContestTierByTierType(latestProposalContest.getContestTier());
                if (contestTier == ContestTier.REGION_AGGREGATE) {
                    subProposalIds.add(subProposal.getId());
                }
            }
            subProposalIds.remove(proposal.getId());
            return PointsDistributionUtil.distributeAmongProposals(distributionStrategy, proposal, pointType, subProposalIds);
        }

    }),
    BASIC_SUBPROPOSALS(Type.SUB_PROPOSAL, new ReceiverLimitationTargetsPickerAlgorithm() {

        @Autowired
        ProposalService proposalService;

        @Override
        public List<PointsTarget> getPointTargets(Proposal proposal,
                                                  PointType pointType, DistributionStrategy distributionStrategy) {
            List<Proposal> subProposals = proposalService.getSubproposals(proposal.getId(), false);
            Set<Long> subProposalIds = new HashSet<>();
            for (Proposal subProposal : subProposals) {
                final Contest latestProposalContest = proposalService.getLatestProposalContest(subProposal.getId());
                final ContestTier contestTier = ContestTier.getContestTierByTierType(latestProposalContest.getContestTier());
                if (contestTier == ContestTier.BASIC || contestTier == ContestTier.NONE) {
                    subProposalIds.add(subProposal.getId());
                }
            }
            subProposalIds.remove(proposal.getId());
            return PointsDistributionUtil.distributeAmongProposals(distributionStrategy, proposal, pointType, subProposalIds);
        }

    }),
	NONE(Type.OTHER, (proposal, pointType, distributionStrategy) -> null);

	private final Type type;
	private final ReceiverLimitationTargetsPickerAlgorithm targetsPickerAlgorithm;
	
	ReceiverLimitationStrategy(Type type, ReceiverLimitationTargetsPickerAlgorithm algorithm) {
		this.type = type;
		targetsPickerAlgorithm = algorithm;
	}
	
	public List<PointsTarget> getTargets(Proposal proposal, PointType pointType, DistributionStrategy distributionStrategy)  {
		return targetsPickerAlgorithm.getPointTargets(proposal, pointType, distributionStrategy);
		
	}

	public Type getType() {
		return type;
	}

	public interface ReceiverLimitationTargetsPickerAlgorithm {
		List<PointsTarget> getPointTargets(Proposal proposal, PointType pointType, DistributionStrategy distributionStrategy);
	}

	public enum Type {
		USER, SUB_PROPOSAL, OTHER
	}
}
