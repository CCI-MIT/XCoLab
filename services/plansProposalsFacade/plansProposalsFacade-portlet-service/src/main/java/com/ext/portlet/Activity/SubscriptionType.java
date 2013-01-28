package com.ext.portlet.Activity;

import com.ext.portlet.model.ActivitySubscription;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.model.PlanItem;

public enum SubscriptionType {
    DISCUSSION(DiscussionCategoryGroup.class.getName(), "Discussion"), PLAN(PlanItem.class.getName(), "Proposal"), CONTEST(
            Contest.class.getName(), "Contest");

    private String className;
    private String printName;

    SubscriptionType(String className, String printName) {
        this.className = className;
        this.printName = printName;
    }

    public static SubscriptionType getSubscriptionType(ActivitySubscription subscription) {

        for (SubscriptionType type : SubscriptionType.values()) {
            if (type.className.equals(subscription.getClassName())) {
                return type;
            }

        }

        return null;
    }

    public String getPrintName() {
        return printName;
    }

}
