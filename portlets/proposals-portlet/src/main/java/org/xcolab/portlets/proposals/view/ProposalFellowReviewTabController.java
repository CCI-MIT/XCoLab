package org.xcolab.portlets.proposals.view;

import com.ext.portlet.model.Proposal;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
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
        model.addAttribute("discussionId", proposal.getFellowDiscussionId());
        model.addAttribute("authorId", proposal.getAuthorId());
        model.addAttribute("proposalId", proposal.getProposalId());

        setCommonModelAndPageAttributes(request, model, ProposalTab.FELLOW_REVIEW);

        return "proposalComments";
    }
    
}
