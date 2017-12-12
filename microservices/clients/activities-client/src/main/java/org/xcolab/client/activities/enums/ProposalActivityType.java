package org.xcolab.client.activities.enums;

public enum ProposalActivityType implements ActivityType {

    COMMENT_ADDED, //DiscussionAddProposalCommentActivityEntry(3, 39202L ,1L),
    UPDATED, //ProposalAttributeUpdateActivityEntry(6,1368503L, 1L),
    CREATED, //ProposalCreatedActivityEntry(7,39701L, 0L),
    MEMBER_ADDED, //ProposalMemberAddedActivityEntry(8,1368503L, 6L),
    MEMBER_REMOVED, //ProposalMemberRemovedActivityEntry(9,1368503L, 7L),
    SUPPORT_ADDED, //ProposalSupporterAddedActivityEntry(10,1368503L, 8L),
    SUPPORT_REMOVED, //ProposalSupporterRemovedActivityEntry(11,1368503L, 9L),
    VOTE_ADDED, //ProposalVoteActivityEntry(12,1368503L, 3L),
    VOTE_RETRACTED, //ProposalVoteRetractActivityEntry(13,1368503L, 4L),
    VOTE_SWITCHED, //ProposalVoteSwitchActivityEntry(14,1368503L, 5L),

    REMOVED, //ProposalAttributeRemoveActivityEntry(5,1368503L, 2L),
    ;

    @Override
    public ActivityCategory getCategory() {
        return ActivityCategory.PROPOSAL;
    }
}
