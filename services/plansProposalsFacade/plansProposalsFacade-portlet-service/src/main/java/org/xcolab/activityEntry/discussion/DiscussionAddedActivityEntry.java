package org.xcolab.activityEntry.discussion;

public class DiscussionAddedActivityEntry extends DiscussionBaseActivityEntry {
    public static final String DISCUSSION_ADDED = "%s started a new discussion %s in %s"; // user,
    // thread,
    // categorygroup


    @Override
    public Long getSecondaryType() {
        return DiscussionActivitySubType.DISCUSSION_ADDED.getSecondaryTypeId();
    }
    @Override
    public String getBody() {

        String template = "%s started a new discussion %s in %s";

        return String.format(template, getUserLink(), getDiscussionMessageLink(),
                getDiscussionCategoryLink());

    }

    @Override
    public String getTitle() {
        return getTitle() + " started a new discussion";
    }

    @Override
    public String getName() {
        return "Added new discussion";
    }


}
