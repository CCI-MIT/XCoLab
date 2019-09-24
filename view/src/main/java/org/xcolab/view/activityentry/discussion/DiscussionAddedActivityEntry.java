package org.xcolab.view.activityentry.discussion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.client.comment.pojo.ICategory;
import org.xcolab.util.activities.enums.ActivityType;
import org.xcolab.util.activities.enums.DiscussionThreadActivityType;
import org.xcolab.view.activityentry.ActivityInitializationException;
import org.xcolab.view.i18n.ResourceMessageResolver;

@Component
public class DiscussionAddedActivityEntry extends DiscussionBaseActivityEntry {

    private static final String MESSAGE_CODE = "activities.discussion.discussionadded.message";

    private ICategory category;

    @Autowired
    public DiscussionAddedActivityEntry(ResourceMessageResolver resourceMessageResolver) {
        super(resourceMessageResolver);
    }

    @Override
    public void initializeInternal() throws ActivityInitializationException {
        super.initializeInternal();

        category = getThread().getCategory();
        if (category == null) {
            throw new ActivityInitializationException(getActivityEntry().getId());
        }
    }

    @Override
    public ActivityType getActivityType() {
        return DiscussionThreadActivityType.CREATED;
    }

    @Override
    public String getBody() {
        String[] params = {getUserLink(), getThreadLink(), getCategoryLink()};
        return resourceMessageResolver.getLocalizedMessage(MESSAGE_CODE, params);
    }

    @Override
    public String getTitle() {
        return "New discussion in " + category.getName();
    }

    private String getCategoryLink() {
        return String.format(HYPERLINK_FORMAT, category.getLinkUrl(), category.getName());
    }
}
