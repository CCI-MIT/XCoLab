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

    private static final int EDIT_GRACE_PERIOD_IN_MINUTES = 15;

    public static final String REQUEST_ATTRIBUTE_NAME = "DISCUSSION_PERMISSIONS";

    protected final long userId;
    private final boolean isGuest;
    protected boolean isLoggedIn;

    public DiscussionPermissions(HttpServletRequest request) {
        userId = MemberAuthUtil.getuserId(request);
        isLoggedIn = userId > 0;
        this.isGuest = PermissionsClient.isGuest(userId);
    }

    public boolean getCanReport() {
        return (ConfigurationAttributeKey.FLAGGING_ALLOW_MEMBERS.get() && isLoggedIn && !isGuest)
                || getCanAdminMessages();
    }

    public boolean getCanAdminSpamReports() {
        return getCanAdminMessages();
    }

    public boolean getCanReportMessage(Comment comment) {
        return getCanReport() && comment.getAuthorUserId() != userId && FlaggingClient
                .countReports(userId, TargetType.COMMENT, comment.getId(), null, null)
                == 0;
    }

    public boolean getCanSeeAddThreadButton() {
        return !isGuest;
    }

    public boolean getCanSeeAddCommentButton() {
        return !isGuest;
    }

    public boolean getCanAddComment() {
        return isLoggedIn && !isGuest;
    }

    public boolean getCanAdminMessages() {
        return getCanAdminAll();
    }

    public boolean getCanAdminMessage(Comment comment) {
        final boolean canAdminMessage =
                isAuthor(comment) && isRecent(comment, EDIT_GRACE_PERIOD_IN_MINUTES + 5);
        return canAdminMessage || getCanAdminAll();
    }

    public boolean getCanViewAdminMessage(Comment comment) {
        final boolean canViewAdminMessage =
                isAuthor(comment) && isRecent(comment, EDIT_GRACE_PERIOD_IN_MINUTES);
        return canViewAdminMessage || getCanAdminAll();
    }

    private boolean isAuthor(Comment comment) {
        return comment.getAuthorUserId() == userId;
    }

    private boolean isRecent(Comment comment, int recencyInMinutes) {
        Instant now = Instant.now();
        return comment.getCreatedAt().toInstant().plus(recencyInMinutes, ChronoUnit.MINUTES)
                .isAfter(now);
    }

    public boolean getCanCreateCategory() {
        return getCanAdminAll();
    }

    public boolean getCanAdminAll() {
        return PermissionsClient.canAdminAll(userId);
    }
}
