package org.xcolab.portlets.proposals.view;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.ProposalTab;
import org.xcolab.portlets.proposals.wrappers.ProposalTabWrapper;

import javax.portlet.PortletRequest;
import java.util.ArrayList;
import java.util.List;

@Component
public class BaseProposalTabController extends BaseProposalsController {

    protected final static String ACCESS_TAB_DENIED_MESSAGE = "You don't have the permissions to access this content.";
    
    @Autowired
    private ProposalsContext proposalsContext;
    
    @ModelAttribute
    public void getTabs(Model model, PortletRequest request) throws PortalException, SystemException {
        // populate available tabs
        
        List<ProposalTabWrapper> tabs = new ArrayList<>();
        
        for (ProposalTab tab: ProposalTab.values()) {
            
            ProposalTabWrapper tabWrapper = new ProposalTabWrapper(tab, request, proposalsContext);
            if (tabWrapper.getCanAccess()) {
                tabs.add(new ProposalTabWrapper(tab, request, proposalsContext));
            }
        }
        
        model.addAttribute("tabs", tabs);
    }
    

    protected void setCommonModelAndPageAttributes(PortletRequest request, Model model, ProposalTab tab) 
            throws PortalException, SystemException {
       
        model.addAttribute("currentTab", tab);
        model.addAttribute("currentTabWrapped", new ProposalTabWrapper(tab, request, proposalsContext));
        
        String pageTitle = proposalsContext.getContestWrapped(request).getContestShortName();
        String pageSubTitle = proposalsContext.getProposalWrapped(request).getName();
        String pageDescription = proposalsContext.getProposalWrapped(request).getPitch();
        
        if (pageSubTitle == null || StringUtils.isBlank(pageSubTitle)) {
            pageSubTitle = "Proposal for " + proposalsContext.getContestWrapped(request).getContestShortName();
        }

        if (tab != ProposalTab.DESCRIPTION) {
            pageSubTitle = tab.getDisplayName() + " - " + pageTitle;
        }
        setSeoTexts(request, pageTitle, pageSubTitle, pageDescription);
        
    }

}
