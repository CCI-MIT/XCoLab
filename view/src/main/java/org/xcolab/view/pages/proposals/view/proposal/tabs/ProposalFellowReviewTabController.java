package org.xcolab.view.pages.proposals.view.proposal.tabs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contests/{contestYear}/{contestUrlName}")
public class ProposalFellowReviewTabController extends BaseProposalTabController {

    //TODO COLAB-2623: check if we want to reactivate this
//    @GetMapping(value = "c/{proposalUrlString}/{proposalId}", params = "tab=FELLOW_REVIEW")
//    public String showFellowReview(HttpServletRequest request, Model model) {
//
//        final Proposal proposal = proposalContext.getProposal());
//
//        long fellowDiscussionId = proposal.getFellowDiscussionId();
//        if (fellowDiscussionId == 0) {
//            fellowDiscussionId = createFellowThread(request);
//        }
//        request.setAttribute(DiscussionPermissions.REQUEST_ATTRIBUTE_NAME,
//                new ProposalDiscussionPermissions(request));
//
//        model.addAttribute("discussionId", fellowDiscussionId);
//        model.addAttribute("authorId", proposal.getAuthorId());
//        model.addAttribute("proposalId", proposal.getProposalId());
//
//        setCommonModelAndPageAttributes(request, model, ProposalTab.FELLOW_REVIEW);
//
//        return "proposalComments";
//    }
//
//    private long createFellowThread(HttpServletRequest request) {
//        Proposal proposal = proposalContext.getProposal();
//        final long discussionThreadId = createDiscussionThread(request, " fellow review", true);
//        proposal.setFellowDiscussionId(discussionThreadId);
//        proposalContext.getClients().getProposalClient().updateProposal(proposal);
//        return discussionThreadId;
//    }
}
