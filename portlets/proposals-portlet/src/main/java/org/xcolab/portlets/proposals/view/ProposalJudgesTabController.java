package org.xcolab.portlets.proposals.view;

import javax.portlet.PortletRequest;

import com.ext.portlet.JudgingSystemActions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.portlets.proposals.requests.FellowProposalScreeningBean;
import org.xcolab.portlets.proposals.requests.ProposalAdvancingBean;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.ProposalTab;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;

@Controller
@RequestMapping("view")
public class ProposalJudgesTabController extends BaseProposalTabController {
    @Autowired
    private ProposalsContext proposalsContext;
    
    @RequestMapping(params = {"pageToDisplay=proposalDetails_JUDGE"})
    public String showJudgesPanel(PortletRequest request, Model model) 
            throws PortalException, SystemException {
        
        setCommonModelAndPageAttributes(request, model, ProposalTab.JUDGE);
        ProposalWrapper proposalWrapper = proposalsContext.getProposalWrapped(request);

        model.addAttribute("discussionId", proposalsContext.getProposal(request).getJudgeDiscussionId());
        model.addAttribute("proposalAdvancingBean", new ProposalAdvancingBean(proposalWrapper,
                proposalsContext.getProposalsPreferences(request)));
        model.addAttribute("advanceOptions", JudgingSystemActions.AdvanceDecision.values());

        return "proposalJudge";
    }
    
    @RequestMapping(params = {"pageToDisplay=proposalDetails_FELLOW"})
    public String showFellowsPanel(PortletRequest request,Model model) 
            throws PortalException, SystemException {

        model.addAttribute("discussionId", proposalsContext.getProposal(request).getFellowDiscussionId());

        setCommonModelAndPageAttributes(request, model, ProposalTab.FELLOW);

        ProposalWrapper proposalWrapper = proposalsContext.getProposalWrapped(request);
        model.addAttribute("fellowProposalScreeningBean", new FellowProposalScreeningBean(proposalWrapper,
                proposalsContext.getProposalsPreferences(request)));
        model.addAttribute("judgingOptions", JudgingSystemActions.FellowAction.values());

        return "proposalFellow";
    }
    
}
