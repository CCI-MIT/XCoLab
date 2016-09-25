package org.xcolab.activityEntry.discussion;

import org.apache.commons.lang3.StringEscapeUtils;

import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.community.CommunityUtil;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.xcolab.client.activities.contentProviders.ActivityEntryContentProvider;
import org.xcolab.client.activities.pojo.ActivityEntry;
import org.xcolab.client.comment.exceptions.CategoryNotFoundException;
import org.xcolab.client.comment.exceptions.CommentNotFoundException;
import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.comment.pojo.Category;
import org.xcolab.client.comment.pojo.CategoryGroup;
import org.xcolab.client.comment.pojo.Comment;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.client.comment.util.CategoryClientUtil;
import org.xcolab.client.comment.util.CommentClientUtil;
import org.xcolab.client.comment.util.ThreadClientUtil;
import org.xcolab.helpers.ProposalAttributeHelper;
import org.xcolab.util.enums.activity.ActivityEntryType;
import org.xcolab.util.exceptions.DatabaseAccessException;

public abstract class DiscussionBaseActivityEntry implements ActivityEntryContentProvider {

    private static final Log _log = LogFactoryUtil.getLog(DiscussionBaseActivityEntry.class);

    protected ActivityEntry activityEntry;

    protected Comment comment;

    protected CommentThread thread;

    protected Category category;

    protected CategoryGroup categoryGroup;

    protected Proposal proposal;

    protected Contest contest;

    protected String proposalName;

    public static final String HYPERLINK_FORMAT = "<a href=\"%s\">%s</a>";

    @Override
    public void setActivityEntry(ActivityEntry activityEntry) {
        this.activityEntry = activityEntry;
        if (!this.getSecondaryType().equals(DiscussionActivitySubType.DISCUSSION_PROPOSAL_COMMENT.getSecondaryTypeId())) {

            try {
                category = CategoryClientUtil.getCategory(activityEntry.getClassPrimaryKey());
                comment = CommentClientUtil
                        .getComment(Long.parseLong(activityEntry.getExtraData()));
                thread = ThreadClientUtil.getThread(comment.getThreadId());
                category = CategoryClientUtil.getCategory(thread.getCategoryId());
            } catch (CategoryNotFoundException | ThreadNotFoundException | CommentNotFoundException e) {
                _log.warn("Could not initialize discussion from " + activityEntry);
            }
        } else {
            try {
                thread = ThreadClientUtil.getThread(activityEntry.getClassPrimaryKey());
                final Long proposalId = ThreadClientUtil.getProposalIdForThread(thread.getThreadId());

                if (proposalId != null) {

                    proposal = ProposalLocalServiceUtil.getProposal(proposalId);
                    contest = Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal(proposal.getProposalId());

                    ProposalAttributeHelper proposalAttributeHelper = new ProposalAttributeHelper(proposal);
                    proposalName = proposalAttributeHelper.getAttributeValueString(ProposalAttributeKeys.NAME, "");
                }
            } catch (SystemException e) {
                throw new DatabaseAccessException(e);
            } catch (PortalException | ThreadNotFoundException e) {
                _log.warn("Could not initialize proposal comment from " + activityEntry);
            }
        }
    }

    protected String getProposalLink() {
        String url = "";
        if (proposal!= null) {
            url = "<a href='" + ProposalLocalServiceUtil.getProposalLinkUrl(contest, proposal)+ "/tab/COMMENTS" + "'>" + proposalName + "</a>";
        }
        return url;
    }

    protected String getThreadLink() {
        return String.format(
                HYPERLINK_FORMAT,
                StringEscapeUtils.escapeHtml4(thread.getLinkUrl()), thread.getTitle());
    }

    protected String getCategoryLink() {
        return String.format(
                HYPERLINK_FORMAT,
                StringEscapeUtils.escapeHtml4(category.getLinkUrl()), category.getName());
    }

    protected String getUserLink() {
        try {
            return CommunityUtil.generateUserURL(this.activityEntry.getMemberId());
        } catch (PortalException | SystemException e) {
            _log.info(e.getMessage());
        }
        return "<user removed>";
    }

    @Override
    public Long getPrimaryType() {
        return ActivityEntryType.DISCUSSION.getPrimaryTypeId();
    }


    public enum DiscussionActivitySubType {
        DISCUSSION_PROPOSAL_COMMENT(1L),
        DISCUSSION_CATEGORY_ADDED(2L),
        DISCUSSION_ADDED(3L),
        DISCUSSION_FORUM_COMMENT(4L),
        DISCUSSION_ADDED_COMMENT(5L);

        private final Long secondaryTypeId;
        DiscussionActivitySubType(Long type) {
            this.secondaryTypeId = type;
        }

        public Long getSecondaryTypeId(){
            return this.secondaryTypeId;
        }
    }
}
