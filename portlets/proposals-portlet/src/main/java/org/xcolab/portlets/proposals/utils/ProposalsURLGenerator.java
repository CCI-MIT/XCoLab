package org.xcolab.portlets.proposals.utils;

import com.ext.portlet.community.CommunityConstants;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;

public class ProposalsURLGenerator {

    
    public static String getProposalURL(Proposal proposal) throws PortalException, SystemException {
        return String.format("/web/guest/plans/-/plans/contestId/" + 
                Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal(proposal.getProposalId()).getContestPK() + 
                "/planId/" + proposal.getProposalId());
    }
    
    public static String getUserURL(long userId) throws PortalException, SystemException {
        if (userId <= 0) return StringPool.BLANK;
        return CommunityConstants.USER_PROFILE_PATH + userId;   
    }

}
