package org.xcolab.view.pages.proposals.view.proposal.tabs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.view.pages.proposals.discussion.ProposalDiscussionPermissions;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContext;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContextUtil;
import org.xcolab.view.pages.proposals.wrappers.ProposalTab;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.DiscussionPermissions;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/contests/{contestYear}/{contestUrlName}")
public class ProposalFellowReviewTabController extends BaseProposalTabController {

    @Autowired
    private ProposalsContext proposalsContext;

    //TODO: check if we want to reactivate this
//    @GetMapping(value = "c/{proposalUrlString}/{proposalId}", params = "tab=FELLOW_REVIEW")
    public String showFellowReview(HttpServletRequest request, Model model) {

        final Proposal proposal = proposalsContext.getProposalWrapped(request);

        long fellowDiscussionId = proposal.getFellowDiscussionId();
        if (fellowDiscussionId == 0) {
            fellowDiscussionId = createFellowThread(request);
        }
        request.setAttribute(DiscussionPermissions.REQUEST_ATTRIBUTE_NAME,
                new ProposalDiscussionPermissions(request));

        model.addAttribute("discussionId", fellowDiscussionId);
        model.addAttribute("authorId", proposal.getAuthorId());
        model.addAttribute("proposalId", proposal.getProposalId());

        setCommonModelAndPageAttributes(request, model, ProposalTab.FELLOW_REVIEW);

        return "proposalComments";
    }

    private long createFellowThread(HttpServletRequest request) {
        Proposal proposal = proposalsContext.getProposal(request);
        final long discussionThreadId = createDiscussionThread(request, " fellow review", true);
        proposal.setFellowDiscussionId(discussionThreadId);
        ProposalsContextUtil.getClients(request).getProposalClient().updateProposal(proposal);
        return discussionThreadId;
    }
}
