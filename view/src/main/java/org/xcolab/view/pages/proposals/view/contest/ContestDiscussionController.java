package org.xcolab.view.pages.proposals.view.contest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.pages.proposals.permissions.ContestPermissions;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;
import org.xcolab.view.pages.proposals.view.proposal.BaseProposalsController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller

public class ContestDiscussionController extends BaseProposalsController {

    @GetMapping("/contests/{contestYear}/{contestUrlName}/discussion")
    public String showContestProposals(HttpServletRequest request, HttpServletResponse response,
            Model model, ProposalContext proposalContext, UserWrapper loggedInMember) {

        final ContestWrapper contest = proposalContext.getContest();
        final ContestType contestType = contest.getContestType();
        if (contestType.isRestrictedAccess() && !new ContestPermissions(loggedInMember)
                .getCanAccessContest(contest)) {
            return new AccessDeniedPage(loggedInMember).toViewName(response);
        }

        model.addAttribute("discussionId",  proposalContext.getContest().getOrCreateDiscussionGroupId());

        model.addAttribute("recaptchaDataSiteKey", PlatformAttributeKey.GOOGLE_RECAPTCHA_SITE_KEY.get());
        model.addAttribute("isCommentCaptchaOn",PlatformAttributeKey.GOOGLE_RECAPTCHA_IS_ACTIVE_FOR_COMMENTS.get());

        setBasePageAttributes(proposalContext, model);
        return "proposals/contestDiscussion";
    }
}
