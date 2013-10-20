package org.xcolab.portlets.proposals.view;

import javax.portlet.PortletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.ProposalTab;

import com.ext.portlet.service.ContestPhaseRibbonTypeLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

@Controller
@RequestMapping("view")
public class ProposalAdminTabController extends BaseProposalTabController {

    @Autowired
    private ProposalsContext context;
    
    @RequestMapping(params = {"pageToDisplay=proposalDetails_ADMIN"})
    public String showProposalDetails(Model model, PortletRequest request) 
            throws PortalException, SystemException {

        setCommonModelAndPageAttributes(request, model, ProposalTab.ADMIN);
        model.addAttribute("availableRibbons", ContestPhaseRibbonTypeLocalServiceUtil.getContestPhaseRibbonTypes(0, Integer.MAX_VALUE));
        
        return "proposalAdmin";
    }
    
    

}
