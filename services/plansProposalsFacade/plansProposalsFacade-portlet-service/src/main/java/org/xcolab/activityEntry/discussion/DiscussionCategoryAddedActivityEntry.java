package org.xcolab.activityEntry.discussion;

public class DiscussionCategoryAddedActivityEntry extends DiscussionBaseActivityEntry {

    @Override
    public Long getSecondaryType() {
        return DiscussionActivitySubType.DISCUSSION_CATEGORY_ADDED.getSecondaryTypeId();
    }
    @Override
    public String getBody() {

        String template = "%s added a category %s to %s";
        return String.format(template, getUserLink(), getDiscussionCategoryLink(),
                getCategoryGroupLink());

    }

    @Override
    public String getTitle() {
        return getUserLink() + " added a category";
    }

    @Override
    public String getName() {
        return "Category added";
    }
}
