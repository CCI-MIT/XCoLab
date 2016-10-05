package org.xcolab.service.activities.activityEntry.discussion;

public class DiscussionAddCommentActivityEntry extends DiscussionBaseActivityEntry {

    @Override
    public Long getSecondaryType() {
        return DiscussionActivitySubType.DISCUSSION_ADDED_COMMENT.getSecondaryTypeId();
    }

    @Override
    public String getBody() {
        String template = "%s added a comment to %s";

        return String.format(template, getUserLink(),
                getThreadLink());

    }

    @Override
    public String getTitle() {
        return getUserLink() + " added a comment to thread";
    }

    @Override
    public String getName() {
        return "Comment to thread";
    }
}
