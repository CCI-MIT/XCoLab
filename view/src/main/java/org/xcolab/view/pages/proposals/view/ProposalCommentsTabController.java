package org.xcolab.view.pages.proposals.view;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.view.pages.proposals.discussion.ProposalDiscussionPermissions;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContext;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContextUtil;
import org.xcolab.view.pages.proposals.wrappers.ProposalTab;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.DiscussionPermissions;

import javax.servlet.http.HttpServletRequest;

@Controller

public class ProposalCommentsTabController extends BaseProposalTabController {
    @Autowired
    private ProposalsContext proposalsContext;


    @GetMapping({"/contests/{contestYear}/{contestUrlName}/phase/{phaseId}/{proposalUrlString}/{proposalId}/tab/COMMENTS",
            "/contests/{contestYear}/{contestUrlName}/c/{proposalUrlString}/{proposalId}/tab/COMMENTS"
    })
    public String showComments(HttpServletRequest request, Model model) {

        final Proposal proposal = proposalsContext.getProposal(request);
        long discussionId = proposal.getDiscussionId();
        if (discussionId == 0) {
            discussionId = createCommentThread(request);
        }

        request.setAttribute(DiscussionPermissions.REQUEST_ATTRIBUTE_NAME,
                new ProposalDiscussionPermissions(request));

        model.addAttribute("discussionId", discussionId);
        model.addAttribute("authorId", proposal.getAuthorId());
        model.addAttribute("proposalId", proposal.getProposalId());

        setCommonModelAndPageAttributes(request, model, ProposalTab.COMMENTS);

        return "proposals/proposalComments";
    }

    private long createCommentThread(HttpServletRequest request) {
        Proposal proposal = proposalsContext.getProposal(request);
        final long discussionThreadId = createDiscussionThread(request, " comments", false);
        proposal.setDiscussionId(discussionThreadId);
        ProposalsContextUtil.getClients(request).getProposalClient().updateProposal(proposal);
        return discussionThreadId;
    }
}
