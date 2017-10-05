package org.xcolab.view.pages.proposals.view.action;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalMemberRatingClient;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.pages.loginregister.SharedColabUtil;
import org.xcolab.view.pages.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;
import org.xcolab.view.util.entity.analytics.AnalyticsUtil;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/contests/{contestYear}/{contestUrlName}/c/{proposalUrlString}/{proposalId}")
public class SupportProposalActionController {

    private final static String SUPPORT_ANALYTICS_KEY = "SUPPORTED_ENTRIES";
    private final static String SUPPORT_ANALYTICS_CATEGORY = "User";
    private final static String SUPPORT_ANALYTICS_ACTION = "Support contest entry";
    private final static String SUPPORT_ANALYTICS_LABEL = "";

    @PostMapping("supportProposalAction")
    public String handleAction(HttpServletRequest request, HttpServletResponse response,
            Model model, Member currentMember, ProposalContext proposalContext,
            @RequestParam(required = false) String forwardToTab)
            throws ProposalsAuthorizationException, IOException {

        if (!proposalContext.getPermissions().getCanSupportProposal()) {
            return new AccessDeniedPage(currentMember).toViewName(response);
        }
        long memberId = currentMember.getUserId();
        long proposalId = proposalContext.getProposal().getProposalId();
        ProposalMemberRatingClient proposalMemberRatingClient =
                proposalContext.getClients().getProposalMemberRatingClient();
        if (proposalMemberRatingClient.isMemberProposalSupporter(proposalId, memberId)) {
            proposalMemberRatingClient.removeProposalSupporter(proposalId, memberId);
        } else {
            proposalMemberRatingClient.addProposalSupporter(proposalId, memberId);
            int supportedCount = proposalMemberRatingClient.getProposalSupportersCount(memberId);
            if (supportedCount > 0) {
                int analyticsValue = AnalyticsUtil.getAnalyticsValueForCount(supportedCount);
                AnalyticsUtil
                        .publishEvent(request, memberId, SUPPORT_ANALYTICS_KEY + analyticsValue,
                                SUPPORT_ANALYTICS_CATEGORY, SUPPORT_ANALYTICS_ACTION,
                                SUPPORT_ANALYTICS_LABEL, analyticsValue);
            }
            try {
                Contest contest = proposalContext.getClients().getProposalClient()
                        .getLatestContestInProposal(proposalId);
                SharedColabUtil
                        .checkTriggerForAutoUserCreationInContest(contest.getContestPK(), memberId);
            } catch (ContestNotFoundException ignore) {

            }
        }
        ServiceRequestUtils.clearCache(CacheName.MISC_REQUEST);
        String proposalLinkUrl =
                proposalContext.getProposal().getProposalLinkUrl(proposalContext.getContest());
        if (!StringUtils.isBlank(forwardToTab)) {
            proposalLinkUrl += "/tab/" + forwardToTab;
        }
        return "redirect:" + proposalLinkUrl;
    }

}
