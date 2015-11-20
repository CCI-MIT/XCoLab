package org.xcolab.portlets.userprofile.wrappers;

import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.model.DiscussionMessage;
import com.ext.portlet.model.SpamReport;
import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.service.DiscussionMessageLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.util.PortalUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by johannes on 11/19/15.
 */
public class SpamCommentWrapper {

    private final DiscussionCategoryGroup discussionCategoryGroup;
    private final DiscussionMessage message;
    private final List<SpamReport> spamReports;
    private final List<SpamReport> adminReports = new ArrayList<>();

    public SpamCommentWrapper(long messageId, List<SpamReport> spamReports) throws SystemException {
        this.spamReports = spamReports;
        this.message = DiscussionMessageLocalServiceUtil.fetchDiscussionMessage(messageId);
        this.discussionCategoryGroup = DiscussionCategoryGroupLocalServiceUtil.fetchDiscussionCategoryGroup(message.getCategoryGroupId());
        for (SpamReport spamReport : spamReports) {
            if (spamReport.isIsAdminReport()) {
                this.adminReports.add(spamReport);
            }
        }
    }

    public DiscussionCategoryGroup getDiscussionCategoryGroup() {
        return discussionCategoryGroup;
    }

    public List<SpamReport> getAdminReports() {
        return adminReports;
    }

    public int getAdminReportCount() {
        return adminReports.size();
    }

    public DiscussionMessage getMessage() {
        return message;
    }

    public List<SpamReport> getSpamReports() {
        return spamReports;
    }

    public int getOtherReportCount() {
        return spamReports.size() - getAdminReportCount();
    }

    public Date getMessageDate() {
        return message.getCreateDate();
    }

    public DiscussionMessage getDiscussionMessage() {
        return message;
    }

    public String getDeleteCommentUrl() {
        return "/web/guest/member/-/member/spamReports/deleteComment/"+message.getMessageId();
    }
}
