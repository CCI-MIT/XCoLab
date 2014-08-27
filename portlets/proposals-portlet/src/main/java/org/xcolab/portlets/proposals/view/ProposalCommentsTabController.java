package org.xcolab.portlets.proposals.view;

import javax.portlet.PortletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.ProposalTab;
import org.xcolab.portlets.proposals.wrappers.ProposalTabWrapper;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

@Controller
@RequestMapping("view")
public class ProposalCommentsTabController extends BaseProposalTabController {
    @Autowired
    private ProposalsContext proposalsContext;
    
    @RequestMapping(params = {"pageToDisplay=proposalDetails_COMMENTS"})
    public String showComments(PortletRequest request, Model model) 
            throws PortalException, SystemException {

        model.addAttribute("discussionId",  proposalsContext.getProposal(request).getDiscussionId() );
        model.addAttribute("authorId", proposalsContext.getProposal(request).getAuthorId());
        model.addAttribute("proposalId", proposalsContext.getProposal(request).getProposalId());

        setCommonModelAndPageAttributes(request, model, ProposalTab.COMMENTS);

        return "proposalComments";
    }
    
}
