package org.xcolab.portlets.proposals.view.action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import com.ext.portlet.service.ProposalVoteLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
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
import org.xcolab.portlets.proposals.utils.ProposalsURLGenerator;

import java.io.IOException;

@Controller
@RequestMapping("view")
public class VoteOnProposalActionController {

    @Autowired
    private ProposalsContext proposalsContext;
    
    private final static String VOTE_ANALYTICS_KEY = "VOTE_CONTEST_ENTRIES";
    private final static String VOTE_ANALYTICS_CATEGORY = "User";
    private final static String VOTE_ANALYTICS_ACTION = "Vote contest entry";
    private final static String VOTE_ANALYTICS_LABEL = "";

    private static Log _log = LogFactoryUtil.getLog(VoteOnProposalActionController.class);
    
    @RequestMapping(params = {"action=voteOnProposalAction"})
    public void handleAction(ActionRequest request, Model model, ActionResponse response)
            throws PortalException, SystemException, ProposalsAuthorizationException, IOException {
        boolean hasVoted = false;
        if (proposalsContext.getPermissions(request).getCanVote()) {
            long proposalId = proposalsContext.getProposal(request).getProposalId();
            long contestPhaseId = proposalsContext.getContestPhase(request).getContestPhasePK();
            long userId = proposalsContext.getUser(request).getUserId();
            if (ProposalLocalServiceUtil.hasUserVoted(proposalId, contestPhaseId, userId)) {
                // User has voted for this proposal and would like to retract the vote
                ProposalLocalServiceUtil.removeVote(contestPhaseId, userId);
            }
            else {
                if (ProposalVoteLocalServiceUtil.hasUserVoted(contestPhaseId, userId)) {
                    // User has voted for a different proposal. Vote will be retracted and converted to a vote of this proposal.
                    ProposalLocalServiceUtil.removeVote(contestPhaseId, userId);
                }
                try {
                    ProposalLocalServiceUtil.addVote(proposalId, contestPhaseId, userId);
                    hasVoted = true;
                } catch(SystemException exception) {
                    _log.error("kmang: Original Vote exception occured: ", exception.getCause());
                    _log.error("kmang: Wrapped Vote exception occured: ", exception);
                }


                //publish event per contestPhaseId to allow voting on exactly one proposal per contest(phase)
            	AnalyticsUtil.publishEvent(request, userId, VOTE_ANALYTICS_KEY+contestPhaseId,
            			VOTE_ANALYTICS_CATEGORY, 
            			VOTE_ANALYTICS_ACTION,
            			VOTE_ANALYTICS_LABEL, 
            			1);
            }
        }
        else {
            if (proposalsContext.getUser(request) == null || proposalsContext.getUser(request).getUserId() == 10115) {
                /* User is not logged in - don't count vote and let user log in*/
                request.setAttribute("promptLoginWindow","true");
                return;
            } else {
                throw new ProposalsAuthorizationException("User isn't allowed to vote on proposal ");
            }
        }
        // Redirect to prevent page-refreshing from influencing the vote
        response.sendRedirect(ProposalsURLGenerator.getProposalURL(
                proposalsContext.getProposal(request))+(hasVoted?"?voted=true":"")
        );
    }

}
