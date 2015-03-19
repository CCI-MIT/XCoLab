package org.xcolab.portlets.proposals.utils;

import com.ext.portlet.community.CommunityConstants;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;

public class ProposalsURLGenerator {
    private final static Log _log = LogFactoryUtil.getLog(ProposalsURLGenerator.class);
    
    public static String getProposalURL(Proposal proposal) throws PortalException, SystemException {
        try {
            return String.format("/web/guest/plans/-/plans/contestId/" +
                    Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal(proposal.getProposalId()).getContestPK() +
                    "/planId/" + proposal.getProposalId());
        } catch (Exception e){
            _log.warn("Could not generate proposal URL for proposalID: " + proposal.getProposalId(), e);
            return String.format("/web/guest/plans");
        }
    }
    
    public static String getUserURL(long userId) throws PortalException, SystemException {
        if (userId <= 0) return StringPool.BLANK;
        return CommunityConstants.USER_PROFILE_PATH + userId;   
    }

}
