package org.xcolab.service.activities.enums;


import org.xcolab.service.activities.activityEntry.discussion.DiscussionAddCommentActivityEntry;
import org.xcolab.service.activities.activityEntry.discussion.DiscussionAddProposalCommentActivityEntry;
import org.xcolab.service.activities.activityEntry.discussion.DiscussionAddedActivityEntry;
import org.xcolab.service.activities.activityEntry.member.MemberJoinedActivityEntry;
import org.xcolab.service.activities.activityEntry.proposal.ProposalAttributeRemoveActivityEntry;
import org.xcolab.service.activities.activityEntry.proposal.ProposalAttributeUpdateActivityEntry;
import org.xcolab.service.activities.activityEntry.proposal.ProposalCreatedActivityEntry;
import org.xcolab.service.activities.activityEntry.proposal.ProposalMemberAddedActivityEntry;
import org.xcolab.service.activities.activityEntry.proposal.ProposalMemberRemovedActivityEntry;
import org.xcolab.service.activities.activityEntry.proposal.ProposalSupporterAddedActivityEntry;
import org.xcolab.service.activities.activityEntry.proposal.ProposalSupporterRemovedActivityEntry;
import org.xcolab.service.activities.activityEntry.proposal.ProposalVoteActivityEntry;
import org.xcolab.service.activities.activityEntry.proposal.ProposalVoteRetractActivityEntry;
import org.xcolab.service.activities.activityEntry.proposal.ProposalVoteSwitchActivityEntry;
import org.xcolab.service.activities.activityEntry.provider.ActivityEntryContentProvider;

public enum ActivityProvidersImpl {
    DiscussionAddCommentActivityEntry(1, new DiscussionAddCommentActivityEntry()),
    DiscussionAddedActivityEntry(2, new DiscussionAddedActivityEntry()),
    DiscussionAddProposalCommentActivityEntry(3, new DiscussionAddProposalCommentActivityEntry()),

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
