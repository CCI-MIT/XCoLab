package org.xcolab.activityEntry.discussion;

public class DiscussionCategoryAddedActivityEntry extends DiscussionBaseActivityEntry {

    @Override
    public Integer getSecondaryType() {
        return 2;
    }
    @Override
    public String getBody() {

        String template = "%s added a category %s to %s";
        return String.format(template, getUserLink(), getDiscussionCategoryLink(),
                getCategoryGroupLink());

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
