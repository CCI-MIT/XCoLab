package org.xcolab.view.pages.proposals.view.action;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.client.contest.proposals.IProposalMemberRatingClient;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.commons.servlet.flash.AlertMessage;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.pages.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;
import org.xcolab.view.util.entity.analytics.AnalyticsUtil;
import org.xcolab.view.util.googleanalytics.GoogleAnalyticsEventType;
import org.xcolab.view.util.googleanalytics.GoogleAnalyticsUtils;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/contests/{contestYear}/{contestUrlName}/c/{proposalUrlString}/{proposalId}")
public class SupportProposalActionController {

    private static final String SUPPORT_ANALYTICS_KEY = "SUPPORTED_ENTRIES";
    private static final String SUPPORT_ANALYTICS_CATEGORY = "User";
    private static final String SUPPORT_ANALYTICS_ACTION = "Support contest entry";
    private static final String SUPPORT_ANALYTICS_LABEL = "";

    @PostMapping("supportProposalAction")
    public String handleAction(HttpServletRequest request, HttpServletResponse response,
            Model model, UserWrapper currentMember, ProposalContext proposalContext,
            @RequestParam(required = false) String forwardToTab)
            throws ProposalsAuthorizationException, IOException {

        if (!proposalContext.getPermissions().getCanSupportProposal()) {
            return new AccessDeniedPage(currentMember).toViewName(response);
        }
        long userId = currentMember.getId();
        long proposalId = proposalContext.getProposal().getId();
        IProposalMemberRatingClient proposalMemberRatingClient =
                proposalContext.getClients().getProposalMemberRatingClient();
        if (proposalMemberRatingClient.isMemberProposalSupporter(proposalId, userId)) {
            proposalMemberRatingClient.deleteProposalSupporter(proposalId, userId);
        } else {
            proposalMemberRatingClient.addProposalSupporter(proposalId, userId);
            int supportedCount = proposalMemberRatingClient.getProposalSupportersCount(userId);
            if (supportedCount > 0) {
                int analyticsValue = AnalyticsUtil.getAnalyticsValueForCount(supportedCount);
                AnalyticsUtil
                        .publishEvent(request, userId, SUPPORT_ANALYTICS_KEY + analyticsValue,
                                SUPPORT_ANALYTICS_CATEGORY, SUPPORT_ANALYTICS_ACTION,
                                SUPPORT_ANALYTICS_LABEL, analyticsValue);
				GoogleAnalyticsUtils.pushEventAsync(GoogleAnalyticsEventType.CONTEST_ENTRY_SUPPORT);
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

    @GetMapping("supportProposalAction")
    public String handleInvalidGetRequest(HttpServletRequest request,
            HttpServletResponse response, Model model, ProposalContext proposalContext,
            UserWrapper member) {

        AlertMessage.warning(
                "Your support hasn't been recorded, please make sure to click the button only once.")
                .flash(request);
        final ContestWrapper contest = proposalContext.getContest();
        final ProposalWrapper proposal = proposalContext.getProposal();
        return "redirect:" + proposal.getProposalLinkUrl(contest);
    }
}
