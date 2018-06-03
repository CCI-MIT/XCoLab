package org.xcolab.view.pages.proposals.view.proposal.tabs;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.xcolab.view.pages.proposals.tabs.ProposalTab;
import org.xcolab.view.pages.proposals.tabs.ProposalTabWrapper;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;
import org.xcolab.view.pages.proposals.view.proposal.BaseProposalsController;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@Component
public class BaseProposalTabController extends BaseProposalsController {

    @ModelAttribute
    public void getTabs(Model model, HttpServletRequest request, ProposalContext proposalContext) {
        // populate available tabs
        
        List<ProposalTabWrapper> tabs = new ArrayList<>();
        
        for (ProposalTab tab: ProposalTab.values()) {
            
            ProposalTabWrapper tabWrapper = new ProposalTabWrapper(tab, request, proposalContext);
            if (tabWrapper.getCanAccess()) {
                tabs.add(new ProposalTabWrapper(tab, request, proposalContext));
            }
        }
        
        model.addAttribute("tabs", tabs);
    }
    

    protected void setCommonModelAndPageAttributes(HttpServletRequest request, Model model,
            ProposalContext proposalContext, ProposalTab tab) {
       
        model.addAttribute("currentTab", tab);
        model.addAttribute("currentTabWrapped", new ProposalTabWrapper(tab, request, proposalContext));
        
        setBasePageAttributes(proposalContext, model);
    }
}
