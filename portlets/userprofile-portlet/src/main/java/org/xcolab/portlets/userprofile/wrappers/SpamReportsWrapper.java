package org.xcolab.portlets.userprofile.wrappers;

import com.ext.portlet.model.SpamReport;
import com.ext.portlet.service.SpamReportLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by johannes on 11/19/15.
 */
public class SpamReportsWrapper {

    private final Map<Long, List<SpamCommentWrapper>> spamCommentsBySpamUserId = new HashMap<>();
    private final List<SpamCommentWrapper> spamComments = new ArrayList<>();

    public SpamReportsWrapper() throws SystemException {
        final List<SpamReport> spamReports = SpamReportLocalServiceUtil.getSpamReports(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        for (Map.Entry<Long, List<SpamReport>> userEntry : groupSpamReportsBySpamUserId(spamReports).entrySet()) {
            final Long userId = userEntry.getKey();
            final List<SpamReport> userSpamReports = userEntry.getValue();
            spamComments.add(new SpamCommentWrapper(userId, userSpamReports));

            List<SpamCommentWrapper> localSpamComments = new ArrayList<>();
            for (Map.Entry<Long, List<SpamReport>> messageEntry : groupSpamReportsByMessage(userSpamReports).entrySet()) {
                final List<SpamReport> messageSpamReports = messageEntry.getValue();
                localSpamComments.add(new SpamCommentWrapper(userId, messageSpamReports));
            }
            spamCommentsBySpamUserId.put(userId, localSpamComments);
        }
    }

    public SpamReportsWrapper(long userId) throws SystemException {
        for (Map.Entry<Long, List<SpamReport>> entry : groupSpamReportsByMessage(SpamReportLocalServiceUtil.getBySpamUserId(userId)).entrySet()) {
            spamComments.add(new SpamCommentWrapper(entry.getKey(), entry.getValue()));
        }
        spamCommentsBySpamUserId.put(userId, spamComments);
    }

    private Map<Long, List<SpamReport>> groupSpamReportsBySpamUserId(List<SpamReport> spamReports) throws SystemException {
        Map<Long, List<SpamReport>> spamReportsByMessage = new HashMap<>();
        for (SpamReport spamReport : spamReports) {
            final long userId = spamReport.getSpamUserId();
            List<SpamReport> messageSpamReports;
            if (spamReportsByMessage.containsKey(userId)) {
                messageSpamReports = spamReportsByMessage.get(userId);
            } else {
                messageSpamReports = new ArrayList<>();
                spamReportsByMessage.put(userId, messageSpamReports);
            }
            messageSpamReports.add(spamReport);
        }
        return spamReportsByMessage;
    }

    private Map<Long, List<SpamReport>> groupSpamReportsByMessage(List<SpamReport> spamReports) throws SystemException {
        Map<Long, List<SpamReport>> spamReportsByMessage = new HashMap<>();
        for (SpamReport spamReport : spamReports) {
            final long messageId = spamReport.getDiscussionMessageId();
            List<SpamReport> messageSpamReports;
            if (spamReportsByMessage.containsKey(messageId)) {
                messageSpamReports = spamReportsByMessage.get(messageId);
            } else {
                messageSpamReports = new ArrayList<>();
                spamReportsByMessage.put(messageId, messageSpamReports);
            }
            messageSpamReports.add(spamReport);
        }
        return spamReportsByMessage;
    }

    public List<SpamCommentWrapper> getSpamComments() {
        return spamComments;
    }

    public Map<Long, List<SpamCommentWrapper>> getSpamCommentsBySpamUserId() {
        return spamCommentsBySpamUserId;
    }

    public String getDeleteUserUrl() {
        return "/web/guest/member/-/member/spamReports/deleteUser/"+spamCommentsBySpamUserId.keySet().iterator().next();
    }
}
