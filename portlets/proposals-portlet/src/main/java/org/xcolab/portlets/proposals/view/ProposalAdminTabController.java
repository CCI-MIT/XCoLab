package org.xcolab.portlets.proposals.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.portlets.proposals.wrappers.ProposalTab;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

@Controller
@RequestMapping("view")
public class ProposalAdminTabController extends BaseProposalTabController {

    @RequestMapping(params = {"pageToDisplay=proposalDetails_ADMIN"})
    public String showProposalDetails(Model model) 
            throws PortalException, SystemException {
        
        model.addAttribute("currentTab", ProposalTab.ADMIN);
        
        return "proposalAdmin";
    }
    
    

}
