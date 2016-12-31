package org.xcolab.portlets.userprofile;

import org.xcolab.client.activities.pojo.ActivitySubscription;
import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.ContestType;
import org.xcolab.util.enums.activity.ActivityEntryType;

public enum SubscriptionType {
    DISCUSSION(ActivityEntryType.DISCUSSION.getPrimaryTypeId()),
    PROPOSAL(ActivityEntryType.PROPOSAL.getPrimaryTypeId()),
    CONTEST(ActivityEntryType.CONTEST.getPrimaryTypeId());

    private final Long className;

    SubscriptionType(Long className) {
        this.className = className;
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
        if (this == SubscriptionType.DISCUSSION) {
            return "Discussion";
        } else {
            final long contestTypeId = ConfigurationAttributeKey
                    .DEFAULT_CONTEST_TYPE_ID.get();
            ContestType contestType = ContestClientUtil.getContestType(contestTypeId);

            if (this == SubscriptionType.PROPOSAL) {
                return contestType != null ? contestType.getProposalName() : "Proposal";
            }
            if (this == SubscriptionType.CONTEST) {
                return contestType != null ? contestType.getContestName() : "Contest";
            }
            return "Other";
        }
    }
}
