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
public class ProposalCommentsTabController extends BaseProposalTabController {
    
    @RequestMapping(params = {"pageToDisplay=proposalDetails_COMMENTS"})
    public String showComments(
            @RequestParam(value="planId") Long proposalId, 
            @RequestParam Long contestId, 
            @RequestParam(required = false) Long phaseId, 
            @RequestParam(defaultValue="DESCRIPTION") String tab,
            Model model) 
            throws PortalException, SystemException {

        handleRequest(proposalId, contestId, phaseId, model);
        model.addAttribute("currentTab", ProposalTab.COMMENTS);
        
        return "proposalComments";
    }
    
    @RequestMapping(params = {"pageToDisplay=proposalDetails_JUDGES_COMMENTS"})
    public String showJudgesComments(
            @RequestParam(value="planId") Long proposalId, 
            @RequestParam Long contestId, 
            @RequestParam(required = false) Long phaseId, 
            @RequestParam(defaultValue="DESCRIPTION") String tab,
            Model model) 
            throws PortalException, SystemException {

        handleRequest(proposalId, contestId, phaseId, model);
        model.addAttribute("currentTab", ProposalTab.JUDGES_COMMENTS);
        
        return "proposalComments";
    }
    
    @RequestMapping(params = {"pageToDisplay=proposalDetails_ADVISORS_COMMENTS"})
    public String showAdvisorsComments(
            @RequestParam(value="planId") Long proposalId, 
            @RequestParam Long contestId, 
            @RequestParam(required = false) Long phaseId, 
            @RequestParam(defaultValue="DESCRIPTION") String tab,
            Model model) 
            throws PortalException, SystemException {
        
        handleRequest(proposalId, contestId, phaseId, model);
        model.addAttribute("currentTab", ProposalTab.ADVISORS_COMMENTS);
        return "proposalComments";
    }
    
    private void handleRequest(Long proposalId, Long contestId, Long phaseId, Model model) throws PortalException, SystemException {
        findEntitiesAndPopulateModel(proposalId, contestId, phaseId, model);
    }
}
