package org.xcolab.view.activityentry;


import org.xcolab.client.activities.enums.ActivityProvidersType;
import org.xcolab.view.activityentry.discussion.DiscussionAddCommentActivityEntry;
import org.xcolab.view.activityentry.discussion.DiscussionAddContestCommentActivityEntry;
import org.xcolab.view.activityentry.discussion.DiscussionAddProposalCommentActivityEntry;
import org.xcolab.view.activityentry.discussion.DiscussionAddedActivityEntry;
import org.xcolab.view.activityentry.member.MemberJoinedActivityEntry;
import org.xcolab.view.activityentry.proposal.ProposalAttributeRemoveActivityEntry;
import org.xcolab.view.activityentry.proposal.ProposalAttributeUpdateActivityEntry;
import org.xcolab.view.activityentry.proposal.ProposalCreatedActivityEntry;
import org.xcolab.view.activityentry.proposal.ProposalMemberAddedActivityEntry;
import org.xcolab.view.activityentry.proposal.ProposalMemberRemovedActivityEntry;
import org.xcolab.view.activityentry.proposal.ProposalSupporterAddedActivityEntry;
import org.xcolab.view.activityentry.proposal.ProposalSupporterRemovedActivityEntry;
import org.xcolab.view.activityentry.proposal.ProposalVoteActivityEntry;
import org.xcolab.view.activityentry.proposal.ProposalVoteRetractActivityEntry;
import org.xcolab.view.activityentry.proposal.ProposalVoteSwitchActivityEntry;
import org.xcolab.view.activityentry.provider.ActivityEntryContentProvider;

public enum ActivityProvidersImpl {
    DiscussionAddCommentActivityEntry(ActivityProvidersType.DiscussionAddCommentActivityEntry, new DiscussionAddCommentActivityEntry()),
    DiscussionAddedActivityEntry(ActivityProvidersType.DiscussionAddedActivityEntry, new DiscussionAddedActivityEntry()),
    DiscussionAddProposalCommentActivityEntry(ActivityProvidersType.DiscussionAddProposalCommentActivityEntry, new DiscussionAddProposalCommentActivityEntry()),
    DiscussionAddContestCommentActivityEntry(ActivityProvidersType.DiscussionAddContestCommentActivityEntry, new DiscussionAddContestCommentActivityEntry()),
    MemberJoinedActivityEntry(ActivityProvidersType.MemberJoinedActivityEntry, new MemberJoinedActivityEntry()),

    ProposalAttributeRemoveActivityEntry(ActivityProvidersType.ProposalAttributeRemoveActivityEntry, new ProposalAttributeRemoveActivityEntry()),
    ProposalAttributeUpdateActivityEntry(ActivityProvidersType.ProposalAttributeUpdateActivityEntry, new ProposalAttributeUpdateActivityEntry()),
    ProposalCreatedActivityEntry(ActivityProvidersType.ProposalCreatedActivityEntry, new ProposalCreatedActivityEntry()),
    ProposalMemberAddedActivityEntry(ActivityProvidersType.ProposalMemberAddedActivityEntry, new ProposalMemberAddedActivityEntry()),
    ProposalMemberRemovedActivityEntry(ActivityProvidersType.ProposalMemberRemovedActivityEntry, new ProposalMemberRemovedActivityEntry()),
    ProposalSupporterAddedActivityEntry(ActivityProvidersType.ProposalSupporterAddedActivityEntry, new ProposalSupporterAddedActivityEntry()),
    ProposalSupporterRemovedActivityEntry(ActivityProvidersType.ProposalSupporterRemovedActivityEntry, new ProposalSupporterRemovedActivityEntry()),
    ProposalVoteActivityEntry(ActivityProvidersType.ProposalVoteActivityEntry, new ProposalVoteActivityEntry()),
    ProposalVoteRetractActivityEntry(ActivityProvidersType.ProposalVoteRetractActivityEntry, new ProposalVoteRetractActivityEntry()),
    ProposalVoteSwitchActivityEntry(ActivityProvidersType.ProposalVoteSwitchActivityEntry, new ProposalVoteSwitchActivityEntry());

    ActivityProvidersType activityProvidersType;


    ActivityEntryContentProvider activityEntryContentProvider;


    ActivityProvidersImpl(ActivityProvidersType activityProvidersTypez, ActivityEntryContentProvider
            activityEntryContentProviderz){
        activityProvidersType = activityProvidersTypez;
        activityEntryContentProvider = activityEntryContentProviderz;

    }

    Integer getType(){
        return activityProvidersType.getType();
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
    public static ActivityEntryContentProvider getActivityEntryContentProviderByPrimarySecondaryType(Long primaryType, Long secondaryType){
        for (ActivityProvidersImpl dir : ActivityProvidersImpl.values()) {
            // do what you want
            if(dir.getActivityEntryContentProvider().getPrimaryType().longValue() == primaryType &&
                    dir.getActivityEntryContentProvider().getSecondaryType().longValue() == secondaryType){
                return dir.getActivityEntryContentProvider();
            }
        }
        return null;
    }
}
