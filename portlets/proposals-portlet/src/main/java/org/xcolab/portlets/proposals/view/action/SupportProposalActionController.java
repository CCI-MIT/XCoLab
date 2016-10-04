package org.xcolab.portlets.proposals.view.action;



import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.analytics.AnalyticsUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.proposals.ProposalSupporterClient;
import org.xcolab.client.proposals.ProposalsClient;
import org.xcolab.liferay.SharedColabUtil;
import org.xcolab.portlets.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.portlets.proposals.utils.ProposalsContext;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import java.io.IOException;

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
            long userId = proposalsContext.getUser(request).getUserId();
            long proposalId = proposalsContext.getProposal(request).getProposalId();

            if (ProposalSupporterClient.isMemberProposalSupporter(proposalId, userId)) {
                ProposalSupporterClient.removeProposalSupporter(proposalId, userId);
            }
            else {
                ProposalSupporterClient.addProposalSupporter(proposalId, userId);
                int supportedCount = ProposalSupporterClient.getProposalSupportersCount(userId);
                if (supportedCount > 0) {
                    int analyticsValue = AnalyticsUtil.getAnalyticsValueForCount(supportedCount);
                    AnalyticsUtil.publishEvent(request, userId, SUPPORT_ANALYTICS_KEY + analyticsValue,
            			SUPPORT_ANALYTICS_CATEGORY,
            			SUPPORT_ANALYTICS_ACTION,
            			SUPPORT_ANALYTICS_LABEL,
            			analyticsValue);
                }
                try {
                    Contest contest = ProposalsClient.getLatestContestInProposal(proposalId);
                    SharedColabUtil.checkTriggerForAutoUserCreationInContest(contest.getContestPK(), userId);
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
