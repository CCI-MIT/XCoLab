package org.xcolab.portlets.proposals.view;

import java.util.List;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.portlets.proposals.utils.ProposalsContext;

import com.ext.portlet.service.ContestLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

@Controller
@RequestMapping("view")
public class ContestModelController extends BaseProposalsController {
    
    @Autowired
    private ProposalsContext proposalsContext;

    @RequestMapping(params = "pageToDisplay=contestModel")
    public String showContestProposals(RenderRequest request, RenderResponse response, Model model) 
            throws PortalException, SystemException {
        
    	Long modelId = ContestLocalServiceUtil.getDefaultModelId(proposalsContext.getContest(request).getContestPK());
    	model.addAttribute("modelId", modelId);
    	if (modelId != null) {
        	Map<Long, String> modelIdsWithNames = ContestLocalServiceUtil.getModelIdsAndNames(proposalsContext.getContest(request).getContestPK());
        	
        	model.addAttribute("availableModels", modelIdsWithNames);
    		
    	}        
        return "contestModel";
        
    }
}
