package org.xcolab.jspTags.discussion;

import com.ext.portlet.service.SpamReportLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;

import org.xcolab.client.comment.pojo.Comment;
import org.xcolab.client.members.PermissionsClient;

import javax.portlet.PortletRequest;


public class DiscussionPermissions {

    public static final String REQUEST_ATTRIBUTE_NAME = "DISCUSSION_PERMISSIONS";

    protected final User currentUser;

    public DiscussionPermissions(PortletRequest request) {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        currentUser = themeDisplay.getUser();
    }

    public boolean getCanReportSpam() {
        return getCanAdminMessages();
    }

    public boolean getCanAdminSpamReports() {
        return getCanAdminMessages();
    }

    public boolean getCanReportMessage(Comment comment) throws SystemException {
        return getCanReportSpam()
                && comment.getAuthorId() != currentUser.getUserId()
                && !SpamReportLocalServiceUtil.hasReporterUserIdDiscussionMessageId(currentUser.getUserId(), comment.getCommentId());
    }

    public boolean getCanRemoveSpamReport(Comment comment) throws SystemException {
        return getCanAdminSpamReports()
                && comment.getAuthorId() != currentUser.getUserId()
                && SpamReportLocalServiceUtil.hasReporterUserIdDiscussionMessageId(currentUser.getUserId(), comment.getCommentId());
    }

    public boolean getCanSeeAddCommentButton(){
        return true;
    }

    public boolean getCanAddComment() {
        return !currentUser.isDefaultUser();
    }

    public boolean getCanAdminMessages() {
        return getCanAdminAll();
    }

    public boolean getCanAdminMessage(Comment comment) {
        return getCanAdminAll(); // || message.getAuthorId() == currentUser.getUserId()
    }

    public boolean getCanCreateCategory() {
        return getCanAdminAll();
    }

    public boolean getCanAdminAll() {
        return PermissionsClient.canAdminAll(currentUser.getUserId());
    }
}
