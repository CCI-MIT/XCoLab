package org.xcolab.util.activities.enums;

public enum ProposalActivityType implements ActivityType {

    COMMENT_ADDED,
    UPDATED,
    MEMBER_ADDED,
    MEMBER_REMOVED,
    SUPPORT_ADDED,
    SUPPORT_REMOVED,
    VOTE_ADDED,
    VOTE_RETRACTED,
    VOTE_SWITCHED,
    ;

    @Override
    public ActivityCategory getCategory() {
        return ActivityCategory.PROPOSAL;
    }
}
