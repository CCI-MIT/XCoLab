package org.xcolab.portlets.proposals.view;

import javax.portlet.PortletRequest;

import com.ext.portlet.JudgingSystemActions;
import com.ext.portlet.service.ContestLocalServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.portlets.proposals.requests.JudgeProposalBean;
import org.xcolab.portlets.proposals.requests.RequestMembershipBean;
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

        ProposalWrapper pw = new ProposalWrapper(
                proposalsContext.getProposal(request),
                proposalsContext.getProposal(request).getCurrentVersion(),
                proposalsContext.getContest(request),
                proposalsContext.getContestPhase(request),
                proposalsContext.getProposal2Phase(request)
        );

        model.addAttribute("currentTab", ProposalTab.JUDGE);
        model.addAttribute("discussionId", proposalsContext.getProposal(request).getJudgeDiscussionId());
        model.addAttribute("judgeProposalBean", new JudgeProposalBean(proposalsContext.getProposalWrapped(request)));
        model.addAttribute("judgingOptions", JudgingSystemActions.JudgeAction.values());

        return "proposalJudge";
    }
    
    @RequestMapping(params = {"pageToDisplay=proposalDetails_FELLOW"})
    public String showFellowsPanel(PortletRequest request,Model model) 
            throws PortalException, SystemException {

        model.addAttribute("discussionId", proposalsContext.getProposal(request).getFellowDiscussionId());
        model.addAttribute("currentTab", ProposalTab.FELLOW);
        model.addAttribute("judgeProposalBean", new JudgeProposalBean(proposalsContext.getProposalWrapped(request)));



        //ProposalWrapper(Proposal proposal, int version, Contest contest, ContestPhase contestPhase, Proposal2Phase proposal2Phase)

        model.addAttribute("judgingOptions", JudgingSystemActions.FellowAction.values());

        return "proposalFellow";
    }
    
}
