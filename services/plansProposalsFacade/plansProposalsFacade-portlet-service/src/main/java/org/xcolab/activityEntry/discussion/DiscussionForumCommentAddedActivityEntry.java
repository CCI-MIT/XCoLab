package org.xcolab.activityEntry.discussion;

public class DiscussionForumCommentAddedActivityEntry extends DiscussionBaseActivityEntry {




    @Override
    public Integer getSecondaryType() {
        return 2;
    }

    @Override
    public String getBody() {

        String template = "%s added a comment to %s in %s";

        return String.format(template, getUserLink(), getDiscussionMessageLink(),
                getDiscussionCategoryLink());
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }
}
