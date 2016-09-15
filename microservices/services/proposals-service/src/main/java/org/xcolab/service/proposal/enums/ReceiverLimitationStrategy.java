package org.xcolab.service.proposal.enums;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.PointType;
import com.ext.portlet.model.PointsDistributionConfiguration;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.PointsDistributionConfigurationLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import org.xcolab.enums.ContestTier;
import org.xcolab.model.tables.pojos.PointType;
import org.xcolab.model.tables.pojos.Proposal;
import org.xcolab.utils.IdListUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public enum ReceiverLimitationStrategy {
	ANY_USER(Type.USER, new ReceiverLimitationTargetsPickerAlgorithm() {

		@Override
		public List<PointsTarget> getPointTargets(Proposal proposal, PointType pointType, DistributionStrategy distributionStrategy) throws SystemException {
			// check if there is any configuration, if there is create appropriate targets
			List<PointsTarget> targets = new ArrayList<>();
			if (distributionStrategy == DistributionStrategy.USER_DEFINED) {
				for (PointsDistributionConfiguration pdc: PointsDistributionConfigurationLocalServiceUtil.findByProposalIdPointTypeId(proposal.getProposalId(), pointType.getId())) {
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

		@Override
		public List<PointsTarget> getPointTargets(Proposal proposal,
				PointType pointType, DistributionStrategy distributionStrategy) throws PortalException, SystemException {
			List<PointsTarget> targets = new ArrayList<>();
			
			if (distributionStrategy == DistributionStrategy.USER_DEFINED) {
				for (PointsDistributionConfiguration pdc: PointsDistributionConfigurationLocalServiceUtil.findByProposalIdPointTypeId(proposal.getProposalId(), pointType.getId())) {
					if (pdc.getTargetUserId() > 0 && !ProposalLocalServiceUtil.isUserAMember(proposal.getProposalId(), pdc.getTargetUserId())) {
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

		@Override
		public List<PointsTarget> getPointTargets(Proposal proposal,
				PointType pointType, DistributionStrategy distributionStrategy) throws PortalException, SystemException {
			List<PointsTarget> targets = new ArrayList<>();

			if (distributionStrategy == DistributionStrategy.USER_DEFINED) {
				for (PointsDistributionConfiguration pdc: PointsDistributionConfigurationLocalServiceUtil.findByProposalIdPointTypeId(proposal.getProposalId(), pointType.getId())) {
					if (pdc.getTargetUserId() > 0 && ProposalLocalServiceUtil.isUserAMember(proposal.getProposalId(), pdc.getTargetUserId())) {
						PointsTarget target = new PointsTarget();
						target.setUserId(pdc.getTargetUserId());
						target.setPercentage(pdc.getPercentage());
						targets.add(target);
					}
				}
				if (targets.isEmpty()) {
                    return PointsDistributionUtil.distributeEquallyAmongContributors(proposal.getProposalId());
				}
			} else if (distributionStrategy == DistributionStrategy.EQUAL_DIVISION) {
                return PointsDistributionUtil.distributeEquallyAmongContributors(proposal.getProposalId());
			}
			return targets;
		}
		
	}),
    SUBPROPOSALS(Type.SUB_PROPOSAL, new ReceiverLimitationTargetsPickerAlgorithm() {

        @Override
        public List<PointsTarget> getPointTargets(Proposal proposal,
                                                  PointType pointType, DistributionStrategy distributionStrategy) throws SystemException, PortalException {
            List<Proposal> subProposals = ProposalLocalServiceUtil.getSubproposals(proposal.getProposalId(), false);
            Set<Long> subProposalIds = new HashSet<>(IdListUtil.PROPOSALS.toIdList(subProposals));
            return PointsDistributionUtil.distributeAmongProposals(distributionStrategy, proposal, pointType, subProposalIds);
        }

    }),
    REGIONAL_SUBPROPOSALS(Type.SUB_PROPOSAL, new ReceiverLimitationTargetsPickerAlgorithm() {

        @Override
        public List<PointsTarget> getPointTargets(Proposal proposal,
                                                  PointType pointType, DistributionStrategy distributionStrategy) throws SystemException, PortalException {
            List<Proposal> subProposals = ProposalLocalServiceUtil.getSubproposals(proposal.getProposalId(), false);
            Set<Long> subProposalIds = new HashSet<>();
            for (Proposal subProposal : subProposals) {
                final Contest latestProposalContest = ProposalLocalServiceUtil.getLatestProposalContest(subProposal.getProposalId());
                final ContestTier contestTier = ContestTier.getContestTierByTierType(latestProposalContest.getContestTier());
                if (contestTier == ContestTier.REGION_AGGREGATE) {
                    subProposalIds.add(subProposal.getProposalId());
                }
            }
            subProposalIds.remove(proposal.getProposalId());
            return PointsDistributionUtil.distributeAmongProposals(distributionStrategy, proposal, pointType, subProposalIds);
        }

    }),
    BASIC_SUBPROPOSALS(Type.SUB_PROPOSAL, new ReceiverLimitationTargetsPickerAlgorithm() {

        @Override
        public List<PointsTarget> getPointTargets(Proposal proposal,
                                                  PointType pointType, DistributionStrategy distributionStrategy) throws SystemException, PortalException {
            List<Proposal> subProposals = ProposalLocalServiceUtil.getSubproposals(proposal.getProposalId(), false);
            Set<Long> subProposalIds = new HashSet<>();
            for (Proposal subProposal : subProposals) {
                final Contest latestProposalContest = ProposalLocalServiceUtil.getLatestProposalContest(subProposal.getProposalId());
                final ContestTier contestTier = ContestTier.getContestTierByTierType(latestProposalContest.getContestTier());
                if (contestTier == ContestTier.BASIC || contestTier == ContestTier.NONE) {
                    subProposalIds.add(subProposal.getProposalId());
                }
            }
            subProposalIds.remove(proposal.getProposalId());
            return PointsDistributionUtil.distributeAmongProposals(distributionStrategy, proposal, pointType, subProposalIds);
        }

    }),
	NONE(Type.OTHER, new ReceiverLimitationTargetsPickerAlgorithm() {

		@Override
		public List<PointsTarget> getPointTargets(Proposal proposal,
				PointType pointType, DistributionStrategy distributionStrategy) {
			return null;
		}
		
	});

	private final Type type;
	private final ReceiverLimitationTargetsPickerAlgorithm targetsPickerAlgorithm;
	
	ReceiverLimitationStrategy(Type type, ReceiverLimitationTargetsPickerAlgorithm algorithm) {
		this.type = type;
		targetsPickerAlgorithm = algorithm;
	}
	
	public List<PointsTarget> getTargets(Proposal proposal, PointType pointType, DistributionStrategy distributionStrategy) throws PortalException, SystemException {
		return targetsPickerAlgorithm.getPointTargets(proposal, pointType, distributionStrategy);
		
	}

	public Type getType() {
		return type;
	}

	public interface ReceiverLimitationTargetsPickerAlgorithm {
		List<PointsTarget> getPointTargets(Proposal proposal, PointType pointType, DistributionStrategy distributionStrategy) throws PortalException, SystemException;
	}

	public enum Type {
		USER, SUB_PROPOSAL, OTHER
	}
}
