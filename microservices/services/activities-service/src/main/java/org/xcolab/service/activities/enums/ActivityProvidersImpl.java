package org.xcolab.service.activities.enums;


import org.xcolab.service.activities.activityentry.discussion.DiscussionAddCommentActivityEntry;
import org.xcolab.service.activities.activityentry.discussion
        .DiscussionAddContestCommentActivityEntry;
import org.xcolab.service.activities.activityentry.discussion
        .DiscussionAddProposalCommentActivityEntry;
import org.xcolab.service.activities.activityentry.discussion.DiscussionAddedActivityEntry;
import org.xcolab.service.activities.activityentry.member.MemberJoinedActivityEntry;
import org.xcolab.service.activities.activityentry.proposal.ProposalAttributeRemoveActivityEntry;
import org.xcolab.service.activities.activityentry.proposal.ProposalAttributeUpdateActivityEntry;
import org.xcolab.service.activities.activityentry.proposal.ProposalCreatedActivityEntry;
import org.xcolab.service.activities.activityentry.proposal.ProposalMemberAddedActivityEntry;
import org.xcolab.service.activities.activityentry.proposal.ProposalMemberRemovedActivityEntry;
import org.xcolab.service.activities.activityentry.proposal.ProposalSupporterAddedActivityEntry;
import org.xcolab.service.activities.activityentry.proposal.ProposalSupporterRemovedActivityEntry;
import org.xcolab.service.activities.activityentry.proposal.ProposalVoteActivityEntry;
import org.xcolab.service.activities.activityentry.proposal.ProposalVoteRetractActivityEntry;
import org.xcolab.service.activities.activityentry.proposal.ProposalVoteSwitchActivityEntry;
import org.xcolab.service.activities.activityentry.provider.ActivityEntryContentProvider;

public enum ActivityProvidersImpl {
    DiscussionAddCommentActivityEntry(1, new DiscussionAddCommentActivityEntry()),
    DiscussionAddedActivityEntry(2, new DiscussionAddedActivityEntry()),
    DiscussionAddProposalCommentActivityEntry(3, new DiscussionAddProposalCommentActivityEntry()),
    DiscussionAddContestCommentActivityEntry(15, new DiscussionAddContestCommentActivityEntry()),
    MemberJoinedActivityEntry(4, new MemberJoinedActivityEntry()),

    ProposalAttributeRemoveActivityEntry(5, new ProposalAttributeRemoveActivityEntry()),
    ProposalAttributeUpdateActivityEntry(6, new ProposalAttributeUpdateActivityEntry()),
    ProposalCreatedActivityEntry(7, new ProposalCreatedActivityEntry()),
    ProposalMemberAddedActivityEntry(8, new ProposalMemberAddedActivityEntry()),
    ProposalMemberRemovedActivityEntry(9, new ProposalMemberRemovedActivityEntry()),
    ProposalSupporterAddedActivityEntry(10, new ProposalSupporterAddedActivityEntry()),
    ProposalSupporterRemovedActivityEntry(11, new ProposalSupporterRemovedActivityEntry()),
    ProposalVoteActivityEntry(12, new ProposalVoteActivityEntry()),
    ProposalVoteRetractActivityEntry(13, new ProposalVoteRetractActivityEntry()),
    ProposalVoteSwitchActivityEntry(14, new ProposalVoteSwitchActivityEntry());




    Integer activityProvidersType;

    ActivityEntryContentProvider activityEntryContentProvider;


    


    ActivityProvidersImpl(Integer activityProvidersTypez, ActivityEntryContentProvider activityEntryContentProviderz){
        activityProvidersType = activityProvidersTypez;
        activityEntryContentProvider = activityEntryContentProviderz;
    }

    Integer getType(){
        return activityProvidersType;
    }

    ActivityEntryContentProvider getActivityEntryContentProvider(){
        return activityEntryContentProvider;
    }
    public static ActivityEntryContentProvider getActivityEntryContentProviderByType(Integer id){
        for (ActivityProvidersImpl dir : ActivityProvidersImpl.values()) {
            // do what you want
            if(dir.getType() == id){
                return dir.getActivityEntryContentProvider();
            }
        }
        return null;
    }
}
