package org.xcolab.portlets.proposals.wrappers;

import javax.portlet.PortletRequest;

import org.xcolab.portlets.proposals.permissions.ProposalsPermissions;
import org.xcolab.portlets.proposals.utils.ProposalsContext;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

public class ProposalTabWrapper {
    private ProposalTab tab;
    private String displayName;
    private final PortletRequest request;
    private final ProposalsContext context;
    private final ProposalsPermissions permissions;
    
    public ProposalTabWrapper(ProposalTab tab, PortletRequest request, ProposalsContext context) throws PortalException, SystemException {
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
