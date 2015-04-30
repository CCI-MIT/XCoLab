package org.xcolab.portlets.proposals.wrappers;

import javax.portlet.PortletRequest;

import org.xcolab.portlets.proposals.permissions.ProposalsPermissions;
import org.xcolab.portlets.proposals.utils.ProposalsContext;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public enum ProposalTab {
    DESCRIPTION("Description", ProposalTabCanAccessAlgorithm.alwaysTrue, ProposalTabCanAccessAlgorithm.canEditAccess, ProposalTabActivityCountAlgorithm.alwaysZero),
    ACTIONSIMPACTS("Model results", 
            new ProposalTabCanAccessAlgorithm() {

                @Override
                public boolean canAccess(ProposalsPermissions permissions, ProposalsContext context,
                        PortletRequest request) {
                    // user is allowed to access actions and impacts only when there is a model defined 
                    // for current contest (model id in proposalwrapper will be > 0)
                    try {
                        return context.getProposalWrapped(request).getModelId() > 0;
                    } catch (PortalException e) {
                        _log.error("Can't access proposals model id", e);
                    } catch (SystemException e) {
                        _log.error("Can't access proposals model id", e);
                    }
                    return false;
                }
                private Log _log = LogFactoryUtil.getLog(ProposalTabActivityCountAlgorithm.class);
    }
            , ProposalTabCanAccessAlgorithm.canEditAccess, ProposalTabActivityCountAlgorithm.alwaysZero), // TODO might need to change this
    IMPACT("Impact", ProposalTabCanAccessAlgorithm.impactViewAccess, ProposalTabCanAccessAlgorithm.impactEditAccess, ProposalTabActivityCountAlgorithm.alwaysZero),
    TEAM("Contributors", ProposalTabCanAccessAlgorithm.alwaysTrue, ProposalTabCanAccessAlgorithm.alwaysFalse, ProposalTabActivityCountAlgorithm.membersCount),
    COMMENTS("Comments", ProposalTabCanAccessAlgorithm.alwaysTrue, ProposalTabCanAccessAlgorithm.alwaysFalse, ProposalTabActivityCountAlgorithm.commentsCount),
    DISCUSSION("Evaluation", ProposalTabCanAccessAlgorithm.adminOnlyAccess, ProposalTabCanAccessAlgorithm.alwaysFalse, ProposalTabActivityCountAlgorithm.discussionCommentsCount),
    ADVANCING("Judging Decision",ProposalTabCanAccessAlgorithm.advancingAccess, ProposalTabCanAccessAlgorithm.alwaysFalse, ProposalTabActivityCountAlgorithm.alwaysZero),
    SCREENING("Screening", ProposalTabCanAccessAlgorithm.screeningAccess, ProposalTabCanAccessAlgorithm.alwaysFalse, ProposalTabActivityCountAlgorithm.alwaysZero),
    ADMIN("Admin", ProposalTabCanAccessAlgorithm.adminOnlyAccess, ProposalTabCanAccessAlgorithm.alwaysFalse, ProposalTabActivityCountAlgorithm.alwaysZero),
    POINTS("Points", ProposalTabCanAccessAlgorithm.pointsViewAccess, ProposalTabCanAccessAlgorithm.pointsEditAccess, ProposalTabActivityCountAlgorithm.alwaysZero);



    private final String displayName;
    private final ProposalTabCanAccessAlgorithm canAccessTabAlgorithm;
    private final ProposalTabCanAccessAlgorithm canEditAlgorithm;
    private final ProposalTabActivityCountAlgorithm activitiesCountAlgorithm;
    
    private ProposalTab(String displayName, ProposalTabCanAccessAlgorithm canAccessTabAlgorithm, 
            ProposalTabCanAccessAlgorithm canEditAlgorithm, 
            ProposalTabActivityCountAlgorithm activitiesCountAlgorithm) {
        this.displayName = displayName;
        this.canAccessTabAlgorithm = canAccessTabAlgorithm;
        this.canEditAlgorithm = canEditAlgorithm;
        this.activitiesCountAlgorithm = activitiesCountAlgorithm;
    }

    public String getDisplayName() {
        return displayName;
    }
    
    public boolean isDefault() {
        return this.ordinal() == 0;
    }
    
    public boolean getCanAccess(ProposalsPermissions permissions, ProposalsContext context, PortletRequest request) {
        return canAccessTabAlgorithm.canAccess(permissions, context, request);
    }

    public boolean getCanEdit(ProposalsPermissions permissions, ProposalsContext context, PortletRequest request) {
        return canEditAlgorithm.canAccess(permissions, context, request);
    }
    
    public int getActivityCount(ProposalsContext context, PortletRequest request) {
        return activitiesCountAlgorithm.getActivityCount(context, request);
    }
    
    
    
}