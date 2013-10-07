package org.xcolab.portlets.proposals.view;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.portlets.proposals.wrappers.ProposalTab;

@Controller
@RequestMapping("view")
public class ProposalJudgesTabController extends BaseProposalTabController {
    @RequestMapping(params = {"pageToDisplay=proposalDetails_JUDGE"})
    public String showJudgesPanel(
            @RequestParam(value="planId") Long proposalId, 
            @RequestParam Long contestId, 
            @RequestParam(required = false) Long phaseId, 
            @RequestParam(defaultValue="DESCRIPTION") String tab,
            Model model) 
            throws PortalException, SystemException {

        handleRequest(proposalId, contestId, phaseId, model);
        model.addAttribute("currentTab", ProposalTab.JUDGE);
        
        return "proposalJudge";
    }
    
    @RequestMapping(params = {"pageToDisplay=proposalDetails_FELLOW"})
    public String showFellowsPanel(
            @RequestParam(value="planId") Long proposalId, 
            @RequestParam Long contestId, 
            @RequestParam(required = false) Long phaseId, 
            @RequestParam(defaultValue="DESCRIPTION") String tab,
            Model model) 
            throws PortalException, SystemException {
        
        handleRequest(proposalId, contestId, phaseId, model);
        model.addAttribute("currentTab", ProposalTab.FELLOW);
        return "proposalFellow";
    }
    
    private void handleRequest(Long proposalId, Long contestId, Long phaseId, Model model) throws PortalException, SystemException {
        findEntitiesAndPopulateModel(proposalId, contestId, phaseId, model);
    }
}
