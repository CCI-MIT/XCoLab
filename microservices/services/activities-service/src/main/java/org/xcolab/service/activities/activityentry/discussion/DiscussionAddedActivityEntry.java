package org.xcolab.service.activities.activityentry.discussion;

public class DiscussionAddedActivityEntry extends DiscussionBaseActivityEntry {


    @Override
    public Long getSecondaryType() {
        return DiscussionActivitySubType.DISCUSSION_ADDED.getSecondaryTypeId();
    }
    @Override
    public String getBody() {

        String template = "%s started a new discussion %s in %s";

        return String.format(template, getUserLink(), getThreadLink(),
                getCategoryLink());

    }

    @Override
    public String getTitle() {
        return  " started a new discussion";
    }

    @Override
    public String getName() {
        return "Added new discussion";
    }


}
