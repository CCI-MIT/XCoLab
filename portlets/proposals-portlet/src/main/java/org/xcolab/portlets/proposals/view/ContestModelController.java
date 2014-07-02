package org.xcolab.portlets.proposals.view;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.portlets.proposals.utils.ProposalsContext;

import com.ext.portlet.models.CollaboratoriumModelingService;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ModelRunnerServiceUtil;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

@Controller
@RequestMapping("view")
public class ContestModelController extends BaseProposalsController {
    private final static String COOKIE_PREFERRED_MODEL = "cc_contests_preferredModels";
    
    @Autowired
    private ProposalsContext proposalsContext;

    @RequestMapping(params = "pageToDisplay=contestModel")
    public String showContestProposals(RenderRequest request, RenderResponse response, Model model, @RequestParam(required = false) boolean refreshModels) 
            throws PortalException, SystemException, IOException {
    	
    	if (refreshModels) {
    		ModelRunnerServiceUtil.refreshModels();
    	}
    	Long contestPK = proposalsContext.getContest(request).getContestPK();
    	Long modelId = ContestLocalServiceUtil.getDefaultModelId(contestPK);
    	

        for (Cookie cookie: request.getCookies()) {
            if (cookie.getName().equals(COOKIE_PREFERRED_MODEL)) {
            	try {
            		JsonElement element = new JsonParser().parse(URLDecoder.decode(cookie.getValue()));
            		JsonObject object = element.getAsJsonObject();
            		if (object.has(String.valueOf(proposalsContext.getContest(request).getContestPK()))) {
            			modelId = object.get(String.valueOf(contestPK)).getAsLong();
            		}
            				
            	}
            	catch (Exception e) {
            		// just ignore
            	}
            } 
        }
    	
    	
    	model.addAttribute("modelId", modelId);
    	if (modelId != null) {
        	Map<Long, String> modelIdsWithNames = ContestLocalServiceUtil.getModelIdsAndNames(proposalsContext.getContest(request).getContestPK());
        	
        	model.addAttribute("availableModels", modelIdsWithNames);
    		
    	}        
        return "contestModel";
        
    }
}
