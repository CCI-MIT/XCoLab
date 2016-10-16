package org.xcolab.portlets.proposals.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.jspTags.discussion.DiscussionPermissions;
import org.xcolab.portlets.proposals.discussion.ProposalDiscussionPermissions;
import org.xcolab.portlets.proposals.utils.context.ProposalsContext;
import org.xcolab.portlets.proposals.utils.context.ProposalsContextUtil;
import org.xcolab.portlets.proposals.wrappers.ProposalTab;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;

import javax.portlet.PortletRequest;

@Controller
@RequestMapping("view")
public class ProposalFellowReviewTabController extends BaseProposalTabController {

    @Autowired
    private ProposalsContext proposalsContext;
    
    @RequestMapping(params = {"pageToDisplay=proposalDetails_FELLOW_REVIEW"})
    public String showFellowReview(PortletRequest request, Model model)
            throws PortalException, SystemException {

        final ProposalWrapper proposal = proposalsContext.getProposalWrapped(request);

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

    private long createFellowThread(PortletRequest request)
            throws SystemException, PortalException {
        Proposal proposal = proposalsContext.getProposal(request);
        final long discussionThreadId = createDiscussionThread(request, " fellow review", true);
        proposal.setFellowDiscussionId(discussionThreadId);
        ProposalsContextUtil.getClients(request).getProposalClient().updateProposal(proposal);
        return discussionThreadId;
    }
}
