package org.xcolab.portlets.registration;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import com.ext.portlet.Activity.SubscriptionType;
import com.ext.portlet.model.ActivitySubscription;
import com.ext.portlet.service.ActivitySubscriptionLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

public class UserSubscriptionsBean {
    private User user;
    private List<ActivitySubscriptionWrapper> subscriptions;
    private SubscriptionType typeFilter;
    
    public UserSubscriptionsBean(User user) throws SystemException {
        this.user = user;
    }
    
    public List<ActivitySubscriptionWrapper> getSubscriptions() throws SystemException {
        if (subscriptions == null) {
            subscriptions = new ArrayList<ActivitySubscriptionWrapper>();
            
            for (ActivitySubscription subscription: ActivitySubscriptionLocalServiceUtil.findByUser(user.getUserId())) {
                if (typeFilter == null || typeFilter == SubscriptionType.getSubscriptionType(subscription)) {
                    subscriptions.add(new ActivitySubscriptionWrapper(subscription));
                }
            }
        }
        return subscriptions;
    }
    
    public void removeSelected(ActionEvent e) throws SystemException {
        for (ActivitySubscriptionWrapper subscription: subscriptions) {
            if (subscription.getSelected()) {
                ActivitySubscriptionLocalServiceUtil.delete(subscription.getWrapped());
            }
        }
        subscriptions = null;
        
    }
    
    public void setFilterType(ActionEvent e) {
        Object type = e.getComponent().getAttributes().get("type");
        if (type == null) {
            typeFilter = null;
        }
        else {
            typeFilter = SubscriptionType.valueOf(type.toString());
        }
        subscriptions = null;
    }
    
    public int getSubscriptionsCount() {
        return subscriptions.size();
    }

    public SubscriptionType getTypeFilter() {
        return typeFilter;
    }

    public String getTypeFilterName() {
        return typeFilter != null ? typeFilter.name() : null;
    }

}
