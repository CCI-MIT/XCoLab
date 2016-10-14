package org.xcolab.portlets.proposals.view.action;



import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import org.xcolab.analytics.AnalyticsUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.ProposalMemberRatingClientUtil;
import org.xcolab.liferay.SharedColabUtil;
import org.xcolab.portlets.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.portlets.proposals.utils.context.ProposalsContext;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

@Controller
@RequestMapping("view")
public class SupportProposalActionController {

    @Autowired
    private ProposalsContext proposalsContext;
    
    private final static String SUPPORT_ANALYTICS_KEY = "SUPPORTED_ENTRIES";
    private final static String SUPPORT_ANALYTICS_CATEGORY = "User";
    private final static String SUPPORT_ANALYTICS_ACTION = "Support contest entry";
    private final static String SUPPORT_ANALYTICS_LABEL = "";

    
    @RequestMapping(params = {"action=supportProposalAction"})
    public synchronized void handleAction(ActionRequest request, Model model, ActionResponse response,
            @RequestParam(required = false) String forwardToTab)
            throws PortalException, SystemException, ProposalsAuthorizationException, IOException {
        
        if (proposalsContext.getPermissions(request).getCanSupportProposal()) {
            long memberId = proposalsContext.getMember(request).getUserId();
            long proposalId = proposalsContext.getProposal(request).getProposalId();

            if (ProposalMemberRatingClientUtil.isMemberProposalSupporter(proposalId, memberId)) {
                ProposalMemberRatingClientUtil.removeProposalSupporter(proposalId, memberId);
            }
            else {
                ProposalMemberRatingClientUtil.addProposalSupporter(proposalId, memberId);
                int supportedCount = ProposalMemberRatingClientUtil.getProposalSupportersCount(memberId);
                if (supportedCount > 0) {
                    int analyticsValue = AnalyticsUtil.getAnalyticsValueForCount(supportedCount);
                    AnalyticsUtil.publishEvent(request, memberId, SUPPORT_ANALYTICS_KEY + analyticsValue,
            			SUPPORT_ANALYTICS_CATEGORY,
            			SUPPORT_ANALYTICS_ACTION,
            			SUPPORT_ANALYTICS_LABEL,
            			analyticsValue);
                }
                try {
                    Contest contest = ProposalClientUtil.getLatestContestInProposal(proposalId);
                    SharedColabUtil.checkTriggerForAutoUserCreationInContest(contest.getContestPK(), memberId);
                }catch (ContestNotFoundException ignore){

                }
            }
            String proposalLinkUrl = proposalsContext.getProposal(request).getProposalLinkUrl(proposalsContext.getContest(request));
            if (!StringUtils.isBlank(forwardToTab)) {
                proposalLinkUrl += "/tab/" + forwardToTab;
            }
            response.sendRedirect(proposalLinkUrl);
        }
        else {
            throw new ProposalsAuthorizationException("User isn't allowed to toggle support for proposal");
        }
    }

}
