package org.xcolab.jspTags.discussion;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.comment.pojo.Comment;
import org.xcolab.client.flagging.FlaggingClient;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.entity.utils.members.MemberAuthUtil;
import org.xcolab.util.enums.flagging.TargetType;

import javax.portlet.PortletRequest;

public class DiscussionPermissions {

    public static final String REQUEST_ATTRIBUTE_NAME = "DISCUSSION_PERMISSIONS";

    protected final long memberId;
    protected boolean isLoggedIn;

    public DiscussionPermissions(PortletRequest request) {
        memberId = MemberAuthUtil.getMemberId(request);
        isLoggedIn = memberId > 0;
    }

    public boolean getCanReport() {
        return ConfigurationAttributeKey.FLAGGING_ALLOW_MEMBERS.get()
                || getCanAdminMessages();
    }

    public boolean getCanAdminSpamReports() {
        return getCanAdminMessages();
    }

    public boolean getCanReportMessage(Comment comment) {
        return getCanReport() && comment.getAuthorId() != memberId
                && FlaggingClient.countReports(memberId, TargetType.COMMENT,
                comment.getCommentId(), null, null) == 0;
    }

    public boolean getCanSeeAddCommentButton() {
        return true;
    }

    public boolean getCanAddComment() {
        return isLoggedIn;
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
        return PermissionsClient.canAdminAll(memberId);
    }

    public boolean getMustFilterContent() {
        return ConfigurationAttributeKey.FILTER_PROFANITY.get();
    }

    public boolean getCanSeeShareButtons() {
        return ConfigurationAttributeKey.SHOW_SHARE_BUTTONS.get();
    }
}
