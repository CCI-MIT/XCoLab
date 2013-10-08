package org.xcolab.portlets.proposals.view;

import javax.portlet.PortletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.portlets.proposals.requests.UpdateProposalDetailsBean;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.ProposalTab;

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
            Model model, PortletRequest request) 
            throws PortalException, SystemException {
        
        //findEntitiesAndPopulateModel(proposalId, contestId, phaseId, model);
        
        model.addAttribute("updateProposalSectionsBean", new UpdateProposalDetailsBean(proposalsContext.getProposalWrapped(request)));
        
        model.addAttribute("currentTab", ProposalTab.DESCRIPTION);
        
        if (edit) {
            return "proposalDetails_edit";
        }
        return "proposalDetails";
    }
}
