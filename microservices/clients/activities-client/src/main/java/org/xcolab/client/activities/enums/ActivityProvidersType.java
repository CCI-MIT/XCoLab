package org.xcolab.client.activities.enums;

public enum ActivityProvidersType {

    DiscussionAddCommentActivityEntry(1),
    DiscussionAddedActivityEntry(2),
    DiscussionAddProposalCommentActivityEntry(3),

    MemberJoinedActivityEntry(4),

    ProposalAttributeRemoveActivityEntry(5),
    ProposalAttributeUpdateActivityEntry(6),
    ProposalCreatedActivityEntry(7),
    ProposalMemberAddedActivityEntry(8),
    ProposalMemberRemovedActivityEntry(9),
    ProposalSupporterAddedActivityEntry(10),
    ProposalSupporterRemovedActivityEntry(11),
    ProposalVoteActivityEntry(12),
    ProposalVoteRetractActivityEntry(13),
    ProposalVoteSwitchActivityEntry(14);



    Integer activityProvidersType;

    ActivityProvidersType(Integer activityProvidersTypez){
        activityProvidersType = activityProvidersTypez;
    }


    public Integer getType(){
        return activityProvidersType;
    }

}
