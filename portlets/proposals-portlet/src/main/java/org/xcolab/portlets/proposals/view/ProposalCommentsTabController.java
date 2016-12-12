package org.xcolab.portlets.proposals.view;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.jspTags.discussion.DiscussionPermissions;
import org.xcolab.portlets.proposals.discussion.ProposalDiscussionPermissions;
import org.xcolab.portlets.proposals.utils.context.ProposalsContext;
import org.xcolab.portlets.proposals.utils.context.ProposalsContextUtil;
import org.xcolab.portlets.proposals.wrappers.ProposalTab;

import javax.portlet.PortletRequest;

@Controller
@RequestMapping("view")
public class ProposalCommentsTabController extends BaseProposalTabController {
    @Autowired
    private ProposalsContext proposalsContext;
    
    @RequestMapping(params = {"pageToDisplay=proposalDetails_COMMENTS"})
    public String showComments(PortletRequest request, Model model) {

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

        return "proposalComments";
    }

    private long createCommentThread(PortletRequest request) {
        Proposal proposal = proposalsContext.getProposal(request);
        final long discussionThreadId = createDiscussionThread(request, " comments", false);
        proposal.setDiscussionId(discussionThreadId);
        ProposalsContextUtil.getClients(request).getProposalClient().updateProposal(proposal);
        return discussionThreadId;
    }
}
