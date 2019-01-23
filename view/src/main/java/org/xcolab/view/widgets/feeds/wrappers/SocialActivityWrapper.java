package org.xcolab.view.widgets.feeds.wrappers;

import org.xcolab.client.activity.pojo.IActivityEntry;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.commons.time.DurationFormatter;
import org.xcolab.util.activities.enums.ContestActivityType;
import org.xcolab.util.activities.enums.ProposalActivityType;

import java.io.Serializable;
import java.util.Date;

import static org.xcolab.util.activities.enums.ActivityCategory.PROPOSAL;

public class SocialActivityWrapper implements Serializable {

    private static final long serialVersionUID = 1L;

    private final IActivityEntry activity;
    private final String body;

    public SocialActivityWrapper(IActivityEntry activity, String body) {
        this.activity = activity;
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public Date getCreatedAt() {
        return new Date(activity.getCreatedAt().getTime());
    }

    public String getRelativeDate() {
        return DurationFormatter.forRequestLocale().format(activity.getCreatedAt());
    }

    public boolean getIsHiddenActivityType() {
        if (ConfigurationAttributeKey.VOTING_SECRET_FLAG.get() &&
                activity.getActivityCategoryEnum().equals(PROPOSAL)) {
            switch ((ProposalActivityType) activity.getActivityTypeEnum()) {
                case VOTE_ADDED:
                case VOTE_SWITCHED:
                case VOTE_RETRACTED:
                case SUPPORT_ADDED:
                case SUPPORT_REMOVED:
                    return true;
                case UPDATED:
                case MEMBER_ADDED:
                case MEMBER_REMOVED:
                case COMMENT_ADDED:
                    return false;
                default: throw new UnknownActivityTypeException(activity);
            }
        } else {
            return false;
        }
    }

    public Icon getIcon() {
        switch (activity.getActivityCategoryEnum()) {
            case MEMBER:
                return Icon.NEW_USER;
            case DISCUSSION:
                return Icon.COMMENT;
            case CONTEST:
                switch ((ContestActivityType) activity.getActivityTypeEnum()) {
                    case PROPOSAL_CREATED:
                        return Icon.NEW_PROPOSAL;
                    case COMMENT_ADDED:
                        return Icon.COMMENT;
                    default: throw new UnknownActivityTypeException(activity);
                }
            case PROPOSAL:
                switch ((ProposalActivityType) activity.getActivityTypeEnum()) {
                    case UPDATED:
                    case MEMBER_ADDED:
                    case MEMBER_REMOVED:
                        return Icon.EDIT;
                    case VOTE_ADDED:
                    case VOTE_SWITCHED:
                    case VOTE_RETRACTED:
                    case SUPPORT_ADDED:
                    case SUPPORT_REMOVED:
                        return Icon.THUMBS_UP;
                    case COMMENT_ADDED:
                        return Icon.COMMENT;
                    default: throw new UnknownActivityTypeException(activity);
                }
            default: throw new UnknownActivityTypeException(activity);
        }
    }

    public enum Icon {
        COMMENT("Comment", "/images/icons/activity/comment.png"),
        EDIT("Edit", "/images/icons/activity/edit.png"),
        THUMBS_UP("Thumbs up", "/images/icons/activity/thumbs-up.png"),
        NEW_PROPOSAL("New proposal", "/images/icons/activity/new-proposal.png"),
        NEW_USER("New member", "/images/icons/activity/new-user.png");

        private final String altText;
        private final String path;

        Icon(String altText, String path) {
            this.altText = altText;
            this.path = path;
        }

        public String getAltText() {
            return altText;
        }

        public String getPath() {
            return path;
        }
    }

    public static class UnknownActivityTypeException extends RuntimeException {
        public UnknownActivityTypeException(IActivityEntry activity) {
            super("Unknown activity type: " + activity.getActivityCategory() + "."
                    + activity.getActivityType());
        }
    }
}
