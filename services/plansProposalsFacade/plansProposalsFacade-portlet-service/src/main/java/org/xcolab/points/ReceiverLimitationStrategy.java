package org.xcolab.points;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ext.portlet.model.PointType;
import com.ext.portlet.model.PointsDistributionConfiguration;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.PointsDistributionConfigurationLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

public enum ReceiverLimitationStrategy {
	ANY_USER(new ReceiverLimitationTargetsPickerAlgorithm() {

		@Override
		public List<PointsTarget> getPointTargets(Proposal proposal, PointType pointType, DistributionStrategy distributionStrategy) throws SystemException {
			// check if there is any configuration, if there is create appropriate targets
			List<PointsTarget> targets = new ArrayList<PointsTarget>();
			if (distributionStrategy == DistributionStrategy.USER_DEFINED) {
				for (PointsDistributionConfiguration pdc: PointsDistributionConfigurationLocalServiceUtil.findByProposalPointType(proposal, pointType)) {
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
	
	ANY_NON_TEAM_MEMBER(new ReceiverLimitationTargetsPickerAlgorithm() {

		@Override
		public List<PointsTarget> getPointTargets(Proposal proposal,
				PointType pointType, DistributionStrategy distributionStrategy) throws PortalException, SystemException {
			List<PointsTarget> targets = new ArrayList<PointsTarget>();
			
			if (distributionStrategy == DistributionStrategy.USER_DEFINED) {
				for (PointsDistributionConfiguration pdc: PointsDistributionConfigurationLocalServiceUtil.findByProposalPointType(proposal, pointType)) {
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
	ANY_TEAM_MEMBER(new ReceiverLimitationTargetsPickerAlgorithm() {

		@Override
		public List<PointsTarget> getPointTargets(Proposal proposal,
				PointType pointType, DistributionStrategy distributionStrategy) throws PortalException, SystemException {
			List<PointsTarget> targets = new ArrayList<PointsTarget>();
			
			if (distributionStrategy == DistributionStrategy.USER_DEFINED) {
				for (PointsDistributionConfiguration pdc: PointsDistributionConfigurationLocalServiceUtil.findByProposalPointType(proposal, pointType)) {
					if (pdc.getTargetUserId() > 0 && ProposalLocalServiceUtil.isUserAMember(proposal.getProposalId(), pdc.getTargetUserId())) {
						PointsTarget target = new PointsTarget();
						target.setUserId(pdc.getTargetUserId());
						target.setPercentage(pdc.getPercentage());
						targets.add(target);
					}
				}
				if (targets.isEmpty()) {
					// there is no configuration for specific users, distribute equally
					List<User> members = ProposalLocalServiceUtil.getMembers(proposal.getProposalId());
					for (User u: members) {
						PointsTarget target = new PointsTarget();
						target.setUserId(u.getUserId());
						target.setPercentage(1.0d / members.size());
						targets.add(target);
					}
				}
			}
			else if (distributionStrategy == DistributionStrategy.EQUAL_DIVISION) {
				List<User> members = ProposalLocalServiceUtil.getMembers(proposal.getProposalId());
				for (User u: members) {
					PointsTarget target = new PointsTarget();
					target.setUserId(u.getUserId());
					target.setPercentage(1.0d / members.size());
					targets.add(target);
				}
			}
			return targets;
		}
		
	}),  
	SUBPROPOSALS(new ReceiverLimitationTargetsPickerAlgorithm() {

		@Override
		public List<PointsTarget> getPointTargets(Proposal proposal,
				PointType pointType, DistributionStrategy distributionStrategy) throws SystemException, PortalException {
			List<PointsTarget> targets = new ArrayList<PointsTarget>();
			
			Collection<Proposal> subProposals = ProposalLocalServiceUtil.getSubproposals(proposal.getProposalId(), false);
			Set<Long> proposalIds = new HashSet<Long>();
			for (Proposal p: subProposals) {
                if (p.getProposalId() != proposal.getProposalId()) {
                    proposalIds.add(p.getProposalId());
                }
			}
			if (distributionStrategy == DistributionStrategy.USER_DEFINED) {
				for (PointsDistributionConfiguration pdc: PointsDistributionConfigurationLocalServiceUtil.findByProposalPointType(proposal, pointType)) {
					if (pdc.getTargetSubProposalId() > 0 && proposalIds.contains(pdc.getTargetSubProposalId()) && pdc.getTargetSubProposalId() != proposal.getProposalId()) {
						PointsTarget target = new PointsTarget();
						target.setProposalId(pdc.getTargetSubProposalId());
						target.setPercentage(pdc.getPercentage());
						targets.add(target);
					}
				}
			}
			else if (distributionStrategy == DistributionStrategy.EQUAL_DIVISION) {
				for (Long proposalId: proposalIds) {
                    PointsTarget target = new PointsTarget();
                    target.setProposalId(proposalId);
                    target.setPercentage(1.0d / proposalIds.size());
                    targets.add(target);
				}
			}
			return targets;
		}
		
	}), 
	NONE(new ReceiverLimitationTargetsPickerAlgorithm() {

		@Override
		public List<PointsTarget> getPointTargets(Proposal proposal,
				PointType pointType, DistributionStrategy distributionStrategy) {
			return null;
		}
		
	}), ;
	
	private final ReceiverLimitationTargetsPickerAlgorithm targetsPickerAlgorithm;
	
	private ReceiverLimitationStrategy(ReceiverLimitationTargetsPickerAlgorithm algorithm) {
		targetsPickerAlgorithm = algorithm;
	}
	
	public List<PointsTarget> getTargets(Proposal proposal, PointType pointType, DistributionStrategy distributionStrategy) throws PortalException, SystemException {
		return targetsPickerAlgorithm.getPointTargets(proposal, pointType, distributionStrategy);
		
	}
	
	
	public static interface ReceiverLimitationTargetsPickerAlgorithm {
		List<PointsTarget> getPointTargets(Proposal proposal, PointType pointType, DistributionStrategy distributionStrategy) throws PortalException, SystemException;
	}
}
