package org.xcolab.activityEntry;

import org.xcolab.client.proposals.enums.ProposalAttributeKeys;
import com.ext.portlet.model.Contest;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestTypeLocalServiceUtil;
import com.ext.portlet.service.ProposalAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.apache.commons.lang.StringUtils;

import org.xcolab.client.activities.pojo.ActivitySubscription;
import org.xcolab.client.comment.util.CategoryClientUtil;
import org.xcolab.client.comment.exceptions.CategoryGroupNotFoundException;
import org.xcolab.client.comment.exceptions.CategoryNotFoundException;
import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.comment.pojo.Category;
import org.xcolab.client.comment.pojo.CategoryGroup;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.client.comment.util.ThreadClientUtil;
import org.xcolab.util.enums.activity.ActivityEntryType;
import org.xcolab.utils.IdListUtil;

import java.util.List;

public class ActivitySubscriptionNameGenerator {
    private static final Log _log = LogFactoryUtil.getLog(ActivitySubscriptionNameGenerator.class);
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
            return "Proposal: " + String.format(HYPERLINK,
                    ProposalLocalServiceUtil.getProposalLinkUrl(proposalId),
                    ProposalAttributeLocalServiceUtil.getAttribute(proposalId, ProposalAttributeKeys.NAME, 0).getStringValue());
        }
        catch (SystemException | PortalException e) {
            return "";
        }
    }

    private static String getNameForContestSubscription(ActivitySubscription subscription){
        try {
            Contest contest = ContestLocalServiceUtil.getContest(subscription.getClassPK());
            final String contestNameString = ContestTypeLocalServiceUtil.getContestType(contest).getContestName();
            return contest.getContestShortName() + " " + StringUtils.uncapitalize(contestNameString);
        } catch (PortalException | SystemException e) {
            return null;
        }
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
