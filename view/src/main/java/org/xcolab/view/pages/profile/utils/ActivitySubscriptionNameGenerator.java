package org.xcolab.view.pages.profile.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.activity.pojo.IActivitySubscription;
import org.xcolab.client.comment.StaticCommentContext;
import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.comment.pojo.ICategory;
import org.xcolab.client.comment.pojo.ICategoryGroup;
import org.xcolab.client.comment.pojo.IThread;
import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.client.contest.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.contest.proposals.exceptions.ProposalNotFoundException;

public class ActivitySubscriptionNameGenerator {

    private static final Logger _log = LoggerFactory.getLogger(ActivitySubscriptionNameGenerator.class);
    private static final String HYPERLINK = "<a href=\"%s\">%s</a>";

    public static String getName(IActivitySubscription subscription) {
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

    private static String getNameForProposalSubscription(IActivitySubscription subscription){
        Long proposalId = subscription.getCategoryId();
        try {
            ProposalWrapper proposal = StaticProposalContext.getProposalClient()
                    .getProposal(proposalId);
            ContestWrapper contest = StaticProposalContext.getProposalClient()
                    .getCurrentContestForProposal(proposalId);
            return "Proposal: " + String.format(HYPERLINK,
                    proposal.getProposalLinkUrl(contest),
                    StaticProposalContext.getProposalAttributeClient()
                            .getProposalAttribute(proposalId, ProposalAttributeKeys.NAME, 0L)
                            .getStringValue());
        } catch (ProposalNotFoundException | ContestNotFoundException e) {
            return "";
        }
    }

    private static String getNameForContestSubscription(IActivitySubscription subscription){
        ContestWrapper contest = StaticContestContext.getContestClient()
                .getContest(subscription.getCategoryId());
        final String contestNameString = contest.getContestType().getContestName();
        return contest.getTitleWithEndYear() + " " + StringUtils.uncapitalize(contestNameString);
    }

    private static String getNameForDiscussionSubscription(IActivitySubscription subscription) {
        final Long categoryId = subscription.getCategoryId();
//        final String extraData = subscription.getExtraData();

//        StringBuilder name = new StringBuilder();

        try {
            IThread thread = StaticCommentContext.getThreadClient().getThread(categoryId);
            return String.format(HYPERLINK, thread.getLinkUrl(), thread.getTitle());
        } catch (ThreadNotFoundException e) {
            _log.warn("Could not resolve discussion subscription name for subscription {}",
                    subscription.getId() , e);
        }

        return "[No title]";
    }

    private static  String getCategoryHyperlink(ICategory category) {
        return String.format(HYPERLINK, category.getLinkUrl(), category.getName());
    }

    private static String getDiscussion(IThread thread) {
        return String.format(HYPERLINK, thread.getLinkUrl(), thread.getTitle());
    }

    private static String getCategoryGroupHyperlink(ICategoryGroup categoryGroup) {
        return String.format(HYPERLINK, categoryGroup.getLinkUrl(), categoryGroup.getDescription());
    }
}
