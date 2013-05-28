package org.xcolab.portlets.feeds;

import java.io.Serializable;

import org.xcolab.portlets.feeds.activities.ActivitiesBean;
import org.xcolab.portlets.feeds.activities.PaginatedActivitiesBean;
import org.xcolab.portlets.feeds.members.MembersBean;


public class FeedsBean implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ActivitiesBean activitiesBean;
    private MembersBean membersBean;
    private FeedsPreferences preferences;
    private PaginatedActivitiesBean paginatedActivitiesBean;

    public FeedsBean() {
    }
    
    public ActivitiesBean getActivitiesBean() {
        if (activitiesBean == null) {
            activitiesBean = new ActivitiesBean(preferences);
        }
        return activitiesBean;
    }
    
    public PaginatedActivitiesBean getPaginatedActivitiesBean() {
        if (paginatedActivitiesBean == null) {
            paginatedActivitiesBean = new PaginatedActivitiesBean(preferences);
        }
        return paginatedActivitiesBean;
    }
    
    public void setFeedsPreferences(FeedsPreferences preferences) {
        this.preferences = preferences;
    }
    
    public MembersBean getMembersBean() {
        if (membersBean == null) {
            membersBean = new MembersBean(preferences);
        }
        return membersBean;
    }
}
