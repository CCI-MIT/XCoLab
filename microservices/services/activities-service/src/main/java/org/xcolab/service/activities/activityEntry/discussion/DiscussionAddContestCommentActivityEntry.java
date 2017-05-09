package org.xcolab.service.activities.activityEntry.discussion;

public class DiscussionAddContestCommentActivityEntry  extends DiscussionBaseActivityEntry {



    @Override
    public Long getSecondaryType() {
        return DiscussionActivitySubType.DISCUSSION_CONTEST_COMMENT.getSecondaryTypeId();
    }

    @Override
    public String getBody() {
        String template = "%s added a comment to Contest %s";

        return String.format(template, getUserLink(),
                getContestLink());

    }

    @Override
    public String getTitle() {
        return getUserLink() + " added a comment to contest";
    }

    @Override
    public String getName() {
        return "Comment to contest";
    }
}

