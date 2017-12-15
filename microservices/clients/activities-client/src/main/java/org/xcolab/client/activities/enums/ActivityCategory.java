package org.xcolab.client.activities.enums;

import java.util.Arrays;

public enum ActivityCategory {

    MEMBER(MemberActivityType.values()),
    PROPOSAL(ProposalActivityType.values()),
    CONTEST(ContestActivityType.values()),
    DISCUSSION_THREAD(DiscussionThreadActivityType.values()),
    DISCUSSION_CATEGORY(null),
    ;

    private final ActivityType[] activityTypes;

    ActivityCategory(ActivityType[] activityTypes) {
        this.activityTypes = activityTypes;
    }

    public ActivityType getActivityType(String activityType) {
        if (activityTypes == null) {
            throw new UnsupportedOperationException(name() + " does not have activity types");
        }
        return Arrays.stream(activityTypes)
                .filter(s -> s.name().equalsIgnoreCase(activityType))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(
                        "No ActivityType " + name() + "." + activityType));
    }
}
