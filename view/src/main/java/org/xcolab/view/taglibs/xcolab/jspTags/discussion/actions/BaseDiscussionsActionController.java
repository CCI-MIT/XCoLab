package org.xcolab.view.taglibs.xcolab.jspTags.discussion.actions;

import org.springframework.beans.factory.annotation.Autowired;

import org.xcolab.client.comment.pojo.IThread;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.IProposalClient;
import org.xcolab.client.contest.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.view.pages.proposals.discussion.ProposalDiscussionPermissions;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.DiscussionPermissions;

import javax.servlet.http.HttpServletRequest;

public abstract class BaseDiscussionsActionController {

    @Autowired
    protected IProposalClient proposalClient;

    protected DiscussionPermissions getDiscussionPermissions(HttpServletRequest request,
            IThread commentThread) {
        if (commentThread.getCategory() == null) {
            final ProposalWrapper proposal = getProposal(proposalClient, commentThread);
            if (proposal != null) {
                return new ProposalDiscussionPermissions(request, proposal);
            }
        }
        return new DiscussionPermissions();
    }

    protected ProposalWrapper getProposal(IProposalClient proposalClient, IThread commentThread) {
        try {
            return proposalClient.getProposalByThreadId(commentThread.getId());
        } catch (ProposalNotFoundException e) {
            return null;
        }
    }
}
