package org.xcolab.view.pages.profile;

import org.xcolab.client.activity.pojo.IActivitySubscription;
import org.xcolab.client.admin.StaticAdminContext;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.util.activities.enums.ActivityCategory;

public enum SubscriptionType {
    DISCUSSION(ActivityCategory.DISCUSSION),
    PROPOSAL(ActivityCategory.PROPOSAL),
    CONTEST(ActivityCategory.CONTEST);

    private final ActivityCategory activityCategory;

    SubscriptionType(ActivityCategory activityCategory) {
        this.activityCategory = activityCategory;
    }

    public static SubscriptionType getSubscriptionType(IActivitySubscription subscription) {
        for (SubscriptionType type : SubscriptionType.values()) {
            if (type.activityCategory == subscription.getActivityCategoryEnum()) {
                return type;
            }
        }
        return null;
    }

    public String getPrintName() {
        if (this == SubscriptionType.DISCUSSION) {
            return "Discussion";
        } else {
            final long contestTypeId = ConfigurationAttributeKey
                    .DEFAULT_CONTEST_TYPE_ID.get();
            ContestType contestType =
                    StaticAdminContext.getContestTypeClient().getContestType(contestTypeId);

            if (this == SubscriptionType.PROPOSAL) {
                return contestType.getProposalName();
            }
            if (this == SubscriptionType.CONTEST) {
                return contestType.getContestName();
            }
            return "Other";
        }
    }
}
