package org.xcolab.portlets.proposals.wrappers;

public class ProposalTabWrapper {
    private ProposalTab tab;
    private String displayName;
    private int activityCount;
    
    public ProposalTabWrapper(ProposalTab tab, int activityCount) {
        super();
        this.tab = tab;
        this.activityCount = activityCount;
    }

    public ProposalTabWrapper(ProposalTab tab, int activityCount, String displayName) {
        super();
        this.tab = tab;
        this.activityCount = activityCount;
        this.displayName = displayName;
    }
    
    public ProposalTab getTab() {
        return tab;
    }
    public void setTab(ProposalTab tab) {
        this.tab = tab;
    }
    public int getActivityCount() {
        return activityCount;
    }
    public void setActivityCount(int activityCount) {
        this.activityCount = activityCount;
    }

    public String getName() {
        return tab.name();
    }
    
    public String getDisplayName() {
        if (displayName == null) {
            return tab.getDisplayName();
        }
        return displayName;
    }
    
    public boolean isDefault() {
        return tab.isDefault();
    }
    
    public boolean isDefaultTab() {
        return tab.isDefault();
    }
}
