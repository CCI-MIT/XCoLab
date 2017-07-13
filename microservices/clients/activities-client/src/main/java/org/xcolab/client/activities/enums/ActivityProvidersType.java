package org.xcolab.client.activities.enums;

public enum ActivityProvidersType {

    DiscussionAddCommentActivityEntry(1, 39202L, 5L),
    DiscussionAddedActivityEntry(2, 39202L, 3L),
    DiscussionAddProposalCommentActivityEntry(3, 39202L ,1L),

    MemberJoinedActivityEntry(4, 10038L,  1L),

    ProposalAttributeRemoveActivityEntry(5,1368503L, 2L),
    ProposalAttributeUpdateActivityEntry(6,1368503L, 1L),
    ProposalCreatedActivityEntry(7,39701L, 0L),
    ProposalMemberAddedActivityEntry(8,1368503L, 6L),
    ProposalMemberRemovedActivityEntry(9,1368503L, 7L),
    ProposalSupporterAddedActivityEntry(10,1368503L, 8L),
    ProposalSupporterRemovedActivityEntry(11,1368503L, 9L),
    ProposalVoteActivityEntry(12,1368503L, 3L),
    ProposalVoteRetractActivityEntry(13,1368503L, 4L),
    ProposalVoteSwitchActivityEntry(14,1368503L, 5L),

    DiscussionAddContestCommentActivityEntry(15, 39202L, 6L);

    Integer activityProvidersType;

    Long activityPrimaryType;
    Long activitySecondaryType;

    ActivityProvidersType(Integer activityProvidersTypez,
    Long activityPrimaryTypez, Long activitySecondaryTypez){
        activityProvidersType = activityProvidersTypez;
        activityPrimaryType = activityPrimaryTypez;
        activitySecondaryType = activitySecondaryTypez;
    }


    public Integer getType(){
        return activityProvidersType;
    }

}
