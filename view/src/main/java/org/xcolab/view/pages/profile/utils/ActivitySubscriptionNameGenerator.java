package org.xcolab.view.pages.profile.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.activities.pojo.ActivitySubscription;
import org.xcolab.client.comment.ThreadClient;
import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.comment.pojo.Category;
import org.xcolab.client.comment.pojo.CategoryGroup;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.proposals.ProposalAttributeClientUtil;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;

public class ActivitySubscriptionNameGenerator {
    private static final Logger _log = LoggerFactory.getLogger(ActivitySubscriptionNameGenerator.class);
    private static final String HYPERLINK = "<a href=\"%s\">%s</a>";

    public static String getName(ActivitySubscription subscription) {
        switch (subscription.getActivityCategoryEnum()) {
            case PROPOSAL:
                return getNameForProposalSubscription(subscription);
            case CONTEST:
                return getNameForContestSubscription(subscription);
            case DISCUSSION:
                return getNameForDiscussionSubscription(subscription);
            default:
                return "";
        }
    }

    private static String getNameForProposalSubscription(ActivitySubscription subscription){
        Long proposalId = subscription.getCategoryId();
        try {
            Proposal proposal = ProposalClientUtil.getProposal(proposalId);
            Contest contest = ProposalClientUtil.getCurrentContestForProposal(proposalId);
            return "Proposal: " + String.format(HYPERLINK,
                    proposal.getProposalLinkUrl(contest),
                    ProposalAttributeClientUtil
                            .getProposalAttribute(proposalId, ProposalAttributeKeys.NAME, 0L)
                            .getStringValue());
        } catch (ProposalNotFoundException | ContestNotFoundException e) {
            return "";
        }
    }

    private static String getNameForContestSubscription(ActivitySubscription subscription){
        Contest contest = ContestClientUtil.getContest(subscription.getCategoryId());
        final String contestNameString = contest.getContestType().getContestName();
        return contest.getTitleWithEndYear() + " " + StringUtils.uncapitalize(contestNameString);
    }

    private static String getNameForDiscussionSubscription(ActivitySubscription subscription) {
        final Long categoryId = subscription.getCategoryId();
//        final String extraData = subscription.getExtraData();

//        StringBuilder name = new StringBuilder();

        try {
            CommentThread thread = ThreadClient.instance().getThread(categoryId);
            return String.format(HYPERLINK, thread.getLinkUrl(), thread.getTitle());
        } catch (ThreadNotFoundException e) {
            _log.warn("Could not resolve discussion subscription name for subscription {}",
                    subscription.getId() , e);
        }

        return "[No title]";
    }

    private static  String getCategoryHyperlink(Category category) {
        return String.format(HYPERLINK, category.getLinkUrl(), category.getName());
    }

    private static String getDiscussion(CommentThread thread) {
        return String.format(HYPERLINK, thread.getLinkUrl(), thread.getTitle());
    }

    private static String getCategoryGroupHyperlink(CategoryGroup categoryGroup) {
        return String.format(HYPERLINK, categoryGroup.getLinkUrl(), categoryGroup.getDescription());
    }
}
