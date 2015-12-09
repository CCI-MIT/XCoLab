package org.xcolab.portlets.proposals.view;

import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.jspTags.discussion.DiscussionPermissions;
import org.xcolab.portlets.proposals.discussion.ProposalDiscussionPermissions;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.ProposalTab;

import javax.portlet.PortletRequest;

@Controller
@RequestMapping("view")
public class ProposalCommentsTabController extends BaseProposalTabController {
    @Autowired
    private ProposalsContext proposalsContext;
    
    @RequestMapping(params = {"pageToDisplay=proposalDetails_COMMENTS"})
    public String showComments(PortletRequest request, Model model) 
            throws PortalException, SystemException {

        final long discussionCategoryGroupId = proposalsContext.getProposal(request).getDiscussionId();

        request.setAttribute(DiscussionPermissions.REQUEST_ATTRIBUTE_NAME, new ProposalDiscussionPermissions(request,
                DiscussionCategoryGroupLocalServiceUtil.getDiscussionCategoryGroup(discussionCategoryGroupId)));

        model.addAttribute("discussionId", discussionCategoryGroupId);
        model.addAttribute("authorId", proposalsContext.getProposal(request).getAuthorId());
        model.addAttribute("proposalId", proposalsContext.getProposal(request).getProposalId());

        setCommonModelAndPageAttributes(request, model, ProposalTab.COMMENTS);

        return "proposalComments";
    }
    
}
