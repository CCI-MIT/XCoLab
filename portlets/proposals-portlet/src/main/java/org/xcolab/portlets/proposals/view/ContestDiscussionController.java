package org.xcolab.portlets.proposals.view;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.portlets.proposals.utils.context.ProposalsContext;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

@Controller
@RequestMapping("view")
public class ContestDiscussionController extends BaseProposalsController {
    
    @Autowired
    private ProposalsContext proposalsContext;

    @RequestMapping(params = "pageToDisplay=contestDiscussion")
    public String showContestProposals(RenderRequest request, RenderResponse response, Model model) 
            throws PortalException, SystemException {

        model.addAttribute("discussionId",  proposalsContext.getContest(request).getDiscussionGroupId());        
        return "contestDiscussion";
    }
}
