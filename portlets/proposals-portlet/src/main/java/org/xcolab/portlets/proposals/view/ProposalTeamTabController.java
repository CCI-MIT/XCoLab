package org.xcolab.portlets.proposals.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.portlets.proposals.wrappers.ProposalTab;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

@Controller
@RequestMapping("view")
public class ProposalTeamTabController extends BaseProposalTabController {

    @RequestMapping(params = {"pageToDisplay=proposalDetails", "tab=TEAM"})
    public String show(
            @RequestParam(value="planId") Long proposalId, 
            @RequestParam Long contestId, 
            @RequestParam(required = false) Long phaseId, 
            @RequestParam(defaultValue="DESCRIPTION") String tab,
            Model model) 
            throws PortalException, SystemException {
        
        findEntitiesAndPopulateModel(proposalId, contestId, phaseId, model);
        model.addAttribute("currentTab", ProposalTab.TEAM);
        
        return "proposalTeam";
    }
    

}
