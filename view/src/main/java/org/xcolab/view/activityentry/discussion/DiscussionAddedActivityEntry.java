package org.xcolab.view.activityentry.discussion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.client.activities.enums.ActivityType;
import org.xcolab.client.activities.enums.DiscussionActivityType;
import org.xcolab.view.i18n.ResourceMessageResolver;

@Component
public class DiscussionAddedActivityEntry extends DiscussionBaseActivityEntry {

    private static final String MESSAGE_CODE = "activities.discussion.discussionadded.message";

    @Autowired
    public DiscussionAddedActivityEntry(ResourceMessageResolver resourceMessageResolver) {
        super(resourceMessageResolver);
    }

    @Override
    public ActivityType getActivityType() {
        return DiscussionActivityType.THREAD_ADDED;
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
