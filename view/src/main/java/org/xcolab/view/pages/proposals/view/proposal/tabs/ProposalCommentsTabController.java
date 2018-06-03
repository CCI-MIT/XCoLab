package org.xcolab.view.pages.proposals.view.proposal.tabs;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.view.pages.proposals.discussion.ProposalDiscussionPermissions;
import org.xcolab.view.pages.proposals.tabs.ProposalTab;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.DiscussionPermissions;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/contests/{contestYear}/{contestUrlName}")
public class ProposalCommentsTabController extends BaseProposalTabController {

    @GetMapping(value = "c/{proposalUrlString}/{proposalId}", params = "tab=COMMENTS")
    public String showComments(HttpServletRequest request, Model model,
            ProposalContext proposalContext) {

        final Proposal proposal = proposalContext.getProposal();
        long discussionId = proposal.getDiscussionId();
        if (discussionId == 0) {
            discussionId = createCommentThread(proposalContext);
        }

        ProposalDiscussionPermissions pdp = new ProposalDiscussionPermissions(request,
                proposalContext);
        pdp.setProposalId(proposalContext.getProposal().getProposalId(),
                proposalContext.getContestPhase().getContestPhasePK());

        request.setAttribute(DiscussionPermissions.REQUEST_ATTRIBUTE_NAME, pdp);

        model.addAttribute("discussionId", discussionId);
        model.addAttribute("authorId", proposal.getAuthorId());
        model.addAttribute("proposalId", proposal.getProposalId());

        setCommonModelAndPageAttributes(request, model, proposalContext, ProposalTab.COMMENTS);

        return "proposals/proposalComments";
    }

    private long createCommentThread(ProposalContext proposalContext) {
        Proposal proposal = proposalContext.getProposal();
        final long discussionThreadId = createDiscussionThread(proposalContext, " comments", false);
        proposal.setDiscussionId(discussionThreadId);
        proposalContext.getClients().getProposalClient().updateProposal(proposal);
        return discussionThreadId;
    }
}
