package org.xcolab.portlets.proposals.view;

import com.ext.portlet.JudgingSystemActions;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ProposalRatingLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.portlets.proposals.requests.FellowProposalScreeningBean;
import org.xcolab.portlets.proposals.requests.ProposalAdvancingBean;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.ProposalFellowWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalTab;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;

import javax.portlet.PortletRequest;

@Controller
@RequestMapping("view")
public class ProposalJudgesTabController extends BaseProposalTabController {
    @Autowired
    private ProposalsContext proposalsContext;
    
    @RequestMapping(params = {"pageToDisplay=proposalDetails_ADVANCING"})
    public String showJudgesPanel(PortletRequest request, Model model) 
            throws PortalException, SystemException {
        
        setCommonModelAndPageAttributes(request, model, ProposalTab.ADVANCING);
        ProposalWrapper proposalWrapper = new ProposalWrapper(proposalsContext.getProposal(request), proposalsContext.getContestPhase(request));

        model.addAttribute("discussionId", proposalsContext.getProposal(request).getJudgeDiscussionId());
        model.addAttribute("proposalAdvancingBean", new ProposalAdvancingBean(proposalWrapper, proposalsContext.getContestPhase(request),
                proposalsContext.getProposalsPreferences(request)));
        model.addAttribute("advanceOptions", JudgingSystemActions.AdvanceDecision.values());

        return "proposalAdvancing";
    }
    
    @RequestMapping(params = {"pageToDisplay=proposalDetails_SCREENING"})
    public String showFellowsPanel(PortletRequest request,Model model) 
            throws PortalException, SystemException {

        model.addAttribute("discussionId", proposalsContext.getProposal(request).getFellowDiscussionId());

        setCommonModelAndPageAttributes(request, model, ProposalTab.SCREENING);

        Proposal proposal = proposalsContext.getProposal(request);

        ProposalWrapper proposalWrapper = new ProposalWrapper(proposal, proposalsContext.getContestPhase(request));
        ProposalFellowWrapper proposalFellowWrapper = new ProposalFellowWrapper(proposalWrapper, proposalsContext.getUser(request));
        model.addAttribute("fellowProposalScreeningBean", new FellowProposalScreeningBean(proposalFellowWrapper,
                proposalsContext.getProposalsPreferences(request)));
        model.addAttribute("judgingOptions", JudgingSystemActions.FellowAction.values());

        model.addAttribute("comments", ProposalRatingLocalServiceUtil.getAllRatingsForProposal(proposal.getProposalId()));

        return "proposalScreening";
    }
    
}
