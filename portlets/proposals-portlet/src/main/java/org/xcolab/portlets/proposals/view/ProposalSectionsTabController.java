package org.xcolab.portlets.proposals.view;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.portlets.proposals.requests.JudgeProposalFeedbackBean;
import org.xcolab.portlets.proposals.requests.UpdateProposalDetailsBean;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.*;

import javax.portlet.PortletRequest;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("view")
public class ProposalSectionsTabController extends BaseProposalTabController {

    @Autowired
    private ProposalsContext proposalsContext;

    @RequestMapping(params = "pageToDisplay=proposalDetails")
    public String showProposalDetails(
            @RequestParam(value="planId") Long proposalId, 
            @RequestParam Long contestId, 
            @RequestParam(required = false) Long phaseId, 
            @RequestParam(defaultValue="false") boolean edit,
            @RequestParam(defaultValue="false") boolean move,
            Model model, PortletRequest request) 
            throws PortalException, SystemException {
        
        //findEntitiesAndPopulateModel(proposalId, contestId, phaseId, model);

        setCommonModelAndPageAttributes(request, model, ProposalTab.DESCRIPTION);
        
        if (move) {
        	// get base proposal from base contest
        	Proposal proposal = proposalsContext.getProposal(request);
        	Contest baseContest = Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal(proposal.getProposalId());
        	ContestPhase contestPhase = ContestLocalServiceUtil.getActiveOrLastPhase(baseContest);
        	
        	ProposalWrapper baseProposalWrapped = new ProposalWrapper(proposal, proposal.getCurrentVersion(), baseContest, contestPhase, null);
        	model.addAttribute("baseProposal", baseProposalWrapped);
        	model.addAttribute("baseContest", new ContestWrapper(baseContest));
        	model.addAttribute("move", true);
        	
        	
        	UpdateProposalDetailsBean updateProposalDetailsBean = 
        			new UpdateProposalDetailsBean(proposalsContext.getProposalWrapped(request), baseProposalWrapped, true);
        	
        	// find sections that can't be mapped without user interaction

        	boolean hasNotMappedSections = false;
        	Set<Long> newContestSections = new HashSet<>();
        	
        	for (ProposalSectionWrapper section: proposalsContext.getProposalWrapped(request).getSections()) {
        		newContestSections.add(section.getSectionDefinitionId());
        	}
        	
        	
        	for (ProposalSectionWrapper section: baseProposalWrapped.getSections()) {
        		if (section.getContent() != null && section.getContent().trim().length() > 0) {
        			// we have non empty section in base proposal, check if such
        			// section exists in target contest
        			if (! newContestSections.contains(section.getSectionDefinitionId())) {
        				hasNotMappedSections = true;
        			}
        		}
        		
        	}
        	
        	updateProposalDetailsBean.setMoveToContestPhaseId(contestPhase.getContestPhasePK());
        	
        	
        	model.addAttribute("updateProposalSectionsBean", updateProposalDetailsBean);
        	model.addAttribute("hasNotMappedSections", hasNotMappedSections);
        } else {
            model.addAttribute("updateProposalSectionsBean", new UpdateProposalDetailsBean(proposalsContext.getProposalWrapped(request)));
        }

        
        if (edit || move) {
            return "proposalDetails_edit";
        }

        ProposalWrapper proposalWrapper = proposalsContext.getProposalWrapped(request);
        ProposalJudgeWrapper proposalJudgeWrapper = new ProposalJudgeWrapper(proposalWrapper, proposalsContext.getUser(request));
        JudgeProposalFeedbackBean judgeProposalBean = new JudgeProposalFeedbackBean(proposalJudgeWrapper);
        judgeProposalBean.setContestPhaseId(proposalsContext.getContestPhase(request).getContestPhasePK());
        model.addAttribute("judgeProposalBean", judgeProposalBean);

        return "proposalDetails";
    }
}
