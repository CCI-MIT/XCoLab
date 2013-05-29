package org.xcolab.portlets.userprofile;

import java.io.Serializable;
import java.util.Date;

import com.ext.portlet.Activity.SubscriptionType;
import com.ext.portlet.model.ActivitySubscription;
import com.ext.portlet.service.ActivitySubscriptionLocalServiceUtil;

public class ActivitySubscriptionWrapper implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ActivitySubscription subscription;
    private boolean selected;
    
    public ActivitySubscriptionWrapper(ActivitySubscription subscription) {
        this.subscription = subscription;
    }
    
    public String getName() {
        return ActivitySubscriptionLocalServiceUtil.getName(subscription);
    }
    
    public Date getUpdated() {
        return subscription.getModifiedDate();
    }
    
    public SubscriptionType getType() {
        return SubscriptionType.getSubscriptionType(subscription);
    }
    
    public boolean getSelected() {
        return selected;
    }
    
    
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public ActivitySubscription getWrapped() {
        return subscription;
    }
}
