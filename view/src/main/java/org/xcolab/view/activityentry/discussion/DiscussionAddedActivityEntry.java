package org.xcolab.view.activityentry.discussion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.view.i18n.ResourceMessageResolver;

@Component
public class DiscussionAddedActivityEntry extends DiscussionBaseActivityEntry {

    private static final String MESSAGE_CODE = "activities.discussion.discussionadded.message";

    @Autowired
    public DiscussionAddedActivityEntry(ResourceMessageResolver resourceMessageResolver) {
        super(resourceMessageResolver);
    }

    @Override
    public Long getSecondaryType() {
        return DiscussionActivitySubType.DISCUSSION_ADDED.getSecondaryTypeId();
    }

    @Override
    public String getBody() {
        String[] params = {getUserLink(), getThreadLink(), getCategoryLink()};
        return resourceMessageResolver.getLocalizedMessage(MESSAGE_CODE, params);
    }

    @Override
    public String getTitle() {
        return " started a new discussion";
    }

    @Override
    public String getName() {
        return "Added new discussion";
    }
}
