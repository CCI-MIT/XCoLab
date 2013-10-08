package org.xcolab.portlets.proposals.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.xcolab.portlets.proposals.wrappers.ProposalTab;
import org.xcolab.portlets.proposals.wrappers.ProposalTabWrapper;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

public class BaseProposalTabController {
    
    
    @ModelAttribute
    public void getTabs(Model model) throws PortalException, SystemException {
        // populate available tabs
        
        List<ProposalTabWrapper> tabs = new ArrayList<ProposalTabWrapper>();
        
        int i=0; 
        for (ProposalTab tab: ProposalTab.values()) {
            tabs.add(new ProposalTabWrapper(tab, i++));
        }
        
        model.addAttribute("tabs", tabs);
        
        
    }

}
