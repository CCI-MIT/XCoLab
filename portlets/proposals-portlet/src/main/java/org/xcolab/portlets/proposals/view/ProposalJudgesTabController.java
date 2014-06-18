package org.xcolab.portlets.proposals.view;

import com.ext.portlet.JudgingSystemActions;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalRating;
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
import org.xcolab.portlets.proposals.wrappers.ProposalRatingWrapper;

import javax.portlet.PortletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("view")
public class ProposalJudgesTabController extends BaseProposalTabController {
    @Autowired
    private ProposalsContext proposalsContext;
    
    @RequestMapping(params = {"pageToDisplay=proposalDetails_ADVANCING"})
    public String showJudgesPanel(PortletRequest request, Model model) 
            throws PortalException, SystemException {
        
        setCommonModelAndPageAttributes(request, model, ProposalTab.ADVANCING);

        Proposal proposal = proposalsContext.getProposal(request);
        ProposalWrapper proposalWrapper = new ProposalWrapper(proposal, proposalsContext.getContestPhase(request));

        model.addAttribute("discussionId", proposalsContext.getProposal(request).getJudgeDiscussionId());
        model.addAttribute("proposalAdvancingBean", new ProposalAdvancingBean(proposalWrapper, proposalsContext.getContestPhase(request),
                proposalsContext.getProposalsPreferences(request)));
        model.addAttribute("advanceOptions", JudgingSystemActions.AdvanceDecision.values());

        List<ProposalRatingWrapper> fellowRatings = wrapProposalRatings(ProposalRatingLocalServiceUtil.getFellowRatingsForProposal(proposal.getProposalId()));
        List<ProposalRatingWrapper> judgeRatings = wrapProposalRatings(ProposalRatingLocalServiceUtil.getJudgeRatingsForProposal(proposal.getProposalId()));


        model.addAttribute("fellowRatings", fellowRatings);
        model.addAttribute("judgeRatings", judgeRatings);

        return "proposalAdvancing";
    }

    private static List<ProposalRatingWrapper> wrapProposalRatings(List<ProposalRating> ratings) {
        List<ProposalRatingWrapper> wrappedRatings = new ArrayList<ProposalRatingWrapper>();
        //wrap the comments
        for (ProposalRating r : ratings) {
            wrappedRatings.add(new ProposalRatingWrapper(r));
        }
        return wrappedRatings;
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

        return "proposalScreening";
    }
    
}
