package org.xcolab.view.taglibs.xcolab.jspTags.discussion;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.comment.pojo.Comment;
import org.xcolab.client.flagging.FlaggingClient;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.util.enums.flagging.TargetType;
import org.xcolab.view.auth.MemberAuthUtil;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import javax.servlet.http.HttpServletRequest;

public class DiscussionPermissions {

    public static final String REQUEST_ATTRIBUTE_NAME = "DISCUSSION_PERMISSIONS";
    public static final int EDIT_GRACE_PERIOD_IN_MINUTES = 15;

    protected final long memberId;
    protected boolean isLoggedIn;

    public DiscussionPermissions(HttpServletRequest request) {
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
        return getCanAdminAll()
                || (isAuthor(comment) && isRecent(comment, EDIT_GRACE_PERIOD_IN_MINUTES + 5));
    }

    public boolean getCanViewAdminMessage(Comment comment) {
        return getCanAdminAll()
                || (isAuthor(comment) && isRecent(comment, EDIT_GRACE_PERIOD_IN_MINUTES));
    }

    private boolean isAuthor(Comment comment) {
        return comment.getAuthorId() == memberId;
    }

    private boolean isRecent(Comment comment, int recencyInMinutes) {
        Instant now = Instant.now();
        return comment.getCreateDate().toInstant()
                .plus(recencyInMinutes, ChronoUnit.MINUTES).isAfter(now);
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
