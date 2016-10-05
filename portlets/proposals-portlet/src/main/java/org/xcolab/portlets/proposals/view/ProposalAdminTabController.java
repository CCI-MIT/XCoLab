package org.xcolab.portlets.proposals.view;


import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.client.contest.ContestClient;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.ProposalTab;

import javax.portlet.PortletRequest;

@Controller
@RequestMapping("view")
public class ProposalAdminTabController extends BaseProposalTabController {

    @Autowired
    private ProposalsContext context;
    
    @RequestMapping(params = {"pageToDisplay=proposalDetails_ADMIN"})
    public String showProposalDetails(Model model, PortletRequest request) 
            throws PortalException, SystemException {

        setCommonModelAndPageAttributes(request, model, ProposalTab.ADMIN);
        model.addAttribute("availableRibbons", ContestClient.getAllContestPhaseRibbonType());
        
        return "proposalAdmin";
    }
    
    

}
