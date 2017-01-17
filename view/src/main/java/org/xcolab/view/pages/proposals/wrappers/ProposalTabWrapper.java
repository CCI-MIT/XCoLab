package org.xcolab.view.pages.proposals.wrappers;

import org.xcolab.view.pages.proposals.permissions.ProposalsPermissions;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContext;

import javax.servlet.http.HttpServletRequest;

public class ProposalTabWrapper {
    private ProposalTab tab;
    private String displayName;
    private final HttpServletRequest request;
    private final ProposalsContext context;
    private final ProposalsPermissions permissions;
    
    public ProposalTabWrapper(ProposalTab tab, HttpServletRequest request, ProposalsContext context) {
        super();
        this.tab = tab;
        this.request = request;
        this.context = context;
        this.permissions = context.getPermissions(request);
    }
    
    public ProposalTab getTab() {
        return tab;
    }
    public void setTab(ProposalTab tab) {
        this.tab = tab;
    }
    public int getActivityCount() {
        return tab.getActivityCount(context, request);
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
        return tab.getCanEdit(permissions, context, request);
    }
    
    public boolean getCanAccess() {
        return tab.getCanAccess(permissions, context, request);
    }
}
