package org.xcolab.entity.utils.activity;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.activities.pojo.ActivitySubscription;
import org.xcolab.client.comment.exceptions.CategoryGroupNotFoundException;
import org.xcolab.client.comment.exceptions.CategoryNotFoundException;
import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.comment.pojo.Category;
import org.xcolab.client.comment.pojo.CategoryGroup;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.client.comment.util.CategoryClientUtil;
import org.xcolab.client.comment.util.ThreadClientUtil;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.proposals.ProposalAttributeClientUtil;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.util.IdListUtil;
import org.xcolab.util.enums.activity.ActivityEntryType;

import java.util.List;

public class ActivitySubscriptionNameGenerator {
    private static final Logger _log = LoggerFactory.getLogger(ActivitySubscriptionNameGenerator.class);
    private static final String HYPERLINK = "<a href=\"%s\">%s</a>";

    public static String getName(ActivitySubscription subscription) {
        if (subscription.getClassNameId().equals(ActivityEntryType.PROPOSAL.getPrimaryTypeId())) {
            return getNameForProposalSubscription(subscription);
        }
        if (subscription.getClassNameId().equals(ActivityEntryType.CONTEST.getPrimaryTypeId())) {
            return getNameForContestSubscription(subscription);
        }
        if (subscription.getClassNameId().equals(ActivityEntryType.DISCUSSION.getPrimaryTypeId())) {
            return getNameForDiscussionSubscription(subscription);
        }
        return "";
    }

    private static String getNameForProposalSubscription(ActivitySubscription subscription){
        Long proposalId = subscription.getClassPK();
        try {
            //TODO: figure out which client we need in case it's a shared contest!
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
        Contest contest = ContestClientUtil.getContest(subscription.getClassPK());
        final String contestNameString = contest.getContestType().getContestName();
        return contest.getContestShortName() + " " + StringUtils.uncapitalize(contestNameString);
    }

    private static String getNameForDiscussionSubscription(ActivitySubscription subscription){
        final Long classPK = subscription.getClassPK();
        final String extraData = subscription.getExtraData();

        StringBuilder name = new StringBuilder();

        try {
            CategoryGroup categoryGroup = CategoryClientUtil.getCategoryGroup(classPK);
            name.append(getCategoryGroupHyperlink(categoryGroup));

            if (extraData != null && !extraData.isEmpty()) {
                List<Long> ids = IdListUtil.getIdsFromString(extraData);
                if (!ids.isEmpty()) {
                    long categoryId = ids.get(0);
                    Category category = CategoryClientUtil.getCategory(categoryId);
                    name.append(" &gt; ");
                    name.append(getCategoryHyperlink(category));
                }
                if (ids.size() > 1) {
                    long threadId = ids.get(1);
                    CommentThread thread = ThreadClientUtil.getThread(threadId);

                    name.append(" &gt; ");
                    name.append(getDiscussion(thread));
                }
            }
        } catch (CategoryGroupNotFoundException | ThreadNotFoundException | CategoryNotFoundException e) {
            _log.warn("Could not resolve discussion subscription name", e);
        }

        return name.toString();
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
