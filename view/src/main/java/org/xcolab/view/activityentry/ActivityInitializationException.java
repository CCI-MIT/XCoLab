package org.xcolab.view.activityentry;

public class ActivityInitializationException extends Exception {

    public ActivityInitializationException(long activityEntryId) {
        super("Cannot initialize content provider for ActivityEntry " + activityEntryId);
    }

    public ActivityInitializationException(long activityEntryId, Throwable e) {
        super("Cannot initialize content provider for ActivityEntry " + activityEntryId
                + ": " + e.getMessage(), e);
    }
}
