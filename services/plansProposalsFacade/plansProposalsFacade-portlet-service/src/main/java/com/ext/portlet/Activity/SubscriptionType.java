package com.ext.portlet.Activity;

import org.xcolab.activityEntry.ActivityEntryType;
import org.xcolab.client.activities.pojo.ActivitySubscription;

public enum SubscriptionType {
    DISCUSSION(ActivityEntryType.DISCUSSION.getPrimaryTypeId(), "Discussion"),
    PROPOSAL(ActivityEntryType.PROPOSOSAL.getPrimaryTypeId(), "Proposal"),
    CONTEST(ActivityEntryType.CONTEST.getPrimaryTypeId(), "Contest");

    private Long className;
    private String printName;

    SubscriptionType(Long className, String printName) {
        this.className = className;
        this.printName = printName;
    }

    public static SubscriptionType getSubscriptionType(ActivitySubscription subscription) {
        for (SubscriptionType type : SubscriptionType.values()) {
            if (type.className.equals(subscription.getClassNameId())) {
                return type;
            }
        }
        return null;
    }

    public String getPrintName() {
        return printName;
    }
}
