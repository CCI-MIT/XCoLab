package org.xcolab.jspTags.discussion;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.comment.pojo.Comment;
import org.xcolab.client.flagging.FlaggingClient;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.util.enums.flagging.TargetType;

import javax.portlet.PortletRequest;

public class DiscussionPermissions {

    public static final String REQUEST_ATTRIBUTE_NAME = "DISCUSSION_PERMISSIONS";

    protected final User currentUser;

    public DiscussionPermissions(PortletRequest request) {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        currentUser = themeDisplay.getUser();
    }

    public boolean getCanReport() {
        return ConfigurationAttributeKey.FLAGGING_ALLOW_MEMBERS.getBooleanValue()
                || getCanAdminMessages();
    }

    public boolean getCanAdminSpamReports() {
        return getCanAdminMessages();
    }

    public boolean getCanReportMessage(Comment comment) {
        return getCanReport() && comment.getAuthorId() != currentUser.getUserId()
                && FlaggingClient.countReports(currentUser.getUserId(), TargetType.COMMENT,
                comment.getCommentId(), null, null) == 0;
    }

    public boolean getCanSeeAddCommentButton() {
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

    public boolean getMustFilterContent() {
        return ConfigurationAttributeKey.FILTER_PROFANITY.getBooleanValue();
    }
}
