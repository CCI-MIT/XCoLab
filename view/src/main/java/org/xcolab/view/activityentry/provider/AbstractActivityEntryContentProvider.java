package org.xcolab.view.activityentry.provider;

import org.xcolab.client.activities.pojo.ActivityEntry;
import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.exceptions.MemberNotFoundException;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.view.activityentry.ActivityInitializationException;

public abstract class AbstractActivityEntryContentProvider implements ActivityEntryContentProvider {

    protected static final String HYPERLINK_FORMAT = "<a href=\"%s\">%s</a>";

    private ActivityEntry activityEntry;
    private UserWrapper user;

    @Override
    public void initialize(ActivityEntry activityEntry) throws ActivityInitializationException {
        this.activityEntry = activityEntry;
        try {
            user = StaticUserContext.getUserClient().getMember(activityEntry.getUserId());
        } catch (MemberNotFoundException e) {
            user = null;
        }
        initializeInternal();
    }

    protected abstract void initializeInternal() throws ActivityInitializationException;

    protected ActivityEntry getActivityEntry() {
        return activityEntry;
    }

    protected UserWrapper getUser() {
        return user;
    }

    protected String getUserLink() {
        if (user == null) {
            return "(Removed)";
        }
        return String.format(HYPERLINK_FORMAT, getUser().getProfileLinkUrl(),
                getUser().getDisplayName());
    }
}
