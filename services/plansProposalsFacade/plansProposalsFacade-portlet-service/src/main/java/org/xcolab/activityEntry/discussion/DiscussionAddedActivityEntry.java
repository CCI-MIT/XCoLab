package org.xcolab.activityEntry.discussion;

public class DiscussionAddedActivityEntry extends DiscussionBaseActivityEntry {
    public static final String DISCUSSION_ADDED = "%s started a new discussion %s in %s"; // user,
    // thread,
    // categorygroup


    @Override
    public Integer getSecondaryType() {
        return 3;
    }
    @Override
    public String getBody() {

        String template = "%s started a new discussion %s in %s";

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
