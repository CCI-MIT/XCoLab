package org.xcolab.portlets.proposals.view.action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.analytics.AnalyticsUtil;
import org.xcolab.portlets.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.portlets.proposals.utils.ProposalsContext;

import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

@Controller
@RequestMapping("view")
public class VoteOnProposalActionController {

    @Autowired
    private ProposalsContext proposalsContext;
    
    private final static String VOTE_ANALYTICS_KEY = "VOTE_CONTEST_ENTRIES";
    private final static String VOTE_ANALYTICS_CATEGORY = "User";
    private final static String VOTE_ANALYTICS_ACTION = "Vote contest entry";
    private final static String VOTE_ANALYTICS_LABEL = "";
    
    @RequestMapping(params = {"action=voteOnProposalAction"})
    public void handleAction(ActionRequest request, Model model, ActionResponse response) 
                    throws PortalException, SystemException, ProposalsAuthorizationException {
        if (proposalsContext.getPermissions(request).getCanVote()) {
            long proposalId = proposalsContext.getProposal(request).getProposalId();
            long contestPhaseId = proposalsContext.getContestPhase(request).getContestPhasePK();
            long userId = proposalsContext.getUser(request).getUserId();
            if (ProposalLocalServiceUtil.hasUserVoted(proposalId, contestPhaseId, userId)) {
                ProposalLocalServiceUtil.removeVote(contestPhaseId, userId);
            }
            else {
                ProposalLocalServiceUtil.addVote(proposalId, contestPhaseId, userId);
                int analyticsValue = 0;
                int supportedCount = ProposalLocalServiceUtil.getUserVotedProposalsCount(userId);
                if (supportedCount > 0) {
                	if (supportedCount == 1) {
                		analyticsValue = 1;
                	}
                	else if ( supportedCount < 5) {
                		analyticsValue = 2;
                	}
                	else {
                		analyticsValue = 3;
                	}
            	AnalyticsUtil.publishEvent(request, userId, VOTE_ANALYTICS_KEY + analyticsValue, 
            			VOTE_ANALYTICS_CATEGORY, 
            			VOTE_ANALYTICS_ACTION , 
            			VOTE_ANALYTICS_LABEL, 
            			analyticsValue);
                }
            }
        }
        else {
            if (proposalsContext.getUser(request) == null || proposalsContext.getUser(request).getUserId() == 10115) {
                /* User is not logged in - don't count vote and let user log in*/
                request.setAttribute("promptLoginWindow","true");
            } else {
                throw new ProposalsAuthorizationException("User isn't allowed to vote on proposal ");
            }
        }
    }

}
