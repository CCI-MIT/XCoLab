package org.xcolab.portlets.proposals.wrappers;

import org.xcolab.portlets.proposals.permissions.ProposalsPermissions;

import java.util.ArrayList;
import java.util.List;

public class ProposalTabWrapper {
    private ProposalTab tab;
    private String displayName;
    private int activityCount;


    public static List<ProposalTabWrapper> createAll(ProposalsPermissions permissions){
        List<ProposalTabWrapper> tabs = new ArrayList<ProposalTabWrapper>();

        int i=0;
        for (ProposalTab tab: ProposalTab.values()) {
            if (tab == ProposalTab.ADMIN && !permissions.getCanAdmin()) continue;
            if (tab == ProposalTab.JUDGE && (!permissions.getCanJudgeActions() || !permissions.getCanFellowActions())) continue;
            if (tab == ProposalTab.FELLOW && !permissions.getCanFellowActions()) continue;
            tabs.add(new ProposalTabWrapper(tab, i++));
        }
        return tabs;
    }


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
