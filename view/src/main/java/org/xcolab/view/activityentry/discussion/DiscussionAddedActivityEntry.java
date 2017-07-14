package org.xcolab.view.activityentry.discussion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.view.i18n.ResourceMessageResolver;

@Component
public class DiscussionAddedActivityEntry extends DiscussionBaseActivityEntry {



    @Override
    public Long getSecondaryType() {
        return DiscussionActivitySubType.DISCUSSION_ADDED.getSecondaryTypeId();
    }

    @Autowired
    private ResourceMessageResolver resourceMessageResolver;

    @Override
    public String getBody() {

        String [] params = { getUserLink(), getThreadLink(), getCategoryLink()};
        return resourceMessageResolver.getLocalizedMessage("activities.discussion.discussionadded.message",params);

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
