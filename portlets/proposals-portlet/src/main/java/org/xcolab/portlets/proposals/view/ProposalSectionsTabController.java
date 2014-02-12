package org.xcolab.portlets.proposals.view;

import javax.portlet.PortletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.portlets.proposals.requests.UpdateProposalDetailsBean;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.ContestWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalTab;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

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
        
        model.addAttribute("updateProposalSectionsBean", new UpdateProposalDetailsBean(proposalsContext.getProposalWrapped(request)));

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
        	
        	updateProposalDetailsBean.setMoveToContestPhaseId(contestPhase.getContestPhasePK());
        	
        	
        	model.addAttribute("updateProposalSectionsBean", updateProposalDetailsBean);
        }
        
        if (edit || move) {
            return "proposalDetails_edit";
        }
        return "proposalDetails";
    }
}
