package org.xcolab.view.activityentry.provider;

import org.xcolab.client.activities.pojo.IActivityEntry;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.view.activityentry.ActivityInitializationException;

public abstract class AbstractActivityEntryContentProvider implements ActivityEntryContentProvider {

    protected static final String HYPERLINK_FORMAT = "<a href=\"%s\">%s</a>";

    private IActivityEntry activityEntry;
    private Member user;

    @Override
    public void initialize(IActivityEntry activityEntry) throws ActivityInitializationException {
        this.activityEntry = activityEntry;
        try {
            user = MembersClient.getMember(activityEntry.getUserId());
        } catch (MemberNotFoundException e) {
            user = null;
        }
        initializeInternal();
    }

    protected abstract void initializeInternal() throws ActivityInitializationException;

    protected IActivityEntry getActivityEntry() {
        return activityEntry;
    }

    protected Member getUser() {
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
