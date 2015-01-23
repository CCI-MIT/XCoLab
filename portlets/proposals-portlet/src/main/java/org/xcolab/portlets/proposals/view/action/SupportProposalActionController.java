package org.xcolab.portlets.proposals.view.action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.analytics.AnalyticsUtil;
import org.xcolab.portlets.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.portlets.proposals.utils.ProposalsContext;

import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

@Controller
@RequestMapping("view")
public class SupportProposalActionController {

    @Autowired
    private ProposalsContext proposalsContext;
    
    private final static String SUPPORT_ANALYTICS_KEY = "SUPPORTED_ENTRIES";
    private final static String SUPPORT_ANALYTICS_CATEGORY = "User";
    private final static String SUPPORT_ANALYTICS_ACTION = "Support contest entry";
    private final static String SUPPORT_ANALYTICS_LABEL = "";

    
    @RequestMapping(params = {"action=supportProposalAction"})
    public synchronized void handleAction(ActionRequest request, Model model, ActionResponse response)
                    throws PortalException, SystemException, ProposalsAuthorizationException {
        
        if (proposalsContext.getPermissions(request).getCanSupportProposal()) {
            long userId = proposalsContext.getUser(request).getUserId();
            long proposalId = proposalsContext.getProposal(request).getProposalId();

            if (ProposalLocalServiceUtil.isSupporter(proposalId, userId)) {
                ProposalLocalServiceUtil.removeSupporter(proposalId, userId);
            }
            else {
                ProposalLocalServiceUtil.addSupporter(proposalId, userId);
                int analyticsValue = 0;
                int supportedCount = ProposalLocalServiceUtil.getUserSupportedProposalsCount(userId);
                if (supportedCount > 0) {
                	if (supportedCount == 1) {
                		analyticsValue = 1;
                	}
                	else if ( supportedCount < 5) {
                		analyticsValue = 2;
                	}
                	else {
                		analyticsValue = 3;
                	}
            	AnalyticsUtil.publishEvent(request, userId, SUPPORT_ANALYTICS_KEY + analyticsValue,
            			SUPPORT_ANALYTICS_CATEGORY,
            			SUPPORT_ANALYTICS_ACTION,
            			SUPPORT_ANALYTICS_LABEL,
            			analyticsValue);
                }
            }
        }
        else {
            throw new ProposalsAuthorizationException("User isn't allowed to toggle support for proposal");
        }
    }
    
}
