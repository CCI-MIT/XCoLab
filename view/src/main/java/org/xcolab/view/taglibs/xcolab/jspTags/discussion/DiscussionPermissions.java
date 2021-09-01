package org.xcolab.view.taglibs.xcolab.jspTags.discussion;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.comment.pojo.IComment;

import org.xcolab.client.moderation.StaticModerationContext;
import org.xcolab.client.user.StaticUserContext;
import org.xcolab.util.enums.moderation.TargetType;
import org.xcolab.view.auth.MemberAuthUtil;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class DiscussionPermissions {

    private static final int EDIT_GRACE_PERIOD_IN_MINUTES = 15;

    public static final String REQUEST_ATTRIBUTE_NAME = "DISCUSSION_PERMISSIONS";

    protected final long userId;
    private final boolean isGuest;
    protected boolean isLoggedIn;

    public DiscussionPermissions() {
        userId = MemberAuthUtil.getUserId();
        isLoggedIn = userId > 0;
        this.isGuest = StaticUserContext.getPermissionClient().isGuest(userId);
    }

    public boolean getCanReport() {
        return (ConfigurationAttributeKey.FLAGGING_ALLOW_MEMBERS.get() && isLoggedIn && !isGuest)
                || getCanAdminMessages();
    }

    public boolean getCanAdminSpamReports() {
        return getCanAdminMessages();
    }

    public boolean getCanReportMessage(IComment comment) {
        return getCanReport() && comment.getAuthorUserId() != userId &&
                StaticModerationContext.getModerationClient()
                        .countReports(userId, TargetType.COMMENT, comment.getId(), null, null) == 0;
    }

    public boolean getCanSeeAddThreadButton() {
        return !isGuest;
    }

    public boolean getCanSeeAddCommentButton() {
        boolean isReadOnly = ConfigurationAttributeKey.DISCUSSION_COMMENTS_READ_ONLY.get();
        if (isReadOnly) {
            return getCanAdminAll();
        }
        return !isGuest;
    }

    public boolean getCanAddComment() {
        boolean isReadOnly = ConfigurationAttributeKey.DISCUSSION_COMMENTS_READ_ONLY.get();
        return isLoggedIn && !isGuest && !isReadOnly;
    }

    public boolean getCanAdminMessages() {
        return getCanAdminAll();
    }

    public boolean getCanAdminMessage(IComment comment) {
        final boolean canAdminMessage =
                isAuthor(comment) && isRecent(comment, EDIT_GRACE_PERIOD_IN_MINUTES + 5);
        return canAdminMessage || getCanAdminAll();
    }

    public boolean getCanViewAdminMessage(IComment comment) {
        final boolean canViewAdminMessage =
                isAuthor(comment) && isRecent(comment, EDIT_GRACE_PERIOD_IN_MINUTES);
        return canViewAdminMessage || getCanAdminAll();
    }

    private boolean isAuthor(IComment comment) {
        return comment.getAuthorUserId() == userId;
    }

    private boolean isRecent(IComment comment, int recencyInMinutes) {
        Instant now = Instant.now();
        return comment.getCreatedAt().toInstant().plus(recencyInMinutes, ChronoUnit.MINUTES)
                .isAfter(now);
    }

    public boolean getCanCreateCategory() {
        return getCanAdminAll();
    }

    public boolean getCanAdminAll() {
        return StaticUserContext.getPermissionClient().canAdminAll(userId);
    }
}
