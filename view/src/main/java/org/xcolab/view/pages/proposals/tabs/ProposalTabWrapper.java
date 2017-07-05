package org.xcolab.view.pages.proposals.tabs;

import org.xcolab.view.pages.proposals.utils.context.ProposalContext;

import javax.servlet.http.HttpServletRequest;

public class ProposalTabWrapper {

    private ProposalTab tab;
    private String displayName;
    private final HttpServletRequest request;
    private final ProposalContext proposalContext;

    public ProposalTabWrapper(ProposalTab tab, HttpServletRequest request, ProposalContext proposalContext) {
        super();
        this.tab = tab;
        this.request = request;
        this.proposalContext = proposalContext;
    }
    
    public ProposalTab getTab() {
        return tab;
    }

    public void setTab(ProposalTab tab) {
        this.tab = tab;
    }

    public int getActivityCount() {
        return tab.getActivityCount(proposalContext, request);
    }

    public String getName() {
        return tab.name();
    }
    
    public String getDisplayName() {
        if (displayName == null) {
            displayName = tab.getDisplayName();
        }
        return displayName;
    }
    
    public boolean isDefault() {
        return tab.isDefault();
    }
    
    public boolean isDefaultTab() {
        return tab.isDefault();
    }
    
    public boolean getCanEdit() {
        return tab.getCanEdit(proposalContext);
    }
    
    public boolean getCanAccess() {
        return tab.getCanAccess(proposalContext);
    }
}
