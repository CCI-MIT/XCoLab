package org.xcolab.view.pages.profile.wrappers;

import org.xcolab.client.activity.StaticActivityContext;
import org.xcolab.client.activity.pojo.IActivitySubscription;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.view.pages.profile.SubscriptionType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserSubscriptionsWrapper implements Serializable {
    private static final long serialVersionUID = 1L;

    private UserWrapper user;
    private List<ActivitySubscriptionWrapper> subscriptions;
    private SubscriptionType typeFilter;

    public UserSubscriptionsWrapper() {
    }

    public UserSubscriptionsWrapper(UserWrapper user) {
        this.user = user;
        getSubscriptions();
    }

    public List<ActivitySubscriptionWrapper> getSubscriptions() {
        if (subscriptions == null) {

                subscriptions = new ArrayList<>();

                for (IActivitySubscription subscription : StaticActivityContext.getActivityClient()
                        .getActivitySubscriptionsForMember(user.getId())) {

                    if (typeFilter == null
                            || typeFilter == SubscriptionType.getSubscriptionType(subscription)) {
                        subscriptions.add(new ActivitySubscriptionWrapper(subscription));
                    }
                }
        }
        return subscriptions;
    }

    public void setFilterType(String filterType) {
        if (filterType.equals("null")) {
            typeFilter = null;
        } else {
            typeFilter = SubscriptionType.valueOf(filterType);
        }
        subscriptions = null;
        getSubscriptions();
    }

    public int getSubscriptionsCount() {
        return getSubscriptions().size();
    }

    public SubscriptionType getTypeFilter() {
        return typeFilter;
    }

    public String getTypeFilterName() {
        return typeFilter != null ? typeFilter.name() : null;
    }
}
