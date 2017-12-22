package org.xcolab.util.activities.enums;

import java.util.Arrays;

public enum ActivityCategory {

    MEMBER(MemberActivityType.values()),
    PROPOSAL(ProposalActivityType.values()),
    CONTEST(ContestActivityType.values()),
    DISCUSSION(DiscussionThreadActivityType.values()),
    DISCUSSION_CATEGORY(null),

    //TODO COLAB-2486: delete once fixed
    UNKNOWN(null),
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
