package org.xcolab.service.proposal.enums;

import com.ext.portlet.NoSuchPointsDistributionConfigurationException;
import com.ext.portlet.model.PointType;
import com.ext.portlet.model.PointsDistributionConfiguration;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalAttribute;
import com.ext.portlet.model.ProposalReference;
import com.ext.portlet.service.PointsDistributionConfigurationLocalServiceUtil;
import com.ext.portlet.service.ProposalAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.ext.portlet.service.ProposalReferenceLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class PointsDistributionUtil {
    public static List<PointsTarget> distributeEquallyAmongContributors(long proposalId)
            throws SystemException, PortalException {
        List<PointsTarget> targets = new ArrayList<>();
        List<User> members = ProposalLocalServiceUtil.getMembers(proposalId);
        for (User u : members) {
            targets.add(PointsTarget.forUser(u.getUserId(), 1.0d / members.size()));
        }
        return targets;
    }

    public static List<PointsTarget> distributeEquallyAmongProposals(Collection<Long> proposalIds)
            throws SystemException, PortalException {
        List<PointsTarget> targets = new ArrayList<>();
        for (Long proposalId : proposalIds) {
            targets.add(PointsTarget.forProposal(proposalId, 1.0d / proposalIds.size()));
        }
        return targets;
    }

    public static List<PointsTarget> distributeSectionDefinedAmongProposals(Proposal proposal, PointType pointType, Set<Long> subProposalIds) throws PortalException, SystemException {
        List<PointsTarget> targets = new ArrayList<>();
        for (long subProposalId : subProposalIds) {
            ProposalReference reference = ProposalReferenceLocalServiceUtil.getByProposalIdSubProposalId(proposal.getProposalId(), subProposalId);
            final ProposalAttribute referenceSectionProposalAttribute = ProposalAttributeLocalServiceUtil.getProposalAttribute(reference.getSectionAttributeId());
            final long planSectionDefinitionId = referenceSectionProposalAttribute.getAdditionalId();
            try {
                PointsDistributionConfiguration pdc = PointsDistributionConfigurationLocalServiceUtil.getByPlanSectionDefinitionId(planSectionDefinitionId);
                targets.add(PointsTarget.forProposal(subProposalId, pdc.getPercentage()));
            } catch (NoSuchPointsDistributionConfigurationException ignored) { }
        }
        return targets;
    }

    public static List<PointsTarget> distributeUserDefinedAmongProposals(Proposal proposal, PointType pointType, Set<Long> subProposalIds) throws SystemException {
        List<PointsTarget> targets = new ArrayList<>();
        for (PointsDistributionConfiguration pdc : PointsDistributionConfigurationLocalServiceUtil.findByProposalIdPointTypeId(proposal.getProposalId(), pointType.getId())) {
            if (pdc.getTargetSubProposalId() > 0 && subProposalIds.contains(pdc.getTargetSubProposalId()) && pdc.getTargetSubProposalId() != proposal.getProposalId()) {
                PointsTarget target = new PointsTarget();
                target.setProposalId(pdc.getTargetSubProposalId());
                target.setPercentage(pdc.getPercentage());
                targets.add(target);
            }
        }
        return targets;
    }

    public static List<PointsTarget> distributeAmongProposals(DistributionStrategy distributionStrategy, Proposal parentProposals, PointType pointType, Set<Long> proposalIds) throws SystemException, PortalException {
        switch (distributionStrategy) {
            case USER_DEFINED:
                return distributeUserDefinedAmongProposals(parentProposals, pointType, proposalIds);
            case EQUAL_DIVISION:
                return distributeEquallyAmongProposals(proposalIds);
            case SECTION_DEFINED:
                return distributeSectionDefinedAmongProposals(parentProposals, pointType, proposalIds);
        }
        return Collections.emptyList();
    }
}
