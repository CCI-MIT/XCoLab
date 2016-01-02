package org.xcolab.portlets.proposals.view;

import com.ext.portlet.service.ContestLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.ProposalTab;

import javax.portlet.PortletRequest;
import java.util.Map;

@Controller
@RequestMapping("view")
public class ProposalModelTabController extends BaseProposalTabController {

    @Autowired
    private ProposalsContext proposalsContext;
    
    @RequestMapping(params = {"pageToDisplay=proposalDetails_ACTIONSIMPACTS"})
    public String show(Model model, @RequestParam(required = false) boolean edit, PortletRequest request) 
            throws PortalException, SystemException {

        setCommonModelAndPageAttributes(request, model, ProposalTab.ACTIONSIMPACTS);
        
        if (edit) {
        	Map<Long, String> modelIdsWithNames = ContestLocalServiceUtil.getModelIdsAndNames(proposalsContext.getContest(request).getContestPK());
        	if (modelIdsWithNames.size() > 1) {
        		model.addAttribute("availableModels", modelIdsWithNames);
        	}
        	
            return "proposalModel_edit";
        }
        return "proposalModel";
    }
    

}
